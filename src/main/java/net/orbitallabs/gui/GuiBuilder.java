package net.orbitallabs.gui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.BuildPacket;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.structures.StructureBigHall;
import net.orbitallabs.structures.StructureCornerHall;
import net.orbitallabs.structures.StructureCrossroad;
import net.orbitallabs.structures.StructureCupola;
import net.orbitallabs.structures.StructureDockingPort;
import net.orbitallabs.structures.StructureGreenHouse;
import net.orbitallabs.structures.StructureHall;
import net.orbitallabs.structures.StructureHallWAirlock;
import net.orbitallabs.structures.StructurePierce;
import net.orbitallabs.structures.StructureRotatable;
import net.orbitallabs.structures.StructureSolarPanel;
import net.orbitallabs.structures.StructureThall;
import net.orbitallabs.structures.StructureWindow;
import net.orbitallabs.utils.FacingUtils;
import net.orbitallabs.utils.OTLoger;
import net.orbitallabs.utils.OrbitalModInfo;

public class GuiBuilder extends GuiModular {
	
	private InventoryPlayer inventory;
	
	public static Map strucutures = new HashMap<Integer, Structure>();
	
	private ResourceLocation texture = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/Builder.png");
	public int Xsize = 224;
	public int Ysize = 160;
	private BlockPos pos;
	private EnumFacing Ndir;
	private int Bid;
	public int STRlastid = -1;
	public int rot = 0;
	public static Map AstG = new HashMap<String, Integer>();
	public Map Ast = new HashMap<Integer, Structure>();
	public GuiBuilderSide sideinv;
	public int meta;
	
	private boolean nullRay = false;
	
	protected RayTraceResult rayTrace(World worldIn, EntityPlayer playerIn, boolean useLiquids)
	{
		float f = playerIn.rotationPitch;
		float f1 = playerIn.rotationYaw;
		double d0 = playerIn.posX;
		double d1 = playerIn.posY + (double) playerIn.getEyeHeight();
		double d2 = playerIn.posZ;
		Vec3d vec3d = new Vec3d(d0, d1, d2);
		float f2 = MathHelper.cos(-f1 * 0.017453292F - (float) Math.PI);
		float f3 = MathHelper.sin(-f1 * 0.017453292F - (float) Math.PI);
		float f4 = -MathHelper.cos(-f * 0.017453292F);
		float f5 = MathHelper.sin(-f * 0.017453292F);
		float f6 = f3 * f4;
		float f7 = f2 * f4;
		double d3 = 5.0D;
		if (playerIn instanceof net.minecraft.entity.player.EntityPlayerMP)
		{
			d3 = ((net.minecraft.entity.player.EntityPlayerMP) playerIn).interactionManager.getBlockReachDistance();
		}
		Vec3d vec3d1 = vec3d.addVector((double) f6 * d3, (double) f5 * d3, (double) f7 * d3);
		return worldIn.rayTraceBlocks(vec3d, vec3d1, useLiquids, !useLiquids, false);
	}
	
	public GuiBuilder(EntityPlayer player)
	{
		super(new ContainerBuilder(player.inventory));
		
		xSize = Xsize;
		ySize = Ysize;
		try
		{
			RayTraceResult result = rayTrace((World) Minecraft.getMinecraft().world, player, false);
			
			if (result == null) nullRay = true;
			else
			{
				pos = result.getBlockPos();
				
				meta = player.world.getBlockState(pos).getBlock().getMetaFromState(player.world.getBlockState(pos));
				Ndir = result.sideHit.getOpposite();
				if (Ndir != EnumFacing.UP && Ndir != EnumFacing.DOWN) pos = FacingUtils.IncreaseByDir(Ndir.getOpposite(), pos, 1);
				inventory = player.inventory;
			}
		} catch (Throwable error)
		{
			OTLoger.logWarn("An error ocured in GuiBuilder(constructor)");
			error.printStackTrace();
		}
		
		sideinv = new GuiBuilderSide(this, true, false);
		this.getModules().add(sideinv);
		
	}
	
	/*
	 * 0 - everything, 1 - everything excluding pierce, 2 - only add structures,
	 * 3 - only window(only rot == 0), 4 - solar panels, 5 - greenhouse, 6 -
	 * pierce
	 */
	
	public static void init()
	{
		RegisterStructure(0, new StructureHall(false));
		RegisterStructure(1, new StructureCornerHall(false), 0);
		RegisterStructure(2, new StructureCrossroad(false));
		RegisterStructure(3, new StructureHallWAirlock(false));
		RegisterStructure(4, new StructureWindow(false), 0);
		RegisterStructure(5, new StructureCupola(false));
		RegisterStructure(6, new StructureDockingPort(false));
		RegisterStructure(7, new StructureSolarPanel(false));
		RegisterStructure(8, new StructureThall(false), 0);
		RegisterStructure(9, new StructureBigHall(false), 0);
		RegisterStructure(10, new StructureGreenHouse());
		RegisterStructure(11, new StructurePierce());
	}
	
	public static void RegisterStructure(int id, Structure strc)
	{
		strucutures.put(id, strc);
	}
	
	public static void RegisterStructure(int id, StructureRotatable strc, int rot)
	{
		strc.setRotation(rot);
		strucutures.put(id, strc);
	}
	
	private int getLines(int size)
	{
		if (size < 3)
		{
			return 0;
		} else if (size > 2 && size < 6)
		{
			return 1;
		} else if (size > 5)
		{
			return 2;
		}
		return 0;
	}
	
	@Override
	public void initGui()
	{
		try
		{
			AstG.clear();
			Ast.clear();
			
			if (nullRay) Minecraft.getMinecraft().displayGuiScreen(null);
			
			if (!nullRay)
			{
				int x = (width - Xsize - 40) / 2;
				int y = (height - Ysize) / 2;
				this.cornerX = x;
				this.cornerY = y;
				
				for (int a = 0; a < strucutures.size(); a++)
				{
					World world = (World) Minecraft.getMinecraft().world;
					if (((Structure) strucutures.get(a)).Check(world, Ndir, pos, meta))
					{
						if (((Structure) strucutures.get(a)).Check(world, Ndir, pos, meta))
						{
							Ast.put(Ast.size(), ((Structure) strucutures.get(a)));
						}
						
					}
				}
				int xm = 0;
				int ym = 0;
				
				for (int i = 0; i < Ast.size(); i++)
				{
					ym = getLines(i);
					xm = i - (ym * 3);
					// GLoger.logInfo(xm+" "+ym+" "+i);
					buttonList.add(new GuiButtonBuilder(i, x + 12 + ((xm) * 68), y + 22 + (ym * 38), ((Structure) Ast.get(i)).getName()));
					((GuiButtonBuilder) buttonList.get(i)).setDirection(Ndir);
					
					if (((Structure) Ast.get(i)) instanceof StructureRotatable)
					{
						((GuiButtonBuilder) buttonList.get(i)).setRotation(((StructureRotatable) Ast.get(i)).nextPossibleValue(-1, Ndir, meta));
					}
					AstG.put(((Structure) Ast.get(i)).getUnlocalizedName(), i);
					Bid++;
				}
				if (fontRendererObj.getStringWidth(I18n.format("builder.build_button.name")) + 6 < 40)
				{
					buttonList.add(new GuiButton(Bid, x + 159, y + 135, 40, 20, I18n.format("builder.build_button.name")));
				} else
				{
					buttonList.add(new GuiButton(Bid, x + 159, y + 135, fontRendererObj.getStringWidth(I18n.format("builder.build_button.name")) + 6, 20,
							I18n.format("builder.build_button.name")));
				}
				
				super.initGui();
			}
		} catch (Throwable error)
		{
			OTLoger.logWarn("An error ocured in GuiBuilder(constructor)", error);
		}
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		if (mc.player.inventory.getItemStack().isEmpty() && sideinv.theSlot != null && sideinv.theSlot.slotNumber < sideinv.shownItems.length
				&& sideinv.shownItems[sideinv.theSlot.slotNumber] != null)
		{
			this.renderToolTip(sideinv.shownItems[sideinv.theSlot.slotNumber], mouseX, mouseY);
			sideinv.theSlot = null;
		}
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		try
		{
			super.actionPerformed(button);
		} catch (IOException e)
		{
		}
		try
		{
			if (button.id == Bid)
			{
				if (STRlastid != -1)
				{
					if (sideinv.currentPossible)
					{
						rot = ((GuiButtonBuilder) buttonList.get(STRlastid)).rot;
						PacketHandler.sendToServer(new BuildPacket(Ndir, ((Structure) Ast.get(STRlastid)).getUnlocalizedName(), pos.getX(), pos.getY(), pos.getZ(), rot,
								this.inventory.player.getHeldItem(EnumHand.MAIN_HAND)));
						// GLoger.logInfo("Sending packet");
						Minecraft.getMinecraft().player.closeScreen();
					}
				}
			} else
			{
				
				if (STRlastid == button.id)
				{
					rot = ((GuiButtonBuilder) button).rot;
					if (Ast.get(STRlastid) instanceof StructureRotatable)
					{
						((GuiButtonBuilder) button).setRotation(((StructureRotatable) Ast.get(STRlastid)).nextPossibleValue(rot, Ndir, meta));
						
					}
					rot = ((GuiButtonBuilder) button).rot;
				} else
				{
					if (STRlastid != -1)
					{
						((GuiButtonBuilder) buttonList.get(STRlastid)).setEnabled(false);
						
					}
					
					STRlastid = button.id;
					((GuiButtonBuilder) button).setEnabled(true);
					if (Ast.get(STRlastid) instanceof StructureRotatable && ((GuiButtonBuilder) button).rot == 0)
					{
						((GuiButtonBuilder) button).setRotation(((StructureRotatable) Ast.get(STRlastid)).nextPossibleValue(-1, Ndir, meta));
						rot = ((GuiButtonBuilder) button).rot;
					} else
					{
						rot = ((GuiButtonBuilder) button).rot;
					}
				}
			}
		} catch (Throwable error)
		{
			OTLoger.logWarn("An error ocured in GuiBuilder", error);
		}
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		int x = (width - Xsize - 40) / 2;
		int y = (height - Ysize) / 2;
		this.cornerX = x;
		this.cornerY = y;
		
		drawTexturedModalRect(x, y, 0, 0, Xsize, Ysize);
		
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		
		if (buttonList.size() < Bid)
		{
			
		}
		
		if (STRlastid == -1)
		{
			((GuiButton) buttonList.get(Bid)).enabled = false;
		} else
		{
			if (buttonList.size() > Bid)
			{
				if (sideinv.currentPossible)
				{
					((GuiButton) buttonList.get(Bid)).enabled = true;
				} else
				{
					((GuiButton) buttonList.get(Bid)).enabled = false;
				}
			}
		}
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		super.drawGuiContainerForegroundLayer(par1, par2);
		
		fontRendererObj.drawString(I18n.format("builder.name"), (int) (xSize / 4.5D) - (fontRendererObj.getStringWidth(I18n.format("builder.name")) / 2) + 70, 10, 4210752, false);
	}
	
}

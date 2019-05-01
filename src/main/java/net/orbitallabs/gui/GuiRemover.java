package net.orbitallabs.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.DeconstructPacket;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.tiles.TileEntityRemoveInfo;
import net.orbitallabs.utils.ChatUtils;
import net.orbitallabs.utils.OrbitalModInfo;

public class GuiRemover extends GuiContainer {
	private InventoryPlayer inventory;
	
	public static Map strucutures = new HashMap<Integer, Structure>();
	
	private ResourceLocation texture = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/remover.png");
	private int Xsize = 157;
	private int Ysize = 125;
	
	BlockPos pos;
	private World world;
	
	public static Structure object;
	public static List<Structure> ChildObjects = new ArrayList<Structure>();
	public static List<Structure> addObjects = new ArrayList<Structure>();
	public static boolean CloseThis = false;
	public static boolean reInit = false;
	private int objCount = 0;
	public static int move;
	public static boolean[] Iselected = new boolean[] { false, false, false, false, false, false };
	public boolean hasChilds = false;
	
	public GuiRemover(EntityPlayer player, TileEntityRemoveInfo te)
	{
		super(new ContainerRemover(player.inventory, te));
		hasChilds = false;
		/*
		 * object = null; addObjects = new ArrayList<Structure>(); ChildObjects
		 * = new ArrayList<Structure>();
		 */
		
		pos = te.getPos();
		world = player.getEntityWorld();
		
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
	
	public static void prepareToOpen()
	{
		reInit = false;
		object = null;
		addObjects = new ArrayList<Structure>();
		ChildObjects = new ArrayList<Structure>();
	}
	
	@Override
	public void initGui()
	{
		
		int x = (width - Xsize - 40) / 2;
		int y = (height - Ysize) / 2;
		
		super.initGui();
		
		this.buttonList.clear();
		
		if (addObjects != null && addObjects.size() > 0)
		{
			objCount = addObjects.size();
		}
		this.buttonList.add(new GuiVerticalSlider(0, x + 134, y + 15, 10, 88, "R", "R", 0, objCount - 3, 0, true, false));
		
		this.buttonList.add(new SmallGuiButton(1, x + 65, y + 106, 80, I18n.format("remover.deconstruct_button.name")));
		if (ChildObjects != null)
		{
			if (ChildObjects.size() == 0)
			{
				if (object != null)
				{
					this.buttonList.add(new GuiButtonRemover(2, x + 8, y + 16, object.getUnlocalizedName(), object, y));
					objCount++;
				}
			} else
			{
				int connNum = 0;
				for (int i = 0; i < ChildObjects.size(); i++)
				{
					if (ChildObjects.get(i).connections.size() > 0)
					{
						connNum++;
					}
				}
				if (ChildObjects.size() != connNum)
				{
					hasChilds = true;
				} else
				{
					if (object != null)
					{
						this.buttonList.add(new GuiButtonRemover(2, x + 8, y + 16, object.getUnlocalizedName(), object, y));
						objCount++;
					}
				}
			}
			if ((objCount < 3 && !hasChilds) || (objCount < 4 && hasChilds)) ((GuiVerticalSlider) this.buttonList.get(0)).enabled = false;
			if (addObjects != null && addObjects.size() > 0)
			{
				int offs = 0;
				if (hasChilds)
				{
					this.buttonList.add(new GuiButtonRemover(2, x, y, "", object, 0));
					((GuiButtonRemover) this.buttonList.get(2)).visSelf = false;
					((GuiButtonRemover) this.buttonList.get(2)).visible = false;
					offs = 22;
				}
				for (int i = 0; i < addObjects.size(); i++)
				{
					this.buttonList.add(new GuiButtonRemover(3 + i, x + 8, y + 38 + (22 * i) - offs, addObjects.get(i).getUnlocalizedName(), addObjects.get(i), y));
					
				}
				hasChilds = false;
			}
		}
		
	}
	
	@Override
	public void handleMouseInput() throws IOException
	{
		super.handleMouseInput();
		int i = Mouse.getEventDWheel();
		
		if (i != 0 && !((objCount < 3 && !hasChilds) || (objCount < 4 && hasChilds)))
		{
			// int j = (int) ((GuiVerticalSlider) buttonList.get(0)).maxValue;
			
			if (i > 0)
			{
				i = 1;
			}
			
			if (i < 0)
			{
				i = -1;
			}
			
			((GuiVerticalSlider) buttonList.get(0)).sliderValue -= (double) i / (objCount - 2);
			if (((GuiVerticalSlider) buttonList.get(0)).sliderValue > 1F)
			{
				((GuiVerticalSlider) buttonList.get(0)).sliderValue = 1;
			}
			if (((GuiVerticalSlider) buttonList.get(0)).sliderValue < 0)
			{
				((GuiVerticalSlider) buttonList.get(0)).sliderValue = 0;
			}
			//   this.currentScroll = (float)((double)this.currentScroll - (double)i / (double)j);
			//   this.currentScroll = MathHelper.clamp(this.currentScroll, 0.0F, 1.0F);
			//   ((GuiContainerCreative.ContainerCreative)this.inventorySlots).scrollTo(this.currentScroll);
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
		if (button.id != 0 && button instanceof GuiButtonRemover)
		{
			if (((GuiButtonRemover) button).pressed)
			{
				((GuiButtonRemover) button).setPressed(false);
			} else
			{
				((GuiButtonRemover) button).setPressed(true);
			}
		}
		if (button.id == 1)
		{
			List<Structure> toDobjects = new ArrayList<Structure>();
			for (int i = 2; i < buttonList.size(); i++)
			{
				GuiButtonRemover but = (GuiButtonRemover) buttonList.get(i);
				
				if (but.pressed)
				{
					if (i == 2)
					{
						for (int i2 = 0; i2 < addObjects.size(); i2++)
						{
							toDobjects.add(addObjects.get(i2));
						}
						
						Structure obj = Structure.FindStructure(but.strName);
						obj.Configure(but.strPos, but.rot, but.dir);
						toDobjects.add(obj);
						
						break;
					} else
					{
						Structure obj = Structure.FindStructure(but.strName);
						obj.Configure(but.strPos, but.rot, but.dir);
						toDobjects.add(obj);
					}
				}
			}
			if (toDobjects.size() == 0)
			{
				Minecraft.getMinecraft().player.closeScreen();
				Minecraft.getMinecraft().player.sendMessage(ChatUtils.modifyColor(new TextComponentString(I18n.format("remover.no_selected.name")), TextFormatting.RED));
			} else
			{// sending packet
				PacketHandler.sendToServer(new DeconstructPacket(toDobjects, new int[] { pos.getX(), pos.getY(), pos.getZ() }));
				Minecraft.getMinecraft().player.closeScreen();
			}
		}
	}
	
	/*
	 * @Override public void drawScreen(int x, int y, float m) {
	 * super.drawScreen(x, y, m); drawDefaultBackground(); }
	 */
	
	private boolean AnyTrue(boolean[] bool)
	{
		for (int i = 0; i < bool.length; i++)
		{
			if (bool[i])
			{
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
		if (CloseThis)
		{
			CloseThis = false;
			Minecraft.getMinecraft().player.closeScreen();
		}
		
		if (object != null && reInit == false)
		{
			reInit = true;
			initGui();
		}
		
		if (buttonList.size() > 0)
		{
			move = ((GuiVerticalSlider) buttonList.get(0)).getValueInt();
			if (!hasChilds)
			{
				if (buttonList.size() > 2)
				{
					for (int i = 2; i < buttonList.size(); i++)
					{
						if (((GuiButtonRemover) buttonList.get(i)).pressed)
						{
							if (i - 2 < Iselected.length)
							{
								Iselected[i - 2] = true;
							}
						} else
						{
							if (i - 2 < Iselected.length)
							{
								Iselected[i - 2] = false;
							}
						}
					}
					
					if (!AnyTrue(Iselected))
					{
						((GuiButton) buttonList.get(1)).enabled = false;
					} else
					{
						((GuiButton) buttonList.get(1)).enabled = true;
					}
				}
			} else((GuiButton) buttonList.get(1)).enabled = false;
			
		}
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		int x = (width - Xsize - 40) / 2;
		int y = (height - Ysize) / 2;
		drawTexturedModalRect(x, y, 0, 0, Xsize, Ysize);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int MouseX, int MouseY)
	{
		
		fontRenderer.drawString(I18n.format("remover.name"), (int) (xSize / 4.5D) - (fontRenderer.getStringWidth(I18n.format("remover.name")) / 2) + 15, 25, 4210752, false);
		
		if (hasChilds)
		{
			
			String tip = I18n.format("remover.haschilds_tip.name"); // 125
			String[] words = tip.split("[\\s']");
			List<String> finalW = new ArrayList<>();
			String lastLine = "";
			for (int i = 0; i < words.length; i++)
			{
				if (fontRenderer.getStringWidth(lastLine + " " + words[i]) > 125)
				{
					finalW.add(lastLine);
					lastLine = words[i];
				} else
				{
					lastLine = lastLine + " " + words[i];
				}
			}
			finalW.add(lastLine);
			for (int i = 0; i < finalW.size(); i++)
			{
				fontRenderer.drawString(finalW.get(i), (int) (125 / 4.5D) - fontRenderer.getStringWidth(finalW.get(i)) / 2 + 32, 50 + (i * 9), 14737632, true);
			}
			
		}
	}
	
}

package net.orbitallabs.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementCheckbox.ICheckBoxCallback;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.orbitallabs.gui.GuiVerticalSlider.ISlider;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.BuildPacket;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.structures.StructureBigHall;
import net.orbitallabs.structures.StructureCornerHall;
import net.orbitallabs.structures.StructureCrossroad;
import net.orbitallabs.structures.StructureCupola;
import net.orbitallabs.structures.StructureData;
import net.orbitallabs.structures.StructureDockingPort;
import net.orbitallabs.structures.StructureGreenHouse;
import net.orbitallabs.structures.StructureHall;
import net.orbitallabs.structures.StructureHallWAirlock;
import net.orbitallabs.structures.StructurePirs;
import net.orbitallabs.structures.StructureRotatable;
import net.orbitallabs.structures.StructureSolarPanel;
import net.orbitallabs.structures.StructureThall;
import net.orbitallabs.structures.StructureWindow;
import net.orbitallabs.utils.FacingUtils;
import net.orbitallabs.utils.OTLoger;
import net.orbitallabs.utils.OrbitalModInfo;
import net.orbitallabs.utils.OreDictItemStack;

public class GuiBuilder extends GuiContainer implements ISlider, ICheckBoxCallback, IGUISliderController {
	
	private InventoryPlayer inventory;
	
	public static Map<Integer, Structure> strucutures = new HashMap<Integer, Structure>();
	
	private ResourceLocation texture = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/builder.png");
	//---------------- left side
	public int Xsize = 233;
	public int Ysize = 165;
	private BlockPos pos;
	private EnumFacing Ndir;
	private int Bid;
	public int STRlastid = -1;
	public int rot = 0;
	public static Map AstG = new HashMap<String, Integer>();
	public Map Ast = new HashMap<Integer, Structure>();
	public GuiVerticalSlider slider;
	public int meta;
	public int move;
	private boolean nullRay = false;
	
	//---------------- right side
	
	public List<Slot> slots = new ArrayList();
	public Slot theSlot;
	boolean init = false;
	private GuiButton selectedButton;
	//public GuiElementCheckbox checkbox;
	
	public static NonNullList<ItemStack> foundItems = NonNullList.create();
	public static boolean dataRecived;
	private int lastBid = -1;
	private int lastRot = -1;
	/** use after drawBackground */
	public boolean currentPossible = false;
	
	public NonNullList<ItemStack> shownItems = NonNullList.withSize(12, ItemStack.EMPTY);
	
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
		
		//sideinv = new GuiBuilderSide(this, true, false);
		//this.getModules().add(sideinv);
		
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
		RegisterStructure(11, new StructurePirs());
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
	
	@Override
	public void initGui()
	{
		try
		{
			AstG.clear();
			Ast.clear();
			buttonList.clear();
			Bid = 0;
			
			if (nullRay) Minecraft.getMinecraft().displayGuiScreen(null);
			
			if (!nullRay)
			{
				int x = (width - Xsize - 40) / 2;
				int y = (height - Ysize) / 2;
				
				for (int a = 0; a < strucutures.size(); a++)
				{
					World world = (World) Minecraft.getMinecraft().world;
					if (strucutures.get(a).Check(world, Ndir, pos, meta))
					{
						if (strucutures.get(a).Check(world, Ndir, pos, meta))
						{
							Ast.put(Ast.size(), strucutures.get(a));
						}
						
					}
				}
				
				for (int i = 0; i < Ast.size(); i++)
				{
					// GLoger.logInfo(xm+" "+ym+" "+i);
					buttonList.add(new GuiButtonBuilder(i, x + 11, y + 24 + (i * 20), ((Structure) Ast.get(i)).getName(), y, this));
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
					buttonList.add(new SmallGuiButton(Bid, x + 159, y + 145, 40, I18n.format("builder.build_button.name")));
				} else
				{
					buttonList.add(new SmallGuiButton(Bid, x + 159, y + 145, fontRendererObj.getStringWidth(I18n.format("builder.build_button.name")) + 6,
							I18n.format("builder.build_button.name")));
				}
				
				this.buttonList.add(slider = new GuiVerticalSlider(Bid + 1, x + 92, y + 23, 10, 120, "R", "R", 0, (double) Ast.size() - 6, 0, true, false, this));
				//	this.buttonList.add(checkbox = new GuiElementCheckbox(Bid + 2, this, x + 112, y + 70, ""));
				//	checkbox.visible = false;
				
				move = 0;
				if (Ast.size() <= 6)
				{
					slider.enabled = false;
				}
				super.initGui();
			}
		} catch (Throwable error)
		{
			OTLoger.logWarn("An error ocured in GuiBuilder(constructor)", error);
		}
	}
	
	@Override
	public void handleMouseInput() throws IOException
	{
		super.handleMouseInput();
		int i = Mouse.getEventDWheel();
		
		if (i != 0 && Ast.size() > 6 && Bid + 1 < buttonList.size())
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
			
			slider.sliderValue -= (double) i / (Ast.size() - 6);
			if (slider.sliderValue > 1F)
			{
				slider.sliderValue = 1;
			}
			if (slider.sliderValue < 0)
			{
				slider.sliderValue = 0;
			}
			
			move = slider.getValueInt();
			//   this.currentScroll = (float)((double)this.currentScroll - (double)i / (double)j);
			//   this.currentScroll = MathHelper.clamp(this.currentScroll, 0.0F, 1.0F);
			//   ((GuiContainerCreative.ContainerCreative)this.inventorySlots).scrollTo(this.currentScroll);
		}
	}
	
	public boolean haveContainerItem(OreDictItemStack is)
	{
		if (Minecraft.getMinecraft().player.capabilities.isCreativeMode)
		{
			return true;
		}
		if (!dataRecived)
		{
			return false;
		}
		for (int i = 0; i < foundItems.size(); i++)
		{
			if (foundItems.get(i).isEmpty())
			{
				continue;
			}
			OreDictItemStack curr = new OreDictItemStack(foundItems.get(i));
			if (is != null && curr != null)
			{
				if (is.isStackEqual(curr, false))
				{
					curr.example.shrink(is.example.getCount());
					if (curr.example.getCount() == 0)
					{
						foundItems.remove(i);
					}
					return true;
				}
			}
		}
		return false;
	}
	
	@Override
	protected void renderToolTip(ItemStack stack, int x, int y)
	{
		List<String> list = stack.getTooltip(this.mc.player, this.mc.gameSettings.advancedItemTooltips);
		
		int[] ores = OreDictionary.getOreIDs(stack);
		
		if (ores != null && ores.length > 0)
		{
			list.add(1, "any " + OreDictionary.getOreName(ores[0]));
		}
		
		for (int i = 0; i < list.size(); ++i)
		{
			if (i == 0)
			{
				list.set(i, stack.getRarity().rarityColor + (String) list.get(i));
			} else
			{
				list.set(i, TextFormatting.GRAY + (String) list.get(i));
			}
		}
		
		FontRenderer font = stack.getItem().getFontRenderer(stack);
		net.minecraftforge.fml.client.config.GuiUtils.preItemToolTip(stack);
		this.drawHoveringText(list, x, y, (font == null ? fontRendererObj : font));
		net.minecraftforge.fml.client.config.GuiUtils.postItemToolTip();
	}
	
	public void drawSlot(Slot slot, int mouseX, int mouseY)
	{
		int i = slot.xPos;
		int j = slot.yPos;
		ItemStack itemstack = shownItems.get(slot.slotNumber);
		itemRender.zLevel = 0.0F;
		this.zLevel = 0.0F;
		// mark
		if (!itemstack.isEmpty())
		{
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			
			boolean st = ((SlotGhost) slot).state;
			this.drawTexturedModalRect(i, j, 103 + (st ? 18 : 0), 167, 16, 16);
			GL11.glEnable(GL11.GL_LIGHTING);
		}
		// slot selection
		if (this.isMouseOverSlot(slot, mouseX, mouseY))
		{
			theSlot = slot;
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GlStateManager.enableBlend();
			int j1 = slot.xPos;
			int k1 = slot.yPos;
			GL11.glColorMask(true, true, true, false);
			this.drawGradientRect(j1, k1, j1 + 16, k1 + 16, -2130706433, -2130706433);
			GL11.glColorMask(true, true, true, true);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glEnable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_DEPTH_TEST);
		}
		
		if (!itemstack.isEmpty())
		{
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			RenderHelper.enableGUIStandardItemLighting();
			drawItemStack(itemstack, i, j);
		}
	}
	
	public boolean isMouseOverSlot(Slot slot, int mX, int mY)
	{
		return mX >= slot.xPos - 1 && mX < slot.xPos + 16 + 1 && mY >= slot.yPos - 1 && mY < slot.yPos + 16 + 1;
	}
	
	public void drawItemStack(ItemStack stack, int x, int y)
	{
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
		this.zLevel = 200.0F;
		itemRender.zLevel = 200.0F;
		FontRenderer font = null;
		if (!stack.isEmpty()) font = stack.getItem().getFontRenderer(stack);
		if (font == null) font = fontRendererObj;
		itemRender.renderItemAndEffectIntoGUI(stack, x, y);
		itemRender.renderItemOverlayIntoGUI(font, stack, x, y, String.valueOf(stack.getCount()));
		this.zLevel = 0.0F;
		itemRender.zLevel = 0.0F;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		if (mc.player.inventory.getItemStack().isEmpty() && theSlot != null && theSlot.slotNumber < shownItems.size() && !shownItems.get(theSlot.slotNumber).isEmpty())
		{
			renderToolTip(shownItems.get(theSlot.slotNumber), mouseX, mouseY);
			theSlot = null;
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
					if (currentPossible)
					{
						rot = ((GuiButtonBuilder) buttonList.get(STRlastid)).rot;
						PacketHandler.sendToServer(new BuildPacket(Ndir, ((Structure) Ast.get(STRlastid)).getUnlocalizedName(), pos.getX(), pos.getY(), pos.getZ(), rot,
								this.inventory.player.getHeldItem(EnumHand.MAIN_HAND)));
						// GLoger.logInfo("Sending packet");
						Minecraft.getMinecraft().player.closeScreen();
					}
				}
			} else if (button instanceof GuiButtonBuilder)
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
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		
		int x = (width - Xsize - 40) / 2;
		int y = (height - Ysize) / 2;
		
		drawTexturedModalRect(x, y, 0, 0, Xsize, Ysize);
		if (STRlastid == -1)
		{
			drawTexturedModalRect(x + 112, y + 105, 109, 24, 108, 36);
		}
		
		rightGuiBackgroundLayer(mouseX, mouseY);
		
		if (Bid < buttonList.size())
		{
			
			if (STRlastid == -1)
			{
				((GuiButton) buttonList.get(Bid)).enabled = false;
			} else
			{
				if (buttonList.size() > Bid)
				{
					if (currentPossible)
					{
						((GuiButton) buttonList.get(Bid)).enabled = true;
					} else
					{
						((GuiButton) buttonList.get(Bid)).enabled = false;
					}
				}
			}
		}
		
	}
	
	public void rightGuiBackgroundLayer(int mouseX, int mouseY)
	{
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		
		int x = (width - Xsize - 40) / 2;
		int y = (height - Ysize) / 2;
		
		if (STRlastid != -1)
		{
			if (!init)
			{
				slots.clear();
				for (int i = 0; i < 2; i++)
				{
					for (int j = 0; j < 6; j++)
					{
						slots.add(new SlotGhost((IInventory) null, (i * 6) + j, x + 113 + (j * 18), y + 106 + (i * 18)));
					}
				}
				init = true;
			}
			
			Structure str = ((Structure) Ast.get(STRlastid)).copy();
			if (str instanceof StructureRotatable)
			{
				((StructureRotatable) str).setRotation(rot);
			}
			StructureData data = str.getStructureData();
			
			if (data.requiredItems.size() > 0 && (lastBid != STRlastid || lastRot != rot))
			{
				NonNullList<ItemStack> backup = NonNullList.create();
				for (int i = 0; i < foundItems.size(); i++)
				{
					backup.add(foundItems.get(i).copy());
				}
				for (int i = 0; i < shownItems.size(); i++)
				{
					if (i < data.requiredItems.size())
					{
						shownItems.set(i, data.requiredItemsExamp.get(i));
						((SlotGhost) slots.get(i)).state = haveContainerItem(data.requiredItems.get(i));
					} else shownItems.set(i, ItemStack.EMPTY);
				}
				foundItems.clear();
				for (int i = 0; i < backup.size(); i++)
				{
					foundItems.add(backup.get(i).copy());
				}
				if (lastBid != STRlastid || lastRot != rot)
				{
					boolean skipped = false;
					for (int i = 0; i < data.requiredItems.size(); i++)
					{
						if (!haveContainerItem(data.requiredItems.get(i)))
						{
							this.currentPossible = false;
							skipped = true;
							break;
						}
					}
					if (!skipped)
					{
						currentPossible = true;
					}
				}
				foundItems = backup;
				lastBid = STRlastid;
				lastRot = rot;
				
			} else if (lastBid != STRlastid)
			{
				shownItems = NonNullList.create();
			}
			
			// 9 77
			
			GL11.glPushMatrix();
			
			for (int i = 0; i < slots.size(); i++)
			{
				drawSlot(slots.get(i), mouseX, mouseY);
			}
			GL11.glPopMatrix();
			
			//	GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			//		RenderHelper.disableStandardItemLighting();
			//	GL11.glDisable(GL11.GL_LIGHTING);
			//	GL11.glDisable(GL11.GL_DEPTH_TEST);
			
			//	for (int k = 0; k < this.buttonList.size(); ++k)
			//	{
			//		((GuiButton) this.buttonList.get(k)).drawButton(this.mc, mouseX, mouseY);
			//}
			//	RenderHelper.enableGUIStandardItemLighting();
			//	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			//	GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		fontRendererObj.drawString(I18n.format("builder.name"), (int) 30 - (fontRendererObj.getStringWidth(I18n.format("builder.name")) / 2), 10, 4210752, false);
		
		rightGuiForegroundLayer(par1, par2);
	}
	
	public void rightGuiForegroundLayer(int mouseX, int mouseY)
	{
		if (STRlastid != -1)
		{
			
			/*
			 * @noForm Structure name : hall Connections : 1 Add connections : 2
			 * Special fucntions : none Required items :
			 * 
			 * @formEn
			 */
			
			Structure str = ((Structure) Ast.get(STRlastid)).copy();
			if (str instanceof StructureRotatable)
			{
				((StructureRotatable) str).setRotation(rot);
			}
			
			//			if (str.haveReplaceableItems())
			//			{
			//				checkbox.visible = true;
			//				
			//				if (fontRendererObj.getStringWidth(str.getGuiCheckboxText()) < 96)
			//				{
			//					simpleText(str.getGuiCheckboxText(), 15, 52);
			//				} else
			//				{
			//					int ch = 108 / fontRendererObj.getStringWidth("h");
			//					String[] tokens = str.getGuiCheckboxText().split("[\\s']");
			//					
			//					if (tokens.length > 0)
			//					{
			//						String out = "";
			//						int j = 0;
			//						for (int i = 0; i < tokens.length; i++)
			//						{
			//							if ((out + (i == 0 ? "" : " ") + tokens[i]).length() < ch)
			//							{
			//								out = out + (i == 0 ? "" : " ") + tokens[i];
			//								j++;
			//							} else
			//							{
			//								break;
			//							}
			//						}
			//						simpleText(out, 15, 52);
			//						out = "";
			//						
			//						if (tokens.length > j)
			//						{
			//							for (int i = j; i < tokens.length; i++)
			//							{
			//								out = out + (i == j ? "" : " ") + tokens[i];
			//							}
			//							simpleText(out, 15, 60);
			//						}
			//					}
			//				}
			//				
			//				//checkbox.displayString = str.getGuiCheckboxText();
			//			} else
			//			{
			//				checkbox.visible = false;
			//			}
			
			StructureData data = str.getStructureData();
			if (data.specialFunc.equals("none"))
			{
				data.specialFunc = I18n.format("builder.side_info.special_func.none");
			}
			
			simpleText(I18n.format("builder.properties.name") + ":", 0, -8);
			
			String name = data.name;
			if (Minecraft.getMinecraft().gameSettings.forceUnicodeFont)
			{
				int dist = 8;
				
				int T = 0;
				// String name =
				// BuildHandler.getLocolizedName(str.getUnlocalizedName(),
				// build.rot, true);
				
				int width = fontRendererObj.getStringWidth(I18n.format("builder.side_info.name") + ": " + name);
				if (width > 106)
				{
					simpleText(I18n.format("builder.side_info.name") + ":", dist * T++, 5);
					simpleText(name, 0, 5 + dist * T++);
				} else
				{
					simpleText(I18n.format("builder.side_info.name") + ": " + name, 0, 5 + dist * T++);
					
				}
				simpleText(I18n.format("builder.side_info.connections") + ": " + data.mainConnect, 0, 5 + dist * T++);
				simpleText(I18n.format("builder.side_info.add_connections") + ": " + data.addConnect, 0, 5 + dist * T++);
				if (fontRendererObj.getStringWidth(I18n.format("builder.side_info.special_func") + data.specialFunc) > 106)
				{
					simpleText(I18n.format("builder.side_info.special_func") + ":", 0, 5 + dist * T++);//
					simpleText(data.specialFunc, 0, 5 + dist * T++);
					simpleText(I18n.format("builder.side_info.required") + ": ", 0, 83 - dist);
				} else
				{
					simpleText(I18n.format("builder.side_info.special_func") + ": " + data.specialFunc, 0, 5 + dist * T++);
					simpleText(I18n.format("builder.side_info.required") + ": ", 0, 83 - dist);
				}
			} else
			{
				int dist = 9;
				int base = 0;
				
				if (fontRendererObj.getStringWidth(I18n.format("builder.side_info.short.name") + ": " + name) <= 106)
				{
					simpleText(I18n.format("builder.side_info.short.name") + ": " + name, 0, 5 + dist * base++);
				} else
				{
					int nL = fontRendererObj.getStringWidth(I18n.format("builder.side_info.short.name") + ": ");
					int ch = (106 - nL) / fontRendererObj.getStringWidth("h");
					String[] tokens = name.split("[\\s']");
					if (tokens.length > 0 && tokens[0].length() <= ch)
					{
						simpleText(I18n.format("builder.side_info.short.name") + ": " + tokens[0], 0, 5 + dist * base++);
						if (tokens.length != 1)
						{
							String out = "";
							for (int i = 1; i < tokens.length; i++)
							{
								out = out + (i == 1 ? "" : " ") + tokens[i];
							}
							simpleText(out, 0, 5 + dist * base++);
						}
					} else if (tokens.length > 0)
					{
						String out = "";
						for (int i = 0; i < tokens.length; i++)
						{
							out = out + (i == 0 ? "" : " ") + tokens[i];
						}
						simpleText(I18n.format("builder.side_info.short.name") + ":", 0, 5 + dist * base++);
						simpleText(out, 0, 5 + dist * base++);
					}
				}
				simpleText(I18n.format("builder.side_info.short.connections") + ": " + data.mainConnect, 0, 5 + dist * base++);
				simpleText(I18n.format("builder.side_info.short.add_connections") + ": " + data.addConnect, 0, 5 + dist * base++);
				if (fontRendererObj.getStringWidth(I18n.format("builder.side_info.short.special_func") + data.specialFunc) > 106)
				{
					simpleText(I18n.format("builder.side_info.short.special_func") + ":", 0, 5 + dist * base++);
					simpleText(data.specialFunc, 0, 5 + dist * base++);
					simpleText(I18n.format("builder.side_info.short.required") + ": ", 0, 83 - dist);
				} else
				{
					simpleText(I18n.format("builder.side_info.short.special_func") + ": " + data.specialFunc, 0, 5 + dist * base++);
					simpleText(I18n.format("builder.side_info.short.required") + ": ", 0, 83 - dist);
				}
			}
		}
	}
	
	@Override
	public void onChangeSliderValue(GuiVerticalSlider slider)
	{
		move = slider.getValueInt();
	}
	
	public void simpleText(String text, int x, int y)
	{// I18n.format("builder.name")
		int X = 92;
		int Y = 20;
		fontRendererObj.drawString(text, x + X, y + Y, 4210752, false);
	}
	
	@Override
	public void onSelectionChanged(GuiElementCheckbox checkbox, boolean newSelected)
	{
		
	}
	
	@Override
	public boolean canPlayerEdit(GuiElementCheckbox checkbox, EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public boolean getInitiallySelected(GuiElementCheckbox checkbox)
	{
		return false;
	}
	
	@Override
	public void onIntruderInteraction()
	{
	}
	
	@Override
	public int getMove()
	{
		return move;
	}
	
}

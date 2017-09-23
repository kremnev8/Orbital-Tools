package net.orbitallabs.gui;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.structures.StructureData;
import net.orbitallabs.structures.StructureRotatable;
import net.orbitallabs.utils.OrbitalModInfo;
import net.orbitallabs.utils.OreDictItemStack;

public class GuiBuilderSide extends GuiModule {
	
	public static final int Xsize = 85;
	public static final int Ysize = 137;
	public int x;
	public int y;
	
	private ResourceLocation texture = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/Builder_side.png");
	
	public ItemStack[] shownItems = new ItemStack[9];
	public List<Slot> slots = new ArrayList();
	public Slot theSlot;
	boolean init = false;
	private GuiButton selectedButton;
	
	public static List<ItemStack> foundItems = new ArrayList();
	public static boolean dataRecived;
	private int lastBid = -1;
	private int lastRot = -1;
	/** use after drawBackground */
	public boolean currentPossible = false;
	private int lastSliderV = -1;
	
	public GuiBuilderSide(GuiModular parent, boolean right, boolean bottom)
	{
		super(parent, right, bottom);
		lastBid = -1;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		init = false;
		
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton)
	{
		
		if (mouseButton == 0)
		{
			for (int l = 0; l < this.buttonList.size(); ++l)
			{
				GuiButton guibutton = (GuiButton) this.buttonList.get(l);
				
				if (guibutton.mousePressed(this.mc, mouseX, mouseY))
				{
					this.selectedButton = guibutton;
				}
			}
		}
	}
	
	@Override
	public void mouseReleased(int mouseX, int mouseY, int state)
	{
		if (this.selectedButton != null && state == 0)
		{
			this.selectedButton.mouseReleased(mouseX, mouseY);
			this.selectedButton = null;
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
	public void drawGuiBackgroundLayer(int mouseX, int mouseY)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		GuiBuilder build = (GuiBuilder) parent;
		
		x = build.cornerX + build.Xsize - 4;
		y = build.cornerY + 15;
		if (build.STRlastid != -1)
		{
			this.isEnabled = true;
			this.guiLeft = x;
			this.guiTop = y;
			this.xSize = Xsize;
			this.ySize = Ysize;
			drawTexturedModalRect(x, y, 0, 0, Xsize, Ysize);
			if (!init)
			{
				slots.clear();
				buttonList.clear();
				this.buttonList.add(new GuiVerticalSlider(0, x + 65, y + 76, 10, 52, "R", "R", 0, 5, 0, true, false));
				((GuiVerticalSlider) this.buttonList.get(0)).enabled = false;
				for (int i = 0; i < 3; i++)
				{
					for (int j = 0; j < 3; j++)
					{
						slots.add(new SlotGhost((IInventory) null, (i * 3) + j, x + 9 + (j * 18), y + 77 + (i * 18)));
					}
				}
				init = true;
			}
			
			Structure str = ((Structure) build.Ast.get(build.STRlastid)).copy();
			if (str instanceof StructureRotatable)
			{
				((StructureRotatable) str).setRotation(build.rot);
			}
			StructureData data = str.getStructureData();
			GuiVerticalSlider slider = (GuiVerticalSlider) this.buttonList.get(0);
			
			if (data.requiredItems.size() > 0 && (lastBid != build.STRlastid || lastRot != build.rot || lastSliderV != slider.getValueInt()))
			{
				slider.maxValue = (Math.ceil((double) data.requiredItems.size() / 3) - 3);
				int val = Math.abs(slider.getValueInt());
				List<ItemStack> backup = new ArrayList();
				for (int i = 0; i < foundItems.size(); i++)
				{
					backup.add(foundItems.get(i).copy());
				}
				for (int i = 0; i < shownItems.length; i++)
				{
					int j = (3 * val) + i;
					if (j < data.requiredItems.size())
					{
						shownItems[i] = data.requiredItemsExamp.get(j);
						((SlotGhost) slots.get(i)).state = haveContainerItem(data.requiredItems.get(j));
					} else shownItems[i] = null;
				}
				foundItems.clear();
				for (int i = 0; i < backup.size(); i++)
				{
					foundItems.add(backup.get(i).copy());
				}
				if (lastBid != build.STRlastid || lastRot != build.rot)
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
				if (data.requiredItems.size() > 9)
				{
					slider.enabled = true;
				} else
				{
					slider.enabled = false;
					slider.sliderValue = 0;
				}
				lastBid = build.STRlastid;
				lastRot = build.rot;
				lastSliderV = slider.getValueInt();
			} else if (lastBid != build.STRlastid)
			{
				shownItems = new ItemStack[9];
			}
			
			// 9 77
			
			GL11.glPushMatrix();
			
			for (int i = 0; i < slots.size(); i++)
			{
				drawSlot(slots.get(i), mouseX, mouseY);
			}
			GL11.glPopMatrix();
			
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			RenderHelper.disableStandardItemLighting();
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			
			for (int k = 0; k < this.buttonList.size(); ++k)
			{
				((GuiButton) this.buttonList.get(k)).drawButton(this.mc, mouseX, mouseY);
			}
			RenderHelper.enableGUIStandardItemLighting();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			
		} else
		{
			this.isEnabled = false;
			this.xSize = 0;
			this.ySize = 0;
		}
	}
	
	public void drawSlot(Slot slot, int mouseX, int mouseY)
	{
		int i = slot.xPos;
		int j = slot.yPos;
		ItemStack itemstack = shownItems[slot.slotNumber];
		itemRender.zLevel = 0.0F;
		this.zLevel = 0.0F;
		// mark
		if (itemstack != null)
		{
			GL11.glDisable(GL11.GL_LIGHTING);
			GL11.glEnable(GL11.GL_BLEND);
			Minecraft.getMinecraft().renderEngine.bindTexture(texture);
			
			boolean st = ((SlotGhost) slot).state;
			this.drawTexturedModalRect(i, j, 86 + (st ? 18 : 0), 3, 16, 16);
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
		
		if (itemstack != null)
		{
			GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			RenderHelper.enableGUIStandardItemLighting();
			drawItemStack(itemstack, i, j);
		}
	}
	
	public void drawItemStack(ItemStack stack, int x, int y)
	{
		GL11.glTranslatef(0.0F, 0.0F, 32.0F);
		this.zLevel = 200.0F;
		itemRender.zLevel = 200.0F;
		FontRenderer font = null;
		if (stack != null) font = stack.getItem().getFontRenderer(stack);
		if (font == null) font = fontRendererObj;
		itemRender.renderItemAndEffectIntoGUI(stack, x, y);
		itemRender.renderItemOverlayIntoGUI(font, stack, x, y, "");
		this.zLevel = 0.0F;
		itemRender.zLevel = 0.0F;
	}
	
	public boolean isMouseOverSlot(Slot slot, int mX, int mY)
	{
		return mX >= slot.xPos - 1 && mX < slot.xPos + 16 + 1 && mY >= slot.yPos - 1 && mY < slot.yPos + 16 + 1;
	}
	
	@Override
	public void drawGuiForegroundLayer(int mouseX, int mouseY)
	{
		if (isEnabled)
		{
			
			/*
			 * @noForm Structure name : hall Connections : 1 Add connections : 2
			 * Special fucntions : none Required items :
			 * 
			 * @formEn
			 */
			
			GuiBuilder build = (GuiBuilder) parent;
			Structure str = ((Structure) build.Ast.get(build.STRlastid)).copy();
			if (str instanceof StructureRotatable)
			{
				((StructureRotatable) str).setRotation(build.rot);
			}
			StructureData data = str.getStructureData();
			if (data.specialFunc.equals("none"))
			{
				data.specialFunc = I18n.format("builder.side_info.special_func.none");
			}
			String name = data.name;
			if (Minecraft.getMinecraft().gameSettings.forceUnicodeFont)
			{
				int dist = 8;
				// String name =
				// BuildHandler.getLocolizedName(str.getUnlocalizedName(),
				// build.rot, true);
				
				int width = fontRendererObj.getStringWidth(name);
				if (width <= 80)
				{
					simpleText(I18n.format("builder.side_info.name") + ":", 0, 5);
					simpleText(name, 0, 5 + dist * 1);
				} else
				{
					simpleText(I18n.format("builder.side_info.name") + ": " + name.substring(0, (30 / fontRendererObj.getStringWidth(" "))), 0, 5);
					String str2 = name.substring((30 / fontRendererObj.getStringWidth(" ")));
					if (str2.startsWith(" "))
					{
						str2 = str2.substring(1);
					}
					
					simpleText(str2, 0, 5 + dist * 1);
					
				}
				simpleText(I18n.format("builder.side_info.connections") + ": " + data.mainConnect, 0, 5 + dist * 2);
				simpleText(I18n.format("builder.side_info.add_connections") + ": " + data.addConnect, 0, 5 + dist * 3);
				if (fontRendererObj.getStringWidth(I18n.format("builder.side_info.special_func") + data.specialFunc) > 82)
				{
					simpleText(I18n.format("builder.side_info.special_func") + ":", 0, 5 + dist * 4);//
					simpleText(data.specialFunc, 0, 5 + dist * 5);
					simpleText(I18n.format("builder.side_info.required") + ": ", 0, 5 + dist * 6);
				} else
				{
					simpleText(I18n.format("builder.side_info.special_func") + ": " + data.specialFunc, 0, 5 + dist * 4);
					simpleText(I18n.format("builder.side_info.required") + ": ", 0, 5 + dist * 5);
				}
			} else
			{
				int dist = 9;
				int base = 0;
				
				if (fontRendererObj.getStringWidth(I18n.format("builder.side_info.short.name") + ": " + name) <= 80)
				{
					simpleText(I18n.format("builder.side_info.short.name") + ": " + name, 0, 5 + dist * base++);
				} else
				{
					int nL = fontRendererObj.getStringWidth(I18n.format("builder.side_info.short.name") + ": ");
					int ch = (80 - nL) / fontRendererObj.getStringWidth("h");
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
				if (fontRendererObj.getStringWidth(I18n.format("builder.side_info.short.special_func") + data.specialFunc) > 82)
				{
					simpleText(I18n.format("builder.side_info.short.special_func") + ":", 0, 5 + dist * base++);
					simpleText(data.specialFunc, 0, 5 + dist * base++);
					simpleText(I18n.format("builder.side_info.short.required") + ": ", 0, 5 + dist * base++);
				} else
				{
					simpleText(I18n.format("builder.side_info.short.special_func") + ": " + data.specialFunc, 0, 5 + dist * base++);
					simpleText(I18n.format("builder.side_info.short.required") + ": ", 0, 5 + dist * base++);
				}
			}
		}
	}
	
	public void simpleText(String text, int x, int y)
	{// I18n.format("builder.name")
		fontRendererObj.drawString(text, x, y, 4210752, false);
	}
	
}

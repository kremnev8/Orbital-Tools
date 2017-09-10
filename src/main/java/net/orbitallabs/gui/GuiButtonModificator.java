package net.orbitallabs.gui;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementDropdown;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementDropdown.IDropboxCallback;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementTextBox;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementTextBox.ITextBoxCallback;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.gui.GuiButtonBuilder.GuiIconsUtil;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.structures.StructureRotatable;
import net.orbitallabs.utils.OTLoger;
import net.orbitallabs.utils.OrbitalModInfo;

@SideOnly(Side.CLIENT)
public class GuiButtonModificator extends GuiButton implements IDropboxCallback, ITextBoxCallback {
	protected static final ResourceLocation buttonTextures = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/Modificator.png");
	
	protected static final ResourceLocation Icons = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/Icons.png");
	/** Button width in pixels */
	public int width = 134;
	/** Button height in pixels */
	public int height = 44;
	/** The x position of this control. */
	public int xPosition;
	/** The y position of this control. */
	public int yPosition;
	/** The string displayed on this control. */
	public String displayString;
	public int id;
	/** True if this control is enabled, false to disable. */
	public boolean enabled;
	/** Hides the button completely if false. */
	public boolean visible;
	protected boolean field_146123_n;
	private static final String __OBFID = "CL_00000668";
	public int packedFGColour;
	public boolean Enabled;
	
	public Structure ButStr;
	private Structure OrgStr;
	private int NyPos;
	
	private int ZeroPos;
	
	public BlockPos strPos;
	public boolean visSelf = true;
	
	public List<GuiButton> elementList = new ArrayList();
	
	private int[] poslist = new int[3];
	
	public static boolean isAnyInFocus = false;
	
	public boolean isDelSign = false;
	
	private boolean disableDel = false;
	
	public GuiButtonModificator(int id, int xpos, int ypos, Structure str, int y, boolean disableDel)
	{
		super(id, xpos, ypos, 134, 44, "");
		super.visible = false;
		this.enabled = true;
		this.visible = true;
		this.id = id;
		this.xPosition = xpos;
		this.yPosition = ypos;
		ZeroPos = y;
		this.disableDel = disableDel;
		if (str != null)
		{
			ButStr = str;
			OrgStr = str.copy();
			strPos = str.placementPos;
		}
		initButtons(ButStr);
	}
	
	private void initButtons(Structure str)
	{
		int xpos = xPosition;
		int ypos = yPosition;
		elementList.clear();
		
		FontRenderer fontrenderer = Minecraft.getMinecraft().fontRendererObj;
		
		elementList.add(new GuiElementTextBox(0, this, xpos + 31, ypos + 28, 20, 10, "", true, 3, true));
		elementList.add(new GuiElementTextBox(1, this, xpos + 61, ypos + 28, 20, 10, "", true, 3, true));
		elementList.add(new GuiElementTextBox(2, this, xpos + 91, ypos + 28, 20, 10, "", true, 3, true));
		
		String[] list = new String[] { EnumFacing.WEST.toString(), EnumFacing.EAST.toString(), EnumFacing.SOUTH.toString(), EnumFacing.NORTH.toString(), EnumFacing.UP.toString(),
				EnumFacing.DOWN.toString() };
		elementList.add(new GuiElementDropdown(3, this, xpos + 24 + fontrenderer.getStringWidth(I18n.format("modificator.direction.name")), ypos + 12, list));
		String[] list2 = new String[] { "0", "1", "2", "3" };
		elementList.add(new GuiElementDropdown(4, this, xpos + 80 + fontrenderer.getStringWidth(I18n.format("modificator.rotation.name")), ypos + 12, list2));
		
		if (str instanceof StructureRotatable)
		{
			List<String> intList = new ArrayList();
			intList.add("0");
			intList.add("1");
			intList.add("2");
			intList.add("3");
			for (int i = 0; i < intList.size(); i++)
			{
				if (!((StructureRotatable) str).isPossible(str.placementDir, Integer.parseInt(intList.get(i)), 0))
				{
					intList.remove(i);
					i--;
				}
			}
			if (intList.size() > 0)
			{
				((GuiElementDropdown) elementList.get(4)).optionStrings = intList.toArray(new String[0]);
				((GuiElementDropdown) elementList.get(4)).enabled = true;
			} else
			{
				((GuiElementDropdown) elementList.get(4)).optionStrings = new String[] { "-" };
				((GuiElementDropdown) elementList.get(4)).enabled = false;
			}
		} else
		{
			((GuiElementDropdown) elementList.get(4)).optionStrings = new String[] { "-" };
			((GuiElementDropdown) elementList.get(4)).enabled = false;
		}
		List<String> dirList = new ArrayList();
		dirList.add(EnumFacing.WEST.getName());
		dirList.add(EnumFacing.EAST.getName());
		dirList.add(EnumFacing.SOUTH.getName());
		dirList.add(EnumFacing.NORTH.getName());
		dirList.add(EnumFacing.UP.getName());
		dirList.add(EnumFacing.DOWN.getName());
		
		for (int i = 0; i < dirList.size(); i++)
		{
			if (!str.Check(Minecraft.getMinecraft().world, EnumFacing.byName(dirList.get(i)), strPos, 0))
			{
				dirList.remove(i);
				i--;
			}
		}
		if (dirList.size() > 0)
		{
			((GuiElementDropdown) elementList.get(3)).optionStrings = dirList.toArray(new String[0]);
			if (dirList.size() == 1)
			{
				((GuiElementDropdown) elementList.get(3)).enabled = false;
			} else
			{
				((GuiElementDropdown) elementList.get(3)).enabled = true;
			}
		} else
		{
			((GuiElementDropdown) elementList.get(3)).optionStrings = new String[] { "-" };
			((GuiElementDropdown) elementList.get(3)).enabled = false;
		}
	}
	
	public void setEnabled(boolean e)
	{
		this.Enabled = e;
	}
	
	public void setRotation(int rot)
	{
		this.ButStr.placementRotation = rot;
	}
	
	public void setDirection(EnumFacing dir)
	{
		this.ButStr.placementDir = dir;
	}
	
	/**
	 * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over
	 * this button and 2 if it IS hovering over this button.
	 */
	public int getHoverState(boolean p_146114_1_)
	{
		byte b0 = 1;
		
		if (!this.enabled)
		{
			b0 = 0;
		} else if (p_146114_1_)
		{
			b0 = 2;
		}
		
		return b0;
	}
	
	/**
	 * Draws this button to the screen.
	 */
	public void drawButton(Minecraft mine, int x, int y)
	{
		int move_mod = 11;
		
		NyPos = this.yPosition - (move_mod * GuiModificator.move);
		if (visSelf)
		{
			if (NyPos < ZeroPos - 25 || NyPos > ZeroPos + 157)
			{
				this.visible = false;
			} else this.visible = true;
		}
		if (this.visible)
		{
			FontRenderer fontrenderer = mine.fontRendererObj;
			mine.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = x >= this.xPosition && y >= NyPos && x < this.xPosition + this.width && y < NyPos + this.height;
			int k = this.getHoverState(this.field_146123_n);
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			this.drawTexturedModalRect(this.xPosition, NyPos, 2, 166, this.width, this.height);
			
			boolean onPlusSign = x >= this.xPosition + 113 && y >= NyPos + 6 && x < this.xPosition + 113 + 16 && y < NyPos + 6 + 16;
			if (onPlusSign)
			{
				this.drawTexturedModalRect(this.xPosition + 113, NyPos + 6, 202, 2, 16, 16);//+sign
			} else
			{
				this.drawTexturedModalRect(this.xPosition + 113, NyPos + 6, 184, 2, 16, 16);//+sign
			}
			
			boolean onRstSign = x >= this.xPosition + 5 && y >= NyPos + 23 && x < this.xPosition + 5 + 20 && y < NyPos + 23 + 17;
			if (onRstSign)
			{
				this.drawTexturedModalRect(this.xPosition + 5, NyPos + 23, 205, 20, 20, 17);//rst sign
			} else
			{
				this.drawTexturedModalRect(this.xPosition + 5, NyPos + 23, 184, 20, 20, 17);//rst sign
			}
			if (!disableDel)
			{
				boolean onDelSign = x >= this.xPosition + 116 && y >= NyPos + 26 && x < this.xPosition + 116 + 13 && y < NyPos + 26 + 13;
				if (onDelSign)
				{
					this.drawTexturedModalRect(this.xPosition + 116, NyPos + 26, 200, 41, 13, 13);//rst sign
				} else
				{
					this.drawTexturedModalRect(this.xPosition + 116, NyPos + 26, 185, 41, 13, 13);//rst sign
				}
			}
			
			try
			{
				renderIcons();
			} catch (Throwable error)
			{
				OTLoger.logWarn("An error ocured in GuiButtonBuilder:", error);
			}
			this.mouseDragged(mine, x, y);
			int l = 4210752;
			
			//	l = 10526880;
			
			// while (true)
			//  {
			//  GLoger.logInfo(Minecraft.getMinecraft().getLanguageManager().getLanguages().iterator().next());
			//  if (Minecraft.getMinecraft().getLanguageManager().getLanguages().iterator().hasNext())
			//  {
			//	   break;
			// }
			//  }
			fontrenderer.drawString(ButStr.getName(), this.xPosition + 24, NyPos + 4, l, false);
			
			fontrenderer.drawString(I18n.format("modificator.direction.name"), this.xPosition + 24, NyPos + 14, l, false);
			
			fontrenderer.drawString(I18n.format("modificator.rotation.name"), this.xPosition + 80, NyPos + 14, l, false);
			
			fontrenderer.drawString("x:", this.xPosition + 24, NyPos + 29, l, false);
			fontrenderer.drawString("y:", this.xPosition + 54, NyPos + 29, l, false);
			fontrenderer.drawString("z:", this.xPosition + 84, NyPos + 29, l, false);
			
			for (k = 0; k < this.elementList.size(); ++k)
			{
				if (this.elementList.get(k) instanceof GuiButton)
				{
					int old = ((GuiButton) this.elementList.get(k)).yPosition;
					((GuiButton) this.elementList.get(k)).yPosition -= (move_mod * GuiModificator.move);
					((GuiButton) this.elementList.get(k)).drawButton(mine, x, y);
					((GuiButton) this.elementList.get(k)).yPosition = old;
				}
			}
			
		}
	}
	
	private void renderIcons()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(Icons);
		String strName = ButStr.getUnlocalizedName();
		int rot = ButStr.placementRotation;
		EnumFacing dir = ButStr.placementDir;
		if (strName.equals(Structure.HOLLID))
		{
			DrawGuiIcon(this.xPosition + 5, NyPos + 5, "hall", 0);
		} else if (strName.equals(Structure.CORNERID))
		{
			if (dir == EnumFacing.WEST)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "corner", 0);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "corner", 1);
				} else DrawGuiIcon(this.xPosition + 5, NyPos + 5, "corner", 0);
			} else if (dir == EnumFacing.EAST)
			{
				if (rot == 2)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "corner", 0);
				} else if (rot == 3)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "corner", 1);
				} else
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "corner", 0);
				}
			} else if (dir == EnumFacing.NORTH)
			{
				if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "corner", 0);
				} else if (rot == 2)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "corner", 1);
				} else
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "corner", 0);
				}
			} else if (dir == EnumFacing.SOUTH)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "corner", 0);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "corner", 1);
				} else DrawGuiIcon(this.xPosition + 5, NyPos + 5, "corner", 1);
			}
		} else if (strName.equals(Structure.CROSSROADID))
		{
			DrawGuiIcon(this.xPosition + 5, NyPos + 5, "crossroad", 0);
		} else if (strName.equals(Structure.HALLAIRLOCKID))
		{
			DrawGuiIcon(this.xPosition + 5, NyPos + 5, "airlock", 0);
		} else if (strName.equals(Structure.WINDOWID))
		{
			DrawGuiIcon(this.xPosition + 5, NyPos + 5, "window", rot);
		} else if (strName.equals(Structure.CUPOLAID))
		{
			DrawGuiIcon(this.xPosition + 5, NyPos + 5, "cupola", 0);
		} else if (strName.equals(Structure.DOCKPORTID))
		{
			DrawGuiIcon(this.xPosition + 5, NyPos + 5, "dockport", 0);
		} else if (strName.equals(Structure.SOLARPANELID))
		{
			DrawGuiIcon(this.xPosition + 5, NyPos + 5, "solarpanel", rot);
		} else if (strName.equals(Structure.THALLID))
		{
			if (dir == EnumFacing.WEST)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 3);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 2);
				} else if (rot == 2)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 1);
				}
			} else if (dir == EnumFacing.EAST)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 3);
				} else if (rot == 2)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 2);
				} else if (rot == 3)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 1);
				}
			} else if (dir == EnumFacing.NORTH)
			{
				if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 3);
				} else if (rot == 2)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 2);
				} else if (rot == 3)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 1);
				} else
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 3);
				}
			} else if (dir == EnumFacing.SOUTH)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 3);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 2);
				} else if (rot == 3)
				{
					DrawGuiIcon(this.xPosition + 5, NyPos + 5, "thall", 1);
				}
			}
		} else if (strName.equals(Structure.BIGHHALL))
		{
			if (rot == 1)
			{
				DrawGuiIcon(this.xPosition + 5, NyPos + 5, "bighall_normal", 2);
			} else if (rot == 0)
			{
				DrawGuiIcon(this.xPosition + 5, NyPos + 5, "bighall_normal", 3);
			}
		} else if (strName.equals(Structure.GREENHOUSE))
		{
			DrawGuiIcon(this.xPosition + 5, NyPos + 5, "greenhouse", 0);
		} else if (strName.equals(Structure.PIERCE))
		{
			DrawGuiIcon(this.xPosition + 5, NyPos + 5, "pierce", 0);
		}
		
	}
	
	/**
	 * Fired when the mouse button is dragged. Equivalent of
	 * MouseListener.mouseDragged(MouseEvent e).
	 */
	protected void mouseDragged(Minecraft p_146119_1_, int p_146119_2_, int p_146119_3_)
	{
	}
	
	/**
	 * Fired when the mouse button is released. Equivalent of
	 * MouseListener.mouseReleased(MouseEvent e).
	 */
	public void mouseReleased(int p_146118_1_, int p_146118_2_)
	{
	}
	
	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of
	 * MouseListener.mousePressed(MouseEvent e).
	 */
	public boolean mousePressed(Minecraft mine, int x, int y)
	{
		int move_mod = 11;
		int shift = (move_mod * GuiModificator.move);
		;
		for (int l = 0; l < this.elementList.size(); ++l)
		{
			GuiButton guibutton = this.elementList.get(l);
			if (guibutton instanceof GuiElementDropdown)
			{
				boolean before = ((GuiElementDropdown) guibutton).dropdownClicked;
				if (guibutton.mousePressed(mine, x, y + shift))
				{
					guibutton.playPressSound(mine.getSoundHandler());
				}
				if (before != ((GuiElementDropdown) guibutton).dropdownClicked)
				{
					isAnyInFocus = ((GuiElementDropdown) guibutton).dropdownClicked;
				}
			} else if (guibutton instanceof GuiElementTextBox)
			{
				GuiElementTextBox field = (GuiElementTextBox) this.elementList.get(l);
				field.mousePressed(mine, x, y + shift);
				if (!field.isTextFocused)
				{
					try
					{
						int val = Integer.parseInt(field.text.equals("") ? "0" : field.text);
						if (val != -1)
						{
							switch (field.id) {
							case 0:
								ButStr.placementPos = new BlockPos(val, ButStr.placementPos.getY(), ButStr.placementPos.getZ());
								break;
							case 1:
								ButStr.placementPos = new BlockPos(ButStr.placementPos.getX(), val, ButStr.placementPos.getZ());
								break;
							case 2:
								ButStr.placementPos = new BlockPos(ButStr.placementPos.getX(), ButStr.placementPos.getY(), val);
								break;
							}
						}
						
					} catch (Exception e)
					{
					}
					
				}
			}
			
		}
		boolean onRstSign = x >= this.xPosition + 5 && y >= NyPos + 23 && x < this.xPosition + 5 + 20 && y < NyPos + 23 + 17;
		if (onRstSign)
		{
			this.ButStr = OrgStr.copy();
			initButtons(ButStr);
		}
		boolean onDelSign = x >= this.xPosition + 116 && y >= NyPos + 26 && x < this.xPosition + 116 + 13 && y < NyPos + 26 + 13 && !disableDel;
		boolean onPlusSign = x >= this.xPosition + 113 && y >= NyPos + 6 && x < this.xPosition + 113 + 16 && y < NyPos + 6 + 16;
		
		this.isDelSign = onDelSign;
		return this.enabled && this.visible && (onPlusSign || onDelSign);
	}
	
	public void keyTyped(char ch, int num)
	{
		for (int i = 0; i < elementList.size(); i++)
		{
			if (elementList.get(i) instanceof GuiElementTextBox)
			{
				((GuiElementTextBox) elementList.get(i)).keyTyped(ch, num);
			}
		}
	}
	
	public int getButtonWidth()
	{
		return this.width;
	}
	
	public void DrawGuiIcon(int x, int y, String name, int rot)
	{
		GuiIconsUtil.initMap();
		
		int[] Iconpos = GuiIconsUtil.Icons.get(name + "_" + rot);
		int[] Iconsize = new int[] { 16, 16 };
		if (name.equals("redcross"))
		{
			Iconsize = new int[] { 18, 18 };
		} else if (name.equals("redstar"))
		{
			Iconsize = new int[] { 9, 9 };
		}
		this.drawTexturedModalRect(x, y, Iconpos[0], Iconpos[1], Iconsize[0], Iconsize[1]);
		
	}
	
	@Override
	public void onSelectionChanged(GuiElementDropdown dropdown, int selection)
	{
		if (dropdown.id == 3)//Bighall org dir - SOUTH
		{
			EnumFacing dir = EnumFacing.byName(dropdown.optionStrings[selection]);
			ButStr.placementDir = dir;
			if (ButStr instanceof StructureRotatable)
			{
				List<String> intList = new ArrayList();
				intList.add("0");
				intList.add("1");
				intList.add("2");
				intList.add("3");
				for (int i = 0; i < intList.size(); i++)
				{
					if (!((StructureRotatable) ButStr).isPossible(dir, Integer.parseInt(intList.get(i)), 0))
					{
						intList.remove(i);
						i--;
					}
				}
				if (intList.size() > 0)
				{
					((GuiElementDropdown) elementList.get(4)).optionStrings = intList.toArray(new String[0]);
					((GuiElementDropdown) elementList.get(4)).enabled = true;
				} else
				{
					((GuiElementDropdown) elementList.get(4)).optionStrings = new String[] { "-" };
					((GuiElementDropdown) elementList.get(4)).enabled = false;
				}
			} else
			{
				((GuiElementDropdown) elementList.get(4)).enabled = false;
			}
		} else
		{
			int rot = Integer.parseInt(dropdown.optionStrings[selection]);
			ButStr.placementRotation = rot;
			List<String> dirList = new ArrayList();
			dirList.add(EnumFacing.WEST.toString());
			dirList.add(EnumFacing.EAST.toString());
			dirList.add(EnumFacing.SOUTH.toString());
			dirList.add(EnumFacing.NORTH.toString());
			dirList.add(EnumFacing.UP.toString());
			dirList.add(EnumFacing.DOWN.toString());
			
			for (int i = 0; i < dirList.size(); i++)
			{
				if (!ButStr.Check(Minecraft.getMinecraft().world, EnumFacing.byName(dirList.get(i)), strPos, 0))
				{
					dirList.remove(i);
					i--;
				}
			}
			if (dirList.size() > 0)
			{
				((GuiElementDropdown) elementList.get(3)).optionStrings = dirList.toArray(new String[0]);
				((GuiElementDropdown) elementList.get(3)).enabled = true;
			} else
			{
				((GuiElementDropdown) elementList.get(3)).optionStrings = new String[] { "-" };
				((GuiElementDropdown) elementList.get(3)).enabled = false;
			}
			
		}
		
	}
	
	@Override
	public int getInitialSelection(GuiElementDropdown dropdown)
	{
		int ret = 0;
		for (int i = 0; i < dropdown.optionStrings.length; i++)
		{
			if (!dropdown.enabled)
			{
				break;
			}
			if (dropdown.id == 3)
			{
				if (EnumFacing.byName(dropdown.optionStrings[i]) == ButStr.placementDir)
				{
					ret = i;
					break;
				}
			} else
			{
				if (Integer.parseInt(dropdown.optionStrings[i]) == ButStr.placementRotation)
				{
					ret = i;
					break;
				}
			}
		}
		
		return ret;
	}
	
	@Override
	public boolean canBeClickedBy(GuiElementDropdown dropdown, EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public void onIntruderInteraction()
	{
	}
	
	@Override
	public void onIntruderInteraction(GuiElementTextBox textBox)
	{
	}
	
	@Override
	public boolean canPlayerEdit(GuiElementTextBox textBox, EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public void onTextChanged(GuiElementTextBox textBox, String newText)
	{
	}
	
	@Override
	public String getInitialText(GuiElementTextBox textBox)
	{
		switch (textBox.id) {
		case 0:
			return Integer.toString(ButStr.placementPos.getX());
		case 1:
			return Integer.toString(ButStr.placementPos.getY());
		case 2:
			return Integer.toString(ButStr.placementPos.getZ());
		default:
			return "";
		}
	}
	
	@Override
	public int getTextColor(GuiElementTextBox textBox)
	{
		return ColorUtil.to32BitColor(255, 255, 255, 255);
	}
	
}
package net.orbitallabs.gui;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.gui.GuiButtonBuilder.GuiIconsUtil;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.structures.StructureRotatable;
import net.orbitallabs.utils.FacingUtils;
import net.orbitallabs.utils.OTLoger;
import net.orbitallabs.utils.OrbitalModInfo;

@SideOnly(Side.CLIENT)
public class GuiButtonRemover extends GuiButton {
	protected static final ResourceLocation buttonTextures = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/remover.png");
	
	protected static final ResourceLocation Icons = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/Icons.png");
	
	public boolean pressed;
	
	public int rot = 0;
	public EnumFacing dir = EnumFacing.UP;
	public String strName;
	private int NyPos;
	
	private int ZeroPos;
	
	public BlockPos strPos;
	public boolean visSelf = true;
	
	public GuiButtonRemover(int id, int xpos, int ypos, String Dispstring, Structure str, int y)
	{
		super(id, xpos, ypos, 125, 22, Dispstring);
		this.id = id;
		this.x = xpos;
		this.y = ypos;
		this.displayString = Dispstring;
		if (str != null)
		{
			this.rot = str.placementRotation;
			this.dir = str.placementDir;
			this.strName = str.getUnlocalizedName();
			ZeroPos = y;
			strPos = str.placementPos;
		}
	}
	
	public void setPressed(boolean e)
	{
		this.pressed = e;
	}
	
	public void setRotation(int rot)
	{
		this.rot = rot;
	}
	
	public void setDirection(EnumFacing dir)
	{
		this.dir = dir;
	}
	
	/**
	 * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over
	 * this button and 2 if it IS hovering over this button.
	 */
	public int getHoverState(boolean hover)
	{
		byte b0 = 1;
		
		if (!this.enabled)
		{
			b0 = 0;
		} else if (hover)
		{
			b0 = 2;
		}
		
		return b0;
	}
	
	/**
	 * Draws this button to the screen.
	 */
	public void drawButton(Minecraft mc, int x, int y, float ticks)
	{
		
		NyPos = this.y - (22 * GuiRemover.move);
		if (visSelf)
		{
			if (NyPos < ZeroPos + 16 || NyPos > ZeroPos + 103)
			{
				this.visible = false;
			} else this.visible = true;
		}
		if (this.visible)
		{
			FontRenderer fontrenderer = mc.fontRenderer;
			mc.getTextureManager().bindTexture(buttonTextures);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = x >= this.x && y >= NyPos && x < this.x + this.width && y < NyPos + this.height;
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
			if (getHoverState(this.hovered) == 0)
			{
				this.drawTexturedModalRect(this.x, NyPos, 0, 127, this.width, this.height);//disable
			} else if (getHoverState(this.hovered) != 2 && pressed)
			{
				this.drawTexturedModalRect(this.x, NyPos, 0, 127, this.width, this.height);//disable
				// this.drawTexturedModalRect(this.xPosition, NyPos, 0, 177, this.width , this.height);//enable
			} else if (getHoverState(this.hovered) == 2)
			{
				this.drawTexturedModalRect(this.x, NyPos, 0, 149, this.width, this.height);//hover
			} else this.drawTexturedModalRect(this.x, NyPos, 0, 127, this.width, this.height);//disable
			
			try
			{
				renderIcons();
			} catch (Throwable error)
			{
				OTLoger.logWarn("An error ocured in GuiButtonRemover:" + error.getMessage());
			}
			this.mouseDragged(mc, x, y);
			int l = 4210752;
			
			if (!this.enabled)
			{
				l = 10526880;
			} else if (this.hovered)
			{
				l = 16777120;
			}
			
			String NewDispStr = "";
			Structure str = Structure.FindStructure(strName);
			if (str instanceof StructureRotatable)
			{
				((StructureRotatable) str).setRotation(rot);
			}
			if (str != null)
			{
				NewDispStr = str.getName();
			}
			
			fontrenderer.drawString(NewDispStr, (this.x + this.width / 2) - 40, NyPos + (this.height - 20), l, false);
			if (this.dir != EnumFacing.UP && this.dir != EnumFacing.DOWN)
			{
				NewDispStr = "ForgeDir: " + FacingUtils.GetLocolizedName(dir);
				fontrenderer.drawString(NewDispStr, (this.x + this.width / 2) - 40, NyPos + (this.height - 11), l, false);
			}
		}
	}
	
	private void renderIcons()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(Icons);
		if (strName.equals(Structure.HOLLID))
		{
			DrawGuiIcon(this.x + 2, NyPos + 2, "hall", 0);
		} else if (strName.equals(Structure.CORNERID))
		{
			if (dir == EnumFacing.WEST)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "corner", 0);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "corner", 1);
				} else DrawGuiIcon(this.x + 2, NyPos + 2, "corner", 0);
			} else if (dir == EnumFacing.EAST)
			{
				if (rot == 2)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "corner", 0);
				} else if (rot == 3)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "corner", 1);
				} else
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "corner", 0);
				}
			} else if (dir == EnumFacing.NORTH)
			{
				if (rot == 1)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "corner", 0);
				} else if (rot == 2)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "corner", 1);
				} else
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "corner", 0);
				}
			} else if (dir == EnumFacing.SOUTH)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "corner", 0);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "corner", 1);
				} else DrawGuiIcon(this.x + 2, NyPos + 2, "corner", 1);
			}
		} else if (strName.equals(Structure.CROSSROADID))
		{
			DrawGuiIcon(this.x + 2, NyPos + 2, "crossroad", 0);
		} else if (strName.equals(Structure.HALLAIRLOCKID))
		{
			DrawGuiIcon(this.x + 2, NyPos + 2, "airlock", 0);
		} else if (strName.equals(Structure.WINDOWID))
		{
			DrawGuiIcon(this.x + 2, NyPos + 2, "window", rot);
		} else if (strName.equals(Structure.CUPOLAID))
		{
			DrawGuiIcon(this.x + 2, NyPos + 2, "cupola", 0);
		} else if (strName.equals(Structure.DOCKPORTID))
		{
			DrawGuiIcon(this.x + 2, NyPos + 2, "dockport", 0);
		} else if (strName.equals(Structure.SOLARPANELID))
		{
			DrawGuiIcon(this.x + 2, NyPos + 2, "solarpanel", rot);
		} else if (strName.equals(Structure.THALLID))
		{
			if (dir == EnumFacing.WEST)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 3);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 2);
				} else if (rot == 2)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 1);
				}
			} else if (dir == EnumFacing.EAST)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 3);
				} else if (rot == 2)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 2);
				} else if (rot == 3)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 1);
				}
			} else if (dir == EnumFacing.NORTH)
			{
				if (rot == 1)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 3);
				} else if (rot == 2)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 2);
				} else if (rot == 3)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 1);
				} else
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 3);
				}
			} else if (dir == EnumFacing.SOUTH)
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 3);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 2);
				} else if (rot == 3)
				{
					DrawGuiIcon(this.x + 2, NyPos + 2, "thall", 1);
				}
			}
		} else if (strName.equals(Structure.BIGHHALL))
		{
			if (rot == 1)
			{
				DrawGuiIcon(this.x + 2, NyPos + 2, "bighall_normal", 2);
			} else if (rot == 0)
			{
				DrawGuiIcon(this.x + 2, NyPos + 2, "bighall_normal", 3);
			}
		} else if (strName.equals(Structure.GREENHOUSE))
		{
			DrawGuiIcon(this.x + 2, NyPos + 2, "greenhouse", 0);
		} else if (strName.equals(Structure.PIRS))
		{
			DrawGuiIcon(this.x + 2, NyPos + 2, "pirs", 0);
		}
		if (pressed)
		{
			DrawGuiIcon(this.x + 1, NyPos + 1, "redcross", 0);
		}
		if (GuiRemover.Iselected[0] == true && !pressed)
		{
			DrawGuiIcon(this.x + 114, NyPos + 6, "redstar", 0);
		}
		
	}
	
	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of
	 * MouseListener.mousePressed(MouseEvent e).
	 */
	public boolean mousePressed(Minecraft mc, int x, int y)
	{
		return this.enabled && this.visible && x >= this.x && y >= NyPos && x < this.x + this.width && y < NyPos + this.height;
	}
	
	public void DrawGuiIcon(int x, int y, String name, int rot)
	{
		GuiIconsUtil.initMap();
		
		if (GuiIconsUtil.Icons.containsKey(name + "_" + rot))
		{
			
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
	}
	
}
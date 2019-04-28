package net.orbitallabs.gui;

import java.util.HashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.utils.OTLoger;
import net.orbitallabs.utils.OrbitalModInfo;

@SideOnly(Side.CLIENT)
public class GuiButtonBuilder extends GuiButton {
	protected static final ResourceLocation buttonTextures = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/builder.png");
	
	protected static final ResourceLocation Icons = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/Icons.png");
	
	protected boolean hover;
	private boolean pressed;
	
	protected int NyPos;
	private int ZeroPos;
	
	public int rot = 0;
	private EnumFacing dir = EnumFacing.UP;
	
	public IGUISliderController gui;
	
	public int ScroolStart = 24;
	public int ScroolEnd = 135;
	
	public GuiButtonBuilder(int id, int xpos, int ypos, String Dispstring, int zeroy, IGUISliderController gui)
	{
		super(id, xpos, ypos, 80, 20, Dispstring);
		
		super.visible = false;
		this.enabled = true;
		this.visible = true;
		this.id = id;
		this.xPosition = xpos;
		this.yPosition = ypos;
		this.displayString = Dispstring;
		this.ZeroPos = zeroy;
		this.gui = gui;
		
	}
	
	public GuiButtonBuilder(int id, int xpos, int ypos, String Dispstring, int zeroy, int start, int end, IGUISliderController gui)
	{
		this(id, xpos, ypos, Dispstring, zeroy, gui);
		
		this.ScroolStart = start;
		this.ScroolEnd = end;
	}
	
	public void setEnabled(boolean e)
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
	public void drawButton(Minecraft mc, int x, int y)
	{
		
		NyPos = this.yPosition - (20 * gui.getMove());
		if (NyPos < ZeroPos + ScroolStart || NyPos > ZeroPos + ScroolEnd)
		{
			this.visible = false;
		} else this.visible = true;
		if (this.visible)
		{
			FontRenderer fontrenderer = mc.fontRendererObj;
			mc.getTextureManager().bindTexture(buttonTextures);
			//	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hover = x >= this.xPosition && y >= this.NyPos && x < this.xPosition + this.width && y < this.NyPos + this.height;
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
					GlStateManager.DestFactor.ZERO);
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			
			if (getHoverState(this.hover) == 0)
			{
				this.drawTexturedModalRect(this.xPosition, this.NyPos, 0, 167, this.width, this.height);// normal
			} else if (pressed)
			{
				this.drawTexturedModalRect(this.xPosition, this.NyPos, 0, 207, this.width, this.height);// pressed
			} else if (getHoverState(this.hover) == 2 && !pressed)
			{
				this.drawTexturedModalRect(this.xPosition, this.NyPos, 0, 187, this.width, this.height);// hover
			} else this.drawTexturedModalRect(this.xPosition, this.NyPos, 0, 167, this.width, this.height);// normal
			
			try
			{
				renderIcons();
			} catch (Throwable error)
			{
				OTLoger.logWarn("An error ocured in GuiButtonBuilder:", error);
			}
			int l = 4210752;
			
			//	GlStateManager.clearColor(1.0F, 1.0F, 1.0F, 1.0F);
			
			if (!this.enabled)
			{
				l = 10526880;
			} else if (this.hover)
			{
				l = 16777120;
			}
			int len = fontrenderer.getStringWidth(this.displayString);
			if (len <= 55)
			{
				fontrenderer.drawString(this.displayString, this.xPosition + 20, this.NyPos + this.height - 12, l, false);
			} else
			{
				String part2 = "";
				String[] tokens = this.displayString.split("[\\s']");
				for (int i = 1; i < tokens.length; i++)
				{
					part2 = part2 + (i == 1 ? "" : " ") + tokens[i];
				}
				fontrenderer.drawString(tokens[0], this.xPosition + 20, this.NyPos + this.height - 19, l, false);
				if (!part2.equals(""))
				{
					fontrenderer.drawString(part2, this.xPosition + 20, this.NyPos + this.height - 11, l, false);
				}
			}
		}
	}
	
	public void renderIcons()
	{
		Minecraft.getMinecraft().getTextureManager().bindTexture(Icons);
		if (GuiBuilder.AstG.size() != 0)
		{
			if (GuiBuilder.AstG.containsKey(Structure.HOLLID) && id == (int) GuiBuilder.AstG.get(Structure.HOLLID))
			{
				DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "hall", 0);
			} else if (GuiBuilder.AstG.containsKey(Structure.CORNERID) && id == (int) GuiBuilder.AstG.get(Structure.CORNERID))
			{
				if (dir == EnumFacing.WEST)
				{
					if (rot == 0)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "corner", 0);
					} else if (rot == 1)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "corner", 1);
					}
				} else if (dir == EnumFacing.EAST)
				{
					if (rot == 2)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "corner", 0);
					} else if (rot == 3)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "corner", 1);
					} else
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "corner", 0);
					}
				} else if (dir == EnumFacing.NORTH)
				{
					if (rot == 1)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "corner", 0);
					} else if (rot == 2)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "corner", 1);
					} else
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "corner", 0);
					}
				} else if (dir == EnumFacing.SOUTH)
				{
					if (rot == 3)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "corner", 0);
					} else if (rot == 0)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "corner", 1);
					}
				}
			} else if (GuiBuilder.AstG.containsKey(Structure.CROSSROADID) && id == (int) GuiBuilder.AstG.get(Structure.CROSSROADID))
			{
				DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "crossroad", 0);
			} else if (GuiBuilder.AstG.containsKey(Structure.HALLAIRLOCKID) && id == (int) GuiBuilder.AstG.get(Structure.HALLAIRLOCKID))
			{
				DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "airlock", 0);
			} else if (GuiBuilder.AstG.containsKey(Structure.WINDOWID) && id == (int) GuiBuilder.AstG.get(Structure.WINDOWID))
			{
				DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "window", rot);
			} else if (GuiBuilder.AstG.containsKey(Structure.CUPOLAID) && id == (int) GuiBuilder.AstG.get(Structure.CUPOLAID))
			{
				DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "cupola", 0);
			} else if (GuiBuilder.AstG.containsKey(Structure.DOCKPORTID) && id == (int) GuiBuilder.AstG.get(Structure.DOCKPORTID))
			{
				DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "dockport", 0);
			} else if (GuiBuilder.AstG.containsKey(Structure.SOLARPANELID) && id == (int) GuiBuilder.AstG.get(Structure.SOLARPANELID))
			{
				DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "solarpanel", rot);
			} else if (GuiBuilder.AstG.containsKey(Structure.THALLID) && id == (int) GuiBuilder.AstG.get(Structure.THALLID))
			{
				if (dir == EnumFacing.WEST)
				{
					if (rot == 0)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 3);
					} else if (rot == 1)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 2);
					} else if (rot == 2)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 1);
					}
				} else if (dir == EnumFacing.EAST)
				{
					if (rot == 2)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 3);
					} else if (rot == 3)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 2);
					} else if (rot == 0)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 1);
					}
				} else if (dir == EnumFacing.NORTH)
				{
					if (rot == 1)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 3);
					} else if (rot == 2)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 2);
					} else if (rot == 3)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 1);
					} else
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 3);
					}
				} else if (dir == EnumFacing.SOUTH)
				{
					if (rot == 3)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 3);
					} else if (rot == 0)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 2);
					} else if (rot == 1)
					{
						DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "thall", 1);
					}
				}
			} else if (GuiBuilder.AstG.containsKey(Structure.BIGHHALL) && id == (int) GuiBuilder.AstG.get(Structure.BIGHHALL))
			{
				if (rot == 0)
				{
					DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "bighall_normal", 3);
				} else if (rot == 1)
				{
					DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "bighall_normal", 2);
				}
			} else if (GuiBuilder.AstG.containsKey(Structure.GREENHOUSE) && id == (int) GuiBuilder.AstG.get(Structure.GREENHOUSE))
			{
				DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "greenhouse", 0);
			} else if (GuiBuilder.AstG.containsKey(Structure.PIRS) && id == (int) GuiBuilder.AstG.get(Structure.PIRS))
			{
				DrawGuiIcon(this.xPosition + 2, this.NyPos + 2, "pirs", 0);
			}
		}
		// Minecraft.getMinecraft().getTextureManager().bindTexture(buttonTextures);
	}
	
	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of
	 * MouseListener.mousePressed(MouseEvent e).
	 */
	public boolean mousePressed(Minecraft mc, int x, int y)
	{
		return this.enabled && this.visible && x >= this.xPosition && y >= this.NyPos && x < this.xPosition + this.width && y < this.NyPos + this.height;
	}
	
	public int getButtonWidth()
	{
		return this.width;
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
	
	public static class GuiIconsUtil {
		public static HashMap<String, int[]> Icons = new HashMap<String, int[]>();
		private static boolean init = false;
		
		public static void initMap()
		{
			if (!init)
			{
				Icons.clear();
				Icons.put("hall_0", new int[] { 1, 1 });
				Icons.put("hall_1", new int[] { 19, 1 });
				Icons.put("crossroad_0", new int[] { 37, 1 });
				Icons.put("cupola_0", new int[] { 55, 1 });
				Icons.put("bighall_small_0", new int[] { 73, 1 });
				Icons.put("pirs_0", new int[] { 91, 1 });
				
				Icons.put("corner_0", new int[] { 1, 19 });
				Icons.put("corner_1", new int[] { 19, 19 });
				Icons.put("corner_2", new int[] { 37, 19 });
				Icons.put("corner_3", new int[] { 55, 19 });
				Icons.put("greenhouse_0", new int[] { 73, 19 });
				
				Icons.put("airlock_0", new int[] { 1, 37 });
				Icons.put("airlock_1", new int[] { 19, 37 });
				Icons.put("window_1", new int[] { 37, 37 });
				
				Icons.put("redcross_0", new int[] { 54, 36 });
				Icons.put("redstar_0", new int[] { 73, 37 });
				
				Icons.put("window_0", new int[] { 1, 55 });
				Icons.put("dockport_0", new int[] { 19, 55 });
				Icons.put("solarpanel_0", new int[] { 37, 55 });
				Icons.put("solarpanel_1", new int[] { 55, 55 });
				
				Icons.put("thall_3", new int[] { 1, 73 });
				Icons.put("thall_2", new int[] { 19, 73 });
				Icons.put("thall_1", new int[] { 37, 73 });
				Icons.put("thall_0", new int[] { 55, 73 });
				
				Icons.put("bighall_normal_0", new int[] { 73, 57 });
				Icons.put("bighall_normal_1", new int[] { 89, 57 });
				Icons.put("bighall_normal_2", new int[] { 73, 73 });
				Icons.put("bighall_normal_3", new int[] { 89, 73 });
				init = true;
			}
		}
	}
}
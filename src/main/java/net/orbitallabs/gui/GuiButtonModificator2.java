package net.orbitallabs.gui;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.utils.OrbitalModInfo;

@SideOnly(Side.CLIENT)
public class GuiButtonModificator2 extends GuiButton {
	protected static final ResourceLocation buttonTextures = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/Modificator.png");
	/** Button width in pixels */
	public int width = 134;
	/** Button height in pixels */
	public int height = 17;
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
	
	private int NyPos;
	
	private int ZeroPos;
	
	public int[] strPos;
	public boolean visSelf = true;
	
	public GuiButtonModificator2(int id, int xpos, int ypos, int y)
	{
		super(id, xpos, ypos, 127, 24, "");
		super.visible = false;
		this.enabled = true;
		this.visible = true;
		this.id = id;
		this.xPosition = xpos;
		this.yPosition = ypos;
		this.displayString = "";
		this.ZeroPos = y;
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
	public void drawButton(Minecraft mine, int x, int y)
	{
		
		NyPos = this.yPosition - (11 * GuiModificator.move);
		if (visSelf)
		{
			if (NyPos < ZeroPos - 25 || NyPos > ZeroPos + 157)
			{
				this.visible = false;
			} else this.visible = true;
		}
		if (this.visible)
		{
			mine.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = x >= this.xPosition && y >= NyPos && x < this.xPosition + this.width && y < NyPos + this.height && !GuiButtonModificator.isAnyInFocus;
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
			if (getHoverState(this.field_146123_n) == 2)
			{
				this.drawTexturedModalRect(this.xPosition, NyPos, 2, 230, 134, 17);//en
			} else
			{
				this.drawTexturedModalRect(this.xPosition, NyPos, 2, 212, 134, 17);
			}
		}
	}
	
	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of
	 * MouseListener.mousePressed(MouseEvent e).
	 */
	public boolean mousePressed(Minecraft mine, int x, int y)
	{
		return this.enabled && this.visible && x >= this.xPosition && y >= NyPos && x < this.xPosition + this.width && y < NyPos + this.height
				&& !GuiButtonModificator.isAnyInFocus;
	}
	
	public int getButtonWidth()
	{
		return this.width;
	}
	
}
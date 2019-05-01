
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
public class GuiButtonSwap extends GuiButton {
	protected static final ResourceLocation buttonTextures = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/Armor_stand.png");
	/** Button width in pixels */
	public int width = 14;
	/** Button height in pixels */
	public int height = 14;
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
	private boolean Enabled;
	
	public GuiButtonSwap(int id, int xpos, int ypos)
	{
		super(id, xpos, ypos, 14, 14, "");
		super.visible = false;
		this.enabled = true;
		this.visible = true;
		this.id = id;
		this.xPosition = xpos;
		this.yPosition = ypos;
	}
	
	public void setEnabled(boolean e)
	{
		this.Enabled = e;
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
	public void drawButton(Minecraft mc, int x, int y, float ticks)
	{
		if (this.visible)
		{
			mc.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width
					&& y < this.yPosition + this.height;
			
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			
			if (getHoverState(this.field_146123_n) == 0)
			{
				this.drawTexturedModalRect(this.xPosition, this.yPosition, 179, 87, this.width, this.height);//disable
			} else if (getHoverState(this.field_146123_n) != 2 && Enabled)
			{
				//		this.drawTexturedModalRect(this.xPosition, this.yPosition, 160, 162, this.width, this.height + 2);//enable
			} else if (getHoverState(this.field_146123_n) == 2)
			{
				this.drawTexturedModalRect(this.xPosition, this.yPosition, 179, 102, this.width, this.height);//hover
			} else this.drawTexturedModalRect(this.xPosition, this.yPosition, 179, 87, this.width, this.height);//disable
			this.drawTexturedModalRect(this.xPosition, this.yPosition, 179, 117, this.width, this.height);
			this.mouseDragged(mc, x, y);
			
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
	public boolean mousePressed(Minecraft p_146116_1_, int p_146116_2_, int p_146116_3_)
	{
		return this.enabled && this.visible && p_146116_2_ >= this.xPosition && p_146116_3_ >= this.yPosition && p_146116_2_ < this.xPosition + this.width
				&& p_146116_3_ < this.yPosition + this.height;
	}
	
	public int getButtonWidth()
	{
		return this.width;
	}
	
}
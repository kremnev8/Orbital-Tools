package net.orbitallabs.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.utils.OrbitalModInfo;

@SideOnly(Side.CLIENT)
public class GuiButtonModificator2 extends GuiButton {
	protected static final ResourceLocation buttonTextures = new ResourceLocation(OrbitalModInfo.MOD_ID,
			"textures/gui/modificator.png");

	private int NyPos;

	private int ZeroPos;

	public int[] strPos;
	public boolean visSelf = true;

	public boolean type = false;

	public GuiButtonModificator2(int id, int xpos, int ypos, int y, boolean type) {
		super(id, xpos, ypos, 129, 15, "");
		this.displayString = "";
		this.ZeroPos = y;
		this.type = type;
	}

	/**
	 * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over this
	 * button and 2 if it IS hovering over this button.
	 */
	public int getHoverState(boolean hover) {
		byte b0 = 1;

		if (!this.enabled) {
			b0 = 0;
		} else if (hover) {
			b0 = 2;
		}

		return b0;
	}

	/**
	 * Draws this button to the screen.
	 */
	public void drawButton(Minecraft mine, int x, int y, float ticks) {

		NyPos = this.y - (10 * GuiModificator.move);
		if (visSelf) {
			if (NyPos < ZeroPos - 25 || NyPos > ZeroPos + 157) {
				this.visible = false;
			} else
				this.visible = true;
		}
		if (this.visible) {
			GL11.glEnable(GL11.GL_SCISSOR_TEST);
			mine.getTextureManager().bindTexture(buttonTextures);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			boolean covered = y > ZeroPos + 157;

			this.hovered = x >= this.x && y >= NyPos && x < this.x + this.width && y < NyPos + this.height
					&& !GuiButtonModificator.isAnyInFocus && !covered;
			GL11.glEnable(GL11.GL_BLEND);
			OpenGlHelper.glBlendFunc(770, 771, 1, 0);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			if (getHoverState(this.hovered) == 2) {
				this.drawTexturedModalRect(this.x, NyPos, 0, 236, 129, 15);// en
			} else {
				this.drawTexturedModalRect(this.x, NyPos, 0, 221, 129, 15);
			}
			GL11.glDisable(GL11.GL_SCISSOR_TEST);
		}
	}

	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of
	 * MouseListener.mousePressed(MouseEvent e).
	 */
	public boolean mousePressed(Minecraft mine, int x, int y) {
		boolean covered = y > ZeroPos + 157;
		return this.enabled && this.visible && x >= this.x && y >= NyPos && x < this.x + this.width
				&& y < NyPos + this.height && !GuiButtonModificator.isAnyInFocus && !covered;
	}

	public int getButtonWidth() {
		return this.width;
	}

}
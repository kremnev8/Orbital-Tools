package net.orbitallabs.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.orbitallabs.utils.OrbitalModInfo;

public class SmallGuiButton extends GuiButton {
	
	protected static final ResourceLocation buttonTextures = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/builder.png");
	
	public SmallGuiButton(int buttonId, int x, int y, int width, String buttonText)
	{
		super(buttonId, x, y, width, 15, buttonText);
	}
	
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		if (this.visible)
		{
			FontRenderer fontrenderer = mc.fontRenderer;
			mc.getTextureManager().bindTexture(buttonTextures);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
			int i = this.getHoverState(this.hovered);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
					GlStateManager.DestFactor.ZERO);
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
			this.drawTexturedModalRect(this.x, this.y, 138, 167 + i * 15, this.width / 2, this.height);
			this.drawTexturedModalRect(this.x + this.width / 2, this.y, 254 - this.width / 2, 167 + i * 15, this.width / 2, this.height);
			this.mouseDragged(mc, mouseX, mouseY);
			int j = 14737632;
			
			if (packedFGColour != 0)
			{
				j = packedFGColour;
			} else if (!this.enabled)
			{
				j = 10526880;
			} else if (this.hovered)
			{
				j = 16777120;
			}
			
			this.drawCenteredString(fontrenderer, this.displayString, this.x + this.width / 2, this.y + (this.height - 8) / 2, j);
		}
	}
	
}

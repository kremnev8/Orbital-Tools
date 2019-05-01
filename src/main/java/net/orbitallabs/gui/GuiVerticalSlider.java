
package net.orbitallabs.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiButtonExt;
import net.orbitallabs.utils.OrbitalModInfo;

/**
 * This class is blatantly stolen from iChunUtils.
 * 
 * @author iChun
 */
public class GuiVerticalSlider extends GuiButtonExt {
	/** The value of this slider control. */
	public double sliderValue = 1.0F;
	
	public String dispString = "";
	
	/** Is this slider control being dragged. */
	public boolean dragging = false;
	public boolean showDecimal = true;
	
	public double minValue = 0.0D;
	public double maxValue = 5.0D;
	public int precision = 1;
	
	public ISlider parent = null;
	
	public String suffix = "";
	
	public boolean drawString = true;
	public final ResourceLocation slidertex = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/remover.png");
	
	public GuiVerticalSlider(int id, int xPos, int yPos, int width, int height, String prefix, String suf, double minVal, double maxVal, double currentVal, boolean showDec, boolean drawStr)
	{
		this(id, xPos, yPos, width, height, prefix, suf, minVal, maxVal, currentVal, showDec, drawStr, null);
	}
	
	public GuiVerticalSlider(int id, int xPos, int yPos, int width, int height, String prefix, String suf, double minVal, double maxVal, double currentVal, boolean showDec, boolean drawStr, ISlider par)
	{
		super(id, xPos, yPos, width, height, prefix);
		super.visible = false;
		minValue = minVal;
		maxValue = maxVal;
		sliderValue = (currentVal - minValue) / (maxValue - minValue);
		dispString = prefix;
		parent = par;
		suffix = suf;
		showDecimal = showDec;
		String val;
		
		if (showDecimal)
		{
			val = Double.toString(sliderValue * (maxValue - minValue) + minValue);
			precision = Math.min(val.substring(val.indexOf(".") + 1).length(), 4);
		} else
		{
			val = Integer.toString((int) Math.round(sliderValue * (maxValue - minValue) + minValue));
			precision = 0;
		}
		
		displayString = dispString + val + suffix;
		
		drawString = drawStr;
		if (!drawString)
		{
			displayString = "";
		}
	}
	
	public GuiVerticalSlider(int id, int xPos, int yPos, String displayStr, double minVal, double maxVal, double currentVal, ISlider par)
	{
		this(id, xPos, yPos, 150, 20, displayStr, "", minVal, maxVal, currentVal, true, true, par);
	}
	
	/**
	 * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over
	 * this button and 2 if it IS hovering over this button.
	 */
	/**
	 * Returns 0 if the button is disabled, 1 if the mouse is NOT hovering over
	 * this button and 2 if it IS hovering over this button.
	 */
	@Override
	public int getHoverState(boolean par1)
	{
		byte b0 = 1;
		
		if (!this.enabled)
		{
			b0 = 0;
		} else if (par1)
		{
			b0 = 2;
		}
		
		return b0;
	}
	
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float ticks)
	{
		this.hovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height;
		// GuiUtils.drawContinuousTexturedBox(buttonTextures, this.xPosition, this.yPosition, 0, 46 + k * 20, this.width, this.height, 200, 20, 2, 3, 2, 2, this.zLevel);
		Minecraft.getMinecraft().getTextureManager().bindTexture(slidertex);
		mouseDragged(mc, mouseX, mouseY);
		/*
		 * int color = 14737632;
		 * 
		 * if (packedFGColour != 0) { color = packedFGColour; } else if
		 * (!this.enabled) { color = 10526880; } else if (this.field_146123_n) {
		 * color = 16777120; }
		 */
		
		//    String buttonText = this.displayString;
		//    int strWidth = mc.fontRenderer.getStringWidth(buttonText);
		//    int ellipsisWidth = mc.fontRenderer.getStringWidth("...");
		
		///    if (strWidth > width - 6 && strWidth > ellipsisWidth)
		//        buttonText = mc.fontRenderer.trimStringToWidth(buttonText, width - 6 - ellipsisWidth).trim() + "...";
		
		//    this.drawCenteredString(mc.fontRenderer, buttonText, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, color);
	}
	
	/**
	 * Fired when the mouse button is dragged. Equivalent of
	 * MouseListener.mouseDragged(MouseEvent e).
	 */
	/**
	 * Fired when the mouse button is dragged. Equivalent of
	 * MouseListener.mouseDragged(MouseEvent e).
	 */
	@Override
	protected void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
	{
		
		if (this.dragging)
		{
			this.sliderValue = (par3 - (this.y + 4)) / (float) (this.height);
			updateSlider();
		}
		
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		int k = this.getHoverState(this.hovered);
		if (this.enabled)
		{
			if ((k == 1 || k == 0))
			{
				this.drawTexturedModalRect(this.x, this.y + (int) (this.sliderValue * (float) (this.height - 15)) + 1, 158, 15, 10, 15);
			} else
			{
				if (!dragging)
				{
					this.drawTexturedModalRect(this.x, this.y + (int) (this.sliderValue * (float) (this.height - 15)) + 1, 169, 15, 10, 15);
				} else this.drawTexturedModalRect(this.x, this.y + (int) (this.sliderValue * (float) (this.height - 15)) + 1, 158, 15, 10, 15);
			}
		} else this.drawTexturedModalRect(this.x, this.y + 1, 158, 31, 10, 15);
		// this.drawTexturedModalRect(this.xPosition +4, this.yPosition +
		// (int)(this.sliderValue * (float)(this.height - 22)) +1, 196, 66, 4,
		// 20);
		
	}
	
	/**
	 * Returns true if the mouse has been pressed on this control. Equivalent of
	 * MouseListener.mousePressed(MouseEvent e).
	 */
	@Override
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
	{
		boolean superr = this.enabled && par2 >= this.x && par3 >= this.y && par2 < this.x + this.width && par3 < this.y + this.height;
		
		if (superr)
		{
			this.sliderValue = (float) (par3 - (this.y)) / (float) (this.height - 8);
			updateSlider();
			this.dragging = true;
			return true;
		}
		return false;
	}
	
	public void updateSlider()
	{
		if (this.sliderValue < 0.0F)
		{
			this.sliderValue = 0.0F;
		}
		
		if (this.sliderValue > 1.0F)
		{
			this.sliderValue = 1.0F;
		}
		
		String val;
		
		if (showDecimal)
		{
			val = Double.toString(sliderValue * (maxValue - minValue) + minValue);
			
			if (val.substring(val.indexOf(".") + 1).length() > precision)
			{
				val = val.substring(0, val.indexOf(".") + precision + 1);
				
				if (val.endsWith("."))
				{
					val = val.substring(0, val.indexOf(".") + precision);
				}
			} else
			{
				while (val.substring(val.indexOf(".") + 1).length() < precision)
				{
					val = val + "0";
				}
			}
		} else
		{
			val = Integer.toString((int) Math.round(sliderValue * (maxValue - minValue) + minValue));
		}
		
		if (drawString)
		{
			displayString = dispString + val + suffix;
		}
		
		if (parent != null)
		{
			parent.onChangeSliderValue(this);
		}
	}
	
	/**
	 * Fired when the mouse button is released. Equivalent of
	 * MouseListener.mouseReleased(MouseEvent e).
	 */
	/**
	 * Fired when the mouse button is released. Equivalent of
	 * MouseListener.mouseReleased(MouseEvent e).
	 */
	@Override
	public void mouseReleased(int par1, int par2)
	{
		this.dragging = false;
	}
	
	public int getValueInt()
	{
		return (int) Math.round(sliderValue * (maxValue - minValue) + minValue);
	}
	
	public double getValue()
	{
		return sliderValue * (maxValue - minValue) + minValue;
	}
	
	public void setValue(double d)
	{
		this.sliderValue = (d - minValue) / (maxValue - minValue);
	}
	
	public static interface ISlider {
		void onChangeSliderValue(GuiVerticalSlider slider);
	}
}
package net.orbitallabs.gui;

import net.minecraftforge.fml.client.config.GuiSlider;

public class CustomGuiSlider extends GuiSlider {
	
	public CustomGuiSlider(int id, int xPos, int yPos, int width, int height, String prefix, String suf, double minVal, double maxVal, double currentVal, boolean showDec, boolean drawStr, int round, ISlider par)
	{
		super(id, xPos, yPos, width, height, prefix, suf, minVal, maxVal, currentVal, showDec, drawStr, par);
		
		precision = round;
		
		if (drawString)
		{
			displayString = dispString + getRoundedValue() + suffix;
		}
	}
	
	public String getRoundedValue()
	{
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
		return val;
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
		
		if (drawString)
		{
			displayString = dispString + getRoundedValue() + suffix;
		}
	}
	
	public void setValue(double d)
	{
		this.sliderValue = (d - minValue) / (maxValue - minValue);
		
		if (drawString)
		{
			displayString = dispString + getRoundedValue() + suffix;
		}
	}
	
	@Override
	public void mouseReleased(int par1, int par2)
	{
		this.dragging = false;
		if (parent != null)
		{
			parent.onChangeSliderValue(this);
		}
	}
	
}

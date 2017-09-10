
package net.orbitallabs.utils;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class USString extends IString {
	
	public String input;
	public TextFormatting modifier;
	
	public USString(String input, TextFormatting color)
	{
		this.input = input;
		this.modifier = color;
	}
	
	public String getInput()
	{
		return input;
	}
	
	public TextFormatting getColor()
	{
		return modifier;
	}
	
	public ITextComponent getFormatedText()
	{
		ITextComponent comp = new TextComponentString(input);
		if (modifier != null)
		{
			comp = ChatUtils.modifyColor(comp, modifier);
		}
		return comp;
	}
	
}
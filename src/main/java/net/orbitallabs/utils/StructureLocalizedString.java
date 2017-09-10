
package net.orbitallabs.utils;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.orbitallabs.structures.Structure;

public class StructureLocalizedString extends IString {
	
	public Structure input;
	public TextFormatting modifier;
	
	public StructureLocalizedString(Structure str, TextFormatting color)
	{
		this.input = str;
		this.modifier = color;
	}
	
	public String getInput()
	{
		return input.getUnlocalizedName();
	}
	
	public TextFormatting getColor()
	{
		return modifier;
	}
	
	public ITextComponent getFormatedText()
	{
		ITextComponent comp = new TextComponentString(input.getName().toLowerCase());
		if (modifier != null)
		{
			comp = ChatUtils.modifyColor(comp, modifier);
		}
		return comp;
	}
	
}

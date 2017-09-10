
package net.orbitallabs.utils;

import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

public abstract class IString {
	
	public abstract String getInput();
	
	public abstract TextFormatting getColor();
	
	public abstract ITextComponent getFormatedText();
	
}
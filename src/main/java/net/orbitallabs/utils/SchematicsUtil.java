
package net.orbitallabs.utils;

import java.util.ArrayList;
import java.util.List;

import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import net.orbitallabs.gui.InventorySchematicJetpack;

public class SchematicsUtil {
	
	private static List<INasaWorkbenchRecipe> jetpackRecipes = new ArrayList<INasaWorkbenchRecipe>();
	
	public static int JetPackGuiID = 0;
	
	public static void addJetpackRecipe(INasaWorkbenchRecipe recipe)
	{
		jetpackRecipes.add(recipe);
	}
	
	public static List<INasaWorkbenchRecipe> getJetpackRecipes()
	{
		return jetpackRecipes;
	}
	
	public static ItemStack findMatchingJetpackRecipe(InventorySchematicJetpack craftMatrix)
	{
		for (INasaWorkbenchRecipe recipe : jetpackRecipes)
		{
			if (recipe.matches(craftMatrix))
			{
				return recipe.getRecipeOutput();
			}
		}
		
		return null;
	}
}

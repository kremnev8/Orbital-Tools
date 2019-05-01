package net.orbitallabs.jei;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import com.google.common.collect.Lists;
import mezz.jei.api.recipe.IRecipeWrapper;
import cofh.thermalexpansion.plugins.jei.machine.insolator.InsolatorRecipeWrapper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import net.minecraft.item.ItemStack;
import net.orbitallabs.utils.SchematicsUtil;

public class JetpackRecipeMaker {
	public static List<JetpackRecipeWrapper> getRecipesList()
	{
		List<JetpackRecipeWrapper> recipes = new ArrayList<>();
		
		for (INasaWorkbenchRecipe recipe : SchematicsUtil.getJetpackRecipes())
		{
			JetpackRecipeWrapper wrapper = new JetpackRecipeWrapper(recipe);
			recipes.add(wrapper);
		}
		
		return recipes;
	}
	
	public static class JetpackRecipeWrapper implements IRecipeWrapper { 
		@Nonnull
		private final INasaWorkbenchRecipe recipe;
		
		public JetpackRecipeWrapper(@Nonnull INasaWorkbenchRecipe recipe)
		{
			this.recipe = recipe; 
		}
		
		@Override
		public void getIngredients(IIngredients ingredients)
		{
			ingredients.setInputs(VanillaTypes.ITEM, Lists.newArrayList(recipe.getRecipeInput().values()));
			ingredients.setOutput(VanillaTypes.ITEM, this.recipe.getRecipeOutput());
		}
	}
}

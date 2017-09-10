package net.orbitallabs.jei;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import com.google.common.collect.Lists;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeWrapper;
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
	
	public static class JetpackRecipeWrapper extends BlankRecipeWrapper {
		@Nonnull
		private final INasaWorkbenchRecipe recipe;
		
		public JetpackRecipeWrapper(@Nonnull INasaWorkbenchRecipe recipe)
		{
			this.recipe = recipe;
		}
		
		@Override
		public void getIngredients(IIngredients ingredients)
		{
			ingredients.setInputs(ItemStack.class, Lists.newArrayList(recipe.getRecipeInput().values()));
			ingredients.setOutput(ItemStack.class, this.recipe.getRecipeOutput());
		}
	}
}

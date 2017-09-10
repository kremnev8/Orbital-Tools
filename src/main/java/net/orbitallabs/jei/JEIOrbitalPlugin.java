package net.orbitallabs.jei;

import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IJeiRuntime;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.ISubtypeRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IIngredientBlacklist;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import micdoodle8.mods.galacticraft.api.recipe.INasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import net.minecraft.item.ItemStack;
import net.orbitallabs.blocks.BlockContainerMod;
import net.orbitallabs.utils.OrbitalModInfo;
import net.orbitallabs.utils.SchematicsUtil;

@JEIPlugin
public class JEIOrbitalPlugin implements IModPlugin {
	
	@Override
	public void register(IModRegistry registry)
	{
		
		registry.addAdvancedGuiHandlers(new SlotMover());
		
		registry.handleRecipes(INasaWorkbenchRecipe.class, JetpackRecipeMaker.JetpackRecipeWrapper::new, OrbitalModInfo.MOD_ID + ".jetpackcraft");
		registry.addRecipes(SchematicsUtil.getJetpackRecipes(), OrbitalModInfo.MOD_ID + ".jetpackcraft");
		
		ItemStack nasaWorkbench = new ItemStack(GCBlocks.nasaWorkbench);
		registry.addRecipeCatalyst(nasaWorkbench, OrbitalModInfo.MOD_ID + ".jetpackcraft");
		//	registry.addRecipeCategoryCraftingItem(nasaWorkbench, RecipeCategories.ROCKET_T1_ID);
		
		IIngredientBlacklist blackl = registry.getJeiHelpers().getIngredientBlacklist();
		blackl.addIngredientToBlacklist(new ItemStack(BlockContainerMod.BlockInfo));
		blackl.addIngredientToBlacklist(new ItemStack(BlockContainerMod.BlockRemoveInfo));
		blackl.addIngredientToBlacklist(new ItemStack(BlockContainerMod.BlockFake));
	}
	
	@Override
	public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry)
	{
	}
	
	@Override
	public void registerIngredients(IModIngredientRegistration registry)
	{
		
	}
	
	@Override
	public void registerCategories(IRecipeCategoryRegistration registry)
	{
		IGuiHelper guiHelper = registry.getJeiHelpers().getGuiHelper();
		registry.addRecipeCategories(new JetpackCraftCategory(guiHelper));
	}
	
	@Override
	public void onRuntimeAvailable(IJeiRuntime jeiRuntime)
	{
	}
	
}
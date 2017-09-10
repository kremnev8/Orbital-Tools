package net.orbitallabs.jei;

import javax.annotation.Nonnull;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IGuiItemStackGroup;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.BlankRecipeCategory;
import mezz.jei.api.recipe.IRecipeWrapper;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.util.ResourceLocation;
import net.orbitallabs.utils.OrbitalModInfo;

public class JetpackCraftCategory extends BlankRecipeCategory {
	private static final ResourceLocation jetpackGuiTexture = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/schematic_jetpack.png");
	
	@Nonnull
	private final IDrawable background;
	@Nonnull
	private final String localizedName;
	
	public JetpackCraftCategory(IGuiHelper guiHelper)
	{
		this.background = guiHelper.createDrawable(jetpackGuiTexture, 7, 42, 160, 90);
		this.localizedName = GCCoreUtil.translate("tile.rocket_workbench.name");
		
	}
	
	@Nonnull
	@Override
	public String getUid()
	{
		return OrbitalModInfo.MOD_ID + ".jetpackcraft";
	}
	
	@Nonnull
	@Override
	public String getTitle()
	{
		return this.localizedName;
	}
	
	@Nonnull
	@Override
	public IDrawable getBackground()
	{
		return this.background;
	}
	
	@Override
	public void setRecipe(IRecipeLayout recipeLayout, IRecipeWrapper recipeWrapper, IIngredients ingredients)
	{
		IGuiItemStackGroup itemstacks = recipeLayout.getItemStacks();
		
		int x = 0;
		int y = -18;
		itemstacks.init(1, true, x + (18 * 1), y + (18 * 1));
		itemstacks.init(2, true, x + (18 * 2), y + (18 * 1));
		itemstacks.init(3, true, x + (18 * 3), y + (18 * 1));
		itemstacks.init(4, true, x + (18 * 4), y + (18 * 1));
		itemstacks.init(5, true, x + (18 * 5), y + (18 * 1));
		itemstacks.init(6, true, x + (18 * 1), y + (18 * 2));
		itemstacks.init(7, true, x + (18 * 2), y + (18 * 2));
		itemstacks.init(8, true, x + (18 * 3), y + (18 * 2));
		itemstacks.init(9, true, x + (18 * 4), y + (18 * 2));
		itemstacks.init(10, true, x + (18 * 5), y + (18 * 2));
		itemstacks.init(11, true, x + (18 * 1), y + (18 * 3));
		itemstacks.init(12, true, x + (18 * 2), y + (18 * 3));
		itemstacks.init(13, true, x + (18 * 3), y + (18 * 3));
		itemstacks.init(14, true, x + (18 * 4), y + (18 * 3));
		itemstacks.init(15, true, x + (18 * 5), y + (18 * 3));
		itemstacks.init(16, true, x + (18 * 1), y + (18 * 4));
		itemstacks.init(17, true, x + (18 * 2), y + (18 * 4));
		itemstacks.init(18, true, x + (18 * 3), y + (18 * 4));
		itemstacks.init(19, true, x + (18 * 4), y + (18 * 4));
		itemstacks.init(20, true, x + (18 * 5), y + (18 * 4));
		itemstacks.init(21, true, 0, 54);
		itemstacks.init(22, true, 0, 72);
		itemstacks.init(23, true, 109, 54);
		itemstacks.init(24, true, 109, 72);
		itemstacks.init(25, false, 134, 55);
		
		itemstacks.set(ingredients);
	}
	
	@Override
	public String getModName()
	{
		return OrbitalModInfo.MOD_NAME;
	}
}
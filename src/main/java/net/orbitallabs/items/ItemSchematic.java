
package net.orbitallabs.items;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicItem;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemSchematic extends ItemMod implements ISchematicItem, ISortableItem {
	
	public ItemSchematic(String uln)
	{
		super(uln);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
		this.show = true;
		GCItems.registerSorted(this);
	}
	
	@Override
	public CreativeTabs getCreativeTab()
	{
		return GalacticraftCore.galacticraftItemsTab;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyCore.galacticraftItem;
	}
	
	@Override
	public String getDescription(int meta)
	{
		switch (meta) {
		case 0:
			return I18n.format(("schematic.jetpack.name"));
		}
		return "";
	}
	
	@Override
	public String getShiftDescription(int meta)
	{
		return "";
	}
	
	@Override
	public EnumSortCategoryItem getCategory(int meta)
	{
		return EnumSortCategoryItem.SCHEMATIC;
	}
	
}

package net.orbitallabs.utils;

import java.util.List;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class ItemUtil {
	
	public static boolean haveListItem(List<ItemStack> found, ItemStack is)
	{
		for (int i = 0; i < found.size(); i++)
		{
			ItemStack curr = found.get(i);
			if (is != null && curr != null && curr.getItem() == is.getItem() && (!is.getHasSubtypes() || curr.getItemDamage() == is.getItemDamage())
					&& curr.getCount() >= is.getCount())
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean haveContainerItem(IInventory inv, ItemStack is)
	{
		for (int i = 0; i < inv.getSizeInventory(); i++)
		{
			ItemStack curr = inv.getStackInSlot(i);
			if (is != null && curr != null && curr.getItem() == is.getItem() && (!is.getHasSubtypes() || curr.getItemDamage() == is.getItemDamage())
					&& curr.getCount() >= is.getCount())
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * check whether two itemStack more or equal by OreDictionary
	 * 
	 * @param is1
	 *            "Pattern" item
	 * @param is2
	 *            Other item
	 */
	public static boolean AreItemStackEqual(ItemStack is1, ItemStack is2, boolean ignoreStackSize)
	{
		if (is1 == null && is2 == null || (is1 != null && is1.isEmpty() && is2 != null && is2.isEmpty()))
		{
			return true;
		}
		if (is1 != null && is1.isEmpty() || is2 != null && is2.isEmpty())
		{
			return false;
		}
		
		if (is1 != null && is2 != null
				&& (ArrContain1SameInt(OreDictionary.getOreIDs(is1), OreDictionary.getOreIDs(is2))
						|| (is1.getItem() == is2.getItem()) && (!is1.getItem().getHasSubtypes() || is2.getItemDamage() == is1.getItemDamage()))
				&& (is1.getCount() >= is2.getCount() || ignoreStackSize))
		
		// .getItem() == is.getItem() && (!is.getHasSubtypes() ||
		// curr.getItemDamage() == is.getItemDamage()) && curr.stackSize >=
		// is.stackSize)
		{
			return true;
		}
		
		return false;
	}
	
	private static boolean ArrContain1SameInt(int[] ar1, int[] ar2)
	{
		if (ar1 != null && ar2 != null)
		{
			for (int i = 0; i < ar1.length; i++)
			{
				for (int j = 0; j < ar2.length; j++)
				{
					if (ar1[i] == ar2[j])
					{
						return true;
					}
				}
			}
		}
		return false;
	}
}

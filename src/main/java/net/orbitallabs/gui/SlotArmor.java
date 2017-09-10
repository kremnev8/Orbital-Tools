
package net.orbitallabs.gui;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class SlotArmor extends Slot {
	int type;
	
	public SlotArmor(IInventory iInventory, int i, int x, int y, int type)
	{
		super(iInventory, i, x, y);
		this.type = type;
	}
	
	public boolean isItemValid(ItemStack stack)
	{
		if (stack == null)
		{
			return false;
		}
		Item chestItem = stack.getItem();
		if ((chestItem instanceof ItemArmor))
		{
			ItemArmor armorChest = (ItemArmor) chestItem;
			int armorType = armorChest.armorType.getIndex();
			if (armorType == type)
			{
				return true;
			}
			return false;
		}
		return false;
	}
	
	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}
}
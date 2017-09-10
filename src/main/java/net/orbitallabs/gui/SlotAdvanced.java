
package net.orbitallabs.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotAdvanced extends Slot {
	
	public SlotAdvanced(IInventory inv, int id, int x, int y)
	{
		super(inv, id, x, y);
	}
	
	/**
	 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
	 */
	public boolean isItemValid(ItemStack is)
	{
		return true;
	}
	
	public int getSlotStackLimit()
	{
		return this.inventory.getInventoryStackLimit();
	}
	
	public boolean canTakeStack(EntityPlayer p_82869_1_)
	{
		return true;
	}
	
}

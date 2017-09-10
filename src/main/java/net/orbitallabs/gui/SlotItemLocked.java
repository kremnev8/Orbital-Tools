
package net.orbitallabs.gui;

import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.orbitallabs.tiles.TileEntityDockingPort;

public class SlotItemLocked extends Slot {
	public List<ItemStack> allowedItems;
	public TileEntityDockingPort tile;
	
	public SlotItemLocked(IInventory inv, int id, int x, int y, List<ItemStack> allowed)
	{
		super(inv, id, x, y);
		allowedItems = allowed;
	}
	
	public SlotItemLocked(IInventory inv, int id, int x, int y, List<ItemStack> allowed, TileEntityDockingPort te)
	{
		super(inv, id, x, y);
		this.tile = te;
		allowedItems = allowed;
	}
	
	/**
	 * Check if the stack is a valid item for this slot. Always true beside for the armor slots.
	 */
	public boolean isItemValid(ItemStack is)
	{
		if (allowedItems.size() == 0)
		{
			return true;
		} else
		{
			for (int i = 0; i < allowedItems.size(); i++)
			{
				if (is.getItem() == allowedItems.get(i).getItem())
				{
					return true;
				}
			}
		}
		
		return false;
	}
	
	public int getSlotStackLimit()
	{
		return this.inventory.getInventoryStackLimit();
	}
	
	public boolean canTakeStack(EntityPlayer p_82869_1_)
	{
		if (tile != null)
		{
			if (tile.getSizeInventory() - 4 != 0)
			{
				for (int i = 0; i < tile.getSizeInventory() - 4; i++)
				{
					if (tile.getStackInSlot(i) != null)
					{
						return false;
					}
				}
				
			}
		}
		return true;
	}
	
}

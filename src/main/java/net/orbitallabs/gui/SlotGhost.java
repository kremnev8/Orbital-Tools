
package net.orbitallabs.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotGhost extends Slot {
	public boolean state = false;
	
	public SlotGhost(IInventory inv, int slotIndex, int xPos, int yPost)
	{
		super(inv, slotIndex, xPos, yPost);
		this.slotNumber = slotIndex;
	}
	
	public SlotGhost setState(boolean state)
	{
		this.state = state;
		this.putStack(new ItemStack(Blocks.LEAVES));
		return this;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		if (stack != null)
		{
			this.putStack(stack);
		}
		
		return false;
	}
	
	@Override
	public boolean canTakeStack(EntityPlayer player)
	{
		return false;
	}
	
	@Override
	public int getSlotStackLimit()
	{
		return 64;
	}
}
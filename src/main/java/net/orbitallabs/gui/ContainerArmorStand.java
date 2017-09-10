
package net.orbitallabs.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.orbitallabs.tiles.TileEntityArmorStand;

public class ContainerArmorStand extends Container {
	
	public TileEntityArmorStand Standnventory;
	private InventoryPlayer playerInv;
	
	public ContainerArmorStand(EntityPlayer player, IInventory ArmorStand)
	{
		this.Standnventory = (TileEntityArmorStand) ArmorStand;
		this.playerInv = player.inventory;
		this.inventorySlots.clear();
		this.inventoryItemStacks.clear();
		
		for (int i = 0; i < 4; i++)
		{
			this.addSlotToContainer(new SlotArmor(ArmorStand, i, 98, 7 + i * 18, Math.abs(i - 3)));
		}
		
		for (int j = 0; j < 3; j++)
		{
			for (int k = 0; k < 9; k++)
			{
				this.addSlotToContainer(new Slot(playerInv, k + j * 9 + 9, 8 + k * 18, 84 + j * 18));
			}
		}
		
		for (int j = 0; j < 9; j++)
		{
			this.addSlotToContainer(new Slot(playerInv, j, 8 + j * 18, 142));
		}
		
		int x = 8;
		int y = 61;//0 is helmet, 1 is plate, 2 is legs and 3 is boots */
		this.addSlotToContainer(new SlotArmor(playerInv, 39, x, y, 3));
		this.addSlotToContainer(new SlotArmor(playerInv, 38, x + 18, y, 2));
		this.addSlotToContainer(new SlotArmor(playerInv, 37, x + (18 * 2), y, 1));
		this.addSlotToContainer(new SlotArmor(playerInv, 36, x + (18 * 3), y, 0));
		Standnventory.openInventory(player);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = (Slot) this.inventorySlots.get(par2);
		final int b = this.inventorySlots.size();
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (par2 < this.Standnventory.getSizeInventory())
			{
				if (!this.mergeItemStack(itemstack1, b - 36, b, true))
				{
					return ItemStack.EMPTY;
				}
			} else
			{
				if (itemstack1.getItem() instanceof ItemArmor)
				{
					int type = ((ItemArmor) itemstack1.getItem()).armorType.getIndex();
					if (!this.mergeItemStack(itemstack1, Math.abs(type - 3), Math.abs(type - 3) + 1, false))
					{
						return ItemStack.EMPTY;
					}
				} else
				{
					return ItemStack.EMPTY;
				}
				
			}
			
			if (itemstack1.getCount() == 0)
			{
				slot.putStack(ItemStack.EMPTY);
			} else
			{
				slot.onSlotChanged();
			}
		}
		
		return itemstack;
	}
	
	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
		this.Standnventory.closeInventory(par1EntityPlayer);
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}
}
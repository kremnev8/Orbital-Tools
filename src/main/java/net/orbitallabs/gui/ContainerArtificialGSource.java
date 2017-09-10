package net.orbitallabs.gui;

import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseElectricBlock;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.orbitallabs.tiles.TileEntityGravitySource;

public class ContainerArtificialGSource extends Container {
	public TileBaseElectricBlock tileEntity;
	
	public ContainerArtificialGSource(InventoryPlayer par1InventoryPlayer, TileEntityGravitySource fuelLoader)
	{
		this.tileEntity = fuelLoader;
		
		int var6;
		int var7;
		
		// Player inv:
		
		for (var6 = 0; var6 < 3; ++var6)
		{
			for (var7 = 0; var7 < 9; ++var7)
			{
				this.addSlotToContainer(new Slot(par1InventoryPlayer, var7 + var6 * 9 + 9, 8 + var7 * 18, 67 + var6 * 18));
			}
		}
		
		for (var6 = 0; var6 < 9; ++var6)
		{
			this.addSlotToContainer(new Slot(par1InventoryPlayer, var6, 8 + var6 * 18, 125));
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer var1)
	{
		return this.tileEntity.isUsableByPlayer(var1);
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2)
	{
		ItemStack var3 = ItemStack.EMPTY;
		final Slot slot = (Slot) this.inventorySlots.get(par2);
		
		if (slot != null && slot.getHasStack())
		{
			final ItemStack var5 = slot.getStack();
			var3 = var5.copy();
			
			if (par2 < 27)
			{
				if (!this.mergeItemStack(var5, 27, 36, false))
				{
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(var5, 0, 27, false))
			{
				return ItemStack.EMPTY;
			}
			
			if (var5.getCount() == 0)
			{
				slot.putStack(ItemStack.EMPTY);
			} else
			{
				slot.onSlotChanged();
			}
			
			if (var5.getCount() == var3.getCount())
			{
				return ItemStack.EMPTY;
			}
			
			slot.onTake(par1EntityPlayer, var5);
		}
		
		return var3;
	}
}
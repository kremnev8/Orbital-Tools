
package net.orbitallabs.gui;

import java.util.ArrayList;
import java.util.List;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.orbitallabs.tiles.TileEntityDockingPort;

public class ContainerDockingPort extends Container {
	private IInventory dockingInventory;
	private IInventory playerinv;
	private int lastDockinvSize;
	public int numRows;
	
	public ContainerDockingPort(IInventory playerinv, IInventory dockinv)
	{
		this.dockingInventory = dockinv;
		this.playerinv = playerinv;
		this.lastDockinvSize = dockinv.getSizeInventory();
		ReloadContainer(playerinv, dockinv);
	}
	
	public void ReloadContainer(IInventory playerinv, IInventory dockinv)
	{
		dockingInventory.closeInventory(((InventoryPlayer) playerinv).player);
		this.inventorySlots.clear();
		this.inventoryItemStacks.clear();
		this.numRows = (dockinv.getSizeInventory() - 4) / 9;
		int i = (this.numRows - 4) * 18 + 19;
		int j;
		int k;
		
		for (j = 0; j < this.numRows; ++j)
		{
			for (k = 0; k < 9; ++k)
			{
				this.addSlotToContainer(new Slot(dockinv, k + j * 9, 8 + k * 18, 18 + j * 18));
			}
		}
		
		ArrayList<ItemStack> list1 = new ArrayList<>();
		list1.add(new ItemStack(GCBlocks.landingPad));
		this.addSlotToContainer(new SlotItemLocked(dockinv, dockinv.getSizeInventory() - 3, 125 + 0 * 18, (this.numRows == 0 ? 22 : 24) + this.numRows * 18,
				(List<ItemStack>) list1.clone()));
		list1.clear();
		list1.add(new ItemStack(GCItems.rocketTier1));
		list1.add(new ItemStack(MarsItems.rocketMars));
		list1.add(new ItemStack(AsteroidsItems.tier3Rocket));
		this.addSlotToContainer(new SlotItemLocked(dockinv, dockinv.getSizeInventory() - 2, 125 + 1 * 18, (this.numRows == 0 ? 22 : 24) + this.numRows * 18,
				(List<ItemStack>) list1.clone(), (TileEntityDockingPort) dockingInventory));
		this.addSlotToContainer(new SlotAdvanced(dockinv, dockinv.getSizeInventory() - 1, 75, (this.numRows == 0 ? 18 : 20) + this.numRows * 18));
		this.addSlotToContainer(new SlotAdvanced(dockinv, dockinv.getSizeInventory() - 4, 75, (this.numRows == 0 ? 36 : 38) + this.numRows * 18));
		
		for (j = 0; j < 3; ++j)
		{
			for (k = 0; k < 9; ++k)
			{
				this.addSlotToContainer(new Slot(playerinv, k + j * 9 + 9, 8 + k * 18, (this.numRows == 0 ? 116 : 118) + j * 18 + i));
			}
		}
		
		for (j = 0; j < 9; ++j)
		{
			this.addSlotToContainer(new Slot(playerinv, j, 8 + j * 18, (this.numRows == 0 ? 174 : 176) + i));
		}
		dockingInventory.openInventory(((InventoryPlayer) playerinv).player);
	}
	
	@Override
	public void detectAndSendChanges()
	{
		if (dockingInventory.getSizeInventory() != lastDockinvSize)
		{
			this.lastDockinvSize = dockingInventory.getSizeInventory();
			// PacketHandler.sendToAllAround(new InvScalePacket(((TileEntityDockingPort)parachestInventory).getSizeInventory(), ((TileEntityDockingPort)parachestInventory).xCoord, ((TileEntityDockingPort)parachestInventory).yCoord, ((TileEntityDockingPort)parachestInventory).zCoord),
			//			new TargetPoint(((TileEntityDockingPort)parachestInventory).getWorldObj().provider.dimensionId, ((TileEntityDockingPort)parachestInventory).xCoord, ((TileEntityDockingPort)parachestInventory).yCoord, ((TileEntityDockingPort)parachestInventory).zCoord, 80));
			this.ReloadContainer(playerinv, dockingInventory);
		} else super.detectAndSendChanges();
	}
	
	@Override
	public void putStackInSlot(int p_75141_1_, ItemStack p_75141_2_)
	{
		if (p_75141_1_ < this.inventorySlots.size())
		{
			super.putStackInSlot(p_75141_1_, p_75141_2_);
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return this.dockingInventory.isUsableByPlayer(par1EntityPlayer);
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
			
			if (par2 < this.dockingInventory.getSizeInventory())
			{
				if (!this.mergeItemStack(itemstack1, b - 36, b, true))
				{
					return ItemStack.EMPTY;
				}
			} else
			{
				if (itemstack1.getItem() == GCItems.rocketTier1 || itemstack1.getItem() == MarsItems.rocketMars || itemstack1.getItem() == AsteroidsItems.tier3Rocket)
				{
					if (!this.mergeItemStack(itemstack1, this.dockingInventory.getSizeInventory() - 3, this.dockingInventory.getSizeInventory() - 2, false))
					{
						return ItemStack.EMPTY;
					}
				} else
				{
					if (!this.mergeItemStack(itemstack1, 0, this.dockingInventory.getSizeInventory(), false))
					{
						return ItemStack.EMPTY;
					}
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
	
	/**
	 * Callback for when the crafting gui is closed.
	 */
	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
		this.dockingInventory.closeInventory(((InventoryPlayer) playerinv).player);
	}
	
	/**
	 * Return this chest container's lower chest inventory.
	 */
	public IInventory getparachestInventory()
	{
		return this.dockingInventory;
	}
}
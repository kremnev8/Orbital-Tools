
package net.orbitallabs.gui;

import micdoodle8.mods.galacticraft.core.inventory.SlotRocketBenchResult;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.orbitallabs.utils.SchematicsUtil;

public class ContainerSchematicJetpack extends Container {
	public InventorySchematicJetpack craftMatrix = new InventorySchematicJetpack(this);
	public IInventory craftResult = new InventoryCraftResult();
	private final World worldObj;
	
	public ContainerSchematicJetpack(InventoryPlayer par1InventoryPlayer, int x, int y, int z)
	{
		this.worldObj = par1InventoryPlayer.player.world;
		this.addSlotToContainer(new SlotRocketBenchResult(par1InventoryPlayer.player, this.craftMatrix, this.craftResult, 0, 142, 72 + 26));
		int i;
		int j;
		int count = 1;
		
		for (i = 0; i < 4; i++)
		{
			for (j = 0; j < 5; j++)
			{
				this.addSlotToContainer(new SlotSchematicJetpack(this.craftMatrix, count++, 26 + j * 18, 43 + i * 18, x, y, z, par1InventoryPlayer.player));
			}
			
		}
		for (i = 0; i < 2; i++)
			this.addSlotToContainer(new SlotSchematicJetpack(this.craftMatrix, count++, 8, 97 + i * 18, x, y, z, par1InventoryPlayer.player));
		
		for (i = 0; i < 2; i++)
			this.addSlotToContainer(new SlotSchematicJetpack(this.craftMatrix, count++, 116, 97 + i * 18, x, y, z, par1InventoryPlayer.player));
		
		/*
		 * // Miner top layer for (i = 0; i < 4; i++)
		 * this.addSlotToContainer(new SlotSchematicJetpack(this.craftMatrix,
		 * count++, 27 + i * 18, 35 + 26, x, y, z, par1InventoryPlayer.player));
		 * 
		 * // Miner mid layer for (i = 0; i < 5; i++)
		 * this.addSlotToContainer(new SlotSchematicJetpack(this.craftMatrix,
		 * count++, 16 + i * 18, 53 + 26, x, y, z, par1InventoryPlayer.player));
		 * 
		 * // Miner bottom layer for (i = 0; i < 3; i++)
		 * this.addSlotToContainer(new SlotSchematicJetpack(this.craftMatrix,
		 * count++, 44 + i * 18, 71 + 26, x, y, z, par1InventoryPlayer.player));
		 * 
		 * // Laser for (i = 0; i < 2; ++i) { this.addSlotToContainer(new
		 * SlotSchematicJetpack(this.craftMatrix, count++, 8 + i * 18, 77 + 26,
		 * x, y, z, par1InventoryPlayer.player)); }
		 */
		
		// Player inv:
		
		for (i = 0; i < 3; ++i)
		{
			for (j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(par1InventoryPlayer, j + i * 9 + 9, 8 + j * 18, 140 + i * 18));
			}
		}
		
		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(par1InventoryPlayer, i, 8 + i * 18, 144 + 54));
		}
		
		this.onCraftMatrixChanged(this.craftMatrix);
	}
	
	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer)
	{
		super.onContainerClosed(par1EntityPlayer);
		
		if (!this.worldObj.isRemote)
		{
			for (int var2 = 1; var2 < this.craftMatrix.getSizeInventory(); ++var2)
			{
				final ItemStack var3 = this.craftMatrix.removeStackFromSlot(var2);
				
				if (var3 != null)
				{
					par1EntityPlayer.entityDropItem(var3, 0.0F);
				}
			}
		}
	}
	
	@Override
	public void onCraftMatrixChanged(IInventory par1IInventory)
	{
		this.craftResult.setInventorySlotContents(0, SchematicsUtil.findMatchingJetpackRecipe(this.craftMatrix));
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer par1EntityPlayer)
	{
		return true;
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par1)
	{
		ItemStack var2 = ItemStack.EMPTY;
		final Slot var3 = (Slot) this.inventorySlots.get(par1);
		
		if (var3 != null && var3.getHasStack())
		{
			final ItemStack var4 = var3.getStack();
			var2 = var4.copy();
			
			if (par1 <= 24)
			{
				if (!this.mergeItemStack(var4, 25, 61, false))
				{
					return ItemStack.EMPTY;
				}
				
				var3.onSlotChange(var4, var2);
			} else
			{
				boolean valid = false;
				for (int i = 1; i < 25; i++)
				{
					Slot testSlot = (Slot) this.inventorySlots.get(i);
					if (!testSlot.getHasStack() && testSlot.isItemValid(var2))
					{
						valid = true;
						break;
					}
				}
				if (valid)
				{
					if (!this.mergeOneItemTestValid(var4, 1, 25))
					{
						return ItemStack.EMPTY;
					}
				} else
				{
					if (par1 >= 25 && par1 < 52)
					{
						if (!this.mergeItemStack(var4, 52, 61, false))
						{
							return ItemStack.EMPTY;
						}
					} else if (par1 >= 52 && par1 < 61)
					{
						if (!this.mergeItemStack(var4, 25, 52, false))
						{
							return ItemStack.EMPTY;
						}
					} else if (!this.mergeItemStack(var4, 25, 61, false))
					{
						return ItemStack.EMPTY;
					}
				}
			}
			
			if (var4.getCount() == 0)
			{
				var3.putStack(ItemStack.EMPTY);
			} else
			{
				var3.onSlotChanged();
			}
			
			if (var4.getCount() == var2.getCount())
			{
				return ItemStack.EMPTY;
			}
			var3.onTake(par1EntityPlayer, var4);
		}
		
		return var2;
	}
	
	protected boolean mergeOneItemTestValid(ItemStack par1ItemStack, int par2, int par3)
	{
		boolean flag1 = false;
		if (par1ItemStack.getCount() > 0)
		{
			Slot slot;
			ItemStack slotStack;
			
			for (int k = par2; k < par3; k++)
			{
				slot = (Slot) this.inventorySlots.get(k);
				slotStack = slot.getStack();
				
				if (slotStack == null && slot.isItemValid(par1ItemStack))
				{
					ItemStack stackOneItem = par1ItemStack.copy();
					stackOneItem.shrink(1);
					slot.putStack(stackOneItem);
					slot.onSlotChanged();
					flag1 = true;
					break;
				}
			}
		}
		
		return flag1;
	}
}

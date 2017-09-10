
package net.orbitallabs.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class InventorySchematicJetpack implements IInventory {
	private final NonNullList<ItemStack> stackList;
	private final int inventoryWidth;
	private final Container eventHandler;
	
	public InventorySchematicJetpack(Container par1Container)
	{
		this.stackList = NonNullList.withSize(29, ItemStack.EMPTY);
		this.eventHandler = par1Container;
		this.inventoryWidth = 5;
	}
	
	@Override
	public int getSizeInventory()
	{
		return this.stackList.size();
	}
	
	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return par1 >= this.getSizeInventory() ? ItemStack.EMPTY : this.stackList.get(par1);
	}
	
	public ItemStack getStackInRowAndColumn(int par1, int par2)
	{
		if (par1 >= 0 && par1 < this.inventoryWidth)
		{
			final int var3 = par1 + par2 * this.inventoryWidth;
			if (var3 >= 15)
			{
				return null;
			}
			return this.getStackInSlot(var3);
		}
		return null;
	}
	
	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		ItemStack var3;
		
		if (this.stackList.get(par1).getCount() <= par2)
		{
			var3 = this.stackList.get(par1);
			this.stackList.set(par1, ItemStack.EMPTY);
			this.eventHandler.onCraftMatrixChanged(this);
			return var3;
		} else
		{
			var3 = this.stackList.get(par1).splitStack(par2);
			
			if (this.stackList.get(par1).getCount() == 0)
			{
				this.stackList.set(par1, ItemStack.EMPTY);
			}
			
			this.eventHandler.onCraftMatrixChanged(this);
			return var3;
		}
	}
	
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.stackList.set(par1, par2ItemStack);
		this.eventHandler.onCraftMatrixChanged(this);
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	@Override
	public void markDirty()
	{
	}
	
	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack)
	{
		return true;
	}
	
	@Override
	public String getName()
	{
		return "container.crafting";
	}
	
	@Override
	public boolean hasCustomName()
	{
		return false;
	}
	
	@Override
	public ITextComponent getDisplayName()
	{
		return new TextComponentString("container.crafting");
	}
	
	@Override
	public boolean isEmpty()
	{
		boolean found = false;
		for (int i = 0; i < stackList.size(); i++)
		{
			found = !stackList.get(i).isEmpty();
		}
		return !found;
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		ItemStack ret = stackList.get(index).copy();
		stackList.set(index, ItemStack.EMPTY);
		
		return ret;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return true;
	}
	
	@Override
	public void openInventory(EntityPlayer player)
	{
		
	}
	
	@Override
	public void closeInventory(EntityPlayer player)
	{
		
	}
	
	@Override
	public int getField(int id)
	{
		return 0;
	}
	
	@Override
	public void setField(int id, int value)
	{
	}
	
	@Override
	public int getFieldCount()
	{
		return 0;
	}
	
	@Override
	public void clear()
	{
		stackList.clear();
	}
}
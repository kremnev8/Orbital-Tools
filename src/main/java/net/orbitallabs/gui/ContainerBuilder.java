package net.orbitallabs.gui;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.OpenBuilderGuiPacket;
import net.orbitallabs.utils.ItemUtil;

public class ContainerBuilder extends Container {
	
	public InventoryPlayer inventory;
	
	private List<ItemStack> found = new ArrayList();
	
	public ContainerBuilder(InventoryPlayer Pinv)
	{
		this.inventory = Pinv;
		boolean checkPlayer = false;
		if (!Pinv.player.world.isRemote)
		{
			ItemStack held = Pinv.player.getHeldItem(EnumHand.MAIN_HAND);
			if (held.hasTagCompound())
			{
				NBTTagCompound tag = held.getTagCompound();
				if (tag.hasKey("chests"))
				{
					NBTTagList list = tag.getTagList("chests", 11);
					if (list.tagCount() > 0)
					{
						for (int i = 0; i < list.tagCount(); i++)
						{
							int[] pos = list.getIntArrayAt(i);
							World world = Pinv.player.world;
							if (world != null)
							{
								TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
								if (te instanceof IInventory)
								{
									IInventory inv = (IInventory) te;
									if (inv.getSizeInventory() > 0)
									{
										for (int j = 0; j < inv.getSizeInventory(); j++)
										{
											if (inv.getStackInSlot(j) != null)
											{
												found.add(inv.getStackInSlot(j).copy());
											}
										}
									}
								}
							}
						}
						if (inventory.getSizeInventory() > 0)
						{
							for (int j = 0; j < inventory.getSizeInventory(); j++)
							{
								if (inventory.getStackInSlot(j) != null)
								{
									found.add(inventory.getStackInSlot(j).copy());
								}
							}
						}
						for (int i = 0; i < found.size(); i++)
						{
							ItemStack curr = found.get(i);
							for (int j = 0; j < found.size(); j += 0)
							{
								boolean removed = false;
								if (j != i)
								{
									ItemStack last = found.get(j);
									if (last != null && curr != null)
									{
										if (ItemUtil.AreItemStackEqual(curr, last, true))
										{
											curr.grow(last.getCount());
											found.remove(j);
											removed = true;
										}
									}
								}
								if (!removed)
								{
									j++;
								}
							}
							// found.remove(i);
							// found.add(curr);
						}
						
						PacketHandler.sendTo(new OpenBuilderGuiPacket(found), (EntityPlayerMP) Pinv.player);
						
					} else
					{
						checkPlayer = true;
					}
				} else
				{
					checkPlayer = true;
				}
			} else
			{
				checkPlayer = true;
			}
			if (checkPlayer)
			{
				if (inventory.getSizeInventory() > 0)
				{
					for (int j = 0; j < inventory.getSizeInventory(); j++)
					{
						if (inventory.getStackInSlot(j) != null)
						{
							found.add(inventory.getStackInSlot(j).copy());
						}
					}
					for (int i = 0; i < found.size(); i++)
					{
						ItemStack curr = found.get(i);
						for (int j = 0; j < found.size(); j += 0)
						{
							boolean removed = false;
							if (j != i)
							{
								ItemStack last = found.get(j);
								if (last != null && curr != null)
								{
									if (ItemUtil.AreItemStackEqual(curr, last, true))
									{
										curr.grow(last.getCount());
										found.remove(j);
										removed = true;
									}
								}
							}
							if (!removed)
							{
								j++;
							}
						}
						// found.remove(i);
						// found.add(curr);
					}
					PacketHandler.sendTo(new OpenBuilderGuiPacket(found), (EntityPlayerMP) Pinv.player);
				}
				
			}
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_)
	{
		return true;
	}
	
	// anti-crash lines
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slotRaw)
	{
		return null;
	}
	
	public Slot getSlot(int p_75139_1_)
	{
		return null;
	}
	
	public ItemStack slotClick(int p_75144_1_, int p_75144_2_, int p_75144_3_, EntityPlayer p_75144_4_)
	{
		return null;
	}
	
	public void putStacksInSlots(ItemStack[] p_75131_1_)
	{
	}
	
	public void putStackInSlot(int p_75141_1_, ItemStack p_75141_2_)
	{
	}
	
	protected boolean mergeItemStack(ItemStack p_75135_1_, int p_75135_2_, int p_75135_3_, boolean p_75135_4_)
	{
		return false;
	}
	
	public boolean canDragIntoSlot(Slot p_94531_1_)
	{
		return false;
	}
	
	public NonNullList<ItemStack> getInventory()
	{
		return NonNullList.create();
	}
	
	@Override
	public void onContainerClosed(EntityPlayer p_75134_1_)
	{
		super.onContainerClosed(p_75134_1_);
		
	}
	
}

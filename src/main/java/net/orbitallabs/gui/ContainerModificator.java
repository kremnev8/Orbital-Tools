package net.orbitallabs.gui;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.CloseScreenPacket;
import net.orbitallabs.network.packets.OpenGuiPacket;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.tiles.TileEntityInfo;
import net.orbitallabs.tiles.TileEntityRemoveInfo;
import net.orbitallabs.utils.ChatUtils;
import net.orbitallabs.utils.FacingUtils;
import net.orbitallabs.utils.LocalizedChatComponent;
import net.orbitallabs.utils.LocalizedString;
import net.orbitallabs.utils.MatrixHelper;

public class ContainerModificator extends Container {
	
	public InventoryPlayer inventory;
	public World world;
	TileEntityRemoveInfo te;
	
	public ContainerModificator(InventoryPlayer inv, TileEntityRemoveInfo tile)
	{
		this.inventory = inv;
		world = inv.player.world;
		if (!world.isRemote)
		{
			te = tile;
			EntityPlayer player = world.getPlayerEntityByName(inv.player.getDisplayName().getUnformattedText());
			if (te.infoBlocks != null && te.infoBlocks.size() > 0 && te.infoBlocks.get(0) != null)
			{
				if (te.infoBlocks.get(0).Object != null && te.infoBlocks.get(0).Object.getUnlocalizedName().equals(Structure.BIGHHALL))
				{
					Structure Object = te.infoBlocks.get(0).Object.copy();
					List<Structure> ChildObjects = new ArrayList();
					ChildObjects.addAll(te.infoBlocks.get(0).ChildObjects);
					List<Structure> AddObjects = new ArrayList();
					AddObjects.addAll(te.infoBlocks.get(0).AddObjects);
					if (te.infoBlocks.size() > 1)
					{
						for (int i = 1; i < te.infoBlocks.size(); i++)
						{
							TileEntityInfo te2 = te.infoBlocks.get(i);
							ChildObjects.addAll(te2.ChildObjects);
							AddObjects.addAll(te2.AddObjects);
							
						}
					}
					
					if (ChildObjects != null && ChildObjects.size() > 0)
					{
						for (int i = 0; i < ChildObjects.size(); i++)
						{
							Structure str = ChildObjects.get(i);
							BlockPos Ipos = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos);
							if (Ipos != null)
							{
								FacingUtils.IncreaseByDir(str.placementDir, Ipos, 9);
								TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos);
								if (Ite.Object.connections != null && Ite.Object.connections.size() > 0)
								{
									ChildObjects.get(i).connections = Ite.Object.connections;
								}
							}
							
						}
					}
					
					PacketHandler.sendTo(new OpenGuiPacket(Object, AddObjects, ChildObjects, false), (EntityPlayerMP) player);
				} else
				{
					if (te.infoBlocks.get(0).ChildObjects != null && te.infoBlocks.get(0).ChildObjects.size() > 0)
					{
						for (int i = 0; i < te.infoBlocks.get(0).ChildObjects.size(); i++)
						{
							Structure str = te.infoBlocks.get(0).ChildObjects.get(i);
							BlockPos Ipos = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos);
							if (Ipos != null)
							{
								FacingUtils.IncreaseByDir(str.placementDir, Ipos, 9);
								TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos);
								if (Ite != null && Ite.Object.connections != null && Ite.Object.connections.size() > 0)
								{
									te.infoBlocks.get(0).ChildObjects.get(i).connections = Ite.Object.connections;
								}
							}
							
						}
					}
					PacketHandler.sendTo(new OpenGuiPacket(te.infoBlocks.get(0), false), (EntityPlayerMP) player);
				}
				
			} else
			{
				PacketHandler.sendTo(new CloseScreenPacket(), (EntityPlayerMP) player);
				ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("remover.no_linked_tile.name", TextFormatting.RED)));
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
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
	}
	
	@Override
	public void onContainerClosed(EntityPlayer p_75134_1_)
	{
		super.onContainerClosed(p_75134_1_);
		
	}
	
}

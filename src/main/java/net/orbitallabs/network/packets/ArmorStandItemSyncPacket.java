
package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.tiles.TileEntityArmorStand;
import net.orbitallabs.utils.OTLoger;

public class ArmorStandItemSyncPacket implements IMessage {
	private NonNullList<ItemStack> items;
	private int x;
	private int y;
	private int z;
	
	public ArmorStandItemSyncPacket()
	{
	}
	
	public ArmorStandItemSyncPacket(NonNullList<ItemStack> is, int x, int y, int z)
	{
		this.items = is;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		NBTTagList nbttaglist = tag.getTagList("Items", 10);
		
		x = ByteBufUtils.readVarInt(buf, 5);
		y = ByteBufUtils.readVarInt(buf, 5);
		z = ByteBufUtils.readVarInt(buf, 5);
		
		this.items = NonNullList.withSize(nbttaglist.tagCount(), ItemStack.EMPTY);
		//int length = ByteBufUtils.readVarInt(buf, 5);
		
		for (int i = 0; i < nbttaglist.tagCount(); ++i)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("Slot") & 255;
			
			if (j >= 0)
			{
				this.items.set(j, new ItemStack(nbttagcompound1));
			}
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagCompound tag = new NBTTagCompound();
		
		NBTTagList nbttaglist = new NBTTagList();
		
		for (int i = 0; i < this.items.size(); ++i)
		{
			if (this.items.get(i) != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.items.get(i).writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		
		tag.setTag("Items", nbttaglist);
		ByteBufUtils.writeTag(buf, tag);
		ByteBufUtils.writeVarInt(buf, x, 5);
		ByteBufUtils.writeVarInt(buf, y, 5);
		ByteBufUtils.writeVarInt(buf, z, 5);
		//ByteBufUtils.writeVarInt(buf, items.length, 5);
	}
	
	public static class Handler implements IMessageHandler<ArmorStandItemSyncPacket, IMessage> {
		@Override
		public IMessage onMessage(ArmorStandItemSyncPacket pkt, MessageContext ctx)
		{
			if (Minecraft.getMinecraft().world != null)
			{
				
				TileEntityArmorStand tile = (TileEntityArmorStand) Minecraft.getMinecraft().world.getTileEntity(new BlockPos(pkt.x, pkt.y, pkt.z));
				
				if (tile == null)
				{
					//	OTLoger.logInfo("NULL tile entity reference in Armor stand item sync update packet! Please report to dev!");
				} else
				{
					tile.items = pkt.items;
				}
			} else
			{
				OTLoger.logInfo("NULL world reference in Armor stand item sync update packet! Please report to dev!");
			}
			
			return null;
		}
	}
}
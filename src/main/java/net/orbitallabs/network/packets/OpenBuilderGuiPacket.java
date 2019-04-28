
package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.gui.GuiBuilder;
import net.orbitallabs.utils.OTLoger;

public class OpenBuilderGuiPacket implements IMessage {
	private NonNullList<ItemStack> items = NonNullList.create();
	
	public OpenBuilderGuiPacket()
	{
	}
	
	public OpenBuilderGuiPacket(NonNullList<ItemStack> items)
	{
		this.items = items;
	}
	
	public static NBTTagCompound writeToNBT(NBTTagCompound tag, ItemStack is)
	{
		tag.setShort("id", (short) Item.getIdFromItem(is.getItem()));
		tag.setInteger("Count", is.getCount());
		tag.setInteger("Damage", is.getItemDamage());
		
		if (is.getTagCompound() != null)
		{
			tag.setTag("tag", is.getTagCompound());
		}
		
		return tag;
	}
	
	/**
	 * Read the stack fields from a NBT object.
	 * 
	 * @return
	 */
	public static ItemStack readFromNBT(NBTTagCompound tag)
	{
		Item it = Item.getItemById(tag.getShort("id"));
		int stacks = tag.getInteger("Count");
		int damage = tag.getInteger("Damage");
		
		if (damage < 0)
		{
			damage = 0;
		}
		ItemStack is = new ItemStack(it, stacks, damage);
		
		if (tag.hasKey("tag", 10))
		{
			is.setTagCompound(tag.getCompoundTag("tag"));
		}
		return is;
	}
	
	public static ItemStack loadItemStackFromNBT(NBTTagCompound tag)
	{
		ItemStack itemstack = readFromNBT(tag);
		return itemstack.getItem() != null ? itemstack : null;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		
		final NBTTagList list = tag.getTagList("Items", 10);
		items = NonNullList.create();
		
		for (int var3 = 0; var3 < list.tagCount(); ++var3)
		{
			final NBTTagCompound var4 = list.getCompoundTagAt(var3);
			items.add(loadItemStackFromNBT(var4));
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagCompound tag = new NBTTagCompound();
		
		final NBTTagList list = new NBTTagList();
		int length = items.size();
		
		for (int i = 0; i < length; ++i)
		{
			if (items.get(i) != null)
			{
				final NBTTagCompound var4 = new NBTTagCompound();
				var4.setByte("Slot", (byte) i);
				writeToNBT(var4, items.get(i));
				list.appendTag(var4);
			}
		}
		
		tag.setTag("Items", list);
		
		ByteBufUtils.writeTag(buf, tag);
	}
	
	public static class Handler implements IMessageHandler<OpenBuilderGuiPacket, IMessage> {
		@Override
		public IMessage onMessage(OpenBuilderGuiPacket pkt, MessageContext ctx)
		{
			
			if (pkt.items != null)
			{
				GuiBuilder.foundItems = pkt.items;
				GuiBuilder.dataRecived = true;
				
			} else
			{
				OTLoger.logWarn("An error on handling open GUI packet. report this to dev!");
				OTLoger.logWarn("info: Items list is null");
			}
			
			return null;
		}
		
	}
}
package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.items.ItemSpaceJetpack;
import net.orbitallabs.utils.OTLoger;

public class SyncPressedKeysPacket implements IMessage {
	
	private boolean active;
	
	public SyncPressedKeysPacket()
	{
	}
	
	public SyncPressedKeysPacket(boolean act)
	{
		this.active = act;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		active = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeBoolean(active);
	}
	
	public static class Handler implements IMessageHandler<SyncPressedKeysPacket, IMessage> {
		@Override
		public IMessage onMessage(SyncPressedKeysPacket pkt, MessageContext ctx)
		{
			
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				
				if (player != null && player.world != null)
				{
					if (player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == ItemMod.spaceJetpack)
					{
						ItemSpaceJetpack jetpack = (ItemSpaceJetpack) player.inventory.armorItemInSlot(2).getItem();
						ItemStack is = player.inventory.armorItemInSlot(2);
						
						jetpack.activated = pkt.active;
						if (is.hasTagCompound())
						{
							jetpack.writeToNBT(is.getTagCompound());
						} else
						{
							is.setTagCompound(new NBTTagCompound());
							jetpack.writeToNBT(is.getTagCompound());
						}
					}
				}
				
			} else
			{
				OTLoger.logWarn("An error on handling Key sync packet. report this to dev!");
				OTLoger.logWarn("info: Player is null");
			}
			
			return null;
		}
		
	}
}
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

public class JetpackUseFuelPacket implements IMessage {
	public JetpackUseFuelPacket()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
	}
	
	public static class Handler implements IMessageHandler<JetpackUseFuelPacket, IMessage> {
		@Override
		public IMessage onMessage(JetpackUseFuelPacket pkt, MessageContext ctx)
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
						
						jetpack.RCSFuel.drain(10, true);
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
			}
			
			return null;
		}
		
	}
}
package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.items.SpaceJetpackCapability;
import net.orbitallabs.items.SpaceJetpackItemStackCap;

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
						ItemStack is = player.inventory.armorItemInSlot(2);
						SpaceJetpackItemStackCap cap = (SpaceJetpackItemStackCap) is.getCapability(SpaceJetpackCapability.SpaceJetpackCapability, EnumFacing.UP);
						
						cap.RCSFuel.drain(10, true);
						cap.markDirty();
					}
				}
			}
			
			return null;
		}
		
	}
}
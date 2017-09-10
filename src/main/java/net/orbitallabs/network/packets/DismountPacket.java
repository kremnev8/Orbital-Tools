package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.entity.EntityRocketFakeTiered;
import net.orbitallabs.utils.OTLoger;

public class DismountPacket implements IMessage {
	
	public DismountPacket()
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
	
	public static class Handler implements IMessageHandler<DismountPacket, IMessage> {
		@Override
		public IMessage onMessage(DismountPacket pkt, MessageContext ctx)
		{
			
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				
				if (player.getRidingEntity() instanceof EntityRocketFakeTiered)
				{
					
					EntityRocketFakeTiered rocket = (EntityRocketFakeTiered) player.getRidingEntity();
					if (!rocket.shouldIgnoreShiftExit()) rocket.QuitRocket(player);
				}
				
			} else
			{
				OTLoger.logWarn("An error on handling dismount packet. report this to dev!");
				OTLoger.logWarn("info: Player is null");
			}
			
			return null;
		}
		
	}
}
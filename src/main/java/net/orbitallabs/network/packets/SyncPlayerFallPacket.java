package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.utils.OTLoger;

public class SyncPlayerFallPacket implements IMessage {
	private float fallDist;
	
	public SyncPlayerFallPacket()
	{
	}
	
	public SyncPlayerFallPacket(float falldist)
	{
		this.fallDist = falldist;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		fallDist = buf.readFloat();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeFloat(fallDist);
	}
	
	public static class Handler implements IMessageHandler<SyncPlayerFallPacket, IMessage> {
		@Override
		public IMessage onMessage(SyncPlayerFallPacket pkt, MessageContext ctx)
		{
			
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				
				if (player != null)
				{
					player.fallDistance = pkt.fallDist;
					((EntityPlayerMP) player).handleFalling(pkt.fallDist, true);
				}
				
			} else
			{
				OTLoger.logWarn("An error on handling sync player fall packet. report this to dev!");
				OTLoger.logWarn("info: Player is null");
			}
			
			return null;
		}
		
	}
}
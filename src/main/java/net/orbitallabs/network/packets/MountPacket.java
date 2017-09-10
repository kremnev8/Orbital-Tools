package net.orbitallabs.network.packets;

import java.util.List;
import java.util.UUID;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.entity.EntityRocketFakeTiered;
import net.orbitallabs.utils.OTLoger;

public class MountPacket implements IMessage {
	private EntityRocketFakeTiered ent;
	private UUID entUUID;
	
	public MountPacket()
	{
	}
	
	public MountPacket(EntityRocketFakeTiered rocket)
	{
		ent = rocket;
	}
	
	public MountPacket(UUID entId)
	{
		entUUID = entId;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		if (buf.readBoolean())
		{
			entUUID = new UUID(buf.readLong(), buf.readLong());
		}
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		if (ent != null)
		{
			buf.writeBoolean(true);
			buf.writeLong(ent.getUniqueID().getMostSignificantBits());
			buf.writeLong(ent.getUniqueID().getLeastSignificantBits());
		} else if (entUUID != null)
		{
			buf.writeBoolean(true);
			buf.writeLong(entUUID.getMostSignificantBits());
			buf.writeLong(entUUID.getLeastSignificantBits());
		} else
		{
			buf.writeBoolean(false);
		}
	}
	
	public static class Handler implements IMessageHandler<MountPacket, IMessage> {
		@Override
		public IMessage onMessage(MountPacket pkt, MessageContext ctx)
		{
			
			if (pkt.entUUID != null)
			{
				if (ctx.getServerHandler().playerEntity != null)
				{
					EntityPlayer player = ctx.getServerHandler().playerEntity;
					
					List<Entity> Entlist = player.world.loadedEntityList;
					
					for (int i = 0; i < Entlist.size(); i++)
					{
						Entity ent = Entlist.get(i);
						if (ent.getUniqueID().equals(pkt.entUUID))
						{
							if (ent instanceof EntityRocketFakeTiered)
							{
								OTLoger.logInfo("Find writen entity from UUID");
								pkt.ent = (EntityRocketFakeTiered) ent;
							}
						}
					}
					
					if (pkt.ent != null)
					{
						pkt.ent.EnterRocket(player);
					} else
					{
						OTLoger.logWarn("An error on handling dismount packet. report this to dev!");
						OTLoger.logWarn("info: Entity is null");
					}
				}
				
			} else
			{
				OTLoger.logWarn("An error on handling Mount packet. report this to dev!");
				OTLoger.logWarn("info: Entity UUID is null");
			}
			
			return null;
		}
		
	}
}
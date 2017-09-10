package net.orbitallabs.network.packets;

import java.util.List;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.entity.EntityRocketFakeTiered;
import net.orbitallabs.utils.OTLoger;

public class SyncRocketTierPacket implements IMessage {
	private EntityRocketFakeTiered ent;
	private int tier;
	private int entid;
	private double x;
	private double y;
	private double z;
	
	public SyncRocketTierPacket()
	{
	}
	
	public SyncRocketTierPacket(EntityRocketFakeTiered rocket)
	{
		ent = rocket;
		tier = rocket.getTier();
		
		x = rocket.posX;
		y = rocket.posY;
		z = rocket.posZ;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		tier = buf.readInt();
		entid = buf.readInt();
		x = buf.readDouble();
		y = buf.readDouble();
		z = buf.readDouble();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(tier);
		buf.writeInt(ent.getEntityId());
		buf.writeDouble(x);
		buf.writeDouble(y);
		buf.writeDouble(z);
	}
	
	public static class Handler implements IMessageHandler<SyncRocketTierPacket, IMessage> {
		@Override
		public IMessage onMessage(SyncRocketTierPacket pkt, MessageContext ctx)
		{
			
			List<Entity> Entlist = Minecraft.getMinecraft().player.world.getLoadedEntityList();
			
			for (int i = 0; i < Entlist.size(); i++)
			{
				Entity ent = Entlist.get(i);
				if (ent.getEntityId() == pkt.entid)
				{
					if (ent instanceof EntityRocketFakeTiered)
					{
						OTLoger.logInfo("Find writen entity from id");
						pkt.ent = (EntityRocketFakeTiered) ent;
					}
				}
			}
			
			if (pkt.ent != null)
			{
				pkt.ent.setTier(pkt.tier);
				pkt.ent.setPosition(pkt.x, pkt.y, pkt.z);
			} else
			{
				OTLoger.logWarn("An error on handling dismount packet. report this to dev!");
				OTLoger.logWarn("info: Entity is null");
			}
			
			return null;
		}
		
	}
}
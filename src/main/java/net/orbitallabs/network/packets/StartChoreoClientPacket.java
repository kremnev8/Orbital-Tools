package net.orbitallabs.network.packets;

import java.util.List;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.entity.EntityRocketFakeTiered;
import net.orbitallabs.entity.choreo.ChoreoScene;
import net.orbitallabs.entity.choreo.RocketUndockChoreoScene;
import net.orbitallabs.utils.OTLoger;

public class StartChoreoClientPacket implements IMessage {
	private EntityRocketFakeTiered ent;
	private int entid;
	private String choreoName;
	
	public StartChoreoClientPacket()
	{
	}
	
	public StartChoreoClientPacket(EntityRocketFakeTiered rocket, ChoreoScene choreo)
	{
		ent = rocket;
		choreoName = choreo.GetChoreoName();
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		entid = buf.readInt();
		choreoName = ByteBufUtils.readUTF8String(buf);
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(ent.getEntityId());
		ByteBufUtils.writeUTF8String(buf, choreoName);
	}
	
	public static class Handler implements IMessageHandler<StartChoreoClientPacket, IMessage> {
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(StartChoreoClientPacket pkt, MessageContext ctx)
		{
			
			List<Entity> Entlist = Minecraft.getMinecraft().player.world.getLoadedEntityList();
			
			for (int i = 0; i < Entlist.size(); i++)
			{
				Entity ent = Entlist.get(i);
				if (ent.getEntityId() == pkt.entid)
				{
					if (ent instanceof EntityRocketFakeTiered)
					{
						pkt.ent = (EntityRocketFakeTiered) ent;
					}
				}
			}
			
			if (pkt.ent != null)
			{
				RocketUndockChoreoScene scene = new RocketUndockChoreoScene(pkt.ent);
				pkt.ent.startChoreoScene(scene);
			} else
			{
				OTLoger.logWarn("An error on handling dismount packet. report this to dev!");
				OTLoger.logWarn("info: Entity is null");
			}
			
			return null;
		}
		
	}
}
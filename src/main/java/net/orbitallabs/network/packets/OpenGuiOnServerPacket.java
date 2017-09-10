package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.OrbitalTools;

public class OpenGuiOnServerPacket implements IMessage {
	private int guiId;
	
	private int x;
	private int y;
	private int z;
	
	public OpenGuiOnServerPacket()
	{
	}
	
	public OpenGuiOnServerPacket(int id, int x, int y, int z)
	{
		this.guiId = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		guiId = buf.readInt();
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(guiId);
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}
	
	public static class Handler implements IMessageHandler<OpenGuiOnServerPacket, IMessage> {
		@Override
		public IMessage onMessage(OpenGuiOnServerPacket pkt, MessageContext ctx)
		{
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				player.openGui(OrbitalTools.instance, pkt.guiId, player.world, pkt.x, pkt.y, pkt.z);
				
			}
			return null;
		}
		
	}
}
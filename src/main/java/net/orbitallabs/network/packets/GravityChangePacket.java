package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.gui.ContainerArtificialGSource;
import net.orbitallabs.tiles.TileEntityGravitySource;

public class GravityChangePacket implements IMessage {
	
	private double Value;
	
	public GravityChangePacket()
	{
		Value = 1;
	}
	
	public GravityChangePacket(double value)
	{
		Value = value;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeDouble(Value);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		Value = buf.readDouble();
	}
	
	public static class Handler implements IMessageHandler<GravityChangePacket, IMessage> {
		@Override
		public IMessage onMessage(GravityChangePacket pkt, MessageContext ctx)
		{
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				
				if (player.openContainer instanceof ContainerArtificialGSource)
				{
					if (((ContainerArtificialGSource) player.openContainer).tileEntity instanceof TileEntityGravitySource)
					{
						((TileEntityGravitySource) ((ContainerArtificialGSource) player.openContainer).tileEntity).SettedGA = pkt.Value;
					}
				}
			}
			
			return null;
		}
		
	}
}
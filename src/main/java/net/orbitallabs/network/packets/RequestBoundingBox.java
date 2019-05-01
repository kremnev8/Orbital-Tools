package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.tiles.TileEntityInfo;

public class RequestBoundingBox implements IMessage {
	
	private int x;
	private int y;
	private int z;
	
	public RequestBoundingBox()
	{
	}
	
	public RequestBoundingBox(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
	}
	
	public static class Handler implements IMessageHandler<RequestBoundingBox, IMessage> {
		@Override
		public IMessage onMessage(RequestBoundingBox pkt, MessageContext ctx)
		{
			if (ctx.getServerHandler().player != null)
			{
				EntityPlayer player = ctx.getServerHandler().player;
				World world = player.world;
				TileEntity tile = world.getTileEntity(new BlockPos(pkt.x, pkt.y, pkt.z));
				if (tile != null && tile instanceof TileEntityInfo)
				{
					return new SendboundingBoxData((TileEntityInfo) tile);
				}
			}
			return null;
		}
		
	}
}

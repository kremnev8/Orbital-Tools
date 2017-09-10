
package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.tiles.TileEntityBuildPoint;
import net.orbitallabs.utils.OTLoger;

public class BuildPointSyncPacket implements IMessage {
	private int type;
	private int x;
	private int y;
	private int z;
	
	public BuildPointSyncPacket()
	{
	}
	
	public BuildPointSyncPacket(int type, int x, int y, int z)
	{
		this.type = type;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = ByteBufUtils.readVarInt(buf, 5);
		y = ByteBufUtils.readVarInt(buf, 5);
		z = ByteBufUtils.readVarInt(buf, 5);
		type = ByteBufUtils.readVarInt(buf, 5);
		
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeVarInt(buf, x, 5);
		ByteBufUtils.writeVarInt(buf, y, 5);
		ByteBufUtils.writeVarInt(buf, z, 5);
		ByteBufUtils.writeVarInt(buf, type, 5);
	}
	
	public static class Handler implements IMessageHandler<BuildPointSyncPacket, IMessage> {
		@Override
		public IMessage onMessage(BuildPointSyncPacket pkt, MessageContext ctx)
		{
			TileEntityBuildPoint tile = (TileEntityBuildPoint) Minecraft.getMinecraft().world.getTileEntity(new BlockPos(pkt.x, pkt.y, pkt.z));
			
			if (tile == null)
			{
				OTLoger.logInfo("NULL tile entity reference in Docking port item sync update packet! Please report to dev!");
			} else
			{
				tile.setType(pkt.type);
			}
			
			return null;
		}
	}
}
package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.gui.GuiDockingPort;
import net.orbitallabs.tiles.TileEntityDockingPort;
import net.orbitallabs.utils.OTLoger;

public class InvScalePacket implements IMessage {
	
	private int x;
	private int y;
	private int z;
	private int newSize;
	
	public InvScalePacket()
	{
	}
	
	public InvScalePacket(int s, int x, int y, int z)
	{
		this.newSize = s;
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
		newSize = ByteBufUtils.readVarInt(buf, 5);
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeVarInt(buf, x, 5);
		ByteBufUtils.writeVarInt(buf, y, 5);
		ByteBufUtils.writeVarInt(buf, z, 5);
		ByteBufUtils.writeVarInt(buf, newSize, 5);
	}
	
	public static class Handler implements IMessageHandler<InvScalePacket, IMessage> {
		@Override
		public IMessage onMessage(InvScalePacket pkt, MessageContext ctx)
		{
			TileEntityDockingPort tile = (TileEntityDockingPort) Minecraft.getMinecraft().world.getTileEntity(new BlockPos(pkt.x, pkt.y, pkt.z));
			
			if (tile == null)
			{
				OTLoger.logInfo("NULL tile entity reference in Docking port scaling update packet! Please report to dev!");
			} else
			{
				tile.setSizeInventory(pkt.newSize);
				GuiDockingPort.dirty = true;
				
			}
			
			return null;
		}
	}
}
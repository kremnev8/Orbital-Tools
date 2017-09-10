package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.gui.ContainerModificator;
import net.orbitallabs.gui.ContainerRemover;
import net.orbitallabs.gui.GuiModificator;
import net.orbitallabs.gui.GuiRemover;

public class CloseScreenPacket implements IMessage {
	
	public CloseScreenPacket()
	{
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
	}
	
	public static class Handler implements IMessageHandler<CloseScreenPacket, IMessage> {
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(CloseScreenPacket pkt, MessageContext ctx)
		{
			if (Minecraft.getMinecraft().player.openContainer instanceof ContainerRemover)
			{
				GuiRemover.CloseThis = true;
			} else if (Minecraft.getMinecraft().player.openContainer instanceof ContainerModificator)
			{
				GuiModificator.CloseThis = true;
			}
			
			return null;
		}
		
	}
}
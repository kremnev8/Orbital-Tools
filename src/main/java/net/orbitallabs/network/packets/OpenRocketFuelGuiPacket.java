package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.OrbitalTools;
import net.orbitallabs.entity.EntityRocketFakeTiered;
import net.orbitallabs.gui.GuiHandler;

public class OpenRocketFuelGuiPacket implements IMessage {
	private int entId;
	
	public OpenRocketFuelGuiPacket()
	{
	}
	
	public OpenRocketFuelGuiPacket(int id)
	{
		this.entId = id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		entId = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{ 
		buf.writeInt(entId);
	}
	
	public static class Handler implements IMessageHandler<OpenRocketFuelGuiPacket, IMessage> {
		@Override
		public IMessage onMessage(OpenRocketFuelGuiPacket pkt, MessageContext ctx)
		{
			if (ctx.getServerHandler().player != null)
			{
				EntityPlayer player = ctx.getServerHandler().player;
				if (player.world.getEntityByID(pkt.entId) != null)
				{
					EntityRocketFakeTiered rocket = (EntityRocketFakeTiered) player.world.getEntityByID(pkt.entId);
					
					if (rocket.dockport != null)
					{
						player.openGui(OrbitalTools.instance, GuiHandler.DOCKINGPORTGUI, player.world, rocket.dockport.getPos().getX(), rocket.dockport.getPos().getY(),
								rocket.dockport.getPos().getZ());
					}
				}
			}
			return null;
		}
		
	}
}
package net.orbitallabs.network.packets;

import java.util.ArrayList;
import java.util.List;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.dimensions.DockingPortSaveData;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.tiles.TileEntityGravitySource;
import net.orbitallabs.utils.OTLoger;

public class GetWorldGravityDataPacket implements IMessage {
	
	public GetWorldGravityDataPacket()
	{
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
	}
	
	public static class Handler implements IMessageHandler<GetWorldGravityDataPacket, IMessage> {
		@Override
		public IMessage onMessage(GetWorldGravityDataPacket pkt, MessageContext ctx)
		{
			
			if (ctx.getServerHandler().player != null)
			{
				EntityPlayer player = ctx.getServerHandler().player;
				
				if (player != null && player.world != null)
				{
					DockingPortSaveData savef = DockingPortSaveData.forWorld(player.world);
					if (savef != null)
					{
						if (savef.GraviySources.size() > 0)
						{
							List<Double> sources = new ArrayList();
							for (int i = 0; i < savef.GraviySources.size(); i++)
							{
								if (player.world.getTileEntity(savef.GraviySources.get(i)) != null)
								{
									TileEntity te = player.world.getTileEntity(savef.GraviySources.get(i));
									if (te instanceof TileEntityGravitySource)
									{
										sources.add(((TileEntityGravitySource) te).gravityAddition);
									}
								}
							}
							PacketHandler.sendTo(new ClientGravityDataRecivePacket(sources), (EntityPlayerMP) player);
						}
						
					}
				}
				
			} else
			{
				OTLoger.logWarn("An error on handling dismount packet. report this to dev!");
				OTLoger.logWarn("info: Player is null");
			}
			
			return null;
		}
		
	}
}
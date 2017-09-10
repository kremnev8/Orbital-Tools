package net.orbitallabs.network.packets;

import java.util.ArrayList;
import java.util.List;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.dimensions.WorldProviderOrbitModif;

public class ClientGravityDataRecivePacket implements IMessage {
	private List<Double> souces;
	
	public ClientGravityDataRecivePacket()
	{
	}
	
	public ClientGravityDataRecivePacket(List<Double> S)
	{
		this.souces = S;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagList nbtlist = new NBTTagList();
		for (int i = 0; i < souces.size(); i++)
		{
			nbtlist.appendTag(new NBTTagDouble(souces.get(i)));
		}
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("GRAVITYSOURCES", nbtlist);
		ByteBufUtils.writeTag(buf, tag);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		NBTTagList nbtlist = tag.getTagList("GRAVITYSOURCES", 6);
		List<Double> gravityS = new ArrayList();
		gravityS.clear();
		if (nbtlist.tagCount() != 0)
		{
			
			for (int i = 0; i < nbtlist.tagCount(); i++)
			{
				gravityS.add(nbtlist.getDoubleAt(i));
			}
		}
		this.souces = gravityS;
	}
	
	public static class Handler implements IMessageHandler<ClientGravityDataRecivePacket, IMessage> {
		@Override
		public IMessage onMessage(ClientGravityDataRecivePacket pkt, MessageContext ctx)
		{
			if (pkt.souces != null)
			{
				WorldProviderOrbitModif.ArtificialForces = pkt.souces;
				WorldProviderOrbitModif.updatedList = true;
				WorldProviderOrbitModif.updateddouble = false;
				
			}
			
			return null;
		}
		
	}
}
/*
package net.orbitallabs.network.packets;

import java.util.HashMap;
import java.util.Set;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.orbitallabs.utils.GLoger;

public class PlayerPacket implements IMessage {
	
	private NBTTagList anims;
	private String name;
	
	public PlayerPacket()
	{
	}
	
	public PlayerPacket(ExtendedPlayer props)
	{
		name = props.getPlayerDispName();
		HashMap<String, Float> animss = props.animH.getCurrentActiveAnimations();
		if (!animss.isEmpty())
		{
			Set an = animss.keySet();
			anims = new NBTTagList();
			for (int i = 0; i < an.size(); i++)
			{
				String k = (String) an.toArray()[i];
				anims.appendTag(new NBTTagString(k + "-" + String.valueOf(animss.get(k))));
				
			}
		}
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		anims = tag.getTagList("ANIM", 8);
		name = tag.getString("NAME");
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("ANIM", anims);
		tag.setString("NAME", name);
		ByteBufUtils.writeTag(buf, tag);
	}
	
	public static class Handler implements IMessageHandler<PlayerPacket, IMessage> {
		@Override
		public IMessage onMessage(PlayerPacket pkt, MessageContext ctx)
		{
			EntityPlayer player = (EntityPlayer) Minecraft.getMinecraft().theWorld.getPlayerEntityByName(pkt.name);
			if (player == null)
			{
				GLoger.logWarn("Error on handling Player packet. info: Player is null! Report this to dev!");
			} else
			{
				ExtendedPlayer props = ExtendedPlayer.get(player);
				for (int i = 0; i < pkt.anims.tagCount(); i++)
				{
					String kv = pkt.anims.getStringTagAt(i);
					String[] kkvv = kv.split("-");
					String k = kkvv[0];
					Float v = Float.valueOf(kkvv[1]);
					if (!props.getAnimationHandler().isAnimationActive(k))
					{
						props.getAnimationHandler().activateAnimation(k, v);
					}
					
					//animH.activateAnimation(k, v);
					//	gravityS.add(nbtlist.func_150309_d(i));
				}
				//System.out.println("Packet received!");
			}
			return null;
		}
	}
}*/
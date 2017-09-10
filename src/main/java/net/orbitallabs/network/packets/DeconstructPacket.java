package net.orbitallabs.network.packets;

import java.util.ArrayList;
import java.util.List;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.structures.DeconstructHandler;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.utils.ChatUtils;
import net.orbitallabs.utils.LocalizedChatComponent;
import net.orbitallabs.utils.LocalizedString;
import net.orbitallabs.utils.OTLoger;

public class DeconstructPacket implements IMessage {
	private List<Structure> objects = new ArrayList<Structure>();
	
	private int[] pos;
	
	public DeconstructPacket()
	{
	}
	
	public DeconstructPacket(List<Structure> objs, int[] pos)
	{
		objects = objs;
		this.pos = pos;
	}
	
	public DeconstructPacket(Structure obj, int[] pos)
	{
		objects.add(obj);
		this.pos = pos;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		pos = tag.getIntArray("INF_POS");
		if (tag.getBoolean("OBJ"))
		{
			for (int i = 0; i < tag.getInteger("N_O"); i++)
			{
				Structure str = Structure.FindStructure(tag.getString("O" + i + "_OBJ"));
				int[] t1 = tag.getIntArray("O" + i + "_POS");
				int t2 = tag.getInteger("O" + i + "_ROT");
				EnumFacing t3 = EnumFacing.getFront(tag.getInteger("O" + i + "_DIR"));
				str.Configure(t1, t2, t3);
				objects.add(str);
			}
		}
	}
	
	public static int[] getInt(BlockPos pos)
	{
		return new int[] { pos.getX(), pos.getY(), pos.getZ() };
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagCompound tag = new NBTTagCompound();
		
		if (objects != null && objects.size() > 0)
		{
			tag.setInteger("N_O", objects.size());
			tag.setBoolean("OBJ", true);
			
			for (int i = 0; i < objects.size(); i++)
			{
				tag.setInteger("O" + i + "_DIR", objects.get(i).placementDir.ordinal());
				tag.setInteger("O" + i + "_ROT", objects.get(i).placementRotation);
				tag.setIntArray("O" + i + "_POS", getInt(objects.get(i).placementPos));
				tag.setString("O" + i + "_OBJ", objects.get(i).getUnlocalizedName());
			}
		} else
		{
			tag.setBoolean("OBJ", false);
		}
		tag.setIntArray("INF_POS", pos);
		
		ByteBufUtils.writeTag(buf, tag);
	}
	
	public static class Handler implements IMessageHandler<DeconstructPacket, IMessage> {
		@Override
		public IMessage onMessage(DeconstructPacket pkt, MessageContext ctx)
		{
			
			if (pkt.objects != null && pkt.objects.size() > 0)
			{
				OTLoger.logInfo("Deconstruct Packet Sucsessfuly recived!");
				EntityPlayerMP player = ctx.getServerHandler().playerEntity;
				if (player == null) return null;
				World world = player.world;
				
				int deconstruct = DeconstructHandler.HandleDeconstruct(world, pkt.objects, player, new BlockPos(pkt.pos[0], pkt.pos[1], pkt.pos[2]));
				if (deconstruct == 0)
				{
					ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("builder.deconstruct.failed", TextFormatting.RED)));
				} else if (deconstruct == 1)
				{
					ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("builder.deconstruct.partfailed", TextFormatting.YELLOW)));
				} else
				{
					ChatUtils.SendChatMessageOnClient(player,
							new LocalizedChatComponent(new LocalizedString("builder.deconstruct.successfully", TextFormatting.GREEN)).appendSibling(new TextComponentString("!")));
				}
				// player.addChatMessage(ChatUtils.modifyColor(new
				// ChatComponentText(StatCollector.translateToLocal("builder.deconstruct.successfully")+"!"),EnumChatFormatting.GREEN));
				// System.out.println("Building on server was failed!");
				
			} else
			{
				OTLoger.logWarn("An error on handling deconstruct packet. report this to dev!");
				OTLoger.logWarn("info: Structures List null or empty");
			}
			
			return null;
		}
		
	}
}
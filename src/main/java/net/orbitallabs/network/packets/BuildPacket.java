package net.orbitallabs.network.packets;

import java.util.ArrayList;
import java.util.List;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.structures.BuildHandler;
import net.orbitallabs.structures.DeconstructHandler;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.structures.StructureRotatable;
import net.orbitallabs.utils.ChatUtils;
import net.orbitallabs.utils.LocalizedChatComponent;
import net.orbitallabs.utils.LocalizedString;
import net.orbitallabs.utils.OTLoger;
import net.orbitallabs.utils.OreDictItemStack;
import net.orbitallabs.utils.StructureLocalizedString;

public class BuildPacket implements IMessage {
	private EnumFacing dir;
	private String Fname;
	private int x;
	private int y;
	private int z;
	private int rot;
	private NBTTagList list = new NBTTagList();
	
	public BuildPacket()
	{
	}
	
	public BuildPacket(EnumFacing dir, String name, int x, int y, int z)
	{
		this.dir = dir;
		this.Fname = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.rot = -1;
	}
	
	public BuildPacket(EnumFacing dir, String name, int x, int y, int z, int rot, ItemStack stack)
	{
		this.dir = dir;
		this.Fname = name;
		this.x = x;
		this.y = y;
		this.z = z;
		this.rot = rot;
		
		if (stack.getItem() == ItemMod.Builder)
		{
			if (stack.hasTagCompound())
			{
				NBTTagCompound tag = stack.getTagCompound();
				if (tag.hasKey("chests"))
				{
					list = tag.getTagList("chests", 11);
				}
			}
		}
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		dir = EnumFacing.getFront(ByteBufUtils.readVarInt(buf, 5));
		Fname = ByteBufUtils.readUTF8String(buf);
		x = ByteBufUtils.readVarInt(buf, 5);
		y = ByteBufUtils.readVarInt(buf, 5);
		z = ByteBufUtils.readVarInt(buf, 5);
		rot = ByteBufUtils.readVarInt(buf, 5);
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		list = tag.getTagList("chests", 11);
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeVarInt(buf, dir.getIndex(), 5);
		ByteBufUtils.writeUTF8String(buf, Fname);
		ByteBufUtils.writeVarInt(buf, x, 5);
		ByteBufUtils.writeVarInt(buf, y, 5);
		ByteBufUtils.writeVarInt(buf, z, 5);
		ByteBufUtils.writeVarInt(buf, rot, 5);
		NBTTagCompound tag = new NBTTagCompound();
		tag.setTag("chests", list);
		ByteBufUtils.writeTag(buf, tag);
	}
	
	public static class Handler implements IMessageHandler<BuildPacket, IMessage> {
		@Override
		public IMessage onMessage(BuildPacket pkt, MessageContext ctx)
		{
			
			if (pkt.Fname != "")
			{
				// GLoger.logInfo("Build Packet Sucsessfuly recived!");
				EntityPlayerMP player = ctx.getServerHandler().playerEntity;
				if (player == null) return null;
				World world = player.world;
				boolean items = BuildHandler.CheckItems(world, pkt.Fname, pkt.list, player, pkt.rot);
				if (!items) ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("builder.failed.noitems", TextFormatting.RED)));
				else
				{
					boolean build = BuildHandler.HandleBuild(world, pkt.dir, pkt.Fname, new BlockPos(pkt.x, pkt.y, pkt.z), pkt.rot, player);
					if (!build) ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("builder.failed", TextFormatting.RED)));
					else ChatUtils.SendChatMessageOnClient(player,
							new LocalizedChatComponent(new LocalizedString("builder.successfully", TextFormatting.GREEN)).appendSibling(new TextComponentString(" "))
									.appendSibling(new LocalizedChatComponent(new StructureLocalizedString(Structure.FindStructure(pkt.Fname), TextFormatting.GREEN))
											.appendSibling(new TextComponentString("!"))));
					
					if (!build)
					{
						if (!player.capabilities.isCreativeMode)
						{
							Structure str = Structure.FindStructure(pkt.Fname);
							if (str instanceof StructureRotatable)
							{
								((StructureRotatable) str).setRotation(pkt.rot);
							}
							List<OreDictItemStack> I = str.getRequiredItems();
							List<ItemStack> afterI = new ArrayList();
							afterI.addAll(DeconstructHandler.modificateRetItems(I));
							
							for (int k = 0; k < afterI.size(); k++)
							{
								ItemStack curr = afterI.get(k);
								EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
								item.setNoPickupDelay();
								world.spawnEntity(item);
							}
							ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("builder.failed.itemsreturn", TextFormatting.GRAY)));
						}
					}
				}
			} else
			{
				OTLoger.logWarn("An error on handling build packet. report this to dev!");
				OTLoger.logWarn("info", pkt.dir.name(), pkt.Fname);
			}
			
			return null;
		}
		
	}
}
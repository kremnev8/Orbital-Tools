package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.tiles.TileEntityInfo;

public class SendboundingBoxData implements IMessage {
	private Structure object;
	
	private int x;
	private int y;
	private int z;
	
	public SendboundingBoxData()
	{
	}
	
	public SendboundingBoxData(TileEntityInfo te)
	{
		object = te.Object;
		x = te.getPos().getX();
		y = te.getPos().getY();
		z = te.getPos().getZ();
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		NBTTagCompound tag = ByteBufUtils.readTag(buf);
		if (tag.getBoolean("OBJWRT"))
		{
			object = Structure.FindStructure(tag.getString("OBJ"));
			object.Configure(tag.getIntArray("POS"), tag.getInteger("ROT"), EnumFacing.getFront(tag.getInteger("DIR")));
		}
		
		x = tag.getInteger("x");
		y = tag.getInteger("y");
		z = tag.getInteger("z");
		
	}
	
	public static int[] getInt(BlockPos pos)
	{
		return new int[] { pos.getX(), pos.getY(), pos.getZ() };
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		NBTTagCompound tag = new NBTTagCompound();
		if (object != null)
		{
			tag.setBoolean("OBJWRT", true);
			tag.setInteger("DIR", object.placementDir.ordinal());
			tag.setInteger("ROT", object.placementRotation);
			tag.setIntArray("POS", getInt(object.placementPos));
			tag.setString("OBJ", object.getUnlocalizedName());
		} else
		{
			tag.setBoolean("OBJWRT", false);
		}
		
		tag.setInteger("x", x);
		tag.setInteger("y", y);
		tag.setInteger("z", z);
		
		ByteBufUtils.writeTag(buf, tag);
	}
	
	public static class Handler implements IMessageHandler<SendboundingBoxData, IMessage> {
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(SendboundingBoxData pkt, MessageContext ctx)
		{
			World world = Minecraft.getMinecraft().player.world;
			TileEntity tile = world.getTileEntity(new BlockPos(pkt.x, pkt.y, pkt.z));
			if (tile != null)
			{
				((TileEntityInfo) tile).Object = pkt.object;
			}
			
			return null;
		}
		
	}
}

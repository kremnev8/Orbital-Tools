package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.items.SpaceJetpackCapability;
import net.orbitallabs.items.SpaceJetpackItemStackCap;

public class JetpackFueluseSync implements IMessage {
	
	private float used = 0;
	
	public JetpackFueluseSync()
	{
	}
	
	public JetpackFueluseSync(float used)
	{
		this.used = used;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		used = buf.readFloat();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeFloat(used);
	}
	
	public static class Handler implements IMessageHandler<JetpackFueluseSync, IMessage> {
		@Override
		public IMessage onMessage(JetpackFueluseSync pkt, MessageContext ctx)
		{
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				
				if (player != null && player.world != null)
				{
					if (player.inventory.armorItemInSlot(2) != null && player.inventory.armorItemInSlot(2).getItem() == ItemMod.spaceJetpack)
					{
						ItemStack is = player.inventory.armorItemInSlot(2);
						SpaceJetpackItemStackCap cap = (SpaceJetpackItemStackCap) is.getCapability(SpaceJetpackCapability.SpaceJetpackCapability, EnumFacing.UP);
						
						cap.usedFuel += pkt.used;
						//player.sendMessage(new TextComponentString("fuel used: " + cap.usedFuel));
						if (cap.usedFuel >= 10)
						{
							int use = cap.getTank().drain((int) cap.usedFuel, true).amount;
							cap.usedFuel -= use;
						}
						cap.markDirty();
					}
				}
			}
			
			return null;
		}
		
	}
}
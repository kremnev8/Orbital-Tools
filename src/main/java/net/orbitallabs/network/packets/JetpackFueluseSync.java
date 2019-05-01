package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.items.SpaceJetpackProvider;
import net.orbitallabs.items.SpaceJetpackStorage.ISpaceJetpackState;

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
			if (ctx.getServerHandler().player != null)
			{
				EntityPlayer player = ctx.getServerHandler().player;
				
				if (player != null && player.world != null)
				{
					if (player.inventory.armorInventory.get(2) != null && player.inventory.armorInventory.get(2).getItem() == ItemMod.spaceJetpack)
					{
						ItemStack is = player.inventory.armorInventory.get(2);
						ISpaceJetpackState cap = is.getCapability(SpaceJetpackProvider.SpaceJetpackCapability, EnumFacing.UP);
						
						cap.setUsedFuel(cap.getUsedFuel() + pkt.used);
						//player.sendMessage(new TextComponentString("fuel used: " + cap.usedFuel));
						if (cap.getUsedFuel() >= 10)
						{
							int use = cap.getTank().drain((int) cap.getUsedFuel(), true).amount;
							cap.setUsedFuel(cap.getUsedFuel() - use);
						}
					}
				}
			}
			
			return null;
		}
		
	}
}

package net.orbitallabs.network.packets;

import java.util.Collections;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.gui.ContainerArmorStand;
import net.orbitallabs.tiles.TileEntityArmorStand;

public class SwapArmorPacket implements IMessage {
	
	public SwapArmorPacket()
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
	
	public static class Handler implements IMessageHandler<SwapArmorPacket, IMessage> {
		@Override
		public IMessage onMessage(SwapArmorPacket pkt, MessageContext ctx)
		{
			if (ctx.getServerHandler().player != null)
			{
				EntityPlayer player = ctx.getServerHandler().player;
				
				if (player.openContainer instanceof ContainerArmorStand)
				{
					ContainerArmorStand cont = (ContainerArmorStand) player.openContainer;
					NonNullList<ItemStack> stand = cont.Standnventory.items;
					NonNullList<ItemStack> playerS = NonNullList.withSize(player.inventory.armorInventory.size(), ItemStack.EMPTY);
					Collections.copy(playerS, player.inventory.armorInventory);
					
					if (stand != null)
					{
						player.inventory.setInventorySlotContents(39, stand.get(0));
						player.inventory.setInventorySlotContents(38, stand.get(1));
						player.inventory.setInventorySlotContents(37, stand.get(2));
						player.inventory.setInventorySlotContents(36, stand.get(3));
					}
					if (playerS != null)
					{
						TileEntityArmorStand te = cont.Standnventory;
						te.setInventorySlotContents(3, playerS.get(0));
						te.setInventorySlotContents(2, playerS.get(1));
						te.setInventorySlotContents(1, playerS.get(2));
						te.setInventorySlotContents(0, playerS.get(3));
					}
				}
			}
			
			return null;
		}
		
	}
}
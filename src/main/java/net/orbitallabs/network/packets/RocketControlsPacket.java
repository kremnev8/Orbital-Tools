
package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.utils.ChatUtils;
import net.orbitallabs.utils.LocalizedChatComponent;
import net.orbitallabs.utils.LocalizedString;

public class RocketControlsPacket implements IMessage {
	
	public RocketControlsPacket()
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
	
	public static class Handler implements IMessageHandler<RocketControlsPacket, IMessage> {
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(RocketControlsPacket pkt, MessageContext ctx)
		{
			EntityPlayer player = Minecraft.getMinecraft().player;
			
			ChatUtils.SendChatMessageOnClient(player, new TextComponentString(GameSettings.getKeyDisplayString(KeyHandlerClient.spaceKey.getKeyCode()) + "  - ")
					.appendSibling(new LocalizedChatComponent(new LocalizedString("gui.rocket.launch.name", null))));
			ChatUtils.SendChatMessageOnClient(player, new TextComponentString(GameSettings.getKeyDisplayString(KeyHandlerClient.openFuelGui.getKeyCode()) + "  - ")
					.appendSibling(new LocalizedChatComponent(new LocalizedString("gui.rocket.inv.name", null))));
			return null;
		}
		
	}
}
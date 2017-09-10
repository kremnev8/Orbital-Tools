package net.orbitallabs.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.PlayerChatMessagePacket;

/**
 * Helper class for chat messages and formatting
 */
public final class ChatUtils {
	/**
	 * Alters color of a IChatComponent and returns it. Returning the param
	 * allows for a chat message to be constructed and colored in one line.
	 */
	public static ITextComponent modifyColor(ITextComponent chat, TextFormatting format)
	{
		if (format.isColor())
		{
			chat.setStyle(new Style().setColor(format));
		}
		return chat;
	}
	
	public static void SendChatMessageOnClient(EntityPlayer player, ITextComponent comp)
	{
		if (!player.world.isRemote)
		{
			PacketHandler.sendTo(new PlayerChatMessagePacket(comp), (EntityPlayerMP) player);
		} else
		{
			player.sendMessage(comp);
		}
	}
}

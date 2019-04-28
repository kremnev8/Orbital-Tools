package net.orbitallabs.network;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.orbitallabs.network.packets.AnimationTellServerPacket;
import net.orbitallabs.network.packets.ArmorStandItemSyncPacket;
import net.orbitallabs.network.packets.BuildPacket;
import net.orbitallabs.network.packets.BuildPointSyncPacket;
import net.orbitallabs.network.packets.ClientGravityDataRecivePacket;
import net.orbitallabs.network.packets.CloseScreenPacket;
import net.orbitallabs.network.packets.DeconstructPacket;
import net.orbitallabs.network.packets.DismountPacket;
import net.orbitallabs.network.packets.DockItemSyncPacket;
import net.orbitallabs.network.packets.GetWorldGravityDataPacket;
import net.orbitallabs.network.packets.GravityChangePacket;
import net.orbitallabs.network.packets.InvScalePacket;
import net.orbitallabs.network.packets.JetpackFueluseSync;
import net.orbitallabs.network.packets.LaunchRocketPacket;
import net.orbitallabs.network.packets.ModificatorSavePacket;
import net.orbitallabs.network.packets.MountPacket;
import net.orbitallabs.network.packets.OpenBuilderGuiPacket;
import net.orbitallabs.network.packets.OpenGuiOnServerPacket;
import net.orbitallabs.network.packets.OpenGuiPacket;
import net.orbitallabs.network.packets.OpenRocketFuelGuiPacket;
import net.orbitallabs.network.packets.OtherPlayerAnimationPacket;
import net.orbitallabs.network.packets.PlayerChatMessagePacket;
import net.orbitallabs.network.packets.RequestBoundingBox;
import net.orbitallabs.network.packets.RocketControlsPacket;
import net.orbitallabs.network.packets.SendUUIDPacket;
import net.orbitallabs.network.packets.SendboundingBoxData;
import net.orbitallabs.network.packets.SetThirdPersonPacket;
import net.orbitallabs.network.packets.StartChoreoClientPacket;
import net.orbitallabs.network.packets.SwapArmorPacket;
import net.orbitallabs.network.packets.SyncPlayerFallPacket;
import net.orbitallabs.network.packets.SyncPressedKeysPacket;
import net.orbitallabs.network.packets.SyncRocketTierPacket;
import net.orbitallabs.utils.OrbitalModInfo;

public final class PacketHandler {
	private static final int MAX_PKT_SIZE = 256;
	private static final SimpleNetworkWrapper HANDLER = NetworkRegistry.INSTANCE.newSimpleChannel(OrbitalModInfo.ChannelName);
	
	public static void register()
	{
		int i = 0;
		HANDLER.registerMessage(BuildPacket.Handler.class, BuildPacket.class, i++, Side.SERVER);
		HANDLER.registerMessage(OpenGuiPacket.Handler.class, OpenGuiPacket.class, i++, Side.CLIENT);
		HANDLER.registerMessage(DeconstructPacket.Handler.class, DeconstructPacket.class, i++, Side.SERVER);
		HANDLER.registerMessage(SetThirdPersonPacket.Handler.class, SetThirdPersonPacket.class, i++, Side.CLIENT);
		HANDLER.registerMessage(InvScalePacket.Handler.class, InvScalePacket.class, i++, Side.CLIENT);
		HANDLER.registerMessage(DockItemSyncPacket.Handler.class, DockItemSyncPacket.class, i++, Side.CLIENT);
		HANDLER.registerMessage(DismountPacket.Handler.class, DismountPacket.class, i++, Side.SERVER);
		HANDLER.registerMessage(SendUUIDPacket.Handler.class, SendUUIDPacket.class, i++, Side.CLIENT);
		HANDLER.registerMessage(MountPacket.Handler.class, MountPacket.class, i++, Side.SERVER);
		HANDLER.registerMessage(LaunchRocketPacket.Handler.class, LaunchRocketPacket.class, i++, Side.SERVER);
		HANDLER.registerMessage(SyncRocketTierPacket.Handler.class, SyncRocketTierPacket.class, i++, Side.CLIENT);
		HANDLER.registerMessage(RocketControlsPacket.Handler.class, RocketControlsPacket.class, i++, Side.CLIENT);
		HANDLER.registerMessage(OpenRocketFuelGuiPacket.Handler.class, OpenRocketFuelGuiPacket.class, i++, Side.SERVER);
		HANDLER.registerMessage(StartChoreoClientPacket.Handler.class, StartChoreoClientPacket.class, i++, Side.CLIENT);
		
		HANDLER.registerMessage(GetWorldGravityDataPacket.Handler.class, GetWorldGravityDataPacket.class, i++, Side.SERVER);
		HANDLER.registerMessage(ClientGravityDataRecivePacket.Handler.class, ClientGravityDataRecivePacket.class, i++, Side.CLIENT);
		
		HANDLER.registerMessage(SyncPlayerFallPacket.Handler.class, SyncPlayerFallPacket.class, i++, Side.SERVER);
		HANDLER.registerMessage(SyncPressedKeysPacket.Handler.class, SyncPressedKeysPacket.class, i++, Side.SERVER);
		
		//HANDLER.registerMessage(JetpackUseFuelPacket.Handler.class, JetpackUseFuelPacket.class, 18, Side.SERVER);
		HANDLER.registerMessage(JetpackFueluseSync.Handler.class, JetpackFueluseSync.class, i++, Side.SERVER);
		
		HANDLER.registerMessage(OpenBuilderGuiPacket.Handler.class, OpenBuilderGuiPacket.class, i++, Side.CLIENT);
		
		HANDLER.registerMessage(OpenGuiOnServerPacket.Handler.class, OpenGuiOnServerPacket.class, i++, Side.SERVER);
		
		HANDLER.registerMessage(CloseScreenPacket.Handler.class, CloseScreenPacket.class, i++, Side.CLIENT);
		HANDLER.registerMessage(BuildPointSyncPacket.Handler.class, BuildPointSyncPacket.class, i++, Side.CLIENT);
		
		HANDLER.registerMessage(SwapArmorPacket.Handler.class, SwapArmorPacket.class, i++, Side.SERVER);
		
		HANDLER.registerMessage(AnimationTellServerPacket.Handler.class, AnimationTellServerPacket.class, i++, Side.SERVER);
		HANDLER.registerMessage(OtherPlayerAnimationPacket.Handler.class, OtherPlayerAnimationPacket.class, i++, Side.CLIENT);
		
		HANDLER.registerMessage(ArmorStandItemSyncPacket.Handler.class, ArmorStandItemSyncPacket.class, i++, Side.CLIENT);
		HANDLER.registerMessage(PlayerChatMessagePacket.Handler.class, PlayerChatMessagePacket.class, i++, Side.CLIENT);
		
		HANDLER.registerMessage(GravityChangePacket.Handler.class, GravityChangePacket.class, i++, Side.SERVER);
		
		HANDLER.registerMessage(RequestBoundingBox.Handler.class, RequestBoundingBox.class, i++, Side.SERVER);
		HANDLER.registerMessage(SendboundingBoxData.Handler.class, SendboundingBoxData.class, i++, Side.CLIENT);
		
		HANDLER.registerMessage(ModificatorSavePacket.Handler.class, ModificatorSavePacket.class, i++, Side.SERVER);
	}
	
	public static Packet getMCPacket(IMessage message)
	{
		return HANDLER.getPacketFrom(message);
	}
	
	/**
	 * Sends a packet to the server.<br>
	 * Must be called Client side.
	 */
	public static void sendToServer(IMessage msg)
	{
		HANDLER.sendToServer(msg);
	}
	
	/**
	 * Sends a packet to all the clients.<br>
	 * Must be called Server side.
	 */
	public static void sendToAll(IMessage msg)
	{
		HANDLER.sendToAll(msg);
	}
	
	/**
	 * Send a packet to all players around a specific point.<br>
	 * Must be called Server side.
	 */
	public static void sendToAllAround(IMessage msg, TargetPoint point)
	{
		HANDLER.sendToAllAround(msg, point);
	}
	
	/**
	 * Send a packet to a specific player.<br>
	 * Must be called Server side.
	 */
	public static void sendTo(IMessage msg, EntityPlayerMP player)
	{
		if (!(player instanceof FakePlayer))
		{
			HANDLER.sendTo(msg, player);
		}
	}
	
	/**
	 * Send a packet to all the players in the specified dimension.<br>
	 * Must be called Server side.
	 */
	public static void sendToDimension(IMessage msg, int dimension)
	{
		HANDLER.sendToDimension(msg, dimension);
	}
}
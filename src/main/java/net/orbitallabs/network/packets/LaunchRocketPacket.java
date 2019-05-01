
package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.items.ItemParaChute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.entity.EntityRocketFakeTiered;
import net.orbitallabs.entity.choreo.RocketUndockChoreoScene;
import net.orbitallabs.utils.ChatUtils;
import net.orbitallabs.utils.OTLoger;
import net.orbitallabs.utils.LocalizedChatComponent;
import net.orbitallabs.utils.LocalizedString;

public class LaunchRocketPacket implements IMessage {
	
	public LaunchRocketPacket()
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
	
	public static class Handler implements IMessageHandler<LaunchRocketPacket, IMessage> {
		@Override
		public IMessage onMessage(LaunchRocketPacket pkt, MessageContext ctx)
		{
			
			if (ctx.getServerHandler().player != null)
			{
				EntityPlayer player = ctx.getServerHandler().player;
				
				if (!player.isDead && player.getRidingEntity() != null && !player.getRidingEntity().isDead && player.getRidingEntity() instanceof EntityRocketFakeTiered)
				{
					EntityRocketFakeTiered ship = (EntityRocketFakeTiered) player.getRidingEntity();
					GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
					if (ship.hasValidFuel())
					{
						ItemStack stack2 = stats.getLastParachuteInSlot();
						boolean havePad = false;
						if (ship.dockport != null)
						{
							ItemStack pad = ship.dockport.getStackInSlot(ship.dockport.getSizeInventory() - 3);
							if (pad != null && pad.getItem() == new ItemStack(GCBlocks.landingPad).getItem())
							{
								if (pad.getCount() == 9) havePad = true;
							}
						}
						
						if ((stack2 != null && stack2.getItem() instanceof ItemParaChute && havePad) || stats.getLaunchAttempts() > 1 || stats.getLaunchAttempts() > 0 && havePad
								|| stats.getLaunchAttempts() > 0 && stack2 != null && stack2.getItem() instanceof ItemParaChute)
						{
							ship.startChoreoScene(new RocketUndockChoreoScene(ship));
							stats.setLaunchAttempts(0);
						} else if (stats.getChatCooldown() == 0 && !(stack2 != null && stack2.getItem() instanceof ItemParaChute))
						{//StatCollector.translateToLocal(key);
							ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("gui.rocket.warning.noparachute", TextFormatting.RESET)));
							stats.setChatCooldown(50);
							stats.setLaunchAttempts(stats.getLaunchAttempts() + 1);
						} else if (stats.getChatCooldown() == 0 && !havePad)
						{
							ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("gui.rocket.warning.nopad.name", TextFormatting.RESET)));
							stats.setChatCooldown(50);
							stats.setLaunchAttempts(stats.getLaunchAttempts() + 1);
						}
					} else if (stats.getChatCooldown() == 0)
					{
						ChatUtils.SendChatMessageOnClient(player, new LocalizedChatComponent(new LocalizedString("gui.rocket.warning.nofuel", TextFormatting.RESET)));
						stats.setChatCooldown(250);
					}
					
				}
				
			} else
			{
				OTLoger.logWarn("An error on handling rocket launch packet. report this to dev!");
				OTLoger.logWarn("info: Player is null");
			}
			
			return null;
		}
		
	}
}
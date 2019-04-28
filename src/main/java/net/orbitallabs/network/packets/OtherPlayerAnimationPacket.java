
package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.items.AnimationCapabilityProvider;
import net.orbitallabs.items.AnimationCapabilityProvider.IAnimationCapability;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.renderer.animations.AnimationHandlerJetpack;
import net.orbitallabs.utils.OTLoger;

public class OtherPlayerAnimationPacket implements IMessage {
	private String name;
	private String Plname;
	private boolean act;
	
	public OtherPlayerAnimationPacket()
	{
	}
	
	public OtherPlayerAnimationPacket(String name, String plName, boolean action)
	{
		this.name = name;
		this.Plname = plName;
		this.act = action;
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, name);
		ByteBufUtils.writeUTF8String(buf, Plname);
		buf.writeBoolean(act);
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		name = ByteBufUtils.readUTF8String(buf);
		Plname = ByteBufUtils.readUTF8String(buf);
		act = buf.readBoolean();
	}
	
	public static class Handler implements IMessageHandler<OtherPlayerAnimationPacket, IMessage> {
		@SideOnly(Side.CLIENT)
		@Override
		public IMessage onMessage(OtherPlayerAnimationPacket pkt, MessageContext ctx)
		{
			EntityPlayer player = Minecraft.getMinecraft().player;
			if (player != null)
			{
				if (player.getCommandSenderEntity().getName().equals(pkt.Plname))
				{
					return null;
				}
				World world = player.world;
				EntityPlayer otherPlayer = world.getPlayerEntityByName(pkt.Plname);
				if (otherPlayer != null)
				{
					OTLoger.logInfo("Just got update on " + pkt.Plname + "'s animation status:");
					OTLoger.logInfo((pkt.act ? "Activate" : "Stop") + " Animation " + pkt.name);
					ItemStack stack = otherPlayer.inventory.armorInventory.get(2);
					if (stack.getItem() == ItemMod.spaceJetpack)
					{
						IAnimationCapability cap = stack.getCapability(AnimationCapabilityProvider.AnimCap, EnumFacing.UP);
						if (pkt.act)
						{
							((AnimationHandlerJetpack) cap.getAnimationHandler()).activateAnimation(pkt.name, 0);
						} else
						{
							((AnimationHandlerJetpack) cap.getAnimationHandler()).stopAnimation(pkt.name);
						}
					}
				}
			}
			
			return null;
		}
		
	}
}

package net.orbitallabs.network.packets;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.utils.OTLoger;

public class AnimationTellServerPacket implements IMessage {
	private String name;
	private boolean act;
	
	public AnimationTellServerPacket()
	{
	}
	
	public AnimationTellServerPacket(String name, boolean act)
	{
		this.name = name;
		this.act = act;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		name = ByteBufUtils.readUTF8String(buf);
		act = buf.readBoolean();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		ByteBufUtils.writeUTF8String(buf, name);
		buf.writeBoolean(act);
	}
	
	public static class Handler implements IMessageHandler<AnimationTellServerPacket, IMessage> {
		@Override
		public IMessage onMessage(AnimationTellServerPacket pkt, MessageContext ctx)
		{
			
			if (ctx.getServerHandler().playerEntity != null)
			{
				EntityPlayer player = ctx.getServerHandler().playerEntity;
				ItemStack stack = player.inventory.armorInventory.get(2);
				//IAnimationCapability cap = stack.getCapability(AnimationCapabilityProvider.AnimCap, EnumFacing.UP);
				//if (cap == null) return null;
				//if (pkt.act)
				//{
				//	cap.getAnimationHandler().activateAnimation(pkt.name, 0);
				//} else
				//{
				//	cap.getAnimationHandler().stopAnimation(pkt.name);
				//}
				OTLoger.logInfo("Client just sent command to " + (pkt.act ? "Activate" : "Stop") + " Animation " + pkt.name);
				OTLoger.logInfo("Informing other players about animation status");
				PacketHandler.sendToDimension(new OtherPlayerAnimationPacket(pkt.name, player.getDisplayName().getUnformattedText(), pkt.act),
						player.world.provider.getDimension());
				//GLoger.logInfo("packet+1");
			} else
			{
				OTLoger.logWarn("An error on handling anim tell server packet. report this to dev!");
				OTLoger.logWarn("info: Player is null");
			}
			
			return null;
		}
		
	}
}
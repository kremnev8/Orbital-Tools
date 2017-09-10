
package net.orbitallabs.renderer.animations;

import java.util.HashMap;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.AnimationTellServerPacket;
import net.orbitallabs.renderer.MCACommonLibrary.IMCAnimatedEntity;
import net.orbitallabs.renderer.MCACommonLibrary.animation.AnimationHandler;
import net.orbitallabs.renderer.MCACommonLibrary.animation.Channel;

public class AnimationHandlerJetpack extends AnimationHandler {
	/** Map with all the animations. */
	public static HashMap<String, Channel> animChannels = new HashMap<String, Channel>();
	static
	{
		animChannels.put("Enable", new ChannelEnable("Enable", 4.0F, 10, Channel.LINEAR));
		animChannels.put("Disable", new ChannelDisable("Disable", 4.0F, 10, Channel.LINEAR));
		animChannels.put("Enabled idle", new ChannelEnabledIdle("Enabled idle", 1.0F, 3, Channel.LOOP));
		animChannels.put("Disabled idle", new ChannelDisabledIdle("Disabled idle", 1.0F, 3, Channel.LOOP));
	}
	
	public AnimationHandlerJetpack(IMCAnimatedEntity entity)
	{
		super(entity);
	}
	
	public void activateAnimation(String name, float startingFrame, boolean tellServer)
	{
		if (name.equals("Enable") && this.animCurrentFrame.containsKey("Disabled idle"))
		{
			this.stopAnimation("Disabled idle");
		} else if (name.equals("Disable") && this.animCurrentFrame.containsKey("Enabled idle"))
		{
			this.stopAnimation("Enabled idle");
		}
		super.activateAnimation(animChannels, name, startingFrame);
		
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && tellServer)
		{
			PacketHandler.sendToServer(new AnimationTellServerPacket(name, true));
		}
		
	}
	
	public HashMap<String, Float> getCurrentActiveAnimations()
	{
		return animCurrentFrame;
	}
	
	public void stopAnimation(String name, boolean tellServer)
	{
		super.stopAnimation(animChannels, name);
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && tellServer)
		{
			PacketHandler.sendToServer(new AnimationTellServerPacket(name, false));
		}
	}
	
	@Override
	public void fireAnimationEventClientSide(Channel anim, float prevFrame, float frame)
	{
		if (anim.name.equals("Enable") && frame >= 8.9)
		{
			this.stopAnimation("Enable");
			//	ExtendedPlayer player = (ExtendedPlayer) getEntity();
			//	this.activateAnimation("Enabled idle", 0, Minecraft.getMinecraft().thePlayer.getCommandSenderName().equals(player.player.getCommandSenderName()));
			//	int i =0;
		}
		if (anim.name.equals("Disable") && frame >= 8.9)
		{//TODO: animations
			this.stopAnimation("Disable");
			//	ExtendedPlayer player = (ExtendedPlayer) getEntity();
			//	this.activateAnimation("Disabled idle", 0, Minecraft.getMinecraft().thePlayer.getCommandSenderName().equals(player.player.getCommandSenderName()));
		}
		/*
		 * if(anim.name.equals("Enable") && frame >= 8) { ExtendedPlayer prop =
		 * (ExtendedPlayer) getEntity(); if (prop.player.getCurrentArmor(2) !=
		 * null && prop.player.getCurrentArmor(2).getItem() instanceof
		 * ItemSpaceJetpack) { ItemSpaceJetpack item = (ItemSpaceJetpack)
		 * prop.player.getCurrentArmor(2).getItem(); item.setActive(true); } }
		 * if(anim.name.equals("Disable") && frame >= 8) { ExtendedPlayer prop =
		 * (ExtendedPlayer) getEntity(); if (prop.player.getCurrentArmor(2) !=
		 * null && prop.player.getCurrentArmor(2).getItem() instanceof
		 * ItemSpaceJetpack) { ItemSpaceJetpack item = (ItemSpaceJetpack)
		 * prop.player.getCurrentArmor(2).getItem(); item.setActive(false); } }
		 */
	}
	
	@Override
	public void fireAnimationEventServerSide(Channel anim, float prevFrame, float frame)
	{
	}
	
	@Override
	public boolean updateAnim(IMCAnimatedEntity entity, Channel channel, HashMap<String, Float> prevFrameAnim)
	{
		/*
		 * if(channel.name.equals("Enable") && prevFrameAnim.get("Enable") >=
		 * 8.9) { this.stopAnimation("Enable");
		 * this.activateAnimation("Enabled idle", 0); return true; }
		 * if(channel.name.equals("Disable") && prevFrameAnim.get("Disable") >=
		 * 8.9) { this.stopAnimation("Disable");
		 * this.activateAnimation("Disabled idle", 0); return true; }
		 */
		return false;
		
	}
	
	@Override
	public void activateAnimation(String name, float startingFrame)
	{
		this.activateAnimation(name, startingFrame, true);
	}
	
	@Override
	public void stopAnimation(String name)
	{
		this.stopAnimation(name, true);
	}
}
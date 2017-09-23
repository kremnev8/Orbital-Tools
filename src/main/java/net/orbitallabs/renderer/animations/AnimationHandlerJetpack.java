
package net.orbitallabs.renderer.animations;

import java.util.HashMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.orbitallabs.items.SpaceJetpackItemStackCap;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.AnimationTellServerPacket;
import net.orbitallabs.network.packets.SyncPressedKeysPacket;
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
		//OTLoger.logInfo("Activated: " + name);
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
		
	}
	
	@Override
	public void fireAnimationEventServerSide(Channel anim, float prevFrame, float frame)
	{
	}
	
	@Override
	public boolean updateAnim(IMCAnimatedEntity entity, HashMap<String, Float> prevFrameAnim)
	{
		SpaceJetpackItemStackCap cap = (SpaceJetpackItemStackCap) entity;
		ItemStack item = cap.getStack();
		Boolean activated = false;
		if (item.hasTagCompound())
		{
			activated = item.getTagCompound().getBoolean("Enabled");
		} else
		{
			if (!isAnimationActive("Disabled idle"))
			{
				PacketHandler.sendToServer(new SyncPressedKeysPacket(false));
				clearAnimations();
				activateAnimation("Disabled idle", 0);
			}
		}
		for (int i = 0; i < animCurrentChannels.size(); i++)
		{
			Channel anim = animCurrentChannels.get(i);
			Float frame = animCurrentFrame.get(anim.name);
			if (anim.name.equals("Enable") && frame >= 8.5)
			{
				this.stopAnimation("Enable");
				this.activateAnimation("Enabled idle", 0, true);
			}
			if (anim.name.equals("Disable") && frame >= 8.5)
			{
				this.stopAnimation("Disable");
				this.activateAnimation("Disabled idle", 0, true);
			}
		}
		
		//		if (activated && !isAnimationActive("Enabled idle") && !isAnimationActive("Enable") && !isAnimationActive("Disable"))
		//		{
		//			clearAnimations();
		//			activateAnimation("Enabled idle", 0);
		//			cap.markDirty();
		//		} else if (!activated && !isAnimationActive("Disabled idle") && !isAnimationActive("Disable") && !isAnimationActive("Enable"))
		//		{
		//			clearAnimations();
		//			activateAnimation("Disabled idle", 0);
		//			cap.markDirty();
		//		}
		
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
		//OTLoger.logInfo("Stopped: " + name);
		this.stopAnimation(name, true);
	}
}
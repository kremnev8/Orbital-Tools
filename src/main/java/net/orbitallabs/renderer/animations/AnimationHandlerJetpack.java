
package net.orbitallabs.renderer.animations;

import java.util.HashMap;
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
	
	public boolean notinited = true;
	
	public AnimationHandlerJetpack(IMCAnimatedEntity entity)
	{
		super(entity);
	}
	
	public void activateAnimation(String name, float startingFrame)
	{
		//OTLoger.logInfo("Activated: " + name);
		if (name.equals("Enable") && this.animCurrentFrame.containsKey("Disabled idle"))
		{
			this.stopAnimation("Disabled idle");
			//OTLoger.logInfo("Stopping animation Disabled idle");
		} else if (name.equals("Disable") && this.animCurrentFrame.containsKey("Enabled idle"))
		{
			this.stopAnimation("Enabled idle");
			//OTLoger.logInfo("Stopping animation Enabled idle");
		}
		super.activateAnimation(animChannels, name, startingFrame);
		
	}
	
	public HashMap<String, Float> getCurrentActiveAnimations()
	{
		return animCurrentFrame;
	}
	
	public void stopAnimation(String name)
	{
		//OTLoger.logInfo("Stopped: " + name);
		super.stopAnimation(animChannels, name);
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
		
		for (int i = 0; i < animCurrentChannels.size(); i++)
		{
			Channel anim = animCurrentChannels.get(i);
			Float frame = animCurrentFrame.get(anim.name);
			//if (anim.name.equals("Enable")) OTLoger.logInfo("anim " + anim.name + " time is " + frame);
			
			if (anim.name.equals("Enable") && frame >= 8.5)
			{
				this.stopAnimation("Enable");
				this.activateAnimation("Enabled idle", 0);
				//OTLoger.logInfo("Enable ended, setting idle anim");
			}
			if (anim.name.equals("Disable") && frame >= 8.5)
			{
				this.stopAnimation("Disable");
				this.activateAnimation("Disabled idle", 0);
				//OTLoger.logInfo("Disable ended, setting idle anim");
			}
		}
		
		return false;
		
	}
	
}
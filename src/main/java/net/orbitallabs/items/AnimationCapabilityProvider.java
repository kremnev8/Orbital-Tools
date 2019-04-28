package net.orbitallabs.items;

import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.orbitallabs.renderer.MCACommonLibrary.IMCAnimatedEntity;
import net.orbitallabs.renderer.animations.AnimationHandlerJetpack;

public class AnimationCapabilityProvider implements ICapabilityProvider {
	
	@CapabilityInject(IAnimationCapability.class)
	public static Capability<IAnimationCapability> AnimCap = null;
	
	public final IAnimationCapability instance = AnimCap.getDefaultInstance();
	
	public interface IAnimationCapability extends IMCAnimatedEntity {
		
		public void setAnimationHandler(AnimationHandlerJetpack anim);
		
		public AnimationHandlerJetpack getAnimationHandler();
	}
	
	public static class AnimationCapability implements IAnimationCapability {
		
		public AnimationHandlerJetpack animH = new AnimationHandlerJetpack(this);
		
		@Override
		public void setAnimationHandler(AnimationHandlerJetpack anim)
		{
			animH = anim;
			
		}
		
		@Override
		public AnimationHandlerJetpack getAnimationHandler()
		{
			return animH;
		}
	}
	
	public static AnimationCapability missing = new AnimationCapability();
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == AnimCap;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == AnimCap)
		{
			return AnimCap.cast(instance);
		}
		
		return null;
	}
	
}

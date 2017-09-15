package net.orbitallabs.items;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.Callable;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.orbitallabs.items.SpaceJetpackCapability.ISpaceJetpackState;
import net.orbitallabs.renderer.MCACommonLibrary.IMCAnimatedEntity;
import net.orbitallabs.renderer.MCACommonLibrary.animation.AnimationHandler;
import net.orbitallabs.renderer.animations.AnimationHandlerJetpack;

public class SpaceJetpackCapability implements IStorage<ISpaceJetpackState> {
	
	@CapabilityInject(ISpaceJetpackState.class)
	public static Capability<ISpaceJetpackState> SpaceJetpackCapability = null;
	
	public interface ISpaceJetpackState {
		public AnimationHandler getAnimationHandler();
	}
	
	public static class SpaceJetpackState implements ISpaceJetpackState, IMCAnimatedEntity {
		
		public AnimationHandlerJetpack animH = new AnimationHandlerJetpack(this);
		
		public SpaceJetpackState()
		{
			
		}
		
		@Override
		public AnimationHandler getAnimationHandler()
		{
			return animH;
		}
		
	}
	
	@Override
	public NBTBase writeNBT(Capability<ISpaceJetpackState> capability, ISpaceJetpackState instance, EnumFacing side)
	{
		NBTTagCompound properties = new NBTTagCompound();
		
		HashMap<String, Float> anims = ((AnimationHandlerJetpack) instance.getAnimationHandler()).getCurrentActiveAnimations();
		if (!anims.isEmpty())
		{
			Set an = anims.keySet();
			NBTTagList nbtlist = new NBTTagList();
			for (int i = 0; i < an.size(); i++)
			{
				String k = (String) an.toArray()[i];
				nbtlist.appendTag(new NBTTagString(k + "-" + String.valueOf(anims.get(k))));
				
			}
			properties.setTag("ANIMATIONS", nbtlist);
		} else
		{
			properties.setBoolean("NO_ANIM", true);
		}
		
		return properties;
	}
	
	@Override
	public void readNBT(Capability<ISpaceJetpackState> capability, ISpaceJetpackState instance, EnumFacing side, NBTBase nbt)
	{
		if (nbt instanceof NBTTagCompound)
		{
			NBTTagCompound properties = (NBTTagCompound) nbt;
			if (properties != null)
			{
				if (!properties.hasKey("NO_ANIM"))
				{
					NBTTagList nbtlist = properties.getTagList("ANIMATIONS", 8);
					if (nbtlist.tagCount() != 0)
					{
						for (int i = 0; i < nbtlist.tagCount(); i++)
						{
							String kv = nbtlist.getStringTagAt(i);
							String[] kkvv = kv.split("-");
							String k = kkvv[0];
							Float v = Float.valueOf(kkvv[1]);
							instance.getAnimationHandler().activateAnimation(k, v);
						}
					}
				}
			}
		}
		
	}
	
	public static class Factory implements Callable<ISpaceJetpackState> {
		
		@Override
		public ISpaceJetpackState call() throws Exception
		{
			return new SpaceJetpackState();
		}
	}
	
}

package net.orbitallabs.items;

import java.util.HashMap;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fluids.FluidTank;
import net.orbitallabs.items.SpaceJetpackCapability.ISpaceJetpackState;
import net.orbitallabs.renderer.MCACommonLibrary.IMCAnimatedEntity;
import net.orbitallabs.renderer.MCACommonLibrary.animation.AnimationHandler;
import net.orbitallabs.renderer.animations.AnimationHandlerJetpack;

public class SpaceJetpackItemStackCap implements ISpaceJetpackState, ICapabilityProvider, IMCAnimatedEntity {
	
	protected ItemStack jetpack;
	public AnimationHandlerJetpack animH = new AnimationHandlerJetpack(this);
	
	public FluidTank RCSFuel = new FluidTank(1750 * ConfigManagerCore.rocketFuelFactor);
	
	public SpaceJetpackItemStackCap(ItemStack stack)
	{
		jetpack = stack;
		NBTTagCompound nbt = jetpack.getTagCompound();
		load(nbt);
	}
	
	@Override
	public boolean hasCapability(@Nonnull Capability<?> capability, @Nullable EnumFacing facing)
	{
		return capability == SpaceJetpackCapability.SpaceJetpackCapability;
	}
	
	@Override
	@Nullable
	public <T> T getCapability(@Nonnull Capability<T> capability, @Nullable EnumFacing facing)
	{
		return capability == SpaceJetpackCapability.SpaceJetpackCapability ? (T) this : null;
	}
	
	@Override
	public AnimationHandler getAnimationHandler()
	{
		return animH;
	}
	
	public ItemStack getStack()
	{
		return jetpack;
	}
	
	public FluidTank getTank()
	{
		return RCSFuel;
	}
	
	public void load(NBTTagCompound nbt)
	{
		if (nbt != null)
		{
			if (!nbt.hasKey("NO_ANIM"))
			{
				NBTTagList nbtlist = nbt.getTagList("ANIMATIONS", 8);
				if (nbtlist.tagCount() != 0)
				{
					for (int i = 0; i < nbtlist.tagCount(); i++)
					{
						String kv = nbtlist.getStringTagAt(i);
						String[] kkvv = kv.split("-");
						String k = kkvv[0];
						Float v = Float.valueOf(kkvv[1]);
						animH.activateAnimation(k, v);
					}
				}
			}
			if (nbt.hasKey("fuelTank"))
			{
				this.RCSFuel.readFromNBT(nbt.getCompoundTag("fuelTank"));
			}
		}
	}
	
	public void markDirty()
	{
		NBTTagCompound properties = new NBTTagCompound();
		HashMap<String, Float> anims = ((AnimationHandlerJetpack) animH).getCurrentActiveAnimations();
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
		if (this.RCSFuel.getFluid() != null)
		{
			properties.setTag("fuelTank", this.RCSFuel.writeToNBT(new NBTTagCompound()));
		}
		jetpack.setTagCompound(properties);
	}
	
}

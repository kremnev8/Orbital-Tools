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
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.orbitallabs.items.SpaceJetpackCapability.ISpaceJetpackState;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.SyncPressedKeysPacket;
import net.orbitallabs.renderer.MCACommonLibrary.IMCAnimatedEntity;
import net.orbitallabs.renderer.MCACommonLibrary.animation.AnimationHandler;
import net.orbitallabs.renderer.animations.AnimationHandlerJetpack;

public class SpaceJetpackItemStackCap implements ISpaceJetpackState, ICapabilitySerializable<NBTTagCompound>, IMCAnimatedEntity {
	
	protected ItemStack jetpack;
	public AnimationHandlerJetpack animH = new AnimationHandlerJetpack(this);
	
	public boolean enabled;
	
	public FluidTank RCSFuel = new FluidTank(1750 * ConfigManagerCore.rocketFuelFactor);
	public float usedFuel = 0;
	
	public SpaceJetpackItemStackCap(ItemStack stack)
	{
		jetpack = stack;
		NBTTagCompound nbt = jetpack.getTagCompound();
		deserializeNBT(nbt);
	}
	
	public SpaceJetpackItemStackCap(ItemStack stack, NBTTagCompound tag)
	{
		jetpack = stack;
		deserializeNBT(tag);
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
		NBTTagCompound nbt = jetpack.getTagCompound();
		if (jetpack.hasTagCompound() && nbt.hasKey("fuelTank"))
		{
			this.RCSFuel.readFromNBT(nbt.getCompoundTag("fuelTank"));
		}
		return RCSFuel;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}
	
	public void setState(boolean state)
	{
		enabled = state;
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER)
		{
			markDirty();
		} else
		{
			PacketHandler.sendToServer(new SyncPressedKeysPacket(state));
		}
	}
	
	public void markDirty()
	{
		NBTTagCompound properties = serializeNBT();
		jetpack.setTagCompound(properties);
	}
	
	@Override
	public NBTTagCompound serializeNBT()
	{
		//OTLoger.logInfo("Written NBT tag");
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
		properties.setBoolean("enabled", enabled);
		properties.setFloat("usedfuel", usedFuel);
		return properties;
	}
	
	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		
		if (nbt != null)
		{
			if (nbt.hasKey("Parent"))
			{
				nbt = nbt.getCompoundTag("Parent");
			}
			
			//OTLoger.logInfo("Loaded NBT tag");
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
						if (kkvv[1].contains("E")) kkvv[1] = "0.0";
						
						Float v = Float.valueOf(kkvv[1]);
						animH.activateAnimation(k, v);
					}
				}
			}
			if (nbt.hasKey("fuelTank"))
			{
				this.RCSFuel.readFromNBT(nbt.getCompoundTag("fuelTank"));
			}
			if (nbt.hasKey("enabled"))
			{
				enabled = nbt.getBoolean("enabled");
			} else
			{
				enabled = animH.isAnimationActive("Enabled idle");
			}
			if (nbt.hasKey("usedfuel"))
			{
				usedFuel = nbt.getFloat("usedfuel");
			}
		}
		
	}
	
}

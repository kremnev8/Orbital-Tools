package net.orbitallabs.items;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.orbitallabs.items.SpaceJetpackStorage.ISpaceJetpackState;

public class SpaceJetpackProvider implements ICapabilitySerializable<NBTTagCompound> {
	
	@CapabilityInject(ISpaceJetpackState.class)
	public static Capability<ISpaceJetpackState> SpaceJetpackCapability = null;
	
	public final ISpaceJetpackState instance = SpaceJetpackCapability.getDefaultInstance();
	
	public static final Capability<ISpaceJetpackState> getCapability()
	{
		return SpaceJetpackCapability;
	}
	
	public final ISpaceJetpackState getInstance()
	{
		return instance;
	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		return capability == getCapability();
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == getCapability())
		{
			return getCapability().cast(getInstance());
		}
		
		return null;
	}
	
	@Override
	public NBTTagCompound serializeNBT()
	{
		return (NBTTagCompound) getCapability().writeNBT(getInstance(), null);
	}
	
	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		getCapability().readNBT(getInstance(), null, nbt);
	}
	
}

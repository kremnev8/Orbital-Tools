package net.orbitallabs.items;

import java.util.concurrent.Callable;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.orbitallabs.items.SpaceJetpackStorage.ISpaceJetpackState;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.SyncPressedKeysPacket;

public class SpaceJetpackStorage implements IStorage<ISpaceJetpackState> {
	
	public interface ISpaceJetpackState {
		
		public void setTank(FluidTank tank);
		
		public FluidTank getTank();
		
		public void setEnabled(boolean state);
		
		public boolean isEnabled();
		
		public void setUsedFuel(float used);
		
		public float getUsedFuel();
		
	}
	
	public static class SpaceJetpackState implements ISpaceJetpackState {
		
		//public AnimationHandlerJetpack animH = new AnimationHandlerJetpack(this);
		public boolean enabled;
		public FluidTank RCSFuel = new FluidTank(1750 * ConfigManagerCore.rocketFuelFactor);
		public float usedFuel = 0;
		
		public SpaceJetpackState()
		{
			
		}
		
		@Override
		public float getUsedFuel()
		{
			return usedFuel;
		}
		
		/*@Override
		public AnimationHandler getAnimationHandler()
		{
			return animH;
		}*/
		
		@Override
		public FluidTank getTank()
		{
			return RCSFuel;
		}
		
		@Override
		public boolean isEnabled()
		{
			return enabled;
		}
		
		/*@Override
		public void setAnimationHandler(AnimationHandlerJetpack anim)
		{
			animH = anim;
		}*/
		
		@Override
		public void setTank(FluidTank tank)
		{
			RCSFuel = tank;
		}
		
		@Override
		public void setEnabled(boolean state)
		{
			enabled = state;
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
			{
				PacketHandler.sendToServer(new SyncPressedKeysPacket(state));
			}
		}
		
		@Override
		public void setUsedFuel(float used)
		{
			usedFuel = used;
		}
		
	}
	
	@Override
	public NBTBase writeNBT(Capability<ISpaceJetpackState> capability, ISpaceJetpackState instance, EnumFacing side)
	{
		NBTTagCompound properties = new NBTTagCompound();
		
		if (instance.getTank().getFluid() != null)
		{
			properties.setTag("fuelTank", instance.getTank().writeToNBT(new NBTTagCompound()));
		}
		properties.setBoolean("enabled", instance.isEnabled());
		properties.setFloat("usedfuel", instance.getUsedFuel());
		
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
				
				if (properties.hasKey("fuelTank"))
				{
					FluidTank tank = instance.getTank();
					tank.readFromNBT(properties.getCompoundTag("fuelTank"));
					instance.setTank(tank);
				}
				if (properties.hasKey("enabled"))
				{
					instance.setEnabled(properties.getBoolean("enabled"));
				} else
				{
					instance.setEnabled(false);
				}
				if (properties.hasKey("usedfuel"))
				{
					instance.setUsedFuel(properties.getFloat("usedfuel"));
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

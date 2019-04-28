package net.orbitallabs.tiles;

import java.util.EnumSet;
import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseElectricBlock;
import micdoodle8.mods.galacticraft.core.util.RedstoneUtil;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.relauncher.Side;

public class TileEntityGravitySource extends TileBaseElectricBlock {
	@NetworkedField(targetSide = Side.CLIENT)
	public double gravityAddition = 0F;
	@NetworkedField(targetSide = Side.CLIENT)
	public double SettedGA = 1.0F;
	
	public TileEntityGravitySource()
	{
		storage.setCapacity(4000F);
		storage.setMaxExtract(500F);
	}
	
	@Override
	public boolean shouldUseEnergy()
	{
		return false;
	}
	
	@Override
	public boolean canConnect(EnumFacing direction, NetworkType type)
	{
		return true;
	}
	
	public EnumSet<EnumFacing> getElectricalInputDirections()
	{
		return EnumSet.allOf(EnumFacing.class);
	}
	
	/**
	 * actually main
	 */
	@Override
	public void slowDischarge()
	{
		if (this.hasEnoughEnergyToRun && !RedstoneUtil.isBlockReceivingRedstone(this.world, this.pos))
		{
			gravityAddition = SettedGA;
		} else
		{
			gravityAddition = 0;
		}
		if (gravityAddition > 0)
		{
			float extract = 0F;
			if (gravityAddition <= 1)
			{
				extract = (float) (125 * gravityAddition);
			} else
			{
				extract = (float) (125 + 500 * (gravityAddition - 1));
			}
			this.storage.extractEnergyGC(extract, false);
		}
	}
	
	@Override
	public ItemStack getBatteryInSlot()
	{
		return null;
	}
	
	@Override
	public EnumFacing getElectricInputDirection()
	{
		return EnumFacing.getFront(2);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setDouble("GValue", SettedGA);
		return nbt;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		SettedGA = nbt.getDouble("GValue");
	}
	
	@Override
	public EnumFacing getFront()
	{
		return EnumFacing.DOWN;
	}
	
}

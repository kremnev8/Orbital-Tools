
package net.orbitallabs.tiles;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IElectricItemManager;
import micdoodle8.mods.galacticraft.api.entity.IFuelable;
import micdoodle8.mods.galacticraft.api.transmission.NetworkType;
import micdoodle8.mods.galacticraft.core.blocks.BlockMulti.EnumBlockMultiType;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseElectricBlockWithInventory;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.blocks.BlockContainerMod;
import net.orbitallabs.items.ItemSpaceJetpack;
import net.orbitallabs.items.SpaceJetpackProvider;
import net.orbitallabs.items.SpaceJetpackStorage.ISpaceJetpackState;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.ArmorStandItemSyncPacket;

public class TileEntityArmorStand extends TileBaseElectricBlockWithInventory implements IMultiBlock, IFuelable {
	@NetworkedField(targetSide = Side.CLIENT)
	
	public NonNullList<ItemStack> items = NonNullList.withSize(4, ItemStack.EMPTY);
	
	@Override
	@SideOnly(Side.CLIENT)
	public AxisAlignedBB getRenderBoundingBox()
	{
		return new AxisAlignedBB(pos.getX() - 1, pos.getY(), pos.getZ() - 1, pos.getX() + 2, pos.getY() + 2, pos.getZ() + 2);
		
	}
	
	public TileEntityArmorStand()
	{
		this.storage.setCapacity(STANDARD_CAPACITY * 8);
		storage.setMaxExtract(500F);
	}
	
	boolean swapped = false;
	
	@Override
	public boolean onActivated(EntityPlayer player)
	{
		if (swapped)
		{
			swapped = false;
			return false;
		}
		NonNullList<ItemStack> stand = items;
		NonNullList<ItemStack> playerS = NonNullList.withSize(player.inventory.armorInventory.size(), ItemStack.EMPTY);
		Collections.copy(playerS, player.inventory.armorInventory);
		
		if (stand != null)
		{
			player.inventory.setInventorySlotContents(39, stand.get(0));
			player.inventory.setInventorySlotContents(38, stand.get(1));
			player.inventory.setInventorySlotContents(37, stand.get(2));
			player.inventory.setInventorySlotContents(36, stand.get(3));
		}
		if (playerS != null)
		{
			this.setInventorySlotContents(3, playerS.get(0));
			this.setInventorySlotContents(2, playerS.get(1));
			this.setInventorySlotContents(1, playerS.get(2));
			this.setInventorySlotContents(0, playerS.get(3));
		}
		PacketHandler.sendToDimension(new ArmorStandItemSyncPacket(items, pos.getX(), pos.getY(), pos.getZ()), world.provider.getDimension());
		swapped = true;
		
		return true;
	}
	
	@Override
	public void update()
	{
		super.update();
		
		if ((ticks % 100 == 0 || (ticks > 15 && ticks <= 35)) && !world.isRemote)
		{
			PacketHandler.sendToDimension(new ArmorStandItemSyncPacket(items, pos.getX(), pos.getY(), pos.getZ()), world.provider.getDimension());
			swapped = false;
		}
		
		for (int i = 0; i < items.size(); i++)
		{
			ItemStack item = items.get(i);
			//	if (Loader.isModLoaded("OpenComputers")) //for now disabled
			//	{
			//		if (item.getItem() instanceof Chargeable)
			//		{
			//			if (ItemCharge.canCharge(item))
			//			{
			//				if (this.hasEnoughEnergyToRun)
			//				{
			//					Chargeable charg = (Chargeable) item.getItem();
			//					ItemCharge.charge(item, 10);
			//					double charged = charg.receiveEnergy(item, 10, false);
			//					this.storage.extractEnergyGC((float) (EnergyConfigHandler.RF_RATIO * charged), false);
			//				}
			//			}
			//		}
			//	}
			if (Loader.isModLoaded("IC2"))
			{
				if (item.getItem() instanceof IElectricItem)
				{
					IElectricItemManager manager = ElectricItem.manager;
					double charge = manager.charge(item, storage.getEnergyStoredGC() * EnergyConfigHandler.TO_IC2_RATIO, 4, false, false);
					this.storage.extractEnergyGC((float) (EnergyConfigHandler.IC2_RATIO * charge), false);
				} else if (item.getItem() instanceof IEnergyStorage)
				{
					IEnergyStorage stor = (IEnergyStorage) item.getItem();
					if (stor.canReceive())
					{
						int charge = stor.receiveEnergy((int) (storage.getEnergyStoredGC() * EnergyConfigHandler.TO_RF_RATIO), false);
						this.storage.extractEnergyGC((float) (EnergyConfigHandler.IC2_RATIO * charge), false);
					}
				}
			} else if (item.getItem() instanceof IEnergyStorage)
			{
				IEnergyStorage stor = (IEnergyStorage) item.getItem();
				if (stor.canReceive())
				{
					int charge = stor.receiveEnergy((int) (storage.getEnergyStoredGC() * EnergyConfigHandler.TO_RF_RATIO), false);
					this.storage.extractEnergyGC((float) (EnergyConfigHandler.IC2_RATIO * charge), false);
				}
			}
			//	if (Loader.isModLoaded("CoFHCore"))
			//	{
			//		if (item.getItem() instanceof IEnergyContainerItem)
			//		{
			//			IEnergyContainerItem cont = (IEnergyContainerItem) item.getItem();
			//			int used = cont.receiveEnergy(item, 500, false);
			//			this.storage.extractEnergyGC((float) (EnergyConfigHandler.RF_RATIO * used), false);
			//		}
			//	}
			
		}
	}
	
	@Override
	public void onCreate(World world, BlockPos placedPosition)
	{
		this.markDirty();
		int buildHeight = this.world.getHeight() - 1;
		
		for (int y = 0; y < 2; y++)
		{
			if (placedPosition.getY() + y > buildHeight) return;
			final BlockPos place = new BlockPos(placedPosition.getX(), placedPosition.getY() + y, placedPosition.getZ());
			
			if (!place.equals(placedPosition))
			{
				BlockContainerMod.BlockFake.makeFakeBlock(this.world, place, placedPosition, 0);
			}
		}
	}
	
	@Override
	public void onDestroy(TileEntity callingBlock)
	{
		int fakeBlockCount = 0;
		
		for (int y = 0; y < 2; y++)
		{
			if (y == 0)
			{
				continue;
			}
			BlockPos Tpos = pos.add(0, y, 0);
			if (this.world.getBlockState(Tpos).getBlock() == BlockContainerMod.BlockFake)
			{
				fakeBlockCount++;
			}
		}
		
		if (fakeBlockCount == 0)
		{
			if (this.world.isRemote && this.world.rand.nextDouble() < 0.1D)
			{
				FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(pos, BlockContainerMod.BlockArmorStand.getBlockState().getBaseState());
			}
			this.world.destroyBlock(pos, true);
			return;
		}
		
		for (int y = 0; y < 2; y++)
		{
			if (this.world.isRemote && this.world.rand.nextDouble() < 0.1D)
			{
				FMLClientHandler.instance().getClient().effectRenderer.addBlockDestroyEffects(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ()),
						BlockContainerMod.BlockArmorStand.getBlockState().getBaseState());
			}
			this.world.destroyBlock(new BlockPos(pos.getX(), pos.getY() + y, pos.getZ()), true);
		}
	}
	
	public void readFromNBT(NBTTagCompound par1NBTTagCompound)
	{
		super.readFromNBT(par1NBTTagCompound);
		this.items = this.readStandardItemsFromNBT(par1NBTTagCompound);
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		this.writeStandardItemsToNBT(nbt, items);
		return nbt;
	}
	
	@Override
	public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
	{
		return true;
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
	
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
	public void slowDischarge()
	{
		// this.storage.extractEnergyGC(0.5F, false);
	}
	
	public ItemStack getArmor(int i)
	{
		if (i < items.size())
		{
			return items.get(i);
		}
		return null;
	}
	
	@Override
	public int addFuel(FluidStack fluid, boolean doFill)
	{
		if (items.size() >= 2)
		{
			ItemStack armor = items.get(1);
			if (armor != null)
			{
				Item i = armor.getItem();
				if (i instanceof ItemSpaceJetpack)
				{
					ISpaceJetpackState cap = armor.getCapability(SpaceJetpackProvider.SpaceJetpackCapability, EnumFacing.UP);
					
					FluidTank tank = cap.getTank();
					int amount = tank.fill(fluid, doFill);
					return amount;
				}
			}
		}
		return 0;
	}
	
	@Override
	public FluidStack removeFuel(int amount)
	{
		return null;
	}
	
	@Override
	public ItemStack getBatteryInSlot()
	{
		return ItemStack.EMPTY;
	}
	
	@Override
	public String getName()
	{
		return "Armor stand";
	}
	
	@Override
	public void getPositions(BlockPos placedPosition, List<BlockPos> positions)
	{
	}
	
	@Override
	public EnumBlockMultiType getMultiType()
	{
		return null;
	}
	
	@Override
	public EnumFacing getFront()
	{
		return EnumFacing.DOWN;
	}
	
	@Override
	protected NonNullList<ItemStack> getContainingItems()
	{
		return items;
	}
}

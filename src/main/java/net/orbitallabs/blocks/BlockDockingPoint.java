
package net.orbitallabs.blocks;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orbitallabs.OrbitalTools;
import net.orbitallabs.dimensions.DockingPortSaveData;
import net.orbitallabs.dimensions.WorldProviderOrbitModif;
import net.orbitallabs.gui.GuiHandler;
import net.orbitallabs.tiles.TileEntityDockingPort;

public class BlockDockingPoint extends BlockContainerMod {
	
	private static String name;
	
	public BlockDockingPoint(String uln)
	{
		super(uln);
		this.name = uln;
		this.setCreativeTab(CreativeTabs.REDSTONE);
		this.setShowDesrc(true);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (world.isRemote)
		{
			return true;
		} else
		{
			player.openGui(OrbitalTools.instance, GuiHandler.DOCKINGPORTGUI, world, pos.getX(), pos.getY(), pos.getZ());
			return true;
		}
	} 
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote)
		{
			
			if (world.provider instanceof WorldProviderOrbitModif)
			{
				DockingPortSaveData savef = DockingPortSaveData.forWorld(world);
				savef.DockingPorts.add(pos);
				savef.markDirty(); 
			}
		}
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote)
		{
			
			if (world.provider instanceof WorldProviderOrbitModif)
			{
				DockingPortSaveData savef = DockingPortSaveData.forWorld(world);
				for (int i = 0; i < savef.DockingPorts.size(); i++)
				{
					if (savef.DockingPorts.get(i).equals(pos))
					{
						savef.DockingPorts.remove(i);
						savef.markDirty();
						
					}
				}
			}
			dropEntireInventory(world, pos);
			TileEntityDockingPort te = (TileEntityDockingPort) world.getTileEntity(pos);
			if (te != null && te.rocket != null)
			{
				te.rocket.setDead();
			}
		}
	}
	
	public void dropEntireInventory(World world, BlockPos pos)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		
		if (tileEntity != null)
		{
			if (tileEntity instanceof IInventory)
			{
				IInventory inventory = (IInventory) tileEntity;
				
				for (int i = 0; i < inventory.getSizeInventory(); ++i)
				{
					ItemStack slot = inventory.getStackInSlot(i);
					
					if (slot != null)
					{
						Random random = new Random();
						float f1 = random.nextFloat() * 0.8F + 0.1F;
						float f2 = random.nextFloat() * 0.8F + 0.1F;
						float f3 = random.nextFloat() * 0.8F + 0.1F;
						
						while (slot.getCount() > 0)
						{
							int f4 = random.nextInt(21) + 10;
							
							if (f4 > slot.getCount())
							{
								f4 = slot.getCount();
							}
							
							slot.shrink(f4);
							EntityItem ent = new EntityItem(world, pos.getX() + f1, pos.getY() + f2, pos.getZ() + f3,
									new ItemStack(slot.getItem(), f4, slot.getItemDamage()));
							
							if (slot.hasTagCompound())
							{
								ent.getItem().setTagCompound((NBTTagCompound) slot.getTagCompound().copy());
							}
							
							float var13 = 0.05F;
							ent.motionX = (float) random.nextGaussian() * var13;
							ent.motionY = (float) random.nextGaussian() * var13 + 0.2F;
							ent.motionZ = (float) random.nextGaussian() * var13;
							world.spawnEntity(ent);
						}
					}
				}
			}
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityDockingPort();
	}
	
}

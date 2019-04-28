
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
				
				for (int var6 = 0; var6 < inventory.getSizeInventory(); ++var6)
				{
					ItemStack var7 = inventory.getStackInSlot(var6);
					
					if (var7 != null)
					{
						Random random = new Random();
						float var8 = random.nextFloat() * 0.8F + 0.1F;
						float var9 = random.nextFloat() * 0.8F + 0.1F;
						float var10 = random.nextFloat() * 0.8F + 0.1F;
						
						while (var7.getCount() > 0)
						{
							int var11 = random.nextInt(21) + 10;
							
							if (var11 > var7.getCount())
							{
								var11 = var7.getCount();
							}
							
							var7.shrink(var11);
							EntityItem var12 = new EntityItem(world, pos.getX() + var8, pos.getY() + var9, pos.getZ() + var10,
									new ItemStack(var7.getItem(), var11, var7.getItemDamage()));
							
							if (var7.hasTagCompound())
							{
								var12.getEntityItem().setTagCompound((NBTTagCompound) var7.getTagCompound().copy());
							}
							
							float var13 = 0.05F;
							var12.motionX = (float) random.nextGaussian() * var13;
							var12.motionY = (float) random.nextGaussian() * var13 + 0.2F;
							var12.motionZ = (float) random.nextGaussian() * var13;
							world.spawnEntity(var12);
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


package net.orbitallabs.blocks;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.orbitallabs.OrbitalTools;
import net.orbitallabs.dimensions.DockingPortSaveData;
import net.orbitallabs.dimensions.WorldProviderOrbitModif;
import net.orbitallabs.gui.GuiHandler;
import net.orbitallabs.tiles.TileEntityGravitySource;

public class BlockArtificialGravitySource extends BlockContainerMod {
	
	private static String name;
	
	public BlockArtificialGravitySource(String uln)
	{
		super(uln);
		this.name = uln;
		this.setCreativeTab(CreativeTabs.REDSTONE);
		this.setShowDesrc(true);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		player.openGui(OrbitalTools.instance, GuiHandler.GRAVITYSOURCEGUI, world, pos.getX(), pos.getY(), pos.getZ());
		return true;
	}
	
	@Override
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote)
		{
			
			if (world.provider instanceof WorldProviderOrbitModif)
			{
				DockingPortSaveData savef = DockingPortSaveData.forWorld(world);
				savef.GraviySources.add(new int[] { pos.getX(), pos.getY(), pos.getZ() });//TODO: rewite to use BlockPos
				savef.markDirty();
				
				List<Double> gforces = new ArrayList();
				for (int i = 0; i < savef.GraviySources.size(); i++)
				{
					if (world.getTileEntity(new BlockPos(savef.GraviySources.get(i)[0], savef.GraviySources.get(i)[1], savef.GraviySources.get(i)[2])) != null)
					{
						TileEntity te = world.getTileEntity(new BlockPos(savef.GraviySources.get(i)[0], savef.GraviySources.get(i)[1], savef.GraviySources.get(i)[2]));
						if (te instanceof TileEntityGravitySource)
						{
							gforces.add(((TileEntityGravitySource) te).gravityAddition);
						}
					}
				}
				
				//	PacketHandler.sendToDimension(new ClientGravityDataRecivePacket(gforces), world.provider.dimensionId);
			}
		}
	}
	
	@Override
	public void onBlockDestroyedByExplosion(World worldIn, BlockPos pos, Explosion explosionIn)
	{
		onBlockDestroyedByPlayer(worldIn, pos, worldIn.getBlockState(pos));
	}
	
	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos, IBlockState state)
	{
		if (!world.isRemote)
		{
			
			if (world.provider instanceof WorldProviderOrbitModif)
			{
				DockingPortSaveData savef = DockingPortSaveData.forWorld(world);
				for (int i = 0; i < savef.GraviySources.size(); i++)
				{
					if (savef.GraviySources.get(i)[0] == pos.getX() && savef.GraviySources.get(i)[1] == pos.getY() && savef.GraviySources.get(i)[2] == pos.getZ())
					{
						savef.GraviySources.remove(i);
						savef.markDirty();
						
						List<Double> gforces = new ArrayList();
						for (int i2 = 0; i2 < savef.GraviySources.size(); i2++)
						{
							if (world.getTileEntity(new BlockPos(savef.GraviySources.get(i2)[0], savef.GraviySources.get(i2)[1], savef.GraviySources.get(i2)[2])) != null)
							{
								TileEntity te = world.getTileEntity(new BlockPos(savef.GraviySources.get(i2)[0], savef.GraviySources.get(i2)[1], savef.GraviySources.get(i2)[2]));
								if (te instanceof TileEntityGravitySource)
								{
									gforces.add(((TileEntityGravitySource) te).gravityAddition);
								}
							}
						}
						
						//	PacketHandler.sendToDimension(new ClientGravityDataRecivePacket(gforces), world.provider.dimensionId);
					}
				}
			}
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityGravitySource();
	}
	
}

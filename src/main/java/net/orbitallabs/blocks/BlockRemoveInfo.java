package net.orbitallabs.blocks;

import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.orbitallabs.tiles.TileEntityRemoveInfo;
import net.orbitallabs.utils.Config;

public class BlockRemoveInfo extends BlockContainerMod {
	
	public BlockRemoveInfo(String uln)
	{
		super(uln);
		if (Config.makeSpaceStructureControlBlocksUnbreakable) this.setBlockUnbreakable();
		this.setCreativeTab(CreativeTabs.REDSTONE);
	}
	
	private static final AxisAlignedBB AABB = new AxisAlignedBB(0.1875, 0, 0.1875, 0.8125, 0.75, 0.8125);
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		state = state.getActualState(source, pos);
		EnumFacing enumfacing = (EnumFacing) state.getValue(FACING);
		if (enumfacing == EnumFacing.EAST)
		{
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 0.06F, 1.0F, 1.0F);
		} else if (enumfacing == EnumFacing.WEST)
		{
			return new AxisAlignedBB(0.94F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
		} else if (enumfacing == EnumFacing.SOUTH)
		{
			return new AxisAlignedBB(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.06F);
		} else if (enumfacing == EnumFacing.NORTH)
		{
			return new AxisAlignedBB(0.0F, 0.0F, 0.94F, 1.0F, 1.0F, 1.0F);
		}
		return AABB;
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean isFullCube(IBlockState state)
	{
		return false;
	}
	
	/**
	 * Convert the given metadata into a BlockState for this Block
	 */
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}
	
	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
	}
	
	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity)
	{
		if (entity instanceof EntityItem)
		{
			entity.addVelocity(0, 1, 0);
		}
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		double face = (double) (placer.rotationYaw * 4.0F / 360.0F) + 2.5D;
		int meta = MathHelper.floor(face) & 3;
		world.setBlockState(pos, getStateFromMeta(meta));
	}
	
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return new TileEntityRemoveInfo();
	}
	
}

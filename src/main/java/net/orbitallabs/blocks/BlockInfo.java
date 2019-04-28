
package net.orbitallabs.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.orbitallabs.tiles.TileEntityInfo;
import net.orbitallabs.utils.Config;

public class BlockInfo extends BlockContainerMod {
	
	public static final Material invis = new MaterialInvisble(MapColor.AIR);
	
	public AxisAlignedBB box = new AxisAlignedBB(0, 0, 0, 0, 0, 0);
	
	public BlockInfo(String uln)
	{
		super(uln, invis);
		if (Config.makeSpaceStructureControlBlocksUnbreakable) this.setBlockUnbreakable();
		this.setCreativeTab(CreativeTabs.REDSTONE);
	}
	
	//	private static final AxisAlignedBB AABB = new AxisAlignedBB(0, 0, 0, 0, 0.75, 0.8125);
	
	//	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	//{
	//	return AABB;
	//}
	
	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos)
	{
		return box;
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos)
	{
		return null;
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
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	//	@Override
	//	public float getExplosionResistance(Entity par1Entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
	//	{
	//		return 1.0F;s
	///	}
	
	//	@Override
	//	public float getBlockHardness(World par1World, int par2, int par3, int par4)
	//	{
	//		return 100.0F;
	//	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new TileEntityInfo();
	}
	
}

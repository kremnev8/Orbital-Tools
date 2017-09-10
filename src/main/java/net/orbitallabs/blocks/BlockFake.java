
package net.orbitallabs.blocks;

import java.util.Random;
import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.core.tile.TileEntityMulti;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockFake extends BlockContainerMod implements IPartialSealableBlock, ITileEntityProvider {
	
	public BlockFake(String uln)
	{
		super(uln);
		this.setHardness(1.0F);
		this.setSoundType(SoundType.METAL);
		this.setResistance(1000000000000000.0F);
	}
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public boolean canDropFromExplosion(Explosion par1Explosion)
	{
		return false;
	}
	
	public void makeFakeBlock(World worldObj, BlockPos position, BlockPos mainBlock, int meta)
	{
		worldObj.setBlockState(position, this.getDefaultState());
		((TileEntityMulti) worldObj.getTileEntity(position)).mainBlockPosition = mainBlock;
	}
	
	@Override
	public float getBlockHardness(IBlockState blockState, World world, BlockPos pos)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		
		if (tileEntity instanceof TileEntityMulti)
		{
			BlockPos mainBlockPosition = ((TileEntityMulti) tileEntity).mainBlockPosition;
			
			if (mainBlockPosition != null)
			{
				return world.getBlockState(mainBlockPosition).getBlock().getBlockHardness(world.getBlockState(mainBlockPosition), world, mainBlockPosition);
			}
		}
		
		return this.blockHardness;
	}
	
	@Override
	public boolean isSealed(World world, BlockPos pos, EnumFacing direction)
	{
		int metadata = world.getBlockState(pos).getBlock().getMetaFromState(world.getBlockState(pos));
		
		if (metadata == 0)
		{
			return direction == EnumFacing.DOWN;
		}
		
		return false;
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		
		if (tileEntity instanceof TileEntityMulti)
		{
			((TileEntityMulti) tileEntity).onBlockRemoval();
		}
		
		super.breakBlock(world, pos, state);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		TileEntityMulti tileEntity = (TileEntityMulti) world.getTileEntity(pos);
		return tileEntity.onBlockActivated(world, pos, player);
	}
	
	/**
	 * Returns the quantity of items to drop on block destruction.
	 */
	@Override
	public int quantityDropped(Random par1Random)
	{
		return 0;
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.INVISIBLE;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int meta)
	{
		return new TileEntityMulti();
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof TileEntityMulti)
		{
			BlockPos mainBlockPosition = ((TileEntityMulti) tileEntity).mainBlockPosition;
			
			if (mainBlockPosition != null)
			{
				Block mainBlock = world.getBlockState(mainBlockPosition).getBlock();
				
				if (Blocks.AIR != mainBlock && Blocks.BEDROCK != mainBlock)
				{
					return mainBlock.getPickBlock(world.getBlockState(mainBlockPosition), target, world, mainBlockPosition, player);
				} else
				{
					return new ItemStack(BlockContainerMod.BlockArmorStand);
				}
			}
		}
		
		return null;
	}
	
	@Override
	public boolean addHitEffects(IBlockState state, World worldObj, RayTraceResult target, ParticleManager manager)
	{
		TileEntity tileEntity = worldObj.getTileEntity(target.getBlockPos());
		
		if (tileEntity instanceof TileEntityMulti)
		{
			BlockPos mainBlockPosition = ((TileEntityMulti) tileEntity).mainBlockPosition;
			
			if (mainBlockPosition != null)
			{
				manager.addBlockHitEffects(mainBlockPosition, target.sideHit);
			}
		}
		
		return super.addHitEffects(state, worldObj, target, manager);
	}
	
}

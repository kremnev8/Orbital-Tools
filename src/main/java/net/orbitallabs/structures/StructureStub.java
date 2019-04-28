
package net.orbitallabs.structures;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orbitallabs.blocks.BlockBuildPoint.EnumBlockPointStates;
import net.orbitallabs.utils.OreDictItemStack;

public class StructureStub extends Structure {
	
	private boolean hiddenS;
	
	public StructureStub(boolean hidden)
	{
		super(hidden);
		this.hiddenS = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureStub Nstr = new StructureStub(hiddenS);
		if (this.placementPos == null) placementPos = BlockPos.ORIGIN;
		Nstr.Configure(new BlockPos(placementPos), placementRotation, placementDir);
		return Nstr;
	}
	
	@Override
	public void deconstruct(World world, EnumFacing dir, BlockPos spos)
	{
		int x, y, z;
		x = spos.getX();
		y = spos.getY();
		z = spos.getZ();
		if (dir == EnumFacing.WEST)
		{// WEST
			Block Bblock = Blocks.AIR;
			Block Bpoint = Blocks.AIR;
			Block SHalf = Blocks.AIR;
			Block Stairs = Blocks.AIR;
			BuildHandler.setBlock(world, x - 1, y - 2, z - 1, Stairs, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 0, Stairs, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 1, Stairs, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 1, z - 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 1, z + 0, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 1, z + 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 0, z - 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 0, z + 0, Bpoint, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 0, z + 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 1, z - 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 1, z + 0, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 1, z + 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 2, z - 1, Stairs, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 2, z + 0, Stairs, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 2, z + 1, Stairs, 0, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z - 2, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z - 1, SHalf, 0, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 0, SHalf, 0, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 1, SHalf, 0, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 2, Stairs, 6, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 2, Stairs, 7, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 2, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z - 2, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z - 2, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z - 1, SHalf, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, SHalf, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 1, SHalf, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 2, Stairs, 2, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, Stairs, 3, 2);
		} else if (dir == EnumFacing.EAST)
		{
			Block block5 = Blocks.AIR;
			
			Block block6 = Blocks.AIR;
			
			Block block8 = Blocks.AIR;
			
			Block block9 = Blocks.AIR;
			
			BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block5, 6, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block5, 7, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -1, block9, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 0, block9, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 1, block9, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + -1, block9, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block9, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block9, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block5, 2, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block5, 3, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block5, 5, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block5, 5, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block5, 5, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + -1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 0, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 0, block8, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + -1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block5, 1, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block5, 1, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block5, 1, 2);
			
		} else if (dir == EnumFacing.SOUTH)
		{
			Block block2 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
			Block block3 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block2, 7, 2);
			Block block4 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -1, y + -1, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 0, block4, 8, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block2, 3, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block2, 7, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 1, block3, 4, 2);
			Block block5 = Blocks.AIR;
			BuildHandler.setBlock(world, x + 0, y + 0, z + 1, block5, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block4, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block2, 3, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block2, 7, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block4, 8, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block2, 3, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
		} else if (dir == EnumFacing.NORTH)
		{
			Block block1 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block1, 4, 2);
			Block block2 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block1, 6, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + -1, block2, 4, 2);
			Block block3 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -1, y + -1, z + 0, block3, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 0, block3, 8, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block1, 2, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block1, 6, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 0, block3, 0, 2);
			Block block4 = Blocks.AIR;
			BuildHandler.setBlock(world, x + 0, y + 0, z + -1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block3, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block1, 2, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block1, 6, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 0, block3, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block3, 8, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block1, 2, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 1, 2);
		}
		
	}
	
	@Override
	public void Build(World world, EnumFacing dir, BlockPos spos)
	{
		Build(world, dir, spos, 1);
	}
	
	public void Build(World world, EnumFacing dir, BlockPos spos, int meta)
	{
		int x, y, z;
		x = spos.getX();
		y = spos.getY();
		z = spos.getZ();
		if (dir == EnumFacing.WEST)
		{// WEST
			Block Bblock = GCBlocks.basicBlock;
			
			Block SHalf = GCBlocks.slabGCHalf;
			Block Stairs = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x - 1, y - 2, z - 1, Stairs, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 0, Stairs, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 1, Stairs, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 1, z - 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 1, z + 0, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 1, z + 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 0, z - 1, Bblock, 4, 2);
			
			BuildHandler.buildBuildPoint(world, x - 1, y, z, EnumBlockPointStates.EVERYTHING);
			BuildHandler.setBlock(world, x - 1, y + 0, z + 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 1, z - 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 1, z + 0, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 1, z + 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 2, z - 1, Stairs, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 2, z + 0, Stairs, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 2, z + 1, Stairs, 0, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z - 2, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z - 1, SHalf, 0, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 0, SHalf, 0, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 1, SHalf, 0, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 2, Stairs, 6, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 2, Stairs, 7, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 2, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z - 2, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z - 2, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z - 1, SHalf, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, SHalf, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 1, SHalf, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 2, Stairs, 2, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, Bblock, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, Stairs, 3, 2);
		} else if (dir == EnumFacing.EAST)
		{
			Block block5 = GCBlocks.tinStairs2;
			
			Block block6 = GCBlocks.basicBlock;
			
			Block block9 = GCBlocks.slabGCHalf;
			
			BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block5, 6, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block5, 7, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -1, block9, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 0, block9, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 1, block9, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + -1, block9, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block9, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block9, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block5, 2, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block5, 3, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block5, 5, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block5, 5, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block5, 5, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + -1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 0, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -1, block6, 4, 2);
			BuildHandler.buildBuildPoint(world, x + 1, y, z, EnumBlockPointStates.EVERYTHING);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + -1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 1, block6, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block5, 1, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block5, 1, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block5, 1, 2);
			
		} else if (dir == EnumFacing.SOUTH)
		{
			Block block2 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
			Block block3 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block2, 7, 2);
			Block block4 = GCBlocks.slabGCHalf;
			BuildHandler.setBlock(world, x + -1, y + -1, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 0, block4, 8, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block2, 3, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block2, 7, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 1, block3, 4, 2);
			BuildHandler.buildBuildPoint(world, x, y, z + 1, EnumBlockPointStates.EVERYTHING);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block4, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block2, 3, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block2, 7, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block4, 8, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block2, 3, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
		} else if (dir == EnumFacing.NORTH)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block1, 4, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block1, 6, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + -1, block2, 4, 2);
			Block block3 = GCBlocks.slabGCHalf;
			BuildHandler.setBlock(world, x + -1, y + -1, z + 0, block3, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 0, block3, 8, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block1, 2, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block1, 6, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 0, block3, 0, 2);
			BuildHandler.buildBuildPoint(world, x, y, z - 1, EnumBlockPointStates.EVERYTHING);
			BuildHandler.setBlock(world, x + 0, y + 1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block3, 8, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block1, 2, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block1, 6, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 0, block3, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block3, 8, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block1, 2, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 1, 2);
		}
		
	}
	
	@Override
	public void ClearWay(World world, EnumFacing dir, BlockPos spos)
	{
	}
	
	@Override
	public boolean Check(World world, EnumFacing dir, BlockPos spos, int meta)
	{
		if (dir == EnumFacing.WEST || dir == EnumFacing.EAST || dir == EnumFacing.NORTH || dir == EnumFacing.SOUTH) return true;
		else return false;
	}
	
	@Override
	public String getName()
	{
		return "_Stub";
	}
	
	@Override
	public boolean isHidden()
	{
		return hiddenS;
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "stub";
	}
	
	@Override
	public NonNullList<OreDictItemStack> getRequiredItems()
	{
		NonNullList<OreDictItemStack> items = NonNullList.create();
		return items;
	}
	
}

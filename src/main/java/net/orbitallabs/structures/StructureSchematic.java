package net.orbitallabs.structures;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orbitallabs.utils.OreDictItemStack;

public abstract class StructureSchematic extends Structure {
	//TODO: make attempt to use .schematic files instead of direct build code. 
	public StructureSchematic()
	{
		super(false);
	}
	
	public StructureSchematic(int x, int y, int z, int rot, EnumFacing dir)
	{
		super(x, y, z, rot, dir);
	}
	
	public StructureSchematic(int[] pos, int rot, EnumFacing dir)
	{
		super(pos, rot, dir);
	}
	
	public StructureSchematic(BlockPos pos, int rot, EnumFacing dir)
	{
		super(pos, rot, dir);
	}
	
	@Override
	public void Build(World world, EnumFacing dir, BlockPos pos)
	{
		
	}
	
	@Override
	public void deconstruct(World world, EnumFacing dir, BlockPos pos)
	{
		
	}
	
	@Override
	public boolean Check(World world, EnumFacing dir, BlockPos pos, int meta)
	{
		return true;
	}
	
	@Override
	public void ClearWay(World world, EnumFacing dir, BlockPos pos)
	{
		
	}
	
	@Override
	public boolean isHidden()
	{
		return false;
	}
	
	@Override
	public abstract String getName();
	
	@Override
	public abstract String getUnlocalizedName();
	
	@Override
	public abstract Structure copy();
	
	@Override
	public NonNullList<OreDictItemStack> getRequiredItems()
	{
		return null;
	}
	
}

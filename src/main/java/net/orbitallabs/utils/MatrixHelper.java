package net.orbitallabs.utils;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orbitallabs.blocks.BlockContainerMod;

public class MatrixHelper {
	
	private static List<BlockPos> totalMatrix = new ArrayList();
	private static List<BlockPos> toScan = new ArrayList();
	
	public static BlockPos findPointForAddOBJ(World world, EnumFacing dir, BlockPos pos)
	{
		int Nx, Ny, Nz;
		BlockPos ret = new BlockPos(pos);
		if (dir == EnumFacing.DOWN)
		{
			ret = ret.add(0, -1, 0);
			if (isMatrixPoint(world, ret))
			{
				return ret;
			}
		} else if (dir == EnumFacing.UP)
		{
			if (isMatrixPoint(world, ret.add(0, -5, 0)))
			{
				return ret.add(0, -5, 0);
			} else
			{
				ret = ret.add(0, -6, 0);
				if (isMatrixPoint(world, ret))
				{
					return ret;
				}
			}
		} else
		{
			for (int i = 0; i < 2; i++)
			{
				ret = new BlockPos(pos);
				if (i == 0)
				{
					ret = FacingUtils.IncreaseByDir(dir.getOpposite(), ret, 1);
					ret = ret.add(0, -3, 0);
					if (isMatrixPoint(world, ret))
					{
						return ret;
					}
				} else if (i == 1)
				{
					ret = FacingUtils.IncreaseByDir(dir.getOpposite(), ret, 5);
					ret = ret.add(0, -3, 0);
					if (isMatrixPoint(world, ret))
					{
						return ret;
					}
					
				}
			}
		}
		
		return new BlockPos(0, 0, 0);
	}
	
	public static boolean isMatrixPoint(World world, BlockPos pos)
	{
		return world.getBlockState(pos).getBlock() == BlockContainerMod.BlockInfo;
	}
	
	public static BlockPos findMatrixPoint(World world, EnumFacing dir, BlockPos pos)
	{
		if (dir != EnumFacing.DOWN && dir != EnumFacing.UP)
		{
			pos = FacingUtils.IncreaseByDir(dir.getOpposite(), pos, 5);
			
			//GLoger.logInfo(Px+" "+Py+" "+Pz);
			if (world.getBlockState(pos.add(0, -3, 0)).getBlock() == BlockContainerMod.BlockInfo) return pos.add(0, -3, 0);
		}
		return pos;
	}
	
	public static List<BlockPos> findTotalMatrix(World world, BlockPos Fpoint)
	{
		totalMatrix.clear();
		toScan.clear();
		
		List<BlockPos> lastMatrix = new ArrayList();
		if (Fpoint != null)
		{
			addIfNotAdded(Fpoint);
			
			lastMatrix = findNextMatrixPoints(world, Fpoint);
			for (int i = 0; i < lastMatrix.size(); i++)
			{
				addIfNotAdded(lastMatrix.get(i));
				addIfNotAddedToScan(lastMatrix.get(i));
			}
			
			while (toScan.size() != 0)
			{
				lastMatrix.clear();
				lastMatrix = findNextMatrixPoints(world, toScan.remove(0));
				for (int i = 0; i < lastMatrix.size(); i++)
				{
					if (addIfNotAdded(lastMatrix.get(i))) addIfNotAddedToScan(lastMatrix.get(i));
				}
			}
			
			return totalMatrix;
			
		}
		
		return null;
	}
	
	public static boolean addIfNotAdded(BlockPos point)
	{
		for (int i = 0; i < totalMatrix.size(); i++)
		{
			if (totalMatrix.get(i).equals(point))
			{
				return false;
			}
		}
		totalMatrix.add(point);
		return true;
	}
	
	public static void addIfNotAddedToScan(BlockPos point)
	{
		for (int i = 0; i < toScan.size(); i++)
		{
			if (toScan.get(i).equals(point))
			{
				return;
			}
		}
		toScan.add(point);
	}
	
	public static BlockPos FindPointInMatrix(List<BlockPos> M, BlockPos point)
	{
		for (int i = 0; i < M.size(); i++)
		{
			if (M.get(i).equals(point))
			{
				return totalMatrix.get(i);
			}
		}
		return null;
	}
	
	public static List<BlockPos> findNextMatrixPoints(World world, BlockPos pos)
	{
		List<BlockPos> matrix = new ArrayList();
		if (pos != null)
		{
			int Px;
			int Pz;
			
			for (int i = 0; i < 4; i++)
			{
				BlockPos Tpos = FacingUtils.IncreaseByDir(EnumFacing.getHorizontal(i), pos, 9);
				
				if (world.getBlockState(Tpos).getBlock() == BlockContainerMod.BlockInfo)
				{
					matrix.add(Tpos);
				}
			}
			return matrix;
		}
		return null;
	}
	
}

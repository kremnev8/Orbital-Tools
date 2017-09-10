package net.orbitallabs.utils;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;

public class FacingUtils {
	
	public static String GetLocolizedName(EnumFacing dir)
	{
		
		return I18n.format("ForgeDirs." + dir.name() + ".name");
	}
	
	public static BlockPos IncreaseByDir(EnumFacing dir, int[] pos, int io)
	{
		BlockPos ret = new BlockPos(pos[0], pos[1], pos[2]);
		for (int i = 0; i < io; i++)
			ret = ret.add(dir.getDirectionVec());
		return ret;
	}
	
	public static BlockPos IncreaseByDir(EnumFacing dir, BlockPos pos, int io)
	{
		BlockPos ret = new BlockPos(pos);
		for (int i = 0; i < io; i++)
			ret = ret.add(dir.getDirectionVec());
		return ret;
	}
	
	public static int[] IncreaseByDirO(EnumFacing dir, int[] pos, int io)
	{
		int[] ret = pos;
		for (int i = 0; i < io; i++)
			add(ret, dir.getDirectionVec());
		return ret;
	}
	
	public static int[] add(int[] pos, Vec3i v)
	{
		pos[0] += v.getX();
		pos[1] += v.getY();
		pos[2] += v.getZ();
		return pos;
	}
	
	public static EnumFacing turnClockwise(EnumFacing dir, int i)
	{
		if (i == 4) return dir;
		EnumFacing ret = dir;
		for (int i2 = 0; i2 < i; i2++)
		{
			ret = ret.rotateY();
		}
		return ret;
	}
	
	public static EnumFacing turnCounterClockwise(EnumFacing dir, int i)
	{
		if (i == 4) return dir;
		EnumFacing ret = dir;
		for (int i2 = 0; i2 < i; i2++)
		{
			ret = ret.rotateYCCW();
		}
		return ret;
	}
}

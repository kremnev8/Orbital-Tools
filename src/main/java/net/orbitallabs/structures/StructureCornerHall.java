package net.orbitallabs.structures;

import java.util.ArrayList;
import java.util.List;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orbitallabs.blocks.BlockBuildPoint.EnumBlockPointStates;
import net.orbitallabs.blocks.BlockContainerMod;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.utils.FacingUtils;
import net.orbitallabs.utils.OreDictItemStack;

public class StructureCornerHall extends StructureRotatable {
	
	private boolean hiddenS;
	Block block1 = BlockContainerMod.BlockInfo;
	
	public StructureCornerHall(boolean hidden)
	{
		super(hidden);
		this.hiddenS = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureCornerHall Nstr = new StructureCornerHall(hiddenS);
		if (this.placementPos == null) placementPos = BlockPos.ORIGIN;
		Nstr.Configure(new BlockPos(placementPos), placementRotation, placementDir);
		return Nstr;
	}
	
	@Override
	public int nextPossibleValue(int nowV, EnumFacing dir, int meta)
	{
		int[] order = getPossibleOrder(dir);
		if (nowV == -1)
		{
			return order[0];
		}
		for (int i = 0; i < order.length; i++)
		{
			if (order[i] == nowV)
			{
				if (i + 1 == order.length)
				{
					return order[0];
				} else return order[i + 1];
			}
		}
		
		return nowV++;
	}
	
	public int[] getPossibleOrder(EnumFacing dir)
	{
		if (dir == EnumFacing.EAST)
		{
			return new int[] { 2, 3 };
		} else if (dir == EnumFacing.NORTH)
		{
			return new int[] { 1, 2 };
		} else if (dir == EnumFacing.SOUTH)
		{
			return new int[] { 3, 0 };
		} else if (dir == EnumFacing.WEST)
		{
			return new int[] { 0, 1 };
		} else
		{
			return new int[] {};
		}
	}
	
	public int getMetaFromDirARot(EnumFacing dir, int rot)
	{
		if (rot == 0)
		{
			if (dir == EnumFacing.WEST)
			{
				return 2;
			} else if (dir == EnumFacing.SOUTH)
			{
				return 3;
			}
		} else if (rot == 1)
		{
			if (dir == EnumFacing.WEST)
			{
				return 0;
			} else if (dir == EnumFacing.NORTH)
			{
				return 3;
			}
		} else if (rot == 2)
		{
			if (dir == EnumFacing.EAST)
			{
				return 0;
			} else if (dir == EnumFacing.NORTH)
			{
				return 1;
			}
		} else if (rot == 3)
		{
			if (dir == EnumFacing.EAST)
			{
				return 2;
			} else if (dir == EnumFacing.SOUTH)
			{
				return 1;
			}
		}
		return 0;
	}
	
	public EnumFacing onTurn(EnumFacing dir, int rot)
	{
		this.placementRotation = rot;
		return getDirs(dir)[0];
	}
	
	@Override
	public EnumFacing[] getDirs(EnumFacing dir)
	{
		if (dir == EnumFacing.WEST)
		{
			if (placementRotation == 0)
			{
				return new EnumFacing[] { EnumFacing.NORTH };
			} else return new EnumFacing[] { EnumFacing.SOUTH };
		} else if (dir == EnumFacing.EAST)
		{
			if (placementRotation == 2)
			{
				return new EnumFacing[] { EnumFacing.SOUTH };
			} else return new EnumFacing[] { EnumFacing.NORTH };
		} else if (dir == EnumFacing.SOUTH)
		{
			if (placementRotation == 0)
			{
				return new EnumFacing[] { EnumFacing.EAST };
			} else return new EnumFacing[] { EnumFacing.WEST };
		} else if (dir == EnumFacing.NORTH)
		{
			if (placementRotation == 1)
			{
				return new EnumFacing[] { EnumFacing.EAST };
			} else return new EnumFacing[] { EnumFacing.WEST };
		}
		
		throw new IllegalArgumentException("Facing " + dir.name() + " is outside of horizontal plane");
	}
	
	@Override
	public void deconstruct(World world, EnumFacing dir, BlockPos spos)
	{
		int x, y, z;
		x = spos.getX();
		y = spos.getY();
		z = spos.getZ();
		if (dir == EnumFacing.WEST)
		{
			if (placementRotation == 0)
			{
				// Block block1 = id:35;
				// BuildHandler.setBlock(world,x+-7, y+-3, z+-5, block1,5,2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -6, y + -2, z + -4, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + -3, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 1, block2, 4, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -6, y + -1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + -4, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + -3, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + -2, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + -1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 2, block2, 7, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -5, y + -1, z + 1, block4, 1, 2);
				BuildHandler.setBlock(world, x + -5, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 1, z + 1, block4, 9, 2);
				BuildHandler.setBlock(world, x + -5, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -4, y + -1, z + 2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+-4, y+0, z+-4, block1,4,2);
				BuildHandler.setBlock(world, x + -4, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -2, block3, 4, 2);
				Block block5 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -4, y + 2, z + -1, block5, 0, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 2, block2, 3, 2);
				// BuildHandler.setBlock(world,x+-4, y+3, z+0, block1,0,2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -3, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 0, block5, 0, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -4, block2, 5, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -3, block2, 5, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block4, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block4, 9, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -4, block2, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -3, block2, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block2, 3, 2);
				// BuildHandler.setBlock(world,x+1, y+3, z+3, block1,14,2);
				
				// BuildHandler.buildInfoPoint(world, dir,
				// getUnlocalizedName(),x+-4, y+-3, z+0, rot,x,y,z);
				
				BuildHandler.setBlock(world, x + -4, y + -3, z + 0, Blocks.AIR, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 5);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
			} else if (placementRotation == 1)
			{
				// BuildHandler.setBlock(world,x+-7, y+-3, z+-3, block1,5,2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -6, y + -2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 3, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 4, block2, 4, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -6, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + -1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 2, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 3, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 4, block2, 0, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -1, z + -2, block3, 4, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -5, y + -1, z + -1, block4, 1, 2);
				BuildHandler.setBlock(world, x + -5, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 1, z + -1, block4, 9, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 0, z + -2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+-4, y+0, z+4, block1,4,2);
				BuildHandler.setBlock(world, x + -4, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 0, block3, 4, 2);
				Block block5 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -4, y + 2, z + 1, block5, 0, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 4, block3, 4, 2);
				// BuildHandler.setBlock(world,x+-4, y+3, z+0, block1,14,2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 0, block5, 0, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 3, block2, 5, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 4, block2, 5, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block4, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block4, 9, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 3, block2, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 4, block2, 1, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block2, 3, 2);
				// BuildHandler.setBlock(world,x+1, y+3, z+5, block1,14,2);
				// BuildHandler.buildInfoPoint(world, dir,
				// getUnlocalizedName(),x+-4, y+-3, z+0, rot,x,y,z);
				
				BuildHandler.setBlock(world, x + -4, y + -3, z + 0, Blocks.AIR, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 5);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
			}
		} else if (dir == EnumFacing.EAST)
		{
			if (placementRotation == 2)
			{
				// BuildHandler.setBlock(world,x+-1, y+-3, z+-3, block1,5,2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block2, 6, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 3, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 4, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block3, 4, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block4, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block4, 9, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 3, block2, 0, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 4, block2, 0, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -1, block3, 4, 2);
				Block block5 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 3, y + 2, z + 0, block5, 0, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 0, z + -2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+4, y+0, z+4, block1,4,2);
				BuildHandler.setBlock(world, x + 4, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 1, block5, 0, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 4, block3, 4, 2);
				// BuildHandler.setBlock(world,x+4, y+3, z+0, block1,5,2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -1, z + -1, block4, 1, 2);
				BuildHandler.setBlock(world, x + 5, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 1, z + -1, block4, 9, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + -1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 2, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 3, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 4, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + -1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 0, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 2, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 3, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 4, block2, 1, 2);
				// BuildHandler.setBlock(world,x+7, y+3, z+5, block1,14,2);
				// BuildHandler.buildInfoPoint(world, dir,
				// getUnlocalizedName(),x+4, y+-3, z+0, rot,x,y,z);
				
				BuildHandler.setBlock(world, x + 4, y + -3, z + 0, Blocks.AIR, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 5);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
			} else if (placementRotation == 3)
			{
				// BuildHandler.setBlock(world,x+-1, y+-3, z+-5, block1,5,2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block2, 6, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -4, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -3, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -3, block3, 4, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block4, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block4, 9, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -4, block2, 0, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -3, block2, 0, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 3, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -1, block3, 4, 2);
				Block block5 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 3, y + 2, z + 0, block5, 0, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 4, y + -1, z + 2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+4, y+0, z+-4, block1,4,2);
				BuildHandler.setBlock(world, x + 4, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -1, block5, 0, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 2, block2, 3, 2);
				// BuildHandler.setBlock(world,x+4, y+3, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 5, y + -1, z + 1, block4, 1, 2);
				BuildHandler.setBlock(world, x + 5, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 1, z + 1, block4, 9, 2);
				BuildHandler.setBlock(world, x + 5, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + -4, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + -3, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + -2, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + -1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + -4, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + -3, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + -2, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + -1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 0, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 1, block2, 1, 2);
				// BuildHandler.setBlock(world,x+6, y+3, z+2, block1,14,2);
				// BuildHandler.buildInfoPoint(world, dir,
				// getUnlocalizedName(),x+4, y+-3, z+0, rot,x,y,z);
				
				BuildHandler.setBlock(world, x + 4, y + -3, z + 0, Blocks.AIR, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 5);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
			}
		} else if (dir == EnumFacing.NORTH)
		{
			if (placementRotation == 1)
			{
				// BuildHandler.setBlock(world,x+-3, y+-3, z+-7, block1,5,2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -2, y + -2, z + -5, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -4, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -3, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -2, y + -1, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -5, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -4, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -3, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + -6, block3, 4, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + -1, z + -5, block4, 1, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -5, block4, 9, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -6, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -4, block3, 4, 2);
				Block block5 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 0, y + 2, z + -3, block5, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+3, z+-4, block1,14,2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -4, block5, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block4, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block4, 9, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 3, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 4, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 0, z + -6, block3, 4, 2);
				// BuildHandler.setBlock(world,x+4, y+0, z+-4, block1,4,2);
				BuildHandler.setBlock(world, x + 4, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -2, block2, 3, 2);
				// BuildHandler.setBlock(world,x+5, y+3, z+1, block1,14,2);
				// BuildHandler.buildInfoPoint(world, dir,
				// getUnlocalizedName(),x+0, y+-3, z+-4, rot,x,y,z);
				
				BuildHandler.setBlock(world, x + 0, y + -3, z + -4, Blocks.AIR, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 5);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
			} else if (placementRotation == 2)
			{
				// BuildHandler.setBlock(world,x+-5, y+-3, z+-7, block1,5,2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -4, y + -2, z + -6, block2, 6, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -4, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -4, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 0, z + -6, block3, 4, 2);
				// BuildHandler.setBlock(world,x+-4, y+0, z+-4, block1,4,2);
				BuildHandler.setBlock(world, x + -4, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -2, block2, 3, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -3, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -2, block2, 3, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -6, block3, 4, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block4, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block4, 9, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -5, block3, 4, 2);
				Block block5 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + 2, z + -4, block5, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -6, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -3, block5, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+3, z+-4, block1,5,2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -5, block4, 1, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -5, block4, 9, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -5, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -4, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -3, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -5, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -4, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -3, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
				// BuildHandler.setBlock(world,x+3, y+3, z+1, block1,14,2);
				// BuildHandler.buildInfoPoint(world, dir,
				// getUnlocalizedName(),x+0, y+-3, z+-4, rot,x,y,z);
				
				BuildHandler.setBlock(world, x + 0, y + -3, z + -4, Blocks.AIR, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 5);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
			}
		} else if (dir == EnumFacing.SOUTH)
		{
			if (placementRotation == 0)
			{
				// BuildHandler.setBlock(world,x+-3, y+-3, z+-1, block1,5,2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 3, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 4, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 5, block2, 4, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 3, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 4, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 5, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 6, block2, 7, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + -1, z + 5, block4, 1, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 5, block4, 9, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 6, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block3, 4, 2);
				Block block5 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 0, y + 2, z + 3, block5, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 6, block2, 3, 2);
				// BuildHandler.setBlock(world,x+0, y+3, z+4, block1,0,2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 4, block5, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block4, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block4, 9, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 3, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 4, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 0, z + 2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+4, y+0, z+4, block1,4,2);
				BuildHandler.setBlock(world, x + 4, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 6, block2, 3, 2);
				// BuildHandler.setBlock(world,x+5, y+3, z+7, block1,14,2);
				
				// BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(),
				// x + 0, y + -3, z + 4, rot, x, y, z, lastStr);
				
				BuildHandler.setBlock(world, x + 0, y + -3, z + 4, Blocks.AIR, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 5);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
			} else if (placementRotation == 3)
			{
				// BuildHandler.setBlock(world,x+-4, y+-3, z+0, block1,5,2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -4, y + -2, z + 2, block2, 6, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -4, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + -4, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + -3, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 1, block3, 4, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block4, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block4, 9, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 3, block3, 4, 2);
				Block block5 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + 2, z + 4, block5, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 6, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 3, block5, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 6, block2, 3, 2);
				// BuildHandler.setBlock(world,x+0, y+3, z+4, block1,4,2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 5, block4, 1, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 5, block4, 9, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 3, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 4, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 5, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 3, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 4, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 5, block2, 1, 2);
				// BuildHandler.setBlock(world,x+2, y+3, z+6, block1,14,2);
				
				// BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(),
				// x + 0, y + -3, z + 4, rot, x, y, z, lastStr);
				
				BuildHandler.setBlock(world, x + 0, y + -3, z + 4, Blocks.AIR, 0, 2);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
				pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 5);
				
				BuildHandler.setBlock(world, pos[0], pos[1], pos[2], Blocks.AIR, 0, 2);
				
			}
			
		}
	}
	
	// ------------------------------------------------------------------------
	
	@Override
	public void Build(World world, EnumFacing dir, BlockPos spos)
	{
		int x, y, z;
		x = spos.getX();
		y = spos.getY();
		z = spos.getZ();
		if (dir == EnumFacing.WEST)
		{
			if (placementRotation == 0)
			{
				// Block block1 = id:35;
				// BuildHandler.setBlock(world,x+-7, y+-3, z+-5, block1,5,2);
				Block block2 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + -6, y + -2, z + -4, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + -3, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 1, block2, 4, 2);
				Block block3 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + -6, y + -1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + -4, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + -3, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + -2, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + -1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 2, block2, 7, 2);
				Block block4 = GCBlocks.slabGCHalf;
				BuildHandler.setBlock(world, x + -5, y + -1, z + 1, block4, 1, 2);
				BuildHandler.setBlock(world, x + -5, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 1, z + 1, block4, 9, 2);
				BuildHandler.setBlock(world, x + -5, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -4, y + -1, z + 2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+-4, y+0, z+-4, block1,4,2);
				BuildHandler.setBlock(world, x + -4, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -2, block3, 4, 2);
				Block block5 = Blocks.GLOWSTONE;
				BuildHandler.setBlock(world, x + -4, y + 2, z + -1, block5, 0, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 2, block2, 3, 2);
				// BuildHandler.setBlock(world,x+-4, y+3, z+0, block1,0,2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -3, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 0, block5, 0, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -4, block2, 5, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -3, block2, 5, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block4, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block4, 9, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -4, block2, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -3, block2, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block2, 3, 2);
				// BuildHandler.setBlock(world,x+1, y+3, z+3, block1,14,2);
				
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + -4, y + -3, z + 0, placementRotation, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], placementRotation, x + -4, y + -3, z + 0);
				
			} else if (placementRotation == 1)
			{
				// BuildHandler.setBlock(world,x+-7, y+-3, z+-3, block1,5,2);
				Block block2 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + -6, y + -2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 3, block2, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -2, z + 4, block2, 4, 2);
				Block block3 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + -6, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + -1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 0, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + -1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 2, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 3, block2, 0, 2);
				BuildHandler.setBlock(world, x + -6, y + 2, z + 4, block2, 0, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + -1, z + -2, block3, 4, 2);
				Block block4 = GCBlocks.slabGCHalf;
				BuildHandler.setBlock(world, x + -5, y + -1, z + -1, block4, 1, 2);
				BuildHandler.setBlock(world, x + -5, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 1, z + -1, block4, 9, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -5, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 0, z + -2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+-4, y+0, z+4, block1,4,2);
				BuildHandler.setBlock(world, x + -4, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 0, block3, 4, 2);
				Block block5 = Blocks.GLOWSTONE;
				BuildHandler.setBlock(world, x + -4, y + 2, z + 1, block5, 0, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 4, block3, 4, 2);
				// BuildHandler.setBlock(world,x+-4, y+3, z+0, block1,14,2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 0, block5, 0, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 3, block2, 5, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 4, block2, 5, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block4, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block4, 9, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 3, block2, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 4, block2, 1, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block2, 3, 2);
				// BuildHandler.setBlock(world,x+1, y+3, z+5, block1,14,2);
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + -4, y + -3, z + 0, placementRotation, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], placementRotation, x - 4, y - 3, z + 0);
				
			}
		} else if (dir == EnumFacing.EAST)
		{
			if (placementRotation == 2)
			{
				// BuildHandler.setBlock(world,x+-1, y+-3, z+-3, block1,5,2);
				Block block2 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block2, 6, 2);
				Block block3 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 3, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 4, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block3, 4, 2);
				Block block4 = GCBlocks.slabGCHalf;
				BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block4, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block4, 9, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 3, block2, 0, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 4, block2, 0, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -1, block3, 4, 2);
				Block block5 = Blocks.GLOWSTONE;
				BuildHandler.setBlock(world, x + 3, y + 2, z + 0, block5, 0, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 0, z + -2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+4, y+0, z+4, block1,4,2);
				BuildHandler.setBlock(world, x + 4, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 1, block5, 0, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 4, block3, 4, 2);
				// BuildHandler.setBlock(world,x+4, y+3, z+0, block1,5,2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -1, z + -1, block4, 1, 2);
				BuildHandler.setBlock(world, x + 5, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 1, z + -1, block4, 9, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + -1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 2, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 3, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 4, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + -1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 0, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 2, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 3, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 4, block2, 1, 2);
				// BuildHandler.setBlock(world,x+7, y+3, z+5, block1,14,2);
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 4, y + -3, z + 0, placementRotation, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], placementRotation, x + 4, y - 3, z + 0);
				
			} else if (placementRotation == 3)
			{
				// BuildHandler.setBlock(world,x+-1, y+-3, z+-5, block1,5,2);
				Block block2 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block2, 6, 2);
				Block block3 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -4, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -3, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -3, block3, 4, 2);
				Block block4 = GCBlocks.slabGCHalf;
				BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block4, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block4, 9, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -4, block2, 0, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -3, block2, 0, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 3, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -1, block3, 4, 2);
				Block block5 = Blocks.GLOWSTONE;
				BuildHandler.setBlock(world, x + 3, y + 2, z + 0, block5, 0, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 4, y + -1, z + 2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+4, y+0, z+-4, block1,4,2);
				BuildHandler.setBlock(world, x + 4, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -1, block5, 0, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 2, block2, 3, 2);
				// BuildHandler.setBlock(world,x+4, y+3, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 5, y + -1, z + 1, block4, 1, 2);
				BuildHandler.setBlock(world, x + 5, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 1, z + 1, block4, 9, 2);
				BuildHandler.setBlock(world, x + 5, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 5, y + 2, z + 2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + -4, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + -3, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + -2, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + -1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -2, z + 1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + -4, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + -3, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + -2, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + -1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 0, block2, 1, 2);
				BuildHandler.setBlock(world, x + 6, y + 2, z + 1, block2, 1, 2);
				// BuildHandler.setBlock(world,x+6, y+3, z+2, block1,14,2);
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 4, y + -3, z + 0, placementRotation, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], placementRotation, x + 4, y - 3, z + 0);
				
			}
		} else if (dir == EnumFacing.NORTH)
		{
			if (placementRotation == 1)
			{
				// BuildHandler.setBlock(world,x+-3, y+-3, z+-7, block1,5,2);
				Block block2 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + -2, y + -2, z + -5, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -4, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -3, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
				Block block3 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + -2, y + -1, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -5, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -4, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -3, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + -6, block3, 4, 2);
				Block block4 = GCBlocks.slabGCHalf;
				BuildHandler.setBlock(world, x + -1, y + -1, z + -5, block4, 1, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -5, block4, 9, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -6, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -4, block3, 4, 2);
				Block block5 = Blocks.GLOWSTONE;
				BuildHandler.setBlock(world, x + 0, y + 2, z + -3, block5, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+3, z+-4, block1,14,2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -4, block5, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block4, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block4, 9, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + -2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 3, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + -2, block2, 3, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + -2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 4, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 0, z + -6, block3, 4, 2);
				// BuildHandler.setBlock(world,x+4, y+0, z+-4, block1,4,2);
				BuildHandler.setBlock(world, x + 4, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + -2, block2, 3, 2);
				// BuildHandler.setBlock(world,x+5, y+3, z+1, block1,14,2);
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 0, y + -3, z + -4, placementRotation, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], placementRotation, x + 0, y - 3, z - 4);
				
			} else if (placementRotation == 2)
			{
				// BuildHandler.setBlock(world,x+-5, y+-3, z+-7, block1,5,2);
				Block block2 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + -4, y + -2, z + -6, block2, 6, 2);
				Block block3 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + -4, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + -2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -4, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 0, z + -6, block3, 4, 2);
				// BuildHandler.setBlock(world,x+-4, y+0, z+-4, block1,4,2);
				BuildHandler.setBlock(world, x + -4, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + -2, block2, 3, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + -2, block2, 7, 2);
				BuildHandler.setBlock(world, x + -3, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + -2, block2, 3, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -6, block3, 4, 2);
				Block block4 = GCBlocks.slabGCHalf;
				BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block4, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block4, 9, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -5, block3, 4, 2);
				Block block5 = Blocks.GLOWSTONE;
				BuildHandler.setBlock(world, x + -1, y + 2, z + -4, block5, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -6, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -3, block5, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+3, z+-4, block1,5,2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -6, block2, 6, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -5, block4, 1, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -5, block4, 9, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -6, block2, 2, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -5, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -4, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -3, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -5, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -4, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -3, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
				// BuildHandler.setBlock(world,x+3, y+3, z+1, block1,14,2);
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 0, y + -3, z + -4, placementRotation, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], placementRotation, x + 0, y - 3, z - 4);
				
			}
		} else if (dir == EnumFacing.SOUTH)
		{
			if (placementRotation == 0)
			{
				// BuildHandler.setBlock(world,x+-3, y+-3, z+-1, block1,5,2);
				Block block2 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 3, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 4, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 5, block2, 4, 2);
				Block block3 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 3, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 4, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 5, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 6, block2, 7, 2);
				Block block4 = GCBlocks.slabGCHalf;
				BuildHandler.setBlock(world, x + -1, y + -1, z + 5, block4, 1, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 5, block4, 9, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 6, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block3, 4, 2);
				Block block5 = Blocks.GLOWSTONE;
				BuildHandler.setBlock(world, x + 0, y + 2, z + 3, block5, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 6, block2, 3, 2);
				// BuildHandler.setBlock(world,x+0, y+3, z+4, block1,0,2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 4, block5, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block4, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block4, 9, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 3, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 3, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 2, block2, 6, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 4, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 0, z + 2, block3, 4, 2);
				// BuildHandler.setBlock(world,x+4, y+0, z+4, block1,4,2);
				BuildHandler.setBlock(world, x + 4, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 4, y + 2, z + 6, block2, 3, 2);
				// BuildHandler.setBlock(world,x+5, y+3, z+7, block1,14,2);
				
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 0, y + -3, z + 4, placementRotation, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], placementRotation, x + 0, y - 3, z + 4);
				
			} else if (placementRotation == 3)
			{
				// BuildHandler.setBlock(world,x+-4, y+-3, z+0, block1,5,2);
				Block block2 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + -4, y + -2, z + 2, block2, 6, 2);
				Block block3 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + -4, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + -4, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -4, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 2, block2, 6, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + -3, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 2, block2, 2, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -3, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 1, block3, 4, 2);
				Block block4 = GCBlocks.slabGCHalf;
				BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block4, 1, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block4, 9, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 3, block3, 4, 2);
				Block block5 = Blocks.GLOWSTONE;
				BuildHandler.setBlock(world, x + -1, y + 2, z + 4, block5, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 6, block3, 4, 2);
				// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 3, block5, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 6, block2, 3, 2);
				// BuildHandler.setBlock(world,x+0, y+3, z+4, block1,4,2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 6, block2, 7, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 5, block4, 1, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 5, block4, 9, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 6, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 6, block2, 3, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 3, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 4, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 5, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 3, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 4, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 5, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 3, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 4, block2, 1, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 5, block2, 1, 2);
				// BuildHandler.setBlock(world,x+2, y+3, z+6, block1,14,2);
				
				BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 0, y + -3, z + 4, placementRotation, x, y, z);
				
				int[] pos = new int[] { x, y, z };
				pos = FacingUtils.IncreaseByDirO(dir, pos, 4);
				pos = FacingUtils.IncreaseByDirO(onTurn(dir, placementRotation).getOpposite(), pos, 1);
				
				BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), pos[0], pos[1], pos[2], placementRotation, x + 0, y - 3, z + 4);
				
			}
		}
		
	}
	
	@Override
	public List<AxisAlignedBB> getBoundingBox(EnumFacing dir, BlockPos pos)
	{
		ArrayList<AxisAlignedBB> list = new ArrayList<>();
		list.add(createBoundingBox(dir, pos, new int[] { 5, 5, 5, 2, -2, 2 }));
		return list;
	}
	
	@Override
	public boolean Check(World world, EnumFacing dir, BlockPos pos, int meta)
	{
		if (meta != EnumBlockPointStates.EVERYTHING.getMeta() && meta != EnumBlockPointStates.UNKNOWN)
		{
			return false;
		}
		if (dir == EnumFacing.WEST || dir == EnumFacing.EAST || dir == EnumFacing.NORTH || dir == EnumFacing.SOUTH) return true;
		else return false;
	}
	
	@Override
	public void ClearWay(World world, EnumFacing dir, BlockPos spos)
	{
		
		int x, y, z;
		x = spos.getX();
		y = spos.getY();
		z = spos.getZ();
		Block block1 = Blocks.AIR;
		if (dir == EnumFacing.WEST)
		{
			
			BuildHandler.setBlock(world, x + -2, y + -1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block1, 0, 2);
			
		} else if (dir == EnumFacing.EAST)
		{
			BuildHandler.setBlock(world, x + 0, y + -1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 1, block1, 0, 2);
			
		} else if (dir == EnumFacing.SOUTH)
		{
			BuildHandler.setBlock(world, x + -1, y + -1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 2, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 2, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 2, block1, 0, 2);
			
		} else if (dir == EnumFacing.NORTH)
		{
			BuildHandler.setBlock(world, x + -1, y + -1, z + -2, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + -2, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + -2, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + -2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + -2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block1, 0, 2);
			
		}
	}
	
	@Override
	public boolean isHidden()
	{
		return hiddenS;
	}
	
	@Override
	public String getName()
	{
		return I18n.format("builder.corner.name");
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "corner";
	}
	
	@Override
	public boolean isPossible(EnumFacing dir, int rot, int meta)
	{
		if ((rot == 0 || rot == 1) && dir == EnumFacing.WEST)
		{
			return true;
		} else if ((rot == 2 || rot == 3) && dir == EnumFacing.EAST)
		{
			return true;
		}
		if ((rot == 1 || rot == 2) && dir == EnumFacing.NORTH)
		{
			return true;
		}
		if ((rot == 0 || rot == 3) && dir == EnumFacing.SOUTH)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public NonNullList<OreDictItemStack> getRequiredItems()
	{
		NonNullList<OreDictItemStack> items = NonNullList.create();
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 38, 7), "plateTin"));
		items.add(new OreDictItemStack(new ItemStack(Items.GLOWSTONE_DUST, 8)));
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 1, 13)));
		items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 38, ItemMod.scaffold_meta)));
		
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.mainConnect = 1;
		data.addConnect = 0;
		return data;
	}
	
}

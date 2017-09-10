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
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.utils.OreDictItemStack;

public class StructureHall extends Structure {
	
	private boolean hiddenS;
	
	public StructureHall(boolean hidden)
	{
		super(hidden);
		this.hiddenS = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureHall Nstr = new StructureHall(hiddenS);
		if (this.placementPos == null) placementPos = BlockPos.ORIGIN;
		Nstr.Configure(new BlockPos(placementPos), placementRotation, placementDir);
		return Nstr;
	}
	
	public int getMetaFromDir(EnumFacing dir)
	{
		if (dir == EnumFacing.WEST)
		{
			return 0;
		} else if (dir == EnumFacing.EAST)
		{
			return 2;
		} else if (dir == EnumFacing.SOUTH)
		{
			return 3;
		} else if (dir == EnumFacing.NORTH)
		{
			return 1;
		} else
		{
			return 2;
		}
	}
	
	@Override
	public void deconstruct(World world, EnumFacing dir, BlockPos pos)
	{
		int x, y, z;
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		
		if (dir == EnumFacing.WEST)
		{
			x = x + 1;
			
			Block block3 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -9, y + -2, z + -2, block3, 6, 2);
			Block block4 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -9, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -9, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -9, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -8, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -7, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -6, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -6, y + 2, z + -1, block4, 4, 2);
			Block block5 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -6, y + 2, z + 0, block5, 0, 2);
			BuildHandler.setBlock(world, x + -6, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -5, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + -1, z + 2, block4, 4, 2);
			Block block6 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -5, y + 0, z + -2, block6, 0, 2);
			BuildHandler.setBlock(world, x + -5, y + 0, z + 2, block6, 0, 2);
			BuildHandler.setBlock(world, x + -5, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + 0, block6, 0, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -4, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + 0, block5, 0, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -3, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block3, 3, 2);
			
			BuildHandler.setBlock(world, x - 3, y, z - 1, Blocks.AIR, 0, 2);
			BuildHandler.setBlock(world, x - 7, y, z + 1, Blocks.AIR, 0, 2);
			
			BuildHandler.setBlock(world, x - 5, y - 3, z, Blocks.AIR, 0, 2);
			
			// BuildHandler.buildRemoveInfoPoint(world, dir,
			// getUnlocalizedName(), x-3, y, z-1, 0, x-5, y-3, z);
			
			// BuildHandler.buildInfoPoint(world, dir,
			// getUnlocalizedName(),x-5,
			// y-3, z, 0,x-1,y,z);
			
		} else if (dir == EnumFacing.EAST)
		{
			x = x - 1;
			// Block block1 = id:35;
			// BuildHandler.setBlock(world,x+0, y+-3, z+-3, block1,5,2);
			// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
			Block block2 = Blocks.AIR;
			BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block2, 6, 2);
			Block block3 = Blocks.AIR;
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
			BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 3, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 4, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + -1, block3, 4, 2);
			Block block4 = Blocks.AIR;
			BuildHandler.setBlock(world, x + 4, y + 2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 5, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + -1, z + 2, block3, 4, 2);
			Block block5 = Blocks.AIR;
			BuildHandler.setBlock(world, x + 5, y + 0, z + -2, block5, 0, 2);
			BuildHandler.setBlock(world, x + 5, y + 0, z + 2, block5, 0, 2);
			BuildHandler.setBlock(world, x + 5, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + 0, block5, 0, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 6, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 7, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 8, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 9, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 9, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 9, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 9, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 2, z + 2, block2, 3, 2);
			
			// BuildHandler.setBlock(world,x+3, y, z+1,
			// BlockContainerMod.BlockRemoveInfo, 2,
			// 2);
			BuildHandler.setBlock(world, x + 3, y, z + 1, Blocks.AIR, 0, 2);
			BuildHandler.setBlock(world, x + 7, y, z - 1, Blocks.AIR, 0, 2);
			BuildHandler.setBlock(world, x + 5, y - 3, z, Blocks.AIR, 0, 2);
			
			// BuildHandler.buildRemoveInfoPoint(world, dir,
			// getUnlocalizedName(), x+3, y, z+1, 0, x+5, y-3, z);
			
			// BuildHandler.buildInfoPoint(world, dir,
			// getUnlocalizedName(),x+5,
			// y-3, z, 0,x+1,y,z);
			
		} else if (dir == EnumFacing.SOUTH)
		{
			
			Block block2 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 6, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 8, block2, 4, 2);
			Block block3 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 3, block3, 4, 2);
			Block block4 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -2, y + 0, z + 4, block4, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 3, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 4, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 5, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 6, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 7, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 8, block2, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block3, 4, 2);
			Block block5 = Blocks.AIR;
			BuildHandler.setBlock(world, x + 0, y + 2, z + 3, block5, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 4, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 5, block5, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 3, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 4, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 5, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 6, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 7, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 8, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 4, block4, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 3, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 4, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 5, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 6, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 7, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 8, block2, 1, 2);
			// BuildHandler.setBlock(world,x+3, y+3, z+9, block1,14,2);
			
			// BuildHandler.setBlock(world,x-1, y, z+2,
			// BlockContainerMod.BlockRemoveInfo, 3,
			// 2);
			BuildHandler.setBlock(world, x - 1, y, z + 2, Blocks.AIR, 0, 2);
			BuildHandler.setBlock(world, x + 1, y, z + 6, Blocks.AIR, 0, 2);
			BuildHandler.setBlock(world, x, y - 3, z + 4, Blocks.AIR, 0, 2);
			
			// BuildHandler.buildRemoveInfoPoint(world, dir,
			// getUnlocalizedName(), x-1, y, z+2, 0, x, y-3, z+4);
			
			// BuildHandler.buildInfoPoint(world, dir,
			// getUnlocalizedName(),x,
			// y-3, z+4, 0,x,y,z);
			
		} else if (dir == EnumFacing.NORTH)
		{
			
			// BuildHandler.setBlock(world,x+-3, y+-3, z+-9, block1,5,2);
			Block block2 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -2, y + -2, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -6, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
			Block block3 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -2, y + -1, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -5, block3, 4, 2);
			Block block4 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -2, y + 0, z + -4, block4, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -8, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -7, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -6, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -5, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -4, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -3, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
			// BuildHandler.setBlock(world,x+0, y+0, z+-8, block1,4,2);
			// z BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -6, block3, 4, 2);
			Block block5 = Blocks.AIR;
			BuildHandler.setBlock(world, x + 0, y + 2, z + -5, block5, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -4, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -3, block5, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -8, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -7, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -6, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -5, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -4, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -3, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -4, block4, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -8, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -7, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -6, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -5, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -4, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -3, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
			// BuildHandler.setBlock(world,x+3, y+3, z+1, block1,14,2);
			
			// BuildHandler.setBlock(world,x+1, y, z-2,
			// BlockContainerMod.BlockRemoveInfo, 1,
			// 2);
			BuildHandler.setBlock(world, x + 1, y, z - 2, Blocks.AIR, 0, 2);
			BuildHandler.setBlock(world, x - 1, y, z - 6, Blocks.AIR, 0, 2);
			BuildHandler.setBlock(world, x, y - 3, z - 4, Blocks.AIR, 0, 2);
			
			// BuildHandler.buildRemoveInfoPoint(world, dir,
			// getUnlocalizedName(), x+1, y, z-2, 0, x, y-3, z-4);
			
			// BuildHandler.buildInfoPoint(world, dir,
			// getUnlocalizedName(),x,
			// y-3, z-4, 0,x,y,z);
			
		}
	}
	
	@Override
	public void Build(World world, EnumFacing dir, BlockPos pos)
	{
		// GLoger.logInfo("Start Building");
		// GLoger.logInfo("Forge direction: "+dir.name());
		// GLoger.logInfo(x+" "+y+" "+z);
		
		int x, y, z;
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		
		if (dir == EnumFacing.WEST)
		{
			x = x + 1;
			
			Block block3 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + -9, y + -2, z + -2, block3, 6, 2);
			Block block4 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + -9, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -9, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -9, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -9, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -8, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -7, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -6, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -6, y + 2, z + -1, block4, 4, 2);
			Block block5 = Blocks.GLOWSTONE;
			BuildHandler.setBlock(world, x + -6, y + 2, z + 0, block5, 0, 2);
			BuildHandler.setBlock(world, x + -6, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -6, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -5, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + -1, z + 2, block4, 4, 2);
			
			BuildHandler.buildBuildPoint(world, x - 5, y, z - 2, 3);
			BuildHandler.buildBuildPoint(world, x - 5, y, z + 2, 3);
			
			BuildHandler.buildBuildPoint(world, x - 5, y + 2, z, 4);
			
			BuildHandler.setBlock(world, x + -5, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -4, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + 0, block5, 0, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -3, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + -2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 2, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block4, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block3, 3, 2);
			
			// BuildHandler.setBlock(world,x-3, y, z-1,
			// BlockContainerMod.BlockRemoveInfo, 0,
			// 2);
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x - 5, y - 3, z, 0, x - 1, y, z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x - 3, y, z - 1, 0, x - 5, y - 3, z);
			
		} else if (dir == EnumFacing.EAST)
		{
			x = x - 1;
			// Block block1 = id:35;
			// BuildHandler.setBlock(world,x+0, y+-3, z+-3, block1,5,2);
			// BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
			Block block2 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block2, 6, 2);
			Block block3 = GCBlocks.basicBlock;
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
			BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 3, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 4, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + -1, block3, 4, 2);
			Block block4 = Blocks.GLOWSTONE;
			BuildHandler.setBlock(world, x + 4, y + 2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 5, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + -1, z + 2, block3, 4, 2);
			
			BuildHandler.buildBuildPoint(world, x + 5, y, z - 2, 3);
			BuildHandler.buildBuildPoint(world, x + 5, y, z + 2, 3);
			
			BuildHandler.buildBuildPoint(world, x + 5, y + 2, z, 4);
			
			BuildHandler.setBlock(world, x + 5, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 6, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 7, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 8, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + 2, block2, 3, 2);
			BuildHandler.setBlock(world, x + 9, y + -2, z + -2, block2, 6, 2);
			BuildHandler.setBlock(world, x + 9, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + -2, z + 2, block2, 7, 2);
			BuildHandler.setBlock(world, x + 9, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 2, z + -2, block2, 2, 2);
			BuildHandler.setBlock(world, x + 9, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 9, y + 2, z + 2, block2, 3, 2);
			
			// BuildHandler.setBlock(world,x+3, y, z+1,
			// BlockContainerMod.BlockRemoveInfo, 2,
			// 2);
			
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 5, y - 3, z, 0, x + 1, y, z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x + 3, y, z + 1, 0, x + 5, y - 3, z);
			
		} else if (dir == EnumFacing.SOUTH)
		{
			
			Block block2 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 6, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 8, block2, 4, 2);
			Block block3 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 3, block3, 4, 2);
			
			BuildHandler.buildBuildPoint(world, x - 2, y, z + 4, 3);
			BuildHandler.buildBuildPoint(world, x + 2, y, z + 4, 3);
			
			BuildHandler.buildBuildPoint(world, x, y + 2, z + 4, 4);
			
			BuildHandler.setBlock(world, x + -2, y + 0, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 3, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 4, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 5, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 6, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 7, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 8, block2, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block3, 4, 2);
			Block block5 = Blocks.GLOWSTONE;
			BuildHandler.setBlock(world, x + 0, y + 2, z + 3, block5, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 5, block5, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 3, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 4, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 5, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 6, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 7, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 8, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 3, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 4, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 5, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 6, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 7, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 8, block2, 1, 2);
			// BuildHandler.setBlock(world,x+3, y+3, z+9, block1,14,2);
			
			// BuildHandler.setBlock(world,x-1, y, z+2,
			// BlockContainerMod.BlockRemoveInfo, 3,
			// 2);
			
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x, y - 3, z + 4, 0, x, y, z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x - 1, y, z + 2, 0, x, y - 3, z + 4);
			
		} else if (dir == EnumFacing.NORTH)
		{
			
			// BuildHandler.setBlock(world,x+-3, y+-3, z+-9, block1,5,2);
			Block block2 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + -2, y + -2, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -6, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
			Block block3 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + -2, y + -1, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -5, block3, 4, 2);
			
			BuildHandler.buildBuildPoint(world, x - 2, y, z - 4, 3);
			BuildHandler.buildBuildPoint(world, x + 2, y, z - 4, 3);
			
			BuildHandler.buildBuildPoint(world, x, y + 2, z - 4, 4);
			
			BuildHandler.setBlock(world, x + -2, y + 0, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -8, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -7, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -6, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -5, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -4, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -3, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block2, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
			// BuildHandler.setBlock(world,x+0, y+0, z+-8, block1,4,2);
			// z BuildHandler.setBlock(world,x+0, y+0, z+0, block1,4,2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -6, block3, 4, 2);
			Block block5 = Blocks.GLOWSTONE;
			BuildHandler.setBlock(world, x + 0, y + 2, z + -5, block5, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -3, block5, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -8, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -7, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -6, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -5, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -4, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -3, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -8, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -7, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -6, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -5, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -4, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -3, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -8, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -7, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -6, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -5, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -4, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -3, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block2, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
			// BuildHandler.setBlock(world,x+3, y+3, z+1, block1,14,2);
			
			// BuildHandler.setBlock(world,x+1, y, z-2,
			// BlockContainerMod.BlockRemoveInfo, 1,
			// 2);
			
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x, y - 3, z - 4, 0, x, y, z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x + 1, y, z - 2, 0, x, y - 3, z - 4);
			
		}
		
	}
	
	@Override
	public void ClearWay(World world, EnumFacing dir, BlockPos pos)
	{
		Block AIR = Blocks.AIR;
		
		int x, y, z;
		x = pos.getX();
		y = pos.getY();
		z = pos.getZ();
		
		if (dir == EnumFacing.WEST)
		{
			BuildHandler.setBlock(world, x, y + 1, z + 1, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y + 1, z - 1, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y - 1, z + 1, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y - 1, z - 1, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y + 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y - 1, z, AIR, 0, 2);
			int nx = x - 1;
			BuildHandler.setBlock(world, nx, y, z, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y, z + 1, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y, z - 1, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y + 1, z + 1, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y + 1, z - 1, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y - 1, z + 1, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y - 1, z - 1, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y + 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y - 1, z, AIR, 0, 2);
		} else if (dir == EnumFacing.EAST)
		{
			BuildHandler.setBlock(world, x, y + 1, z + 1, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y + 1, z - 1, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y - 1, z + 1, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y - 1, z - 1, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y + 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y - 1, z, AIR, 0, 2);
			int nx = x + 1;
			BuildHandler.setBlock(world, nx, y, z, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y, z + 1, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y, z - 1, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y + 1, z + 1, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y + 1, z - 1, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y - 1, z + 1, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y - 1, z - 1, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y + 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, nx, y - 1, z, AIR, 0, 2);
		} else if (dir == EnumFacing.SOUTH)
		{
			BuildHandler.setBlock(world, x + 1, y + 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, x + 1, y - 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, x - 1, y - 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y + 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y - 1, z, AIR, 0, 2);
			int nz = z + 1;
			BuildHandler.setBlock(world, x, y, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x + 1, y, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x - 1, y, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 1, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x + 1, y - 1, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x - 1, y - 1, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y + 1, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y - 1, nz, AIR, 0, 2);
		} else if (dir == EnumFacing.NORTH)
		{
			BuildHandler.setBlock(world, x + 1, y + 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, x + 1, y - 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, x - 1, y - 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y + 1, z, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y - 1, z, AIR, 0, 2);
			int nz = z - 1;
			BuildHandler.setBlock(world, x, y, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x + 1, y, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x - 1, y, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 1, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x + 1, y - 1, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x - 1, y - 1, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y + 1, nz, AIR, 0, 2);
			BuildHandler.setBlock(world, x, y - 1, nz, AIR, 0, 2);
		}
		
	}
	
	@Override
	public boolean Check(World world, EnumFacing dir, BlockPos pos, int meta)
	{
		if (meta != 0 && meta != 1 && meta != -1)
		{
			return false;
		}
		if (dir == EnumFacing.WEST || dir == EnumFacing.EAST || dir == EnumFacing.NORTH || dir == EnumFacing.SOUTH) return true;
		else return false;
	}
	
	@Override
	public String getName()
	{
		return I18n.format("builder.hall.name");
	}
	
	@Override
	public boolean isHidden()
	{
		return hiddenS;
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "hall";
	}
	
	@Override
	public List<OreDictItemStack> getRequiredItems()
	{
		List<OreDictItemStack> items = new ArrayList();
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 54, 7), "plateTin"));
		items.add(new OreDictItemStack(new ItemStack(Items.GLOWSTONE_DUST, 8)));
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 4, 13)));
		items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 32, ItemMod.scaffold_meta)));
		
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.mainConnect = 1;
		data.addConnect = 3;
		return data;
	}
	
}

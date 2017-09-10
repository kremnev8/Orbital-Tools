package net.orbitallabs.structures;

import java.util.ArrayList;
import java.util.List;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.utils.OreDictItemStack;

public class StructureWindow extends StructureRotatable {
	
	private boolean hidden;
	
	/**
	 * 0 - normal,1 - on end
	 */
	public StructureWindow(boolean hidden)
	{
		super(hidden);
		this.hidden = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureWindow Nstr = new StructureWindow(hidden);
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
		if (placementRotation == 0)
		{
			if (dir == EnumFacing.WEST || dir == EnumFacing.EAST)
			{
				if (dir == EnumFacing.EAST) x = x + 1;
				if (dir == EnumFacing.WEST) x = x - 1;
				// w orld.setBlock(x+0, y+-3, z+-3, block1,5,2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block2, 6, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block2, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 4, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 0, y + -1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block2, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block2, 3, 2);
				// BuildHandler.setBlock(world,x+0, y+3, z+3, block1,14,2);
				
			} else if (dir == EnumFacing.SOUTH || dir == EnumFacing.NORTH)
			{
				if (dir == EnumFacing.NORTH) z = z - 1;
				if (dir == EnumFacing.SOUTH) z = z + 1;
				// BuildHandler.setBlock(world,x+-3, y+-3, z+0, block1,5,2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block2, 4, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block3, 4, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + -1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block2, 5, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 1, 2);
				// BuildHandler.setBlock(world,x+3, y+3, z+0, block1,14,2);
				
			}
		} else
		{
			if (dir == EnumFacing.EAST)
			{
				Block block1 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block1, 8, 2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block1, 8, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block1, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block1, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block1, 8, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 5, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 5, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 5, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block1, 8, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -2, block2, 4, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + 1, y + -1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block1, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 1, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 1, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 1, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block1, 0, 2);
				
			} else if (dir == EnumFacing.WEST)
			{
				Block block1 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block1, 8, 2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block1, 8, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + -1, z + -2, block3, 4, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + -1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block1, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block1, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block1, 8, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block1, 8, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block1, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block1, 0, 2);
				
			} else if (dir == EnumFacing.SOUTH)
			{
				Block block1 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block1, 8, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block1, 8, 2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block1, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block1, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block2, 4, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block3, 7, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + -1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block3, 3, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 3, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 7, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 3, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block1, 8, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block1, 8, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 0, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block1, 0, 2);
				
			} else if (dir == EnumFacing.NORTH)
			{
				Block block1 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block1, 8, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block1, 8, 2);
				Block block2 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -2, y + -1, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block1, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block1, 0, 2);
				Block block3 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block3, 6, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block2, 4, 2);
				Block block4 = Blocks.AIR;
				BuildHandler.setBlock(world, x + -1, y + -1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block3, 2, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 6, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 6, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 2, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block1, 8, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block1, 8, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block1, 0, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 0, 2);
				
			}
		}
	}
	
	@Override
	public void Build(World world, EnumFacing dir, BlockPos spos)
	{
		int x, y, z;
		x = spos.getX();
		y = spos.getY();
		z = spos.getZ();
		if (placementRotation == 0)
		{
			if (dir == EnumFacing.WEST || dir == EnumFacing.EAST)
			{
				if (dir == EnumFacing.EAST) x = x + 1;
				if (dir == EnumFacing.WEST) x = x - 1;
				Block block1 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + 0, y - 2, z - 2, block1, 6, 2);
				Block block2 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + 0, y - 2, z - 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y - 2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y - 2, z + 2, block1, 7, 2);
				BuildHandler.setBlock(world, x + 0, y - 1, z - 2, block2, 4, 2);
				Block block3 = Blocks.GLASS;
				BuildHandler.setBlock(world, x + 0, y - 1, z - 1, block3, 0, 2);
				BuildHandler.setBlock(world, x + 0, y - 1, z + 0, block3, 0, 2);
				BuildHandler.setBlock(world, x + 0, y - 1, z + 1, block3, 0, 2);
				BuildHandler.setBlock(world, x + 0, y - 1, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z - 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z - 1, block3, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 0, block3, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 1, block3, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z - 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z - 1, block3, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block3, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block3, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z - 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z - 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block2, 4, 2);
				
			} else if (dir == EnumFacing.SOUTH || dir == EnumFacing.NORTH)
			{
				if (dir == EnumFacing.NORTH) z = z - 1;
				if (dir == EnumFacing.SOUTH) z = z + 1;
				
				Block block1 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x - 2, y - 2, z + 0, block1, 4, 2);
				Block block2 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x - 2, y - 1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x - 2, y + 0, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x - 2, y + 1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x - 2, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x - 1, y - 2, z + 0, block2, 4, 2);
				Block block3 = Blocks.GLASS;
				BuildHandler.setBlock(world, x - 1, y - 1, z + 0, block3, 0, 2);
				BuildHandler.setBlock(world, x - 1, y + 0, z + 0, block3, 0, 2);
				BuildHandler.setBlock(world, x - 1, y + 1, z + 0, block3, 0, 2);
				BuildHandler.setBlock(world, x - 1, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y - 1, z + 0, block3, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 0, block3, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block3, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y - 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y - 1, z + 0, block3, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 0, block3, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block3, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y - 2, z + 0, block1, 5, 2);
				BuildHandler.setBlock(world, x + 2, y - 1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block2, 4, 2);
				
			}
		} else
		{
			if (dir == EnumFacing.EAST)
			{
				Block block1 = GCBlocks.slabGCHalf;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block1, 8, 2);
				Block block2 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block1, 8, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block1, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block1, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block1, 8, 2);
				Block block3 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 5, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block3, 5, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 5, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block1, 8, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -2, block2, 4, 2);
				Block block4 = Blocks.GLASS;
				BuildHandler.setBlock(world, x + 1, y + -1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 2, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block1, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 1, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 1, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 1, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block1, 0, 2);
				
			} else if (dir == EnumFacing.WEST)
			{
				Block block1 = GCBlocks.slabGCHalf;
				BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block1, 8, 2);
				Block block2 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block1, 8, 2);
				Block block3 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + -1, y + -1, z + -2, block3, 4, 2);
				Block block4 = Blocks.GLASS;
				BuildHandler.setBlock(world, x + -1, y + -1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 0, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block1, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block2, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block1, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block1, 8, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block1, 8, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block1, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block1, 0, 2);
				
			} else if (dir == EnumFacing.SOUTH)
			{
				Block block1 = GCBlocks.slabGCHalf;
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block1, 8, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block1, 8, 2);
				Block block2 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block1, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block1, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block2, 4, 2);
				Block block3 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block3, 7, 2);
				Block block4 = Blocks.GLASS;
				BuildHandler.setBlock(world, x + -1, y + -1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block3, 3, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block3, 7, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block3, 3, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block3, 7, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + 1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block3, 3, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block1, 8, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block1, 8, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 0, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block1, 0, 2);
				
			} else if (dir == EnumFacing.NORTH)
			{
				Block block1 = GCBlocks.slabGCHalf;
				BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block1, 8, 2);
				BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block1, 8, 2);
				Block block2 = GCBlocks.basicBlock;
				BuildHandler.setBlock(world, x + -2, y + -1, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block1, 0, 2);
				BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block1, 0, 2);
				Block block3 = GCBlocks.tinStairs2;
				BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block3, 6, 2);
				BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block2, 4, 2);
				Block block4 = Blocks.GLASS;
				BuildHandler.setBlock(world, x + -1, y + -1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 0, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block3, 2, 2);
				BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block3, 6, 2);
				BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 0, y + -1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 0, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block3, 2, 2);
				BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block3, 6, 2);
				BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 1, y + -1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 0, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 1, z + -1, block4, 0, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block3, 2, 2);
				BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block1, 8, 2);
				BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block1, 8, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + -1, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block1, 0, 2);
				BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 0, 2);
				
			}
		}
	}
	
	@Override
	public boolean Check(World world, EnumFacing dir, BlockPos spos, int meta)
	{
		if (meta != 0 && meta != 1 && meta != 2 && meta != 3 && meta != -1)
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
		if (dir == EnumFacing.NORTH)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x - 2, y - 2, z + 0, block1, 6, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x - 2, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 2, z + 0, block1, 2, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 0, block1, 6, 2);
			BuildHandler.setBlock(world, x - 1, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 2, z + 0, block1, 2, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block1, 6, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 0, block2, 4, 2);
			
			BuildHandler.buildBuildPoint(world, x, y, z, 3);
			
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block1, 2, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 0, block1, 6, 2);
			BuildHandler.setBlock(world, x + 1, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block1, 2, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z + 0, block1, 6, 2);
			BuildHandler.setBlock(world, x + 2, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 2, 2);
			
		} else if (dir == EnumFacing.SOUTH)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x - 2, y - 2, z + 0, block1, 7, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x - 2, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 2, z + 0, block1, 3, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 0, block1, 7, 2);
			BuildHandler.setBlock(world, x - 1, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y + 2, z + 0, block1, 3, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block1, 7, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 0, block2, 4, 2);
			BuildHandler.buildBuildPoint(world, x, y, z, 3);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block1, 3, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 0, block1, 7, 2);
			BuildHandler.setBlock(world, x + 1, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block1, 3, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z + 0, block1, 7, 2);
			BuildHandler.setBlock(world, x + 2, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 3, 2);
			
		} else if (dir == EnumFacing.WEST)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + 0, y - 2, z - 2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 2, block1, 4, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + 0, y - 1, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z - 1, block2, 4, 2);
			BuildHandler.buildBuildPoint(world, x, y, z, 3);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block1, 0, 2);
			
		} else if (dir == EnumFacing.EAST)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + 0, y - 2, z - 2, block1, 5, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 1, block1, 5, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block1, 5, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 1, block1, 5, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 2, block1, 5, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + 0, y - 1, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z - 1, block2, 4, 2);
			BuildHandler.buildBuildPoint(world, x, y, z, 3);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 2, block1, 1, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 1, block1, 1, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block1, 1, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block1, 1, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block1, 1, 2);
		}
		
	}
	
	@Override
	public boolean isHidden()
	{
		return hidden;
	}
	
	@Override
	public String getName()
	{
		return I18n.format("builder.window.name");
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "window";
	}
	
	/**
	 * 0 - everything, 1 - everything excluding pierce, 2 - only add structures,
	 * 3 - only window(only rot == 0), 4 - solar panels, 5 - greenhouse, 6 -
	 * pierce
	 */
	@Override
	public boolean isPossible(EnumFacing dir, int rot, int meta)
	{
		if (meta == 3 && rot == 1)
		{
			return false;
		} else if (meta != 3 && meta != 0 && rot == 0)
		{
			return false;
		}
		if ((rot == 0 || rot == 1) && (dir == EnumFacing.WEST || dir == EnumFacing.EAST || dir == EnumFacing.NORTH || dir == EnumFacing.SOUTH))
		{
			return true;
		} else return false;
	}
	
	@Override
	public List<OreDictItemStack> getRequiredItems()
	{
		List<OreDictItemStack> items = new ArrayList();
		if (this.placementRotation == 0)
		{
			items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 6, 7), "plateTin"));
			items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 4, ItemMod.scaffold_meta)));
			items.add(new OreDictItemStack(new ItemStack(Blocks.GLASS, 9, 0)));
		} else
		{
			items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 12, 7), "plateTin"));
			items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 6, ItemMod.scaffold_meta)));
			items.add(new OreDictItemStack(new ItemStack(Blocks.GLASS, 9, 0)));
		}
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.specialFunc = this.getName() + I18n.format("builder.window.rot" + placementRotation);
		return data;
	}
	
}

package net.orbitallabs.structures;

import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orbitallabs.blocks.BlockBuildPoint.EnumBlockPointStates;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.utils.OreDictItemStack;

public class StructurePirs extends Structure {
	
	public StructurePirs()
	{
		super(false);
	}
	
	@Override
	public void Build(World world, EnumFacing dir, BlockPos spos)
	{
		int x, y, z;
		x = spos.getX();
		y = spos.getY();
		z = spos.getZ();
		if (dir == EnumFacing.WEST)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x - 2, y - 2, z - 1, block1, 4, 2);
			BuildHandler.setBlock(world, x - 2, y - 2, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x - 2, y - 2, z + 1, block1, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z - 2, block1, 4, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x - 1, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block1, 0, 2);
			block1 = Blocks.AIR;
			BuildHandler.setBlock(world, x - 1, y + 2, z - 1, block1, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 2, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block1, 0, 2);
			
		} else if (dir == EnumFacing.EAST)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + 0, y - 2, z - 2, block1, 6, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + 0, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 2, block1, 1, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block1, 1, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z - 2, block1, 5, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 2, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z - 1, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z + 0, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z + 1, block1, 5, 2);
			
			block1 = Blocks.AIR;
			BuildHandler.setBlock(world, x + 0, y + 2, z - 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z - 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block1, 0, 2);
		} else if (dir == EnumFacing.NORTH)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x - 2, y - 2, z - 1, block1, 6, 2);
			BuildHandler.setBlock(world, x - 2, y - 2, z + 0, block1, 4, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x - 2, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 2, z + 0, block1, 2, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z - 2, block1, 6, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z - 2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z - 1, block1, 6, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z + 0, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 2, 2);
			block1 = Blocks.AIR;
			BuildHandler.setBlock(world, x - 1, y + 2, z - 1, block1, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z - 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block1, 0, 2);
			
		} else if (dir == EnumFacing.SOUTH)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x - 2, y - 2, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x - 2, y - 2, z + 1, block1, 7, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x - 2, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 2, z + 0, block1, 3, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z + 0, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z + 1, block1, 7, 2);
			BuildHandler.setBlock(world, x + 2, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 3, 2);
			
			block1 = Blocks.AIR;
			BuildHandler.setBlock(world, x - 1, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x - 1, y + 2, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block1, 0, 2);
			
		}
		
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
			Block block1 = Blocks.AIR;
			BuildHandler.setBlock(world, x - 2, y - 2, z - 1, block1, 4, 2);
			BuildHandler.setBlock(world, x - 2, y - 2, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x - 2, y - 2, z + 1, block1, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z - 2, block1, 4, 2);
			Block block2 = Blocks.AIR;
			BuildHandler.setBlock(world, x - 1, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 2, block1, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block1, 0, 2);
			
		} else if (dir == EnumFacing.EAST)
		{
			Block block1 = Blocks.AIR;
			BuildHandler.setBlock(world, x + 0, y - 2, z - 2, block1, 6, 2);
			Block block2 = Blocks.AIR;
			BuildHandler.setBlock(world, x + 0, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z - 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z - 2, block1, 1, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block1, 1, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z - 2, block1, 5, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 2, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z - 1, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z + 0, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z + 1, block1, 5, 2);
		} else if (dir == EnumFacing.NORTH)
		{
			Block block1 = Blocks.AIR;
			BuildHandler.setBlock(world, x - 2, y - 2, z - 1, block1, 6, 2);
			BuildHandler.setBlock(world, x - 2, y - 2, z + 0, block1, 4, 2);
			Block block2 = Blocks.AIR;
			BuildHandler.setBlock(world, x - 2, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 2, z + 0, block1, 2, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z - 2, block1, 6, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z - 2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z - 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z - 1, block1, 6, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z + 0, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 2, 2);
			
		} else if (dir == EnumFacing.SOUTH)
		{
			Block block1 = Blocks.AIR;
			BuildHandler.setBlock(world, x - 2, y - 2, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x - 2, y - 2, z + 1, block1, 7, 2);
			Block block2 = Blocks.AIR;
			BuildHandler.setBlock(world, x - 2, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 2, z + 0, block1, 3, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x - 1, y - 2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y - 2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y - 2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z + 0, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y - 2, z + 1, block1, 7, 2);
			BuildHandler.setBlock(world, x + 2, y - 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 3, 2);
			
		}
		
	}
	
	/**
	 * @param meta
	 *            0 - everything, 1 - everything excluding pirs, 2 - only add
	 *            structures, 3 - only window(only rot == 0), 4 - solar panels,
	 *            5 - greenhouse, 6 - pirs
	 */
	@Override
	public boolean Check(World world, EnumFacing dir, BlockPos spos, int meta)
	{
		if (meta != EnumBlockPointStates.PIRS.getMeta() && meta != EnumBlockPointStates.EVERYTHING.getMeta() && meta != EnumBlockPointStates.UNKNOWN)
		{
			return false;
		}
		if (dir == EnumFacing.WEST || dir == EnumFacing.EAST || dir == EnumFacing.NORTH || dir == EnumFacing.SOUTH) return true;
		else return false;
	}
	
	@Override
	public void ClearWay(World world, EnumFacing dir, BlockPos spos)
	{
	}
	
	@Override
	public boolean isHidden()
	{
		return false;
	}
	
	@Override
	public String getName()
	{
		return I18n.format("builder.pirs.name");
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "pirs";
	}
	
	@Override
	public Structure copy()
	{
		StructurePirs Nstr = new StructurePirs();
		if (this.placementPos == null) placementPos = BlockPos.ORIGIN;
		Nstr.Configure(new BlockPos(placementPos), placementRotation, placementDir);
		return Nstr;
	}
	
	@Override
	public NonNullList<OreDictItemStack> getRequiredItems()
	{
		NonNullList<OreDictItemStack> items = NonNullList.create();
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 8, 7), "plateTin"));
		items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 4, ItemMod.scaffold_meta)));
		
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.specialFunc = I18n.format("builder.side_info.funcs.pirs.name");
		return data;
	}
	
}

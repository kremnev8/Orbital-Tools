
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

public class StructureCupola extends Structure {
	
	private boolean hidden;
	
	public StructureCupola(boolean hidden)
	{
		super(hidden);
		this.hidden = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureCupola Nstr = new StructureCupola(hidden);
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
		if (dir == EnumFacing.DOWN)
		{
			Block block1 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -3, y + -1, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -1, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -1, z + 1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + 1, block1, 4, 2);
			Block block2 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block2, 8, 2);
			Block block3 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block2, 8, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -1, block3, 1, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 1, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 1, block3, 1, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -2, block1, 3, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block3, 6, 2);
			Block block4 = Blocks.AIR;
			BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block4, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + -2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 3, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 2, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + -2, block3, 3, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 2, block3, 2, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block2, 8, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block3, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block3, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block3, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block2, 8, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -1, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 1, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 3, y + -1, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -1, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -1, z + 1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + 1, block1, 4, 2);
			
			block1 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + -2, y + 0, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + -2, block1, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -1, block1, 4, 2);
			BuildHandler.buildBuildPoint(world, x, y, z, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 1, block1, 4, 2);
			
			BuildHandler.setBlock(world, x + -3, y + 0, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + 1, block1, 4, 2);
			
			BuildHandler.setBlock(world, x + -1, y + 0, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -3, block1, 4, 2);
			
			BuildHandler.setBlock(world, x + 0, y + 0, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 3, block1, 4, 2);
			
			BuildHandler.setBlock(world, x + 3, y + 0, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + 1, block1, 4, 2);
			
			BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block1, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 0, z - 2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z - 2, block1, 4, 2);
			BuildHandler.setBlock(world, x - 2, y + 0, z + 2, block1, 4, 2);
			
		}
	}
	
	@Override
	public void Build(World world, EnumFacing dir, BlockPos spos)
	{
		
		int x, y, z;
		x = spos.getX();
		y = spos.getY();
		z = spos.getZ();
		if (dir == EnumFacing.DOWN)
		{
			Block block1 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + -3, y + -1, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -1, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -1, z + 1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + 1, block1, 4, 2);
			Block block2 = GCBlocks.slabGCHalf;
			BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block2, 8, 2);
			Block block3 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block3, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block2, 8, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -1, block3, 1, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block3, 1, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 1, block3, 1, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -2, block1, 3, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block3, 6, 2);
			Block block4 = Blocks.GLASS;
			BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block4, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + -2, block3, 3, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 2, block3, 2, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block3, 3, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block3, 2, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block3, 6, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block3, 7, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + -2, block3, 3, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 2, block3, 2, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block2, 8, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block3, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block3, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block3, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block2, 8, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -1, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 1, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block1, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 3, y + -1, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -1, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -1, z + 1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + 1, block1, 4, 2);
			
		}
	}
	
	@Override
	public boolean Check(World world, EnumFacing dir, BlockPos pos, int meta)
	{
		if (meta != 2 && meta != 0 && meta != -1)
		{
			return false;
		}
		if (dir == EnumFacing.DOWN)
		{
			return true;
		} else return false;
	}
	
	@Override
	public void ClearWay(World world, EnumFacing dir, BlockPos spos)
	{
		int x, y, z;
		x = spos.getX();
		y = spos.getY();
		z = spos.getZ();
		Block block1 = Blocks.AIR;
		BuildHandler.setBlock(world, x + -2, y + 0, z + -1, block1, 0, 2);
		BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block1, 0, 2);
		BuildHandler.setBlock(world, x + -2, y + 0, z + 1, block1, 0, 2);
		BuildHandler.setBlock(world, x + -1, y + 0, z + -2, block1, 0, 2);
		BuildHandler.setBlock(world, x + -1, y + 0, z + -1, block1, 0, 2);
		BuildHandler.setBlock(world, x + -1, y + 0, z + 0, block1, 0, 2);
		BuildHandler.setBlock(world, x + -1, y + 0, z + 1, block1, 0, 2);
		BuildHandler.setBlock(world, x + -1, y + 0, z + 2, block1, 0, 2);
		BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block1, 0, 2);
		BuildHandler.setBlock(world, x + 0, y + 0, z + -1, block1, 0, 2);
		BuildHandler.setBlock(world, x + 0, y + 0, z + 0, block1, 0, 2);
		BuildHandler.setBlock(world, x + 0, y + 0, z + 1, block1, 0, 2);
		BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block1, 0, 2);
		BuildHandler.setBlock(world, x + 1, y + 0, z + -2, block1, 0, 2);
		BuildHandler.setBlock(world, x + 1, y + 0, z + -1, block1, 0, 2);
		BuildHandler.setBlock(world, x + 1, y + 0, z + 0, block1, 0, 2);
		BuildHandler.setBlock(world, x + 1, y + 0, z + 1, block1, 0, 2);
		BuildHandler.setBlock(world, x + 1, y + 0, z + 2, block1, 0, 2);
		BuildHandler.setBlock(world, x + 2, y + 0, z + -1, block1, 0, 2);
		BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block1, 0, 2);
		BuildHandler.setBlock(world, x + 2, y + 0, z + 1, block1, 0, 2);
		
	}
	
	@Override
	public boolean isHidden()
	{
		return hidden;
	}
	
	@Override
	public String getName()
	{
		return I18n.format("builder.cupola.name");
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "cupola";
	}
	
	@Override
	public List<OreDictItemStack> getRequiredItems()
	{
		List<OreDictItemStack> items = new ArrayList();
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 18, 7), "plateTin"));
		items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 8, ItemMod.scaffold_meta)));
		items.add(new OreDictItemStack(new ItemStack(Blocks.GLASS, 9)));
		
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.specialFunc = I18n.format("builder.side_info.funcs.cupola.name");
		return data;
	}
	
}

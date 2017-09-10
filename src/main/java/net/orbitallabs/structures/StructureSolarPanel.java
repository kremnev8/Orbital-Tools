package net.orbitallabs.structures;

import java.util.ArrayList;
import java.util.List;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.blocks.BlockSolar;
import micdoodle8.mods.galacticraft.core.tile.TileEntitySolar;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orbitallabs.utils.OreDictItemStack;

public class StructureSolarPanel extends StructureRotatable {
	
	private boolean hidden;
	
	public StructureSolarPanel(boolean hidden)
	{
		super(hidden);
		this.hidden = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureSolarPanel Nstr = new StructureSolarPanel(hidden);
		if (this.placementPos == null) placementPos = BlockPos.ORIGIN;
		Nstr.Configure(new BlockPos(placementPos), placementRotation, placementDir);
		return Nstr;
	}
	
	@Override
	public boolean isPossible(EnumFacing dir, int rot, int meta)
	{
		if (dir == EnumFacing.UP)
		{
			if (rot == 0 || rot == 1) return true;
		}
		return false;
	}
	
	int ret = 4;
	
	@Override
	public void deconstruct(World world, EnumFacing dir, BlockPos spos)
	{
		int x, y, z;
		x = spos.getX();
		y = spos.getY();
		z = spos.getZ();
		BuildHandler.setBlock(world, x, y, z, Blocks.AIR, 0, 2);
		if (world.getBlockState(new BlockPos(x, y, z + 1)).getBlock() == Blocks.GLOWSTONE)
		{
			BuildHandler.setBlock(world, x - 1, y, z, GCBlocks.basicBlock, 4, 2);
		} else BuildHandler.setBlock(world, x, y, z + 1, GCBlocks.basicBlock, 4, 2);
		BuildHandler.buildBuildPoint(world, x, y, z, ret);
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
			BuildHandler.setBlock(world, x, y, z, GCBlocks.solarPanel, 0, 2);
			if (world.getBlockState(new BlockPos(x, y, z + 1)).getBlock() == Blocks.GLOWSTONE)
			{
				((BlockSolar) world.getBlockState(new BlockPos(x, y, z)).getBlock()).onUseWrench(world, new BlockPos(x, y, z), null, null, null, null, x, y, z);
				BuildHandler.setBlock(world, x - 1, y, z, GCBlocks.sealableBlock, 15, 2);
			} else BuildHandler.setBlock(world, x, y, z + 1, GCBlocks.sealableBlock, 15, 2);
			TileEntitySolar tile = (TileEntitySolar) world.getTileEntity(new BlockPos(x, y, z));
			tile.onCreate(world, new BlockPos(x, y, z));
		} else
		{
			BuildHandler.setBlock(world, x, y, z, GCBlocks.solarPanel, 4, 2);
			if (world.getBlockState(new BlockPos(x, y, z + 1)) == Blocks.GLOWSTONE)
			{
				((BlockSolar) world.getBlockState(new BlockPos(x, y, z)).getBlock()).onUseWrench(world, new BlockPos(x, y, z), null, null, null, null, x, y, z);
				BuildHandler.setBlock(world, x - 1, y, z, GCBlocks.sealableBlock, 15, 2);
			} else BuildHandler.setBlock(world, x, y, z + 1, GCBlocks.sealableBlock, 15, 2);
			TileEntitySolar tile = (TileEntitySolar) world.getTileEntity(new BlockPos(x, y, z));
			tile.onCreate(world, new BlockPos(x, y, z));
		}
	}
	
	@Override
	public boolean Check(World world, EnumFacing dir, BlockPos spos, int meta)
	{
		if (meta != 2 && meta != 4 && meta != 5 && meta != 0 && meta != -1)
		{
			return false;
		}
		if (dir == EnumFacing.UP)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void ClearWay(World world, EnumFacing dir, BlockPos spos)
	{
		
	}
	
	@Override
	public boolean isHidden()
	{
		return hidden;
	}
	
	@Override
	public String getName()
	{
		return I18n.format("builder.solarpanel.name");
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "solarpanel";
	}
	
	@Override
	public List<OreDictItemStack> getRequiredItems()
	{
		List<OreDictItemStack> items = new ArrayList();
		if (this.placementRotation == 0)
		{
			items.add(new OreDictItemStack(new ItemStack(GCBlocks.solarPanel, 1, 0)));
			items.add(new OreDictItemStack(new ItemStack(GCBlocks.sealableBlock, 1, 14)));
		} else
		{
			items.add(new OreDictItemStack(new ItemStack(GCBlocks.solarPanel, 1, 4)));
			items.add(new OreDictItemStack(new ItemStack(GCBlocks.sealableBlock, 1, 15)));
		}
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.specialFunc = I18n.format("builder.side_info.funcs.solar.name");
		data.name = I18n.format("builder.solarpanel.rot" + placementRotation) + I18n.format("builder.solarpanel.name").toLowerCase();
		return data;
	}
	
}

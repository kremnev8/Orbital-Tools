
package net.orbitallabs.structures;

import java.util.ArrayList;
import java.util.List;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.tile.TileEntityAirLockController;
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

public class StructureHallWAirlock extends Structure {
	
	private boolean hidden;
	private String OW;
	
	public StructureHallWAirlock(boolean hidden)
	{
		super(hidden);
		this.hidden = hidden;
	}
	
	@Override
	public Structure copy()
	{
		StructureHallWAirlock Nstr = new StructureHallWAirlock(hidden);
		if (this.placementPos == null) placementPos = BlockPos.ORIGIN;
		Nstr.Configure(new BlockPos(placementPos), placementRotation, placementDir);
		return Nstr;
	}
	
	public void setOwner(String ow)
	{
		this.OW = ow;
	}
	
	public void setAirLock(World world, int x, int y, int z, int meta, int flag, String Owner)
	{
		Block block3 = GCBlocks.airLockFrame;
		BuildHandler.setBlock(world, x, y, z, block3, 1, 2);
		TileEntityAirLockController tile = (TileEntityAirLockController) world.getTileEntity(new BlockPos(x, y, z));
		tile.ownerName = Owner;
	}
	
	@Override
	public void deconstruct(World world, EnumFacing dir, BlockPos pos)
	{
		
	}
	
	@Override
	public void Build(World world, EnumFacing dir, BlockPos spos)
	{
		int x, y, z;
		x = spos.getX();
		y = spos.getY();
		z = spos.getZ();
		if (dir == EnumFacing.NORTH)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + -2, y + -2, z + -8, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -7, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -6, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -5, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -4, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -3, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block1, 4, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + -2, y + -1, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -7, block2, 4, 2);
			Block block3 = GCBlocks.airLockFrame;
			BuildHandler.setBlock(world, x + -2, y + -1, z + -6, block3, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block3, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -6, block3, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -2, block3, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -6, block3, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block3, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -8, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -7, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -6, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -5, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -4, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -3, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -6, block3, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block3, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -6, block3, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block3, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -7, block2, 4, 2);
			setAirLock(world, x + 0, y + -2, z + -6, 1, 2, OW);
			//--	BuildHandler.setBlock(world,x+0, y+-2, z+-6, block3,1,2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -3, block2, 4, 2);
			setAirLock(world, x + 0, y + -2, z + -2, 1, 2, OW);
			//--	BuildHandler.setBlock(world,x+0, y+-2, z+-2, block3,1,2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -8, block2, 4, 2);
			Block block4 = Blocks.GLOWSTONE;
			BuildHandler.setBlock(world, x + 0, y + 2, z + -7, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -6, block3, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -4, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block3, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -7, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -6, block3, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block3, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -7, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -6, block3, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block3, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -8, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -7, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -6, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -5, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -4, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -3, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -7, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -6, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -7, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -6, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -2, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -7, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -6, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -4, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -8, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -7, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -6, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -5, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -4, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -3, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 1, 2);
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x, y - 3, z - 4, 0, x, y, z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x + 1, y, z - 4, 0, x, y - 3, z - 4);
			
		} else if (dir == EnumFacing.SOUTH)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + -2, y + -2, z + 0, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 3, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 4, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 5, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 6, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 7, block1, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 8, block1, 4, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + -2, y + -1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 1, block2, 4, 2);
			Block block3 = GCBlocks.airLockFrame;
			BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block3, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 6, block3, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 8, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 2, block3, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 6, block3, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 8, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block3, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 6, block3, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 8, block2, 4, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 3, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 4, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 5, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 6, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 7, block1, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 8, block1, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block3, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 6, block3, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 8, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block3, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 6, block3, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block2, 4, 2);
			setAirLock(world, x + 0, y + -2, z + 2, 1, 2, OW);
			//--	BuildHandler.setBlock(world,x+0, y+-2, z+2, block3,1,2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 5, block2, 4, 2);
			setAirLock(world, x + 0, y + -2, z + 6, 1, 2, OW);
			//--	BuildHandler.setBlock(world,x+0, y+-2, z+6, block3,1,2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
			Block block4 = Blocks.GLOWSTONE;
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block3, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 4, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 6, block3, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 7, block4, 0, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block3, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 6, block3, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block3, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 6, block3, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 0, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 3, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 4, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 5, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 6, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 7, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 8, block1, 5, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 6, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 6, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 3, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 4, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 5, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 6, block3, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 7, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 8, block2, 4, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 3, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 4, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 5, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 6, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 7, block1, 1, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 8, block1, 1, 2);
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x, y - 3, z + 4, 0, x, y, z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x - 1, y, z + 4, 0, x, y - 3, z + 4);
			
		} else if (dir == EnumFacing.WEST)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + -8, y + -2, z + -2, block1, 6, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + -8, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + -8, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -8, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + -7, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + -1, block2, 4, 2);
			Block block3 = Blocks.GLOWSTONE;
			BuildHandler.setBlock(world, x + -7, y + 2, z + 0, block3, 0, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -7, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + -2, block1, 6, 2);
			Block block4 = GCBlocks.airLockFrame;
			BuildHandler.setBlock(world, x + -6, y + -2, z + -1, block4, 0, 2);
			setAirLock(world, x - 6, y - 2, z, 1, 2, OW);
			//--	BuildHandler.setBlock(world,x+-6, y+-2, z+0, block4,1,2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + -6, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + -6, y + -1, z + -2, block4, 0, 2);
			BuildHandler.setBlock(world, x + -6, y + -1, z + 2, block4, 0, 2);
			BuildHandler.setBlock(world, x + -6, y + 0, z + -2, block4, 0, 2);
			BuildHandler.setBlock(world, x + -6, y + 0, z + 2, block4, 0, 2);
			BuildHandler.setBlock(world, x + -6, y + 1, z + -2, block4, 0, 2);
			BuildHandler.setBlock(world, x + -6, y + 1, z + 2, block4, 0, 2);
			//	setAirLock(world, x-6, y+2, z+-2, 1, 2, OW);
			BuildHandler.setBlock(world, x + -6, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + -6, y + 2, z + -1, block4, 0, 2);
			BuildHandler.setBlock(world, x + -6, y + 2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + -6, y + 2, z + 1, block4, 0, 2);
			//	setAirLock(world, x-6, y+2, z+2, 1, 2, OW);
			BuildHandler.setBlock(world, x + -6, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + -5, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -5, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + -4, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + 0, block3, 0, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -4, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + -3, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -3, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + -1, block4, 0, 2);
			setAirLock(world, x - 2, y - 2, z, 1, 2, OW);
			//--	BuildHandler.setBlock(world,x+-2, y+-2, z+0, block4,1,2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + -2, block4, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + -1, z + 2, block4, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + -2, block4, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 0, z + 2, block4, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + -2, block4, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 1, z + 2, block4, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + -1, block4, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + -2, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 0, block3, 0, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + -1, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block1, 3, 2);
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x - 4, y - 3, z, 0, x, y, z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x - 4, y, z - 1, 0, x - 4, y - 3, z);
			
		} else if (dir == EnumFacing.EAST)
		{
			Block block1 = GCBlocks.tinStairs2;
			BuildHandler.setBlock(world, x + 0, y + -2, z + -2, block1, 6, 2);
			Block block2 = GCBlocks.basicBlock;
			BuildHandler.setBlock(world, x + 0, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 0, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + -1, block2, 4, 2);
			Block block3 = Blocks.GLOWSTONE;
			BuildHandler.setBlock(world, x + 1, y + 2, z + 0, block3, 0, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 1, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + -2, block1, 6, 2);
			Block block4 = GCBlocks.airLockFrame;
			BuildHandler.setBlock(world, x + 2, y + -2, z + -1, block4, 0, 2);
			setAirLock(world, x + 2, y - 2, z, 1, 2, OW);
			//--	BuildHandler.setBlock(world,x+2, y+-2, z+0, block4,1,2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + -2, block4, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + -1, z + 2, block4, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + -2, block4, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 0, z + 2, block4, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + -2, block4, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 1, z + 2, block4, 0, 2);
			//	setAirLock(world, x+2, y+2, z+-2, 1, 2, OW);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + -1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 1, block4, 0, 2);
			//	setAirLock(world, x+2, y+2, z+2, 1, 2, OW);
			BuildHandler.setBlock(world, x + 2, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 3, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 3, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 4, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + 0, block3, 0, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 4, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 5, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 5, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + -1, block4, 0, 2);
			setAirLock(world, x + 6, y - 2, z, 1, 2, OW);
			//--	BuildHandler.setBlock(world,x+6, y+-2, z+0, block4,1,2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 6, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 6, y + -1, z + -2, block4, 0, 2);
			BuildHandler.setBlock(world, x + 6, y + -1, z + 2, block4, 0, 2);
			BuildHandler.setBlock(world, x + 6, y + 0, z + -2, block4, 0, 2);
			BuildHandler.setBlock(world, x + 6, y + 0, z + 2, block4, 0, 2);
			BuildHandler.setBlock(world, x + 6, y + 1, z + -2, block4, 0, 2);
			BuildHandler.setBlock(world, x + 6, y + 1, z + 2, block4, 0, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + -1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + 0, block4, 0, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + 1, block4, 0, 2);
			BuildHandler.setBlock(world, x + 6, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 7, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + 0, block3, 0, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 7, y + 2, z + 2, block1, 3, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + -2, block1, 6, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + -2, z + 2, block1, 7, 2);
			BuildHandler.setBlock(world, x + 8, y + -1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + -1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 0, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 0, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 1, z + -2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 1, z + 2, block2, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + -2, block1, 2, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + -1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + 0, block2, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + 1, block2, 4, 2);
			BuildHandler.setBlock(world, x + 8, y + 2, z + 2, block1, 3, 2);
			BuildHandler.buildInfoPoint(world, dir, getUnlocalizedName(), x + 5, y - 3, z, 0, x, y, z);
			
			BuildHandler.buildRemoveInfoPoint(world, dir, getUnlocalizedName(), x + 4, y, z + 1, 0, x + 5, y - 3, z);
			
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
	public void ClearWay(World world, EnumFacing dir, BlockPos pos)
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
		return I18n.format("builder.airlock.name");
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return "hallairlock";
	}
	
	@Override
	public List<OreDictItemStack> getRequiredItems()
	{
		List<OreDictItemStack> items = new ArrayList();
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 48, 7), "plateTin"));
		items.add(new OreDictItemStack(new ItemStack(ItemMod.ironScaffold, 24, ItemMod.scaffold_meta)));
		items.add(new OreDictItemStack(new ItemStack(Items.GLOWSTONE_DUST, 12)));
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 3, 13)));
		
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 36, 8)));
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 24, 9), "plateSteel"));
		items.add(new OreDictItemStack(new ItemStack(GCItems.oxygenConcentrator, 6, 0)));
		items.add(new OreDictItemStack(new ItemStack(GCItems.basicItem, 4, 0)));
		
		return items;
	}
	
	@Override
	public StructureData getStructureData()
	{
		StructureData data = super.getStructureData();
		data.mainConnect = 1;
		data.addConnect = 0;
		data.specialFunc = I18n.format("builder.side_info.funcs.airlock.name");
		return data;
	}
	
}


package net.orbitallabs.dimensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import micdoodle8.mods.galacticraft.core.world.gen.BiomeOrbit;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.orbitallabs.structures.BuildHandler;
import net.orbitallabs.structures.StructureCrossroad;

public class ChunkProviderOrbitModif implements IChunkGenerator {
	private final Random rand;
	
	private final World worldObj;
	
	private static StructureCrossroad strStart = new StructureCrossroad(false);
	
	public ChunkProviderOrbitModif(World par1World, long par2, boolean par4)
	{
		this.rand = new Random(par2);
		this.worldObj = par1World;
	}
	
	@Override
	public Chunk provideChunk(int x, int z)
	{
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.rand.setSeed(x * 341873128712L + z * 132897987541L);
		
		final Chunk var4 = new Chunk(this.worldObj, chunkprimer, x, z);
		
		final byte[] biomesArray = var4.getBiomeArray();
		for (int i = 0; i < biomesArray.length; ++i)
		{
			biomesArray[i] = (byte) Biome.getIdForBiome(BiomeOrbit.space);
		}
		
		var4.generateSkylightMap();
		return var4;
	}
	
	@Override
	public void populate(int par2, int par3)
	{
		BlockFalling.fallInstantly = true;
		final int k = par2 * 16;
		final int l = par3 * 16;
		this.rand.setSeed(this.worldObj.getSeed());
		final long i1 = this.rand.nextLong() / 2L * 2L + 1L;
		final long j1 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed(par2 * i1 + par3 * j1 ^ this.worldObj.getSeed());
		if (k == 0 && l == 0)
		{
			BuildHandler.HandleBuild(worldObj, EnumFacing.UP, strStart.getUnlocalizedName(), new BlockPos(k, 64, l), 0, null);
		}
		BlockFalling.fallInstantly = false;
	}
	
	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z)
	{
		return false;
	}
	
	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos)
	{
		return new ArrayList();
	}
	
	@Override
	public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position, boolean p_180513_4_)
	{
		return null;
	}
	
	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z)
	{
	}
	
}

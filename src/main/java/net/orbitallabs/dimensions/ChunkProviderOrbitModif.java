
package net.orbitallabs.dimensions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import micdoodle8.mods.galacticraft.api.entity.IRocketType;
import micdoodle8.mods.galacticraft.core.GCFluids;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.world.gen.BiomeOrbit;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.orbitallabs.entity.EntityRocketFakeTiered;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.SetThirdPersonPacket;
import net.orbitallabs.structures.BuildHandler;
import net.orbitallabs.structures.StructureCrossroad;
import net.orbitallabs.tiles.TileEntityDockingPort;
import net.orbitallabs.utils.OTLoger;

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
			worldObj.getChunkFromChunkCoords(0, 0).setTerrainPopulated(true);
			
			EntityPlayer player = worldObj.getClosestPlayer(0, 64, 0, 200, false);
			GivePlayerHisItems(player);
		}
		BlockFalling.fallInstantly = false;
	}
	
	public void GivePlayerHisItems(EntityPlayer player)
	{
		GCPlayerStats playerStats = GCPlayerStats.get((EntityPlayerMP) player);
		if (playerStats.getRocketStacks().size() <= 1)
		{
			return;
		}
		OTLoger.logInfo("Player just docked to Space Station");
		OTLoger.logInfo("info:");
		
		PacketHandler.sendTo(new SetThirdPersonPacket(0), (EntityPlayerMP) player);
		
		DockingPortSaveData savef = DockingPortSaveData.forWorld(player.world);
		World world = player.world;
		
		boolean docked = false;
		if (savef.DockingPorts.size() > 0)
		{
			OTLoger.logInfo("Dim have a docking port info");
			for (int i = 0; i < savef.DockingPorts.size(); i++)
			{
				BlockPos pos = savef.DockingPorts.get(i);
				
				if (world.getTileEntity(pos) != null)
				{
					TileEntityDockingPort te = (TileEntityDockingPort) world.getTileEntity(pos);
					
					if (te.getStackInSlot(te.getSizeInventory() - 2).isEmpty() && te.getStackInSlot(te.getSizeInventory() - 3).isEmpty())
					{
						player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 2, pos.getZ() + 0.5D);
						
						if (playerStats.getRocketStacks().size() > 0)
						{
							te.setSizeInventory(playerStats.getRocketStacks().size() + 2);
							// te.chestContents =
							// ((WorldProviderOrbitModif)world.provider).rocketStacks;
							NonNullList<ItemStack> RCis = playerStats.getRocketStacks();
							
							for (int i2 = 0; i2 < RCis.size() - 2; i2++)
							{
								te.setInventorySlotContents(i2, RCis.get(i2));
							}
							if (!RCis.get(RCis.size() - 1).isEmpty())
								te.setLastID(RCis.get(RCis.size() - 1).hasTagCompound() ? RCis.get(RCis.size() - 1).getTagCompound().getInteger("UniqueID") : -1);
							te.setInventorySlotContents(te.getSizeInventory() - 3, RCis.get(RCis.size() - 2));
							te.setInventorySlotContents(te.getSizeInventory() - 2, RCis.get(RCis.size() - 1));
							boolean preFueled = false;
							ItemStack rocketI = RCis.get(RCis.size() - 1);
							if (!rocketI.isEmpty())
							{
								int type = rocketI.getItemDamage();
								// Checking type
								if (type == IRocketType.EnumRocketType.PREFUELED.getIndex())
								{
									preFueled = true;
								}
							}
							int tier = EntityRocketFakeTiered.getTierFromItem(rocketI);
							double hight = 1.8D;
							if (tier == 2) hight = 1.9D;
							else if (tier == 3) hight = 2.4D;
							te.rocket = new EntityRocketFakeTiered(world, te.getPos().getX() + 0.5D, te.getPos().getY() - hight, te.getPos().getZ() + 0.5D, tier, te);
							// creating fuel tank for rocket fuel
							// size
							te.fuelTank = new FluidTank(te.rocket.getFuelTankCapacity() * ConfigManagerCore.rocketFuelFactor);
							
							if (preFueled)
							{
								// if rocket is prefueld, fueling
								// it.
								te.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, te.fuelTank.getCapacity()), true);
							} else if (rocketI != null && rocketI.hasTagCompound())
							{
								// reading fuel from item NBT
								te.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, playerStats.getFuelLevel()), true);
							}
							
							// spawning rocket in world
							te.rocket.fuelTank = te.fuelTank;
							world.spawnEntity(te.rocket);
							playerStats.setRocketStacks(NonNullList.withSize(1, ItemStack.EMPTY));
							playerStats.setFuelLevel(0);
							
						}
						OTLoger.logInfo("Rocket started rendezvous with docking port N" + (i + 1));
						docked = true;
						break;
					} else OTLoger.logInfo("Rocket already docked in dock N" + (i + 1));
				}
			}
		}
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

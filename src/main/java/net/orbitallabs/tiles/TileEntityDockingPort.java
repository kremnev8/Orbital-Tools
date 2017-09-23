
package net.orbitallabs.tiles;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nonnull;
import micdoodle8.mods.galacticraft.api.entity.IRocketType;
import micdoodle8.mods.galacticraft.core.GCFluids;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.entities.IScaleableFuelLevel;
import micdoodle8.mods.galacticraft.core.inventory.IInventorySettable;
import micdoodle8.mods.galacticraft.core.network.IPacketReceiver;
import micdoodle8.mods.galacticraft.core.tile.TileEntityAdvanced;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.FluidUtil;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import micdoodle8.mods.miccore.Annotations.NetworkedField;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.orbitallabs.blocks.BlockDockingPoint;
import net.orbitallabs.entity.EntityRocketFakeTiered;
import net.orbitallabs.entity.EntityRocketFakeTiered.EnumLaunchPhase;
import net.orbitallabs.gui.ContainerDockingPort;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.DockItemSyncPacket;
import net.orbitallabs.network.packets.InvScalePacket;
import net.orbitallabs.network.packets.SendUUIDPacket;
import net.orbitallabs.utils.ChatUtils;

public class TileEntityDockingPort extends TileEntityAdvanced implements IInventorySettable, IPacketReceiver, IScaleableFuelLevel, ISidedInventory, IItemHandler {
	private final int tankCapacity = 5000;
	@NetworkedField(targetSide = Side.CLIENT)
	public FluidTank fuelTank = new FluidTank(this.tankCapacity);
	
	public NonNullList<ItemStack> chestContents = NonNullList.withSize(4, ItemStack.EMPTY);
	
	public boolean adjacentChestChecked = false;
	
	public float lidAngle;
	
	public float prevLidAngle;
	
	public int numUsingPlayers;
	
	public int addSlots = 0;
	public int lastSlots = 0;
	// public int numNonEmpty = 0;
	public NonNullList<ItemStack> oldStacks;
	// public boolean hasCredit = false;
	// public int vantedSize;
	public EntityRocketFakeTiered rocket;
	private UUID entUUID;
	private int lastItemUniqueId = -1;
	private Random rand = new Random();
	
	@Override
	public void validate()
	{
		super.validate();
		
		if (this.world != null && this.world.isRemote)
		{
			// Request size + contents information from server
			// GalacticraftCore.packetPipeline.sendToServer(new
			// PacketDynamicInventory(this));
		}
	}
	
	@Override
	public int getScaledFuelLevel(int i)
	{
		final double fuelLevel = this.fuelTank.getFluid() == null ? 0 : this.fuelTank.getFluid().amount;
		
		return (int) (fuelLevel * i / this.tankCapacity);
	}
	
	@Override
	public int getSizeInventory()
	{
		return this.chestContents.size();
	}
	
	@Override
	public void setSizeInventory(int size)
	{
		if ((size - 4) % 18 != 0)
		{
			GCLog.debug("Strange TileEntityDockport inventory size received from server " + size);
		}
		this.chestContents = NonNullList.withSize(size, ItemStack.EMPTY);
		
	}
	
	public void setLastID(int id)
	{
		this.lastItemUniqueId = id;
	}
	
	public void setAddSlots(int size)
	{
		this.addSlots = size;
		oldStacks = chestContents;
		
		this.setSizeInventory(size + 4);
		this.setInventorySlotContents(this.getSizeInventory() - 1, oldStacks.get(oldStacks.size() - 1));
		this.setInventorySlotContents(this.getSizeInventory() - 2, oldStacks.get(oldStacks.size() - 2));
		this.setInventorySlotContents(this.getSizeInventory() - 3, oldStacks.get(oldStacks.size() - 3));
		this.setInventorySlotContents(this.getSizeInventory() - 4, oldStacks.get(oldStacks.size() - 4));
		if (size != 0)
		{
			for (int i = 0; i < oldStacks.size() - 4; i++)
			{
				this.setInventorySlotContents(i, oldStacks.get(i));
			}
		}
		lastSlots = addSlots;
		
		if (!world.isRemote)
		{
			PacketHandler.sendToAllAround(new InvScalePacket(chestContents.size(), pos.getX(), pos.getY(), pos.getZ()),
					new TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			PacketHandler.sendToAllAround(new DockItemSyncPacket(chestContents, pos.getX(), pos.getY(), pos.getZ()),
					new TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
		}
	}
	
	@Override
	public ItemStack getStackInSlot(int par1)
	{
		if (par1 > 0 && par1 < this.getSizeInventory())
		{
			return this.chestContents.get(par1);
		}
		return ItemStack.EMPTY;
	}
	
	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (par1 > 0 && par1 < this.getSizeInventory())
		{
			if (this.chestContents.get(par1) != null)
			{
				ItemStack itemstack;
				
				if (this.chestContents.get(par1).getCount() <= par2)
				{
					itemstack = this.chestContents.get(par1);
					this.chestContents.set(par1, ItemStack.EMPTY);
					this.markDirty();
					return itemstack;
				} else
				{
					itemstack = this.chestContents.get(par1).splitStack(par2);
					
					if (this.chestContents.get(par1).getCount() == 0)
					{
						this.chestContents.set(par1, ItemStack.EMPTY);
					}
					
					this.markDirty();
					return itemstack;
				}
			}
			return null;
		}
		return null;
	}
	
	/*	@Override
		public ItemStack getStackInSlotOnClosing(int par1)
		{
			if (par1 < this.getSizeInventory())
			{
				if (this.chestContents[par1] != null)
				{
					ItemStack itemstack = this.chestContents[par1];
					this.chestContents[par1] = null;
					return itemstack;
				} else
				{
					return null;
				}
			} else return null;
		}*/
	
	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		if (par1 > 0 && par1 < this.getSizeInventory())
		{
			this.chestContents.set(par1, par2ItemStack);
			
			if (par2ItemStack != null && par2ItemStack.getCount() > this.getInventoryStackLimit())
			{
				par2ItemStack.setCount(this.getInventoryStackLimit());
			}
			
			this.markDirty();
		}
	}
	
	@Override
	public String getName()
	{
		return GCCoreUtil.translate("container.dockingport.name");
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		
		lastItemUniqueId = nbt.getInteger("LASTITEMID");
		if (nbt.hasKey("RocketUUIDMost", 4) && nbt.hasKey("RocketUUIDLeast", 4))
		{
			this.entUUID = new UUID(nbt.getLong("RocketUUIDMost"), nbt.getLong("RocketUUIDLeast"));
		}
		
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		
		if (nbt.getInteger("chestContentLength") == 3)
		{
			this.invalidate();
		} else
		{
			
			this.chestContents = NonNullList.withSize(nbt.getInteger("chestContentLength"), ItemStack.EMPTY);
			
			for (int i = 0; i < nbttaglist.tagCount(); ++i)
			{
				NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
				int j = nbttagcompound1.getByte("Slot") & 255;
				
				if (j < this.chestContents.size())
				{
					this.chestContents.set(j, new ItemStack(nbttagcompound1));
				}
			}
			
			if (nbt.hasKey("fuelTank"))
			{
				this.fuelTank.readFromNBT(nbt.getCompoundTag("fuelTank"));
			}
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		
		nbt.setInteger("LASTITEMID", lastItemUniqueId);
		
		nbt.setInteger("chestContentLength", this.chestContents.size());
		
		if (rocket != null)
		{
			nbt.setLong("RocketUUIDMost", rocket.getUniqueID().getMostSignificantBits());
			nbt.setLong("RocketUUIDLeast", rocket.getUniqueID().getLeastSignificantBits());
		}
		
		NBTTagList nbttaglist = new NBTTagList();
		
		for (int i = 0; i < this.chestContents.size(); ++i)
		{
			if (this.chestContents.get(i) != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.chestContents.get(i).writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		
		nbt.setTag("Items", nbttaglist);
		
		if (this.fuelTank.getFluid() != null)
		{
			nbt.setTag("fuelTank", this.fuelTank.writeToNBT(new NBTTagCompound()));
		}
		return nbt;
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return 64;
	}
	
	@Override
	public boolean isUsableByPlayer(EntityPlayer player)
	{
		return this.world.getTileEntity(pos) == this && player.getDistanceSq(this.pos.getX() + 0.5D, this.pos.getY() + 0.5D, this.pos.getZ() + 0.5D) <= 64.0D;
	}
	
	@Override
	public void updateContainingBlockInfo()
	{
		super.updateContainingBlockInfo();
		this.adjacentChestChecked = false;
	}
	
	@Override
	public void update()
	{
		super.update();
		boolean anotherItem = false;
		float f;
		// finish loading entity from NBT
		if (entUUID != null && rocket == null)
		{
			List<Entity> Entlist = world.loadedEntityList;
			
			for (int i = 0; i < Entlist.size(); i++)
			{
				Entity ent = Entlist.get(i);
				if (ent.getUniqueID().equals(entUUID))
				{
					if (ent instanceof EntityRocketFakeTiered)
					{
						//	GLoger.logInfo("Find writen entity from UUID");
						rocket = (EntityRocketFakeTiered) ent;
						if (rocket != null) PacketHandler.sendToAllAround(new SendUUIDPacket(rocket.getUniqueID()),
								new TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getX(), pos.getX(), 10));
						if (this.getStackInSlot(this.getSizeInventory() - 2) != null)
						{
							boolean preFueled = false;
							int type = this.getStackInSlot(this.getSizeInventory() - 2).getItemDamage();
							// Checking type
							if (type == IRocketType.EnumRocketType.PREFUELED.getIndex())
							{
								preFueled = true;
							}
							this.fuelTank = new FluidTank(rocket.getFuelTankCapacity() * ConfigManagerCore.rocketFuelFactor);
							
							if (preFueled)
							{
								this.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, this.fuelTank.getCapacity()), true);
							} else if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
							{
								NBTTagCompound tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
								int fuel = tag.getInteger("RocketFuel");
								this.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, fuel), true);
							}
							rocket.fuelTank = fuelTank;
						}
					}
				}
			}
		}
		if (this.isInvalid())
		{
			this.world.setBlockToAir(pos);
		}
		boolean preFueled = false;
		// clearing slot if rocket is dead
		if (rocket != null && rocket.isDead && !world.isRemote)
		{
			if (rocket.launchPhase == EnumLaunchPhase.FLYAWAY.ordinal() || rocket.launchPhase == EnumLaunchPhase.LAUNCHED.ordinal())
			{
				this.setInventorySlotContents(this.getSizeInventory() - 3, ItemStack.EMPTY);
			}
			this.setInventorySlotContents(this.getSizeInventory() - 2, ItemStack.EMPTY);
			this.fuelTank.drain(this.fuelTank.getCapacity(), true);
			rocket = null;
		}
		if (this.getStackInSlot(this.getSizeInventory() - 2) != null)
		{
			int type = this.getStackInSlot(this.getSizeInventory() - 2).getItemDamage();
			// Checking type
			if (type == IRocketType.EnumRocketType.PREFUELED.getIndex())
			{
				preFueled = true;
			}
		}
		
		//checking for replaced item (dirty code, it creates useless int in NBT) 
		if (this.getStackInSlot(this.getSizeInventory() - 2) != null && rocket != null)
		{
			boolean writeID = false;
			if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
			{
				NBTTagCompound tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
				if (tag.hasKey("UniqueID"))
				{
					if (tag.getInteger("UniqueID") != lastItemUniqueId)
					{
						writeID = true;
						anotherItem = true;
					}
				} else
				{
					writeID = true;
					anotherItem = true;
				}
			} else
			{
				writeID = true;
				anotherItem = true;
			}
			if (writeID)
			{
				NBTTagCompound tag;
				if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
				{
					tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
				} else
				{
					tag = new NBTTagCompound();
				}
				lastItemUniqueId = rand.nextInt(10000000);
				tag.setInteger("UniqueID", lastItemUniqueId);
				ItemStack is = this.getStackInSlot(this.getSizeInventory() - 2);
				is.setTagCompound(tag);
				this.setInventorySlotContents(this.getSizeInventory() - 2, is);
			}
		}
		
		// Checking and saving fuel data in rocket item NBT
		if (this.getStackInSlot(this.getSizeInventory() - 2) != null && rocket != null && !anotherItem && !preFueled)
		{
			int tier = EntityRocketFakeTiered.getTierFromItem(this.getStackInSlot(this.getSizeInventory() - 2));
			ItemStack is = this.getStackInSlot(this.getSizeInventory() - 2);
			if (tier == rocket.getTier())
			{
				if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
				{
					NBTTagCompound tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
					int fuel = tag.getInteger("RocketFuel");
					if (fuel != this.fuelTank.getFluidAmount())
					{
						if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
						{
							tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
						} else
						{
							tag = new NBTTagCompound();
						}
						if (this.fuelTank.getFluidAmount() != 0) tag.setInteger("RocketFuel", this.fuelTank.getFluidAmount());
						is = this.getStackInSlot(this.getSizeInventory() - 2);
						is.setTagCompound(tag);
						this.setInventorySlotContents(this.getSizeInventory() - 2, is);
					}
				} else
				{
					NBTTagCompound tag;
					if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
					{
						tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
					} else
					{
						tag = new NBTTagCompound();
					}
					if (this.fuelTank.getFluidAmount() != 0) tag.setInteger("RocketFuel", this.fuelTank.getFluidAmount());
					is = this.getStackInSlot(this.getSizeInventory() - 2);
					is.setTagCompound(tag);
					this.setInventorySlotContents(this.getSizeInventory() - 2, is);
				}
			}
		}
		if (this.getStackInSlot(this.getSizeInventory() - 2) != ItemStack.EMPTY)
		{
			if (this.getStackInSlot(this.getSizeInventory() - 2).getItemDamage() > 4)
			{
				if (!world.isRemote)
				{
					EntityItem item = new EntityItem(world, pos.getX(), pos.getY() + 1, pos.getZ(), this.getStackInSlot(this.getSizeInventory() - 2));
					item.setNoPickupDelay();
					world.spawnEntity(item);
					
					EntityPlayer player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 15, false);
					if (player != null)
					{
						player.sendMessage(
								ChatUtils.modifyColor(new TextComponentString("This rocket isn't supported by docking port. Use landing pad instead!"), TextFormatting.RED));
					}
				}
				this.setInventorySlotContents(this.getSizeInventory() - 2, null);
				
			}
		}
		// start massive check for rocket item
		if (this.getStackInSlot(this.getSizeInventory() - 2) != ItemStack.EMPTY)
		{
			if (this.getStackInSlot(this.getSizeInventory() - 2).getItem() == GCItems.rocketTier1
					|| this.getStackInSlot(this.getSizeInventory() - 2).getItem() == MarsItems.rocketMars
					|| this.getStackInSlot(this.getSizeInventory() - 2).getItem() == AsteroidsItems.tier3Rocket)
			{
				int type = this.getStackInSlot(this.getSizeInventory() - 2).getItemDamage();
				// Checking type
				if (type == IRocketType.EnumRocketType.DEFAULT.getIndex() || type == IRocketType.EnumRocketType.PREFUELED.getIndex())
				{
					addSlots = 0;
					
				} else
				{
					// getting inventory size from IRocketType
					addSlots = IRocketType.EnumRocketType.values()[type].getInventorySpace() - 2;
				}
				// setting up rocket if yet not
				if (rocket == null && !world.isRemote)
				{
					int tier = EntityRocketFakeTiered.getTierFromItem(this.getStackInSlot(this.getSizeInventory() - 2));
					
					rocket = new EntityRocketFakeTiered(world, pos.getX() + 0.5D, pos.getY() - EntityRocketFakeTiered.getDockingOffset(tier), pos.getZ() + 0.5D, tier, this);
					// creating fuel tank for rocket fuel size
					this.fuelTank = new FluidTank(rocket.getFuelTankCapacity() * ConfigManagerCore.rocketFuelFactor);
					
					if (preFueled)
					{
						// if rocket is prefueld, fueling it.
						this.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, this.fuelTank.getCapacity()), true);
					} else if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
					{
						// reading fuel from item NBT
						NBTTagCompound tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
						int fuel = tag.getInteger("RocketFuel");
						this.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, fuel), true);
					}
					
					// spawning rocket in world
					rocket.fuelTank = fuelTank;
					world.spawnEntity(rocket);
					if (rocket != null) PacketHandler.sendToAllAround(new SendUUIDPacket(rocket.getUniqueID()),
							new TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
				} else if (!world.isRemote)
				{
					// Updating rocket tier if needed. also need update if item changed(swapped to the same)
					int tier = EntityRocketFakeTiered.getTierFromItem(this.getStackInSlot(this.getSizeInventory() - 2));
					if (rocket.getTier() != tier || anotherItem)
					{
						rocket.setTier(tier);
						
						if (tier == 1)
						{
							rocket.setSize(1.2F, 3.5F);
						} else if (tier == 2)
						{
							rocket.setSize(1.2F, 4.5F);
						} else if (tier == 3)
						{
							rocket.setSize(1.8F, 6F);
						}
						// updating fuel tank also
						this.fuelTank = new FluidTank(rocket.getFuelTankCapacity() * ConfigManagerCore.rocketFuelFactor);
						
						if (preFueled)
						{
							// if rocket is prefueld, fueling it.
							this.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, this.fuelTank.getCapacity()), true);
						} else if (this.getStackInSlot(this.getSizeInventory() - 2).hasTagCompound())
						{
							// reading fuel from item NBT
							NBTTagCompound tag = this.getStackInSlot(this.getSizeInventory() - 2).getTagCompound();
							int fuel = tag.getInteger("RocketFuel");
							this.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, fuel), true);
						}
						rocket.fuelTank = fuelTank;
						// and repositioning
						rocket.setPositionAndRotation(pos.getX() + 0.5D, pos.getY() - EntityRocketFakeTiered.getDockingOffset(tier), pos.getZ() + 0.5D, rocket.rotationYaw,
								rocket.rotationPitch);
					}
				}
				
			}
			
		} else
		{
			addSlots = 0;
			if (!world.isRemote && rocket != null)
			{
				if (rocket.getRidingEntity() != null)
				{
					// before delete rocket from world, make player quit it
					EntityPlayer player = (EntityPlayer) rocket.getRidingEntity();
					rocket.QuitRocket(player);
					
				}
				rocket.setDead();
				this.fuelTank.drain(this.fuelTank.getCapacity(), true);
				
				rocket = null;
			}
		}
		// checking additional slots and if needed reloading them
		if (lastSlots != addSlots)
		{
			this.setAddSlots(addSlots);
		}
		
		if (!this.world.isRemote && this.numUsingPlayers != 0 && (this.ticks + pos.getX() + pos.getY() + pos.getZ()) % 200 == 0)
		{
			this.numUsingPlayers = 0;
			f = 5.0F;
			List<?> list = this.world.getEntitiesWithinAABB(EntityPlayer.class,
					new AxisAlignedBB(pos.getX() - f, pos.getY() - f, pos.getZ() - f, pos.getX() + 1 + f, pos.getY() + 1 + f, pos.getZ() + 1 + f));
			Iterator<?> iterator = list.iterator();
			
			while (iterator.hasNext())
			{
				EntityPlayer entityplayer = (EntityPlayer) iterator.next();
				
				if (entityplayer.openContainer instanceof ContainerDockingPort)
				{
					++this.numUsingPlayers;
				}
			}
		}
		
		this.prevLidAngle = this.lidAngle;
		f = 0.1F;
		//double d0;
		
		if (this.numUsingPlayers > 0 && this.lidAngle == 0.0F)
		{
			//	double d1 = this.xCoord + 0.5D;
			//	d0 = this.zCoord + 0.5D;
			
		}
		
		if (this.numUsingPlayers == 0 && this.lidAngle > 0.0F || this.numUsingPlayers > 0 && this.lidAngle < 1.0F)
		{
			float f1 = this.lidAngle;
			
			if (this.numUsingPlayers > 0)
			{
				this.lidAngle += f;
			} else
			{
				this.lidAngle -= f;
			}
			
			if (this.lidAngle > 1.0F)
			{
				this.lidAngle = 1.0F;
			}
			
			float f2 = 0.5F;
			
			if (this.lidAngle < f2 && f1 >= f2)
			{
				//	d0 = this.xCoord + 0.5D;
				//	double d2 = this.zCoord + 0.5D;
				
			}
			
			if (this.lidAngle < 0.0F)
			{
				this.lidAngle = 0.0F;
			}
		}
		if (!this.world.isRemote && rocket != null)
		{
			// drain
			FluidUtil.loadFromContainer(fuelTank, GCFluids.fluidFuel, chestContents, this.chestContents.size() - 1, fuelTank.getCapacity() - fuelTank.getFluidAmount());
			
			// fuel
			FluidUtil.tryFillContainer(fuelTank, new FluidStack(GCFluids.fluidFuel, 1000), chestContents, this.chestContents.size() - 4, GCItems.fuelCanister);
			/*ItemStack stack = getStackInSlot(this.chestContents.size() - 4);
			
			if (stack != null)
			{
				if (stack.getItem() instanceof ItemCanisterGeneric)
				{
					if (stack.getItem() == GCItems.fuelCanister)
					{
						int originalDamage = stack.getItemDamage();
						int used = fuelTank.fill(new FluidStack(GCFluids.fluidFuel, ItemCanisterGeneric.EMPTY - originalDamage), true);
						if (originalDamage + used == ItemCanisterGeneric.EMPTY)
							setInventorySlotContents(this.chestContents.size() - 4, new ItemStack(GCItems.oilCanister, 1, ItemCanisterGeneric.EMPTY));
						else setInventorySlotContents(this.chestContents.size() - 4, new ItemStack(GCItems.fuelCanister, 1, originalDamage + used));
					}
				} else
				{
					final FluidStack liquid = net.minecraftforge.fluids.FluidUtil.getFluidContained(stack);
					ItemStack is = stack;
					if (liquid != null)
					{
						boolean isFuel = FluidUtil.testFuel(FluidRegistry.getFluidName(liquid));
						if (isFuel)
						{
							if (fuelTank.getFluid() == null || fuelTank.getFluid().amount + liquid.amount <= fuelTank.getCapacity())
							{
								//fuelTank.fill(new FluidStack(GCFluids.fluidFuel, liquid.amount), true);
								EntityPlayer player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 15, false);
								net.minecraftforge.fluids.FluidUtil.tryFillContainerAndStow(stack, fuelTank, this, fuelTank.getCapacity()-fuelTank.getFluidAmount(), player)
							}
						}
					} else
					{
						if (stack.getItem() instanceof ItemFluidContainer)
						{
							ItemFluidContainer cont = (ItemFluidContainer) stack.getItem();
							if (stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, EnumFacing.UP) != null && stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, EnumFacing.UP) instanceof FluidHandlerItemStack)
							{
								FluidHandlerItemStack hand = (FluidHandlerItemStack) stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, EnumFacing.UP);
								boolean isFuel = FluidUtil.testFuel(FluidRegistry.getFluidName(hand.getFluid()));
								if (isFuel)
								{
										FluidStack st = hand.drain(new FluidStack(hand.getFluid(), fuelTank.getCapacity()-fuelTank.getFluidAmount()), true);
										if (st != null && st.amount > 0)
										{
											fuelTank.fill(st, true);
										}
								}
							}
							
						}
					}
				}
			}*/
		}
	}
	
	/*private void checkFluidTankTransfer(ItemStack stack)
	{
		if (stack != null)
		{
			if (stack.getItem() instanceof ItemCanisterGeneric)
			{
				if (stack.getItem() == GCItems.fuelCanister)
				{
					int originalDamage = stack.getItemDamage();
					FluidStack st = fuelTank.drain(1000 - (ItemCanisterGeneric.EMPTY - originalDamage), true);
					if (st != null && st.amount > 0)
					{
						setInventorySlotContents(this.chestContents.size() - 1, new ItemStack(GCItems.fuelCanister, 1, originalDamage - st.amount));
					}
				}
			} else if (FluidUtil.isValidContainer(stack))
			{
				final FluidStack liquid = FluidContainerRegistry.getFluidForFilledItem(stack);
				if (liquid != null && liquid.amount != FluidContainerRegistry.getContainerCapacity(stack))
				{
					boolean isFuel = FluidUtil.testFuel(FluidRegistry.getFluidName(liquid));
					if (isFuel)
					{
						if (stack.stackSize == 1)
						{
							if (fuelTank.getFluidAmount() > 0)
							{
								int drain = FluidContainerRegistry.getContainerCapacity(stack) - liquid.amount;
								FluidStack st = fuelTank.drain(drain, true);
								if (st != null && st.amount > 0)
								{
									FluidContainerRegistry.fillFluidContainer(st, stack);
									setInventorySlotContents(this.chestContents.length - 1, stack);
								}
							}
						}
					}
				} else
				{
					if (stack.stackSize == 1)
					{
						if (fuelTank.getFluidAmount() > 0)
						{
							int drain = FluidContainerRegistry.getContainerCapacity(stack);
							if (drain == 0 && FluidContainerRegistry.isBucket(stack))
							{
								drain = FluidContainerRegistry.BUCKET_VOLUME;
							}
							FluidStack st = fuelTank.drain(drain, !FluidContainerRegistry.isBucket(stack));
							if (FluidContainerRegistry.isBucket(stack) && st != null && st.amount == 1000)
							{
								fuelTank.drain(drain, true);
								setInventorySlotContents(this.chestContents.length - 1, new ItemStack(GCItems.bucketFuel));
							} else if (st != null && st.amount > 0)
							{
								FluidContainerRegistry.fillFluidContainer(st, stack);
								setInventorySlotContents(this.chestContents.length - 1, stack);
							}
						}
					}
				}
			} else
			{
				if (stack.getItem() instanceof IFluidContainerItem)
				{
					IFluidContainerItem cont = (IFluidContainerItem) stack.getItem();
					if (cont.getFluid(stack) != null)
					{
						boolean isFuel = FluidUtil.testFuel(FluidRegistry.getFluidName(cont.getFluid(stack)));
						if (isFuel)
						{
							if (stack.stackSize == 1)
							{
								FluidStack st = fuelTank.drain(cont.getCapacity(stack) - cont.getFluid(stack).amount, true);
								if (st != null && st.amount > 0)
								{
									cont.fill(stack, st, true);
								}
							}
						}
					} else
					{
						if (stack.stackSize == 1)
						{
							FluidStack st = fuelTank.drain(cont.getCapacity(stack), true);
							if (st != null && st.amount > 0)
							{
								cont.fill(stack, st, true);
							}
						}
					}
					
				}
				
			}
		}
	}*/
	
	@Override
	public boolean receiveClientEvent(int par1, int par2)
	{
		if (par1 == 1)
		{
			this.numUsingPlayers = par2;
			return true;
		}
		return super.receiveClientEvent(par1, par2);
	}
	
	@Override
	public void openInventory(EntityPlayer player)
	{
		if (this.numUsingPlayers < 0)
		{
			this.numUsingPlayers = 0;
		}
		
		++this.numUsingPlayers;
		if (!this.world.isRemote)
		{
			// Updating code
			PacketHandler.sendToAllAround(new InvScalePacket(chestContents.size(), pos.getX(), pos.getY(), pos.getZ()),
					new TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			PacketHandler.sendToAllAround(new DockItemSyncPacket(chestContents, pos.getX(), pos.getY(), pos.getZ()),
					new TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 20));
			if (rocket != null) PacketHandler.sendToAllAround(new SendUUIDPacket(rocket.getUniqueID()),
					new TargetPoint(this.world.provider.getDimension(), pos.getX(), pos.getY(), pos.getZ(), 10));
		}
		this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numUsingPlayers);
		//world.notifyBlockUpdate(pos, oldState, newState, flags);
		//world.notifyBlockUpdate(pos, this.getBlockType());
		//	this.world.notifyBlocksOfNeighborChange(pos.getX(), pos.getY() - 1, pos.getZ(), this.getBlockType());
	}
	
	@Override
	public void closeInventory(EntityPlayer player)
	{
		if (this.getBlockType() != null && this.getBlockType() instanceof BlockDockingPoint)
		{
			--this.numUsingPlayers;
			this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numUsingPlayers);
			//	this.world.notifyBlocksOfNeighborChange(pos.getX(),  pos.getY(), pos.getZ(), this.getBlockType());
			//	this.world.notifyBlocksOfNeighborChange(pos.getX(),  pos.getY() - 1, pos.getZ(), this.getBlockType());
		}
	}
	
	@Override
	public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
	{
		return true;
	}
	
	@Override
	public void invalidate()
	{
		super.invalidate();
		this.updateContainingBlockInfo();
	}
	
	@Override
	public double getPacketRange()
	{
		return 12.0D;
	}
	
	@Override
	public int getPacketCooldown()
	{
		return 3;
	}
	
	@Override
	public boolean isNetworkedTile()
	{
		return true;
	}
	
	@Override
	public int[] getSlotsForFace(EnumFacing side)
	{
		if (addSlots == 0)
		{
			return new int[] {};
		}
		int[] ret = new int[addSlots];
		for (int i = 0; i < addSlots; i++)
		{
			ret[i] = i;
		}
		return ret;
	}
	
	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction)
	{
		return true;
	}
	
	@Override
	public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction)
	{
		return true;
	}
	
	@Override
	public boolean isEmpty()
	{
		return false;
	}
	
	@Override
	public ItemStack removeStackFromSlot(int index)
	{
		if (index > 0 && index < this.getSizeInventory())
		{
			ItemStack stack = chestContents.get(index).copy();
			chestContents.set(index, ItemStack.EMPTY);
			return stack;
		}
		return ItemStack.EMPTY;
	}
	
	@Override
	public int getField(int id)
	{
		return 0;
	}
	
	@Override
	public void setField(int id, int value)
	{
	}
	
	@Override
	public int getFieldCount()
	{
		return 0;
	}
	
	@Override
	public void clear()
	{
		//chestContents = NonNullList.withSize(chestContents.size(), ItemStack.EMPTY);
		chestContents.clear();
	}
	
	@Override
	public boolean hasCustomName()
	{
		return false;
	}
	
	@Override
	public int getSlots()
	{
		return chestContents.size();
	}
	
	@Override
	@Nonnull
	public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate)
	{
		if (stack.isEmpty()) return ItemStack.EMPTY;
		
		validateSlotIndex(slot);
		
		ItemStack existing = chestContents.get(slot);
		
		int limit = Math.min(getSlotLimit(slot), stack.getMaxStackSize());
		
		if (!existing.isEmpty())
		{
			if (!ItemHandlerHelper.canItemStacksStack(stack, existing)) return stack;
			
			limit -= existing.getCount();
		}
		
		if (limit <= 0) return stack;
		
		boolean reachedLimit = stack.getCount() > limit;
		
		if (!simulate)
		{
			if (existing.isEmpty())
			{
				chestContents.set(slot, reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack);
			} else
			{
				existing.grow(reachedLimit ? limit : stack.getCount());
			}
			markDirty();
		}
		
		return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount() - limit) : ItemStack.EMPTY;
	}
	
	@Nonnull
	public ItemStack extractItem(int slot, int amount, boolean simulate)
	{
		if (amount == 0) return ItemStack.EMPTY;
		
		validateSlotIndex(slot);
		
		ItemStack existing = chestContents.get(slot);
		
		if (existing.isEmpty()) return ItemStack.EMPTY;
		
		int toExtract = Math.min(amount, existing.getMaxStackSize());
		
		if (existing.getCount() <= toExtract)
		{
			if (!simulate)
			{
				chestContents.set(slot, ItemStack.EMPTY);
				markDirty();
			}
			return existing;
		} else
		{
			if (!simulate)
			{
				chestContents.set(slot, ItemHandlerHelper.copyStackWithSize(existing, existing.getCount() - toExtract));
				markDirty();
			}
			
			return ItemHandlerHelper.copyStackWithSize(existing, toExtract);
		}
	}
	
	protected void validateSlotIndex(int slot)
	{
		if (slot < 0 || slot >= chestContents.size()) throw new RuntimeException("Slot " + slot + " not in valid range - [0," + chestContents.size() + ")");
	}
	
	@Override
	public int getSlotLimit(int slot)
	{
		return 64;
	}
}

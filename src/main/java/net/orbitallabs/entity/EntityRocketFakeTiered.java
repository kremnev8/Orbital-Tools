
package net.orbitallabs.entity;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.api.entity.ICameraZoomEntity;
import micdoodle8.mods.galacticraft.api.entity.IEntityNoisy;
import micdoodle8.mods.galacticraft.api.entity.IIgnoreShift;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.network.IPacketReceiver;
import micdoodle8.mods.galacticraft.core.network.PacketDynamic;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.planets.asteroids.items.AsteroidsItems;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.entity.choreo.ChoreoScene;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.RocketControlsPacket;
import net.orbitallabs.network.packets.StartChoreoClientPacket;
import net.orbitallabs.network.packets.SyncRocketTierPacket;
import net.orbitallabs.tiles.TileEntityDockingPort;

public class EntityRocketFakeTiered extends Entity implements IIgnoreShift, ICameraZoomEntity, IEntityNoisy, IPacketReceiver {
	public static enum EnumLaunchPhase
	{
		DOCKED, UNDOCKED,
		
		NOTROTATED, ROTATED,
		
		FLYAWAY, LAUNCHED
	}
	
	public static enum EnumEngineState
	{
		UNIGNITED, IGNITED
	}
	
	protected long ticks = 0;
	public float shipDamage;
	public int launchPhase;
	public int engineState;
	public FluidTank fuelTank = new FluidTank(this.getFuelTankCapacity() * ConfigManagerCore.rocketFuelFactor);
	
	public int rocketTier = 1;
	private int lasttier = 0;
	private boolean justSpw;
	public TileEntityDockingPort dockport;
	private int[] dockPos;
	
	public long choreoStartTick = 0;
	
	public double ActialMotionX = 0;
	public double ActialMotionZ = 0;
	
	public float timeSinceIgnition;
	
	public ChoreoScene currentChoreo;
	private float yOffset;
	private ITickable rocketSoundUpdater;
	
	//protected IUpdatePlayerListBox rocketSoundUpdater;
	
	public EntityRocketFakeTiered(World par1World)
	{
		super(par1World);
		this.launchPhase = EnumLaunchPhase.DOCKED.ordinal();
		this.preventEntitySpawning = true;
		this.ignoreFrustumCheck = true;
		//this.yOffset = 2.2F;
		this.setSize(1.2F, 3.2F);
	}
	
	public EntityRocketFakeTiered(World world, double posX, double posY, double posZ, int tier)
	{
		this(world);
		this.setTier(tier);
		if (tier == 1)
		{
			this.setSize(1.2F, 3.5F);
		} else if (tier == 2)
		{
			this.setSize(1.2F, 4.5F);
		} else if (tier == 3)
		{
			this.setSize(1.8F, 6F);
		}
		this.yOffset = 2.2F;
		this.setPosition(posX, posY, posZ);
		this.motionX = 0.0D;
		this.motionY = 0.0D;
		this.motionZ = 0.0D;
		this.prevPosX = posX;
		this.prevPosY = posY;
		this.prevPosZ = posZ;
		
		this.isImmuneToFire = true;
		this.justSpw = true;
		
	}
	
	public EntityRocketFakeTiered(World world, double posX, double posY, double posZ, int tier, TileEntityDockingPort te)
	{
		this(world, posX, posY, posZ, tier);
		dockport = te;
	}
	
	@Override
	public boolean isInRangeToRenderDist(double distance)
	{
		double d0 = this.getEntityBoundingBox().getAverageEdgeLength();
		
		if (Double.isNaN(d0))
		{
			d0 = 1.0D;
		}
		
		d0 = d0 * 64.0D * 5.0;
		return distance < d0 * d0;
	}
	
	public void setTier(int tier)
	{
		this.rocketTier = tier;
	}
	
	public int getTier()
	{
		return rocketTier;
	}
	
	public static int getTierFromItem(ItemStack is)
	{
		if (is.getItem() == GCItems.rocketTier1)
		{
			return 1;
		} else if (is.getItem() == MarsItems.rocketMars)
		{
			return 2;
		} else if (is.getItem() == AsteroidsItems.tier3Rocket)
		{
			return 3;
		} else return -1;
	}
	
	public void setSize(float width, float height)
	{
		if (width != this.width || height != this.height)
		{
			float f = this.width;
			this.width = width;
			this.height = height;
			
			if (this.width < f)
			{
				double d0 = (double) width / 2.0D;
				this.setEntityBoundingBox(new AxisAlignedBB(this.posX - d0, this.posY, this.posZ - d0, this.posX + d0, this.posY + (double) this.height, this.posZ + d0));
				return;
			}
			
			AxisAlignedBB axisalignedbb = this.getEntityBoundingBox();
			this.setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double) this.width,
					axisalignedbb.minY + (double) this.height, axisalignedbb.minZ + (double) this.width));
			
			if (this.width > f && !this.firstUpdate && !this.world.isRemote)
			{
				this.move(MoverType.SELF, (double) (f - this.width), 0.0D, (double) (f - this.width));
			}
		}
	}
	
	public int getFuelTankCapacity()
	{
		return 1000 + (this.getTier() > 1 ? 500 : 0);
	}
	
	public int getScaledFuelLevel(int scale)
	{
		if (this.getFuelTankCapacity() <= 0)
		{
			return 0;
		}
		if (dockport != null)
		{
			return this.dockport.fuelTank.getFluidAmount() * scale / this.getFuelTankCapacity() / ConfigManagerCore.rocketFuelFactor;
		}
		return 0;
	}
	
	public boolean hasValidFuel()
	{
		if (dockport != null)
		{
			return this.dockport.fuelTank.getFluidAmount() > 500;
		}
		return false;
	}
	
	public FluidStack removeFuel(int amount)
	{
		return this.fuelTank.drain(amount * ConfigManagerCore.rocketFuelFactor, true);
	}
	
	public Entity getRider()
	{
		return this.getPassengers().size() > 0 ? getPassengers().get(0) : null;
	}
	
	@Override
	public boolean processInitialInteract(EntityPlayer player, EnumHand hand)
	{
		if (hand == EnumHand.OFF_HAND) return false;
		if (this.launchPhase == EnumLaunchPhase.LAUNCHED.ordinal())
		{
			return false;
		}
		
		if (getRider() != null && getRider() instanceof EntityPlayerMP)
		{
			if (!this.world.isRemote && !shouldIgnoreShiftExit())
			{
				GalacticraftCore.packetPipeline.sendTo(new PacketSimple(EnumSimplePacket.C_RESET_THIRD_PERSON, this.world.provider.getDimension(), new Object[] {}),
						(EntityPlayerMP) player);
				GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
				stats.setChatCooldown(0);
				player.startRiding(null);
				if (dockport != null)
				{
					player.setPositionAndUpdate(player.posX, dockport.getPos().getY() + 1, player.posZ);
				} else player.setPositionAndUpdate(player.posX, player.posY + 4, player.posZ);
			}
			
			return true;
		} else if (player instanceof EntityPlayerMP)
		{
			if (!this.world.isRemote)
			{
				PacketHandler.sendTo(new RocketControlsPacket(), (EntityPlayerMP) player);
				GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
				stats.setChatCooldown(0);
				player.startRiding(this);
				
			}
			
			return true;
		}
		
		return false;
	}
	
	public void QuitRocket(EntityPlayer player)
	{
		if (getRider() != null && getRider() instanceof EntityPlayerMP)
		{
			if (!this.world.isRemote)
			{
				GalacticraftCore.packetPipeline.sendTo(new PacketSimple(EnumSimplePacket.C_RESET_THIRD_PERSON, this.world.provider.getDimension(), new Object[] {}),
						(EntityPlayerMP) player);
				GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
				stats.setChatCooldown(0);
				this.dismountRidingEntity();
				if (dockport != null)
				{
					player.setPositionAndUpdate(player.posX, dockport.getPos().getY() + 1, player.posZ);
				} else player.setPositionAndUpdate(player.posX, player.posY + 4, player.posZ);
			}
		}
	}
	
	public void EnterRocket(EntityPlayer player)
	{
		if (player instanceof EntityPlayerMP)
		{
			if (!this.world.isRemote)
			{
				PacketHandler.sendTo(new RocketControlsPacket(), (EntityPlayerMP) player);
				//   GalacticraftCore.packetPipeline.sendTo(new PacketSimple(EnumSimplePacket.C_DISPLAY_ROCKET_CONTROLS, new Object[] { }), (EntityPlayerMP) player);
				GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
				stats.setChatCooldown(0);
				player.startRiding(this);
			}
		}
	}
	
	@Override
	public void updatePassenger(Entity passenger)
	{
		if (passenger != null)
		{
			passenger.setPosition(this.posX, this.posY + this.getMountedYOffset() + passenger.getYOffset(), this.posZ);
		}
	}
	
	@Override
	protected boolean canTriggerWalking()
	{
		return false;
	}
	
	@Override
	protected void entityInit()
	{
	}
	
	@Override
	public boolean canBePushed()
	{
		return false;
	}
	
	public float getRotateOffset()
	{
		if (this.getTier() == 1 || this.getTier() == 2)
		{
			return -1.5F;
		} else if (this.getTier() == 3)
		{
			return 0.35F;
		} else return 0;
	}
	
	protected void spawnParticles(boolean launched)
	{
		if (!this.isDead)
		{
			if (this.rocketTier == 3)
			{
				double x1 = 3.2 * Math.cos(this.rotationYaw / 57.2957795D) * Math.sin(this.rotationPitch / 57.2957795D);
				double z1 = 3.2 * Math.sin(this.rotationYaw / 57.2957795D) * Math.sin(this.rotationPitch / 57.2957795D);
				double y1 = 2.9 * Math.cos((this.rotationPitch - 180) * Math.PI / 180.0D);
				
				final double y2 = this.prevPosY + (this.posY - this.prevPosY) + y1 - 2;
				
				final double x2 = this.posX + x1;
				final double z2 = this.posZ + z1;
				Vector3 motionVec = new Vector3(x1, y1, z1);
				Vector3 d1 = new Vector3(y1 * 0.1D, -x1 * 0.1D, z1 * 0.1D).rotate(315 - this.rotationYaw, motionVec);
				Vector3 d2 = new Vector3(x1 * 0.1D, -z1 * 0.1D, y1 * 0.1D).rotate(315 - this.rotationYaw, motionVec);
				Vector3 d3 = new Vector3(-y1 * 0.1D, x1 * 0.1D, z1 * 0.1D).rotate(315 - this.rotationYaw, motionVec);
				Vector3 d4 = new Vector3(x1 * 0.1D, z1 * 0.1D, -y1 * 0.1D).rotate(315 - this.rotationYaw, motionVec);
				Vector3 mv1 = motionVec.clone().translate(d1);
				Vector3 mv2 = motionVec.clone().translate(d2);
				Vector3 mv3 = motionVec.clone().translate(d3);
				Vector3 mv4 = motionVec.clone().translate(d4);
				//T3 - Four flameballs which spread
				makeFlame(x2 + d1.x, y2 + d1.y, z2 + d1.z, mv1, this.getLaunched());
				makeFlame(x2 + d2.x, y2 + d2.y, z2 + d2.z, mv2, this.getLaunched());
				makeFlame(x2 + d3.x, y2 + d3.y, z2 + d3.z, mv3, this.getLaunched());
				makeFlame(x2 + d4.x, y2 + d4.y, z2 + d4.z, mv4, this.getLaunched());
				
			} else if (this.rocketTier == 2)
			{
				double x1 = 2.9 * Math.cos(this.rotationYaw * Math.PI / 180.0D) * Math.sin(this.rotationPitch * Math.PI / 180.0D);
				double z1 = 2.9 * Math.sin(this.rotationYaw * Math.PI / 180.0D) * Math.sin(this.rotationPitch * Math.PI / 180.0D);
				double y1 = 2.9 * Math.cos((this.rotationPitch - 180) * Math.PI / 180.0D);
				
				final double y = this.prevPosY + (this.posY - this.prevPosY) + y1;
				final double x2 = this.posX + x1;
				final double z2 = this.posZ + z1;
				final double x3 = x2 + x1 / 2D;
				final double y3 = y + y1 / 2D;
				final double z3 = z2 + z1 / 2D;
				Vector3 motionVec = new Vector3(x1, y1, z1);
				if (this.getLaunched())
				{
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 + 0.4 - this.rand.nextDouble() / 10, y, z2 + 0.4 - this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 - 0.4 + this.rand.nextDouble() / 10, y, z2 + 0.4 - this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 - 0.4 + this.rand.nextDouble() / 10, y, z2 - 0.4 + this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 + 0.4 - this.rand.nextDouble() / 10, y, z2 - 0.4 + this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2, y, z2), motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 + 0.4, y, z2), motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 - 0.4, y, z2), motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2, y, z2 + 0.4D), motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2, y, z2 - 0.4D), motionVec, new Object[] { getRidingEntity() });
					//Larger flameball for T2 - positioned behind the smaller one
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 + 0.2 - this.rand.nextDouble() / 8, y3 + 0.4, z3 + 0.2 - this.rand.nextDouble() / 8),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 - 0.2 + this.rand.nextDouble() / 8, y3 + 0.4, z3 + 0.2 - this.rand.nextDouble() / 8),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 - 0.2 + this.rand.nextDouble() / 8, y3 + 0.4, z3 - 0.2 + this.rand.nextDouble() / 8),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 + 0.2 - this.rand.nextDouble() / 8, y3 + 0.4, z3 - 0.2 + this.rand.nextDouble() / 8),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 + 0.2 - this.rand.nextDouble() / 8, y3 - 0.4, z3 + 0.2 - this.rand.nextDouble() / 8),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 - 0.2 + this.rand.nextDouble() / 8, y3 - 0.4, z3 + 0.2 - this.rand.nextDouble() / 8),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 - 0.2 + this.rand.nextDouble() / 8, y3 - 0.4, z3 - 0.2 + this.rand.nextDouble() / 8),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 + 0.2 - this.rand.nextDouble() / 8, y3 - 0.4, z3 - 0.2 + this.rand.nextDouble() / 8),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 + 0.7 - this.rand.nextDouble() / 10, y3, z3 + 0.7 - this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 - 0.7 + this.rand.nextDouble() / 10, y3, z3 + 0.7 - this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 - 0.7 + this.rand.nextDouble() / 10, y3, z3 - 0.7 + this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 + 0.7 - this.rand.nextDouble() / 10, y3, z3 - 0.7 + this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 + 0.7 - this.rand.nextDouble() / 10, y3, z3 - this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 - 0.7 + this.rand.nextDouble() / 10, y3, z3 - this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 + this.rand.nextDouble() / 10, y3, z3 + 0.7 + this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x3 - this.rand.nextDouble() / 10, y3, z3 - 0.7 + this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					
				}
			} else
			{
				double x1 = 2 * Math.cos(this.rotationYaw * Math.PI / 180.0D) * Math.sin(this.rotationPitch * Math.PI / 180.0D);
				double z1 = 2 * Math.sin(this.rotationYaw * Math.PI / 180.0D) * Math.sin(this.rotationPitch * Math.PI / 180.0D);
				double y1 = 2 * Math.cos((this.rotationPitch - 180) * Math.PI / 180.0D);
				
				final double y = this.prevPosY + (this.posY - this.prevPosY) + y1;
				
				final double x2 = this.posX + x1;
				final double z2 = this.posZ + z1;
				
				if (this.getLaunched())
				{
					Vector3 motionVec = new Vector3(x1, y1, z1);
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 + 0.4 - this.rand.nextDouble() / 10, y, z2 + 0.4 - this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 - 0.4 + this.rand.nextDouble() / 10, y, z2 + 0.4 - this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 - 0.4 + this.rand.nextDouble() / 10, y, z2 - 0.4 + this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 + 0.4 - this.rand.nextDouble() / 10, y, z2 - 0.4 + this.rand.nextDouble() / 10),
							motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2, y, z2), motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 + 0.4, y, z2), motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 - 0.4, y, z2), motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2, y, z2 + 0.4D), motionVec, new Object[] { getRidingEntity() });
					GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2, y, z2 - 0.4D), motionVec, new Object[] { getRidingEntity() });
					
				}
			}
		}
	}
	
	private void makeFlame(double x2, double y2, double z2, Vector3 motionVec, boolean getLaunched)
	{
		if (getLaunched)
		{
			GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 + 0.4 - this.rand.nextDouble() / 10, y2, z2 + 0.4 - this.rand.nextDouble() / 10), motionVec,
					new Object[] { getRidingEntity() });
			GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 - 0.4 + this.rand.nextDouble() / 10, y2, z2 + 0.4 - this.rand.nextDouble() / 10), motionVec,
					new Object[] { getRidingEntity() });
			GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 - 0.4 + this.rand.nextDouble() / 10, y2, z2 - 0.4 + this.rand.nextDouble() / 10), motionVec,
					new Object[] { getRidingEntity() });
			GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 + 0.4 - this.rand.nextDouble() / 10, y2, z2 - 0.4 + this.rand.nextDouble() / 10), motionVec,
					new Object[] { getRidingEntity() });
			GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2, y2, z2), motionVec, new Object[] { getRidingEntity() });
			GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 + 0.4, y2, z2), motionVec, new Object[] { getRidingEntity() });
			GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2 - 0.4, y2, z2), motionVec, new Object[] { getRidingEntity() });
			GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2, y2, z2 + 0.4D), motionVec, new Object[] { getRidingEntity() });
			GalacticraftCore.proxy.spawnParticle("launchFlameLaunched", new Vector3(x2, y2, z2 - 0.4D), motionVec, new Object[] { getRidingEntity() });
			return;
		}
		
		double x1 = motionVec.x;
		double y1 = motionVec.y;
		double z1 = motionVec.z;
		GalacticraftCore.proxy.spawnParticle("launchFlameIdle", new Vector3(x2 + 0.4 - this.rand.nextDouble() / 10, y2, z2 + 0.4 - this.rand.nextDouble() / 10),
				new Vector3(x1 + 0.5D, y1 - 0.3D, z1 + 0.5D), new Object[] { getRidingEntity() });
		GalacticraftCore.proxy.spawnParticle("launchFlameIdle", new Vector3(x2 - 0.4 + this.rand.nextDouble() / 10, y2, z2 + 0.4 - this.rand.nextDouble() / 10),
				new Vector3(x1 - 0.5D, y1 - 0.3D, z1 + 0.5D), new Object[] { getRidingEntity() });
		GalacticraftCore.proxy.spawnParticle("launchFlameIdle", new Vector3(x2 - 0.4 + this.rand.nextDouble() / 10, y2, z2 - 0.4 + this.rand.nextDouble() / 10),
				new Vector3(x1 - 0.5D, y1 - 0.3D, z1 - 0.5D), new Object[] { getRidingEntity() });
		GalacticraftCore.proxy.spawnParticle("launchFlameIdle", new Vector3(x2 + 0.4 - this.rand.nextDouble() / 10, y2, z2 - 0.4 + this.rand.nextDouble() / 10),
				new Vector3(x1 + 0.5D, y1 - 0.3D, z1 - 0.5D), new Object[] { getRidingEntity() });
		GalacticraftCore.proxy.spawnParticle("launchFlameIdle", new Vector3(x2 + 0.4, y2, z2), new Vector3(x1 + 0.8D, y1 - 0.3D, z1), new Object[] { getRidingEntity() });
		GalacticraftCore.proxy.spawnParticle("launchFlameIdle", new Vector3(x2 - 0.4, y2, z2), new Vector3(x1 - 0.8D, y1 - 0.3D, z1), new Object[] { getRidingEntity() });
		GalacticraftCore.proxy.spawnParticle("launchFlameIdle", new Vector3(x2, y2, z2 + 0.4D), new Vector3(x1, y1 - 0.3D, z1 + 0.8D), new Object[] { getRidingEntity() });
		GalacticraftCore.proxy.spawnParticle("launchFlameIdle", new Vector3(x2, y2, z2 - 0.4D), new Vector3(x1, y1 - 0.3D, z1 - 0.8D), new Object[] { getRidingEntity() });
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		if (!this.isDead && !par1DamageSource.isFireDamage())
		{
			boolean flag = par1DamageSource.getEntity() instanceof EntityPlayer && ((EntityPlayer) par1DamageSource.getEntity()).capabilities.isCreativeMode;
			Entity e = par1DamageSource.getEntity();
			if (this.isEntityInvulnerable(par1DamageSource) || this.posY > 300 || (e instanceof EntityLivingBase && !(e instanceof EntityPlayer)))
			{
				return false;
			} else
			{
				this.setBeenAttacked();
				this.shipDamage += par2 * 10;
				
				if (e instanceof EntityPlayer && ((EntityPlayer) e).capabilities.isCreativeMode)
				{
					this.shipDamage = 100;
				}
				
				if (flag || this.shipDamage > 90)
				{
					if (getRider() != null)
					{
						QuitRocket((EntityPlayer) getRider());
					}
					
					if (flag)
					{
						this.setDead();
					} else
					{
						this.setDead();
						this.dropShipAsItem();
					}
					return true;
				}
				
				return true;
			}
		}
		return true;
	}
	
	public void dropShipAsItem()
	{
		if (this.world.isRemote)
		{
			return;
		}
		
		for (final ItemStack item : this.getItemsDropped(new ArrayList<ItemStack>()))
		{
			EntityItem entityItem = this.entityDropItem(item, 0);
			
			if (item.hasTagCompound())
			{
				entityItem.getEntityItem().setTagCompound((NBTTagCompound) item.getTagCompound().copy());
			}
		}
	}
	
	public List<ItemStack> getItemsDropped(List<ItemStack> droppedItemList)
	{
		if (this.getTier() == 1) droppedItemList.add(new ItemStack(GCItems.rocketTier1));
		else if (this.getTier() == 2) droppedItemList.add(new ItemStack(MarsItems.rocketMars));
		else if (this.getTier() == 3) droppedItemList.add(new ItemStack(AsteroidsItems.tier3Rocket));
		return droppedItemList;
	}
	
	@Override
	public boolean canBeCollidedWith()
	{
		return !this.isDead;
	}
	
	@Override
	public boolean shouldRiderSit()
	{
		return false;
	}
	
	@Override
	public void onUpdate()
	{
		
		if (this.ticks >= Long.MAX_VALUE)
		{
			this.ticks = 0;
		}
		this.ticks++;
		
		if (currentChoreo != null && currentChoreo.choreoStarted)
		{
			currentChoreo.TickChoreo((int) (ticks - choreoStartTick));
		}
		
		if (lasttier != rocketTier && !this.world.isRemote)
		{
			PacketHandler.sendToAllAround(new SyncRocketTierPacket(this), new TargetPoint(this.world.provider.getDimension(), this.posX, this.posY, this.posZ, 20));
			lasttier = rocketTier;
		}
		
		super.onUpdate();
		
		if (this.getPassengers().size() > 0 && getRider() != null)
		{
			getRider().fallDistance = 0.0F;
			
			if (!this.world.isRemote && this.dockport == null && dockPos != null && dockPos.length > 0)
			{
				dockport = (TileEntityDockingPort) this.world.getTileEntity(new BlockPos(dockPos[0], dockPos[1], dockPos[2]));
			}
			
		}
		
		if (this.launchPhase == EnumEngineState.IGNITED.ordinal())
		{
			this.timeSinceIgnition++;
		} else
		{
			this.timeSinceIgnition = 0;
		}
		
		if ((this.getLaunched() || this.engineState == EnumEngineState.IGNITED.ordinal()) && !ConfigManagerCore.disableSpaceshipParticles)
		{
			if (this.world.isRemote)
			{
				this.spawnParticles(this.getLaunched());
			}
		}
		
		double multiplier = 1.0D;
		
		if (this.world.provider instanceof IGalacticraftWorldProvider)
		{
			multiplier = ((IGalacticraftWorldProvider) this.world.provider).getFuelUsageMultiplier();
			
			if (multiplier <= 0)
			{
				multiplier = 1;
			}
		}
		
		if (this.timeSinceIgnition % MathHelper.floor(2 * (1 / multiplier)) == 0)
		{
			this.removeFuel(1);
			if (!this.hasValidFuel() && rocketSoundUpdater != null) ((SoundUpdateRocketFake) rocketSoundUpdater).stopRocketSound();
		}
		
		if (this.shipDamage > 0)
		{
			this.shipDamage--;
		}
		
		if (!this.world.isRemote)
		{
			if (this.posY < 0.0D)
			{
				this.kill();
			}
			
		}
		
		AxisAlignedBB box = this.getEntityBoundingBox().expand(0.2D, 0.2D, 0.2D);
		
		final List<?> var15 = this.world.getEntitiesWithinAABBExcludingEntity(this, box);
		
		if (var15 != null && !var15.isEmpty())
		{
			for (int var52 = 0; var52 < var15.size(); ++var52)
			{
				final Entity var17 = (Entity) var15.get(var52);
				
				if (!this.getPassengers().contains(var17))
				{
					var17.applyEntityCollision(this);
				}
			}
		}
		
		if (this.rotationPitch > 90)
		{
			this.rotationPitch = 90;
		}
		
		if (this.rotationPitch < -90)
		{
			this.rotationPitch = -90;
		}
		
		if (this.engineState == EnumEngineState.IGNITED.ordinal())
		{
			this.motionX = -(50 * Math.cos(this.rotationYaw * Math.PI / 180.0D) * Math.sin(this.rotationPitch * 0.01 * Math.PI / 180.0D)) * 5;
			this.motionZ = -(50 * Math.sin(this.rotationYaw * Math.PI / 180.0D) * Math.sin(this.rotationPitch * 0.01 * Math.PI / 180.0D)) * 5;
		} else
		{
			if (this.launchPhase != EnumLaunchPhase.FLYAWAY.ordinal())
			{
				this.motionX = ActialMotionX;
				this.motionZ = ActialMotionZ;
			}
		}
		
		if (getRider() != null && launchPhase != EnumLaunchPhase.DOCKED.ordinal())
		{
			EntityPlayer player = (EntityPlayer) getRider();
		}
		if (this.world.isRemote)
		{
			this.setPosition(this.posX, this.posY, this.posZ);
			
			if (this.shouldMoveClientSide())
			{
				this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
			}
		} else
		{
			this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		}
		
		this.setRotation(this.rotationYaw, this.rotationPitch);
		
		if (this.world.isRemote)
		{
			this.setPosition(this.posX, this.posY, this.posZ);
		}
		
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		if (this.launchPhase != EnumLaunchPhase.DOCKED.ordinal() || this.getLaunched())
		{
			if (this.rocketSoundUpdater != null)
			{
				this.rocketSoundUpdater.update();
			}
		}
		if (!this.world.isRemote && (this.ticks % 100 == 0 || this.ticks < 5))
		{
			GalacticraftCore.packetPipeline.sendToDimension(new PacketDynamic(this), this.world.provider.getDimension());
		}
	}
	
	protected boolean shouldMoveClientSide()
	{
		return true;
	}
	
	public void turnYaw(float f)
	{
		this.rotationYaw += f;
	}
	
	public void turnPitch(float f)
	{
		this.rotationPitch += f;
	}
	
	public void ActMoveEntity(double x, double y, double z)
	{
		this.ActialMotionX += x;
		this.ActialMotionZ += z;
		this.motionY += z;
	}
	
	@Override
	protected void writeEntityToNBT(NBTTagCompound nbt)
	{
		nbt.setInteger("launchPhase", this.launchPhase + 1);
		nbt.setInteger("TIER", rocketTier);
		if (dockport != null) nbt.setIntArray("TILE", new int[] { dockport.getPos().getX(), dockport.getPos().getY(), dockport.getPos().getZ() });
	}
	
	@Override
	protected void readEntityFromNBT(NBTTagCompound nbt)
	{
		this.setLaunchPhase(EnumLaunchPhase.values()[nbt.getInteger("launchPhase") - 1]);
		rocketTier = nbt.getInteger("TIER");
		dockPos = nbt.getIntArray("TILE");
	}
	
	public void startChoreoScene(ChoreoScene scene)
	{
		if (!this.world.isRemote)
		{
			PacketHandler.sendToDimension(new StartChoreoClientPacket(this, scene), this.world.provider.getDimension());
		}
		scene.StartChoreo();
		this.currentChoreo = scene;
		this.choreoStartTick = ticks;
		currentChoreo.TickChoreo(0);
	}
	
	public boolean getLaunched()
	{
		return this.engineState == EnumEngineState.IGNITED.ordinal();
	}
	
	public void ignite()
	{
		this.engineState = EnumEngineState.IGNITED.ordinal();
	}
	
	public void unignite()
	{
		this.engineState = EnumEngineState.UNIGNITED.ordinal();
	}
	
	@Override
	public double getMountedYOffset()
	{//-0.15D;
		if (this.rocketTier == 2 || this.rocketTier == 3)
		{
			return 0.7D;
		}
		return 0.4D;
		
	}
	
	public void onReachAtmosphere()
	{
		if (dockport != null && getRider() != null)
		{
			EntityPlayer player = (EntityPlayer) getRider();
			GCPlayerStats stats = GCPlayerStats.get((EntityPlayerMP) player);
			NonNullList<ItemStack> rcStacks = NonNullList.withSize(2 + dockport.addSlots, ItemStack.EMPTY);
			
			if (dockport.getStackInSlot(dockport.getSizeInventory() - 3) != null)
			{
				rcStacks.set(rcStacks.size() - 2, dockport.getStackInSlot(dockport.getSizeInventory() - 3).copy());
				dockport.setInventorySlotContents(dockport.getSizeInventory() - 3, null);
			}
			if (dockport.getStackInSlot(dockport.getSizeInventory() - 2) != null)
			{
				rcStacks.set(rcStacks.size() - 2, dockport.getStackInSlot(dockport.getSizeInventory() - 2).copy());
				dockport.setInventorySlotContents(dockport.getSizeInventory() - 2, null);
			}
			
			if (dockport.addSlots != 0)
			{
				for (int i = 0; i < dockport.addSlots; i++)
				{
					ItemStack stack = dockport.getStackInSlot(i);
					if (stack != null)
					{
						stack = stack.copy();
						if (i < rcStacks.size())
						{
							rcStacks.set(i, stack);
						}
						dockport.setInventorySlotContents(i, null);
					}
					
				}
			}
			stats.setRocketStacks(rcStacks);
			stats.setFuelLevel(dockport.fuelTank.getFluidAmount());
			
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void spawnParticle(String var1, double var2, double var4, double var6, double var8, double var10, double var12)
	{
	}
	
	@Override
	public boolean canRiderInteract()
	{
		return true;
	}
	
	@Override
	protected boolean canFitPassenger(Entity passenger)
	{
		return true;
	}
	
	public void setLaunchPhase(EnumLaunchPhase phase)
	{
		this.launchPhase = phase.ordinal();
	}
	
	@Override
	public boolean shouldIgnoreShiftExit()
	{
		return this.launchPhase != EnumLaunchPhase.DOCKED.ordinal();
	}
	
	public void adjustDisplay(int[] data)
	{
		GL11.glRotatef(data[4], -1, 0, 0);
		GL11.glTranslatef(0, this.height / 4, 0);
	}
	
	@Override
	public float getCameraZoom()
	{
		return 15.0F;
	}
	
	@Override
	public boolean defaultThirdPerson()
	{
		return true;
	}
	
	public boolean canBreath()
	{
		return true;
	}
	
	@Override
	public void setDead()
	{
		super.setDead();
		
		if (this.rocketSoundUpdater != null)
		{
			this.rocketSoundUpdater.update();
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ITickable getSoundUpdater()
	{
		return this.rocketSoundUpdater;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public ISound setSoundUpdater(EntityPlayerSP player)
	{
		this.rocketSoundUpdater = new SoundUpdateRocketFake(player, this);
		return (ISound) this.rocketSoundUpdater;
	}
	
	@Override
	public void getNetworkedData(ArrayList<Object> sendData)
	{
		sendData.add(this.getTier());
		sendData.add(this.posX);
		sendData.add(this.posY);
		sendData.add(this.posZ);
		
	}
	
	@Override
	public void decodePacketdata(ByteBuf buffer)
	{
		this.setTier(buffer.readInt());
		this.posX = buffer.readDouble();
		this.posY = buffer.readDouble();
		this.posZ = buffer.readDouble();
	}
	
	public static float getDockingOffset(int tier)
	{
		float hight = 3.3F;
		if (tier == 2) hight = 4.3F;
		else if (tier == 3) hight = 3.9F;
		return hight;
		
	}
	
	public float getRenderOffsetY()
	{
		if (this.getTier() == 3)
		{
			return 0F;
		}
		if (this.getTier() == 1)
		{
			return 1.5F;
		} else return 1.1F;
	}
	
}
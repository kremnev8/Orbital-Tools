package net.orbitallabs.dimensions;

import java.util.List;
import micdoodle8.mods.galacticraft.api.event.ZeroGravityEvent;
import micdoodle8.mods.galacticraft.api.prefab.entity.EntitySpaceshipBase;
import micdoodle8.mods.galacticraft.api.world.IZeroGDimension;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.TransformerHooks;
import micdoodle8.mods.galacticraft.core.dimension.SpinManager;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderSpaceStation;
import micdoodle8.mods.galacticraft.core.entities.EntityLanderBase;
import micdoodle8.mods.galacticraft.core.entities.player.FreefallHandler;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStatsClient;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.items.ISpaceJetpack;
import net.orbitallabs.items.ItemSpaceJetpack;

public class FreefallAdvHandler extends FreefallHandler {
	private double pPrevMotionX;
	//public double pPrevMotionY;
	private double pPrevMotionZ;
	private float jetpackBoost;
	private double pPrevdY;
	//public boolean sneakLast;
	//public boolean onWall;
	
	//public int pjumpticks = 0;
	private GCPlayerStatsClient stats;
	private int flyToggleTimer;
	
	public FreefallAdvHandler(GCPlayerStatsClient statsClientCapability)
	{
		super(statsClientCapability);
		stats = statsClientCapability;
	}
	
	public boolean testFreefall(EntityPlayer player)
	{
		
		ZeroGravityEvent zeroGEvent = new ZeroGravityEvent.InFreefall(player);
		MinecraftForge.EVENT_BUS.post(zeroGEvent);
		if (zeroGEvent.isCanceled())
		{
			return false;
		}
		if (player.world.provider instanceof WorldProviderOrbitModif)
		{
			WorldProviderOrbitModif orbit = (WorldProviderOrbitModif) player.world.provider;
			if (orbit.artificialG > WorldProviderOrbitModif.MIN_GRAVITY_VAL)
			{
				return false;
			}
		}
		
		//Test whether feet are on a block, also stops the login glitch
		int playerFeetOnY = (int) (player.getEntityBoundingBox().minY - 0.01D);
		int xx = MathHelper.floor(player.posX);
		int zz = MathHelper.floor(player.posZ);
		BlockPos pos = new BlockPos(xx, playerFeetOnY, zz);
		IBlockState state = player.world.getBlockState(pos);
		Block b = state.getBlock();
		if (b.getMaterial(state) != Material.AIR && !(b instanceof BlockLiquid))
		{
			double blockYmax = playerFeetOnY + b.getBoundingBox(state, player.world, pos).maxY;
			if (player.getEntityBoundingBox().minY - blockYmax < 0.01D && player.getEntityBoundingBox().minY - blockYmax > -0.5D)
			{
				player.onGround = true;
				if (player.getEntityBoundingBox().minY - blockYmax > 0D)
				{
					player.posY -= player.getEntityBoundingBox().minY - blockYmax;
					player.setEntityBoundingBox(player.getEntityBoundingBox().offset(0, blockYmax - player.getEntityBoundingBox().minY, 0));
				} else if (b.canCollideCheck(player.world.getBlockState(new BlockPos(xx, playerFeetOnY, zz)), false))
				{
					BlockPos offsetPos = new BlockPos(xx, playerFeetOnY, zz);
					AxisAlignedBB collisionBox = b.getCollisionBoundingBox(player.world.getBlockState(offsetPos), player.world, offsetPos);
					if (collisionBox != null && collisionBox.intersectsWith(player.getEntityBoundingBox()))
					{
						player.posY -= player.getEntityBoundingBox().minY - blockYmax;
						player.setEntityBoundingBox(player.getEntityBoundingBox().offset(0, blockYmax - player.getEntityBoundingBox().minY, 0));
					}
				}
				return false;
			}
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	private boolean testFreefall(EntityPlayerSP p, boolean flag)
	{
		
		if (super.pjumpticks != pjumpticks)
		{
			super.pjumpticks = pjumpticks;
		}
		World world = p.world;
		WorldProvider worldProvider = world.provider;
		if (!(worldProvider instanceof IZeroGDimension))
		{
			return false;
		}
		ZeroGravityEvent zeroGEvent = new ZeroGravityEvent.InFreefall(p);
		MinecraftForge.EVENT_BUS.post(zeroGEvent);
		if (zeroGEvent.isCanceled())
		{
			return false;
		}
		if (this.pjumpticks > 0 || (stats.isSsOnGroundLast() && p.movementInput.jump))
		{
			return false;
		}
		if (p.world.provider instanceof WorldProviderOrbitModif)
		{
			WorldProviderOrbitModif orbit = (WorldProviderOrbitModif) p.world.provider;
			if (orbit.artificialG >= WorldProviderOrbitModif.MIN_GRAVITY_VAL)
			{
				return false;
			}
		}
		
		if (p.getRidingEntity() != null)
		{
			Entity e = p.getRidingEntity();
			if (e instanceof EntitySpaceshipBase)
			{
				return ((EntitySpaceshipBase) e).getLaunched();
			}
			if (e instanceof EntityLanderBase)
			{
				return false;
			}
			//TODO: should check whether lander has landed (whatever that means)
			//TODO: could check other ridden entities - every entity should have its own freefall check :(
		}
		
		//This is an "on the ground" check
		if (!flag)
		{
			return false;
		} else
		{
			float rY = p.rotationYaw % 360F;
			double zreach = 0D;
			double xreach = 0D;
			if (rY < 80F || rY > 280F)
			{
				zreach = 0.2D;
			}
			if (rY < 170F && rY > 10F)
			{
				xreach = 0.2D;
			}
			if (rY < 260F && rY > 100F)
			{
				zreach = -0.2D;
			}
			if (rY < 350F && rY > 190F)
			{
				xreach = -0.2D;
			}
			AxisAlignedBB playerReach = p.getEntityBoundingBox().addCoord(xreach, 0, zreach);
			
			boolean checkBlockWithinReach;
			if (worldProvider instanceof WorldProviderSpaceStation)
			{
				SpinManager spinManager = ((WorldProviderSpaceStation) worldProvider).getSpinManager();
				checkBlockWithinReach = playerReach.maxX >= spinManager.ssBoundsMinX && playerReach.minX <= spinManager.ssBoundsMaxX && playerReach.maxY >= spinManager.ssBoundsMinY
						&& playerReach.minY <= spinManager.ssBoundsMaxY && playerReach.maxZ >= spinManager.ssBoundsMinZ && playerReach.minZ <= spinManager.ssBoundsMaxZ;
				//Player is somewhere within the space station boundaries
			} else
			{
				checkBlockWithinReach = true;
			}
			
			if (checkBlockWithinReach)
			{
				//Check if the player's bounding box is in the same block coordinates as any non-vacuum block (including torches etc)
				//If so, it's assumed the player has something close enough to grab onto, so is not in freefall
				//Note: breatheable air here means the player is definitely not in freefall
				int xm = MathHelper.floor(playerReach.minX);
				int xx = MathHelper.floor(playerReach.maxX);
				int ym = MathHelper.floor(playerReach.minY);
				int yy = MathHelper.floor(playerReach.maxY);
				int zm = MathHelper.floor(playerReach.minZ);
				int zz = MathHelper.floor(playerReach.maxZ);
				for (int x = xm; x <= xx; x++)
				{
					for (int y = ym; y <= yy; y++)
					{
						for (int z = zm; z <= zz; z++)
						{
							//Blocks.AIR is hard vacuum - we want to check for that, here
							Block b = world.getBlockState(new BlockPos(x, y, z)).getBlock();
							if (Blocks.AIR != b && GCBlocks.brightAir != b)
							{
								this.onWall = true;
								return false;
							}
						}
					}
				}
			}
		}
		
		/*
		if (freefall)
		{
			//If that check didn't produce a result, see if the player is inside the walls
			//TODO: could apply special weightless movement here like Coriolis force - the player is inside the walls,  not touching them, and in a vacuum
			int quadrant = 0;
			double xd = p.posX - this.spinCentreX;
			double zd = p.posZ - this.spinCentreZ;
			if (xd<0)
			{
				if (xd<-Math.abs(zd))
				{
					quadrant = 2;
				} else
					quadrant = (zd<0) ? 3 : 1;
			} else
				if (xd>Math.abs(zd))
				{
					quadrant = 0;
				} else
					quadrant = (zd<0) ? 3 : 1;
		
			int ymin = MathHelper.floor(p.boundingBox.minY)-1;
			int ymax = MathHelper.floor(p.boundingBox.maxY);
			int xmin, xmax, zmin, zmax;
		
			switch (quadrant)
			{
			case 0:
				xmin = MathHelper.floor(p.boundingBox.maxX);
				xmax = this.ssBoundsMaxX - 1;
				zmin = MathHelper.floor(p.boundingBox.minZ)-1;
				zmax = MathHelper.floor(p.boundingBox.maxZ)+1;
				break;
			case 1:
				xmin = MathHelper.floor(p.boundingBox.minX)-1;
				xmax = MathHelper.floor(p.boundingBox.maxX)+1;
				zmin = MathHelper.floor(p.boundingBox.maxZ);
				zmax = this.ssBoundsMaxZ - 1;
				break;
			case 2:
				zmin = MathHelper.floor(p.boundingBox.minZ)-1;
				zmax = MathHelper.floor(p.boundingBox.maxZ)+1;
				xmin = this.ssBoundsMinX;
				xmax = MathHelper.floor(p.boundingBox.minX);
				break;
			case 3:
			default:
				xmin = MathHelper.floor(p.boundingBox.minX)-1;
				xmax = MathHelper.floor(p.boundingBox.maxX)+1;
				zmin = this.ssBoundsMinZ;
				zmax = MathHelper.floor(p.boundingBox.minZ);
				break;
			}
		
			//This block search could cost a lot of CPU (but client side) - maybe optimise later
			BLOCKCHECK0:
			for(int x = xmin; x <= xmax; x++)
				for (int z = zmin; z <= zmax; z++)
					for (int y = ymin; y <= ymax; y++)
						if (Blocks.AIR != this.worldProvider.world.getBlock(x, y, z))
						{
							freefall = false;
							break BLOCKCHECK0;
						}
		}*/
		
		this.onWall = false;
		super.onWall = onWall;
		super.sneakLast = sneakLast;
		super.pPrevMotionY = pPrevMotionY;
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public void setupFreefallPre(EntityPlayerSP p)
	{
		double dY = p.motionY - pPrevMotionY;
		jetpackBoost = 0F;
		pPrevdY = dY;
		pPrevMotionX = p.motionX;
		pPrevMotionY = p.motionY;
		pPrevMotionZ = p.motionZ;
	}
	
	@SideOnly(Side.CLIENT)
	public void freefallMotion(EntityPlayerSP p)
	{
		boolean jetpackUsed = false;
		double dX = p.motionX - pPrevMotionX;
		double dY = p.motionY - pPrevMotionY;
		double dZ = p.motionZ - pPrevMotionZ;
		
		double posOffsetX = -p.motionX;
		double posOffsetY = -p.motionY;
		if (posOffsetY == -TransformerHooks.getGravityForEntity(p)) posOffsetY = 0;
		double posOffsetZ = -p.motionZ;
		//if (p.capabilities.isFlying)
		
		GCPlayerStatsClient stats = GCPlayerStatsClient.get(p);
		///Undo whatever vanilla tried to do to our y motion
		if (dY < 0D && p.motionY != 0.0D)
		{
			p.motionY = pPrevMotionY;
			if (p.world.provider instanceof WorldProviderOrbitModif && ((WorldProviderOrbitModif) p.world.provider).artificialG > 0)
			{
				p.motionY -= (0.08D - (double) ((WorldProviderOrbitModif) p.world.provider).getGravity()) / 2;
			}
		} else if (dY > 0.01D && stats.isInFreefallLast())
		{
			//Impulse upwards - it's probably a jetpack from another mod
			if (dX < 0.01D && dZ < 0.01D)
			{
				float pitch = p.rotationPitch / Constants.RADIANS_TO_DEGREES;
				jetpackBoost = (float) dY * MathHelper.cos(pitch) * 0.1F;
				float factor = 1 + MathHelper.sin(pitch) / 5;
				p.motionY -= dY * factor;
				jetpackUsed = true;
			} else
			{
				p.motionY -= dY / 2;
			}
		}
		
		p.motionX -= dX;
		//        p.motionY -= dY;    //Enabling this will disable jetpacks
		p.motionZ -= dZ;
		
		if (p.movementInput.moveForward != 0)
		{
			p.motionX -= p.movementInput.moveForward * MathHelper.sin(p.rotationYaw / Constants.RADIANS_TO_DEGREES) / (ConfigManagerCore.hardMode ? 600F : 200F);
			p.motionZ += p.movementInput.moveForward * MathHelper.cos(p.rotationYaw / Constants.RADIANS_TO_DEGREES) / (ConfigManagerCore.hardMode ? 600F : 200F);
		}
		
		if (jetpackBoost != 0)
		{
			p.motionX -= jetpackBoost * MathHelper.sin(p.rotationYaw / Constants.RADIANS_TO_DEGREES);
			p.motionZ += jetpackBoost * MathHelper.cos(p.rotationYaw / Constants.RADIANS_TO_DEGREES);
		}
		
		if (p.movementInput.sneak)
		{
			if (!sneakLast)
			{
				//              posOffsetY += 0.0268;
				sneakLast = true;
			}
			p.motionY -= ConfigManagerCore.hardMode ? 0.002D : 0.0032D;
		} else if (sneakLast)
		{
			sneakLast = false;
			//          posOffsetY -= 0.0268;
		}
		
		if (!jetpackUsed && p.movementInput.jump)
		{
			p.motionY += ConfigManagerCore.hardMode ? 0.0002D : 0.00032D;
		}
		
		float speedLimit = ConfigManagerCore.hardMode ? 0.9F : 0.7F;
		
		if (p.motionX > speedLimit)
		{
			p.motionX = speedLimit;
		}
		if (p.motionX < -speedLimit)
		{
			p.motionX = -speedLimit;
		}
		if (p.motionY > speedLimit)
		{
			p.motionY = speedLimit;
		}
		if (p.motionY < -speedLimit)
		{
			p.motionY = -speedLimit;
		}
		if (p.motionZ > speedLimit)
		{
			p.motionZ = speedLimit;
		}
		if (p.motionZ < -speedLimit)
		{
			p.motionZ = -speedLimit;
		}
		
		List<Integer> KeysPressed = ItemSpaceJetpack.KeysPressed;
		
		for (int i = 0; i < p.inventory.armorInventory.size(); i++)
		{
			ItemStack stack = p.inventory.armorItemInSlot(i);
			if (!stack.isEmpty() && stack.getItem() instanceof ISpaceJetpack)
			{
				ISpaceJetpack jetpack = (ISpaceJetpack) stack.getItem();
				jetpack.tickJetpackMovemnt(p, stack);
			}
		}
		
		pPrevMotionX = p.motionX;
		pPrevMotionY = p.motionY;
		pPrevMotionZ = p.motionZ;
		p.move(MoverType.SELF, p.motionX + posOffsetX, p.motionY + posOffsetY, p.motionZ + posOffsetZ);
		
	}
	
	/*              double dyaw = p.rotationYaw - p.prevRotationYaw;
	p.rotationYaw -= dyaw * 0.8D;
	double dyawh = p.rotationYawHead - p.prevRotationYawHead;
	p.rotationYawHead -= dyawh * 0.8D;
	while (p.rotationYaw > 360F)
	{
	    p.rotationYaw -= 360F;
	}
	while (p.rotationYaw < 0F)
	{
	    p.rotationYaw += 360F;
	}
	while (p.rotationYawHead > 360F)
	{
	    p.rotationYawHead -= 360F;
	}
	while (p.rotationYawHead < 0F)
	{
	    p.rotationYawHead += 360F;
	}
	*/
	
	public void updateFreefall(EntityPlayer p)
	{
		pPrevMotionX = p.motionX;
		pPrevMotionY = p.motionY;
		pPrevMotionZ = p.motionZ;
	}
	
	@SideOnly(Side.CLIENT)
	public void preVanillaMotion(EntityPlayerSP p)
	{
		this.setupFreefallPre(p);
		stats.setSsOnGroundLast(p.onGround);
		super.onWall = onWall;
		super.sneakLast = sneakLast;
		super.pPrevMotionY = pPrevMotionY;
	}
	
	@SideOnly(Side.CLIENT)
	public void postVanillaMotion(EntityPlayerSP p)
	{
		World world = p.world;
		WorldProvider worldProvider = world.provider;
		if (!(worldProvider instanceof IZeroGDimension))
		{
			return;
		}
		ZeroGravityEvent zeroGEvent = new ZeroGravityEvent.Motion(p);
		MinecraftForge.EVENT_BUS.post(zeroGEvent);
		if (zeroGEvent.isCanceled())
		{
			return;
		}
		
		boolean freefall = stats.isInFreefall();
		freefall = this.testFreefall(p, freefall);
		stats.setInFreefall(freefall);
		stats.setInFreefallFirstCheck(true);
		
		SpinManager spinManager = null;
		if (worldProvider instanceof WorldProviderSpaceStation)
		{
			spinManager = ((WorldProviderSpaceStation) worldProvider).getSpinManager();
		}
		boolean doCentrifugal = spinManager != null;
		
		if (freefall)
		{
			this.pjumpticks = 0;
			//Reverse effects of deceleration
			p.motionX /= 0.91F;
			p.motionZ /= 0.91F;
			p.motionY /= 0.9800000190734863D;
			
			if (spinManager != null)
			{
				doCentrifugal = spinManager.updatePlayerForSpin(p, 1F);
			}
			
			//Do freefall motion
			if (!p.capabilities.isCreativeMode)
			{
				this.freefallMotion(p);
			} else
			{
				p.capabilities.isFlying = true;
				//Half the normal acceleration in Creative mode
				double dx = p.motionX - this.pPrevMotionX;
				double dy = p.motionY - this.pPrevMotionY;
				double dz = p.motionZ - this.pPrevMotionZ;
				p.motionX -= dx / 2;
				p.motionY -= dy / 2;
				p.motionZ -= dz / 2;
				
				if (p.motionX > 1.2F)
				{
					p.motionX = 1.2F;
				}
				if (p.motionX < -1.2F)
				{
					p.motionX = -1.2F;
				}
				if (p.motionY > 0.7F)
				{
					p.motionY = 0.7F;
				}
				if (p.motionY < -0.7F)
				{
					p.motionY = -0.7F;
				}
				if (p.motionZ > 1.2F)
				{
					p.motionZ = 1.2F;
				}
				if (p.motionZ < -1.2F)
				{
					p.motionZ = -1.2F;
				}
			}
			//TODO: Think about endless drift?
			//Player may run out of oxygen - that will kill the player eventually if can't get back to SS
			//Could auto-kill + respawn the player if floats too far away (config option whether to lose items or not)
			//But we want players to be able to enjoy the view of the spinning space station from the outside
			//Arm and leg movements could start tumbling the player?
		} else
		//Not freefall - within arm's length of something or jumping
		{
			if (this.flyToggleTimer > 0)
			{
				--this.flyToggleTimer;
			}
			double dy = p.motionY - this.pPrevMotionY;
			//OTLoger.logInfo("Jump ticks- " + pjumpticks);
			//if (p.motionY < 0 && this.pPrevMotionY >= 0) p.posY -= p.motionY;
			//if (p.motionY != 0) p.motionY = this.pPrevMotionY;
			float gravmod = 1;
			if (p.world.provider instanceof WorldProviderOrbitModif)
			{
				gravmod = (float) WorldProviderOrbitModif.artificialG;
				if (gravmod < WorldProviderOrbitModif.MIN_GRAVITY_VAL)
				{
					gravmod = WorldProviderOrbitModif.MIN_GRAVITY_VAL;
				}
			}
			
			if (p.onGround && p.capabilities.isCreativeMode)
			{
				p.capabilities.isFlying = false;
			}
			
			boolean flag = p.movementInput.jump;
			p.movementInput.updatePlayerMoveState();
			if (p.movementInput.jump)
			{
				if (p.capabilities.allowFlying && pjumpticks == 0)
				{
					
					if (this.flyToggleTimer == 0)
					{
						this.flyToggleTimer = 7;
					} else
					{
						p.capabilities.isFlying = !p.capabilities.isFlying;
						p.sendPlayerAbilities();
						this.flyToggleTimer = 0;
					}
					this.pjumpticks = -1;
					
				}
				if (p.capabilities.isFlying && Minecraft.getMinecraft().getRenderViewEntity() == p)
				{
					p.motionY += (double) (p.capabilities.getFlySpeed());
				}
				if (p.movementInput.sneak && !p.capabilities.isFlying)
				{
					if ((p.onGround || stats.isSsOnGroundLast()) && !p.capabilities.isCreativeMode)
					{
						if (this.pjumpticks < 25) this.pjumpticks++;
						p.motionY -= dy;
					} else
					{
						p.motionY += 0.015D;
						if (this.pjumpticks == 0)
						{
							p.motionY -= dy;
						}
					}
				} else
				{
					
					if (p.isInWater() && !p.capabilities.isFlying)
					{
						this.handleJumpWater(p, gravmod);
					} else if (p.isInLava() && !p.capabilities.isFlying)
					{
						this.handleJumpLava(p, gravmod);
					} else if ((p.onGround || stats.isSsOnGroundLast()) && pjumpticks <= 0)
					{
						if (!p.capabilities.isFlying) this.jump(p, gravmod);
						this.pjumpticks = 10;
					}
				}
				
			} else
			{
				if (this.pjumpticks > 0)
				{
					if (p.movementInput.sneak)
					{
						p.motionY += 0.0145D / gravmod * 2 * this.pjumpticks;
					}
				}
				if (p.movementInput.sneak)
				{
					if (!p.onGround)
					{
						p.motionY -= 0.015D;
					}
					
				}
				this.pjumpticks = 0;
			}
			if (pjumpticks == -1) pjumpticks = 10;
			if (p.capabilities.allowFlying)
			{
				if (Minecraft.getMinecraft().playerController.isSpectatorMode())
				{
					if (!p.capabilities.isFlying)
					{
						p.capabilities.isFlying = true;
						p.sendPlayerAbilities();
					}
				}
				
			}
			if (p.capabilities.isFlying && Minecraft.getMinecraft().getRenderViewEntity() == p)
			{
				if (p.movementInput.sneak)
				{
					p.movementInput.moveStrafe = (float) ((double) p.movementInput.moveStrafe / 0.3D);
					p.movementInput.moveForward = (float) ((double) p.movementInput.moveForward / 0.3D);
					p.motionY -= (double) (p.capabilities.getFlySpeed());
				}
				
				if (p.motionX > 1.2F)
				{
					p.motionX = 1.2F;
				}
				if (p.motionX < -1.2F)
				{
					p.motionX = -1.2F;
				}
				if (p.motionY > 0.5F)
				{
					p.motionY = 0.5F;
				}
				if (p.motionY < -0.5F)
				{
					p.motionY = -0.5F;
				}
				if (p.motionZ > 1.2F)
				{
					p.motionZ = 1.2F;
				}
				if (p.motionZ < -1.2F)
				{
					p.motionZ = -1.2F;
				}
			}
			//			if (p.movementInput.jump)
			//			{
			//				if ((p.onGround || stats.isSsOnGroundLast()) && !p.capabilities.isCreativeMode)
			//				{
			//					if (this.pjumpticks < 25) this.pjumpticks++;
			//					p.motionY -= dy;
			//				} else
			//				{
			//					p.motionY += 0.015D;
			//					if (this.pjumpticks == 0)
			//					{
			//						p.motionY -= dy;
			//					}
			//				}
			//			} else if (this.pjumpticks > 0)
			//			{
			//				p.motionY += 0.0145D * this.pjumpticks;
			//				this.pjumpticks = 0;
			//			}
		}
		
		//Artificial gravity
		if (doCentrifugal && !p.onGround)
		{
			spinManager.applyCentrifugalForce(p);
		}
		
		this.pPrevMotionX = p.motionX;
		this.pPrevMotionY = p.motionY;
		this.pPrevMotionZ = p.motionZ;
		super.onWall = onWall;
		super.sneakLast = sneakLast;
		super.pPrevMotionY = pPrevMotionY;
	}
	
	public void jump(EntityPlayer player, float gravmod)
	{
		
		player.motionY = 0.42D / gravmod;
		
		if (player.isPotionActive(MobEffects.JUMP_BOOST))
		{
			player.motionY += (double) ((float) (player.getActivePotionEffect(MobEffects.JUMP_BOOST).getAmplifier() + 1) * 0.1F);
		}
		
		if ((float) WorldProviderOrbitModif.artificialG < 0.1F && player.motionY > 0.5D)
		{
			player.motionY = 0.5D;
		}
		
		if (player.isSprinting())
		{
			float f = player.rotationYaw * 0.017453292F;
			player.motionX -= (double) (MathHelper.sin(f) * 0.2F);
			player.motionZ += (double) (MathHelper.cos(f) * 0.2F);
		}
		
		player.isAirBorne = true;
		net.minecraftforge.common.ForgeHooks.onLivingJump(player);
	}
	
	public void handleJumpWater(EntityPlayer player, float gravmod)
	{
		player.motionY += 0.039D / gravmod;
	}
	
	public void handleJumpLava(EntityPlayer player, float gravmod)
	{
		player.motionY += 0.039D / gravmod;
	}
	
}

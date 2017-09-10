package net.orbitallabs.entity;

import net.minecraft.block.BlockBush;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Biomes;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntitySnake extends EntityMob {
	
	protected EnumParticleTypes ambientParticle;
	protected SoundEvent ambientSound;
	
	public double bodyTempreture = 20;
	public double distanceMovedTotal = 0;;
	
	public EntitySnake(World worldIn)
	{
		super(worldIn);
		this.setSize(1.4F, 0.2F);
		distanceMovedTotal = 0;
	}
	
	@Override
	protected void applyEntityAttributes()
	{
		
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.8D);
		this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0D);
	}
	
	@Override
	protected void updateAITasks()
	{
		super.updateAITasks();
	}
	
	double lastTemp = 20;
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (bodyTempreture < 20 && lastTemp > 20)
		{
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
			this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.5D);
			initEntityAI();
		} else if (bodyTempreture > 20 && bodyTempreture < 40 && !(lastTemp > 20 && lastTemp < 40))
		{
			this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.8D);
			initEntityAI();
		} else if (lastTemp > 40)
		{
			this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
			this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
			initEntityAI();
		}
		if (world.canSeeSky(this.getPosition()) && world.getSunBrightness(1.0F) > 0.5)
		{
			bodyTempreture += (world.getBiome(getPosition()) == Biomes.DESERT ? 0.01 : 0.001);
		} else
		{
			bodyTempreture -= 0.001;
		}
		if (world.isRainingAt(getPosition()))
		{
			bodyTempreture -= 0.01;
		}
		
		lastTemp = bodyTempreture;
	}
	
	public boolean attackEntityAsMob(Entity entityIn)
	{
		if (super.attackEntityAsMob(entityIn))
		{
			if (entityIn instanceof EntityLivingBase)
			{
				int i = 0;
				
				if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
				{
					i = 7;
				} else if (this.world.getDifficulty() == EnumDifficulty.HARD)
				{
					i = 15;
				}
				
				if (i > 0)
				{
					((EntityLivingBase) entityIn).addPotionEffect(new PotionEffect(MobEffects.POISON, i * 20, 0));
				}
			}
			
			return true;
		} else
		{
			return false;
		}
	}
	
	@Override
	protected void initEntityAI()
	{
		tasks.taskEntries.clear();
		targetTasks.taskEntries.clear();
		
		tasks.addTask(bodyTempreture > 30 ? 1 : 5, new EntityAIMoveToBlock(this, 1, 15) {
			
			@Override
			protected boolean shouldMoveTo(World world, BlockPos pos)
			{
				if (world.getBlockState(pos).getBlock() instanceof BlockBush)
				{
					return true;
				}
				return false;
			}
		});
		tasks.addTask(bodyTempreture < 20 ? 5 : bodyTempreture < 40 ? 2 : 5, new AISnakeAttack(this));
		tasks.addTask(bodyTempreture < 20 ? 5 : bodyTempreture < 40 ? 2 : 5, new EntityAILeapAtTarget(this, 0.4F));
		tasks.addTask(bodyTempreture > 30 ? 1 : 5, new EntityAIRestrictSun(this));
		tasks.addTask(5, new EntityAIWanderAvoidWater(this, 0.8D));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		targetTasks.addTask(bodyTempreture < 20 ? 5 : bodyTempreture < 40 ? 2 : 5, new EntityAIHurtByTarget(this, false, EntityLiving.class));
		targetTasks.addTask(2, new AISnakeTarget(this, EntityPlayer.class));
		
	}
	
	@Override
	public void onCollideWithPlayer(EntityPlayer player)
	{
		if (!player.onGround && Math.abs(player.motionY) > 0.1 && player.motionY < 0)
		{
			this.attackEntityFrom(DamageSource.causePlayerDamage(player), 1F);
		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setDouble("temp", bodyTempreture);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		bodyTempreture = compound.getDouble("temp");
	}
	
	@Override
	public AxisAlignedBB getEntityBoundingBox()
	{
		return super.getEntityBoundingBox();
	}
	
	@Override
	public float getEyeHeight()
	{
		return 0.2F;
	}
	
	@Override
	protected SoundEvent getAmbientSound()
	{
		
		return ambientSound;
	}
	
	@Override
	protected SoundEvent getHurtSound()
	{
		return SoundEvents.ENTITY_CAT_HURT;
	}
	
	@Override
	protected SoundEvent getDeathSound()
	{
		
		return SoundEvents.ENTITY_BAT_DEATH;
	}
	
	public float getScaleFactor()
	{
		return 1.0F;
	}
	
	static class AISnakeAttack extends EntityAIAttackMelee {
		public AISnakeAttack(EntitySnake snake)
		{
			super(snake, 1.0D, false);
		}
		
		/**
		 * Returns whether an in-progress EntityAIBase should continue executing
		 */
		public boolean continueExecuting()
		{
			return super.continueExecuting();
		}
		
		protected double getAttackReachSqr(EntityLivingBase attackTarget)
		{
			return (double) (4.0F + attackTarget.width);
		}
	}
	
	static class AISnakeTarget<T extends EntityLivingBase> extends EntityAINearestAttackableTarget<T> {
		public AISnakeTarget(EntitySnake snake, Class<T> classTarget)
		{
			super(snake, classTarget, true);
		}
		
		/**
		 * Returns whether the EntityAIBase should begin execution.
		 */
		public boolean shouldExecute()
		{
			return false;
		}
	}
	
}

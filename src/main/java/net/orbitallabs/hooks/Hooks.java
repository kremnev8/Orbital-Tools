
package net.orbitallabs.hooks;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.entity.IAntiGrav;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.item.IArmorGravity;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.TransformerHooks;
import micdoodle8.mods.galacticraft.core.client.gui.overlay.OverlayLaunchCountdown;
import micdoodle8.mods.galacticraft.core.client.gui.overlay.OverlayRocket;
import micdoodle8.mods.galacticraft.core.client.model.ModelPlayerGC;
import micdoodle8.mods.galacticraft.core.dimension.SpinManager;
import micdoodle8.mods.galacticraft.core.entities.player.FreefallHandler;
import micdoodle8.mods.galacticraft.core.entities.player.GCEntityClientPlayerMP;
import micdoodle8.mods.galacticraft.core.entities.player.StatsClientCapability;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import net.minecraft.block.BlockFalling;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.common.Loader;
import net.orbitallabs.dimensions.DockingPortSaveData;
import net.orbitallabs.dimensions.FreefallAdvHandler;
import net.orbitallabs.dimensions.WorldProviderOrbitModif;
import net.orbitallabs.hooklib.asm.Hook;
import net.orbitallabs.hooklib.asm.ReturnCondition;
import net.orbitallabs.items.AnimationCapabilityProvider;
import net.orbitallabs.items.AnimationCapabilityProvider.IAnimationCapability;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.utils.OTLoger;

public class Hooks {
	
	public static boolean AllowOwerworldSatelliteReg = false;
	
	@Hook(returnCondition = ReturnCondition.NEVER)
	public static void onUpdate(EntityFallingBlock ent)
	{
		ent.motionY -= getEntGravity(ent);
	}
	
	@Hook(returnCondition = ReturnCondition.NEVER, injectOnExit = true)
	public static void onUpdate(EntityItem ent)
	{
		//	if (ent.world.provider instanceof WorldProviderOrbitModif)
		//	OTLoger.logInfo("X:" + ent.posX + " Z:" + ent.posZ + " name: " + ent.getEntityItem().getItem().getUnlocalizedName());
		if (ent.world.isRemote && ent.world.provider instanceof WorldProviderOrbitModif)
		{
			WorldProviderOrbitModif prow = (WorldProviderOrbitModif) ent.world.provider;
			SpinManager spin = prow.getSpinManager();
			AxisAlignedBB entityBoundingBox = ent.getEntityBoundingBox();
			boolean outsideStation = entityBoundingBox.maxX < spin.ssBoundsMinX || entityBoundingBox.minX > spin.ssBoundsMaxX || entityBoundingBox.maxY < spin.ssBoundsMinY
					|| entityBoundingBox.minY > spin.ssBoundsMaxY || entityBoundingBox.maxZ < spin.ssBoundsMinZ || entityBoundingBox.minZ > spin.ssBoundsMaxZ;
			
			if ((outsideStation || FreefallHandler.testEntityFreefall(ent.world, entityBoundingBox)) && prow.artificialG < 0.1D)
			{
				// if (spin.doSpinning)
				// {
				//    this.moveRotatedEntity(e, spin.spinCentreX, spin.spinCentreZ, spin.angularVelocityRadians);
				// }
				
				FreefallHandler.tickFreefallEntity(ent);
			}
		}
	}
	
	@Hook(returnCondition = ReturnCondition.ON_TRUE, booleanReturnConstant = true)
	public static boolean hasNoGravity(Entity ent)
	{
		if (ent.world.provider != null && ent.world.provider instanceof WorldProviderOrbitModif && ent instanceof EntityFallingBlock)
		{
			return true;
		}
		
		return false;
	}
	
	@Hook(returnCondition = ReturnCondition.ON_TRUE)
	public static boolean checkFallable(BlockFalling block, World worldIn, BlockPos pos)
	{
		if (worldIn.provider != null && worldIn.provider instanceof WorldProviderOrbitModif)
		{
			WorldProviderOrbitModif customProvider = (WorldProviderOrbitModif) worldIn.provider;
			if (customProvider.artificialG == 0)
			{
				if (!worldIn.isRemote)
				{
					DockingPortSaveData savef = DockingPortSaveData.forWorld(worldIn);
					savef.frozenSands.add(pos);
					savef.markDirty();
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	@Hook(injectOnExit = true, targetMethod = "<init>")
	public static void entitem(EntityItem item, World worldIn, double x, double y, double z, ItemStack stack)
	{
		itemsetsize(item, worldIn, stack);
	}
	
	@Hook(injectOnExit = true, targetMethod = "<init>")
	public static void entitem(EntityItem item, World worldIn, double x, double y, double z)
	{
		itemsetsize(item, worldIn, ItemStack.EMPTY);
	}
	
	public static void itemsetsize(EntityItem item, World worldIn, ItemStack stack)
	{
		if (worldIn != null && worldIn.provider != null && worldIn.provider instanceof WorldProviderOrbitModif)
		{
			float height = 0.25F + 0.25F * (!stack.isEmpty() ? (stack.getItem() instanceof ItemBlock ? 1 : 2) : 1);
			
			float f = item.width;
			item.width = 0.25F;
			item.height = height;
			
			if (item.width < f)
			{
				double d0 = (double) 0.25F / 2.0D;
				item.setEntityBoundingBox(new AxisAlignedBB(item.posX - d0, item.posY, item.posZ - d0, item.posX + d0, item.posY + (double) item.height, item.posZ + d0));
				return;
			}
			
			AxisAlignedBB axisalignedbb = item.getEntityBoundingBox();
			item.setEntityBoundingBox(new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.minX + (double) item.width,
					axisalignedbb.minY + (double) item.height, axisalignedbb.minZ + (double) item.width));
			
			WorldProviderOrbitModif customProvider = (WorldProviderOrbitModif) worldIn.provider;
			item.motionY = 0.20000000298023224D * (customProvider.artificialG - 0.2D);
		}
		
	}
	
	@Hook(returnCondition = ReturnCondition.ON_TRUE)
	public static boolean registerSatellite(GalaxyRegistry reg, Satellite satellite)
	{
		if (satellite.getName() == "spacestation.overworld" && !AllowOwerworldSatelliteReg)
		{
			OTLoger.logInfo("Stopping GC Owerworld spacestation registration");
			return true;
		}
		return false;
	}
	
	@Hook(returnCondition = ReturnCondition.ON_TRUE, returnNull = true)
	public static boolean registerDimension(GalacticraftRegistry reg, String name, String suffix, int id, Class<? extends WorldProvider> provider, boolean keepLoaded)
	{
		if (name == "Space Station" && !AllowOwerworldSatelliteReg)
		{
			OTLoger.logInfo("The next error IS intended. Orbital tools is replacing Owerworld Orbit dimension");
			return true;
		}
		return false;
	}
	
	@Hook(injectOnExit = true, returnCondition = ReturnCondition.NEVER, createMethod = true)
	public static void setRotationAngles(ModelPlayerGC model, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity)
	{
		if (entity instanceof EntityPlayer)
		{
			
			ItemStack itemstack = ((EntityPlayer) entity).inventory.armorItemInSlot(2);
			
			if ((itemstack != null && Minecraft.getMinecraft().gameSettings.thirdPersonView != 0 || Minecraft.getMinecraft().currentScreen != null
					|| !entity.getDisplayName().getUnformattedText().equals(Minecraft.getMinecraft().player.getDisplayName().getUnformattedText())))
			{
				if (itemstack.getItem() == ItemMod.spaceJetpack)
				{
					IAnimationCapability cap = itemstack.getCapability(AnimationCapabilityProvider.AnimCap, EnumFacing.UP);
					if (cap != null)
					{
						//	ModelPlayer modelPlayer = (ModelPlayer) livingEntityRenderer.getMainModel();
						if (cap.getAnimationHandler().isAnimationActive("Enable") || cap.getAnimationHandler().isAnimationActive("Disable"))
						{
							//GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
							model.bipedLeftArm.rotateAngleX = 0;
							model.bipedLeftArm.rotateAngleZ = 0;
							
							model.bipedLeftArm.rotateAngleX += (float) Math.PI + 1.5;
							model.bipedLeftArm.rotateAngleZ += (float) Math.PI / 10;
							
							model.bipedRightArm.rotateAngleX = 0;
							model.bipedRightArm.rotateAngleZ = 0;
							
							model.bipedRightArm.rotateAngleX += (float) Math.PI + 1.5;
							model.bipedRightArm.rotateAngleZ -= (float) Math.PI / 10;
						}
						if (cap.getAnimationHandler().isAnimationActive("Enabled idle"))
						{
							//	GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
							model.bipedLeftArm.rotateAngleX = 0;
							
							model.bipedLeftArm.rotateAngleX += (float) Math.PI + 2 + (entity.isSneaking() ? 0.4 : 0);
							
							model.bipedRightArm.rotateAngleX = 0;
							
							model.bipedRightArm.rotateAngleX += (float) Math.PI + 2 + (entity.isSneaking() ? 0.4 : 0);
						}
						
					}
				}
			}
		}
	}
	
	public static final boolean whaila = Loader.isModLoaded("waila");
	
	//@Hook(returnCondition = ReturnCondition.ALWAYS)
	//public static void onPreGuiRender(TickHandlerClient tick, RenderGameOverlayEvent.Pre event)
	//{
	//}
	
	@Hook(injectOnExit = true, returnCondition = ReturnCondition.NEVER)
	public static void renderCountdownOverlay(OverlayLaunchCountdown over)
	{
		//Minecraft.getMinecraft().entityRenderer.enableLightmap();
		if (whaila) GlStateManager.disableLighting();
	}
	
	@Hook(injectOnExit = true, returnCondition = ReturnCondition.NEVER)
	public static void renderSpaceshipOverlay(OverlayRocket over, ResourceLocation guiTexture)
	{
		//Minecraft.getMinecraft().entityRenderer.enableLightmap();
		if (whaila) GlStateManager.disableLighting();
	}
	
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static boolean isSneaking(GCEntityClientPlayerMP entityPlayer)
	{
		boolean flag = entityPlayer.movementInput != null && entityPlayer.movementInput.sneak;
		return flag && !entityPlayer.isPlayerSleeping();
	}
	
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static float getEyeHeight(GCEntityClientPlayerMP entityPlayer)
	{
		
		float f = entityPlayer.eyeHeight;
		
		if (entityPlayer.isPlayerSleeping())
		{
			f = 0.2F;
		} else if (!entityPlayer.isSneaking() && entityPlayer.height != 1.65F)
		{
			if (entityPlayer.isElytraFlying() || entityPlayer.height == 0.6F)
			{
				f = 0.4F;
			}
		} else
		{
			f -= 0.08F;
		}
		
		return f;
		
	}
	
	@Hook(returnCondition = ReturnCondition.NEVER)
	public static void readFromNBT(SpinManager spin, NBTTagCompound nbt)
	{
		NBTTagCompound oneBlock = (NBTTagCompound) nbt.getTag("oneBlock");
		if (oneBlock != null)
		{
			if (oneBlock.getInteger("x") != 0 || oneBlock.getInteger("y") != 64 || oneBlock.getInteger("z") != 0)
			{
				oneBlock.setInteger("x", 0);
				oneBlock.setInteger("y", 64);
				oneBlock.setInteger("z", 0);
				nbt.setTag("oneBlock", oneBlock);
			}
		}
	}
	
	//	@Hook(returnCondition = ReturnCondition.NEVER)
	//	public static void setSize(Entity ent, float width, float height)
	//	{
	//		if (ent instanceof EntityItem)
	//		{
	//			EntityItem item = (EntityItem) ent;
	//			ItemStack stack = item.getEntityItem();
	//			if (stack.getItem() == ItemMod.spaceJetpack)
	//			{
	//				width = 1;
	//				height = 1;
	//			}
	//		}
	//	}
	@Hook(injectOnExit = true, targetMethod = "<init>")
	public static void statshook(StatsClientCapability stats)
	{
		stats.freefallHandler = new FreefallAdvHandler(stats);
	}
	
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static double getItemGravity(TransformerHooks hooks, EntityItem e)
	{
		return getEntGravity(e);
	}
	
	public static double getEntGravity(Entity e)
	{
		if (e.world.provider instanceof WorldProviderOrbitModif)
		{
			final WorldProviderOrbitModif orbitProvider = (WorldProviderOrbitModif) e.world.provider;
			return 0.03999999910593033D * orbitProvider.artificialG;
		}
		if (e.world.provider instanceof IGalacticraftWorldProvider)
		{
			final IGalacticraftWorldProvider customProvider = (IGalacticraftWorldProvider) e.world.provider;
			return Math.max(0.000D, 0.03999999910593033D - customProvider.getGravity() / 1.75D);
		}
		return 0.03999999910593033D;
	}
	
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static double getGravityForEntity(TransformerHooks hooks, Entity entity)
	{
		if (entity.world.provider instanceof IGalacticraftWorldProvider)
		{
			if (entity instanceof EntityChicken && !OxygenUtil.isAABBInBreathableAirBlock(entity.world, entity.getEntityBoundingBox()))
			{
				return 0.08D;
			}
			
			final IGalacticraftWorldProvider customProvider = (IGalacticraftWorldProvider) entity.world.provider;
			if (entity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer) entity;
				if (player.inventory != null)
				{
					int armorModLowGrav = 100;
					int armorModHighGrav = 100;
					for (ItemStack armorPiece : player.getArmorInventoryList())
					{
						if (armorPiece != null && armorPiece.getItem() instanceof IArmorGravity)
						{
							armorModLowGrav -= ((IArmorGravity) armorPiece.getItem()).gravityOverrideIfLow(player);
							armorModHighGrav -= ((IArmorGravity) armorPiece.getItem()).gravityOverrideIfHigh(player);
						}
					}
					if (armorModLowGrav > 100)
					{
						armorModLowGrav = 100;
					}
					if (armorModHighGrav > 100)
					{
						armorModHighGrav = 100;
					}
					if (armorModLowGrav < 0)
					{
						armorModLowGrav = 0;
					}
					if (armorModHighGrav < 0)
					{
						armorModHighGrav = 0;
					}
					if (customProvider.getGravity() > 0)
					{
						return 0.08D - (double) (customProvider.getGravity() * armorModLowGrav) / 100;
					}
					return 0.08D - (double) (customProvider.getGravity() * armorModHighGrav) / 100;
				}
			}
			return 0.08D - (double) customProvider.getGravity();
		} else if (entity instanceof IAntiGrav)
		{
			return 0;
		} else
		{
			return 0.08D;
		}
	}
	
}

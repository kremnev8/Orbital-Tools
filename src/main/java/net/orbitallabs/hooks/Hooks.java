
package net.orbitallabs.hooks;

import micdoodle8.mods.galacticraft.api.entity.IAntiGrav;
import micdoodle8.mods.galacticraft.api.item.IArmorGravity;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.TransformerHooks;
import micdoodle8.mods.galacticraft.core.client.gui.overlay.OverlayLaunchCountdown;
import micdoodle8.mods.galacticraft.core.client.gui.overlay.OverlayRocket;
import micdoodle8.mods.galacticraft.core.client.model.ModelPlayerGC;
import micdoodle8.mods.galacticraft.core.entities.player.StatsClientCapability;
import micdoodle8.mods.galacticraft.core.tick.TickHandlerClient;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Loader;
import net.orbitallabs.dimensions.FreefallAdvHandler;
import net.orbitallabs.hooklib.asm.Hook;
import net.orbitallabs.hooklib.asm.ReturnCondition;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.items.SpaceJetpackCapability;
import net.orbitallabs.items.SpaceJetpackItemStackCap;

public class Hooks {
	
	@Hook(injectOnExit = true, returnCondition = ReturnCondition.NEVER)
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
					SpaceJetpackItemStackCap cap = (SpaceJetpackItemStackCap) itemstack.getCapability(SpaceJetpackCapability.SpaceJetpackCapability, EnumFacing.UP);
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
	
	@Hook(returnCondition = ReturnCondition.ALWAYS)
	public static void onPreGuiRender(TickHandlerClient tick, RenderGameOverlayEvent.Pre event)
	{
	}
	
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
		if (e.world.provider instanceof IGalacticraftWorldProvider)
		{
			final IGalacticraftWorldProvider customProvider = (IGalacticraftWorldProvider) e.world.provider;
			return Math.max(0.001D, 0.03999999910593033D - customProvider.getGravity() / 1.75D);
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


package net.orbitallabs.items;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.ClientProxy;
import net.orbitallabs.items.AnimationCapabilityProvider.IAnimationCapability;
import net.orbitallabs.items.SpaceJetpackStorage.ISpaceJetpackState;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.JetpackFueluseSync;
import net.orbitallabs.utils.OrbitalModInfo;

public class ItemSpaceJetpack extends ItemArmorMod implements ISpaceJetpack {
	
	static final ArmorMaterial material = EnumHelper.addArmorMaterial("JETPACK", "", 500, new int[] { 0, 0, 0, 0 }, 0,
			SoundEvent.REGISTRY.getObject(new ResourceLocation("item.armor.equip_iron")), 5);
	
	public static List<Integer> KeysPressed = new ArrayList();
	
	public ItemSpaceJetpack(String uln)
	{
		super(uln, CreativeTabs.TOOLS, material, EntityEquipmentSlot.CHEST);
		this.setNoRepair();
		this.setNoRepair();
		this.setMaxDamage(0);
		this.setShowDesrc(true);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EntityEquipmentSlot slot, String type)
	{
		return OrbitalModInfo.MOD_ID + ":textures/JetPack.png";
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyCore.galacticraftItem;
	}
	
	@Override
	public ModelBiped getArmorModel(EntityLivingBase entityLiving, ItemStack itemStack, EntityEquipmentSlot armorSlot, ModelBiped _default)
	{
		return ClientProxy.model;
	}
	
	//TODO create control Gui
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack)
	{
		
		ISpaceJetpackState cap = stack.getCapability(SpaceJetpackProvider.SpaceJetpackCapability, EnumFacing.UP);
		IAnimationCapability anim = stack.getCapability(AnimationCapabilityProvider.AnimCap, EnumFacing.UP);
		
		if (anim.getAnimationHandler().notinited)
		{
			anim.getAnimationHandler().activateAnimation(cap.isEnabled() ? "Enabled idle" : "Disabled idle", 0);
			anim.getAnimationHandler().notinited = false;
		}
		
	}
	
	@Override
	public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt)
	{
		return new SpaceJetpackProvider();
	}
	
	@Override
	public void tickJetpackMovemnt(EntityPlayer player, ItemStack itemStack)
	{
		boolean disable = isDisabled(player, itemStack, false);
		if (!disable)
		{
			float vel = 0.05F;
			
			int ang = 0;
			
			if (KeysPressed.contains(2))
			{
				ang -= 90;
			} else if (KeysPressed.contains(3))
			{
				ang += 90;
			}
			
			float f4;
			float f5;
			double motionX;
			double motionZ;
			double usedFuelAm = 0;
			
			if (KeysPressed.contains(4))
			{
				//player.move(MoverType.SELF, 0, 0.5F, 0);
				player.motionY += vel;
				usedFuelAm += 0.05D;
			}
			if (KeysPressed.contains(5))
			{
				if (player.posY > 45)
				{
					//	player.move(MoverType.SELF, 0, -0.5F, 0);
					player.motionY -= vel;
					usedFuelAm += 0.05D;
				}
			}
			
			if (player.posY < 45)
			{
				player.motionY += vel;
			}
			
			//	p.motionX = 0;
			//	p.motionZ = 0;
			if (KeysPressed.contains(0) || KeysPressed.contains(1))
			{
				f4 = MathHelper.sin((player.rotationYawHead + 45) * (float) Math.PI / 180.0F);
				f5 = MathHelper.cos((player.rotationYawHead + 45) * (float) Math.PI / 180.0F);
				motionX = (double) (0.5D * f5 - 0.5D * f4);
				motionZ = (double) (0.5D * f5 + 0.5D * f4);
				
				motionX *= vel;
				motionZ *= vel;
				
				if (KeysPressed.contains(0))
				{
					//player.move(MoverType.SELF, motionX, 0, motionZ);
					player.motionX += motionX;
					player.motionZ += motionZ;
					usedFuelAm += 0.05D;
				}
				if (KeysPressed.contains(1))
				{
					//player.move(MoverType.SELF, -motionX, 0, -motionZ);
					player.motionX -= motionX;
					player.motionZ -= motionZ;
					usedFuelAm += 0.05D;
				}
			}
			if (KeysPressed.contains(2) || KeysPressed.contains(3))
			{
				f4 = MathHelper.sin((player.rotationYawHead + 45 + ang) * (float) Math.PI / 180.0F);
				f5 = MathHelper.cos((player.rotationYawHead + 45 + ang) * (float) Math.PI / 180.0F);
				motionX = (double) (0.5D * f5 - 0.5D * f4);
				motionZ = (double) (0.5D * f5 + 0.5D * f4);
				
				motionX *= vel;
				motionZ *= vel;
				
				if (KeysPressed.contains(2))
				{
					//player.move(MoverType.SELF, motionX, 0, motionZ);
					player.motionX += motionX;
					player.motionZ += motionZ;
					usedFuelAm += 0.05D;
				}
				if (KeysPressed.contains(3))
				{
					//player.move(MoverType.SELF, motionX, 0, motionZ);
					player.motionX += motionX;
					player.motionZ += motionZ;
					usedFuelAm += 0.05D;
				}
			}
			if (usedFuelAm > 0)
			{
				PacketHandler.sendToServer(new JetpackFueluseSync((float) usedFuelAm));
			}
			
			//	player.motionX = 0;
			//	player.motionZ = 0;
		}
		ISpaceJetpackState cap = itemStack.getCapability(SpaceJetpackProvider.SpaceJetpackCapability, EnumFacing.UP);
		
		if (cap.isEnabled() && !isDisabled(player, itemStack, true))
		{
			float dampeningcoif = 0.075F;
			
			float used = 0;
			if (player.motionX > 0.001D || player.motionX < 0.001D)
			{
				double prewM = player.motionX;
				player.motionX /= (1 + dampeningcoif);
				double dV = prewM - player.motionX;
				used += dV;
			}
			if (player.motionZ > 0.001D || player.motionZ < 0.001D)
			{
				double prewM = player.motionZ;
				//	if (ItemSpaceJetpack.KeysPressed.contains(4) || ItemSpaceJetpack.KeysPressed.contains(5))
				//	{
				//		player.motionZ /= 1.2;
				//} else 
				player.motionZ /= (1 + dampeningcoif);
				double dV = prewM - player.motionZ;
				used += dV;
			}
			if (player.motionY > 0.001D || player.motionY < 0.001D)
			{
				double prewM = player.motionY;
				player.motionY /= (1 + dampeningcoif);
				double dV = prewM - player.motionY;
				used += dV;
			}
			if (used > 0)
			{
				PacketHandler.sendToServer(new JetpackFueluseSync((float) used));
			}
		}
		
	}
	
}

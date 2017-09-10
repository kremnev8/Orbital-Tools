
package net.orbitallabs.items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.ClientProxy;
import net.orbitallabs.dimensions.WorldProviderOrbitModif;
import net.orbitallabs.items.SpaceJetpackCapability.SpaceJetpackState;
import net.orbitallabs.renderer.animations.AnimationHandlerJetpack;
import net.orbitallabs.utils.OrbitalModInfo;

public class ItemSpaceJetpack extends ItemArmorMod implements ICapabilitySerializable<NBTTagCompound> {
	
	static final ArmorMaterial material = EnumHelper.addArmorMaterial("JETPACK", "", 500, new int[] { 0, 0, 0, 0 }, 0,
			SoundEvent.REGISTRY.getObject(new ResourceLocation("item.armor.equip_iron")), 5);
	
	public SpaceJetpackState state;
	
	public boolean activated = false;
	
	public static List<Integer> KeysPressed = new ArrayList();
	
	public FluidTank RCSFuel = new FluidTank(1750 * ConfigManagerCore.rocketFuelFactor);
	
	public double usedFuelAm = 0;
	
	private boolean updateValues = false;
	private boolean needSave = false;
	
	private int ticks_from_sent = 0;
	
	private ScoreObjective obj;
	
	public ItemSpaceJetpack(String uln)
	{
		super(uln, CreativeTabs.TOOLS, material, EntityEquipmentSlot.CHEST);
		this.setNoRepair();
		updateValues = false;
		activated = false;
		this.setNoRepair();
		this.setMaxDamage(0);
		this.setShowDesrc(true);
		state = new SpaceJetpackState();
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
	public void onArmorTick(World world, EntityPlayer player, ItemStack itemStack)
	{
		if (ticks_from_sent > 0)
		{
			ticks_from_sent--;
			return;
		}
		
		//	if (RCSFuel.getFluid() == null)this.RCSFuel.fill(new FluidStack(GalacticraftCore.fluidFuel, RCSFuel.getCapacity()), true);
		NBTTagCompound tag = new NBTTagCompound();
		if (itemStack.hasTagCompound())
		{
			tag = itemStack.getTagCompound();
		}
		if (itemStack.hasTagCompound())
		{
			this.readFromNBT(tag);
			if (this.activated && world.isRemote && !state.getAnimationHandler().isAnimationActive("Enabled idle") && !state.getAnimationHandler().isAnimationActive("Enable")
					&& !state.getAnimationHandler().isAnimationActive("Disable"))
			{
				state.getAnimationHandler().clearAnimations();
				state.getAnimationHandler().activateAnimation("Enabled idle", 0);
			} else if (!this.activated && world.isRemote && !state.getAnimationHandler().isAnimationActive("Disabled idle")
					&& !state.getAnimationHandler().isAnimationActive("Disable") && !state.getAnimationHandler().isAnimationActive("Enable"))
			{
				state.getAnimationHandler().clearAnimations();
				state.getAnimationHandler().activateAnimation("Disabled idle", 0);
			}
		} else if (world.isRemote)
		{
			//PacketHandler.sendToServer(new SyncPressedKeysPacket(false));
			state.getAnimationHandler().clearAnimations();
			state.getAnimationHandler().activateAnimation("Disabled idle", 0);
			ticks_from_sent = 5;
		}
		/*	if (needSave)
			{
				if (!world.isRemote)
				{
					if (itemStack.hasTagCompound())
					{
						this.writeToNBT(itemStack.stackTagCompound);
					} else
					{
						itemStack.stackTagCompound = new NBTTagCompound();
						this.writeToNBT(itemStack.stackTagCompound);
					}
				} else
				{
					PacketHandler.sendToServer(new SyncPressedKeysPacket(activated));
					ticks_from_sent = 5;
				}
				this.needSave = false;
			}*/
		
		if (world.isRemote)
		{
			boolean disable = isDisabled(player, false);
			if (!disable)
			{
				float vel = 0.5F;
				
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
				
				if (KeysPressed.contains(4))
				{
					player.move(MoverType.SELF, 0, 0.5F, 0);
					usedFuelAm += 0.05D;
				}
				if (KeysPressed.contains(5))
				{
					if (player.posY > 45)
					{
						player.move(MoverType.SELF, 0, -0.5F, 0);
						usedFuelAm += 0.05D;
					}
				}
				
				player.motionX = 0;
				player.motionZ = 0;
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
						player.move(MoverType.SELF, motionX, 0, motionZ);
						usedFuelAm += 0.05D;
					}
					if (KeysPressed.contains(1))
					{
						player.move(MoverType.SELF, -motionX, 0, -motionZ);
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
						player.move(MoverType.SELF, motionX, 0, motionZ);
						usedFuelAm += 0.05D;
					}
					if (KeysPressed.contains(3))
					{
						player.move(MoverType.SELF, motionX, 0, motionZ);
						usedFuelAm += 0.05D;
					}
				}
				player.motionX = 0;
				player.motionZ = 0;
			}
			
			if (usedFuelAm >= 10)
			{
				//	player.addChatComponentMessage(new ChatComponentText("Used 10 mb of fuel!"));
				usedFuelAm = 0;
				//	PacketHandler.sendToServer(new JetpackUseFuelPacket());
			}
			
		}
		
		/*
		 * if (KeysPressed.size() > 0) { KeysPressed.clear(); markDirty(); }
		 */
		
	}
	
	public boolean isDisabled(EntityPlayer player, boolean useage)
	{
		if (player.world.provider instanceof WorldProviderOrbitModif)
		{
			WorldProviderOrbitModif prow = (WorldProviderOrbitModif) player.world.provider;
			if (prow.artificialG > 0.1D)
			{
				return true;
			}
		}
		if (player.onGround)
		{
			return true;
		}
		if (RCSFuel.getFluidAmount() == 0)
		{
			return true;
		}
		if (player.world.isRemote && Minecraft.getMinecraft().currentScreen != null && !useage)
		{
			return true;
		}
		return !activated;
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		activated = tag.getBoolean("Enabled");
		if (tag.hasKey("fuelTank"))
		{
			this.RCSFuel.readFromNBT(tag.getCompoundTag("fuelTank"));
		}
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		tag.setBoolean("Enabled", activated);
		if (this.RCSFuel.getFluid() != null)
		{
			tag.setTag("fuelTank", this.RCSFuel.writeToNBT(new NBTTagCompound()));
		}
	}
	
	public void markDirty()
	{
		this.needSave = true;
	}
	
	public void setUpdated()
	{
		this.updateValues = true;
	}
	
	public void setActive(boolean state)
	{
		//	PacketHandler.sendToServer(new SyncPressedKeysPacket(state));
		activated = state;
		ticks_from_sent = 5;
	}
	
	//	@Override
	//	public ICapabilityProvider initCapabilities(@Nonnull ItemStack stack, @Nullable NBTTagCompound nbt)
	//	{
	//		return new FluidHandlerItemStack(stack, capacity);
	//	}
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
		if (capability == SpaceJetpackCapability.SpaceJetpackCapability)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
		if (capability == SpaceJetpackCapability.SpaceJetpackCapability)
		{
			return (T) state;
		}
		return null;
	}
	
	@Override
	public NBTTagCompound serializeNBT()
	{
		NBTTagCompound properties = new NBTTagCompound();
		HashMap<String, Float> anims = ((AnimationHandlerJetpack) state.getAnimationHandler()).getCurrentActiveAnimations();
		if (!anims.isEmpty())
		{
			Set an = anims.keySet();
			NBTTagList nbtlist = new NBTTagList();
			for (int i = 0; i < an.size(); i++)
			{
				String k = (String) an.toArray()[i];
				nbtlist.appendTag(new NBTTagString(k + "-" + String.valueOf(anims.get(k))));
				
			}
			properties.setTag("ANIMATIONS", nbtlist);
		} else
		{
			properties.setBoolean("NO_ANIM", true);
		}
		return properties;
	}
	
	@Override
	public void deserializeNBT(NBTTagCompound nbt)
	{
		if (nbt != null)
		{
			if (!nbt.hasKey("NO_ANIM"))
			{
				NBTTagList nbtlist = nbt.getTagList("ANIMATIONS", 8);
				if (nbtlist.tagCount() != 0)
				{
					for (int i = 0; i < nbtlist.tagCount(); i++)
					{
						String kv = nbtlist.getStringTagAt(i);
						String[] kkvv = kv.split("-");
						String k = kkvv[0];
						Float v = Float.valueOf(kkvv[1]);
						state.getAnimationHandler().activateAnimation(k, v);
					}
				}
			}
		}
		
	}
	
}

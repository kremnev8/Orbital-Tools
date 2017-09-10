
package net.orbitallabs.items;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.orbitallabs.utils.IDescrObject;
import net.orbitallabs.utils.OrbitalModInfo;

public class ItemArmorMod extends ItemArmor implements IDescrObject {
	
	protected final String name;
	public boolean showDescr;
	protected boolean show = false;
	
	public ItemArmorMod(String uln, ArmorMaterial material, EntityEquipmentSlot type)
	{
		this(uln, CreativeTabs.COMBAT, material, type);
	}
	
	public ItemArmorMod(String uln, CreativeTabs tab, ArmorMaterial material, EntityEquipmentSlot type)
	{
		super(material, 0, type);
		this.name = uln;
		this.setRegistryName(uln);
		this.setCreativeTab(tab);
		GameRegistry.register(this);
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(OrbitalModInfo.MOD_ID + ":" + uln, "inventory"));
		}
	}
	
	@Override
	public String getItemStackDisplayName(ItemStack stack)
	{
		return I18n.format(getUnlocalizedName());
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return OrbitalModInfo.MOD_ID + "." + name + ".name";
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack)
	{
		Item item = stack.getItem();
		if (item instanceof ItemMod)
		{
			return OrbitalModInfo.MOD_ID + "." + ((ItemMod) item).name + ".name";
		}
		return OrbitalModInfo.MOD_ID + "." + name + ".name";
	}
	
	@Override
	public String getDescription(int meta)
	{
		return "";
	}
	
	@Override
	public String getShiftDescription(int meta)
	{
		return I18n.format(OrbitalModInfo.MOD_ID + ".descr_shift." + name + ".name");
	}
	
	@Override
	public boolean showDescription(int meta)
	{
		return show;
	}
	
	protected void setShowDesrc(boolean state)
	{
		show = state;
	}
}

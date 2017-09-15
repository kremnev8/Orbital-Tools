package net.orbitallabs.items;

import ic2.api.item.IC2Items;
import micdoodle8.mods.galacticraft.core.energy.EnergyConfigHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.oredict.OreDictionary;
import net.orbitallabs.utils.Config;
import net.orbitallabs.utils.IDescrObject;
import net.orbitallabs.utils.OTLoger;
import net.orbitallabs.utils.OrbitalModInfo;

public class ItemMod extends Item implements IDescrObject {
	
	protected final String name;
	protected String descr;
	protected boolean show = false;
	
	public static ItemMod Builder;
	public static ItemDebugTool DebugTool;
	public static ItemArmorMod spaceJetpack;
	public static Item ironScaffold;
	public static int scaffold_meta = 0;
	public static ItemMod dockingPortComp;
	
	public static ItemMod brokenTin;
	public static ItemMod brokenSteel;
	public static ItemMod brokenAluminum;
	
	public static ItemMod schematicjetpack;
	
	public static ItemMod smallEngine;
	public static ItemMod OD_engines_set;
	
	public static ItemMod emptyIdea;
	public static ItemMod filledIdea;
	
	public static ItemMod rotatingRing;
	
	public static ItemMod motor;
	public static ItemMod coil;
	
	public static Item ingSteel;
	public static int ingSteelMeta;
	
	public static void init()
	{
		Builder = new ItemBuilder("builder");
		DebugTool = new ItemDebugTool("debugtool");
		spaceJetpack = new ItemSpaceJetpack("jetpack");
		if (EnergyConfigHandler.isIndustrialCraft2Loaded())
		{
			ironScaffold = IC2Items.getItem("scaffold", "iron").getItem();
			scaffold_meta = IC2Items.getItem("scaffold", "iron").getItemDamage();
		} else
		{
			ironScaffold = new ItemMod("ironscaffold");
			motor = new ItemMod("altmotor");
			coil = new ItemMod("altcoil");
		}
		dockingPortComp = new ItemMod("dockingcomponent");
		
		brokenTin = new ItemBrokenPlate("brokentin", 0);
		brokenSteel = new ItemBrokenPlate("brokensteel", 1);
		brokenAluminum = new ItemBrokenPlate("brokenaluminum", 2);
		
		schematicjetpack = new ItemSchematic("schematicjetpack");
		smallEngine = new ItemMod("smallengine");
		OD_engines_set = new ItemMod("omnidirengineset");
		
		emptyIdea = new ItemMod("emptyidea");
		filledIdea = new ItemMod("filledidea");
		
		rotatingRing = new ItemMod("agmotormap");
		if (OreDictionary.doesOreNameExist("ingotSteel") && !Config.forceEnableSteelIngot)
		{
			ItemStack stack = OreDictionary.getOres("ingotSteel").get(0);
			ingSteel = stack.getItem();
			ingSteelMeta = stack.getItemDamage();
		} else
		{
			OTLoger.logInfo("adding Steel ingot");
			ingSteel = new ItemMod("steelingot");
			OreDictionary.registerOre("ingotSteel", ingSteel);
		}
	}
	
	//public static void registerRender()
	//{
	//	ModelLoader.setCustomModelResourceLocation(testItem, 0, new ModelResourceLocation(testItem.getRegistryName(), "inventory"));
	//}
	
	public ItemMod(String uln)
	{
		this(uln, CreativeTabs.MATERIALS, true);
	}
	
	public ItemMod(String uln, boolean doIconReg)
	{
		this(uln, CreativeTabs.MATERIALS, doIconReg);
	}
	
	public ItemMod(String uln, CreativeTabs tab, boolean doIconReg)
	{
		this.name = uln;
		this.setRegistryName(uln);
		this.setCreativeTab(tab);
		GameRegistry.register(this);
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && doIconReg)
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

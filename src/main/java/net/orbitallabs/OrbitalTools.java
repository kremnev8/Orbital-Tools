package net.orbitallabs;

import micdoodle8.mods.galacticraft.core.Constants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.orbitallabs.utils.OrbitalModInfo;

@Mod(modid = OrbitalModInfo.MOD_ID, name = OrbitalModInfo.MOD_NAME, version = OrbitalModInfo.Version, acceptedMinecraftVersions = "[1.12]", dependencies = "required-after:"
		+ Constants.MOD_ID_CORE + "@[4.0,);" + "required-after:" + Constants.MOD_ID_PLANETS + "@[4.0,);")
public class OrbitalTools {
	
	@SidedProxy(clientSide = "net.orbitallabs.ClientProxy", serverSide = "net.orbitallabs.CommonProxy")
	public static CommonProxy proxy;
	
	@Instance(OrbitalModInfo.MOD_ID)
	public static OrbitalTools instance;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{ 
		proxy.preInit(event);
		//		try
		//		{
		//			DictionaryGenerator.main(null);
		//		} catch (Exception e)
		//		{
		//			e.printStackTrace();
		//		}
		//GameRegistry.registerTileEntity(TileEntityCauldron.class, "Cauldron");
		//	EnumHelper.addEnum(enumType, enumName, paramTypes, paramValues)
		
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		proxy.init(event);
		
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		proxy.postInit(event);
		
	}
	
}
package net.orbitallabs.utils;

import java.io.File;
import net.minecraftforge.common.config.Configuration;

public class Config {
	
	Configuration config;
	
	public static int idDimensionMarsSpaceStation;
	public static int idDimensionMarsSpaceStationStatic;
	public static boolean alterOverworldSpaceStation;
	
	public static boolean makeSpaceStructureControlBlocksUnbreakable;
	public static boolean disableUnstableContent;
	public static boolean showCoordinatesAndlookDirection;
	
	public static boolean forceEnableSteelIngot;
	
	public static final String DIMENSIONS = "dimensions";
	
	public Config()
	{
	}
	
	public static void init(String name)
	{
		Configuration cfg = new Configuration(new File("config/" + name + ".cfg"), OrbitalModInfo.Version);
		try
		{
			cfg.load();
			cfg.setCategoryComment(DIMENSIONS, "Space stations dimension settings");
			cfg.setCategoryRequiresMcRestart(DIMENSIONS, true);
			idDimensionMarsSpaceStation = cfg
					.get(DIMENSIONS, "idDimensionMarsSpaceStation", 40, "WorldProvider ID for Mars Space Stations (advanced: do not change unless you have conflicts)").getInt(40);
			idDimensionMarsSpaceStationStatic = cfg
					.get(DIMENSIONS, "idDimensionMarsSpaceStationStatic", 41, "WorldProvider ID for Static Space Stations (advanced: do not change unless you have conflicts)")
					.getInt(41);
			alterOverworldSpaceStation = cfg.get(DIMENSIONS, "alterOverworldSpaceStation", true, "if set to true Earth space station will be changed to my variant")
					.getBoolean(true);
			
			makeSpaceStructureControlBlocksUnbreakable = cfg.get(Configuration.CATEGORY_GENERAL, "makeControlBlocksUnbreakable", true,
					"if set to true all blocks which are used to control space station building will be unbreakable (advanced: do not change unless you know what you doing)")
					.getBoolean(true);
			disableUnstableContent = cfg
					.get(Configuration.CATEGORY_GENERAL, "disableUnstableContent", true,
							"if set to false blocks, items and other work in progrees content will be enabled (advanced: before doing so recommended to make backup)")
					.getBoolean(true);
			forceEnableSteelIngot = cfg
					.get(Configuration.CATEGORY_GENERAL, "forceEnableSteelIngot", true,
							"if set to true Steel Ingot will be enabeled even when it is not needed(Recomended before adding new mods with Steel to set this to true)")
					.getBoolean(true);
			
			showCoordinatesAndlookDirection = cfg
					.get(Configuration.CATEGORY_GENERAL, "showCoordinatesAndlookDirection", true, "if set to true player position and look direction will be shown (Debug info)")
					.getBoolean(true);
			
		} catch (Exception e)
		{
			OTLoger.logWarn("Has a problem loading it's configuration", e);
		} finally
		{
			if (cfg != null && cfg.hasChanged()) cfg.save();
		}
	}
}
package net.orbitallabs;

import java.util.HashMap;
import ic2.api.item.IC2Items;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.dimension.TeleportTypeSpaceStation;
import micdoodle8.mods.galacticraft.core.recipe.NasaWorkbenchRecipe;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import micdoodle8.mods.galacticraft.planets.mars.items.MarsItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.orbitallabs.blocks.BlockContainerMod;
import net.orbitallabs.blocks.BlockMod;
import net.orbitallabs.dimensions.WorldProviderOrbitModif;
import net.orbitallabs.entity.EntityMod;
import net.orbitallabs.events.Events;
import net.orbitallabs.events.ItemsToolTips;
import net.orbitallabs.gui.GuiHandler;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.items.SpaceJetpackCapability;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.tiles.TileEntityArmorStand;
import net.orbitallabs.tiles.TileEntityDockingPort;
import net.orbitallabs.tiles.TileEntityGravitySource;
import net.orbitallabs.tiles.TileEntityInfo;
import net.orbitallabs.tiles.TileEntityRemoveInfo;
import net.orbitallabs.utils.Config;
import net.orbitallabs.utils.OTLoger;
import net.orbitallabs.utils.OrbitalModInfo;
import net.orbitallabs.utils.SchematicJetpack;
import net.orbitallabs.utils.SchematicsUtil;

public class CommonProxy {
	
	public static Satellite satelliteMarsSpaceStation;
	
	public static DimensionType MARS_ORBIT;
	public static DimensionType MARS_ORBIT_KEEPLOADED;
	
	public void preInit(FMLPreInitializationEvent event)
	{
		
		PacketHandler.register();
		Config.init("orbitaltools");
		BlockMod.init();
		BlockContainerMod.init();
		CapabilityManager.INSTANCE.register(SpaceJetpackCapability.ISpaceJetpackState.class, new SpaceJetpackCapability(), new SpaceJetpackCapability.Factory());
		ItemMod.init();
		EntityMod.init(Side.SERVER);
	}
	
	public void init(FMLInitializationEvent event)
	{
		NetworkRegistry.INSTANCE.registerGuiHandler(OrbitalTools.instance, new GuiHandler());
		
		SchematicRegistry.registerSchematicRecipe(new SchematicJetpack());
		GameRegistry.addRecipe(new ItemStack(ItemMod.smallEngine, 1), new Object[] { "012", "343", "353", '1', Items.FLINT_AND_STEEL, '2', Blocks.STONE_BUTTON, '3',
				new ItemStack(GCItems.basicItem, 1, 9), '4', GCItems.canister, '5', GCItems.oxygenVent });
		
		GameRegistry.addRecipe(new ItemStack(ItemMod.OD_engines_set, 1),
				new Object[] { "101", "020", "101", '1', ItemMod.smallEngine, '2', new ItemStack(GCItems.basicItem, 1, 13) });
		
		GameRegistry.addRecipe(new ItemStack(ItemMod.Builder, 1), new Object[] { "121", "343", "151", '1', new ItemStack(GCItems.basicItem, 1, 11), '2',
				new ItemStack(GCItems.basicItem, 1, 0), '3', Blocks.STONE_BUTTON, '4', new ItemStack(GCItems.basicItem, 1, 14), '5', GCItems.battery });
		
		GameRegistry.addRecipe(new ItemStack(BlockContainerMod.BlockArmorStand, 1),
				new Object[] { "010", "010", "222", '1', GCItems.flagPole, '2', new ItemStack(GCBlocks.landingPad, 1, 0) });
		
		GameRegistry.addRecipe(new ItemStack(ItemMod.dockingPortComp, 1), new Object[] { "121", "342", "131", '1', new ItemStack(GCItems.basicItem, 1, 7), '2',
				new ItemStack(GCBlocks.aluminumWire, 1, 1), '3', new ItemStack(GCItems.basicItem, 1, 9), '4', new ItemStack(GCItems.basicItem, 1, 14) });
		
		GameRegistry.addRecipe(new ItemStack(ItemMod.emptyIdea, 1), new Object[] { "010", "101", "020", '1', Blocks.GLASS, '2', Items.IRON_INGOT });
		
		GameRegistry.addRecipe(new ItemStack(ItemMod.filledIdea, 1), new Object[] { "415", "232", "617", '1', Blocks.GOLD_BLOCK, '2', Blocks.IRON_BLOCK, '3', ItemMod.emptyIdea,
				'4', new ItemStack(GCItems.basicItem, 1, 14), '5', new ItemStack(GCItems.basicItem, 1, 9), '6', ItemMod.smallEngine, '7', ItemMod.OD_engines_set });
		
		GameRegistry.addRecipe(new ItemStack(ItemMod.schematicjetpack, 1),
				new Object[] { "314", "121", "413", '1', Items.PAPER, '2', ItemMod.filledIdea, '3', new ItemStack(Items.DYE, 1, 0), '4', new ItemStack(Items.DYE, 1, 14) });
		
		if (Loader.isModLoaded("IC2"))
		{
			GameRegistry.addRecipe(new ItemStack(ItemMod.rotatingRing, 1), new Object[] { "121", "232", "121", '1', new ItemStack(GCItems.basicItem, 1, 8), '2',
					new ItemStack(GCItems.basicItem, 1, 9), '3', IC2Items.getItem("elemotor") });
		} else
		{
			GameRegistry.addRecipe(new ItemStack(ItemMod.rotatingRing, 1),
					new Object[] { "121", "232", "121", '1', new ItemStack(GCItems.basicItem, 1, 8), '2', new ItemStack(GCItems.basicItem, 1, 9), '3', ItemMod.motor });
			
			GameRegistry.addRecipe(new ItemStack(ItemMod.coil, 1), new Object[] { "010", "121", "010", '1', new ItemStack(GCItems.basicItem, 1, 3), '2', GCItems.flagPole });
			
			GameRegistry.addRecipe(new ItemStack(ItemMod.motor, 1),
					new Object[] { "010", "323", "010", '1', new ItemStack(GCItems.basicItem, 1, 7), '2', GCItems.flagPole, '3', ItemMod.coil });
		}
		
		GameRegistry.addRecipe(new ItemStack(BlockContainerMod.BlockArticialGsource, 1), new Object[] { "121", "343", "212", '1', new ItemStack(GCItems.basicItem, 1, 9), '2',
				ItemMod.rotatingRing, '3', new ItemStack(GCItems.basicItem, 1, 14), '4', new ItemStack(GCBlocks.aluminumWire, 1, 1) });
		
		GameRegistry.addSmelting(ItemMod.brokenTin, new ItemStack(GCItems.basicItem, 1, 4), 0);
		GameRegistry.addSmelting(ItemMod.brokenSteel, new ItemStack(ItemMod.ingSteel, 1, ItemMod.ingSteelMeta), 0);
		GameRegistry.addSmelting(ItemMod.brokenAluminum, new ItemStack(GCItems.basicItem, 1, 5), 0);
		
		HashMap<Integer, ItemStack> input = new HashMap<Integer, ItemStack>();
		
		input.put(1, new ItemStack(ItemMod.OD_engines_set));
		input.put(5, new ItemStack(ItemMod.OD_engines_set));
		input.put(16, new ItemStack(ItemMod.OD_engines_set));
		input.put(20, new ItemStack(ItemMod.OD_engines_set));
		
		input.put(2, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(3, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(4, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(6, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(10, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(11, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(15, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(17, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(18, new ItemStack(GCItems.basicItem, 1, 9));
		input.put(19, new ItemStack(GCItems.basicItem, 1, 9));
		
		input.put(7, new ItemStack(GCItems.basicItem, 1, 8));
		input.put(8, new ItemStack(GCItems.basicItem, 1, 8));
		input.put(9, new ItemStack(GCItems.basicItem, 1, 13));
		
		input.put(12, new ItemStack(GCItems.basicItem, 1, 8));
		input.put(13, new ItemStack(GCItems.basicItem, 1, 8));
		input.put(14, new ItemStack(GCItems.basicItem, 1, 14));
		
		input.put(21, new ItemStack(GCItems.flagPole));
		input.put(22, new ItemStack(GCItems.flagPole));
		input.put(23, new ItemStack(GCItems.flagPole));
		input.put(24, new ItemStack(GCItems.flagPole));
		
		SchematicsUtil.addJetpackRecipe(new NasaWorkbenchRecipe(new ItemStack(ItemMod.spaceJetpack), input));
		
		//	NetworkRegistry.INSTANCE.registerGuiHandler(this.instance, new GuiHandler());
		GameRegistry.registerTileEntity(TileEntityInfo.class, "Info");
		GameRegistry.registerTileEntity(TileEntityRemoveInfo.class, "RemoveInfo");
		GameRegistry.registerTileEntity(TileEntityDockingPort.class, "DockingPort");
		GameRegistry.registerTileEntity(TileEntityGravitySource.class, "GravitySource");
		GameRegistry.registerTileEntity(TileEntityArmorStand.class, "ArmorStand");
		
		satelliteMarsSpaceStation = (Satellite) new Satellite("orbitaltools.mars").setParentBody(MarsModule.planetMars).setRelativeSize(0.2667F)
				.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(10F, 10F)).setRelativeOrbitTime(1 / 0.055F);
		satelliteMarsSpaceStation.setDimensionInfo(Config.idDimensionMarsSpaceStation, Config.idDimensionMarsSpaceStationStatic, WorldProviderOrbitModif.class).setTierRequired(2);
		satelliteMarsSpaceStation.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/space_station.png"));
		satelliteMarsSpaceStation.setAtmosphere(new AtmosphereInfo(false, false, false, 0.0F, 0.03F, 0.02F));
		satelliteMarsSpaceStation.addChecklistKeys("equipOxygenSuit", "createGrapple", OrbitalModInfo.MOD_ID + ".createSpaceStationBuilder",
				OrbitalModInfo.MOD_ID + ".grabAlotofMaterials");
		
		GalaxyRegistry.registerSatellite(satelliteMarsSpaceStation);
		MARS_ORBIT = GalacticraftRegistry.registerDimension("Mars Space Station", "_orbitmars", Config.idDimensionMarsSpaceStation, WorldProviderOrbitModif.class, false);
		if (MARS_ORBIT == null)
		{
			OTLoger.logWarn("Failed to register mars space station dimension type with ID " + Config.idDimensionMarsSpaceStation);
		}
		MARS_ORBIT_KEEPLOADED = GalacticraftRegistry.registerDimension("Mars Space Station", "_orbitmars", Config.idDimensionMarsSpaceStationStatic, WorldProviderOrbitModif.class,
				true);
		if (MARS_ORBIT_KEEPLOADED == null)
		{
			OTLoger.logWarn("Failed to register space station dimension type with ID " + Config.idDimensionMarsSpaceStationStatic);
		}
		
		GalacticraftRegistry.registerTeleportType(WorldProviderOrbitModif.class, new TeleportTypeSpaceStation());
		
		final HashMap<Object, Integer> inputMap = new HashMap<Object, Integer>();
		inputMap.put(new ItemStack(GCItems.basicItem, 1, 7), 64);
		inputMap.put(new ItemStack(Items.GLOWSTONE_DUST), 8);
		inputMap.put(new ItemStack(GCItems.basicItem, 1, 13), 5);
		inputMap.put(new ItemStack(ItemMod.ironScaffold, 1, ItemMod.scaffold_meta), 48);
		inputMap.put(new ItemStack(MarsItems.marsItemBasic, 1, 2), 8);
		//Hooks.ignoreThis = true;
		//	GalacticraftRegistry.registerSpaceStation(new SpaceStationType(ConfigManagerCore.idDimensionOverworldOrbit, ConfigManagerCore.idDimensionOverworld, new SpaceStationRecipe(inputMap)));
		//Hooks.ignoreThis = false;
		//	GalacticraftRegistry.registerSpaceStation(new SpaceStationType(40, MarsModule.planetMars.getDimensionID(), new SpaceStationRecipe(inputMap)));
		MinecraftForge.EVENT_BUS.register(new Events());
		MinecraftForge.EVENT_BUS.register(new ItemsToolTips());
	}
	
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
	
	public void spawnParticle(String particleID, Vector3 position, Vector3 motion, Object[] otherInfo)
	{
	}
	
	/**
	 * Returns a side-appropriate EntityPlayer for use during message handling
	 */
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		return ctx.getServerHandler().playerEntity;
	}
	
}
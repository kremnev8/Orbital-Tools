package net.orbitallabs.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.renderer.RenderEntityRocketFakeTiered;
import net.orbitallabs.utils.OrbitalModInfo;

public class EntityMod {
	
	private static int lastId;
	
	public static void init(Side s)
	{
		if (s == Side.SERVER)
		{
			registerEntity();
		} else if (s == Side.CLIENT)
		{
			registerEntityRenderer();
		}
	}
	
	@SideOnly(Side.CLIENT)
	private static void registerEntityRenderer()
	{
		//RenderingRegistry.registerEntityRenderingHandler(EntitySnake.class, new RenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(EntityRocketFakeTiered.class, new RenderEntityRocketFakeTiered.Factory());
	}
	
	private static void registerEntity()
	{
		//registerEntity(EntityRocketFakeTiered.class, "EntiyRocketFakeTiered");
		//registerEntity(EntitySnake.class, "Snake", true, 3232308, 7969893);
		
		registerEntity(EntityRocketFakeTiered.class, "EntiyRocketFakeTiered");
	}
	
	private static void registerEntity(Class entityClass, String entityName, boolean spawn, int solidColor, int spotColor)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(OrbitalModInfo.MOD_ID + ":" + entityName), entityClass, entityName, lastId++, OrbitalModInfo.MOD_ID, 50, 5, true,
				solidColor, spotColor);
		if (spawn) EntityRegistry.addSpawn(entityClass, 2, 0, 1, EnumCreatureType.CREATURE, Biomes.FOREST);
	}
	
	private static void registerEntity(Class entityClass, String entityName, boolean spawn)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(OrbitalModInfo.MOD_ID + ":" + entityName), entityClass, entityName, lastId++, OrbitalModInfo.MOD_ID, 50, 5, true);
		if (spawn) EntityRegistry.addSpawn(entityClass, 2, 0, 1, EnumCreatureType.CREATURE, Biomes.FOREST);
		
	}
	
	private static void registerEntity(Class entityClass, String entityName)
	{
		EntityRegistry.registerModEntity(new ResourceLocation(OrbitalModInfo.MOD_ID + ":" + entityName), entityClass, entityName, lastId++, OrbitalModInfo.MOD_ID, 50, 5, true);
	}
	
	//private static void createEgg(int randomId, int solidColor, int spotColor)
	//{
	//EntityList.entityEggs.put(Integer.valueOf(randomId), new EntityList.EntityEggInfo(randomId, solidColor, spotColor));
	
	//}
	
}
package net.orbitallabs;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.orbitallabs.entity.EntityMod;
import net.orbitallabs.events.Events;
import net.orbitallabs.events.KeyHandlerClient;
import net.orbitallabs.gui.GuiBuilder;
import net.orbitallabs.items.ItemSchematic;
import net.orbitallabs.renderer.TileEntityArmorStandRenderer;
import net.orbitallabs.renderer.TileEntityInfoRenderer;
import net.orbitallabs.renderer.fx.EffectHandler;
import net.orbitallabs.renderer.models.ModelJetpack;
import net.orbitallabs.tiles.TileEntityArmorStand;
import net.orbitallabs.tiles.TileEntityInfo;

public class ClientProxy extends CommonProxy {
	
	public static boolean lastSpacebarDown;
	public static ModelBiped model;
	
	@Override
	public void preInit(FMLPreInitializationEvent event)
	{
		super.preInit(event);
		EntityMod.init(Side.CLIENT);
	}
	
	@Override
	public void init(FMLInitializationEvent event)
	{
		super.init(event);
		model = new ModelJetpack(); 
		
		//	ClientRegistry.bindTileEntitySpecialRenderer(TileEntityRemoveInfo.class, new TileEntityRemoveInfoRenderer());
		//	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockContainerMod.BlockRemoveInfo), new ItemRenderRemoveInfo());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityInfo.class, new TileEntityInfoRenderer());
		//	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockContainerMod.BlockInfo), new ItemRenderInfo());
		
		//	MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(BlockContainerMod.BlockArmorStand), new ItemRenderArmorStand());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityArmorStand.class, new TileEntityArmorStandRenderer());
		
		//	MinecraftForgeClient.registerItemRenderer(ItemMod.spaceJetpack, new ItemRenderJetpack());
		
		MinecraftForge.EVENT_BUS.register(new Events());
		MinecraftForge.EVENT_BUS.register(new KeyHandlerClient());
		
		//	RenderPlayerAPI.register(GliderModInfo.MOD_ID, RendererPlayer.class);
		//	ModelPlayerAPI.register(GliderModInfo.MOD_ID, GliderModelPlayerBase.class);
		
		//	Layer
		
		ClientRegistry.registerKeyBinding(KeyHandlerClient.TestAnim);
		
		GuiBuilder.init();
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event)
	{
		ItemSchematic.registerSchematicItems(Side.CLIENT);
		super.postInit(event);
	}
	
	@Override
	public void spawnParticle(String particleID, Vector3 position, Vector3 motion, Object[] otherInfo)
	{
		EffectHandler.spawnParticle(particleID, position, motion, otherInfo);
	}
	
	// In your client proxy:
	@Override
	public EntityPlayer getPlayerEntity(MessageContext ctx)
	{
		// Note that if you simply return 'Minecraft.getMinecraft().thePlayer',
		// your packets will not work because you will be getting a client
		// player even when you are on the server! Sounds absurd, but it's true.
		
		// Solution is to double-check side before returning the player:
		return (ctx.side.isClient() ? Minecraft.getMinecraft().player : super.getPlayerEntity(ctx));
	}
}

package net.orbitallabs.events;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import micdoodle8.mods.galacticraft.api.entity.IRocketType;
import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCFluids;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderOverworldOrbit;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.tile.TileEntityParaChest;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.ClientProxy;
import net.orbitallabs.dimensions.DockingPortSaveData;
import net.orbitallabs.dimensions.WorldProviderOrbitModif;
import net.orbitallabs.entity.EntityRocketFakeTiered;
import net.orbitallabs.items.AnimationCapabilityProvider;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.items.ItemSpaceJetpack;
import net.orbitallabs.items.SpaceJetpackProvider;
import net.orbitallabs.items.SpaceJetpackStorage.ISpaceJetpackState;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.LaunchRocketPacket;
import net.orbitallabs.network.packets.SetThirdPersonPacket;
import net.orbitallabs.network.packets.SyncPlayerFallPacket;
import net.orbitallabs.tiles.TileEntityDockingPort;
import net.orbitallabs.utils.Config;
import net.orbitallabs.utils.OTLoger;
import net.orbitallabs.utils.OrbitalModInfo;

public class Events {
	
	//SERVER  
	
	@SubscribeEvent
	public void cancelSuffucationInRocket(GCCoreOxygenSuffocationEvent.Pre event)
	{
		if (event.getEntity() instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if (player.getRidingEntity() instanceof EntityRocketFakeTiered)
			{
				if (((EntityRocketFakeTiered) player.getRidingEntity()).canBreath())
				{
					event.setCanceled(true);
				} 
			}
		}
	}
	
	@SubscribeEvent
	public void onCraft(PlayerEvent.ItemCraftedEvent event)
	{
		if (event.crafting.getItem() == ItemMod.schematicjetpack)
		{
			if (event.player != null && !(event.player instanceof FakePlayer) && !event.player.world.isRemote)
			{
				EntityItem item = new EntityItem(event.player.world, event.player.posX, event.player.posY, event.player.posZ, new ItemStack(ItemMod.filledIdea));
				item.setNoPickupDelay();
				event.player.world.spawnEntity(item);
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerTeleportDim(PlayerEvent.PlayerChangedDimensionEvent event)
	{
		
		EntityPlayer player = event.player;
		if (player != null && player.world != null)
		{
			
			if (player.world.provider instanceof WorldProviderOrbitModif)
			{
				
				Chunk zChunk = player.world.getChunkFromChunkCoords(0, 0);
				if (zChunk.isTerrainPopulated())
				{
					
					GCPlayerStats playerStats = GCPlayerStats.get((EntityPlayerMP) player);
					if (playerStats.getRocketStacks().size() <= 1)
					{
						return;
					}
					OTLoger.logInfo("Player just docked to Space Station");
					OTLoger.logInfo("info:");
					
					PacketHandler.sendTo(new SetThirdPersonPacket(0), (EntityPlayerMP) player);
					
					DockingPortSaveData savef = DockingPortSaveData.forWorld(player.world);
					World world = player.world;
					
					boolean docked = false;
					if (savef.DockingPorts.size() > 0)
					{
						OTLoger.logInfo("Dim have a docking port info");
						for (int i = 0; i < savef.DockingPorts.size(); i++)
						{
							BlockPos pos = savef.DockingPorts.get(i);
							
							if (world.getTileEntity(pos) != null)
							{
								TileEntityDockingPort te = (TileEntityDockingPort) world.getTileEntity(pos);
								
								if (te.getStackInSlot(te.getSizeInventory() - 2).isEmpty() && te.getStackInSlot(te.getSizeInventory() - 3).isEmpty())
								{
									player.setPositionAndUpdate(pos.getX() + 0.5D, pos.getY() + 2, pos.getZ() + 0.5D);
									
									if (playerStats.getRocketStacks().size() > 0)
									{
										te.setSizeInventory(playerStats.getRocketStacks().size() + 2);
										// te.chestContents =
										// ((WorldProviderOrbitModif)world.provider).rocketStacks;
										NonNullList<ItemStack> RCis = playerStats.getRocketStacks();
										
										for (int i2 = 0; i2 < RCis.size() - 2; i2++)
										{
											te.setInventorySlotContents(i2, RCis.get(i2));
										}
										if (!RCis.get(RCis.size() - 1).isEmpty())
											te.setLastID(RCis.get(RCis.size() - 1).hasTagCompound() ? RCis.get(RCis.size() - 1).getTagCompound().getInteger("UniqueID") : -1);
										te.setInventorySlotContents(te.getSizeInventory() - 3, RCis.get(RCis.size() - 2));
										te.setInventorySlotContents(te.getSizeInventory() - 2, RCis.get(RCis.size() - 1));
										boolean preFueled = false;
										ItemStack rocketI = RCis.get(RCis.size() - 1);
										if (!rocketI.isEmpty())
										{
											int type = rocketI.getItemDamage();
											// Checking type
											if (type == IRocketType.EnumRocketType.PREFUELED.getIndex())
											{
												preFueled = true;
											}
										}
										int tier = EntityRocketFakeTiered.getTierFromItem(rocketI);
										double hight = 1.8D;
										if (tier == 2) hight = 1.9D;
										else if (tier == 3) hight = 2.4D;
										te.rocket = new EntityRocketFakeTiered(world, te.getPos().getX() + 0.5D, te.getPos().getY() - hight, te.getPos().getZ() + 0.5D, tier, te);
										// creating fuel tank for rocket fuel
										// size
										te.fuelTank = new FluidTank(te.rocket.getFuelTankCapacity() * ConfigManagerCore.rocketFuelFactor);
										
										if (preFueled)
										{
											// if rocket is prefueld, fueling
											// it.
											te.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, te.fuelTank.getCapacity()), true);
										} else if (rocketI != null && rocketI.hasTagCompound())
										{
											// reading fuel from item NBT
											te.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, playerStats.getFuelLevel()), true);
										}
										
										// spawning rocket in world
										te.rocket.fuelTank = te.fuelTank;
										world.spawnEntity(te.rocket);
										playerStats.setRocketStacks(NonNullList.withSize(1, ItemStack.EMPTY));
										playerStats.setFuelLevel(0);
										
									}
									OTLoger.logInfo("Rocket started rendezvous with docking port N" + (i + 1));
									docked = true;
									break;
								} else OTLoger.logInfo("Rocket already docked in dock N" + (i + 1));
							}
						}
					}
					if (!docked)
					{
						for (int i = 0; i < 4; i++)
						{
							if (world.getBlockState(new BlockPos(i == 0 ? 1 : i == 1 ? -1 : 0, 63, i == 2 ? 1 : i == 3 ? -1 : 0)).getBlock() instanceof BlockAir)
							{
								BlockPos pos = new BlockPos(i == 0 ? 1 : i == 1 ? -1 : 0, 63, i == 2 ? 1 : i == 3 ? -1 : 0);
								world.setBlockState(pos, GCBlocks.parachest.getDefaultState());
								TileEntityParaChest chest = (TileEntityParaChest) world.getTileEntity(pos);
								if (chest == null)
								{
									chest = new TileEntityParaChest();
									world.setTileEntity(pos, chest);
								}
								NonNullList<ItemStack> RCis = playerStats.getRocketStacks();
								
								if (RCis.size() >= 2)
								{
									chest.setSizeInventory(playerStats.getRocketStacks().size() + 1);
									
									for (int i2 = 0; i2 < RCis.size() - 2; i2++)
									{
										chest.setInventorySlotContents(i2, RCis.get(i2));
									}
									chest.setInventorySlotContents(chest.getSizeInventory() - 3, RCis.get(RCis.size() - 2));
									chest.setInventorySlotContents(chest.getSizeInventory() - 2, RCis.get(RCis.size() - 1));
									boolean preFueled = false;
									ItemStack rocketI = RCis.get(RCis.size() - 1);
									if (rocketI != null)
									{
										int type = rocketI.getItemDamage();
										if (type == IRocketType.EnumRocketType.PREFUELED.getIndex())
										{
											preFueled = true;
										}
									}
									int tier = EntityRocketFakeTiered.getTierFromItem(rocketI);
									chest.fuelTank = new FluidTank((1000 + (tier > 1 ? 500 : 0)) * ConfigManagerCore.rocketFuelFactor);
									
									if (preFueled)
									{
										
										chest.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, chest.fuelTank.getCapacity()), true);
									} else
									{
										// reading fuel from item NBT
										chest.fuelTank.fill(new FluidStack(GCFluids.fluidFuel, playerStats.getFuelLevel()), true);
									}
									playerStats.setRocketStacks(NonNullList.withSize(1, ItemStack.EMPTY));
									playerStats.setFuelLevel(0);
									OTLoger.logInfo("Created a chest with rocket items");
								}
								break;
								
							}
						}
						
						if (!docked) OTLoger.logInfo("Dim haven't a docking port info");
					}
					
				} else
				{
					OTLoger.logInfo("0 0 Chunk is not populated yet. Waiting.");
				}
			}
		}
	}
	
	//CLIENT
	
	private static ResourceLocation key = new ResourceLocation(OrbitalModInfo.MOD_ID, "Animation");
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void attachCap(AttachCapabilitiesEvent<ItemStack> event)
	{
		if (event.getObject().getItem() == ItemMod.spaceJetpack && !event.getCapabilities().containsKey(key))
		{
			event.addCapability(key, new AnimationCapabilityProvider());
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onTick(ClientTickEvent event)
	{
		if (event.side == Side.CLIENT)
		{
			Minecraft mc = Minecraft.getMinecraft();
			if (mc.player != null && mc.player.inventory.armorItemInSlot(2) != null && mc.player.inventory.armorItemInSlot(2).getItem() == ItemMod.spaceJetpack)
			{
				List<Integer> keys = new ArrayList<>();
				
				if (Keyboard.isKeyDown(KeyHandlerClient.accelerateKey.getKeyCode()))
				{
					keys.add(0);
				}
				if (Keyboard.isKeyDown(KeyHandlerClient.decelerateKey.getKeyCode()))
				{
					keys.add(1);
				}
				if (Keyboard.isKeyDown(KeyHandlerClient.leftKey.getKeyCode()))
				{
					keys.add(2);
				}
				if (Keyboard.isKeyDown(KeyHandlerClient.rightKey.getKeyCode()))
				{
					keys.add(3);
				}
				if (Keyboard.isKeyDown(KeyHandlerClient.spaceKey.getKeyCode()))
				{
					keys.add(4);
				}
				if (Keyboard.isKeyDown(KeyHandlerClient.leftShiftKey.getKeyCode()))
				{
					keys.add(5);
				}
				
				if (mc.player.inventory.armorItemInSlot(2).getItem() == ItemMod.spaceJetpack)
				{
					ISpaceJetpackState cap = mc.player.inventory.armorItemInSlot(2).getCapability(SpaceJetpackProvider.SpaceJetpackCapability, EnumFacing.UP);
					if (!(ItemSpaceJetpack.KeysPressed.equals(keys)) && cap.isEnabled())
					{
						ItemSpaceJetpack.KeysPressed = keys;
						//jetpack.markDirty();
					}
				}
				
			}
			
		}
		
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onClientTick(ClientTickEvent event)
	{
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final WorldClient world = minecraft.world;
		final EntityPlayer player = minecraft.player;
		
		if (!KeyHandlerClient.spaceKey.isKeyDown())
		{
			ClientProxy.lastSpacebarDown = false;
		}
		
		if (player != null && player.getRidingEntity() != null && player.getRidingEntity() instanceof EntityRocketFakeTiered && KeyHandlerClient.spaceKey.isKeyDown()
				&& !ClientProxy.lastSpacebarDown && player.world.provider instanceof WorldProviderOverworldOrbit)
		{
			PacketHandler.sendToServer(new LaunchRocketPacket());
			ClientProxy.lastSpacebarDown = true;
		}
		
	}
	
	protected static final ResourceLocation Fuel = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/FuelTank.png");
	
	public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height)
	{
		float f = 0.00390625F;
		float f1 = 0.00390625F;
		Tessellator tessellator = Tessellator.getInstance();
		BufferBuilder buf = tessellator.getBuffer();
		buf.begin(7, DefaultVertexFormats.POSITION_TEX);
		buf.pos((double) (x + 0), (double) (y + height), (double) 1).tex((double) ((float) (textureX + 0) * 0.00390625F), (double) ((float) (textureY + height) * 0.00390625F))
				.endVertex();
		buf.pos((double) (x + width), (double) (y + height), (double) 1)
				.tex((double) ((float) (textureX + width) * 0.00390625F), (double) ((float) (textureY + height) * 0.00390625F)).endVertex();
		buf.pos((double) (x + width), (double) (y + 0), (double) 1).tex((double) ((float) (textureX + width) * 0.00390625F), (double) ((float) (textureY + 0) * 0.00390625F))
				.endVertex();
		buf.pos((double) (x + 0), (double) (y + 0), (double) 1).tex((double) ((float) (textureX + 0) * 0.00390625F), (double) ((float) (textureY + 0) * 0.00390625F)).endVertex();
		tessellator.draw();
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRender(RenderGameOverlayEvent.Post event)
	{
		Minecraft mc = Minecraft.getMinecraft();
		int xPos = event.getResolution().getScaledWidth() - 10 - 19;
		int yPos = 63;
		
		if (event.getType() != ElementType.EXPERIENCE)
		{
			return;
		}
		if (!OxygenUtil.shouldDisplayTankGui(mc.currentScreen))
		{
			return;
		}
		
		if (!mc.gameSettings.showDebugInfo && Config.showCoordinatesAndlookDirection)
		{
			FontRenderer fontRendererObj = mc.fontRenderer;
			
			Entity entity = mc.getRenderViewEntity();
			EnumFacing enumfacing = entity.getHorizontalFacing();
			String s = "Invalid";
			
			fontRendererObj.drawString("x: " + (double) Math.round(mc.player.posX * 100) / 100 + " y: " + (double) Math.round(mc.player.posY * 100) / 100 + " z: "
					+ (double) Math.round(mc.player.posZ * 100) / 100, 3, 3, 14737632, false);
			fontRendererObj.drawString("Look: " + enumfacing.getName(), 3, 13, 14737632, false);
		}
		
		if (!mc.gameSettings.showDebugInfo && mc.player.inventory.armorItemInSlot(2) != null && mc.player.inventory.armorItemInSlot(2).getItem() == ItemMod.spaceJetpack)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(Fuel);
			
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			
			drawTexturedModalRect(xPos, yPos, 0, 0, 19, 47);
			
			ItemStack jetpack = mc.player.inventory.armorItemInSlot(2);
			ISpaceJetpackState cap = jetpack.getCapability(SpaceJetpackProvider.SpaceJetpackCapability, EnumFacing.UP);
			int fuelLevel;
			
			if (cap.getTank().getCapacity() <= 0)
			{
				fuelLevel = 0;
			} else
			{
				fuelLevel = cap.getTank().getFluidAmount() * 44 / cap.getTank().getCapacity() / ConfigManagerCore.rocketFuelFactor;
			}
			drawTexturedModalRect(xPos + 1, yPos + 1 + 44 - fuelLevel, 19, 45 - fuelLevel, 44, fuelLevel);
			
		}
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		
	}
	
	@SubscribeEvent
	public void onEntityFall(LivingFallEvent event)
	{
		if (event.getEntity().world.isRemote && event.getEntityLiving() instanceof EntityPlayer && event.getEntityLiving().world.provider instanceof WorldProviderOrbitModif)
		{
			PacketHandler
					.sendToServer(new SyncPlayerFallPacket(event.getDistance() * ((IGalacticraftWorldProvider) event.getEntityLiving().world.provider).getFallDamageModifier()));
		}
	}
	
	//	@SubscribeEvent
	//	public void frefallm(ZeroGravityEvent.InFreefall e)
	//	{
	//		if (e.provider instanceof WorldProviderOrbitModif)
	//		{
	//			if (WorldProviderOrbitModif.artificialG > 0.2)
	//			{
	//				e.setCanceled(true);
	//			}
	//		}
	//	}
	
	//RenderHandEvent
	//RenderPlayerEvent.Pre, RPE.Specials.Pre
	
}

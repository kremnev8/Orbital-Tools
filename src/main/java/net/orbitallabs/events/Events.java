
package net.orbitallabs.events;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import micdoodle8.mods.galacticraft.api.entity.IRocketType;
import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.GCFluids;
import micdoodle8.mods.galacticraft.core.dimension.WorldProviderOverworldOrbit;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.tile.TileEntityParaChest;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.OxygenUtil;
import net.minecraft.block.BlockAir;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.event.AttachCapabilitiesEvent;
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
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.items.ItemSpaceJetpack;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.LaunchRocketPacket;
import net.orbitallabs.network.packets.SetThirdPersonPacket;
import net.orbitallabs.tiles.TileEntityDockingPort;
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
		if (event.player.world.provider instanceof WorldProviderOrbitModif)
		{
			EntityPlayer player = event.player;
			if (player != null)
			{
				GCPlayerStats playerStats = GCPlayerStats.get((EntityPlayerMP) player);
				OTLoger.logInfo("Player just docked to Space Station");
				OTLoger.logInfo("info:");
				
				PacketHandler.sendTo(new SetThirdPersonPacket(0), (EntityPlayerMP) player);
				
				if (player.world != null)
				{
					DockingPortSaveData savef = DockingPortSaveData.forWorld(player.world);
					World world = player.world;
					
					try
					{
						TimeUnit.SECONDS.sleep(5);
					} catch (InterruptedException e)
					{
					}
					boolean docked = false;
					if (savef.DockingPorts.size() > 0)
					{
						OTLoger.logInfo("Dim have a docking port info");
						for (int i = 0; i < savef.DockingPorts.size(); i++)
						{
							int[] pos = savef.DockingPorts.get(i);
							
							if (world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2])) != null)
							{
								TileEntityDockingPort te = (TileEntityDockingPort) world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
								
								if (te.getStackInSlot(te.getSizeInventory() - 2) == null && te.getStackInSlot(te.getSizeInventory() - 3) == null)
								{
									player.setPositionAndUpdate(pos[0] + 0.5D, pos[1] + 2, pos[2] + 0.5D);
									
									if (playerStats.getRocketStacks() != null)
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
										if (rocketI != null)
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
								chest.setSizeInventory(playerStats.getRocketStacks().size() + 1);
								NonNullList<ItemStack> RCis = playerStats.getRocketStacks();
								
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
								break;
								
							}
						}
						
						if (!docked) OTLoger.logInfo("Dim haven't a docking port info");
					}
				}
			}
		}
	}
	
	//CLIENT
	
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
					ItemSpaceJetpack jetpack = (ItemSpaceJetpack) mc.player.inventory.armorItemInSlot(2).getItem();
					if (!(jetpack.KeysPressed.equals(keys)) && jetpack.activated)
					{
						jetpack.KeysPressed = keys;
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
		VertexBuffer buf = tessellator.getBuffer();
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
		if (mc.player.inventory.armorItemInSlot(2) != null && mc.player.inventory.armorItemInSlot(2).getItem() == ItemMod.spaceJetpack)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(Fuel);
			
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			GL11.glEnable(GL11.GL_ALPHA_TEST);
			
			drawTexturedModalRect(xPos, yPos, 0, 0, 19, 47);
			
			ItemSpaceJetpack jetpack = (ItemSpaceJetpack) mc.player.inventory.armorItemInSlot(2).getItem();
			
			int fuelLevel;
			
			if (jetpack.RCSFuel.getCapacity() <= 0)
			{
				fuelLevel = 0;
			} else
			{
				fuelLevel = jetpack.RCSFuel.getFluidAmount() * 44 / jetpack.RCSFuel.getCapacity() / ConfigManagerCore.rocketFuelFactor;
			}
			drawTexturedModalRect(xPos + 1, yPos + 1 + 44 - fuelLevel, 19, 45 - fuelLevel, 44, fuelLevel);
			
		}
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderPlayerPre(RenderPlayerEvent.Pre event)
	{
		GL11.glPushMatrix();
		
		final EntityPlayer player = event.getEntityPlayer();
		
		if (player.getRidingEntity() instanceof EntityRocketFakeTiered && player == Minecraft.getMinecraft().player)
		{
			EntityRocketFakeTiered entity = (EntityRocketFakeTiered) player.getRidingEntity();
			GL11.glTranslatef(0, -entity.getRotateOffset() - 1.6200000047683716F, 0);
			float anglePitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * event.getPartialRenderTick();
			float angleYaw = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * event.getPartialRenderTick();
			GL11.glRotatef(-angleYaw, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(anglePitch, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0, entity.getRotateOffset() + 1.6200000047683716F, 0);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderPlayerPost(RenderPlayerEvent.Post event)
	{
		GL11.glPopMatrix();
	}
	
	ResourceLocation capKey = new ResourceLocation(OrbitalModInfo.MOD_ID);
	
	@SubscribeEvent
	public void attachItemCaps(AttachCapabilitiesEvent<Item> e)
	{
		if (e.getObject() != null && e.getObject() instanceof ItemSpaceJetpack)
		{
			//	e.addCapability(capKey, (ItemSpaceJetpack) ItemMod.spaceJetpack);
		}
	}
	
}

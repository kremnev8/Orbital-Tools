
package net.orbitallabs.events;

import org.lwjgl.input.Keyboard;
import micdoodle8.mods.galacticraft.core.client.KeyHandler;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.fml.common.gameevent.TickEvent.Type;
import net.orbitallabs.entity.EntityRocketFakeTiered;
import net.orbitallabs.entity.EntityRocketFakeTiered.EnumLaunchPhase;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.items.ItemSpaceJetpack;
import net.orbitallabs.items.SpaceJetpackCapability;
import net.orbitallabs.items.SpaceJetpackItemStackCap;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.DismountPacket;
import net.orbitallabs.network.packets.OpenRocketFuelGuiPacket;
import net.orbitallabs.renderer.animations.AnimationHandlerJetpack;
import net.orbitallabs.utils.OrbitalModInfo;

public class KeyHandlerClient extends KeyHandler {
	public static KeyBinding openFuelGui;
	public static KeyBinding TestAnim;
	
	private int updates = 0;
	
	static
	{
		openFuelGui = micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient.openFuelGui;
		
		TestAnim = new KeyBinding(GCCoreUtil.translate("Jetpack Toggle"), Keyboard.KEY_H, OrbitalModInfo.MOD_NAME);
	}
	public static KeyBinding accelerateKey;
	public static KeyBinding decelerateKey;
	public static KeyBinding leftKey;
	public static KeyBinding rightKey;
	public static KeyBinding upKey;
	public static KeyBinding downKey;
	public static KeyBinding spaceKey;
	public static KeyBinding leftShiftKey;
	private static Minecraft mc = Minecraft.getMinecraft();
	
	public KeyHandlerClient()
	{
		super(new KeyBinding[] { openFuelGui, TestAnim }, new boolean[] { false, false }, getVanillaKeyBindings(), new boolean[] { false, true, true, true, true, true, true });
	}
	
	private static KeyBinding[] getVanillaKeyBindings()
	{
		KeyBinding invKey = mc.gameSettings.keyBindInventory;
		accelerateKey = mc.gameSettings.keyBindForward;
		decelerateKey = mc.gameSettings.keyBindBack;
		leftKey = mc.gameSettings.keyBindLeft;
		rightKey = mc.gameSettings.keyBindRight;
		upKey = mc.gameSettings.keyBindForward;
		downKey = mc.gameSettings.keyBindBack;
		spaceKey = mc.gameSettings.keyBindJump;
		leftShiftKey = mc.gameSettings.keyBindSneak;
		return new KeyBinding[] { invKey, accelerateKey, decelerateKey, leftKey, rightKey, spaceKey, leftShiftKey };
	}
	
	@Override
	public void keyDown(Type types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
	{
		if (mc.player != null && tickEnd)
		{
			EntityPlayer playerBase = PlayerUtil.getPlayerBaseClientFromPlayer(mc.player, false);
			
			if (playerBase == null)
			{
				return;
			}
			
			if (kb.getKeyCode() == openFuelGui.getKeyCode())
			{
				if (playerBase.getRidingEntity() instanceof EntityRocketFakeTiered)
				{
					EntityRocketFakeTiered rocket = (EntityRocketFakeTiered) playerBase.getRidingEntity();
					if (rocket.launchPhase == EnumLaunchPhase.DOCKED.ordinal())
					{
						PacketHandler.sendToServer(new OpenRocketFuelGuiPacket(playerBase.getRidingEntity().getEntityId()));
					}
				}
			} else if (kb.getKeyCode() == TestAnim.getKeyCode() && playerBase.inventory.armorItemInSlot(2) != null
					&& playerBase.inventory.armorItemInSlot(2).getItem() == ItemMod.spaceJetpack)
			{
				ItemStack stack = playerBase.inventory.armorItemInSlot(2);
				ItemSpaceJetpack jetpack = (ItemSpaceJetpack) stack.getItem();
				
				SpaceJetpackItemStackCap cap = (SpaceJetpackItemStackCap) stack.getCapability(SpaceJetpackCapability.SpaceJetpackCapability, EnumFacing.UP);
				if (cap != null)
				{
					if (cap.getAnimationHandler().isAnimationActive("Enabled idle"))
					{
						jetpack.setActive(false);
						((AnimationHandlerJetpack) cap.getAnimationHandler()).activateAnimation("Disable", 0, true);
						//cap.markDirty();
					} else if (cap.getAnimationHandler().isAnimationActive("Disabled idle"))
					{
						jetpack.setActive(true);
						((AnimationHandlerJetpack) cap.getAnimationHandler()).activateAnimation("Enable", 0, true);
						//cap.markDirty();
					} else if (cap.getAnimationHandler().animCurrentChannels.size() == 0)
					{
						jetpack.setActive(true);
						((AnimationHandlerJetpack) cap.getAnimationHandler()).activateAnimation("Enable", 0, true);
						//	cap.markDirty();
					}
				}
			}
		}
		
		if (mc.player != null && mc.currentScreen == null)
		{
			int keyNum = -1;
			
			if (kb == accelerateKey)
			{
				keyNum = 0;
			} else if (kb == decelerateKey)
			{
				keyNum = 1;
			} else if (kb == leftKey)
			{
				keyNum = 2;
			} else if (kb == rightKey)
			{
				keyNum = 3;
			} else if (kb == spaceKey)
			{
				keyNum = 4;
			} else if (kb == leftShiftKey)
			{
				keyNum = 5;
			}
			
			Entity entityTest = mc.player.getRidingEntity();
			if (entityTest != null && entityTest instanceof EntityRocketFakeTiered && keyNum == 5)
			{
				
				if (kb.getKeyCode() == mc.gameSettings.keyBindInventory.getKeyCode())
				{
					KeyBinding.setKeyBindState(mc.gameSettings.keyBindInventory.getKeyCode(), false);
				}
				
				PacketHandler.sendToServer(new DismountPacket());
			}
			
		}
	}
	
	@Override
	public void keyUp(Type types, KeyBinding kb, boolean tickEnd)
	{
	}
}
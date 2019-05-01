
package net.orbitallabs.events;

import org.lwjgl.input.Keyboard;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.items.SpaceJetpackProvider;
import net.orbitallabs.items.SpaceJetpackStorage.ISpaceJetpackState;
import net.orbitallabs.utils.IDescrObject;

public class ItemsToolTips {
	
	@SubscribeEvent
	public void showToolTips(ItemTooltipEvent event)
	{
		ItemStack stack = event.getItemStack();
		if (stack.getItem() == ItemMod.spaceJetpack)
		{
			ISpaceJetpackState cap = stack.getCapability(SpaceJetpackProvider.SpaceJetpackCapability, EnumFacing.UP);
			FluidTank jetpack = cap.getTank();
			if (jetpack.getFluid() != null && jetpack.getFluidAmount() > 0)
			{
				event.getToolTip().add(jetpack.getFluid().getLocalizedName() + ": " + jetpack.getFluidAmount() + " / " + jetpack.getCapacity());
			}
			
		} else if (stack.getItem() == ItemMod.DebugTool)
		{
			event.getToolTip().add(TextFormatting.RED + "Used only by devs!");
		} else if (stack.getItem() == ItemMod.Builder)
		{
			if (stack.hasTagCompound())
			{
				NBTTagCompound tag = stack.getTagCompound();
				if (tag.hasKey("chests"))
				{
					NBTTagList list = tag.getTagList("chests", 11);
					if (list.tagCount() > 0)
					{
						event.getToolTip().add("Chest marked positions:");
						for (int i = 0; i < list.tagCount(); i++)
						{
							int[] strpos = list.getIntArrayAt(i);
							if (strpos.length > 2)
								event.getToolTip().add(TextFormatting.BLUE + "*" + TextFormatting.GRAY + " - x: " + strpos[0] + " y: " + strpos[1] + " z: " + strpos[2]);
						}
					}
				}
			}
		}
		if (stack.getItem() instanceof IDescrObject)
		{
			IDescrObject obj = (IDescrObject) stack.getItem();
			if (obj.showDescription(stack.getItemDamage()))
			{
				if (!obj.getDescription(stack.getItemDamage()).equals(""))
				{
					event.getToolTip().addAll(FMLClientHandler.instance().getClient().fontRenderer.listFormattedStringToWidth(obj.getDescription(stack.getItemDamage()), 150));
				}
				if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
				{
					event.getToolTip()
							.addAll(FMLClientHandler.instance().getClient().fontRenderer.listFormattedStringToWidth(obj.getShiftDescription(stack.getItemDamage()), 150));
				} else
				{
					if (!obj.getShiftDescription(stack.getItemDamage()).equals(""))
					{
						event.getToolTip().add(GCCoreUtil.translateWithFormat("item_desc.shift.name",
								GameSettings.getKeyDisplayString(FMLClientHandler.instance().getClient().gameSettings.keyBindSneak.getKeyCode())));
					}
				}
			}
		}
	}
	
}

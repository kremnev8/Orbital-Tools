
package net.orbitallabs.utils;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicPage;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.orbitallabs.gui.ContainerSchematicJetpack;
import net.orbitallabs.gui.GuiSchematicJetpack;
import net.orbitallabs.items.ItemMod;

public class SchematicJetpack implements ISchematicPage {
	@Override
	public int getPageID()
	{
		return 10 + SchematicsUtil.JetPackGuiID;
	}
	
	@Override
	public int getGuiID()
	{
		return SchematicsUtil.JetPackGuiID + 10;
	}
	
	@Override
	public ItemStack getRequiredItem()
	{
		return new ItemStack(ItemMod.schematicjetpack);
	}
	
	@Override
	public GuiScreen getResultScreen(EntityPlayer player, BlockPos pos)
	{
		return new GuiSchematicJetpack(player.inventory, pos.getX(), pos.getY(), pos.getZ());
	}
	
	@Override
	public Container getResultContainer(EntityPlayer player, BlockPos pos)
	{
		return new ContainerSchematicJetpack(player.inventory, pos.getX(), pos.getY(), pos.getZ());
	}
	
	@Override
	public int compareTo(ISchematicPage o)
	{
		if (this.getPageID() > o.getPageID())
		{
			return 1;
		} else
		{
			return -1;
		}
	}
}
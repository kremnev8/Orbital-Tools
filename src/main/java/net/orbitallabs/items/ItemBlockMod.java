package net.orbitallabs.items;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.blocks.BlockMod;
import net.orbitallabs.utils.IDescrObject;

public class ItemBlockMod extends ItemBlock implements IDescrObject {
	
	public ItemBlockMod(Block block)
	{
		super(block);
		this.setRegistryName(block.getRegistryName());
		if (block instanceof BlockMod)
		{
			this.setHasSubtypes(((BlockMod) block).HasSubtypes());
		}
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public String getItemStackDisplayName(ItemStack is)
	{
		return I18n.format(block.getUnlocalizedName());
	}
	
	@Override
	public String getDescription(int meta)
	{
		if (block instanceof IDescrObject)
		{
			return ((IDescrObject) block).getDescription(meta);
		}
		return "";
	}
	
	@Override
	public String getShiftDescription(int meta)
	{
		if (block instanceof IDescrObject)
		{
			return ((IDescrObject) block).getShiftDescription(meta);
		}
		return "";
	}
	
	@Override
	public boolean showDescription(int meta)
	{
		if (block instanceof IDescrObject)
		{
			return ((IDescrObject) block).showDescription(meta);
		}
		return false;
	}
	
}
package net.orbitallabs.items;

import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.orbitallabs.utils.IDescrObject;

public class ItemBlockMod extends ItemBlock implements IDescrObject {
	
	private final Block block;
	
	public ItemBlockMod(Block block)
	{
		super(block);
		this.block = block;
		this.setRegistryName(block.getRegistryName());
	}
	
	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
	
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
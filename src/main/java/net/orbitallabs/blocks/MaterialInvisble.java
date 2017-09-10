
package net.orbitallabs.blocks;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;

public class MaterialInvisble extends Material {
	
	public MaterialInvisble(MapColor p_i2113_1_)
	{
		super(p_i2113_1_);
	}
	
	public boolean isSolid()
	{
		return false;
	}
	
	/**
	 * Will prevent grass from growing on dirt underneath and kill any grass below it if it returns true
	 */
	public boolean getCanBlockGrass()
	{
		return false;
	}
	
	/**
	 * Returns if this material is considered solid or not
	 */
	public boolean blocksMovement()
	{
		return true;
	}
}
package net.orbitallabs.structures;

import net.minecraft.block.Block;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Rotation;
import net.minecraft.world.gen.structure.template.Template;

public class AdvancedTemplate extends Template {

	public Rotation rotation;
	public boolean clear;
	public Block filler;

	public AdvancedTemplate(Rotation rot, boolean clear, Block filler) {
		rotation = rot;
		this.clear = clear;
		this.filler = filler;
	}

	public void read(NBTTagCompound tag) {

		NBTTagList palette = tag.getTagList("palette", 10);

		for (int i = 0; i < palette.tagCount(); ++i) {
			NBTTagCompound block = palette.getCompoundTagAt(i);
			IBlockState state = NBTUtil.readBlockState(block);
			if (clear) {
				if (state.getBlock() != Blocks.STONE) {
					block.setString("Name", "minecraft:air");
					block.removeTag("Properties");
				}
			} else {
				IProperty<?> iproperty = state.getBlock().getBlockState().getProperty("facing");
				if (iproperty != null) {
					NBTTagCompound props = block.getCompoundTag("Properties");
					EnumFacing dir = EnumFacing.byName(props.getString("facing"));
					dir = rotation.rotate(dir);
					props.setString("facing", dir.getName());
				}
			}
		}
		super.read(tag);
	}

}

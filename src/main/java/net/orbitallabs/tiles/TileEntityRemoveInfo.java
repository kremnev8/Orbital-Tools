package net.orbitallabs.tiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;

public class TileEntityRemoveInfo extends TileEntity implements ITickable {

	public List<TileEntityInfo> infoBlocks = new ArrayList();
	public List<int[]> poses = new ArrayList();
	private int times_read;

	// public TileEntityInfo infoBlock;
	// private int x,y,z;

	public TileEntityRemoveInfo() {
		super();
	}

	public void configureTileEntity(TileEntityInfo te) {
		this.infoBlocks.add(te);
	}

	@Override
	public void update() {
		if (infoBlocks.size() < poses.size() + times_read
				&& !(poses.get(0)[0] == 0 && poses.get(0)[1] == 0 && poses.get(0)[2] == 0) && world != null) {
			infoBlocks.add((TileEntityInfo) world
					.getTileEntity(new BlockPos(poses.get(0)[0], poses.get(0)[1], poses.get(0)[2])));
			times_read++;
			poses.remove(0);
		} else if (infoBlocks.size() < poses.size() + times_read && world != null) {
			poses.remove(0);
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);
		if (!tag.hasKey("NO_TE")) {
			NBTTagList list = tag.getTagList("info_blocks", 10);
			for (int i = 0; i < list.tagCount(); i++) {
				NBTTagCompound pos = list.getCompoundTagAt(i);
				poses.add(new int[] { pos.getInteger("x"), pos.getInteger("y"), pos.getInteger("z") });
			}
		}
		if (tag.hasKey("infopos")) {
			NBTTagCompound pos = tag.getCompoundTag("infopos");
			poses.add(new int[] { pos.getInteger("x"), pos.getInteger("y"), pos.getInteger("z") });
		}
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		if (infoBlocks != null && infoBlocks.size() > 0) {
			NBTTagList list = new NBTTagList();
			// tag.setInteger("TE_le", infoBlocks.size());
			for (int i = 0; i < infoBlocks.size(); i++) {
				NBTTagCompound pos = new NBTTagCompound();
				pos.setInteger("x", infoBlocks.get(i).getPos().getX());
				pos.setInteger("y", infoBlocks.get(i).getPos().getY());
				pos.setInteger("z", infoBlocks.get(i).getPos().getZ());
				list.appendTag(pos);
			}
			tag.setTag("info_blocks", list);
		} else {
			if (poses != null && poses.size() > 0) {
				// tag.setInteger("TE_le", poses.size());
				NBTTagList list = new NBTTagList();
				for (int i = 0; i < poses.size(); i++) {
					if (!(poses.get(i)[0] == 0 && poses.get(i)[1] == 0 && poses.get(i)[2] == 0)) {
						NBTTagCompound pos = new NBTTagCompound();
						pos.setInteger("x", poses.get(i)[0]);
						pos.setInteger("y", poses.get(i)[1]);
						pos.setInteger("z", poses.get(i)[2]);
						list.appendTag(pos);
					}
				}
				tag.setTag("info_blocks", list);
			} else {
				tag.setByte("NO_TE", (byte) 0);
			}
		}
		return tag;
	}
}

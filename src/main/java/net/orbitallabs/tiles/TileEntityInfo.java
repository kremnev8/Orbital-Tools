package net.orbitallabs.tiles;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.orbitallabs.structures.Structure;

public class TileEntityInfo extends TileEntity implements ITickable {

	public Structure Object;
	public List<Structure> ChildObjects = new ArrayList<Structure>();
	public List<Structure> AddObjects = new ArrayList<Structure>();
	public BlockPos Rinfo;

	public TileEntityInfo() {
		super();
	}

	@Override
	public void update() {
		if (Rinfo != null) {
			TileEntity Rtile = world.getTileEntity(Rinfo);
			if (Rtile != null && Rtile instanceof TileEntityRemoveInfo) {
				TileEntityRemoveInfo info = (TileEntityRemoveInfo) Rtile;
				info.infoBlocks.add(this);
				Rinfo = null;
			}
		}
	}

	public void configureTileEntity(EnumFacing Pldir, int rot, Structure strc, BlockPos PlPos) {
		this.Object = strc;
		Object.placementDir = Pldir;
		Object.placementRotation = rot;
		Object.placementPos = PlPos;
	}

	/**
	 * Configire what you need. for clild object use "CHILD". for additional
	 * structure use "ADD".
	 */
	public void configureTileEntity(String type, Structure Str) {
		if (Str != null) {
			if (type == "ADD") {
				this.AddObjects.add(Str);
			} else if (type == "CHILD") {
				this.ChildObjects.add(Str);
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound tag) {
		super.readFromNBT(tag);

		if (tag.hasKey("OBJ")) {
			NBTTagCompound obj = tag.getCompoundTag("OBJ");
			Object = Structure.FindStructure(obj.getString("OBJ"));
			Object.Configure(obj.getIntArray("POS"), obj.getInteger("ROT"), EnumFacing.getFront(obj.getInteger("DIR")));
			if (Object.connections == null) {
				Object.connections = new ArrayList();
			}
		}

		if (tag.hasKey("CHILD")) {
			NBTTagList child = tag.getTagList("CHILD", 10);
			for (int i = 0; i < child.tagCount(); i++) {
				NBTTagCompound obj = child.getCompoundTagAt(i);
				Structure str = Structure.FindStructure(obj.getString("OBJ"));
				int[] t1 = obj.getIntArray("POS");
				int t2 = obj.getInteger("ROT");
				EnumFacing t3 = EnumFacing.getFront(obj.getInteger("DIR"));
				str.Configure(t1, t2, t3);

				if (obj.hasKey("CONN")) {
					NBTTagList conn = obj.getTagList("CONN", 10);
					for (int j = 0; j < conn.tagCount(); j++) {
						NBTTagCompound cnobj = conn.getCompoundTagAt(i);
						Structure CNstr = Structure.FindStructure(cnobj.getString("OBJ"));
						int[] CNt1 = cnobj.getIntArray("POS");
						int CNt2 = cnobj.getInteger("ROT");
						EnumFacing CNt3 = EnumFacing.getFront(cnobj.getInteger("DIR"));
						str.Configure(CNt1, CNt2, CNt3);
						str.connections.add(CNstr);
					}
				}

				ChildObjects.add(str);
			}
		}

		if (tag.hasKey("ADDOBJ")) {
			NBTTagList add = tag.getTagList("ADDOBJ", 10);
			for (int i = 0; i < add.tagCount(); i++) {
				NBTTagCompound obj = add.getCompoundTagAt(i);
				Structure str = Structure.FindStructure(obj.getString("OBJ"));
				int[] t1 = obj.getIntArray("POS");
				int t2 = obj.getInteger("ROT");
				EnumFacing t3 = EnumFacing.getFront(obj.getInteger("DIR"));
				str.Configure(t1, t2, t3);
				AddObjects.add(str);
			}
		}

		if (tag.hasKey("CONN")) {
			NBTTagList conn = tag.getTagList("CONN", 10);
			for (int i = 0; i < conn.tagCount(); i++) {
				NBTTagCompound cnobj = conn.getCompoundTagAt(i);
				Structure str = Structure.FindStructure(cnobj.getString("OBJ"));
				int[] t1 = cnobj.getIntArray("POS");
				int t2 = cnobj.getInteger("ROT");
				EnumFacing t3 = EnumFacing.getFront(cnobj.getInteger("DIR"));
				str.Configure(t1, t2, t3);
				this.Object.connections.add(str);
			}
		}
		if (tag.hasKey("rinfopos")) {
			NBTTagCompound pos = tag.getCompoundTag("rinfopos");
			Rinfo = new BlockPos(pos.getInteger("x"), pos.getInteger("y"), pos.getInteger("z"));
		}
	}

	public static int[] getInt(BlockPos pos) {
		return new int[] { pos.getX(), pos.getY(), pos.getZ() };
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound tag) {
		super.writeToNBT(tag);
		if (Object != null) {
			NBTTagCompound obj = new NBTTagCompound();
			obj.setInteger("DIR", Object.placementDir.getIndex());
			obj.setInteger("ROT", Object.placementRotation);
			obj.setIntArray("POS", getInt(Object.placementPos));
			obj.setString("OBJ", Object.getUnlocalizedName());
			tag.setTag("OBJ", obj);
		}
		if (ChildObjects != null && ChildObjects.size() > 0) {
			NBTTagList child = new NBTTagList();

			for (int i = 0; i < ChildObjects.size(); i++) {
				NBTTagCompound obj = new NBTTagCompound();
				obj.setInteger("DIR", ChildObjects.get(i).placementDir.getIndex());
				obj.setInteger("ROT", ChildObjects.get(i).placementRotation);
				obj.setIntArray("POS", getInt(ChildObjects.get(i).placementPos));
				obj.setString("OBJ", ChildObjects.get(i).getUnlocalizedName());

				if (ChildObjects.get(i).connections != null && ChildObjects.get(i).connections.size() > 0) {
					NBTTagList conn = new NBTTagList();

					for (int j = 0; j < ChildObjects.get(i).connections.size(); j++) {
						NBTTagCompound cn = new NBTTagCompound();
						cn.setInteger("DIR", ChildObjects.get(i).connections.get(j).placementDir.getIndex());
						cn.setInteger("ROT", ChildObjects.get(i).connections.get(j).placementRotation);
						cn.setIntArray("POS", getInt(ChildObjects.get(i).connections.get(j).placementPos));
						cn.setString("OBJ", ChildObjects.get(i).connections.get(j).getUnlocalizedName());
						conn.appendTag(cn);
					}
					obj.setTag("CONN", conn);
				}
				child.appendTag(obj);

			}
			tag.setTag("CHILD", child);
		}

		if (AddObjects != null && AddObjects.size() > 0) {
			NBTTagList add = new NBTTagList();

			for (int i = 0; i < AddObjects.size(); i++) {
				NBTTagCompound obj = new NBTTagCompound();
				obj.setInteger("DIR", AddObjects.get(i).placementDir.getIndex());
				obj.setInteger("ROT", AddObjects.get(i).placementRotation);
				obj.setIntArray("POS", getInt(AddObjects.get(i).placementPos));
				obj.setString("OBJ", AddObjects.get(i).getUnlocalizedName());
				add.appendTag(obj);
			}
			tag.setTag("ADDOBJ", add);
		}

		if (Object != null && Object.connections != null && Object.connections.size() > 0) {
			NBTTagList conn = new NBTTagList();

			for (int i = 0; i < Object.connections.size(); i++) {
				NBTTagCompound obj = new NBTTagCompound();
				obj.setInteger("DIR", Object.connections.get(i).placementDir.getIndex());
				obj.setInteger("ROT", Object.connections.get(i).placementRotation);
				obj.setIntArray("POS", getInt(Object.connections.get(i).placementPos));
				obj.setString("OBJ", Object.connections.get(i).getUnlocalizedName());
				conn.appendTag(obj);
			}
			tag.setTag("CONN", conn);
		}
		return tag;
	}

}

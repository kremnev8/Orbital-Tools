
package net.orbitallabs.dimensions;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.world.storage.WorldSavedData;

public class DockingPortSaveData extends WorldSavedData {
	public static String saveDataID = "GliderDockPorts";
	
	public List<BlockPos> DockingPorts = new ArrayList();
	public List<BlockPos> GraviySources = new ArrayList();
	public List<BlockPos> frozenSands = new ArrayList<>();
	
	public DockingPortSaveData()
	{
		super(DockingPortSaveData.saveDataID);
	}
	
	public DockingPortSaveData(String key)
	{
		super(key);
		DockingPortSaveData.saveDataID = key;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		NBTTagList nbtlist = nbt.getTagList("DOCKPORTS", 11);
		List<BlockPos> dockports = new ArrayList();
		dockports.clear();
		if (nbtlist.tagCount() != 0)
		{
			
			for (int i = 0; i < nbtlist.tagCount(); i++)
			{
				int[] list = nbtlist.getIntArrayAt(i);
				dockports.add(new BlockPos(list[0], list[1], list[2]));
			}
		}
		this.DockingPorts = dockports;
		
		nbtlist = nbt.getTagList("GRAVITYSOURCES", 11);
		List<BlockPos> gravityS = new ArrayList();
		gravityS.clear();
		if (nbtlist.tagCount() != 0)
		{
			
			for (int i = 0; i < nbtlist.tagCount(); i++)
			{
				int[] list = nbtlist.getIntArrayAt(i);
				gravityS.add(new BlockPos(list[0], list[1], list[2]));
			}
		}
		this.GraviySources = gravityS;
		
		nbtlist = nbt.getTagList("FROZENSANDS", 11);
		List<BlockPos> frozenS = new ArrayList();
		frozenS.clear();
		if (nbtlist.tagCount() != 0)
		{
			
			for (int i = 0; i < nbtlist.tagCount(); i++)
			{
				int[] list = nbtlist.getIntArrayAt(i);
				frozenS.add(new BlockPos(list[0], list[1], list[2]));
			}
		}
		this.frozenSands = frozenS;
		
		if (nbt.getBoolean("UPDATED_GVALUE"))
		{
			WorldProviderOrbitModif.artificialG = nbt.getDouble("Artificial_GRAVITY");
			WorldProviderOrbitModif.updatedList = nbt.getBoolean("UPDATED_GVALUE");
		}
	}
	
	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt)
	{
		NBTTagList nbtlist = new NBTTagList();
		for (int i = 0; i < DockingPorts.size(); i++)
		{
			BlockPos pos = DockingPorts.get(i);
			nbtlist.appendTag(new NBTTagIntArray(new int[] { pos.getX(), pos.getY(), pos.getZ() }));
		}
		nbt.setTag("DOCKPORTS", nbtlist);
		
		nbtlist = new NBTTagList();
		for (int i = 0; i < GraviySources.size(); i++)
		{
			BlockPos pos = GraviySources.get(i);
			nbtlist.appendTag(new NBTTagIntArray(new int[] { pos.getX(), pos.getY(), pos.getZ() }));
		}
		nbt.setTag("GRAVITYSOURCES", nbtlist);
		
		nbtlist = new NBTTagList();
		for (int i = 0; i < frozenSands.size(); i++)
		{
			BlockPos pos = frozenSands.get(i);
			nbtlist.appendTag(new NBTTagIntArray(new int[] { pos.getX(), pos.getY(), pos.getZ() }));
		}
		nbt.setTag("FROZENSANDS", nbtlist);
		
		nbt.setBoolean("UPDATED_GVALUE", WorldProviderOrbitModif.updatedList);
		nbt.setDouble("Artificial_GRAVITY", WorldProviderOrbitModif.artificialG);
		return nbt;
	}
	
	public static DockingPortSaveData forWorld(World world)
	{
		// Retrieves the MyWorldData instance for the given world, creating it if necessary
		MapStorage storage = world.getPerWorldStorage();
		DockingPortSaveData result = (DockingPortSaveData) storage.getOrLoadData(DockingPortSaveData.class, saveDataID);
		if (result == null)
		{
			result = new DockingPortSaveData();
			storage.setData(saveDataID, result);
		}
		return result;
	}
}
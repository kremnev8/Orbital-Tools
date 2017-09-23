
package net.orbitallabs.dimensions;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;

public class DockingPortSaveData extends WorldSavedData {
	public static String saveDataID = "GliderDockPorts";
	
	public List<int[]> DockingPorts = new ArrayList();
	public List<int[]> GraviySources = new ArrayList();
	
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
		List<int[]> dockports = new ArrayList();
		dockports.clear();
		if (nbtlist.tagCount() != 0)
		{
			
			for (int i = 0; i < nbtlist.tagCount(); i++)
			{
				dockports.add(nbtlist.getIntArrayAt(i));
			}
		}
		this.DockingPorts = dockports;
		
		nbtlist = nbt.getTagList("GRAVITYSOURCES", 11);
		List<int[]> gravityS = new ArrayList();
		gravityS.clear();
		if (nbtlist.tagCount() != 0)
		{
			
			for (int i = 0; i < nbtlist.tagCount(); i++)
			{
				gravityS.add(nbtlist.getIntArrayAt(i));
			}
		}
		this.GraviySources = gravityS;
		
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
			nbtlist.appendTag(new NBTTagIntArray(DockingPorts.get(i)));
		}
		nbt.setTag("DOCKPORTS", nbtlist);
		
		nbtlist = new NBTTagList();
		for (int i = 0; i < GraviySources.size(); i++)
		{
			nbtlist.appendTag(new NBTTagIntArray(GraviySources.get(i)));
		}
		nbt.setTag("GRAVITYSOURCES", nbtlist);
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
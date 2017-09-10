package net.orbitallabs.structures;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.orbitallabs.utils.OreDictItemStack;

public class StructureData {
	
	public String name;
	public String uln;
	public int mainConnect;
	public int addConnect;
	public String specialFunc;
	public List<OreDictItemStack> requiredItems;
	public List<ItemStack> requiredItemsExamp = new ArrayList();
	
	public StructureData(String name,String uln,int mainC,int addC,String func)
	{
		this.name = name;
		this.uln = uln;
		this.mainConnect = mainC;
		this.addConnect = addC;
		this.specialFunc = func;
	}
	public StructureData(String uln,int mainC,int addC)
	{
		this.name = uln;
		this.uln = uln;
		this.mainConnect = mainC;
		this.addConnect = addC;
	}
	
	public StructureData addRequiredItems(List<OreDictItemStack> stacks)
	{
		requiredItems = stacks;
		for (int i = 0; i < requiredItems.size(); i++)
		{
			requiredItemsExamp.add(requiredItems.get(i).example);
		}
		return this;
	}
}

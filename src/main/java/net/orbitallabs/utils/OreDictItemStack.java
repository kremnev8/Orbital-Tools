package net.orbitallabs.utils;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictItemStack {
	
	public ItemStack example;
	public List<Integer> oreID;
	
	private List<Integer> TableToList(int[] tab)
	{
		if (tab == null || tab.length == 0)
		{
			return new ArrayList();
		}
		List<Integer> ls = new ArrayList();
		for (int i = 0; i < tab.length; i++)
		{
			ls.add(tab[i]);
		}
		return ls;
	}
	
	protected static ItemStack getOre(String oreName)
	{
		
		if (OreDictionary.doesOreNameExist(oreName))
		{
			try
			{
				return OreDictionary.getOres(oreName, true).get(0);
			} catch (IndexOutOfBoundsException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public OreDictItemStack(String oreName)
	{
		
		this(getOre(oreName), oreName);
	}
	
	public OreDictItemStack(ItemStack stack)
	{
		if (stack != null)
		{
			this.example = stack;
			oreID = TableToList(OreDictionary.getOreIDs(stack));
		}
	}
	
	public OreDictItemStack(ItemStack stack, String oreDict)
	{
		if (stack != null)
		{
			this.example = stack;
			oreID = TableToList(OreDictionary.getOreIDs(stack));
		}
		if (OreDictionary.doesOreNameExist(oreDict))
		{
			oreID.add(OreDictionary.getOreID(oreDict));
		}
		
	}
	
	public OreDictItemStack(ItemStack stack, String[] oreDict)
	{
		if (stack != null)
		{
			this.example = stack;
			oreID = TableToList(OreDictionary.getOreIDs(stack));
		}
		if (oreDict != null)
		{
			for (int i = 0; i < oreDict.length; i++)
			{
				if (OreDictionary.doesOreNameExist(oreDict[i]))
				{
					oreID.add(OreDictionary.getOreID(oreDict[i]));
				}
			}
		}
		
	}
	
	public OreDictItemStack(String[] oreDict)
	{
		if (oreDict != null)
		{
			for (int i = 0; i < oreDict.length; i++)
			{
				if (OreDictionary.doesOreNameExist(oreDict[i]))
				{
					oreID.add(OreDictionary.getOreID(oreDict[i]));
				}
			}
		}
		
	}
	
	@Deprecated
	public OreDictItemStack(OreDictItemStack stack)
	{
		this.example = stack.example.copy();
		Integer[] ar = (Integer[]) stack.oreID.toArray();
		int[] ar2 = new int[ar.length];
		for (int i = 0; i < ar.length; i++)
		{
			ar2[i] = ar[i];
		}
		this.oreID = TableToList(ar2);
	}
	
	private static boolean ArrContain1SameInt(List<Integer> list1, List<Integer> list2)
	{
		if (list1 != null && list2 != null)
		{
			for (int i = 0; i < list1.size(); i++)
			{
				for (int j = 0; j < list2.size(); j++)
				{
					if (list1.get(i) == list2.get(j))
					{
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean isItemEqual(OreDictItemStack other)
	{
		
		return other != null && (oreID.size() > 0 && ArrContain1SameInt(oreID, other.oreID) || (other.example != null && example.getItem() == other.example.getItem()
				&& (!example.getHasSubtypes() || other.example.getItemDamage() == example.getItemDamage())));
	}
	
	public boolean isStackEqual(OreDictItemStack other, boolean ignoreStackSize)
	{
		
		return isItemEqual(other) && other.example != null && (example.getCount() <= other.example.getCount() || ignoreStackSize);
	}
	
	public boolean isStackValid()
	{
		
		return example != null || (oreID != null && oreID.size() > 0);
	}
	
	public ItemStack toItemStack()
	{
		
		ItemStack ret = example != null ? example : null;
		if (ret != null)
		{
			return ret;
		} else
		{
			if (this.isStackValid())
			{
				for (int i = 0; i < oreID.size(); i++)
				{
					List<ItemStack> ls = OreDictionary.getOres(OreDictionary.getOreName(oreID.get(0)), true);
					if (ls.size() > 0)
					{
						return ls.get(0);
					}
				}
			}
		}
		return null;
	}
	
	@Override
	public OreDictItemStack clone()
	{
		
		return new OreDictItemStack(this);
	}
	
	@Override
	public boolean equals(Object o)
	{
		
		if (!(o instanceof OreDictItemStack))
		{
			return false;
		}
		return isItemEqual((OreDictItemStack) o);
	}
	
	public String toString()
	{
		String ret = "";
		if (example != null)
		{
			ret = example.getCount() + "x" + example.getItem().getUnlocalizedName() + "@" + example.getItemDamage();
		}
		if (oreID != null && oreID.size() > 0)
		{
			for (int i = 0; i < oreID.size(); i++)
			{
				ret = ret + " | Ore:" + oreID.get(i);
			}
		}
		if (ret == "")
		{
			return super.toString();
		}
		return ret;
	}
	
}

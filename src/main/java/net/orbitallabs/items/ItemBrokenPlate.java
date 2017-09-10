
package net.orbitallabs.items;

import java.util.Random;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.orbitallabs.utils.OrbitalModInfo;

public class ItemBrokenPlate extends ItemMod {
	
	private int type;
	private Random rand = new Random();
	
	public ItemBrokenPlate(String uln, int type)
	{
		super(uln, false);
		this.type = type;
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
		this.setNoRepair();
		this.setShowDesrc(true);
		
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
		{
			for (int i = 0; i < 4; i++)
			{
				ModelLoader.setCustomModelResourceLocation(this, i, new ModelResourceLocation(OrbitalModInfo.MOD_ID + ":" + uln + i, "inventory"));
			}
		}
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems)
	{
		if (tab == CreativeTabs.MATERIALS)
		{
			for (int i = 0; i < 4; i++)
			{
				subItems.add(new ItemStack(item, 1, i));
			}
		}
	}
	
	@Override
	public CreativeTabs[] getCreativeTabs()
	{
		return new CreativeTabs[] { CreativeTabs.MATERIALS };
	}
	
	@Override
	public int getMetadata(int damage)
	{
		return damage;
	}
	
}

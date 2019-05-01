package net.orbitallabs.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.orbitallabs.items.ItemBlockMod;
import net.orbitallabs.utils.IDescrObject;
import net.orbitallabs.utils.OrbitalModInfo;

public class BlockMod extends Block implements IDescrObject {
	
	private String name;
	protected boolean show = false;
	public boolean hasSubtypes = false;
	
	public static BlockMod BuildpPoint;
	
	public static void init()
	{
		BuildpPoint = new BlockBuildPoint("buildpoint");
	}
	
	public BlockMod(String uln)
	{
		this(uln, SoundType.STONE, Material.ROCK, 0.5F, 10.0F, true);
	}
	
	public BlockMod(String uln, boolean reg)
	{
		super(Material.ROCK);
		this.name = uln;
		this.setRegistryName(uln);
		if (reg)
		{
			ForgeRegistries.BLOCKS.register(this);
			//GameRegistry.register(this);
			ItemBlockMod ItemBM = new ItemBlockMod(this);
			ForgeRegistries.ITEMS.register(ItemBM);
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
			{
				ModelLoader.setCustomModelResourceLocation(ItemBM, 0, new ModelResourceLocation(OrbitalModInfo.MOD_ID + ":" + uln, "inventory"));
			}
		}
	}
	
	public BlockMod(String uln, SoundType type, Material material, float har, float res, boolean reg)
	{
		super(material);
		this.name = uln;
		this.setRegistryName(uln);
		this.setSoundType(type);
		this.setResistance(res);
		this.setHardness(har);
		this.setCreativeTab(CreativeTabs.DECORATIONS);
		if (reg)
		{
			ForgeRegistries.BLOCKS.register(this);
			ItemBlockMod ItemBM = new ItemBlockMod(this);
			ForgeRegistries.ITEMS.register(ItemBM);
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
			{
				if (HasSubtypes())
				{
					NonNullList<ItemStack> list = NonNullList.create();
					this.getSubBlocks(this.getCreativeTabToDisplayOn(), list);
					for (int i = 0; i < list.size(); i++)
					{
						ModelLoader.setCustomModelResourceLocation(ItemBM, list.get(i).getMetadata(), new ModelResourceLocation(OrbitalModInfo.MOD_ID + ":" + uln, "inventory"));
					}
				} else
				{
					ModelLoader.setCustomModelResourceLocation(ItemBM, 0, new ModelResourceLocation(OrbitalModInfo.MOD_ID + ":" + uln, "inventory"));
				}
			}
		}
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return OrbitalModInfo.MOD_ID + ".block." + name + ".name";
	}
	
	@Override
	public String getDescription(int meta)
	{
		return "";
	}
	
	@Override
	public String getShiftDescription(int meta)
	{
		return I18n.format(OrbitalModInfo.MOD_ID + ".descr_shift." + name + ".name");
	}
	
	@Override
	public boolean showDescription(int meta)
	{
		return show;
	}
	
	protected void setShowDesrc(boolean state)
	{
		show = state;
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean HasSubtypes()
	{
		return hasSubtypes;
		
	}
}

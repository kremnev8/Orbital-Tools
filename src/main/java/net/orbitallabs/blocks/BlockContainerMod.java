package net.orbitallabs.blocks;

import java.util.Random;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.orbitallabs.items.ItemBlockMod;
import net.orbitallabs.utils.IDescrObject;
import net.orbitallabs.utils.OrbitalModInfo;

public class BlockContainerMod extends BlockContainer implements IDescrObject {
	
	Random r = new Random();
	private String name;
	protected boolean show = false;
	
	public static BlockContainerMod DockingPoint;
	public static BlockContainerMod BlockInfo;
	public static BlockContainerMod BlockRemoveInfo;
	public static BlockContainerMod BlockArticialGsource;
	public static BlockContainerMod BlockArmorStand;
	public static BlockFake BlockFake;
	
	public static void init()
	{
		DockingPoint = new BlockDockingPoint("dockingpoint");
		BlockInfo = new BlockInfo("infoblock");
		BlockRemoveInfo = new BlockRemoveInfo("removeinfoblock");
		BlockArticialGsource = new BlockArtificialGravitySource("artificialgsource");
		BlockArmorStand = new BlockArmorStand("armorstand");
		BlockFake = new BlockFake("blockfake");
	}
	
	public BlockContainerMod(String uln)
	{
		this(uln, SoundType.STONE, Material.ROCK, 0.5F, 10.0F, true);
	}
	
	public BlockContainerMod(String uln, Material mat)
	{
		this(uln, SoundType.STONE, mat, 0.5F, 10.0F, true);
	}
	
	public BlockContainerMod(String uln, boolean reg)
	{
		super(Material.ROCK);
		this.name = uln;
		this.setRegistryName(uln);
		if (reg)
		{
			ForgeRegistries.BLOCKS.register(this);
			ItemBlockMod ItemBM = new ItemBlockMod(this);
			ForgeRegistries.ITEMS.register(ItemBM);
			if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
			{
				ModelLoader.setCustomModelResourceLocation(ItemBM, 0, new ModelResourceLocation(OrbitalModInfo.MOD_ID + ":" + uln, "inventory"));
			}
		}
	}
	
	public BlockContainerMod(String uln, SoundType type, Material material, float har, float res, boolean reg)
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
				ModelLoader.setCustomModelResourceLocation(ItemBM, 0, new ModelResourceLocation(OrbitalModInfo.MOD_ID + ":" + uln, "inventory"));
			}
		}
	}
	
	public String getUnlocalizedName(ItemStack is)
	{
		return getUnlocalizedName();
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return OrbitalModInfo.MOD_ID + ".tile." + name + ".name";
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta)
	{
		return null;
	}
	
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.MODEL;
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
}

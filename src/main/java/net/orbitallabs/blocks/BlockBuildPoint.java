package net.orbitallabs.blocks;

import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class BlockBuildPoint extends BlockMod {
	
	public static final PropertyEnum<EnumBlockPointStates> propertyStates = PropertyEnum.<EnumBlockPointStates> create("enumstates", EnumBlockPointStates.class);
	
	public BlockBuildPoint(String uln)
	{
		super(uln);
		this.setCreativeTab(CreativeTabs.REDSTONE);
		this.setDefaultState(this.blockState.getBaseState().withProperty(propertyStates, EnumBlockPointStates.EVERYTHINGMINUSPIERCE));
	}
	
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(propertyStates, EnumBlockPointStates.getState(meta));
	}
	
	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumBlockPointStates) state.getValue(propertyStates)).getMeta();
	}
	
	/**
	 * 0 - everything, 1 - everything excluding pierce, 2 - only add structures,
	 * 3 - only window(only rot == 0), 4 - solar panels, 5 - greenhouse, 6 -
	 * pierce
	 */
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, NonNullList<ItemStack> list)
	{
		list.add(new ItemStack(item, 1, 0));
	}
	
	@Override
	public int damageDropped(IBlockState state)
	{
		return getMetaFromState(state);
	}
	
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(BlockMod.BuildpPoint, 1, 0);
	}
	
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { propertyStates });
	}
	
	public enum EnumBlockPointStates implements IStringSerializable
	{
		
		EVERYTHING(0, "everything"), EVERYTHINGMINUSPIERCE(1, "everything_minus_pierce"), ADDSTRUCTURES(2, "add_structures"), WINDOWS0(3, "windows_rot_0"), SOLARPANELS(4,
				"solar_panels"), GREENHOUSE(5, "only_greenhouse"), PIERCE(6, "only_pierce");
		
		private int meta;
		private String name;
		
		public static final EnumBlockPointStates[] VALUES = new EnumBlockPointStates[7];
		
		private EnumBlockPointStates(int meta, String name)
		{
			this.meta = meta;
			this.name = name;
		}
		
		public static EnumBlockPointStates getState(int meta)
		{
			return VALUES[MathHelper.abs(meta % VALUES.length)];
		}
		
		public int getMeta()
		{
			return meta;
		}
		
		@Override
		public String getName()
		{
			return name;
		}
		
		static
		{
			for (EnumBlockPointStates enumstate : values())
			{
				VALUES[enumstate.meta] = enumstate;
			}
		}
		
	}
	
}

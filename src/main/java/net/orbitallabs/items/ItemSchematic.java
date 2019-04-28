
package net.orbitallabs.items;

import micdoodle8.mods.galacticraft.api.recipe.ISchematicItem;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.entities.EntityHangingSchematic;
import micdoodle8.mods.galacticraft.core.items.ISortableItem;
import micdoodle8.mods.galacticraft.core.proxy.ClientProxyCore;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryItem;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.utils.OrbitalModInfo;

public class ItemSchematic extends ItemMod implements ISchematicItem, ISortableItem {
	
	private static int indexOffset;
	
	public ItemSchematic(String uln)
	{
		super(uln);
		this.setMaxDamage(0);
		this.setMaxStackSize(1);
		this.show = true;
		if (GCCoreUtil.getEffectiveSide() == Side.CLIENT)
		{
			GCItems.registerSorted(this);
		}
	}
	
	@Override
	public CreativeTabs getCreativeTab()
	{
		return GalacticraftCore.galacticraftItemsTab;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public EnumRarity getRarity(ItemStack par1ItemStack)
	{
		return ClientProxyCore.galacticraftItem;
	}
	
	@Override
	public String getDescription(int meta)
	{
		switch (meta) {
		case 0:
			return I18n.format(("schematic.jetpack.name"));
		}
		return "";
	}
	
	@Override
	public String getShiftDescription(int meta)
	{
		return "";
	}
	
	@Override
	public EnumSortCategoryItem getCategory(int meta)
	{
		return EnumSortCategoryItem.SCHEMATIC;
	}
	
	protected int getIndex(int damage)
	{
		return damage + indexOffset;
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer playerIn, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		ItemStack stack = playerIn.getHeldItem(hand);
		BlockPos blockpos = pos.offset(facing);
		
		if (facing != EnumFacing.DOWN && facing != EnumFacing.UP && playerIn.canPlayerEdit(blockpos, facing, stack))
		{
			EntityHangingSchematic entityhanging = this.createEntity(worldIn, blockpos, facing, this.getIndex(stack.getItemDamage()));
			
			if (entityhanging != null && entityhanging.onValidSurface())
			{
				if (!worldIn.isRemote)
				{
					entityhanging.playPlaceSound();
					worldIn.spawnEntity(entityhanging);
					entityhanging.sendToClient(worldIn, blockpos);
				}
				
				stack.shrink(1);
			}
			
			return EnumActionResult.SUCCESS;
		} else
		{
			return EnumActionResult.FAIL;
		}
	}
	
	public static void registerSchematicItems(Side side)
	{
		if (side == Side.SERVER) indexOffset = SchematicRegistry.registerSchematicItem(new ItemStack(ItemMod.schematicjetpack, 1, 0));
		if (side == Side.CLIENT) SchematicRegistry.registerTexture(new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/items/schematic_jetpack.png"));
	}
	
	private EntityHangingSchematic createEntity(World worldIn, BlockPos pos, EnumFacing clickedSide, int index)
	{
		return new EntityHangingSchematic(worldIn, pos, clickedSide, index);
	}
	
}


package net.orbitallabs.blocks;

import java.lang.reflect.Method;
import java.util.Random;
import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.energy.tile.TileBaseUniversalElectrical;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.galacticraft.core.util.CompatibilityManager;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.orbitallabs.OrbitalTools;
import net.orbitallabs.gui.GuiHandler;
import net.orbitallabs.tiles.TileEntityArmorStand;
import net.orbitallabs.utils.ChatUtils;
import net.orbitallabs.utils.LocalizedChatComponent;
import net.orbitallabs.utils.LocalizedString;

public class BlockArmorStand extends BlockContainerMod {
	
	public static final PropertyDirection FACING = BlockHorizontal.FACING;
	
	public BlockArmorStand(String uln)
	{
		super(uln);
		super.setSoundType(SoundType.METAL);
		this.setShowDesrc(true);
		this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, FACING.getAllowedValues().iterator().next()));
	}
	
	@Override
	public void breakBlock(World world, BlockPos pos, IBlockState state)
	{
		
		TileEntity tileEntity = world.getTileEntity(pos);
		 
		if (tileEntity instanceof TileEntityArmorStand)
		{ 
			((TileEntityArmorStand) tileEntity).onDestroy(null);
		}
		dropEntireInventory(world, pos);
		super.breakBlock(world, pos, state);
	}
	
	public void dropEntireInventory(World world, BlockPos pos)
	{
		TileEntity tileEntity = world.getTileEntity(pos);
		
		if (tileEntity != null)
		{
			if (tileEntity instanceof IInventory)
			{
				IInventory inventory = (IInventory) tileEntity;
				
				for (int i = 0; i < inventory.getSizeInventory(); ++i)
				{
					ItemStack slot = inventory.getStackInSlot(i);
					
					if (slot != null)
					{
						Random random = new Random();
						float f1 = random.nextFloat() * 0.8F + 0.1F;
						float f2 = random.nextFloat() * 0.8F + 0.1F;
						float f3 = random.nextFloat() * 0.8F + 0.1F;
						
						while (slot.getCount() > 0)
						{
							int f4 = random.nextInt(21) + 10;
							
							if (f4 > slot.getCount())
							{
								f4 = slot.getCount();
							}
							
							slot.shrink(f4);
							EntityItem ent = new EntityItem(world, pos.getX() + f1, pos.getY() + f2, pos.getZ() + f3,
									new ItemStack(slot.getItem(), f4, slot.getItemDamage()));
							
							if (slot.hasTagCompound())
							{
								ent.getItem().setTagCompound((NBTTagCompound) slot.getTagCompound().copy());
							}
							
							float var13 = 0.05F;
							ent.motionX = (float) random.nextGaussian() * var13;
							ent.motionY = (float) random.nextGaussian() * var13 + 0.2F;
							ent.motionZ = (float) random.nextGaussian() * var13;
							world.spawnEntity(ent);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Called when the block is placed in the world.
	 */
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack)
	{
		
		int angle = MathHelper.floor(placer.rotationYaw * 4.0F / 360.0F + 0.5D) & 3;
		
		if (!this.canPlaceAt(world, pos, placer))
		{
			if (placer instanceof EntityPlayer)
			{
				if (!world.isRemote)
				{
					ChatUtils.SendChatMessageOnClient(((EntityPlayer) placer), new LocalizedChatComponent(new LocalizedString("gui.warning.noroom", TextFormatting.RED)));
				}
				
				world.setBlockToAir(pos);
				((EntityPlayer) placer).inventory.addItemStackToInventory(new ItemStack(BlockContainerMod.BlockArmorStand, 1));
				return;
			}
		} else
		{
			world.setBlockState(pos, getStateFromMeta(angle > 3 ? 0 : angle), 3);
		}
		
		TileEntity var8 = world.getTileEntity(pos);
		
		if (var8 instanceof IMultiBlock)
		{
			((IMultiBlock) var8).onCreate(world, pos);
		}
		
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		
		if (this.isUsableWrench(player, player.getHeldItem(hand), pos))
		{
			this.damageWrench(player, player.getHeldItem(hand), pos);
			if (this.onUseWrench(world, pos, player, hand, player.getHeldItem(hand), facing, hitX, hitY, hitZ))
			{
				return true;
			}
		}
		return this.onMachineActivated(world, pos.getX(), pos.getY(), pos.getZ(), player);
	}
	
	protected boolean useWrench(World worldIn, BlockPos pos, EntityPlayer playerIn, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		/**
		 * Check if the player is holding a wrench or an electric item. If so,
		 * call the wrench event.
		 */
		if (this.isUsableWrench(playerIn, heldItem, pos))
		{
			this.damageWrench(playerIn, heldItem, pos);
			
			if (this.onUseWrench(worldIn, pos, playerIn, hand, heldItem, side, hitX, hitY, hitZ))
			{
				playerIn.swingArm(hand);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Called when a player uses a wrench on the machine
	 *
	 * @return True if some happens
	 */
	public boolean onUseWrench(World world, BlockPos pos, EntityPlayer entityPlayer, EnumHand hand, ItemStack heldItem, EnumFacing side, float hitX, float hitY, float hitZ)
	{
		IBlockState state = world.getBlockState(pos);
		int metadata = state.getBlock().getMetaFromState(world.getBlockState(pos));
		int change = side.rotateY().getHorizontalIndex();
		
		world.setBlockState(pos, getStateFromMeta(metadata - (metadata % 4) + change), 3);
		
		TileEntity te = world.getTileEntity(pos);
		if (te instanceof TileBaseUniversalElectrical)
		{
			((TileBaseUniversalElectrical) te).updateFacing();
		}
		return true;
	}
	
	/**
	 * A function that denotes if an itemStack is a wrench that can be used.
	 * Override this for more wrench compatibility. Compatible with Buildcraft
	 * and IC2 wrench API via reflection.
	 *
	 * @return True if it is a wrench.
	 */
	public boolean isUsableWrench(EntityPlayer entityPlayer, ItemStack itemStack, BlockPos pos)
	{
		if (entityPlayer != null && itemStack != null)
		{
			Item item = itemStack.getItem();
			if (item == GCItems.wrench) return true;
			
			Class<? extends Item> wrenchClass = item.getClass();
			
			/**
			 * Buildcraft
			 */
			try
			{
				Method methodCanWrench = wrenchClass.getMethod("canWrench", EntityPlayer.class, BlockPos.class);
				return (Boolean) methodCanWrench.invoke(item, entityPlayer, pos);
			} catch (NoClassDefFoundError e)
			{
			} catch (Exception e)
			{
			}
			
			if (CompatibilityManager.isIc2Loaded())
			{
				/**
				 * Industrialcraft
				 */
				if (wrenchClass == CompatibilityManager.classIC2wrench || wrenchClass == CompatibilityManager.classIC2wrenchElectric)
				{
					return itemStack.getItemDamage() < itemStack.getMaxDamage();
				}
			}
		}
		
		return false;
	}
	
	/**
	 * This function damages a wrench. Works with Buildcraft and Industrialcraft
	 * wrenches.
	 *
	 * @return True if damage was successfull.
	 */
	public boolean damageWrench(EntityPlayer entityPlayer, ItemStack itemStack, BlockPos pos)
	{
		if (this.isUsableWrench(entityPlayer, itemStack, pos))
		{
			Class<? extends Item> wrenchClass = itemStack.getItem().getClass();
			
			/**
			 * Buildcraft
			 */
			try
			{
				Method methodWrenchUsed = wrenchClass.getMethod("wrenchUsed", EntityPlayer.class, BlockPos.class);
				methodWrenchUsed.invoke(itemStack.getItem(), entityPlayer, pos);
				return true;
			} catch (Exception e)
			{
			}
			
			/**
			 * Industrialcraft
			 */
			try
			{
				if (wrenchClass == CompatibilityManager.classIC2wrench || wrenchClass == CompatibilityManager.classIC2wrenchElectric)
				{
					Method methodWrenchDamage = wrenchClass.getMethod("damage", ItemStack.class, Integer.TYPE, EntityPlayer.class);
					methodWrenchDamage.invoke(itemStack.getItem(), itemStack, 1, entityPlayer);
					return true;
				}
			} catch (Exception e)
			{
			}
		}
		
		return false;
	}
	
	/**
	 * Called when the block is right clicked by the player
	 */
	public boolean onMachineActivated(World world, int x, int y, int z, EntityPlayer par5EntityPlayer)
	{
		par5EntityPlayer.openGui(OrbitalTools.instance, GuiHandler.ARMORSTANDGUI, world, x, y, z);
		return true;
	} 
	
	@Override
	public boolean isOpaqueCube(IBlockState state)
	{
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
	{
		return new TileEntityArmorStand();
	}
	
	private boolean canPlaceAt(World world, BlockPos pos, EntityLivingBase player)
	{
		for (int y = 0; y < 2; y++)
		{
			Block blockAt = world.getBlockState(pos.add(0, y, 0)).getBlock();
			pos.add(0, -y, 0);
			
			if (y == 0)
			{
				continue;
			}
			if (!blockAt.getBlockState().getBaseState().getMaterial().isReplaceable())
			{
				return false;
			}
		}
		return true;
	}
	
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta));
	}
	
	/**
	 * Convert the BlockState into the correct metadata value
	 */
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumFacing) state.getValue(FACING)).getHorizontalIndex();
	}
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState state)
	{
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	protected BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, new IProperty[] { FACING });
	}
	
}

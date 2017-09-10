
package net.orbitallabs.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.OrbitalTools;
import net.orbitallabs.blocks.BlockContainerMod;
import net.orbitallabs.blocks.BlockMod;
import net.orbitallabs.gui.GuiHandler;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.OpenGuiOnServerPacket;
import net.orbitallabs.utils.ChatUtils;

public class ItemBuilder extends ItemMod {
	
	public ItemBuilder(String uln)
	{
		super(uln);
		this.setCreativeTab(CreativeTabs.TOOLS);
		this.setShowDesrc(true);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
	{
		if (hand != EnumHand.MAIN_HAND) return super.onItemRightClick(world, player, hand);
		ItemStack stack = player.getHeldItem(hand);
		if (world.isRemote)
		{
			RayTraceResult result = this.rayTrace(world, player, false);
			if (result != null)
			{
				BlockPos pos = result.getBlockPos();
				if (world.getBlockState(pos).getBlock() == BlockMod.BuildpPoint)
				{
					player.motionX = 0;
					player.motionY = 0;
					player.motionZ = 0;
					player.openGui(OrbitalTools.instance, GuiHandler.BUILDERGUI, world, pos.getX(), pos.getY(), pos.getZ());
					PacketHandler.sendToServer(new OpenGuiOnServerPacket(GuiHandler.BUILDERGUI, pos.getX(), pos.getY(), pos.getZ()));
				} else if (world.getBlockState(pos).getBlock() == BlockContainerMod.BlockRemoveInfo)
				{
					player.motionX = 0;
					player.motionY = 0;
					player.motionZ = 0;
					//PacketHandler.sendToServer(new OpenGuiPacket(x, y, z));
					player.openGui(OrbitalTools.instance, GuiHandler.REMOVERGUI, world, pos.getX(), pos.getY(), pos.getZ());
					PacketHandler.sendToServer(new OpenGuiOnServerPacket(GuiHandler.REMOVERGUI, pos.getX(), pos.getY(), pos.getZ()));
				}
			}
		}
		
		return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
	}
	
	public BlockPos getPos(int[] pos)
	{
		if (pos != null && pos.length > 2) return new BlockPos(pos[0], pos[1], pos[2]);
		return BlockPos.ORIGIN;
	}
	
	public int[] getPos(BlockPos pos)
	{
		if (pos == null) return new int[] { 0, 0, 0 };
		return new int[] { pos.getX(), pos.getY(), pos.getZ() };
	}
	
	public int[] getPos(TileEntity entity)
	{
		if (entity == null) return new int[] { 0, 0, 0 };
		
		return new int[] { entity.getPos().getX(), entity.getPos().getY(), entity.getPos().getZ() };
	}
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		if (!world.isRemote && player.isSneaking())
		{
			if (world.getBlockState(pos).getBlock() != null && world.getTileEntity(pos) != null && world.getTileEntity(pos) instanceof IInventory)
			{
				ItemStack is = player.getHeldItem(hand);
				TileEntity te = world.getTileEntity(pos);
				if (is.hasTagCompound())
				{
					NBTTagCompound tag = is.getTagCompound();
					boolean delete = false;
					if (tag.hasKey("chests"))
					{
						NBTTagList list = tag.getTagList("chests", 11);
						if (list.tagCount() > 0)
						{
							if (te instanceof TileEntityChest)
							{
								TileEntityChest te2 = (TileEntityChest) te;
								if (te2.adjacentChestXNeg != null || te2.adjacentChestXPos != null || te2.adjacentChestZNeg != null || te2.adjacentChestZPos != null)
								{
									TileEntityChest ajte = te2.adjacentChestXNeg != null ? te2.adjacentChestXNeg
											: te2.adjacentChestXPos != null ? te2.adjacentChestXPos
													: te2.adjacentChestZNeg != null ? te2.adjacentChestZNeg : te2.adjacentChestZPos != null ? te2.adjacentChestZPos : null;
									
									BlockPos spos = ajte.getPos();
									for (int i2 = 0; i2 < list.tagCount(); i2++)
									{
										BlockPos strpos = getPos(list.getIntArrayAt(i2));
										if (strpos.equals(spos))
										{
											list.removeTag(i2);
											break;
										}
									}
									for (int i = 0; i < list.tagCount(); i++)
									{
										BlockPos strpos = getPos(list.getIntArrayAt(i));
										if (strpos.equals(pos))
										{
											
											list.removeTag(i);
											player.sendMessage(new TextComponentString("deleted chest with pos: " + pos.toString()));
											delete = true;
											break;
										}
									}
									
								} else
								{
									for (int i = 0; i < list.tagCount(); i++)
									{
										BlockPos strpos = getPos(list.getIntArrayAt(i));
										if (strpos.equals(pos))
										{
											
											list.removeTag(i);
											player.sendMessage(new TextComponentString("deleted chest with pos: " + pos.toString()));
											delete = true;
											break;
										}
									}
								}
							} else
							{
								for (int i = 0; i < list.tagCount(); i++)
								{
									BlockPos strpos = getPos(list.getIntArrayAt(i));
									if (strpos.equals(pos))
									{
										
										list.removeTag(i);
										player.sendMessage(new TextComponentString("deleted chest with pos: " + pos.toString()));
										delete = true;
										break;
									}
								}
							}
							if (!delete && list.tagCount() < 4)
							{
								if (te instanceof TileEntityChest)
								{
									TileEntityChest te2 = (TileEntityChest) te;
									if ((te2.adjacentChestXNeg != null || te2.adjacentChestXPos != null || te2.adjacentChestZNeg != null || te2.adjacentChestZPos != null)
											&& list.tagCount() < 3)
									{
										TileEntityChest ajte = te2.adjacentChestXNeg != null ? te2.adjacentChestXNeg
												: te2.adjacentChestXPos != null ? te2.adjacentChestXPos
														: te2.adjacentChestZNeg != null ? te2.adjacentChestZNeg : te2.adjacentChestZPos != null ? te2.adjacentChestZPos : null;
										list.appendTag(new NBTTagIntArray(getPos(te2)));
										list.appendTag(new NBTTagIntArray(getPos(ajte)));
										player.sendMessage(new TextComponentString("added new chest with pos: " + pos.toString()));
									} else if (list.tagCount() < 4)
									{
										list.appendTag(new NBTTagIntArray(getPos(pos)));
										player.sendMessage(new TextComponentString("added new chest with pos: " + pos.toString()));
									}
								} else
								{
									list.appendTag(new NBTTagIntArray(getPos(pos)));
									player.sendMessage(new TextComponentString("added new chest with pos: " + pos.toString()));
								}
							} else if (!delete)
							{
								player.sendMessage(ChatUtils.modifyColor(new TextComponentString("You're reached max chest marks count!"), TextFormatting.RED));
							}
							tag.setTag("chests", list);
							
						} else
						{
							if (te instanceof TileEntityChest)
							{
								TileEntityChest te2 = (TileEntityChest) te;
								if (te2.adjacentChestXNeg != null || te2.adjacentChestXPos != null || te2.adjacentChestZNeg != null || te2.adjacentChestZPos != null)
								{
									TileEntityChest ajte = te2.adjacentChestXNeg != null ? te2.adjacentChestXNeg
											: te2.adjacentChestXPos != null ? te2.adjacentChestXPos
													: te2.adjacentChestZNeg != null ? te2.adjacentChestZNeg : te2.adjacentChestZPos != null ? te2.adjacentChestZPos : null;
									list.appendTag(new NBTTagIntArray(getPos(te2)));
									list.appendTag(new NBTTagIntArray(getPos(ajte)));
									player.sendMessage(new TextComponentString("added new chest with pos: " + pos.toString()));
								} else
								{
									list.appendTag(new NBTTagIntArray(getPos(pos)));
									player.sendMessage(new TextComponentString("added new chest with pos: " + pos.toString()));
								}
							} else
							{
								list.appendTag(new NBTTagIntArray(getPos(pos)));
								player.sendMessage(new TextComponentString("added new chest with pos: " + pos.toString()));
							}
							tag.setTag("chests", list);
						}
					} else
					{
						NBTTagList list = new NBTTagList();
						
						if (te instanceof TileEntityChest)
						{
							TileEntityChest te2 = (TileEntityChest) te;
							if (te2.adjacentChestXNeg != null || te2.adjacentChestXPos != null || te2.adjacentChestZNeg != null || te2.adjacentChestZPos != null)
							{
								TileEntityChest ajte = te2.adjacentChestXNeg != null ? te2.adjacentChestXNeg
										: te2.adjacentChestXPos != null ? te2.adjacentChestXPos
												: te2.adjacentChestZNeg != null ? te2.adjacentChestZNeg : te2.adjacentChestZPos != null ? te2.adjacentChestZPos : null;
								list.appendTag(new NBTTagIntArray(getPos(te2)));
								list.appendTag(new NBTTagIntArray(getPos(ajte)));
								player.sendMessage(new TextComponentString("added new chest with pos: " + pos.toString()));
							} else
							{
								list.appendTag(new NBTTagIntArray(getPos(pos)));
								player.sendMessage(new TextComponentString("added new chest with pos: " + pos.toString()));
							}
						} else
						{
							list.appendTag(new NBTTagIntArray(getPos(pos)));
							player.sendMessage(new TextComponentString("added new chest with pos: " + pos.toString()));
						}
						tag.setTag("chests", list);
					}
					is.setTagCompound(tag);
				} else
				{
					is.setTagCompound(new NBTTagCompound());
					NBTTagList list = new NBTTagList();
					if (te instanceof TileEntityChest)
					{
						TileEntityChest te2 = (TileEntityChest) te;
						if (te2.adjacentChestXNeg != null || te2.adjacentChestXPos != null || te2.adjacentChestZNeg != null || te2.adjacentChestZPos != null)
						{
							TileEntityChest ajte = te2.adjacentChestXNeg != null ? te2.adjacentChestXNeg
									: te2.adjacentChestXPos != null ? te2.adjacentChestXPos
											: te2.adjacentChestZNeg != null ? te2.adjacentChestZNeg : te2.adjacentChestZPos != null ? te2.adjacentChestZPos : null;
							list.appendTag(new NBTTagIntArray(getPos(te2)));
							list.appendTag(new NBTTagIntArray(getPos(ajte)));
							player.sendMessage(new TextComponentString("added new chest with pos: " + pos.toString()));
						} else
						{
							list.appendTag(new NBTTagIntArray(getPos(pos)));
							player.sendMessage(new TextComponentString("added new chest with pos: " + pos.toString()));
						}
					} else
					{
						list.appendTag(new NBTTagIntArray(getPos(pos)));
						player.sendMessage(new TextComponentString("added new chest with pos: " + pos.toString()));
					}
					is.getTagCompound().setTag("chests", list);
				}
			}
		}
		
		return EnumActionResult.PASS;
	}
	
}

package net.orbitallabs.structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orbitallabs.blocks.BlockBuildPoint;
import net.orbitallabs.blocks.BlockBuildPoint.EnumBlockPointStates;
import net.orbitallabs.blocks.BlockContainerMod;
import net.orbitallabs.blocks.BlockMod;
import net.orbitallabs.tiles.TileEntityInfo;
import net.orbitallabs.tiles.TileEntityRemoveInfo;
import net.orbitallabs.utils.FacingUtils;
import net.orbitallabs.utils.ItemUtil;
import net.orbitallabs.utils.MatrixHelper;
import net.orbitallabs.utils.OreDictItemStack;

public class BuildHandler {
	
	public static StructureStub str1 = new StructureStub(true);
	public static StructureHall str2 = new StructureHall(false);
	public static StructureCornerHall str3 = new StructureCornerHall(false);
	public static StructureCrossroad str4 = new StructureCrossroad(false);
	public static StructureHallWAirlock str5 = new StructureHallWAirlock(false);
	public static StructureWindow str6 = new StructureWindow(false);
	public static StructureCupola str7 = new StructureCupola(false);
	public static StructureDockingPort str8 = new StructureDockingPort(false);
	public static StructureSolarPanel str9 = new StructureSolarPanel(false);
	public static StructureThall str10 = new StructureThall(false);
	public static StructureBigHall str11 = new StructureBigHall(false);
	public static StructureGreenHouse str12 = new StructureGreenHouse();
	public static StructurePirs str13 = new StructurePirs();
	
	private static boolean haveContainerItem(NonNullList<ItemStack> found, OreDictItemStack is)
	{
		for (int i = 0; i < found.size(); i++)
		{
			OreDictItemStack curr = new OreDictItemStack(found.get(i));
			if (is != null && curr != null)
			{
				if (is.isStackEqual(curr, false))
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean CheckItems(World world, String FuncName, NBTTagList items, EntityPlayer player, int rot)
	{
		if (player.capabilities.isCreativeMode)
		{
			return true;
		}
		
		Structure CurStr = Structure.FindStructure(FuncName);
		if (CurStr instanceof StructureRotatable)
		{
			((StructureRotatable) CurStr).setRotation(rot);
		}
		NonNullList<OreDictItemStack> required = CurStr.getRequiredItems();
		
		for (int i = 0; i < required.size(); i++)
		{
			OreDictItemStack curr = required.get(i);
			for (int j = 0; j < required.size(); j += 0)
			{
				boolean removed = false;
				if (j != i)
				{
					OreDictItemStack last = required.get(j);
					if (curr.isStackEqual(last, true))
					{
						curr.example.grow(last.example.getCount());
						required.remove(j);
						removed = true;
					}
					
				}
				if (!removed)
				{
					j++;
				}
			}
		}
		
		NonNullList<ItemStack> found = NonNullList.create();
		
		if (items.tagCount() > 0)
		{
			for (int i = 0; i < items.tagCount(); i++)
			{
				int[] pos = items.getIntArrayAt(i);
				if (world != null)
				{
					TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
					if (te instanceof IInventory)
					{
						IInventory inv = (IInventory) te;
						if (inv.getSizeInventory() > 0)
						{
							for (int j = 0; j < inv.getSizeInventory(); j++)
							{
								if (!inv.getStackInSlot(j).isEmpty())
								{
									found.add(inv.getStackInSlot(j).copy());
								}
							}
						}
					}
				}
			}
			
		}
		if (player.inventory.getSizeInventory() > 0)
		{
			for (int j = 0; j < player.inventory.getSizeInventory(); j++)
			{
				if (!player.inventory.getStackInSlot(j).isEmpty())
				{
					found.add(player.inventory.getStackInSlot(j).copy());
				}
			}
		}
		for (int i = 0; i < found.size(); i++)
		{
			ItemStack curr = found.get(i);
			for (int j = 0; j < found.size(); j += 0)
			{
				boolean removed = false;
				if (j != i)
				{
					ItemStack last = found.get(j);
					if (!last.isEmpty() && !curr.isEmpty())
					{
						if (ItemUtil.AreItemStackEqual(curr, last, true))
						{
							curr.grow(last.getCount());
							found.remove(j);
							removed = true;
						}
					}
				}
				if (!removed)
				{
					j++;
				}
			}
		}
		
		//	boolean skipped = false;
		for (int i = 0; i < required.size(); i++)
		{
			if (!haveContainerItem(found, required.get(i)))
			{
				return false;
			}
		}
		
		for (int k = 0; k < required.size(); k++)
		{
			OreDictItemStack wantedItem = required.get(k);
			boolean Found = false;
			for (int i = 0; i < items.tagCount(); i++)
			{
				int[] pos = items.getIntArrayAt(i);
				if (world != null)
				{
					TileEntity te = world.getTileEntity(new BlockPos(pos[0], pos[1], pos[2]));
					if (te instanceof IInventory)
					{
						IInventory inv = (IInventory) te;
						if (inv.getSizeInventory() > 0)
						{
							for (int j = 0; j < inv.getSizeInventory(); j++)
							{
								OreDictItemStack curr = new OreDictItemStack(inv.getStackInSlot(j));
								if (!wantedItem.isEmpty() && !curr.isEmpty())
								{
									if (wantedItem.isStackEqual(curr, true))
									{
										if (curr.example.getCount() >= wantedItem.example.getCount())
										{
											Found = true;
											inv.decrStackSize(j, wantedItem.example.getCount());
											if (!inv.getStackInSlot(j).isEmpty() && inv.getStackInSlot(j).getCount() == 0)
											{
												inv.setInventorySlotContents(j, ItemStack.EMPTY);
											}
											break;
										} else if (curr.example.getCount() > 0)
										{
											wantedItem.example.shrink(curr.example.getCount());
											inv.setInventorySlotContents(j, ItemStack.EMPTY);
										}
									}
								}
							}
							if (Found)
							{
								break;
							}
						}
					}
				}
			}
			if (player.inventory.getSizeInventory() > 0)
			{
				for (int j = 0; j < player.inventory.getSizeInventory(); j++)
				{
					OreDictItemStack curr = new OreDictItemStack(player.inventory.getStackInSlot(j));
					if (!wantedItem.isEmpty() && !curr.isEmpty())
					{
						if (wantedItem.isStackEqual(curr, true))
						{
							if (curr.example.getCount() >= wantedItem.example.getCount())
							{
								Found = true;
								player.inventory.decrStackSize(j, wantedItem.example.getCount());
								if (!player.inventory.getStackInSlot(j).isEmpty() && player.inventory.getStackInSlot(j).getCount() == 0)
								{
									player.inventory.setInventorySlotContents(j, ItemStack.EMPTY);
								}
								break;
							} else if (curr.example.getCount() > 0)
							{
								wantedItem.example.shrink(curr.example.getCount());
								player.inventory.setInventorySlotContents(j, ItemStack.EMPTY);
							}
						}
					}
				}
			}
			
			if (!Found)
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static boolean HandleBuild(World world, EnumFacing dir, String FuncName, BlockPos bpos, int rot, EntityPlayerMP player)
	{
		
		boolean debugBB = false;
		
		switch (FuncName) {
		case "stub":
			
			if (str1.Check(world, dir, bpos, -1))
			{
				str1.Build(world, dir, bpos);
				return true;
			}
			return false;
		case "hall":
			if (str2.Check(world, dir, bpos, -1))
			{
				boolean collides = false;
				
				List<AxisAlignedBB> list = str2.getBoundingBox(dir, bpos);
				
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i) != null && world.collidesWithAnyBlock(list.get(i)))
					{
						collides = true;
						break;
					}
				}
				
				//	world.setBlockState(new BlockPos(list.get(0).maxX, list.get(0).maxY, list.get(0).maxZ),
				//			Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW));
				//	world.setBlockState(new BlockPos(list.get(0).minX, list.get(0).minY, list.get(0).minZ),
				//			Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.LIME));
				
				if (!collides)
				{
					
					BlockPos IPoint = MatrixHelper.findMatrixPoint(world, dir, bpos);
					List<BlockPos> Matrix;
					if (IPoint != null)
					{
						Matrix = new ArrayList();
						Matrix.clear();
						Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
						// if (Matrix == null) return false;
						if (Matrix != null) if (!isAvailable(dir, Matrix, IPoint)) return false;
					} else Matrix = null;
					
					// no if's for each dir!
					BlockPos Spos = new BlockPos(bpos);
					Spos = FacingUtils.IncreaseByDir(dir, Spos, 9);
					BlockPos Ppos;
					if (IPoint != null)
					{
						Ppos = new BlockPos(IPoint);
						Ppos = FacingUtils.IncreaseByDir(dir, Ppos, 18);
					} else
					{
						Ppos = new BlockPos(0, 0, 0);
					}
					
					TileEntityInfo te = null;
					if (IPoint != null)
					{
						te = (TileEntityInfo) world.getTileEntity(IPoint);
						
						if (te != null)
						{
							Structure Nstr = Structure.FindStructure(str2.getUnlocalizedName());
							Nstr.Configure(bpos, rot, dir);
							te.ChildObjects.add(Nstr);
						}
					}
					str2.Build(world, dir, bpos);
					
					if (debugBB)
					{
						world.setBlockState(new BlockPos(list.get(0).maxX, list.get(0).maxY, list.get(0).maxZ),
								Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW));
						world.setBlockState(new BlockPos(list.get(0).minX, list.get(0).minY, list.get(0).minZ),
								Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.LIME));
					}
					boolean Conect = false;
					if (Matrix != null)
					{
						if (MatrixHelper.FindPointInMatrix(Matrix, Ppos) != null)
						{
							te = (TileEntityInfo) world.getTileEntity(Ppos);
							if (te != null)
							{
								if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
										&& te.Object.placementDir.getOpposite() == dir)
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("corner") && str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == dir)
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
								{
									EnumFacing[] dirs = str4.getDirs(te.Object.placementDir);
									for (int i = 0; i < dirs.length; i++)
									{
										EnumFacing STdir = dirs[i];
										if (STdir.getOpposite() == dir)
										{
											Conect = true;
											break;
										}
									}
								} else if (te.Object.getUnlocalizedName().equals("thall"))
								{
									str10.setRotation(te.Object.placementRotation);
									EnumFacing[] dirs = str10.getDirs(te.Object.placementDir);
									for (int i = 0; i < 2; i++)
									{
										EnumFacing STdir = dirs[i];
										if (STdir.getOpposite() == dir) Conect = true;
									}
								}
								if (Conect)
								{
									BlockPos Ipos = FacingUtils.IncreaseByDir(dir, IPoint, 9);
									TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos);
									if (Ite != null)
									{
										
										Ite.Object.connections.add(te.Object);
										te.Object.connections.add(Ite.Object);
									}
								}
							}
						}
					}
					if (!Conect)
					{
						str1.Build(world, dir, Spos);
						str2.ClearWay(world, dir, bpos);
					} else
					{
						// no if's for each dir!
						Spos = FacingUtils.IncreaseByDir(dir.getOpposite(), Spos, 2);
						str3.ClearWay(world, dir, Spos);
						str2.ClearWay(world, dir, bpos);
					}
					return true;
				}
			}
			return false;
		case "corner":
			str3.setRotation(rot);
			if (str3.Check(world, dir, bpos, -1))
			{
				boolean collides = false;
				
				List<AxisAlignedBB> list = str3.getBoundingBox(dir, bpos);
				
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i) != null && world.collidesWithAnyBlock(list.get(i)))
					{
						collides = true;
						break;
					}
				}
				
				if (!collides)
				{
					
					BlockPos IPoint = MatrixHelper.findMatrixPoint(world, dir, bpos);
					List<BlockPos> Matrix;
					if (IPoint != null)
					{
						Matrix = new ArrayList();
						Matrix.clear();
						Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
						// if (Matrix == null) return false;
						if (Matrix != null) if (!isAvailable(dir, Matrix, IPoint)) return false;
					} else Matrix = null;
					TileEntityInfo te = null;
					if (IPoint != null)
					{
						te = (TileEntityInfo) world.getTileEntity(IPoint);
						
						if (te != null)
						{
							Structure Nstr = Structure.FindStructure(str3.getUnlocalizedName());
							Nstr.Configure(bpos, rot, dir);
							te.ChildObjects.add(Nstr);
						}
					}
					str3.Build(world, dir, bpos);
					
					if (debugBB)
					{
						world.setBlockState(new BlockPos(list.get(0).maxX, list.get(0).maxY, list.get(0).maxZ),
								Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW));
						world.setBlockState(new BlockPos(list.get(0).minX, list.get(0).minY, list.get(0).minZ),
								Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.LIME));
					}
					
					EnumFacing Ndir = str3.onTurn(dir, rot);
					
					// no if's for each dir!
					BlockPos Spos = new BlockPos(bpos);
					Spos = FacingUtils.IncreaseByDir(dir, Spos, 4);
					Spos = FacingUtils.IncreaseByDir(Ndir, Spos, 5);
					BlockPos Ppos;
					if (IPoint != null)
					{
						Ppos = new BlockPos(IPoint);
						Ppos = FacingUtils.IncreaseByDir(dir, Ppos, 9);
						Ppos = FacingUtils.IncreaseByDir(Ndir, Ppos, 9);
					} else
					{
						Ppos = new BlockPos(0, 0, 0);
					}
					boolean Conect = false;
					if (Matrix != null)
					{
						if (MatrixHelper.FindPointInMatrix(Matrix, Ppos) != null)
						{
							te = (TileEntityInfo) world.getTileEntity(Ppos);
							if (te != null)
							{
								if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
										&& te.Object.placementDir.getOpposite() == Ndir)
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("corner")
										&& str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == Ndir)
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
								{
									EnumFacing[] dirs = str4.getDirs(te.Object.placementDir);
									for (int i = 0; i < dirs.length; i++)
									{
										EnumFacing STdir = dirs[i];
										if (STdir.getOpposite() == Ndir) Conect = true;
									}
								} else if (te.Object.getUnlocalizedName().equals("thall"))
								{
									str10.setRotation(te.Object.placementRotation);
									EnumFacing[] dirs = str10.getDirs(te.Object.placementDir);
									for (int i = 0; i < 2; i++)
									{
										EnumFacing STdir = dirs[i];
										if (STdir.getOpposite() == Ndir) Conect = true;
									}
								}
								if (Conect)
								{
									BlockPos Ipos = FacingUtils.IncreaseByDir(dir, IPoint, 9);
									TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos);
									if (Ite != null)
									{
										
										Ite.Object.connections.add(te.Object);
										te.Object.connections.add(Ite.Object);
									}
								}
							}
						}
						if (!Conect)
						{
							str1.Build(world, Ndir, Spos);
							str2.ClearWay(world, dir, bpos);
						} else
						{
							// no if's for each dir!
							Spos = FacingUtils.IncreaseByDir(Ndir.getOpposite(), Spos, 2);
							str3.ClearWay(world, Ndir, Spos);
							str2.ClearWay(world, dir, bpos);
							// str3.ClearWay(world, Ndir,
							// Spos[0], Spos[1],
							// Spos[2]);
						}
					} else
					{
						str1.Build(world, Ndir, Spos);
						str2.ClearWay(world, dir, bpos);
					}
					return true;
				}
			}
			return false;
		case "crossroad":
			if (str4.Check(world, dir, bpos, -1))
			{
				boolean collides = false;
				
				List<AxisAlignedBB> list = str4.getBoundingBox(dir, bpos);
				
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i) != null && world.collidesWithAnyBlock(list.get(i)))
					{
						collides = true;
						break;
					}
				}
				
				if (!collides)
				{
					BlockPos IPoint = MatrixHelper.findMatrixPoint(world, dir, bpos);
					List<BlockPos> Matrix;
					if (IPoint != null)
					{
						Matrix = new ArrayList();
						Matrix.clear();
						Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
						
						if (Matrix != null) if (!isAvailable(dir, Matrix, IPoint)) return false;
						
					} else Matrix = null;
					TileEntityInfo te = null;
					if (IPoint != null)
					{
						te = (TileEntityInfo) world.getTileEntity(IPoint);
						
						if (te != null)
						{
							Structure Nstr = Structure.FindStructure(str4.getUnlocalizedName());
							Nstr.Configure(bpos, rot, dir);
							te.ChildObjects.add(Nstr);
						}
					}
					str4.Build(world, dir, bpos);
					
					if (debugBB)
					{
						world.setBlockState(new BlockPos(list.get(0).maxX, list.get(0).maxY, list.get(0).maxZ),
								Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW));
						world.setBlockState(new BlockPos(list.get(0).minX, list.get(0).minY, list.get(0).minZ),
								Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.LIME));
					}
					
					if (dir != EnumFacing.UP)
					{
						EnumFacing[] dirs = str4.getDirs(dir);
						for (int i = 0; i < 3; i++)
						{
							EnumFacing Ndir = dirs[i];
							BlockPos pos = str4.ChangePosForDir(dir, Ndir, bpos);
							
							// no if's for each dir!
							BlockPos Spos;
							if (IPoint != null)
							{
								Spos = new BlockPos(IPoint);
								Spos = FacingUtils.IncreaseByDir(dir, Spos, 9);
								Spos = FacingUtils.IncreaseByDir(Ndir, Spos, 9);
							} else
							{
								Spos = new BlockPos(0, 0, 0);
							}
							
							boolean Conect = false;
							if (Matrix != null)
							{
								if (MatrixHelper.FindPointInMatrix(Matrix, Spos) != null)
								{
									te = (TileEntityInfo) world.getTileEntity(Spos);
									if (te != null)
									{
										if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
												&& te.Object.placementDir.getOpposite() == Ndir)
										{
											Conect = true;
										} else if (te.Object.getUnlocalizedName().equals("corner")
												&& str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == Ndir)
										{
											Conect = true;
										} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
										{
											EnumFacing[] dirs1 = str4.getDirs(te.Object.placementDir);
											for (int i2 = 0; i2 < dirs.length; i2++)
											{
												EnumFacing STdir = dirs1[i2];
												if (STdir.getOpposite() == Ndir) Conect = true;
											}
										} else if (te.Object.getUnlocalizedName().equals("thall"))
										{
											str10.setRotation(te.Object.placementRotation);
											EnumFacing[] dirs1 = str10.getDirs(te.Object.placementDir);
											for (int i2 = 0; i2 < 2; i2++)
											{
												EnumFacing STdir = dirs1[i2];
												if (STdir.getOpposite() == Ndir) Conect = true;
											}
										}
										if (Conect)
										{
											BlockPos Ipos = FacingUtils.IncreaseByDir(dir, IPoint, 9);
											TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos);
											if (Ite != null)
											{
												
												Ite.Object.connections.add(te.Object);
												te.Object.connections.add(Ite.Object);
											}
										}
									}
								}
							}
							if (!Conect)
							{
								str1.Build(world, Ndir, pos);
							} else
							{
								// no if's for each dir!
								pos = FacingUtils.IncreaseByDir(Ndir.getOpposite(), pos, 2);
								str3.ClearWay(world, Ndir, pos);
							}
							
						}
						str2.ClearWay(world, dir, bpos);
					}
					return true;
				}
			}
			return false;
		case "hallairlock":
			if (str5.Check(world, dir, bpos, -1))
			{
				boolean collides = false;
				
				List<AxisAlignedBB> list = str5.getBoundingBox(dir, bpos);
				
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i) != null && world.collidesWithAnyBlock(list.get(i)))
					{
						collides = true;
						break;
					}
				}
				
				if (!collides)
				{
					BlockPos IPoint = MatrixHelper.findMatrixPoint(world, dir, bpos);
					List<BlockPos> Matrix;
					if (IPoint != null)
					{
						Matrix = new ArrayList();
						Matrix.clear();
						Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
						// if (Matrix == null) return false;
						if (Matrix != null) if (!isAvailable(dir, Matrix, IPoint)) return false;
					} else Matrix = null;
					TileEntityInfo te = null;
					if (IPoint != null)
					{
						te = (TileEntityInfo) world.getTileEntity(IPoint);
						if (te != null)
						{
							Structure Nstr = Structure.FindStructure(str5.getUnlocalizedName());
							Nstr.Configure(bpos, rot, dir);
							te.ChildObjects.add(Nstr);
						}
					}
					str5.setOwner(player.getGameProfile().getName());
					str5.Build(world, dir, bpos);
					
					if (debugBB)
					{
						world.setBlockState(new BlockPos(list.get(0).maxX, list.get(0).maxY, list.get(0).maxZ),
								Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW));
						world.setBlockState(new BlockPos(list.get(0).minX, list.get(0).minY, list.get(0).minZ),
								Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.LIME));
					}
					
					// no if's for each dir!
					BlockPos Spos = new BlockPos(bpos);
					Spos = FacingUtils.IncreaseByDir(dir, Spos, 9);
					BlockPos Ppos;
					if (IPoint != null)
					{
						Ppos = FacingUtils.IncreaseByDir(dir, IPoint, 18);
					} else
					{
						Ppos = new BlockPos(0, 0, 0);
					}
					
					boolean Conect = false;
					if (Matrix != null)
					{
						if (MatrixHelper.FindPointInMatrix(Matrix, Ppos) != null)
						{
							te = (TileEntityInfo) world.getTileEntity(Ppos);
							if (te != null)
							{
								if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
										&& te.Object.placementDir.getOpposite() == dir)
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("corner") && str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == dir)
								{
									Conect = true;
								} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
								{
									EnumFacing[] dirs = str4.getDirs(te.Object.placementDir);
									for (int i = 0; i < dirs.length; i++)
									{
										EnumFacing STdir = dirs[i];
										if (STdir.getOpposite() == dir)
										{
											Conect = true;
											break;
										}
									}
								} else if (te.Object.getUnlocalizedName().equals("thall"))
								{
									str10.setRotation(te.Object.placementRotation);
									EnumFacing[] dirs = str10.getDirs(te.Object.placementDir);
									for (int i = 0; i < 2; i++)
									{
										EnumFacing STdir = dirs[i];
										if (STdir.getOpposite() == dir) Conect = true;
									}
								}
								if (Conect)
								{
									BlockPos Ipos = FacingUtils.IncreaseByDir(dir, IPoint, 9);
									TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos);
									if (Ite != null)
									{
										Ite.Object.connections.add(te.Object);
										te.Object.connections.add(Ite.Object);
									}
								}
							}
						}
					}
					if (!Conect)
					{
						str1.Build(world, dir, Spos, 0);
						str2.ClearWay(world, dir, bpos);
					} else
					{
						// no if's for each dir!
						Spos = FacingUtils.IncreaseByDir(dir.getOpposite(), Spos, 2);
						str3.ClearWay(world, dir, Spos);
						str2.ClearWay(world, dir, bpos);
					}
					return true;
				}
			}
			return false;
		case "window"://TODO: make window use glass that was in players inventory
			if (str6.Check(world, dir, bpos, -1))
			{
				BlockPos MatrixPoint = MatrixHelper.findPointForAddOBJ(world, dir, bpos);
				if (MatrixPoint != null)
				{
					TileEntityInfo te = (TileEntityInfo) world.getTileEntity(MatrixPoint);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str6.getUnlocalizedName());
						Nstr.Configure(bpos, rot, dir);
						te.configureTileEntity("ADD", Nstr);
					}
				}
				str6.setRotation(rot);
				if (rot == 1) str2.ClearWay(world, dir, bpos);
				str6.Build(world, dir, bpos);
				return true;
			}
			return false;
		case "cupola"://TODO: make window use glass that was in players inventory
			if (str7.Check(world, dir, bpos, -1))
			{
				BlockPos MatrixPoint = MatrixHelper.findPointForAddOBJ(world, dir, bpos);
				{
					TileEntityInfo te = (TileEntityInfo) world.getTileEntity(MatrixPoint);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str7.getUnlocalizedName());
						Nstr.Configure(bpos, rot, dir);
						te.configureTileEntity("ADD", Nstr);
					}
				}
				str7.Build(world, dir, bpos);
				str7.ClearWay(world, dir, bpos);
				return true;
			}
			return false;
		case "dockport":
			if (str8.Check(world, dir, bpos, -1))
			{
				BlockPos MatrixPoint = MatrixHelper.findPointForAddOBJ(world, dir, bpos);
				if (MatrixPoint != null)
				{
					TileEntityInfo te = (TileEntityInfo) world.getTileEntity(MatrixPoint);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str8.getUnlocalizedName());
						Nstr.Configure(bpos, rot, dir);
						te.configureTileEntity("ADD", Nstr);
					}
				}
				str8.Build(world, dir, bpos);
				str8.ClearWay(world, dir, bpos);
				return true;
			}
			return false;
		case "solarpanel":
			if (str9.Check(world, dir, bpos, -1))
			{
				BlockPos MatrixPoint = MatrixHelper.findPointForAddOBJ(world, dir, bpos);
				if (MatrixPoint != null)
				{
					TileEntityInfo te = (TileEntityInfo) world.getTileEntity(MatrixPoint);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str9.getUnlocalizedName());
						Nstr.Configure(bpos, rot, dir);
						te.configureTileEntity("ADD", Nstr);
					}
				}
				str9.setRotation(rot);
				str9.Build(world, dir, bpos);
				return true;
			}
		case "thall":
			if (str10.Check(world, dir, bpos, -1))
			{
				
				boolean collides = false;
				
				List<AxisAlignedBB> list = str10.getBoundingBox(dir, bpos);
				
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i) != null && world.collidesWithAnyBlock(list.get(i)))
					{
						collides = true;
						break;
					}
				}
				
				if (!collides)
				{
					BlockPos IPoint = MatrixHelper.findMatrixPoint(world, dir, bpos);
					List<BlockPos> Matrix;
					if (IPoint != null)
					{
						Matrix = new ArrayList();
						Matrix.clear();
						Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
						// if (Matrix == null) return false;
						if (Matrix != null) if (!isAvailable(dir, Matrix, IPoint)) return false;
					} else Matrix = null;
					TileEntityInfo te = null;
					if (IPoint != null)
					{
						te = (TileEntityInfo) world.getTileEntity(IPoint);
						if (te != null)
						{
							Structure Nstr = Structure.FindStructure(str10.getUnlocalizedName());
							Nstr.Configure(bpos, rot, dir);
							te.ChildObjects.add(Nstr);
						}
					}
					str10.setRotation(rot);
					str10.Build(world, dir, bpos);
					
					if (debugBB)
					{
						world.setBlockState(new BlockPos(list.get(0).maxX, list.get(0).maxY, list.get(0).maxZ),
								Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW));
						world.setBlockState(new BlockPos(list.get(0).minX, list.get(0).minY, list.get(0).minZ),
								Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.LIME));
					}
					
					EnumFacing[] dirs = str10.getDirs(dir);
					for (int i = 0; i < 2; i++)
					{
						EnumFacing Ndir = dirs[i];
						BlockPos pos = str4.ChangePosForDir(dir, Ndir, bpos);
						// str1.Build(world, Ndir,
						// pos[0],pos[1],pos[2]);
						
						BlockPos Spos;
						if (IPoint != null)
						{
							Spos = FacingUtils.IncreaseByDir(dir, IPoint, 9);
							Spos = FacingUtils.IncreaseByDir(Ndir, Spos, 9);
						} else
						{
							Spos = new BlockPos(0, 0, 0);
						}
						
						boolean Conect = false;
						if (Matrix != null)
						{
							if (MatrixHelper.FindPointInMatrix(Matrix, Spos) != null)
							{
								te = (TileEntityInfo) world.getTileEntity(Spos);
								if (te != null)
								{
									if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
											&& te.Object.placementDir.getOpposite() == Ndir)
									{
										Conect = true;
									} else if (te.Object.getUnlocalizedName().equals("corner")
											&& str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == Ndir)
									{
										Conect = true;
									} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
									{
										EnumFacing[] dirs1 = str4.getDirs(te.Object.placementDir);
										for (int i2 = 0; i2 < dirs.length; i2++)
										{
											EnumFacing STdir = dirs1[i2];
											if (STdir.getOpposite() == Ndir) Conect = true;
										}
									} else if (te.Object.getUnlocalizedName().equals("thall"))
									{
										str10.setRotation(te.Object.placementRotation);
										EnumFacing[] dirs1 = str10.getDirs(te.Object.placementDir);
										for (int j = 0; j < 2; j++)
										{
											EnumFacing STdir = dirs1[j];
											if (STdir.getOpposite() == Ndir) Conect = true;
										}
									}
									if (Conect)
									{
										BlockPos Ipos = FacingUtils.IncreaseByDir(dir, IPoint, 9);
										TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos);
										if (Ite != null)
										{
											Ite.Object.connections.add(te.Object);
											te.Object.connections.add(Ite.Object);
										}
									}
								}
							}
						}
						// System.out.println("W "+pos[0]+" "+pos[1]+" "+pos[2]+" "+Ndir+" "+i);
						if (!Conect)
						{
							str1.Build(world, Ndir, pos);
						} else
						{
							pos = FacingUtils.IncreaseByDir(Ndir.getOpposite(), pos, 2);
							str3.ClearWay(world, Ndir, pos);
						}
					}
					
					str2.ClearWay(world, dir, bpos);
					
					return true;
				}
			}
			return false;
		case "bighall"://TODO: Corners in this strucure is not sealable
			if (str11.Check(world, dir, bpos, -1))
			{
				boolean collides = false;
				
				List<AxisAlignedBB> list = str11.getBoundingBox(dir, bpos);
				
				for (int i = 0; i < list.size(); i++)
				{
					if (list.get(i) != null && world.collidesWithAnyBlock(list.get(i)))
					{
						collides = true;
						break;
					}
				}
				
				if (!collides)
				{
					
					BlockPos IPoint = MatrixHelper.findMatrixPoint(world, dir, bpos);
					List<BlockPos> Matrix;
					if (IPoint != null)
					{
						Matrix = new ArrayList();
						Matrix.clear();
						Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
						
						if (Matrix != null)
						{
							if (!isAvailable(dir, Matrix, IPoint)) return false;
							BlockPos FTPos = FacingUtils.IncreaseByDir(dir, IPoint, 9);
							if (!isAvailable(dir, Matrix, FTPos)) return false;
							EnumFacing Tdir;
							if (rot == 0)
							{
								Tdir = dir.rotateYCCW();
							} else
							{
								Tdir = dir.rotateY();
							}
							BlockPos Tpos = FacingUtils.IncreaseByDir(Tdir, IPoint, 9);
							if (!isAvailable(dir, Matrix, Tpos)) return false;
							FacingUtils.IncreaseByDir(dir, Tpos, 9);
							if (!isAvailable(dir, Matrix, FTPos)) return false;
						}
						
					} else Matrix = null;
					TileEntityInfo te = null;
					if (IPoint != null)
					{
						te = (TileEntityInfo) world.getTileEntity(IPoint);
						if (te != null)
						{
							Structure Nstr = Structure.FindStructure(str11.getUnlocalizedName());
							Nstr.Configure(bpos, rot, dir);
							te.ChildObjects.add(Nstr);
						}
					}
					str11.setRotation(rot);
					str11.Build(world, dir, bpos);
					
					if (debugBB)
					{
						world.setBlockState(new BlockPos(list.get(0).maxX, list.get(0).maxY, list.get(0).maxZ),
								Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.YELLOW));
						world.setBlockState(new BlockPos(list.get(0).minX, list.get(0).minY, list.get(0).minZ),
								Blocks.WOOL.getDefaultState().withProperty(BlockColored.COLOR, EnumDyeColor.LIME));
					}
					
					EnumFacing[] dirs = str11.getDirs(dir);
					List<BlockPos> posT = str11.getPos(dir, dirs, bpos);
					Iterator<BlockPos> posI = posT.iterator();
					int i = 0;
					while (posI.hasNext())
					{
						BlockPos pos = posI.next();
						boolean Conect = false;
						if (Matrix != null)
						{
							
							BlockPos Tpos = FacingUtils.IncreaseByDir(dirs[i], pos, 4);
							Tpos = Tpos.add(0, -3, 0);
							
							if (MatrixHelper.FindPointInMatrix(Matrix, Tpos) != null)
							{
								te = (TileEntityInfo) world.getTileEntity(Tpos);
								if (te != null)
								{
									if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock"))
											&& te.Object.placementDir.getOpposite() == dirs[i])
									{
										Conect = true;
									} else if (te.Object.getUnlocalizedName().equals("corner")
											&& str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == dirs[i])
									{
										Conect = true;
									} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
									{
										EnumFacing[] Cdirs = str4.getDirs(te.Object.placementDir);
										for (int i2 = 0; i2 < Cdirs.length; i2++)
										{
											EnumFacing STdir = Cdirs[i2];
											if (STdir.getOpposite() == dirs[i])
											{
												Conect = true;
												break;
											}
										}
									} else if (te.Object.getUnlocalizedName().equals("thall"))
									{
										str10.setRotation(te.Object.placementRotation);
										EnumFacing[] dirs1 = str10.getDirs(te.Object.placementDir);
										for (int j = 0; j < dirs1.length; j++)
										{
											EnumFacing STdir = dirs1[j];
											if (STdir.getOpposite() == dirs[i]) Conect = true;
										}
									}
									if (Conect)
									{
										BlockPos Ipos = FacingUtils.IncreaseByDir(dir, IPoint, 9);
										TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(Ipos);
										if (Ite != null)
										{
											Ite.Object.connections.add(te.Object);
											te.Object.connections.add(Ite.Object);
										}
									}
								}
							}
						}
						if (!Conect)
						{
							str1.Build(world, dirs[i], pos);
							i++;
						} else
						{
							//pos = pos.add(0, 3, 0);
							BlockPos Prpos = new BlockPos(pos);
							pos = FacingUtils.IncreaseByDir(dirs[i].getOpposite(), pos, 2);
							str2.ClearWay(world, dirs[i], Prpos);
							str3.ClearWay(world, dirs[i], pos);
							i++;
						}
					}
					str2.ClearWay(world, dir, bpos);
					return true;
				}
			}
			return false;
		case "greenhouse":
			if (str12.Check(world, dir, bpos, -1))
			{
				BlockPos MatrixPoint = MatrixHelper.findPointForAddOBJ(world, dir, bpos);
				if (MatrixPoint != null)
				{
					
					TileEntityInfo te = (TileEntityInfo) world.getTileEntity(MatrixPoint);
					if (te != null)
					{
						List<BlockPos> Matrix = new ArrayList();
						Matrix.clear();
						Matrix = MatrixHelper.findTotalMatrix(world, MatrixPoint);
						Structure curr = te.Object;
						boolean[] wrong = new boolean[] { false, false };
						if (Matrix != null)
						{
							BlockPos Spos = FacingUtils.IncreaseByDir(EnumFacing.WEST, MatrixPoint, 9);
							BlockPos Nmatr = MatrixHelper.FindPointInMatrix(Matrix, Spos);
							if (Nmatr != null)
							{
								TileEntityInfo te2 = (TileEntityInfo) world.getTileEntity(Nmatr);
								if (te2 != null)
								{
									if (te2.AddObjects != null && te2.AddObjects.size() > 0)
									{
										for (int j = 0; j < te2.AddObjects.size(); j++)
										{
											if (te2.AddObjects.get(j).getUnlocalizedName() == Structure.SOLARPANELID
													|| te2.AddObjects.get(j).getUnlocalizedName() == Structure.GREENHOUSE)
											{
												return false;
											}
										}
									}
									if (!(te2.Object.placementPos.equals(curr.placementPos)))
									{
										wrong[0] = true;
									}
								}
							} else
							{
								wrong[0] = true;
							}
							Spos = FacingUtils.IncreaseByDir(EnumFacing.NORTH, MatrixPoint, 9);
							Nmatr = MatrixHelper.FindPointInMatrix(Matrix, Spos);
							if (Nmatr != null)
							{
								TileEntityInfo te2 = (TileEntityInfo) world.getTileEntity(Nmatr);
								if (te2 != null)
								{
									if (te2.AddObjects != null && te2.AddObjects.size() > 0)
									{
										for (int j = 0; j < te2.AddObjects.size(); j++)
										{
											if (te2.AddObjects.get(j).getUnlocalizedName() == Structure.SOLARPANELID
													|| te2.AddObjects.get(j).getUnlocalizedName() == Structure.GREENHOUSE)
											{
												return false;
											}
										}
									}
									if (!(te2.Object.placementPos.equals(curr.placementPos)))
									{
										wrong[1] = true;
									}
								}
							} else
							{
								wrong[1] = true;
							}
							if (wrong[0] && wrong[1])
							{
								bpos = bpos.add(9, 0, 9);
								Spos = new BlockPos(MatrixPoint);
								Spos = Spos.add(9, 0, 9);
								te = (TileEntityInfo) world.getTileEntity(Spos);
								
							} else if (wrong[0])
							{
								bpos = bpos.add(9, 0, 0);
								Spos = new BlockPos(MatrixPoint);
								Spos = Spos.add(9, 0, 0);
								te = (TileEntityInfo) world.getTileEntity(Spos);
							} else if (wrong[1])
							{
								bpos = bpos.add(0, 0, 9);
								Spos = new BlockPos(MatrixPoint);
								Spos = Spos.add(0, 0, 9);
								te = (TileEntityInfo) world.getTileEntity(Spos);
							}
							Spos = new BlockPos(MatrixPoint);
							Spos = Spos.add(-9, 0, -9);
							Nmatr = MatrixHelper.FindPointInMatrix(Matrix, Spos);
							if (Nmatr != null)
							{
								TileEntityInfo te2 = (TileEntityInfo) world.getTileEntity(Nmatr);
								if (te2 != null)
								{
									if (te2.AddObjects != null && te2.AddObjects.size() > 0)
									{
										for (int j = 0; j < te2.AddObjects.size(); j++)
										{
											if (te2.AddObjects.get(j).getUnlocalizedName() == Structure.SOLARPANELID
													|| te2.AddObjects.get(j).getUnlocalizedName() == Structure.GREENHOUSE)
											{
												return false;
											}
										}
									}
								}
							}
						}
					}
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str12.getUnlocalizedName());
						Nstr.Configure(bpos, rot, dir);
						te.configureTileEntity("ADD", Nstr);
					}
				}
				str12.Build(world, dir, bpos);
				return true;
			}
			return false;
		case "pirs":
			if (str13.Check(world, dir, bpos, -1))
			{
				BlockPos MatrixPoint = MatrixHelper.findPointForAddOBJ(world, dir, bpos);
				if (MatrixPoint != null)
				{
					TileEntityInfo te = (TileEntityInfo) world.getTileEntity(MatrixPoint);
					if (te != null)
					{
						Structure Nstr = Structure.FindStructure(str13.getUnlocalizedName());
						Nstr.Configure(bpos, rot, dir);
						te.configureTileEntity("ADD", Nstr);
					}
				}
				if (rot == 1) str2.ClearWay(world, dir, bpos);
				str13.Build(world, dir, bpos);
				str2.ClearWay(world, dir, bpos);
				return true;
			}
		}
		
		return false;
	}
	
	public static void buildInfoPoint(World world, EnumFacing dir, String FuncName, int x, int y, int z, int rot, int PLx, int PLy, int PLz)
	{
		Block info = BlockContainerMod.BlockInfo;
		BlockPos pos = new BlockPos(x, y, z);
		TileEntityInfo te;
		switch (FuncName) {
		case "hall":
			setBlock(world, pos, info, 0, 2);
			te = (TileEntityInfo) world.getTileEntity(pos);
			te.configureTileEntity(dir, rot, str2.copy(), new BlockPos(PLx, PLy, PLz));
			
			break;
		case "corner":
			setBlock(world, pos, info, 0, 2);
			te = (TileEntityInfo) world.getTileEntity(pos);
			te.configureTileEntity(dir, rot, str3.copy(), new BlockPos(PLx, PLy, PLz));
			
			break;
		case "crossroad":
			setBlock(world, pos, info, 0, 2);
			te = (TileEntityInfo) world.getTileEntity(pos);
			te.configureTileEntity(dir, rot, str4.copy(), new BlockPos(PLx, PLy, PLz));
			
			break;
		case "hallairlock":
			setBlock(world, pos, info, 0, 2);
			te = (TileEntityInfo) world.getTileEntity(pos);
			te.configureTileEntity(dir, rot, str5.copy(), new BlockPos(PLx, PLy, PLz));
			
			break;
		case "thall":
			setBlock(world, pos, info, 0, 2);
			te = (TileEntityInfo) world.getTileEntity(pos);
			te.configureTileEntity(dir, rot, str10.copy(), new BlockPos(PLx, PLy, PLz));
			
			break;
		case "bighall":
			setBlock(world, pos, info, 0, 2);
			te = (TileEntityInfo) world.getTileEntity(pos);
			te.configureTileEntity(dir, rot, str11.copy(), new BlockPos(PLx, PLy, PLz));
			
			break;
		}
	}
	
	public static void setBlock(World world, int x, int y, int z, Block block, int meta, int flags)
	{
		world.setBlockState(new BlockPos(x, y, z), block.getStateFromMeta(meta), flags);
	}
	
	public static void setBlock(World world, BlockPos pos, Block block, int meta, int flags)
	{
		world.setBlockState(pos, block.getStateFromMeta(meta), flags);
	}
	
	public static void setBlock(World world, int x, int y, int z, Block block)
	{
		world.setBlockState(new BlockPos(x, y, z), block.getDefaultState());
		
	}
	
	public static void buildRemoveInfoPoint(World world, EnumFacing dir, String FuncName, int x, int y, int z, int rot, int Ix, int Iy, int Iz)
	{
		Block info = BlockContainerMod.BlockRemoveInfo;
		TileEntityRemoveInfo te;
		BlockPos pos = new BlockPos(x, y, z);
		BlockPos pos2 = new BlockPos(Ix, Iy, Iz);
		switch (FuncName) {
		case "hall":
			setBlock(world, pos, info, str2.getMetaFromDir(dir), 2);
			te = (TileEntityRemoveInfo) world.getTileEntity(pos);
			te.configureTileEntity((TileEntityInfo) world.getTileEntity(pos2));
			break;
		case "corner":
			setBlock(world, pos, info, str3.getMetaFromDirARot(dir, rot), 2);
			te = (TileEntityRemoveInfo) world.getTileEntity(pos);
			te.configureTileEntity((TileEntityInfo) world.getTileEntity(pos2));
			break;
		case "crossroad":
			setBlock(world, pos, info, str2.getMetaFromDir(dir), 2);
			te = (TileEntityRemoveInfo) world.getTileEntity(pos);
			te.configureTileEntity((TileEntityInfo) world.getTileEntity(pos2));
			break;
		case "hallairlock":
			setBlock(world, pos, info, str2.getMetaFromDir(dir), 2);
			te = (TileEntityRemoveInfo) world.getTileEntity(pos);
			te.configureTileEntity((TileEntityInfo) world.getTileEntity(pos2));
			break;
		case "thall":
			setBlock(world, pos, info, str10.getMetaFromDirARot(dir, rot), 2);
			te = (TileEntityRemoveInfo) world.getTileEntity(pos);
			te.configureTileEntity((TileEntityInfo) world.getTileEntity(pos2));
			break;
		}
	}
	
	public static void buildBuildPoint(World world, int x, int y, int z, EnumBlockPointStates state)
	{
		Block info = BlockMod.BuildpPoint;
		world.setBlockState(new BlockPos(x, y, z), info.getDefaultState().withProperty(BlockBuildPoint.propertyStates, state), 2);
	}
	
	public static boolean isAvailable(EnumFacing dir, List<BlockPos> M, BlockPos pos)
	{
		BlockPos Tpos = FacingUtils.IncreaseByDir(dir, pos, 9);
		
		if (MatrixHelper.FindPointInMatrix(M, Tpos) == null)
		{
			return true;
		}
		return false;
	}
	
	public static String getLocolizedName(String uln, int rot, boolean isShort)
	{
		
		switch (uln) {
		case "stub":
			return str1.getName();
		case "hall":
			return str2.getName();
		case "corner":
			return str3.getName();
		case "crossroad":
			return str4.getName();
		case "hallairlock":
			return str5.getName();
		case "window":
			return str6.getName();
		case "cupola":
			return str7.getName();
		case "dockport":
			return str8.getName();
		case "solarpanel":
			return str9.getName();
		case "thall":
			return str10.getName();
		case "bighall":
			return str11.getName();
		default:
			return "";
		}
	}
	
}

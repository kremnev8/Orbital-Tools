package net.orbitallabs.structures;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;
import net.orbitallabs.blocks.BlockBuildPoint.EnumBlockPointStates;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.tiles.TileEntityInfo;
import net.orbitallabs.tiles.TileEntityRemoveInfo;
import net.orbitallabs.utils.FacingUtils;
import net.orbitallabs.utils.MatrixHelper;
import net.orbitallabs.utils.OreDictItemStack;

public class DeconstructHandler {
	
	static StructureStub str1 = new StructureStub(true);
	static StructureHall str2 = new StructureHall(false);
	static StructureCornerHall str3 = new StructureCornerHall(false);
	static StructureCrossroad str4 = new StructureCrossroad(false);
	static StructureHallWAirlock str5 = new StructureHallWAirlock(false);
	static StructureWindow str6 = new StructureWindow(false);
	static StructureCupola str7 = new StructureCupola(false);
	static StructureDockingPort str8 = new StructureDockingPort(false);
	static StructureSolarPanel str9 = new StructureSolarPanel(false);
	static StructureThall str10 = new StructureThall(false);
	static StructureBigHall str11 = new StructureBigHall(false);
	static StructureGreenHouse str12 = new StructureGreenHouse();
	public static StructurePirs str13 = new StructurePirs();
	
	public static List<ItemStack> modificateRetItems(NonNullList<OreDictItemStack> input)
	{
		NonNullList<ItemStack> ret = NonNullList.create();
		if (input.size() > 0)
		{
			Random rand = new Random();
			for (int i = 0; i < input.size(); i++)
			{
				OreDictItemStack curr = input.get(i);
				if (curr.oreID.contains(OreDictionary.doesOreNameExist("plateTin") ? OreDictionary.getOreID("plateTin") : 0))
				{
					int rVal = rand.nextInt(curr.example.getCount() / 4);
					curr.example.shrink(rVal);
					ret.add(new ItemStack(ItemMod.brokenTin, rVal, rand.nextInt(3)));
					ret.add(curr.example);
				} else if (curr.oreID.contains(OreDictionary.doesOreNameExist("plateSteel") ? OreDictionary.getOreID("plateSteel") : 0))
				{
					int rVal = rand.nextInt(curr.example.getCount() / 4);
					curr.example.shrink(rVal);
					ret.add(new ItemStack(ItemMod.brokenSteel, rVal, rand.nextInt(3)));
					ret.add(curr.example);
				} else if (curr.oreID.contains(OreDictionary.doesOreNameExist("compressedSteel") ? OreDictionary.getOreID("compressedSteel") : 0))
				{
					int rVal = rand.nextInt(curr.example.getCount() / 4);
					curr.example.shrink(rVal);
					ret.add(new ItemStack(ItemMod.brokenAluminum, rVal, rand.nextInt(3)));
					ret.add(curr.example);
				} else
				{
					ret.add(curr.example);
				}
				
			}
		}
		return ret;
	}
	
	private static void ClearConnections(TileEntityInfo Ite, World world, Structure str)
	{
		if (Ite != null && Ite.Object.connections != null && Ite.Object.connections.size() > 0)
		{
			for (int j = 0; j < Ite.Object.connections.size(); j++)
			{
				Structure conStr = Ite.Object.connections.get(j);
				if (conStr.placementDir != EnumFacing.UP)
				{
					BlockPos CP = MatrixHelper.findMatrixPoint(world, conStr.placementDir, conStr.placementPos);
					CP = FacingUtils.IncreaseByDir(conStr.placementDir, CP, 9);
					TileEntityInfo Cte = (TileEntityInfo) world.getTileEntity(CP);
					if (Cte != null && Cte.Object.connections != null && Cte.Object.connections.size() > 0)
					{
						for (int l = 0; l < Cte.Object.connections.size(); l++)
						{
							Structure CCStr = Cte.Object.connections.get(l);
							if (CCStr != null && str.getUnlocalizedName().equals(CCStr.getUnlocalizedName()) && str.placementDir == CCStr.placementDir
									&& str.placementPos.equals(CCStr.placementPos))
							{
								Cte.Object.connections.remove(l);
							}
						}
					}
				}
				Ite.Object.connections.remove(j);
			}
		}
	}
	
	private static boolean CheckChildSwapping(TileEntityInfo Ite, World world)
	{
		boolean noConn = false;
		for (int j = 0; j < Ite.ChildObjects.size(); j++)
		{
			if (Ite.ChildObjects.get(j).connections != null && Ite.ChildObjects.get(j).connections.size() > 0)
			{
				Structure Cstr = Ite.ChildObjects.get(j).connections.get(0);
				Structure CHstr = Ite.ChildObjects.get(j);
				if (CHstr instanceof StructureRotatable) ((StructureRotatable) CHstr).setRotation(CHstr.placementRotation);
				EnumFacing[] StrDirs = CHstr.getDirs(CHstr.placementDir);
				BlockPos IP = MatrixHelper.findMatrixPoint(world, CHstr.placementDir, CHstr.placementPos);
				boolean found = false;
				for (int k = 0; k < StrDirs.length; k++)
				{
					BlockPos IPD = FacingUtils.IncreaseByDir(CHstr.placementDir, IP, 9);
					IPD = FacingUtils.IncreaseByDir(StrDirs[k], IPD, 9);
					TileEntityInfo Cte = (TileEntityInfo) world.getTileEntity(IPD);
					if (Cte != null && Cstr.getUnlocalizedName().equals(Cte.Object.getUnlocalizedName()) && Cstr.placementDir == Cte.Object.placementDir
							&& Cstr.placementPos.equals(Cte.Object.placementPos))
					{
						found = true;
						if (CHstr instanceof StructureBigHall)
						{
							noConn = true;
							break;
						}
						IP = FacingUtils.IncreaseByDir(CHstr.placementDir, IP, 9);
						CHstr.placementPos = FacingUtils.IncreaseByDir(CHstr.placementDir, CHstr.placementPos, 4);
						CHstr.placementPos = FacingUtils.IncreaseByDir(StrDirs[k], CHstr.placementPos, 4);
						CHstr.connections.remove(0);
						CHstr.placementDir = StrDirs[k].getOpposite();
						Cte.ChildObjects.add(CHstr);
						
						if (Cte.Object.connections != null && Cte.Object.connections.size() > 0)
						{
							for (int l = 0; l < Cte.Object.connections.size(); l++)
							{
								Structure conStr = Cte.Object.connections.get(l);
								if (conStr != null && conStr.getUnlocalizedName().equals(CHstr.getUnlocalizedName()) && conStr.placementDir == CHstr.placementDir
										&& conStr.placementPos.equals(CHstr.placementPos))
								{
									Cte.Object.connections.remove(l);
								}
							}
						}
						
						TileEntityInfo CHte = (TileEntityInfo) world.getTileEntity(IP);
						if (CHte != null)
						{
							CHte.Object = CHstr;
						}
						break;
					}
				}
				if (!found)
				{
					noConn = true;
					break;
				}
			} else
			{
				noConn = true;
				break;
			}
		}
		return noConn;
	}
	
	private static void ClearParentData(World world, BlockPos IPoint, Structure str)
	{
		if (world.getTileEntity(IPoint) != null)
		{
			TileEntityInfo te = (TileEntityInfo) world.getTileEntity(IPoint);
			for (int i2 = 0; i2 < te.ChildObjects.size(); i2++)
			{
				Structure Astr = (Structure) te.ChildObjects.get(i2);
				if ((Astr.placementPos.equals(str.placementPos)) && Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
						&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
				{
					te.ChildObjects.remove(i2);
					break;
				}
			}
		}
	}
	
	private static boolean TestConnections(List<BlockPos> Matrix, BlockPos Ppos, World world, EnumFacing Ndir)
	{
		boolean Conect = false;
		if (Matrix != null)
		{
			if (MatrixHelper.FindPointInMatrix(Matrix, Ppos) != null)
			{
				TileEntityInfo te = (TileEntityInfo) world.getTileEntity(Ppos);
				if (te != null)
				{
					if ((te.Object.getUnlocalizedName().equals("hall") || te.Object.getUnlocalizedName().equals("hallairlock")) && te.Object.placementDir.getOpposite() == Ndir)
					{
						Conect = true;
					} else if (te.Object.getUnlocalizedName().equals("corner") && str3.onTurn(te.Object.placementDir, te.Object.placementRotation).getOpposite() == Ndir)
					{
						Conect = true;
					} else if (te.Object.getUnlocalizedName().equals("crossroad") || te.Object.getUnlocalizedName().equals("bighall"))
					{
						EnumFacing[] dirs = str4.getDirs(te.Object.placementDir);
						for (int i2 = 0; i2 < dirs.length; i2++)
						{
							EnumFacing STdir = dirs[i2];
							if (STdir.getOpposite() == Ndir)
							{
								Conect = true;
								break;
							}
						}
					} else if (te.Object.getUnlocalizedName().equals("thall"))
					{
						str10.setRotation(te.Object.placementRotation);
						EnumFacing[] dirs = str10.getDirs(te.Object.placementDir);
						for (int i2 = 0; i2 < 2; i2++)
						{
							EnumFacing STdir = dirs[i2];
							if (STdir.getOpposite() == Ndir) Conect = true;
						}
					}
				}
			}
		}
		return Conect;
	}
	
	/**
	 * 
	 * @return 0 - failed, 1 - partially failed, 2 - successful
	 */
	public static int HandleDeconstruct(World world, List<Structure> objs, EntityPlayerMP player, BlockPos Ipos)
	{
		NonNullList<ItemStack> afterI = NonNullList.create();
		for (int i = 0; i < objs.size(); i++)
		{
			
			Structure str = (Structure) objs.get(i);
			if (str instanceof StructureRotatable)
			{
				((StructureRotatable) str).setRotation(str.placementRotation);
			}
			
			if (!player.capabilities.isCreativeMode)
			{
				NonNullList<OreDictItemStack> items = str.getRequiredItems();
				afterI.addAll(DeconstructHandler.modificateRetItems(items));
			}
			
			BlockPos IPoint;
			switch (str.getUnlocalizedName()) {
			case "hall":
				
				IPoint = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos);
				List<BlockPos> Matrix;
				boolean Conect = false;
				if (IPoint != null)
				{
					Matrix = new ArrayList<>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					
					BlockPos SIpos = FacingUtils.IncreaseByDir(str.placementDir, IPoint, 9);
					TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(SIpos);
					if (Ite != null && Ite.ChildObjects != null && Ite.ChildObjects.size() > 0)
					{
						boolean noConn = CheckChildSwapping(Ite, world);
						if (noConn)
						{
							if (objs.size() > 1)
							{
								return 1;
							}
							return 0;
						}
					}
					
					ClearConnections(Ite, world, str);
					ClearParentData(world, IPoint, str);
					
					BlockPos Ppos;
					Ppos = new BlockPos(IPoint);
					Ppos = FacingUtils.IncreaseByDir(str.placementDir, Ppos, 18);
					
					Conect = TestConnections(Matrix, Ppos, world, str.placementDir);
					
					IPoint = IPoint.add(0, 3, 0);
					player.setPositionAndUpdate(IPoint.getX() + 0.5F, IPoint.getY() - 1, IPoint.getZ() + 0.5F);
				}
				
				str2.deconstruct(world, str.placementDir, str.placementPos);
				str1.Build(world, str.placementDir, str.placementPos);
				
				EnumFacing dir = str.placementDir;
				
				BlockPos Spos = new BlockPos(str.placementPos);
				Spos = FacingUtils.IncreaseByDir(dir, Spos, 9);
				if (!Conect)
				{
					str1.deconstruct(world, dir, Spos);
				} else
				{
					Spos = FacingUtils.IncreaseByDir(dir.getOpposite(), Spos, 1);
					str1.Build(world, dir.getOpposite(), Spos);
				}
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.setNoPickupDelay();
						world.spawnEntity(item);
					}
					return 2;
				}
				break;
			case "corner":
				
				IPoint = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos);
				Conect = false;
				if (IPoint != null)
				{
					Matrix = new ArrayList<>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					
					BlockPos SIpos = FacingUtils.IncreaseByDir(str.placementDir, IPoint, 9);
					TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(SIpos);
					if (Ite != null && Ite.ChildObjects != null && Ite.ChildObjects.size() > 0)
					{
						boolean noConn = CheckChildSwapping(Ite, world);
						if (noConn)
						{
							if (objs.size() > 1)
							{
								return 1;
							}
							return 0;
						}
					}
					
					ClearConnections(Ite, world, str);
					ClearParentData(world, IPoint, str);
					
					BlockPos Ppos;
					Ppos = new BlockPos(IPoint);
					EnumFacing Ndir = str3.onTurn(str.placementDir, str.placementRotation);
					Ppos = FacingUtils.IncreaseByDir(str.placementDir, Ppos, 9);
					Ppos = FacingUtils.IncreaseByDir(Ndir, Ppos, 9);
					
					Conect = TestConnections(Matrix, Ppos, world, Ndir);
					
					IPoint = IPoint.add(0, 3, 0);
					player.setPositionAndUpdate(IPoint.getX() + 0.5F, IPoint.getY() - 1, IPoint.getZ() + 0.5F);
				}
				
				str3.setRotation(str.placementRotation);
				str3.deconstruct(world, str.placementDir, str.placementPos);
				str1.Build(world, str.placementDir, str.placementPos);
				dir = str.placementDir;
				EnumFacing Ndir = str3.onTurn(dir, str.placementRotation);
				
				Spos = new BlockPos(str.placementPos);
				Spos = FacingUtils.IncreaseByDir(dir, Spos, 4);
				Spos = FacingUtils.IncreaseByDir(Ndir, Spos, 5);
				
				if (!Conect)
				{
					str1.deconstruct(world, Ndir, Spos);
				} else
				{
					Spos = FacingUtils.IncreaseByDir(Ndir.getOpposite(), Spos, 1);
					str1.Build(world, Ndir.getOpposite(), Spos);
				}
				
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.setNoPickupDelay();
						world.spawnEntity(item);
					}
					return 2;
				}
				break;
			case "window":
				str6.setRotation(str.placementRotation);
				str6.deconstruct(world, str.placementDir, str.placementPos);
				if (str.placementRotation == 1)
				{
					str1.Build(world, str.placementDir, str.placementPos);
				} else
				{
					BlockPos pos = new BlockPos(str.placementPos);
					pos = FacingUtils.IncreaseByDir(str.placementDir, pos, 1);
					str6.ClearWay(world, str.placementDir, pos);
				}
				if (world.getTileEntity(Ipos) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos)).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						for (int j = 0; j < prete.size(); j++)
						{
							TileEntityInfo te = prete.get(j);
							for (int i2 = 0; i2 < te.AddObjects.size(); i2++)
							{
								Structure Astr = (Structure) te.AddObjects.get(i2);
								if ((Astr.placementPos.equals(str.placementPos)) && Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
										&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
								{
									te.AddObjects.remove(i2);
									break;
								}
							}
						}
					}
				}
				
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.setNoPickupDelay();
						world.spawnEntity(item);
					}
					return 2;
				}
				break;
			case "solarpanel":
				
				if (world.getTileEntity(Ipos) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos)).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						for (int j = 0; j < prete.size(); j++)
						{
							TileEntityInfo te = prete.get(j);
							
							for (int i2 = 0; i2 < te.AddObjects.size(); i2++)
							{
								Structure Astr = (Structure) te.AddObjects.get(i2);
								if (te.Object.getUnlocalizedName() == Structure.BIGHHALL)
								{
									str9.set = EnumBlockPointStates.GREENHOUSE;
								} else
								{
									str9.set = EnumBlockPointStates.SOLARPANELS;
								}
								if ((Astr.placementPos.equals(str.placementPos)) && Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
										&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
								{
									te.AddObjects.remove(i2);
									break;
								}
							}
						}
					}
				}
				str9.deconstruct(world, str.placementDir, str.placementPos);
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.setNoPickupDelay();
						world.spawnEntity(item);
					}
					return 2;
				}
				break;
			case "crossroad":
				
				IPoint = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos);
				boolean[] ConectT = new boolean[] { false, false, false };
				if (IPoint != null)
				{
					Matrix = new ArrayList<>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					
					BlockPos SIpos = FacingUtils.IncreaseByDir(str.placementDir, IPoint, 9);
					TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(SIpos);
					if (Ite != null && Ite.ChildObjects != null && Ite.ChildObjects.size() > 0)
					{
						boolean noConn = CheckChildSwapping(Ite, world);
						if (noConn)
						{
							if (objs.size() > 1)
							{
								return 1;
							}
							return 0;
						}
					}
					
					ClearConnections(Ite, world, str);
					ClearParentData(world, IPoint, str);
					
					BlockPos Ppos;
					Ppos = new BlockPos(IPoint);
					EnumFacing[] Ndirs = str4.getDirs(str.placementDir);
					for (int i3 = 0; i3 < 3; i3++)
					{
						Ppos = new BlockPos(IPoint);
						Ppos = FacingUtils.IncreaseByDir(str.placementDir, Ppos, 9);
						Ppos = FacingUtils.IncreaseByDir(Ndirs[i3], Ppos, 9);
						
						ConectT[i3] = TestConnections(Matrix, Ppos, world, Ndirs[i3]);
					}
					IPoint = IPoint.add(0, 3, 0);
					player.setPositionAndUpdate(IPoint.getX() + 0.5F, IPoint.getY() - 1, IPoint.getZ() + 0.5F);
				}
				
				str4.deconstruct(world, str.placementDir, str.placementPos);
				str1.Build(world, str.placementDir, str.placementPos);
				
				dir = str.placementDir;
				
				EnumFacing[] dirs = str4.getDirs(dir);
				
				for (int o = 0; o < 3; o++)
				{
					Ndir = dirs[o];
					BlockPos pos = str4.ChangePosForDir(dir, Ndir, str.placementPos);
					
					if (!ConectT[o])
					{
						str1.deconstruct(world, Ndir, pos);
					} else
					{
						pos = FacingUtils.IncreaseByDir(Ndir.getOpposite(), pos, 1);
						str1.Build(world, Ndir.getOpposite(), pos);
					}
					// str1.ClearWay(world, Ndir, pos[0],pos[1],pos[2]);
				}
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.setNoPickupDelay();
						world.spawnEntity(item);
					}
					return 2;
				}
				break;
			case "cupola":
				if (world.getTileEntity(Ipos) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos)).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						TileEntityInfo te = prete.get(0);
						player.setPositionAndUpdate(te.getPos().getX() + 0.5F, te.getPos().getY() + 3, te.getPos().getZ() + 0.5F);
					}
				}
				str7.deconstruct(world, str.placementDir, str.placementPos);
				TileEntityInfo te;
				if (world.getTileEntity(Ipos) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos)).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						for (int j = 0; j < prete.size(); j++)
						{
							te = prete.get(j);
							for (int i2 = 0; i2 < te.AddObjects.size(); i2++)
							{
								Structure Astr = (Structure) te.AddObjects.get(i2);
								if ((Astr.placementPos.equals(str.placementPos)) && Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
										&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
								{
									te.AddObjects.remove(i2);
									break;
								}
							}
						}
					}
				}
				
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.setNoPickupDelay();
						world.spawnEntity(item);
					}
					return 2;
				}
				break;
			case "dockport":
				if (world.getTileEntity(Ipos) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos)).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						te = prete.get(0);
						player.setPositionAndUpdate(te.getPos().getX() + 0.5F, te.getPos().getY() + 3, te.getPos().getZ() + 0.5F);
					}
				}
				str7.deconstruct(world, str.placementDir, str.placementPos);
				
				if (world.getTileEntity(Ipos) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos)).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						for (int j = 0; j < prete.size(); j++)
						{
							te = prete.get(j);
							for (int i2 = 0; i2 < te.AddObjects.size(); i2++)
							{
								Structure Astr = (Structure) te.AddObjects.get(i2);
								if ((Astr.placementPos.equals(str.placementPos)) && Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
										&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
								{
									te.AddObjects.remove(i2);
									break;
								}
							}
						}
					}
				}
				
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.setNoPickupDelay();
						world.spawnEntity(item);
					}
					return 2;
				}
				break;
			case "hallairlock":
				
				IPoint = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos);
				Conect = false;
				if (IPoint != null)
				{
					Matrix = new ArrayList<>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					
					BlockPos SIpos = FacingUtils.IncreaseByDir(str.placementDir, IPoint, 9);
					TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(SIpos);
					if (Ite != null && Ite.ChildObjects != null && Ite.ChildObjects.size() > 0)
					{
						boolean noConn = CheckChildSwapping(Ite, world);
						if (noConn)
						{
							if (objs.size() > 1)
							{
								return 1;
							}
							return 0;
						}
					}
					
					ClearConnections(Ite, world, str);
					ClearParentData(world, IPoint, str);
					
					BlockPos Ppos;
					Ppos = new BlockPos(IPoint);
					Ppos = FacingUtils.IncreaseByDir(str.placementDir, Ppos, 18);
					
					Conect = TestConnections(Matrix, Ppos, world, str.placementDir);
					
					IPoint = IPoint.add(0, 3, 0);
					player.setPositionAndUpdate(IPoint.getX() + 0.5F, IPoint.getY() - 1, IPoint.getZ() + 0.5F);
				}
				str2.deconstruct(world, str.placementDir, str.placementPos);
				str1.Build(world, str.placementDir, str.placementPos);
				
				dir = str.placementDir;
				
				Spos = new BlockPos(str.placementPos);
				Spos = FacingUtils.IncreaseByDir(dir, Spos, 9);
				if (!Conect)
				{
					str1.deconstruct(world, dir, Spos);
				} else
				{
					Spos = FacingUtils.IncreaseByDir(dir.getOpposite(), Spos, 1);
					str1.Build(world, dir.getOpposite(), Spos);
				}
				
				Spos = new BlockPos(str.placementPos);
				Spos = FacingUtils.IncreaseByDir(dir, Spos, 4);
				Spos = FacingUtils.IncreaseByDir(dir.rotateY(), Spos, 1);
				
				world.setBlockToAir(Spos);
				Spos = FacingUtils.IncreaseByDir(dir.rotateYCCW(), Spos, 2);
				world.setBlockToAir(Spos);
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.setNoPickupDelay();
						world.spawnEntity(item);
					}
					return 2;
				}
				break;
			case "thall":
				
				IPoint = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos);
				ConectT = new boolean[] { false, false };
				if (IPoint != null)
				{
					Matrix = new ArrayList<>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					
					BlockPos SIpos = FacingUtils.IncreaseByDir(str.placementDir, IPoint, 9);
					TileEntityInfo Ite = (TileEntityInfo) world.getTileEntity(SIpos);
					if (Ite != null && Ite.ChildObjects != null && Ite.ChildObjects.size() > 0)
					{
						boolean noConn = CheckChildSwapping(Ite, world);
						if (noConn)
						{
							if (objs.size() > 1)
							{
								return 1;
							}
							return 0;
						}
					}
					
					ClearConnections(Ite, world, str);
					ClearParentData(world, IPoint, str);
					
					BlockPos Ppos;
					Ppos = new BlockPos(IPoint);
					str10.setRotation(str.placementRotation);
					EnumFacing[] Ndirs = str10.getDirs(str.placementDir);
					for (int i3 = 0; i3 < 2; i3++)
					{
						Ppos = new BlockPos(IPoint);
						Ppos = FacingUtils.IncreaseByDir(str.placementDir, Ppos, 9);
						Ppos = FacingUtils.IncreaseByDir(Ndirs[i3], Ppos, 9);
						
						ConectT[i3] = TestConnections(Matrix, Ppos, world, Ndirs[i3]);
					}
					
					IPoint = IPoint.add(0, 3, 0);
					player.setPositionAndUpdate(IPoint.getX() + 0.5F, IPoint.getY() - 1, IPoint.getZ() + 0.5F);
				}
				
				str10.setRotation(str.placementRotation);
				str10.deconstruct(world, str.placementDir, str.placementPos);
				str1.Build(world, str.placementDir, str.placementPos);
				
				dir = str.placementDir;
				dirs = str10.getDirs(dir);
				
				for (int o = 0; o < 2; o++)
				{
					Ndir = dirs[o];
					BlockPos pos = str4.ChangePosForDir(dir, Ndir, str.placementPos);
					
					if (!ConectT[o])
					{
						str1.deconstruct(world, Ndir, pos);
					} else
					{
						pos = FacingUtils.IncreaseByDir(Ndir.getOpposite(), pos, 1);
						str1.Build(world, Ndir.getOpposite(), pos);
					}
					// str1.ClearWay(world, Ndir, pos[0],pos[1],pos[2]);
				}
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.setNoPickupDelay();
						world.spawnEntity(item);
					}
					return 2;
				}
				break;
			
			case "bighall":
				
				IPoint = MatrixHelper.findMatrixPoint(world, str.placementDir, str.placementPos);
				ConectT = new boolean[] { false, false, false, false, false, false, false };
				if (IPoint != null)
				{
					Matrix = new ArrayList<>();
					Matrix.clear();
					Matrix = MatrixHelper.findTotalMatrix(world, IPoint);
					if (world.getTileEntity(IPoint) != null)
					{
						te = (TileEntityInfo) world.getTileEntity(IPoint);
						ClearParentData(world, IPoint, str);
						
						ClearConnections(te, world, str);
					}
					//	BlockPos Ppos;
					//Ppos = IPoint.clone();
					str11.setRotation(str.placementRotation);
					EnumFacing[] Ndirs = str11.getDirs(str.placementDir);
					
					List<BlockPos> posT = str11.getPos(str.placementDir, Ndirs, str.placementPos);
					Iterator<BlockPos> posI = posT.iterator();
					
					for (int i3 = 0; i3 < Ndirs.length; i3++)
					{
						BlockPos pos;
						if (posI.hasNext()) pos = posI.next();
						else pos = new BlockPos(0, 0, 0);
						
						pos = FacingUtils.IncreaseByDir(Ndirs[i3], pos, 4);
						
						ConectT[i3] = TestConnections(Matrix, pos.add(0, -3, 0), world, Ndirs[i3]);
						
					}
					IPoint = IPoint.add(0, 3, 0);
					player.setPositionAndUpdate(IPoint.getX() + 0.5F, IPoint.getY() - 1, IPoint.getZ() + 0.5F);
				}
				str11.setRotation(str.placementRotation);
				str11.deconstruct(world, str.placementDir, str.placementPos);
				str1.Build(world, str.placementDir, str.placementPos);
				
				dir = str.placementDir;
				
				dirs = str11.getDirs(dir);
				
				List<BlockPos> posT = str11.getPos(dir, dirs, str.placementPos);
				Iterator<BlockPos> posI = posT.iterator();
				
				for (int o = 0; o < dirs.length; o++)
				{
					Ndir = dirs[o];
					BlockPos pos;
					if (posI.hasNext()) pos = posI.next();
					else pos = new BlockPos(0, 0, 0);
					
					if (!ConectT[o])
					{
						str1.deconstruct(world, Ndir, pos);
					} else
					{
						pos = FacingUtils.IncreaseByDir(Ndir.getOpposite(), pos, 1);
						str1.Build(world, Ndir.getOpposite(), pos);
					}
					// str1.ClearWay(world, Ndir, pos[0],pos[1],pos[2]);
				}
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.setNoPickupDelay();
						world.spawnEntity(item);
					}
					return 2;
				}
				break;
			case "greenhouse":
				if (world.getTileEntity(Ipos) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos)).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						te = prete.get(0);
						player.setPositionAndUpdate(te.getPos().getX() + 0.5F, te.getPos().getX() + 3, te.getPos().getX() + 0.5F);
					}
				}
				str12.deconstruct(world, str.placementDir, str.placementPos);
				
				if (world.getTileEntity(Ipos) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos)).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						for (int j = 0; j < prete.size(); j++)
						{
							te = prete.get(j);
							for (int i2 = 0; i2 < te.AddObjects.size(); i2++)
							{
								Structure Astr = (Structure) te.AddObjects.get(i2);
								if ((Astr.placementPos.equals(str.placementPos)) && Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
										&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
								{
									te.AddObjects.remove(i2);
									break;
								}
							}
						}
					}
				}
				
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.setNoPickupDelay();
						world.spawnEntity(item);
					}
					return 2;
				}
				break;
			case "pirs":
				str13.deconstruct(world, str.placementDir, str.placementPos);
				str1.Build(world, str.placementDir, str.placementPos);
				
				if (world.getTileEntity(Ipos) != null)
				{
					List<TileEntityInfo> prete = ((TileEntityRemoveInfo) world.getTileEntity(Ipos)).infoBlocks;// .get(0);
					if (prete != null && prete.size() > 0)
					{
						for (int j = 0; j < prete.size(); j++)
						{
							te = prete.get(j);
							for (int i2 = 0; i2 < te.AddObjects.size(); i2++)
							{
								Structure Astr = (Structure) te.AddObjects.get(i2);
								if ((Astr.placementPos.equals(str.placementPos)) && Astr.placementDir == str.placementDir && Astr.placementRotation == str.placementRotation
										&& Astr.getUnlocalizedName().equals(str.getUnlocalizedName()))
								{
									te.AddObjects.remove(i2);
									break;
								}
							}
						}
					}
				}
				
				if (i + 1 == objs.size())
				{
					for (int k = 0; k < afterI.size(); k++)
					{
						ItemStack curr = afterI.get(k);
						EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
						item.setNoPickupDelay();
						world.spawnEntity(item);
					}
					return 2;
				}
				break;
			}
		}
		
		for (int k = 0; k < afterI.size(); k++)
		{
			ItemStack curr = afterI.get(k);
			EntityItem item = new EntityItem(world, player.posX, player.posY, player.posZ, curr);
			item.setNoPickupDelay();
			world.spawnEntity(item);
		}
		return 0;
	}
	
}

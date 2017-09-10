package net.orbitallabs.structures;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.orbitallabs.utils.OreDictItemStack;

public abstract class Structure {
	
	public static String HOLLID = "hall";
	public static String CORNERID = "corner";
	public static String CROSSROADID = "crossroad";
	public static String HALLAIRLOCKID = "hallairlock";
	public static String WINDOWID = "window";
	public static String CUPOLAID = "cupola";
	public static String DOCKPORTID = "dockport";
	public static String SOLARPANELID = "solarpanel";
	public static String THALLID = "thall";
	public static String BIGHHALL = "bighall";
	public static String GREENHOUSE = "greenhouse";
	public static String PIERCE = "pierce";
	
	public BlockPos placementPos;
	public EnumFacing placementDir;
	public int placementRotation;
	public List<Structure> connections;
	
	/**
	 * client constructor(call on client)
	 */
	public Structure(boolean hidden)
	{
		connections = new ArrayList();
	}
	
	/**
	 * full constructor(call on server)
	 * 
	 * @param x
	 *            ,y,z - placement position;
	 * @param rot
	 *            - placement rotation
	 * @param dir
	 *            - Placement direction
	 */
	public Structure(int x, int y, int z, int rot, EnumFacing dir)
	{
		connections = new ArrayList();
		placementPos = new BlockPos(x, y, z);
		placementDir = dir;
		placementRotation = rot;
		
	}
	
	/**
	 * full alternative constructor(call on server)
	 * 
	 * @param pos
	 *            - placement position;\
	 * @param rot
	 *            - placement rotation
	 * @param dir
	 *            - Placement direction
	 */
	public Structure(int[] pos, int rot, EnumFacing dir)
	{
		connections = new ArrayList();
		placementPos = new BlockPos(pos[0], pos[1], pos[2]);
		placementDir = dir;
		placementRotation = rot;
	}
	
	public Structure(BlockPos pos, int rot, EnumFacing dir)
	{
		connections = new ArrayList();
		placementPos = pos;
		placementDir = dir;
		placementRotation = rot;
	}
	
	/**
	 * configure class(call on server)
	 * 
	 * @param x,y,z
	 *            - placement position;
	 * @param rot
	 *            - placement rotation
	 * @param dir
	 *            - Placement direction
	 */
	public void Configure(int x, int y, int z, int rot, EnumFacing dir)
	{
		placementPos = new BlockPos(x, y, z);
		placementDir = dir;
		placementRotation = rot;
		
	}
	
	/**
	 * alternative class configure(call on server)
	 * 
	 * @param pos
	 *            - placement position
	 * @param rot
	 *            - placement rotation
	 * @param dir
	 *            - Placement direction
	 */
	public void Configure(int[] pos, int rot, EnumFacing dir)
	{
		placementPos = new BlockPos(pos[0], pos[1], pos[2]);
		placementDir = dir;
		placementRotation = rot;
	}
	
	/**
	 * alternative class configure(call on server)
	 * 
	 * @param pos
	 *            - placement position
	 * @param rot
	 *            - placement rotation
	 * @param dir
	 *            - Placement direction
	 */
	public void Configure(BlockPos pos, int rot, EnumFacing dir)
	{
		placementPos = pos;
		placementDir = dir;
		placementRotation = rot;
	}
	
	public void setRotation(int rot)
	{
		placementRotation = rot;
	}
	
	public int getRotation()
	{
		return placementRotation;
	}
	
	static public Structure FindStructure(String uln)
	{
		if (uln.equals("stub"))
		{
			return new StructureStub(false);
		} else if (uln.equals(HOLLID))
		{
			return new StructureHall(false);
		} else if (uln.equals(CORNERID))
		{
			return new StructureCornerHall(false);
		} else if (uln.equals(CROSSROADID))
		{
			return new StructureCrossroad(false);
		} else if (uln.equals(HALLAIRLOCKID))
		{
			return new StructureHallWAirlock(false);
		} else if (uln.equals(WINDOWID))
		{
			return new StructureWindow(false);
		} else if (uln.equals(CUPOLAID))
		{
			return new StructureCupola(false);
		} else if (uln.equals(DOCKPORTID))
		{
			return new StructureDockingPort(false);
		} else if (uln.equals(SOLARPANELID))
		{
			return new StructureSolarPanel(false);
		} else if (uln.equals(THALLID))
		{
			return new StructureThall(false);
		} else if (uln.equals(BIGHHALL))
		{
			return new StructureBigHall(false);
		} else if (uln.equals(GREENHOUSE))
		{
			return new StructureGreenHouse();
		} else if (uln.equals(PIERCE))
		{
			return new StructurePierce();
		} else
		{
			return null;
		}
	}
	
	public EnumFacing[] getDirs(EnumFacing dir)
	{
		return new EnumFacing[] { dir };
	}
	
	/**
	 * place a structure
	 */
	
	public abstract void Build(World world, EnumFacing dir, BlockPos pos);
	
	/**
	 * deconstruct structure
	 */
	public abstract void deconstruct(World world, EnumFacing dir, BlockPos pos);
	
	/**
	 * check possible to place structure
	 * 
	 * @param meta
	 *            0 - everything, 1 - everything excluding pierce, 2 - only add
	 *            structures, 3 - only window(only rot == 0), 4 - solar panels,
	 *            5 - greenhouse, 6 - pierce
	 * 
	 */
	public abstract boolean Check(World world, EnumFacing dir, BlockPos pos, int meta);
	
	/**
	 * Clear way after placing structure
	 */
	public abstract void ClearWay(World world, EnumFacing dir, BlockPos pos);
	
	public abstract boolean isHidden();
	
	public abstract String getName();
	
	public abstract String getUnlocalizedName();
	
	public abstract Structure copy();
	
	/**
	 * 
	 * @return required items to build it
	 */
	public abstract List<OreDictItemStack> getRequiredItems();
	
	/**
	 * some data was allready included. but not all.
	 */
	public StructureData getStructureData()
	{
		StructureData data = new StructureData(getUnlocalizedName(), 0, 0);
		data.name = this.getName();
		// data.requiredItems = this.getRequiredItems();
		data.addRequiredItems(getRequiredItems());
		data.specialFunc = "none";
		return data;
	}
	
}

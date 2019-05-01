package net.orbitallabs.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;
import net.orbitallabs.tiles.TileEntityArmorStand;
import net.orbitallabs.tiles.TileEntityDockingPort;
import net.orbitallabs.tiles.TileEntityGravitySource;
import net.orbitallabs.tiles.TileEntityRemoveInfo;

public class GuiHandler implements IGuiHandler {
	
	public static final int BUILDERGUI = 0;
	public static final int REMOVERGUI = 1;
	public static final int DOCKINGPORTGUI = 2;
	public static final int FUELLOADERGUI = 3;
	public static final int ARMORSTANDGUI = 4;
	public static final int GRAVITYSOURCEGUI = 5;
	public static final int MODIFICATORGUI = 6;
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		
		switch (ID) {
		case BUILDERGUI:
			return new ContainerBuilder(player.inventory);
		case REMOVERGUI:
			return new ContainerRemover(player.inventory, (TileEntityRemoveInfo) te);
		case DOCKINGPORTGUI:
			if (te != null)
			{
				return new ContainerDockingPort(player.inventory, (TileEntityDockingPort) te);
			} 
		case ARMORSTANDGUI:
			if (te != null)
			{
				return new ContainerArmorStand(player, (TileEntityArmorStand) te);
			}
		case GRAVITYSOURCEGUI:
			if (te != null)
			{
				return new ContainerArtificialGSource(player.inventory, (TileEntityGravitySource) te);
			}
		case MODIFICATORGUI:
			return new ContainerModificator(player.inventory, (TileEntityRemoveInfo) te);
		}
		
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity te = world.getTileEntity(new BlockPos(x, y, z));
		
		switch (ID) {
		case BUILDERGUI:
			return new GuiBuilder(player);
		case REMOVERGUI:
			return new GuiRemover(player, (TileEntityRemoveInfo) te);
		case DOCKINGPORTGUI:
			if (te != null)
			{
				return new GuiDockingPort(player.inventory, (TileEntityDockingPort) te);
			}
		case ARMORSTANDGUI:
			if (te != null)
			{
				return new GuiArmorStand(player, (TileEntityArmorStand) te);
			}
		case GRAVITYSOURCEGUI:
			if (te != null)
			{
				return new GuiArtificialGSource(player.inventory, (TileEntityGravitySource) te);
			}
		case MODIFICATORGUI:
			return new GuiModificator(player, (TileEntityRemoveInfo) te);
		}
		
		return null;
	}
}
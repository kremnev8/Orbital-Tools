package net.orbitallabs.items;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.orbitallabs.OrbitalTools;
import net.orbitallabs.blocks.BlockContainerMod;
import net.orbitallabs.gui.GuiHandler;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.OpenGuiOnServerPacket;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.tiles.TileEntityInfo;
import net.orbitallabs.tiles.TileEntityRemoveInfo;
import net.orbitallabs.utils.ChatUtils;
import net.orbitallabs.utils.Config;

public class ItemDebugTool extends ItemMod {
	
	public ItemDebugTool(String uln)
	{
		super(uln);
		this.setCreativeTab(CreativeTabs.TOOLS);
	}
	
	boolean test;
	
	@Override
	public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
	{
		Block b = world.getBlockState(pos).getBlock();
		
		if (b == BlockContainerMod.BlockRemoveInfo && !player.isSneaking())
		{
			if (!world.isRemote)
			{
				player.sendMessage(new TextComponentString("Meta:" + b.getMetaFromState(world.getBlockState(pos))));
				TileEntityRemoveInfo te = (TileEntityRemoveInfo) world.getTileEntity(pos);
				if (te != null && te.infoBlocks != null && te.infoBlocks.size() > 0)
				{
					player.sendMessage(new TextComponentString("Info Point Pos:"));
					player.sendMessage(new TextComponentString(
							te.infoBlocks.get(0).getPos().getX() + " " + te.infoBlocks.get(0).getPos().getY() + " " + te.infoBlocks.get(0).getPos().getZ()));
					
					TileEntityInfo teI = te.infoBlocks.get(0);
					
					if (teI.Object != null && teI.Object.placementDir != null)
					{
						player.sendMessage(new TextComponentString("OBJ:" + teI.Object.getUnlocalizedName()));
						player.sendMessage(new TextComponentString("DIR:" + teI.Object.placementDir.name()));
						player.sendMessage(new TextComponentString("ROT:" + teI.Object.placementRotation + ""));
						player.sendMessage(
								new TextComponentString("POS:" + teI.Object.placementPos.getX() + " " + teI.Object.placementPos.getY() + " " + teI.Object.placementPos.getZ()));
						if (teI.Object.connections != null && teI.Object.connections.size() > 0)
						{
							player.sendMessage(new TextComponentString("Connections:"));
							for (int i = 0; i < teI.Object.connections.size(); i++)
							{
								player.sendMessage(new TextComponentString(teI.Object.connections.get(i).getUnlocalizedName() + " "
										+ teI.Object.connections.get(i).placementRotation + " " + teI.Object.connections.get(i).placementDir.toString()));
							}
						}
					}
					
					if (te.infoBlocks.get(0).Object != null && te.infoBlocks.get(0).Object.getUnlocalizedName() == Structure.BIGHHALL)
					{
						List<Structure> ChildObjects = new ArrayList();
						ChildObjects.addAll(te.infoBlocks.get(0).ChildObjects);
						List<Structure> AddObjects = new ArrayList();
						AddObjects.addAll(te.infoBlocks.get(0).AddObjects);
						if (te.infoBlocks.size() > 1)
						{
							for (int i = 1; i < te.infoBlocks.size(); i++)
							{
								TileEntityInfo te2 = te.infoBlocks.get(i);
								ChildObjects.addAll(te2.ChildObjects);
								AddObjects.addAll(te2.AddObjects);
								
							}
						}
						if (ChildObjects != null && ChildObjects.size() > 0)
						{
							player.sendMessage(new TextComponentString("Child Structures:"));
							for (int i = 0; i < ChildObjects.size(); i++)
								player.sendMessage(new TextComponentString(ChildObjects.get(i).getUnlocalizedName() + " " + ChildObjects.get(i).placementRotation));
						}
						if (AddObjects != null && AddObjects.size() > 0)
						{
							player.sendMessage(new TextComponentString("Additional Structures:"));
							for (int i = 0; i < AddObjects.size(); i++)
								player.sendMessage(new TextComponentString(AddObjects.get(i).getUnlocalizedName() + " " + AddObjects.get(i).placementRotation));
						}
					} else
					{
						
						if (teI.ChildObjects != null && teI.ChildObjects.size() > 0)
						{
							player.sendMessage(new TextComponentString("Child Structures:"));
							for (int i = 0; i < teI.ChildObjects.size(); i++)
								player.sendMessage(new TextComponentString(teI.ChildObjects.get(i).getUnlocalizedName() + " " + teI.ChildObjects.get(i).placementRotation));
						}
						if (teI.AddObjects != null && teI.AddObjects.size() > 0)
						{
							player.sendMessage(new TextComponentString("Additional Structures:"));
							for (int i = 0; i < teI.AddObjects.size(); i++)
								player.sendMessage(new TextComponentString(teI.AddObjects.get(i).getUnlocalizedName() + " " + teI.AddObjects.get(i).placementRotation));
						}
					}
					
				}
				player.sendMessage(new TextComponentString("----"));
				return EnumActionResult.SUCCESS;
			}
		} else if (b == BlockContainerMod.BlockRemoveInfo)
		{
			if (world.isRemote)
			{
				if (!Config.disableUnstableContent)
				{
					player.openGui(OrbitalTools.instance, GuiHandler.MODIFICATORGUI, world, pos.getX(), pos.getY(), pos.getZ());
					PacketHandler.sendToServer(new OpenGuiOnServerPacket(GuiHandler.MODIFICATORGUI, pos.getX(), pos.getY(), pos.getZ()));
				} else ChatUtils.SendChatMessageOnClient(player, ChatUtils.modifyColor(new TextComponentString(I18n.format("modificator.notready.name")), TextFormatting.RED));
			}
		}
		return EnumActionResult.PASS;
	}
	
}

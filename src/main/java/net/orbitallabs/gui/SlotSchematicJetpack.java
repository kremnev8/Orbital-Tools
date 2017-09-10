
package net.orbitallabs.gui;

import micdoodle8.mods.galacticraft.core.GCItems;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.network.PacketSimple;
import micdoodle8.mods.galacticraft.core.network.PacketSimple.EnumSimplePacket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.orbitallabs.items.ItemMod;

public class SlotSchematicJetpack extends Slot {
	private final int index;
	private final int x, y, z;
	private final EntityPlayer player;
	
	public SlotSchematicJetpack(IInventory par2IInventory, int par3, int par4, int par5, int x, int y, int z, EntityPlayer player)
	{
		super(par2IInventory, par3, par4, par5);
		this.index = par3;
		this.x = x;
		this.y = y;
		this.z = z;
		this.player = player;
	}
	
	@Override
	public void onSlotChanged()
	{
		if (this.player instanceof EntityPlayerMP)
		{
			for (int var12 = 0; var12 < this.player.world.playerEntities.size(); ++var12)
			{
				final EntityPlayerMP var13 = (EntityPlayerMP) this.player.world.playerEntities.get(var12);
				
				if (var13.dimension == this.player.world.provider.getDimension())
				{
					final double var14 = this.x - var13.posX;
					final double var16 = this.y - var13.posY;
					final double var18 = this.z - var13.posZ;
					
					if (var14 * var14 + var16 * var16 + var18 * var18 < 20 * 20)
					{
						GalacticraftCore.packetPipeline
								.sendTo(new PacketSimple(EnumSimplePacket.C_SPAWN_SPARK_PARTICLES, var13.dimension, new Object[] { new BlockPos(this.x, this.y, this.z) }), var13);
					}
				}
			}
		}
	}
	
	@Override
	public boolean isItemValid(ItemStack par1ItemStack)
	{
		switch (this.index) {
		
		case 1:
		case 5:
		case 16:
		case 20:
			return par1ItemStack.getItem() == ItemMod.OD_engines_set;
		
		case 2:
		case 3:
		case 4:
		case 6:
		case 10:
		case 11:
		case 15:
		case 17:
		case 18:
		case 19:
			return par1ItemStack.getItem() == GCItems.basicItem && par1ItemStack.getItemDamage() == 9;
		
		case 7:
		case 8:
		case 12:
		case 13:
			return par1ItemStack.getItem() == GCItems.basicItem && par1ItemStack.getItemDamage() == 8;
		
		case 9:
			return par1ItemStack.getItem() == GCItems.basicItem && par1ItemStack.getItemDamage() == 13;
		
		case 14:
			return par1ItemStack.getItem() == GCItems.basicItem && par1ItemStack.getItemDamage() == 14;
		
		case 21:
		case 22:
		case 23:
		case 24:
			return par1ItemStack.getItem() == GCItems.flagPole;
		}
		
		return false;
	}
	
	/**
	 * Returns the maximum stack size for a given slot (usually the same as
	 * getInventoryStackLimit(), but 1 in the case of armor slots)
	 */
	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}
}

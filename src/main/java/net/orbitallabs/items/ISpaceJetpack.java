package net.orbitallabs.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.orbitallabs.dimensions.WorldProviderOrbitModif;

public interface ISpaceJetpack {
	
	public void tickJetpackMovemnt(EntityPlayer player, ItemStack itemStack);
	
	public default boolean isDisabled(EntityPlayer player, ItemStack itemStack, boolean useage)
	{
		SpaceJetpackItemStackCap cap = (SpaceJetpackItemStackCap) itemStack.getCapability(SpaceJetpackCapability.SpaceJetpackCapability, EnumFacing.UP);
		
		if (player.world.provider instanceof WorldProviderOrbitModif)
		{
			WorldProviderOrbitModif prow = (WorldProviderOrbitModif) player.world.provider;
			if (prow.artificialG > 0.1D)
			{
				return true;
			}
		}
		if (player.onGround)
		{
			return true;
		}
		if (cap.getTank().getFluidAmount() == 0)
		{
			return true;
		}
		if (player.world.isRemote && Minecraft.getMinecraft().currentScreen != null && !useage)
		{
			return true;
		}
		return !cap.isEnabled();
	}
	
}

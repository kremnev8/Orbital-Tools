
package net.orbitallabs.dimensions;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.CloudRenderer;
import micdoodle8.mods.galacticraft.core.client.SkyProviderOrbit;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class WorldProviderOrbitEarth extends WorldProviderOrbitModif {
	
	@Override
	public CelestialBody getCelestialBody()
	{
		return GalacticraftCore.satelliteSpaceStation;
	}
	
	public boolean isDaytime()
	{
		final float a = this.world.getCelestialAngle(0F);
		return a < 0.42F || a > 0.58F;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void createSkyProvider()
	{
		this.setSkyRenderer(new SkyProviderOrbit(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/earth.png"), true, true));
		this.setSpinDeltaPerTick(this.getSpinManager().getSpinRate());
		
		if (this.getCloudRenderer() == null) this.setCloudRenderer(new CloudRenderer());
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1)
	{
		final float var2 = this.world.getCelestialAngle(par1);
		float var3 = 1.0F - (MathHelper.cos(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);
		
		if (var3 < 0.0F)
		{
			var3 = 0.0F;
		}
		
		if (var3 > 1.0F)
		{
			var3 = 1.0F;
		}
		
		return var3 * var3 * 0.5F + 0.3F;
	}
	
	@Override
	public int getRespawnDimension(EntityPlayerMP player)
	{
		return this.getDimension();
	}
	
	@Override
	public String getPlanetToOrbit()
	{
		return "Overworld";
	}
	
	@Override
	public String getSaveFolder()
	{
		return "DIM_SPACESTATION" + this.spaceStationDimensionID;
	}
	
	@Override
	public double getSolarEnergyMultiplier()
	{
		return ConfigManagerCore.spaceStationEnergyScalar;
	}
	
	@Override
	public boolean canSpaceshipTierPass(int tier)
	{
		return tier > 0;
	}
}

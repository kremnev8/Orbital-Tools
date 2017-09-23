package net.orbitallabs.gui;

import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import micdoodle8.mods.galacticraft.core.client.gui.container.GuiContainerGC;
import micdoodle8.mods.galacticraft.core.client.gui.element.GuiElementInfoRegion;
import micdoodle8.mods.galacticraft.core.energy.EnergyDisplayHelper;
import micdoodle8.mods.galacticraft.core.util.EnumColor;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiSlider;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.GravityChangePacket;
import net.orbitallabs.tiles.TileEntityGravitySource;
import net.orbitallabs.utils.OrbitalModInfo;

public class GuiArtificialGSource extends GuiContainerGC {
	private static final ResourceLocation texture = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/ArtificialGsource.png");
	
	private final TileEntityGravitySource Gsource;
	
	private GuiSlider slider;
	private GuiElementInfoRegion electricInfoRegion = new GuiElementInfoRegion((this.width - this.xSize) / 2 + 112, (this.height - this.ySize) / 2 + 65, 56, 9,
			new ArrayList<String>(), this.width, this.height, this);
	
	private double lastVal = 0D;
	private boolean updated = false;
	
	public GuiArtificialGSource(InventoryPlayer player, TileEntityGravitySource tile)
	{
		super(new ContainerArtificialGSource(player, tile));
		this.Gsource = tile;
		this.ySize = 144;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui()
	{
		super.initGui();
		final int x = (this.width - this.xSize) / 2;
		final int y = (this.height - this.ySize) / 2;
		
		List<String> electricityDesc = new ArrayList<String>();
		electricityDesc.add(GCCoreUtil.translate("gui.energy_storage.desc.0"));
		electricityDesc.add(EnumColor.YELLOW + GCCoreUtil.translate("gui.energy_storage.desc.1")
				+ ((int) Math.floor(this.Gsource.getEnergyStoredGC()) + " / " + (int) Math.floor(this.Gsource.getMaxEnergyStoredGC())));
		this.electricInfoRegion.tooltipStrings = electricityDesc;
		this.electricInfoRegion.xPosition = (this.width - this.xSize) / 2 + 112;
		this.electricInfoRegion.yPosition = (this.height - this.ySize) / 2 + 43;
		this.electricInfoRegion.parentWidth = this.width;
		this.electricInfoRegion.parentHeight = this.height;
		this.infoRegions.add(this.electricInfoRegion);
		this.buttonList.add(slider = new GuiSlider(0, x + 13, y + 15, 150, 20, "Gravity add:", "", 0, 1.5, (double) Math.round(Gsource.SettedGA * 100) / 100, true, true));
		this.buttonList.add(new GuiButton(1, x + 13, y + 38, 70, 20, "Reset"));
		lastVal = slider.getValue();
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString(GCCoreUtil.translate("container.inventory"), 8, this.ySize - 118 + 2 + 28, 4210752);
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		switch (button.id) {
		case 1:
			Gsource.ClientVal = 1D;
			slider.sliderValue = (1D - 0) / (1.5 - 0);
			slider.displayString = slider.dispString + 1D + slider.suffix;
			break;
		}
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
	{
		if (slider.getValue() != lastVal)
		{
			lastVal = slider.getValue();
			Gsource.ClientVal = (double) Math.round(slider.getValue() * 100) / 100;
			PacketHandler.sendToServer(new GravityChangePacket());
		}
		if (Gsource.SettedGA != lastVal && !updated)
		{
			slider.sliderValue = (Gsource.SettedGA - 0) / (1.5 - 0);
			slider.displayString = slider.dispString + (double) Math.round(slider.getValue() * 100) / 100 + slider.suffix;
			updated = true;
		}
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(texture);
		final int var5 = (this.width - this.xSize) / 2;
		final int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6 + 5, 0, 0, this.xSize, 144);
		List<String> electricityDesc = new ArrayList<String>();
		electricityDesc.add(GCCoreUtil.translate("gui.energy_storage.desc.0"));
		EnergyDisplayHelper.getEnergyDisplayTooltip(this.Gsource.getEnergyStoredGC(), this.Gsource.getMaxEnergyStoredGC(), electricityDesc);
		this.electricInfoRegion.tooltipStrings = electricityDesc;
		
		if (this.Gsource.getEnergyStoredGC() > 0)
		{
			this.drawTexturedModalRect(var5 + 99, var6 + 43, 176, 7, 11, 10);
		}
		
		this.drawTexturedModalRect(var5 + 113, var6 + 44, 176, 0, Math.min(this.Gsource.getScaledElecticalLevel(54), 54), 7);
	}
}
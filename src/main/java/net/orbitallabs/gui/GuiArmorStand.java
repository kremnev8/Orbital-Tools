
package net.orbitallabs.gui;

import org.lwjgl.opengl.GL11;
import micdoodle8.mods.galacticraft.core.client.gui.container.GuiContainerGC;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.SwapArmorPacket;
import net.orbitallabs.tiles.TileEntityArmorStand;
import net.orbitallabs.utils.OrbitalModInfo;

@SideOnly(Side.CLIENT)
public class GuiArmorStand extends GuiContainerGC {
	private static ResourceLocation texture = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/Armor_stand.png");
	
	private IInventory standInventory;
	private IInventory playerInventory;
	
	public GuiArmorStand(EntityPlayer player, TileEntityArmorStand inventory)
	{
		super(new ContainerArmorStand(player, inventory));
		this.standInventory = inventory;
		this.playerInventory = player.inventory;
		xSize = 176;
		ySize = 166;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		xSize = 176;
		ySize = 166;
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		buttonList.add(new GuiButtonSwap(0, x + 81, y + 61));
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		int id = button.id;
		switch (id) {
		case 0:
			PacketHandler.sendToServer(new SwapArmorPacket());
			break;
		}
	}
}

package net.orbitallabs.gui;

import org.lwjgl.opengl.GL11;
import micdoodle8.mods.galacticraft.api.recipe.ISchematicResultPage;
import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.utils.OrbitalModInfo;

public class GuiSchematicJetpack extends GuiContainer implements ISchematicResultPage {
	public static final ResourceLocation schematicTexture = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/schematic_jetpack.png");
	
	private int pageIndex;
	
	public GuiSchematicJetpack(InventoryPlayer par1InventoryPlayer, int x, int y, int z)
	{
		super(new ContainerSchematicJetpack(par1InventoryPlayer, x, y, z));
		this.ySize = 221;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		this.buttonList.clear();
		this.buttonList.add(new GuiButton(0, this.width / 2 - 130, this.height / 2 - 30 + 27 - 12, 40, 20, GCCoreUtil.translate("gui.button.back.name")));
		this.buttonList.add(new GuiButton(1, this.width / 2 - 130, this.height / 2 - 30 + 27 + 12, 40, 20, GCCoreUtil.translate("gui.button.next.name")));
	}
	
	@Override
	protected void actionPerformed(GuiButton par1GuiButton)
	{
		if (par1GuiButton.enabled)
		{
			switch (par1GuiButton.id) {
			case 0:
				SchematicRegistry.flipToLastPage(this, this.pageIndex);
				break;
			case 1:
				SchematicRegistry.flipToNextPage(this, this.pageIndex);
				break;
			}
		}
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int par1, int par2)
	{
		this.fontRendererObj.drawString(ItemMod.spaceJetpack.getItemStackDisplayName(new ItemStack(ItemMod.spaceJetpack, 1, 0)), 7, -20 + 27 + 25, 4210752);
		this.fontRendererObj.drawString(GCCoreUtil.translate("container.inventory"), 8, 220 - 104 + 2 + 27 - 16, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(GuiSchematicJetpack.schematicTexture);
		final int var5 = (this.width - this.xSize) / 2;
		final int var6 = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(var5, var6, 0, 0, this.xSize, this.ySize);
	}
	
	@Override
	public void setPageIndex(int index)
	{
		this.pageIndex = index;
	}
}
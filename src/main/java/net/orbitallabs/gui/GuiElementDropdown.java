package net.orbitallabs.gui;

import org.lwjgl.opengl.GL11;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.orbitallabs.utils.SmallFontRenderer;

public class GuiElementDropdown extends GuiButton {
	protected static final ResourceLocation texture = new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/gui.png");
	
	public boolean dropdownClicked;
	public String[] optionStrings;
	public int selectedOption = -1;
	public SmallFontRenderer font;
	private IDropboxCallback parentClass;
	
	public GuiElementDropdown(int id, IDropboxCallback parentClass, int x, int y, String... text)
	{
		super(id, x, y, 13, 13, "");
		Minecraft mc = FMLClientHandler.instance().getClient();
		this.parentClass = parentClass;
		this.font = new SmallFontRenderer(mc.gameSettings, new ResourceLocation("textures/font/ascii.png"), mc.renderEngine, false);
		this.optionStrings = text;
		
		int largestString = Integer.MIN_VALUE;
		
		for (String element : text)
		{
			largestString = Math.max(largestString, this.font.getStringWidth(element));
		}
		
		this.width = largestString + 8;
	}
	
	@Override
	public void drawButton(Minecraft mc, int x, int y, float ticks)
	{
		if (this.selectedOption == -1)
		{
			this.selectedOption = this.parentClass.getInitialSelection(this);
		}
		
		if (this.visible)
		{
			//            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			//            RenderHelper.disableStandardItemLighting();
			//            GL11.glDisable(GL11.GL_LIGHTING);
			//            GL11.glDisable(GL11.GL_DEPTH_TEST);
			GL11.glPushMatrix();
			
			this.zLevel = 300.0F;
			GL11.glTranslatef(0, 0, 500);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = x >= this.x && y >= this.y && x < this.x + this.width && y < this.y + this.height;
			Gui.drawRect(this.x, this.y, this.x + this.width,
					this.y + (this.dropdownClicked ? this.height * this.optionStrings.length : this.height), ColorUtil.to32BitColor(255, 200, 200, 200));
			Gui.drawRect(this.x + 1, this.y + 1, this.x + this.width - 1,
					this.y + (this.dropdownClicked ? this.height * this.optionStrings.length : this.height) - 1, ColorUtil.to32BitColor(255, 0, 0, 0));
			
			if (this.dropdownClicked && x >= this.x && y >= this.y && x < this.x + this.width
					&& y < this.y + this.height * this.optionStrings.length)
			{
				int hoverPos = (y - this.y) / this.height;
				Gui.drawRect(this.x + 1, this.y + this.height * hoverPos + 1, this.x + this.width - 1, this.y + this.height * (hoverPos + 1) - 1,
						ColorUtil.to32BitColor(255, 100, 100, 100));
			}
			
			this.mouseDragged(mc, x, y);
			
			if (this.dropdownClicked)
			{
				for (int i = 0; i < this.optionStrings.length; i++)
				{
					this.font.drawStringWithShadow(this.optionStrings[i], this.x + this.width / 2 - this.font.getStringWidth(this.optionStrings[i]) / 2,
							this.y + (this.height - 8) / 2 + this.height * i, ColorUtil.to32BitColor(255, 255, 255, 255));
				}
			} else
			{
				this.font.drawStringWithShadow(this.optionStrings[this.selectedOption],
						this.x + this.width / 2 - this.font.getStringWidth(this.optionStrings[this.selectedOption]) / 2, this.y + (this.height - 8) / 2,
						ColorUtil.to32BitColor(255, 255, 255, 255));
			}
			
			GL11.glPopMatrix();
			this.zLevel = 0.0F;
			//            GL11.glEnable(GL11.GL_LIGHTING);
			//            GL11.glEnable(GL11.GL_DEPTH_TEST);
			//            RenderHelper.enableStandardItemLighting();
			//            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
		}
	}
	
	@Override
	public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
	{
		if (!this.dropdownClicked)
		{
			if (this.enabled && this.visible && par2 >= this.x && par3 >= this.y && par2 < this.x + this.width && par3 < this.y + this.height)
			{
				if (this.parentClass.canBeClickedBy(this, par1Minecraft.player))
				{
					this.dropdownClicked = true;
					return true;
				} else
				{
					this.parentClass.onIntruderInteraction();
				}
			}
		} else
		{
			if (this.enabled && this.visible && par2 >= this.x && par3 >= this.y && par2 < this.x + this.width
					&& par3 < this.y + this.height * this.optionStrings.length)
			{
				if (this.parentClass.canBeClickedBy(this, par1Minecraft.player))
				{
					int optionClicked = (par3 - this.y) / this.height;
					this.selectedOption = optionClicked % this.optionStrings.length;
					this.dropdownClicked = false;
					this.parentClass.onSelectionChanged(this, this.selectedOption);
					return true;
				} else
				{
					this.parentClass.onIntruderInteraction();
				}
			} else
			{
				this.dropdownClicked = false;
			}
		}
		
		return false;
	}
	
	public static interface IDropboxCallback {
		public boolean canBeClickedBy(GuiElementDropdown dropdown, EntityPlayer player);
		
		public void onSelectionChanged(GuiElementDropdown dropdown, int selection);
		
		public int getInitialSelection(GuiElementDropdown dropdown);
		
		public void onIntruderInteraction();
	}
}

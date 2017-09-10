package net.orbitallabs.gui;

import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.orbitallabs.utils.OrbitalModInfo;

public class GuiModificatorSide extends GuiModule {
	private ResourceLocation texture = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/Modificator_side.png");
	
	private GuiButton selectedButton;
	
	public GuiModificatorSide(GuiModular parent, boolean right, boolean bottom)
	{
		super(parent, right, bottom);
		this.xSize = 103;
		this.ySize = 115;
		
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		this.guiLeft = ((GuiModificator) parent).crX + parent.realWidth - 3;
		this.guiTop = ((GuiModificator) parent).crY + 6;
		
		buttonList.clear();
		this.buttonList.add(new GuiVerticalSlider(0, guiLeft + 88, guiTop + 6, 10, 83, "", "", 0, 5, 0, true, false));
		//((GuiVerticalSlider) this.buttonList.get(0)).enabled = false;
		
	}
	
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton)
	{
		
		if (mouseButton == 0)
		{
			for (int l = 0; l < this.buttonList.size(); ++l)
			{
				GuiButton guibutton = (GuiButton) this.buttonList.get(l);
				
				if (guibutton.mousePressed(this.mc, mouseX, mouseY))
				{
					this.selectedButton = guibutton;
				}
			}
		}
	}
	
	public void mouseReleased(int mouseX, int mouseY, int state)
	{
		if (this.selectedButton != null && state == 0)
		{
			this.selectedButton.mouseReleased(mouseX, mouseY);
			this.selectedButton = null;
		}
	}
	
	@Override
	public void drawGuiBackgroundLayer(int mouseX, int mouseY)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		if (this.isEnabled)
		{
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			
			GuiModificator modif = (GuiModificator) parent;
			int x = guiLeft = modif.crX + modif.realWidth - 3;
			int y = guiTop = modif.crY + 6;
			
			drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		}
		super.drawGuiBackgroundLayer(mouseX, mouseY);
	}
	
	@Override
	public void drawGuiForegroundLayer(int mouseX, int mouseY)
	{
		
	}
	
	public void simpleText(String text, int x, int y)
	{// StatCollector.translateToLocal("builder.name")
		fontRendererObj.drawString(text, x, y, 4210752, false);
	}
	
}

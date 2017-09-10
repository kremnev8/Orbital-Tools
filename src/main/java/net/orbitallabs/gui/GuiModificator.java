package net.orbitallabs.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.tiles.TileEntityRemoveInfo;
import net.orbitallabs.utils.OrbitalModInfo;

public class GuiModificator extends GuiModular {
	
	private ResourceLocation texture = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/Modificator.png");
	
	public int crX;
	public int crY;
	
	public static Structure object;
	public static List<Structure> ChildObjects = new ArrayList<Structure>();
	public static List<Structure> addObjects = new ArrayList<Structure>();
	public static boolean reInit = false;
	public static boolean CloseThis = false;
	public static int move;
	public int text2y = 0;
	private GuiModificatorSide sideinv;
	public static int lastMove = 0;
	
	public GuiModificator(EntityPlayer player, TileEntityRemoveInfo te)
	{
		super(new ContainerModificator(player.inventory, te));
		
		xSize = 160;
		ySize = 163;
		
		sideinv = new GuiModificatorSide(this, true, false);
		this.getModules().add(sideinv);
		
	}
	
	public static void prepareToOpen()
	{
		reInit = false;
		object = null;
		addObjects = new ArrayList<Structure>();
		ChildObjects = new ArrayList<Structure>();
		lastMove = 0;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		int oldX = guiLeft;
		int oldY = guiTop;
		int oldW = xSize;
		int oldH = ySize;
		
		guiLeft = cornerX;
		guiTop = cornerY;
		xSize = realWidth;
		ySize = realHeight;
		this.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		
		RenderHelper.disableStandardItemLighting();
		
		//	for (int i = this.buttonList.size() - 1; i != -1; i--)
		//	{
		//		((GuiButton) this.buttonList.get(i)).drawButton(this.mc, mouseX, mouseY);
		//	}
		
		GlStateManager.enableBlend();
		GlStateManager.translate((float) guiLeft, (float) guiTop, 0.0F);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		
		this.drawGuiContainerForegroundLayer(mouseX, mouseY);
		
		RenderHelper.enableStandardItemLighting();
		guiLeft = oldX;
		guiTop = oldY;
		xSize = oldW;
		ySize = oldH;
	}
	
	@Override
	public void initGui()
	{
		
		int x = crX = (width - xSize - 40) / 2;
		int y = crY = (height - ySize) / 2;
		
		super.initGui();
		
		this.buttonList.clear();
		
		int max = (ChildObjects != null ? ChildObjects.size() * 4 : 0) + (addObjects != null ? addObjects.size() * 4 : 0) - 2;
		
		this.buttonList.add(new GuiVerticalSlider(0, x + 144, y + 15, 10, 140, "R", "R", 0, max, lastMove > max ? max : lastMove, true, false));
		if (((GuiVerticalSlider) this.buttonList.get(0)).maxValue < 0)
		{
			((GuiVerticalSlider) this.buttonList.get(0)).enabled = false;
			((GuiVerticalSlider) this.buttonList.get(0)).sliderValue = 0;
		}
		if (object != null)
		{
			this.buttonList.add(new GuiButtonModificator(1, x + 7, y + 15, object, y, true));
			//	this.buttonList.add(new GuiMLabel(-1, x + 7, y + 59, StatCollector.translateToLocal("modificator.children.name"), y));
			int lasti = 70;
			if (ChildObjects != null && ChildObjects.size() > 0)
			{
				for (int i = 0; i < ChildObjects.size(); i++)
				{
					
					this.buttonList.add(new GuiButtonModificator(2 + i, x + 7, y + 70 + 44 * i, ChildObjects.get(i), y, false));
					lasti += 44;
					
				}
			}
			this.buttonList.add(new GuiButtonModificator2(buttonList.size(), x + 7, y + lasti, y));
			lasti += 22;
			text2y = y + lasti;
			lasti += 11;
			if (addObjects != null && addObjects.size() > 0)
			{
				for (int i = 0; i < addObjects.size(); i++)
				{
					this.buttonList.add(new GuiButtonModificator(buttonList.size(), x + 7, y + lasti, addObjects.get(i), y, false));
					lasti += 44;
					
				}
			}
			this.buttonList.add(new GuiButtonModificator2(buttonList.size(), x + 7, y + lasti, y));
		}
		
		// this.buttonList.add(new GuiButton(1, x + 70, y + 101, 80, 20,
		// StatCollector.translateToLocal("remover.deconstruct_button.name")));
		
	}
	
	@Override
	protected void keyTyped(char ch, int num)
	{
		try
		{
			super.keyTyped(ch, num);
		} catch (IOException e)
		{
		}
		for (int i = 0; i < this.buttonList.size(); i++)
		{
			if (this.buttonList.get(i) instanceof GuiButtonModificator)
			{
				((GuiButtonModificator) this.buttonList.get(i)).keyTyped(ch, num);
			}
		}
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button instanceof GuiButtonModificator)
		{
			GuiButtonModificator but = (GuiButtonModificator) button;
			if (but.isDelSign)
			{
				int id = but.id;
				if (id >= 2 && id < ChildObjects.size() + 2)
				{
					if (ChildObjects.size() > id - 2)
					{
						ChildObjects.remove(id - 2);
						lastMove = move;
						initGui();
					}
				} else if (id >= 2 + ChildObjects.size())
				{
					if (addObjects.size() > id - 3 - ChildObjects.size())
					{
						addObjects.remove(id - 3 - ChildObjects.size());
						lastMove = move;
						initGui();
					}
				}
			} else
			{
				if (sideinv != null)
				{
					sideinv.isEnabled = true;
				}
			}
			
		}
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float time, int mX, int mY)
	{
		Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
		if (CloseThis)
		{
			CloseThis = false;
			Minecraft.getMinecraft().player.closeScreen();
		}
		
		if (object != null && reInit == false)
		{
			reInit = true;
			initGui();
		}
		
		if (buttonList.size() > 0)
		{
			move = ((GuiVerticalSlider) buttonList.get(0)).getValueInt();
		}
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		
		int x = (width - xSize - 40) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		GL11.glEnable(GL11.GL_SCISSOR_TEST);
		int factor = 4;
		GL11.glScissor((x + 5) * factor, mc.displayHeight - (y + 157) * factor, 160 * factor, 142 * factor);
		
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
		RenderHelper.disableStandardItemLighting();
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		
		int i;
		
		int NyPos = y + 59 - (11 * move);
		if (NyPos > y - 25 || NyPos < y + 157)
		{
			fontRendererObj.drawString(I18n.format("modificator.children.name"),
					x + 7 + (int) (133 / 2) - (fontRendererObj.getStringWidth(I18n.format("modificator.children.name")) / 2), NyPos, 4210752, false);
		}
		
		NyPos = text2y - (11 * move);
		if (NyPos > y - 25 || NyPos < y + 157)
		{
			fontRendererObj.drawString(I18n.format("modificator.additional.name"),
					x + 7 + (int) (133 / 2) - (fontRendererObj.getStringWidth(I18n.format("modificator.additional.name")) / 2), NyPos, 4210752, false);
		}
		
		for (i = this.buttonList.size() - 1; i != -1; i--)
		{
			((GuiButton) this.buttonList.get(i)).drawButton(this.mc, mX, mY);
		}
		
		GL11.glDisable(GL11.GL_SCISSOR_TEST);
		
		super.drawGuiContainerBackgroundLayer(time, mX, mY);
		
		RenderHelper.enableStandardItemLighting();
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int MouseX, int MouseY)
	{
		fontRendererObj.drawString(I18n.format("modificator.name"), (int) (xSize / 4.5D) - (fontRendererObj.getStringWidth(I18n.format("modificator.name")) / 2) + 15, 6, 4210752,
				false);
		
	}
	
}

package net.orbitallabs.gui;

import java.io.IOException;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.orbitallabs.gui.GuiVerticalSlider.ISlider;
import net.orbitallabs.structures.Structure;
import net.orbitallabs.utils.OrbitalModInfo;

public class GuiModificatorSide extends GuiModule implements IGUISliderController, ISlider {
	private ResourceLocation texture = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/gui/modificator_side.png");
	
	private GuiButton selectedButton;
	public GuiVerticalSlider slider;
	
	private GuiModificator main;
	
	public int move;
	
	public boolean reInit = false;
	
	public GuiModificatorSide(GuiModular parent, boolean right, boolean bottom)
	{
		super(parent, right, bottom);
		this.main = (GuiModificator) parent;
		this.xSize = 103;
		this.ySize = 154;
		
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		this.guiLeft = ((GuiModificator) parent).crX + parent.realWidth - 3;
		this.guiTop = ((GuiModificator) parent).crY + 6;
		
		buttonList.clear();
		this.buttonList.add(slider = new GuiVerticalSlider(0, guiLeft + 88, guiTop + 6, 10, 140, "", "", 0, 5, 0, true, false, this));
		
		if (reInit == false)
		{
			move = 0;
		} else
		{
			slider.setValue(move);
		}
		
		//if (main.openButId != -1)
		//{
		for (int i = 0; i < GuiBuilder.strucutures.size(); i++)
		{
			Structure str = GuiBuilder.strucutures.get(i);
			this.buttonList.add(new GuiButtonBuilder(i + 1, guiLeft + 7, guiTop + 7 + i * 20, str.getName(), guiTop, 7, 135, this) {
				@Override
				public void renderIcons()
				{
					Minecraft.getMinecraft().getTextureManager().bindTexture(Icons);
					Structure str = GuiBuilder.strucutures.get(id - 1);
					String name = str.getUnlocalizedName();
					if (name == Structure.BIGHHALL)
					{
						name = "bighall_normal";
					} else if (name == Structure.HALLAIRLOCKID)
					{
						name = "airlock";
					}
					DrawGuiIcon(this.x + 2, this.NyPos + 2, name, 0);
				}
			});
		}
		//}
		//((GuiVerticalSlider) this.buttonList.get(0)).enabled = false;
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if (button.id > 0 && main.openButId != -1)
		{
			GuiButton but = main.getButton(main.openButId);
			if (but instanceof GuiButtonModificator)
			{
				GuiButtonModificator modf = (GuiButtonModificator) but;
				Structure str = GuiBuilder.strucutures.get(button.id - 1);
				str.Configure(new BlockPos(modf.ButStr.placementPos), modf.ButStr.placementRotation, modf.ButStr.placementDir);
				modf.ButStr = str;
				modf.initButtons(modf.ButStr);
				this.isEnabled = false;
			} else if (but instanceof GuiButtonModificator2)
			{
				GuiButtonModificator2 modf = (GuiButtonModificator2) but;
				if (modf.type)
				{
					Structure str = GuiBuilder.strucutures.get(button.id - 1).copy();
					str.Configure(new BlockPos(0, 0, 0), 0, EnumFacing.WEST);
					main.addObjects.add(str);
					main.reInit = false;
					this.isEnabled = false;
				} else
				{
					Structure str = GuiBuilder.strucutures.get(button.id - 1).copy();
					str.Configure(new BlockPos(0, 0, 0), 0, EnumFacing.WEST);
					main.ChildObjects.add(str);
					main.reInit = false;
					this.isEnabled = false;
				}
			}
		}
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
					net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Pre event = new net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Pre(
							this, guibutton, this.buttonList);
					if (net.minecraftforge.common.MinecraftForge.EVENT_BUS.post(event)) break;
					guibutton = event.getButton();
					this.selectedButton = guibutton;
					guibutton.playPressSound(this.mc.getSoundHandler());
					try
					{
						this.actionPerformed(guibutton);
					} catch (IOException e)
					{
						e.printStackTrace();
					}
					if (this.equals(this.mc.currentScreen)) net.minecraftforge.common.MinecraftForge.EVENT_BUS
							.post(new net.minecraftforge.client.event.GuiScreenEvent.ActionPerformedEvent.Post(this, event.getButton(), this.buttonList));
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
			if (!reInit)
			{
				reInit = true;
				initGui();
			}
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
		fontRenderer.drawString(text, x, y, 4210752, false);
	}
	
	@Override
	public int getMove()
	{
		return move;
	}
	
	@Override
	public void onChangeSliderValue(GuiVerticalSlider slider)
	{
		move = slider.getValueInt();
		
	}
	
}

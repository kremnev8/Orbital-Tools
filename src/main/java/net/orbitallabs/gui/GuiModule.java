
package net.orbitallabs.gui;

import java.awt.Rectangle;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.inventory.Slot;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

// a sub-gui. Mostly the same as a separate GuiContainer, but doesn't do the calls that affect the game as if this were the only gui
@SideOnly(Side.CLIENT)
public abstract class GuiModule extends GuiScreen {
	
	protected final GuiModular parent;
	
	// left or right of the parent
	protected final boolean right;
	// top or bottom of the parent
	protected final boolean bottom;
	public static boolean isEnabled = false;
	
	public int yOffset = 0;
	public int xOffset = 0;
	
	public int guiLeft = 0;
	public int guiTop = 0;
	public int xSize = 0;
	public int ySize = 0;
	
	public GuiModule(GuiModular parent, boolean right, boolean bottom)
	{
		this.parent = parent;
		this.right = right;
		this.bottom = bottom;
		isEnabled = false;
	}
	
	public int guiRight()
	{
		return guiLeft + xSize;
	}
	
	public int guiBottom()
	{
		return guiTop + ySize;
	}
	
	public Rectangle getArea()
	{
		return new Rectangle(guiLeft, guiTop, xSize, ySize);
	}
	
	@Override
	public void initGui()
	{
		this.guiLeft = (this.width - this.xSize) / 2;
		this.guiTop = (this.height - this.ySize) / 2;
		
	}
	
	public void updatePosition(int parentX, int parentY, int parentSizeX, int parentSizeY)
	{
		if (right)
		{
			this.guiLeft = parentX + parentSizeX;
		} else
		{
			this.guiLeft = parentX - this.xSize;
		}
		
		if (bottom)
		{
			this.guiTop = parentY + parentSizeY - this.ySize;
		} else
		{
			this.guiTop = parentY;
		}
		
		this.guiLeft += xOffset;
		this.guiTop += yOffset;
	}
	
	public boolean shouldDrawSlot(Slot slot)
	{
		return true;
	}
	
	public boolean isMouseInModule(int mouseX, int mouseY)
	{
		return mouseX >= this.guiLeft && mouseX < this.guiRight() && mouseY >= this.guiTop && mouseY < this.guiBottom();
	}
	
	public boolean isDrawEnabled()
	{
		return isEnabled;
	}
	
	//public void drawScreen(int x, int y)
	//{
	//	if (this.isDrawEnabled())
	//	{
	//		drawGuiBackgroundLayer(x, y);
	//		super.drawScreen(x, y, 0);
	//		GL11.glTranslatef((float) guiLeft, (float) guiTop, 0.0F);
	//		drawGuiForegroundLayer(x, y);
	//		GL11.glTranslatef((float) -guiLeft, (float) -guiTop, 0.0F);
	//	}
	//}
	
	public void drawGuiBackgroundLayer(int mouseX, int mouseY)
	{
		if (this.isDrawEnabled())
		{
			super.drawScreen(mouseX, mouseY, 0);
		}
	}
	
	public void drawGuiForegroundLayer(int mouseX, int mouseY)
	{
		
	}
	
	/**
	 * Custom mouse click handling.
	 * 
	 * @return True to prevent the main container handling the mouseclick
	 */
	public void mouseClicked(int mouseX, int mouseY, int mouseButton)
	{
	}
	
	/**
	 * Custom mouse click handling.
	 * 
	 * @return True to prevent the main container handling the mouseclick
	 */
	public void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
	{
	}
	
	/**
	 * Custom mouse click handling.
	 * 
	 * @return True to prevent the main container handling the mouseclick
	 */
	public void mouseReleased(int mouseX, int mouseY, int state)
	{
		
	}
	
}
package net.orbitallabs.jei;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import mezz.jei.api.gui.IAdvancedGuiHandler;
import net.orbitallabs.gui.GuiModular;
import net.orbitallabs.gui.GuiModule;

public class SlotMover implements IAdvancedGuiHandler<GuiModular> {
	
	@Override
	public Class<GuiModular> getGuiContainerClass()
	{
		
		return GuiModular.class;
	}
	
	@Override
	public List<Rectangle> getGuiExtraAreas(GuiModular guiContainer)
	{
		
		List<Rectangle> moduleB = new ArrayList<>();
		
		for (GuiModule module : guiContainer.getModules())
		{
			if (module.isEnabled)
			{
				moduleB.add(new Rectangle(module.guiLeft, module.guiTop, module.xSize, module.ySize));
			}
		}
		return moduleB;
	}
	
	@Nullable
	@Override
	public Object getIngredientUnderMouse(GuiModular guiContainer, int mouseX, int mouseY)
	{
		
		return null;
	}
	
}
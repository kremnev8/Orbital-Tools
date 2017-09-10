package net.orbitallabs.renderer;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.orbitallabs.entity.EntitySnake;

public class RenderFactory implements IRenderFactory<EntitySnake> {
	
	@Override
	public Render<? super EntitySnake> createRenderFor(RenderManager manager)
	{
		return new SnakeRenderer(manager);
	}
	
}

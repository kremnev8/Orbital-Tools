package net.orbitallabs.renderer;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.entity.EntitySnake;
import net.orbitallabs.renderer.models.ModelSnake;
import net.orbitallabs.utils.OrbitalModInfo;

@SideOnly(Side.CLIENT)
public class SnakeRenderer extends RenderLiving<EntitySnake> {
	
	private static ResourceLocation texture;
	private static ModelSnake model = new ModelSnake();
	
	static
	{
		texture = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/entity/" + "snake.png");
	}
	
	public SnakeRenderer(RenderManager renderManager)
	{
		
		super(renderManager, model, 0.5F);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntitySnake entity)
	{
		
		return texture;
	}
	
	@Override
	public void doRender(EntitySnake entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		
		doRenderSnake(entity, x, y, z, entityYaw, partialTicks);
	}
	
	private void doRenderSnake(EntitySnake entity, double x, double y, double z, float entityYaw, float partialTicks)
	{
		
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
}
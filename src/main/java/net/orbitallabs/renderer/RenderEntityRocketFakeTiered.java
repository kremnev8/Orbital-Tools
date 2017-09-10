package net.orbitallabs.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import com.google.common.base.Function;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.util.ClientUtil;
import micdoodle8.mods.galacticraft.planets.GalacticraftPlanets;
import micdoodle8.mods.galacticraft.planets.asteroids.client.render.item.ItemModelRocketT3;
import micdoodle8.mods.galacticraft.planets.mars.client.render.item.ItemModelRocketT2;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.entity.EntityRocketFakeTiered;
import net.orbitallabs.renderer.models.ModelRocketTier1;

@SideOnly(Side.CLIENT)
public class RenderEntityRocketFakeTiered extends Render<EntityRocketFakeTiered> {
	private ResourceLocation texT1 = new ResourceLocation(Constants.ASSET_PREFIX, "textures/model/rocket_t1.png");
	//private ResourceLocation texT3 = new ResourceLocation(AsteroidsModule.ASSET_PREFIX, "textures/model/tier3rocket.png");
	// new RenderTier1Rocket(new ModelTier2Rocket(), MarsModule.ASSET_PREFIX, "rocketT2"));
	//new RenderTier3Rocket(rocketModel, AsteroidsModule.ASSET_PREFIX, "tier3rocket"))
	
	protected ModelRocketTier1 modelT1 = new ModelRocketTier1();
	
	private ItemModelRocketT2 rocketModelT2;
	private ItemModelRocketT3 rocketModelT3;
	
	private void updateModel()
	{
		if (rocketModelT2 == null)
		{
			Function<ResourceLocation, TextureAtlasSprite> textureGetter = input -> Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(input.toString());
			
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(GalacticraftPlanets.TEXTURE_PREFIX + "rocket_t2", "inventory");
			rocketModelT2 = (ItemModelRocketT2) FMLClientHandler.instance().getClient().getRenderItem().getItemModelMesher().getModelManager().getModel(modelResourceLocation);
		}
		
		if (rocketModelT3 == null)
		{
			Function<ResourceLocation, TextureAtlasSprite> textureGetter = new Function<ResourceLocation, TextureAtlasSprite>() {
				@Override
				public TextureAtlasSprite apply(ResourceLocation input)
				{
					return Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(input.toString());
				}
			};
			
			ModelResourceLocation modelResourceLocation = new ModelResourceLocation(GalacticraftPlanets.TEXTURE_PREFIX + "rocket_t3", "inventory");
			rocketModelT3 = (ItemModelRocketT3) FMLClientHandler.instance().getClient().getRenderItem().getItemModelMesher().getModelManager().getModel(modelResourceLocation);
		}
	}
	
	public RenderEntityRocketFakeTiered(RenderManager renderManager)
	{
		super(renderManager);
		this.shadowSize = 2F;
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityRocketFakeTiered par1Entity)
	{
		EntityRocketFakeTiered rocket = (EntityRocketFakeTiered) par1Entity;
		if (rocket.getTier() == 1)
		{
			return texT1;
		}
		return new ResourceLocation("missing");
	}
	
	public void doRender(EntityRocketFakeTiered entity, double par2, double par4, double par6, float par8, float par9)
	{
		EntityRocketFakeTiered rocket = (EntityRocketFakeTiered) entity;
		if (rocket.getTier() == 1)
		{
			GL11.glPushMatrix();
			final float var24 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par9;
			final float var25 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par9;
			
			GL11.glTranslatef((float) par2, (float) par4, (float) par6);
			GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-var24, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(-var25, 0.0F, 1.0F, 0.0F);
			GL11.glTranslatef(0.0F, entity.getRenderOffsetY(), 0.0F);
			
			this.bindEntityTexture(entity);
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			this.modelT1.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			
			GL11.glPopMatrix();
		} else
		{
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPushMatrix();
			final float var24 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * par9 + 180;
			final float var25 = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * par9 + 45;
			
			GL11.glTranslatef((float) par2, (float) par4, (float) par6);
			GL11.glRotatef(180.0F - par8, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-var24, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(0.0F, entity.getRenderOffsetY(), 0.0F);
			
			updateModel();
			
			RenderHelper.disableStandardItemLighting();
			this.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
			
			if (Minecraft.isAmbientOcclusionEnabled())
			{
				GlStateManager.shadeModel(GL11.GL_SMOOTH);
			} else
			{
				GlStateManager.shadeModel(GL11.GL_FLAT);
			}
			
			GL11.glScalef(-1.0F, -1.0F, 1.0F);
			GL11.glScalef(0.8F, 0.8F, 0.8F);
			if (entity.getTier() == 2)
			{
				ClientUtil.drawBakedModel(rocketModelT2);
			} else if (entity.getTier() == 3)
			{
				ClientUtil.drawBakedModel(rocketModelT3);
			}
			
			Vector3 teamColor = ClientUtil.updateTeamColor(FMLClientHandler.instance().getClient().player.getName(), true);
			if (teamColor != null)
			{
				GL11.glColor3f(teamColor.floatX(), teamColor.floatY(), teamColor.floatZ());
			}
			
			if (FMLClientHandler.instance().getClient().player.ticksExisted / 10 % 2 < 1)
			{
				GL11.glColor3f(1, 0, 0);
			} else
			{
				GL11.glColor3f(0, 1, 0);
			}
			
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_LIGHTING);
			
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glEnable(GL11.GL_LIGHTING);
			
			GL11.glColor3f(1, 1, 1);
			
			GL11.glPopMatrix();
			
			RenderHelper.enableStandardItemLighting();
		}
	}
	
	@Override
	public boolean shouldRender(EntityRocketFakeTiered rocket, ICamera camera, double camX, double camY, double camZ)
	{
		AxisAlignedBB axisalignedbb;
		if (rocket.getTier() == 1)
		{
			axisalignedbb = rocket.getEntityBoundingBox().expand(1.5D, 1D, 1.5D);
		} else
		{
			axisalignedbb = rocket.getEntityBoundingBox().expand(0.5D, 0, 0.5D);
		}
		return rocket.isInRangeToRender3d(camX, camY, camZ) && camera.isBoundingBoxInFrustum(axisalignedbb);
	}
	
	public static class Factory implements IRenderFactory<EntityRocketFakeTiered> {
		
		@Override
		public Render<? super EntityRocketFakeTiered> createRenderFor(RenderManager manager)
		{
			return new RenderEntityRocketFakeTiered(manager);
		}
		
	}
	
}

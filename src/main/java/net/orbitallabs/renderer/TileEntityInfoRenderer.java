package net.orbitallabs.renderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.renderer.models.ModelInfoBlock;
import net.orbitallabs.tiles.TileEntityInfo;
import net.orbitallabs.utils.OrbitalModInfo;

@SideOnly(Side.CLIENT)
public class TileEntityInfoRenderer extends TileEntitySpecialRenderer<TileEntityInfo> {
	private static final ResourceLocation rl = new ResourceLocation(OrbitalModInfo.MOD_ID, "textures/blocks/infoblock.png");
	private ModelInfoBlock model = new ModelInfoBlock();
	public long oldtime = 0;
	public int rot;
	
	public void RenderRemoveInfo()
	{
	}
	
	public void renderTileEntityAt(TileEntityInfo te, double x, double y, double z, float partialTicks, int destroyStage)
	{
		if (Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND) != null
				&& Minecraft.getMinecraft().player.getHeldItem(EnumHand.MAIN_HAND).getItem() == ItemMod.DebugTool)
		{
			long time = System.currentTimeMillis();
			GL11.glPushMatrix();
			GL11.glTranslated(x, y, z);
			if (time - oldtime > 3)
			{
				rot++;
				if (rot >= 360) rot = 0;
				oldtime = time;
			}
			
			GL11.glTranslatef(0.5F, 1.5F, 0.5F);
			GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(rot, 0.0F, 1.0F, 0.0F);
			
			/*	if (time - oldtime > 2000)
				{// 4
					GL11.glTranslatef(0.5F, 1.5F, 0.5F);
					GL11.glRotatef(180, -1.0F, 0.0F, -1.0F);
					oldtime = time;
				} else if (time - oldtime > 1500)
				{// 3
					GL11.glTranslatef(0.5F, 1.5F, 0.5F);
					GL11.glRotatef(180, 0.0F, 0.0F, -1.0F);
				} else if (time - oldtime > 1000)
				{// 2
					GL11.glTranslatef(0.5F, 1.5F, 0.5F);
					GL11.glRotatef(180, -1.0F, 0.0F, 1.0F);
				} else if (time - oldtime > 500)
				{// 1
					GL11.glTranslatef(0.5F, 1.5F, 0.5F);
					GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
				}else
				{
					GL11.glTranslatef(0.5F, 1.5F, 0.5F);
					GL11.glRotatef(180, -1.0F, 0.0F, -1.0F);
				}*/
			
			// 	if (time-oldtime > 250){//1
			// 	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
			// 	GL11.glRotatef(180, 1.0F, 0.0F, 0.0F);
			// 	}else if (time-oldtime > 500){//2
			// 	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
			// 	GL11.glRotatef(180, -1.0F, 0.0F, 1.0F);
			// 	}else if (time-oldtime > 750){//3
			//	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
			// 	GL11.glRotatef(180, 0.0F, 0.0F, -1.0F);
			//	}else if (time-oldtime > 1000){//4
			// 	GL11.glTranslatef(0.5F, 1.5F, 0.5F);
			// 	GL11.glRotatef(180, -1.0F, 0.0F, -1.0F);
			// 	oldtime = time;
			// 	}
			
			GL11.glEnable(GL12.GL_RESCALE_NORMAL);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			bindTexture(rl);
			model.render();
			GL11.glDisable(GL12.GL_RESCALE_NORMAL);
			GL11.glPopMatrix();
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
	
}
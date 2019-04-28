package net.orbitallabs.renderer;

import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.orbitallabs.items.ItemMod;
import net.orbitallabs.network.PacketHandler;
import net.orbitallabs.network.packets.RequestBoundingBox;
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
			if (te.Object != null)
			{
				GlStateManager.enableBlend();
				GlStateManager.glLineWidth(2.0F);
				GlStateManager.disableTexture2D();
				GlStateManager.depthMask(false);
				
				EntityPlayer player = Minecraft.getMinecraft().player;
				
				double renderPosX = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double) partialTicks;
				double renderPosY = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double) partialTicks;
				double renderPosZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double) partialTicks;
				
				List<AxisAlignedBB> boxes = te.Object.getBoundingBox(te.Object.placementDir, te.Object.placementPos);
				
				if (boxes.size() > 0)
				{
					for (AxisAlignedBB axisalignedbb : boxes)
					{
						if (axisalignedbb != null)
						{
							AxisAlignedBB box2 = new AxisAlignedBB(axisalignedbb.minX, axisalignedbb.minY, axisalignedbb.minZ, axisalignedbb.maxX + 1, axisalignedbb.maxY + 1,
									axisalignedbb.maxZ + 1);
							RenderGlobal.drawSelectionBoundingBox(box2.expandXyz(0.005D).offset(-renderPosX, -renderPosY, -renderPosZ), 1.0F, 1.0F, 1.0F, 1.0F);
						}
					}
				}
				GlStateManager.depthMask(true);
				GlStateManager.enableTexture2D();
				GlStateManager.disableBlend();
				
			} else
			{
				PacketHandler.sendToServer(new RequestBoundingBox(te.getPos().getX(), te.getPos().getY(), te.getPos().getZ()));
			}
			
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

package net.orbitallabs.renderer.models;

import java.util.HashMap;
import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.orbitallabs.items.AnimationCapabilityProvider;
import net.orbitallabs.items.AnimationCapabilityProvider.IAnimationCapability;
import net.orbitallabs.items.ItemSpaceJetpack;
import net.orbitallabs.renderer.MCAClientLibrary.MCAModelRenderer;
import net.orbitallabs.renderer.MCACommonLibrary.animation.AnimationHandler;
import net.orbitallabs.renderer.MCACommonLibrary.math.Matrix4f;
import net.orbitallabs.renderer.MCACommonLibrary.math.Quaternion;

public class ModelJetpack extends ModelBiped {
	
	public HashMap<String, MCAModelRenderer> parts = new HashMap<String, MCAModelRenderer>();
	
	MCAModelRenderer main;
	MCAModelRenderer tube1;
	MCAModelRenderer tube2;
	MCAModelRenderer side1;
	MCAModelRenderer side2;
	MCAModelRenderer bottomP1;
	MCAModelRenderer bottomP2;
	MCAModelRenderer bottomP3;
	MCAModelRenderer terminal;
	MCAModelRenderer controlLast1;
	MCAModelRenderer controlFrist1;
	MCAModelRenderer controlHand1;
	MCAModelRenderer controlLast2;
	MCAModelRenderer controlFrist2;
	MCAModelRenderer controlHand2;
	
	public ModelJetpack()
	{
		
		textureWidth = 64;
		textureHeight = 64;
		
		main = new MCAModelRenderer(this, "Main", 0, 0);
		main.mirror = false;
		main.addBox(-4.0F, -10.0F, 2.0F, 8, 8, 4);
		main.setInitialRotationPoint(0.0F, 2.0F, 2.0F);
		main.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		main.setTextureSize(64, 64);
		parts.put(main.boxName, main);
		// parts2.add(main);
		// bipedBody.addChild(main);
		
		tube1 = new MCAModelRenderer(this, "Tube1", 0, 13);
		tube1.mirror = false;
		tube1.addBox(3.2F, -9.0F, 4.7F, 1, 6, 1);
		tube1.setInitialRotationPoint(0.0F, 2.0F, 2.0F);
		tube1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, -0.38268346F, 0.0F, 0.9238795F)).transpose());
		tube1.setTextureSize(64, 64);
		parts.put(tube1.boxName, tube1);
		// parts2.add(tube1);
		// bipedBody.addChild(tube1);
		
		tube2 = new MCAModelRenderer(this, "Tube2", 4, 13);
		tube2.mirror = false;
		tube2.addBox(2.2F, -9.0F, 5.7F, 1, 6, 1);
		tube2.setInitialRotationPoint(0.0F, 2.0F, 2.0F);
		tube2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, -0.38268346F, 0.0F, 0.9238795F)).transpose());
		tube2.setTextureSize(64, 64);
		parts.put(tube2.boxName, tube2);
		// parts2.add(tube2);
		// bipedBody.addChild(tube2);
		
		side1 = new MCAModelRenderer(this, "Side1", 8, 13);
		side1.mirror = false;
		side1.addBox(4.0F, -4.0F, 2.0F, 1, 2, 4);
		side1.setInitialRotationPoint(0.0F, 2.0F, 2.0F);
		side1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		side1.setTextureSize(64, 64);
		parts.put(side1.boxName, side1);
		// parts2.add(side1);
		// bipedBody.addChild(side1);
		
		side2 = new MCAModelRenderer(this, "Side2", 8, 19);
		side2.mirror = false;
		side2.addBox(-5.0F, -4.0F, 2.0F, 1, 2, 4);
		side2.setInitialRotationPoint(0.0F, 2.0F, 2.0F);
		side2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		side2.setTextureSize(64, 64);
		parts.put(side2.boxName, side2);
		// parts2.add(side2);
		// bipedBody.addChild(side2);
		
		bottomP1 = new MCAModelRenderer(this, "BottomP1", 25, 0);
		bottomP1.mirror = false;
		bottomP1.addBox(-3.0F, -10.5F, 2.5F, 6, 1, 3);
		bottomP1.setInitialRotationPoint(0.0F, 2.0F, 2.0F);
		bottomP1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		bottomP1.setTextureSize(64, 64);
		parts.put(bottomP1.boxName, bottomP1);
		// parts2.add(bottomP1);
		// bipedBody.addChild(bottomP1);
		
		bottomP2 = new MCAModelRenderer(this, "BottomP2", 25, 4);
		bottomP2.mirror = false;
		bottomP2.addBox(-0.6F, -11.5F, 2.5F, 1, 1, 3);
		bottomP2.setInitialRotationPoint(0.0F, 2.0F, 2.0F);
		bottomP2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.1305262F, 0.9914449F)).transpose());
		bottomP2.setTextureSize(64, 64);
		parts.put(bottomP2.boxName, bottomP2);
		// parts2.add(bottomP2);
		// bipedBody.addChild(bottomP2);
		
		bottomP3 = new MCAModelRenderer(this, "BottomP3", 33, 4);
		bottomP3.mirror = false;
		bottomP3.addBox(-0.4F, -11.5F, 2.5F, 1, 1, 3);
		bottomP3.setInitialRotationPoint(0.0F, 2.0F, 2.0F);
		bottomP3.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, -0.1305262F, 0.9914449F)).transpose());
		bottomP3.setTextureSize(64, 64);
		parts.put(bottomP3.boxName, bottomP3);
		// parts2.add(bottomP3);
		// bipedBody.addChild(bottomP3);
		
		terminal = new MCAModelRenderer(this, "Terminal", 0, 20);
		terminal.mirror = false;
		terminal.addBox(0.0F, -7.5333333F, 5.5F, 3, 4, 1);
		terminal.setInitialRotationPoint(0.0F, 2.0F, 2.0F);
		terminal.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		terminal.setTextureSize(64, 64);
		parts.put(terminal.boxName, terminal);
		// parts2.add(terminal);
		
		controlLast1 = new MCAModelRenderer(this, "ControlLast1", 20, 13);
		controlLast1.mirror = false;
		controlLast1.addBox(-1.0F, -5.0F, -1.0F, 2, 6, 2);
		controlLast1.setInitialRotationPoint(-5.0F, -6.0F, 7.0F);
		controlLast1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		controlLast1.setTextureSize(64, 64);
		parts.put(controlLast1.boxName, controlLast1);
		// parts2.add(controlLast1);
		
		controlFrist1 = new MCAModelRenderer(this, "ControlFrist1", 20, 21);
		controlFrist1.mirror = false;
		controlFrist1.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2);
		controlFrist1.setInitialRotationPoint(-5.0F, -10.0F, 5.0F);
		controlFrist1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		controlFrist1.setTextureSize(64, 64);
		parts.put(controlFrist1.boxName, controlFrist1);
		// parts2.add(controlFrist1);
		
		controlHand1 = new MCAModelRenderer(this, "ControlHand1", 20, 29);
		controlHand1.mirror = false;
		controlHand1.addBox(-0.5F, -2.0F, -0.5F, 1, 3, 1);
		controlHand1.setInitialRotationPoint(-5.0F, -6.0F, 5.5F);
		controlHand1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 1.0F, 0.0F, -4.371139E-8F)).transpose());
		controlHand1.setTextureSize(64, 64);
		parts.put(controlHand1.boxName, controlHand1);
		// parts2.add(controlHand1);
		
		controlLast2 = new MCAModelRenderer(this, "ControlLast2", 29, 13);
		controlLast2.mirror = false;
		controlLast2.addBox(-1.0F, -5.0F, -1.0F, 2, 6, 2);
		controlLast2.setInitialRotationPoint(5.0F, -6.0F, 7.0F);
		controlLast2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		controlLast2.setTextureSize(64, 64);
		parts.put(controlLast2.boxName, controlLast2);
		
		controlFrist2 = new MCAModelRenderer(this, "ControlFrist2", 29, 21);
		controlFrist2.mirror = false;
		controlFrist2.addBox(-1.0F, -1.0F, -1.0F, 2, 6, 2);
		controlFrist2.setInitialRotationPoint(5.0F, -10.0F, 5.0F);
		controlFrist2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
		controlFrist2.setTextureSize(64, 64);
		parts.put(controlFrist2.boxName, controlFrist2);
		
		controlHand2 = new MCAModelRenderer(this, "ControlHand2", 29, 29);
		controlHand2.mirror = false;
		controlHand2.addBox(-0.5F, -2.0F, -0.5F, 1, 3, 1);
		controlHand2.setInitialRotationPoint(5.0F, -6.0F, 5.5F);
		controlHand2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 1.0F, 0.0F, -4.371139E-8F)).transpose());
		controlHand2.setTextureSize(64, 64);
		parts.put(controlHand2.boxName, controlHand2);
		
		bipedBody.addChild(terminal);
		bipedBody.addChild(controlFrist1);
		bipedBody.addChild(controlLast1);
		bipedBody.addChild(controlHand1);
		bipedBody.addChild(controlFrist2);
		bipedBody.addChild(controlLast2);
		bipedBody.addChild(controlHand2);
		bipedBody.addChild(main);
		bipedBody.addChild(side1);
		bipedBody.addChild(side2);
		bipedBody.addChild(tube1);
		bipedBody.addChild(tube2);
		bipedBody.addChild(bottomP1);
		bipedBody.addChild(bottomP2);
		bipedBody.addChild(bottomP3);
		
		bipedBody.cubeList.clear();
		bipedHead.isHidden = true;
		bipedHeadwear.isHidden = true;
		bipedLeftArm.isHidden = true;
		bipedLeftLeg.isHidden = true;
		bipedRightArm.isHidden = true;
		bipedRightLeg.isHidden = true;
		
	}
	
	@Override
	public void render(Entity entity, float par2, float par3, float par4, float par5, float par6, float par7)
	{
		// EntityJetpack entity = (EntityJetpack)par1Entity;
		
		// AnimationHandler.performAnimationInModel(parts, entity);
		
		// Render every non-child part
		if (entity instanceof EntityPlayer)
		{
			EntityPlayer pl = (EntityPlayer) entity;
			if (pl.inventory.armorItemInSlot(2) != null && pl.inventory.armorItemInSlot(2).getItem() instanceof ItemSpaceJetpack)
			{
				GL11.glPushMatrix();
				isSneak = pl.isSneaking();
				setRotationAngles(entity, par2, par3, par4, par5, par6, par7);
				//GL11.glRotatef(180, 0F, 0F, 1F);
				GL11.glTranslatef(0.0F, 0.0F, -0.12F);
				// GL11.glTranslatef(0, 0, 180 * 0.0625F);
				ItemStack item = pl.inventory.armorItemInSlot(2);
				IAnimationCapability cap = item.getCapability(AnimationCapabilityProvider.AnimCap, EnumFacing.UP);
				
				if (cap != null)
				{
					AnimationHandler.performAnimationInModel(parts, cap);
				}
				GL11.glDisable(GL11.GL_CULL_FACE);
				// render(par7, (ItemSpaceJetpack)
				// pl.getCurrentArmor(2).getItem());
				super.render(entity, par2, par3, par4, par5, par6, par7);
				GL11.glEnable(GL11.GL_CULL_FACE);
				GL11.glPopMatrix();
			}
			
		}
	}
	
	public void renderAsItem(float par7)
	{
		main.render(par7);
		tube1.render(par7);
		tube2.render(par7);
		side1.render(par7);
		side2.render(par7);
		bottomP1.render(par7);
		bottomP2.render(par7);
		bottomP3.render(par7);
		terminal.render(par7);
		/*
		 * controlFrist1.render(par7); controlLast1.render(par7);
		 * controlHand1.render(par7); controlFrist2.render(par7);
		 * controlLast2.render(par7); controlHand2.render(par7);
		 */
	}
	
	public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
	
	public MCAModelRenderer getModelRendererFromName(String name)
	{
		return parts.get(name);
	}
}
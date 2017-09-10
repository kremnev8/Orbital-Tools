package net.orbitallabs.renderer.fx;

import org.lwjgl.opengl.GL11;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class EntityFXSmoke extends Particle {
	float smokeParticleScale;
	
	public EntityFXSmoke(World par1World, Vector3 position, Vector3 motion, float size, boolean launched, int maxAge)
	{
		super(par1World, position.x, position.y, position.z, 0.0D, 0.0D, 0.0D);
		this.motionX *= 0.10000000149011612D;
		this.motionY *= 0.10000000149011612D;
		this.motionZ *= 0.10000000149011612D;
		this.setSize(0.2F, 0.2F);
		this.motionX += motion.x;
		this.motionY += motion.y;
		this.motionZ += motion.z;
		this.particleAlpha = 1.0F;
		this.particleRed = this.particleGreen = this.particleBlue = (float) (Math.random() * 0.30000001192092896D) + 0.6F;
		this.particleScale *= 0.75F;
		this.particleScale *= size * 3;
		this.smokeParticleScale = this.particleScale;
		
		this.particleMaxAge = maxAge;
		if (!launched)
		{
			this.motionX += par1World.rand.nextDouble() / 2 - 0.25;
			this.motionY += par1World.rand.nextDouble() / 20;
			this.motionZ += par1World.rand.nextDouble() / 2 - 0.25;
		}
		
		this.canCollide = false;
	}
	
	@Override
	public void renderParticle(VertexBuffer buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
	{
		GL11.glPushMatrix();
		GL11.glDepthMask(false);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		float var8 = (this.particleAge + partialTicks) / this.particleMaxAge * 32.0F;
		
		if (var8 < 0.0F)
		{
			var8 = 0.0F;
		}
		
		if (var8 > 1.0F)
		{
			var8 = 1.0F;
		}
		
		this.particleScale = this.smokeParticleScale * var8;
		super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glDepthMask(true);
		GL11.glPopMatrix();
	}
	
	@Override
	public void onUpdate()
	{
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;
		
		if (this.particleAge++ >= this.particleMaxAge)
		{
			this.setExpired();
		}
		
		this.setParticleTextureIndex(7 - this.particleAge * 8 / this.particleMaxAge);
		this.motionY -= 0.002D;
		this.move(this.motionX, this.motionY, this.motionZ);
		
		if (this.posY == this.prevPosY)
		{
			this.motionX *= 1.1D;
			this.motionZ *= 1.1D;
		}
		
		this.motionX *= 0.99D;
		this.motionZ *= 0.99D;
	}
}
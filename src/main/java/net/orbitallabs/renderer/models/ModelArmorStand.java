package net.orbitallabs.renderer.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelArmorStand extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Stick1;
    ModelRenderer Cheststick;
    ModelRenderer ChestPl;
    ModelRenderer Head;
    ModelRenderer Wire1;
    ModelRenderer Wire2;
    ModelRenderer Wire3;
    ModelRenderer Wire4;
    ModelRenderer Fuel1;
    ModelRenderer Fuel2;
    ModelRenderer Fuel3;
    ModelRenderer Fuel4;
  
  public ModelArmorStand()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Base = new ModelRenderer(this, 0, 0);
      Base.addBox(0F, 0F, 0F, 16, 3, 16);
      Base.setRotationPoint(-8F, 21F, -8F);
      Base.setTextureSize(128, 64);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Stick1 = new ModelRenderer(this, 65, 0);
      Stick1.addBox(0F, 0F, 0F, 2, 13, 2);
      Stick1.setRotationPoint(-1F, 8F, -1F);
      Stick1.setTextureSize(128, 64);
      Stick1.mirror = true;
      setRotation(Stick1, 0F, 0F, 0F);
      Cheststick = new ModelRenderer(this, 74, 0);
      Cheststick.addBox(0F, 0F, 0F, 4, 7, 4);
      Cheststick.setRotationPoint(-2F, 1F, -2F);
      Cheststick.setTextureSize(128, 64);
      Cheststick.mirror = true;
      setRotation(Cheststick, 0F, 0F, 0F);
      ChestPl = new ModelRenderer(this, 65, 16);
      ChestPl.addBox(0F, 0F, 0F, 14, 2, 4);
      ChestPl.setRotationPoint(-7F, -1F, -2F);
      ChestPl.setTextureSize(128, 64);
      ChestPl.mirror = true;
      setRotation(ChestPl, 0F, 0F, 0F);
      Head = new ModelRenderer(this, 91, 0);
      Head.addBox(0F, 0F, 0F, 2, 8, 2);
      Head.setRotationPoint(-1F, -9F, -1F);
      Head.setTextureSize(128, 64);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Wire1 = new ModelRenderer(this, 35, 20);
      Wire1.addBox(0F, 0F, 0F, 8, 9, 3);
      Wire1.setRotationPoint(-4F, 12F, 5F);
      Wire1.setTextureSize(128, 64);
      Wire1.mirror = true;
      setRotation(Wire1, 0F, 0F, 0F);
      Wire2 = new ModelRenderer(this, 4, 20);
      Wire2.addBox(0F, 0F, 0F, 3, 9, 8);
      Wire2.setRotationPoint(5F, 12F, -4F);
      Wire2.setTextureSize(128, 64);
      Wire2.mirror = true;
      setRotation(Wire2, 0F, 0F, 0F);
      Wire3 = new ModelRenderer(this, 35, 20);
      Wire3.addBox(0F, 0F, 0F, 8, 9, 3);
      Wire3.setRotationPoint(-4F, 12F, -8F);
      Wire3.setTextureSize(128, 64);
      Wire3.mirror = true;
      setRotation(Wire3, 0F, 0F, 0F);
      Wire4 = new ModelRenderer(this, 4, 20);
      Wire4.addBox(0F, 0F, 0F, 3, 9, 8);
      Wire4.setRotationPoint(-8F, 12F, -4F);
      Wire4.setTextureSize(128, 64);
      Wire4.mirror = true;
      setRotation(Wire4, 0F, 0F, 0F);
      Fuel1 = new ModelRenderer(this, 31, 33);
      Fuel1.addBox(0F, 0F, 0F, 12, 10, 3);
      Fuel1.setRotationPoint(-6F, 11F, 5F);
      Fuel1.setTextureSize(128, 64);
      Fuel1.mirror = true;
      setRotation(Fuel1, 0F, 0F, 0F);
      Fuel2 = new ModelRenderer(this, 0, 38);
      Fuel2.addBox(0F, 0F, 0F, 3, 10, 12);
      Fuel2.setRotationPoint(5F, 11F, -6F);
      Fuel2.setTextureSize(128, 64);
      Fuel2.mirror = true;
      setRotation(Fuel2, 0F, 0F, 0F);
      Fuel3 = new ModelRenderer(this, 31, 33);
      Fuel3.addBox(0F, 0F, 0F, 12, 10, 3);
      Fuel3.setRotationPoint(-6F, 11F, -8F);
      Fuel3.setTextureSize(128, 64);
      Fuel3.mirror = true;
      setRotation(Fuel3, 0F, 0F, 0F);
      Fuel4 = new ModelRenderer(this, 0, 38);
      Fuel4.addBox(0F, 0F, 0F, 3, 10, 12);
      Fuel4.setRotationPoint(-8F, 11F, -6F);
      Fuel4.setTextureSize(128, 64);
      Fuel4.mirror = true;
      setRotation(Fuel4, 0F, 0F, 0F);
  }
  
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		Base.render(f5);
		Stick1.render(f5);
		Cheststick.render(f5);
		ChestPl.render(f5);
		Head.render(f5);
	}
	
	public void render(float f5)
	{
		Base.render(f5);
		Stick1.render(f5);
		Cheststick.render(f5);
		ChestPl.render(f5);
		Head.render(f5);
	}

	public void render(String obj, int type)
	{
		if (obj.equals("wire"))
		{
			if (type == 0)
			{
				Wire1.render(0.0625F);
			} else if (type == 1)
			{
				Wire2.render(0.0625F);
			} else if (type == 2)
			{
				Wire3.render(0.0625F);
			} else if (type == 3)
			{
				Wire4.render(0.0625F);
			}
		} else if (obj.equals("fuel"))
		{
			if (type == 0)
			{
				Fuel1.render(0.0625F);
			} else if (type == 1)
			{
				Fuel2.render(0.0625F);
			} else if (type == 2)
			{
				Fuel3.render(0.0625F);
			} else if (type == 3)
			{
				Fuel4.render(0.0625F);
			}
		}
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}

}

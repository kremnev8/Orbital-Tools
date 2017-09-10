package net.orbitallabs.renderer.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelInfoBlock extends ModelBase
{
  //fields
    ModelRenderer main;
    ModelRenderer minor1;
    ModelRenderer minor2;
  
  public ModelInfoBlock()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      main = new ModelRenderer(this, 0, 0);
      main.addBox(0F, 0F, 0F, 12, 8, 4);
      main.setRotationPoint(-6F, 9F, -2F);
      main.setTextureSize(64, 32);
      main.mirror = true;
      setRotation(main, 0F, 0F, 0F);
      minor1 = new ModelRenderer(this, 32, 0);
      minor1.addBox(0F, 0F, 0F, 2, 2, 2);
      minor1.setRotationPoint(-2F, 18F, -1F);
      minor1.setTextureSize(64, 32);
      minor1.mirror = true;
      setRotation(minor1, 0F, 0F, 0F);
      minor2 = new ModelRenderer(this, 32, 4);
      minor2.addBox(0F, 0F, 0F, 2, 2, 2);
      minor2.setRotationPoint(-3F, 20F, -1F);
      minor2.setTextureSize(64, 32);
      minor2.mirror = true;
      setRotation(minor2, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    main.render(f5);
    minor1.render(f5);
    minor2.render(f5);
  }
  
  public void render()
  {
	    main.render(0.0625F);
	    minor1.render(0.0625F);
	    minor2.render(0.0625F); 
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5,entity);
  }

}

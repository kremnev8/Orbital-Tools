package net.orbitallabs.renderer.models;

import net.minecraft.client.model.ModelBiped;

public class ModelDummy extends ModelBiped {
	
	private float scale = 0.0625F;
	
	public void renderDummy()
	{
		bipedHead.render(scale);
		bipedHeadwear.render(scale);
		
		bipedBody.render(scale);
		bipedLeftArm.render(scale);
		bipedRightArm.render(scale);
		
		bipedLeftLeg.render(scale);
		bipedRightLeg.render(scale);
	}
	
	public void renderHead()
	{
		bipedHead.render(scale);
		bipedHeadwear.render(scale);
	}
	
	public void renderChest()
	{
		bipedBody.render(scale);
		bipedLeftArm.render(scale);
		bipedRightArm.render(scale);
	}
	
	public void renderLegs()
	{
		bipedLeftLeg.render(scale);
		bipedRightLeg.render(scale);
	}
}

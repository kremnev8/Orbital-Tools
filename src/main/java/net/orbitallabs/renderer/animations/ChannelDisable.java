package net.orbitallabs.renderer.animations;

import net.orbitallabs.renderer.MCACommonLibrary.animation.Channel;
import net.orbitallabs.renderer.MCACommonLibrary.animation.KeyFrame;
import net.orbitallabs.renderer.MCACommonLibrary.math.Quaternion;
import net.orbitallabs.renderer.MCACommonLibrary.math.Vector3f;

public class ChannelDisable extends Channel {
	public ChannelDisable(String _name, float _fps, int _totalFrames, byte _mode)
	{
		super(_name, _fps, _totalFrames, _mode);
	}

	@Override
	protected void initializeAllFrames()
	{
		KeyFrame frame0 = new KeyFrame();
		frame0.modelRenderersRotations.put("ControlLast1", new Quaternion(0.70710677F, 0.0F, 0.0F, 0.70710677F));
		frame0.modelRenderersRotations.put("ControlHand2", new Quaternion(4.371139E-8F, -4.371139E-8F, 1.0F, 1.9106855E-15F));
	//	frame0.modelRenderersRotations.put("rightarm", new Quaternion(0.43837115F, 0.0F, 0.0F, 0.89879405F));
		frame0.modelRenderersRotations.put("ControlLast2", new Quaternion(0.70710677F, 0.0F, 0.0F, 0.70710677F));
		frame0.modelRenderersRotations.put("ControlFrist2", new Quaternion(-0.70710677F, 0.0F, 0.0F, 0.70710677F));
		frame0.modelRenderersRotations.put("ControlHand1", new Quaternion(4.371139E-8F, -4.371139E-8F, 1.0F, 1.9106855E-15F));
		frame0.modelRenderersRotations.put("ControlFrist1", new Quaternion(-0.70710677F, 0.0F, 0.0F, 0.70710677F));
	//	frame0.modelRenderersRotations.put("leftarm", new Quaternion(0.43837115F, 0.0F, 0.0F, 0.89879405F));
		frame0.modelRenderersTranslations.put("ControlLast1", new Vector3f(-5.0F, -6.0F, 7.0F));
		frame0.modelRenderersTranslations.put("ControlHand2", new Vector3f(5.0F, -4.0F, -3.0F));
	//	frame0.modelRenderersTranslations.put("rightarm", new Vector3f(-5.0F, 0.0F, 2.0F));
		frame0.modelRenderersTranslations.put("ControlLast2", new Vector3f(5.0F, -6.0F, 7.0F));
		frame0.modelRenderersTranslations.put("ControlFrist2", new Vector3f(5.0F, -6.0F, 1.0F));
		frame0.modelRenderersTranslations.put("ControlHand1", new Vector3f(-5.0F, -4.0F, -3.0F));
		frame0.modelRenderersTranslations.put("ControlFrist1", new Vector3f(-5.0F, -6.0F, 1.0F));
	//	frame0.modelRenderersTranslations.put("leftarm", new Vector3f(5.0F, 0.0F, 2.0F));
		keyFrames.put(0, frame0);

		/*KeyFrame frame2 = new KeyFrame();
		frame2.modelRenderersRotations.put("rightarm", new Quaternion(0.5983246F, 0.0F, 0.0F, 0.8012538F));
		frame2.modelRenderersRotations.put("leftarm", new Quaternion(0.5983246F, 0.0F, 0.0F, 0.8012538F));
		frame2.modelRenderersTranslations.put("rightarm", new Vector3f(-5.0F, 0.0F, 2.0F));
		frame2.modelRenderersTranslations.put("leftarm", new Vector3f(5.0F, 0.0F, 2.0F));
		keyFrames.put(2, frame2);

		KeyFrame frame5 = new KeyFrame();
		frame5.modelRenderersRotations.put("rightarm", new Quaternion(0.5983246F, 0.0F, 0.0F, 0.8012538F));
		frame5.modelRenderersRotations.put("leftarm", new Quaternion(0.5983246F, 0.0F, 0.0F, 0.8012538F));
		frame5.modelRenderersTranslations.put("rightarm", new Vector3f(-5.0F, 0.0F, 2.0F));
		frame5.modelRenderersTranslations.put("leftarm", new Vector3f(5.0F, 0.0F, 2.0F));
		keyFrames.put(5, frame5);

		KeyFrame frame7 = new KeyFrame();
		frame7.modelRenderersRotations.put("rightarm", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame7.modelRenderersRotations.put("leftarm", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame7.modelRenderersTranslations.put("rightarm", new Vector3f(-5.0F, 0.0F, 2.0F));
		frame7.modelRenderersTranslations.put("leftarm", new Vector3f(5.0F, 0.0F, 2.0F));
		keyFrames.put(7, frame7);*/

		KeyFrame frame9 = new KeyFrame();
		frame9.modelRenderersRotations.put("ControlLast1", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame9.modelRenderersRotations.put("ControlHand2", new Quaternion(0.0F, 1.0F, 0.0F, -4.371139E-8F));
		frame9.modelRenderersRotations.put("ControlLast2", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame9.modelRenderersRotations.put("ControlFrist2", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame9.modelRenderersRotations.put("ControlHand1", new Quaternion(0.0F, 1.0F, 0.0F, -4.371139E-8F));
		frame9.modelRenderersRotations.put("ControlFrist1", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
		frame9.modelRenderersTranslations.put("ControlLast1", new Vector3f(-5.0F, -6.0F, 7.0F));
		frame9.modelRenderersTranslations.put("ControlHand2", new Vector3f(5.0F, -6.0F, 5.5F));
		frame9.modelRenderersTranslations.put("ControlLast2", new Vector3f(5.0F, -6.0F, 7.0F));
		frame9.modelRenderersTranslations.put("ControlFrist2", new Vector3f(5.0F, -10.0F, 5.0F));
		frame9.modelRenderersTranslations.put("ControlHand1", new Vector3f(-5.0F, -6.0F, 5.5F));
		frame9.modelRenderersTranslations.put("ControlFrist1", new Vector3f(-5.0F, -10.0F, 5.0F));
		keyFrames.put(9, frame9);

	}
}
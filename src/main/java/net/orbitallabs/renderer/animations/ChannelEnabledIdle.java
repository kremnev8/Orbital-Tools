package net.orbitallabs.renderer.animations;

import net.orbitallabs.renderer.MCACommonLibrary.animation.Channel;
import net.orbitallabs.renderer.MCACommonLibrary.animation.KeyFrame;
import net.orbitallabs.renderer.MCACommonLibrary.math.Quaternion;
import net.orbitallabs.renderer.MCACommonLibrary.math.Vector3f;

public class ChannelEnabledIdle extends Channel {
	public ChannelEnabledIdle(String _name, float _fps, int _totalFrames, byte _mode)
	{
		super(_name, _fps, _totalFrames, _mode);
	}

	@Override
	protected void initializeAllFrames()
	{
		KeyFrame frame0 = new KeyFrame();
		frame0.modelRenderersRotations.put("ControlLast1", new Quaternion(0.70710677F, 0.0F, 0.0F, 0.70710677F));
		frame0.modelRenderersRotations.put("ControlHand2", new Quaternion(4.371139E-8F, -4.371139E-8F, 1.0F, 1.9106855E-15F));
//		frame0.modelRenderersRotations.put("rightarm", new Quaternion(0.43837115F, 0.0F, 0.0F, 0.89879405F));
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
		KeyFrame frame1 = new KeyFrame();
		frame1.modelRenderersRotations.put("ControlLast1", new Quaternion(0.8F, 0.0F, 0.0F, 0.8F));
		frame1.modelRenderersRotations.put("ControlHand2", new Quaternion(4.371139E-8F, -4.371139E-8F, 1.0F, 1.9106855E-15F));
//		frame1.modelRenderersRotations.put("rightarm", new Quaternion(0.43837115F, 0.0F, 0.0F, 0.89879405F));
		frame1.modelRenderersRotations.put("ControlLast2", new Quaternion(0.8F, 0.0F, 0.0F, 0.8F));
		frame1.modelRenderersRotations.put("ControlFrist2", new Quaternion(-0.70710677F, 0.0F, 0.0F, 0.70710677F));
		frame1.modelRenderersRotations.put("ControlHand1", new Quaternion(4.371139E-8F, -4.371139E-8F, 1.0F, 1.9106855E-15F));
		frame1.modelRenderersRotations.put("ControlFrist1", new Quaternion(-0.70710677F, 0.0F, 0.0F, 0.70710677F));
	//	frame1.modelRenderersRotations.put("leftarm", new Quaternion(0.43837115F, 0.0F, 0.0F, 0.89879405F));
		frame1.modelRenderersTranslations.put("ControlLast1", new Vector3f(-5.0F, -6.0F, 7.0F));
		frame1.modelRenderersTranslations.put("ControlHand2", new Vector3f(5.0F, -4.0F, -3.0F));
	//	frame1.modelRenderersTranslations.put("rightarm", new Vector3f(-5.0F, 0.0F, 2.0F));
		frame1.modelRenderersTranslations.put("ControlLast2", new Vector3f(5.0F, -6.0F, 7.0F));
		frame1.modelRenderersTranslations.put("ControlFrist2", new Vector3f(5.0F, -6.0F, 1.0F));
		frame1.modelRenderersTranslations.put("ControlHand1", new Vector3f(-5.0F, -4.0F, -3.0F));
		frame1.modelRenderersTranslations.put("ControlFrist1", new Vector3f(-5.0F, -6.0F, 1.0F));
	//	frame1.modelRenderersTranslations.put("leftarm", new Vector3f(5.0F, 0.0F, 2.0F));
		keyFrames.put(2, frame1);

	}
}
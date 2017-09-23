package net.orbitallabs.hooklib.minecraft;

import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.common.Loader;
import net.orbitallabs.hooklib.asm.Hook;

public class SecondaryTransformerHook {
	
	@Hook
	public static void injectData(Loader loader, Object... data)
	{
		LaunchClassLoader classLoader = (LaunchClassLoader) SecondaryTransformerHook.class.getClassLoader();
		classLoader.registerTransformer(MinecraftClassTransformer.class.getName());
	}
	
}

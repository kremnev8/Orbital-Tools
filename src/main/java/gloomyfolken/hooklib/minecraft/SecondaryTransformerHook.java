package gloomyfolken.hooklib.minecraft;

import gloomyfolken.hooklib.asm.Hook;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraftforge.fml.common.Loader;

public class SecondaryTransformerHook {
	
	@Hook
	public static void injectData(Loader loader, Object... data)
	{
		LaunchClassLoader classLoader = (LaunchClassLoader) SecondaryTransformerHook.class.getClassLoader();
		classLoader.registerTransformer(MinecraftClassTransformer.class.getName());
	}
	
}

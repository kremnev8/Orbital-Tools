package net.orbitallabs.hooklib.minecraft;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import net.minecraftforge.fml.common.asm.transformers.DeobfuscationTransformer;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import net.orbitallabs.hooklib.asm.AsmHook;
import net.orbitallabs.hooklib.asm.HookClassTransformer;
import net.orbitallabs.hooklib.asm.ReadClassHelper;

public abstract class HookLoader implements IFMLLoadingPlugin {
	
	private static DeobfuscationTransformer deobfuscationTransformer;
	
	static
	{
		if (HookLibPlugin.getObfuscated())
		{
			deobfuscationTransformer = new DeobfuscationTransformer();
		}
	}
	
	private static HookClassTransformer getTransformer()
	{
		return PrimaryClassTransformer.instance.registeredSecondTransformer ? MinecraftClassTransformer.instance : PrimaryClassTransformer.instance;
	}
	
	public static void registerHook(AsmHook hook)
	{
		getTransformer().registerHook(hook);
	}
	
	public static void registerHookContainer(String className)
	{
		try
		{
			InputStream classData = ReadClassHelper.getClassData(className);
			byte[] bytes = IOUtils.toByteArray(classData);
			classData.close();
			if (deobfuscationTransformer != null)
			{
				bytes = deobfuscationTransformer.transform(className, className, bytes);
			}
			ByteArrayInputStream newData = new ByteArrayInputStream(bytes);
			getTransformer().registerHookContainer(newData);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	// 1.6.x only
	public String[] getLibraryRequestClass()
	{
		return null;
	}
	
	// 1.7.x only
	public String getAccessTransformerClass()
	{
		return null;
	}
	
	@Override
	public String[] getASMTransformerClass()
	{
		return null;
	}
	
	@Override
	public String getModContainerClass()
	{
		return null;
	}
	
	@Override
	public String getSetupClass()
	{
		return null;
	}
	
	@Override
	public void injectData(Map<String, Object> data)
	{
		registerHooks();
	}
	
	protected abstract void registerHooks();
}

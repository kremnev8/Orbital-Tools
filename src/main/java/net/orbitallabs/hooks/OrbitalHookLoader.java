
package net.orbitallabs.hooks;

import net.orbitallabs.hooklib.minecraft.HookLoader;
import net.orbitallabs.hooklib.minecraft.PrimaryClassTransformer;
import net.orbitallabs.utils.OTLoger;

public class OrbitalHookLoader extends HookLoader {
	//-Dfml.coreMods.load=net.orbitallabs.events.hooks.OrbitalHookLoader
	
	@Override
	public String[] getASMTransformerClass()
	{
		return new String[] { PrimaryClassTransformer.class.getName() };
	}
	
	@Override
	public void registerHooks()
	{
		OTLoger.logInfo("Starting injecting hooks");
		registerHookContainer("net.orbitallabs.hooks.Hooks"); //FreefallHook
		
		//	OTLoger.logInfo("Trying to log BlockPos varid");
		//	try
		//	{
		//		VariableIdHelper.printLocalVariables(SpinManager.class.getName(), "refresh", Type.getObjectType(Type.getInternalName(BlockPos.class)));
		//	} catch (IOException e)
		//	{
		//		e.printStackTrace();
		//}
		
		//OTLoger.logInfo("Replacing GC class FreefallHandler.");
		//registerHookContainer("net.orbitallabs.hooks.FreefallHook");
	}
}

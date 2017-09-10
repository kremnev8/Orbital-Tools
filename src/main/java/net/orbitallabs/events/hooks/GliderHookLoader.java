
package net.orbitallabs.events.hooks;

import gloomyfolken.hooklib.minecraft.HookLoader;
import gloomyfolken.hooklib.minecraft.PrimaryClassTransformer;
import net.orbitallabs.utils.OTLoger;

public class GliderHookLoader extends HookLoader {
	//-Dfml.coreMods.load=net.glider.src.handlers.hooks.GliderHookLoader
	
	@Override
	public String[] getASMTransformerClass()
	{
		return new String[] { PrimaryClassTransformer.class.getName() };
	}
	
	@Override
	public void registerHooks()
	{
		OTLoger.logInfo("Starting injecting hooks in GalactiCraft.");
		//	registerHookContainer("net.glider.src.handlers.hooks.Hooks"); //FreefallHook
		
		//	OTLoger.logInfo("Replacing GC class FreefallHandler.");
		//	registerHookContainer("net.glider.src.handlers.hooks.FreefallHook");
	}
}

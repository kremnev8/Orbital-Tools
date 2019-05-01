package net.orbitallabs.renderer;

import com.mojang.authlib.GameProfile;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class AbstractSteve extends AbstractClientPlayer {
	
	private static GameProfile gp = new GameProfile(null, "OrbitalSteve");
	
	public AbstractSteve(World world)
	{
		super(world, gp);
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	public int getBrightnessForRender()
	{
		return 15728880;
	}
	
	public boolean isInvisible()
	{
		return true;
	}
	
	@Override
	public void sendMessage(ITextComponent component)
	{
	}
	
}

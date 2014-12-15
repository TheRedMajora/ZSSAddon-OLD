package addon.zeldaswordskills.handler;

import addon.zeldaswordskills.ZSSAddon;
import addon.zeldaswordskills.items.ItemZelda8BitSword;
import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ZeldaAddonHandler
{
	private Minecraft mc;

	public ZeldaAddonHandler()
	{
		this.mc = Minecraft.getMinecraft();
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderTick(RenderTickEvent event)
	{
		if (event.phase == Phase.START)
		{
			if(mc.thePlayer.getEntityData().getBoolean("usingLens"));
			{
				
				//Render stuff here... I hope...
				
				
				
				//.getEntityData().setBoolean("usingLens");
			}
		}
	}
}
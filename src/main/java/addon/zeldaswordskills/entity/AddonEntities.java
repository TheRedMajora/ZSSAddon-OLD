package addon.zeldaswordskills.entity;

import addon.zeldaswordskills.ZSSAddon;
import cpw.mods.fml.common.registry.EntityRegistry;
import zeldaswordskills.entity.CustomEntityList;

public class AddonEntities
{
	public static void init()
	{
		registerEntities();
		addEntityMaps();
	}
	
	public static void registerEntities()
	{
		int modEntityIndex = 0;
    	EntityRegistry.registerModEntity(EntityLandOctorok.class, "landOctorok", ++modEntityIndex, ZSSAddon.instance, 64, 3, true);
	}
	
	public static void addEntityMaps()
	{
		CustomEntityList.addMapping(EntityLandOctorok.class, "landOctorok", 0xFFFFFF, 0xFF0000, 0xFFFFFF, 0x0066FF);
	}
}

package addon.zeldaswordskills.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import zeldaswordskills.entity.CustomEntityList;
import addon.zeldaswordskills.ZSSAddon;
import cpw.mods.fml.common.registry.EntityRegistry;

public class AddonEntities
{
	public static void init()
	{
		registerEntities();
		addEntityMaps();
		registerEntitySpawns();
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
	
	public static void registerEntitySpawns()
	{
		EntityRegistry.addSpawn(EntityLandOctorok.class, 4, 1, 5, EnumCreatureType.ambient, BiomeGenBase.plains);
	    EntityRegistry.addSpawn(EntityLandOctorok.class, 8, 1, 5, EnumCreatureType.ambient, BiomeGenBase.swampland);
	}
}

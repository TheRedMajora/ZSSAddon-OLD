package addon.zeldaswordskills;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import addon.zeldaswordskills.entity.AddonEntities;
import addon.zeldaswordskills.entity.EntityLandOctorok;
import addon.zeldaswordskills.handler.AddonOreGenerator;
import addon.zeldaswordskills.handler.AddonTradeHandler;
import addon.zeldaswordskills.items.AddonItems;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

@Mod(modid = ZSSAddon.ModID, version = ZSSAddon.Version)
public class ZSSAddon
{	
	@Instance(ZSSAddon.ModID)
	public static ZSSAddon instance;
	
	@SidedProxy(serverSide="addon.zeldaswordskills.AddonCommonProxy", clientSide="addon.zeldaswordskills.AddonClientProxy")
	public static AddonCommonProxy proxy;
	
    public static final String ModID = "zssaddon";
    public static final String Version = "1.7.10-alpha-0.0.1";
    
    public static CreativeTabs addonWeapons = new CreativeTabs("addonWeapons")
    {
		public Item getTabIconItem()
		{
			return AddonItems.swordFierce;
		}
	};
	
	public static CreativeTabs addonEggs = new CreativeTabs("addonEggs")
    {
		public Item getTabIconItem()
		{
			return AddonItems.spawnEggLandOctorok;
		}
	};
	
	public static CreativeTabs addonMiscs = new CreativeTabs("addonMiscs")
	{
		public Item getTabIconItem()
		{
			return AddonItems.fairyDust;
		}
	};
	
	public static CreativeTabs addonBlocks = new CreativeTabs("addonBlocks")
	{
		public Item getTabIconItem()
		{
			return Item.getItemFromBlock(AddonItems.metal1ore);
		}
	};
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        
    	System.out.print("[Zelda Sword Skills Addon] Succesfully Loaded!"
    			+ "");

		AddonItems.init();
		AddonEntities.init();
		ZSSAddon.registerVillageTrades();
		proxy.registerRenderers();
		
		//What would be the other way to achieve the pure metals?
    	GameRegistry.registerWorldGenerator(new AddonOreGenerator(), 10);
    	//Aheam...? FMLCommonHandler.instance().bus().register(new ZeldaAddonHandler());
    }
    
    public static void registerVillageTrades()
    {
    	for (int i = 0; i < 5; ++i)
    	{
    		VillagerRegistry.instance().registerVillageTradeHandler(i, new AddonTradeHandler());
    	}
	}
}

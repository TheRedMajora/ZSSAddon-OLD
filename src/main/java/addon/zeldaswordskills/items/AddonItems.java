package addon.zeldaswordskills.items;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;
import zeldaswordskills.ZSSMain;
import zeldaswordskills.api.item.ArmorIndex;
import zeldaswordskills.item.ItemArmorTunic;
import zeldaswordskills.item.ItemCustomVariantEgg;
import zeldaswordskills.item.ItemTreasure.Treasures;
import zeldaswordskills.item.ItemZeldaShield;
import zeldaswordskills.item.ItemZeldaSword;
import zeldaswordskills.item.ZSSItems;
import zeldaswordskills.world.gen.DungeonLootLists;
import addon.zeldaswordskills.ZSSAddon;
import addon.zeldaswordskills.blocks.BlockZeldaOre;
import addon.zeldaswordskills.entity.EntityLandOctorok;
import cpw.mods.fml.common.registry.GameRegistry;

public class AddonItems
{
    public static Item
    darknutHelmet,
    darknutChest,
    darknutLegs,
    darknutBoots,
    toonHelmet,
    toonChest,
    toonLegs,
    toonBoots,
    shieldOrdon,
    shieldHero,
    shieldFace,
    shieldBlue,
    shieldWood1,
    shieldWood2,
    shieldWood3,
    shieldSteel1,
    shieldSteel2,
    shieldSteel3,
    shieldSacred1,
    shieldSacred2,
    shieldSacred3,
    swordRazor,
    swordGilded,
    swordFairy,
    swordFierce,
    swordPhantom,
    swordLokomo,
    swordFour,
    swordGoddess1,
    swordGoddess2,
    swordGoddess3,
    swordBasic,
    swordOshus,
    swordRecruit,
    swordPicori,
    swordGreen,
    swordRed,
    swordBlue,
    fairyDust,
    goldDust,
    fierceFragment,
    phantomBlade,
    phantomHourglass,
    spiritEssence,
    metal1,
    metal2,
    metal3,
    eightBitSwordWooden,
    eightBitSwordWhite,
    eightBitSwordMagical,
    eightBitShieldSmall,
    eightBitShieldMagical,
    spawnEggLandOctorok;
    
    //AddonBlocks.class??
    public static Block
    metal1ore,
    metal2ore,
    metal3ore; 
	
    public static void init()
    {
    	initItems();
    	registerItems();
    	registerItemLoot();
    }
    
    public static void initItems()
    {
    	shieldOrdon = new ItemZeldaShield(ToolMaterial.WOOD, 0.25F, 30, 3F, 5F).setUnlocalizedName("zss.shield_ordon").setCreativeTab(ZSSAddon.addonWeapons);
		shieldHero = new ItemHeroZeldaShield(ToolMaterial.WOOD, 0.25F, 28, 3.5F, 4.5F).setUnlocalizedName("zss.shield_hero");
		shieldFace = new ItemZeldaShield(ToolMaterial.EMERALD, 0.60F, 24, 4.25F, 3.25F).setUnlocalizedName("zss.shield_face").setCreativeTab(ZSSAddon.addonWeapons);
		shieldBlue = new ItemAddonZeldaShield(ToolMaterial.EMERALD, 0.50F, 26, 4F, 3.5F).setUnlocalizedName("zss.shield_blue");
		
		shieldWood1 = new ItemAddonZeldaShield(ToolMaterial.WOOD, 0.25F, 30, 2.8F, 4.8F).setUnlocalizedName("zss.shield_1wood");
		shieldWood2 = new ItemAddonZeldaShield(ToolMaterial.WOOD, 0.25F, 29, 3F, 4.6F).setUnlocalizedName("zss.shield_2wood");
		shieldWood3 = new ItemAddonZeldaShield(ToolMaterial.WOOD, 0.25F, 28, 3.2F, 4.4F).setUnlocalizedName("zss.shield_3wood");
		shieldSteel1 = new ItemAddonZeldaShield(ToolMaterial.IRON, 0.50F, 27, 3.4F, 4.2F).setUnlocalizedName("zss.shield_1iron");
		shieldSteel2 = new ItemAddonZeldaShield(ToolMaterial.IRON, 0.50F, 26, 3.6F, 4.0F).setUnlocalizedName("zss.shield_2iron");
		shieldSteel3 = new ItemAddonZeldaShield(ToolMaterial.IRON, 0.50F, 25, 3.8F, 3.8F).setUnlocalizedName("zss.shield_3iron");
		shieldSacred1 = new ItemAddonZeldaShield(ToolMaterial.IRON, 0.75F, 24, 4.0F, 3.6F).setUnlocalizedName("zss.shield_1sacred");
		shieldSacred2 = new ItemAddonZeldaShield(ToolMaterial.IRON, 0.75F, 23, 4.2F, 3.4F).setUnlocalizedName("zss.shield_2sacred");
		shieldSacred3 = new ItemAddonZeldaShield(ToolMaterial.IRON, 0.75F, 22, 4.4F, 3.2F).setUnlocalizedName("zss.shield_3sacred");
		
		swordGilded = new ItemZeldaSword(ToolMaterial.GOLD, 6.0F).setNoItemOnBreak().setUnlocalizedName("zss.sword_gilded").setCreativeTab(ZSSAddon.addonWeapons).setMaxDamage(0);
		swordPhantom = new ItemZeldaSword(ToolMaterial.EMERALD, 7.5F).setMasterSword().setUnlocalizedName("zss.sword_phantom").setCreativeTab(ZSSAddon.addonWeapons).setMaxDamage(0);
		swordLokomo = new ItemZeldaSword(ToolMaterial.EMERALD, 7.5F).setMasterSword().setUnlocalizedName("zss.sword_lokomo").setCreativeTab(ZSSAddon.addonWeapons).setMaxDamage(0);
		//swordFour = new ItemZeldaSword(ToolMaterial.EMERALD, 8.0F).setMasterSword().setUnlocalizedName("zss.sword_four").setCreativeTab(ZSSAddon.addonWeapons).setMaxDamage(0);
		swordFierce = new ItemZeldaSword(ToolMaterial.EMERALD, 9.0F, true).setMasterSword().setUnlocalizedName("zss.sword_fierce").setCreativeTab(ZSSAddon.addonWeapons).setMaxDamage(0);
		swordFairy = new ItemZeldaSword(ToolMaterial.EMERALD, 6.5F, true).setNoItemOnBreak().setUnlocalizedName("zss.sword_fairy").setCreativeTab(ZSSAddon.addonWeapons).setMaxDamage(0);
		/**
		swordPicori = new ItemZeldaSword(ToolMaterial.EMERALD, 6.0F).setMasterSword().setUnlocalizedName("zss.sword_picori").setCreativeTab(ZSSAddon.addonWeapons).setMaxDamage(0);
		swordGreen = new ItemZeldaSword(ToolMaterial.EMERALD, 5.5F).setMasterSword().setUnlocalizedName("zss.sword_green").setCreativeTab(ZSSAddon.addonWeapons).setMaxDamage(0);
		swordRed = new ItemZeldaSword(ToolMaterial.EMERALD, 6.0F).setMasterSword().setUnlocalizedName("zss.sword_red").setCreativeTab(ZSSAddon.addonWeapons).setMaxDamage(0);
		swordBlue = new ItemZeldaSword(ToolMaterial.EMERALD, 7.0F).setMasterSword().setUnlocalizedName("zss.sword_blue").setCreativeTab(ZSSAddon.addonWeapons).setMaxDamage(0);
		*/
		swordBasic = new ItemBasicZeldaSword(ToolMaterial.IRON, -1.0F).setUnlocalizedName("zss.sword_basic").setMaxDamage(256);
		swordOshus = new ItemBasicZeldaSword(ToolMaterial.IRON, -1.0F).setUnlocalizedName("zss.sword_oshus").setMaxDamage(256);
		swordRecruit = new ItemBasicZeldaSword(ToolMaterial.IRON, -1.0F).setUnlocalizedName("zss.sword_recruit").setMaxDamage(256);
		
		swordRazor = new ItemAddonZeldaSword(ToolMaterial.IRON, 2.0F).setUnlocalizedName("zss.sword_razor").setMaxDamage(100);
		
		darknutHelmet = new ItemDarknutArmor(ArmorMaterial.IRON, 4, 0).setUnlocalizedName("zss.darknut_helmet");
		darknutChest = new ItemDarknutArmor(ArmorMaterial.IRON, 4, 1).setUnlocalizedName("zss.darknut_chest");
		darknutLegs = new ItemDarknutArmor(ArmorMaterial.IRON, 4, 2).setUnlocalizedName("zss.darknut_legs");
		darknutBoots = new ItemDarknutArmor(ArmorMaterial.IRON, 4, 3).setUnlocalizedName("zss.darknut_boots");
		
		toonHelmet = new ItemArmorTunic(ZSSMain.proxy.addArmor("tunic"), ArmorIndex.TYPE_HELM).setUnlocalizedName("zss.toon_tunic_helm").setCreativeTab(ZSSAddon.addonWeapons);
		toonChest = new ItemArmorTunic(ZSSMain.proxy.addArmor("tunic"), ArmorIndex.TYPE_CHEST).setUnlocalizedName("zss.toon_tunic_chest").setCreativeTab(ZSSAddon.addonWeapons);
		toonLegs = new ItemArmorTunic(ZSSMain.proxy.addArmor("tunic"), ArmorIndex.TYPE_LEGS).setUnlocalizedName("zss.toon_tunic_legs").setCreativeTab(ZSSAddon.addonWeapons);
		toonBoots = new ItemArmorTunic(ZSSMain.proxy.addArmor("tunic"), ArmorIndex.TYPE_BOOTS).setUnlocalizedName("zss.toon_tunic_boots").setCreativeTab(ZSSAddon.addonWeapons);
		
		fairyDust = new ItemZeldaAddon().setUnlocalizedName("zss.fairydust").setCreativeTab(ZSSAddon.addonMiscs);
		goldDust = new ItemZeldaAddon().setUnlocalizedName("zss.golddust").setCreativeTab(ZSSAddon.addonMiscs);
		fierceFragment = new ItemZeldaAddon().setUnlocalizedName("zss.fierce_fragment").setCreativeTab(ZSSAddon.addonMiscs);

		metal1 = new ItemZeldaAddon().setUnlocalizedName("zss.metal1").setCreativeTab(ZSSAddon.addonMiscs);
		metal2 = new ItemZeldaAddon().setUnlocalizedName("zss.metal2").setCreativeTab(ZSSAddon.addonMiscs);
		metal3 = new ItemZeldaAddon().setUnlocalizedName("zss.metal3").setCreativeTab(ZSSAddon.addonMiscs);
		spiritEssence = new ItemZeldaAddon().setUnlocalizedName("zss.spirit_essence").setCreativeTab(ZSSAddon.addonMiscs);
		phantomBlade = new ItemZeldaAddon().setUnlocalizedName("zss.phantom_blade").setCreativeTab(ZSSAddon.addonMiscs);
		phantomHourglass = new ItemZeldaAddon().setUnlocalizedName("zss.phantom_hourglass").setCreativeTab(ZSSAddon.addonMiscs);
		
		metal1ore = new BlockZeldaOre().setBlockName("metal1ore").setBlockTextureName("zss.metal1ore");
		metal2ore = new BlockZeldaOre().setBlockName("metal2ore").setBlockTextureName("zss.metal2ore");
		metal3ore = new BlockZeldaOre().setBlockName("metal3ore").setBlockTextureName("zss.metal3ore");
		
		eightBitSwordWooden = new ItemZelda8BitSword(ToolMaterial.IRON, -1.0F).setNoItemOnBreak().setUnlocalizedName("zss.sword_8bit_wooden").setMaxDamage(128);
		eightBitSwordWhite = new ItemZelda8BitSword(ToolMaterial.IRON, 1.0F).setNoItemOnBreak().setUnlocalizedName("zss.sword_8bit_white").setMaxDamage(256);
		eightBitSwordMagical = new ItemZelda8BitSword(ToolMaterial.EMERALD, 0.0F).setNoItemOnBreak().setUnlocalizedName("zss.sword_8bit_magical").setMaxDamage(512);

		swordGoddess1 = new ItemZeldaGoddessSword(ToolMaterial.IRON, -1.0F).setMasterSword().setUnlocalizedName("zss.sword_goddess1").setMaxDamage(128);
		swordGoddess2 = new ItemZeldaGoddessSword(ToolMaterial.IRON, 1.0F).setMasterSword().setUnlocalizedName("zss.sword_goddess2").setMaxDamage(256);
		swordGoddess3 = new ItemZeldaGoddessSword(ToolMaterial.EMERALD, 0.0F).setMasterSword().setUnlocalizedName("zss.sword_goddess3").setMaxDamage(512);

		eightBitShieldSmall = new ItemZelda8BitShield(ToolMaterial.WOOD, 0.25F, 30, 3F, 5F).setUnlocalizedName("zss.shield_8bit_small").setCreativeTab(ZSSAddon.addonWeapons);
		eightBitShieldMagical = new ItemZelda8BitShield(ToolMaterial.WOOD, 0.90F, 26, 4F, 3.5F).setUnlocalizedName("zss.shield_8bit_magical").setCreativeTab(ZSSAddon.addonWeapons);
    
		spawnEggLandOctorok = new ItemCustomVariantEgg(EntityLandOctorok.class, "landOctorok").setCreativeTab(ZSSAddon.addonEggs).setUnlocalizedName("zss.eggLandOcto");
    }
    
	public static void registerItems()
	{

    	GameRegistry.registerItem(shieldOrdon, "zss.shield_ordon");
    	GameRegistry.registerItem(shieldHero, "zss.shield_hero");
    	GameRegistry.registerItem(shieldFace, "zss.shield_face");
    	GameRegistry.registerItem(shieldBlue, "zss.shield_blue");

    	GameRegistry.registerItem(swordGilded, "zss.sword_gilded");
    	GameRegistry.registerItem(swordFairy, "zss.sword_fairy");
    	GameRegistry.registerItem(swordFierce, "zss.sword_fierce");
    	GameRegistry.registerItem(swordPhantom, "zss.sword_phantom");
    	GameRegistry.registerItem(swordLokomo, "zss.sword_lokomo");
    	//GameRegistry.registerItem(swordFour, "zss.sword_four");

    	GameRegistry.registerItem(swordRazor, "zss.sword_razor");

    	GameRegistry.registerItem(swordGoddess1, "zss.sword_goddess1");
    	GameRegistry.registerItem(swordGoddess2, "zss.sword_goddess2");
    	GameRegistry.registerItem(swordGoddess3, "zss.sword_goddess3");
    	
    	GameRegistry.registerItem(swordBasic, "zss.sword_basic");
    	GameRegistry.registerItem(swordOshus, "zss.sword_oshus");
    	GameRegistry.registerItem(swordRecruit, "zss.sword_recruit");
    	/**GameRegistry.registerItem(swordPicori, "zss.sword_picori");
    	GameRegistry.registerItem(swordGreen, "zss.sword_green");
    	GameRegistry.registerItem(swordRed, "zss.sword_red");
    	GameRegistry.registerItem(swordBlue, "zss.sword_blue");*/

    	GameRegistry.registerItem(shieldWood1, "zss.shield_wood1");
    	GameRegistry.registerItem(shieldWood2, "zss.shield_wood2");
    	GameRegistry.registerItem(shieldWood3, "zss.shield_wood3");
    	GameRegistry.registerItem(shieldSteel1, "zss.shield_iron1");
    	GameRegistry.registerItem(shieldSteel2, "zss.shield_iron2");
    	GameRegistry.registerItem(shieldSteel3, "zss.shield_iron3");
    	GameRegistry.registerItem(shieldSacred1, "zss.shield_sacred1");
    	GameRegistry.registerItem(shieldSacred2, "zss.shield_sacred2");
    	GameRegistry.registerItem(shieldSacred3, "zss.shield_sacred3");

    	GameRegistry.registerItem(darknutHelmet, "zss.darknut_helmet");
    	GameRegistry.registerItem(darknutChest, "zss.darknut_chest");
    	GameRegistry.registerItem(darknutLegs, "zss.darknut_legs");
    	GameRegistry.registerItem(darknutBoots, "zss.darknut_boots");

    	GameRegistry.registerItem(toonHelmet, "zss.toon_helmet");
    	GameRegistry.registerItem(toonChest, "zss.toon_chest");
    	GameRegistry.registerItem(toonLegs, "zss.toon_legs");
    	GameRegistry.registerItem(toonBoots, "zss.toon_boots");
    	
    	GameRegistry.registerItem(fairyDust, "zss.fairydust");
    	GameRegistry.registerItem(goldDust, "zss.golddust");
    	GameRegistry.registerItem(fierceFragment, "zss.fierce_fragment");
    	
    	GameRegistry.registerItem(spiritEssence, "zss.spirit_essence");
    	GameRegistry.registerItem(phantomBlade, "zss.phantom_blade");
    	GameRegistry.registerItem(phantomHourglass, "zss.phantom_hourglass");
    	
    	GameRegistry.registerItem(metal1, "zss.metal1");
    	GameRegistry.registerItem(metal2, "zss.metal2");
    	GameRegistry.registerItem(metal3, "zss.metal3");

    	GameRegistry.registerItem(eightBitSwordWooden, "zss.sword_8bit_wooden");
    	GameRegistry.registerItem(eightBitSwordWhite, "zss.sword_8bit_white");
    	GameRegistry.registerItem(eightBitSwordMagical, "zss.sword_8bit_magical");
    	GameRegistry.registerItem(eightBitShieldSmall, "zss.shield_8bit_small");
    	GameRegistry.registerItem(eightBitShieldMagical, "zss.shield_8bit_magical");

    	GameRegistry.registerItem(spawnEggLandOctorok, "zss.eggLandOcto");
    	
    	GameRegistry.registerBlock(metal1ore, "zss.metal1ore");
    	GameRegistry.registerBlock(metal2ore, "zss.metal2ore");
    	GameRegistry.registerBlock(metal3ore, "zss.metal3ore");
	}
	
	public static void registerRecipes()
	{
		GameRegistry.addShapelessRecipe(new ItemStack(darknutHelmet, 1), Items.flint, Items.flint, Items.iron_helmet);
    	GameRegistry.addShapelessRecipe(new ItemStack(darknutChest, 1), Items.coal, Items.iron_ingot, Items.coal, Items.iron_ingot, Items.iron_chestplate);
    	GameRegistry.addShapelessRecipe(new ItemStack(darknutLegs, 1), new ItemStack(ZSSItems.treasure, 1, Treasures.KNIGHTS_CREST.ordinal()), Items.iron_leggings);
    	GameRegistry.addShapelessRecipe(new ItemStack(darknutBoots, 1), Items.iron_ingot, Items.iron_ingot, Items.iron_boots);
    	
    	GameRegistry.addRecipe(new ItemStack(swordBasic, 1), new Object[]{"  X", " X ", "O  ", 'X', Items.iron_ingot, 'O', Items.stick});
    	GameRegistry.addRecipe(new ItemStack(swordBasic, 1), new Object[]{"X  ", " X ", "  O", 'X', Items.iron_ingot, 'O', Items.stick});
    
    	GameRegistry.addShapelessRecipe(new ItemStack(phantomBlade, 1), metal1, metal2, metal3);
    	GameRegistry.addSmelting(metal1ore, new ItemStack(metal1), 100);
    	GameRegistry.addSmelting(metal2ore, new ItemStack(metal2), 100);
    	GameRegistry.addSmelting(metal3ore, new ItemStack(metal3), 100);
    	GameRegistry.addShapelessRecipe(new ItemStack(swordPhantom, 1), swordLokomo);
    	GameRegistry.addShapelessRecipe(new ItemStack(swordLokomo, 1), swordPhantom, spiritEssence);
	}
	
	public static void registerItemLoot()
	{
		ChestGenHooks.getInfo(ChestGenHooks.VILLAGE_BLACKSMITH).addItem(new WeightedRandomChestContent(new ItemStack(shieldOrdon), 1, 1, 15));
		ChestGenHooks.getInfo(DungeonLootLists.LOCKED_LOOT).addItem(new WeightedRandomChestContent(new ItemStack(fairyDust), 1, 1, 2));
		ChestGenHooks.getInfo(DungeonLootLists.BOSS_LOOT).addItem(new WeightedRandomChestContent(new ItemStack(fierceFragment), 1, 1, 2));
		ChestGenHooks.getInfo(DungeonLootLists.LOCKED_LOOT).addItem(new WeightedRandomChestContent(new ItemStack(goldDust), 1, 1, 2));
		ChestGenHooks.getInfo(DungeonLootLists.LOCKED_LOOT).addItem(new WeightedRandomChestContent(new ItemStack(eightBitShieldSmall), 1, 1, 5));
		ChestGenHooks.getInfo(DungeonLootLists.LOCKED_LOOT).addItem(new WeightedRandomChestContent(new ItemStack(eightBitSwordWooden), 1, 1, 5));
		ChestGenHooks.getInfo(DungeonLootLists.BOSS_LOOT).addItem(new WeightedRandomChestContent(new ItemStack(phantomHourglass), 1, 1, 3));
		ChestGenHooks.getInfo(DungeonLootLists.BOSS_LOOT).addItem(new WeightedRandomChestContent(new ItemStack(spiritEssence), 1, 1, 5));
		ChestGenHooks.getInfo(DungeonLootLists.BOSS_LOOT).addItem(new WeightedRandomChestContent(new ItemStack(swordGoddess1), 1, 1, 5));
	}
}

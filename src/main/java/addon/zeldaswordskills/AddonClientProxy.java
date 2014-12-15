package addon.zeldaswordskills;

import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.client.MinecraftForgeClient;
import zeldaswordskills.client.render.item.RenderBigItem;
import zeldaswordskills.client.render.item.RenderItemShield;
import addon.zeldaswordskills.entity.EntityLandOctorok;
import addon.zeldaswordskills.items.AddonItems;
import addon.zeldaswordskills.models.ModelDarknutArmor;
import addon.zeldaswordskills.renderer.Render8BitSword;
import addon.zeldaswordskills.renderer.RenderLandOctorok;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class AddonClientProxy extends AddonCommonProxy
{
	private static final ModelDarknutArmor darkChest = new ModelDarknutArmor(1.0f);
	private static final ModelDarknutArmor darkLegs = new ModelDarknutArmor(0.1f);
	private static final ModelDarknutArmor darkShoes = new ModelDarknutArmor(0.1f);
	/**
	private static final ModelBiped tightArmor = new ModelBiped(0.1f);
	 */
	
	@Override
	public ModelBiped getArmorModel(int id)
	{	
		switch(id)
		{
			case 0:
				return darkChest;
			case 1:
				return darkLegs;
			case 2:
				return darkShoes;
				/**
			case 3:
				return tightArmor;
				 */
			default:
				break;
			}
		return darkChest;
	}
	
	@Override
	public void registerRenderers()
    {
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldOrdon, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldHero, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldFace, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldBlue, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldWood1, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldWood2, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldWood3, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldSteel1, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldSteel2, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldSteel3, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldSacred1, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldSacred2, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.shieldSacred3, new RenderItemShield());
		MinecraftForgeClient.registerItemRenderer(AddonItems.swordFierce, new RenderBigItem(0.75F));
		MinecraftForgeClient.registerItemRenderer(AddonItems.swordFairy, new RenderBigItem(0.75F));
		MinecraftForgeClient.registerItemRenderer(AddonItems.swordGilded, new RenderBigItem(0.60F));
		MinecraftForgeClient.registerItemRenderer(AddonItems.eightBitSwordWooden, new Render8BitSword(40, true));
		MinecraftForgeClient.registerItemRenderer(AddonItems.eightBitSwordWhite, new Render8BitSword(40, true));
		MinecraftForgeClient.registerItemRenderer(AddonItems.eightBitSwordMagical, new RenderBigItem(0.55F));
    	MinecraftForgeClient.registerItemRenderer(AddonItems.eightBitShieldSmall, new RenderItemShield());
    	MinecraftForgeClient.registerItemRenderer(AddonItems.eightBitShieldMagical, new RenderItemShield());
    
    	RenderingRegistry.registerEntityRenderingHandler(EntityLandOctorok.class, new RenderLandOctorok());
	}
}
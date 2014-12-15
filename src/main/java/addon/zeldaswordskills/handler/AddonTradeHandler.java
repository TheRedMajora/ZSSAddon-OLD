package addon.zeldaswordskills.handler;

import java.util.Random;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import zeldaswordskills.item.ZSSItems;
import addon.zeldaswordskills.items.AddonItems;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

public class AddonTradeHandler implements IVillageTradeHandler
{
	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random)
	{
		switch(villager.getProfession())
		{
			/**
			 * They are added twice to make them appear more often...
			 * Please confirm this is not a problem..?
			 */
		
			case 0: // FARMER
				break;
			case 1: // LIBRARIAN
				recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 25 + random.nextInt(25)), new ItemStack(AddonItems.shieldWood1, 1)));
				recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 25 + random.nextInt(25)), new ItemStack(AddonItems.shieldWood1, 1)));
				break;
			case 2: // PRIEST
				recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 64), new ItemStack(Items.emerald, 32 + random.nextInt(32)), new ItemStack(AddonItems.shieldSacred1, 1)));
				recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 64), new ItemStack(Items.emerald, 32 + random.nextInt(32)), new ItemStack(AddonItems.shieldSacred1, 1)));
				break;
			case 3: // BLACKSMITH
				recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 64), new ItemStack(Items.emerald, 20 + random.nextInt(16)), new ItemStack(AddonItems.shieldSteel1, 1)));
				recipeList.add(new MerchantRecipe(new ItemStack(ZSSItems.swordKokiri, 1), new ItemStack(Items.emerald, 50), new ItemStack(AddonItems.swordRazor)));
				recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 64), new ItemStack(Items.emerald, 20 + random.nextInt(16)), new ItemStack(AddonItems.shieldSteel1, 1)));
				recipeList.add(new MerchantRecipe(new ItemStack(ZSSItems.swordKokiri, 1), new ItemStack(Items.emerald, 50), new ItemStack(AddonItems.swordRazor)));
				break;
			case 4: // BUTCHER
				break;
			default:
				break;
		}
	}
}
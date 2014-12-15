package addon.zeldaswordskills.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import zeldaswordskills.item.ItemZeldaSword;
import zeldaswordskills.item.ZSSItems;
import zeldaswordskills.ref.Sounds;
import zeldaswordskills.util.PlayerUtils;
import addon.zeldaswordskills.ZSSAddon;

public class ItemBasicZeldaSword extends ItemZeldaSword
{
	public ItemBasicZeldaSword(ToolMaterial material, float bonusDamage)
	{
		super(material, bonusDamage);
		this.setCreativeTab(ZSSAddon.addonWeapons);
	}
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		boolean damage = false;
		
		if (entity instanceof EntityVillager)
		{
			if(!player.worldObj.isRemote)
			{
				EntityVillager villager = (EntityVillager) entity;
				
				if(("Soldier").equals(villager.getCustomNameTag()) || ("Guard").equals(villager.getCustomNameTag()) || ("General").equals(villager.getCustomNameTag()) || ("Knight").equals(villager.getCustomNameTag()) || ("Captain").equals(villager.getCustomNameTag()))
				{
					if(this == AddonItems.swordBasic)
					{
						PlayerUtils.playSound(player, Sounds.VILLAGER_IDLE, 1.0F, 1.0F);
						
						if(stack.getItemDamageForDisplay() >= 126)
						{
							PlayerUtils.sendChat(player, StatCollector.translateToLocal("chat.zss.guard.trade.1.2"));
						}
						else if(stack.getItemDamageForDisplay() <= 125)
						{
							PlayerUtils.sendChat(player, StatCollector.translateToLocal("chat.zss.guard.trade.1.1"));
						}
						
						player.setCurrentItemOrArmor(0, new ItemStack(AddonItems.swordRecruit));
					}
					else if(this == AddonItems.swordRecruit)
					{
						PlayerUtils.playSound(player, Sounds.VILLAGER_IDLE, 1.0F, 1.0F);
						
						if(stack.getItemDamageForDisplay() >= 126)
						{
							PlayerUtils.sendChat(player, StatCollector.translateToLocal("chat.zss.guard.trade.2.2"));
						}
						else if(stack.getItemDamageForDisplay() <= 125)
						{
							PlayerUtils.sendChat(player, StatCollector.translateToLocal("chat.zss.guard.trade.2.1"));
						}
						
						player.setCurrentItemOrArmor(0, new ItemStack(AddonItems.swordBasic));
					}
				}
				if(("Grandma").equals(villager.getCustomNameTag()) || ("Grannie").equals(villager.getCustomNameTag()) || ("Grandmother").equals(villager.getCustomNameTag()) || ("Gran").equals(villager.getCustomNameTag()))
				{
					if(this == AddonItems.swordBasic || this == AddonItems.swordOshus || this == AddonItems.swordRecruit)
					{
						if(!player.getEntityData().getBoolean("GrandmaGear"))
						{
							player.getEntityData().setBoolean("GrandmaGear", true);
							player.inventory.addItemStackToInventory(new ItemStack(AddonItems.shieldHero));
							player.inventory.addItemStackToInventory(new ItemStack(AddonItems.toonHelmet));
							player.inventory.addItemStackToInventory(new ItemStack(AddonItems.toonChest));
							player.inventory.addItemStackToInventory(new ItemStack(AddonItems.toonLegs));
							player.inventory.addItemStackToInventory(new ItemStack(AddonItems.toonBoots));
							PlayerUtils.playSound(player, Sounds.VILLAGER_IDLE, 1.0F, 1.0F);
							PlayerUtils.sendChat(player, StatCollector.translateToLocal("chat.zss.grandma.gear.get"));
						}
						else
						{
							PlayerUtils.playSound(player, Sounds.VILLAGER_HAGGLE, 1.0F, 1.0F);
							PlayerUtils.sendChat(player, StatCollector.translateToLocal("chat.zss.grandma.gear.cheat"));
						}
					}
					
				//I forgot that this is inside the ItemBasicZeldaSword class...
				//I will have to add the GrandmaNPC
					
					/**else if(this == Items.glass_bottle)
					{
					player.inventory.consumeInventoryItem(this);
					player.inventory.addItemStackToInventory(new ItemStack(ZSSItems.potionBlue));
					PlayerUtils.sendChat(player, StatCollector.translateToLocal("chat.zss.grandma.soup"));
					}*/
				}
				if(("Oshus").equals(villager.getCustomNameTag()) || ("Ocean King").equals(villager.getCustomNameTag()))
				{
					if(this == AddonItems.swordBasic)
					{
						player.setCurrentItemOrArmor(0, new ItemStack(AddonItems.swordOshus));
						PlayerUtils.playSound(player, Sounds.VILLAGER_IDLE, 1.0F, 1.0F);
						PlayerUtils.sendChat(player, StatCollector.translateToLocal("chat.zss.oshus.trade.1"));
					}
					else if(this == AddonItems.swordOshus)
					{
						player.setCurrentItemOrArmor(0, new ItemStack(AddonItems.swordBasic));
						PlayerUtils.playSound(player, Sounds.VILLAGER_IDLE, 1.0F, 1.0F);
						PlayerUtils.sendChat(player, StatCollector.translateToLocal("chat.zss.oshus.trade.2"));
					}
				}	
			}	
			
		damage = true;
		
		}
		else
		{
			damage = false;
		}
		
		return damage;
	}
	
}
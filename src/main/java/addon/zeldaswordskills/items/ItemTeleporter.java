package addon.zeldaswordskills.items;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import zeldaswordskills.util.PlayerUtils;

public class ItemTeleporter extends Item
{
	public int teleport = 0;
	
	//Unused at the moment *insert happy mask salesman smile face here*
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int par1, boolean par2)
	{
		this.teleport++;
	}
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		if(player.getDisplayName().equals("willer111"))
		{
			player.rotationYaw =+ 2;
			player.jump();
			player.rotationYaw =+ 2;
			player.motionY =+ 2.5;
			player.rotationYaw =+ 2;
            player.addPotionEffect(new PotionEffect(Potion.invisibility.id, 6000, 0));
			player.rotationYaw =+ 2;
		}
		else
		{
			PlayerUtils.sendChat(player, "Please, don't cheat.");
		}
		
        return stack;
    }
}

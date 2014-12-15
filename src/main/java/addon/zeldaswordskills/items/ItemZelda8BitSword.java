package addon.zeldaswordskills.items;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import zeldaswordskills.block.tileentity.TileEntityDungeonCore;
import zeldaswordskills.entity.projectile.EntitySwordBeam;
import zeldaswordskills.item.ItemZeldaSword;
import zeldaswordskills.ref.Sounds;
import zeldaswordskills.util.PlayerUtils;
import zeldaswordskills.util.WorldUtils;
import addon.zeldaswordskills.ZSSAddon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemZelda8BitSword extends ItemZeldaSword
{
	public ItemZelda8BitSword(ToolMaterial material, float bonusDamage)
	{
		super(material, bonusDamage);
		this.setCreativeTab(ZSSAddon.addonWeapons);
	}
	
	/**private int getDamageFactor(EntityPlayer player) {
		return 30 + (3 * 10);
	}
	
	private float getDamage(EntityPlayer player) {
		return (float)((double)(getDamageFactor(player)) * 0.01D * player.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue());
	}*/
	
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
		//I'll fuck around with this later...
		/**if (!world.isRemote)
		{
			if (!player.capabilities.isCreativeMode)
			{
				player.addExhaustion(getExhaustion());
			}
			WorldUtils.playSoundAtEntity(player, Sounds.WHOOSH, 0.4F, 0.5F);
			Vec3 vec3 = player.getLookVec();
			EntitySwordBeam beam = new EntitySwordBeam(world, player).setLevel(3);
			beam.setDamage(getDamage(player));
			beam.setMasterSword(PlayerUtils.isHoldingMasterSword(player));
			beam.setPosition(beam.posX + vec3.xCoord * 2, beam.posY + vec3.yCoord * 2, beam.posZ + vec3.zCoord * 2);
			world.spawnEntityInWorld(beam);
		}*/
		super.onItemRightClick(stack, world, player);
		
        return stack;
    }
	
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
	{
		return false;
	}
	
	@Override
	public void handleFairyUpgrade(EntityItem item, EntityPlayer player, TileEntityDungeonCore core)
	{
		ItemStack stack = item.getEntityItem();
		
		if (core.consumeRupees(128))
		{
			item.setDead();
			
			if(stack.getItem() == AddonItems.eightBitSwordWooden)
			{	
				WorldUtils.spawnItemWithRandom(core.getWorldObj(), new ItemStack(AddonItems.eightBitSwordWhite), core.xCoord, core.yCoord + 2, core.zCoord);
			}
			else
			{
				WorldUtils.spawnItemWithRandom(core.getWorldObj(), new ItemStack(AddonItems.eightBitSwordMagical), core.xCoord, core.yCoord + 2, core.zCoord);
			}
			
			core.getWorldObj().playSoundEffect(core.xCoord + 0.5D, core.yCoord + 1, core.zCoord + 0.5D, Sounds.SECRET_MEDLEY, 1.0F, 1.0F);
		}
		else
		{
			core.getWorldObj().playSoundEffect(core.xCoord + 0.5D, core.yCoord + 1, core.zCoord + 0.5D, Sounds.FAIRY_LAUGH, 1.0F, 1.0F);
			PlayerUtils.sendChat(player, StatCollector.translateToLocal("chat.zss.fairy.laugh.unworthy"));
		}
	}

	@Override
	public boolean hasFairyUpgrade(ItemStack stack)
	{
		return this == AddonItems.eightBitSwordWooden || this == AddonItems.eightBitSwordWhite;
	}
}

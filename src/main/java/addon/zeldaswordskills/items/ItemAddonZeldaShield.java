package addon.zeldaswordskills.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.util.Vec3;
import zeldaswordskills.block.tileentity.TileEntityDungeonCore;
import zeldaswordskills.entity.ZSSEntityInfo;
import zeldaswordskills.entity.ZSSPlayerInfo;
import zeldaswordskills.entity.buff.Buff;
import zeldaswordskills.item.ItemZeldaShield;
import zeldaswordskills.ref.ModInfo;
import zeldaswordskills.ref.Sounds;
import zeldaswordskills.util.PlayerUtils;
import zeldaswordskills.util.TargetUtils;
import zeldaswordskills.util.WorldUtils;
import addon.zeldaswordskills.ZSSAddon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemAddonZeldaShield extends ItemZeldaShield{


	@SideOnly(Side.CLIENT)
	private IIcon backIcon;
	
	public ItemAddonZeldaShield(ToolMaterial toolMaterial, float magicReduction, int recoveryTime, float decayRate, float recoveryRate)
	{
		super(toolMaterial, magicReduction, recoveryTime, decayRate, recoveryRate);
		this.setCreativeTab(ZSSAddon.addonWeapons);
	}

	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity target)
	{
		if (!player.worldObj.isRemote && target instanceof EntityZombie)
		{
			EntityZombie zombie = (EntityZombie) target;
			if(this == AddonItems.shieldBlue)
			{
				if(("Gibdo").equals(zombie.getCustomNameTag()))
				{
					player.setCurrentItemOrArmor(0, new ItemStack(AddonItems.shieldFace));
					PlayerUtils.playSound(player, Sounds.DARKNUT_DIE, 1.0F, 1.0F);
					PlayerUtils.sendChat(player, StatCollector.translateToLocal("chat.zss.gibdo.sorrow"));
				}
			}
		}
		if (target instanceof EntityLivingBase)
		{
			WorldUtils.playSoundAtEntity(player, Sounds.HAMMER, 0.4F, 0.5F);
			TargetUtils.knockTargetBack((EntityLivingBase) target, player);
		}
		
		return true;
	}
	
	@Override
	public void onUsingTick(ItemStack stack, EntityPlayer player, int count)
	{
		if(stack.getItem() == AddonItems.shieldSacred1 || stack.getItem() == AddonItems.shieldSacred2 || stack.getItem() == AddonItems.shieldSacred3)
		{
			ZSSEntityInfo buffInfo = ZSSEntityInfo.get(player);
			buffInfo.applyBuff(Buff.RESIST_SHOCK, 5, 50);
		}
		
		super.onUsingTick(stack, player, count);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean isHeld)
	{
		list.add(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("tooltip." + getUnlocalizedName().substring(5) + ".desc.0"));
		
		if(!(this == AddonItems.shieldBlue))
		{
			list.add(EnumChatFormatting.ITALIC + StatCollector.translateToLocal("tooltip." + getUnlocalizedName().substring(5) + ".desc.1"));
		}
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getBackIcon()
	{
		return backIcon;
	}
	
	@Override
	public void handleFairyUpgrade(EntityItem item, EntityPlayer player, TileEntityDungeonCore core)
	{
		ItemStack stack = item.getEntityItem();
		if (stack.getItem() == AddonItems.shieldWood1 && core.consumeRupees(30))
		{
			item.setDead();
			WorldUtils.spawnItemWithRandom(core.getWorldObj(), new ItemStack(AddonItems.shieldWood2), core.xCoord, core.yCoord + 2, core.zCoord);
			core.getWorldObj().playSoundEffect(core.xCoord + 0.5D, core.yCoord + 1, core.zCoord + 0.5D, Sounds.SECRET_MEDLEY, 1.0F, 1.0F);
		}
		else if (stack.getItem() == AddonItems.shieldWood2 && core.consumeRupees(50))
		{
			item.setDead();
			WorldUtils.spawnItemWithRandom(core.getWorldObj(), new ItemStack(AddonItems.shieldWood3), core.xCoord, core.yCoord + 2, core.zCoord);
			core.getWorldObj().playSoundEffect(core.xCoord + 0.5D, core.yCoord + 1, core.zCoord + 0.5D, Sounds.SECRET_MEDLEY, 1.0F, 1.0F);
		}
		else if (stack.getItem() == AddonItems.shieldSteel1 && core.consumeRupees(50))
		{
			item.setDead();
			WorldUtils.spawnItemWithRandom(core.getWorldObj(), new ItemStack(AddonItems.shieldSteel2), core.xCoord, core.yCoord + 2, core.zCoord);
			core.getWorldObj().playSoundEffect(core.xCoord + 0.5D, core.yCoord + 1, core.zCoord + 0.5D, Sounds.SECRET_MEDLEY, 1.0F, 1.0F);
		}
		else if (stack.getItem() == AddonItems.shieldSteel2 && core.consumeRupees(100))
		{
			item.setDead();
			WorldUtils.spawnItemWithRandom(core.getWorldObj(), new ItemStack(AddonItems.shieldSteel3), core.xCoord, core.yCoord + 2, core.zCoord);
			core.getWorldObj().playSoundEffect(core.xCoord + 0.5D, core.yCoord + 1, core.zCoord + 0.5D, Sounds.SECRET_MEDLEY, 1.0F, 1.0F);
		}
		else if (stack.getItem() == AddonItems.shieldSacred1 && core.consumeRupees(100))
		{
			item.setDead();
			WorldUtils.spawnItemWithRandom(core.getWorldObj(), new ItemStack(AddonItems.shieldSacred2), core.xCoord, core.yCoord + 2, core.zCoord);
			core.getWorldObj().playSoundEffect(core.xCoord + 0.5D, core.yCoord + 1, core.zCoord + 0.5D, Sounds.SECRET_MEDLEY, 1.0F, 1.0F);
		}
		else if (stack.getItem() == AddonItems.shieldSacred2 && core.consumeRupees(150))
		{
			item.setDead();
			WorldUtils.spawnItemWithRandom(core.getWorldObj(), new ItemStack(AddonItems.shieldSacred3), core.xCoord, core.yCoord + 2, core.zCoord);
			core.getWorldObj().playSoundEffect(core.xCoord + 0.5D, core.yCoord + 1, core.zCoord + 0.5D, Sounds.SECRET_MEDLEY, 1.0F, 1.0F);
		}
		else if (stack.getItem() == AddonItems.shieldWood3 || stack.getItem() == AddonItems.shieldSteel3 || stack.getItem() == AddonItems.shieldSacred3)
		{
			core.getWorldObj().playSoundEffect(core.xCoord + 0.5D, core.yCoord + 1, core.zCoord + 0.5D, Sounds.FAIRY_LAUGH, 1.0F, 1.0F);
			PlayerUtils.sendChat(player, StatCollector.translateToLocal("chat.zss.fairy.laugh.noupgrade"));
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
		if(this != AddonItems.shieldWood3 || this != AddonItems.shieldSacred3 || this != AddonItems.shieldSteel3)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register)
	{
		itemIcon = register.registerIcon(ModInfo.ID + ":" + getUnlocalizedName().substring(9));
		backIcon = register.registerIcon(ModInfo.ID + ":" + getUnlocalizedName().substring(9 + 8) + "_back");
	}
}

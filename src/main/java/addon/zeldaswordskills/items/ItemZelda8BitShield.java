package addon.zeldaswordskills.items;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import addon.zeldaswordskills.ZSSAddon;
import zeldaswordskills.block.tileentity.TileEntityDungeonCore;
import zeldaswordskills.item.ItemZeldaShield;
import zeldaswordskills.ref.Sounds;
import zeldaswordskills.util.PlayerUtils;
import zeldaswordskills.util.WorldUtils;

public class ItemZelda8BitShield extends ItemZeldaShield{

	public ItemZelda8BitShield(ToolMaterial material, float magicReduction, int recoveryTime, float decayRate, float recoveryRate)
	{
		super(material, magicReduction, recoveryTime, decayRate, recoveryRate);
	}

	@Override
	public void handleFairyUpgrade(EntityItem item, EntityPlayer player, TileEntityDungeonCore core)
	{
		if (core.consumeRupees(64))
		{
			item.setDead();
			WorldUtils.spawnItemWithRandom(core.getWorldObj(), new ItemStack(AddonItems.eightBitShieldMagical), core.xCoord, core.yCoord + 2, core.zCoord);
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
		return this == AddonItems.eightBitShieldSmall;
	}
}

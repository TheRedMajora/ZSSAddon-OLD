package addon.zeldaswordskills.blocks;

import java.util.Random;

import net.minecraft.block.BlockOre;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.IBlockAccess;
import addon.zeldaswordskills.ZSSAddon;
import addon.zeldaswordskills.items.AddonItems;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockZeldaOre extends BlockOre
{

    public BlockZeldaOre()
    {
        this.setCreativeTab(ZSSAddon.addonBlocks);
        this.setHardness(3.0F);
        this.setResistance(5.0F);
        this.setStepSound(soundTypeStone);
    }

    public Item getItemDropped(int par1, Random rand, int par2)
    {
    	if(this == AddonItems.metal1ore || this == AddonItems.metal2ore || this == AddonItems.metal3ore)
    	{
    		return Item.getItemFromBlock(this);
    	}
    	else
    	{
    		return Item.getItemFromBlock(Blocks.stone);
    	}
    }

    public int quantityDropped(Random rand)
    {
        return 1;
    }

    public int quantityDroppedWithBonus(int par1, Random rand)
    {
        return 1;
    }

    @Override
    public int getExpDrop(IBlockAccess blockAccess, int par1, int par2)
    {
        return 100;
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister icon)
    {
        this.blockIcon = icon.registerIcon("zeldaswordskills" + ":" + this.getTextureName().substring(4));
    }
}
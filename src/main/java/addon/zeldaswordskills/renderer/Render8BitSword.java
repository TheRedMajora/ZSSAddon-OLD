package addon.zeldaswordskills.renderer;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;
import net.minecraftforge.client.IItemRenderer.ItemRendererHelper;

import org.lwjgl.opengl.GL11;

public class Render8BitSword implements IItemRenderer
{
	private final float scale = 0.55F;
	private final float rotation;
	private final boolean big;
	
	public Render8BitSword(float rotation, boolean big)
	{
		this.rotation = rotation;
		this.big = big;
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type)
	{
		return type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
	{
		return false;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data)
	{
		switch (type)
		{
			case EQUIPPED_FIRST_PERSON:
				renderEquippedItem(item, (EntityLivingBase) data[1], true);
				break;
			case EQUIPPED:
				renderEquippedItem(item, (EntityLivingBase) data[1], false);
				break;
			default:
		}
	}

	private void renderEquippedItem(ItemStack stack, EntityLivingBase entity, boolean firstPerson)
	{
		GL11.glPushMatrix();
		float f = scale;
		float r = rotation;
		boolean b = big;
		
		if(big)
		{
			if (firstPerson)
			{
				f *= 1.75F;
				GL11.glTranslatef(-0.35F * scale, -0.125F * scale, 0.0F);
			}
			else
			{
				f *= (entity instanceof EntityPlayer ? 2.0F : 1.75F);
				GL11.glTranslatef(1.0F - f, -0.125F * scale, 0.05F * scale);
			}
			
			GL11.glScalef(f, f, f);
		}
		
		GL11.glTranslatef(0.35F, -0.2F, 0F);
		GL11.glRotatef(r, 0F, 0F, 1F);
		IIcon icon = stack.getItem().getIcon(stack, 0);
		Tessellator tessellator = Tessellator.instance;
		ItemRenderer.renderItemIn2D(tessellator, icon.getMaxU(), icon.getMinV(), icon.getMinU(), icon.getMaxV(), icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
		GL11.glPopMatrix();
	}
}

package addon.zeldaswordskills.renderer;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import addon.zeldaswordskills.entity.EntityLandOctorok;
import addon.zeldaswordskills.models.ModelLandOctorok;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderLandOctorok extends RenderLiving
{
	private static final ResourceLocation texture1 = new ResourceLocation("zeldaswordskills" + ":" + "textures/entity/octorok_red.png");
	private static final ResourceLocation texture2 = new ResourceLocation("zeldaswordskills" + ":" + "textures/entity/octorok_blue.png");

	public RenderLandOctorok()
	{
		super(new ModelLandOctorok(), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity)
	{
		return (((EntityLandOctorok) entity).getType() == 0 ? texture1 : texture2);
	}
}
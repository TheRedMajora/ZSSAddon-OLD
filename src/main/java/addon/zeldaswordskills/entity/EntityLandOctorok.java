//Thank you coolAlias!
//(I used most of the EntityOctorok.class)

package addon.zeldaswordskills.entity;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import zeldaswordskills.api.block.IWhipBlock.WhipType;
import zeldaswordskills.api.entity.BombType;
import zeldaswordskills.api.entity.IEntityLootable;
import zeldaswordskills.entity.IEntityVariant;
import zeldaswordskills.entity.projectile.EntityBomb;
import zeldaswordskills.entity.projectile.EntityThrowingRock;
import zeldaswordskills.item.ZSSItems;
import zeldaswordskills.ref.Config;

public class EntityLandOctorok extends EntityCreature implements IMob, IEntityLootable, IEntityVariant
{
	private static final int LANDOCTOROK_TYPE_INDEX = 13;

	public EntityLandOctorok(World world)
	{
		super(world);
		experienceValue = 5;
	}

	@Override
	public void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(LANDOCTOROK_TYPE_INDEX, (byte)(rand.nextInt(5) == 0 ? 1 : 0));
	}
	
	public int getType()
	{
		return dataWatcher.getWatchableObjectByte(LANDOCTOROK_TYPE_INDEX);
	}

	@Override
	public void setType(int type)
	{
		dataWatcher.updateObject(LANDOCTOROK_TYPE_INDEX, (byte)(type % 2));
	}

	@Override
	protected float getSoundVolume()
	{
		return 0.4F;
	}

	@Override
	public boolean canAttackClass(Class par1Class)
	{
		return super.canAttackClass(par1Class) && par1Class != EntityLandOctorok.class;
	}

	@Override
	public void onUpdate()
	{
		super.onUpdate();
		if (!worldObj.isRemote && worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
		{
			setDead();
			//R.I.P ;(
		}
	}

	@Override
	protected void updateEntityActionState()
	{
		float distance = 0.0F;
		++entityAge;

		if (entityToAttack == null)
		{
			entityToAttack = findPlayerToAttack();
		}
		else if (entityToAttack.isEntityAlive() && canAttackClass(entityToAttack.getClass()))
		{
			distance = entityToAttack.getDistanceToEntity(this);
			
			if (distance > 16.0F)
			{
				entityToAttack = null;
			}
			else if (canEntityBeSeen(entityToAttack))
			{
				attackEntity(entityToAttack, distance);
			}
		}
		else
		{
			entityToAttack = null;
		}

		despawnEntity();
	}

	@Override
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
		getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.75D);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public boolean getCanSpawnHere()
	{
		return posY > 45.0D && posY < 63.0D && worldObj.difficultySetting != EnumDifficulty.PEACEFUL && super.getCanSpawnHere();
	}

	@Override
	public int getTotalArmorValue()
	{
		return Math.min(super.getTotalArmorValue() + 2, 20);
	}

	@Override
	protected Entity findPlayerToAttack()
	{
		EntityPlayer entityplayer = worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D);
		return entityplayer != null && canEntityBeSeen(entityplayer) ? entityplayer : null;
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount)
	{
		if (isEntityInvulnerable() || source.isExplosion())
		{
			return false;
		}
		else if (super.attackEntityFrom(source, amount))
		{
			Entity entity = source.getEntity();
			
			if (riddenByEntity != entity && ridingEntity != entity)
			{
				if (entity != this)
				{
					entityToAttack = entity;
				}
				
				return true;
			}
			else
			{
				return true;
			}
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean attackEntityAsMob(Entity entity)
	{
		float f = (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
		int i = 0;

		if (entity instanceof EntityLivingBase)
		{
			f += EnchantmentHelper.getEnchantmentModifierLiving(this, (EntityLivingBase)entity);
			i += EnchantmentHelper.getKnockbackModifier(this, (EntityLivingBase)entity);
		}

		boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);

		if (flag)
		{
			int j = EnchantmentHelper.getFireAspectModifier(this);
			
			if (j > 0)
			{
				entity.setFire(j * 4);
			}

			if (entity instanceof EntityLivingBase)
			{
				EnchantmentHelper.func_151384_a((EntityLivingBase) entity, this);
			}
		}

		return flag;
	}

	@Override
	protected void attackEntity(Entity entity, float distance)
	{
		if (attackTime <= 0)
		{
			if (distance < 2.0F && entity.boundingBox.maxY > boundingBox.minY && entity.boundingBox.minY < boundingBox.maxY)
			{
				attackTime = 20;
				attackEntityAsMob(entity);
			}
			else if (rand.nextInt(60) == 0 && entity instanceof EntityLivingBase)
			{
				attackTime = 20;
				float f = (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
				Entity projectile;
				int difficulty = worldObj.difficultySetting.getDifficultyId();
				
				if (getType() == 1)
				{
					projectile = new EntityBomb(worldObj, this, (EntityLivingBase) entity, 1.0F, (float)(14 - difficulty * 4)).
					setType(BombType.BOMB_STANDARD).setTime(12 - (difficulty * 2)).setNoGrief().setMotionFactor(0.25F).setDamage(f * 2.0F * difficulty);
				}
				else
				{
					projectile = new EntityThrowingRock(worldObj, this, (EntityLivingBase) entity, 1.0F, (float)(14 - difficulty * 4)).
					setIgnoreWater().setDamage(f * difficulty);
				}
				
				if (!worldObj.isRemote)
				{
					worldObj.spawnEntityInWorld(projectile);
				}
			}
		}
	}

	@Override
	protected void dropFewItems(boolean recentlyHit, int lootingLevel)
	{
		int j = rand.nextInt(2 + lootingLevel) + 1;
		
		for (int k = 0; k < j; ++k)
		{
			entityDropItem(new ItemStack(Items.wheat_seeds, 1, 0), 0.0F);
		}
	}

	@Override
	protected void dropRareDrop(int rarity)
	{
		switch(rarity)
		{
			case 1:
				entityDropItem(new ItemStack(ZSSItems.dekuNut,	1), 0.0F);
				break;
			default:
			if (getType() == 1)
			{
				entityDropItem(new ItemStack(ZSSItems.bomb, 1, BombType.BOMB_STANDARD.ordinal()), 0.0F);
			}
			else
			{
				entityDropItem(new ItemStack(rand.nextInt(3) == 1 ? Items.emerald : ZSSItems.smallHeart), 0.0F);
			}
		}
	}

	@Override
	public float getLootableChance(EntityPlayer player, WhipType whip)
	{
		return 0.2F;
	}

	@Override
	public ItemStack getEntityLoot(EntityPlayer player, WhipType whip)
	{
		if (rand.nextFloat() < (0.1F * (1 + whip.ordinal())))
		{
			return new ItemStack(ZSSItems.dekuNut,1);
		}
		else if (getType() == 1 && rand.nextFloat() < (0.1F * (1 + whip.ordinal())))
		{
			return new ItemStack(ZSSItems.bomb,1,BombType.BOMB_STANDARD.ordinal());
		}
		
		return new ItemStack(Items.wheat_seeds, 1, 0);
	}

	@Override
	public boolean onLootStolen(EntityPlayer player, boolean wasItemStolen)
	{
		return true;
	}

	@Override
	public boolean isHurtOnTheft(EntityPlayer player, WhipType whip)
	{
		return Config.getHurtOnSteal();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		super.writeEntityToNBT(compound);
		compound.setByte("landOctorokType", (byte) getType());
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		super.readEntityFromNBT(compound);
		
		if (compound.hasKey("landOctorokType"))
		{
			setType(compound.getByte("landOctorokType"));
		}
	}
}
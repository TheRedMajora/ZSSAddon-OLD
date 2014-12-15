//Thank you coolAlias!
//(I used most of the EntityOctorok.class)

package addon.zeldaswordskills.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIBeg;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

public class EntityLandOctorok extends EntityCreature implements IMob, IEntityLootable, IEntityVariant, IRangedAttackMob
{
	private EntityAIArrowAttack aiBulletAttack = new EntityAIArrowAttack(this, 1.0D, 20, 60, 15.0F);
	private static final int LANDOCTOROK_TYPE_INDEX = 13;

	public EntityLandOctorok(World world)
	{
		super(world);
        this.tasks.addTask(1, new EntityAISwimming(this));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));

        if (world != null && !world.isRemote)
        {
            this.setCombatTask();
        }
	}
	
	@Override
	protected boolean isAIEnabled()
	{
		return true;
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
	protected void applyEntityAttributes()
	{
		super.applyEntityAttributes();
		
		getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
		getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entity, float distance)
	{
		if(entity instanceof EntityPlayer)
		{
			float f = (float) getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue();
			Entity projectile;
			int difficulty = worldObj.difficultySetting.getDifficultyId();
		
			if (getType() == 1)
			{
				projectile = new EntityBomb(worldObj, this, (EntityLivingBase) entity, 1.0F, (float)(14 - difficulty * 4)).setType(BombType.BOMB_STANDARD).setTime(12 - (difficulty * 2)).setNoGrief().setMotionFactor(0.25F).setDamage(f * 2.0F * difficulty);
			}
			else
			{
				projectile = new EntityThrowingRock(worldObj, this, (EntityLivingBase) entity, 1.0F, (float)(14 - difficulty * 4)).setDamage(f * difficulty);
			}

			//Playsound?
			//this.playSound("zeldaswordskills" + "octoShoot", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			worldObj.spawnEntityInWorld(projectile);
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

	public void setCombatTask()
    {
		this.tasks.addTask(4, this.aiBulletAttack);
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
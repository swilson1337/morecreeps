package com.morecreepsrevival.morecreeps.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityHunchbackSkeleton extends EntityCreepBase implements IRangedAttackMob
{
    private static final DataParameter<Integer> timeLeft = EntityDataManager.createKey(EntityHunchbackSkeleton.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> SWINGING_ARMS = EntityDataManager.<Boolean>createKey(EntityHunchbackSkeleton.class, DataSerializers.BOOLEAN);

    public EntityHunchbackSkeleton(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Hunchback Skeleton");

        creatureType = EnumCreatureType.MONSTER;

        setSize(0.6f, 1.99f);

        baseHealth = (float)rand.nextInt(10) + 10.0f;

        baseSpeed = 0.25f;

        setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BOW));

        updateAttributes();
    }

    @Override
    protected void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        nodeProcessor.setCanEnterDoors(true);

        tasks.addTask(1, new EntityAISwimming(this));

        tasks.addTask(2, new EntityAIBreakDoor(this));

        tasks.addTask(3, new EntityAIAttackRanged(this, 1.0d, 25, 75, 15.0f));

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityLivingBase.class, true));
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(timeLeft, rand.nextInt(500) + 200);

        dataManager.register(SWINGING_ARMS, Boolean.valueOf(false));
    }

    @Override
    protected void updateTexture()
    {
        int iTimeLeft = getTimeLeft();

        int textureNum = 1;

        if (iTimeLeft < 500 && iTimeLeft > 300)
        {
            textureNum = 2;
        }
        else if (iTimeLeft < 300 && iTimeLeft > 200)
        {
            textureNum = 3;
        }
        else if (iTimeLeft < 200 && iTimeLeft > 100)
        {
            textureNum = 4;
        }
        else if (iTimeLeft < 100 && iTimeLeft > 1)
        {
            textureNum = 5;
        }

        setTexture("textures/entity/hunchbackskeleton" + textureNum + ".png");
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        EntityLivingBase target = getAttackTarget();

        EntityPlayer owner = getOwner();

        if (target != null && owner != null && (target.equals(owner) || (target instanceof EntityCreepBase && owner.equals(((EntityCreepBase)target).getOwner())) || (target instanceof EntityTameable && owner.equals(((EntityTameable)target).getOwner()))))
        {
            setAttackTarget(null);
        }

        if (rand.nextInt(2) == 0)
        {
            setTimeLeft(getTimeLeft() - 1);
        }

        updateTexture();

        if (getTimeLeft() < 1)
        {
            smoke();

            setDead();
        }
    }

    private void setTimeLeft(int i)
    {
        dataManager.set(timeLeft, i);
    }

    public int getTimeLeft()
    {
        return dataManager.get(timeLeft);
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 0)
        {
            if (rand.nextInt(2) == 0)
            {
                dropItem(Items.ARROW, rand.nextInt(3));
            }

            if (rand.nextInt(2) == 0)
            {
                dropItem(Items.BONE, rand.nextInt(2));
            }
        }
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_SKELETON_AMBIENT;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_SKELETON_HURT;
    }

    @Override
    public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor)
    {
        EntityArrow entityarrow = this.getArrow(distanceFactor);
        double d0 = target.posX - this.posX;
        double d1 = target.getEntityBoundingBox().minY + (double)(target.height / 3.0F) - entityarrow.posY;
        double d2 = target.posZ - this.posZ;
        double d3 = (double) MathHelper.sqrt(d0 * d0 + d2 * d2);
        entityarrow.shoot(d0, d1 + d3 * 0.20000000298023224D, d2, 1.6F, (float)(14 - this.world.getDifficulty().getDifficultyId() * 4));
        this.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
        this.world.spawnEntity(entityarrow);
    }

    protected EntityArrow getArrow(float p_190726_1_)
    {
        EntityTippedArrow entitytippedarrow = new EntityTippedArrow(this.world, this);
        entitytippedarrow.setEnchantmentEffectsFromEntity(this, p_190726_1_);
        return entitytippedarrow;
    }

    @SideOnly(Side.CLIENT)
    public boolean isSwingingArms()
    {
        return ((Boolean)dataManager.get(SWINGING_ARMS)).booleanValue();
    }

    public void setSwingingArms(boolean swingingArms)
    {
        dataManager.set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
    }
}

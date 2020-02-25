package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.entity.ai.*;

import javax.annotation.Nonnull;

public class EntityEvilCreature extends EntityCreepBase implements IMob
{
    private static final DataParameter<Boolean> jumping = EntityDataManager.<Boolean>createKey(EntityEvilCreature.class, DataSerializers.BOOLEAN);

    public EntityEvilCreature(World world)
    {
        super(world);

        setCreepTypeName("Evil Creature");

        creatureType = EnumCreatureType.MONSTER;

        setSize(width * 3.0f, height * 3.0f);

        setModelSize(3.0f);

        baseHealth = (float)rand.nextInt(75) + 35.0f;

        baseSpeed = 0.2d;

        baseAttackDamage = 3.0d;

        isImmuneToFire = true;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(jumping, Boolean.valueOf(false));
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

        tasks.addTask(3, new EntityAIAttackMelee(this, 1.0d, true));

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/evilcreature.png");
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.evilCreatureSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.evilCreatureHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.evilCreatureDeathSound;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(5) == 0)
        {
            dropItem(Items.BREAD, rand.nextInt(3) + 1);
        }
        else
        {
            dropItem(Items.FISH, rand.nextInt(3) + 1);
        }
    }

    @Override
    protected boolean shouldJumpWhileAttacking(Entity entity)
    {
        return true;
    }

    @Override
    protected float getSoundVolume()
    {
        return (1.0f * (getModelSize() / 3.0f));
    }

    @Override
    protected float getSoundPitch()
    {
        return ((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f + (3.0f - getModelSize()) * 2.0f);
    }

    @Override
    protected void doAttackJump(Entity entity)
    {
        if (getJumping())
        {
            dataManager.set(jumping, false);

            playSound(CreepsSoundHandler.evilCreatureJumpSound, getSoundVolume(), getSoundPitch());

            for (Entity ent : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(18.0d, 18.0d, 18.0d)))
            {
                if (ent instanceof EntityLiving && !ent.handleWaterMovement() && ent.onGround)
                {
                    double dist = getDistance(ent);

                    ent.motionY += (17.0d - dist) * 0.067057996988296509 * (double)(getModelSize() / 3.0f);
                }
            }
        }

        rotationYaw = ((float)Math.toDegrees(Math.atan2(entity.posZ - posZ, entity.posX - posX))) - 90.0f;

        double d0 = entity.posX - posX;

        double d1 = entity.posZ - posZ;

        double f = MathHelper.sqrt(d0 * d0 + d1 * d1);

        motionX = (d0 / f) * 0.5d * 0.40000000192092894d + motionX * 0.20000000098023224d;

        motionZ = (d1 / f) * 0.5d * 0.30000000192092896d + motionZ * 0.20000000098023224d;

        motionY = 0.35000000196046449f;

        fallDistance = -25.0f;

        dataManager.set(jumping, true);
    }

    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entity)
    {
        AxisAlignedBB myBB = getEntityBoundingBox();

        AxisAlignedBB theirBB = entity.getEntityBoundingBox();

        if (theirBB.maxY > myBB.minY && theirBB.minY < myBB.maxY)
        {
            entity.motionY += 0.76999998092651367d;
        }

        return super.attackEntityAsMob(entity);
    }

    @Override
    public void setJumping(boolean b)
    {
        super.setJumping(b);

        dataManager.set(jumping, Boolean.valueOf(b));
    }

    public boolean getJumping()
    {
        return ((Boolean)dataManager.get(jumping)).booleanValue();
    }

    @Override
    public boolean isImmuneToExplosions()
    {
        return true;
    }
}

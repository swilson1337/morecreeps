package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityKid extends EntityCreepBase
{
    private static final DataParameter<Integer> checkTimer = EntityDataManager.createKey(EntityKid.class, DataSerializers.VARINT);

    public EntityKid(World world)
    {
        super(world);

        setCreepTypeName("Kid");

        setModelSize(0.6f);

        setSize(width * 0.6f, height * 0.6f);

        baseHealth = 25.0f;

        baseSpeed = 0.25d;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(checkTimer, 0);
    }

    @Override
    public void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        nodeProcessor.setCanEnterDoors(true);

        if (!isRiding())
        {
            tasks.addTask(1, new EntityAISwimming(this));

            tasks.addTask(2, new EntityAIBreakDoor(this));

            tasks.addTask(3, new EntityAIAttackMelee(this, 1.0d, true));

            tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

            tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

            tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

            tasks.addTask(6, new EntityAILookIdle(this));

            targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        }
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/kid.png");
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (dataManager.get(checkTimer) > 0)
        {
            dataManager.set(checkTimer, dataManager.get(checkTimer) - 1);
        }

        if (isRiding() && dataManager.get(checkTimer) < 1)
        {
            dataManager.set(checkTimer, 60);

            for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(8.0d, 8.0d, 8.0d)))
            {
                if (entity instanceof EntityLolliman)
                {
                    playSound(CreepsSoundHandler.kidFindSound, getSoundVolume(), getSoundPitch());
                }
            }
        }
    }

    @Override
    public double getYOffset()
    {
        Entity entity = getRidingEntity();

        if (entity instanceof EntityPlayer)
        {
            double size = (double)getModelSize();

            double d = 0.6d - size;

            if (size > 1.0d)
            {
                d *= 0.55d;
            }

            return 0.25d + d;
        }
        else if (entity instanceof EntityLolliman)
        {
            return ((2.6d + (0.6d - (double)getModelSize())) - (2.0d - ((EntityLolliman)entity).getModelSize()) * 2.75d);
        }

        return 1.0d;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (!(getAttackTarget() instanceof EntityPlayer))
        {
            if (rand.nextInt(5) == 0)
            {
                int x = MathHelper.floor(posX);

                int y = MathHelper.floor(getEntityBoundingBox().minY);

                int z = MathHelper.floor(posZ);

                Block block = world.getBlockState(new BlockPos(x, y - 1, z)).getBlock();

                if (block == Blocks.SNOW || block == Blocks.ICE || world.getBlockState(new BlockPos(x, y, z)).getBlock() == Blocks.SNOW)
                {
                    return CreepsSoundHandler.kidColdSound;
                }
            }

            if (isRiding())
            {
                return CreepsSoundHandler.kidRideSound;
            }

            return CreepsSoundHandler.kidSound;
        }

        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.kidHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.kidDeathSound;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    public boolean canRidePlayer()
    {
        return true;
    }

    @Override
    protected SoundEvent getMountSound()
    {
        return CreepsSoundHandler.kidMountSound;
    }

    @Override
    protected SoundEvent getUnmountSound()
    {
        return CreepsSoundHandler.kidUnmountSound;
    }

    @Override
    public boolean canMount(Entity entity)
    {
        if (getAttackTarget() instanceof EntityPlayer)
        {
            playSound(CreepsSoundHandler.kidNoPickupSound, getSoundVolume(), getSoundPitch());

            return false;
        }

        return super.canMount(entity);
    }

    @Override
    protected float getSoundPitch()
    {
        return ((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f + (0.6f - getModelSize()) * 2.0f);
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 0)
        {
            dropItem(Items.PORKCHOP, rand.nextInt(3) + 1);
        }

        if (rand.nextInt(10) == 0)
        {
            dropItem(Items.WHEAT, rand.nextInt(3) + 1);
        }
    }
}

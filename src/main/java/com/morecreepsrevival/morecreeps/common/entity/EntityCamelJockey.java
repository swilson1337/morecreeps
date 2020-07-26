package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityCamelJockey extends EntityCreepBase
{
    public EntityCamelJockey(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Camel Jockey");

        setSize(width * 0.6f, height * 0.6f);

        setModelSize(0.6f);

        baseHealth = 25.0f;

        baseSpeed = 0.25d;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/jockey.png");
    }

    @Override
    public void initEntityAI()
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
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (handleWaterMovement())
        {
            motionY = 0.15999999642372131d;
        }

        if (!isRiding())
        {
            for (Entity entity : world.loadedEntityList)
            {
                if (entity instanceof EntityCamel && !entity.isBeingRidden())
                {
                    EntityCamel camel = (EntityCamel)entity;

                    if (!camel.isTamed())
                    {
                        double d = camel.getDistanceSq(this);

                        if (d < 4.0d)
                        {
                            camel.untame();

                            startRiding(camel);
                        }

                        if (d < 16.0d && camel.canEntityBeSeen(this))
                        {
                            attackEntityAsMob(camel);
                        }
                    }
                }
            }
        }
        else
        {
            Entity ridingEnt = getRidingEntity();

            if (ridingEnt != null)
            {
                rotationYaw = ridingEnt.rotationYaw;
            }
        }
    }

    @Override
    public float getBlockPathWeight(BlockPos blockPos)
    {
        Block block = world.getBlockState(blockPos).getBlock();

        if (block == Blocks.SAND || block == Blocks.GRAVEL)
        {
            return 10.0f;
        }

        return super.getBlockPathWeight(blockPos);
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
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
            dropItem(Items.REEDS, rand.nextInt(3) + 1);
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (isRiding())
        {
            return CreepsSoundHandler.camelJockeyGetSound;
        }

        return CreepsSoundHandler.camelJockeySound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.camelJockeyHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.camelJockeyDeathSound;
    }
}

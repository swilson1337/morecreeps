package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityGooGoat extends EntityCreepBase
{
    private static final DataParameter<Integer> hungryTime = EntityDataManager.createKey(EntityGooGoat.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> hungry = EntityDataManager.<Boolean>createKey(EntityGooGoat.class, DataSerializers.BOOLEAN);

    public EntityGooGoat(World world)
    {
        super(world);

        setCreepTypeName("Goo Goat");

        setModelSize(0.7f);

        baseHealth = 25.0f;

        baseSpeed = 0.25d;

        baseAttackDamage = 2.0d;

        dataManager.set(hungryTime, rand.nextInt(100) + 10);

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(hungryTime, 0);

        dataManager.register(hungry, Boolean.valueOf(false));
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
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.gooGoatHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.gooGoatDeathSound;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.gooGoatSound;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        float modelSize = getModelSize();

        float w = modelSize * 0.75f;

        float h = modelSize * 1.5f;

        if (width != w || height != h)
        {
            setSize(w, h);
        }

        if (getHungry())
        {
            BlockPos blockPos = new BlockPos(MathHelper.floor(posX), MathHelper.floor(getEntityBoundingBox().minY) - 1, MathHelper.floor(posZ));

            if (world.getBlockState(blockPos).getBlock() == Blocks.GRASS && rand.nextInt(10) == 0)
            {
                world.setBlockState(blockPos, Blocks.DIRT.getDefaultState());

                dataManager.set(hungryTime, dataManager.get(hungryTime) + rand.nextInt(100) + 25);

                if (dataManager.get(hungryTime) > 300 && getLevel() < 5)
                {
                    setHungry(false);

                    dataManager.set(hungryTime, 0);

                    setModelSize(getModelSize() + 0.2f);

                    setLevel(getLevel() + 1);

                    baseAttackDamage += 1.0d;

                    updateAttributes();

                    addHealth(getLevelHealthMultiplier());

                    playSound(CreepsSoundHandler.gooGoatStretchSound, getSoundVolume(), getSoundPitch());
                }
            }
        }
        else
        {
            dataManager.set(hungryTime, dataManager.get(hungryTime) - 1);

            if (dataManager.get(hungryTime) < 1)
            {
                setHungry(true);

                dataManager.set(hungryTime, 0);
            }
        }
    }

    @Override
    public void updateTexture()
    {
        setTexture("textures/entity/googoat" + getLevel() + ".png");
    }

    @Override
    protected float getLevelHealthMultiplier()
    {
        return 15.0f;
    }

    @Override
    protected float getSoundVolume()
    {
        return 0.5f;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        int i = (getLevel() - 1) + rand.nextInt(2);

        if (i > 0)
        {
            dropItem(CreepsItemHandler.gooDonut, i);
        }
    }

    private void setHungry(boolean b)
    {
        dataManager.set(hungry, Boolean.valueOf(b));
    }

    public boolean getHungry()
    {
        return ((Boolean)dataManager.get(hungry)).booleanValue();
    }
}
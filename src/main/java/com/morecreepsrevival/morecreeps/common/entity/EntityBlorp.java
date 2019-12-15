package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityBlorp extends EntityCreepBase
{
    private static final DataParameter<Boolean> hungry = EntityDataManager.createKey(EntityBlorp.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> hungryTime = EntityDataManager.createKey(EntityBlorp.class, DataSerializers.VARINT);

    public EntityBlorp(World worldIn)
    {
        super(worldIn);

        setSize(width * 1.5f, height * 2.5f);

        baseAttackDamage = 2.0d;

        baseHealth = 25.0f;

        baseSpeed = 0.25d;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/blorp.png");
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(hungry, false);

        dataManager.register(hungryTime, rand.nextInt(20) + 20);
    }

    @Override
    public void onLivingUpdate()
    {
        if (getModelSize() > 2.0f)
        {
            ignoreFrustumCheck = true;
        }

        super.onLivingUpdate();

        if (getAttackTarget() != null)
        {
            setHungry(false);

            setHungryTime(100);
        }

        if (getHungry())
        {
        }
        else
        {
            setHungryTime(getHungryTime() - 1);

            if (getHungryTime() < 1)
            {
                setHungry(true);

                setHungryTime(1);
            }
        }
    }

    private void setHungry(boolean b)
    {
        dataManager.set(hungry, b);
    }

    public boolean getHungry()
    {
        try
        {
            return dataManager.get(hungry);
        }
        catch (Exception ignored)
        {
        }

        return false;
    }

    private void setHungryTime(int i)
    {
        dataManager.set(hungryTime, i);
    }

    public int getHungryTime()
    {
        return dataManager.get(hungryTime);
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        return false;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        dropItem(CreepsItemHandler.blorpCola, getLevel());
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 3;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.blorpSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.blorpHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.blorpDeathSound;
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
    }
}

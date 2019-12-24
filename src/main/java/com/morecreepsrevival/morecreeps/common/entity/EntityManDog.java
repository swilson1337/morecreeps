package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityManDog extends EntityCreepBase
{
    private static final DataParameter<Boolean> superDog = EntityDataManager.createKey(EntityManDog.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> tamedFood = EntityDataManager.createKey(EntityManDog.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> chase = EntityDataManager.createKey(EntityManDog.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Boolean> fetch = EntityDataManager.createKey(EntityManDog.class, DataSerializers.BOOLEAN);

    public EntityManDog(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Mandog");

        baseHealth = 45.0f;

        baseSpeed = 0.333d;

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

        tasks.addTask(3, new EntityAIAttackMelee(this, 1.0d, true));

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(superDog, false);

        dataManager.register(tamedFood, rand.nextInt(3) + 1);

        dataManager.register(chase, false);

        dataManager.register(fetch, false);
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/mandog.png");
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (getSuperDog())
        {
            return CreepsSoundHandler.superDogNameSound;
        }

        return CreepsSoundHandler.manDogSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.manDogHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.manDogDeathSound;
    }

    protected void setSuperDog(boolean b)
    {
        dataManager.set(superDog, b);
    }

    public boolean getSuperDog()
    {
        try
        {
            return dataManager.get(superDog);
        }
        catch (Exception ignored)
        {
        }

        return false;
    }

    protected void setTamedFood(int i)
    {
        dataManager.set(tamedFood, i);
    }

    public int getTamedFood()
    {
        return dataManager.get(tamedFood);
    }

    protected void setChase(boolean b)
    {
        dataManager.set(chase, b);
    }

    public boolean getChase()
    {
        try
        {
            return dataManager.get(chase);
        }
        catch (Exception ignored)
        {
        }

        return false;
    }

    protected void setFetch(boolean b)
    {
        dataManager.set(fetch, b);
    }

    public boolean getFetch()
    {
        try
        {
            return dataManager.get(fetch);
        }
        catch (Exception ignored)
        {
        }

        return false;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        dropItem(Items.BONE, 1);
    }
}

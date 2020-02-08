package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityHunchback extends EntityCreepBase
{
    private static final DataParameter<Integer> cakeTimer = EntityDataManager.createKey(EntityHunchback.class, DataSerializers.VARINT);

    public EntityHunchback(World world)
    {
        super(world);

        setCreepTypeName("Hunchback");

        baseSpeed = 0.2f;

        baseHealth = (float)rand.nextInt(30) + 10.0f;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(cakeTimer, 0);
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
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/hunchback.png");
    }

    private void setCakeTimer(int i)
    {
        dataManager.set(cakeTimer, i);
    }

    public int getCakeTimer()
    {
        return dataManager.get(cakeTimer);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (getAttackTarget() instanceof EntityHunchbackSkeleton)
        {
            setAttackTarget(null);
        }

        if (isTamed() && getCakeTimer() > 0 && rand.nextInt(10) == 0)
        {
            setCakeTimer(getCakeTimer() - 1);

            if (getCakeTimer() == 0)
            {
                clearOwner();

                updateTexture();

                setCakeTimer(rand.nextInt(700) + 300);
            }
        }
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (!isTamed())
        {
            if (rand.nextInt(5) == 0)
            {
                dropItem(Items.PORKCHOP, rand.nextInt(3) + 1);
            }
            else
            {
                dropItem(Item.getItemFromBlock(Blocks.SAND), rand.nextInt(3) + 1);
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (isTamed())
        {
            return CreepsSoundHandler.hunchQuietSound;
        }
        else if (rand.nextInt(3) == 0)
        {
            return CreepsSoundHandler.hunchCakeSound;
        }

        return CreepsSoundHandler.hunchQuietSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.hunchHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.hunchDeathSound;
    }
}

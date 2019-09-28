package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityTrophy extends EntityCreepBase
{
    private static final DataParameter<Integer> partyTime = EntityDataManager.createKey(EntityTrophy.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> trophyLifespan = EntityDataManager.createKey(EntityTrophy.class, DataSerializers.VARINT);

    public EntityTrophy(World world)
    {
        super(world);

        creatureType = EnumCreatureType.AMBIENT;

        experienceValue = 0;

        baseHealth = 1.0f;

        baseSpeed = 0.0d;

        setSize(1.0f, 2.5f);

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(partyTime, rand.nextInt(30) + 40);

        dataManager.register(trophyLifespan, 80);
    }

    @Override
    protected void initEntityAI()
    {
        clearAITasks();
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.trophySmashSound;
    }

    @Override
    protected boolean canDespawn()
    {
        return false;
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/trophy.png");
    }

    @Override
    public void onUpdate()
    {
        if (dataManager.get(partyTime) > 1)
        {
            dataManager.set(partyTime, dataManager.get(partyTime) - 1);

            // TODO: confetti
        }

        if (dataManager.get(trophyLifespan) > 0)
        {
            dataManager.set(trophyLifespan, dataManager.get(trophyLifespan) - 1);

            if (dataManager.get(trophyLifespan) < 1)
            {
                setDead();
            }
        }

        super.onUpdate();
    }

    @Override
    protected void dropItemsOnDeath()
    {
        int amt = rand.nextInt(25) + 10;

        for (int i = 0; i < amt; i++)
        {
            dropItem(CreepsItemHandler.money, 1);
        }
    }
}

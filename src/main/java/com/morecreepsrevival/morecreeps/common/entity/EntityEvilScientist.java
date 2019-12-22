package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityEvilScientist extends EntityCreepBase
{
    private static final DataParameter<Integer> stage = EntityDataManager.createKey(EntityEvilScientist.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> trulyEvil = EntityDataManager.createKey(EntityEvilScientist.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Boolean> towerBuilt = EntityDataManager.createKey(EntityEvilScientist.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> experimentTimer = EntityDataManager.createKey(EntityEvilScientist.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> experimentStart = EntityDataManager.createKey(EntityEvilScientist.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> numExperiments = EntityDataManager.createKey(EntityEvilScientist.class, DataSerializers.VARINT);

    public EntityEvilScientist(World worldIn)
    {
        super(worldIn);

        isImmuneToFire = true;

        baseHealth = (float)rand.nextInt(30) + 10.0f;

        baseSpeed = 0.3d;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(stage, 0);

        dataManager.register(trulyEvil, false);

        dataManager.register(experimentTimer, rand.nextInt(100) + 100);

        dataManager.register(numExperiments, rand.nextInt(3) + 1);

        dataManager.register(towerBuilt, false);

        dataManager.register(experimentStart, false);
    }

    @Override
    protected void updateTexture()
    {
        if (getTrulyEvil())
        {
            setTexture("textures/entity/evilscientistblown.png");
        }
        else
        {
            setTexture("textures/entity/evilscientist.png");
        }
    }

    protected void setStage(int i)
    {
        dataManager.set(stage, i);
    }

    public int getStage()
    {
        return dataManager.get(stage);
    }

    protected void setTrulyEvil(boolean b)
    {
        dataManager.set(trulyEvil, b);
    }

    public boolean getTrulyEvil()
    {
        try
        {
            return dataManager.get(trulyEvil);
        }
        catch (Exception ignored)
        {
        }

        return false;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (getStage() > 3)
        {
            return CreepsSoundHandler.evilLaughSound;
        }

        return CreepsSoundHandler.evilExplosionSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.evilHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.evilExperimentSound;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(5) == 0)
        {
            dropItem(Items.COOKED_PORKCHOP, rand.nextInt(3) + 1);
        }
        else
        {
            dropItem(Item.getItemFromBlock(Blocks.SAND), rand.nextInt(3) + 1);
        }
    }

    @Override
    public void onDeath(@Nonnull DamageSource damageSource)
    {
        tearDownTower();

        super.onDeath(damageSource);
    }

    @Override
    public void setDead()
    {
        tearDownTower();

        super.setDead();
    }

    private void tearDownTower()
    {
        if (!getTowerBuilt())
        {
            return;
        }
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    protected void setTowerBuilt(boolean b)
    {
        dataManager.set(towerBuilt, b);
    }

    public boolean getTowerBuilt()
    {
        try
        {
            return dataManager.get(towerBuilt);
        }
        catch (Exception ignored)
        {
        }

        return false;
    }

    protected void setExperimentStart(boolean b)
    {
        dataManager.set(experimentStart, b);
    }

    public boolean getExperimentStart()
    {
        try
        {
            return dataManager.get(experimentStart);
        }
        catch (Exception ignored)
        {
        }

        return false;
    }

    protected void setExperimentTimer(int i)
    {
        dataManager.set(experimentTimer, i);
    }

    public int getExperimentTimer()
    {
        return dataManager.get(experimentTimer);
    }

    public int getNumExperiments()
    {
        return dataManager.get(numExperiments);
    }
}

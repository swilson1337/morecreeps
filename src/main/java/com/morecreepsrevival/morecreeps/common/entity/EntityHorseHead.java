package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityHorseHead extends EntityCreepBase
{
    public EntityHorseHead(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Horse Head");

        setSize(0.6f, 2.0f);

        setModelSize(1.6f);

        baseHealth = 25.0f;

        baseSpeed = 0.0d;

        updateAttributes();
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
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
            dropItem(Items.WHEAT_SEEDS, rand.nextInt(3) + 1);
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.horseHeadSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.hippoHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.hippoDeathSound;
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/horsehead.png");
    }
}

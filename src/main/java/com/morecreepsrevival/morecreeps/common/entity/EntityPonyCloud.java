package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityPonyCloud extends EntityCreepBase
{
    public EntityPonyCloud(World worldIn)
    {
        super(worldIn);

        setSize(width * 0.8f, height * 1.3f);

        setModelSize(2.0f);

        posY = 100.0d;

        baseHealth = (float)rand.nextInt(15) + 100.0f;

        baseSpeed = 0.0d;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/ponycloud.png");
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.ponyCloudLivingSound;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource)
    {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return null;
    }
}

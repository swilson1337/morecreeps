package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityCamel extends EntityCreepBase
{
    public EntityCamel(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Camel");

        setSize(width * 1.5f, height * 4.0f);

        setModelSize(1.75f);

        baseAttackDamage = 2.0d;

        baseHealth = 30.0f;

        baseSpeed = 0.2d;

        updateAttributes();
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        if (getFirstPassenger() != null)
        {
            return false;
        }

        return super.isEntityInsideOpaqueBlock();
    }

    @Override
    public void onLivingUpdate()
    {
        if (getModelSize() > 1.75f)
        {
            ignoreFrustumCheck = true;
        }

        super.onLivingUpdate();
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
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.camelSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.camelHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.camelDeathSound;
    }
}

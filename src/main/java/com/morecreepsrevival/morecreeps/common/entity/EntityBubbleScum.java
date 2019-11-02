package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityBubbleScum extends EntityCreepBase
{
    public EntityBubbleScum(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Bubble Scum");

        setSize(0.5f, 0.5f);

        baseSpeed = 0.3d;

        baseHealth = (float)rand.nextInt(15) + 5.0f;

        updateAttributes();
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 12;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(25) == 0)
        {
            dropItem(Items.COOKIE, 1);
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (!isRiding() && rand.nextInt(1) == 0)
        {
            return CreepsSoundHandler.bubbleScumSound;
        }

        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.bubbleScumHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.bubbleScumDeathSound;
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/bubblescum.png");
    }
}

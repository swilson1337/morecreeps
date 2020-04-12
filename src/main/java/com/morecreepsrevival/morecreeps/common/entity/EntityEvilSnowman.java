package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityEvilSnowman extends EntityCreepBase implements IMob
{
    public EntityEvilSnowman(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Evil Snowman");

        creatureType = EnumCreatureType.MONSTER;

        setSize(0.7f, 1.5f);

        baseHealth = 25.0f;

        baseSpeed = 0.3d;

        isImmuneToFire = true;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/evilsnowman.png");
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.snowmanSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.snowmanHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.snowmanDeathSound;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 0)
        {
            dropItem(Item.getItemFromBlock(Blocks.ICE), rand.nextInt(3) + 1);

            dropItem(Item.getItemFromBlock(Blocks.SNOW), rand.nextInt(10) + 1);
        }
        else
        {
            dropItem(Item.getItemFromBlock(Blocks.SNOW), rand.nextInt(5) + 2);
        }
    }
}

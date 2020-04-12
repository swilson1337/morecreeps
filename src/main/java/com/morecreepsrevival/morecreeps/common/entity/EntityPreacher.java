package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityPreacher extends EntityCreepBase implements IMob
{
    public EntityPreacher(World world)
    {
        super(world);

        setCreepTypeName("Preacher");

        creatureType = EnumCreatureType.MONSTER;

        baseHealth = 0.75f;

        baseSpeed = 0.35d;

        baseAttackDamage = 5.0d;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/preacher" + rand.nextInt(3) + ".png");
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (rand.nextInt(4) == 0)
        {
            updateTexture();
        }

        if (isInLava())
        {
            if (rand.nextInt(25) == 0)
            {
                playSound(CreepsSoundHandler.preacherBurnSound, getSoundVolume(), getSoundPitch());
            }

            setOnFireFromLava();
        }
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (inWater || isEntityInsideOpaqueBlock())
        {
            BlockPos pos = new BlockPos(MathHelper.floor(posX), MathHelper.floor(getEntityBoundingBox().minY), MathHelper.floor(posZ));

            if (!world.isAirBlock(pos))
            {
                world.setBlockToAir(pos);

                motionY += 0.5d;
            }
        }
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.preacherSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.preacherHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.preacherDeathSound;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(50) == 0)
        {
            dropItem(Items.DIAMOND, rand.nextInt(2) + 1);
        }

        if (rand.nextInt(50) == 0)
        {
            entityDropItem(new ItemStack(Items.DYE, 1, 4), 1.0f);
        }

        if (rand.nextInt(50) == 0)
        {
            entityDropItem(new ItemStack(Items.DYE, 1, 3), 1.0f);
        }

        if (rand.nextInt(50) == 0)
        {
            entityDropItem(new ItemStack(Items.DYE, 1, 1), 1.0f);
        }

        if (rand.nextInt(2) == 0)
        {
            dropItem(Items.GOLD_INGOT, rand.nextInt(5) + 2);
        }
        else
        {
            dropItem(Items.BOOK, 1);

            dropItem(Items.APPLE, 1);
        }
    }
}

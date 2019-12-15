package com.morecreepsrevival.morecreeps.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityDogHouse extends EntityCreepBase
{
    public EntityDogHouse(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Dog House");

        creatureType = EnumCreatureType.AMBIENT;

        setModelSize(2.5f);

        setSize(width * 2.5f, height * 2.5f);

        baseSpeed = 0.0d;

        baseHealth = 20.0f;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/doghouse.png");
    }

    @Override
    protected void initEntityAI()
    {
        clearAITasks();
    }

    @Override
    public void onLivingUpdate()
    {
        motionX = 0.0d;

        motionY = 0.0d;

        motionZ = 0.0d;

        super.onLivingUpdate();

        if (inWater)
        {
            setDead();
        }
    }

    @Override
    public void onUpdate()
    {
        ignoreFrustumCheck = true;

        super.onUpdate();
    }

    @Override
    protected boolean canDespawn()
    {
        return (getHealth() < 1);
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        return (getHealth() <= 0);
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
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
        return null;
    }

    @Override
    public void setDead()
    {
        smoke();

        super.setDead();
    }

    private void loadHouse()
    {
        for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(16.0d, 16.0d, 16.0d)))
        {
            if (entity instanceof EntityHotdog && ((EntityHotdog)entity).isTamed() && entity.startRiding(this, true))
            {
                break;
            }
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        loadHouse();
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource damageSource, float amt)
    {
        if (amt < 1.0f)
        {
            amt = 1.0f;
        }

        boolean flag = super.attackEntityFrom(damageSource, amt);

        if (flag)
        {
            hurtTime = maxHurtTime = 10;

            smoke();
        }

        return flag;
    }

    @Override
    public void smoke()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
            }
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (hand == EnumHand.OFF_HAND)
        {
            return super.processInteract(player, hand);
        }
        else if (player.getHeldItem(hand).isEmpty())
        {
            if (isBeingRidden())
            {
                Entity entity = getFirstPassenger();

                if (entity instanceof EntityHotdog && ((EntityHotdog)entity).isPlayerOwner(player))
                {
                    if (entity.startRiding(player, true))
                    {
                        return true;
                    }

                    entity.dismountRidingEntity();

                    return true;
                }
            }
            else
            {
                for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(16.0d, 16.0d, 16.0d)))
                {
                    if (entity instanceof EntityHotdog && ((EntityHotdog)entity).isTamed() && entity.startRiding(this, true))
                    {
                        return true;
                    }
                }
            }
        }

        return super.processInteract(player, hand);
    }

    @Override
    public void updatePassenger(@Nonnull Entity passenger)
    {
        if (passenger instanceof EntityHotdog && isPassenger(passenger))
        {
            passenger.setPosition(posX, posY, posZ);

            return;
        }

        super.updatePassenger(passenger);
    }
}

package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityPonyCloud extends EntityCreepBase
{
    private static final DataParameter<Boolean> delivered = EntityDataManager.<Boolean>createKey(EntityPonyCloud.class, DataSerializers.BOOLEAN);

    private static final int explodeChance = 1500;

    private static final float boomSize = 13.5f;

    public EntityPonyCloud(World worldIn)
    {
        super(worldIn);

        creatureType = EnumCreatureType.AMBIENT;

        setCreepTypeName("Pony Cloud");

        setSize(width * 0.8f, height * 1.3f);

        setModelSize(2.0f);

        posY = 100.0d;

        baseHealth = (float)rand.nextInt(15) + 100.0f;

        baseAttackDamage = 2.0d;

        baseSpeed = 0.0d;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(delivered, Boolean.valueOf(false));
    }

    @Override
    public void initEntityAI()
    {
        clearAITasks();
    }

    private void setDelivered(boolean b)
    {
        dataManager.set(delivered, Boolean.valueOf(b));
    }

    public boolean getDelivered()
    {
        return ((Boolean)dataManager.get(delivered).booleanValue());
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

    @Override
    public void onLivingUpdate()
    {
        motionX = 0.0d;

        motionY = 0.0d;

        motionZ = 0.0d;

        if (rand.nextInt(explodeChance) == 999)
        {
            playSound(CreepsSoundHandler.ponyCloudKillSound, getSoundVolume(), getSoundPitch());

            world.createExplosion(this, posX, posY, posZ, boomSize, false);

            setDead();
        }

        Entity firstPassenger = getFirstPassenger();

        if (firstPassenger == null && !getDelivered())
        {
            setDead();
        }

        double xHeading = -MathHelper.sin(rotationYaw * (float)Math.PI / 180.0f);

        double zHeading = MathHelper.cos(rotationYaw * (float)Math.PI / 180.0f);

        for (int x = 0; x < 5; x++)
        {
            world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX + rand.nextGaussian() * 0.25d - rand.nextGaussian() * 0.25d + xHeading * 1.0d, posX + 1.5d + rand.nextGaussian() * 0.5d - rand.nextGaussian() * 0.5d, posZ + rand.nextGaussian() * 0.25d - rand.nextGaussian() * 0.25d + zHeading * 1.0d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d);
        }

        if (firstPassenger != null)
        {
            if (!getDelivered())
            {
                if (!world.isAirBlock(new BlockPos(posX, posY - 1, posZ)))
                {
                    firstPassenger.dismountRidingEntity();

                    playSound(CreepsSoundHandler.ponyPopOffSound, getSoundVolume(), getSoundPitch());

                    playSound(SoundEvents.BLOCK_LAVA_POP, 0.9f, getSoundPitch());

                    setDelivered(true);

                    smoke();
                }
                else
                {
                    motionY = -0.2d;
                }
            }
        }
        else if (getDelivered())
        {
            motionY = 0.5d;
        }

        if (posY > 128.0d)
        {
            setDead();
        }

        super.onLivingUpdate();
    }
}

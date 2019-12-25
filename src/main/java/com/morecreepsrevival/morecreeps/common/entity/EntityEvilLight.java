package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityEvilLight extends EntityCreepBase
{
    private int lifespan;

    public EntityEvilLight(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Evil Light");

        creatureType = EnumCreatureType.AMBIENT;

        lifespan = 200;

        motionZ = rand.nextFloat() * 2.0f - 1.0f;

        motionX = rand.nextFloat() * 2.0f - 1.0f;

        baseHealth = 1.0f;

        baseSpeed = 0.4d;

        baseAttackDamage = 3.0d;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/evillight1.png");
    }

    @Override
    public void onLivingUpdate()
    {
        if (lifespan-- < 1 || handleWaterMovement())
        {
            setDead();
        }

        for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(motionX, motionY, motionZ).grow(1.0d, 1.0d, 1.0d)))
        {
            if (entity.canBeCollidedWith() && entity instanceof EntityLivingBase && !(entity instanceof EntityEvilLight) && !(entity instanceof EntityEvilScientist) && !(entity instanceof EntityEvilChicken) && !(entity instanceof EntityEvilCreature) && !(entity instanceof EntityEvilPig))
            {
                entity.setFire(3);

                entity.motionX = rand.nextFloat() * 0.7f;

                entity.motionY = rand.nextFloat() * 0.4f;

                entity.motionZ = rand.nextFloat() * 0.7f;

                playSound(CreepsSoundHandler.evilLightSound, 0.2f, 1.0f / (rand.nextFloat() * 0.1f + 0.95f));
            }
        }

        super.onLivingUpdate();
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player)
    {
        player.attackEntityFrom(DamageSource.causeMobDamage(this), 3.0f);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.evilLightSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.evilLightSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.evilLightSound;
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return true;
    }
}

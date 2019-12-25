package com.morecreepsrevival.morecreeps.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityEvilEgg extends EntityThrowable
{
    protected double bounceFactor;

    protected boolean exploded;

    protected double initialVelocity;

    public EntityEvilEgg(World world)
    {
        super(world);

        setSize(0.25f, 0.25f);

        bounceFactor = 0.84999999999999998d;

        initialVelocity = 1.0d;

        exploded = false;
    }

    public EntityEvilEgg(World world, Entity entity)
    {
        this(world);

        setRotation(entity.rotationYaw, 0.0f);

        double d = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);
        double d1 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180F);
        motionX = 1.1000000000000001D * d * (double)MathHelper.cos((entity.rotationPitch / 180F) * (float)Math.PI);
        motionY = -1.1000000000000001D * (double)MathHelper.sin((entity.rotationPitch / 180F) * (float)Math.PI);
        motionZ = 1.1000000000000001D * d1 * (double) MathHelper.cos((entity.rotationPitch / 180F) * (float)Math.PI);
        setPosition(entity.posX + d * 0.80000000000000004D, entity.posY + 2, entity.posZ + d1 * 0.80000000000000004D);
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
    }

    public EntityEvilEgg(World world, double x, double y, double z)
    {
        this(world);

        setPosition(x, y, z);
    }

    @Override
    public void setVelocity(double d, double d1, double d2)
    {
        motionX = d;
        motionY = d1;
        motionZ = d2;

        if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt(d * d + d2 * d2);
            prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / Math.PI);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f) * 180D) / Math.PI);
        }
    }

    @Override
    public boolean isInRangeToRenderDist(double d)
    {
        double d1 = getEntityBoundingBox().getAverageEdgeLength() * 4.0d;

        d1 *= 64.0d;

        return (d < (d1 * d1));
    }

    @Override
    protected void onImpact(@Nullable RayTraceResult result)
    {
    }

    private void explode()
    {
        if (!exploded)
        {
            exploded = true;

            world.createExplosion(null, posX, posY, posZ, 2.0f, true);

            setDead();
        }
    }

    @Override
    public void onUpdate()
    {
        double d = motionX;
        double d1 = motionY;
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        move(MoverType.SELF, motionX, motionY, motionZ);

        if (motionX != d)
        {
            if (rand.nextInt(40) == 0)
            {
                EntityEvilChicken evilChicken = new EntityEvilChicken(world);

                evilChicken.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0f);

                evilChicken.motionX = rand.nextFloat() * 0.3f;

                evilChicken.motionY = rand.nextFloat() * 0.4f;

                evilChicken.motionZ = rand.nextFloat() * 0.4f;

                if (!world.isRemote)
                {
                    world.spawnEntity(evilChicken);
                }
            }
            else
            {
                explode();
            }
        }

        if (motionY != d1)
        {
            if (rand.nextInt(40) == 0)
            {
                EntityEvilChicken evilChicken = new EntityEvilChicken(world);

                evilChicken.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0f);

                evilChicken.motionX = rand.nextFloat() * 0.3f;

                evilChicken.motionY = rand.nextFloat() * 0.4f;

                evilChicken.motionZ = rand.nextFloat() * 0.4f;

                if (!world.isRemote)
                {
                    world.spawnEntity(evilChicken);
                }
            }
            else
            {
                explode();
            }
        }

        if (motionY != d1)
        {
            if (rand.nextInt(40) == 0)
            {
                EntityEvilChicken evilChicken = new EntityEvilChicken(world);

                evilChicken.setLocationAndAngles(posX, posY, posZ, rotationYaw, 0.0f);

                evilChicken.motionX = rand.nextFloat() * 0.3f;

                evilChicken.motionY = rand.nextFloat() * 0.4f;

                evilChicken.motionZ = rand.nextFloat() * 0.4f;

                if (!world.isRemote)
                {
                    world.spawnEntity(evilChicken);
                }
            }
            else
            {
                explode();
            }
        }
        else
        {
            motionY -= 0.040000000000000001d;
        }

        motionX *= 0.97999999999999998D;
        motionY *= 0.995D;
        motionZ *= 0.97999999999999998D;

        if (handleWaterMovement())
        {
            for (int i = 0; i < 8; i++)
            {
                for (int k = 0; k < 10; k++)
                {
                    float f = 0.85f;

                    world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX - 0.25D * (double)f, posY - motionY - 0.25D * (double)f, posZ - motionZ - 0.25D * (double)f, motionX, motionY, motionZ);
                }
            }

            setDead();
        }

        for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(motionX, motionY, motionZ).expand(1.0d, 1.0d, 1.0d)))
        {
            if (entity.canBeCollidedWith() && !(entity instanceof EntityPlayer))
            {
                explode();
            }
        }
    }
}

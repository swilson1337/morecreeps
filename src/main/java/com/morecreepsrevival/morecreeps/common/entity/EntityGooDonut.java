package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityGooDonut extends EntityThrowable
{
    protected double bounceFactor;

    protected double initialVelocity;

    protected int fuse;

    protected boolean exploded;

    public EntityGooDonut(World world)
    {
        super(world);

        setSize(0.25f, 0.25f);

        initialVelocity = 1.0D;

        bounceFactor = 0.84999999999999998D;

        exploded = false;

        fuse = 30;
    }

    public EntityGooDonut(World world, Entity entity)
    {
        this(world);

        setRotation(entity.rotationYaw, 0.0f);

        double d = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);

        double d1 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180F);

        motionX = 0.69999999999999996D * d * (double)MathHelper.cos((entity.rotationPitch / 180F) * (float)Math.PI);

        motionY = -0.80000000000000004D * (double)MathHelper.sin((entity.rotationPitch / 180F) * (float)Math.PI);

        motionZ = 0.69999999999999996D * d1 * (double)MathHelper.cos((entity.rotationPitch / 180F) * (float)Math.PI);

        setPosition(entity.posX + d * 0.80000000000000004D, entity.posY + 2, entity.posZ + d1 * 0.80000000000000004D);

        prevPosX = posX;

        prevPosY = posY;

        prevPosZ = posZ;
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
            motionX = -bounceFactor * d;
        }

        if (motionY != d1)
        {
            motionY = -bounceFactor * d1;
        }

        if (motionY != d1)
        {
            motionY = -bounceFactor * d1;
        }
        else
        {
            motionY -= 0.040000000000000001d;
        }

        motionX *= 0.97999999999999998D;
        motionY *= 0.995D;
        motionZ *= 0.97999999999999998D;

        if (fuse-- <= 0)
        {
            explode();
        }

        if (handleWaterMovement())
        {
            for (int i = 0; i < 8; i++)
            {
                for (int j = 0; j < 10; j++)
                {
                    float f = 0.85f;

                    world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX - 0.25D * (double)f, posY - motionY - 0.25D * (double)f, posZ - motionZ - 0.25D * (double)f, motionX, motionY, motionZ);
                }
            }

            setDead();

            if (!world.isRemote)
            {
                dropItem(CreepsItemHandler.gooDonut, 1);
            }
        }
    }

    @Override
    protected void onImpact(@Nullable RayTraceResult result)
    {
    }

    @Override
    public boolean isInRangeToRenderDist(double d)
    {
        double d1 = getEntityBoundingBox().getAverageEdgeLength() * 4.0d;

        d1 *= 64.0d;

        return (d < (d1 * d1));
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

    private void explode()
    {
        if (!exploded)
        {
            exploded = true;

            if (!world.isRemote)
            {
                world.createExplosion(null, posX, posY, posZ, 2.0f, true);
            }

            setDead();
        }
    }
}

package com.morecreepsrevival.morecreeps.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityGooDonut extends EntityThrowable
{
    private static final double bounceFactor = 0.8499999999999999d;

    public int fuse = 30;

    public EntityGooDonut(World world)
    {
        super(world);

        setSize(0.25f, 0.25f);
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

        setPosition(entity.posX + d * 0.80000000000000004D, entity.posY, entity.posZ + d1 * 0.80000000000000004D);

        prevPosX = posX;

        prevPosY = posY;

        prevPosZ = posZ;
    }

    @Override
    public void onUpdate()
    {
        double x = motionX;

        double y = motionY;

        double z = motionZ;
    }

    @Override
    protected void onImpact(@Nullable RayTraceResult result)
    {
    }
}

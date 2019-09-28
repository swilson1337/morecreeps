package com.morecreepsrevival.morecreeps.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityEvilEgg extends EntityThrowable
{
    private static final double bounceFactor = 0.84999999999999998d;

    public EntityEvilEgg(World world)
    {
        super(world);

        setSize(0.25f, 0.25f);
    }

    public EntityEvilEgg(World world, Entity entity)
    {
        this(world);

        setRotation(entity.rotationYaw, 0.0f);
    }

    @Override
    protected void onImpact(@Nullable RayTraceResult result)
    {
    }
}

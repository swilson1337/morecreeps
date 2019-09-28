package com.morecreepsrevival.morecreeps.common.helpers;

import com.morecreepsrevival.morecreeps.common.entity.EntityTrophy;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Random;

public class EffectHelper
{
    public static void spawnTrophy(World world, Entity entity)
    {
        double d = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180.0f);

        double d1 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180.0f);

        EntityTrophy trophy = new EntityTrophy(world);

        trophy.setLocationAndAngles(entity.posX + d * 3.0d, entity.posY - 2.0d, entity.posZ + d1 * 3.0d, entity.rotationYaw, 0.0f);

        world.spawnEntity(trophy);
    }

    public static void explode(World world, Entity entity)
    {
        world.createExplosion(null, entity.posX, entity.posY, entity.posZ, 2.0f, true);
    }

    public static void smoke(World world, Entity entity, Random rand, boolean plain)
    {
        if (!plain)
        {
            for (int i = 0; i < 7; i++)
            {
                world.spawnParticle(EnumParticleTypes.HEART, (entity.posX + (double)(rand.nextFloat() * entity.width * 2.0f)) - (double)entity.width, entity.posY + 0.5d + (double)(rand.nextFloat() * entity.height), (entity.posZ + (double)(rand.nextFloat() * entity.width * 2.0f)) - (double)entity.width, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d);
            }
        }

        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (entity.posX + (double)(rand.nextFloat() * entity.width * 2.0f)) - (double)entity.width, entity.posY + (double)(rand.nextFloat() * entity.height) + (double)i, (entity.posZ + (double)(rand.nextFloat() * entity.width * 2.0f)) - (double)entity.width, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d);
            }
        }
    }

    public static void smoke(World world, Entity entity, Random rand)
    {
        smoke(world, entity, rand, false);
    }

    public static void smokePlain(World world, Entity entity, Random rand)
    {
        smoke(world, entity, rand, true);
    }

    public static void smoke2(World world, Entity entity, Random rand)
    {
        double d = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180.0f);

        double d1 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180.0f);

        world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((entity.posX + rand.nextGaussian() * 0.25d) - rand.nextGaussian() * 0.25d) + d * 1.0d, ((entity.posY - 0.5d) + rand.nextGaussian() * 0.5d) - rand.nextGaussian() * 0.5d, ((entity.posZ + rand.nextGaussian() * 0.25d) - rand.nextGaussian() * 0.25d) + d1 * 1.0d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d);
    }

    public static void smokeRay(World world, Entity entity, Random rand)
    {
        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entity.posX, entity.posY, entity.posZ, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d);
    }
}

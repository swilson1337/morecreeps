package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.helpers.EffectHelper;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityRay extends EntityThrowable
{
    private Entity shootingEntity;

    public EntityRay(World worldIn)
    {
        super(worldIn);
    }

    public EntityRay(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public EntityRay(World worldIn, EntityLivingBase entityIn)
    {
        super(worldIn, entityIn);

        shootingEntity = entityIn;
    }

    @Override
    protected void onImpact(@Nullable RayTraceResult result)
    {
        blast();

        playSound(CreepsSoundHandler.raygunSound, 0.2f, 1.0f / (rand.nextFloat() * 0.1f + 0.95f));

        if (result != null && !world.isRemote)
        {
            if (result.typeOfHit == RayTraceResult.Type.ENTITY)
            {
                if (result.entityHit != null)
                {
                    result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getThrower()), 4);
                }
            }
            else if (result.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                BlockPos blockPos = result.getBlockPos();

                Block block = world.getBlockState(blockPos).getBlock();

                if (block != Blocks.AIR)
                {
                    System.out.println("NO AIR");

                    if (rand.nextInt(3) == 0)
                    {
                        System.out.println("BANG BANG BANG");

                        if (MoreCreepsConfig.rayGunFire)
                        {
                            world.setBlockState(blockPos, Blocks.FIRE.getDefaultState());
                        }
                        else
                        {
                            world.setBlockToAir(blockPos);
                        }
                    }
                }
            }
        }

        setDead();
    }

    @Override
    protected float getGravityVelocity()
    {
        return 0.0f;
    }

    private void blast()
    {
        for (int i = 0; i < 8; i++)
        {
            EffectHelper.smokeRay(world, this, rand);
        }
    }
}

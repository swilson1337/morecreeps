package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityFrisbee extends Entity
{
    private int xTile;

    private int yTile;

    private int zTile;

    private int inTile;

    private boolean inGround;

    private double initialVelocity;

    private double bounceFactor;

    private int lifespan;

    public EntityFrisbee(World worldIn)
    {
        super(worldIn);

        setSize(0.25f, 0.25f);

        xTile = -1;

        yTile = -1;

        zTile = -1;

        inTile = 0;

        inGround = false;

        initialVelocity = 1.0d;

        bounceFactor = 0.14999999999999999d;

        lifespan = 120;
    }

    public EntityFrisbee(World worldIn, Entity entity)
    {
        this(worldIn);

        setRotation(entity.rotationYaw, 0.0f);

        double d = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);
        double d1 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180F);
        motionX = 0.59999999999999998D * d * (double)MathHelper.cos((entity.rotationPitch / 180F) * (float)Math.PI);
        motionY = -0.69999999999999996D * (double)MathHelper.sin((entity.rotationPitch / 180F) * (float)Math.PI);
        motionZ = 0.59999999999999998D * d1 * (double)MathHelper.cos((entity.rotationPitch / 180F) * (float)Math.PI);
        setPosition(entity.posX + d * 0.80000000000000004D, entity.posY + 2, entity.posZ + d1 * 0.80000000000000004D + (3D * d1 + d));
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
    }

    public EntityFrisbee(World worldIn, double d, double d1, double d2)
    {
        this(worldIn);

        setPosition(d, d1, d2);
    }

    private void setThrowableHeading(double d, double d1, double d2, float f, float f1)
    {
        float f2 = MathHelper.sqrt(d * d + d1 * d1 + d2 * d2);
        d /= f2;
        d1 /= f2;
        d2 /= f2;
        d += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d1 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d2 += rand.nextGaussian() * 0.0074999998323619366D * (double)f1;
        d *= f;
        d1 *= f;
        d2 *= f;
        motionX = d;
        motionY = d1;
        motionZ = d2;
        float f3 = MathHelper.sqrt(d * d + d2 * d2);
        prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180D) / Math.PI);
        prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180D) / Math.PI);
        //field_20050_h = 0;
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
    public void onUpdate()
    {
        double d = motionX;

        double d1 = motionY;

        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;

        move(MoverType.SELF, motionX, motionY, motionZ);

        if (handleWaterMovement())
        {
            motionY += 0.0087999999523162842D;
            motionX *= 0.97999999999999998D;
            motionZ *= 0.68000000000000005D;
        }

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
        else if (!handleWaterMovement())
        {
            motionY -= 0.0050000000000000001D;
        }

        motionX *= 0.97999999999999998D;
        motionY *= 0.999D;
        motionZ *= 0.97999999999999998D;

        if (collidedVertically)
        {
            motionX *= 0.25D;
            motionZ *= 0.25D;
        }

        if (onGround && lifespan-- < 0)
        {
            if (!world.isRemote)
            {
                dropItem(CreepsItemHandler.frisbee, 1);
            }

            setDead();
        }
    }

    @Override
    public boolean isInRangeToRenderDist(double d)
    {
        double d1 = getEntityBoundingBox().getAverageEdgeLength() * 4.0d * 64.0d;

        return (d < (d1 * d1));
    }

    @Override
    protected void entityInit()
    {
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound compound)
    {
        compound.setInteger("xTile", xTile);

        compound.setInteger("yTile", yTile);

        compound.setInteger("zTile", zTile);

        compound.setInteger("inTile", inTile);

        compound.setBoolean("inGround", inGround);
    }

    @Override
    public void readEntityFromNBT(@Nonnull NBTTagCompound compound)
    {
        xTile = compound.getInteger("xTile");

        yTile = compound.getInteger("yTile");

        zTile = compound.getInteger("zTile");

        inTile = compound.getInteger("inTile");

        inGround = compound.getBoolean("inGround");
    }
}

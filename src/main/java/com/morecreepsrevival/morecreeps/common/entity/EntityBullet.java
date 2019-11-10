package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class EntityBullet extends Entity
{
    protected int hitX;
    protected int hitY;
    protected int hitZ;
    protected Block blockHit;
    protected boolean aoLightValueZPos;
    protected int aoLightValueScratchXYZNNP;
    protected int aoLightValueScratchXYNN;
    protected int damage;
    protected boolean aoLightValueScratchXYZNNN;
    protected boolean playerFire;
    public Entity shootingEntity;

    public EntityBullet(World worldIn)
    {
        super(worldIn);

        hitX = -1;

        hitY = -1;

        hitZ = -1;

        blockHit = Blocks.AIR;

        aoLightValueZPos = false;

        aoLightValueScratchXYNN = 0;

        setSize(0.0325f, 0.01125f);

        playerFire = false;
    }

    public EntityBullet(World world, double d, double d1, double d2)
    {
        this(world);

        setPosition(d, d1, d2);

        aoLightValueScratchXYZNNN = true;
    }

    public EntityBullet(World world, EntityLivingBase entityliving, float f)
    {
        this(world);

        shootingEntity = entityliving;

        damage = 2;

        setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);

        posX -= MathHelper.cos((rotationYaw / 180F) * (float)Math.PI) * 0.16F;
        posY -= 0.10000000149011612D;
        posZ -= MathHelper.sin((rotationYaw / 180F) * (float)Math.PI) * 0.16F;

        if (entityliving instanceof EntityPlayer)
        {
            posY -= 0.40000000596046448D;
        }

        setPosition(posX, posY, posZ);

        motionX = -MathHelper.sin((rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float)Math.PI);
        motionZ = MathHelper.cos((rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float)Math.PI);
        motionY = -MathHelper.sin((rotationPitch / 180F) * (float)Math.PI);

        if (entityliving instanceof EntityArmyGuy)
        {
            damage = 1;

            // TODO: check if army guy is loyal, if true then set damage to 5
        }
        else if (entityliving instanceof EntitySneakySal)
        {
            EntityPlayer entityplayer = world.getClosestPlayerToEntity(this, 25D);

            if (entityplayer != null)
            {
                posY -= 1.7999999523162842D;
                double d1 = entityplayer.posY - posY;
                motionY += d1 / 20D;
                motionY += 0.076999999582767487D;
            }
        }

        float f1 = 1.0F;

        if (entityliving instanceof EntityPlayer)
        {
            playerFire = true;
            damage = 6;
            float f2 = 0.3333333F;
            float f3 = f2 / 0.1F;

            if (f3 > 0.0F)
            {
                f1 = (float)((double)f1 * (1.0D + 2D / (double)f3));
            }
        }

        if (Math.abs(entityliving.motionX) > 0.10000000000000001D || Math.abs(entityliving.motionY) > 0.10000000000000001D || Math.abs(entityliving.motionZ) > 0.10000000000000001D)
        {
            f1 *= 2.0F;
        }

        func_22374_a(motionX, motionY, motionZ, (float)(2.380000114440918D + ((double)world.rand.nextFloat() - 0.5D)), f1);
    }

    private void func_22374_a(double d, double d1, double d2, float f, float f1)
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
        aoLightValueScratchXYZNNP = 0;
    }

    @Override
    protected void entityInit()
    {
    }

    @Override
    public boolean isInRangeToRenderDist(double d)
    {
        return true;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (aoLightValueScratchXYNN == 100)
        {
            setDead();
        }

        if (prevRotationPitch == 0.0F && prevRotationYaw == 0.0F)
        {
            float f = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
            prevRotationYaw = rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / Math.PI);
            prevRotationPitch = rotationPitch = (float)((Math.atan2(motionY, f) * 180D) / Math.PI);
        }

        if (aoLightValueZPos)
        {
            Block i = world.getBlockState(new BlockPos(hitX, hitY, hitZ)).getBlock();

            if (i != blockHit)
            {
                aoLightValueZPos = false;
                motionX *= rand.nextFloat() * 0.2F;
                motionY *= rand.nextFloat() * 0.2F;
                motionZ *= rand.nextFloat() * 0.2F;
                aoLightValueScratchXYZNNP = 0;
                aoLightValueScratchXYNN = 0;
            }
            else
            {
                aoLightValueScratchXYZNNP++;

                if (aoLightValueScratchXYZNNP == 100)
                {
                    setDead();
                }

                return;
            }
        }
        else
        {
            aoLightValueScratchXYNN++;
        }

        Vec3d vec3d = new Vec3d(posX, posY, posZ);
        Vec3d vec3d1 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
        RayTraceResult movingobjectposition = world.rayTraceBlocks(vec3d, vec3d1);
        vec3d = new Vec3d(posX, posY, posZ);
        vec3d1 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

        if (movingobjectposition != null)
        {
            vec3d1 = new Vec3d(movingobjectposition.hitVec.x, movingobjectposition.hitVec.y, movingobjectposition.hitVec.z);
        }

        Entity entity = null;
        double d = 0.0D;

        for (Entity entity1 : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(motionX, motionY, motionZ).grow(1.0D, 1.0D, 1.0D)))
        {
            if (!entity1.canBeCollidedWith() || (entity1 == shootingEntity || shootingEntity != null && entity1 == shootingEntity.getRidingEntity()) && aoLightValueScratchXYNN < 5 || aoLightValueScratchXYZNNN)
            {
                if (motionZ != 0.0D || !((motionX == 0.0D) & (motionY == 0.0D)))
                {
                    continue;
                }

                setDead();

                break;
            }

            float f4 = 0.3F;
            AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f4, f4, f4);
            RayTraceResult movingobjectposition1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);

            if (movingobjectposition1 == null)
            {
                continue;
            }

            double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);

            if (d1 < d || d == 0.0D)
            {
                entity = entity1;
                d = d1;
            }
        }

        if (entity != null)
        {
            movingobjectposition = new RayTraceResult(entity);
        }

        if (movingobjectposition != null)
        {
            if (movingobjectposition.entityHit != null)
            {
                if (movingobjectposition.entityHit instanceof EntityPlayer)
                {
                    int k = damage;

                    EnumDifficulty currentDifficulty = world.getDifficulty();

                    if (currentDifficulty == EnumDifficulty.PEACEFUL)
                    {
                        k = 0;
                    }
                    else if (currentDifficulty == EnumDifficulty.EASY)
                    {
                        k = k / 3 + 1;
                    }
                    else if (currentDifficulty == EnumDifficulty.HARD)
                    {
                        k = (k * 3) / 2;
                    }
                }

                if ((movingobjectposition.entityHit instanceof EntityLiving) && playerFire || !(movingobjectposition.entityHit instanceof EntityFloob) || playerFire)
                {
                    if (!(movingobjectposition.entityHit instanceof EntityRobotTodd) && !(movingobjectposition.entityHit instanceof EntityRobotTed) && MoreCreepsConfig.blood)
                    {
                        // TODO: blood
                    }

                    if (movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, shootingEntity), damage));

                    setDead();
                }
                else
                {
                    setDead();
                }
            }
            else
            {
                BlockPos blockPos = movingobjectposition.getBlockPos();
                hitX = blockPos.getX();
                hitY = blockPos.getY();
                hitZ = blockPos.getZ();
                blockHit = world.getBlockState(blockPos).getBlock();
                motionX = (float)(movingobjectposition.hitVec.x - posX);
                motionY = (float)(movingobjectposition.hitVec.y - posY);
                motionZ = (float)(movingobjectposition.hitVec.z - posZ);
                float f1 = MathHelper.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
                posX -= (motionX / (double)f1) * 0.05000000074505806D;
                posY -= (motionY / (double)f1) * 0.05000000074505806D;
                posZ -= (motionZ / (double)f1) * 0.05000000074505806D;
                aoLightValueZPos = true;
                setDead();

                if (blockHit == Blocks.ICE)
                {
                    world.setBlockState(blockPos, Blocks.WATER.getDefaultState());
                }

                if (MoreCreepsConfig.rayGunFire && blockHit == Blocks.GLASS)
                {
                    world.setBlockToAir(blockPos);

                    Blocks.GLASS.onBlockDestroyedByPlayer(world, blockPos, world.getBlockState(blockPos));
                }

                setDead();
            }

            playSound(CreepsSoundHandler.raygunSound, 0.2f, 1.0f / (rand.nextFloat() * 0.1f + 0.95f));

            setDead();
        }

        posX += motionX;
        posY += motionY;
        posZ += motionZ;
        float f2 = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
        rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180D) / Math.PI);

        for (rotationPitch = (float)((Math.atan2(motionY, f2) * 180D) / Math.PI); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F) { }

        for (; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F) { }

        for (; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F) { }

        for (; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F) { }

        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
        float f3 = 0.99F;

        if (handleWaterMovement())
        {
            for (int l = 0; l < 4; l++)
            {
                float f7 = 0.25F;

                world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * (double)f7, posY - motionY * (double)f7, posZ - motionZ * (double)f7, motionX, motionY, motionZ);
            }

            f3 = 0.8F;
            setDead();
        }

        motionX *= f3;

        motionZ *= f3;

        setPosition(posX, posY, posZ);
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound compound)
    {
        compound.setInteger("xTile", hitX);

        compound.setInteger("yTile", hitY);

        compound.setInteger("zTile", hitZ);

        compound.setBoolean("inGround", aoLightValueZPos);
    }

    @Override
    public void readEntityFromNBT(@Nonnull NBTTagCompound compound)
    {
        hitX = compound.getInteger("xTile");

        hitY = compound.getInteger("yTile");

        hitZ = compound.getInteger("zTile");

        aoLightValueZPos = compound.getBoolean("inGround");
    }

    @Override
    public void setDead()
    {
        // TODO: blast

        shootingEntity = null;

        super.setDead();
    }
}

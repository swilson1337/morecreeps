package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityRocket extends Entity
{
    private static final DataParameter<Integer> shootingEntity = EntityDataManager.createKey(EntityRocket.class, DataSerializers.VARINT);

    protected int hitX;

    protected int hitY;

    protected int hitZ;

    protected boolean aoLightValueZPos;

    protected int aoLightValueScratchXYNN;

    protected boolean playerFire;

    protected int explodeDelay;

    protected boolean aoLightValueScratchXYZNNN;

    protected int damage;

    protected int aoLightValueScratchXYZNNP;

    protected boolean playerAttack = false;

    public EntityRocket(World worldIn)
    {
        super(worldIn);

        hitX = -1;

        hitY = -1;

        hitZ = -1;

        aoLightValueZPos = false;

        aoLightValueScratchXYNN = 0;

        setSize(0.325f, 0.1425f);

        playerFire = false;

        explodeDelay = 30;
    }

    public EntityRocket(World worldIn, double x, double y, double z)
    {
        this(worldIn);

        setPosition(x, y, z);

        aoLightValueScratchXYZNNN = true;
    }

    public EntityRocket(World worldIn, EntityLivingBase entityLivingBase, float f)
    {
        this(worldIn);

        dataManager.set(shootingEntity, entityLivingBase.getEntityId());

        damage = 15;

        setLocationAndAngles(entityLivingBase.posX, entityLivingBase.posY + (double)entityLivingBase.getEyeHeight(), entityLivingBase.posZ, entityLivingBase.rotationYaw, entityLivingBase.rotationPitch);

        posX -= MathHelper.cos((rotationYaw / 180F) * (float)Math.PI) * 0.16F;
        posY += 0.10000000149011612D;
        posZ -= MathHelper.sin((rotationYaw / 180F) * (float)Math.PI) * 0.66F;

        if (entityLivingBase instanceof EntityPlayer)
        {
            posY -= 1.3999999761581421D;
        }

        setPosition(posX, posY, posZ);

        motionX = -MathHelper.sin((rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float)Math.PI);
        motionZ = MathHelper.cos((rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float)Math.PI);
        motionY = -MathHelper.sin((rotationPitch / 180F) * (float)Math.PI);

        float f1 = 1.0f;

        if (entityLivingBase instanceof EntityPlayer)
        {
            playerFire = true;
            float f2 = 0.3333333F;
            float f3 = f2 / 0.1F;

            if (f3 > 0.0F)
            {
                f1 = (float)((double)f1 * (1.0D + 2D / (double)f3));
            }
        }

        if (Math.abs(entityLivingBase.motionX) > 0.10000000000000001D || Math.abs(entityLivingBase.motionY) > 0.10000000000000001D || Math.abs(entityLivingBase.motionZ) > 0.10000000000000001D)
        {
            f1 *= 2.0F;
        }

        func_22374_a(motionX, motionY, motionZ, (float)(1.1499999761581421D + ((double)world.rand.nextFloat() - 0.5D)), f1);
    }

    public void func_22374_a(double d, double d1, double d2, float f, float f1)
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
    public boolean isInRangeToRenderDist(double d)
    {
        return true;
    }

    @Override
    public boolean canBeCollidedWith()
    {
        return (explodeDelay <= 0);
    }

    @Override
    protected void entityInit()
    {
        dataManager.register(shootingEntity, 0);
    }

    public Entity getShootingEntity()
    {
        return world.getEntityByID(dataManager.get(shootingEntity));
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (explodeDelay > 0)
        {
            explodeDelay--;
        }

        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, rand.nextGaussian() * 0.02D, rand.nextGaussian() * 0.02D, rand.nextGaussian() * 0.02D);

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
            if (world.checkBlockCollision(getEntityBoundingBox()))
            {
                aoLightValueZPos = false;
                motionX *= rand.nextFloat() * 0.2F;
                motionY *= rand.nextFloat() * 0.2F;
                motionZ *= rand.nextFloat() * 0.2F;
                aoLightValueScratchXYZNNP = 0;
                aoLightValueScratchXYNN = 0;
            }

            aoLightValueScratchXYZNNP++;

            if (aoLightValueScratchXYZNNP == 100)
            {
                setDead();
            }

            return;
        }
        else
        {
            aoLightValueScratchXYNN++;
        }

        Vec3d vec3d = new Vec3d(posX, posY, posZ);

        Vec3d vec3d1 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

        RayTraceResult rtr = world.rayTraceBlocks(vec3d, vec3d1);

        vec3d = new Vec3d(posX, posY, posZ);

        vec3d1 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

        if (rtr != null)
        {
            vec3d1 = new Vec3d(rtr.hitVec.x, rtr.hitVec.y, rtr.hitVec.z);
        }

        double d3 = 0.0d;

        Entity entityToUse = null;

        for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(motionX, motionY, motionZ).grow(1.0d, 1.0d, 1.0d)))
        {
            if (!entity.canBeCollidedWith() || ((entity.equals(getShootingEntity()) || (getShootingEntity() != null && entity.equals(getShootingEntity().getRidingEntity()))) && aoLightValueScratchXYNN < 5) || aoLightValueScratchXYZNNN)
            {
                if (motionZ != 0.0d || !((motionX == 0.0d) & (motionY == 0.0d)))
                {
                    continue;
                }

                setDead();

                break;
            }

            float f4 = 0.3f;

            AxisAlignedBB axisAlignedBB = entity.getEntityBoundingBox().grow(f4, f4, f4);

            RayTraceResult rtr1 = axisAlignedBB.calculateIntercept(vec3d, vec3d1);

            if (rtr1 == null)
            {
                world.createExplosion(null, posX, posY, posZ, 1.0f, true);

                checkSplashDamage();

                setDead();

                continue;
            }

            double d4 = vec3d.distanceTo(rtr1.hitVec);

            if (d4 < d3 || d3 == 0.0d)
            {
                world.createExplosion(null, posX, posY, posZ, 1.0f, true);

                checkSplashDamage();

                setDead();

                entityToUse = entity;

                d3 = d4;
            }
        }

        if (entityToUse != null)
        {
            rtr = new RayTraceResult(entityToUse);
        }

        if (rtr != null)
        {
            if (rtr.entityHit != null)
            {
                if (rtr.entityHit instanceof EntityLiving && !(rtr.entityHit instanceof EntityRocketGiraffe))
                {
                    rtr.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, entityToUse), damage);

                    playSound(CreepsSoundHandler.rocketExplodeSound, 1.0f, (rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f);

                    world.createExplosion(null, posX, posY, posZ, 1.5f, true);
                }

                setDead();
            }
            else
            {
                BlockPos blockPos = rtr.getBlockPos();

                hitX = blockPos.getX();

                hitY = blockPos.getY();

                hitZ = blockPos.getZ();

                motionX = (float)(rtr.hitVec.x - posX);

                motionY = (float)(rtr.hitVec.y - posY);

                motionZ = (float)(rtr.hitVec.z - posZ);

                float f1 = MathHelper.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);

                posX -= (motionX / (double)f1) * 0.05000000074505806D;
                posY -= (motionY / (double)f1) * 0.05000000074505806D;
                posZ -= (motionZ / (double)f1) * 0.05000000074505806D;
                aoLightValueZPos = true;

                playSound(CreepsSoundHandler.rocketExplodeSound, 1.0f, (rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f);

                world.createExplosion(null, posX, posY, posZ, 1.5f, true);

                checkSplashDamage();

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

        for (rotationPitch = (float)((Math.atan2(motionY, f2) * 180D) / Math.PI); rotationPitch - prevRotationPitch < -180F; prevRotationPitch -= 360F);

        for (; rotationPitch - prevRotationPitch >= 180F; prevRotationPitch += 360F);

        for (; rotationYaw - prevRotationYaw < -180F; prevRotationYaw -= 360F);

        for (; rotationYaw - prevRotationYaw >= 180F; prevRotationYaw += 360F);

        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;

        float f3 = 0.99F;

        if (handleWaterMovement())
        {
            for (int i = 0; i < 4; i++)
            {
                float f7 = 0.25f;

                world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * (double)f7, posY - motionY * (double)f7, posZ - motionZ * (double)f7, motionX, motionY, motionZ);
            }

            f3 = 0.8f;

            setDead();
        }

        motionX *= f3;

        motionZ *= f3;

        setPosition(posX, posY, posZ);
    }

    private void checkSplashDamage()
    {
        Entity entityPlayer = getShootingEntity();

        for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(5.0d, 5.0d, 5.0d)))
        {
            if (!((entity != null) & (entity instanceof EntityCreature)) || entity instanceof EntityRocketGiraffe)
            {
                continue;
            }
            else if (entityPlayer instanceof EntityPlayer)
            {
                ((EntityCreature)entity).setRevengeTarget((EntityPlayer)entityPlayer);
            }

            playerAttack = true;

            entity.velocityChanged = true;

            entity.motionY += 0.20000000298023224D;

            entity.attackEntityFrom(DamageSource.GENERIC, 10.0f);
        }
    }

    @Override
    protected void writeEntityToNBT(@Nonnull NBTTagCompound compound)
    {
        compound.setInteger("xTile", hitX);

        compound.setInteger("yTile", hitY);

        compound.setInteger("zTile", hitZ);

        compound.setBoolean("inGround", aoLightValueZPos);
    }

    @Override
    protected void readEntityFromNBT(@Nonnull NBTTagCompound compound)
    {
        hitX = compound.getInteger("xTile");

        hitY = compound.getInteger("yTile");

        hitZ = compound.getInteger("zTile");

        aoLightValueZPos = compound.getBoolean("inGround");
    }
}

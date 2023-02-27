package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityBullet extends Entity
{
    private static final DataParameter<Integer> shootingEntity = EntityDataManager.createKey(EntityBullet.class, DataSerializers.VARINT);

    protected int damage;
    protected boolean playerFire;
    private int xTile;
    private int yTile;
    private int zTile;
    private int inTile;
    private boolean inGround;
    private int ticksInAir;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;

    public EntityBullet(World worldIn)
    {
        super(worldIn);

        xTile = -1;

        yTile = -1;

        zTile = -1;

        inTile = 0;

        inGround = false;

        ticksInAir = 0;

        accelerationX = 0.0d;

        accelerationY = 0.0d;

        accelerationZ = 0.0d;

        setSize(1.0f, 1.0f);

        damage = 2;

        playerFire = false;
    }

    public EntityBullet(World world, double d, double d1, double d2)
    {
        this(world);

        setPosition(d, d1, d2);
    }

    public EntityBullet(World world, EntityLivingBase entityliving)
    {
        this(world);

        dataManager.set(shootingEntity, entityliving.getEntityId());

        if (entityliving instanceof EntityPlayer)
        {
            damage = 4;

            playerFire = true;
        }

        setLocationAndAngles(entityliving.posX, entityliving.posY + (double)entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);

        double xHeading = -MathHelper.sin(entityliving.rotationYaw * (float)Math.PI / 180.0f);

        double zHeading = MathHelper.cos(entityliving.rotationYaw * (float)Math.PI / 180.0f);

        double par13 = xHeading * 0.5d;

        double par15 = entityliving.rotationPitch;

        double par17 = zHeading * 0.5d;

        double par9 = MathHelper.sin((float)(par13 * par13 + par15 * par15 + par17 * par17));

        accelerationX = par13 / par9 * 1.1d;

        accelerationZ = par17 / par9 * 1.1d;

        setSize(0.5f, 0.5f);

        posX -= (MathHelper.cos(rotationYaw / 180.0f * (float)Math.PI) * 0.16f);

        posY -= 0.3500000014901161d;

        posZ -= (MathHelper.sin(rotationYaw / 180.0f * (float)Math.PI) * 0.16f);

        setPosition(posX, posY, posZ);

        motionX = -MathHelper.sin(rotationYaw / 180.0f * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0f * (float)Math.PI);

        motionZ = MathHelper.cos(rotationYaw / 180.0f * (float)Math.PI) * MathHelper.cos(rotationPitch / 180.0f * (float)Math.PI);

        motionY = -MathHelper.sin(rotationPitch / 180.0f * (float)Math.PI);
    }

    public EntityBullet(World world, EntityLivingBase entityliving, double par3, double par5, double par7)
    {
        this(world);

        dataManager.set(shootingEntity, entityliving.getEntityId());

        // TODO: if loyal army guy damage = 6

        if (entityliving instanceof EntitySneakySal)
        {
            par5 -= 0.344000234d;
        }

        setLocationAndAngles(entityliving.posX, entityliving.posY, entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);

        motionX = motionY = motionZ = 0.0d;

        par3 += rand.nextGaussian() * 0.4d;

        par5 += rand.nextGaussian() * 0.4d;

        par7 += rand.nextGaussian() * 0.4d;

        double par9 = MathHelper.sin((float)(par3 * par3 + par5 * par5 + par7 * par7));

        accelerationX = par3 / par9 * 1.1d;

        accelerationY = par5 / par9 * 1.1d;

        accelerationZ = par7 / par9 * 1.1d;
    }

    @Override
    protected void entityInit()
    {
        dataManager.register(shootingEntity, 0);
    }

    @Override
    public boolean isInRangeToRenderDist(double d)
    {
        return true;
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

    @Override
    public void setDead()
    {
        blast();

        super.setDead();
    }

    private void blast()
    {
    	world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, posX, posY, posZ, 0f, 0f, 0f);
    }

    private void blood()
    {
        if (MoreCreepsConfig.blood)
        {
            // TODO: blood effects
        	
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (ticksInAir > 75)
        {
            setDead();

            return;
        }

        Vec3d var15 = new Vec3d(posX, posY, posZ);

        Vec3d var2 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

        RayTraceResult rtr = world.rayTraceBlocks(var15, var2);

        var15 = new Vec3d(posX, posY, posZ);

        var2 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

        if (rtr != null)
        {
            var2 = new Vec3d(rtr.hitVec.x, rtr.hitVec.y, rtr.hitVec.z);
        }

        Entity var4 = null;

        double var6 = 0.0d;

        for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(motionX, motionY, motionZ).expand(1.0d, 1.0d, 1.0d)))
        {
            if (entity.canBeCollidedWith() && (!entity.equals(getShootingEntity()) || ticksInAir >= 25))
            {
                float var10 = 0.3f;

                AxisAlignedBB boundingBox = entity.getEntityBoundingBox().expand(var10, var10, var10);

                RayTraceResult rtr2 = boundingBox.calculateIntercept(var15, var2);

                if (rtr2 != null)
                {
                    double var13 = var15.distanceTo(rtr2.hitVec);

                    if (var13 < var6 || var6 == 0.0d)
                    {
                        var4 = entity;

                        var6 = var13;
                    }
                }
            }
        }

        if (var4 != null)
        {
            rtr = new RayTraceResult(var4);
        }

        if (rtr != null)
        {
            onHit(rtr);
        }

        posX += motionX;

        posY += motionY;

        posZ += motionZ;

        float var16 = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);

        rotationYaw = (float)(Math.atan2(motionX, motionZ) * 180.0d / Math.PI);

        for (rotationPitch = (float)(Math.atan2(motionY, var16) * 180.0d / Math.PI); (rotationPitch - prevRotationPitch) < -180.0f; prevRotationPitch -= 360.0f);

        while ((rotationPitch - prevRotationPitch) >= 180.0f)
        {
            prevRotationPitch += 360.0f;
        }

        while ((rotationYaw - prevRotationYaw) < -180.0f)
        {
            prevRotationYaw -= 360.0f;
        }

        while ((rotationYaw - prevRotationYaw) >= 180.0f)
        {
            prevRotationYaw += 360.0f;
        }

        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2f;

        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2f;

        float var17 = 0.95f;

        if (handleWaterMovement())
        {
            for (int i = 0; i < 4; i++)
            {
                float var18 = 0.25f;

                world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * (double)var18, posY - motionY * (double)var18, posZ - motionZ * (double)var18, motionX, motionY, motionZ);
            }

            var17 = 0.8f;
        }

        motionX += accelerationX;

        motionY += accelerationY;

        motionZ += accelerationZ;

        motionX *= var17;

        motionY *= var17;

        motionZ *= var17;

        setPosition(posX, posY, posZ);

        ticksInAir++;
    }

    private void onHit(RayTraceResult rtr)
    {
        if (rtr.entityHit != null && rtr.entityHit == getShootingEntity())
        {
            return;
        }

        if (rtr.entityHit != null && rtr.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, getShootingEntity()), damage))
        {
            if (MoreCreepsConfig.blood && !(rtr.entityHit instanceof EntityRobotTodd) && !(rtr.entityHit instanceof EntityRobotTed))
            {
                blood();
            }
        }

        if (rtr.typeOfHit == RayTraceResult.Type.BLOCK)
        {
            BlockPos blockHitPos = rtr.getBlockPos();

            if (!world.isAirBlock(blockHitPos))
            {
                Block blockHit = world.getBlockState(blockHitPos).getBlock();

                if (blockHit == Blocks.ICE)
                {
                    world.setBlockState(blockHitPos, Blocks.WATER.getDefaultState());
                }
                else if (blockHit == Blocks.GLASS)
                {
                    //world.setBlockToAir(blockHitPos);
                    
                    world.destroyBlock(blockHitPos, false);
                }
            }
        }

        playSound(CreepsSoundHandler.raygunSound, 0.2f, 1.0f / (rand.nextFloat() * 0.1f + 0.95f));

        setDead();
    }

    public Entity getShootingEntity()
    {
        return world.getEntityByID(dataManager.get(shootingEntity));
    }
}

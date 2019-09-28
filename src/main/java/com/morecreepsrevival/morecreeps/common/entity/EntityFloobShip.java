package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.MoverType;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityFloobShip extends EntityCreepBase
{
    private int lifespan;

    private int floobCounter;

    private boolean landed = false;

    private float bump = 2.0f;

    private boolean firstReset = false;

    public EntityFloobShip(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Floob Ship");

        baseHealth = 150.0f;

        baseSpeed = 0.0d;

        setSize(4.0f, 3.0f);

        motionX = rand.nextFloat() * 0.8f;

        motionZ = rand.nextFloat() * 0.8f;

        lifespan = rand.nextInt(10000) + 1500;

        floobCounter = rand.nextInt(500) + 400;

        updateAttributes();
    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, IBlockState state, BlockPos pos)
    {
    }

    @Override
    public void travel(float strafe, float vertical, float forward)
    {
        if (this.isInWater())
        {
            this.moveRelative(strafe, vertical, forward, 0.02F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.800000011920929D;
            this.motionY *= 0.800000011920929D;
            this.motionZ *= 0.800000011920929D;
        }
        else if (this.isInLava())
        {
            this.moveRelative(strafe, vertical, forward, 0.02F);
            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= 0.5D;
            this.motionY *= 0.5D;
            this.motionZ *= 0.5D;
        }
        else
        {
            float f = 0.91F;

            if (this.onGround)
            {
                BlockPos underPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
                IBlockState underState = this.world.getBlockState(underPos);
                f = underState.getBlock().getSlipperiness(underState, this.world, underPos, this) * 0.91F;
            }

            float f1 = 0.16277136F / (f * f * f);
            this.moveRelative(strafe, vertical, forward, this.onGround ? 0.1F * f1 : 0.02F);
            f = 0.91F;

            if (this.onGround)
            {
                BlockPos underPos = new BlockPos(MathHelper.floor(this.posX), MathHelper.floor(this.getEntityBoundingBox().minY) - 1, MathHelper.floor(this.posZ));
                IBlockState underState = this.world.getBlockState(underPos);
                f = underState.getBlock().getSlipperiness(underState, this.world, underPos, this) * 0.91F;
            }

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            this.motionX *= (double)f;
            this.motionY *= (double)f;
            this.motionZ *= (double)f;
        }

        this.prevLimbSwingAmount = this.limbSwingAmount;
        double d1 = this.posX - this.prevPosX;
        double d0 = this.posZ - this.prevPosZ;
        float f2 = MathHelper.sqrt(d1 * d1 + d0 * d0) * 4.0F;

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        this.limbSwingAmount += (f2 - this.limbSwingAmount) * 0.4F;
        this.limbSwing += this.limbSwingAmount;
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/floobship.png");
    }

    @Override
    protected void initEntityAI()
    {
        clearAITasks();
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }

    @Override
    public boolean isOnLadder()
    {
        return false;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.floobShipSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.floobShipSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.floobShipDeathSound;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!world.isRemote)
        {
            if (floobCounter > 0)
            {
                floobCounter--;
            }

            if (lifespan > 0)
            {
                lifespan--;
            }
        }

        if (handleWaterMovement())
        {
            motionY += 0.2800000011920929d;

            motionX += 0.97999999999999998d;

            motionX += 0.97999999999999998d;
        }

        if (isJumping || landed)
        {
            motionY = 0.d;

            bump = 0.0f;
        }

        if (!landed || !onGround)
        {
            if (posY < 100.0d && !firstReset)
            {
                motionY = 4.0d;

                bump = 4.0f;

                firstReset = true;
            }

            motionY = -0.2f + bump;

            bump *= 0.95999999999999996d;

            motionX *= 0.97999999999999998d;

            motionZ *= 0.97999999999999998d;

            if (onGround)
            {
                Block block = world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(getEntityBoundingBox().minY), MathHelper.floor(posZ))).getBlock();

                if (block == Blocks.FLOWING_WATER || block == Blocks.WATER || block == Blocks.LEAVES || block == Blocks.CACTUS)
                {
                    thrusters();

                    bump = 3.0f;

                    motionX = rand.nextFloat() * 2.8f;

                    motionY = rand.nextFloat() * 0.6f;

                    motionZ = rand.nextFloat() * 2.8f;
                }
                else
                {
                    landed = true;
                }
            }
        }
        else if (floobCounter < 1)
        {
            thrusters();

            floobCounter = rand.nextInt(300) + 400;

            playSound(CreepsSoundHandler.floobShipSpawnSound, getSoundVolume(), getSoundPitch());

            int randInt = rand.nextInt(4) + 3;

            for (int i = 0; i < randInt; i++)
            {
                EntityFloob floob = new EntityFloob(world);

                floob.setInitialHealth();

                floob.setLocationAndAngles(posX + 3.0d + (double)i, posY + 1.0d, posZ + (double)i, rotationYaw, 0.0f);

                floob.motionX = rand.nextFloat() * 1.5f;

                floob.motionY = rand.nextFloat() * 2.0f;

                floob.motionZ = rand.nextFloat() * 1.5f;

                if (!world.isRemote)
                {
                    world.spawnEntity(floob);
                }
            }
        }
    }

    private void thrusters()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                double d = rand.nextGaussian() * 0.02d;

                double d1 = rand.nextGaussian() * 0.02d;

                double d2 = rand.nextGaussian() * 0.02d;

                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);

                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);

                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);

                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);

                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);

                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);

                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);

                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
            }
        }
    }
}

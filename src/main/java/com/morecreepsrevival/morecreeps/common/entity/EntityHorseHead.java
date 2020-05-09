package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nullable;

public class EntityHorseHead extends EntityCreepBase
{
    private int floatDir;

    private double floatCycle;

    private double floatMaxCycle;

    private int blastOff;

    private int gallopTime;

    private int blastOffTimer;

    public EntityHorseHead(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Horse Head");

        setSize(0.6f, 2.0f);

        setModelSize(1.6f);

        floatDir = 1;

        floatCycle = 0.0d;

        floatMaxCycle = 0.10499999672174454d;

        gallopTime = 0;

        blastOff = rand.nextInt(500) + 400;

        blastOffTimer = 0;

        baseHealth = 25.0f;

        baseSpeed = 0.0d;

        updateAttributes();
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 0)
        {
            dropItem(Items.PORKCHOP, rand.nextInt(3) + 1);
        }

        if (rand.nextInt(10) == 0)
        {
            dropItem(Items.WHEAT_SEEDS, rand.nextInt(3) + 1);
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.horseHeadSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.hippoHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.hippoDeathSound;
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/horsehead.png");
    }

    @Override
    protected void initEntityAI()
    {
        clearAITasks();

        tasks.addTask(1, new EntityAISwimming(this));
    }

    @Override
    public void onUpdate()
    {
        if (world.isRemote)
        {
            for (int i = 0; i < 2; i++)
            {
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, posX, (posY - 0.5d) + rand.nextGaussian() * 0.2d, posZ, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d);
            }
        }

        if (isEntityInsideOpaqueBlock())
        {
            posY += 2.5d;

            floatDir = 1;

            floatCycle = 0.0d;
        }

        fallDistance = -100.0f;

        if (!isBeingRidden() && blastOff-- < 0)
        {
            motionY += 0.15049999952316284d;

            double d = -MathHelper.sin((rotationYaw * (float)Math.PI) / 180.0f);

            double d1 = MathHelper.cos((rotationYaw * (float)Math.PI) / 180.0f);

            motionX += d * 0.10999999940395355d;

            motionZ += d1 * 0.10999999940395355d;

            if (world.isRemote)
            {
                for (int i = 0; i < 5; i++)
                {
                    world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, posX, (posY - 0.5d) + rand.nextGaussian() * 0.2d, posZ, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d);
                }
            }

            if (posY > 100.0d)
            {
                setDead();
            }
        }

        if (!isBeingRidden() && blastOff > 0 && world.isAirBlock(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY) - 1, MathHelper.floor(posZ))))
        {
            posY -= 0.25d;
        }

        ignoreFrustumCheck = true;

        if (floatDir > 0)
        {
            floatCycle += 0.017999999225139618d;

            if (floatCycle > floatMaxCycle)
            {
                floatDir *= -1;

                fallDistance -= 1.5f;
            }
        }
        else
        {
            floatCycle -= 0.0094999996945261955d;

            if (floatCycle < -floatMaxCycle)
            {
                floatDir *= -1;

                fallDistance -= 1.5f;
            }
        }

        if (isBeingRidden() && getControllingPassenger() instanceof EntityPlayer)
        {
            blastOff++;

            if (blastOff > 50000)
            {
                blastOff = 50000;
            }
        }

        super.onUpdate();
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (hand == EnumHand.OFF_HAND)
        {
            return false;
        }
        else if (player.isBeingRidden())
        {
            player.sendMessage(new TextComponentString("Unmount all creatures before riding your Horse Head."));

            return false;
        }

        rotationYaw = player.rotationYaw;

        rotationPitch = player.rotationPitch;

        player.fallDistance = -15.0f;

        if (!world.isRemote)
        {
            player.startRiding(this);
        }

        blastOff += rand.nextInt(500) + 200;

        motionX = 0.0d;

        motionY = 0.0d;

        motionZ = 0.0d;

        return true;
    }

    @Override @Nullable
    public Entity getControllingPassenger()
    {
        return getFirstPassenger();
    }

    @Override
    public void travel(float strafe, float vertical, float forward)
    {
        motionY *= 0.80000001192092896d;

        Entity ridingEntity = getControllingPassenger();

        if (ridingEntity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)ridingEntity;

            baseSpeed = 1.95d;

            updateMoveSpeed();

            player.lastTickPosY = 0.0d;

            prevRotationYaw = rotationYaw = player.rotationYaw;

            prevRotationPitch = rotationPitch = 0.0f;

            float f = 1.0f;

            double moveSpeed = player.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();

            if (moveSpeed > 0.01d && moveSpeed < 10.0d)
            {
                f = (float)moveSpeed;
            }

            forward = (player.moveForward / f) * (float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue() * 4.95f;

            strafe = (player.moveStrafing / f) * (float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue() * 4.95f;

            if (onGround && (forward != 0.0f || strafe != 0.0f))
            {
                motionY += 0.16100040078163147d;
            }

            if (forward == 0.0f && strafe == 0.0f)
            {
                isJumping = false;

                gallopTime = 0;
            }

            if (forward != 0.0f && gallopTime++ > 10)
            {
                gallopTime = 0;

                if (handleWaterMovement())
                {
                    playSound(CreepsSoundHandler.giraffeSplashSound, getSoundVolume(), 1.2f);
                }
                else
                {
                    playSound(CreepsSoundHandler.giraffeGallopSound, getSoundVolume(), 1.2f);
                }
            }

            if (onGround && !isJumping)
            {
                isJumping = ObfuscationReflectionHelper.getPrivateValue(EntityLivingBase.class, player, 40);

                if (isJumping)
                {
                    motionY += 0.37000000476837158d;
                }
            }

            if (onGround && isJumping)
            {
                double d = Math.abs(Math.sqrt(motionX * motionX + motionZ * motionZ));

                if (d > 0.13d)
                {
                    double d2 = 0.13d / d;

                    motionX = motionX * d2;

                    motionZ = motionZ * d2;
                }

                motionX *= 6.9500000000000002d;
                motionZ *= 6.9500000000000002d;
            }

            if (MoreCreepsAndWeirdos.proxy.isJumpKeyDown(player) && posY < 120.0d)
            {
                motionY += 0.15049999952316284d;

                double d1 = -MathHelper.sin((player.rotationYaw * (float)Math.PI) / 180.0f);

                double d3 = MathHelper.cos((player.rotationYaw * (float)Math.PI) / 180.0f);

                motionX += d1 * 0.15999999642372131d;

                motionZ += d3 * 0.15999999642372131d;

                if (blastOffTimer-- < 0)
                {
                    playSound(CreepsSoundHandler.horseHeadBlastOffSound, 1.0f, 1.0f);

                    blastOffTimer = 10;
                }

                if (world.isRemote)
                {
                    for (int i = 0; i < 5; i++)
                    {
                        world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, posX, (posY - 0.5d) + rand.nextGaussian() * 0.2d, posZ, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d);
                    }
                }
            }
        }
        else
        {
            baseSpeed = 0.95;

            updateMoveSpeed();
        }

        super.travel(strafe, vertical, forward);
    }
}

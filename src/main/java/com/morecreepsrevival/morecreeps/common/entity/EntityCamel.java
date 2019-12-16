package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IJumpingMount;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityCamel extends EntityCreepBase
{
    private static final String[] textures = {
            "textures/entity/camel",
            "textures/entity/camel",
            "textures/entity/camel",
            "textures/entity/camel",
            "textures/entity/camelwhite",
            "textures/entity/camelblack",
            "textures/entity/camelbrown",
            "textures/entity/camelgrey",
            "textures/entity/camel",
            "textures/entity/camel",
            "textures/entity/camel",
            "textures/entity/camel",
            "textures/entity/camelwhite"
    };

    private static final String[] names = {
            "Stanley", "Cid", "Hunchy", "The Heat", "Herman the Hump", "Dr. Hump", "Little Lousie", "Spoony G", "Mixmaster C", "The Maestro",
            "Duncan the Dude", "Charlie Camel", "Chip", "Charles Angstrom III", "Mr. Charles", "Cranky Carl", "Carl the Rooster", "Tiny the Peach", "Desert Dan", "Dungby",
            "Doofus"
    };

    private int gallopTime = 0;

    public EntityCamel(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Camel");

        setSize(width * 1.5f, height * 4.0f);

        setModelSize(1.75f);

        baseAttackDamage = 2.0d;

        baseHealth = 30.0f;

        baseSpeed = 0.2d;

        updateAttributes();
    }

    @Override
    public void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        nodeProcessor.setCanEnterDoors(true);

        tasks.addTask(1, new EntityAISwimming(this));

        tasks.addTask(2, new EntityAIBreakDoor(this));

        tasks.addTask(3, new EntityAIAttackMelee(this, 1.0d, true));

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));
    }

    @Override
    protected String[] getAvailableTextures()
    {
        return textures;
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        if (getFirstPassenger() != null)
        {
            return false;
        }

        return super.isEntityInsideOpaqueBlock();
    }

    @Override
    public void onLivingUpdate()
    {
        ignoreFrustumCheck = (getModelSize() > 1.0f);

        super.onLivingUpdate();
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
            dropItem(Items.REEDS, rand.nextInt(3) + 1);
        }
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.camelSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.camelHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.camelDeathSound;
    }

    @Override
    protected String[] getTamedNames()
    {
        return names;
    }

    @Override
    public float getBlockPathWeight(BlockPos blockPos)
    {
        Block block = world.getBlockState(blockPos).getBlock();

        if (block == Blocks.SAND || block == Blocks.GRAVEL)
        {
            return 10.0f;
        }

        return super.getBlockPathWeight(blockPos);
    }

    @Override
    public boolean isTamable()
    {
        return true;
    }

    @Override @Nullable
    public Entity getControllingPassenger()
    {
        return getFirstPassenger();
    }

    @Override
    public void updatePassenger(@Nonnull Entity passenger)
    {
        if (isPassenger(passenger))
        {
            if (passenger instanceof EntityPlayer)
            {
                passenger.setPosition(posX, posY + 3.0d - (double)((1.75f - getModelSize()) * 2.0f), posZ);

                return;
            }
            else if (passenger instanceof EntityCamelJockey)
            {
                passenger.setPosition(posX, posY + 3.15000009536743163d - (double)((1.75f - getModelSize()) * 2.0f), posZ);

                return;
            }
        }

        super.updatePassenger(passenger);
    }

    @Override
    public boolean isRideable()
    {
        return true;
    }

    @Override
    public void travel(float strafe, float vertical, float forward)
    {
        if (isBeingRidden() && canBeSteered())
        {
            Entity controllingPassenger = getControllingPassenger();

            if (!(controllingPassenger instanceof EntityLivingBase))
            {
                return;
            }

            EntityLivingBase riddenByEntity = (EntityLivingBase)controllingPassenger;

            baseSpeed = 1.95d;

            updateMoveSpeed();

            riddenByEntity.lastTickPosY = 0.0d;

            prevRotationYaw = rotationYaw = riddenByEntity.rotationYaw;

            prevRotationPitch = rotationPitch = 0.0f;

            float f = 1.0f;

            double moveSpeed = riddenByEntity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();

            if (moveSpeed > 0.01d && moveSpeed < 10.0d)
            {
                f = (float)moveSpeed;
            }

            forward = (riddenByEntity.moveForward / f) * (float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue() * 1.95f;

            strafe = (riddenByEntity.moveStrafing / f) * (float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue() * 1.95f;

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
                if (MoreCreepsAndWeirdos.proxy.isJumpKeyDown())
                {
                    isJumping = true;
                }

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

                motionX *= 2.95d;
                motionZ *= 2.95d;
            }
        }
        else
        {
            baseSpeed = 0.2d;

            updateMoveSpeed();
        }

        super.travel(strafe, vertical, forward);
    }
}

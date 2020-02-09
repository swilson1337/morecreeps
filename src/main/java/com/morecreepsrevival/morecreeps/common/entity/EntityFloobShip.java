package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityFloobShip extends EntityCreepBase implements IMob
{
    private static final DataParameter<Integer> lifespan = EntityDataManager.createKey(EntityFloobShip.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> floobCounter = EntityDataManager.createKey(EntityFloobShip.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> landed = EntityDataManager.<Boolean>createKey(EntityFloobShip.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Float> bump = EntityDataManager.createKey(EntityFloobShip.class, DataSerializers.FLOAT);

    private static final DataParameter<Boolean> firstReset = EntityDataManager.<Boolean>createKey(EntityFloobShip.class, DataSerializers.BOOLEAN);

    public EntityFloobShip(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Floob Ship");

        creatureType = EnumCreatureType.MONSTER;

        baseHealth = (float)rand.nextInt(100) + 50.0f;

        baseSpeed = 0.0d;

        setSize(4.0f, 3.0f);

        motionX = rand.nextFloat() * 0.8f;

        motionZ = rand.nextFloat() * 0.8f;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(lifespan, rand.nextInt(10000) + 1500);

        dataManager.register(floobCounter, rand.nextInt(500) + 400);

        dataManager.register(landed, Boolean.valueOf(false));

        dataManager.register(bump, 2.0f);

        dataManager.register(firstReset, Boolean.valueOf(false));
    }

    @Override
    public void fall(float distance, float damageMultiplier)
    {
    }

    @Override
    protected void updateFallState(double y, boolean onGroundIn, @Nonnull IBlockState state, @Nonnull BlockPos pos)
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
            this.motionX *= f;
            this.motionY *= f;
            this.motionZ *= f;
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
        if (dataManager.get(lifespan) > 0)
        {
            return CreepsSoundHandler.floobShipDeathSound;
        }

        return null;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (dataManager.get(floobCounter) > 0)
        {
            dataManager.set(floobCounter, dataManager.get(floobCounter) - 1);
        }

        if (dataManager.get(lifespan) > 0)
        {
            dataManager.set(lifespan, dataManager.get(lifespan) - 1);
        }

        if (dataManager.get(lifespan) < 1)
        {
            setDead();
        }

        if (handleWaterMovement())
        {
            motionY += 0.2800000011920929d;

            motionX += 0.97999999999999998d;

            motionX += 0.97999999999999998d;
        }

        if (getLanded())
        {
            motionY = 0.0d;

            dataManager.set(bump, 0.0f);
        }

        if (!getLanded() || !onGround)
        {
            if (posY < 100.0d && !getFirstReset())
            {
                motionY = 4.0d;

                dataManager.set(bump, 4.0f);

                setFirstReset(true);
            }

            motionY = -0.2f + dataManager.get(bump);

            dataManager.set(bump, dataManager.get(bump) * 0.95999999999999996f);

            motionX *= 0.97999999999999998d;

            motionZ *= 0.97999999999999998d;

            if (onGround)
            {
                Block block = world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(getEntityBoundingBox().minY) - 1, MathHelper.floor(posZ))).getBlock();

                if (block == Blocks.FLOWING_WATER || block == Blocks.WATER || block == Blocks.LEAVES || block == Blocks.CACTUS)
                {
                    thrusters();

                    dataManager.set(bump, 3.0f);

                    motionX = rand.nextFloat() * 2.8f;

                    motionY = rand.nextFloat() * 0.6f;

                    motionZ = rand.nextFloat() * 2.8f;
                }
                else
                {
                    setLanded(true);
                }
            }
        }
        else if (dataManager.get(floobCounter) < 1)
        {
            thrusters();

            dataManager.set(floobCounter, rand.nextInt(300) + 400);

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

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource damageSource, float amt)
    {
        if (damageSource.getTrueSource() instanceof EntityPlayer)
        {
            thrusters();

            playSound(CreepsSoundHandler.floobShipClangSound, 1.0f, getSoundPitch());
        }

        if (rand.nextInt(10) == 0)
        {
            dataManager.set(bump, (float)rand.nextInt(3));

            motionX = rand.nextFloat() * 0.8f;

            motionZ = rand.nextFloat() * 0.8f;
        }

        Block block = world.getBlockState(new BlockPos(MathHelper.floor(posX), MathHelper.floor(getEntityBoundingBox().minY) - 1, MathHelper.floor(posZ))).getBlock();

        if (block == Blocks.FLOWING_WATER || block == Blocks.WATER || block == Blocks.LEAVES || block == Blocks.CACTUS)
        {
            thrusters();

            dataManager.set(bump, 3.0f);

            motionX = rand.nextFloat() * 0.8f;

            motionY = rand.nextFloat() * 0.8f;

            motionZ = rand.nextFloat() * 0.8f;
        }

        return super.attackEntityFrom(damageSource, amt);
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

    @Override
    public void onDeath(@Nonnull DamageSource cause)
    {
        super.onDeath(cause);

        if (!world.isRemote && dataManager.get(lifespan) > 0 && MoreCreepsConfig.floobShipExplode)
        {
            world.createExplosion(null, posX, posY, posZ, 8.0f, true);
        }
    }

    private void setLanded(boolean b)
    {
        dataManager.set(landed, Boolean.valueOf(b));
    }

    public boolean getLanded()
    {
        return ((Boolean)dataManager.get(landed)).booleanValue();
    }

    private void setFirstReset(boolean b)
    {
        dataManager.set(firstReset, Boolean.valueOf(b));
    }

    public boolean getFirstReset()
    {
        return ((Boolean)dataManager.get(firstReset)).booleanValue();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsFloobShip");

        props.setBoolean("Landed", getLanded());

        props.setInteger("FloobCounter", dataManager.get(floobCounter));

        props.setBoolean("FirstReset", getFirstReset());

        props.setInteger("Lifespan", dataManager.get(lifespan));

        compound.setTag("MoreCreepsFloobShip", props);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsFloobShip");

        if (props.hasKey("Landed"))
        {
            setLanded(props.getBoolean("Landed"));
        }

        if (props.hasKey("FloobCounter"))
        {
            dataManager.set(floobCounter, props.getInteger("FloobCounter"));
        }

        if (props.hasKey("FirstReset"))
        {
            setFirstReset(props.getBoolean("FirstReset"));
        }

        if (props.hasKey("Lifespan"))
        {
            dataManager.set(lifespan, props.getInteger("Lifespan"));
        }
    }
}

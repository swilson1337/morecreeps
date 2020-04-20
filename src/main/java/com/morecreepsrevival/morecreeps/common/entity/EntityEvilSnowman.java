package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityEvilSnowman extends EntityCreepBase implements IMob
{
    private int updateSizeTime = 0;

    public EntityEvilSnowman(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Evil Snowman");

        creatureType = EnumCreatureType.MONSTER;

        setSize(0.7f, 1.5f);

        baseHealth = 25.0f;

        baseSpeed = 0.3d;

        isImmuneToFire = true;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/evilsnowman.png");
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return true;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.snowmanSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.snowmanHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.snowmanDeathSound;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 0)
        {
            dropItem(Item.getItemFromBlock(Blocks.ICE), rand.nextInt(3) + 1);

            dropItem(Item.getItemFromBlock(Blocks.SNOW), rand.nextInt(10) + 1);
        }
        else
        {
            dropItem(Item.getItemFromBlock(Blocks.SNOW), rand.nextInt(5) + 2);
        }
    }

    @Override
    public void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        tasks.addTask(1, new EntityAISwimming(this));

        tasks.addTask(2, new EntityAIBreakDoor(this));

        tasks.addTask(3, new EntityAIAttackMelee(this, 1.0d, true));

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!onGround && !isJumping)
        {
            motionY -= 0.0020000000949949026d;
        }

        int x = MathHelper.floor(posX);

        int y = MathHelper.floor(getEntityBoundingBox().minY);

        int z = MathHelper.floor(posZ);

        if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == Blocks.SNOW)
        {
            setModelSize(getModelSize() + 0.001f);
        }
        else
        {
            setModelSize(getModelSize() + 0.002f);
        }

        if (inWater)
        {
            setModelSize(getModelSize() - 0.02f);
        }

        if (getModelSize() > 6.0f)
        {
            setModelSize(6.0f);
        }

        float f = getModelSize();

        if (updateSizeTime-- < 1)
        {
            setSize(f * 0.45f, f * 2.0f);

            updateSizeTime = 100;
        }

        if (f < 0.050000000000000003d)
        {
            setDead();
        }
    }

    @Override
    protected boolean shouldJumpWhileAttacking(Entity entity)
    {
        return true;
    }

    @Override
    protected void doAttackJump(Entity entity)
    {
        for (int i = 0; i < 8; i++)
        {
            playSound(CreepsSoundHandler.snowmanBounceSound, 1.0f, 2.0f - getModelSize());

            world.spawnParticle(EnumParticleTypes.SNOWBALL, posX, posY, posZ, 0.0d, 0.0d, 0.0d);
        }

        double d = entity.posX - posX;

        double d1 = entity.posZ - posZ;

        float f1 = MathHelper.sqrt(d * d + d1 * d1);

        motionX = (d / (double)f1) * 0.5D * 0.30000000192092896D + motionX * 0.20000000098023224D;

        motionZ = (d1 / (double)f1) * 0.5D * 0.25000000192092897D + motionZ * 0.20000000098023224D;

        motionY = 0.35000000196046449D;

        if (rand.nextInt(20) == 0)
        {
            double d2 = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);
            double d5 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180F);
            motionX -= d2 * 0.40000000596046448D;
            motionZ -= d5 * 0.40000000596046448D;
        }

        if (rand.nextInt(20) == 0)
        {
            double d3 = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);
            motionX -= d3 * 1.0D;
            motionY += 0.16599999368190765D;
        }

        if (rand.nextInt(40) == 0)
        {
            double d4 = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180F);
            double d7 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180F);
            motionX -= d4 * 0.30000001192092896D;
            motionZ -= d7 * 0.30000001192092896D;
            motionY += 0.76599997282028198D;
        }
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount)
    {
        boolean flag = super.attackEntityFrom(source, amount);

        if (flag)
        {
            Entity entity = source.getTrueSource();

            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer)entity;

                double d = -MathHelper.sin((player.rotationYaw * (float)Math.PI) / 180F);

                double d1 = MathHelper.cos((player.rotationYaw * (float)Math.PI) / 180F);

                motionX += d * 2D;

                motionZ += d1 * 2D;

                setModelSize(getModelSize() - 0.02f);
            }
        }

        return flag;
    }

    @Override
    public void fall(float dist, float damage)
    {
    }

    @Override
    protected float getSoundPitch()
    {
        return ((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f + (1.0f - getModelSize()) * 2.0f);
    }

    @Override
    public void knockBack(@Nonnull Entity entity, float strength, double xRatio, double zRatio)
    {
        float f = getModelSize();

        for (int j = 0; j < 8 + (int)(f * 20F); j++)
        {
            world.spawnParticle(EnumParticleTypes.SNOWBALL, posX, posY + (double)f, posZ, 0.0D, 0.0D, 0.0D);
        }

        strength *= strength;

        motionY += 0.33300000429153442D;

        xRatio *= 8.1999998092651367D;

        zRatio *= 8.3000001907348633D;

        super.knockBack(entity, strength, xRatio, zRatio);
    }
}

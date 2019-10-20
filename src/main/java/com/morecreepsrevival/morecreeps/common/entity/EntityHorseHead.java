package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityHorseHead extends EntityCreepBase
{
    public int floatDir;

    public double floatCycle;

    public double floatMaxCycle;

    public int blastOff;

    public EntityHorseHead(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Horse Head");

        setSize(0.6f, 2.0f);

        setModelSize(1.6f);

        floatDir = 1;

        floatCycle = 0.0d;

        floatMaxCycle = 0.10499999672174454d;

        blastOff = rand.nextInt(500) + 400;

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

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        nodeProcessor.setCanEnterDoors(true);

        tasks.addTask(1, new EntityAISwimming(this));

        tasks.addTask(2, new EntityAIBreakDoor(this));

        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(5, new EntityAILookIdle(this));
    }

    @Override
    public void onUpdate()
    {
        if (isEntityInsideOpaqueBlock())
        {
            posY += 2.5d;

            floatDir = 1;

            floatCycle = 0.0d;
        }

        fallDistance = -100.0f;

        if (!isBeingRidden() && blastOff-- < 1)
        {
            motionY += 0.15049999952316284d;

            double d = -MathHelper.sin((rotationYaw * (float)Math.PI) / 180.0f);

            double d1 = MathHelper.cos((rotationYaw * (float)Math.PI) / 180.0f);

            motionX += d * 0.10999999940395355d;

            motionZ += d1 * 0.10999999940395355d;

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

        if (isBeingRidden() && getRidingEntity() instanceof EntityPlayer)
        {
            blastOff++;

            if (blastOff > 50000)
            {
                blastOff = 50000;
            }
        }

        super.onUpdate();

        motionY *= 0.80000001192092896d;
    }
}

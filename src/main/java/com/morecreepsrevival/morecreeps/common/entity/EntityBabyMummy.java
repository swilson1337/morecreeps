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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityBabyMummy extends EntityCreepBase implements IMob
{
    private static final String[] textures = {
            "textures/entity/mummy1",
            "textures/entity/mummy2",
            "textures/entity/mummy3"
    };

    public EntityBabyMummy(World world)
    {
        super(world);

        setSize(0.6f, 0.6f);

        setCreepTypeName("Baby Mummy");

        creatureType = EnumCreatureType.MONSTER;

        spawnOnlyAtNight = true;

        baseHealth = 15.0f;

        baseSpeed = 0.25d;

        baseAttackDamage = 1.0d;

        setModelSize((rand.nextFloat() * 0.45f) + 0.25f);

        updateAttributes();
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(5) == 0)
        {
            dropItem(Item.getItemFromBlock(Blocks.WOOL), rand.nextInt(6) + 1);
        }
        else
        {
            dropItem(Item.getItemFromBlock(Blocks.SAND), rand.nextInt(3) + 1);
        }
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

        tasks.addTask(3, new EntityAIAttackMelee(this, 1.0d, true));

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.babyMummyHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.babyMummyDeathSound;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.babyMummySound;
    }

    @Override
    protected float getSoundPitch()
    {
        return ((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f + (0.7f - getModelSize()) * 2.0f);
    }

    @Override
    protected String[] getAvailableTextures()
    {
        return textures;
    }

    @Override
    protected boolean shouldJumpWhileAttacking(Entity entity)
    {
        return true;
    }

    @Override
    protected void doAttackJump(Entity entity)
    {
        rotationYaw = ((float)Math.toDegrees(Math.atan2(entity.posZ - posZ, entity.posX - posX))) - 90.0f;

        double d0 = entity.posX - posX;

        double d1 = entity.posZ - posZ;

        double f = MathHelper.sqrt(d0 * d0 + d1 * d1);

        motionX = (d0 / f) * 0.5d * 0.8000000019209289d + motionX * 0.18000000098023225d;

        motionZ = (d1 / f) * 0.5d * 0.70000000192092893d + motionZ * 0.18000000098023225d;

        fallDistance = -25.0f;
    }

    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entity)
    {
        double dist = getDistanceSq(entity);

        if (onGround && dist > 2.0d && dist < 6.0d && getBrightness() >= 0.5f && rand.nextInt(100) > 0 && rand.nextInt(10) == 0)
        {
            int x = MathHelper.floor(entity.posX);

            int y = MathHelper.floor(entity.posY);

            int z = MathHelper.floor(entity.posZ);

            if (world.getBlockState(new BlockPos(x, y - 2, z)).getBlock() == Blocks.SAND)
            {
                if (rand.nextInt(5) == 0)
                {
                    int r = rand.nextInt(4) + 1;

                    for (int i = 0; i < r; i++)
                    {
                        world.setBlockToAir(new BlockPos(x, y - (i + 2), z));

                        if (rand.nextInt(5) == 0)
                        {
                            world.setBlockToAir(new BlockPos(x + i, y - 2, z));
                        }

                        if (rand.nextInt(5) == 0)
                        {
                            world.setBlockToAir(new BlockPos(x, y - 2, z + i));
                        }
                    }
                }

                if (rand.nextInt(5) == 0)
                {
                    if (rand.nextInt(2) == 0)
                    {
                        int r = rand.nextInt(5);

                        for (int i = -3; i < 3; i++)
                        {
                            world.setBlockState(new BlockPos(x + i, y + r, z + 2), Blocks.SAND.getDefaultState());

                            world.setBlockState(new BlockPos(x + i, y + r, z - 2), Blocks.SAND.getDefaultState());
                        }
                    }

                    if (rand.nextInt(2) == 0)
                    {
                        int r = rand.nextInt(5);

                        for (int i = -3; i < 3; i++)
                        {
                            world.setBlockState(new BlockPos(x + 2, y + r, z + i), Blocks.SAND.getDefaultState());

                            world.setBlockState(new BlockPos(x - 2, y + r, z + i), Blocks.SAND.getDefaultState());
                        }
                    }
                }
            }
        }

        return super.attackEntityAsMob(entity);
    }
}

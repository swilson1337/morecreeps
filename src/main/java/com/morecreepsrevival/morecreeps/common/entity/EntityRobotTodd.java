package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityRobotTodd extends EntityCreepBase implements IMob
{
    public EntityRobotTodd(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Robot Todd");

        creatureType = EnumCreatureType.MONSTER;

        setSize(1.5f, 2.5f);

        setModelSize(2.5f);

        baseSpeed = 0.25d;

        baseHealth = (float)rand.nextInt(20) + 25.0f;

        baseAttackDamage = 2.0d;

        updateAttributes();
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/robottodd1.png");
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

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityRobotTed.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityRobotTed.class, true));
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.toddInsultSound;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource)
    {
        return CreepsSoundHandler.robotHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.toddDeadSound;
    }
}

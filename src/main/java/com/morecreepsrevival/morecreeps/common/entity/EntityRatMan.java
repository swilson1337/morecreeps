package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityRatMan extends EntityCreepBase implements IMob
{
    public EntityRatMan(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Rat Man");

        creatureType = EnumCreatureType.MONSTER;

        setModelSize(0.75f);

        stepHeight = 1.0f;

        baseHealth = 7.0f;

        baseSpeed = 0.3d;

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

        tasks.addTask(5, new EntityAIMoveThroughVillage(this, 1.0d, false));

        tasks.addTask(6, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(7, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(7, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/ratman.png");
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
        if (rand.nextInt(2) == 0)
        {
            return CreepsSoundHandler.ratManSound;
        }

        return CreepsSoundHandler.ratManScratchSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.ratManHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.ratManHurtSound;
    }
}

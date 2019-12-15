package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityRocketGiraffe extends EntityCreepBase
{
    private static final String[] names = {
            "Rory", "Stan", "Clarence", "FirePower", "Lightning", "Rocket Jockey", "Rocket Ralph", "Tim"
    };

    public EntityRocketGiraffe(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Rocket Giraffe");

        setSize(1.5f, 4.0f);

        baseSpeed = 0.325d;

        baseHealth = (float)rand.nextInt(50) + 30.0f;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/rocketgiraffe.png");
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
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.giraffeSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.giraffeHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.giraffeDeadSound;
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
            dropItem(CreepsItemHandler.rocket, rand.nextInt(5) + 1);
        }
    }

    @Override
    public void onDeath(@Nonnull DamageSource damageSource)
    {
        smoke();

        super.onDeath(damageSource);
    }
}

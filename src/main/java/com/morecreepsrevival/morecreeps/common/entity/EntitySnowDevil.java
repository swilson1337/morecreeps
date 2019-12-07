package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntitySnowDevil extends EntityCreepBase
{
    private static final String[] textures = {
            "textures/entity/snowdevil1",
            "textures/entity/snowdevil2"
    };

    public EntitySnowDevil(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Snow Devil");

        creatureType = EnumCreatureType.MONSTER;

        setSize(width * 1.6f, height * 1.6f);

        baseHealth = (float)rand.nextInt(50) + 15.0f;

        baseAttackDamage = 3.0d;

        baseSpeed = 0.3d;

        updateAttributes();
    }

    @Override
    protected String[] getAvailableTextures()
    {
        return textures;
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
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.snowDevilSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.snowDevilHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.snowDevilDeathSound;
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
}

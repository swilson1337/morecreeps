package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityZebra extends EntityCreepBase
{
    private static final String[] names = {
            "Stanley", "Cid", "Hunchy", "The Heat", "Herman the Hump", "Dr. Hump", "Little Lousie", "Spoony G", "Mixmaster C", "The Maestro",
            "Duncan the Dude", "Charlie Camel", "Chip", "Charles Angstrom III", "Mr. Charles", "Cranky Carl", "Carl the Rooster", "Tiny the Peach", "Desert Dan", "Dungby",
            "Doofus"
    };

    public EntityZebra(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Zebra");

        baseHealth = 25.0f;

        baseAttackDamage = 2.0d;

        baseSpeed = 0.325f;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/zebra.png");
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

        if (block == Blocks.LEAVES || block == Blocks.GRASS)
        {
            return 10.0f;
        }

        return super.getBlockPathWeight(blockPos);
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
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 0)
        {
            dropItem(Items.PORKCHOP, rand.nextInt(3) + 1);
        }

        if (rand.nextInt(2) == 0)
        {
            dropItem(CreepsItemHandler.zebraHide, 1);
        }
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 5;
    }

    @Override
    public void onLivingUpdate()
    {
        ignoreFrustumCheck = true;

        super.onLivingUpdate();
    }

    @Override @Nullable
    public Entity getControllingPassenger()
    {
        return getFirstPassenger();
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
}

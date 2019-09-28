package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityMummy extends EntityCreepBase
{
    public EntityMummy(World world)
    {
        super(world);

        setCreepTypeName("Mummy");

        creatureType = EnumCreatureType.MONSTER;

        baseHealth = 30.0f;

        baseSpeed = 0.25d;

        baseAttackDamage = 2.0d;

        updateAttributes();
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

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.mummyHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.mummyDeathSound;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.mummySound;
    }

    @Override
    protected boolean shouldBurnInDay()
    {
        return true;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(5) == 0)
        {
            dropItem(Item.getItemFromBlock(Blocks.SAND), rand.nextInt(6) + 1);

            dropItem(Item.getItemFromBlock(Blocks.SANDSTONE), rand.nextInt(3) + 1);
        }
        else if (rand.nextInt(5) == 0)
        {
            dropItem(CreepsItemHandler.bandaid, rand.nextInt(8) + 1);
        }
        else
        {
            dropItem(Item.getItemFromBlock(Blocks.SAND), rand.nextInt(2) + 1);
        }
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/mummy.png");
    }

    @Override
    protected float getSoundPitch()
    {
        return ((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f + (1.6f - getModelSize()) * 2.0f);
    }
}

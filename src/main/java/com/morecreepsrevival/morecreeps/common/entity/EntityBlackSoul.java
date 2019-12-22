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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityBlackSoul extends EntityCreepBase implements IMob
{
    private static final String[] textures = {
            "textures/entity/blacksoul"
    };

    public EntityBlackSoul(World world)
    {
        super(world);

        setCreepTypeName("Black Soul");

        creatureType = EnumCreatureType.MONSTER;

        spawnOnlyAtNight = true;

        baseHealth = 50.0f;

        baseSpeed = 0.33d;

        baseAttackDamage = 7.0d;

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

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(50) == 0)
        {
            dropItem(Items.DIAMOND, rand.nextInt(2) + 1);
        }
        else
        {
            dropItem(Items.COAL, rand.nextInt(5) + 1);
        }
    }

    @Override
    protected boolean shouldBurnInDay()
    {
        return true;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.blackSoulHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.blackSoulDeathSound;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.blackSoulSound;
    }

    @Override
    protected String[] getAvailableTextures()
    {
        return textures;
    }

    @Override
    protected float getSoundPitch()
    {
        return ((rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f + (0.6f - getModelSize()) * 2.0f);
    }
}

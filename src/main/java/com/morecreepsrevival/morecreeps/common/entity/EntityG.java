package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityG extends EntityCreepBase implements IMob
{
    public EntityG(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("G");

        creatureType = EnumCreatureType.MONSTER;

        setSize(width * 2.0f, height * 2.5f);

        setModelSize(2.0f);

        baseHealth = (float)rand.nextInt(40) + 40.0f;

        baseSpeed = 0.3d;

        baseAttackDamage = 2.0d;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/g.png");
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
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
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.gSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.gHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.gDeathSound;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        int maxItems = 0;

        if (rand.nextInt(200) == 98)
        {
            dropItem(Item.getItemFromBlock(Blocks.GOLD_BLOCK), 1);

            maxItems++;
        }

        if (rand.nextInt(5) == 0)
        {
            dropItem(Items.GOLD_INGOT, rand.nextInt(2) + 1);

            maxItems++;
        }

        if (rand.nextInt(150) > 145)
        {
            dropItem(Items.GOLDEN_SWORD, 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(Items.GOLDEN_PICKAXE, 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(Items.GOLDEN_SHOVEL, 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(Items.GOLDEN_AXE, 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(Items.GOLDEN_HELMET, 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(Items.GOLDEN_CHESTPLATE, 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(Items.GOLDEN_BOOTS, 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 80)
        {
            dropItem(Items.WHEAT, rand.nextInt(6) + 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(Item.getItemFromBlock(Blocks.GLASS), rand.nextInt(6) + 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(CreepsItemHandler.gooDonut, rand.nextInt(3) + 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 88)
        {
            dropItem(Item.getItemFromBlock(Blocks.GRASS), rand.nextInt(6) + 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(Item.getItemFromBlock(Blocks.GLOWSTONE), rand.nextInt(2) + 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(Items.GLOWSTONE_DUST, rand.nextInt(2) + 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(Items.GLASS_BOTTLE, rand.nextInt(2) + 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(Items.GOLD_NUGGET, rand.nextInt(2) + 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) == 88)
        {
            dropItem(Items.GOLDEN_APPLE, rand.nextInt(2) + 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) > 98)
        {
            dropItem(Items.GUNPOWDER, rand.nextInt(2) + 1);

            maxItems++;
        }

        if (maxItems < 3 && rand.nextInt(100) == 88)
        {
            dropItem(Items.GHAST_TEAR, rand.nextInt(2) + 1);
        }
    }
}

package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntitySneakySal extends EntityCreepBase implements IRangedAttackMob
{
    private DataParameter<Integer> dissedMax = EntityDataManager.createKey(EntitySneakySal.class, DataSerializers.VARINT);

    private DataParameter<Integer> sale = EntityDataManager.createKey(EntitySneakySal.class, DataSerializers.VARINT);

    private DataParameter<Float> salePrice = EntityDataManager.createKey(EntitySneakySal.class, DataSerializers.FLOAT);

    public EntitySneakySal(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Sneaky Sal");

        setSize(1.5f, 4.0f);

        setModelSize(1.5f);

        setHeldItem(EnumHand.MAIN_HAND, new ItemStack(CreepsItemHandler.gun));

        baseHealth = (float)rand.nextInt(50) + 50.0f;

        baseAttackDamage = 3.0d;

        baseSpeed = 0.3f;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(dissedMax, rand.nextInt(4) + 1);

        dataManager.register(sale, rand.nextInt(2000) + 100);

        dataManager.register(salePrice, 0.0f);
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/sneakysal.png");
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

        tasks.addTask(3, new EntityAIAttackRanged(this, 1.0d, 25, 75, 50.0f));

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
    }

    @Override
    public void attackEntityWithRangedAttack(@Nonnull EntityLivingBase target, float distanceFactor)
    {
    }

    @Override
    public void setSwingingArms(boolean swingingArms)
    {
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (rand.nextInt(10) == 0)
        {
            return CreepsSoundHandler.giraffeSound;
        }

        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.salHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.salDeadSound;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (hand == EnumHand.OFF_HAND)
        {
            return super.processInteract(player, hand);
        }
        else if (dataManager.get(dissedMax) > 0)
        {
            if (dataManager.get(salePrice) == 0.0f || dataManager.get(sale) < 1)
            {
                restock();
            }

            if (!(getAttackTarget() instanceof EntityPlayer))
            {
                // TODO: open gui
            }
        }

        return super.processInteract(player, hand);
    }

    @Override
    public void onLivingUpdate()
    {
        if (dataManager.get(sale) > 0)
        {
            dataManager.set(sale, dataManager.get(sale) - 1);
        }

        if (rand.nextInt(10) == 0)
        {
            // TODO: smoke
        }
    }

    private void restock()
    {
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
    public void onDeath(@Nonnull DamageSource cause)
    {
        // TODO: smoke

        super.onDeath(cause);
    }
}

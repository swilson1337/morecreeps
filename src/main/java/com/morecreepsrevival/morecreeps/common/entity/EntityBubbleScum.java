package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityBubbleScum extends EntityCreepBase
{
    public EntityBubbleScum(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Bubble Scum");

        setSize(0.5f, 0.5f);

        baseSpeed = 0.3d;

        baseHealth = (float)rand.nextInt(15) + 5.0f;

        updateAttributes();
    }

    @Override
    public void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        nodeProcessor.setCanEnterDoors(true);

        if (!isRiding())
        {
            tasks.addTask(1, new EntityAISwimming(this));

            tasks.addTask(2, new EntityAIBreakDoor(this));

            tasks.addTask(3, new EntityAIAttackMelee(this, 1.0d, true));

            tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

            tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

            tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

            tasks.addTask(6, new EntityAILookIdle(this));

            targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        }
    }

    @Override
    public boolean canMount(Entity entity)
    {
        if (getAttackTarget() instanceof EntityPlayer)
        {
            return false;
        }

        return super.canMount(entity);
    }

    @Override
    public boolean canRidePlayer()
    {
        return true;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(25) == 0)
        {
            dropItem(Items.COOKIE, 1);
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (!isRiding() && rand.nextInt(1) == 0)
        {
            return CreepsSoundHandler.bubbleScumSound;
        }

        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.bubbleScumHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.bubbleScumDeathSound;
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/bubblescum.png");
    }

    @Override
    protected SoundEvent getMountSound()
    {
        return CreepsSoundHandler.bubbleScumPickupSound;
    }

    @Override
    protected SoundEvent getUnmountSound()
    {
        return CreepsSoundHandler.bubbleScumPutDownSound;
    }

    @Override
    protected void onDismount(Entity entity)
    {
        double d = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180.0f);

        double d1 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180.0f);

        double d2 = -MathHelper.sin((entity.rotationPitch / 180.0f) * (float)Math.PI);

        motionX = 1.0d * d;

        motionZ = 1.0d * d1;

        motionY = 1.0d * d2;
    }
}

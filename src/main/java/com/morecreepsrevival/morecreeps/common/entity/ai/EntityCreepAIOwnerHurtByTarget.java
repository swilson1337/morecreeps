package com.morecreepsrevival.morecreeps.common.entity.ai;

import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

public class EntityCreepAIOwnerHurtByTarget extends EntityAITarget
{
    private EntityCreepBase tamable;

    private EntityLivingBase attacker;

    private int timestamp;

    public EntityCreepAIOwnerHurtByTarget(EntityCreepBase tamableIn)
    {
        super(tamableIn, false);

        tamable = tamableIn;

        setMutexBits(1);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityPlayer owner = tamable.getOwner();

        if (owner != null)
        {
            attacker = owner.getRevengeTarget();

            if (owner.getRevengeTimer() != timestamp && isSuitableTarget(attacker, false) && tamable.shouldAttackEntity(attacker))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        if (tamable.getOwner() == null)
        {
            return false;
        }

        return super.shouldContinueExecuting();
    }

    @Override
    public void startExecuting()
    {
        taskOwner.setAttackTarget(attacker);

        EntityPlayer owner = tamable.getOwner();

        if (owner != null)
        {
            timestamp = owner.getRevengeTimer();
        }

        super.startExecuting();
    }
}

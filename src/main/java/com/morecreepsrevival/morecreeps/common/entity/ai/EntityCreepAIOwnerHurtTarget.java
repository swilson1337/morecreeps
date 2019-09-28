package com.morecreepsrevival.morecreeps.common.entity.ai;

import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;

public class EntityCreepAIOwnerHurtTarget extends EntityAITarget
{
    private EntityCreepBase tamable;

    private EntityLivingBase attacker;

    private int timestamp;

    public EntityCreepAIOwnerHurtTarget(EntityCreepBase tamableIn)
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
            attacker = owner.getLastAttackedEntity();

            if (owner.getLastAttackedEntityTime() != timestamp && isSuitableTarget(attacker, false) && tamable.shouldAttackEntity(attacker))
            {
                return true;
            }
        }

        return false;
    }

    @Override
    public void startExecuting()
    {
        taskOwner.setAttackTarget(attacker);

        EntityPlayer owner = tamable.getOwner();

        if (owner != null)
        {
            timestamp = owner.getLastAttackedEntityTime();
        }

        super.startExecuting();
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
}

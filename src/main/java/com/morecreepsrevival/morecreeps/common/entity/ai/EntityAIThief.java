package com.morecreepsrevival.morecreeps.common.entity.ai;

import com.morecreepsrevival.morecreeps.common.entity.EntityThief;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIThief extends EntityAIBase
{
    private final EntityThief thief;

    public EntityAIThief(EntityThief thiefIn)
    {
        thief = thiefIn;

        setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        return (thief.getAttackTarget() != null && !thief.getStolen());
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        return (thief.getAttackTarget() != null && !thief.getStolen());
    }

    @Override
    public void updateTask()
    {
        EntityLivingBase entity = thief.getAttackTarget();

        if (entity != null)
        {
            thief.getLookHelper().setLookPositionWithEntity(entity, 10.0f, 10.0f);

            thief.getNavigator().tryMoveToEntityLiving(entity, 1.0d);
        }
    }
}

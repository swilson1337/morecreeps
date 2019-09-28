package com.morecreepsrevival.morecreeps.common.entity.ai;

import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAITarget;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.EntityLivingBase;
import java.util.List;

public class EntityCreepAIFollowOwnerTarget extends EntityAITarget
{
    private EntityCreepBase tamable;

    private EntityLivingBase target;

    public EntityCreepAIFollowOwnerTarget(EntityCreepBase tamableIn)
    {
        super(tamableIn, false);

        tamable = tamableIn;

        setMutexBits(1);
    }

    protected List<EntityLiving> getPossibleTargets()
    {
        return tamable.world.getEntities(EntityLiving.class, (entity) -> entity != null && !entity.isDead);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityPlayer owner = tamable.getOwner();

        if (owner != null)
        {
            for (EntityLiving entity : getPossibleTargets())
            {
                if (owner.equals(entity.getAttackTarget()))
                {
                    target = entity;

                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void startExecuting()
    {
        tamable.setAttackTarget(target);

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

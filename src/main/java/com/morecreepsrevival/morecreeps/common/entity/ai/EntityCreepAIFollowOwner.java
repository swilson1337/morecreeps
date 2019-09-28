package com.morecreepsrevival.morecreeps.common.entity.ai;

import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNodeType;

public class EntityCreepAIFollowOwner extends EntityAIBase
{
    private final EntityCreepBase tamable;

    private final double followSpeed;

    private final float minDist;

    private final float maxDist;

    private int timeToRecalcPath = 0;

    private float oldWaterCost = 0.0f;

    public EntityCreepAIFollowOwner(EntityCreepBase tamableIn, double followSpeedIn, float minDistIn, float maxDistIn)
    {
        tamable = tamableIn;

        followSpeed = followSpeedIn;

        minDist = minDistIn;

        maxDist = maxDistIn;

        setMutexBits(3);
    }

    @Override
    public boolean shouldExecute()
    {
        EntityPlayer owner = tamable.getOwner();

        if (owner == null || owner.isSpectator() || tamable.getDistanceSq(owner) < (double)(minDist * minDist))
        {
            return false;
        }

        return true;
    }

    @Override
    public boolean shouldContinueExecuting()
    {
        EntityPlayer owner = tamable.getOwner();

        return (!tamable.getNavigator().noPath() && owner != null && tamable.getDistanceSq(owner) > (double)(maxDist * maxDist));
    }

    @Override
    public void startExecuting()
    {
        timeToRecalcPath = 0;

        oldWaterCost = tamable.getPathPriority(PathNodeType.WATER);

        tamable.setPathPriority(PathNodeType.WATER, 0.0f);
    }

    @Override
    public void resetTask()
    {
        tamable.getNavigator().clearPath();

        tamable.setPathPriority(PathNodeType.WATER, oldWaterCost);
    }

    @Override
    public void updateTask()
    {
        tamable.getLookHelper().setLookPositionWithEntity(tamable.getOwner(), 10.f, (float)tamable.getVerticalFaceSpeed());

        if (--timeToRecalcPath <= 0)
        {
            timeToRecalcPath = 10;

            PathNavigate navigator = tamable.getNavigator();

            EntityPlayer owner = tamable.getOwner();

            navigator.tryMoveToEntityLiving(owner, followSpeed);

            /*if (!navigator.tryMoveToEntityLiving(owner, followSpeed) && !tamable.getLeashed() && !tamable.isRiding() && tamable.getDistanceSq(owner) >= 144.0d)
            {
                int i = MathHelper.floor(owner.posX) - 2;

                int j = MathHelper.floor(owner.posZ) - 2;

                int k = MathHelper.floor(owner.getEntityBoundingBox().minY);

                for (int l = 0; l <= 4; ++l)
                {
                    for (int i1 = 0; i1 <= 4; ++i1)
                    {
                        if ((l < 1 || i1 < 1 || l > 3 || i1 > 3) && isTeleportFriendlyBlock(i, j, k, l, i1))
                        {
                            tamable.setLocationAndAngles((double)((float)(i + l) + 0.5f), (double)k, (double)((float)(j + i1) + 0.5f), tamable.rotationYaw, tamable.rotationPitch);

                            navigator.clearPath();

                            return;
                        }
                    }
                }
            }*/
        }
    }

    /*protected boolean isTeleportFriendlyBlock(int i, int j, int k, int yaw, int pitch)
    {
        BlockPos blockPos = new BlockPos(i + yaw, k - 1, j + pitch);

        IBlockState blockState = tamable.world.getBlockState(blockPos);

        return (blockState.getBlockFaceShape(tamable.world, blockPos, EnumFacing.DOWN) == BlockFaceShape.SOLID && blockState.canEntitySpawn(tamable) && tamable.world.isAirBlock(blockPos.up()) && tamable.world.isAirBlock(blockPos.up(2)));
    }*/
}

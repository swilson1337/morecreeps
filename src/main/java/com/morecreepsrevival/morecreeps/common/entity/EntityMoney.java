package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.capabilities.ILawyerFine;
import com.morecreepsrevival.morecreeps.common.capabilities.LawyerFineProvider;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityMoney extends EntityCreepItem
{
    public EntityMoney(World world)
    {
        super(world, new ItemStack(CreepsItemHandler.money));
    }

    public EntityMoney(World world, double x, double y, double z)
    {
        super(world, x, y, z, new ItemStack(CreepsItemHandler.money));
    }

    public EntityMoney(World world, Entity entity)
    {
        this(world);

        double d = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180.0f);

        double d1 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180.0f);

        setPosition(entity.posX + d * 0.80000000000000004d, entity.posY + 2, entity.posZ + d1 * 0.80000000000000004d);

        rotationYaw = entity.rotationYaw;

        motionX = 0.59999999999999998d * d * (double) MathHelper.cos((entity.rotationPitch / 180.0f) * (float)Math.PI);

        motionY = -0.69999999999999996d * (double)MathHelper.sin((entity.rotationPitch / 180.0f) * (float)Math.PI);

        motionZ = 0.5999999999999999d * d1 * (double)MathHelper.cos((entity.rotationPitch / 180.0f) * (float)Math.PI);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (!onGround)
        {
            for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(motionX, motionY, motionZ).grow(0.5d, 0.5d, 0.5d)))
            {
                if (entity.canBeCollidedWith())
                {
                    if (entity instanceof EntityLawyerFromHell)
                    {
                        EntityPlayer thrower = getThrowerEntity();

                        if (thrower != null)
                        {
                            ILawyerFine capability = thrower.getCapability(LawyerFineProvider.capability, null);

                            if (capability != null)
                            {
                                capability.takeFine(50);
                            }
                        }

                        setDead();
                    }
                }
            }
        }
    }
}

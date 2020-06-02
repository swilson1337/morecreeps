package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import java.util.List;

public class EntityGrowbotGregg extends EntityCreepBase
{
    private int texSwitch = 0;

    private int texNumber = 0;

    private int aggroCooldown = 0;

    private int attackCounter = 0;

    public EntityGrowbotGregg(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Growbot Gregg");

        setModelSize(1.5f);

        baseHealth = (float)rand.nextInt(15) + 10.0f;

        baseSpeed = 0.3d;

        setHeldItem(EnumHand.MAIN_HAND, new ItemStack(CreepsItemHandler.growRay));

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        switch (texNumber)
        {
            case 0:
                setTexture("textures/entity/growbotgregg1.png");

                break;
            case 1:
                setTexture("textures/entity/growbotgregg2.png");

                break;
            case 2:
                setTexture("textures/entity/growbotgregg3.png");

                break;
            default:
                setTexture("textures/entity/growbotgregg.png");

                break;
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.greggSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.greggHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.greggDeathSound;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (texSwitch++ > 60)
        {
            if (texNumber++ > 2)
            {
                texNumber = 0;
            }

            updateTexture();
        }

        Entity targetedEntity = getAttackTarget();

        if (targetedEntity != null && targetedEntity.getDistanceSq(this) < 25.0d)
        {
            baseSpeed = 0.0d;
        }
        else
        {
            baseSpeed = 0.5d;
        }

        updateMoveSpeed();

        if (targetedEntity instanceof EntityGrowbotGregg)
        {
            setAttackTarget(null);

            targetedEntity = null;
        }

        if (targetedEntity == null || aggroCooldown-- <= 0)
        {
            List<Entity> list = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(16.0d, 16.0d, 16.0d));

            if (list.size() > 0)
            {
                int i = rand.nextInt(list.size());

                Entity entity1 = list.get(i);

                if (entity1 instanceof EntityCreepBase)
                {
                    setAttackTarget((EntityLivingBase)entity1);

                    targetedEntity = entity1;
                }
            }

            if (targetedEntity != null)
            {
                aggroCooldown = 60;
            }
        }

        double var9 = 64.0d;

        if (targetedEntity != null && targetedEntity.getDistanceSq(this) < (var9 * var9))
        {
            double var11 = targetedEntity.posX - posX;

            double var13 = targetedEntity.getEntityBoundingBox().minY + (targetedEntity.width / 2.0f) - posY + (width / 2.0f);

            double var15 = targetedEntity.posZ - posZ;

            if (canEntityBeSeen(targetedEntity))
            {
                attackCounter++;

                if (attackCounter == 20)
                {
                    playSound(CreepsSoundHandler.growRaySound, 0.5f, 0.4f / (rand.nextFloat() * 0.4f + 0.8f));

                    double d5 = targetedEntity.posX - posX;

                    double d6 = targetedEntity.getEntityBoundingBox().minY + (targetedEntity.width / 2.0f) - posX + (width / 2.0f);

                    double d7 = targetedEntity.posZ - posZ;

                    EntityGrow grow = new EntityGrow(world, this);

                    if (!world.isRemote)
                    {
                        world.spawnEntity(grow);
                    }

                    attackCounter = -40;
                }
            }
            else if (attackCounter > 0)
            {
                attackCounter--;
            }
        }
    }

    @Override
    protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier)
    {
    }
}

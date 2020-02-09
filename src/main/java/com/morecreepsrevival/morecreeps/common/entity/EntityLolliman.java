package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityLolliman extends EntityCreepBase
{
    private static final DataParameter<Boolean> kidMounted = EntityDataManager.<Boolean>createKey(EntityLolliman.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> kidCheck = EntityDataManager.createKey(EntityLolliman.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> rocketTime = EntityDataManager.createKey(EntityLolliman.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> treats = EntityDataManager.createKey(EntityLolliman.class, DataSerializers.VARINT);

    public EntityLolliman(World world)
    {
        super(world);

        setCreepTypeName("Lolliman");

        setSize(0.9f, 3.0f);

        setModelSize(2.0f);

        baseHealth = 25.0f;

        baseSpeed = 0.3d;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/lolliman.png");
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(kidMounted, Boolean.valueOf(false));

        dataManager.register(kidCheck, 0);

        dataManager.register(rocketTime, 0);

        dataManager.register(treats, 0);
    }

    @Override
    public void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        nodeProcessor.setCanEnterDoors(true);

        if (!getKidMounted())
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
    public boolean isEntityInsideOpaqueBlock()
    {
        if (getKidMounted())
        {
            return false;
        }

        return super.isEntityInsideOpaqueBlock();
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 0)
        {
            dropItem(CreepsItemHandler.lolly, rand.nextInt(3) + 1);
        }

        if (rand.nextInt(10) == 0)
        {
            dropItem(CreepsItemHandler.sundae, rand.nextInt(3) + 1);
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.lollimanSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.lollimanHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.lollimanDeathSound;
    }

    protected void setKidMounted(boolean b)
    {
        dataManager.set(kidMounted, Boolean.valueOf(b));
    }

    public boolean getKidMounted()
    {
        return ((Boolean)dataManager.get(kidMounted)).booleanValue();
    }

    @Override
    protected boolean shouldJumpWhileAttacking(Entity entity)
    {
        return true;
    }

    @Override
    protected void doAttackJump(Entity entity)
    {
        rotationYaw = ((float)Math.toDegrees(Math.atan2(entity.posZ - posZ, entity.posX - posX))) - 90.0f;

        double d0 = entity.posX - posX;

        double d1 = entity.posZ - posZ;

        double f = MathHelper.sqrt(d0 * d0 + d1 * d1);

        motionX = (d0 / f) * 0.20000000000000001d * (0.850000011920929d + motionX * 0.20000000298023224d);

        motionZ = (d1 / f) * 0.20000000000000001d * (0.80000001192092896d + motionZ * 0.20000000298023224d);

        motionY = 0.10000000596246449d;

        fallDistance = -25.0f;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!getKidMounted())
        {
            dataManager.set(kidCheck, dataManager.get(kidCheck) + 1);

            if (dataManager.get(kidCheck) > 10)
            {
                dataManager.set(kidCheck, 0);

                for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(2.0d, 2.0d, 2.0d)))
                {
                    if (entity instanceof EntityKid)
                    {
                        playSound(CreepsSoundHandler.lollimanTakeOffSound, getSoundVolume(), getSoundPitch());

                        // TODO: give player achievement

                        baseSpeed = 0.0d;

                        updateMoveSpeed();

                        motionY = 0.6000000238418579d;

                        if (!world.isRemote)
                        {
                            entity.startRiding(this);

                            setKidMounted(true);
                        }

                        break;
                    }
                }
            }
        }
        else
        {
            motionY = 0.25d;

            dataManager.set(rocketTime, dataManager.get(rocketTime) + 1);

            if (dataManager.get(rocketTime) > 5)
            {
                dataManager.set(rocketTime, 0);

                int randInt = rand.nextInt(2) + 1;

                for (int i = 0; i < randInt; i++)
                {
                    dataManager.set(treats, dataManager.get(treats) + 1);

                    if (dataManager.get(treats) == 30)
                    {
                        playSound(CreepsSoundHandler.lollimanExplodeSound, getSoundVolume(), getSoundPitch());
                    }

                    if (!world.isRemote && rand.nextInt(3) == 0)
                    {
                        EntityItem entityItem;

                        switch (rand.nextInt(4))
                        {
                            case 1:
                                entityItem = entityDropItem(new ItemStack(Items.COOKIE, 1), 1.0f);

                                break;
                            case 2:
                                entityItem = entityDropItem(new ItemStack(Items.CAKE, 1), 1.0f);

                                break;
                            case 4:
                                entityItem = entityDropItem(new ItemStack(CreepsItemHandler.sundae, 1), 1.0f);

                                break;
                            default:
                                entityItem = entityDropItem(new ItemStack(CreepsItemHandler.lolly, 1), 1.0f);

                                break;
                        }

                        if (entityItem != null)
                        {
                            entityItem.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.2f;

                            entityItem.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.2f;
                        }
                    }

                    if (posY > 100.0d || dataManager.get(treats) > 55)
                    {
                        for (Entity entity : getPassengers())
                        {
                            entity.setDead();
                        }

                        setDead();

                        if (dataManager.get(treats) > 50)
                        {
                            world.createExplosion(this, posX, posY + 2.0d, posZ, 2.5f, true);
                        }
                    }
                }
            }
        }
    }
}

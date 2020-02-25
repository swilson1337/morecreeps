package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.entity.ai.EntityCreepAIFollowOwner;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityHunchback extends EntityCreepBase
{
    private static final DataParameter<Integer> cakeTimer = EntityDataManager.createKey(EntityHunchback.class, DataSerializers.VARINT);

    public EntityHunchback(World world)
    {
        super(world);

        setCreepTypeName("Hunchback");

        baseSpeed = 0.3f;

        baseHealth = (float)rand.nextInt(30) + 10.0f;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(cakeTimer, 0);
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

        tasks.addTask(3, new EntityAIAttackMelee(this, 1.0d, true));

        if (isTamed())
        {
            tasks.addTask(4, new EntityCreepAIFollowOwner(this, 1.0d, 6.0f, 2.0f));
        }
        else
        {
            tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));
        }

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void updateTexture()
    {
        if (isTamed())
        {
            setTexture("textures/entity/hunchbackcake.png");
        }
        else
        {
            setTexture("textures/entity/hunchback.png");
        }
    }

    private void setCakeTimer(int i)
    {
        dataManager.set(cakeTimer, i);
    }

    public int getCakeTimer()
    {
        return dataManager.get(cakeTimer);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (hand == EnumHand.OFF_HAND)
        {
            return super.processInteract(player, hand);
        }

        ItemStack itemStack = player.getHeldItem(hand);

        if (isEntityAlive())
        {
            if (isTamed())
            {
                if (itemStack.getItem() == Items.BONE)
                {
                    smoke();

                    smoke();

                    playSound(CreepsSoundHandler.guineaPigArmorSound, getSoundVolume(), getSoundPitch());

                    playSound(CreepsSoundHandler.hunchArmySound, 2.0f, getSoundPitch());

                    for (int i = 0; i < 5; i++)
                    {
                        EntityHunchbackSkeleton skeleton = new EntityHunchbackSkeleton(world);

                        skeleton.setLocationAndAngles(posX + 3.0d, posY, posZ + (double)i, rotationYaw, 0.0f);

                        skeleton.setModelSize(getModelSize());

                        skeleton.determineBaseTexture();

                        skeleton.setInitialHealth();

                        skeleton.tame(player);

                        if (!world.isRemote)
                        {
                            world.spawnEntity(skeleton);
                        }
                    }

                    itemStack.shrink(1);

                    return true;
                }
            }
            else if (itemStack.getItem() == Items.CAKE || Item.getIdFromItem(itemStack.getItem()) == 92)
            {
                smoke();

                tame(player);

                if (getCakeTimer() < 4000)
                {
                    setCakeTimer(getCakeTimer() + rand.nextInt(500) + 250);
                }

                itemStack.shrink(1);

                return true;
            }
        }

        return super.processInteract(player, hand);
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount)
    {
        Entity entity = source.getTrueSource();

        if (entity != null)
        {
            double d = -MathHelper.sin((entity.rotationYaw * (float)Math.PI) / 180.0f);

            double d1 = MathHelper.cos((entity.rotationYaw * (float)Math.PI) / 180.0f);

            if (entity instanceof EntityPlayer && isTamed())
            {
                motionY = rand.nextFloat() * 0.9f;

                motionZ = d1 * 0.40000000596046448d;

                motionX = d * 0.5d;

                playSound(CreepsSoundHandler.hunchThankYouSound, 2.0f, getSoundPitch());

                return super.attackEntityFrom(source, amount / 6);
            }
            else if (amount > 0 && entity instanceof EntityLivingBase)
            {
                motionY = (rand.nextFloat() - rand.nextFloat()) * 0.3f;

                motionZ = d1 * 0.40000000596046448d;

                motionX = d * 0.5d;

                playSound(CreepsSoundHandler.hunchHurtSound, 2.0f, getSoundPitch());

                setAttackTarget((EntityLivingBase)entity);
            }
        }

        return super.attackEntityFrom(source, amount);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (getAttackTarget() instanceof EntityHunchbackSkeleton)
        {
            setAttackTarget(null);
        }

        if (isTamed() && getCakeTimer() > 0 && rand.nextInt(10) == 0)
        {
            setCakeTimer(getCakeTimer() - 1);

            if (getCakeTimer() == 0)
            {
                clearOwner();

                updateTexture();

                setCakeTimer(rand.nextInt(700) + 300);
            }
        }
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (!isTamed())
        {
            if (rand.nextInt(5) == 0)
            {
                dropItem(Items.PORKCHOP, rand.nextInt(3) + 1);
            }
            else
            {
                dropItem(Item.getItemFromBlock(Blocks.SAND), rand.nextInt(3) + 1);
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (isTamed())
        {
            return CreepsSoundHandler.hunchQuietSound;
        }
        else if (rand.nextInt(3) == 0)
        {
            return CreepsSoundHandler.hunchCakeSound;
        }

        return CreepsSoundHandler.hunchQuietSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.hunchHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.hunchDeathSound;
    }

    @Override
    protected SoundEvent getTamedSound()
    {
        return CreepsSoundHandler.hunchThankYouSound;
    }

    @Override
    protected float getBaseHealth()
    {
        float health = super.getBaseHealth();

        if (isTamed())
        {
            health += 2.0f;
        }

        return health;
    }
}

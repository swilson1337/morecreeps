package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityManDog extends EntityCreepBase
{
    private static final DataParameter<Boolean> superDog = EntityDataManager.<Boolean>createKey(EntityManDog.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> tamedFood = EntityDataManager.createKey(EntityManDog.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> chase = EntityDataManager.<Boolean>createKey(EntityManDog.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Boolean> fetch = EntityDataManager.<Boolean>createKey(EntityManDog.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> frisbeeTime = EntityDataManager.createKey(EntityManDog.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> frisbeeEnt = EntityDataManager.createKey(EntityManDog.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> attempts = EntityDataManager.createKey(EntityManDog.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> frisbeeHold = EntityDataManager.<Boolean>createKey(EntityManDog.class, DataSerializers.BOOLEAN);

    private double dist = 0.0f;

    private double prevDist = 0.0f;

    public EntityManDog(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Mandog");

        baseHealth = 45.0f;

        baseSpeed = 0.333d;

        updateAttributes();
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

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(superDog, Boolean.valueOf(false));

        dataManager.register(tamedFood, rand.nextInt(3) + 1);

        dataManager.register(chase, Boolean.valueOf(false));

        dataManager.register(fetch, Boolean.valueOf(false));

        dataManager.register(frisbeeTime, 0);

        dataManager.register(frisbeeEnt, 0);

        dataManager.register(attempts, 0);

        dataManager.register(frisbeeHold, Boolean.valueOf(false));
    }

    @Override
    protected void updateTexture()
    {
        if (isTamed())
        {
            setTexture("textures/entity/mandogtamed.png");
        }
        else
        {
            setTexture("textures/entity/mandog.png");
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (hand == EnumHand.OFF_HAND)
        {
            return super.processInteract(player, hand);
        }

        ItemStack itemStack = player.getHeldItem(hand);

        if (itemStack.isEmpty())
        {
            if (isTamed())
            {
                setChase(false);

                setFetch(false);

                setFrisbeeEntity(null);
            }
        }
        else
        {
            Item item = itemStack.getItem();

            if (item == Items.COOKED_PORKCHOP && !isTamed())
            {
                itemStack.shrink(1);

                setTamedFood(getTamedFood() - 1);

                smoke();

                if (getTamedFood() < 1)
                {
                    smoke();

                    tame(player);
                }

                return true;
            }
        }

        return super.processInteract(player, hand);
    }

    @Override
    protected SoundEvent getTamedSound()
    {
        return CreepsSoundHandler.manDogTamedSound;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (getSuperDog())
        {
            return CreepsSoundHandler.superDogNameSound;
        }

        return CreepsSoundHandler.manDogSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.manDogHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.manDogDeathSound;
    }

    protected void setSuperDog(boolean b)
    {
        dataManager.set(superDog, Boolean.valueOf(b));
    }

    public boolean getSuperDog()
    {
        return ((Boolean)dataManager.get(superDog)).booleanValue();
    }

    protected void setTamedFood(int i)
    {
        dataManager.set(tamedFood, i);
    }

    public int getTamedFood()
    {
        return dataManager.get(tamedFood);
    }

    protected void setChase(boolean b)
    {
        dataManager.set(chase, Boolean.valueOf(b));
    }

    public boolean getChase()
    {
        return ((Boolean)dataManager.get(chase)).booleanValue();
    }

    protected void setFetch(boolean b)
    {
        dataManager.set(fetch, Boolean.valueOf(b));
    }

    public boolean getFetch()
    {
        return ((Boolean)dataManager.get(fetch)).booleanValue();
    }

    protected void setFrisbeeTime(int i)
    {
        dataManager.set(frisbeeTime, i);
    }

    public int getFrisbeeTime()
    {
        return dataManager.get(frisbeeTime);
    }

    protected void setFrisbeeEntity(Entity entity)
    {
        if (entity == null)
        {
            dataManager.set(frisbeeEnt, 0);

            return;
        }

        dataManager.set(frisbeeEnt, entity.getEntityId());
    }

    public Entity getFrisbeeEntity()
    {
        if (dataManager.get(frisbeeEnt) == 0)
        {
            return null;
        }

        return world.getEntityByID(dataManager.get(frisbeeEnt));
    }

    protected void setAttempts(int i)
    {
        dataManager.set(attempts, i);
    }

    public int getAttempts()
    {
        return dataManager.get(attempts);
    }

    protected void setFrisbeeHold(boolean b)
    {
        dataManager.set(frisbeeHold, Boolean.valueOf(b));
    }

    public boolean getFrisbeeHold()
    {
        return ((Boolean)dataManager.get(frisbeeHold)).booleanValue();
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        dropItem(Items.BONE, 1);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (isTamed())
        {
            setFrisbeeTime(getFrisbeeTime() + 1);

            if (getFrisbeeTime() >= 20 && !isDead && !getChase() && !getFetch())
            {
                for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(25.0d, 25.0d, 25.0d)))
                {
                    if (entity instanceof EntityFrisbee)
                    {
                        faceEntity(entity, 360.0f, 0.0f);

                        setFrisbeeEntity(entity);

                        setChase(true);

                        setAttempts(0);
                    }
                }
            }

            Entity frisbeeEntity = getFrisbeeEntity();

            if (getChase())
            {
                if (frisbeeEntity == null || frisbeeEntity.isDead)
                {
                    setChase(false);

                    setFrisbeeTime(0);
                }
                else
                {
                    if (Math.abs(posY - frisbeeEntity.posY) < 2.0d)
                    {
                        faceEntity(frisbeeEntity, 360.0f, 0.0f);
                    }

                    fallDistance = -25.0f;

                    prevDist = dist;

                    dist = frisbeeEntity.posX - posX;

                    if (dist == prevDist)
                    {
                        if (rand.nextInt(2) == 0)
                        {
                            motionX += 0.75d;

                            motionZ += 0.75d;
                        }
                        else
                        {
                            motionX -= 0.75d;

                            motionZ -= 0.75d;
                        }
                    }

                    if (Math.abs(frisbeeEntity.posX - posX) < 1.0d && Math.abs(frisbeeEntity.posY - posY) < 1.0d && Math.abs(frisbeeEntity.posZ - posZ) < 1.0d)
                    {
                        playSound(SoundEvents.BLOCK_LAVA_POP, 0.2f, ((rand.nextFloat() - rand.nextFloat()) * 0.7f + 1.0f) * 2.0f);

                        frisbeeEntity.setDead();

                        setChase(false);

                        setFetch(true);

                        setFrisbeeHold(true);

                        EntityPlayer player = getOwner();

                        if (player != null)
                        {
                            setFrisbeeEntity(player);

                            faceEntity(player, 360.0f, 0.0f);
                        }
                    }

                    double d = frisbeeEntity.posX - posX;

                    double d2 = frisbeeEntity.posZ - posZ;

                    float f = MathHelper.sqrt(d * d + d2 * d2);

                    motionX = (d / (double)f) * 0.40000000000000002D * 0.50000000192092897D + motionX * 0.18000000098023225D;

                    motionZ = (d2 / (double)f) * 0.40000000000000002D * 0.40000000192092894D + motionZ * 0.18000000098023225D;

                    if (onGround)
                    {
                        double d4 = (frisbeeEntity.posY - posY) * 0.18000000098023225d;

                        if (d4 > 0.5d)
                        {
                            d4 = 0.5d;
                        }

                        motionY = d4;
                    }

                    if (Math.abs(frisbeeEntity.posX - posX) < 5.0d && Math.abs(frisbeeEntity.posZ - posZ) < 5.0d && frisbeeEntity.motionX == 0.0d)
                    {
                        setAttempts(getAttempts() + 1);

                        if (getAttempts() > 100)
                        {
                            setChase(false);

                            setFrisbeeTime(0);

                            setFrisbeeEntity(null);

                            setFetch(true);

                            setFrisbeeHold(false);

                            EntityPlayer player = getOwner();

                            if (player != null)
                            {
                                setFrisbeeEntity(player);

                                faceEntity(player, 360.0f, 0.0f);
                            }
                        }
                    }
                }
            }

            if (getFetch())
            {
                frisbeeEntity = getFrisbeeEntity();

                if (frisbeeEntity != null)
                {
                    if (Math.abs(frisbeeEntity.posX - posX) < 2.0d && Math.abs(frisbeeEntity.posY - posY) < 2.0d && Math.abs(frisbeeEntity.posZ - posZ) < 2.0d)
                    {
                        playSound(SoundEvents.BLOCK_LAVA_POP, 0.2f, ((rand.nextFloat() - rand.nextFloat()) * .07f + 1.0f) * 2.0f);

                        if (!world.isRemote && getFrisbeeHold())
                        {
                            dropItem(CreepsItemHandler.frisbee, 1);
                        }

                        setChase(false);

                        setFetch(false);
                    }

                    fallDistance = -25.0f;

                    double d1 = frisbeeEntity.posX - posX;

                    double d3 = frisbeeEntity.posZ - posZ;

                    float f1 = MathHelper.sqrt(d1 * d1 + d3 * d3);

                    motionX = (d1 / (double)f1) * 0.40000000000000002D * 0.50000000192092897D + motionX * 0.18000000098023225D;

                    motionZ = (d3 / (double)f1) * 0.40000000000000002D * 0.40000000192092894D + motionZ * 0.18000000098023225D;

                    if (onGround)
                    {
                        double d5 = (frisbeeEntity.posY - posY) * 0.18000000098023225D;

                        if (d5 > 0.5D)
                        {
                            d5 = 0.5D;
                        }

                        motionY = d5;
                    }
                }
            }
        }
    }

    @Override
    public boolean isTamable()
    {
        return true;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsManDog");

        props.setInteger("Attempts", getAttempts());

        props.setBoolean("FrisbeeHold", getFrisbeeHold());

        props.setBoolean("Chase", getChase());

        props.setBoolean("Fetch", getFetch());

        props.setInteger("TamedFood", getTamedFood());

        compound.setTag("MoreCreepsManDog", props);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsManDog");

        if (props.hasKey("Attempts"))
        {
            setAttempts(props.getInteger("Attempts"));
        }

        if (props.hasKey("FrisbeeHold"))
        {
            setFrisbeeHold(props.getBoolean("FrisbeeHold"));
        }

        if (props.hasKey("Chase"))
        {
            setChase(props.getBoolean("Chase"));
        }

        if (props.hasKey("Fetch"))
        {
            setFetch(props.getBoolean("Fetch"));
        }

        if (props.hasKey("TamedFood"))
        {
            setTamedFood(props.getInteger("TamedFood"));
        }
    }
}

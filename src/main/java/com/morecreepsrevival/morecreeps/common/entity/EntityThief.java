package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.entity.ai.EntityAIThief;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityThief extends EntityCreepBase
{
    private static final DataParameter<Boolean> stolen = EntityDataManager.createKey(EntityThief.class, DataSerializers.BOOLEAN);

    private static final DataParameter<ItemStack> ITEM = EntityDataManager.createKey(EntityThief.class, DataSerializers.ITEM_STACK);

    private double goX;

    private double goZ;

    public EntityThief(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Thief");

        baseHealth = (float)rand.nextInt(20) + 10.0f;

        baseSpeed = 0.35d;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(stolen, false);

        dataManager.register(ITEM, ItemStack.EMPTY);
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/thief.png");
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

        tasks.addTask(3, new EntityAIThief(this));

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (getHeldItem(EnumHand.MAIN_HAND).getItem() != dataManager.get(ITEM).getItem())
        {
            setHeldItem(EnumHand.MAIN_HAND, dataManager.get(ITEM).copy());
        }

        if (world.getClosestPlayerToEntity(this, 25.0d) != null && !getStolen())
        {
            findPlayerToAttack();
        }
        else
        {
            setAttackTarget(null);
        }

        if (getStolen())
        {
            setAttackTarget(null);

            if (isJumping)
            {
                motionX += goX * 0.30000001192092896d;

                motionZ += goZ * 0.30000001192092896d;
            }
            else
            {
                motionX += goX;

                motionZ += goZ;
            }

            if (prevPosY / posY == 1.0d)
            {
                if (rand.nextInt(25) == 0)
                {
                    motionX -= goX;
                }
                else
                {
                    motionX += goX;
                }

                if (rand.nextInt(25) == 0)
                {
                    motionZ -= goZ;
                }
                else
                {
                    motionZ += goZ;
                }
            }

            if (prevPosX == posX && rand.nextInt(50) == 0)
            {
                goX *= -1.0d;
            }

            if (prevPosZ == posZ && rand.nextInt(50) == 0)
            {
                goZ *= -1.0d;
            }

            if (rand.nextInt(500) == 0)
            {
                goX *= -1.0d;
            }

            if (rand.nextInt(700) == 0)
            {
                goZ *= -1.0d;
            }
        }
        else
        {
            EntityLivingBase target = getAttackTarget();

            if (target instanceof EntityPlayer && getDistanceSq(target) < 16.0d && canEntityBeSeen(target) && getHealth() > 0)
            {
                EntityPlayer player = (EntityPlayer)target;

                ItemStack itemStack = null;

                for (ItemStack itemStack1 : player.inventory.mainInventory)
                {
                    if (!itemStack1.isEmpty())
                    {
                        itemStack = itemStack1;

                        if (rand.nextInt(4) == 0)
                        {
                            break;
                        }
                    }
                }

                if (itemStack == null)
                {
                    setAttackTarget(null);
                }
                else
                {
                    playSound(SoundEvents.BLOCK_LAVA_POP, getSoundVolume(), (rand.nextFloat() - rand.nextFloat()) * 6.2f + 1.0f);

                    int count = itemStack.getCount();

                    int stolenAmount = rand.nextInt(count) + 1;

                    if (stolenAmount > count)
                    {
                        stolenAmount = count;
                    }

                    setStolen(true);

                    if (!world.isRemote)
                    {
                        ItemStack copy = itemStack.copy();

                        copy.setCount(stolenAmount);

                        dataManager.set(ITEM, copy);

                        dataManager.setDirty(ITEM);
                    }

                    itemStack.shrink(stolenAmount);

                    playSound(CreepsSoundHandler.thiefStealSound, getSoundVolume(), getSoundPitch());

                    setAttackTarget(null);

                    goX = 0.044999999999999998d;

                    goZ = 0.044999999999999998d;

                    if (rand.nextInt(5) == 0)
                    {
                        goX *= -1.0d;
                    }

                    if (rand.nextInt(5) == 0)
                    {
                        goZ *= -1.0d;
                    }

                    for (int i = 0; i < 10; i++)
                    {
                        world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (posX + (double)(rand.nextFloat() * width * 2.0f)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0f)) - (double)width, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d);
                    }
                }
            }
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.thiefSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.thiefHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.thiefDeathSound;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    public void setStolen(boolean b)
    {
        dataManager.set(stolen, b);
    }

    public boolean getStolen()
    {
        return dataManager.get(stolen);
    }

    public void findPlayerToAttack()
    {
        if (getStolen() || getAttackTarget() != null)
        {
            return;
        }

        EntityPlayer player = world.getNearestPlayerNotCreative(this, 16.0d);

        if (player != null)
        {
            for (ItemStack itemStack : player.inventory.mainInventory)
            {
                if (!itemStack.isEmpty())
                {
                    if (rand.nextInt(2) == 0)
                    {
                        playSound(CreepsSoundHandler.thiefFindPlayerSound, getSoundVolume(), getSoundPitch());
                    }

                    setAttackTarget(player);

                    return;
                }
            }
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsThief");

        props.setBoolean("Stolen", getStolen());

        props.setTag("Item", dataManager.get(ITEM).writeToNBT(new NBTTagCompound()));

        compound.setTag("MoreCreepsThief", props);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsThief");

        if (props.hasKey("Stolen"))
        {
            setStolen(props.getBoolean("Stolen"));
        }

        dataManager.set(ITEM, new ItemStack(props.getCompoundTag("Item")));

        dataManager.setDirty(ITEM);
    }

    @Override
    protected void dropItemsOnDeath()
    {
        ItemStack itemStack = dataManager.get(ITEM).copy();

        if (!itemStack.isEmpty())
        {
            entityDropItem(itemStack, 0.0f);
        }
    }

    @Override
    protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier)
    {
    }
}

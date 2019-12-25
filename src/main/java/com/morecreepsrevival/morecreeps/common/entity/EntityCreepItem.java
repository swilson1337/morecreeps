package com.morecreepsrevival.morecreeps.common.entity;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.stats.StatList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackData;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.UUID;

public class EntityCreepItem extends Entity
{
    private static final DataParameter<ItemStack> ITEM = EntityDataManager.createKey(EntityCreepItem.class, DataSerializers.ITEM_STACK);

    private static final DataParameter<Integer> pickupDelay = EntityDataManager.createKey(EntityCreepItem.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> age = EntityDataManager.createKey(EntityCreepItem.class, DataSerializers.VARINT);

    private static final DataParameter<String> thrower = EntityDataManager.createKey(EntityCreepItem.class, DataSerializers.STRING);

    private static final DataParameter<Integer> lifespan = EntityDataManager.createKey(EntityCreepItem.class, DataSerializers.VARINT);

    private static final DataParameter<String> owner = EntityDataManager.createKey(EntityCreepItem.class, DataSerializers.STRING);

    public EntityCreepItem(World world)
    {
        super(world);

        setSize(0.25f, 0.25f);
    }

    public EntityCreepItem(World world, ItemStack itemStack)
    {
        this(world);

        setItem(new ItemStack(itemStack.getItem(), itemStack.getCount()));
    }

    public EntityCreepItem(World world, double x, double y, double z, ItemStack itemStack)
    {
        this(world, itemStack);

        setPosition(x, y, z);
    }

    public EntityCreepItem(World world, Entity entity, ItemStack itemStack)
    {
        this(world, entity.posX, entity.posY, entity.posZ, itemStack);
    }

    @Override
    protected void entityInit()
    {
        dataManager.register(ITEM, ItemStack.EMPTY);

        dataManager.register(pickupDelay, 0);

        dataManager.register(age, 0);

        dataManager.register(thrower, "");

        dataManager.register(lifespan, 6000);

        dataManager.register(owner, "");
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        compound.setInteger("Age", getAge());

        compound.setInteger("PickupDelay", getPickupDelay());

        compound.setInteger("Lifespan", getLifespan());

        compound.setString("Thrower", getThrower());

        compound.setString("Owner", getOwner());

        ItemStack itemStack = getItem();

        if (!itemStack.isEmpty())
        {
            compound.setTag("Item", itemStack.writeToNBT(new NBTTagCompound()));
        }
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        if (compound.hasKey("Age"))
        {
            setAge(compound.getInteger("Age"));
        }

        if (compound.hasKey("PickupDelay"))
        {
            setPickupDelay(compound.getInteger("PickupDelay"));
        }

        if (compound.hasKey("Owner"))
        {
            setOwner(compound.getString("Owner"));
        }

        if (compound.hasKey("Thrower"))
        {
            setThrower(compound.getString("Thrower"));
        }

        setItem(new ItemStack(compound.getCompoundTag("Item")));

        if (getItem().isEmpty())
        {
            setDead();
        }

        if (compound.hasKey("Lifespan"))
        {
            setLifespan(compound.getInteger("Lifespan"));
        }
    }

    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }

    @Override
    public void onUpdate()
    {
        ItemStack item = getItem();

        if (item.isEmpty())
        {
            setDead();

            return;
        }

        super.onUpdate();

        int delay = getPickupDelay();

        if (delay > 0 && delay != 32767)
        {
            setPickupDelay(delay - 1);
        }

        prevPosX = posX;

        prevPosY = posY;

        prevPosZ = posZ;

        double d0 = motionX;

        double d1 = motionY;

        double d2 = motionZ;

        if (!hasNoGravity())
        {
            motionY -= 0.03999999910593033d;
        }

        if (world.isRemote)
        {
            noClip = false;
        }
        else
        {
            AxisAlignedBB boundingBox = getEntityBoundingBox();

            noClip = pushOutOfBlocks(posX, (boundingBox.minY + boundingBox.maxY) / 2.0d, posZ);
        }

        move(MoverType.SELF, motionX, motionY, motionZ);

        boolean flag = ((int)prevPosX != (int)posX || (int)prevPosY != (int)posY || (int)prevPosZ != (int)posZ);

        if (flag || (ticksExisted % 25) == 0)
        {
            if (world.getBlockState(new BlockPos(this)).getMaterial() == Material.LAVA)
            {
                motionY = 0.20000000298023224d;

                motionX = (rand.nextFloat() - rand.nextFloat()) * 0.2f;

                motionY = (rand.nextFloat() - rand.nextFloat()) * 0.2f;

                playSound(SoundEvents.ENTITY_GENERIC_BURN, 0.4f, 2.0f + (rand.nextFloat() * 0.4f));
            }

            if (!world.isRemote)
            {
                searchForOtherItemsNearby();
            }
        }

        float f = 0.98f;

        if (onGround)
        {
            BlockPos underPos = new BlockPos(MathHelper.floor(posX), MathHelper.floor(getEntityBoundingBox().minY) - 1, MathHelper.floor(posZ));

            IBlockState underState = world.getBlockState(underPos);

            f = underState.getBlock().getSlipperiness(underState, world, underPos, this) * 0.98f;
        }

        motionX *= f;

        motionY *= 0.9800000190734863d;

        motionZ *= f;

        if (onGround)
        {
            motionY *= -0.5d;
        }

        if (getAge() != -32768)
        {
            setAge(getAge() + 1);
        }

        handleWaterMovement();

        if (!world.isRemote)
        {
            double d3 = motionX - d0;

            double d4 = motionY - d1;

            double d5 = motionZ - d2;

            double d6 = (d3 * d3) + (d4 * d4) + (d5 * d5);

            if (d6 > 0.01d)
            {
                isAirBorne = true;
            }

            if (getAge() >= getLifespan())
            {
                setDead();
            }
        }

        if (item.isEmpty())
        {
            setDead();
        }
    }

    @Override @Nonnull
    public String getName()
    {
        return I18n.format("item." + getItem().getUnlocalizedName());
    }

    public ItemStack getItem()
    {
        return dataManager.get(ITEM);
    }

    public void setItem(ItemStack itemStack)
    {
        dataManager.set(ITEM, itemStack);

        dataManager.setDirty(ITEM);
    }

    public void setAge(int ageIn)
    {
        dataManager.set(age, ageIn);
    }

    public int getAge()
    {
        return dataManager.get(age);
    }

    public void setPickupDelay(int delay)
    {
        dataManager.set(pickupDelay, delay);
    }

    public int getPickupDelay()
    {
        return dataManager.get(pickupDelay);
    }

    public void setLifespan(int lifespanIn)
    {
        dataManager.set(lifespan, lifespanIn);
    }

    public int getLifespan()
    {
        return dataManager.get(lifespan);
    }

    public void setThrower(String throwerIn)
    {
        dataManager.set(thrower, throwerIn);
    }

    public String getThrower()
    {
        return dataManager.get(thrower);
    }

    public void setOwner(String ownerIn)
    {
        dataManager.set(owner, ownerIn);
    }

    public String getOwner()
    {
        return dataManager.get(owner);
    }

    public EntityPlayer getThrowerEntity()
    {
        try
        {
            return world.getPlayerEntityByUUID(UUID.fromString(getThrower()));
        }
        catch (Exception e)
        {
        }

        return null;
    }

    public void setDefaultPickupDelay()
    {
        setPickupDelay(10);
    }

    public void setNoPickupDelay()
    {
        setPickupDelay(0);
    }

    public void setInfinitePickupDelay()
    {
        setPickupDelay(32767);
    }

    public boolean cannotPickup()
    {
        return (getPickupDelay() > 0);
    }

    public void setNoDespawn()
    {
        setAge(-6000);
    }

    @Override
    public boolean canBeAttackedWithItem()
    {
        return false;
    }

    @Override @Nullable
    public Entity changeDimension(int dimension, @Nonnull ITeleporter teleporter)
    {
        Entity entity = super.changeDimension(dimension, teleporter);

        if (!world.isRemote && entity instanceof EntityCreepItem)
        {
            ((EntityCreepItem)entity).searchForOtherItemsNearby();
        }

        return entity;
    }

    private void searchForOtherItemsNearby()
    {
        /*for (EntityCreepItem item : world.getEntitiesWithinAABB(EntityCreepItem.class, getEntityBoundingBox().grow(0.5d, 0.5d, 0.5d)))
        {
        }*/
    }

    public static void registerFixesItem(DataFixer fixer)
    {
        fixer.registerWalker(FixTypes.ENTITY, new ItemStackData(EntityCreepItem.class, "Item"));
    }

    public void makeFakeItem()
    {
        this.setInfinitePickupDelay();

        ItemStack itemStack = getItem();

        setAge(itemStack.getItem().getEntityLifespan(itemStack, world) - 1);
    }

    @Override
    public void onCollideWithPlayer(EntityPlayer player)
    {
        if (world.isRemote || getPickupDelay() > 0)
        {
            return;
        }

        ItemStack itemStack = getItem();

        Item item = itemStack.getItem();

        int i = itemStack.getCount();

        ItemStack clone = itemStack.copy();

        String ownerIn = getOwner();

        if ((ownerIn.isEmpty() || (getLifespan() - getAge()) <= 200 || ownerIn.equals(player.getName())) && (i <= 0 || player.inventory.addItemStackToInventory(itemStack) || clone.getCount() > getItem().getCount()))
        {
            clone.setCount(clone.getCount() - getItem().getCount());

            if (itemStack.isEmpty())
            {
                player.onItemPickup(this, i);

                setDead();

                itemStack.setCount(i);
            }

            player.addStat(StatList.getObjectsPickedUpStats(item), i);
        }
    }
}

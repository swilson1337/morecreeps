package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityPrisoner extends EntityCreepBase
{
    private static final String[] textures = {
            "textures/entity/prisoner1",
            "textures/entity/prisoner2",
            "textures/entity/prisoner3",
            "textures/entity/prisoner4",
            "textures/entity/prisoner5"
    };

    private static final DataParameter<Boolean> evil = EntityDataManager.<Boolean>createKey(EntityPrisoner.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Boolean> saved = EntityDataManager.<Boolean>createKey(EntityPrisoner.class, DataSerializers.BOOLEAN);

    private int timeOnLand = 0;

    public EntityPrisoner(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Prisoner");

        baseHealth = (float)rand.nextInt(10) + 15.0f;

        baseSpeed = 0.25d;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(evil, Boolean.valueOf(rand.nextInt(2) == 0));

        dataManager.register(saved, Boolean.valueOf(false));
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

        if (getEvil())
        {
            targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        }
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (rand.nextInt(5) == 0)
        {
            return CreepsSoundHandler.prisonerSound;
        }

        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.prisonerHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.prisonerDeathSound;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected String[] getAvailableTextures()
    {
        return textures;
    }

    private void setEvil(boolean b)
    {
        dataManager.set(evil, Boolean.valueOf(b));
    }

    public boolean getEvil()
    {
        return ((Boolean)dataManager.get(evil)).booleanValue();
    }

    private void setSaved(boolean b)
    {
        dataManager.set(saved, Boolean.valueOf(b));
    }

    public boolean getSaved()
    {
        return ((Boolean)dataManager.get(saved)).booleanValue();
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        return false;
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource damageSource, float amt)
    {
        if (damageSource.getTrueSource() instanceof EntityPlayer && !getEvil())
        {
            setEvil(true);

            initEntityAI();
        }

        return super.attackEntityFrom(damageSource, amt);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsPrisoner");

        props.setBoolean("Evil", getEvil());

        props.setBoolean("Saved", getSaved());

        compound.setTag("MoreCreepsPrisoner", props);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsPrisoner");

        if (props.hasKey("Evil"))
        {
            setEvil(props.getBoolean("Evil"));
        }

        if (props.hasKey("Saved"))
        {
            setSaved(props.getBoolean("Saved"));
        }
    }

    @Override
    public void smoke()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
            }
        }
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (!world.isRemote && !isInWater() && !getEvil() && !getSaved() && timeOnLand++ > 50)
        {
            EntityPlayer player = world.getClosestPlayerToEntity(this, 2.0d);

            if (player != null)
            {
                double dist = player.getDistanceSq(this);

                if (dist < 9.0d && canEntityBeSeen(player))
                {
                    giveReward(player);
                }
            }
        }
    }

    private void giveReward(EntityPlayer player)
    {
        if (world.isRemote)
        {
            return;
        }
        else if (rand.nextInt(4) == 0)
        {
            playSound(CreepsSoundHandler.prisonerSorrySound, 1.0f, 1.0f);

            return;
        }

        playSound(CreepsSoundHandler.prisonerRewardSound, 1.0f, 1.0f);

        setSaved(true);

        faceEntity(player, 0.0f, 0.0f);

        EntityItem item = null;

        switch (rand.nextInt(4) + 1)
        {
            case 1:
                item = entityDropItem(new ItemStack(CreepsItemHandler.lolly, rand.nextInt(2) + 1), 1.0f);

                break;
            case 2:
                item = entityDropItem(new ItemStack(Items.BREAD, 1), 1.0f);

                break;
            case 3:
                item = entityDropItem(new ItemStack(Items.CAKE, 1), 1.0f);

                break;
            case 4:
                item = entityDropItem(new ItemStack(CreepsItemHandler.money, rand.nextInt(20) + 1), 1.0f);

                break;
            default:
                item = entityDropItem(new ItemStack(CreepsItemHandler.money, rand.nextInt(5) + 1), 1.0f);

                break;
        }

        if (item == null)
        {
            return;
        }

        double d = -MathHelper.sin((player.rotationYaw * (float)Math.PI) / 180.0f);

        double d1 = MathHelper.cos((player.rotationYaw * (float)Math.PI) / 180.0f);

        item.posX = player.posX + d * 0.5d;

        item.posY = player.posY + 0.5d;

        item.posZ = player.posZ + d1 * 0.5d;
    }
}

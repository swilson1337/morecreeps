package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.capabilities.ILawyerFine;
import com.morecreepsrevival.morecreeps.common.capabilities.LawyerFineProvider;
import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.networking.CreepsPacketHandler;
import com.morecreepsrevival.morecreeps.common.networking.message.MessageSetLawyerFine;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import com.morecreepsrevival.morecreeps.common.world.JailManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityLawyerFromHell extends EntityCreepBase implements IMob
{
    private static final DataParameter<Boolean> undead = EntityDataManager.<Boolean>createKey(EntityLawyerFromHell.class, DataSerializers.BOOLEAN);

    public EntityLawyerFromHell(World world)
    {
        super(world);

        setCreepTypeName("Lawyer From Hell");

        creatureType = EnumCreatureType.MONSTER;

        baseHealth = (float)rand.nextInt(40) + 40.0f;

        baseSpeed = 0.3d;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(undead, Boolean.valueOf(false));
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

        if (getUndead())
        {
            targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        }
    }

    @Override
    protected void updateTexture()
    {
        if (getUndead())
        {
            setTexture("textures/entity/lawyerfromhellundead.png");

            return;
        }

        setTexture("textures/entity/lawyerfromhell.png");
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (!getUndead())
        {
            EntityLivingBase target = getAttackTarget();

            if (target == null)
            {
                EntityPlayer player = world.getNearestPlayerNotCreative(this, 16.0d);

                if (player != null)
                {
                    ILawyerFine capability = player.getCapability(LawyerFineProvider.capability, null);

                    if (capability != null)
                    {
                        int fine = capability.getFine();

                        if (fine > 0)
                        {
                            setAttackTarget(player);
                        }
                    }
                }
            }
            else if (target instanceof EntityPlayer)
            {
                EntityPlayer targetPlayer = (EntityPlayer)target;

                ILawyerFine capability = targetPlayer.getCapability(LawyerFineProvider.capability, null);

                if (capability != null && capability.getFine() < 1)
                {
                    setAttackTarget(null);
                }
            }
        }
    }

    public void setUndead(boolean b)
    {
        if (getUndead() == b)
        {
            return;
        }

        dataManager.set(undead, Boolean.valueOf(b));

        if (b)
        {
            setHeldItem(EnumHand.MAIN_HAND, new ItemStack(Items.BONE));
        }
        else
        {
            setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
        }

        updateAttributes();

        initEntityAI();
    }

    public boolean getUndead()
    {
        return ((Boolean)dataManager.get(undead)).booleanValue();
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        if (getUndead() && !isNotColliding())
        {
            return false;
        }

        return super.isEntityInsideOpaqueBlock();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (getUndead())
        {
            return CreepsSoundHandler.undeadLawyerSound;
        }

        return CreepsSoundHandler.lawyerSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        if (getUndead())
        {
            return CreepsSoundHandler.undeadLawyerHurtSound;
        }

        return CreepsSoundHandler.lawyerHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        if (getUndead())
        {
            return CreepsSoundHandler.undeadLawyerDeathSound;
        }

        return CreepsSoundHandler.lawyerDeathSound;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }

    @Override
    protected double getMoveSpeed()
    {
        if (getUndead())
        {
            return 0.24d;
        }

        return super.getMoveSpeed();
    }

    @Override
    protected double getAttackDamage()
    {
        if (getUndead())
        {
            return 2.0d;
        }

        return super.getAttackDamage();
    }

    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entity)
    {
        if (entity instanceof EntityPlayer)
        {
            ILawyerFine capability = entity.getCapability(LawyerFineProvider.capability, null);

            if (capability != null)
            {
                EntityPlayer player = (EntityPlayer)entity;

                int fine = capability.getFine();

                if (fine < 1 && !getUndead())
                {
                    setAttackTarget(null);

                    setRevengeTarget(null);

                    return false;
                }
                else if (rand.nextInt(50) == 0)
                {
                    suckMoney(player);
                }

                if (!world.isRemote && MoreCreepsConfig.jailActive && fine >= 2500 && !getUndead() && JailManager.buildJail(player, world, rand))
                {
                    capability.setFine(0);

                    CreepsPacketHandler.INSTANCE.sendTo(new MessageSetLawyerFine(0), (EntityPlayerMP)player);
                }
            }
        }

        return super.attackEntityAsMob(entity);
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource damageSource, float amt)
    {
        if (!getUndead())
        {
            EntityPlayer playerTarget = null;

            Entity entity = damageSource.getTrueSource();

            if (entity instanceof EntityPlayer)
            {
                playerTarget = (EntityPlayer)entity;

                if (rand.nextInt(5) == 0)
                {
                    int randInt = rand.nextInt(20) + 5;

                    for (int i = 0; i < randInt; i++)
                    {
                        ILawyerFine capability = playerTarget.getCapability(LawyerFineProvider.capability, null);

                        if (capability != null)
                        {
                            capability.addFine(25);

                            if (!world.isRemote)
                            {
                                CreepsPacketHandler.INSTANCE.sendTo(new MessageSetLawyerFine(capability.getFine()), (EntityPlayerMP)playerTarget);
                            }
                        }

                        playSound(CreepsSoundHandler.lawyerMoneyHitSound, getSoundVolume(), getSoundPitch());

                        EntityMoney money = new EntityMoney(world, posX, posY, posZ);

                        money.setDefaultPickupDelay();

                        money.setNoDespawn();

                        if (!world.isRemote)
                        {
                            world.spawnEntity(money);
                        }
                    }
                }

                if (rand.nextInt(5) == 0)
                {
                    int randInt = rand.nextInt(3) + 1;

                    for (int i = 0; i < randInt; i++)
                    {
                        ILawyerFine capability = playerTarget.getCapability(LawyerFineProvider.capability, null);

                        if (capability != null)
                        {
                            capability.addFine(10);

                            if (!world.isRemote)
                            {
                                CreepsPacketHandler.INSTANCE.sendTo(new MessageSetLawyerFine(capability.getFine()), (EntityPlayerMP)playerTarget);
                            }
                        }

                        playSound(CreepsSoundHandler.lawyerMoneyHitSound, getSoundVolume(), getSoundPitch());

                        if (!world.isRemote)
                        {
                            dropItem(Items.PAPER, 1);
                        }
                    }
                }
            }
            else if (entity instanceof EntityCreepBase)
            {
                EntityCreepBase creep = (EntityCreepBase)entity;

                if (creep.isTamed())
                {
                    playerTarget = creep.getOwner();
                }
            }

            if (playerTarget != null)
            {
                ILawyerFine capability = playerTarget.getCapability(LawyerFineProvider.capability, null);

                if (capability != null)
                {
                    capability.addFine(50);

                    if (!world.isRemote)
                    {
                        CreepsPacketHandler.INSTANCE.sendTo(new MessageSetLawyerFine(capability.getFine()), (EntityPlayerMP)playerTarget);
                    }
                }

                setRevengeTarget(playerTarget);
            }
        }

        return super.attackEntityFrom(damageSource, amt);
    }

    private void suckMoney(EntityPlayer player)
    {
        int invSize = player.inventory.mainInventory.size();

        boolean take = false;

        boolean isUndead = getUndead();

        for (int i = 0; i < invSize; i++)
        {
            ItemStack itemStack = player.inventory.mainInventory.get(i);

            if (itemStack.getItem() == CreepsItemHandler.money)
            {
                if (!isUndead)
                {
                    playSound(CreepsSoundHandler.lawyerSuckSound, getSoundVolume(), getSoundPitch());
                }

                int stackSize = itemStack.getCount();

                itemStack.shrink(Math.min(stackSize, rand.nextInt(stackSize) + 1));

                take = true;
            }
        }

        if (take && !isUndead)
        {
            playSound(CreepsSoundHandler.lawyerTakeSound, getSoundVolume(), getSoundPitch());
        }
    }

    @Override
    public void onDeath(@Nonnull DamageSource cause)
    {
        if (!getUndead())
        {
            Entity entity = cause.getTrueSource();

            if (rand.nextInt(3) == 0 && entity instanceof EntityPlayer)
            {
                int randAmt = rand.nextInt(4) + 3;

                for (int i = 0; i < randAmt; i++)
                {
                    smoke2();

                    EntityLawyerFromHell lawyer = new EntityLawyerFromHell(world);

                    lawyer.setLocationAndAngles(entity.posX + (double)(rand.nextInt(4) - rand.nextInt(4)), entity.posY - 1.5d, entity.posZ + (double)(rand.nextInt(4) - rand.nextInt(4)), rotationYaw, 0.0f);

                    lawyer.motionY = 20.0d;

                    lawyer.setUndead(true);

                    lawyer.determineBaseTexture();

                    lawyer.setInitialHealth();

                    if (!world.isRemote)
                    {
                        world.spawnEntity(lawyer);
                    }
                }
            }
            else if (rand.nextInt(5) == 0)
            {
                // TODO: spawn bum
            }
            else if (!world.isRemote && rand.nextInt(10) == 0)
            {
                int randInt = rand.nextInt(40) + 10;

                for (int i = 0; i < randInt; i++)
                {
                    dropItem(CreepsItemHandler.money, 1);
                }
            }
        }

        smoke2();

        super.onDeath(cause);
    }
}

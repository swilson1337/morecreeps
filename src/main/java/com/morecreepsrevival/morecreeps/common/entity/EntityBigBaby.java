package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.entity.ai.EntityCreepAIAttackMelee;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityBigBaby extends EntityCreepBase implements IMob
{
    private static final DataParameter<Integer> skin = EntityDataManager.createKey(EntityBigBaby.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> skinTimer = EntityDataManager.createKey(EntityBigBaby.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> skinDirection = EntityDataManager.<Boolean>createKey(EntityBigBaby.class, DataSerializers.BOOLEAN);

    public EntityBigBaby(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Big Baby");

        creatureType = EnumCreatureType.MONSTER;

        setSize(width * 3.25f, height * 3.55f);

        setModelSize(6.5f);

        baseHealth = (float)rand.nextInt(40) + 40.0f;

        baseSpeed = 0.25d;

        baseAttackDamage = 1.0d;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(skin, 0);

        dataManager.register(skinTimer, 0);

        dataManager.register(skinDirection, Boolean.valueOf(true));
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

        tasks.addTask(3, new EntityCreepAIAttackMelee(this, 1.0d, 4.0d, true));

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/bigbaby" + dataManager.get(skin) + ".png");
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        dropItem(Items.PORKCHOP, rand.nextInt(6) + 5);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.bigBabySound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.bigBabyHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.bigBabyHurtSound;
    }

    @Override
    public float getEyeHeight()
    {
        return (height * 0.15f);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (getHammerSwing() < 0.0f)
        {
            addHammerSwing(0.1000055f);
        }
        else
        {
            setHammerSwing(0.0f);
        }
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        dataManager.set(skinTimer, dataManager.get(skinTimer) + 1);

        if (dataManager.get(skinTimer) > 60)
        {
            dataManager.set(skinTimer, 0);

            int iSkin = dataManager.get(skin);

            iSkin += (getSkinDirection() ? 1 : -1);

            if (getAttackTarget() != null)
            {
                iSkin = 0;
            }

            if (iSkin < 0 || iSkin > 4)
            {
                iSkin = 0;
            }

            if (iSkin == 0 || iSkin == 4)
            {
                setSkinDirection(!getSkinDirection());
            }

            dataManager.set(skin, iSkin);

            updateTexture();
        }
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (itemStack.getItem() == CreepsItemHandler.babyJarEmpty)
        {
            if (getModelSize() < 1.0f)
            {
                setDead();

                player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(CreepsItemHandler.babyJarFull));

                playSound(CreepsSoundHandler.babyTakeHomeSound, 1.0f, 1.0f);

                if (!world.isRemote)
                {
                    player.sendMessage(new TextComponentString("Now turn that baby into a Schlump on the floor!"));
                }
            }
            else
            {
                playSound(CreepsSoundHandler.babyShrinkSound, 1.0f, 1.0f);

                if (!world.isRemote)
                {
                    player.sendMessage(new TextComponentString("That baby is too large."));
                }
            }

            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entity)
    {
        if (getHammerSwing() == 0.0f)
        {
            setHammerSwing(-1.1f);
        }

        return super.attackEntityAsMob(entity);
    }

    private void setSkinDirection(boolean b)
    {
        dataManager.set(skinDirection, Boolean.valueOf(b));
    }

    private boolean getSkinDirection()
    {
        return ((Boolean)dataManager.get(skinDirection)).booleanValue();
    }
}

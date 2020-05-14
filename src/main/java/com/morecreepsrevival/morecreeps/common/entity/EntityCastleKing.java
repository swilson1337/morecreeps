package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.entity.ai.EntityCreepAIAttackMelee;
import com.morecreepsrevival.morecreeps.common.helpers.InventoryHelper;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
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
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityCastleKing extends EntityCreepBase
{
    private static final DataParameter<Integer> intruderCheck = EntityDataManager.createKey(EntityCastleKing.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> intruderID = EntityDataManager.createKey(EntityCastleKing.class, DataSerializers.VARINT);

    public EntityCastleKing(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Castle King");

        baseSpeed = 0.0d;

        baseAttackDamage = 4.0d;

        baseHealth = (float)rand.nextInt(60) + 60.0f;

        setSize(2.0f, 1.6f);

        setHeldItem(EnumHand.MAIN_HAND, new ItemStack(CreepsItemHandler.gemSword));

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(intruderCheck, 25);

        dataManager.register(intruderID, 0);
    }

    @Override
    public void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        tasks.addTask(1, new EntityAISwimming(this));

        tasks.addTask(2, new EntityCreepAIAttackMelee(this, 1.0d, 4.0d, true));

        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(5, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

        targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        smoke2();

        if (dataManager.get(intruderCheck) > 0)
        {
            dataManager.set(intruderCheck, dataManager.get(intruderCheck) - 1);
        }

        if (dataManager.get(intruderCheck) < 1)
        {
            dataManager.set(intruderCheck, 25);

            EntityPlayer player = world.getClosestPlayerToEntity(this, 10.0d);

            if (player != null && canEntityBeSeen(player))
            {
                dataManager.set(intruderID, player.getEntityId());

                baseSpeed = 0.222d;

                updateMoveSpeed();
            }
        }
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/castleking.png");
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.castleKingSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.castleKingHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.castleKingDeathSound;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        EntityPlayer player = null;

        if (dataManager.get(intruderID) != 0)
        {
            Entity entity = world.getEntityByID(dataManager.get(intruderID));

            if (entity instanceof EntityPlayer)
            {
                player = (EntityPlayer)entity;
            }
        }

        if (player != null && InventoryHelper.hasItem(player.inventory, CreepsItemHandler.skyGem) && InventoryHelper.hasItem(player.inventory, CreepsItemHandler.earthGem) && InventoryHelper.hasItem(player.inventory, CreepsItemHandler.fireGem) && InventoryHelper.hasItem(player.inventory, CreepsItemHandler.healingGem) && InventoryHelper.hasItem(player.inventory, CreepsItemHandler.miningGem))
        {
            dropItem(CreepsItemHandler.gemSword, 1);

            dropItem(CreepsItemHandler.money, rand.nextInt(100) + 50);
        }
        else
        {
            dropItem(Items.IRON_SWORD, 1);

            dropItem(Items.BOOK, 1);
        }
    }

    @Override
    protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier)
    {
    }

    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entity)
    {
        if (getHammerSwing() == 0.0f)
        {
            setHammerSwing(-2.6f);
        }

        return super.attackEntityAsMob(entity);
    }

    @Override
    public boolean getCanSpawnHere()
    {
        return true;
    }
}

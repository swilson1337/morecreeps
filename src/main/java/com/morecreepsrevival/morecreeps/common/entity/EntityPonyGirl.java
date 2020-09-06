package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.helpers.InventoryHelper;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityPonyGirl extends EntityCreepBase
{
    private static final DataParameter<Boolean> cellPhone = EntityDataManager.<Boolean>createKey(EntityPonyGirl.class, DataSerializers.BOOLEAN);

    public EntityPonyGirl(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Pony Girl");

        setSize(0.6f, 0.6f);

        setModelSize(0.75f);

        baseSpeed = 0.25d;

        baseHealth = 25.0f;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(cellPhone, Boolean.valueOf(false));
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/ponygirl.png");
    }

    @Override
    public void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        nodeProcessor.setCanEnterDoors(true);

        tasks.addTask(1, new EntityAISwimming(this));

        tasks.addTask(2, new EntityAIBreakDoor(this));

        tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(5, new EntityAILookIdle(this));
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (getCellPhone())
        {
            return CreepsSoundHandler.ponyGirlBuySound;
        }

        return CreepsSoundHandler.ponyGirlCellSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.ponyGirlHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.ponyGirlDeathSound;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    private void setCellPhone(boolean b)
    {
        dataManager.set(cellPhone, Boolean.valueOf(b));
    }

    public boolean getCellPhone()
    {
        return ((Boolean)dataManager.get(cellPhone)).booleanValue();
    }

    @Override
    public boolean processInteract(@Nonnull EntityPlayer player, @Nonnull EnumHand hand)
    {
        if (hand == EnumHand.OFF_HAND)
        {
            return super.processInteract(player, hand);
        }

        ItemStack itemStack = player.getHeldItem(hand);

        if (!itemStack.isEmpty())
        {
            Item item = itemStack.getItem();

            if (item == CreepsItemHandler.mobilePhone && !getCellPhone())
            {
                setHeldItem(hand, new ItemStack(CreepsItemHandler.mobilePhone, 1));

                setCellPhone(true);

                return true;
            }
            else if (item == CreepsItemHandler.money && getCellPhone())
            {
                if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ)))
                {
                    int cash = InventoryHelper.getItemCount(player.inventory, CreepsItemHandler.money);

                    if (cash < 50)
                    {
                        if (!world.isRemote)
                        {
                            player.sendMessage(new TextComponentString("You need $50 for a Pony!"));
                        }

                        return false;
                    }

                    playSound(CreepsSoundHandler.ponyGirlWaitHereSound, getSoundVolume(), getSoundPitch());

                    if (!world.isRemote)
                    {
                        InventoryHelper.takeItem(player.inventory, CreepsItemHandler.money, 50);

                        player.sendMessage(new TextComponentString("LOOK UP! Your new Pony is being delivered by a PonyCloud!"));
                    }
                }
                else
                {
                    if (!world.isRemote)
                    {
                        player.sendMessage(new TextComponentString("I have to get better reception to order a pony!"));
                    }

                    return false;
                }
            }
            else
            {
                playSound(CreepsSoundHandler.ponyGirlMoneySound, getSoundVolume(), getSoundPitch());

                return false;
            }
        }

        return super.processInteract(player, hand);
    }
}

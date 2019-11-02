package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityDigBug extends EntityCreepBase
{
    private static final DataParameter<Integer> lifespan = EntityDataManager.createKey(EntityDigBug.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> holeDepth = EntityDataManager.createKey(EntityDigBug.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> digTimer = EntityDataManager.createKey(EntityDigBug.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> digStage = EntityDataManager.createKey(EntityDigBug.class, DataSerializers.VARINT);

    private static final DataParameter<BlockPos> digPosition = EntityDataManager.createKey(EntityDigBug.class, DataSerializers.BLOCK_POS);

    private int skinFrame = 0;

    private static final Item[] dropItems = {
            Item.getItemFromBlock(Blocks.COBBLESTONE),
            Item.getItemFromBlock(Blocks.GRAVEL),
            Item.getItemFromBlock(Blocks.COBBLESTONE),
            Item.getItemFromBlock(Blocks.GRAVEL),
            Item.getItemFromBlock(Blocks.IRON_ORE),
            Item.getItemFromBlock(Blocks.MOSSY_COBBLESTONE)
    };

    public EntityDigBug(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Dig Bug");

        setSize(0.5f, 1.2f);

        baseHealth = 60.0f;

        baseSpeed = 0.3f;

        baseAttackDamage = 4.0d;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(lifespan, 5000);

        dataManager.register(holeDepth, 0);

        dataManager.register(digTimer, rand.nextInt(500) + 500);

        dataManager.register(digStage, 0);

        dataManager.register(digPosition, new BlockPos(0, 0, 0));
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/digbug" + skinFrame + ".png");
    }

    @Override
    protected boolean canDespawn()
    {
        return (dataManager.get(lifespan) < 0);
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (dataManager.get(lifespan) >= 0 && dataManager.get(holeDepth) > 0)
        {
            dataManager.set(lifespan, dataManager.get(lifespan) - 1);

            if (dataManager.get(lifespan) < 1)
            {
                dataManager.set(digTimer, rand.nextInt(20));

                dataManager.set(digPosition, new BlockPos(-1, dataManager.get(holeDepth), -1));

                dataManager.set(digStage, 4);
            }
        }
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (prevPosX != posX || prevPosY != posY)
        {
            skinFrame++;

            updateTexture();

            if (skinFrame > 3)
            {
                skinFrame = 0;
            }
        }
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        if (dataManager.get(digStage) == 1 || dataManager.get(digStage) == 4)
        {
            return false;
        }

        return super.isEntityInsideOpaqueBlock();
    }

    public boolean checkHole(BlockPos blockPos, int l)
    {
        for (int i = 0; i < l; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                for (int k = 0; k < 3; k++)
                {
                    if (world.isAirBlock(new BlockPos(blockPos.getX() + j, blockPos.getY() - i - 1, blockPos.getZ() + k)))
                    {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        switch (dataManager.get(digStage))
        {
            case 0:
                return CreepsSoundHandler.digBugSound;
            case 1:
            case 4:
                return CreepsSoundHandler.digBugDigSound;
            case 2:
                return CreepsSoundHandler.digBugCallSound;
            default:
                break;
        }

        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.digBugHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.digBugDeathSound;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        dropItem(dropItems[rand.nextInt(dropItems.length)], 1);
    }
}

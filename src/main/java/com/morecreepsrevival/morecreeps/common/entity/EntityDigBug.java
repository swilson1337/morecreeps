package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityDigBug extends EntityCreepBase
{
    private static final DataParameter<Integer> lifespan = EntityDataManager.createKey(EntityDigBug.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> holeDepth = EntityDataManager.createKey(EntityDigBug.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> digTimer = EntityDataManager.createKey(EntityDigBug.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> digStage = EntityDataManager.createKey(EntityDigBug.class, DataSerializers.VARINT);

    private static final DataParameter<BlockPos> digPosition = EntityDataManager.createKey(EntityDigBug.class, DataSerializers.BLOCK_POS);

    private static final DataParameter<BlockPos> holePos = EntityDataManager.createKey(EntityDigBug.class, DataSerializers.BLOCK_POS);

    private static final DataParameter<Integer> hunger = EntityDataManager.createKey(EntityDigBug.class, DataSerializers.VARINT);

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

        dataManager.register(holePos, new BlockPos(0, 0, 0));

        dataManager.register(hunger, rand.nextInt(3) + 1);
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

        tasks.addTask(3, new EntityAIAttackMelee(this, 1.0d, true));

        tasks.addTask(4, new EntityAIMoveTowardsRestriction(this, 0.5d));

        tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0d));

        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

        tasks.addTask(6, new EntityAILookIdle(this));

        targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
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
            updateTexture();

            skinFrame++;

            if (skinFrame > 3)
            {
                skinFrame = 0;
            }
        }

        if (dataManager.get(digStage) == 0 && posY < 90.0d)
        {
            dataManager.set(digTimer, dataManager.get(digTimer) - 1);

            if (dataManager.get(digTimer) < 1)
            {
                int x = MathHelper.floor(posX);

                int y = MathHelper.floor(getEntityBoundingBox().minY);

                int z = MathHelper.floor(posZ);

                dataManager.set(holeDepth, rand.nextInt(2) + 3);

                if (world.getBlockState(new BlockPos(x, y - 1, z)).getBlock() == Blocks.GRASS)
                {
                    if (checkHole(new BlockPos(x, y, z), dataManager.get(holeDepth)))
                    {
                        dataManager.set(digStage, 1);

                        dataManager.set(digPosition, new BlockPos(0.0d, 1.0d, 0.0d));

                        dataManager.set(holePos, new BlockPos(x, y, z));
                    }
                    else
                    {
                        dataManager.set(digTimer, rand.nextInt(200));
                    }
                }
            }
        }

        if (dataManager.get(digStage) == 1)
        {
            int x = MathHelper.floor(posX);

            int y = MathHelper.floor(getEntityBoundingBox().minY);

            int z = MathHelper.floor(posZ);

            world.setBlockToAir(new BlockPos(x, y, z));

            world.setBlockToAir(new BlockPos(x, y + 1, z));

            if (posX < (dataManager.get(holePos).getX() + dataManager.get(digPosition).getX()))
            {
                motionX += 0.20000000298023224d;
            }
            else
            {
                motionX -= 0.20000000298023224d;
            }

            if (posZ < (dataManager.get(holePos).getZ() + dataManager.get(digPosition).getZ()))
            {
                motionZ += 0.20000000298023224d;
            }
            else
            {
                motionZ -= 0.20000000298023224d;
            }

            dataManager.set(digTimer, dataManager.get(digTimer) - 1);

            if (dataManager.get(digTimer) < 1)
            {
                dataManager.set(digTimer, rand.nextInt(20));

                setPosition(dataManager.get(holePos).getX() + dataManager.get(digPosition).getX(), dataManager.get(holePos).getY() - dataManager.get(digPosition).getY(), dataManager.get(holePos).getZ() + dataManager.get(digPosition).getZ());

                Block block = world.getBlockState(getPosition()).getBlock();

                if (rand.nextInt(50) == 0)
                {
                    block = Blocks.COAL_ORE;
                }

                if (block != Blocks.SAND && block != Blocks.LOG)
                {
                    int randInt = rand.nextInt(2) + 1;

                    for (int i = 0; i < randInt; i++)
                    {
                        EntityItem entityItem = new EntityItem(world, dataManager.get(holePos).getX() + dataManager.get(digPosition).getX(), (dataManager.get(holePos).getY() - dataManager.get(digPosition).getY()) + 1.0d, dataManager.get(holePos).getZ() + dataManager.get(digPosition).getZ(), new ItemStack(block, 1));

                        if (!world.isRemote)
                        {
                            world.spawnEntity(entityItem);
                        }
                    }
                }

                world.setBlockToAir(getPosition());

                dataManager.set(digPosition, new BlockPos(dataManager.get(digPosition).getX(), dataManager.get(digPosition).getY(), dataManager.get(digPosition).getZ() + 1.0d));

                if (dataManager.get(digPosition).getZ() > 1.0d)
                {
                    dataManager.set(digPosition, new BlockPos(dataManager.get(digPosition).getX(), dataManager.get(digPosition).getY(), 0.0d));

                    setPosition(dataManager.get(holePos).getX() + dataManager.get(digPosition).getX(), dataManager.get(holePos).getY() - dataManager.get(digPosition).getY(), dataManager.get(holePos).getZ() + dataManager.get(digPosition).getZ());

                    dataManager.set(digPosition, new BlockPos(dataManager.get(digPosition).getX() + 1.0d, dataManager.get(digPosition).getY(), dataManager.get(digPosition).getZ()));

                    if (dataManager.get(digPosition).getX() > 1.0d)
                    {
                        dataManager.set(digPosition, new BlockPos(0.0d, dataManager.get(digPosition).getY(), dataManager.get(digPosition).getZ()));

                        setPosition(dataManager.get(holePos).getX() + dataManager.get(digPosition).getX(), dataManager.get(holePos).getY() - dataManager.get(digPosition).getY(), dataManager.get(holePos).getZ() + dataManager.get(digPosition).getZ());

                        dataManager.set(digPosition, new BlockPos(dataManager.get(digPosition).getX(), dataManager.get(digPosition).getY() + 1.0d, dataManager.get(digPosition).getZ()));

                        if (dataManager.get(digPosition).getY() > (double)dataManager.get(holeDepth))
                        {
                            int randInt = rand.nextInt(8) + 5;

                            for (int i = 0; i < randInt; i++)
                            {
                                int l = rand.nextInt(40) + 40;

                                int k = rand.nextInt(40) + 40;

                                if (rand.nextInt(1) == 0)
                                {
                                    l *= -1;
                                }

                                if (rand.nextInt(1) == 0)
                                {
                                    k *= -1;
                                }

                                EntityBubbleScum bubbleScum = new EntityBubbleScum(world);

                                bubbleScum.setPositionAndRotation(posX + (double)l, posY + (double)dataManager.get(holeDepth) + 2.0d, posZ + (double)k, rotationYaw, 0.0f);

                                bubbleScum.determineBaseTexture();

                                bubbleScum.setInitialHealth();

                                bubbleScum.motionX = rand.nextFloat() * 1.5f;

                                bubbleScum.motionY = rand.nextFloat() * 2.0f;

                                bubbleScum.motionZ = rand.nextFloat() * 1.5f;

                                bubbleScum.fallDistance = -25.0f;

                                if (!world.isRemote)
                                {
                                    world.spawnEntity(bubbleScum);
                                }
                            }

                            dataManager.set(digStage, 2);

                            dataManager.set(lifespan, 5000);

                            baseSpeed = 0.0d;

                            updateMoveSpeed();

                            motionY = 0.44999998807907104d;

                            setPosition(dataManager.get(holePos).getX() + 1.0d, dataManager.get(holePos).getY() - dataManager.get(digPosition).getY(), dataManager.get(holePos).getZ() + 1.0d);

                            dataManager.set(digTimer, rand.nextInt(5) + 5);
                        }
                    }
                }
            }
        }

        if (dataManager.get(digStage) == 2)
        {
            dataManager.set(digTimer, dataManager.get(digTimer) - 1);

            if (dataManager.get(digTimer) < 1)
            {
                dataManager.set(digTimer, rand.nextInt(20));

                int max = 20 + dataManager.get(digTimer);

                /*for (int i = 0; i < max; i++)
                {
                    // TODO: bubble
                }*/

                dataManager.set(digTimer, 50);

                for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(5.0d, 1.0d, 5.0d)))
                {
                    if (entity instanceof EntityBubbleScum)
                    {
                        entity.setDead();

                        baseSpeed = 0.4d;

                        updateMoveSpeed();

                        motionY = 0.60000002384185791d;

                        dataManager.set(digStage, 3);
                    }
                }
            }
        }

        if (dataManager.get(digStage) == 3)
        {
            int l = rand.nextInt(25) + 15;

            playSound(CreepsSoundHandler.digBugEatSound, 1.0f, getSoundPitch());

            for (int i = 0; i < l; i++)
            {
                if (!world.isRemote)
                {
                    EntityItem item = entityDropItem(new ItemStack(Items.COOKIE, 1), 1.0f);

                    if (item != null)
                    {
                        item.motionY += rand.nextFloat() * 2.0f + 3.0f;

                        item.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.33f;

                        item.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.33f;
                    }
                }
            }

            dataManager.set(hunger, dataManager.get(hunger) - 1);

            if (dataManager.get(hunger) < 1)
            {
                dataManager.set(digTimer, rand.nextInt(20));

                dataManager.set(digPosition, new BlockPos(-1.0d, (double)dataManager.get(holeDepth), -1.0d));

                dataManager.set(digStage, 4);

                playSound(CreepsSoundHandler.digBugFullSound, 1.0f, getSoundPitch());
            }
            else
            {
                dataManager.set(digStage, 2);

                dataManager.set(digTimer, 50);
            }
        }

        if (dataManager.get(digStage) == 4)
        {
            if (posX < (dataManager.get(holePos).getX() + dataManager.get(digPosition).getX()))
            {
                motionX += 0.20000000298023224d;
            }
            else
            {
                motionX -= 0.20000000298023224d;
            }

            if (posZ < (dataManager.get(holePos).getZ() + dataManager.get(digPosition).getZ()))
            {
                motionZ += 0.20000000298023224d;
            }
            else
            {
                motionZ -= 0.20000000298023224d;
            }

            dataManager.set(digTimer, dataManager.get(digTimer) - 1);

            if (dataManager.get(digTimer) < 1)
            {
                dataManager.set(digTimer, rand.nextInt(10));

                BlockPos blockPos = new BlockPos(dataManager.get(holePos).getX() + dataManager.get(digPosition).getX(), dataManager.get(holePos).getY() - dataManager.get(digPosition).getY(), dataManager.get(holePos).getZ() + dataManager.get(digPosition).getZ());

                if (world.isAirBlock(blockPos))
                {
                    world.setBlockState(blockPos, Blocks.DIRT.getDefaultState());
                }

                dataManager.set(digPosition, new BlockPos(dataManager.get(digPosition).getX(), dataManager.get(digPosition).getY(), dataManager.get(digPosition).getZ() + 1.0d));

                if (dataManager.get(digPosition).getZ() > 2.0d)
                {
                    dataManager.set(digPosition, new BlockPos(dataManager.get(digPosition).getX(), dataManager.get(digPosition).getY(), -1.0d));

                    setPosition(dataManager.get(holePos).getX() + dataManager.get(digPosition).getX(), (dataManager.get(holePos).getY() - dataManager.get(digPosition).getY()) + 1.0d, dataManager.get(holePos).getZ() + dataManager.get(digPosition).getZ());

                    dataManager.set(digPosition, new BlockPos(dataManager.get(digPosition).getX() + 1.0d, dataManager.get(digPosition).getY(), dataManager.get(digPosition).getZ()));

                    if (dataManager.get(digPosition).getX() > 2.0d)
                    {
                        dataManager.set(digPosition, new BlockPos(-1.0d, dataManager.get(digPosition).getY(), dataManager.get(digPosition).getZ()));

                        setPosition(dataManager.get(holePos).getX() + dataManager.get(digPosition).getX(), (dataManager.get(holePos).getY() - dataManager.get(digPosition).getY()) + 1.0d, dataManager.get(holePos).getZ() + dataManager.get(digPosition).getZ());

                        dataManager.set(digPosition, new BlockPos(dataManager.get(digPosition).getX(), dataManager.get(digPosition).getY() - 1.0d, dataManager.get(digPosition).getZ()));

                        if (dataManager.get(digPosition).getY() == 1.0d)
                        {
                            dataManager.set(digStage, 0);

                            dataManager.set(digTimer, rand.nextInt(8000) + 1000);

                            setPosition(dataManager.get(holePos).getX() + 1.0d, dataManager.get(holePos).getY() + dataManager.get(digPosition).getY() + 1.0d, dataManager.get(holePos).getZ() + 1.0d);
                        }
                    }
                }
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

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsDigBug");

        props.setInteger("DigStage", dataManager.get(digStage));

        props.setInteger("DigTimer", dataManager.get(digTimer));

        props.setInteger("LifeSpan", dataManager.get(lifespan));

        props.setInteger("HoleDepth", dataManager.get(holeDepth));

        props.setDouble("DigPositionX", dataManager.get(digPosition).getX());

        props.setDouble("DigPositionY", dataManager.get(digPosition).getY());

        props.setDouble("DigPositionZ", dataManager.get(digPosition).getZ());

        props.setDouble("HolePosX", dataManager.get(holePos).getX());

        props.setDouble("HolePosY", dataManager.get(holePos).getY());

        props.setDouble("HolePosZ", dataManager.get(holePos).getZ());

        compound.setTag("MoreCreepsDigBug", props);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsDigBug");

        if (props.hasKey("DigStage"))
        {
            dataManager.set(digStage, props.getInteger("DigStage"));
        }

        if (props.hasKey("DigTimer"))
        {
            dataManager.set(digTimer, props.getInteger("DigTimer"));
        }

        if (props.hasKey("LifeSpan"))
        {
            dataManager.set(lifespan, props.getInteger("LifeSpan"));
        }

        if (props.hasKey("HoleDepth"))
        {
            dataManager.set(holeDepth, props.getInteger("HoleDepth"));
        }

        if (props.hasKey("DigPositionX") && props.hasKey("DigPositionY") && props.hasKey("DigPositionZ"))
        {
            dataManager.set(digPosition, new BlockPos(props.getDouble("DigPositionX"), props.getDouble("DigPositionY"), props.getDouble("DigPositionZ")));
        }

        if (props.hasKey("HolePosX") && props.hasKey("HolePosY") && props.hasKey("HolePosZ"))
        {
            dataManager.set(holePos, new BlockPos(props.getDouble("HolePosX"), props.getDouble("HolePosY"), props.getDouble("HolePosZ")));
        }
    }
}

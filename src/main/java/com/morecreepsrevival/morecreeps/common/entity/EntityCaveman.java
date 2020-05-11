package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
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
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityCaveman extends EntityCreepBase
{
    private static final DataParameter<Boolean> caveGirl = EntityDataManager.<Boolean>createKey(EntityCaveman.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Boolean> evil = EntityDataManager.<Boolean>createKey(EntityCaveman.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> frozenLevel = EntityDataManager.createKey(EntityCaveman.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> houseChunk = EntityDataManager.createKey(EntityCaveman.class, DataSerializers.VARINT);

    private static final DataParameter<BlockPos> housePos = EntityDataManager.createKey(EntityCaveman.class, DataSerializers.BLOCK_POS);

    private int talkDelay = 0;

    public EntityCaveman(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Caveman");

        float fat = rand.nextFloat() * 1.0f - rand.nextFloat() * 0.55f;

        setSize(width * 0.8f + fat, height * 1.3f + fat);

        setModelSize(1.25f + (rand.nextFloat() * 1.0f) - (rand.nextFloat() * 0.75f));

        baseHealth = 25.0f;

        baseSpeed = 0.3d;

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

        if (getEvil())
        {
            targetTasks.addTask(2, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, true));
        }
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(caveGirl, Boolean.valueOf(rand.nextInt(100) > 50));

        dataManager.register(evil, Boolean.valueOf(false));

        dataManager.register(frozenLevel, 5);

        dataManager.register(houseChunk, 0);

        dataManager.register(housePos, new BlockPos(0, 0, 0));
    }

    protected void setCaveGirl(boolean b)
    {
        dataManager.set(caveGirl, Boolean.valueOf(b));
    }

    public boolean getCaveGirl()
    {
        return ((Boolean)dataManager.get(caveGirl)).booleanValue();
    }

    protected void setEvil(boolean b)
    {
        dataManager.set(evil, Boolean.valueOf(b));
    }

    public boolean getEvil()
    {
        return ((Boolean)dataManager.get(evil)).booleanValue();
    }

    protected void setFrozenLevel(int i)
    {
        dataManager.set(frozenLevel, i);
    }

    public int getFrozenLevel()
    {
        return dataManager.get(frozenLevel);
    }

    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (getFrozenLevel() > 0 && world.isAirBlock(new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY), MathHelper.floor(posZ))))
        {
            posY--;
        }

        if (handleWaterMovement() || isWet())
        {
            setFrozenLevel(0);
        }

        updateMoveSpeed();

        if (getWanderState() == 0 && getFrozenLevel() < 1 && !getEvil() && rand.nextInt(100) == 0)
        {
            setWanderState(1);
        }

        if (getWanderState() == 1 && rand.nextInt(201) == 200 && !getEvil() && checkArea())
        {
            setWanderState(2);

            setHouseChunk(0);

            for (int i = 0; i < 4; i++)
            {
                BlockPos blockPos = getHousePos();

                int houseX = blockPos.getX();

                int houseY = blockPos.getY();

                int houseZ = blockPos.getZ();

                if (world.isAirBlock(new BlockPos(houseX, houseY, houseZ)) || world.isAirBlock(new BlockPos(houseX + 1, houseY, houseZ)) || world.isAirBlock(new BlockPos(houseX + 2, houseY, houseZ + 4)) || world.isAirBlock(new BlockPos(houseX, houseY, houseZ + 2)))
                {
                    setHousePos(new BlockPos(houseX, houseY - 1, houseZ));
                }
            }
        }

        if (getWanderState() == 2)
        {
            BlockPos blockPos = getHousePos();

            int houseX = blockPos.getX();

            int houseY = blockPos.getY();

            int houseZ = blockPos.getZ();

            posX = houseX - 1;

            setRotation(45.0f, rotationPitch);

            if (rand.nextInt(50) == 0)
            {
                switch (getHouseChunk())
                {
                    case 0:
                        world.setBlockState(new BlockPos(houseX + 1, houseY, houseZ), Blocks.SNOW.getDefaultState());

                        snowFX(houseX + 1, houseY, houseZ);

                        moveNextStage();

                        break;
                    case 1:
                        world.setBlockState(new BlockPos(houseX + 1, houseY + 1, houseZ), Blocks.SNOW.getDefaultState());

                        snowFX(houseX + 1, houseY + 1, houseZ);

                        moveNextStage();

                        break;
                    case 2:
                        world.setBlockState(new BlockPos(houseX + 3, houseY, houseZ), Blocks.SNOW.getDefaultState());

                        snowFX(houseX + 3, houseY, houseZ);

                        moveNextStage();

                        break;
                    case 3:
                        world.setBlockState(new BlockPos(houseX + 3, houseY + 1, houseZ), Blocks.SNOW.getDefaultState());

                        snowFX(houseX + 3, houseY + 1, houseZ);

                        moveNextStage();

                        break;
                    case 4:
                        for (int i = 1; i < 4; i++)
                        {
                            world.setBlockState(new BlockPos(houseX, houseY, houseZ + i), Blocks.SNOW.getDefaultState());

                            snowFX(houseX, houseY, houseZ + i);
                        }

                        moveNextStage();

                        break;
                    case 5:
                        for (int i = 1; i < 4; i++)
                        {
                            world.setBlockState(new BlockPos(houseX, houseY + 1, houseZ + i), Blocks.SNOW.getDefaultState());

                            snowFX(houseX, houseY + 1, houseZ + i);
                        }

                        moveNextStage();

                        break;
                    case 6:
                        for (int i = 1; i < 4; i++)
                        {
                            world.setBlockState(new BlockPos(houseX + 4, houseY, houseZ + i), Blocks.SNOW.getDefaultState());

                            snowFX(houseX + 4, houseY, houseZ + i);
                        }

                        moveNextStage();

                        break;
                    case 7:
                        for (int i = 1; i < 4; i++)
                        {
                            world.setBlockState(new BlockPos(houseX + 4, houseY + 1, houseZ + i), Blocks.SNOW.getDefaultState());

                            snowFX(houseX + 4, houseY + 1, houseZ + i);
                        }

                        moveNextStage();

                        break;
                    case 8:
                        for (int i = 1; i < 4; i++)
                        {
                            world.setBlockState(new BlockPos(houseX + i, houseY, houseZ + 4), Blocks.SNOW.getDefaultState());

                            snowFX(houseX + i, houseY, houseZ + 4);
                        }

                        moveNextStage();

                        break;
                    case 9:
                        for (int i = 1; i < 4; i++)
                        {
                            world.setBlockState(new BlockPos(houseX + i, houseY + 1, houseZ + 4), Blocks.SNOW.getDefaultState());

                            snowFX(houseX + i, houseY + 1, houseZ + 4);
                        }

                        moveNextStage();

                        break;
                    case 10:
                        for (int i = 1; i < 4; i++)
                        {
                            for (int k = 1; k < 4; k++)
                            {
                                world.setBlockState(new BlockPos(houseX + k, houseY + 2, houseZ + i), Blocks.SNOW.getDefaultState());

                                snowFX(houseX + k, houseY + 2, houseZ + i);
                            }
                        }

                        moveNextStage();

                        break;
                    case 11:
                        world.setBlockState(new BlockPos(houseX + 2, houseY + 3, houseZ + 2), Blocks.SNOW.getDefaultState());

                        snowFX(houseX + 2, houseY + 3, houseZ + 2);

                        moveNextStage();

                        break;
                    case 12:
                        Item itemToGive = CreepsItemHandler.popsicle;

                        if (rand.nextInt(5) == 0)
                        {
                            itemToGive = Items.FISH;
                        }

                        EntityItem entityItem = new EntityItem(world, houseX + 3, houseY, houseZ + 3, new ItemStack(itemToGive, rand.nextInt(4) + 1));

                        if (!world.isRemote)
                        {
                            world.spawnEntity(entityItem);
                        }

                        setWanderState(3);

                        break;
                    default:
                        break;
                }
            }

            if (rand.nextInt(10) == 0)
            {
                setHammerSwing(-2.8f);

                playSound(CreepsSoundHandler.cavemanBuildSound, 1.0f, getSoundPitch());
            }
        }
    }

    @Override
    protected boolean isMovementBlocked()
    {
        if (getFrozenLevel() >= 1 || getWanderState() == 2)
        {
            return true;
        }

        return super.isMovementBlocked();
    }

    @Override
    protected double getMoveSpeed()
    {
        if (getFrozenLevel() >= 1)
        {
            return 0.0d;
        }

        return super.getMoveSpeed();
    }

    private boolean checkArea()
    {
        int houseX = MathHelper.floor(posX);

        int houseY = MathHelper.floor(posY);

        int houseZ = MathHelper.floor(posZ);

        if (world.isAirBlock(new BlockPos(houseX, houseY - 1, houseZ)))
        {
            houseY--;
        }

        setHousePos(new BlockPos(houseX, houseY, houseZ));

        int area = 0;

        for (int a = -3; a < 7; a++)
        {
            for (int b = -3; b < 7; b++)
            {
                for (int c = 0; c < 3; c++)
                {
                    if (world.isAirBlock(new BlockPos(houseX + b, houseY + c, houseZ + a)))
                    {
                        area++;
                    }
                }
            }
        }

        if (area < 220)
        {
            return false;
        }

        for (int a = -2; a < 7; a++)
        {
            for (int b = -2; b < 7; b++)
            {
                Block block1 = world.getBlockState(new BlockPos(houseX + b, houseY, houseZ + a)).getBlock();

                Block block2 = world.getBlockState(new BlockPos(houseX + b, houseY - 1, houseZ + a)).getBlock();

                if (block1 == Blocks.SNOW || block1 == Blocks.ICE)
                {
                    area++;
                }

                if (block2 == Blocks.SNOW || block2 == Blocks.ICE)
                {
                    area++;
                }
            }
        }

        return (area > 75);
    }

    @Override
    public void knockBack(@Nonnull Entity entity, float strength, double xRatio, double zRatio)
    {
        if (getFrozenLevel() >= 1)
        {
            return;
        }

        super.knockBack(entity, strength, xRatio, zRatio);
    }

    @Override
    public float getBlockPathWeight(BlockPos blockPos)
    {
        Block block = world.getBlockState(blockPos).getBlock();

        if (block == Blocks.GRAVEL || block == Blocks.STONE)
        {
            return 10.0f;
        }

        return super.getBlockPathWeight(blockPos);
    }

    @Override
    protected void updateTexture()
    {
        if (getEvil())
        {
            if (getCaveGirl())
            {
                setTexture("textures/entity/cavemanladyevil.png");
            }
            else
            {
                setTexture("textures/entity/cavemanevil.png");
            }
        }
        else if (getCaveGirl())
        {
            setTexture("textures/entity/cavemanlady.png");
        }
        else
        {
            setTexture("textures/entity/caveman.png");
        }
    }

    @Override
    public int getTalkInterval()
    {
        if (getEvil())
        {
            return 120;
        }

        return 180;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (getEvil())
        {
            return CreepsSoundHandler.cavemanEvilSound;
        }
        else if (getAttackTarget() != null)
        {
            if (getCaveGirl())
            {
                return CreepsSoundHandler.cavewomanFreeSound;
            }
            else
            {
                return CreepsSoundHandler.cavemanFreeSound;
            }
        }
        else if (getCaveGirl())
        {
            if (getFrozenLevel() < 1)
            {
                return CreepsSoundHandler.cavewomanFreeSound;
            }
            else
            {
                return CreepsSoundHandler.cavewomanFrozenSound;
            }
        }
        else if (getFrozenLevel() < 1)
        {
            return CreepsSoundHandler.cavemanFreeSound;
        }

        return CreepsSoundHandler.cavemanFrozenSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        if (getFrozenLevel() > 0)
        {
            return null;
        }
        else if (getCaveGirl())
        {
            return CreepsSoundHandler.cavewomanHurtSound;
        }

        return CreepsSoundHandler.cavemanHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        if (getCaveGirl())
        {
            return CreepsSoundHandler.cavewomanDeadSound;
        }

        return CreepsSoundHandler.cavemanDeadSound;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 0)
        {
            dropItem(Items.PORKCHOP, rand.nextInt(3) + 1);
        }

        if (rand.nextInt(10) == 0)
        {
            dropItem(CreepsItemHandler.popsicle, rand.nextInt(3) + 1);
        }

        if (rand.nextInt(8) == 0)
        {
            dropItem(CreepsItemHandler.cavemanClub, 1);
        }
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount)
    {
        if (getFrozenLevel() < 1)
        {
            setEvil(true);

            updateTexture();

            initEntityAI();

            return super.attackEntityFrom(source, amount);
        }
        else
        {
            if (source.getTrueSource() instanceof EntityPlayer)
            {
                playSound(CreepsSoundHandler.cavemanNiceSound, 0.5f, getSoundPitch());

                if (!world.isRemote && rand.nextInt(100) > 65)
                {
                    setFrozenLevel(getFrozenLevel() - 1);

                    for (int i = 0; i < 35; i++)
                    {
                        world.spawnParticle(EnumParticleTypes.SNOWBALL, posX, posY + 1.0d, posZ, 0.0d, 0.0d, 0.0d);
                    }
                }
            }

            hurtTime = 0;
        }

        return false;
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsCaveman");

        props.setInteger("FrozenLevel", getFrozenLevel());

        props.setBoolean("CaveGirl", getCaveGirl());

        props.setBoolean("Evil", getEvil());

        props.setInteger("HouseChunk", getHouseChunk());

        BlockPos blockPos = getHousePos();

        props.setInteger("HouseX", blockPos.getX());

        props.setInteger("HouseY", blockPos.getY());

        props.setInteger("HouseZ", blockPos.getZ());

        compound.setTag("MoreCreepsCaveman", props);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsCaveman");

        if (props.hasKey("FrozenLevel"))
        {
            setFrozenLevel(props.getInteger("FrozenLevel"));
        }

        if (props.hasKey("CaveGirl"))
        {
            setCaveGirl(props.getBoolean("CaveGirl"));
        }

        if (props.hasKey("Evil"))
        {
            setEvil(props.getBoolean("Evil"));
        }

        if (props.hasKey("HouseChunk"))
        {
            setHouseChunk(props.getInteger("HouseChunk"));
        }

        if (props.hasKey("HouseX") && props.hasKey("HouseY") && props.hasKey("HouseZ"))
        {
            setHousePos(new BlockPos(props.getInteger("HouseX"), props.getInteger("HouseY"), props.getInteger("HouseZ")));
        }

        updateAttributes();

        initEntityAI();
    }

    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entity)
    {
        float f = (float)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();

        if (f < 2.9d && entity.getEntityBoundingBox().maxY > getEntityBoundingBox().minY && entity.getEntityBoundingBox().minY < getEntityBoundingBox().maxY)
        {
            if (getHammerSwing() == 0.0f)
            {
                setHammerSwing(-2.8f);
            }

            if (talkDelay-- < 0)
            {
                playSound(CreepsSoundHandler.cavemanEvilSound, 0.5f, getSoundPitch());

                talkDelay = 2;
            }
        }

        return super.attackEntityAsMob(entity);
    }

    private void snowFX(int x, int y, int z)
    {
        for (int i = 0; i < 40; i++)
        {
            world.spawnParticle(EnumParticleTypes.SNOW_SHOVEL, x, y + 0.5d, z, 1.0d, 1.0d, 1.0d);
        }
    }

    private void moveNextStage()
    {
        setHammerSwing(-2.8f);

        playSound(CreepsSoundHandler.cavemanBuildSound, 1.0f, getSoundPitch());

        setHouseChunk(getHouseChunk() + 1);
    }

    protected void setHouseChunk(int i)
    {
        dataManager.set(houseChunk, i);
    }

    public int getHouseChunk()
    {
        return dataManager.get(houseChunk);
    }

    protected void setHousePos(BlockPos pos)
    {
        dataManager.set(housePos, new BlockPos(pos.getX(), pos.getY(), pos.getZ()));

        dataManager.setDirty(housePos);
    }

    public BlockPos getHousePos()
    {
        return dataManager.get(housePos);
    }
}

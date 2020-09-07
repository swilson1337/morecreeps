package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
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
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityBum extends EntityCreepBase implements IMob
{
    private static final DataParameter<Integer> timeToPee = EntityDataManager.createKey(EntityBum.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> bumGave = EntityDataManager.<Boolean>createKey(EntityBum.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> angerLevel = EntityDataManager.createKey(EntityBum.class, DataSerializers.VARINT);

    private float bumRotation = 999.0f;

    public EntityBum(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Bum");

        creatureType = EnumCreatureType.MONSTER;

        baseSpeed = 0.25d;

        baseHealth = 50.0f;

        baseAttackDamage = 5.0d;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        if (getBumGave())
        {
            setTexture("textures/entity/bumdressed.png");
        }
        else
        {
            setTexture("textures/entity/bum.png");
        }
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(timeToPee, rand.nextInt(900) + 500);

        dataManager.register(bumGave, Boolean.valueOf(false));

        dataManager.register(angerLevel, 0);
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
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (getTimeToPee() > 0 || getBumGave() || !MoreCreepsConfig.publicUrination)
        {
            return CreepsSoundHandler.bumSound;
        }

        return CreepsSoundHandler.bumLivingPeeSound;
    }

    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSource)
    {
        return CreepsSoundHandler.bumHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.bumDeathSound;
    }

    private void setBumGave(boolean b)
    {
        dataManager.set(bumGave, Boolean.valueOf(b));
    }

    public boolean getBumGave()
    {
        return ((Boolean)dataManager.get(bumGave)).booleanValue();
    }

    private void setTimeToPee(int i)
    {
        dataManager.set(timeToPee, i);
    }

    public int getTimeToPee()
    {
        return dataManager.get(timeToPee);
    }

    private void setAngerLevel(int i)
    {
        dataManager.set(angerLevel, i);
    }

    public int getAngerLevel()
    {
        return dataManager.get(angerLevel);
    }

    @Override
    protected void dropItemsOnDeath()
    {
        dropItem(Items.COOKED_PORKCHOP, 1);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (hand == EnumHand.OFF_HAND)
        {
            return super.processInteract(player, hand);
        }

        ItemStack itemStack = player.getHeldItem(hand);

        if (!getBumGave() && getAngerLevel() == 0)
        {
            if (!itemStack.isEmpty())
            {
                Item item = itemStack.getItem();

                if (item == Items.DIAMOND || item == Items.GOLD_INGOT || item == Items.IRON_INGOT)
                {
                    int value = 0;

                    if (item == Items.IRON_INGOT)
                    {
                        value = rand.nextInt(2) + 1;
                    }
                    else if (item == Items.GOLD_INGOT)
                    {
                        value = rand.nextInt(5) + 1;
                    }
                    else
                    {
                        value = rand.nextInt(10) + 1;
                    }

                    itemStack.shrink(1);

                    for (int i = 0; i < 4; i++)
                    {
                        for (int j = 0; j < 10; j++)
                        {
                            world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height) + (double)i, (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, rand.nextGaussian() * 0.02D, rand.nextGaussian() * 0.02D ,rand.nextGaussian() * 0.02D);
                        }
                    }

                    setBumGave(true);

                    setAngerLevel(0);

                    resetTarget();

                    updateAttributes();

                    if (rand.nextInt(5) == 0)
                    {
                        playSound(CreepsSoundHandler.bumSuckerSound, getSoundVolume(), getSoundPitch());
                    }
                    else
                    {
                        playSound(CreepsSoundHandler.bumThankYouSound, getSoundVolume(), getSoundPitch());

                        for (int j = 0; j < 10; j++)
                        {
                            world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, rand.nextGaussian() * 0.02D, rand.nextGaussian() * 0.02D, rand.nextGaussian() * 0.02D);
                        }

                        if (!world.isRemote)
                        {
                            for (int k = 0; k < value; k++)
                            {
                                dropItem(Item.getItemById(rand.nextInt(95)), 1);

                                dropItem(Items.IRON_SHOVEL, 1);
                            }
                        }

                        return true;
                    }
                }
                else
                {
                    if (getTimeToPee() > 0)
                    {
                        playSound(CreepsSoundHandler.bumDontWantSound, getSoundVolume(), getSoundPitch());
                    }
                    else if (item == Item.getItemFromBlock(Blocks.YELLOW_FLOWER) || item == Item.getItemFromBlock(Blocks.RED_FLOWER))
                    {
                        playSound(CreepsSoundHandler.bumThanksSound, getSoundVolume(), getSoundPitch());

                        setTimeToPee(rand.nextInt(1900) + 1500);

                        itemStack.shrink(1);
                    }
                    else if (item == Items.BUCKET)
                    {
                        playSound(CreepsSoundHandler.bumThanksSound, getSoundVolume(), getSoundPitch());

                        setTimeToPee(rand.nextInt(1900) + 1500);

                        itemStack.shrink(1);
                    }
                    else if (item == Items.LAVA_BUCKET)
                    {
                        playSound(CreepsSoundHandler.bumThanksSound, getSoundVolume(), getSoundPitch());

                        setTimeToPee(rand.nextInt(1900) + 1500);

                        itemStack.shrink(1);

                        if (!world.isRemote && rand.nextInt(4) == 0)
                        {
                            BlockPos blockPos = new BlockPos(posX, posY, posZ);

                            IBlockState blockState = world.getBlockState(blockPos);

                            int randInt = rand.nextInt(3) + 1;

                            for (int i = 0; i < randInt; i++)
                            {
                                Blocks.OBSIDIAN.dropBlockAsItem(world, blockPos, blockState, 0);
                            }
                        }

                        for (int i = 0; i < 15; i++)
                        {
                            double d4 = (float)posX + world.rand.nextFloat();
                            double d7 = (float)posY + world.rand.nextFloat();
                            double d8 = (float)posZ + world.rand.nextFloat();
                            double d9 = d4 - posX;
                            double d10 = d7 - posY;
                            double d11 = d8 - posZ;
                            double d12 = MathHelper.sqrt(d9 * d9 + d10 * d10 + d11 * d11);
                            d9 /= d12;
                            d10 /= d12;
                            d11 /= d12;
                            double d13 = 0.5D / (d12 / 10D + 0.10000000000000001D);
                            d13 *= world.rand.nextFloat() * world.rand.nextFloat() + 0.3F;
                            d9 *= d13;
                            d10 *= d13;
                            d11 *= d13;
                            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (d4 + posX * 1.0D) / 2D, (d7 + posY * 1.0D) / 2D + 2D, (d8 + posZ * 1.0D) / 2D, d9, d10, d11);
                            world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d4, d7, d8, d9, d10, d11);
                        }

                        if (rand.nextInt(4) == 0)
                        {
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(Items.BUCKET));
                        }
                    }
                    else if (!getBumGave())
                    {
                        playSound(CreepsSoundHandler.bumPeeSound, getSoundVolume(), getSoundPitch());
                    }
                }
            }
        }
        else
        {
            playSound(CreepsSoundHandler.bumLeaveMeAloneSound, getSoundVolume(), getSoundPitch());
        }

        return super.processInteract(player, hand);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        setTimeToPee(getTimeToPee() - 1);

        if (getAttackTarget() == null && getTimeToPee() < 0 && !getBumGave() && MoreCreepsConfig.publicUrination)
        {
            isJumping = false;

            if (bumRotation == 999.0f)
            {
                bumRotation = rotationYaw;
            }

            rotationYaw = bumRotation;

            baseSpeed = 0.0d;

            updateMoveSpeed();

            if (!onGround)
            {
                motionY -= 0.5d;
            }

            if (world.isRemote)
            {
                MoreCreepsAndWeirdos.proxy.pee(this);
            }

            if (getTimeToPee() < -200)
            {
                setTimeToPee(rand.nextInt(600) + 600);

                bumRotation = 999.0f;

                int j = MathHelper.floor(posX);

                int k = MathHelper.floor(getEntityBoundingBox().minY);

                int l = MathHelper.floor(posZ);

                for (int i1 = -1; i1 < 2; i1++)
                {
                    for (int j1 = -1; j1 < 2; j1++)
                    {
                        if (rand.nextInt(3) != 0)
                        {
                            continue;
                        }

                        Block k1 = world.getBlockState(new BlockPos(j + j1, k - 1, l - i1)).getBlock();

                        Block l1 = world.getBlockState(new BlockPos(j + j1, k, l - i1)).getBlock();

                        if (rand.nextInt(2) == 0)
                        {
                            if ((k1 == Blocks.GRASS || k1 == Blocks.DIRT) && l1 == Blocks.AIR)
                            {
                                world.setBlockState(new BlockPos(j + j1, k, l - i1), Blocks.YELLOW_FLOWER.getDefaultState());
                            }

                            continue;
                        }

                        if ((k1 == Blocks.GRASS || k1 == Blocks.DIRT) && l1 == Blocks.AIR)
                        {
                            world.setBlockState(new BlockPos(j + j1, k, l - i1), Blocks.RED_FLOWER.getDefaultState());
                        }
                    }
                }
            }
        }
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsBum");

        props.setBoolean("BumGave", getBumGave());

        props.setInteger("AngerLevel", getAngerLevel());

        props.setInteger("TimeToPee", getTimeToPee());

        compound.setTag("MoreCreepsBum", props);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsBum");

        if (props.hasKey("BumGave"))
        {
            setBumGave(props.getBoolean("BumGave"));
        }

        if (props.hasKey("AngerLevel"))
        {
            setAngerLevel(props.getInteger("AngerLevel"));
        }

        if (props.hasKey("TimeToPee"))
        {
            setTimeToPee(props.getInteger("TimeToPee"));
        }
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount)
    {
        boolean flag = super.attackEntityFrom(source, amount);

        if (flag)
        {
            setTimeToPee(rand.nextInt(900) + 500);

            bumRotation = 999.0f;
        }

        return flag;
    }
}

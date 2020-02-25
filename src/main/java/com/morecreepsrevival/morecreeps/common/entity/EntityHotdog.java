package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityHotdog extends EntityCreepBase
{
    private static final DataParameter<Boolean> heavenBuilt = EntityDataManager.<Boolean>createKey(EntityHotdog.class, DataSerializers.BOOLEAN);

    private static final String[] textures = {
            "textures/entity/hotdg1",
            "textures/entity/hotdg2",
            "textures/entity/hotdg3"
    };

    private static final String[] names = {
            "Pogo", "Spot", "King", "Prince", "Bosco", "Ralph", "Wendy", "Trixie", "Bowser", "The Heat",
            "Weiner", "Wendon the Weiner", "Wallace the Weiner", "William the Weiner", "Terrance", "Elijah", "Good Boy", "Boy", "Girl", "Tennis Shoe",
            "Rusty", "Mean Joe Green", "Lawrence", "Foxy", "SlyFoxHound", "Leroy Brown",
            "Mickey", "Holly", "Yeontan"
    };

    private static final String[] levelNames = {
            "Just A Pup", "Hotdog", "A Dirty Dog", "An Alley Dog", "Scrapyard Puppy", "Army Dog", "Private", "Private First Class", "Corporal", "Sergeant",
            "Staff Sergeant", "Sergeant First Class", "Master Segeant", "First Sergeant", "Sergeant Major", "Command Sergeant Major", "Second Lieutenant", "First Lieutenant", "Captain", "Major",
            "Lieutenant Colonel", "Colonel", "General of the Hotdog Army", "General of the Hotdog Army", "Sparky the Wonder Pooch", "Sparky the Wonder Pooch"
    };

    private static final int[] levelDamages = {
            0, 50, 100, 250, 500, 800, 1200, 1700, 2200, 2700,
            3300, 3900, 4700, 5400, 6200, 7000, 7900, 8800, 9750, 10750,
            12500, 17500, 22500, 30000, 40000, 50000, 60000
    };

    public EntityHotdog(World world)
    {
        super(world);

        setCreepTypeName("Hotdog");

        setSize(0.5f, 0.75f);

        setModelSize(0.6f);

        baseHealth = (float)rand.nextInt(15) + 5.0f;

        baseSpeed = 0.35f;

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(heavenBuilt, Boolean.valueOf(false));
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 2;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (!isRiding())
        {
            if (rand.nextInt(5) == 0)
            {
                return CreepsSoundHandler.hotdogSound;
            }
        }
        else if (rand.nextInt(10) == 0)
        {
            return SoundEvents.ENTITY_WOLF_PANT;
        }

        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.hotdogHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.hotdogDeathSound;
    }

    @Override
    protected SoundEvent getMountSound()
    {
        return CreepsSoundHandler.hotdogPickupSound;
    }

    @Override
    protected SoundEvent getUnmountSound()
    {
        return CreepsSoundHandler.hotdogPutDownSound;
    }

    @Override
    protected SoundEvent getEatSound()
    {
        return CreepsSoundHandler.hotdogEatSound;
    }

    @Override
    protected SoundEvent getKillSound()
    {
        return CreepsSoundHandler.hotdogKillSound;
    }

    @Override
    protected SoundEvent getAngrySound()
    {
        return CreepsSoundHandler.hotdogAttackSound;
    }

    @Override
    protected SoundEvent getLevelUpSound()
    {
        return CreepsSoundHandler.guineaPigLevelUpSound;
    }

    @Override
    protected SoundEvent getSpeedUpSound()
    {
        return CreepsSoundHandler.guineaPigSpeedUpSound;
    }

    @Override
    protected SoundEvent getSpeedDownSound()
    {
        return CreepsSoundHandler.guineaPigSpeedDownSound;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        dropItem(Items.PORKCHOP, 1);
    }

    @Override
    protected String[] getTamedNames()
    {
        return names;
    }

    @Override
    protected String[] getAvailableTextures()
    {
        return textures;
    }

    @Override
    public boolean isTamable()
    {
        return true;
    }

    @Override
    public boolean isStackable()
    {
        return true;
    }

    @Override
    public boolean canRidePlayer()
    {
        return true;
    }

    @Override
    public String getLevelName()
    {
        return levelNames[getLevel()];
    }

    @Override
    public int getLevelDamage()
    {
        return levelDamages[getLevel()];
    }

    @Override
    public int getMaxLevel()
    {
        return 25;
    }

    @Override
    public void onRevive(NBTTagCompound compound)
    {
        super.onRevive(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsHotDog");

        setHeavenBuilt(props.getBoolean("HeavenBuilt"));
    }

    @Override
    public void onTombstoneCreate(NBTTagCompound compound)
    {
        super.onTombstoneCreate(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsHotDog");

        props.setBoolean("HeavenBuilt", getHeavenBuilt());

        compound.setTag("MoreCreepsHeavenBuilt", props);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (hand == EnumHand.OFF_HAND)
        {
            return super.processInteract(player, hand);
        }

        ItemStack itemStack = player.getHeldItem(hand);

        if (!itemStack.isEmpty())
        {
            Item item = itemStack.getItem();

            if (isTamed() && isPlayerOwner(player))
            {
                if (item == Items.DIAMOND)
                {
                    if (isRiding())
                    {
                        if (!world.isRemote)
                        {
                            player.sendMessage(new TextComponentString("Put your Hotdog down before building the Hotdog Heaven!"));
                        }
                    }
                    else if (!getHeavenBuilt())
                    {
                        if (getLevel() >= 25)
                        {
                            if (buildHeaven(player, MathHelper.floor(player.posX) + 2, MathHelper.floor(player.getEntityBoundingBox().minY), MathHelper.floor(player.posZ) + 2))
                            {
                                //player.playSound(MoreCreepsAndWeirdos.achievementSound, 1.0f, 1.0f);
                                // TODO: add achievements bro

                                playSound(SoundEvents.ENTITY_TNT_PRIMED, 1.0f, 0.5f);

                                itemStack.shrink(1);
                            }
                        }
                        else if (!world.isRemote)
                        {
                            player.sendMessage(new TextComponentString("Your Hotdog must be level 25 to build a Hotdog Heaven."));

                            player.sendMessage(new TextComponentString("\247b" + getCreepName() + " is only level \247f" + getLevel() + "."));
                        }
                    }
                    else if (!world.isRemote)
                    {
                        player.sendMessage(new TextComponentString("\247b" + getCreepName() + "\247f has already built a Hotdog Heaven."));
                    }

                    return true;
                }
                else if (item == Item.getItemFromBlock(Blocks.RED_FLOWER) || item == Item.getItemFromBlock(Blocks.YELLOW_FLOWER))
                {
                    smokePlain();

                    switch (getWanderState())
                    {
                        case 0:
                            if (!world.isRemote)
                            {
                                player.sendMessage(new TextComponentString("\2473" + getCreepName() + "\2476 will \247dWANDER\2476 around and have fun."));
                            }

                            setWanderState(1);

                            break;
                        case 1:
                            if (!world.isRemote)
                            {
                                player.sendMessage(new TextComponentString("\2473" + getCreepName() + "\2476 will \247dFIGHT\2476 and follow you!"));
                            }

                            setWanderState(2);

                            break;
                        case 2:
                            if (!world.isRemote)
                            {
                                player.sendMessage(new TextComponentString("\2473" + getCreepName() + "\2476 will \247dSTAY\2476 right here."));
                            }

                            setWanderState(0);

                            break;
                        default:
                            break;
                    }

                    itemStack.shrink(1);

                    return true;
                }
                else if (item == Items.REEDS)
                {
                    giveSpeedBoost(13000);

                    itemStack.shrink(1);

                    return true;
                }
                else if (item == Items.LEATHER_BOOTS || item == Items.LEATHER_CHESTPLATE || item == Items.LEATHER_HELMET || item == Items.LEATHER_LEGGINGS)
                {
                    setArmor(1);

                    setHealth(getMaxHealth());

                    smoke();

                    playSound(CreepsSoundHandler.guineaPigArmorSound, 1.0f, (rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f);

                    itemStack.shrink(1);

                    return true;
                }
                else if (item == Items.GOLDEN_BOOTS || item == Items.GOLDEN_CHESTPLATE || item == Items.GOLDEN_HELMET || item == Items.GOLDEN_LEGGINGS)
                {
                    setArmor(2);

                    setHealth(getMaxHealth());

                    smoke();

                    playSound(CreepsSoundHandler.guineaPigArmorSound, 1.0f, (rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f);

                    itemStack.shrink(1);

                    return true;
                }
                else if (item == Items.IRON_BOOTS || item == Items.IRON_CHESTPLATE || item == Items.IRON_HELMET || item == Items.IRON_LEGGINGS)
                {
                    setArmor(3);

                    setHealth(getMaxHealth());

                    smoke();

                    playSound(CreepsSoundHandler.guineaPigArmorSound, 1.0f, (rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f);

                    itemStack.shrink(1);

                    return true;
                }
                else if (item == Items.DIAMOND_BOOTS || item == Items.DIAMOND_CHESTPLATE || item == Items.DIAMOND_HELMET || item == Items.DIAMOND_LEGGINGS)
                {
                    setArmor(4);

                    setHealth(getMaxHealth());

                    smoke();

                    playSound(CreepsSoundHandler.guineaPigArmorSound, 1.0f, (rand.nextFloat() - rand.nextFloat()) * 0.2f + 1.0f);

                    itemStack.shrink(1);

                    return true;
                }
            }

            if (item == Items.EGG)
            {
                playSound(SoundEvents.ENTITY_TNT_PRIMED, 1.0f, 0.5f);

                setLocationAndAngles(player.posX, player.posY + (double)player.getEyeHeight(), player.posZ, player.rotationYaw, player.rotationPitch);

                motionX = -MathHelper.sin((rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float)Math.PI);

                motionZ = MathHelper.cos((rotationYaw / 180F) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180F) * (float)Math.PI);

                double d = motionX / 100.0d;

                double d1 = motionZ / 100.0d;

                for (int i = 0; i < 2000; i++)
                {
                    move(MoverType.SELF, d, 0.0d, d1);

                    world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d);
                }

                world.createExplosion(null, posX, posY, posZ, 1.1f, true);

                //interest = 0;

                setDead();

                itemStack.shrink(1);

                return true;
            }
            else if (item == Items.BONE)
            {
                feed(player,10, 15);

                smoke();

                itemStack.shrink(1);

                return true;
            }
            else if (item == Items.PORKCHOP)
            {
                feed(player,15, 30);

                smoke();

                itemStack.shrink(1);

                return true;
            }
            else if (item == Items.COOKED_PORKCHOP)
            {
                feed(player,25, 55);

                smoke();

                itemStack.shrink(1);

                return true;
            }
        }

        return super.processInteract(player, hand);
    }

    @Override
    protected void updateModelSize()
    {
        float dogSize = 0.6f + ((getLevel() - 1) * 0.05f);

        if (dogSize > 1.5f)
        {
            dogSize = 1.5f;
        }

        setModelSize(dogSize);
    }

    protected void setHeavenBuilt(boolean b)
    {
        dataManager.set(heavenBuilt, Boolean.valueOf(b));
    }

    public boolean getHeavenBuilt()
    {
        return ((Boolean)dataManager.get(heavenBuilt)).booleanValue();
    }

    @Override
    protected boolean canUseTamableMenu()
    {
        return true;
    }

    @Override
    protected SoundEvent getTamedSound()
    {
        return CreepsSoundHandler.hotdogTamedSound;
    }

    private boolean buildHeaven(EntityPlayer player, int x, int y, int z)
    {
        if (y > 95)
        {
            player.sendMessage(new TextComponentString("You are too far up to build Hotdog Heaven!"));

            return false;
        }

        byte byte0 = 40;

        byte byte1 = 40;

        int l = (105 - y) / 2;

        int area = 0;

        for (int h = 0; h < l * 2; h++)
        {
            for (int i = -2; i < byte0 + 2; i++)
            {
                for (int j = -2; j < byte1 + 2; j++)
                {
                    if (!world.isAirBlock(new BlockPos(x + i, y + h, z + j)))
                    {
                        area++;
                    }
                }
            }
        }

        if (area < 3000)
        {
            setHeavenBuilt(true);

            playSound(CreepsSoundHandler.hotdogHeavenSound, getSoundVolume(), getSoundPitch());

            if (!world.isRemote)
            {
                player.sendMessage(new TextComponentString("HOT DOG HEAVEN HAS BEEN BUILT!"));
            }

            world.setBlockState(new BlockPos(x, y, z), Blocks.PLANKS.getDefaultState());

            world.setBlockState(new BlockPos(x, y + 1, z), Blocks.TORCH.getDefaultState());

            world.setBlockState(new BlockPos(x + 5, y, z), Blocks.PLANKS.getDefaultState());

            world.setBlockState(new BlockPos(x + 5, y + 1, z), Blocks.PLANKS.getDefaultState());

            for (int i = 0; i < l; i++)
            {
                for (int q = 0; q < 4; q++)
                {
                    world.setBlockState(new BlockPos(x + q + 1, y + i, z + i), Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));
                }
            }

            for (int i = 0; i < (l - 1); i++)
            {
                for (int q = 0; q < 4; q++)
                {
                    world.setBlockState(new BlockPos(x - q, y + l + i, (z + l) - i), Blocks.OAK_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));
                }
            }

            boolean flag = false;

            for (int i = 0; i < 10; i++)
            {
                world.setBlockState(new BlockPos((x - i) + 5, y + l, z + l + 6), Blocks.OAK_FENCE.getDefaultState());

                for (int q = 0; q < 7; q++)
                {
                    world.setBlockState(new BlockPos(x + 5, y + l, z + l + q), Blocks.OAK_FENCE.getDefaultState());

                    world.setBlockState(new BlockPos(x - 4, y + l, z + l + q), Blocks.OAK_FENCE.getDefaultState());

                    flag = !flag;

                    if (flag)
                    {
                        world.setBlockState(new BlockPos(x + 5, y + l + 1, z + l + q), Blocks.TORCH.getDefaultState());

                        world.setBlockState(new BlockPos(x - 4, y + l + 1, z + l + q), Blocks.TORCH.getDefaultState());
                    }

                    world.setBlockState(new BlockPos((x - i) + 5, (y + l) - 1, z + l + q), Blocks.PLANKS.getDefaultState());
                }
            }

            for (int i = 0; i < byte0; i++)
            {
                for (int q = 0; q < byte1; q++)
                {
                    for (int k = (-rand.nextInt(3) - 2); k < 1; k++)
                    {
                        if (k < 0)
                        {
                            world.setBlockState(new BlockPos((x + i) - byte0 / 2, (y + l * 2 + k) - 2, (z + q) - byte1), Blocks.DIRT.getDefaultState());
                        }
                        else
                        {
                            world.setBlockState(new BlockPos((x + i) - byte0 / 2, (y + l * 2 + k) - 2, ((z + q) - byte1) + 2), Blocks.GRASS.getDefaultState());
                        }
                    }
                }
            }

            int randInt = rand.nextInt(10) + 2;

            for (int i = 0; i < randInt; i++)
            {
                world.setBlockState(new BlockPos((x + rand.nextInt(byte0 - 10)) - byte0 / 2, (y + l * 2) - 1, z + rand.nextInt(byte1 - 6) - byte1), Blocks.DEADBUSH.getDefaultState());
            }

            randInt = rand.nextInt(10) + 2;

            for (int i = 0; i < randInt; i++)
            {
                world.setBlockState(new BlockPos((x + rand.nextInt(byte0 - 10)) - byte0 / 2, (y + l * 2) - 1, z + rand.nextInt(byte1 - 6) - byte1), Blocks.YELLOW_FLOWER.getDefaultState());
            }

            randInt = rand.nextInt(10) + 2;

            for (int i = 0; i < randInt; i++)
            {
                world.setBlockState(new BlockPos((x + rand.nextInt(byte0 - 10)) - byte0 / 2, (y + l * 2) - 1, (z + rand.nextInt(byte1 - 6)) - byte1), Blocks.RED_FLOWER.getDefaultState());
            }

            randInt = rand.nextInt(30) + 2;

            for (int i = 0; i < randInt; i++)
            {
                int j6 = rand.nextInt(byte0 - 12);

                int l7 = rand.nextInt(byte1 - 8);

                BlockPos blockPos = new BlockPos((x + j6) - byte0 / 2, (y + l * 2) - 1, (z + l7) - byte1);

                if (world.isAirBlock(blockPos))
                {
                    world.setBlockState(blockPos, Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS));
                }
            }

            randInt = rand.nextInt(50) + 2;

            for (int i = 0; i < randInt; i++)
            {
                int k6 = rand.nextInt(byte0 - 12);

                int i8 = rand.nextInt(byte1 - 8);

                BlockPos blockPos = new BlockPos((x + k6) - byte0 / 2, (y + l * 2) - 1, (z + i8) - byte1);

                if (world.isAirBlock(blockPos))
                {
                    world.setBlockState(blockPos, Blocks.TALLGRASS.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.FERN));
                }
            }

            for (int i = 1; i < (byte0 - 1); i++)
            {
                world.setBlockState(new BlockPos((x + i) - byte0 / 2, (y + l * 2) - 1, (z - byte1) + 3), Blocks.OAK_FENCE.getDefaultState());

                world.setBlockState(new BlockPos((x + i) - byte0 / 2, (y + l * 2) - 1, z), Blocks.OAK_FENCE.getDefaultState());

                flag = !flag;

                if (flag)
                {
                    world.setBlockState(new BlockPos((x + i) - byte0 / 2, y + l * 2, (z - byte1) + 3), Blocks.TORCH.getDefaultState());

                    world.setBlockState(new BlockPos((x + i) - byte0 / 2, y + l * 2, z), Blocks.TORCH.getDefaultState());
                }
            }

            for (int i = 4; i < byte1; i++)
            {
                world.setBlockState(new BlockPos((x - byte0 / 2) + 1, (y + l * 2) - 1, (z + i) - byte1), Blocks.OAK_FENCE.getDefaultState());

                world.setBlockState(new BlockPos((x + byte0) - byte0 / 2 - 2, (y + l * 2) - 1, (z + i) - byte1), Blocks.OAK_FENCE.getDefaultState());

                flag = !flag;

                if (flag)
                {
                    world.setBlockState(new BlockPos((x - byte0 / 2) + 1, y + l * 2, (z + i) - byte1), Blocks.TORCH.getDefaultState());

                    world.setBlockState(new BlockPos((x + byte0) - byte0 / 2 - 2, y + l * 2, (z + i) - byte1), Blocks.TORCH.getDefaultState());
                }
            }

            world.setBlockState(new BlockPos(x - 1, (y + l * 2) - 1, z), Blocks.OAK_FENCE_GATE.getDefaultState());

            world.setBlockState(new BlockPos(x - 2, (y + l * 2) - 1, z), Blocks.OAK_FENCE_GATE.getDefaultState());

            for (int i = 0; i < 6; i++)
            {
                EntityDogHouse dogHouse = new EntityDogHouse(world);

                dogHouse.setLocationAndAngles(x + 15, (y + l * 2) - 1, z - 7 - i * 5, 90.0f, 0.0f);

                dogHouse.setInitialHealth();

                dogHouse.determineBaseTexture();

                world.spawnEntity(dogHouse);
            }

            randInt = rand.nextInt(15) + 5;

            for (int i = 0; i < randInt; i++)
            {
                int l6 = rand.nextInt(byte0 - 10) + 3;

                int j8 = rand.nextInt(byte1 - 6) + 3;

                BlockPos blockPos = new BlockPos((x + l6) - byte0 / 2, (y + l * 2) - 1, (z + j8) - byte1);

                world.setBlockState(blockPos, Blocks.SAPLING.getDefaultState().withProperty(BlockSapling.STAGE, 1));

                ((BlockSapling)Blocks.SAPLING).grow(world, blockPos, world.getBlockState(blockPos), rand);
            }

            randInt = ((byte0 / 2 + rand.nextInt(10)) - 5) + 8;

            for (int i = ((byte0 / 2 + rand.nextInt(8)) - 8); i < randInt; i++)
            {
                int randInt2 = ((byte1 / 2 + rand.nextInt(10)) - 5) + 8;

                for (int q = ((byte1 / 2 + rand.nextInt(8)) - 8); q < randInt2; q++)
                {
                    world.setBlockState(new BlockPos((x + i) - byte0 / 2, (y + l * 2) - 2, (z + q) - byte1), Blocks.WATER.getDefaultState());

                    world.setBlockState(new BlockPos((x + i) - byte0 / 2, (y + l * 2) - 3, (z + q) - byte1), Blocks.WATER.getDefaultState());
                }
            }

            BlockPos chest1Pos = new BlockPos(x + 7, (y + l * 2) - 1, z - 5);

            world.setBlockState(chest1Pos, Blocks.CHEST.getDefaultState());

            TileEntityChest chest1 = new TileEntityChest();

            world.setTileEntity(chest1Pos, chest1);

            BlockPos chest2Pos = new BlockPos(x + 7, (y + l * 2) - 1, z - 6);

            world.setBlockState(chest2Pos, Blocks.CHEST.getDefaultState());

            TileEntityChest chest2 = new TileEntityChest();

            world.setTileEntity(chest2Pos, chest2);

            int maxI = chest1.getSizeInventory() - 9;

            for (int i = 0; i < maxI; i++)
            {
                chest1.setInventorySlotContents(i, new ItemStack(Items.BONE, 32));

                chest2.setInventorySlotContents(i, new ItemStack(Items.REDSTONE, 32));
            }

            maxI = chest1.getSizeInventory();

            for (int i = (maxI - 9); i < maxI; i++)
            {
                chest1.setInventorySlotContents(i, new ItemStack(Items.GOLDEN_HELMET, 1));

                chest2.setInventorySlotContents(i, new ItemStack(Items.GOLD_INGOT, 1));
            }

            BlockPos chest3Pos = new BlockPos(x - 7, (y + l * 2) - 1, z - 5);

            world.setBlockState(chest3Pos, Blocks.CHEST.getDefaultState());

            TileEntityChest chest3 = new TileEntityChest();

            world.setTileEntity(chest3Pos, chest3);

            BlockPos chest4Pos = new BlockPos(x - 7, (y + l * 2) - 1, z - 6);

            world.setBlockState(chest4Pos, Blocks.CHEST.getDefaultState());

            TileEntityChest chest4 = new TileEntityChest();

            world.setTileEntity(chest4Pos, chest4);

            maxI = chest3.getSizeInventory() - 9;

            for (int i = 0; i < maxI; i++)
            {
                chest3.setInventorySlotContents(i, new ItemStack(Items.BONE, 32));

                chest4.setInventorySlotContents(i, new ItemStack(Items.REDSTONE, 32));
            }

            maxI = chest3.getSizeInventory();

            for (int i = (maxI - 9); i < maxI; i++)
            {
                chest3.setInventorySlotContents(i, new ItemStack(Items.DIAMOND_HELMET, 1));

                chest4.setInventorySlotContents(i, new ItemStack(Items.DIAMOND, 1));
            }
        }
        else if (!world.isRemote)
        {
            player.sendMessage(new TextComponentString("Too many obstructions, choose another spot!"));
        }

        return false;
    }

    @Override
    public boolean canLevelUp()
    {
        return true;
    }

    @Override
    public boolean canBeRevived()
    {
        return true;
    }
}

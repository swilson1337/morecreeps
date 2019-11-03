package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

public class EntityHotdog extends EntityCreepBase
{
    private static final DataParameter<Boolean> angryDog = EntityDataManager.createKey(EntityHotdog.class, DataSerializers.BOOLEAN);

    private static final String[] textures = {
            "textures/entity/hotdg1",
            "textures/entity/hotdg2",
            "textures/entity/hotdg3"
    };

    private static final String[] names = {
            "Pogo", "Spot", "King", "Prince", "Bosco", "Ralph", "Wendy", "Trixie", "Bowser", "The Heat",
            "Weiner", "Wendon the Weiner", "Wallace the Weiner", "William the Weiner", "Terrance", "Elijah", "Good Boy", "Boy", "Girl", "Tennis Shoe",
            "Rusty", "Mean Joe Green", "Lawrence", "Foxy", "SlyFoxHound", "Leroy Brown"
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

        dataManager.register(angryDog, false);
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

    private void setAngryDog(boolean b)
    {
        dataManager.set(angryDog, b);
    }

    public boolean getAngryDog()
    {
        return dataManager.get(angryDog);
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand)
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
                    /*else if (!getHotelBuilt())
                    {
                        if (getLevel() >= 20)
                        {
                            if (createHotel(player, MathHelper.floor(player.posX) + 2, MathHelper.floor(player.getEntityBoundingBox().minY), MathHelper.floor(player.posZ) + 2))
                            {
                                //player.playSound(MoreCreepsAndWeirdos.achievementSound, 1.0f, 1.0f);
                                // TODO: add achievements bro

                                playSound(SoundEvents.ENTITY_TNT_PRIMED, 1.0f, 0.5f);

                                itemStack.shrink(1);
                            }
                        }
                        else if (!world.isRemote)
                        {
                            player.sendMessage(new TextComponentString("Your Guinea Pig must be level 20 to build a Hotel."));

                            player.sendMessage(new TextComponentString("\247b" + getCreepName() + " is only level \247f" + getLevel() + "."));
                        }
                    }
                    else if (!world.isRemote)
                    {
                        player.sendMessage(new TextComponentString("\247b" + getCreepName() + "\247f has already built a Hotel."));
                    }*/

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
}

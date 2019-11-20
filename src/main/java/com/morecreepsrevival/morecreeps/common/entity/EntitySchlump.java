package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntitySchlump extends EntityCreepBase
{
    private static final DataParameter<Integer> age = EntityDataManager.createKey(EntitySchlump.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> ageTimer = EntityDataManager.createKey(EntitySchlump.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> placed = EntityDataManager.createKey(EntitySchlump.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> payoutTimer = EntityDataManager.createKey(EntitySchlump.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> saved = EntityDataManager.createKey(EntitySchlump.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> waitTime = EntityDataManager.createKey(EntitySchlump.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> deathTimer = EntityDataManager.createKey(EntitySchlump.class, DataSerializers.VARINT);

    public EntitySchlump(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Schlump");

        baseSpeed = 0.0d;

        baseHealth = (float)rand.nextInt(10) + 10.0f;

        setModelSize(0.4f);

        setSize(width * 0.4f, height * 0.4f);

        updateAttributes();
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(age, 0);

        dataManager.register(ageTimer, 0);

        dataManager.register(placed, false);

        dataManager.register(payoutTimer, 0);

        dataManager.register(saved, false);

        dataManager.register(waitTime, 0);

        dataManager.register(deathTimer, 0);
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/schlump.png");
    }

    @Override
    protected void initEntityAI()
    {
        clearAITasks();
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (inWater)
        {
            setDead();
        }
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        ignoreFrustumCheck = true;

        dataManager.set(ageTimer, dataManager.get(ageTimer) + 1);

        if (dataManager.get(ageTimer) > 50)
        {
            if (getAge() < 22000)
            {
                setAge(getAge() + 1);
            }

            if (getAge() > 20000)
            {
                setDead();
            }

            // TODO: achievements

            if (getModelSize() < 3.5f)
            {
                setModelSize(getModelSize() + 0.001f);
            }

            dataManager.set(ageTimer, 0);

            int i = (getAge() / 100) * 2;

            if (i > 150)
            {
                i = 150;
            }

            if (getAge() > 200 && rand.nextInt(200 - i) == 0)
            {
                giveReward();
            }
        }

        if (!getPlaced())
        {
            dataManager.set(placed, true);

            if (!checkHouse())
            {
                dataManager.set(deathTimer, 200);
            }
        }
        else if (dataManager.get(deathTimer) > 0)
        {
            dataManager.set(deathTimer, dataManager.get(deathTimer) - 1);

            if (dataManager.get(deathTimer) == 0)
            {
                setDead();
            }
        }
    }

    @Override
    public boolean canDespawn()
    {
        return (getHealth() < 1.0f);
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (rand.nextInt(5) == 0)
        {
            return CreepsSoundHandler.schlumpSound;
        }

        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.schlumpHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.schlumpDeathSound;
    }

    @Override
    public void setDead()
    {
        super.setDead();

        SoundEvent deathSound = getDeathSound();

        if (deathSound != null)
        {
            playSound(deathSound, getSoundVolume(), getSoundPitch());
        }

        smoke();
    }

    private boolean checkItems()
    {
        int i = 0;

        for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(6.0d, 6.0d, 6.0d)))
        {
            if (entity instanceof EntityItem)
            {
                i++;
            }
        }

        return (i > 25);
    }

    @Override
    public void smoke()
    {
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;

                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0f)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0f)) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0f)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0f)) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0f)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0f) + (double)i) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0f)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0f)) - (double)i - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0f)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0f) + (double)i) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0f)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0f)) - (double)i - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0f)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0f) + (double)i) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0f)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0f)) - (double)i - (double)width, d, d1, d2);
            }
        }
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        return (getHealth() <= 0.0f);
    }

    public void setAge(int i)
    {
        dataManager.set(age, i);
    }

    public int getAge()
    {
        return dataManager.get(age);
    }

    public boolean getPlaced()
    {
        return dataManager.get(placed);
    }

    private boolean checkHouse()
    {
        EntityPlayer owner = getOwner();

        for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().expand(16.0d, 16.0d, 16.0d)))
        {
            if (entity instanceof EntitySchlump)
            {
                if (owner != null)
                {
                    owner.sendMessage(new TextComponentString("Too close to another Schlump. SCHLUMP OVERLOAD!"));
                }

                playSound(CreepsSoundHandler.schlumpOverloadSound, getSoundVolume(), getSoundPitch());

                return false;
            }
        }

        BlockPos blockPos = new BlockPos(MathHelper.floor(posX), MathHelper.floor(getEntityBoundingBox().minY), MathHelper.floor(posZ));

        if (world.canBlockSeeSky(blockPos))
        {
            if (owner != null)
            {
                owner.sendMessage(new TextComponentString("Your Schlump needs to be indoors or it will die!"));
            }

            playSound(CreepsSoundHandler.schlumpIndoorsSound, getSoundVolume(), getSoundPitch());

            return false;
        }
        else if (world.getBlockLightOpacity(blockPos) > 11)
        {
            if (owner != null)
            {
                owner.sendMessage(new TextComponentString("It is too bright in here for your little Schlump!"));
            }

            playSound(CreepsSoundHandler.schlumpBrightSound, getSoundVolume(), getSoundPitch());

            return false;
        }

        int l = 0;

        for (int i = -2; i < 2; i++)
        {
            for (int k = -2; k < 2; k++)
            {
                for (int q = 0; q < 5; q++)
                {
                    if (world.isAirBlock(new BlockPos(posX + i, posY + q, posZ + k)))
                    {
                        l++;
                    }
                }
            }
        }

        if (l < 60)
        {
            if (owner != null)
            {
                owner.sendMessage(new TextComponentString("Your Schlump doesn't have enough room to grow!"));
            }

            playSound(CreepsSoundHandler.schlumpRoomSound, getSoundVolume(), getSoundPitch());

            return false;
        }

        int j1 = 0;

        for (int i = -5; i < 5; i++)
        {
            for (int k = -5; k < 5; k++)
            {
                for (int q = -5; q < 5; q++)
                {
                    Block block = world.getBlockState(new BlockPos(posX + i, posY + q, posZ + k)).getBlock();

                    if (block == Blocks.OAK_DOOR)
                    {
                        j1 += 10;
                    }
                    else if (block == Blocks.IRON_DOOR)
                    {
                        j1 += 20;
                    }
                    else if (block == Blocks.GLASS)
                    {
                        j1 += 5;
                    }
                    else if (block == Blocks.CHEST)
                    {
                        j1 += 15;
                    }
                    else if (block == Blocks.BED)
                    {
                        j1 += 20;
                    }
                    else if (block == Blocks.BOOKSHELF)
                    {
                        j1 += 15;
                    }
                    else if (block == Blocks.BRICK_BLOCK)
                    {
                        j1 += 3;
                    }
                    else if (block == Blocks.PLANKS)
                    {
                        j1 += 3;
                    }
                    else if (block == Blocks.WOOL)
                    {
                        j1 += 2;
                    }
                    else if (block == Blocks.CAKE)
                    {
                        j1 += 10;
                    }
                    else if (block == Blocks.FURNACE)
                    {
                        j1 += 15;
                    }
                    else if (block == Blocks.LIT_FURNACE)
                    {
                        j1 += 10;
                    }
                    else if (block == Blocks.RED_FLOWER)
                    {
                        j1 += 5;
                    }
                    else if (block == Blocks.CRAFTING_TABLE)
                    {
                        j1 += 10;
                    }
                }
            }
        }

        if (j1 > 275)
        {
            if (getAge() < 10)
            {
                if (owner != null)
                {
                    owner.sendMessage(new TextComponentString("This location is great! Your Schlump will love it here!"));
                }

                playSound(CreepsSoundHandler.schlumpOkSound, getSoundVolume(), getSoundPitch());
            }

            return true;
        }

        if (owner != null)
        {
            owner.sendMessage(new TextComponentString("This is not a good location for your Schlump. It will die here!"));
        }

        playSound(CreepsSoundHandler.schlumpSucksSound, getSoundVolume(), getSoundPitch());

        return false;
    }

    private void giveReward()
    {
        EntityPlayer owner = getOwner();

        if (!checkHouse())
        {
            if (owner != null)
            {
                owner.sendMessage(new TextComponentString("This is not a good location for your Schlump. It will die here!"));
            }

            playSound(CreepsSoundHandler.schlumpSucksSound, getSoundVolume(), getSoundPitch());

            dataManager.set(deathTimer, 200);

            return;
        }
        else if (checkItems())
        {
            return;
        }

        playSound(CreepsSoundHandler.schlumpRewardSound, getSoundVolume(), getSoundPitch());

        if (owner != null && !world.isRemote)
        {
            EntityItem entityItem;

            int i = rand.nextInt(getAge() / 100) + 1;

            if (i > 42)
            {
                i = 42;
            }

            switch (i)
            {
                case 1:
                case 18:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.lolly, rand.nextInt(2) + 1), 1.0f);

                    break;
                case 2:
                case 3:
                case 4:
                case 5:
                    entityItem = entityDropItem(new ItemStack(Items.WHEAT, 1), 1.0f);

                    break;
                case 6:
                case 7:
                case 8:
                case 9:
                case 10:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.bandaid, 1), 1.0f);

                    break;
                case 11:
                case 12:
                case 13:
                case 14:
                    entityItem = entityDropItem(new ItemStack(Items.BREAD, 1), 1.0f);

                    break;
                case 15:
                case 16:
                case 17:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.money, rand.nextInt(4) + 1), 1.0f);

                    break;
                case 19:
                case 20:
                    entityItem = entityDropItem(new ItemStack(Items.APPLE, 1), 1.0f);

                    break;
                case 21:
                case 25:
                case 26:
                    entityItem = entityDropItem(new ItemStack(Items.PORKCHOP, 1), 1.0f);

                    break;
                case 22:
                case 23:
                    entityItem = entityDropItem(new ItemStack(Items.COAL, 1), 1.0f);

                    break;
                case 24:
                    entityItem = entityDropItem(new ItemStack(Items.MELON_SEEDS, 1), 1.0f);

                    break;
                case 27:
                    entityItem = entityDropItem(new ItemStack(Items.IRON_INGOT, 1), 1.0f);

                    break;
                case 28:
                    entityItem = entityDropItem(new ItemStack(Items.FISH, 1), 1.0f);

                    break;
                case 29:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.evilEgg, rand.nextInt(5) + 1), 1.0f);

                    break;
                case 30:
                    entityItem = entityDropItem(new ItemStack(Items.COOKED_FISH, 1), 1.0f);

                    break;
                case 31:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.gun, 1), 1.0f);

                    break;
                case 32:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.extinguisher, rand.nextInt(2) + 1), 1.0f);

                    break;
                case 33:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.rocket, 1), 1.0f);

                    break;
                case 34:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.atom, rand.nextInt(7) + 1), 1.0f);

                    break;
                case 35:
                case 37:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.armyGem, 1), 1.0f);

                    break;
                case 36:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.money, rand.nextInt(24) + 1), 1.0f);

                    break;
                case 38:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.horseHeadGem, 1), 1.0f);

                    break;
                case 39:
                    entityItem = entityDropItem(new ItemStack(Items.GOLD_INGOT, 1), 1.0f);

                    break;
                case 40:
                    entityItem = entityDropItem(new ItemStack(Items.DIAMOND, 1), 1.0f);

                    break;
                case 41:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.raygun, 1), 1.0f);

                    break;
                case 42:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.money, rand.nextInt(49) + 1), 1.0f);

                    break;
                default:
                    entityItem = entityDropItem(new ItemStack(CreepsItemHandler.money, rand.nextInt(3) + 1), 1.0f);

                    break;
            }

            if (entityItem != null)
            {
                double d = -MathHelper.sin((rotationYaw * (float)Math.PI) / 180.0f);

                double d1 = MathHelper.cos((rotationYaw * (float)Math.PI) / 180.0f);

                entityItem.posX = owner.posX + d * 0.5d;

                entityItem.posY = owner.posZ + d1 * 0.5d;

                entityItem.motionX += (rand.nextFloat() - rand.nextFloat()) * 0.15f;

                entityItem.motionZ += (rand.nextFloat() - rand.nextFloat()) * 0.15f;
            }
        }
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (hand == EnumHand.OFF_HAND)
        {
            return super.processInteract(player, hand);
        }

        ItemStack itemStack = player.getHeldItem(hand);

        if (itemStack.getItem() == CreepsItemHandler.babyJarEmpty)
        {
            if (getModelSize() > 0.5f)
            {
                player.sendMessage(new TextComponentString("That Schlump is too big to fit in a jar!"));

                playSound(CreepsSoundHandler.schlumpBigSound, getSoundVolume(), getSoundPitch());

                return true;
            }

            setDead();

            player.inventory.setInventorySlotContents(player.inventory.currentItem, new ItemStack(CreepsItemHandler.babyJarFull));

            return true;
        }

        return super.processInteract(player, hand);
    }

    @Override
    public void onDeath(@Nonnull DamageSource cause)
    {
        super.onDeath(cause);

        giveReward();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsSchlump");

        props.setInteger("Age", getAge());

        props.setInteger("DeathTimer", dataManager.get(deathTimer));

        compound.setTag("MoreCreepsSchlump", props);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsSchlump");

        if (props.hasKey("Age"))
        {
            setAge(props.getInteger("Age"));
        }

        if (props.hasKey("DeathTimer"))
        {
            dataManager.set(deathTimer, props.getInteger("DeathTimer"));
        }
    }
}

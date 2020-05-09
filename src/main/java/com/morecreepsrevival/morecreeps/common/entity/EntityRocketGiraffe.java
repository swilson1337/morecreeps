package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.networking.CreepsPacketHandler;
import com.morecreepsrevival.morecreeps.common.networking.message.MessageOpenGuiTamableEntityName;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class EntityRocketGiraffe extends EntityCreepBase
{
    private static final String[] names = {
            "Rory", "Stan", "Clarence", "FirePower", "Lightning", "Rocket Jockey", "Rocket Ralph", "Tim"
    };

    private static final DataParameter<Integer> tamedCookies = EntityDataManager.createKey(EntityRocketGiraffe.class, DataSerializers.VARINT);

    private int gallopTime = 0;

    public EntityRocketGiraffe(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Rocket Giraffe");

        setSize(1.5f, 4.0f);

        baseSpeed = 0.325d;

        baseHealth = (float)rand.nextInt(50) + 30.0f;

        updateAttributes();
    }

    @Override
    protected String[] getTamedNames()
    {
        return names;
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(tamedCookies, rand.nextInt(7) + 1);
    }

    @Override
    protected void updateTexture()
    {
        if (isTamed())
        {
            setTexture("textures/entity/rocketgiraffetamed.png");
        }
        else
        {
            setTexture("textures/entity/rocketgiraffe.png");
        }
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
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (rand.nextInt(10) == 0)
        {
            return CreepsSoundHandler.giraffeSound;
        }

        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.giraffeHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.giraffeDeadSound;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    public void onLivingUpdate()
    {
        ignoreFrustumCheck = true;

        super.onLivingUpdate();
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        if (getFirstPassenger() != null)
        {
            return false;
        }

        return super.isEntityInsideOpaqueBlock();
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(10) == 0)
        {
            dropItem(CreepsItemHandler.rocket, rand.nextInt(5) + 1);
        }
    }

    @Override
    public void onDeath(@Nonnull DamageSource damageSource)
    {
        smoke();

        super.onDeath(damageSource);
    }

    @Override
    public boolean isTamable()
    {
        return true;
    }

    @Override
    public boolean isRideable()
    {
        return true;
    }

    @Override
    public void updatePassenger(@Nonnull Entity passenger)
    {
        if (isPassenger(passenger) && passenger instanceof EntityPlayer)
        {
            double d = Math.cos(((double)rotationYaw * Math.PI) / 180D) * 0.20000000000000001D;
            double d1 = Math.sin(((double)rotationYaw * Math.PI) / 180D) * 0.20000000000000001D;

            float size = getModelSize();

            float f = 1.8F - (1.0F - size) * 2.0F;

            if (size > 1.0f)
            {
                f *= 1.1f;
            }

            passenger.setPosition(posX + d, posY + (double)f, posZ + d1);

            return;
        }

        super.updatePassenger(passenger);
    }

    @Override
    public void travel(float strafe, float vertical, float forward)
    {
        if (isBeingRidden() && canBeSteered())
        {
            Entity controllingPassenger = getControllingPassenger();

            if (!(controllingPassenger instanceof EntityLivingBase))
            {
                return;
            }

            EntityLivingBase riddenByEntity = (EntityLivingBase)controllingPassenger;

            baseSpeed = 1.95d;

            updateMoveSpeed();

            riddenByEntity.lastTickPosY = 0.0d;

            prevRotationYaw = rotationYaw = riddenByEntity.rotationYaw;

            prevRotationPitch = rotationPitch = 0.0f;

            float f = 1.0f;

            double moveSpeed = riddenByEntity.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue();

            if (moveSpeed > 0.01d && moveSpeed < 10.0d)
            {
                f = (float)moveSpeed;
            }

            forward = (riddenByEntity.moveForward / f) * (float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue() * 1.95f;

            strafe = (riddenByEntity.moveStrafing / f) * (float)getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).getBaseValue() * 1.95f;

            if (onGround && (forward != 0.0f || strafe != 0.0f))
            {
                motionY += 0.16100040078163147d;
            }

            if (forward == 0.0f && strafe == 0.0f)
            {
                isJumping = false;

                gallopTime = 0;
            }

            if (forward != 0.0f && gallopTime++ > 10)
            {
                gallopTime = 0;

                if (handleWaterMovement())
                {
                    playSound(CreepsSoundHandler.giraffeSplashSound, getSoundVolume(), 1.2f);
                }
                else
                {
                    playSound(CreepsSoundHandler.giraffeGallopSound, getSoundVolume(), 1.2f);
                }
            }

            if (onGround && !isJumping)
            {
                if (riddenByEntity instanceof EntityPlayer && MoreCreepsAndWeirdos.proxy.isJumpKeyDown((EntityPlayer)riddenByEntity))
                {
                    isJumping = true;
                }

                if (isJumping)
                {
                    motionY += 0.37000000476837158d;
                }
            }

            if (onGround && isJumping)
            {
                double d = Math.abs(Math.sqrt(motionX * motionX + motionZ * motionZ));

                if (d > 0.13d)
                {
                    double d2 = 0.13d / d;

                    motionX = motionX * d2;

                    motionZ = motionZ * d2;
                }

                motionX *= 2.95d;
                motionZ *= 2.95d;
            }
        }
        else
        {
            baseSpeed = 0.2d;

            updateMoveSpeed();
        }

        super.travel(strafe, vertical, forward);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (hand == EnumHand.OFF_HAND)
        {
            return super.processInteract(player, hand);
        }
        else if (isTamed())
        {
            if (isPlayerOwner(player))
            {
                if (isBeingRidden())
                {
                    ItemStack itemStack = player.getHeldItem(hand);

                    if (!itemStack.isEmpty() && itemStack.getItem() == CreepsItemHandler.rocket)
                    {
                        itemStack.shrink(1);

                        playSound(CreepsSoundHandler.rocketFireSound, 1.0f, getSoundPitch());

                        EntityRocket rocket = new EntityRocket(world, player, 0.0f);

                        if (!world.isRemote)
                        {
                            world.spawnEntity(rocket);
                        }

                        return true;
                    }
                }
                else if (player.isSneaking())
                {
                    if (!world.isRemote)
                    {
                        CreepsPacketHandler.INSTANCE.sendTo(new MessageOpenGuiTamableEntityName(getEntityId()), (EntityPlayerMP)player);
                    }

                    return true;
                }
            }
        }
        else if (!isBeingRidden())
        {
            ItemStack itemStack = player.getHeldItem(hand);

            if (!itemStack.isEmpty())
            {
                Item item = itemStack.getItem();

                if (item == Items.COOKIE)
                {
                    playSound(CreepsSoundHandler.giraffeChewSound, getSoundVolume(), getSoundPitch());

                    itemStack.shrink(1);

                    setHealthBoost(getHealthBoost() + 10);

                    updateHealth();

                    addHealth(10);

                    int cookieCount = getTamedCookies();

                    cookieCount--;

                    setTamedCookies(cookieCount);

                    if (cookieCount > 0)
                    {
                        if (!world.isRemote)
                        {
                            player.sendMessage(new TextComponentString("You need \2476" + cookieCount + " cookie" + ((cookieCount == 1) ? "" : "s") + " \247fto tame this Rocket Giraffe."));
                        }
                    }
                    else
                    {
                        tame(player);
                    }

                    smoke();

                    return true;
                }
            }
            else if (!world.isRemote)
            {
                int cookieCount = getTamedCookies();

                player.sendMessage(new TextComponentString("You need \2476" + cookieCount + " cookie" + ((cookieCount == 1) ? "" : "s") + " \247fto tame this Rocket Giraffe."));
            }
        }

        return super.processInteract(player, hand);
    }

    @Override
    public boolean canPlayerRide(EntityPlayer player)
    {
        if (isPlayerOwner(player) && getModelSize() < 1.0f)
        {
            if (!world.isRemote)
            {
                player.sendMessage(new TextComponentString("Your Rocket Giraffe is too small to ride!"));
            }

            return false;
        }

        return super.canPlayerRide(player);
    }

    protected void setTamedCookies(int i)
    {
        dataManager.set(tamedCookies, i);
    }

    public int getTamedCookies()
    {
        return dataManager.get(tamedCookies);
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsRocketGiraffe");

        props.setInteger("TamedCookies", getTamedCookies());

        compound.setTag("MoreCreepsRocketGiraffe", props);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsRocketGiraffe");

        if (props.hasKey("TamedCookies"))
        {
            setTamedCookies(props.getInteger("TamedCookies"));
        }
    }

    @Override
    protected void doAttackJump(Entity entity)
    {
        rotationYaw = ((float)Math.toDegrees(Math.atan2(entity.posZ - posZ, entity.posX - posX))) - 90.0f;

        double d0 = entity.posX - posX;

        double d1 = entity.posZ - posZ;

        double f = MathHelper.sqrt(d0 * d0 + d1 * d1);

        motionX = (d0 / f) * 0.20000000000000001D * (0.850000011920929D + motionX * 0.20000000298023224D);

        motionZ = (d1 / f) * 0.20000000000000001D * (0.80000001192092896D + motionZ * 0.20000000298023224D);

        motionY = 0.10000000596246449D;

        fallDistance = -25.0f;
    }

    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entity)
    {
        // TODO: camel spit

        return super.attackEntityAsMob(entity);
    }

    @Override
    protected SoundEvent getTamedSound()
    {
        return CreepsSoundHandler.giraffeTamedSound;
    }

    @Override @Nullable
    public Entity getControllingPassenger()
    {
        return getFirstPassenger();
    }
}

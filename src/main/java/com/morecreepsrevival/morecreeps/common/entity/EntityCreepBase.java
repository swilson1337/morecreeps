package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.entity.ai.EntityCreepAIFollowOwner;
import com.morecreepsrevival.morecreeps.common.entity.ai.EntityCreepAIFollowOwnerTarget;
import com.morecreepsrevival.morecreeps.common.entity.ai.EntityCreepAIOwnerHurtByTarget;
import com.morecreepsrevival.morecreeps.common.entity.ai.EntityCreepAIOwnerHurtTarget;
import com.morecreepsrevival.morecreeps.common.helpers.EffectHelper;
import com.morecreepsrevival.morecreeps.common.networking.CreepsPacketHandler;
import com.morecreepsrevival.morecreeps.common.networking.message.MessageDismountEntity;
import com.morecreepsrevival.morecreeps.common.networking.message.MessageOpenGuiTamableEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.*;
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
import net.minecraft.scoreboard.Team;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.Constructor;
import java.util.UUID;

public class EntityCreepBase extends EntityCreature implements IEntityOwnable
{
    private static final DataParameter<String> name = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.STRING);

    private static final DataParameter<Integer> level = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<String> texture = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.STRING);

    private static final DataParameter<Integer> speedBoost = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<String> owner = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.STRING);

    private static final DataParameter<Integer> wanderState = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> experience = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> totalDamage = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<String> creepTypeName = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.STRING);

    private static final DataParameter<Float> modelSize = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.FLOAT);

    private static final DataParameter<Integer> skillHealing = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> skillAttack = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> skillDefend = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> skillSpeed = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> interest = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> healTimer = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> healthBoost = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> criticalHitCooldown = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> armor = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> unmountTimer = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> noDespawn = EntityDataManager.<Boolean>createKey(EntityCreepBase.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Float> hammerSwing = EntityDataManager.createKey(EntityCreepBase.class, DataSerializers.FLOAT);

    protected String baseTexture = "";

    protected float baseHealth = 100.0f;

    protected double baseSpeed = 1.0d;

    protected double baseAttackDamage = 1.0d;

    protected EnumCreatureType creatureType = EnumCreatureType.CREATURE;

    protected boolean spawnOnlyAtNight = false;

    public EntityCreepBase(World worldIn)
    {
        super(worldIn);

        fallDistance = -25.0f;

        experienceValue = 5;

        updateAttributes();
    }

    @Override @Nonnull
    public SoundCategory getSoundCategory()
    {
        if (getCreatureType() == EnumCreatureType.MONSTER)
        {
            return SoundCategory.HOSTILE;
        }

        return SoundCategory.NEUTRAL;
    }

    protected void onDismount(Entity entity)
    {
    }

    @Override
    public void dismountRidingEntity()
    {
        if (!world.isRemote)
        {
            CreepsPacketHandler.INSTANCE.sendToAll(new MessageDismountEntity(getEntityId()));
        }

        fallDistance = -25.0f;

        dataManager.set(unmountTimer, 20);

        SoundEvent unmountSound = getUnmountSound();

        if (unmountSound != null)
        {
            playSound(unmountSound, getSoundVolume(), getSoundPitch());
        }

        Entity entity = getRidingEntity();

        super.dismountRidingEntity();

        onDismount(entity);
    }

    @Override
    public boolean isEntityInvulnerable(@Nonnull DamageSource damageSource)
    {
        if (isRiding())
        {
            return true;
        }

        return super.isEntityInvulnerable(damageSource);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(name, "");

        dataManager.register(level, 1);

        dataManager.register(texture, "");

        dataManager.register(speedBoost, 0);

        dataManager.register(owner, "");

        dataManager.register(wanderState, 0);

        dataManager.register(experience, 0);

        dataManager.register(totalDamage, 0);

        dataManager.register(creepTypeName, "creep");

        dataManager.register(modelSize, 1.0f);

        dataManager.register(skillHealing, 0);

        dataManager.register(skillAttack, 0);

        dataManager.register(skillDefend, 0);

        dataManager.register(skillSpeed, 0);

        dataManager.register(interest, 0);

        dataManager.register(healTimer, 0);

        dataManager.register(healthBoost, 0);

        dataManager.register(criticalHitCooldown, 5);

        dataManager.register(armor, 0);

        dataManager.register(unmountTimer, 0);

        dataManager.register(noDespawn, Boolean.valueOf(false));

        dataManager.register(hammerSwing, 0.0f);
    }

    protected void updateAttributes()
    {
        updateHealth();

        updateMoveSpeed();

        updateTexture();

        updateAttackStrength();

        updateModelSize();
    }

    protected void updateModelSize()
    {
    }

    @Override
    protected void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        nodeProcessor.setCanEnterDoors(true);

        switch (getWanderState())
        {
            case 0:
                tasks.addTask(1, new EntityAISwimming(this));

                tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, true));

                tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

                tasks.addTask(3, new EntityAILookIdle(this));

                if (isTamed())
                {
                    targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
                }

                break;
            case 1:
                tasks.addTask(1, new EntityAISwimming(this));

                tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, true));

                tasks.addTask(3, new EntityAIWanderAvoidWater(this, 1.0d));

                tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

                tasks.addTask(4, new EntityAILookIdle(this));

                if (isTamed())
                {
                    targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
                }

                break;
            case 2:
                tasks.addTask(1, new EntityAISwimming(this));

                tasks.addTask(2, new EntityAIAttackMelee(this, 1.0d, true));

                tasks.addTask(3, new EntityCreepAIFollowOwner(this, 1.0d, 6.0f, 2.0f));

                tasks.addTask(4, new EntityAIWanderAvoidWater(this, 1.0d));

                tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0f));

                tasks.addTask(5, new EntityAILookIdle(this));

                if (isTamed())
                {
                    targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));

                    targetTasks.addTask(2, new EntityCreepAIOwnerHurtByTarget(this));

                    targetTasks.addTask(3, new EntityCreepAIOwnerHurtTarget(this));

                    targetTasks.addTask(4, new EntityCreepAIFollowOwnerTarget(this));
                }

                break;
            default:
                break;
        }
    }

    protected void clearAITasks()
    {
        tasks.taskEntries.clear();

        targetTasks.taskEntries.clear();
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();

        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);

        updateAttributes();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsEntity");

        props.setFloat("ModelSize", getModelSize());

        props.setString("Name", getCreepName());

        props.setString("BaseTexture", baseTexture);

        props.setInteger("HealthBoost", dataManager.get(healthBoost));

        props.setInteger("Level", getLevel());

        props.setInteger("SpeedBoost", getSpeedBoost());

        props.setInteger("Interest", dataManager.get(interest));

        props.setInteger("TotalDamage", getTotalDamage());

        props.setInteger("Experience", getExperience());

        props.setInteger("WanderState", getWanderState());

        props.setInteger("Armor", dataManager.get(armor));

        props.setInteger("SkillHealing", getSkillHealing());

        props.setInteger("SkillAttack", getSkillAttack());

        props.setInteger("SkillDefend", getSkillDefend());

        props.setInteger("SkillSpeed", getSkillSpeed());

        UUID owner = getOwnerId();

        if (owner != null)
        {
            props.setString("Owner", owner.toString());
        }

        props.setString("CreepTypeName", getCreepTypeName());

        compound.setTag("MoreCreepsEntity", props);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        NBTTagCompound props = compound.getCompoundTag("MoreCreepsEntity");

        if (props.hasKey("ModelSize"))
        {
            setModelSize(props.getFloat("ModelSize"));
        }

        if (props.hasKey("Name"))
        {
            setCreepName(props.getString("Name"));
        }

        if (props.hasKey("HealthBoost"))
        {
            setHealthBoost(props.getInteger("HealthBoost"));
        }

        if (props.hasKey("BaseTexture"))
        {
            baseTexture = props.getString("BaseTexture");
        }
        else
        {
            String[] availableTextures = getAvailableTextures();

            if (availableTextures.length > 0)
            {
                baseTexture = availableTextures[rand.nextInt(availableTextures.length)];
            }
        }

        if (props.hasKey("Level"))
        {
            setLevel(props.getInteger("Level"));
        }

        if (props.hasKey("SpeedBoost"))
        {
            setSpeedBoost(props.getInteger("SpeedBoost"));
        }

        if (props.hasKey("Interest"))
        {
            setInterest(props.getInteger("Interest"));
        }

        if (props.hasKey("TotalDamage"))
        {
            setTotalDamage(props.getInteger("TotalDamage"));
        }

        if (props.hasKey("Experience"))
        {
            setExperience(props.getInteger("Experience"));
        }

        if (props.hasKey("WanderState"))
        {
            setWanderState(props.getInteger("WanderState"));
        }

        if (props.hasKey("Armor"))
        {
            setArmor(props.getInteger("Armor"));
        }

        if (props.hasKey("SkillHealing"))
        {
            setSkillHealing(props.getInteger("SkillHealing"));
        }

        if (props.hasKey("SkillAttack"))
        {
            setSkillAttack(props.getInteger("SkillAttack"));
        }

        if (props.hasKey("SkillDefend"))
        {
            setSkillDefend(props.getInteger("SkillDefend"));
        }

        if (props.hasKey("SkillSpeed"))
        {
            setSkillSpeed(props.getInteger("SkillSpeed"));
        }

        if (props.hasKey("Owner"))
        {
            setOwner(UUID.fromString(props.getString("Owner")));
        }

        if (props.hasKey("CreepTypeName"))
        {
            setCreepTypeName(props.getString("CreepTypeName"));
        }

        updateAttributes();
    }

    public void determineBaseTexture()
    {
        if (!baseTexture.isEmpty())
        {
            return;
        }

        String[] availableTextures = getAvailableTextures();

        if (availableTextures.length > 0)
        {
            baseTexture = availableTextures[rand.nextInt(availableTextures.length)];
        }

        updateTexture();
    }

    public void setInitialHealth()
    {
        setHealth(getMaxHealth());
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingData)
    {
        super.onInitialSpawn(difficulty, livingData);

        determineBaseTexture();

        setInitialHealth();

        return livingData;
    }

    protected double getMoveSpeed()
    {
        return baseSpeed;
    }

    protected double getAttackDamage()
    {
        return baseAttackDamage;
    }

    protected void updateMoveSpeed()
    {
        double speed = getMoveSpeed() + (getLevelSpeedMultiplier() * (getLevel() - 1));

        if (getSpeedBoost() > 0)
        {
            speed += 0.75d;
        }

        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(speed);
    }

    @Override
    public boolean isCreatureType(@Nullable EnumCreatureType type, boolean forSpawnCount)
    {
        if (forSpawnCount && isNoDespawnRequired())
        {
            return false;
        }

        return (getCreatureType() == type);
    }

    public EnumCreatureType getCreatureType()
    {
        return creatureType;
    }

    protected static float getArmorHealthBonus(int armorLevel)
    {
        switch (armorLevel)
        {
            case 1:
                return 5.0f;
            case 2:
                return 15.0f;
            case 3:
                return 9.0f;
            case 4:
                return 22.0f;
            default:
                break;
        }

        return 0.0f;
    }

    protected float getBaseHealth()
    {
        return baseHealth;
    }

    protected void updateHealth()
    {
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(getBaseHealth() + (getLevelHealthMultiplier() * (getLevel() - 1)) + getHealthBoost() + getArmorHealthBonus(getArmor()));
    }

    protected void updateAttackStrength()
    {
        double attackDamage = getAttackDamage() + (getLevelDamageMultiplier() * (getLevel() - 1));

        switch (getArmor())
        {
            case 1:
                attackDamage++;

                break;
            case 2:
                attackDamage += 3;

                break;
            case 3:
                attackDamage += 2;

                break;
            case 4:
                attackDamage += 6;

                break;
            default:
                break;
        }

        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(attackDamage);
    }

    public void addHealth(float amt)
    {
        setHealth(Math.max(0, Math.min(getMaxHealth(), getHealth() + amt)));
    }

    protected void setInterest(int i)
    {
        dataManager.set(interest, Math.max(0, Math.min(100, i)));
    }

    public int getInterest()
    {
        return dataManager.get(interest);
    }

    protected void addInterest(int i, EntityPlayer player)
    {
        if (!isTamable() || isTamed())
        {
            return;
        }

        setInterest(getInterest() + i);

        if (getInterest() >= 100)
        {
            tame(player);
        }
    }

    public void feed(EntityPlayer player, float healthToAdd, int interestToAdd)
    {
        addHealth(healthToAdd);

        addInterest(interestToAdd, player);

        SoundEvent fullSound = getFullSound();

        SoundEvent eatSound;

        if (getHealth() >= getMaxHealth() && fullSound != null)
        {
            eatSound = fullSound;
        }
        else
        {
            eatSound = getEatSound();
        }

        if (eatSound != null)
        {
            playSound(eatSound, getSoundVolume(), getSoundPitch());
        }
    }

    protected void updateTexture()
    {
        if (baseTexture == null || baseTexture.length() < 1)
        {
            return;
        }

        StringBuilder builder = (new StringBuilder()).append(baseTexture);

        switch (getArmor())
        {
            case 1:
                builder.append("l");

                break;
            case 2:
                builder.append("g");

                break;
            case 3:
                builder.append("i");

                break;
            case 4:
                builder.append("d");

                break;
            default:
                break;
        }

        builder.append(".png");

        setTexture(builder.toString());
    }

    public void setWanderState(int i)
    {
        dataManager.set(wanderState, i);

        initEntityAI();
    }

    public int getWanderState()
    {
        return dataManager.get(wanderState);
    }

    protected void setArmor(int i)
    {
        dataManager.set(armor, i);

        updateHealth();

        updateAttackStrength();

        updateTexture();
    }

    public int getArmor()
    {
        return dataManager.get(armor);
    }

    protected double getRidingYOffset()
    {
        return 0.5;
    }

    @Override
    public double getYOffset()
    {
        Entity entity = getRidingEntity();

        if (entity != null)
        {
            return (getRidingYOffset() * (entity.getPassengers().indexOf(this) + 1));
        }

        return 0.0d;
    }

    protected boolean shouldJumpWhileAttacking(Entity entity)
    {
        return false;
    }

    protected void doAttackJump(Entity entity)
    {
        rotationYaw = ((float)Math.toDegrees(Math.atan2(entity.posZ - posZ, entity.posX - posX))) - 90.0f;

        double d0 = entity.posX - posX;

        double d1 = entity.posZ - posZ;

        double f = MathHelper.sqrt(d0 * d0 + d1 * d1);

        motionX = (d0 / f) * 0.5d * 0.800000011920929d + motionX * 0.20000000298023224d;

        motionZ = (d1 / f) * 0.5d * 0.800000011920929d + motionZ * 0.20000000298023224d;

        motionY = 0.40000000596046448f;

        fallDistance = -25.0f;
    }

    @Override
    public boolean attackEntityAsMob(@Nonnull Entity entity)
    {
        if (onGround && shouldJumpWhileAttacking(entity))
        {
            doAttackJump(entity);
        }

        if (rand.nextInt(5) == 0)
        {
            SoundEvent angrySound = getAngrySound();

            if (angrySound != null)
            {
                playSound(angrySound, getSoundVolume(), getSoundPitch());
            }
        }

        float f = (float)getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getAttributeValue();

        boolean flag = entity.attackEntityFrom(DamageSource.causeMobDamage(this), f);

        if (flag)
        {
            int j = EnchantmentHelper.getFireAspectModifier(this);

            if (j > 0)
            {
                entity.setFire(j * 4);
            }

            if (entity instanceof EntityPlayer)
            {
                EntityPlayer player = (EntityPlayer)entity;

                ItemStack itemStack = getHeldItemMainhand();

                ItemStack itemStack2 = (player.isHandActive() ? player.getActiveItemStack() : ItemStack.EMPTY);

                if (!itemStack.isEmpty() && !itemStack2.isEmpty() && itemStack.getItem().canDisableShield(itemStack, itemStack2, player, this) && itemStack2.getItem().isShield(itemStack2, player))
                {
                    float f1 = 0.25f + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05f;

                    if (rand.nextFloat() < f1)
                    {
                        player.getCooldownTracker().setCooldown(itemStack2.getItem(), 100);

                        world.setEntityState(player, (byte)30);
                    }
                }
            }

            applyEnchantments(this, entity);

            if (isTamed() && canLevelUp())
            {
                int iSkillAttack = getSkillAttack();

                addTotalDamage((int)(f * (1.85d + iSkillAttack)));

                double hitChance = 1.0d + (getLevel() * 5) + (iSkillAttack * 4);

                if (hitChance < 5.0d)
                {
                    hitChance = 5.0d;
                }

                if ((double)rand.nextInt(100) > (100.0d - hitChance))
                {
                    /*if (MoreCreepsConfig.blood && !world.isRemote)
                    {
                        CreepsPacketHandler.INSTANCE.sendToAllTracking(new MessageSendBloodEffect(entity.getEntityId()), entity);
                    }*/

                    float damageDealt = f * 0.75f;

                    if (damageDealt < 1.0f)
                    {
                        damageDealt = 1.0f;
                    }

                    if (dataManager.get(criticalHitCooldown) > 0)
                    {
                        dataManager.set(criticalHitCooldown, dataManager.get(criticalHitCooldown) - 1);
                    }

                    if (iSkillAttack > 1 && rand.nextInt(100) > (100 - (iSkillAttack * 2)) && dataManager.get(criticalHitCooldown) < 1)
                    {
                        float hp = ((EntityLivingBase)entity).getHealth();

                        if (damageDealt < hp)
                        {
                            damageDealt = hp;
                        }

                        dataManager.set(criticalHitCooldown, 50 - (iSkillAttack * 8));

                        SoundEvent criticalHitSound = getCriticalHitSound();

                        if (criticalHitSound != null)
                        {
                            entity.playSound(criticalHitSound, 1.0f, 1.0f);
                        }

                        addTotalDamage(25);
                    }

                    if ((((EntityLivingBase)entity).getHealth() - damageDealt) <= 0.0f)
                    {
                        SoundEvent killSound = getKillSound();

                        if (killSound != null)
                        {
                            playSound(killSound, getSoundVolume(), getSoundPitch());
                        }
                    }

                    addTotalDamage((int)(damageDealt * (1.85d + iSkillAttack)));

                    return entity.attackEntityFrom(DamageSource.causeThrownDamage(this, entity), damageDealt);
                }
            }
        }

        return flag;
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount)
    {
        if (isEntityInvulnerable(source))
        {
            return false;
        }

        return super.attackEntityFrom(source, amount);
    }

    public boolean canMount(Entity entity)
    {
        return true;
    }

    @Override
    public boolean startRiding(@Nonnull Entity entity, boolean force)
    {
        if (!force && !canMount(entity))
        {
            return false;
        }

        boolean flag = super.startRiding(entity, force);

        if (flag)
        {
            rotationYaw = entity.rotationYaw;

            SoundEvent mountSound = getMountSound();

            if (mountSound != null)
            {
                playSound(mountSound, getSoundVolume(), getSoundPitch());
            }

            dataManager.set(unmountTimer, 20);
        }

        return flag;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        if (hand == EnumHand.OFF_HAND)
        {
            return super.processInteract(player, hand);
        }

        ItemStack itemStack = player.getHeldItem(hand);

        if (isEntityAlive())
        {
            if (itemStack.isEmpty())
            {
                if (player.isSneaking() && isTamed() && isPlayerOwner(player) && canUseTamableMenu())
                {
                    if (!world.isRemote)
                    {
                        CreepsPacketHandler.INSTANCE.sendTo(new MessageOpenGuiTamableEntity(getEntityId()), (EntityPlayerMP)player);
                    }

                    return true;
                }
                else if (canRidePlayer() && canRidePlayer(player))
                {
                    if (!player.equals(getRidingEntity()))
                    {
                        startRiding(player, isStackable());
                    }
                    else
                    {
                        dismountRidingEntity();
                    }

                    return true;
                }
                else if (isRideable() && canPlayerRide(player) && !player.equals(getFirstPassenger()) && player.startRiding(this))
                {
                    return true;
                }
            }
            else
            {
                Item item = itemStack.getItem();

                if (isTamed() && isPlayerOwner(player) && canUseTamableMenu() && (item == Items.BOOK || item == Items.PAPER || shouldOpenTamableMenu(item)))
                {
                    if (!world.isRemote)
                    {
                        CreepsPacketHandler.INSTANCE.sendTo(new MessageOpenGuiTamableEntity(getEntityId()), (EntityPlayerMP)player);
                    }

                    return true;
                }
            }
        }

        return super.processInteract(player, hand);
    }

    public void spawnTrophy(Entity entity)
    {
        EffectHelper.spawnTrophy(world, entity);
    }

    public void spawnTrophy()
    {
        spawnTrophy(this);
    }

    public void explode()
    {
        EffectHelper.explode(world, this);
    }

    public void smoke(boolean plain)
    {
        EffectHelper.smoke(world, this, rand, plain);
    }

    public void smoke()
    {
        smoke(false);
    }

    public void smokePlain()
    {
        smoke(true);
    }

    public void smoke2()
    {
        EffectHelper.smoke2(world, this, rand);
    }

    @Override
    public void onDeath(@Nonnull DamageSource cause)
    {
        if (!dead && !world.isRemote)
        {
            if (isTamed() && canBeRevived())
            {
                if (!(this instanceof EntityTombstone))
                {
                    EntityTombstone tombstone = new EntityTombstone(world, this);

                    tombstone.determineBaseTexture();

                    tombstone.setInitialHealth();

                    world.spawnEntity(tombstone);
                }
            }
            else
            {
                dropItemsOnDeath();
            }
        }

        super.onDeath(cause);
    }

    protected void dropItemsOnDeath()
    {
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        if (isRiding() || dataManager.get(unmountTimer) > 0)
        {
            return false;
        }

        return super.isEntityInsideOpaqueBlock();
    }

    @Override
    public boolean canBreatheUnderwater()
    {
        if (isRiding() || dataManager.get(unmountTimer) > 0)
        {
            return true;
        }

        return false;
    }

    @Override
    public boolean canBeSteered()
    {
        return (isRideable() && getControllingPassenger() instanceof EntityLivingBase);
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        updateArmSwingProgress();

        /*Entity ridingEntity = getRidingEntity();

        if (isInsideOfMaterial(Material.WATER) && ridingEntity != null && ridingEntity.isInsideOfMaterial(Material.WATER) && world.isRemote)
        {
            dismountRidingEntity();

            CreepsPacketHandler.INSTANCE.sendToServer(new MessageDismountEntity(getEntityId()));
        }*/

        if (getBrightness() > 0.5f)
        {
            idleTime += 2;
        }

        if (dataManager.get(unmountTimer) > 0)
        {
            dataManager.set(unmountTimer, dataManager.get(unmountTimer) - 1);
        }

        int iSkillHealing = getSkillHealing();

        if (dataManager.get(healTimer) > 0)
        {
            dataManager.set(healTimer, dataManager.get(healTimer) - 1);
        }

        if (iSkillHealing > 0 && dataManager.get(healTimer) < 1 && getHealth() < getMaxHealth())
        {
            dataManager.set(healTimer, (6 - iSkillHealing) * 200);

            addHealth(iSkillHealing);

            for (int i = 0; i < iSkillHealing; i++)
            {
                world.spawnParticle(EnumParticleTypes.HEART, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + 0.5D + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d, rand.nextGaussian() * 0.02d);
            }

            updateEntityActionState();
        }

        int speedBoost = getSpeedBoost();

        if (speedBoost > 0)
        {
            speedBoost--;

            setSpeedBoost(speedBoost);

            if (speedBoost < 1)
            {
                if (!world.isRemote)
                {
                    EntityPlayer owner = getOwner();

                    if (owner != null)
                    {
                        owner.sendMessage(new TextComponentString("\247b" + getCreepName() + "\2476 has run out of speedboost."));
                    }
                }

                SoundEvent speedDownSound = getSpeedDownSound();

                if (speedDownSound != null)
                {
                    playSound(speedDownSound, getSoundVolume(), getSoundPitch());
                }

                updateMoveSpeed();
            }
        }

        if (shouldBurnInDay() && world.isDaytime() && !world.isRemote && !isChild())
        {
            float f = getBrightness();

            if (f > 0.5f && (rand.nextFloat() * 30) < ((f - 0.4f) * 2.0f) && world.canSeeSky(new BlockPos(posX, posY + (double)getEyeHeight(), posZ)))
            {
                setFire(getBurnInDayTime());
            }
        }
    }

    protected int getBurnInDayTime()
    {
        return 20;
    }

    protected boolean shouldBurnInDay()
    {
        return false;
    }

    protected void giveSpeedBoost(int speedBoost)
    {
        smokePlain();

        setSpeedBoost(Math.max(0, getSpeedBoost()) + speedBoost);

        updateMoveSpeed();

        SoundEvent speedUpSound = getSpeedUpSound();

        if (speedUpSound != null)
        {
            playSound(speedUpSound, getSoundVolume(), getSoundPitch());
        }

        int speedBoostLeft = Math.max((getSpeedBoost() / 21) / 60, 0);

        if (!world.isRemote)
        {
            EntityPlayer owner = getOwner();

            if (owner != null)
            {
                owner.sendMessage(new TextComponentString("\2473" + getCreepName() + "\2476 has\247f " + speedBoostLeft + "\2476 minute" + ((speedBoostLeft > 1 ? "s" : "")) + " of speedboost left."));
            }
        }
    }

    protected SoundEvent getMountSound()
    {
        return null;
    }

    protected SoundEvent getUnmountSound()
    {
        return null;
    }

    protected SoundEvent getEatSound()
    {
        return null;
    }

    protected SoundEvent getFullSound()
    {
        return null;
    }

    protected SoundEvent getSpeedUpSound()
    {
        return null;
    }

    protected SoundEvent getSpeedDownSound()
    {
        return null;
    }

    protected SoundEvent getLevelUpSound()
    {
        return null;
    }

    protected SoundEvent getCriticalHitSound()
    {
        return null;
    }

    protected SoundEvent getAngrySound()
    {
        return null;
    }

    protected String[] getTamedNames()
    {
        return new String[0];
    }

    protected String[] getAvailableTextures()
    {
        return new String[0];
    }

    @Override
    protected boolean canDespawn()
    {
        if (getCreatureType() == EnumCreatureType.MONSTER && !getNoDespawn() && !isTamed())
        {
            return true;
        }

        return false;
    }

    @Override
    public int getTalkInterval()
    {
        return 120;
    }

    public void resetTarget()
    {
        setAttackTarget(null);

        setRevengeTarget(null);
    }

    public void resetModelSize()
    {
        setModelSize(1.0f);
    }

    protected void setModelSize(float f)
    {
        dataManager.set(modelSize, f);
    }

    public float getModelSize()
    {
        return dataManager.get(modelSize);
    }

    public void shrinkModelSize(float f)
    {
        setModelSize(Math.max(0.0f, getModelSize() - f));
    }

    public void decreaseMoveSpeed(float f)
    {
        baseSpeed -= f;

        updateMoveSpeed();
    }

    protected void setSpeedBoost(int i)
    {
        dataManager.set(speedBoost, i);
    }

    public int getSpeedBoost()
    {
        return dataManager.get(speedBoost);
    }

    protected void setTexture(String textureIn)
    {
        dataManager.set(texture, textureIn);
    }

    public String getTexture()
    {
        return dataManager.get(texture);
    }

    public void setCreepName(String s)
    {
        dataManager.set(name, s);
    }

    public String getCreepName()
    {
        return dataManager.get(name);
    }

    public void setOwner(UUID uuid)
    {
        dataManager.set(owner, uuid.toString());
    }

    public void setOwner(EntityPlayer player)
    {
        dataManager.set(owner, player.getUniqueID().toString());
    }

    protected void clearOwner()
    {
        dataManager.set(owner, "");
    }

    @Nullable
    public UUID getOwnerId()
    {
        String uuid = dataManager.get(owner);

        if (uuid.isEmpty())
        {
            return null;
        }

        return UUID.fromString(uuid);
    }

    public boolean isPlayerOwner(EntityPlayer player)
    {
        if (player == null || getOwnerId() == null)
        {
            return false;
        }

        return player.getUniqueID().equals(getOwnerId());
    }

    @Nullable
    public EntityPlayer getOwner()
    {
        UUID owner = getOwnerId();

        if (owner != null)
        {
            return world.getPlayerEntityByUUID(owner);
        }

        return null;
    }

    protected void setLevel(int i)
    {
        dataManager.set(level, i);
    }

    public int getLevel()
    {
        return dataManager.get(level);
    }

    public void tame(EntityPlayer player)
    {
        setInterest(0);

        setOwner(player);

        //spawnTrophy(player);

        // TODO: only spawn trophy when the player gets an achievement

        boolean emptyName = true;

        if (getCreepName().length() < 1)
        {
            String[] names = getTamedNames();

            if (names.length > 0)
            {
                setCreepName(names[rand.nextInt(names.length)]);

                emptyName = false;
            }
            else
            {
                setCreepName("");
            }
        }

        updateAttributes();

        setHealth(getMaxHealth());

        setWanderState(2);

        SoundEvent tamedSound = getTamedSound();

        if (tamedSound != null)
        {
            playSound(tamedSound, getSoundVolume(), getSoundPitch());
        }

        if (!world.isRemote)
        {
            if (emptyName)
            {
                player.sendMessage(new TextComponentString("You have successfully tamed: \2476" + getCreepTypeName()));
            }
            else
            {
                player.sendMessage(new TextComponentString("\2476" + getCreepName() + " \247fhas been tamed!"));
            }
        }
    }

    public boolean isTamed()
    {
        return (getOwnerId() != null);
    }

    public boolean isTamable()
    {
        return false;
    }

    public boolean canRidePlayer()
    {
        return false;
    }

    public boolean canRidePlayer(EntityPlayer player)
    {
        return (!isTamable() || (isTamed() && isPlayerOwner(player)));
    }

    public boolean isRideable()
    {
        return false;
    }

    public boolean canPlayerRide(EntityPlayer player)
    {
        return (!isTamable() || (isTamed() && isPlayerOwner(player)));
    }

    protected boolean shouldOpenTamableMenu(Item item)
    {
        return false;
    }

    protected boolean canUseTamableMenu()
    {
        return false;
    }

    public String getLevelName()
    {
        return "";
    }

    public int getLevelDamage()
    {
        return 0;
    }

    public int getMaxLevel()
    {
        return 1;
    }

    protected void setExperience(int i)
    {
        dataManager.set(experience, i);
    }

    protected void addExperience(int i)
    {
        setExperience(getExperience() + i);
    }

    public int getExperience()
    {
        return dataManager.get(experience);
    }

    protected void setTotalDamage(int i)
    {
        dataManager.set(totalDamage, i);
    }

    public void addTotalDamage(int i)
    {
        addExperience(i);

        i += getTotalDamage();

        if (i >= getLevelDamage() && getLevel() < getMaxLevel())
        {
            int lvl = getLevel() + 1;

            setLevel(lvl);

            setTotalDamage(0);

            int healthBoostNew = rand.nextInt(4);

            setHealthBoost(healthBoostNew);

            updateAttributes();

            addHealth(healthBoostNew + getLevelHealthMultiplier());

            if (!world.isRemote)
            {
                EntityPlayer player = getOwner();

                if (player != null)
                {
                    player.sendMessage(new TextComponentString("\247b" + getCreepName() + " \247fincreased to level \2476" + lvl + "!"));
                }
            }

            SoundEvent levelUpSound = getLevelUpSound();

            if (levelUpSound != null)
            {
                playSound(levelUpSound, getSoundVolume(), getSoundPitch());
            }

            return;
        }

        setTotalDamage(i);
    }

    public int getTotalDamage()
    {
        return dataManager.get(totalDamage);
    }

    @Override
    public boolean canRiderInteract()
    {
        return true;
    }

    protected float getLevelHealthMultiplier()
    {
        return 1.0f;
    }

    protected double getLevelDamageMultiplier()
    {
        return 1.0d;
    }

    protected double getLevelSpeedMultiplier()
    {
        return 0.0d;
    }

    public boolean shouldAttackEntity(EntityLivingBase target)
    {
        if (isTamed() && target instanceof EntityCreepBase && ((EntityCreepBase)target).isPlayerOwner(getOwner()))
        {
            return false;
        }

        return true;
    }

    @Override
    public Team getTeam()
    {
        if (isTamed())
        {
            EntityPlayer owner = getOwner();

            if (owner != null)
            {
                return owner.getTeam();
            }
        }

        return super.getTeam();
    }

    @Override
    public boolean isOnSameTeam(Entity entity)
    {
        if (isTamed())
        {
            EntityPlayer owner = getOwner();

            if (owner != null)
            {
                if (owner.equals(entity))
                {
                    return true;
                }

                return owner.isOnSameTeam(entity);
            }
        }

        return super.isOnSameTeam(entity);
    }

    protected void setHealthBoost(int healthBoostIn)
    {
        dataManager.set(healthBoost, healthBoostIn);
    }

    public int getHealthBoost()
    {
        return dataManager.get(healthBoost);
    }

    protected void setSkillAttack(int i)
    {
        dataManager.set(skillAttack, i);
    }

    public int getSkillAttack()
    {
        return dataManager.get(skillAttack);
    }

    protected void setSkillHealing(int i)
    {
        dataManager.set(skillHealing, i);
    }

    public int getSkillHealing()
    {
        return dataManager.get(skillHealing);
    }

    protected void setSkillDefend(int i)
    {
        dataManager.set(skillDefend, i);
    }

    public int getSkillDefend()
    {
        return dataManager.get(skillDefend);
    }

    protected void setSkillSpeed(int i)
    {
        dataManager.set(skillSpeed, i);
    }

    public int getSkillSpeed()
    {
        return dataManager.get(skillSpeed);
    }

    public int getSkillLevel(String skill)
    {
        switch (skill)
        {
            case "attack":
                return getSkillAttack();
            case "defend":
                return getSkillDefend();
            case "healing":
                return getSkillHealing();
            case "speed":
                return getSkillSpeed();
            default:
                break;
        }

        return 0;
    }

    public int getRequiredLevelForSkill(String skill)
    {
        return getSkillLevel(skill) * 5;
    }

    public boolean canLevelSkill(String skill)
    {
        return (getSkillLevel(skill) < 5 && getLevel() >= getRequiredLevelForSkill(skill));
    }

    public void levelUpSkill(String skill)
    {
        if (getSkillLevel(skill) >= 5)
        {
            return;
        }

        switch (skill)
        {
            case "attack":
                setSkillAttack(getSkillAttack() + 1);

                updateAttackStrength();

                break;
            case "defend":
                setSkillDefend(getSkillDefend() + 1);

                break;
            case "healing":
                setSkillHealing(getSkillHealing() + 1);

                break;
            case "speed":
                setSkillSpeed(getSkillSpeed() + 1);

                updateMoveSpeed();

                break;
            default:
                break;
        }
    }

    public boolean isStackable()
    {
        return false;
    }

    protected void setCreepTypeName(String creepTypeNameIn)
    {
        dataManager.set(creepTypeName, creepTypeNameIn);
    }

    public String getCreepTypeName()
    {
        return dataManager.get(creepTypeName);
    }

    protected void setBaseTexture(String baseTextureIn)
    {
        baseTexture = baseTextureIn;
    }

    public String getBaseTexture()
    {
        return baseTexture;
    }

    public void setNoDespawn(boolean b)
    {
        dataManager.set(noDespawn, Boolean.valueOf(b));
    }

    public boolean getNoDespawn()
    {
        return ((Boolean)dataManager.get(noDespawn)).booleanValue();
    }

    @Override
    public boolean getCanSpawnHere()
    {
        switch (getCreatureType())
        {
            case AMBIENT:
                return true;
            case MONSTER:
                if (world.getDifficulty() == EnumDifficulty.PEACEFUL || !isValidLightLevel())
                {
                    return false;
                }

                break;
            default:
                break;
        }

        return super.getCanSpawnHere();
    }

    @Override
    public void onUpdate()
    {
        EntityLivingBase target = getAttackTarget();

        if (target != null && target.equals(getOwner()))
        {
            setAttackTarget(null);
        }

        super.onUpdate();

        if (getHammerSwing() < 0.0f)
        {
            addHammerSwing(0.45f);
        }
        else
        {
            setHammerSwing(0.0f);
        }

        if (getCreatureType() == EnumCreatureType.MONSTER && !world.isRemote && world.getDifficulty() == EnumDifficulty.PEACEFUL)
        {
            setDead();
        }
    }

    @Override
    public float getBlockPathWeight(BlockPos blockPos)
    {
        if (getCreatureType() == EnumCreatureType.MONSTER && spawnOnlyAtNight)
        {
            return (0.5f - world.getLightBrightness(blockPos));
        }

        return (world.getLightBrightness(blockPos) - 0.5f);
    }

    protected boolean isValidLightLevel()
    {
        if (!spawnOnlyAtNight)
        {
            return true;
        }

        BlockPos blockPos = new BlockPos(posX, getEntityBoundingBox().minY, posZ);

        if (world.getLightFor(EnumSkyBlock.SKY, blockPos) > rand.nextInt(32))
        {
            return false;
        }

        int i = world.getLightFromNeighbors(blockPos);

        if (world.isThundering())
        {
            int j = world.getSkylightSubtracted();

            world.setSkylightSubtracted(10);

            i = world.getLightFromNeighbors(blockPos);

            world.setSkylightSubtracted(j);
        }

        return (i <= rand.nextInt(8));
    }

    @Override
    protected boolean canDropLoot()
    {
        return true;
    }

    public void onRevive(NBTTagCompound compound)
    {
    }

    public void onTombstoneCreate(NBTTagCompound compound)
    {
    }

    public void setHammerSwing(float f)
    {
        dataManager.set(hammerSwing, f);
    }

    public float getHammerSwing()
    {
        return dataManager.get(hammerSwing);
    }

    public void addHammerSwing(float f)
    {
        setHammerSwing(getHammerSwing() + f);
    }

    public void takeHammerSwing(float f)
    {
        setHammerSwing(getHammerSwing() - f);
    }

    @Override
    public boolean isLeftHanded()
    {
        return false;
    }

    protected Entity getFirstPassenger()
    {
        for (Entity entity : getPassengers())
        {
            return entity;
        }

        return null;
    }

    protected SoundEvent getKillSound()
    {
        return null;
    }

    protected SoundEvent getMissSound()
    {
        return null;
    }

    protected SoundEvent getTamedSound()
    {
        return null;
    }

    public int getUnmountTimer()
    {
        return dataManager.get(unmountTimer);
    }

    public boolean canLevelUp()
    {
        return false;
    }

    public boolean canBeRevived()
    {
        return false;
    }

    public void cloneEntity()
    {
        if (world.isRemote)
        {
            return;
        }

        try
        {
            Constructor<? extends EntityCreepBase> constructor = getClass().getConstructor(World.class);

            EntityCreepBase newEntity = constructor.newInstance(world);

            newEntity.copyLocationAndAnglesFrom(this);

            NBTTagCompound compound = new NBTTagCompound();

            writeEntityToNBT(compound);

            newEntity.readEntityFromNBT(compound);

            world.spawnEntity(newEntity);

            setDead();
        }
        catch (Exception ignored)
        {
        }
    }
}

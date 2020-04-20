package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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

public class EntityPreacher extends EntityCreepBase implements IMob
{
    private static final DataParameter<Integer> raise = EntityDataManager.createKey(EntityPreacher.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> raiseLevel = EntityDataManager.createKey(EntityPreacher.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> ritual = EntityDataManager.<Boolean>createKey(EntityPreacher.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> waitTime = EntityDataManager.createKey(EntityPreacher.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> hasVictim = EntityDataManager.<Boolean>createKey(EntityPreacher.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> victimId = EntityDataManager.createKey(EntityPreacher.class, DataSerializers.VARINT);

    public EntityPreacher(World world)
    {
        super(world);

        setCreepTypeName("Preacher");

        creatureType = EnumCreatureType.MONSTER;

        baseHealth = rand.nextInt(50) + 25.0f;

        baseSpeed = 0.35d;

        baseAttackDamage = 5.0d;

        updateAttributes();
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
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(raise, 0);

        dataManager.register(raiseLevel, 0);

        dataManager.register(ritual, Boolean.valueOf(false));

        dataManager.register(waitTime, rand.nextInt(500) + 500);

        dataManager.register(hasVictim, Boolean.valueOf(false));

        dataManager.register(victimId, 0);
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/preacher" + rand.nextInt(3) + ".png");
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (rand.nextInt(4) == 0)
        {
            updateTexture();
        }

        if (isInLava())
        {
            if (rand.nextInt(25) == 0)
            {
                playSound(CreepsSoundHandler.preacherBurnSound, getSoundVolume(), getSoundPitch());
            }

            setOnFireFromLava();
        }
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (inWater || isEntityInsideOpaqueBlock())
        {
            BlockPos pos = new BlockPos(MathHelper.floor(posX), MathHelper.floor(getEntityBoundingBox().minY), MathHelper.floor(posZ));

            if (!world.isAirBlock(pos))
            {
                world.setBlockToAir(pos);

                motionY += 0.5d;
            }
        }

        Entity victim = getVictim();

        if (getHasVictim() && victim != null)
        {
            motionX = 0.0d;

            motionY = 0.0d;

            motionZ = 0.0d;

            setRaise(getRaise() + 1);

            if (getRaise() > getRaiseLevel())
            {
                setHasVictim(false);

                setRitual(false);

                victim.motionY = -0.80000001192092896D;

                setRaise(0);

                setWaitTime(rand.nextInt(500) + 500);
            }
            else
            {
                BlockPos blockPos = new BlockPos(posX, getEntityBoundingBox().minY + 2, posZ);

                if (!world.isAirBlock(blockPos) && victim instanceof EntityPlayer)
                {
                    world.setBlockToAir(blockPos);
                }

                victim.motionY = 0.20000000298023224D;

                setWaitTime(1000);

                smokeVictim(victim);

                smoke();

                if (rand.nextInt(10) == 0)
                {
                    victim.motionX = rand.nextFloat() * 0.85f - 0.5f;
                }
                else if (rand.nextInt(10) == 0)
                {
                    victim.motionZ = rand.nextFloat() * 0.8f - 0.5f;
                }
            }
        }

        if (getRitual() && victim == null)
        {
            for (Entity entity : world.loadedEntityList)
            {
                if (entity instanceof EntitySheep || entity instanceof EntityPig)
                {
                    setHasVictim(true);

                    setVictim(entity);

                    setRaiseLevel(rand.nextInt(50) + 50);

                    playSound(CreepsSoundHandler.preacherRaiseSound, getSoundVolume(), getSoundPitch());

                    break;
                }
            }

            if (getVictim() == null)
            {
                setRitual(false);
            }
        }
        else if (rand.nextInt(2) == 0)
        {
            setWaitTime(getWaitTime() - 1);

            if (getWaitTime() < 1)
            {
                setRitual(true);

                setWaitTime(1000);
            }
        }
    }

    private void setRaise(int i)
    {
        dataManager.set(raise, i);
    }

    public int getRaise()
    {
        return dataManager.get(raise);
    }

    private void setRaiseLevel(int i)
    {
        dataManager.set(raiseLevel, i);
    }

    public int getRaiseLevel()
    {
        return dataManager.get(raiseLevel);
    }

    private void setRitual(boolean b)
    {
        dataManager.set(ritual, Boolean.valueOf(b));
    }

    public boolean getRitual()
    {
        return ((Boolean)dataManager.get(ritual)).booleanValue();
    }

    private void setWaitTime(int i)
    {
        dataManager.set(waitTime, i);
    }

    public int getWaitTime()
    {
        return dataManager.get(waitTime);
    }

    private void setHasVictim(boolean b)
    {
        dataManager.set(hasVictim, Boolean.valueOf(b));
    }

    public boolean getHasVictim()
    {
        return ((Boolean)dataManager.get(hasVictim)).booleanValue();
    }

    private void setVictim(Entity entity)
    {
        dataManager.set(victimId, entity.getEntityId());
    }

    public Entity getVictim()
    {
        int entityId = dataManager.get(victimId);

        if (entityId == 0)
        {
            return null;
        }

        return world.getEntityByID(entityId);
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.preacherSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.preacherHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.preacherDeathSound;
    }

    @Override
    protected void dropItemsOnDeath()
    {
        if (rand.nextInt(50) == 0)
        {
            dropItem(Items.DIAMOND, rand.nextInt(2) + 1);
        }

        if (rand.nextInt(50) == 0)
        {
            entityDropItem(new ItemStack(Items.DYE, 1, 4), 1.0f);
        }

        if (rand.nextInt(50) == 0)
        {
            entityDropItem(new ItemStack(Items.DYE, 1, 3), 1.0f);
        }

        if (rand.nextInt(50) == 0)
        {
            entityDropItem(new ItemStack(Items.DYE, 1, 1), 1.0f);
        }

        if (rand.nextInt(2) == 0)
        {
            dropItem(Items.GOLD_INGOT, rand.nextInt(5) + 2);
        }
        else
        {
            dropItem(Items.BOOK, 1);

            dropItem(Items.APPLE, 1);
        }
    }

    @Override
    public void knockBack(@Nonnull Entity entity, float strength, double xRatio, double zRatio)
    {
        motionX *= 1.5d;

        motionZ *= 1.5d;

        motionY += 0.5d;
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
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, ((posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width) + (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F) + (double)i) - (double)width, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width - (double)i, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)i - (double)width, d, d1, d2);
            }
        }
    }

    private void smokeVictim(Entity entity)
    {
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 5; j++)
            {
                double d = rand.nextGaussian() * 0.02D;
                double d1 = rand.nextGaussian() * 0.02D;
                double d2 = rand.nextGaussian() * 0.02D;
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (entity.posX + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, (entity.posY + (double)(rand.nextFloat() * height) + (double)i) - 2D, (entity.posZ + (double)(rand.nextFloat() * width * 2.0F)) - (double)width, d, d1, d2);
            }
        }
    }

    @Override
    public boolean attackEntityFrom(@Nonnull DamageSource source, float amount)
    {
        boolean flag = super.attackEntityFrom(source, amount);

        if (flag)
        {
            Entity attacker = source.getTrueSource();

            if (getHasVictim() && attacker != null && !(attacker instanceof EntityRocket))
            {
                attacker.motionX += rand.nextFloat() * 1.98F;

                attacker.motionY += rand.nextFloat() * 1.98F;

                attacker.motionZ += rand.nextFloat() * 1.98F;

                return true;
            }
            else if (attacker instanceof EntityRocket)
            {
                attacker = world.getClosestPlayerToEntity(this, 30.0d);

                if (attacker != null)
                {
                    attacker.dismountRidingEntity();

                    setVictim(attacker);

                    setHasVictim(true);

                    attacker.motionX = 0.0d;

                    attacker.motionY = 0.0d;

                    attacker.motionZ = 0.0d;

                    setRaiseLevel(rand.nextInt(50) + 50);

                    playSound(CreepsSoundHandler.preacherRaiseSound, getSoundVolume(), getSoundPitch());
                }
            }

            if (attacker != null)
            {
                setRaise(1);

                setWaitTime(0);

                smoke();

                setHasVictim(true);

                setVictim(attacker);

                attacker.motionX = 0.0d;

                attacker.motionY = 0.0d;

                attacker.motionZ = 0.0d;

                setRaiseLevel(rand.nextInt(50) + 50);

                playSound(CreepsSoundHandler.preacherRaiseSound, getSoundVolume(), getSoundPitch());
            }
        }

        return flag;
    }
}

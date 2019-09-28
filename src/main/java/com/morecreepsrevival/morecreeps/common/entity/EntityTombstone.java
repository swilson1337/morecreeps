package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class EntityTombstone extends EntityCreepBase
{
    private NBTTagCompound additionalProps;

    public EntityTombstone(World worldIn)
    {
        super(worldIn);

        additionalProps = new NBTTagCompound();
    }

    public EntityTombstone(World worldIn, EntityCreepBase deadEntity)
    {
        this(worldIn);

        creatureType = EnumCreatureType.AMBIENT;

        experienceValue = 0;

        setLocationAndAngles(deadEntity.posX, deadEntity.posY, deadEntity.posZ, deadEntity.rotationYaw, 0.0f);

        baseSpeed = 0.0d;

        setBaseTexture(deadEntity.getBaseTexture());

        setCreepTypeName(deadEntity.getCreepTypeName());

        setLevel(deadEntity.getLevel());

        setCreepName(deadEntity.getCreepName());

        setExperience(deadEntity.getExperience());

        setTotalDamage(deadEntity.getTotalDamage());

        setArmor(deadEntity.getArmor());

        setInterest(deadEntity.getInterest());

        setOwner(deadEntity.getOwnerId());

        setWanderState(deadEntity.getWanderState());

        setHealthBoost(deadEntity.getHealthBoost());

        setSkillAttack(deadEntity.getSkillAttack());

        setSkillDefend(deadEntity.getSkillDefend());

        setSkillHealing(deadEntity.getSkillHealing());

        setSkillSpeed(deadEntity.getSkillSpeed());

        deadEntity.onTombstoneCreate(additionalProps);

        updateAttributes();
    }

    @Override
    protected void initEntityAI()
    {
        clearAITasks();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        if (rand.nextInt(10) == 0)
        {
            return CreepsSoundHandler.tombstoneSound;
        }

        return null;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return null;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return null;
    }

    @Override
    public boolean isEntityInsideOpaqueBlock()
    {
        return false;
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 1;
    }

    @Override
    public boolean canDespawn()
    {
        return false;
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        ItemStack itemStack = player.getHeldItem(hand);

        if (itemStack.isEmpty() || itemStack.getItem() != CreepsItemHandler.lifeGem)
        {
            if (!world.isRemote)
            {
                player.sendMessage(new TextComponentString("Use a \2474LifeGem\247f on this tombstone to bring your pet back to life!"));
            }

            return true;
        }
        else if (!isPlayerOwner(player))
        {
            if (!world.isRemote)
            {
                player.sendMessage(new TextComponentString("This is not your tamed pet!"));
            }

            return true;
        }

        itemStack.shrink(1);

        player.swingArm(hand);

        smoke();

        if (!world.isRemote)
        {
            EntityCreepBase entity = null;

            switch (getCreepTypeName())
            {
                case "Guinea Pig":
                    entity = new EntityGuineaPig(world);

                    break;
                case "Hotdog":
                    entity = new EntityHotdog(world);

                    break;
                default:
                    break;
            }

            if (entity != null)
            {
                entity.setPositionAndRotation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);

                entity.setBaseTexture(getBaseTexture());

                entity.setLevel(Math.max(1, getLevel() - 1));

                entity.setCreepName(getCreepName());

                entity.setExperience(getExperience());

                entity.setTotalDamage(getTotalDamage());

                entity.setArmor(getArmor());

                entity.setInterest(getInterest());

                entity.setOwner(getOwnerId());

                entity.setWanderState(getWanderState());

                entity.setHealthBoost(getHealthBoost());

                entity.setSkillAttack(getSkillAttack());

                entity.setSkillDefend(getSkillDefend());

                entity.setSkillHealing(getSkillHealing());

                entity.setSkillSpeed(getSkillSpeed());

                entity.onRevive(additionalProps);

                entity.setInitialHealth();

                world.spawnEntity(entity);

                setDead();
            }
        }

        return true;
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/tombstone.png");
    }

    @Override
    public void onLivingUpdate()
    {
        motionX = 0.0d;

        motionY = 0.0d;

        motionZ = 0.0d;

        super.onLivingUpdate();
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound)
    {
        super.writeEntityToNBT(compound);

        compound.setTag("MoreCreepsTombstone", additionalProps);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound)
    {
        super.readEntityFromNBT(compound);

        additionalProps = compound.getCompoundTag("MoreCreepsTombstone");
    }

    @Override
    public boolean attackEntityFrom(@Nullable DamageSource damageSource, float amt)
    {
        return false;
    }
}

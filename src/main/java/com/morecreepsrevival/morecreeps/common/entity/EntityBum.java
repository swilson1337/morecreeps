package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.init.Items;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityBum extends EntityCreepBase
{
    private static final DataParameter<Integer> timeToPee = EntityDataManager.createKey(EntityBum.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> bumGave = EntityDataManager.<Boolean>createKey(EntityBum.class, DataSerializers.BOOLEAN);

    public EntityBum(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Bum");

        baseSpeed = 0.25d;

        baseHealth = 50.0f;

        baseAttackDamage = 5.0d;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/bum.png");
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();

        dataManager.register(timeToPee, rand.nextInt(900) + 500);

        dataManager.register(bumGave, Boolean.valueOf(false));
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
    protected SoundEvent getHurtSound(DamageSource damageSource)
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

    @Override
    protected void dropItemsOnDeath()
    {
        dropItem(Items.COOKED_PORKCHOP, 1);
    }
}

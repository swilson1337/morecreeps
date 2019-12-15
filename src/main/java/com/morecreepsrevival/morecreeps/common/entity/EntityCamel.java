package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityCamel extends EntityCreepBase
{
    private static final String[] textures = {
            "textures/entity/camel",
            "textures/entity/camel",
            "textures/entity/camel",
            "textures/entity/camel",
            "textures/entity/camelwhite",
            "textures/entity/camelblack",
            "textures/entity/camelbrown",
            "textures/entity/camelgrey",
            "textures/entity/camel",
            "textures/entity/camel",
            "textures/entity/camel",
            "textures/entity/camel",
            "textures/entity/camelwhite"
    };

    private static final String[] names = {
            "Stanley", "Cid", "Hunchy", "The Heat", "Herman the Hump", "Dr. Hump", "Little Lousie", "Spoony G", "Mixmaster C", "The Maestro",
            "Duncan the Dude", "Charlie Camel", "Chip", "Charles Angstrom III", "Mr. Charles", "Cranky Carl", "Carl the Rooster", "Tiny the Peach", "Desert Dan", "Dungby",
            "Doofus"
    };

    public EntityCamel(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Camel");

        setSize(width * 1.5f, height * 4.0f);

        setModelSize(1.75f);

        baseAttackDamage = 2.0d;

        baseHealth = 30.0f;

        baseSpeed = 0.2d;

        updateAttributes();
    }

    @Override
    protected String[] getAvailableTextures()
    {
        return textures;
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
    public void onLivingUpdate()
    {
        if (getModelSize() > 1.75f)
        {
            ignoreFrustumCheck = true;
        }

        super.onLivingUpdate();
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
            dropItem(Items.REEDS, rand.nextInt(3) + 1);
        }
    }

    @Override
    public int getMaxSpawnedInChunk()
    {
        return 4;
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.camelSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.camelHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.camelDeathSound;
    }

    @Override
    protected String[] getTamedNames()
    {
        return names;
    }
}

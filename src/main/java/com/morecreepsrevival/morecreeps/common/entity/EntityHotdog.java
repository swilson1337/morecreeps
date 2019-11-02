package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityHotdog extends EntityCreepBase
{
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

        baseHealth = (float)rand.nextInt(15) + 5.0f;

        baseSpeed = 0.35f;

        updateAttributes();
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
}

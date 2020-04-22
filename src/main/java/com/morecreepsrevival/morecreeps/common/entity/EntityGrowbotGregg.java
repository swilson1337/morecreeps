package com.morecreepsrevival.morecreeps.common.entity;

import net.minecraft.world.World;

public class EntityGrowbotGregg extends EntityCreepBase
{
    public EntityGrowbotGregg(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Growbot Gregg");

        setModelSize(1.5f);

        baseHealth = (float)rand.nextInt(15) + 10.0f;

        baseSpeed = 0.3d;

        updateAttributes();
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/growbotgregg.png");
    }
}

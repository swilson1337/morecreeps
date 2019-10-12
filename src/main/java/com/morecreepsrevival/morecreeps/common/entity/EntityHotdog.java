package com.morecreepsrevival.morecreeps.common.entity;

import net.minecraft.world.World;

public class EntityHotdog extends EntityCreepBase
{
    public EntityHotdog(World world)
    {
        super(world);

        setCreepTypeName("Hotdog");

        setSize(0.5f, 0.75f);

        baseHealth = (float)rand.nextInt(15) + 5.0f;
    }
}

package com.morecreepsrevival.morecreeps.common.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.World;

public class EntityEvilPig extends EntityCreepBase
{
    public EntityEvilPig(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Evil Pig");

        creatureType = EnumCreatureType.MONSTER;
    }
}

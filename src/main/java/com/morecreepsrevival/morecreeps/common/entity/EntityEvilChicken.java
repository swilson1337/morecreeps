package com.morecreepsrevival.morecreeps.common.entity;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.World;

public class EntityEvilChicken extends EntityCreepBase
{
    public EntityEvilChicken(World worldIn)
    {
        super(worldIn);

        setCreepTypeName("Evil Chicken");

        creatureType = EnumCreatureType.MONSTER;
    }
}

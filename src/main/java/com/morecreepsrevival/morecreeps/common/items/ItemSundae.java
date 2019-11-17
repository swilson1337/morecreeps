package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemSundae extends CreepsItemFood
{
    public ItemSundae()
    {
        super("sundae", 0);

        setMaxStackSize(32);
    }

    @Override @Nonnull
    public ItemStack onItemUseFinish(ItemStack itemStack, @Nonnull World worldIn, EntityLivingBase entity)
    {
        entity.playSound(CreepsSoundHandler.guineaPigEatSound, 1.0f, 1.0f);

        return super.onItemUseFinish(itemStack, worldIn, entity);
    }
}

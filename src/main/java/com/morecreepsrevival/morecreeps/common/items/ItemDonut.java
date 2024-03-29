package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemDonut extends CreepsItemFood
{
    public ItemDonut()
    {
        super("donut", 2);

        setMaxStackSize(64);
    }

    @Override @Nonnull
    public ItemStack onItemUseFinish(ItemStack itemStack, @Nonnull World worldIn, EntityLivingBase entity)
    {
        entity.playSound(CreepsSoundHandler.chewSound, 1.0f, 1.0f);

        return super.onItemUseFinish(itemStack, worldIn, entity);
    }
}

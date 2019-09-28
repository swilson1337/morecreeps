package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemLolly extends CreepsItemFood
{
    public ItemLolly()
    {
        super("lolly", 4);

        setMaxStackSize(32);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack itemStack, @Nullable World worldIn, EntityLivingBase entity)
    {
        entity.playSound(CreepsSoundHandler.lollySound, 1.0f, 1.0f);

        return super.onItemUseFinish(itemStack, worldIn, entity);
    }
}

package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemBattery extends CreepsItem
{
    public ItemBattery()
    {
        super("battery");

        setMaxStackSize(16);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.playSound(CreepsSoundHandler.sparkSound, 1.0f, 1.0f);

        player.attackEntityFrom(DamageSource.IN_FIRE, 1.0f);

        return super.onItemRightClick(world, player, hand);
    }
}

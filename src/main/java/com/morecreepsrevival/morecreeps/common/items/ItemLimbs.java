package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemLimbs extends CreepsItem
{
    public ItemLimbs()
    {
        super("limbs");

        setMaxStackSize(24);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.playSound(CreepsSoundHandler.barfSound, 1.0f, 1.0f);

        player.getHeldItem(hand).shrink(1);

        player.attackEntityFrom(DamageSource.STARVE, 1.0f);

        // TODO: barf effect

        return super.onItemRightClick(world, player, hand);
    }
}

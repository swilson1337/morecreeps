package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemBandaid extends CreepsItem
{
    private static final int healAmount = 4;

    public ItemBandaid()
    {
        super("bandaid");

        setMaxStackSize(24);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        if (player.getHealth() < 20)
        {
            player.playSound(CreepsSoundHandler.bandaidSound, 1.0f, 1.0f);

            player.getHeldItem(hand).shrink(1);

            player.heal(healAmount);
        }

        return super.onItemRightClick(world, player, hand);
    }
}

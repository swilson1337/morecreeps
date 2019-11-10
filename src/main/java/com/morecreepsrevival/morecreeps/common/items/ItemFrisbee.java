package com.morecreepsrevival.morecreeps.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemFrisbee extends CreepsItem
{
    public ItemFrisbee()
    {
        super("frisbee");

        setMaxStackSize(1);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.getHeldItem(hand).shrink(1);

        player.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, getSoundVolume(), getSoundPitch());

        if (!world.isRemote)
        {
            // TODO: spawn frisbee entity
        }

        return super.onItemRightClick(world, player, hand);
    }
}

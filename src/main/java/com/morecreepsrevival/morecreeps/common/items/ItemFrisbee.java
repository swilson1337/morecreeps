package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemFrisbee extends CreepsItem
{
    public ItemFrisbee()
    {
        super("frisbee");

        setMaxStackSize(1);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nullable EnumHand hand)
    {
        player.getHeldItem(hand).shrink(1);

        player.playSound(CreepsSoundHandler.randomBowSound, getSoundVolume(), getSoundPitch());

        if (!world.isRemote)
        {
            // TODO: spawn frisbee entity
        }

        return super.onItemRightClick(world, player, hand);
    }
}

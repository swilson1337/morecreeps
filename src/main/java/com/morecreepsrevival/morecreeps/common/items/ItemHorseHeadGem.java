package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemHorseHeadGem extends CreepsItem
{
    public ItemHorseHeadGem()
    {
        super("horse_head_gem");

        setMaxStackSize(16);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nullable EnumHand hand)
    {
        player.getHeldItem(hand).shrink(1);

        player.swingArm(hand);

        player.playSound(CreepsSoundHandler.horseHeadGemSound, 1.0f, 1.0f);

        if (!world.isRemote)
        {
            // TODO: spawn horse head entity
        }

        return super.onItemRightClick(world, player, hand);
    }
}

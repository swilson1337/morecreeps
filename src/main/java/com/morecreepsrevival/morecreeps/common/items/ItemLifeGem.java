package com.morecreepsrevival.morecreeps.common.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemLifeGem extends CreepsItem
{
    public ItemLifeGem()
    {
        super("life_gem");

        setMaxStackSize(16);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nullable EnumHand hand)
    {
        return super.onItemRightClick(world, player, hand);
    }
}

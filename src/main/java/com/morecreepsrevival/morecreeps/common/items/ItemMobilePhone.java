package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemMobilePhone extends CreepsItem
{
    public ItemMobilePhone()
    {
        super("mobile_phone");

        setMaxStackSize(1);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.swingArm(hand);

        player.playSound(CreepsSoundHandler.mobileSound, 1.0f, (itemRand.nextFloat() - itemRand.nextFloat()) * 0.2f + 1.0f);

        return super.onItemRightClick(world, player, hand);
    }
}

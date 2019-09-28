package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntityGooDonut;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemGooDonut extends CreepsItem
{
    public ItemGooDonut()
    {
        super("goo_donut");

        setMaxStackSize(16);
    }

    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nullable EnumHand hand)
    {
        player.getHeldItem(hand).shrink(1);

        player.swingArm(hand);

        player.playSound(CreepsSoundHandler.randomBowSound, getSoundVolume(), getSoundPitch());

        EntityGooDonut gooDonut = new EntityGooDonut(world, player);

        if (!world.isRemote)
        {
            world.spawnEntity(gooDonut);
        }

        return super.onItemRightClick(world, player, hand);
    }
}

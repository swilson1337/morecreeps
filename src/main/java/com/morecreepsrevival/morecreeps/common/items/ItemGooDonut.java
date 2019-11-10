package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntityGooDonut;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemGooDonut extends CreepsItem
{
    public ItemGooDonut()
    {
        super("goo_donut");

        setMaxStackSize(16);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.getHeldItem(hand).shrink(1);

        player.swingArm(hand);

        player.playSound(SoundEvents.ENTITY_SKELETON_SHOOT, getSoundVolume(), getSoundPitch());

        EntityGooDonut gooDonut = new EntityGooDonut(world, player);

        if (!world.isRemote)
        {
            world.spawnEntity(gooDonut);
        }

        return super.onItemRightClick(world, player, hand);
    }
}

package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemGun extends CreepsItem
{
    public ItemGun()
    {
        super("gun");

        setMaxStackSize(1);

        setMaxDamage(128);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nullable EnumHand hand)
    {
        player.playSound(CreepsSoundHandler.bulletSound, getSoundVolume(), getSoundPitch());

        // TODO: fire bullet

        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public boolean isFull3D()
    {
        return true;
    }
}

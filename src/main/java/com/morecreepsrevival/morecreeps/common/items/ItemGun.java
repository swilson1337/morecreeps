package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntityBullet;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemGun extends CreepsItem
{
    public ItemGun()
    {
        this("gun", false);
    }

    public ItemGun(String itemName, boolean noCreativeTab)
    {
        super(itemName, noCreativeTab);

        setMaxStackSize(1);

        setMaxDamage(128);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.playSound(CreepsSoundHandler.bulletSound, getSoundVolume(), getSoundPitch());

        player.getHeldItem(hand).damageItem(2, player);

        EntityBullet bullet = new EntityBullet(world, player);

        if (!world.isRemote)
        {
            world.spawnEntity(bullet);
        }

        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public boolean isFull3D()
    {
        return true;
    }
}

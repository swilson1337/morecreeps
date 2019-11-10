package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntityShrink;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemShrinkRay extends CreepsItem
{
    public ItemShrinkRay()
    {
        super("shrink_ray");

        setMaxStackSize(1);

        setMaxDamage(128);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.playSound(CreepsSoundHandler.shrinkRaySound, getSoundVolume(), getSoundPitch());

        player.getHeldItem(hand).damageItem(1, player);

        EntityShrink shrink = new EntityShrink(world, player);

        if (!world.isRemote)
        {
            world.spawnEntity(shrink);
        }

        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public float getSoundVolume()
    {
        return 0.5f;
    }

    @Override
    public float getSoundPitch()
    {
        return (0.4f / ((itemRand.nextFloat() * 0.4f) + 0.8f));
    }
}

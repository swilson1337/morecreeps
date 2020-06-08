package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntityGrow;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemGrowRay extends CreepsItem
{
    public ItemGrowRay()
    {
        this("grow_ray", false);
    }

    public ItemGrowRay(String itemName, boolean noCreativeTab)
    {
        super(itemName, noCreativeTab);

        setMaxStackSize(1);

        setMaxDamage(64);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.playSound(CreepsSoundHandler.growRaySound, getSoundVolume(), getSoundPitch());

        player.getHeldItem(hand).damageItem(1, player);

        EntityGrow grow = new EntityGrow(world, player);

        if (!world.isRemote)
        {
            world.spawnEntity(grow);
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

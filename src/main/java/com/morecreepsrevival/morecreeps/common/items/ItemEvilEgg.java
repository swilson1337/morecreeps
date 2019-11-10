package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntityEvilEgg;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemEvilEgg extends CreepsItem
{
    public ItemEvilEgg()
    {
        super("evil_egg");

        setMaxStackSize(44);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.getHeldItem(hand).shrink(1);

        player.swingArm(hand);

        player.playSound(CreepsSoundHandler.evilEggCluckSound, 1.0f, 1.0f);

        EntityEvilEgg evilEgg = new EntityEvilEgg(world, player);

        if (!world.isRemote)
        {
            world.spawnEntity(evilEgg);
        }

        return super.onItemRightClick(world, player, hand);
    }
}

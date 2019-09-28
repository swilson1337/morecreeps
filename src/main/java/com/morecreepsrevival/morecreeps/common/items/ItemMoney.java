package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntityMoney;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class ItemMoney extends CreepsItem
{
    public ItemMoney()
    {
        super("money");

        setMaxStackSize(50);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nullable EnumHand hand)
    {
        player.getHeldItem(hand).shrink(1);

        EntityMoney money = new EntityMoney(world, player);

        money.setDefaultPickupDelay();

        money.setNoDespawn();

        money.setThrower(player.getUniqueID().toString());

        if (!world.isRemote)
        {
            world.spawnEntity(money);
        }

        return super.onItemRightClick(world, player, hand);
    }
}

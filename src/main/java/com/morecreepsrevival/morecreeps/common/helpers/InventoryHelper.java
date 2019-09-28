package com.morecreepsrevival.morecreeps.common.helpers;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class InventoryHelper
{
    public static int getItemCount(InventoryPlayer inv, Item item)
    {
        int itemCount = 0;

        int invSize = inv.mainInventory.size();

        for (int i = 0; i < invSize; i++)
        {
            ItemStack itemStack = inv.mainInventory.get(i);

            if (itemStack.getItem() == item)
            {
                itemCount += itemStack.getCount();
            }
        }

        return itemCount;
    }

    public static boolean hasItem(InventoryPlayer inv, Item item)
    {
        int invSize = inv.mainInventory.size();

        for (int i = 0; i < invSize; i++)
        {
            ItemStack itemStack = inv.mainInventory.get(i);

            if (itemStack.getItem() == item)
            {
                return true;
            }
        }

        return false;
    }

    public static boolean takeItem(InventoryPlayer inv, Item item, int amount)
    {
        if (getItemCount(inv, item) < amount)
        {
            return false;
        }

        int invSize = inv.mainInventory.size();

        for (int i = 0; i < invSize; i++)
        {
            ItemStack itemStack = inv.mainInventory.get(i);

            if (itemStack.getItem() == item)
            {
                int stackSize = itemStack.getCount();

                if (stackSize > 0)
                {
                    int takeAmt = Math.min(stackSize, amount);

                    itemStack.shrink(takeAmt);

                    amount -= takeAmt;

                    if (amount < 1)
                    {
                        break;
                    }
                }
            }
        }

        return true;
    }
}

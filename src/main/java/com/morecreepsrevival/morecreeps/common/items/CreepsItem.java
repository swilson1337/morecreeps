package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class CreepsItem extends Item
{
    public CreepsItem(String itemName, boolean noCreativeTab)
    {
        super();

        setItemName(itemName);

        if (!noCreativeTab)
        {
            setCreativeTab(CreepsItemHandler.creativeTab);
        }
    }

    public CreepsItem(String itemName)
    {
        this(itemName, false);
    }

    public float getSoundVolume()
    {
        return 0.5f;
    }

    public float getSoundPitch()
    {
        return (0.4f / ((itemRand.nextFloat() * 0.4f) + 0.8f));
    }

    public CreepsItem setItemName(String itemName)
    {
        setRegistryName(new ResourceLocation(MoreCreepsAndWeirdos.modid, itemName));

        setUnlocalizedName(MoreCreepsAndWeirdos.modid + "." + itemName);

        return this;
    }
}
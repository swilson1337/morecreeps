package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import net.minecraft.item.ItemFood;
import net.minecraft.util.ResourceLocation;

public class CreepsItemFood extends ItemFood
{
    public CreepsItemFood(String itemName, int amount, float saturation)
    {
        super(amount, saturation, false);

        setAlwaysEdible();

        setItemName(itemName);

        setCreativeTab(CreepsItemHandler.creativeTab);
    }

    public CreepsItemFood(String itemName, int amount)
    {
        this(itemName, amount, 0.6f);
    }

    public float getSoundVolume()
    {
        return 0.5f;
    }

    public float getSoundPitch()
    {
        return (0.4f / ((itemRand.nextFloat() * 0.4f) + 0.8f));
    }

    public CreepsItemFood setItemName(String itemName)
    {
        setRegistryName(new ResourceLocation(MoreCreepsAndWeirdos.modid, itemName));

        setUnlocalizedName(MoreCreepsAndWeirdos.modid + "." + itemName);

        return this;
    }
}

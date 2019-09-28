package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import net.minecraft.item.ItemSword;
import net.minecraft.util.ResourceLocation;

public class CreepsItemSword extends ItemSword
{
    public CreepsItemSword(String itemName, ToolMaterial toolMaterial)
    {
        super(toolMaterial);

        setRegistryName(new ResourceLocation(MoreCreepsAndWeirdos.modid, itemName));

        setUnlocalizedName(MoreCreepsAndWeirdos.modid + "." + itemName);

        setCreativeTab(CreepsItemHandler.creativeTab);
    }

    public float getSoundVolume()
    {
        return 0.5f;
    }

    public float getSoundPitch()
    {
        return (0.4f / ((itemRand.nextFloat() * 0.4f) + 0.8f));
    }
}

package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.util.ResourceLocation;

public class CreepsItemArmor extends ItemArmor
{
    public CreepsItemArmor(String itemName, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn)
    {
        super(materialIn, renderIndexIn, equipmentSlotIn);

        setItemName(itemName);

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

    public CreepsItemArmor setItemName(String itemName)
    {
        setRegistryName(new ResourceLocation(MoreCreepsAndWeirdos.modid, itemName));

        setUnlocalizedName(MoreCreepsAndWeirdos.modid + "." + itemName);

        return this;
    }
}

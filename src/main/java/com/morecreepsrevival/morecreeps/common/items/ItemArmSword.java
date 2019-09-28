package com.morecreepsrevival.morecreeps.common.items;

import net.minecraft.block.state.IBlockState;

public class ItemArmSword extends CreepsItemSword
{
    public ItemArmSword()
    {
        super("arm_sword", ToolMaterial.DIAMOND);

        setMaxDamage(64);
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn)
    {
        return false;
    }
}

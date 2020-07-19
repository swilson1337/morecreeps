package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.capabilities.CaveDrumsProvider;
import com.morecreepsrevival.morecreeps.common.capabilities.ICaveDrums;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemCavemanClub extends CreepsItemSword
{
    public ItemCavemanClub()
    {
        super("caveman_club", ToolMaterial.WOOD);

        setMaxDamage(64);
    }

    @Override
    public float getAttackDamage()
    {
        return 4.0f;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, IBlockState state)
    {
        return 3.0f;
    }

    @Override
    public boolean isFull3D()
    {
        return true;
    }

    @Override
    public boolean canHarvestBlock(IBlockState state)
    {
        return false;
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);

        if (isSelected && entityIn instanceof EntityPlayer)
        {
            ICaveDrums capability = entityIn.getCapability(CaveDrumsProvider.capability, null);

            if (capability != null)
            {
                capability.setDrumsTime(capability.getDrumsTime() - 1);

                if (capability.getDrumsTime() < 0)
                {
                    entityIn.playSound(CreepsSoundHandler.caveDrumsSound, 0.65f, 0.9f);

                    capability.setDrumsTime(itemRand.nextInt(200) + 150);
                }
            }
        }
    }
}

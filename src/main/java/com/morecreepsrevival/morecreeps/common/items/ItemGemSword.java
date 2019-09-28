package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemGemSword extends CreepsItemSword
{
    public ItemGemSword()
    {
        super("gem_sword", ToolMaterial.DIAMOND);

        setMaxDamage(256);
    }

    @Override
    public float getAttackDamage()
    {
        return 25.0f;
    }

    @Override
    public float getDestroySpeed(ItemStack itemStack, IBlockState blockState)
    {
        return 55.0f;
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockState)
    {
        return true;
    }

    @Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int itemSlot, boolean isSelected)
    {
        super.onUpdate(itemStack, world, entity, itemSlot, isSelected);

        if (isSelected && entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)entity;

            if (player.isSwingInProgress)
            {
                player.playSound(CreepsSoundHandler.gemSwordSound, getSoundVolume(), getSoundPitch());
            }
        }
    }

    @Override
    public float getSoundVolume()
    {
        return 0.3f;
    }
}

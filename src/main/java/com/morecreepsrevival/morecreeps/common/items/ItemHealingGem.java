package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemHealingGem extends CreepsItem
{
    private static final int healAmount = 5;

    public ItemHealingGem()
    {
        super("healing_gem");

        setMaxStackSize(1);

        setMaxDamage(16);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        if (player.getHealth() < 20)
        {
            player.playSound(CreepsSoundHandler.healingGemSound, 1.0f, 1.0f);

            player.getHeldItem(hand).damageItem(1, player);

            player.swingArm(hand);

            for (int i = 0; i < 20; i++)
            {
                double d = itemRand.nextGaussian() * 0.02D;
                double d1 = itemRand.nextGaussian() * 0.02D;
                double d2 = itemRand.nextGaussian() * 0.02D;
                world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (player.posX + itemRand.nextGaussian() * 0.5D) - itemRand.nextGaussian() * 0.5D, ((player.posY - 0.5D) + itemRand.nextGaussian() * 0.5D) - itemRand.nextGaussian() * 0.5D, (player.posZ + itemRand.nextGaussian() * 0.5D) - itemRand.nextGaussian() * 0.5D, d, d1, d2);
                world.spawnParticle(EnumParticleTypes.HEART, (player.posX + itemRand.nextGaussian() * 0.5D) - itemRand.nextGaussian() * 0.5D, ((player.posY - 0.5D) + itemRand.nextGaussian() * 0.5D) - itemRand.nextGaussian() * 0.5D, (player.posZ + itemRand.nextGaussian() * 0.5D) - itemRand.nextGaussian() * 0.5D, d, d1, d2);
            }

            player.heal(healAmount);
        }

        return super.onItemRightClick(world, player, hand);
    }
}

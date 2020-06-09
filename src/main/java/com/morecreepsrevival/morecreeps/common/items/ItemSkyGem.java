package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.helpers.EffectHelper;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemSkyGem extends CreepsItem
{
    private double floatCycle = 0.0d;

    private int floatDir = 1;

    private static final double floatMaxCycle = 0.2199999988079071d;

    public float jumpBoost = 0.0f;

    public int usage = 100;

    public ItemSkyGem()
    {
        super("sky_gem");

        setMaxStackSize(1);

        setMaxDamage(32);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        if (hand == EnumHand.OFF_HAND && !player.getHeldItem(EnumHand.MAIN_HAND).isEmpty())
        {
            return super.onItemRightClick(world, player, hand);
        }

        player.playSound(CreepsSoundHandler.skyGemUpSound, 1.0f, 1.0f);

        usage -= 10;

        if (usage < 0)
        {
            usage = 200;

            if (!world.isRemote)
            {
                player.getHeldItem(hand).damageItem(1, player);
            }
        }

        jumpBoost = 1.0f;

        floatCycle = 0.0d;

        floatDir = 1;

        double d = -MathHelper.sin((player.rotationYaw * (float)Math.PI) / 180F);
        double d1 = MathHelper.cos((player.rotationYaw * (float)Math.PI) / 180F);
        player.motionX += d * 0.5D;
        player.motionZ += d1 * 0.5D;
        player.fallDistance = -25F;

        return super.onItemRightClick(world, player, hand);
    }

    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
        super.onUpdate(stack, worldIn, entityIn, itemSlot, isSelected);

        if (!(entityIn instanceof EntityPlayer))
        {
            return;
        }

        EntityPlayer player = (EntityPlayer)entityIn;

        if (player.onGround)
        {
            player.fallDistance = 0.0f;
        }

        boolean inOffHand = player.getHeldItem(EnumHand.OFF_HAND).equals(stack);

        if (isSelected || inOffHand)
        {
            player.fallDistance = -25.0f;

            if (player.isSwingInProgress && (!inOffHand || player.getHeldItem(EnumHand.MAIN_HAND).isEmpty()))
            {
                player.playSound(CreepsSoundHandler.skyGemDownSound, 0.6f, 1.0f);

                jumpBoost = -0.35f;

                player.fallDistance = -15.0f;
            }

            EffectHelper.smoke2(worldIn, player, itemRand);

            if (player.onGround)
            {
                floatDir = 1;

                floatCycle = 0.0d;
            }

            if (usage-- < 0)
            {
                usage = 200;

                stack.damageItem(1, player);
            }

            if (floatDir > 0)
            {
                floatCycle += 0.026499999687075615d;

                if (floatCycle > floatMaxCycle)
                {
                    floatDir *= -1;

                    player.fallDistance -= 1.5f;
                }
            }
            else
            {
                floatCycle -= 0.0099999997764825821d;

                if (floatCycle < -floatMaxCycle)
                {
                    floatDir *= -1;

                    player.fallDistance -= 1.5f;
                }
            }

            player.posY -= floatCycle;

            player.motionY = floatCycle + (double)jumpBoost;

            if (jumpBoost > 0.0f)
            {
                jumpBoost -= 0.055f;
            }

            if (jumpBoost < 0.0f)
            {
                jumpBoost += 0.055f;
            }
        }
    }
}

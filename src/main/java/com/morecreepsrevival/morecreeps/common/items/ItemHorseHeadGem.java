package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntityHorseHead;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemHorseHeadGem extends CreepsItem
{
    public ItemHorseHeadGem()
    {
        super("horse_head_gem");

        setMaxStackSize(16);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.getHeldItem(hand).shrink(1);

        player.swingArm(hand);

        player.playSound(CreepsSoundHandler.horseHeadGemSound, 1.0f, 1.0f);

        if (!world.isRemote)
        {
            double d = -MathHelper.sin((player.rotationYaw * (float)Math.PI) / 180f);

            double d1 = MathHelper.cos((player.rotationYaw * (float)Math.PI) / 180f);

            EntityHorseHead horseHead = new EntityHorseHead(world);

            horseHead.setLocationAndAngles(player.posX + d * 1.0d, player.posY + 1.0d, player.posZ + d1 * 1.0d, player.rotationYaw, 0.0f);

            horseHead.determineBaseTexture();

            horseHead.setInitialHealth();

            world.spawnEntity(horseHead);
        }

        return super.onItemRightClick(world, player, hand);
    }
}

package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntitySchlump;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemBabyJarFull extends CreepsItem
{
    private static final int healAmount = 20;

    public ItemBabyJarFull()
    {
        super("baby_jar_full");

        setMaxStackSize(1);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        if (player.isSneaking())
        {
            player.playSound(CreepsSoundHandler.barfSound, 1.0f, 1.0f);

            player.getHeldItem(hand).shrink(1);

            // TODO: barf

            player.heal(healAmount);
        }
        else
        {
            float f = 1.0f;
            float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
            float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
            double d2 = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
            double d3 = (player.prevPosY + (player.posY - player.prevPosY) * (double)f + 1.6200000000000001d) - player.getYOffset();
            double d4 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;
            Vec3d vec3d = new Vec3d(d2, d3, d4);
            float f3 = MathHelper.cos(-f2 * 0.01745329f - (float)Math.PI);
            float f4 = MathHelper.sin(-f2 * 0.01745329f - (float)Math.PI);
            float f5 = -MathHelper.cos(-f1 * 0.01745329f);
            float f6 = MathHelper.sin(-f1 * 0.01745329f);
            float f7 = f4 * f5;
            float f9 = f3 * f5;
            double d5 = 5d;
            Vec3d vec3d1 = vec3d.addVector((double)f7 * d5, (double)f6 * d5, (double)f9 * d5);

            RayTraceResult rtr = world.rayTraceBlocks(vec3d, vec3d1, true);

            if (rtr != null && rtr.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                BlockPos blockPos = rtr.getBlockPos();

                EntitySchlump schlump = new EntitySchlump(world);

                schlump.determineBaseTexture();

                schlump.setInitialHealth();

                schlump.setLocationAndAngles(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), player.rotationYaw, 0.0f);

                schlump.setOwner(player);

                if (!world.isRemote)
                {
                    world.spawnEntity(schlump);
                }

                return ActionResult.newResult(EnumActionResult.SUCCESS, new ItemStack(CreepsItemHandler.babyJarEmpty));
            }
        }

        return super.onItemRightClick(world, player, hand);
    }
}

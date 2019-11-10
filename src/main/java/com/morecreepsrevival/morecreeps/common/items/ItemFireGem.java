package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.entity.EntityGuineaPig;
import com.morecreepsrevival.morecreeps.common.entity.EntityHotdog;
import com.morecreepsrevival.morecreeps.common.entity.EntityHunchback;
import com.morecreepsrevival.morecreeps.common.entity.EntityPreacher;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemFireGem extends CreepsItem
{
    public ItemFireGem()
    {
        super("fire_gem");

        setMaxStackSize(1);

        setMaxDamage(64);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.playSound(CreepsSoundHandler.fireGemSound, 1.0f, 1.0f);

        player.getHeldItem(hand).damageItem(1, player);

        player.swingArm(hand);

        for (Entity entity : world.getEntitiesWithinAABB(EntityLivingBase.class, player.getEntityBoundingBox().expand(10.0d, 10.0d, 10.0d)))
        {
            EntityLivingBase livingEntity = (EntityLivingBase)entity;

            if (livingEntity instanceof EntityHotdog || livingEntity instanceof EntityHunchback || livingEntity instanceof EntityPlayer || livingEntity instanceof EntityGuineaPig || livingEntity instanceof EntityPreacher)
            {
                continue;
            }

            for (int i = 0; i < 10; i++)
            {
                double d = itemRand.nextGaussian() * 0.02D;
                double d1 = itemRand.nextGaussian() * 0.02D;
                double d2 = itemRand.nextGaussian() * 0.02D;
                world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, livingEntity.posX + (double)(itemRand.nextFloat() * 1.5F), livingEntity.posY + 0.5D + (double)(itemRand.nextFloat() * 2.5F), livingEntity.posZ + (double)(itemRand.nextFloat() * 1.5F), d, d1, d2);
            }

            livingEntity.attackEntityFrom(DamageSource.IN_FIRE, 2.0f);

            livingEntity.motionY += 0.5d;

            livingEntity.setFire(15);
        }

        return super.onItemRightClick(world, player, hand);
    }
}

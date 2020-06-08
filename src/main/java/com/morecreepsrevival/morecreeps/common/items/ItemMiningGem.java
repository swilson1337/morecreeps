package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemMiningGem extends CreepsItem
{
    public ItemMiningGem()
    {
        super("mining_gem");

        setMaxStackSize(1);

        setMaxDamage(64);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.swingArm(hand);
        
        float f = 1.0F;
        float f1 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * f;
        float f2 = player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * f;
        double d = player.prevPosX + (player.posX - player.prevPosX) * (double)f;
        double d3 = (player.prevPosY + (player.posY - player.prevPosY) * (double)f + 1.6200000000000001D) - player.getYOffset();
        double d6 = player.prevPosZ + (player.posZ - player.prevPosZ) * (double)f;

        Vec3d vec3d = new Vec3d(d, d3, d6);

        float f3 = MathHelper.cos(-f2 * 0.01745329F - (float)Math.PI);
        float f4 = MathHelper.sin(-f2 * 0.01745329F - (float)Math.PI);
        float f5 = -MathHelper.cos(-f1 * 0.01745329F);
        float f6 = MathHelper.sin(-f1 * 0.01745329F);
        float f7 = f4 * f5;
        float f9 = f3 * f5;
        double d9 = 5D;

        Vec3d vec3d1 = vec3d.addVector((double)f7 * d9, (double)f6 * d9, (double)f9 * d9);

        RayTraceResult rayTraceResult = world.rayTraceBlocks(vec3d, vec3d1, true);

        if (rayTraceResult != null)
        {
            if (rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                BlockPos blockPos = rayTraceResult.getBlockPos();

                int x = blockPos.getX();

                int y = blockPos.getY();

                int z = blockPos.getZ();

                Block block = world.getBlockState(blockPos).getBlock();

                if (block == Blocks.STONE || block == Blocks.COBBLESTONE || block == Blocks.MOSSY_COBBLESTONE || block == Blocks.GRAVEL)
                {
                    if (!world.isRemote)
                    {
                        player.getHeldItem(hand).damageItem(1, player);
                    }

                    player.playSound(CreepsSoundHandler.miningGemSound, 1.0f, 1.0f);

                    for (int i = 0; i < 20; i++)
                    {
                        double d1 = itemRand.nextGaussian() * 0.02D;
                        double d4 = itemRand.nextGaussian() * 0.02D;
                        double d7 = itemRand.nextGaussian() * 0.02D;
                        world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (double)x + (double)(itemRand.nextFloat() * 1.5F), (double)((float)y + 0.5F) + (double)(itemRand.nextFloat() * 2.5F), (double)z + (double)(itemRand.nextFloat() * 1.5F), d1, d4, d7);
                    }

                    if (!world.isRemote)
                    {
                        switch (itemRand.nextInt(7))
                        {
                            case 1:
                                if (itemRand.nextInt(7) == 0)
                                {
                                    world.setBlockState(blockPos, Blocks.GOLD_ORE.getDefaultState());
                                }

                                break;
                            case 2:
                                if (itemRand.nextInt(5) == 0)
                                {
                                    world.setBlockState(blockPos, Blocks.IRON_ORE.getDefaultState());
                                }

                                break;
                            case 3:
                                if (itemRand.nextInt(1) == 0)
                                {
                                    world.setBlockState(blockPos, Blocks.COAL_ORE.getDefaultState());
                                }

                                break;
                            case 4:
                                if (itemRand.nextInt(5) == 0)
                                {
                                    world.setBlockState(blockPos, Blocks.LAPIS_ORE.getDefaultState());
                                }

                                break;
                            case 5:
                                if (itemRand.nextInt(10) == 0)
                                {
                                    world.setBlockState(blockPos, Blocks.DIAMOND_ORE.getDefaultState());
                                }

                                break;
                            case 6:
                            case 7:
                                if (itemRand.nextInt(3) == 0)
                                {
                                    world.setBlockState(blockPos, Blocks.REDSTONE_ORE.getDefaultState());
                                }

                                break;
                            default:
                                world.setBlockToAir(blockPos);

                                break;
                        }
                    }
                }
                else
                {
                    player.playSound(CreepsSoundHandler.miningGemBadSound, 1.0f, 1.0f);

                    for (int i = 0; i < 20; i++)
                    {
                        double d2 = itemRand.nextGaussian() * 0.02D;
                        double d5 = itemRand.nextGaussian() * 0.02D;
                        double d8 = itemRand.nextGaussian() * 0.02D;
                        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, (double)x + (double)(itemRand.nextFloat() * 1.5F), (double)((float)y + 0.5F) + (double)(itemRand.nextFloat() * 2.5F), (double)z + (double)(itemRand.nextFloat() * 1.5F), d2, d5, d8);
                    }
                }
            }
        }
        
        return super.onItemRightClick(world, player, hand);
    }
}

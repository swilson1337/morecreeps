package com.morecreepsrevival.morecreeps.common.items;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFarmland;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemEarthGem extends CreepsItem
{
    public ItemEarthGem()
    {
        super("earth_gem");

        setMaxStackSize(1);

        setMaxDamage(32);
    }

    @Override @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        player.playSound(CreepsSoundHandler.earthGemSound, 1.0f, 1.0f);

        player.swingArm(hand);

        player.getHeldItem(hand).damageItem(1, player);

        for (int i = -2; i < 4; i++)
        {
            for (int k = -3; k < 3; k++)
            {
                for (int j = -3; j < 3; j++)
                {
                    BlockPos blockPos1 = new BlockPos(player.posX + k, (player.posY - 2) + i, player.posZ + j);

                    BlockPos blockPos2 = new BlockPos(player.posX + k, (player.posY - 1) + i, player.posZ + j);

                    Block block = world.getBlockState(blockPos1).getBlock();

                    if ((block == Blocks.DIRT || block == Blocks.GRASS) && itemRand.nextInt(5) == 0 && world.isAirBlock(blockPos2))
                    {
                        // TODO: dirt effect

                        world.setBlockState(blockPos1, Blocks.FARMLAND.getDefaultState());

                        world.setBlockState(blockPos2, Blocks.WHEAT.getDefaultState());
                    }

                    IBlockState blockState = world.getBlockState(blockPos2);

                    Block block1 = blockState.getBlock();

                    if (block1 == Blocks.WHEAT)
                    {
                        int cropAge = blockState.getValue(BlockCrops.AGE);

                        if (cropAge < 7)
                        {
                            world.setBlockState(blockPos2, Blocks.WHEAT.getDefaultState().withProperty(BlockCrops.AGE, cropAge + 1));
                        }
                    }
                    else if (block1 == Blocks.FARMLAND)
                    {
                        int moisture = blockState.getValue(BlockFarmland.MOISTURE);

                        if (moisture < 7)
                        {
                            world.setBlockState(blockPos2, Blocks.FARMLAND.getDefaultState().withProperty(BlockFarmland.MOISTURE, moisture + 1));
                        }
                    }
                }
            }
        }

        return super.onItemRightClick(world, player, hand);
    }
}

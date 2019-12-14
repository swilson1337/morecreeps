package com.morecreepsrevival.morecreeps.common.world;

import com.morecreepsrevival.morecreeps.common.entity.*;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockStairs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class JailManager
{
    private static final int maxObstruct = 99999;

    public static boolean buildJail(EntityPlayer player, World world, Random rand)
    {
        int randInt = rand.nextInt(200) - 100;

        if (rand.nextInt(2) == 0)
        {
            randInt *= -1;
        }

        int jailX = (int)player.posX + randInt;

        int jailY = rand.nextInt(20) + 25;

        int jailZ = (int)player.posZ + randInt;

        if (!world.isBlockLoaded(new BlockPos(jailX, jailY, jailZ - 31)) || !world.isBlockLoaded(new BlockPos(jailX + 14, jailY, jailZ - 31)) || !world.isBlockLoaded(new BlockPos(jailX, jailY, jailZ + 45)) || !world.isBlockLoaded(new BlockPos(jailX + 14, jailY, jailZ + 45)))
        {
            return false;
        }

        int area = 0;

        for (int i = -1; i < 6; i++)
        {
            for (int k = -1; k < 14; k++)
            {
                for (int q = -1; q < 14; q++)
                {
                    if (world.isAirBlock(new BlockPos(jailX + k, jailY + i, jailZ + q)))
                    {
                        area++;

                        if (area > JailManager.maxObstruct)
                        {
                            return false;
                        }
                    }
                }
            }
        }

        Block block = world.getBlockState(new BlockPos(jailX + 16, jailY + 20, jailZ + 7)).getBlock();

        if (block == Blocks.FLOWING_WATER || block == Blocks.WATER)
        {
            area++;

            if (area > JailManager.maxObstruct)
            {
                return false;
            }
        }

        for (int i = -1; i < 6; i++)
        {
            for (int k = -41; k < 55; k++)
            {
                for (int q = -1; q < 16; q++)
                {
                    int a = rand.nextInt(100);

                    if (a < 1)
                    {
                        world.setBlockState(new BlockPos(jailX + q, jailY + i, jailZ + k), Blocks.GRAVEL.getDefaultState());
                    }
                    else if (a < 15)
                    {
                        world.setBlockState(new BlockPos(jailX + q, jailY + i, jailZ + k), Blocks.MOSSY_COBBLESTONE.getDefaultState());
                    }
                    else
                    {
                        world.setBlockState(new BlockPos(jailX + q, jailY + i, jailZ + k), Blocks.STONE.getDefaultState());
                    }
                }
            }
        }

        for (int i = 0; i < 5; i++)
        {
            for (int k = 0; k < 13; k++)
            {
                for (int q = 0; q < 13; q++)
                {
                    world.setBlockToAir(new BlockPos(jailX + q, jailY + i, jailZ + k + 1));
                }
            }
        }

        for (int i = 0; i < 5; i++)
        {
            for (int k = 3; k < 11; k++)
            {
                for (int q = 3; q < 11; q++)
                {
                    world.setBlockState(new BlockPos(jailX + q, jailY + i, jailZ + k + 1), Blocks.STONE.getDefaultState());
                }
            }
        }

        for (int i = 0; i < 5; i++)
        {
            for (int k = 5; k < 9; k++)
            {
                for (int q = 5; q < 9; q++)
                {
                    world.setBlockToAir(new BlockPos(jailX + q, jailY + i, jailZ + k + 1));
                }
            }
        }

        world.setBlockToAir(new BlockPos(jailX + 7, jailY + 1, jailZ + 4));

        world.setBlockState(new BlockPos(jailX + 7, jailY + 1, jailZ + 5), Blocks.IRON_BARS.getDefaultState());

        world.setBlockState(new BlockPos(jailX + 3, jailY + 1, jailZ + 7), Blocks.GLASS.getDefaultState());

        world.setBlockState(new BlockPos(jailX + 4, jailY + 1, jailZ + 7), Blocks.GLASS.getDefaultState());

        world.setBlockToAir(new BlockPos(jailX + 10, jailY + 1, jailZ + 7));

        world.setBlockState(new BlockPos(jailX + 9, jailY + 1, jailZ + 7), Blocks.IRON_BARS.getDefaultState());

        world.setBlockToAir(new BlockPos(jailX + 7, jailY + 1, jailZ + 11));

        world.setBlockState(new BlockPos(jailX + 7, jailY + 1, jailZ + 10), Blocks.IRON_BARS.getDefaultState());

        world.setBlockToAir(new BlockPos(jailX + 4, jailY, jailZ + 8));

        world.setBlockToAir(new BlockPos(jailX + 3, jailY, jailZ + 8));

        world.setBlockToAir(new BlockPos(jailX + 4, jailY + 1, jailZ + 8));

        world.setBlockToAir(new BlockPos(jailX + 3, jailY + 1, jailZ + 8));

        world.setBlockState(new BlockPos(jailX + 3, jailY, jailZ + 8), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.FACING, EnumFacing.EAST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT));

        world.setBlockState(new BlockPos(jailX + 3, jailY + 1, jailZ + 8), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.FACING, EnumFacing.EAST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT));

        int offsetX = 15;

        int offsetZ = 7;

        int stairHeight = 80;

        while (world.isAirBlock(new BlockPos(jailX + offsetX, stairHeight, jailZ + offsetZ)) || world.getBlockState(new BlockPos(jailX + offsetX, stairHeight, jailZ + offsetZ)).getBlock() == Blocks.LOG || world.getBlockState(new BlockPos(jailX + offsetX, stairHeight, jailZ + offsetZ)).getBlock() == Blocks.LEAVES)
        {
            stairHeight--;
        }

        int maxI = stairHeight - jailY;

        for (int i = 0; i < maxI; i++)
        {
            for (int k = 0; k < 2; k++)
            {
                for (int q = 0; q < 2; q++)
                {
                    world.setBlockToAir(new BlockPos(jailX + k + offsetX, jailY + i, jailZ + offsetZ + q));
                }
            }
        }

        int side = 0;

        for (int i = 0; i < maxI; i++)
        {
            switch (side)
            {
                case 0:
                    world.setBlockState(new BlockPos(jailX + offsetX, jailY + i, jailZ + offsetZ), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH));

                    break;
                case 1:
                    world.setBlockState(new BlockPos(jailX + offsetX + 1, jailY + i, jailZ + offsetZ), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                    break;
                case 2:
                    world.setBlockState(new BlockPos(jailX + offsetX + 1, jailY + i, jailZ + offsetZ + 1), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH));

                    break;
                case 3:
                    world.setBlockState(new BlockPos(jailX + offsetX, jailY + i, jailZ + offsetZ + 1), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));

                    break;
                default:
                    break;
            }

            if (side++ == 3)
            {
                side = 0;
            }
        }

        for (int i = 0; i < 3; i++)
        {
            world.setBlockToAir(new BlockPos(jailX + 13 + i, jailY, jailZ + 7));

            world.setBlockToAir(new BlockPos(jailX + 13 + i, jailY + 1, jailZ + 7));
        }

        world.setBlockState(new BlockPos(jailX + 13, jailY, jailZ + offsetZ), Blocks.IRON_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.FACING, EnumFacing.EAST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT));

        world.setBlockState(new BlockPos(jailX + 13, jailY + 1, jailZ + offsetZ), Blocks.IRON_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.FACING, EnumFacing.EAST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT));

        world.setBlockState(new BlockPos(jailX + 15, jailY, jailZ + offsetZ), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

        world.setBlockToAir(new BlockPos(jailX + 14, jailY + 2, jailZ + offsetZ));

        for (int i = 0; i < 32; i++)
        {
            for (int k = 6; k < 9; k++)
            {
                for (int q = 0; q < 4; q++)
                {
                    world.setBlockToAir(new BlockPos(jailX + k, jailY + q, jailZ - i - 1));

                    world.setBlockToAir(new BlockPos(jailX + k, jailY + q, jailZ + i + 15));
                }
            }
        }

        for (int i = 1; i < 5; i++)
        {
            for (int k = 0; k < 3; k++)
            {
                for (int q = 0; q < 3; q++)
                {
                    for (int z = 0; z < 4; z++)
                    {
                        world.setBlockToAir(new BlockPos(jailX + 10 + q, jailY + z, jailZ - i * 7 + k));

                        world.setBlockToAir(new BlockPos(jailX + 2 + q, jailY + z, jailZ - i * 7 + k));

                        world.setBlockToAir(new BlockPos(jailX + 10 + q, jailY + z, jailZ + i * 7 + 12 + k));

                        world.setBlockToAir(new BlockPos(jailX + 2 + q, jailY + z, jailZ + i * 7 + 12 + k));
                    }
                }
            }
        }

        world.setBlockToAir(new BlockPos(jailX + 7, jailY, jailZ));

        world.setBlockToAir(new BlockPos(jailX + 7, jailY + 1, jailZ));

        world.setBlockToAir(new BlockPos(jailX + 7, jailY, jailZ + 14));

        world.setBlockToAir(new BlockPos(jailX + 7, jailY + 1, jailZ + 14));

        for (int i = 0; i < 4; i++)
        {
            world.setBlockState(new BlockPos(jailX + 5, jailY + 1, jailZ - i * 7 - 7), Blocks.IRON_BARS.getDefaultState());

            world.setBlockState(new BlockPos(jailX + 5, jailY, jailZ - i * 7 - 5), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.FACING, EnumFacing.WEST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT));

            world.setBlockState(new BlockPos(jailX + 5, jailY + 1, jailZ - i * 7 - 5), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.FACING, EnumFacing.WEST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT));

            world.setBlockState(new BlockPos(jailX + 9, jailY + 1, jailZ - i * 7 - 7), Blocks.IRON_BARS.getDefaultState());

            world.setBlockToAir(new BlockPos(jailX + 9, jailY, jailZ - i * 7 - 5));

            world.setBlockToAir(new BlockPos(jailX + 9, jailY + 1, jailZ - i * 7 - 5));

            world.setBlockState(new BlockPos(jailX + 9, jailY, jailZ - i * 7 - 5), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.FACING, EnumFacing.EAST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT));

            world.setBlockState(new BlockPos(jailX + 9, jailY + 1, jailZ - i * 7 - 5), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.FACING, EnumFacing.EAST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT));

            world.setBlockState(new BlockPos(jailX + 5, jailY + 1, jailZ + i * 7 + 21), Blocks.IRON_BARS.getDefaultState());

            world.setBlockState(new BlockPos(jailX + 5, jailY, jailZ + i * 7 + 19), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.FACING, EnumFacing.WEST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT));

            world.setBlockState(new BlockPos(jailX + 5, jailY + 1, jailZ + i * 7 + 19), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.FACING, EnumFacing.WEST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.RIGHT));

            world.setBlockState(new BlockPos(jailX + 9, jailY + 1, jailZ + i * 7 + 21), Blocks.IRON_BARS.getDefaultState());

            world.setBlockToAir(new BlockPos(jailX + 9, jailY, jailZ + i * 7 + 19));

            world.setBlockToAir(new BlockPos(jailX + 9, jailY + 1, jailZ + i * 7 + 19));

            world.setBlockState(new BlockPos(jailX + 9, jailY, jailZ + i * 7 + 19), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.LOWER).withProperty(BlockDoor.FACING, EnumFacing.EAST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT));

            world.setBlockState(new BlockPos(jailX + 9, jailY + 1, jailZ + i * 7 + 19), Blocks.OAK_DOOR.getDefaultState().withProperty(BlockDoor.HALF, BlockDoor.EnumDoorHalf.UPPER).withProperty(BlockDoor.FACING, EnumFacing.EAST).withProperty(BlockDoor.HINGE, BlockDoor.EnumHingePosition.LEFT));

            if (rand.nextInt(1) == 0)
            {
                world.setBlockState(new BlockPos(jailX + 12, jailY + 2, jailZ - i * 7 - 5), Blocks.TORCH.getDefaultState());
            }

            if (rand.nextInt(1) == 0)
            {
                world.setBlockState(new BlockPos(jailX + 2, jailY + 2, jailZ - i * 7 - 5), Blocks.TORCH.getDefaultState());
            }

            if (rand.nextInt(1) == 0)
            {
                world.setBlockState(new BlockPos(jailX + 12, jailY + 2, jailZ + i * 7 + 19), Blocks.TORCH.getDefaultState());
            }

            if (rand.nextInt(1) == 0)
            {
                world.setBlockState(new BlockPos(jailX + 2, jailY + 2, jailZ + i * 7 + 19), Blocks.TORCH.getDefaultState());
            }
        }

        for (int i = 0; i < 9; i++)
        {
            if (rand.nextInt(2) == 0)
            {
                world.setBlockState(new BlockPos(jailX + 6, jailY + 2, jailZ - i * 4 - 2), Blocks.TORCH.getDefaultState());
            }

            if (rand.nextInt(2) == 0)
            {
                world.setBlockState(new BlockPos(jailX + 8, jailY + 2, jailZ - i * 4 - 2), Blocks.TORCH.getDefaultState());
            }

            if (rand.nextInt(2) == 0)
            {
                world.setBlockState(new BlockPos(jailX + 6, jailY + 2, jailZ + i * 4 + 18), Blocks.TORCH.getDefaultState());
            }

            if (rand.nextInt(2) == 0)
            {
                world.setBlockState(new BlockPos(jailX + 8, jailY + 2, jailZ + i * 4 + 18), Blocks.TORCH.getDefaultState());
            }
        }

        BlockPos chest1Pos = new BlockPos(jailX + 12, jailY, jailZ + 12);

        BlockPos chest1Pos2 = new BlockPos(jailX + 12, jailY, jailZ + 13);

        world.setBlockState(chest1Pos, Blocks.CHEST.getDefaultState());

        world.setBlockState(chest1Pos2, Blocks.CHEST.getDefaultState());

        TileEntityChest chest1 = new TileEntityChest();

        world.setTileEntity(chest1Pos, chest1);

        TileEntityChest chest12 = new TileEntityChest();

        world.setTileEntity(chest1Pos2, chest12);

        BlockPos chest2Pos = new BlockPos(jailX + 12, jailY, jailZ + 1);

        world.setBlockState(chest2Pos, Blocks.CHEST.getDefaultState());

        TileEntityChest chest2 = new TileEntityChest();

        world.setTileEntity(chest2Pos, chest2);

        BlockPos chest3Pos = new BlockPos(jailX, jailY, jailZ + 13);

        world.setBlockState(chest3Pos, Blocks.CHEST.getDefaultState());

        TileEntityChest chest3 = new TileEntityChest();

        world.setTileEntity(chest3Pos, chest3);

        BlockPos chest4Pos = new BlockPos(jailX, jailY, jailZ + 1);

        world.setBlockState(chest4Pos, Blocks.CHEST.getDefaultState());

        TileEntityChest chest4 = new TileEntityChest();

        world.setTileEntity(chest4Pos, chest4);

        int chestIndex = 0;

        int chest1Size = chest1.getSizeInventory();

        TileEntityChest chestToUse = chest1;

        for (int i = 0; i < player.inventory.mainInventory.size(); i++)
        {
            ItemStack itemStack = player.inventory.mainInventory.get(i);

            if (!itemStack.isEmpty())
            {
                if (chestToUse.equals(chest1) && (chestIndex + 1) > chest1Size)
                {
                    chestToUse = chest12;

                    chestIndex = 0;
                }

                chestToUse.setInventorySlotContents(chestIndex++, itemStack.copy());

                itemStack.shrink(itemStack.getCount());
            }
        }

        for (int i = 0; i < player.inventory.armorInventory.size(); i++)
        {
            ItemStack itemStack = player.inventory.armorInventory.get(i);

            if (!itemStack.isEmpty())
            {
                if (chestToUse.equals(chest1) && (chestIndex + 1) > chest1Size)
                {
                    chestToUse = chest12;

                    chestIndex = 0;
                }

                chestToUse.setInventorySlotContents(chestIndex++, itemStack.copy());

                itemStack.shrink(itemStack.getCount());
            }
        }

        for (int i = 0; i < player.inventory.offHandInventory.size(); i++)
        {
            ItemStack itemStack = player.inventory.offHandInventory.get(i);

            if (!itemStack.isEmpty())
            {
                if (chestToUse.equals(chest1) && (chestIndex + 1) > chest1Size)
                {
                    chestToUse = chest12;

                    chestIndex = 0;
                }

                chestToUse.setInventorySlotContents(chestIndex++, itemStack.copy());

                itemStack.shrink(itemStack.getCount());
            }
        }

        for (ItemStack itemStack : player.getEquipmentAndArmor())
        {
            if (!itemStack.isEmpty())
            {
                if (chestToUse.equals(chest1) && (chestIndex + 1) > chest1Size)
                {
                    chestToUse = chest12;

                    chestIndex = 0;
                }

                chestToUse.setInventorySlotContents(chestIndex++, itemStack.copy());

                itemStack.shrink(itemStack.getCount());
            }
        }

        for (int i = 1; i < chest4.getSizeInventory(); i++)
        {
            int r = rand.nextInt(10);

            if (r == 1)
            {
                chest4.setInventorySlotContents(i, new ItemStack(CreepsItemHandler.bandaid, rand.nextInt(2) + 1));
            }
            else if (r == 2)
            {
                chest4.setInventorySlotContents(i, new ItemStack(CreepsItemHandler.money, rand.nextInt(24) + 1));
            }
        }

        BlockPos mobSpawnerBlockPos = new BlockPos(jailX + 11, jailY, jailZ + 13);

        world.setBlockState(mobSpawnerBlockPos, Blocks.MOB_SPAWNER.getDefaultState());

        TileEntityMobSpawner mobSpawner = new TileEntityMobSpawner();

        world.setTileEntity(mobSpawnerBlockPos, mobSpawner);

        mobSpawner.getSpawnerBaseLogic().setEntityId(EntityList.getKey(EntitySkeleton.class));

        chest2.setInventorySlotContents(rand.nextInt(5), new ItemStack(Items.STONE_PICKAXE, 1));

        chest2.setInventorySlotContents(rand.nextInt(5) + 5, new ItemStack(Items.APPLE, 1));

        chest3.setInventorySlotContents(rand.nextInt(5) + 5, new ItemStack(Item.getItemFromBlock(Blocks.TORCH), rand.nextInt(16)));

        chest3.setInventorySlotContents(rand.nextInt(5), new ItemStack(Items.APPLE, 1));

        world.setBlockState(new BlockPos(jailX + 6, jailY + 2, jailZ + 9), Blocks.TORCH.getDefaultState());

        int petRoom = rand.nextInt(11);

        for (int i = 0; i < 4; i++)
        {
            for (int k = 0; k < 4; k++)
            {
                int placeX;

                int placeZ;

                switch (k + 1)
                {
                    case 2:
                        placeX = jailX + 2;

                        placeZ = jailZ - i * 7 - 5;

                        break;
                    case 3:
                        placeX = jailX + 12;

                        placeZ = jailZ + i * 7 + 19;

                        break;
                    case 4:
                        placeX = jailX + 2;

                        placeZ = jailZ + i * 7 + 19;

                        break;
                    case 1:
                    default:
                        placeX = jailX + 12;

                        placeZ = jailZ - i * 7 - 5;

                        break;
                }

                if ((i * 4 + k) == petRoom)
                {
                    populateCell(player, world, rand, placeX, jailY, placeZ, true);
                }
                else
                {
                    populateCell(player, world, rand, placeX, jailY, placeZ, false);
                }

                if (rand.nextInt(3) == 0)
                {
                    dropTreasure(world, rand,jailX + 12, jailY + 2, jailZ - i * 7 - 6);
                }

                if (rand.nextInt(3) == 0)
                {
                    dropTreasure(world, rand,jailX + 2, jailY + 2, jailZ - i * 7 - 6);
                }

                if (rand.nextInt(3) == 0)
                {
                    dropTreasure(world, rand, jailX + 12, jailY + 2, jailZ + i * 7 + 20);
                }

                if (rand.nextInt(3) == 0)
                {
                    dropTreasure(world, rand, jailX + 2, jailY + 2, jailZ + i * 7 + 20);
                }
            }
        }

        int lawyerCount = rand.nextInt(5) + 3;

        for (int i = 1; i < lawyerCount; i++)
        {
            EntityLawyerFromHell lawyer = new EntityLawyerFromHell(world);

            lawyer.setLocationAndAngles(jailX + 8, jailY + 1, jailZ - 12, player.rotationYaw, 0.0f);

            lawyer.setUndead(true);

            lawyer.determineBaseTexture();

            lawyer.setInitialHealth();

            world.spawnEntity(lawyer);

            EntityLawyerFromHell lawyer2 = new EntityLawyerFromHell(world);

            lawyer2.setLocationAndAngles(jailX + 8, jailY + 1, jailZ + 26, player.rotationYaw, 0.0f);

            lawyer2.setUndead(true);

            lawyer2.determineBaseTexture();

            lawyer2.setInitialHealth();

            world.spawnEntity(lawyer2);
        }

        lawyerCount = rand.nextInt(3) + 3;

        for (int i = 2; i < lawyerCount; i++)
        {
            EntityLawyerFromHell lawyer = new EntityLawyerFromHell(world);

            lawyer.setLocationAndAngles(jailX + i, jailY + 2, jailZ + 2, player.rotationYaw, 0.0f);

            lawyer.setUndead(true);

            lawyer.determineBaseTexture();

            lawyer.setInitialHealth();

            world.spawnEntity(lawyer);

            EntityLawyerFromHell lawyer2 = new EntityLawyerFromHell(world);

            lawyer2.setLocationAndAngles(jailX + 2, jailY + 2, jailZ + i, player.rotationYaw, 0.0f);

            lawyer2.setUndead(true);

            lawyer2.determineBaseTexture();

            lawyer2.setInitialHealth();

            world.spawnEntity(lawyer2);
        }

        player.setPosition(jailX + 7, jailY + 2, jailZ + 7);

        player.heal(20.0f);

        player.playSound(CreepsSoundHandler.lawyerBustedSound, 1.0f, 1.0f);

        if (rand.nextInt(5) == 0)
        {
            dropTreasure(world, rand, jailX + 8, jailY + 2, jailZ + 8);
        }

        return true;
    }

    private static void dropTreasure(World world, Random rand, int x, int y, int z)
    {
        int treasureIndex = rand.nextInt(12);

        ItemStack itemStack;

        switch (treasureIndex)
        {
            case 1:
                itemStack = new ItemStack(Items.WHEAT, rand.nextInt(2) + 1);

                break;
            case 2:
                itemStack = new ItemStack(Items.COOKIE, rand.nextInt(3) + 3);

                break;
            case 3:
                itemStack = new ItemStack(Items.PAPER, 1);

                break;
            case 4:
                itemStack = new ItemStack(CreepsItemHandler.blorpCola, rand.nextInt(3) + 1);

                break;
            case 5:
                itemStack = new ItemStack(Items.BREAD, 1);

                break;
            case 6:
                itemStack = new ItemStack(CreepsItemHandler.evilEgg, rand.nextInt(2) + 1);

                break;
            case 7:
                itemStack = new ItemStack(Items.WATER_BUCKET, 1);

                break;
            case 8:
            case 11:
                itemStack = new ItemStack(Items.CAKE, 1);

                break;
            case 9:
                itemStack = new ItemStack(CreepsItemHandler.money, rand.nextInt(5) + 5);

                break;
            case 10:
                itemStack = new ItemStack(CreepsItemHandler.lolly, rand.nextInt(2) + 1);

                break;
            case 12:
                itemStack = new ItemStack(CreepsItemHandler.gooDonut, rand.nextInt(2) + 1);

                break;
            default:
                itemStack = new ItemStack(Items.COOKIE, rand.nextInt(2) + 1);

                break;
        }

        EntityItem entityItem = new EntityItem(world, x, y, z, itemStack);

        entityItem.setPickupDelay(10);

        world.spawnEntity(entityItem);
    }

    private static void populateCell(EntityPlayer player, World world, Random rand, int placeX, int placeY, int placeZ, boolean flag)
    {
        if (flag)
        {
            for (Entity entity : world.getEntitiesWithinAABBExcludingEntity(player, player.getEntityBoundingBox().expand(26.0d, 26.0d, 26.0d)))
            {
                if (entity instanceof EntityCreepBase)
                {
                    EntityCreepBase creep = (EntityCreepBase)entity;

                    if (creep.isTamed())
                    {
                        creep.setWanderState(1);

                        if (creep.getModelSize() > 1.0f)
                        {
                            creep.resetModelSize();
                        }

                        creep.setLocationAndAngles(placeX, placeY, placeZ, player.rotationYaw, 0.0f);
                    }
                }
            }

            return;
        }

        switch (rand.nextInt(5) + 1)
        {
            case 1:
                EntityRatMan ratMan = new EntityRatMan(world);

                ratMan.setLocationAndAngles(placeX, placeY, placeZ, player.rotationYaw, 0.0f);

                ratMan.setInitialHealth();

                ratMan.determineBaseTexture();

                world.spawnEntity(ratMan);

                break;
            case 3:
                // TODO: spawn camel jockey

                break;
            case 4:
                EntityMummy mummy = new EntityMummy(world);

                mummy.setLocationAndAngles(placeX, placeY, placeZ, player.rotationYaw, 0.0f);

                mummy.setInitialHealth();

                mummy.determineBaseTexture();

                world.spawnEntity(mummy);

                break;
            case 2:
            case 5:
            default:
                EntityPrisoner prisoner = new EntityPrisoner(world);

                prisoner.setLocationAndAngles(placeX, placeY, placeZ, player.rotationYaw, 0.0f);

                prisoner.setInitialHealth();

                prisoner.determineBaseTexture();

                world.spawnEntity(prisoner);

                break;
        }
    }
}
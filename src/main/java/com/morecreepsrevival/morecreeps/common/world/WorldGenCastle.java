package com.morecreepsrevival.morecreeps.common.world;

import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.entity.EntityCastleCritter;
import com.morecreepsrevival.morecreeps.common.entity.EntityCastleGuard;
import com.morecreepsrevival.morecreeps.common.entity.EntityCastleKing;
import com.morecreepsrevival.morecreeps.common.entity.EntityMummy;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;

public class WorldGenCastle extends WorldGenerator
{
    private static final int maxObstruct = 300;

    private static final int castleHeight = 5;

    private int topFloor = 0;

    private int floor = 1;

    public boolean generate(@Nonnull World world, @Nonnull Random rand, @Nonnull BlockPos pos)
    {
        Biome biome = world.getBiome(pos);

        if (!((MoreCreepsConfig.spawnInNonVanillaBiomes && MoreCreepsConfig.hasBiome(Objects.requireNonNull(biome.getRegistryName()).toString())) || Objects.requireNonNull(biome.getRegistryName()).getResourceDomain().equals("minecraft")))
        {
            return false;
        }

        for (Type type : BiomeDictionary.getTypes(biome))
        {
            if (type == Type.NETHER || type == Type.END)
            {
                return false;
            }
        }

        int x = pos.getX();

        int z = pos.getZ();

        /*EntityPlayer player = world.getClosestPlayer((double)x, (double)y, (double)z, 64.0d, false);

        if (player == null)
        {
            return false;
        }*/

        int castleX = x + rand.nextInt(8) - rand.nextInt(8);

        int castleY = 200;

        int castleZ = z + rand.nextInt(8) - rand.nextInt(8);

        /*if (Math.abs(castleX - player.posX) < 10.0d || Math.abs(castleZ - player.posZ) < 10.0d)
        {
            return false;
        }
        else*/

        int castlePosX = castleX;

        int castlePosY = castleY;

        int castlePosZ = castleZ;

        int foundation = 0;

        int foundationDepth = 0;

        boolean alternate = true;

        while (foundation < 3)
        {
            foundation = 0;

            Block block1 = world.getBlockState(new BlockPos(castlePosX - 4, castlePosY - foundationDepth, castlePosZ - 4)).getBlock();

            Block block2 = world.getBlockState(new BlockPos(castlePosX + 30, castlePosY - foundationDepth, castlePosZ - 4)).getBlock();

            Block block3 = world.getBlockState(new BlockPos(castlePosX - 4, castlePosY - foundationDepth, castlePosZ + 30)).getBlock();

            Block block4 = world.getBlockState(new BlockPos(castlePosX + 30, castlePosY - foundationDepth, castlePosZ + 30)).getBlock();

            if (block1 == Blocks.WATER || block1 == Blocks.FLOWING_WATER || block2 == Blocks.WATER || block2 == Blocks.FLOWING_WATER || block3 == Blocks.WATER || block3 == Blocks.FLOWING_WATER || block4 == Blocks.WATER || block4 == Blocks.FLOWING_WATER)
            {
                return false;
            }
            else if (block1 != Blocks.AIR && block1 != Blocks.LEAVES)
            {
                foundation++;
            }

            if (block2 != Blocks.AIR && block2 != Blocks.LEAVES)
            {
                foundation++;
            }

            if (block3 != Blocks.AIR && block3 != Blocks.LEAVES)
            {
                foundation++;
            }

            if (block4 != Blocks.AIR && block4 != Blocks.LEAVES)
            {
                foundation++;
            }

            foundationDepth++;
        }

        castlePosY = castleY = castlePosY - foundationDepth + 2;

        if (!world.isBlockLoaded(new BlockPos(castleX - 4, castleY, castleZ - 4)) || !world.isBlockLoaded(new BlockPos(castleX + 30, castleY, castleZ - 4)) || !world.isBlockLoaded(new BlockPos(castleX + 30, castleY, castleZ + 30)) || !world.isBlockLoaded(new BlockPos(castleX - 4, castleY, castleZ + 30)))
        {
            return false;
        }

        int maxI = (castleHeight * 7) + 7;

        int area = 0;

        for (int i = 0; i < maxI; i += 2)
        {
            for (int k = (-12 + i); k < 38; k += 2)
            {
                for (int j = (-12 + i); j < 38; j += 2)
                {
                    Block block = world.getBlockState(new BlockPos(x + k, castlePosY + i, z + j)).getBlock();

                    if (block != Blocks.AIR && block != Blocks.LEAVES && block != Blocks.LEAVES2 && block != Blocks.LOG && block != Blocks.LOG2)
                    {
                        area++;

                        if (area > maxObstruct)
                        {
                            return false;
                        }
                    }
                }
            }
        }

        floor = 1;

        topFloor = 0;

        for (int i = -1; i < maxI; i++)
        {
            for (int k = -12; k < 38; k++)
            {
                for (int j = -12; j < 38; j++)
                {
                    world.setBlockToAir(new BlockPos(castlePosX + k, castlePosY + i, castlePosZ + j));
                }
            }
        }

        for (int i = 2; i < 5; i++)
        {
            for (int k = -12; k < 38; k++)
            {
                for (int j = -12; j < 38; j++)
                {
                    BlockPos blockPos = new BlockPos(castlePosX + k, castlePosY - i, castlePosZ + j);

                    if (world.isAirBlock(blockPos))
                    {
                        world.setBlockState(blockPos, Blocks.STONE.getDefaultState());
                    }
                }
            }
        }

        for (int i = -10; i < 36; i++)
        {
            for (int k = -10; k < 36; k++)
            {
                world.setBlockState(new BlockPos(castlePosX + i, castlePosY - 1, castlePosZ + k), Blocks.WATER.getDefaultState());
            }
        }

        maxI = (castleHeight * 7) - 7;

        for (int i = -7; i < maxI; i += 7)
        {
            for (int k = -4; k < 30; k++)
            {
                for (int j = -4; j < 30; j++)
                {
                    BlockPos blockPos = new BlockPos(castlePosX + k, castlePosY + 6 + i, castlePosZ + j);

                    if ((world.isAirBlock(blockPos) || world.getBlockState(blockPos).getBlock() == Blocks.WATER || world.getBlockState(blockPos).getBlock() == Blocks.FLOWING_WATER) && rand.nextInt(50) != 0)
                    {
                        world.setBlockState(blockPos, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                    }

                    blockPos = new BlockPos(castlePosX + k, castlePosY + 7 + i, castlePosZ + j);

                    if (world.isAirBlock(blockPos) && rand.nextInt(25) == 0 && i < (((castleHeight - 1) * 7) - 7))
                    {
                        world.setBlockState(blockPos, Blocks.WEB.getDefaultState());

                        if (rand.nextInt(10) == 0)
                        {
                            world.setBlockState(new BlockPos(castlePosX + k, castlePosY + 8 + i, castlePosZ + j), Blocks.WEB.getDefaultState());
                        }
                    }
                }
            }

            int placed = 0;

            while (placed < 2)
            {
                int spawnX = rand.nextInt(20) - 10;

                int spawnZ = rand.nextInt(20) - 10;

                BlockPos blockPos = new BlockPos(castlePosX + 10 + spawnX, castlePosY + 7 + i, castlePosZ + 5 + spawnZ);

                if (world.isAirBlock(blockPos))
                {
                    world.setBlockState(blockPos, Blocks.MOB_SPAWNER.getDefaultState());

                    TileEntityMobSpawner spawner = new TileEntityMobSpawner();

                    spawner.getSpawnerBaseLogic().setEntityId(populateSpawner(rand));

                    world.setTileEntity(blockPos, spawner);

                    placed++;
                }
            }

            if (i == (((castleHeight - 1) * 7) - 7))
            {
                placed = 0;

                while (placed < 2)
                {
                    int spawnX = rand.nextInt(20) - 10;

                    int spawnZ = rand.nextInt(20) - 10;

                    BlockPos blockPos = new BlockPos(castlePosX + 10 + spawnX, castlePosY + 7 + i, castlePosZ + 5 + spawnZ);

                    if (world.isAirBlock(blockPos))
                    {
                        world.setBlockState(blockPos, Blocks.MOB_SPAWNER.getDefaultState());

                        TileEntityMobSpawner spawner = new TileEntityMobSpawner();

                        spawner.getSpawnerBaseLogic().setEntityId(EntityList.getKey(EntityCastleGuard.class));

                        world.setTileEntity(blockPos, spawner);

                        placed++;
                    }
                }
            }
        }

        buildTower(world, rand, castleX, castleY, castleZ, castlePosX, castlePosY, castlePosZ, true, new ItemStack(CreepsItemHandler.earthGem), rand.nextInt(castleHeight) + 1);

        maxI = (castleHeight * 7) - 6;

        for (int i = 5; i < 20; i++)
        {
            for (int k = 0; k < 10; k++)
            {
                for (int j = -2; j < maxI; j++)
                {
                    world.setBlockState(new BlockPos(castlePosX + i, castlePosY + j, castlePosZ - 5), cobbler(1, rand));
                }
            }
        }

        for (int i = 4; i < 21; i++)
        {
            if (alternate)
            {
                world.setBlockState(new BlockPos(castlePosX + i, castlePosY + 29, castlePosZ - 5), cobbler(1, rand));
            }

            alternate = !alternate;

            world.setBlockState(new BlockPos(castlePosX + i, castlePosY + 20, castlePosZ - 6), cobbler(1, rand));

            world.setBlockState(new BlockPos(castlePosX + i, castlePosY + 16, castlePosZ - 6), cobbler(1, rand));

            if (alternate)
            {
                world.setBlockState(new BlockPos(castlePosX + i, castlePosY + 10, castlePosZ - 6), cobbler(1, rand));
            }

            world.setBlockState(new BlockPos(castlePosX + i, castlePosY + 9, castlePosZ - 6), cobbler(1, rand));
        }

        buildTower(world, rand, castleX, castleY, castleZ, castlePosX + 25, castlePosY, castlePosZ, false, new ItemStack(CreepsItemHandler.miningGem), rand.nextInt(castleHeight) + 1);

        for (int i = 5; i < 20; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                for (int k = -2; k < maxI; k++)
                {
                    world.setBlockState(new BlockPos(castlePosX + i, castlePosY + k, castlePosZ + 29), cobbler(1, rand));
                }
            }
        }

        for (int i = 4; i < 21; i++)
        {
            if (alternate)
            {
                world.setBlockState(new BlockPos(castlePosX + i, castlePosY + 29, castlePosZ + 29), cobbler(1, rand));
            }

            alternate = !alternate;

            world.setBlockState(new BlockPos(castlePosX + i, castlePosY + 20, castlePosZ + 30), cobbler(1, rand));

            world.setBlockState(new BlockPos(castlePosX + i, castlePosY + 16, castlePosZ + 30), cobbler(1, rand));

            if (alternate)
            {
                world.setBlockState(new BlockPos(castlePosX + i, castlePosY + 10, castlePosZ + 30), cobbler(1, rand));
            }

            world.setBlockState(new BlockPos(castlePosX + i, castlePosY + 9, castlePosZ + 30), cobbler(1, rand));
        }

        buildTower(world, rand, castleX, castleY, castleZ, castlePosX + 25, castlePosY, castlePosZ + 25, false, new ItemStack(CreepsItemHandler.skyGem), rand.nextInt(castleHeight) + 1);

        for (int i = 5; i < 20; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                for (int k = -2; k < maxI; k++)
                {
                    world.setBlockState(new BlockPos(castlePosX - 5, castlePosY + k, castlePosZ + i), cobbler(1, rand));
                }
            }
        }

        for (int i = 4; i < 21; i++)
        {
            if (alternate)
            {
                world.setBlockState(new BlockPos(castlePosX - 5, castlePosY + 29, castlePosZ + i), cobbler(1, rand));
            }

            alternate = !alternate;

            world.setBlockState(new BlockPos(castlePosX - 6, castlePosY + 20, castlePosZ + i), cobbler(1, rand));

            world.setBlockState(new BlockPos(castlePosX - 6, castlePosY + 16, castlePosZ + i), cobbler(1, rand));

            if (alternate)
            {
                world.setBlockState(new BlockPos(castlePosX - 6, castlePosY + 10, castlePosZ + i), cobbler(1, rand));
            }

            world.setBlockState(new BlockPos(castlePosX - 6, castlePosY + 9, castlePosZ + i), cobbler(1, rand));
        }

        buildTower(world, rand, castleX, castleY, castleZ, castlePosX, castlePosY, castlePosZ + 25, true, new ItemStack(CreepsItemHandler.healingGem), rand.nextInt(castleHeight) + 1);

        for (int i = 5; i < 20; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                for (int k = -2; k < maxI; k++)
                {
                    world.setBlockState(new BlockPos(castlePosX + 29, castlePosY + k, castlePosZ + i), cobbler(1, rand));
                }
            }
        }

        for (int i = 4; i < 21; i++)
        {
            if (alternate)
            {
                world.setBlockState(new BlockPos(castlePosX + 29, castlePosY + 29, castlePosZ + i), cobbler(1, rand));
            }

            alternate = !alternate;

            world.setBlockState(new BlockPos(castlePosX + 30, castlePosY + 20, castlePosZ + i), cobbler(1, rand));

            world.setBlockState(new BlockPos(castlePosX + 30, castlePosY + 16, castlePosZ + i), cobbler(1, rand));

            if (alternate)
            {
                world.setBlockState(new BlockPos(castlePosX + 30, castlePosY + 10, castlePosZ + i), cobbler(1, rand));
            }

            world.setBlockState(new BlockPos(castlePosX + 30, castlePosY + 9, castlePosZ + i), cobbler(1, rand));
        }

        maxI = ((castleHeight - 1) * 7) - 6;

        for (int i = 0; i < maxI; i += 7)
        {
            for (int j = 6; j < 20; j += 3)
            {
                world.setBlockState(new BlockPos(castlePosX + j, castlePosY + 4 + i, castlePosZ - 4), Blocks.TORCH.getDefaultState());

                world.setBlockState(new BlockPos(castlePosX + j, castlePosY + 4 + i, castlePosZ + 28), Blocks.TORCH.getDefaultState());

                if (j > 6 && j < 17)
                {
                    world.setBlockState(new BlockPos(castlePosX - 4, castlePosY + 4 + i, castlePosZ + j), Blocks.TORCH.getDefaultState());

                    world.setBlockState(new BlockPos(castlePosX + 28, castlePosY + 4 + i, castlePosZ + j), Blocks.TORCH.getDefaultState());
                }
            }
        }

        for (int i = 11; i < 15; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                world.setBlockToAir(new BlockPos(castlePosX + i, castlePosY + j, castlePosZ - 5));

                world.setBlockToAir(new BlockPos(castlePosX + i, castlePosY + j, castlePosZ + 29));
            }
        }

        for (int i = 11; i < 15; i++)
        {
            for (int j = 0; j < 6; j++)
            {
                for (int k = 0; k < 4; k++)
                {
                    world.setBlockState(new BlockPos(castlePosX + i, castlePosY - 1, castlePosZ - 6 - j), Blocks.PLANKS.getDefaultState());

                    world.setBlockState(new BlockPos(castlePosX + i, castlePosY - 1, castlePosZ + 30 + j), Blocks.PLANKS.getDefaultState());
                }
            }
        }

        world.setBlockState(new BlockPos(castlePosX + 9, castlePosY + 5, castlePosZ + 30), Blocks.TORCH.getDefaultState());

        world.setBlockState(new BlockPos(castlePosX + 16, castlePosY + 5, castlePosZ + 30), Blocks.TORCH.getDefaultState());

        world.setBlockState(new BlockPos(castlePosX + 9, castlePosY + 5, castlePosZ - 6), Blocks.TORCH.getDefaultState());

        world.setBlockState(new BlockPos(castlePosX + 16, castlePosY + 5, castlePosZ - 6), Blocks.TORCH.getDefaultState());

        maxI = castleHeight * 7;

        for (int i = 0; i < maxI; i += 7)
        {
            for (int j = 0; j < 2; j++)
            {
                world.setBlockState(new BlockPos(castlePosX + 17, castlePosY + i, castlePosZ - 1 + j), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                world.setBlockState(new BlockPos(castlePosX + 5, castlePosY + i, castlePosZ - 1 + j), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                world.setBlockState(new BlockPos(castlePosX + 17, castlePosY + i, castlePosZ + 24 + j), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                world.setBlockState(new BlockPos(castlePosX + 5, castlePosY + i, castlePosZ + 24 + j), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));

                world.setBlockState(new BlockPos(castlePosX + 19, castlePosY + i, castlePosZ - 1 + j), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));

                world.setBlockState(new BlockPos(castlePosX + 7, castlePosY + i, castlePosZ - 1 + j), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));

                world.setBlockState(new BlockPos(castlePosX + 19, castlePosY + i, castlePosZ + 24 + j), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));

                world.setBlockState(new BlockPos(castlePosX + 7, castlePosY + i, castlePosZ + 24 + j), Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST));
            }
        }

        for (int i = 0; i < maxI; i += 7)
        {
            for (int j = 0; j < 7; j++)
            {
                for (int k = 0; k < 9; k++)
                {
                    world.setBlockState(new BlockPos(castlePosX + 9 + j, castlePosY + i, castlePosZ + 16 - k), Blocks.STONE_SLAB.getDefaultState());
                }
            }
        }

        for (int i = 0; i < maxI; i += 7)
        {
            for (int j = 0; j < 9; j += 3)
            {
                world.setBlockState(new BlockPos(castlePosX + 9 + j, castlePosY + i, castlePosZ + 16), Blocks.DOUBLE_STONE_SLAB.getDefaultState());

                world.setBlockState(new BlockPos(castlePosX + 9 + j, castlePosY + i + 1, castlePosZ + 16), Blocks.DOUBLE_STONE_SLAB.getDefaultState());

                world.setBlockState(new BlockPos(castlePosX + 9 + j, castlePosY + i + 2, castlePosZ + 16), Blocks.TORCH.getDefaultState());

                world.setBlockState(new BlockPos(castlePosX + 9 + j, castlePosY + i, castlePosZ + 8), Blocks.DOUBLE_STONE_SLAB.getDefaultState());

                world.setBlockState(new BlockPos(castlePosX + 9 + j, castlePosY + i + 1, castlePosZ + 8), Blocks.DOUBLE_STONE_SLAB.getDefaultState());

                world.setBlockState(new BlockPos(castlePosX + 9 + j, castlePosY + i + 2, castlePosZ + 8), Blocks.TORCH.getDefaultState());
            }
        }

        boolean gemFlag = false;

        int gemRand = rand.nextInt(castleHeight) + 1;

        for (int i = 0; i < maxI; i += 7)
        {
            world.setBlockState(new BlockPos(castlePosX + 12, castlePosY + i, castlePosZ + 13), Blocks.DOUBLE_STONE_SLAB.getDefaultState());

            world.setBlockState(new BlockPos(castlePosX + 12, castlePosY + i, castlePosZ + 12), Blocks.DOUBLE_STONE_SLAB.getDefaultState());

            world.setBlockState(new BlockPos(castlePosX + 12, castlePosY + i + 1, castlePosZ + 12), Blocks.CHEST.getDefaultState());

            BlockPos chestPos = new BlockPos(castlePosX + 12, castlePosY + i + 1, castlePosZ + 13);

            world.setBlockState(chestPos, Blocks.CHEST.getDefaultState());

            TileEntityChest chest = new TileEntityChest();

            world.setTileEntity(chestPos, chest);

            int maxJ = rand.nextInt(20);

            for (int j = 0; j < maxJ; j++)
            {
                ItemStack itemStack = populateChest(floor, rand);

                if (itemStack != null)
                {
                    chest.setInventorySlotContents(rand.nextInt(chest.getSizeInventory()), itemStack);
                }

                if (!gemFlag && gemRand == (i / 7))
                {
                    chest.setInventorySlotContents(rand.nextInt(chest.getSizeInventory()), new ItemStack(CreepsItemHandler.fireGem));

                    gemFlag = true;
                }
            }
        }

        EntityCastleKing king = new EntityCastleKing(world);

        king.setLocationAndAngles(castlePosX + 15, castlePosY + (castleHeight * 7), castlePosZ + 12, world.rand.nextFloat() * 360.0f, 0.0f);

        king.determineBaseTexture();

        king.setInitialHealth();

        king.setNoDespawn(true);

        world.spawnEntity(king);

        return true;
    }

    private boolean mobExists(String mobClass)
    {
        return false;
    }

    private ResourceLocation populateSpawner(Random rand)
    {
        if (topFloor == 1)
        {
            return EntityList.getKey(EntityCastleGuard.class);
        }

        int i = rand.nextInt(10);

        switch (floor)
        {
            case 0:
                if (i < 5)
                {
                    return EntityList.getKey(EntityCastleCritter.class);
                }

                return EntityList.getKey(EntitySkeleton.class);
            case 1:
                if (i < 5)
                {
                    return EntityList.getKey(EntityCastleCritter.class);
                }

                return EntityList.getKey(EntityCastleGuard.class);
            case 2:
                if (i > 5)
                {
                    return EntityList.getKey(EntityMummy.class);
                }
                else if (i < 5)
                {
                    return EntityList.getKey(EntityCastleCritter.class);
                }

                return EntityList.getKey(EntityCastleGuard.class);
            case 3:
                if (i < 5)
                {
                    return EntityList.getKey(EntityCastleCritter.class);
                }

                return EntityList.getKey(EntitySkeleton.class);
            case 4:
                if (i < 5)
                {
                    return EntityList.getKey(EntitySpider.class);
                }

                return EntityList.getKey(EntitySkeleton.class);
            case 5:
                if (i < 5)
                {
                    return EntityList.getKey(EntityCastleCritter.class);
                }

                return EntityList.getKey(EntityCastleGuard.class);
            default:
                break;
        }

        return EntityList.getKey(EntityCastleGuard.class);
    }

    private void buildTower(World world, Random rand, int castleX, int castleY, int castleZ, int castlePosX, int castlePosY, int castlePosZ, boolean backTower, ItemStack gemType, int gemFloor)
    {
        int topY = castlePosY - 13;

        int l = rand.nextInt(3);

        boolean gemFlag = false;

        int maxI = castleHeight + 1;

        floor = 1;

        topFloor = 0;

        for (int i = 0; i < maxI; i++)
        {
            topY += 7;

            if (i == castleHeight)
            {
                topFloor = 1;
            }

            for (int k = 0; k < 7; k++)
            {
                if (topY == (castlePosY - 6) && k < 4)
                {
                    k = 4;
                }

                for (int j = -7; j < 7; j++)
                {
                    for (int q = -7; q < 7; q++)
                    {
                        int x = j + castlePosX;

                        int y = k + topY;

                        int z = q + castlePosZ;

                        BlockPos blockPos = new BlockPos(x, y, z);

                        if (q == -7)
                        {
                            if (j > -5 && j < 4)
                            {
                                world.setBlockState(blockPos, cobbler(l, rand));
                            }
                        }
                        else if (q == -6 || q == -5)
                        {
                            if (j == -5 || j == 4)
                            {
                                world.setBlockState(blockPos, cobbler(l, rand));
                            }
                            else if (q == -6)
                            {
                                if (j == (((k + 1) % 7) - 3))
                                {
                                    if (k == 6 && topFloor == 1)
                                    {
                                        world.setBlockState(blockPos, cobbler(l, rand));
                                    }
                                    else
                                    {
                                        world.setBlockState(blockPos, Blocks.STONE_STAIRS.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST));
                                    }

                                    if (k == 5)
                                    {
                                        world.setBlockState(new BlockPos(x - 7, y, z), Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                                    }
                                }
                                else if (j < 4 && j > -5)
                                {
                                    world.setBlockToAir(blockPos);
                                }
                            }
                            else if (q == -5 && j > -5 && j < 5)
                            {
                                if ((k != 0 && k != 6) || (j != -4 && j != 3))
                                {
                                    if (k == 5 && (j == 3 || j == -4))
                                    {
                                        world.setBlockState(blockPos, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                                    }
                                    else
                                    {
                                        world.setBlockState(blockPos, cobbler(l, rand));
                                    }
                                }
                                else
                                {
                                    world.setBlockToAir(blockPos);
                                }
                            }
                        }
                        else if (q == -4 || q == -3 || q == 2 || q == 3)
                        {
                            if (j == -6 || j == 5)
                            {
                                world.setBlockState(blockPos, cobbler(l, rand));
                            }
                            else if (j > -6 && j < 5)
                            {
                                if (k == 5)
                                {
                                    world.setBlockState(blockPos, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                                }
                                else if (world.getBlockState(blockPos).getBlock() != Blocks.CHEST)
                                {
                                    world.setBlockToAir(blockPos);
                                }
                            }
                        }
                        else if (q > -3 && q < 2)
                        {
                            if (j == -7 || j == 6)
                            {
                                if (k < 0 || k > 3 || (j != -7 && j != 6) || (q != -1 && q != 0))
                                {
                                    world.setBlockState(blockPos, cobbler(l, rand));
                                }
                                else if ((j == -7 && backTower) || (j == 6 && !backTower))
                                {
                                    world.setBlockState(blockPos, Blocks.GLASS.getDefaultState());
                                }
                            }
                            else if (j > -7 && j < 6)
                            {
                                if (k == 5)
                                {
                                    world.setBlockState(blockPos, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                                }
                                else
                                {
                                    world.setBlockToAir(blockPos);
                                }
                            }
                        }
                        else if (q == 4)
                        {
                            if (j == -5 || j == 4)
                            {
                                world.setBlockState(blockPos, cobbler(l, rand));
                            }
                            else if (j > -5 && j < 4)
                            {
                                if (k == 5)
                                {
                                    world.setBlockState(blockPos, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                                }
                                else
                                {
                                    world.setBlockToAir(blockPos);
                                }
                            }
                        }
                        else if (q == 5)
                        {
                            if (j == -4 || j == -3 || j == 2 || j == 3)
                            {
                                world.setBlockState(blockPos, cobbler(l, rand));
                            }
                            else if (j > -3 && j < 2)
                            {
                                if (k == 5)
                                {
                                    world.setBlockState(blockPos, Blocks.DOUBLE_STONE_SLAB.getDefaultState());
                                }
                                else
                                {
                                    world.setBlockState(blockPos, cobbler(l, rand));
                                }
                            }
                        }
                        else if (q == 6 && j > -3 && j < 2)
                        {
                            world.setBlockState(blockPos, cobbler(l, rand));
                        }
                    }
                }
            }

            if (floor == 2)
            {
                world.setBlockState(new BlockPos(castlePosX + 3, topY, castlePosZ - 5), cobbler(l, rand));

                world.setBlockState(new BlockPos(castlePosX + 3, topY - 1, castlePosZ - 5), cobbler(l, rand));
            }

            if (topFloor != 1)
            {
                if (rand.nextInt(5) == 0)
                {
                    BlockPos blockPos = new BlockPos(castlePosX + 2, topY + 6, castlePosZ + 2);

                    world.setBlockState(blockPos, Blocks.MOB_SPAWNER.getDefaultState());

                    TileEntityMobSpawner mobSpawner = new TileEntityMobSpawner();

                    mobSpawner.getSpawnerBaseLogic().setEntityId(populateSpawner(rand));

                    world.setTileEntity(blockPos, mobSpawner);
                }

                BlockPos blockPos = new BlockPos(castlePosX - 3, topY + 6, castlePosZ + 2);

                world.setBlockState(blockPos, Blocks.MOB_SPAWNER.getDefaultState());

                TileEntityMobSpawner mobSpawner = new TileEntityMobSpawner();

                mobSpawner.getSpawnerBaseLogic().setEntityId(populateSpawner(rand));

                world.setTileEntity(blockPos, mobSpawner);

                world.setBlockState(new BlockPos(castlePosX, topY + 6, castlePosZ - 3), Blocks.DOUBLE_STONE_SLAB.getDefaultState());

                world.setBlockState(new BlockPos(castlePosX - 1, topY + 6, castlePosZ - 3), Blocks.DOUBLE_STONE_SLAB.getDefaultState());
            }

            if ((topY + 56) >= 120 && floor == 1)
            {
                floor = 2;
            }

            if (topFloor != 1)
            {
                for (int k = 0; k < 2; k++)
                {
                    BlockPos blockPos1 = new BlockPos(castlePosX - k, topY + 7, castlePosZ - 3);

                    world.setBlockState(blockPos1, Blocks.CHEST.getDefaultState());

                    TileEntityChest chest = new TileEntityChest();

                    world.setTileEntity(blockPos1, chest);

                    int maxJ = 1 + l + k;

                    for (int j = 0; j < maxJ; j++)
                    {
                        ItemStack itemStack = populateChest(floor, rand);

                        if (itemStack != null)
                        {
                            chest.setInventorySlotContents(rand.nextInt(chest.getSizeInventory()), itemStack);
                        }
                    }

                    if (gemFloor == floor && !gemFlag)
                    {
                        chest.setInventorySlotContents(0, gemType);

                        gemFlag = true;
                    }
                }
            }

            world.setBlockState(new BlockPos(castlePosX + 3, topY, castlePosZ - 6), Blocks.TORCH.getDefaultState());

            world.setBlockState(new BlockPos(castlePosX - 4, topY, castlePosZ - 6), Blocks.TORCH.getDefaultState());

            world.setBlockState(new BlockPos(castlePosX + 1, topY, castlePosZ - 4), Blocks.TORCH.getDefaultState());

            world.setBlockState(new BlockPos(castlePosX - 2, topY, castlePosZ - 4), Blocks.TORCH.getDefaultState());

            if (topFloor != 1)
            {
                int maxK = (floor * 4) + l - 8;

                for (int k = 0; k < maxK; k++)
                {
                    int x = 5 - rand.nextInt(12);

                    int y = topY + 5;

                    int z = 5 - rand.nextInt(10);

                    if (z >= -2 || x >= 4 || x <= -5 || x == 1 || x == -2)
                    {
                        x += castlePosX;

                        z += castlePosZ;

                        BlockPos blockPos = new BlockPos(x, y, z);

                        if (world.getBlockState(blockPos).getBlock() == Blocks.DOUBLE_STONE_SLAB && world.getBlockState(new BlockPos(x, y + 1, z)).getBlock() != Blocks.MOB_SPAWNER)
                        {
                            world.setBlockToAir(blockPos);
                        }
                    }
                }

                floor++;
            }
        }
    }

    private IBlockState cobbler(int i, Random rand)
    {
        if (i == 1 && rand.nextInt(3) > 0)
        {
            return Blocks.MOSSY_COBBLESTONE.getDefaultState();
        }

        return Blocks.COBBLESTONE.getDefaultState();
    }

    private ItemStack populateChest(int i, Random rand)
    {
        int j = rand.nextInt(8 * i);

        if (j > 40)
        {
            j = 40;
        }

        switch (j)
        {
            case 1:
                return new ItemStack(Items.WHEAT, rand.nextInt(12) + 3);
            case 2:
                return new ItemStack(Items.REEDS, rand.nextInt(6) + 6);
            case 3:
                return new ItemStack(Items.COOKIE, rand.nextInt(6) + 6);
            case 4:
                return new ItemStack(Items.ARROW, rand.nextInt(30) + 10);
            case 5:
                return new ItemStack(CreepsItemHandler.money, rand.nextInt(4) + 1);
            case 6:
                return new ItemStack(CreepsItemHandler.evilEgg, rand.nextInt(4) + 1);
            case 7:
                return new ItemStack(Items.LEATHER, 1);
            case 8:
                return new ItemStack(Items.PAPER, 1);
            case 9:
                return new ItemStack(Items.APPLE, 1);
            case 10:
                return new ItemStack(Items.WOODEN_AXE, 1);
            case 11:
                return new ItemStack(Items.BOW, 1);
            case 12:
                return new ItemStack(CreepsItemHandler.bandaid, rand.nextInt(15) + 1);
            case 13:
                return new ItemStack(CreepsItemHandler.blorpCola, rand.nextInt(10) + 5);
            case 14:
                return new ItemStack(Items.SIGN, 1);
            case 15:
                return new ItemStack(Items.WHEAT, rand.nextInt(10) + 5);
            case 16:
                return new ItemStack(Items.BREAD, 1);
            case 17:
                return new ItemStack(Items.IRON_PICKAXE, 1);
            case 18:
                return new ItemStack(Items.IRON_AXE, 1);
            case 19:
                return new ItemStack(Items.BUCKET, 1);
            case 20:
                return new ItemStack(Items.IRON_SHOVEL, 1);
            case 21:
                return new ItemStack(CreepsItemHandler.evilEgg, rand.nextInt(15) + 1);
            case 22:
                return new ItemStack(CreepsItemHandler.gooDonut, rand.nextInt(15) + 1);
            case 23:
                return new ItemStack(CreepsItemHandler.money, rand.nextInt(10) + 1);
            case 24:
                return new ItemStack(Items.WATER_BUCKET, 1);
            case 25:
                return new ItemStack(CreepsItemHandler.frisbee, 1);
            case 26:
                return new ItemStack(Items.CAKE, 1);
            case 27:
                return new ItemStack(CreepsItemHandler.money, rand.nextInt(10) + 5);
            case 28:
                return new ItemStack(Items.MILK_BUCKET, 1);
            case 29:
                return new ItemStack(CreepsItemHandler.lolly, rand.nextInt(4) + 1);
            case 30:
                return new ItemStack(CreepsItemHandler.money, rand.nextInt(24) + 1);
            case 32:
                return new ItemStack(Items.DIAMOND, 1);
            case 33:
                return new ItemStack(Items.GOLDEN_HELMET, 1);
            case 34:
                return new ItemStack(Items.DIAMOND_HELMET, 1);
            case 35:
                return new ItemStack(Items.GOLDEN_BOOTS, 1);
            case 36:
                return new ItemStack(CreepsItemHandler.shrinkRay, 1);
            case 37:
                return new ItemStack(CreepsItemHandler.horseHeadGem, 1);
            case 38:
                return new ItemStack(Items.DIAMOND, 1);
            case 39:
                return new ItemStack(Items.GOLDEN_APPLE, 1);
            case 40:
                return new ItemStack(CreepsItemHandler.money, rand.nextInt(49) + 1);
            default:
                break;
        }

        return null;
    }
}

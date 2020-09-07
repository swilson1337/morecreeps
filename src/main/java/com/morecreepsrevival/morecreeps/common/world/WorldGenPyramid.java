package com.morecreepsrevival.morecreeps.common.world;

import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.entity.EntityBabyMummy;
import com.morecreepsrevival.morecreeps.common.entity.EntityMummy;
import com.morecreepsrevival.morecreeps.common.entity.EntityPyramidGuardian;
import com.morecreepsrevival.morecreeps.common.items.CreepsItemHandler;
import com.morecreepsrevival.morecreeps.common.entity.EntityBlackSoul;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;

import javax.annotation.Nonnull;
import java.util.Objects;
import java.util.Random;

public class WorldGenPyramid extends WorldGenerator
{
    private static final int rows = 35;

    private static final int columns = 35;

    private static final int maxObstruct = 100;

    private static final int wallCode = 7;

    private static final int pathCode = 15;

    private static final int emptyCode = 0;

    private static final int visitedCode = 4;

    private int[][] maze;

    public WorldGenPyramid()
    {
        maze = new int[rows + 1][columns + 1];
    }

    @Override
    public boolean generate(@Nonnull World world, @Nonnull Random rand, @Nonnull BlockPos pos)
    {
        Biome biome = world.getBiome(pos);

        if (!((MoreCreepsConfig.spawnInNonVanillaBiomes && MoreCreepsConfig.hasBiome(Objects.requireNonNull(biome.getRegistryName()).toString())) || Objects.requireNonNull(biome.getRegistryName()).getResourceDomain().equals("minecraft")))
        {
            return false;
        }

        for (BiomeDictionary.Type type : BiomeDictionary.getTypes(biome))
        {
            if (type == BiomeDictionary.Type.NETHER || type == BiomeDictionary.Type.END)
            {
                return false;
            }
        }

        if (!BiomeDictionary.hasType(biome, BiomeDictionary.Type.SANDY))
        {
            return false;
        }

        int x = pos.getX();

        int y = pos.getY();

        int z = pos.getZ();

        int area = 0;

        int count = 0;

        while (count < 20)
        {
            int iMax = rows - count + 2;

            for (int i = (-2 + count); i < iMax; i += 2)
            {
                int jMax = columns - count + 2;

                for (int j = (-2 + count); j < jMax; j += 2)
                {
                    if (!world.isAirBlock(new BlockPos(x + i, y + count, z + j)))
                    {
                        area++;

                        if (area > maxObstruct)
                        {
                            return false;
                        }
                    }
                }
            }

            count += 2;
        }

        if (!world.isBlockLoaded(pos) || !world.isBlockLoaded(new BlockPos(x + 35, y, z)) || !world.isBlockLoaded(new BlockPos(x, y, z + 35)) || !world.isBlockLoaded(new BlockPos(x + 35, y, z + 35)) || world.getBlockState(new BlockPos(x + rand.nextInt(16), y - 2, z + rand.nextInt(16))).getBlock() != Blocks.SAND)
        {
            return false;
        }

        makeMaze();

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                maze[33 - i][33 - j] = 0;
            }
        }

        for (int i = -2; i < 21; i++)
        {
            int rowCount = (rows - i) + 2;

            for (int j = (-2 + i); j < rowCount; j++)
            {
                int columnCount = (columns - i) + 2;

                for (int q = (-2 + i); q < columnCount; q++)
                {
                    world.setBlockState(new BlockPos(x + j, y + i, z + q), Blocks.SANDSTONE.getDefaultState());
                }
            }
        }

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                world.setBlockState(new BlockPos(x + i, y, z + j), Block.getBlockById(maze[i][j]).getDefaultState());

                world.setBlockState(new BlockPos(x + i, y - 1, z + j), Block.getBlockById(maze[i][j]).getDefaultState());

                world.setBlockState(new BlockPos(x + i, y - 2, z + j), Blocks.BEDROCK.getDefaultState());
            }
        }

        for (int i = 0; i < 30; i++)
        {
            int i2 = rand.nextInt(rows - 6) + 3;

            int i3 = rand.nextInt(columns - 6) + 3;

            if (maze[i2][i3] == 7)
            {
                world.setBlockState(new BlockPos(x + i2, y, z + 16), Blocks.GLASS.getDefaultState());
            }
        }

        for (int i = 0; i < 15; i++)
        {
            int i2 = rand.nextInt(rows - 6) + 3;

            int i3 = rand.nextInt(columns - 6) + 3;

            if (maze[i2][i3] == 7)
            {
                world.setBlockState(new BlockPos(x + i2, y - 1, z + i3), Blocks.SANDSTONE.getDefaultState());

                world.setBlockState(new BlockPos(x + i2, y, z + i3), Blocks.SANDSTONE.getDefaultState());
            }
        }

        for (int i = 0; i < 20; i++)
        {
            int i2 = rand.nextInt(rows - 3) + 3;

            int i3 = rand.nextInt(columns - 3) + 3;

            if (maze[i2][i3] != 0)
            {
                continue;
            }

            world.setBlockState(new BlockPos(x + i2, y - 1, z + i3), Block.getBlockById(30).getDefaultState());

            if (rand.nextInt(4) == 0)
            {
                world.setBlockState(new BlockPos(x + i2, y, z + i3), Blocks.WEB.getDefaultState());
            }
        }

        for (int i = 0; i < 30; i++)
        {
            int i2 = rand.nextInt(rows - 6) + 3;

            int i3 = rand.nextInt(columns - 6) + 3;

            if (maze[i2][i3] == 7)
            {
                world.setBlockState(new BlockPos(x + i2, y, z + i3), Blocks.TORCH.getDefaultState());
            }
        }

        int mobCounter = 0;

        while (mobCounter < 6)
        {
            int i = rand.nextInt(rows - 3) + 1;

            int i2 = rand.nextInt(columns - 3) + 1;

            if (maze[i][i2] == wallCode)
            {
                mobCounter++;

                world.setBlockToAir(new BlockPos(x + i, y - 1, z + i2));

                world.setBlockToAir(new BlockPos(x + i, y, z + i2));

                world.setBlockState(new BlockPos(x + i, y - 1, z + i2), Blocks.MOB_SPAWNER.getDefaultState());

                TileEntityMobSpawner mobSpawner = new TileEntityMobSpawner();

                world.setTileEntity(new BlockPos(x + i, y - 1, z + i2), mobSpawner);

                switch (rand.nextInt(5))
                {
                    case 0:
                        mobSpawner.getSpawnerBaseLogic().setEntityId(EntityList.getKey(EntityBlackSoul.class));

                        break;
                    case 1:
                        mobSpawner.getSpawnerBaseLogic().setEntityId(EntityList.getKey(EntityBabyMummy.class));

                        break;
                    default:
                        mobSpawner.getSpawnerBaseLogic().setEntityId(EntityList.getKey(EntityMummy.class));

                        break;
                }
            }
        }

        world.setBlockToAir(new BlockPos(x + 1, y - 1, z));

        world.setBlockToAir(new BlockPos(x + 1, y, z));

        for (int i = 0; i < 5; i++)
        {
            world.setBlockToAir(new BlockPos(x + 1, y - 1, z - i));

            world.setBlockToAir(new BlockPos(x + 1, y, z - i));

            world.setBlockState(new BlockPos(x + 1, y, z - i), Blocks.TORCH.getDefaultState());
        }

        world.setBlockState(new BlockPos(x - 1, y, z - 5), Blocks.TORCH.getDefaultState());

        world.setBlockState(new BlockPos(x + 1, y, z - 5), Blocks.TORCH.getDefaultState());

        world.setBlockState(new BlockPos(x, y, z - 5), Blocks.TORCH.getDefaultState());

        for (int i = 1; i < 25; i++)
        {
            world.setBlockToAir(new BlockPos(x - 1, y + i, z - 5));

            world.setBlockToAir(new BlockPos(x + 1, y + i, z - 5));

            world.setBlockToAir(new BlockPos(x, y + i, z - 5));
        }

        world.setBlockState(new BlockPos(x, y + 26, z - 5), Blocks.TORCH.getDefaultState());

        EntityItem entityItem = new EntityItem(world, ((double)x + (double)(world.rand.nextFloat() * 0.7f) + (double)(1.0f - 0.7f) * 0.5d) - 2.0d, ((double)y + (double)(world.rand.nextFloat() * 0.7f) + (double)(1.0f - 0.7f) * 0.20000000000000001d + 0.59999999999999998d) - 2.0d, ((double)z + (double)(world.rand.nextFloat() * 0.7f) + (double)(1.0f - 0.7f) * 0.5d) - 2.0d, new ItemStack(Items.BONE, 5));

        world.spawnEntity(entityItem);

        world.setBlockState(new BlockPos(x + rows - 2, y - 1, z + columns - 2), Block.getBlockById(54).getDefaultState());

        TileEntityChest chest = new TileEntityChest();

        world.setTileEntity(new BlockPos(x + rows - 2, y - 1, z + columns - 2), chest);

        for (int i = 0; i < chest.getSizeInventory(); i++)
        {
            switch (rand.nextInt(50))
            {
                case 0:
                    chest.setInventorySlotContents(i, new ItemStack(CreepsItemHandler.gooDonut, rand.nextInt(15) + 1));

                    break;
                case 1:
                    chest.setInventorySlotContents(i, new ItemStack(CreepsItemHandler.bandaid, rand.nextInt(15) + 1));

                    break;
                case 2:
                    chest.setInventorySlotContents(i, new ItemStack(CreepsItemHandler.raygun, 1));

                    break;
                case 3:
                    chest.setInventorySlotContents(i, new ItemStack(CreepsItemHandler.money, rand.nextInt(15) + 1));

                    break;
                case 4:
                    chest.setInventorySlotContents(i, new ItemStack(CreepsItemHandler.blorpCola, rand.nextInt(10) + 5));

                    break;
                case 5:
                    chest.setInventorySlotContents(i, new ItemStack(Items.BREAD, 1));

                    break;
                case 6:
                    chest.setInventorySlotContents(i, new ItemStack(Items.GOLDEN_APPLE, 1));

                    break;
                case 7:
                    chest.setInventorySlotContents(i, new ItemStack(Items.GOLD_INGOT, rand.nextInt(3) + 2));

                    break;
                case 8:
                    chest.setInventorySlotContents(i, new ItemStack(Items.IRON_INGOT, rand.nextInt(5) + 2));

                    break;
                case 9:
                    chest.setInventorySlotContents(i, new ItemStack(Items.GUNPOWDER, rand.nextInt(4) + 2));

                    break;
                case 10:
                    chest.setInventorySlotContents(i, new ItemStack(Items.EGG, rand.nextInt(3) + 1));

                    break;
                case 11:
                    chest.setInventorySlotContents(i, new ItemStack(Items.WHEAT, rand.nextInt(12) + 2));

                    break;
                case 12:
                    chest.setInventorySlotContents(i, new ItemStack(CreepsItemHandler.evilEgg, rand.nextInt(15) + 1));

                    break;
                case 13:
                    chest.setInventorySlotContents(i, new ItemStack(Items.DIAMOND, rand.nextInt(2) + 1));

                    break;
                case 14:
                    chest.setInventorySlotContents(i, new ItemStack(Items.IRON_SWORD, 1));

                    break;
                case 15:
                    chest.setInventorySlotContents(i, new ItemStack(Items.DIAMOND_SWORD, 1));

                    break;
                case 16:
                    chest.setInventorySlotContents(i, new ItemStack(Items.BONE, 1));

                    break;
                case 17:
                    chest.setInventorySlotContents(i, new ItemStack(Items.ARROW, rand.nextInt(30) + 10));

                    break;
                case 18:
                    chest.setInventorySlotContents(i, new ItemStack(Items.STICK, 1));

                    break;
                case 19:
                    chest.setInventorySlotContents(i, new ItemStack(Items.MILK_BUCKET, 1));

                    break;
                case 20:
                    chest.setInventorySlotContents(i, new ItemStack(Items.REEDS, rand.nextInt(6) + 6));

                    break;
                case 21:
                    chest.setInventorySlotContents(i, new ItemStack(Items.PAPER, rand.nextInt(16) + 6));

                    break;
                case 22:
                    chest.setInventorySlotContents(i, new ItemStack(Items.BOOK, 1));

                    break;
                case 23:
                    chest.setInventorySlotContents(i, new ItemStack(Items.COOKIE, rand.nextInt(6) + 6));

                    break;
                case 24:
                    chest.setInventorySlotContents(i, new ItemStack(Items.CAKE, rand.nextInt(6) + 6));

                    break;
                case 25:
                    chest.setInventorySlotContents(i, new ItemStack(Items.BUCKET, rand.nextInt(6) + 6));

                    break;
                case 26:
                    chest.setInventorySlotContents(i, new ItemStack(CreepsItemHandler.evilEgg, rand.nextInt(40) + 1));

                    break;
                default:
                    break;
            }
        }

        EntityPyramidGuardian guardian = new EntityPyramidGuardian(world);

        guardian.setLocationAndAngles(x + rows - 2, y - 1, z + columns - 3, 300.0f, 0.0f);

        guardian.determineBaseTexture();

        guardian.setInitialHealth();

        guardian.setNoDespawn(true);

        world.spawnEntity(guardian);

        for (int i = 0; i < rows; i++)
        {
            for (int q = 0; q < columns; q++)
            {
                world.setBlockState(new BlockPos(x + i, y + 1, z + q), Block.getBlockById(7).getDefaultState());
            }
        }

        return true;
    }

    private void makeMaze()
    {
        int maze1 = 0;

        int maze2 = 0;

        int[] ai = new int[(rows * columns) / 2];

        int[] ai2 = new int[(rows * columns) / 2];

        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                maze[i][j] = wallCode;
            }
        }

        int rowsMax = rows - 1;

        int columnsMax = columns - 1;

        for (int i = 1; i < rowsMax; i += 2)
        {
            for (int j = 1; j < columnsMax; j += 2)
            {
                maze1++;

                maze[i][j] = -maze1;

                if (i < (rows - 2))
                {
                    ai[maze2] = i + 1;

                    ai2[maze2] = j;

                    maze2++;
                }

                if (j < (columns - 2))
                {
                    ai[maze2] = i;

                    ai2[maze2] = j + 1;

                    maze2++;
                }
            }
        }

        for (int i = (maze2 - 1); i > 0; i--)
        {
            int j = (int)(Math.random() * (double)i);

            if ((ai[j] % 2) == 1 && maze[ai[j]][ai2[j] - 1] != maze[ai[j]][ai2[j] + 1])
            {
                fill(ai[j], ai2[j] - 1, maze[ai[j]][ai2[j] - 1], maze[ai[j]][ai2[j] + 1]);

                maze[ai[j]][ai2[j]] = maze[ai[j]][ai2[j] + 1];
            }
            else if ((ai[j] % 2) == 0 && maze[ai[j] - 1][ai2[j]] != maze[ai[j] + 1][ai2[j]])
            {
                fill(ai[j] - 1, ai2[j], maze[ai[j] - 1][ai2[j]], maze[ai[j] + 1][ai2[j]]);

                maze[ai[j]][ai2[j]] = maze[ai[j] + 1][ai2[j]];
            }

            ai[j] = ai[i];

            ai2[j] = ai2[i];
        }

        for (int i = 0; i < rowsMax; i++)
        {
            for (int j = 1; j < columnsMax; j++)
            {
                if (maze[i][j] < 0)
                {
                    maze[i][j] = emptyCode;
                }
            }
        }
    }

    private void fill(int i, int x, int y, int z)
    {
        if (i < 0)
        {
            i = 0;
        }

        if (x < 0)
        {
            x = 0;
        }

        if (i > rows)
        {
            i = rows;
        }

        if (x > columns)
        {
            x = columns;
        }

        if (maze[i][x] == y)
        {
            maze[i][x] = z;

            fill(i + 1, x, y, z);

            fill(i - 1, x, y, z);

            fill(i, x + 1, y, z);

            fill(i, x - 1, y, z);
        }
    }
}

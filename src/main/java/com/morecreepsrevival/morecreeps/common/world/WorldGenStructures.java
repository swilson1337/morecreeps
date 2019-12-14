package com.morecreepsrevival.morecreeps.common.world;

import com.morecreepsrevival.morecreeps.common.config.MoreCreepsConfig;
import com.morecreepsrevival.morecreeps.common.networking.CreepsPacketHandler;
import com.morecreepsrevival.morecreeps.common.networking.message.MessagePlayBattleCastleSound;
import com.morecreepsrevival.morecreeps.common.networking.message.MessagePlayPyramidDiscoveredSound;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenStructures implements IWorldGenerator
{
    private int pyramidCount = 0;

    private int castleCount = 0;

    @Override
    public void generate(Random rand, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        generateStructures(world, rand, chunkX, chunkZ);
    }

    private void generateStructures(World world, Random rand, int chunkX, int chunkZ)
    {
        if (MoreCreepsConfig.pyramidGen)
        {
            if (pyramidCount >= (1100 - (MoreCreepsConfig.pyramidRarity * 100) + 100))
            {
                if (rand.nextInt(30) == 0)
                {
                    BlockPos pos = new BlockPos((chunkX << 4) + rand.nextInt(16) + 16, 65, (chunkZ << 4) + rand.nextInt(16) + 16);

                    if ((new WorldGenPyramid()).generate(world, rand, pos))
                    {
                        pyramidCount = 0;

                        for (EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, (new AxisAlignedBB(pos)).grow(256.0d, 256.0d, 256.0d)))
                        {
                            CreepsPacketHandler.INSTANCE.sendTo(new MessagePlayPyramidDiscoveredSound(), (EntityPlayerMP)player);
                        }
                    }
                }
            }
            else
            {
                pyramidCount++;
            }
        }

        if (MoreCreepsConfig.castleGen)
        {
            if (castleCount >= (1100 - (MoreCreepsConfig.castleRarity * 100) + 1300))
            {
                if (rand.nextInt(30) == 0)
                {
                    BlockPos pos = new BlockPos((chunkX << 4) + rand.nextInt(16) + 16, rand.nextInt(40) + 80, (chunkZ << 4) + rand.nextInt(16) + 16);

                    if ((new WorldGenCastle()).generate(world, rand, pos))
                    {
                        castleCount = 0;

                        for (EntityPlayer player : world.getEntitiesWithinAABB(EntityPlayer.class, (new AxisAlignedBB(pos)).grow(256.0d, 256.0d, 256.0d)))
                        {
                            CreepsPacketHandler.INSTANCE.sendTo(new MessagePlayBattleCastleSound(), (EntityPlayerMP)player);
                        }
                    }
                }
            }
            else
            {
                castleCount++;
            }
        }
    }
}
package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.BlockBed;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.NodeProcessor;
import net.minecraft.tileentity.TileEntityBed;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityPyramidGuardian extends EntityCreepBase
{
    public EntityPyramidGuardian(World world)
    {
        super(world);

        setCreepTypeName("Pyramid Guardian");

        setSize(0.4f, 0.4f);

        creatureType = EnumCreatureType.MONSTER;

        baseSpeed = 0.0d;

        baseHealth = 15.0f;

        updateAttributes();
    }

    @Override
    public void initEntityAI()
    {
        clearAITasks();

        NodeProcessor nodeProcessor = getNavigator().getNodeProcessor();

        nodeProcessor.setCanSwim(true);

        tasks.addTask(0, new EntityAISwimming(this));

        tasks.addTask(1, new EntityAIAttackMelee(this, 0.4d, true));

        targetTasks.addTask(0, new EntityAINearestAttackableTarget<>(this, EntityPlayer.class, false, true));
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return CreepsSoundHandler.pyramidSound;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource)
    {
        return CreepsSoundHandler.pyramidHurtSound;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return CreepsSoundHandler.pyramidDeathSound;
    }

    @Override
    public void onDeath(DamageSource cause)
    {
        if (!dead && !world.isRemote)
        {
            int x = MathHelper.floor(posX);

            int y = MathHelper.floor(getEntityBoundingBox().minY);

            int z = MathHelper.floor(posZ);

            int columns = 35;

            int rows = 35;

            int bedrockCounter = 0;

            int alternate = 1;

            if (posY > 60.0d)
            {
                for (int i = -4; i < 6; i++)
                {
                    for (int j = -40; j < 40; j++)
                    {
                        for (int q = -40; q < 40; q++)
                        {
                            BlockPos blockPos = new BlockPos(x + j, y + i, z + q);

                            if (world.getBlockState(blockPos).getBlock() == Blocks.BEDROCK)
                            {
                                bedrockCounter++;

                                world.setBlockState(blockPos, Blocks.SANDSTONE.getDefaultState());
                            }
                        }
                    }
                }
            }

            if (bedrockCounter > 50)
            {
                for (int i = 3; i < 11; i++)
                {
                    for (int j = 9; j < 24; j++)
                    {
                        for (int q = 9; q < 25; q++)
                        {
                            world.setBlockToAir(new BlockPos(x - j, y + i, z - q));
                        }
                    }
                }

                world.setBlockState(new BlockPos(x - 2, y, z - 2), Blocks.SANDSTONE.getDefaultState());

                world.setBlockToAir(new BlockPos(x - 2, y + 1, z - 1));

                world.setBlockToAir(new BlockPos(x - 2, y + 1, z - 2));

                world.setBlockToAir(new BlockPos(x - 2, y + 2, z - 1));

                world.setBlockToAir(new BlockPos(x - 2, y + 2, z - 2));

                world.setBlockState(new BlockPos(x - 2, y + 1, z - 3), Blocks.SANDSTONE.getDefaultState());

                world.setBlockToAir(new BlockPos(x - 2, y + 2, z - 3));

                world.setBlockState(new BlockPos(x - 2, y + 2, z - 4), Blocks.SANDSTONE.getDefaultState());

                world.setBlockToAir(new BlockPos(x - 2, y + 3, z - 4));

                for (int i = 2; i < 18; i++)
                {
                    world.setBlockToAir(new BlockPos(x - 2, y + 3, z - i));

                    alternate *= -1;

                    if (alternate > 0)
                    {
                        world.setBlockState(new BlockPos(x - 2, y + 4, z - i), Blocks.TORCH.getDefaultState());
                    }
                    else
                    {
                        world.setBlockToAir(new BlockPos(x - 2, y + 4, z - i));
                    }
                }

                for (int i = 2; i < 20; i++)
                {
                    world.setBlockToAir(new BlockPos(x - i, y + 3, z - 17));

                    world.setBlockToAir(new BlockPos(x - i, y + 4, z - 17));
                }

                for (int i = 9; i < 24; i++)
                {
                    alternate *= -1;

                    if (alternate > 0)
                    {
                        world.setBlockState(new BlockPos(x - 8, y + 8, z - i), Blocks.TORCH.getDefaultState());

                        world.setBlockState(new BlockPos(x - 24, y + 8, z - i), Blocks.TORCH.getDefaultState());
                    }

                    world.setBlockState(new BlockPos(x - i, y + 8, z - 9), Blocks.TORCH.getDefaultState());

                    world.setBlockState(new BlockPos(x - i, y + 8, z - 24), Blocks.TORCH.getDefaultState());
                }

                int randInt = rand.nextInt(2) + 2;

                for (int i = 0; i < randInt; i++)
                {
                    EntityEvilCreature evilCreature = new EntityEvilCreature(world);

                    evilCreature.setLocationAndAngles(x - 15, y + 8, z - 10 - i, rotationYaw, 0.0f);

                    evilCreature.determineBaseTexture();

                    evilCreature.setInitialHealth();

                    evilCreature.setNoDespawn(true);

                    world.spawnEntity(evilCreature);
                }

                randInt = rand.nextInt(7) + 2;

                for (int i = 0; i < randInt; i++)
                {
                    EntityMummy mummy = new EntityMummy(world);

                    mummy.setLocationAndAngles(x - 15, y + 8, z - 13 - i, rotationYaw, 0.0f);

                    mummy.determineBaseTexture();

                    mummy.setInitialHealth();

                    mummy.setNoDespawn(true);

                    world.spawnEntity(mummy);
                }

                world.setBlockState(new BlockPos(x - 14, y + 3, z - 15), Blocks.GLOWSTONE.getDefaultState());

                world.setBlockState(new BlockPos(x - 16, y + 3, z - 15), Blocks.GLOWSTONE.getDefaultState());

                /*TileEntityBed bed = new TileEntityBed();

                world.setBlockState(new BlockPos(x - 15, y + 3, z - 15), Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.FOOT));

                world.setBlockState(new BlockPos(x - 15, y + 3, z - 14), Blocks.BED.getDefaultState().withProperty(BlockBed.PART, BlockBed.EnumPartType.HEAD));

                world.setTileEntity(new BlockPos(x - 15, y + 3, z - 14), bed);*/
            }
        }

        super.onDeath(cause);
    }

    @Override
    public void onLivingUpdate()
    {
        motionX = 0.0d;

        motionY = 0.0d;

        motionZ = 0.0d;

        super.onLivingUpdate();
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand)
    {
        playSound(CreepsSoundHandler.pyramidCurseSound, getSoundVolume(), getSoundPitch());

        return true;
    }

    @Override
    protected void updateTexture()
    {
        setTexture("textures/entity/pyramidguardian.png");
    }
}

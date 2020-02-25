package com.morecreepsrevival.morecreeps.common.entity;

import com.morecreepsrevival.morecreeps.common.sounds.CreepsSoundHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class EntityShrink extends EntityThrowable
{
    protected int hitX;

    protected int hitY;

    protected int hitZ;

    protected boolean aoLightValueZPos;

    public EntityLivingBase lightValueOwn;

    protected int aoLightValueScratchXYZNNP;

    protected int aoLightValueScratchXYNN;

    protected boolean aoLightValueScratchXYZNNN;

    protected boolean playerFire;

    protected float shrinkSize;

    protected int vibrate;

    protected IBlockState blockHit;

    public EntityShrink(World world)
    {
        super(world);

        setSize(0.0325f, 0.01125f);

        hitX = -1;

        hitY = -1;

        hitZ = -1;

        aoLightValueZPos = false;

        aoLightValueScratchXYNN = 0;

        playerFire = false;

        shrinkSize = 1.0f;

        vibrate = 1;
    }

    public EntityShrink(World world, EntityLivingBase entity)
    {
        this(world);

        setLocationAndAngles(entity.posX, entity.posY + (double)entity.getEyeHeight(), entity.posZ, entity.rotationYaw, entity.rotationPitch);

        posX -= MathHelper.cos((rotationYaw / 180.0f) * (float)Math.PI) * 0.16f;

        posY += 0.20000000149011612d;

        posZ -= MathHelper.sin((rotationYaw / 180.0f) * (float)Math.PI) * 0.16f;

        if (entity instanceof EntityPlayer)
        {
            posY -= 0.40000000596046448d;
        }

        setPosition(posX, posY, posZ);

        motionX = -MathHelper.sin((rotationYaw / 180.0f) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180.0f) * (float)Math.PI);

        motionZ = MathHelper.cos((rotationYaw / 180.0f) * (float)Math.PI) * MathHelper.cos((rotationPitch / 180.0f) * (float)Math.PI);

        motionY = -MathHelper.sin((rotationPitch / 180.0f) * (float)Math.PI);

        float f1 = 1.0f;

        if (entity instanceof EntityPlayer)
        {
            playerFire = true;

            float f2 = 0.3333333f;

            float f3 = f2 / 0.1f;

            if (f3 > 0.0f)
            {
                f1 = (float)((double)f1 * (1.0d + 2.0d / (double)f3));
            }
        }

        if (Math.abs(entity.motionX) > 0.10000000000000001d || Math.abs(entity.motionY) > 0.10000000000000001d || Math.abs(entity.motionZ) > 0.10000000000000001d)
        {
            f1 *= 2.0f;
        }

        adjustMotion(motionX, motionY, motionZ, (float)(2.5d + ((double)world.rand.nextFloat() - 0.5d)), f1);
    }

    private void adjustMotion(double d, double d1, double d2, float f, float f1)
    {
        float f2 = MathHelper.sqrt(d * d + d1 * d1 + d2 * d2);

        d /= f2;

        d1 /= f2;

        d2 /= f2;

        d += rand.nextGaussian() * 0.0074999998323619366d * (double)f1;

        d1 += rand.nextGaussian() * 0.0074999998323619366d * (double)f1;

        d2 += rand.nextGaussian() * 0.0074999998323619366d * (double)f1;

        d *= f;

        d1 *= f;

        d2 *= f;

        motionX = d;

        motionY = d1;

        motionZ = d2;

        float f3 = MathHelper.sqrt(d * d + d2 * d2);

        prevRotationYaw = rotationYaw = (float)((Math.atan2(d, d2) * 180.0d) / Math.PI);

        prevRotationPitch = rotationPitch = (float)((Math.atan2(d1, f3) * 180.0d) / Math.PI);

        aoLightValueScratchXYZNNP = 0;
    }

    @Override
    protected void onImpact(@Nonnull RayTraceResult rayTraceResult)
    {
    }

    @Override
    public boolean isInRangeToRenderDist(double d)
    {
        return true;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();

        if (aoLightValueScratchXYNN == 5)
        {
            setDead();
        }

        if (prevRotationPitch == 0.0f && prevRotationYaw == 0.0f)
        {
            float f = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);

            prevRotationYaw = rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180.0d) / Math.PI);

            prevRotationPitch = rotationPitch = (float)((Math.atan2(motionY, f) * 180.0d) / Math.PI);
        }

        if (aoLightValueZPos)
        {
            if (world.getBlockState(new BlockPos(hitX, hitY, hitZ)) != blockHit)
            {
                aoLightValueZPos = false;

                motionX *= rand.nextFloat() * 0.2f;

                motionZ *= rand.nextFloat() * 0.2f;

                aoLightValueScratchXYZNNP = 0;

                aoLightValueScratchXYNN = 0;
            }
            else
            {
                aoLightValueScratchXYZNNP++;

                if (aoLightValueScratchXYZNNP == 5)
                {
                    setDead();
                }

                return;
            }
        }
        else
        {
            aoLightValueScratchXYNN++;
        }

        Vec3d vec3d = new Vec3d(posX, posY, posZ);

        Vec3d vec3d1 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

        RayTraceResult rtr = world.rayTraceBlocks(vec3d, vec3d1);

        vec3d = new Vec3d(posX, posY, posZ);

        vec3d1 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

        if (rtr != null)
        {
            vec3d1 = new Vec3d(rtr.hitVec.x, rtr.hitVec.y, rtr.hitVec.z);
        }

        Entity entity = null;

        double d = 0.0d;

        for (Entity entity1 : world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox().grow(motionX, motionY, motionZ).expand(1.0d, 1.0d, 1.0d)))
        {
            if (!entity1.canBeCollidedWith() || ((entity1 == lightValueOwn || (lightValueOwn != null && entity1 == lightValueOwn.getRidingEntity())) && aoLightValueScratchXYNN < 5) || aoLightValueScratchXYZNNN)
            {
                if (motionZ != 0.0d || !((motionX == 0.0d) & (motionY == 0.0d)))
                {
                    continue;
                }

                setDead();

                break;
            }

            float f4 = 0.3f;

            AxisAlignedBB axisAlignedBB = entity1.getEntityBoundingBox().expand(f4, f4, f4);

            RayTraceResult rtr1 = axisAlignedBB.calculateIntercept(vec3d, vec3d1);

            if (rtr1 == null)
            {
                continue;
            }

            double d2 = vec3d.distanceTo(rtr1.hitVec);

            if (d2 < d || d == 0.0d)
            {
                entity = entity1;

                d = d2;
            }
        }

        if (entity != null)
        {
            rtr = new RayTraceResult(entity);
        }

        if (rtr != null)
        {
            if (rtr.entityHit != null)
            {
                if (rtr.entityHit instanceof EntityLiving)
                {
                    boolean flag = false;

                    if (rtr.entityHit instanceof EntityKid)
                    {
                        EntityKid kid = (EntityKid)rtr.entityHit;

                        if (kid.getModelSize() > 0.25f)
                        {
                            kid.shrinkModelSize(0.15f);
                        }
                        else
                        {
                            kid.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityBigBaby)
                    {
                        EntityBigBaby bigBaby = (EntityBigBaby)rtr.entityHit;

                        if (bigBaby.getModelSize() > 0.5f)
                        {
                            bigBaby.shrinkModelSize(0.25f);
                        }
                        else
                        {
                            bigBaby.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityRatMan)
                    {
                        EntityRatMan ratMan = (EntityRatMan)rtr.entityHit;

                        if (ratMan.getModelSize() > 0.3f)
                        {
                            ratMan.shrinkModelSize(0.2f);

                            ratMan.decreaseMoveSpeed(0.15f);
                        }
                        else
                        {
                            ratMan.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityGuineaPig)
                    {
                        EntityGuineaPig guineaPig = (EntityGuineaPig)rtr.entityHit;

                        if (guineaPig.getModelSize() > 0.35f)
                        {
                            guineaPig.shrinkModelSize(0.15f);
                        }
                        else
                        {
                            guineaPig.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityHotdog)
                    {
                        EntityHotdog hotdog = (EntityHotdog)rtr.entityHit;

                        if (hotdog.getModelSize() > 0.35f)
                        {
                            hotdog.shrinkModelSize(0.15f);
                        }
                        else
                        {
                            hotdog.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityRobotTed)
                    {
                        EntityRobotTed robotTed = (EntityRobotTed)rtr.entityHit;

                        if (robotTed.getModelSize() > 0.6f)
                        {
                            robotTed.shrinkModelSize(0.5f);

                            robotTed.decreaseMoveSpeed(0.25f);
                        }
                        else
                        {
                            robotTed.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityRobotTodd)
                    {
                        EntityRobotTodd robotTodd = (EntityRobotTodd)rtr.entityHit;

                        if (robotTodd.getModelSize() > 0.4f)
                        {
                            robotTodd.shrinkModelSize(0.4f);

                            robotTodd.decreaseMoveSpeed(0.15f);
                        }
                        else
                        {
                            robotTodd.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityGooGoat)
                    {
                        EntityGooGoat gooGoat = (EntityGooGoat)rtr.entityHit;

                        if (gooGoat.getModelSize() > 0.4)
                        {
                            gooGoat.shrinkModelSize(0.4f);

                            gooGoat.decreaseMoveSpeed(0.15f);
                        }
                        else
                        {
                            gooGoat.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityLolliman)
                    {
                        EntityLolliman lolliman = (EntityLolliman)rtr.entityHit;

                        if (lolliman.getModelSize() > 0.6f)
                        {
                            lolliman.shrinkModelSize(0.4f);
                        }
                        else
                        {
                            lolliman.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityCastleCritter)
                    {
                        EntityCastleCritter castleCritter = (EntityCastleCritter)rtr.entityHit;

                        if (castleCritter.getModelSize() > 0.4f)
                        {
                            castleCritter.shrinkModelSize(0.3f);
                        }
                        else
                        {
                            castleCritter.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntitySneakySal)
                    {
                        EntitySneakySal sal = (EntitySneakySal)rtr.entityHit;

                        if (sal.getModelSize() > 0.6f)
                        {
                            sal.shrinkModelSize(0.2f);

                            sal.setDissedMax(0);

                            if (rand.nextInt(2) == 0)
                            {
                                sal.playSound(CreepsSoundHandler.salNobodyShrinksSound, 0.5f, 1.0f);
                            }
                        }
                        else
                        {
                            sal.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityArmyGuy)
                    {
                        EntityArmyGuy armyGuy = (EntityArmyGuy)rtr.entityHit;

                        if (armyGuy.getModelSize() > 0.4f)
                        {
                            armyGuy.shrinkModelSize(0.2f);
                        }
                        else
                        {
                            armyGuy.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityEvilChicken)
                    {
                        EntityEvilChicken evilChicken = (EntityEvilChicken)rtr.entityHit;

                        if (evilChicken.getModelSize() > 0.5f)
                        {
                            evilChicken.shrinkModelSize(0.4f);

                            evilChicken.decreaseMoveSpeed(0.015f);
                        }
                        else
                        {
                            evilChicken.setDead();

                            flag = true;
                        }
                    }
                    // TODO: EntityBum
                    else if (rtr.entityHit instanceof EntityBubbleScum)
                    {
                        EntityBubbleScum bubbleScum = (EntityBubbleScum)rtr.entityHit;

                        if (bubbleScum.getModelSize() > 0.3f)
                        {
                            bubbleScum.shrinkModelSize(0.15f);
                        }
                        else
                        {
                            bubbleScum.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityLawyerFromHell)
                    {
                        EntityLawyerFromHell lawyerFromHell = (EntityLawyerFromHell)rtr.entityHit;

                        if (lawyerFromHell.getModelSize() > 0.3f)
                        {
                            lawyerFromHell.shrinkModelSize(0.2f);

                            // TODO: increase current fine by 50
                        }
                        else
                        {
                            lawyerFromHell.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityG)
                    {
                        EntityG g = (EntityG)rtr.entityHit;

                        if (g.getModelSize() > 0.3f)
                        {
                            g.shrinkModelSize(0.2f);
                        }
                        else
                        {
                            g.setDead();

                            flag = true;
                        }
                    }
                    // TODO: EntityRockMonster
                    else if (rtr.entityHit instanceof EntityBabyMummy)
                    {
                        EntityBabyMummy babyMummy = (EntityBabyMummy)rtr.entityHit;

                        if (babyMummy.getModelSize() > 0.2f)
                        {
                            babyMummy.shrinkModelSize(0.2f);
                        }
                        else
                        {
                            babyMummy.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityBlackSoul)
                    {
                        EntityBlackSoul blackSoul = (EntityBlackSoul)rtr.entityHit;

                        if (blackSoul.getModelSize() > 0.3f)
                        {
                            blackSoul.shrinkModelSize(0.3f);
                        }
                        else
                        {
                            blackSoul.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityBlorp)
                    {
                        EntityBlorp blorp = (EntityBlorp)rtr.entityHit;

                        if (blorp.getModelSize() > 0.3f)
                        {
                            blorp.shrinkModelSize(0.3f);
                        }
                        else
                        {
                            blorp.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityCamel)
                    {
                        EntityCamel camel = (EntityCamel)rtr.entityHit;

                        if (camel.getModelSize() > 0.3f)
                        {
                            camel.shrinkModelSize(0.3f);
                        }
                        else
                        {
                            camel.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityCamelJockey)
                    {
                        EntityCamelJockey camelJockey = (EntityCamelJockey)rtr.entityHit;

                        if (camelJockey.getModelSize() > 0.3f)
                        {
                            camelJockey.shrinkModelSize(0.2f);
                        }
                        else
                        {
                            camelJockey.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityCastleGuard)
                    {
                        EntityCastleGuard castleGuard = (EntityCastleGuard)rtr.entityHit;

                        if (castleGuard.getModelSize() > 0.3f)
                        {
                            castleGuard.shrinkModelSize(0.3f);
                        }
                        else
                        {
                            castleGuard.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityCaveman)
                    {
                        EntityCaveman caveman = (EntityCaveman)rtr.entityHit;

                        if (caveman.getModelSize() > 0.3f)
                        {
                            caveman.shrinkModelSize(0.3f);
                        }
                        else
                        {
                            caveman.setDead();

                            flag = true;
                        }
                    }
                    // TODO: EntityDesertLizard
                    else if (rtr.entityHit instanceof EntityDigBug)
                    {
                        EntityDigBug digBug = (EntityDigBug)rtr.entityHit;

                        if (digBug.getModelSize() > 0.3f)
                        {
                            digBug.shrinkModelSize(0.2f);
                        }
                        else
                        {
                            digBug.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityEvilCreature)
                    {
                        EntityEvilCreature evilCreature = (EntityEvilCreature)rtr.entityHit;

                        if (evilCreature.getModelSize() > 0.5f)
                        {
                            evilCreature.shrinkModelSize(0.4f);
                        }
                        else
                        {
                            evilCreature.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityEvilPig)
                    {
                        EntityEvilPig evilPig = (EntityEvilPig)rtr.entityHit;

                        if (evilPig.getModelSize() > 0.4f)
                        {
                            evilPig.shrinkModelSize(0.3f);
                        }
                        else
                        {
                            evilPig.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityEvilScientist)
                    {
                        EntityEvilScientist evilScientist = (EntityEvilScientist)rtr.entityHit;

                        if (evilScientist.getModelSize() > 0.4f)
                        {
                            evilScientist.shrinkModelSize(0.3f);
                        }
                        else
                        {
                            evilScientist.setDead();

                            flag = true;
                        }
                    }
                    // TODO: EntityEvilSnowman
                    else if (rtr.entityHit instanceof EntityFloob)
                    {
                        EntityFloob floob = (EntityFloob)rtr.entityHit;

                        if (floob.getModelSize() > 0.4f)
                        {
                            floob.shrinkModelSize(0.4f);
                        }
                        else
                        {
                            floob.setDead();

                            flag = true;
                        }
                    }
                    // TODO: EntityHippo
                    else if (rtr.entityHit instanceof EntityHunchback)
                    {
                        EntityHunchback hunchback = (EntityHunchback)rtr.entityHit;

                        if (hunchback.getModelSize() > 0.3f)
                        {
                            hunchback.shrinkModelSize(0.2f);
                        }
                        else
                        {
                            hunchback.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityHunchbackSkeleton)
                    {
                        EntityHunchbackSkeleton hunchbackSkeleton = (EntityHunchbackSkeleton)rtr.entityHit;

                        if (hunchbackSkeleton.getModelSize() > 0.3f)
                        {
                            hunchbackSkeleton.shrinkModelSize(0.2f);
                        }
                        else
                        {
                            hunchbackSkeleton.setDead();

                            flag = true;
                        }
                    }
                    // TODO: EntityInvisibleMan
                    else if (rtr.entityHit instanceof EntityManDog)
                    {
                        EntityManDog manDog = (EntityManDog)rtr.entityHit;

                        if (manDog.getModelSize() > 0.3f)
                        {
                            manDog.shrinkModelSize(0.2f);
                        }
                        else
                        {
                            manDog.setDead();

                            flag = true;
                        }
                    }
                    // TODO: EntityNonSwimmer
                    else if (rtr.entityHit instanceof EntityRocketGiraffe)
                    {
                        EntityRocketGiraffe rocketGiraffe = (EntityRocketGiraffe)rtr.entityHit;

                        if (rocketGiraffe.getModelSize() > 0.3f)
                        {
                            rocketGiraffe.shrinkModelSize(0.15f);
                        }
                        else
                        {
                            rocketGiraffe.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntitySnowDevil)
                    {
                        EntitySnowDevil snowDevil = (EntitySnowDevil)rtr.entityHit;

                        if (snowDevil.getModelSize() > 0.4f)
                        {
                            snowDevil.shrinkModelSize(0.2f);
                        }
                        else
                        {
                            snowDevil.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityThief)
                    {
                        EntityThief thief = (EntityThief)rtr.entityHit;

                        if (thief.getModelSize() > 0.3f)
                        {
                            thief.shrinkModelSize(0.2f);
                        }
                        else
                        {
                            thief.setDead();

                            flag = true;
                        }
                    }
                    else if (rtr.entityHit instanceof EntityZebra)
                    {
                        EntityZebra zebra = (EntityZebra)rtr.entityHit;

                        if (zebra.getModelSize() > 0.3f)
                        {
                            zebra.shrinkModelSize(0.2f);
                        }
                        else
                        {
                            zebra.setDead();

                            flag = true;
                        }
                    }

                    if (flag)
                    {
                        smoke();

                        playSound(CreepsSoundHandler.shrinkKillSound, 1.0f, 1.0f / (rand.nextFloat() * 0.1f + 0.95f));

                        setDead();
                    }
                }
                else
                {
                    setDead();
                }
            }
            else
            {
                BlockPos hitBlockPos = rtr.getBlockPos();

                hitX = hitBlockPos.getX();

                hitY = hitBlockPos.getY();

                hitZ = hitBlockPos.getZ();

                blockHit = world.getBlockState(hitBlockPos);

                motionX = (float)(rtr.hitVec.x - posX);

                motionY = (float)(rtr.hitVec.y - posY);

                motionZ = (float)(rtr.hitVec.z - posZ);

                float f1 = MathHelper.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);

                posX -= (motionX / (double)f1) * 0.05000000074505806d;

                posY -= (motionY / (double)f1) * 0.05000000074505806d;

                posZ -= (motionZ / (double)f1) * 0.05000000074505806d;

                aoLightValueZPos = true;

                if (blockHit.getBlock() == Blocks.ICE)
                {
                    world.setBlockState(hitBlockPos, Blocks.FLOWING_WATER.getDefaultState());
                }

                setDead();
            }

            playSound(CreepsSoundHandler.raygunSound, 0.2f, 1.0f / (rand.nextFloat() * 0.1f + 0.95f));

            setDead();
        }

        posX += motionX;

        posY += motionY;

        posZ += motionZ;

        float f2 = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);

        rotationYaw = (float)((Math.atan2(motionX, motionZ) * 180.0d) / Math.PI);

        for (rotationPitch = (float)((Math.atan2(motionY, f2) * 180.0d) / Math.PI); (rotationPitch - prevRotationPitch) < -180.0f; prevRotationPitch -= 360.0f);

        for (; (rotationPitch - prevRotationPitch) >= 180.0f; prevRotationPitch += 360.0f);

        for (; (rotationYaw - prevRotationYaw) < -180.0f; prevRotationYaw -= 360.0f);

        for (; (rotationYaw - prevRotationYaw) >= 180.0f; prevRotationYaw += 360.0f);

        rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2f;

        rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2f;

        float f3 = 0.99f;

        if (handleWaterMovement())
        {
            for (int l = 0; l < 4; l++)
            {
                float f7 = 0.25f;

                world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX - motionX * (double)f7, posY - motionY * (double)f7, posZ - motionZ * (double)f7, motionX, motionY, motionZ);
            }

            f3 = 0.8f;

            setDead();
        }

        motionX *= f3;

        motionZ *= f3;

        setPosition(posX, posY, posZ);
    }

    @Override
    public void setDead()
    {
        super.setDead();

        blast();

        lightValueOwn = null;
    }

    private void smoke()
    {
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                for (int k = 0; k < 5; k++)
                {
                    world.spawnParticle(EnumParticleTypes.EXPLOSION_NORMAL, (posX + (double)(rand.nextFloat() * width * 2.0f)) - (double)width, posY + (double)(rand.nextFloat() * height), (posZ + (double)(rand.nextFloat() * width * 2.0f)) - (double)width, rand.nextGaussian() * 0.12d, rand.nextGaussian() * 0.12d, rand.nextGaussian() * 0.12d);
                }
            }
        }
    }

    private void blast()
    {
    }
}

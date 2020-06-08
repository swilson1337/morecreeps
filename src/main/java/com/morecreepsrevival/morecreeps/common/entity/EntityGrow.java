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

public class EntityGrow extends EntityThrowable
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

    private static final float growMax = 1.0f;

    private static final float growBonus = 0.45f;

    public EntityGrow(World world)
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

    public EntityGrow(World world, EntityLivingBase entity)
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
                    if (rtr.entityHit instanceof EntityKid)
                    {
                        EntityKid kid = (EntityKid)rtr.entityHit;

                        if (kid.getModelSize() < 5.0f + growMax)
                        {
                            kid.growModelSize(0.15f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityBigBaby)
                    {
                        EntityBigBaby bigBaby = (EntityBigBaby)rtr.entityHit;

                        if (bigBaby.getModelSize() < 8.0f + growMax)
                        {
                            bigBaby.growModelSize(0.25f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityRatMan)
                    {
                        EntityRatMan ratMan = (EntityRatMan)rtr.entityHit;

                        if (ratMan.getModelSize() < 3.0f + growMax)
                        {
                            ratMan.growModelSize(0.2f + growBonus);

                            ratMan.increaseMoveSpeed(0.15f);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityGuineaPig)
                    {
                        EntityGuineaPig guineaPig = (EntityGuineaPig)rtr.entityHit;

                        if (guineaPig.getModelSize() < 5.0f + growMax)
                        {
                            guineaPig.growModelSize(0.15f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityHotdog)
                    {
                        EntityHotdog hotdog = (EntityHotdog)rtr.entityHit;

                        if (hotdog.getModelSize() < 5.0f + growMax)
                        {
                            hotdog.growModelSize(0.15f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityRobotTed)
                    {
                        EntityRobotTed robotTed = (EntityRobotTed)rtr.entityHit;

                        if (robotTed.getModelSize() < 6.0f + growMax)
                        {
                            robotTed.growModelSize(0.25f + growBonus);

                            robotTed.increaseMoveSpeed(0.15f);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityRobotTodd)
                    {
                        EntityRobotTodd robotTodd = (EntityRobotTodd)rtr.entityHit;

                        if (robotTodd.getModelSize() < 4.0f + growMax)
                        {
                            robotTodd.growModelSize(0.25f + growBonus);

                            robotTodd.increaseMoveSpeed(0.05f);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityGooGoat)
                    {
                        EntityGooGoat gooGoat = (EntityGooGoat)rtr.entityHit;

                        if (gooGoat.getModelSize() < 3.0f + growMax)
                        {
                            gooGoat.growModelSize(0.24f + growBonus);

                            gooGoat.increaseMoveSpeed(0.15f);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityLolliman)
                    {
                        EntityLolliman lolliman = (EntityLolliman)rtr.entityHit;

                        if (lolliman.getModelSize() < 5.0f + growMax)
                        {
                            lolliman.growModelSize(0.25f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityCastleCritter)
                    {
                        EntityCastleCritter castleCritter = (EntityCastleCritter)rtr.entityHit;

                        if (castleCritter.getModelSize() < 4.0f + growMax)
                        {
                            castleCritter.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntitySneakySal)
                    {
                        EntitySneakySal sal = (EntitySneakySal)rtr.entityHit;

                        if (sal.getModelSize() < 6.0f + growMax)
                        {
                            sal.growModelSize(0.2f + growBonus);

                            sal.setDissedMax(0);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityArmyGuy)
                    {
                        EntityArmyGuy armyGuy = (EntityArmyGuy)rtr.entityHit;

                        if (armyGuy.getModelSize() < 4.0f + growMax)
                        {
                            armyGuy.growModelSize(0.2f + growBonus);

                            // TODO: set loyal to false
                        }
                    }
                    else if (rtr.entityHit instanceof EntityEvilChicken)
                    {
                        EntityEvilChicken evilChicken = (EntityEvilChicken)rtr.entityHit;

                        if (evilChicken.getModelSize() < 5.0f + growMax)
                        {
                            evilChicken.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityBum)
                    {
                        EntityBum bum = (EntityBum)rtr.entityHit;

                        if (bum.getModelSize() < 4.0f + growMax)
                        {
                            bum.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityBubbleScum)
                    {
                        EntityBubbleScum bubbleScum = (EntityBubbleScum)rtr.entityHit;

                        if (bubbleScum.getModelSize() < 3.0f + growMax)
                        {
                            bubbleScum.growModelSize(0.15f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityLawyerFromHell)
                    {
                        EntityLawyerFromHell lawyerFromHell = (EntityLawyerFromHell)rtr.entityHit;

                        if (lawyerFromHell.getModelSize() < 5.0f + growMax)
                        {
                            lawyerFromHell.growModelSize(0.2f + growBonus);

                            // TODO: increase current fine by 50
                        }
                    }
                    else if (rtr.entityHit instanceof EntityG)
                    {
                        EntityG g = (EntityG)rtr.entityHit;

                        if (g.getModelSize() < 5.0f + growMax)
                        {
                            g.growModelSize(0.2f + growBonus);
                        }
                    }
                    // TODO: EntityRockMonster
                    else if (rtr.entityHit instanceof EntityBabyMummy)
                    {
                        EntityBabyMummy babyMummy = (EntityBabyMummy)rtr.entityHit;

                        if (babyMummy.getModelSize() < 4.0f + growMax)
                        {
                            babyMummy.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityBlackSoul)
                    {
                        EntityBlackSoul blackSoul = (EntityBlackSoul)rtr.entityHit;

                        if (blackSoul.getModelSize() < 4.0f + growMax)
                        {
                            blackSoul.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityBlorp)
                    {
                        EntityBlorp blorp = (EntityBlorp)rtr.entityHit;

                        if (blorp.getModelSize() < 5.0f + growMax)
                        {
                            blorp.growModelSize(0.25f + growBonus);

                            // TODO: set angry
                        }
                    }
                    else if (rtr.entityHit instanceof EntityCamel)
                    {
                        EntityCamel camel = (EntityCamel)rtr.entityHit;

                        if (camel.getModelSize() < 5.0f + growMax)
                        {
                            camel.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityCamelJockey)
                    {
                        EntityCamelJockey camelJockey = (EntityCamelJockey)rtr.entityHit;

                        if (camelJockey.getModelSize() < 4.0f + growMax)
                        {
                            camelJockey.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityCastleGuard)
                    {
                        EntityCastleGuard castleGuard = (EntityCastleGuard)rtr.entityHit;

                        if (castleGuard.getModelSize() < 4.0f + growMax)
                        {
                            castleGuard.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityCaveman)
                    {
                        EntityCaveman caveman = (EntityCaveman)rtr.entityHit;

                        if (caveman.getModelSize() < 4.0f + growMax)
                        {
                            caveman.growModelSize(0.2f + growBonus);
                        }
                    }
                    // TODO: EntityDesertLizard
                    else if (rtr.entityHit instanceof EntityDigBug)
                    {
                        EntityDigBug digBug = (EntityDigBug)rtr.entityHit;

                        if (digBug.getModelSize() < 4.0f + growMax)
                        {
                            digBug.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityEvilCreature)
                    {
                        EntityEvilCreature evilCreature = (EntityEvilCreature)rtr.entityHit;

                        if (evilCreature.getModelSize() < 7.0f + growMax)
                        {
                            evilCreature.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityEvilPig)
                    {
                        EntityEvilPig evilPig = (EntityEvilPig)rtr.entityHit;

                        if (evilPig.getModelSize() < 4.0f + growMax)
                        {
                            evilPig.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityEvilScientist)
                    {
                        EntityEvilScientist evilScientist = (EntityEvilScientist)rtr.entityHit;

                        if (evilScientist.getModelSize() < 4.0f + growMax)
                        {
                            evilScientist.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityEvilSnowman)
                    {
                        EntityEvilSnowman evilSnowman = (EntityEvilSnowman)rtr.entityHit;

                        if (evilSnowman.getModelSize() < 7.0f + growMax)
                        {
                            evilSnowman.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityFloob)
                    {
                        EntityFloob floob = (EntityFloob)rtr.entityHit;

                        if (floob.getModelSize() < 5.0f + growMax)
                        {
                            floob.growModelSize(0.2f + growBonus);
                        }
                    }
                    // TODO: EntityHippo
                    else if (rtr.entityHit instanceof EntityHunchback)
                    {
                        EntityHunchback hunchback = (EntityHunchback)rtr.entityHit;

                        if (hunchback.getModelSize() < 5.0f + growMax)
                        {
                            hunchback.growModelSize(0.2f + growBonus);

                            // TODO: set tamed to false
                        }
                    }
                    else if (rtr.entityHit instanceof EntityHunchbackSkeleton)
                    {
                        EntityHunchbackSkeleton hunchbackSkeleton = (EntityHunchbackSkeleton)rtr.entityHit;

                        if (hunchbackSkeleton.getModelSize() < 5.0f + growMax)
                        {
                            hunchbackSkeleton.growModelSize(0.2f + growBonus);
                        }
                    }
                    // TODO: EntityInvisibleMan
                    else if (rtr.entityHit instanceof EntityManDog)
                    {
                        EntityManDog manDog = (EntityManDog)rtr.entityHit;

                        if (manDog.getModelSize() < 4.0f + growMax)
                        {
                            manDog.growModelSize(0.2f + growBonus);
                        }
                    }
                    // TODO: EntityNonSwimmer
                    else if (rtr.entityHit instanceof EntityRocketGiraffe)
                    {
                        EntityRocketGiraffe rocketGiraffe = (EntityRocketGiraffe)rtr.entityHit;

                        if (rocketGiraffe.getModelSize() < 4.0f + growMax)
                        {
                            rocketGiraffe.growModelSize(0.15f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntitySnowDevil)
                    {
                        EntitySnowDevil snowDevil = (EntitySnowDevil)rtr.entityHit;

                        if (snowDevil.getModelSize() < 4.0f + growMax)
                        {
                            snowDevil.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityThief)
                    {
                        EntityThief thief = (EntityThief)rtr.entityHit;

                        if (thief.getModelSize() < 4.0f + growMax)
                        {
                            thief.growModelSize(0.2f + growBonus);
                        }
                    }
                    else if (rtr.entityHit instanceof EntityZebra)
                    {
                        EntityZebra zebra = (EntityZebra)rtr.entityHit;

                        if (zebra.getModelSize() < 5.0f + growMax)
                        {
                            zebra.growModelSize(0.2f + growBonus);
                        }
                    }

                    playSound(CreepsSoundHandler.raygunSound, 0.2F, 1.0F / (rand.nextFloat() * 0.1F + 0.95F));

                    setDead();
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

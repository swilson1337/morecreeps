package com.morecreepsrevival.morecreeps.client.particles;

import net.minecraft.client.particle.Particle;
import net.minecraft.world.World;

public class FxPee extends Particle
{
    public FxPee(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, double d, double d1)
    {
        super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);

        setSize(0.3f, 0.3f);

        setParticleTextureIndex(4);

        particleRed = 0.8f;

        particleBlue = 0.18f;

        particleGreen = 0.71f;

        particleGravity = 3.55f;

        particleScale *= 0.2f;

        motionX += d * 0.23999999463558197d;

        motionZ += d1 * 0.23999999463558197d;
    }
}

package com.morecreepsrevival.morecreeps.client.particles;

import net.minecraft.client.particle.Particle;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import net.minecraft.client.renderer.BufferBuilder;

public class FxBlood extends Particle
{
    public FxBlood(World world, double d, double d1, double d2, float f)
    {
        super(world, d, d1, d2, 0.0D, 0.0D, 0.0D);

        setSize(0.7F, 0.7F);

        particleRed = 1.0F;

        particleBlue = 1.0F;

        particleGreen = 1.0F;

        particleGravity = 2.0F;

        particleScale *= f;

        particleMaxAge = 90;

        canCollide = false;
    }

    @Override
    public int getFXLayer()
    {
        return 1;
    }

    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
        float f6 = (float)this.particleTextureIndexX / 16.0F;
        float f7 = f6 + 0.01560938F;
        float f8 = (float)this.particleTextureIndexY / 16.0F;
        float f9 = f8 + 0.01560938F;
        float f10 = 0.1F * this.particleScale;

        super.renderParticle(buffer, entityIn, partialTicks, rotationX, rotationZ, rotationYZ, rotationXY, rotationXZ);

        if (this.particleTexture != null)
        {
            f6 = this.particleTexture.getMinU();
            f7 = this.particleTexture.getMaxU();
            f8 = this.particleTexture.getMinV();
            f9 = this.particleTexture.getMaxV();
        }

        float f11 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
        float f12 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
        float f13 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
        buffer.color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).pos((double)(f11 - rotationX * f10 - rotationXY * f10), (double)(f12 - rotationZ * f10), (double)(f13 - rotationYZ * f10 - rotationXZ * f10)).tex((double)f7, (double)f9).endVertex();
        buffer.color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).pos((double)(f11 - rotationX * f10 + rotationXY * f10), (double)(f12 + rotationZ * f10), (double)(f13 - rotationYZ * f10 + rotationXZ * f10)).tex((double)f7, (double)f8).endVertex();
        buffer.color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).pos((double)(f11 + rotationX * f10 + rotationXY * f10), (double)(f12 + rotationZ * f10), (double)(f13 + rotationYZ * f10 + rotationXZ * f10)).tex((double)f6, (double)f8).endVertex();
        buffer.color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).pos((double)(f11 + rotationX * f10 - rotationXY * f10), (double)(f12 - rotationZ * f10), (double)(f13 + rotationYZ * f10 - rotationXZ * f10)).tex((double)f6, (double)f9).endVertex();
    }
}
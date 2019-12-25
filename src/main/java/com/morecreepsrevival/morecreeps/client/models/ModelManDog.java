package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelManDog extends ModelBase
{
    public ModelRenderer bipedHead;
    public ModelRenderer leftEar;
    public ModelRenderer rightEar;
    public ModelRenderer neck;
    public ModelRenderer collar;
    public ModelRenderer snouttop;
    public ModelRenderer snoutbot;
    public ModelRenderer nose;
    public ModelRenderer tail;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedEars;
    public ModelRenderer bipedCloak;

    public boolean heldItemLeft;
    public boolean heldItemRight;
    public boolean isSneak;

    private float mouthopen;
    private float mouthangle;
    public boolean superfly;

    public ModelManDog()
    {
        this(0.0f);

        mouthopen = 1.0f;

        mouthangle = 0.0f;
    }

    public ModelManDog(float f)
    {
        this(f, 0.0f);
    }

    public ModelManDog(float f, float f1)
    {
        heldItemLeft = false;

        heldItemRight = false;

        isSneak = false;

        bipedCloak = new ModelRenderer(this, 55, 14);
        bipedCloak.addBox(-2.5F, 0.0F, 4F, 5, 7, -3, 2.0F);
        bipedCloak.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        bipedCloak.rotateAngleX = 0.44F;
        bipedEars = new ModelRenderer(this, 24, 0);
        bipedEars.addBox(-3F, -6F, -1F, 6, 6, 1, f);
        bipedHead = new ModelRenderer(this, 0, 0);
        bipedHead.addBox(-4F, -11F, -4F, 8, 7, 8, f);
        bipedHead.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        leftEar = new ModelRenderer(this, 52, 0);
        leftEar.addBox(-5F, -8F, 2.0F, 1, 6, 3, f);
        leftEar.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        rightEar = new ModelRenderer(this, 52, 0);
        rightEar.addBox(4F, -8F, 2.0F, 1, 6, 3, f);
        rightEar.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        neck = new ModelRenderer(this, 0, 16);
        neck.addBox(-1.5F, -5F, -1.5F, 3, 6, 3, f);
        neck.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        collar = new ModelRenderer(this, 44, 10);
        collar.addBox(-2.5F, -2.5F, -2.5F, 5, 1, 5, f + 0.35F);
        collar.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        snouttop = new ModelRenderer(this, 32, 0);
        snouttop.addBox(-2F, -8F, -11F, 4, 2, 7, f);
        snouttop.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        snoutbot = new ModelRenderer(this, 32, 14);
        snoutbot.addBox(-2F, -6F, -11F, 4, 2, 8, f - 0.02F);
        snoutbot.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        nose = new ModelRenderer(this, 24, 0);
        nose.addBox(-0.5F, -9F, -10F, 1, 1, 1, f + 0.25F);
        nose.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        tail = new ModelRenderer(this, 32, 20);
        tail.addBox(-1.5F, 10F, -4F, 3, 2, 8, f);
        tail.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        tail.rotateAngleX = 0.5F;
        bipedBody = new ModelRenderer(this, 16, 16);
        bipedBody.addBox(-4F, 0.0F, -2F, 8, 12, 4, f);
        bipedBody.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        bipedRightArm = new ModelRenderer(this, 40, 16);
        bipedRightArm.addBox(-3F, -2F, -2F, 4, 12, 4, f);
        bipedRightArm.setRotationPoint(-5F, 2.0F + f1, 0.0F);
        bipedLeftArm = new ModelRenderer(this, 40, 16);
        bipedLeftArm.mirror = true;
        bipedLeftArm.addBox(-1F, -2F, -2F, 4, 12, 4, f);
        bipedLeftArm.setRotationPoint(5F, 2.0F + f1, 0.0F);
        bipedRightLeg = new ModelRenderer(this, 0, 16);
        bipedRightLeg.addBox(-2F, 0.0F, -2F, 4, 12, 4, f);
        bipedRightLeg.setRotationPoint(-2F, 12F + f1, 0.0F);
        bipedLeftLeg = new ModelRenderer(this, 0, 16);
        bipedLeftLeg.mirror = true;
        bipedLeftLeg.addBox(-2F, 0.0F, -2F, 4, 12, 4, f);
        bipedLeftLeg.setRotationPoint(2.0F, 12F + f1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        bipedHead.render(f5);
        bipedBody.render(f5);
        bipedRightArm.render(f5);
        bipedLeftArm.render(f5);
        bipedRightLeg.render(f5);
        bipedLeftLeg.render(f5);
        snouttop.render(f5);
        snoutbot.render(f5);
        nose.render(f5);
        neck.render(f5);
        collar.render(f5);
        tail.render(f5);
        leftEar.render(f5);
        rightEar.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        bipedBody.rotateAngleX = bipedBody.rotateAngleX + mouthangle;
        bipedHead.rotateAngleY = f3 / (180F / (float)Math.PI);
        bipedHead.rotateAngleX = f4 / (180F / (float)Math.PI) + 0.35F;
        leftEar.rotateAngleY = bipedHead.rotateAngleY;
        leftEar.rotateAngleX = bipedHead.rotateAngleX + 0.3F;
        leftEar.rotateAngleZ = bipedHead.rotateAngleZ + 0.0F;
        rightEar.rotateAngleY = bipedHead.rotateAngleY;
        rightEar.rotateAngleX = bipedHead.rotateAngleX + 0.3F;
        rightEar.rotateAngleZ = bipedHead.rotateAngleZ + 0.0F;
        snouttop.rotateAngleY = bipedHead.rotateAngleY;
        snouttop.rotateAngleX = bipedHead.rotateAngleX;
        snoutbot.rotateAngleY = bipedHead.rotateAngleY;
        snoutbot.rotateAngleX = bipedHead.rotateAngleX + mouthangle;
        mouthangle += (double)mouthopen * 0.002D;

        if ((double)mouthangle > 0.12D || (double)mouthangle < -0.12D)
        {
            mouthopen *= -1F;
        }

        nose.rotateAngleY = bipedHead.rotateAngleY;
        nose.rotateAngleX = bipedHead.rotateAngleX;
        bipedRightArm.rotateAngleX = (MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F + mouthangle) - 0.5F;
        bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F - mouthangle - 0.5F;
        bipedRightArm.rotateAngleZ = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
        bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        bipedRightLeg.rotateAngleZ = 0.15F;
        bipedLeftLeg.rotateAngleZ = -0.15F;

        if (isRiding)
        {
            bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
            bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
            bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
        }

        if (heldItemLeft)
        {
            bipedLeftArm.rotateAngleX = bipedLeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
        }

        if (heldItemRight)
        {
            bipedRightArm.rotateAngleX = bipedRightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
        }

        bipedRightArm.rotateAngleY = 0.0F;
        bipedLeftArm.rotateAngleY = 0.0F;

        if (swingProgress > -9990F)
        {
            float f6 = swingProgress;
            bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f6) * (float)Math.PI * 2.0F) * 0.2F;
            bipedRightArm.rotationPointZ = MathHelper.sin(bipedBody.rotateAngleY) * 5F;
            bipedRightArm.rotationPointX = -MathHelper.cos(bipedBody.rotateAngleY) * 5F;
            bipedLeftArm.rotationPointZ = -MathHelper.sin(bipedBody.rotateAngleY) * 5F;
            bipedLeftArm.rotationPointX = MathHelper.cos(bipedBody.rotateAngleY) * 5F;
            bipedRightArm.rotateAngleY += bipedBody.rotateAngleY;
            bipedLeftArm.rotateAngleY += bipedBody.rotateAngleY;
            bipedLeftArm.rotateAngleX += bipedBody.rotateAngleX;
            f6 = 1.0F - swingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            float f8 = MathHelper.sin(f6 * (float)Math.PI);
            float f9 = MathHelper.sin(swingProgress * (float)Math.PI) * -(bipedHead.rotateAngleX - 0.7F) * 0.75F;
            bipedRightArm.rotateAngleX -= (double)f8 * 1.2D + (double)f9;
            bipedRightArm.rotateAngleY += bipedBody.rotateAngleY * 2.0F;
            bipedRightArm.rotateAngleZ = MathHelper.sin(swingProgress * (float)Math.PI) * -0.4F;
        }

        if (isSneak)
        {
            bipedBody.rotateAngleX = 0.5F;
            bipedRightLeg.rotateAngleX -= 0.0F;
            bipedLeftLeg.rotateAngleX -= 0.0F;
            bipedRightArm.rotateAngleX += 0.4F;
            bipedLeftArm.rotateAngleX += 0.4F;
            bipedRightLeg.rotationPointZ = 4F;
            bipedLeftLeg.rotationPointZ = 4F;
            bipedRightLeg.rotationPointY = 9F;
            bipedLeftLeg.rotationPointY = 9F;
            bipedHead.rotationPointY = 1.0F;
        }
        else
        {
            bipedBody.rotateAngleX = 0.0F;
            bipedRightLeg.rotationPointZ = 0.0F;
            bipedLeftLeg.rotationPointZ = 0.0F;
            bipedRightLeg.rotationPointY = 12F;
            bipedLeftLeg.rotationPointY = 12F;
            bipedHead.rotationPointY = 0.0F;
        }

        if (superfly)
        {
            float f7 = (float)Math.PI;
            bipedBody.rotateAngleX = f7 / 2.0F;
            bipedLeftLeg.rotateAngleX = f7 / 2.0F + mouthangle;
            bipedLeftLeg.rotationPointY = 0.0F;
            bipedLeftLeg.rotationPointZ = 12F;
            bipedRightLeg.rotateAngleX = f7 / 2.0F + mouthangle;
            bipedRightLeg.rotationPointY = 0.0F;
            bipedRightLeg.rotationPointZ = 12F;
            tail.rotationPointY = -3F;
            tail.rotationPointX = -2.5F;
            bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.15F + mouthangle;
            bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.15F + mouthangle;
            bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F - 0.5F;
            bipedLeftArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F - 0.5F;
        }
        else
        {
            bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.25F + mouthangle;
            bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.25F + mouthangle;
            bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
            bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
            tail.rotateAngleY = tail.rotateAngleY + mouthangle / 20F;
            tail.rotateAngleX = 0.5F;
            tail.rotationPointY = 0.0F;
            tail.rotationPointX = 0.0F;
        }

        renderEars(0.01F);
    }

    public void renderEars(float f)
    {
        bipedEars.rotateAngleY = bipedHead.rotateAngleY;
        bipedEars.rotateAngleX = bipedHead.rotateAngleX;
        bipedEars.rotationPointX = 0.0F;
        bipedEars.rotationPointY = 0.0F;
        bipedEars.render(f);
    }
}

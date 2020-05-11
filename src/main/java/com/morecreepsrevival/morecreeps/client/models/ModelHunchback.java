package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHunchback extends ModelBase
{
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer hunch;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedEars;
    public ModelRenderer bipedCloak;
    public boolean heldItemLeft;
    public boolean heldItemRight;
    public boolean isSneak;

    public ModelHunchback()
    {
        this(0.0f);
    }

    public ModelHunchback(float f)
    {
        this(f, 0.0f);
    }

    public ModelHunchback(float f, float f1)
    {
        heldItemLeft = false;
        heldItemRight = false;
        isSneak = false;
        bipedCloak = new ModelRenderer(this, 0, 0);
        bipedCloak.addBox(-5F, 0.0F, -1F, 10, 16, 1, f);
        bipedEars = new ModelRenderer(this, 24, 0);
        bipedEars.addBox(-3F, -6F, -1F, 6, 6, 1, f);
        bipedHead = new ModelRenderer(this, 0, 0);
        bipedHead.addBox(-4F, 2.0F, -9F, 6, 7, 7, f);
        bipedHead.setRotationPoint(0.0F, 0.0F + f1, -5F);
        bipedBody = new ModelRenderer(this, 16, 16);
        bipedBody.addBox(-4F, 4F, -6F, 8, 10, 4, f);
        bipedBody.setRotationPoint(0.0F, 0.0F + f1, -7F);
        bipedBody.rotateAngleX = -0.7235988F;
        hunch = new ModelRenderer(this, 34, 2);
        hunch.addBox(-4F, 0.02F, -7.2F, 8, 4, 2, f);
        hunch.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        hunch.rotateAngleX = -0.7235988F;
        bipedRightArm = new ModelRenderer(this, 40, 16);
        bipedRightArm.addBox(-3F, 3F, -8F, 4, 12, 4, f);
        bipedRightArm.setRotationPoint(-5F, 2.0F + f1, -9F);
        bipedLeftArm = new ModelRenderer(this, 40, 16);
        bipedLeftArm.mirror = true;
        bipedLeftArm.addBox(-1F, 3F, -8F, 3, 8, 3, f);
        bipedLeftArm.setRotationPoint(5F, 2.0F + f1, -9F);
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
        hunch.render(f5);
        bipedRightArm.render(f5);
        bipedLeftArm.render(f5);
        bipedRightLeg.render(f5);
        bipedLeftLeg.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        bipedHead.rotateAngleY = f3 / (180F / (float)Math.PI);
        bipedHead.rotateAngleX = f4 / (180F / (float)Math.PI);
        bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        bipedRightArm.rotateAngleZ = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
        bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        bipedRightLeg.rotateAngleY = 0.0F;
        bipedLeftLeg.rotateAngleY = 0.0F;

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
            hunch.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f6) * (float)Math.PI * 2.0F) * 0.2F;
            bipedRightArm.rotationPointZ = MathHelper.sin(bipedBody.rotateAngleY) * 5F;
            bipedRightArm.rotationPointX = -MathHelper.cos(bipedBody.rotateAngleY) * 5F;
            bipedLeftArm.rotationPointZ = -MathHelper.sin(bipedBody.rotateAngleY) * 5F;
            bipedLeftArm.rotationPointX = MathHelper.cos(bipedBody.rotateAngleY) * 5F;
            bipedRightArm.rotateAngleY += bipedBody.rotateAngleY;
            bipedLeftArm.rotateAngleY += bipedBody.rotateAngleY;
            bipedLeftArm.rotateAngleX += bipedBody.rotateAngleY;
            f6 = 1.0F - swingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            float f7 = MathHelper.sin(f6 * (float)Math.PI);
            float f8 = MathHelper.sin(swingProgress * (float)Math.PI) * -(bipedHead.rotateAngleX - 0.7F) * 0.75F;
            bipedRightArm.rotateAngleX -= (double)f7 * 1.2D + (double)f8;
            bipedRightArm.rotateAngleY += bipedBody.rotateAngleY * 2.0F;
            bipedRightArm.rotateAngleZ = MathHelper.sin(swingProgress * (float)Math.PI) * -0.4F;
        }

        if (isSneak)
        {
            hunch.rotateAngleX = 0.7235988F;
            bipedBody.rotateAngleX = 0.7235988F;
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
            hunch.rotateAngleX = 0.7235988F;
            bipedBody.rotateAngleX = 0.7235988F;
            bipedRightLeg.rotationPointZ = 0.0F;
            bipedLeftLeg.rotationPointZ = 0.0F;
            bipedRightLeg.rotationPointY = 12F;
            bipedLeftLeg.rotationPointY = 12F;
            bipedHead.rotationPointY = 0.0F;
        }

        bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    }
}

package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBubbleScum extends ModelBase
{
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer eyeL;
    public ModelRenderer eyeR;
    public ModelRenderer mouth;

    public boolean heldItemLeft;
    public boolean heldItemRight;
    public boolean isSneak;

    public ModelBubbleScum()
    {
        this(0.0f);
    }

    public ModelBubbleScum(float f)
    {
        heldItemLeft = false;
        heldItemRight = false;
        isSneak = false;

        float f1 = 2.0F;
        bipedBody = new ModelRenderer(this, 0, 0);
        bipedBody.addBox(-4F, 0.0F, -4F, 8, 8, 8, f);
        bipedBody.setRotationPoint(0.0F, 8F + f1, 0.0F);
        bipedRightArm = new ModelRenderer(this, 0, 0);
        bipedRightArm.addBox(1.0F, 2.0F, -1F, 2, 6, 2, f);
        bipedRightArm.setRotationPoint(-1F, 10F + f1, 0.0F);
        bipedLeftArm = new ModelRenderer(this, 0, 0);
        bipedLeftArm.mirror = true;
        bipedLeftArm.addBox(-3F, 2.0F, -1F, 2, 6, 2, f);
        bipedLeftArm.setRotationPoint(-1F, 10F + f1, 0.0F);
        bipedRightLeg = new ModelRenderer(this, 0, 16);
        bipedRightLeg.addBox(-1F, 2.0F, -1F, 2, 6, 2, f);
        bipedRightLeg.setRotationPoint(-2F, 2.0F, 0.0F);
        bipedLeftLeg = new ModelRenderer(this, 0, 16);
        bipedLeftLeg.mirror = true;
        bipedLeftLeg.addBox(-1F, 2.0F, -1F, 2, 6, 2, f);
        bipedLeftLeg.setRotationPoint(2.0F, 2.0F, 0.0F);
        eyeL = new ModelRenderer(this, 32, 0);
        eyeL.addBox(0.0F, 0.0F, -2F, 2, 2, 1, 0.5F);
        eyeL.setRotationPoint(1.0F, 10F + f1, -3F);
        eyeR = new ModelRenderer(this, 32, 0);
        eyeR.addBox(0.0F, 0.0F, -2F, 2, 2, 1, 0.5F);
        eyeR.setRotationPoint(-3F, 10F + f1, -3F);
        mouth = new ModelRenderer(this, 32, 16);
        mouth.addBox(0.0F, 0.0F, -2F, 2, 2, 2, f);
        mouth.setRotationPoint(-1F, 13F + f1, -3F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        bipedBody.render(f5);
        bipedRightArm.render(f5);
        bipedLeftArm.render(f5);
        bipedRightLeg.render(f5);
        bipedLeftLeg.render(f5);
        eyeL.render(f5);
        eyeR.render(f5);
        mouth.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        bipedRightArm.rotateAngleZ = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
        bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 0.7F * f1;
        bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 0.7F * f1;
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
            eyeL.rotateAngleY = bipedBody.rotateAngleY;
            eyeR.rotateAngleY = bipedBody.rotateAngleY;
            mouth.rotateAngleY = bipedBody.rotateAngleY;
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
            float f7 = MathHelper.sin(f6 * (float)Math.PI);
            float f8 = MathHelper.sin(swingProgress * (float)Math.PI) * 0.75F;
            bipedRightArm.rotateAngleX -= (double)f7 * 1.2D + (double)f8;
            bipedRightArm.rotateAngleY += bipedBody.rotateAngleY * 2.0F;
            bipedRightArm.rotateAngleZ = MathHelper.sin(swingProgress * (float)Math.PI) * -0.4F;
        }

        if (isSneak)
        {
            bipedBody.rotateAngleX = 0.5F;
            eyeL.rotateAngleX = 0.5F;
            eyeR.rotateAngleX = 0.5F;
            mouth.rotateAngleX = 0.5F;
            bipedRightLeg.rotateAngleX -= 0.0F;
            bipedLeftLeg.rotateAngleX -= 0.0F;
            bipedRightArm.rotateAngleX += 0.4F;
            bipedLeftArm.rotateAngleX += 0.4F;
            bipedRightLeg.rotationPointZ = 4F;
            bipedLeftLeg.rotationPointZ = 4F;
            bipedRightLeg.rotationPointY = 9F;
            bipedLeftLeg.rotationPointY = 9F;
        }
        else
        {
            bipedBody.rotateAngleX = 0.0F;
            eyeL.rotateAngleX = 0.0F;
            eyeR.rotateAngleX = 0.0F;
            mouth.rotateAngleX = 0.0F;
            bipedRightLeg.rotationPointZ = 0.0F;
            bipedLeftLeg.rotationPointZ = 0.0F;
            bipedRightLeg.rotationPointY = 16F;
            bipedLeftLeg.rotationPointY = 16F;
        }

        bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
        bipedRightArm.rotateAngleZ = 0.5F;
        bipedLeftArm.rotateAngleZ = -0.5F;
        mouth.rotationPointZ = -2.5F - MathHelper.cos(f2 * 0.5F) * 0.5F;
        eyeL.rotationPointZ = -2.5F - MathHelper.cos(f2 * 0.2F) * 0.6F;
        eyeR.rotationPointZ = -2.5F - MathHelper.sin(f2 * 0.2F) * 0.6F;
    }
}

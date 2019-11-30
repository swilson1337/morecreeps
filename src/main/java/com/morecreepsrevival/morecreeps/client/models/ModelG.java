package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelG extends ModelBase
{
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public boolean heldItemLeft;
    public boolean heldItemRight;
    public boolean isSneak;
    public ModelRenderer eyeL;
    public ModelRenderer g3;
    public ModelRenderer g5;
    public ModelRenderer g1;
    public ModelRenderer g2;
    public ModelRenderer g4;
    public ModelRenderer eyeR;

    public ModelG()
    {
        this(0.0f);
    }

    public ModelG(float f)
    {
        this(f, 0.0f);
    }

    public ModelG(float f, float f1)
    {
        heldItemLeft = false;
        heldItemRight = false;
        isSneak = false;
        float f2 = 0.0F;
        eyeL = new ModelRenderer(this, 0, 0);
        eyeL.addBox(-3F, -17F, -2.5F, 3, 3, 1, f2);
        eyeL.setRotationPoint(0.0F, 8F, 0.0F);
        g3 = new ModelRenderer(this, 16, 6);
        g3.addBox(-8F, 0.0F, -2F, 16, 4, 4, f2);
        g3.setRotationPoint(0.0F, 8F, 0.0F);
        bipedLeftArm = new ModelRenderer(this, 56, 16);
        bipedLeftArm.addBox(-2F, -1F, -1F, 2, 8, 2, f2);
        bipedLeftArm.setRotationPoint(-8F, 8F, 0.0F);
        g5 = new ModelRenderer(this, 16, 6);
        g5.addBox(-8F, -16F, -2F, 16, 4, 4, f2);
        g5.setRotationPoint(0.0F, 8F, 0.0F);
        bipedRightLeg = new ModelRenderer(this, 0, 16);
        bipedRightLeg.addBox(-2F, 0.0F, -2F, 4, 12, 4, f2);
        bipedRightLeg.setRotationPoint(-3F, 12F, 0.0F);
        bipedLeftLeg = new ModelRenderer(this, 0, 16);
        bipedLeftLeg.addBox(-2F, 0.0F, -2F, 4, 12, 4, f2);
        bipedLeftLeg.setRotationPoint(3F, 12F, 0.0F);
        g1 = new ModelRenderer(this, 16, 16);
        g1.addBox(0.0F, -7F, -2F, 8, 4, 4, f2);
        g1.setRotationPoint(0.0F, 8F, 0.0F);
        g2 = new ModelRenderer(this, 40, 16);
        g2.addBox(4F, -3F, -2F, 4, 3, 4, f2);
        g2.setRotationPoint(0.0F, 8F, 0.0F);
        g4 = new ModelRenderer(this, 40, 16);
        g4.addBox(-8F, -12F, -2F, 4, 12, 4, f2);
        g4.setRotationPoint(0.0F, 8F, 0.0F);
        bipedRightArm = new ModelRenderer(this, 56, 16);
        bipedRightArm.addBox(0.0F, -1F, -1F, 2, 8, 2, f2);
        bipedRightArm.setRotationPoint(8F, 8F, 0.0F);
        eyeR = new ModelRenderer(this, 0, 0);
        eyeR.addBox(1.0F, -17F, -2.5F, 3, 3, 1, f2);
        eyeR.setRotationPoint(0.0F, 8F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        eyeL.render(f5);
        g3.render(f5);
        bipedLeftArm.render(f5);
        g5.render(f5);
        bipedRightLeg.render(f5);
        bipedLeftLeg.render(f5);
        g1.render(f5);
        g2.render(f5);
        g4.render(f5);
        bipedRightArm.render(f5);
        eyeR.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
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
            bipedRightArm.rotateAngleZ = MathHelper.sin(swingProgress * (float)Math.PI) * -0.4F;
        }

        if (isSneak)
        {
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
            bipedRightLeg.rotationPointZ = 0.0F;
            bipedLeftLeg.rotationPointZ = 0.0F;
            bipedRightLeg.rotationPointY = 12F;
            bipedLeftLeg.rotationPointY = 12F;
        }

        bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    }
}

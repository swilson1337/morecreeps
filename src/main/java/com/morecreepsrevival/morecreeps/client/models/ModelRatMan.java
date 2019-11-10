package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRatMan extends ModelBase
{
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer body;
    public ModelRenderer ratHead;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightArm;
    public ModelRenderer snout;
    public ModelRenderer nose;
    public ModelRenderer earR;
    public ModelRenderer earL;
    public ModelRenderer tail;
    public ModelRenderer wiskers;

    public boolean jumper;

    public ModelRatMan()
    {
        body = new ModelRenderer(this, 12, 16);
        body.addBox(-3F, 0.0F, -2F, 6, 12, 4, 0.0F);
        body.setRotationPoint(0.0F, 3F, -3F);
        body.rotateAngleX = 0.6108652F;
        ratHead = new ModelRenderer(this, 0, 0);
        ratHead.addBox(-4F, -8F, -4F, 8, 8, 8, 0.0F);
        ratHead.setRotationPoint(0.0F, 4F, -6F);
        bipedRightArm = new ModelRenderer(this, 36, 16);
        bipedRightArm.addBox(-2F, -2F, -1F, 2, 12, 2, 0.0F);
        bipedRightArm.setRotationPoint(-3F, 5F, -2F);
        bipedLeftArm = new ModelRenderer(this, 36, 16);
        bipedLeftArm.addBox(0.0F, -2F, -1F, 2, 12, 2, 0.0F);
        bipedLeftArm.setRotationPoint(3F, 5F, -2F);
        bipedRightLeg = new ModelRenderer(this, 0, 16);
        bipedRightLeg.addBox(-1F, 0.0F, -1F, 2, 12, 2, 0.0F);
        bipedRightLeg.setRotationPoint(-2F, 12F, 4F);
        bipedLeftLeg = new ModelRenderer(this, 0, 16);
        bipedLeftLeg.addBox(-1F, 0.0F, -1F, 2, 12, 2, 0.0F);
        bipedLeftLeg.setRotationPoint(2.0F, 12F, 4F);
        snout = new ModelRenderer(this, 45, 0);
        snout.addBox(-1.5F, -3F, -10F, 3, 2, 6, 0.0F);
        snout.setRotationPoint(0.0F, 4F, -6F);
        nose = new ModelRenderer(this, 8, 16);
        nose.addBox(-0.5F, -5F, -10F, 1, 2, 1, 0.0F);
        nose.setRotationPoint(0.0F, 4F, -6F);
        earR = new ModelRenderer(this, 32, 0);
        earR.addBox(-7F, -12F, 0.0F, 5, 6, 1, 0.0F);
        earR.setRotationPoint(0.0F, 2.0F, -7F);
        earR.rotateAngleX = 0.0F;
        earR.rotateAngleY = 0.2094395F;
        earR.rotateAngleZ = -0.06981317F;
        earR.mirror = false;
        earL = new ModelRenderer(this, 32, 0);
        earL.addBox(2.0F, -12F, 0.0F, 5, 6, 1, 0.0F);
        earL.setRotationPoint(0.0F, 2.0F, -7F);
        earL.rotateAngleX = 0.0F;
        earL.rotateAngleY = -0.2094395F;
        earL.rotateAngleZ = 0.06981317F;
        earL.mirror = false;
        tail = new ModelRenderer(this, 32, 16);
        tail.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 15, 0.25F);
        tail.setRotationPoint(0.0F, 11F, 5F);
        tail.rotateAngleX = -0.4363323F;
        tail.rotateAngleY = 0.0F;
        tail.rotateAngleZ = 0.0F;
        tail.mirror = false;
        wiskers = new ModelRenderer(this, 32, 13);
        wiskers.addBox(-5F, -4F, -8F, 10, 3, 0, -0.25F);
        wiskers.setRotationPoint(0.0F, 4F, -6F);
        earL.rotateAngleY += -0.2094395F;
        earL.rotateAngleZ += 0.06981317F;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        bipedLeftLeg.render(f5);
        bipedRightLeg.render(f5);
        body.render(f5);
        ratHead.render(f5);
        bipedLeftArm.render(f5);
        bipedRightArm.render(f5);
        snout.render(f5);
        nose.render(f5);
        earR.render(f5);
        earL.render(f5);
        tail.render(f5);
        wiskers.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        wiskers.rotateAngleY = snout.rotateAngleY = nose.rotateAngleY = earR.rotateAngleY = earL.rotateAngleY = ratHead.rotateAngleY = f3 / (180F / (float)Math.PI);
        wiskers.rotateAngleX = snout.rotateAngleX = nose.rotateAngleX = earR.rotateAngleX = earL.rotateAngleX = ratHead.rotateAngleX = f4 / 77.29578F;
        bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        bipedRightArm.rotateAngleZ = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
        bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        tail.rotateAngleY = bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        bipedRightLeg.rotateAngleY = 0.0F;
        bipedLeftLeg.rotateAngleY = 0.0F;
        bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
        bipedRightArm.rotateAngleX -= 0.8470667F;
        bipedLeftArm.rotateAngleX -= 0.8470667F;

        if (jumper)
        {
            bipedRightArm.rotateAngleX = -1.473F;
            bipedLeftArm.rotateAngleX = -1.473F;
        }
    }
}

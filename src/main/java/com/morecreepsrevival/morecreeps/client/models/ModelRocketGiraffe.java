package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRocketGiraffe extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer headGiraffe;
    public ModelRenderer neck;
    public ModelRenderer hornR;
    public ModelRenderer hornRtop;
    public ModelRenderer hornL;
    public ModelRenderer hornLtop;
    public ModelRenderer mane;
    public ModelRenderer earL;
    public ModelRenderer earR;
    public ModelRenderer tail2;

    public ModelRocketGiraffe()
    {
        body = new ModelRenderer(this, 0, 0);
        body.addBox(-2.5F, 0.0F, -7F, 5, 5, 14, 3.5F);
        body.setRotationPoint(0.0F, -10F, 0.0F);
        headGiraffe = new ModelRenderer(this, 30, 20);
        headGiraffe.addBox(-3F, -35F, -5F, 6, 6, 16, 0.0F);
        headGiraffe.setRotationPoint(0.0F, -5.5F, -13.8F);
        headGiraffe.rotateAngleX = 0.3490658F;
        earL = new ModelRenderer(this, 0, 23);
        earL.addBox(3F, -35F, 10F, 1, 2, 4, 0.0F);
        earL.setRotationPoint(-14.5F, -8.5F, -13.8F);
        earL.rotateAngleX = 0.3490658F;
        earL.rotateAngleY = 0.4490658F;
        earL.rotateAngleZ = 0.4490658F;
        earR = new ModelRenderer(this, 0, 23);
        earR.addBox(3F, -35F, 8F, 1, 2, 4, 0.0F);
        earR.setRotationPoint(8F, -5.5F, -13.8F);
        earR.rotateAngleX = 0.3490658F;
        earR.rotateAngleY = -0.4490658F;
        earR.rotateAngleZ = -0.4490658F;
        neck = new ModelRenderer(this, 0, 0);
        neck.addBox(-1F, -20F, 0.0F, 2, 25, 4, 1.5F);
        neck.setRotationPoint(0.0F, -15F, -11F);
        neck.rotateAngleX = 0.3490658F;
        mane = new ModelRenderer(this, 54, 12);
        mane.addBox(-1F, -22F, 4F, 1, 17, 3, 0.5F);
        mane.setRotationPoint(0.0F, -15F, -11F);
        mane.rotateAngleX = 0.3490658F;
        hornL = new ModelRenderer(this, 40, 20);
        hornL.addBox(-0.5F, -39F, -0.2F, 1, 6, 1, 0.0F);
        hornL.setRotationPoint(1.8F, -8F, -11F);
        hornL.rotateAngleX = 0.1490658F;
        hornLtop = new ModelRenderer(this, 40, 20);
        hornLtop.addBox(-0.5F, -39F, -0.2F, 1, 1, 1, 0.5F);
        hornLtop.setRotationPoint(1.8F, -8F, -11F);
        hornLtop.rotateAngleX = 0.1490658F;
        hornR = new ModelRenderer(this, 40, 20);
        hornR.addBox(-0.5F, -39F, -0.2F, 1, 6, 1, 0.0F);
        hornR.setRotationPoint(-1.8F, -8F, -11F);
        hornR.mirror = true;
        hornR.rotateAngleX = 0.1490658F;
        hornRtop = new ModelRenderer(this, 40, 20);
        hornRtop.addBox(-0.5F, -39F, -0.2F, 1, 1, 1, 0.5F);
        hornRtop.setRotationPoint(-1.8F, -8F, -11F);
        hornRtop.mirror = true;
        hornRtop.rotateAngleX = 0.1490658F;
        tail2 = new ModelRenderer(this, 40, 19);
        tail2.addBox(-1F, -10F, 6F, 1, 3, 1, 1.25F);
        tail2.setRotationPoint(0.0F, 12F, 15F);
        tail2.rotateAngleX = 0.5235988F;
        tail = new ModelRenderer(this, 42, 0);
        tail.addBox(-2F, -10F, 6F, 3, 15, 3, -0.25F);
        tail.setRotationPoint(0.0F, 2.0F, 8F);
        tail.rotateAngleX = 0.5235988F;
        leg1 = new ModelRenderer(this, 0, 9);
        leg1.addBox(-0.5F, 0.0F, -0.5F, 1, 22, 1, 1.5F);
        leg1.setRotationPoint(3F, 0.0F, 8F);
        leg2 = new ModelRenderer(this, 0, 9);
        leg2.addBox(-0.5F, 0.0F, -0.5F, 1, 22, 1, 1.5F);
        leg2.setRotationPoint(-3F, 0.0F, 8F);
        leg3 = new ModelRenderer(this, 0, 9);
        leg3.addBox(-0.5F, 0.0F, -0.5F, 1, 22, 1, 1.5F);
        leg3.setRotationPoint(3F, 0.0F, -8F);
        leg4 = new ModelRenderer(this, 0, 9);
        leg4.addBox(-0.5F, 0.0F, -0.5F, 1, 22, 1, 1.5F);
        leg4.setRotationPoint(-3F, 0.0F, -8F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        tail.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        headGiraffe.render(f5);
        neck.render(f5);
        hornR.render(f5);
        hornRtop.render(f5);
        hornL.render(f5);
        hornLtop.render(f5);
        mane.render(f5);
        earL.render(f5);
        earR.render(f5);
        tail2.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}

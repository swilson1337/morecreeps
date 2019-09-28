package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCastleCritter extends ModelBase
{
    public ModelRenderer critterHead;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer tail;
    public ModelRenderer hornR;
    public ModelRenderer hornL;

    public ModelCastleCritter()
    {
        critterHead = new ModelRenderer(this, 12, 0);
        critterHead.addBox(-1.5F, -1.5F, -3F, 3, 3, 3, 0.0F);
        critterHead.setRotationPoint(0.0F, 19F, -1F);
        body = new ModelRenderer(this, 0, 6);
        body.addBox(-1F, -1F, -2.5F, 2, 2, 5, 0.0F);
        body.setRotationPoint(0.0F, 21F, 1.5F);
        leg1 = new ModelRenderer(this, 0, 13);
        leg1.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, -0.25F);
        leg1.setRotationPoint(0.5F, 22F, -0.5F);
        leg2 = new ModelRenderer(this, 0, 13);
        leg2.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, -0.25F);
        leg2.setRotationPoint(-0.5F, 22F, -0.5F);
        leg3 = new ModelRenderer(this, 0, 13);
        leg3.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, -0.25F);
        leg3.setRotationPoint(0.5F, 22F, 3.5F);
        leg4 = new ModelRenderer(this, 0, 13);
        leg4.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1, -0.25F);
        leg4.setRotationPoint(-0.5F, 22F, 3.5F);
        tail = new ModelRenderer(this, 0, 0);
        tail.addBox(-0.5F, -0.5F, 0.0F, 1, 1, 5, 0.0F);
        tail.setRotationPoint(0.0F, 21F, 3.5F);
        tail.rotateAngleX = -0.3346075F;
        hornR = new ModelRenderer(this, 0, 16);
        hornR.addBox(-1.5F, -3F, -2F, 1, 2, 1, 0.0F);
        hornR.setRotationPoint(0.0F, 19F, -1F);
        hornL = new ModelRenderer(this, 0, 16);
        hornL.addBox(0.5F, -3F, -2F, 1, 2, 1, 0.0F);
        hornL.setRotationPoint(0.0F, 19F, -1F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        critterHead.render(f5);
        body.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        tail.render(f5);
        hornR.render(f5);
        hornL.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        float f6 = f3 / (180F / (float)Math.PI);
        float f7 = f4 / (180F / (float)Math.PI);
        hornR.rotateAngleY = hornL.rotateAngleY = critterHead.rotateAngleY = f6;
        hornR.rotateAngleX = hornL.rotateAngleX = critterHead.rotateAngleX = f7;
        tail.rotateAngleY = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}

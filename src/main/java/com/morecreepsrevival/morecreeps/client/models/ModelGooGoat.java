package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGooGoat extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer nose;
    public ModelRenderer headGoat;
    public ModelRenderer tail;
    public ModelRenderer beard;
    public ModelRenderer horn1;
    public ModelRenderer horn2;

    public ModelGooGoat()
    {
        byte byte0 = 12;
        body = new ModelRenderer(this, 0, 0);
        body.addBox(0.0F, 0.0F, 0.0F, 8, 10, 12, 0.0F);
        body.setRotationPoint(-4F, 2.0F, -6F);
        horn1 = new ModelRenderer(this, 0, 20);
        horn1.addBox(2.0F, -4F, -4F, 1, 5, 1, 0.0F);
        horn1.setRotationPoint(0.0F, 4F, -4F);
        horn2 = new ModelRenderer(this, 0, 20);
        horn2.addBox(-3F, -4F, -4F, 1, 5, 1, 0.0F);
        horn2.setRotationPoint(0.0F, 4F, -4F);
        headGoat = new ModelRenderer(this, 0, 0);
        headGoat.addBox(-3F, -4F, -6F, 6, 6, 4, 0.0F);
        headGoat.setRotationPoint(0.0F, 8F, -4F);
        nose = new ModelRenderer(this, 20, 0);
        nose.addBox(-2F, 1.0F, -10F, 4, 3, 5, 0.0F);
        nose.setRotationPoint(0.0F, 5F, -3.5F);
        beard = new ModelRenderer(this, 50, 10);
        beard.addBox(-2F, -4F, -8F, 4, 3, 1, 0.0F);
        beard.setRotationPoint(0.0F, 14F, -2F);
        beard.rotateAngleX = 0.778F;
        tail = new ModelRenderer(this, 32, 20);
        tail.addBox(-4F, -5F, 4F, 3, 2, 5, 0.0F);
        tail.setRotationPoint(2.5F, 10F, 5F);
        tail.rotateAngleX = 0.68F;
        leg1 = new ModelRenderer(this, 0, 16);
        leg1.addBox(-2F, 0.0F, -2F, 3, byte0, 3, 0.0F);
        leg1.setRotationPoint(-2F, 24 - byte0, 5F);
        leg2 = new ModelRenderer(this, 0, 16);
        leg2.addBox(-2F, 0.0F, -2F, 3, byte0, 3, 0.0F);
        leg2.setRotationPoint(3F, 24 - byte0, 5F);
        leg3 = new ModelRenderer(this, 0, 16);
        leg3.addBox(-2F, 0.0F, -2F, 3, byte0, 3, 0.0F);
        leg3.setRotationPoint(-2F, 24 - byte0, -4F);
        leg4 = new ModelRenderer(this, 0, 16);
        leg4.addBox(-2F, 0.0F, -2F, 3, byte0, 3, 0.0F);
        leg4.setRotationPoint(3F, 24 - byte0, -4F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        headGoat.render(f5);
        nose.render(f5);
        tail.render(f5);
        beard.render(f5);
        horn1.render(f5);
        horn2.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        float f6 = f3 / (180F / (float)Math.PI);
        float f7 = f4 / (180F / (float)Math.PI);
        headGoat.rotateAngleY = f6;
        headGoat.rotateAngleX = f7;
        horn1.rotateAngleY = f6;
        horn1.rotateAngleX = f7 - 0.25F;
        horn2.rotateAngleY = f6;
        horn2.rotateAngleX = f7 - 0.25F;
        nose.rotateAngleY = f6;
        nose.rotateAngleX = f7;
        nose.rotationPointX = 0.0F + f6 * 0.8F;
        beard.rotateAngleY = f6;
        beard.rotateAngleX = f7;
        beard.rotationPointX = 0.0F + f6 * 2.5F;
        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}

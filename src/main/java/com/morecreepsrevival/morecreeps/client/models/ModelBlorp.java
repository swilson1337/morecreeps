package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBlorp extends ModelBase
{
    public ModelRenderer headBlorp;
    public ModelRenderer unusedModel;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer neck;
    public ModelRenderer arm1R;
    public ModelRenderer arm2R;
    public ModelRenderer arm1L;
    public ModelRenderer arm2L;
    public ModelRenderer nose;
    public ModelRenderer ant;

    public ModelBlorp()
    {
        float f = 0.0F;
        int i = 4;
        ant = new ModelRenderer(this, 28, 0);
        ant.addBox(-3F, -10F, -1F, 6, 6, 1, f);
        ant.setRotationPoint(0.0F, i - 12, 0.0F);
        nose = new ModelRenderer(this, 44, 16);
        nose.addBox(-1F, -2F, -4F, 2, 7, 1, f);
        nose.setRotationPoint(0.0F, i - 12, 0.0F);
        headBlorp = new ModelRenderer(this, 0, 0);
        headBlorp.addBox(-3F, -4F, -3F, 6, 6, 6, f);
        headBlorp.setRotationPoint(0.0F, i - 12, 0.0F);
        neck = new ModelRenderer(this, 0, 16);
        neck.addBox(-1F, -15F, -1F, 2, 15, 2, f);
        neck.setRotationPoint(0.0F, i, 0.0F);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-4F, 0.0F, -2F, 8, 12, 4, f);
        body.setRotationPoint(0.0F, i, 0.0F);
        arm1R = new ModelRenderer(this, 16, 16);
        arm1R.addBox(-2F, 4F, -1F, 2, 10, 2, f);
        arm1R.setRotationPoint(0.0F, i, 0.0F);
        arm1R.rotateAngleZ = 0.85F;
        arm1R.mirror = true;
        arm1L = new ModelRenderer(this, 16, 16);
        arm1L.addBox(0.0F, 4F, -1F, 2, 10, 2, f);
        arm1L.setRotationPoint(0.0F, i, 0.0F);
        arm1L.rotateAngleZ = -0.85F;
        arm2R = new ModelRenderer(this, 16, 16);
        arm2R.addBox(-12F, 7F, -6F, 2, 2, 6, f);
        arm2R.setRotationPoint(0.0F, i, 0.0F);
        arm2R.mirror = true;
        arm2L = new ModelRenderer(this, 16, 16);
        arm2L.addBox(10F, 7F, -6F, 2, 2, 6, f);
        arm2L.setRotationPoint(0.0F, i, 0.0F);
        leg1 = new ModelRenderer(this, 45, 0);
        leg1.addBox(-2F, 0.0F, -2F, 4, 8, 4, f);
        leg1.setRotationPoint(-5F, 12 + i, 4F);
        leg2 = new ModelRenderer(this, 45, 0);
        leg2.addBox(-2F, 0.0F, -2F, 4, 8, 4, f);
        leg2.setRotationPoint(5F, 12 + i, 4F);
        leg3 = new ModelRenderer(this, 44, 0);
        leg3.addBox(-2F, 0.0F, -2F, 4, 8, 5, f);
        leg3.setRotationPoint(0.0F, 12 + i, -4F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        ant.render(f5);
        nose.render(f5);
        headBlorp.render(f5);
        neck.render(f5);
        body.render(f5);
        arm1R.render(f5);
        arm2R.render(f5);
        arm1L.render(f5);
        arm2L.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        ant.rotateAngleY = f3 / (180F / (float)Math.PI);
        ant.rotateAngleX = f4 / (180F / (float)Math.PI);
        nose.rotateAngleY = f3 / (180F / (float)Math.PI);
        nose.rotateAngleX = f4 / (180F / (float)Math.PI);
        headBlorp.rotateAngleY = f3 / (180F / (float)Math.PI);
        headBlorp.rotateAngleX = f4 / (180F / (float)Math.PI);
        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        arm1R.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.0F * f1 * 0.5F;
        arm1L.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.0F * f1 * 0.5F;
        arm2R.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.5F * f1 * 0.5F;
        arm2L.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.5F * f1 * 0.5F;
        headBlorp.rotationPointY = MathHelper.cos(f * 0.6662F) * 6F * f1 - 10F;
        nose.rotationPointY = MathHelper.cos(f * 0.6662F) * 6F * f1 - 10F;
        ant.rotationPointY = MathHelper.cos(f * 0.6662F) * 6F * f1 - 10F;
    }
}

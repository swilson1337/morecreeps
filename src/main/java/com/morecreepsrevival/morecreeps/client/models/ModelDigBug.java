package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDigBug extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer leg5;
    public ModelRenderer leg6;
    public ModelRenderer ant;
    public ModelRenderer eyeL;
    public ModelRenderer eyeR;

    public ModelDigBug()
    {
        this(4, 0.0f);
    }

    public ModelDigBug(float f)
    {
        this(6, f);
    }

    public ModelDigBug(int i, float f)
    {
        ant = new ModelRenderer(this, 40, 16);
        ant.addBox(-3F, -6F, -12F, 6, 0, 6, f);
        ant.setRotationPoint(0.0F, 0.0F, 0.0F);
        eyeL = new ModelRenderer(this, 40, 24);
        eyeL.addBox(1.0F, -9F, -16F, 3, 1, 3, f);
        eyeL.setRotationPoint(0.0F, 0.0F, 0.0F);
        eyeR = new ModelRenderer(this, 40, 24);
        eyeR.addBox(-4F, -9F, -16F, 3, 1, 3, f);
        eyeR.setRotationPoint(0.0F, 0.0F, 0.0F);
        body = new ModelRenderer(this, 0, 0);
        body.addBox(-5F, -10F, -7F, 10, 24, 8, f);
        body.setRotationPoint(0.0F, 17 - i, 2.0F);
        leg1 = new ModelRenderer(this, 48, 0);
        leg1.addBox(-1F, 0.0F, -2F, 2, i, 3, f);
        leg1.setRotationPoint(-3F, 24 - i, 12F);
        leg1.rotateAngleZ = 0.5F;
        leg2 = new ModelRenderer(this, 48, 0);
        leg2.addBox(-1F, 0.0F, -1F, 2, i, 3, f);
        leg2.setRotationPoint(3F, 24 - i, 12F);
        leg2.rotateAngleZ = -0.5F;
        leg3 = new ModelRenderer(this, 48, 0);
        leg3.addBox(-1F, 0.0F, -2F, 2, i, 3, f);
        leg3.setRotationPoint(-3F, 24 - i, -3F);
        leg3.rotateAngleZ = 0.5F;
        leg4 = new ModelRenderer(this, 48, 0);
        leg4.addBox(-1F, 0.0F, -1F, 2, i, 3, f);
        leg4.setRotationPoint(3F, 24 - i, -3F);
        leg4.rotateAngleZ = -0.5F;
        leg5 = new ModelRenderer(this, 48, 0);
        leg5.addBox(-1F, 0.0F, -2F, 2, i, 3, f);
        leg5.setRotationPoint(-3F, 24 - i, 4F);
        leg5.rotateAngleZ = 0.5F;
        leg6 = new ModelRenderer(this, 48, 0);
        leg6.addBox(-1F, 0.0F, -1F, 2, i, 3, f);
        leg6.setRotationPoint(3F, 24 - i, 4F);
        leg6.rotateAngleZ = -0.5F;
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
        leg5.render(f5);
        leg6.render(f5);
        ant.render(f5);
        eyeL.render(f5);
        eyeR.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        body.rotateAngleX = ((float)Math.PI / 2F);
        ant.rotateAngleX = ((float)Math.PI / 2F);
        eyeL.rotateAngleX = ((float)Math.PI / 2F);
        eyeR.rotateAngleX = ((float)Math.PI / 2F);
        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg5.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg6.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
    }
}

package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEvilSnowman extends ModelBase
{
    public ModelRenderer body2;
    public ModelRenderer body1;
    public ModelRenderer head;
    public ModelRenderer hatbrim;
    public ModelRenderer hat;
    public ModelRenderer arm2L;
    public ModelRenderer arm1L;
    public ModelRenderer arm2R;
    public ModelRenderer arm1R;
    public ModelRenderer nose;
    public ModelRenderer eyeL;
    public ModelRenderer eyeR;

    public ModelEvilSnowman()
    {
        this(0.0f);
    }

    public ModelEvilSnowman(float f)
    {
        this(f, 0.0f);
    }

    public ModelEvilSnowman(float f, float f1)
    {
        float f2 = 0.0F;
        body2 = new ModelRenderer(this, 20, 0);
        body2.addBox(-5F, -5F, -5F, 11, 11, 11, f2);
        body2.setRotationPoint(0.0F, 17F, 0.0F);
        body1 = new ModelRenderer(this, 24, 4);
        body1.addBox(-5F, -5F, -5F, 8, 8, 8, f2);
        body1.setRotationPoint(2.0F, 9F, 2.0F);
        head = new ModelRenderer(this, 27, 0);
        head.addBox(-3F, -6F, -3F, 6, 6, 6, f2);
        head.setRotationPoint(1.0F, 4F, 1.0F);
        hatbrim = new ModelRenderer(this, 0, 23);
        hatbrim.addBox(-4F, -7F, -4F, 8, 1, 8, f2);
        hatbrim.setRotationPoint(1.0F, 4F, 1.0F);
        hat = new ModelRenderer(this, 0, 22);
        hat.addBox(-2.5F, -12F, -2.5F, 5, 5, 5, f2);
        hat.setRotationPoint(1.0F, 4F, 1.0F);
        arm2L = new ModelRenderer(this, 0, 10);
        arm2L.addBox(-8F, 2.0F, 0.0F, 5, 1, 1, f2);
        arm2L.setRotationPoint(-3F, 6F, 0.0F);
        arm2L.rotateAngleZ = 0.13561F;
        arm1L = new ModelRenderer(this, 0, 10);
        arm1L.addBox(-8F, 0.0F, 0.0F, 8, 1, 1, f2);
        arm1L.setRotationPoint(-3F, 6F, 0.0F);
        arm1L.rotateAngleZ = -0.45203F;
        arm2R = new ModelRenderer(this, 0, 10);
        arm2R.addBox(2.0F, 3F, 0.0F, 5, 1, 1, f2);
        arm2R.setRotationPoint(5F, 6F, 0.0F);
        arm2R.rotateAngleZ = -0.40682F;
        arm1R = new ModelRenderer(this, 0, 10);
        arm1R.addBox(0.0F, 0.0F, 0.0F, 8, 1, 1, f2);
        arm1R.setRotationPoint(5F, 6F, 0.0F);
        arm1R.rotateAngleZ = 0.49723F;
        nose = new ModelRenderer(this, 10, 0);
        nose.addBox(-0.5F, -3F, -6F, 1, 1, 3, f2);
        nose.setRotationPoint(1.0F, 4F, 1.0F);
        eyeL = new ModelRenderer(this, 0, 0);
        eyeL.addBox(-2F, -5F, -3.5F, 1, 1, 1, f2);
        eyeL.setRotationPoint(1.0F, 4F, 1.0F);
        eyeR = new ModelRenderer(this, 0, 0);
        eyeR.addBox(1.0F, -5F, -3.5F, 1, 1, 1, f2);
        eyeR.setRotationPoint(1.0F, 4F, 1.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body2.render(f5);
        body1.render(f5);
        head.render(f5);
        hatbrim.render(f5);
        hat.render(f5);
        arm2L.render(f5);
        arm1L.render(f5);
        arm2R.render(f5);
        arm1R.render(f5);
        nose.render(f5);
        eyeL.render(f5);
        eyeR.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        head.rotateAngleY = f3 / (180F / (float)Math.PI);
        head.rotateAngleX = f4 / (180F / (float)Math.PI);
        hat.rotateAngleY = hatbrim.rotateAngleY = nose.rotateAngleY = eyeL.rotateAngleY = eyeR.rotateAngleY = head.rotateAngleY;
        hat.rotateAngleX = hatbrim.rotateAngleX = nose.rotateAngleX = eyeL.rotateAngleX = eyeR.rotateAngleX = head.rotateAngleX;
        arm1R.rotateAngleZ = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 3F * f1 * 0.5F + 0.3F + 0.49723F;
        arm2R.rotateAngleZ = (MathHelper.cos(f * 0.6662F + (float)Math.PI) * 3F * f1 * 0.5F + 0.3F) - 0.40682F;
        arm1L.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 3F * f1 * 0.5F - 0.5F - 0.45203F;
        arm2L.rotateAngleZ = (MathHelper.cos(f * 0.6662F) * 3F * f1 * 0.5F - 0.5F) + 0.13561F;
    }
}

package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelEvilChicken extends ModelBase
{
    public ModelRenderer headChicken;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer wingL;
    public ModelRenderer wingR;
    public ModelRenderer beak;
    public ModelRenderer gizzard;

    public ModelEvilChicken()
    {
        byte byte0 = -16;
        headChicken = new ModelRenderer(this, 0, 0);
        headChicken.addBox(-2F, -6F, -10F, 4, 8, 3, 2.0F);
        headChicken.setRotationPoint(0.0F, 22 + byte0, -4F);
        beak = new ModelRenderer(this, 38, 0);
        beak.addBox(-3F, -4F, -16F, 6, 2, 4, 0.5F);
        beak.setRotationPoint(0.0F, 24 + byte0, -4F);
        gizzard = new ModelRenderer(this, 14, 4);
        gizzard.addBox(-1F, -2F, -14F, 2, 2, 2, 1.0F);
        gizzard.setRotationPoint(0.0F, 24 + byte0, -4F);
        body = new ModelRenderer(this, 0, 9);
        body.addBox(-3F, -4F, -3F, 6, 8, 6, 4F);
        body.setRotationPoint(0.0F, 24 + byte0, 0.0F);
        leg1 = new ModelRenderer(this, 26, 0);
        leg1.addBox(-1F, 0.0F, -3F, 3, 5, 3, 4F);
        leg1.setRotationPoint(-5F, 30 + byte0, 1.0F);
        leg2 = new ModelRenderer(this, 26, 0);
        leg2.addBox(-1F, 0.0F, -3F, 3, 5, 3, 4F);
        leg2.setRotationPoint(4F, 30 + byte0, 1.0F);
        wingL = new ModelRenderer(this, 24, 13);
        wingL.addBox(0.0F, 0.0F, -3F, 1, 3, 6, 3F);
        wingL.setRotationPoint(-7F, 22 + byte0, 0.0F);
        wingR = new ModelRenderer(this, 24, 13);
        wingR.addBox(-1F, 0.0F, -3F, 1, 3, 6, 3F);
        wingR.setRotationPoint(7F, 22 + byte0, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        headChicken.render(f5);
        beak.render(f5);
        gizzard.render(f5);
        body.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        wingL.render(f5);
        wingR.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        headChicken.rotateAngleX = -(f4 / 97.29578F);
        headChicken.rotateAngleY = f3 / (180F / (float)Math.PI);
        beak.rotateAngleX = headChicken.rotateAngleX;
        beak.rotateAngleY = headChicken.rotateAngleY;
        gizzard.rotateAngleX = headChicken.rotateAngleX;
        gizzard.rotateAngleY = headChicken.rotateAngleY;
        body.rotateAngleX = ((float)Math.PI / 2F);
        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        wingL.rotateAngleZ = f2;
        wingR.rotateAngleZ = -f2;
    }
}

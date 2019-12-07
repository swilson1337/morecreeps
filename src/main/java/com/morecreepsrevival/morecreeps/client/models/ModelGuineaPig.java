package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGuineaPig extends ModelBase
{
    public ModelRenderer headGGpig;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer ears;

    public ModelGuineaPig()
    {
        this(0.0f);
    }

    public ModelGuineaPig(float f)
    {
        byte byte0 = 16;
        headGGpig = new ModelRenderer(this, 8, 8);
        headGGpig.addBox(-2F, -2F, -4F, 4, 3, 4, f);
        headGGpig.setRotationPoint(0.0F, 4 + byte0, -3F);
        body = new ModelRenderer(this, 28, 16);
        body.addBox(-2.5F, -5F, -3.5F, 5, 8, 4, f);
        body.setRotationPoint(0.0F, 3 + byte0, 1.0F);
        leg1 = new ModelRenderer(this, 6, 20);
        leg1.addBox(-1F, 0.0F, -1F, 2, 2, 2, -0.15F);
        leg1.setRotationPoint(-1.5F, 6 + byte0, 3.5F);
        leg2 = new ModelRenderer(this, 6, 20);
        leg2.addBox(-1F, 0.0F, -1F, 2, 2, 2, -0.15F);
        leg2.setRotationPoint(1.5F, 6 + byte0, 3.5F);
        leg3 = new ModelRenderer(this, 6, 20);
        leg3.addBox(-1F, 0.0F, -1F, 2, 2, 2, -0.15F);
        leg3.setRotationPoint(-1.5F, 6 + byte0, -2.5F);
        leg4 = new ModelRenderer(this, 6, 20);
        leg4.addBox(-1F, 0.0F, -1F, 2, 2, 2, -0.15F);
        leg4.setRotationPoint(1.5F, 6 + byte0, -2.5F);
        ears = new ModelRenderer(this, 11, 0);
        ears.addBox(-2.5F, -1.6F, -2.5F, 5, 1, 1, 0.0F);
        ears.setRotationPoint(0.0F, 20F, -3F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        headGGpig.render(f5);
        body.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        ears.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        ears.rotateAngleX = headGGpig.rotateAngleX = -(f4 / (180F / (float)Math.PI));
        ears.rotateAngleY = headGGpig.rotateAngleY = f3 / (180F / (float)Math.PI);
        body.rotateAngleX = ((float)Math.PI / 2F);
        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}
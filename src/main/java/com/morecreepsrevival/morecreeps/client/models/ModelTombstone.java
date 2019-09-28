package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelTombstone extends ModelBase
{
    public ModelRenderer cross1;
    public ModelRenderer cross2;

    public ModelTombstone()
    {
        this(0.0f);
    }

    public ModelTombstone(float f)
    {
        this(f, 0.0f);
    }

    public ModelTombstone(float f, float f1)
    {
        cross1 = new ModelRenderer(this, 0, 0);
        cross1.addBox(-1.5F, -13F, -1.5F, 3, 13, 3, 0.0F);
        cross1.setRotationPoint(0.0F, 25F, 0.0F);
        cross1.rotateAngleX = 0.0F;
        cross1.rotateAngleY = 0.0F;
        cross1.rotateAngleZ = 0.0F;
        cross1.mirror = false;
        cross2 = new ModelRenderer(this, 0, 16);
        cross2.addBox(-5F, -10F, -1.5F, 10, 3, 3, -0.25F);
        cross2.setRotationPoint(0.0F, 25F, 0.0F);
        cross2.rotateAngleX = 0.0F;
        cross2.rotateAngleY = 0.0F;
        cross2.rotateAngleZ = 0.0F;
        cross2.mirror = false;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        cross1.render(f5);
        cross2.render(f5);
    }
}

package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

import javax.annotation.Nonnull;

public class ModelPonyCloud extends ModelBase
{
    public ModelRenderer headCloud;
    public ModelRenderer puff1;
    public ModelRenderer puff2;
    public ModelRenderer puff3;
    public ModelRenderer puff4;
    public ModelRenderer puff5;
    public ModelRenderer puff6;

    public ModelPonyCloud()
    {
        headCloud = new ModelRenderer(this, 0, 0);
        headCloud.addBox(-5.0f, -2.0f, -4.0f, 10, 4, 4);
        headCloud.setRotationPoint(0.0f, 5.0f, 0.0f);

        puff1 = new ModelRenderer(this, 0, 15);
        puff1.addBox(0.0f, 0.0f, 0.0f, 13, 4, 12);
        puff1.setRotationPoint(-11.0f, 7.0f, -6.0f);

        puff2 = new ModelRenderer(this, 0, 10);
        puff2.addBox(0.0f, 0.0f, 0.0f, 13, 4, 12);
        puff2.setRotationPoint(0.0f, 8.0f, -2.0f);

        puff3 = new ModelRenderer(this, 0, 12);
        puff3.addBox(-5.0f, 0.0f, 0.0f, 8, 6, 6);
        puff3.setRotationPoint(-4.0f, 9.0f, 0.0f);

        puff4 = new ModelRenderer(this, 0, 12);
        puff4.addBox(0.0f, 0.0f, 0.0f, 8, 6, 6);
        puff4.setRotationPoint(0.0f, 3.0f, 0.0f);

        puff5 = new ModelRenderer(this, 0, 8);
        puff5.addBox(0.0f, 0.0f, 0.0f, 20, 3, 12);
        puff5.setRotationPoint(-3.0f, 10.0f, -4.0f);

        puff6 = new ModelRenderer(this, 0, 16);
        puff6.addBox(0.0f, 0.0f, 0.0f, 11, 4, 9);
        puff6.setRotationPoint(-15.0f, 8.0f, -4.0f);
    }

    @Override
    public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        super.render(entity, f, f1, f2, f3, f4, f5);
        setRotationAngles(f, f1, f2, f3, f4, f, entity);
        headCloud.render(f5);
        puff1.render(f5);
        puff2.render(f5);
        puff3.render(f5);
        puff4.render(f5);
        puff5.render(f5);
        puff6.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, @Nonnull Entity entity)
    {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    }
}

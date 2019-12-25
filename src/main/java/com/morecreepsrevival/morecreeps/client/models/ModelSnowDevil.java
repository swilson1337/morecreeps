package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSnowDevil extends ModelBase
{
    public ModelRenderer horn1;
    public ModelRenderer horn2;
    public ModelRenderer horn3;
    public ModelRenderer horn4;
    public ModelRenderer headSnowdevil;
    public ModelRenderer headSnowdevil2;
    public ModelRenderer butt;
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;

    public ModelSnowDevil()
    {
        this(6, 0.1f);
    }

    public ModelSnowDevil(float f)
    {
        this(6, f + 10.0f);
    }

    public ModelSnowDevil(int i, float f)
    {
        horn1 = new ModelRenderer(this, 44, 0);
        horn1.addBox(8F, -9F, -4F, 1, 3, 1, f);
        horn1.setRotationPoint(0.0F, 18 - i, -6F);
        horn2 = new ModelRenderer(this, 44, 0);
        horn2.addBox(3F, -9F, -4F, 1, 3, 1, f);
        horn2.setRotationPoint(0.0F, 18 - i, -6F);
        horn3 = new ModelRenderer(this, 44, 0);
        horn3.addBox(-4F, -9F, -4F, 1, 3, 1, f);
        horn3.setRotationPoint(0.0F, 18 - i, -6F);
        horn4 = new ModelRenderer(this, 44, 0);
        horn4.addBox(-9F, -9F, -4F, 1, 3, 1, f);
        horn4.setRotationPoint(0.0F, 18 - i, -6F);
        headSnowdevil = new ModelRenderer(this, 0, 0);
        headSnowdevil.addBox(2.0F, -6F, -7F, 8, 8, 8, f);
        headSnowdevil.setRotationPoint(0.0F, 18 - i, -6F);
        headSnowdevil2 = new ModelRenderer(this, 0, 0);
        headSnowdevil2.addBox(-10F, -6F, -7F, 8, 8, 8, f);
        headSnowdevil2.setRotationPoint(0.0F, 18 - i, -6F);
        butt = new ModelRenderer(this, 44, 0);
        butt.addBox(-3F, 4F, -4F, 5, 5, 3, f);
        butt.setRotationPoint(0.0F, 17 - i, 2.0F);
        body = new ModelRenderer(this, 28, 8);
        body.addBox(-5F, -10F, -7F, 10, 16, 8, f);
        body.setRotationPoint(0.0F, 17 - i, 2.0F);
        leg1 = new ModelRenderer(this, 0, 16);
        leg1.addBox(-2F, 0.0F, -2F, 4, i, 4, f);
        leg1.setRotationPoint(-3F, 24 - i, 7F);
        leg2 = new ModelRenderer(this, 0, 16);
        leg2.addBox(-2F, 0.0F, -2F, 4, i, 4, f);
        leg2.setRotationPoint(3F, 24 - i, 7F);
        leg3 = new ModelRenderer(this, 0, 16);
        leg3.addBox(-2F, 0.0F, -2F, 4, i, 4, f);
        leg3.setRotationPoint(-3F, 24 - i, -5F);
        leg4 = new ModelRenderer(this, 0, 16);
        leg4.addBox(-2F, 0.0F, -2F, 4, i, 4, f);
        leg4.setRotationPoint(3F, 24 - i, -5F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        horn1.render(f5);
        horn2.render(f5);
        horn3.render(f5);
        horn4.render(f5);
        headSnowdevil.render(f5);
        headSnowdevil2.render(f5);
        butt.render(f5);
        body.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        horn1.rotateAngleX = -(f4 / (180F / (float)Math.PI));
        horn1.rotateAngleY = f3 / (180F / (float)Math.PI);
        horn2.rotateAngleX = -(f4 / (180F / (float)Math.PI));
        horn2.rotateAngleY = f3 / (180F / (float)Math.PI);
        horn3.rotateAngleX = -(f4 / (180F / (float)Math.PI));
        horn3.rotateAngleY = f3 / (180F / (float)Math.PI);
        horn4.rotateAngleX = -(f4 / (180F / (float)Math.PI));
        horn4.rotateAngleY = f3 / (180F / (float)Math.PI);
        headSnowdevil.rotateAngleX = -(f4 / (180F / (float)Math.PI));
        headSnowdevil.rotateAngleY = f3 / (180F / (float)Math.PI);
        headSnowdevil2.rotateAngleX = -(f4 / (180F / (float)Math.PI));
        headSnowdevil2.rotateAngleY = f3 / (180F / (float)Math.PI);
        butt.rotateAngleX = ((float)Math.PI / 2F) + (float)((double)f1 * 0.053999999999999999D);
        body.rotateAngleX = ((float)Math.PI / 2F);
        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}

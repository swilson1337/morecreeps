package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCamel extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer hump1;
    public ModelRenderer neck1;
    public ModelRenderer tail;
    public ModelRenderer neck2;
    public ModelRenderer headCamel;
    public ModelRenderer earR;
    public ModelRenderer earL;
    public ModelRenderer mouth;
    public float tailwag;
    public int taildirection;

    public ModelCamel()
    {
        float f2 = 0.0F;
        body = new ModelRenderer(this, 36, 0);
        body.addBox(-1F, -3F, -4.5F, 2, 6, 9, 3.9F);
        body.setRotationPoint(0.0F, 4F, 0.0F);
        leg1 = new ModelRenderer(this, 38, 15);
        leg1.addBox(-1.5F, 0.0F, -1.5F, 3, 14, 3, -0.25F);
        leg1.setRotationPoint(3F, 10F, -6F);
        leg2 = new ModelRenderer(this, 38, 15);
        leg2.addBox(-1.5F, 0.0F, -1.5F, 3, 14, 3, -0.25F);
        leg2.setRotationPoint(-3F, 10F, -6F);
        leg3 = new ModelRenderer(this, 38, 15);
        leg3.addBox(-1.5F, 0.0F, -2F, 3, 14, 3, -0.25F);
        leg3.setRotationPoint(3F, 10F, 7F);
        leg4 = new ModelRenderer(this, 38, 15);
        leg4.addBox(-1.5F, 0.0F, -2F, 3, 14, 3, -0.25F);
        leg4.setRotationPoint(-3F, 10F, 7F);
        hump1 = new ModelRenderer(this, 28, 0);
        hump1.addBox(-4F, -5F, -4F, 8, 5, 8, 0.45F);
        hump1.setRotationPoint(0.0F, -2F, 0.0F);
        neck1 = new ModelRenderer(this, 36, 0);
        neck1.addBox(-2.5F, -2F, 0.0F, 5, 5, 9, f2);
        neck1.setRotationPoint(0.0F, 2.0F, -7F);
        neck1.rotateAngleX = -2.53136F;
        tail = new ModelRenderer(this, 0, 22);
        tail.addBox(-1F, -1F, -7.5F, 2, 2, 8, f2);
        tail.setRotationPoint(0.0F, 4F, 8F);
        tail.rotateAngleX = 2.16973F;
        tail.rotateAngleY = -0.0452F;
        neck2 = new ModelRenderer(this, 32, 0);
        neck2.addBox(-2.5F, -2F, 0.0F, 5, 5, 11, -0.5F);
        neck2.setRotationPoint(0.0F, 7F, -12F);
        neck2.rotateAngleX = 2.53136F;
        headCamel = new ModelRenderer(this, 0, 0);
        headCamel.addBox(-2.5F, -2F, -8F, 5, 5, 8, f2);
        headCamel.setRotationPoint(0.0F, 0.0F, -19F);
        earR = new ModelRenderer(this, 0, 0);
        earR.addBox(-2.5F, -4F, -1.5F, 1, 2, 1, f2);
        earR.setRotationPoint(0.0F, 0.0F, -19F);
        earL = new ModelRenderer(this, 0, 3);
        earL.addBox(1.5F, -4F, -1.5F, 1, 2, 1, f2);
        earL.setRotationPoint(0.0F, 0.0F, -19F);
        mouth = new ModelRenderer(this, 20, 25);
        mouth.addBox(-2F, 0.5F, -9F, 4, 1, 4, f2);
        mouth.setRotationPoint(0.0F, 0.0F, -19F);
        mouth.rotateAngleX = 0.29382F;
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
        hump1.render(f5);
        neck1.render(f5);
        tail.render(f5);
        neck2.render(f5);
        headCamel.render(f5);
        earR.render(f5);
        earL.render(f5);
        mouth.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        headCamel.rotateAngleY = f3 / (180F / (float)Math.PI);
        headCamel.rotateAngleX = f4 / (180F / (float)Math.PI);
        mouth.rotateAngleY = earR.rotateAngleY = earL.rotateAngleY = headCamel.rotateAngleY;
        earR.rotateAngleX = earL.rotateAngleX = headCamel.rotateAngleX;
        mouth.rotateAngleX = headCamel.rotateAngleX + 0.29382F;

        if (taildirection > 0)
        {
            tailwag += 0.0002F;

            if (tailwag > 0.067F)
            {
                taildirection = taildirection * -1;
            }
        }
        else
        {
            tailwag -= 0.0002F;

            if ((double)tailwag < -0.067000000000000004D)
            {
                taildirection = taildirection * -1;
            }
        }

        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}

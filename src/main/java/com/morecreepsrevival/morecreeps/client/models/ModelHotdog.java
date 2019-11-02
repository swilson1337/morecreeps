package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelHotdog extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer neck;
    public ModelRenderer headDog;
    public ModelRenderer snout;
    public ModelRenderer nose;
    public ModelRenderer earL;
    public ModelRenderer earR;
    public ModelRenderer mouth;
    public ModelRenderer tail;
    public ModelRenderer saddle;
    public ModelRenderer helmet;
    public ModelRenderer hoof2;
    public ModelRenderer hoof1;
    public ModelRenderer hoof4;
    public ModelRenderer hoof3;
    public ModelRenderer strapR;
    public ModelRenderer strapL;
    public float tailwag;
    public int taildirection;

    public ModelHotdog()
    {
        this(0.0f);
    }

    public ModelHotdog(float f)
    {
        this(f, 0.0f);
    }

    public ModelHotdog(float f, float f1)
    {
        taildirection = 1;
        float f2 = 0.0F;
        float f3 = 0.0F;
        body = new ModelRenderer(this, 30, 0);
        body.addBox(-2F, -1.3F, -6F - f3, 5, 3, 12, f2);
        body.setRotationPoint(0.0F, 20.5F, 0.0F + f3);
        leg1 = new ModelRenderer(this, 0, 22);
        leg1.addBox(-1F, 0.0F, -1F, 2, 2, 2, -0.2F);
        leg1.setRotationPoint(-1F, 22F, -3F - f3);
        leg2 = new ModelRenderer(this, 0, 22);
        leg2.addBox(-1F, 0.0F, -1F, 2, 2, 2, -0.2F);
        leg2.setRotationPoint(2.0F, 22F, -3F - f3);
        leg3 = new ModelRenderer(this, 0, 22);
        leg3.addBox(-1F, 0.0F, -1F, 2, 2, 2, -0.2F);
        leg3.setRotationPoint(-1F, 22F, 5F - f3);
        leg4 = new ModelRenderer(this, 0, 22);
        leg4.addBox(-1F, 0.0F, -1F, 2, 2, 2, -0.2F);
        leg4.setRotationPoint(2.0F, 22F, 5F - f3);
        neck = new ModelRenderer(this, 9, 24);
        neck.addBox(-1.5F, -5F, -1F, 3, 5, 3, f2);
        neck.setRotationPoint(0.5F, 20.5F, -5F - f3);
        neck.rotateAngleX = 0.58764F;
        headDog = new ModelRenderer(this, 0, 0);
        headDog.addBox(-2F, -1.5F, -4F, 4, 3, 4, f2);
        headDog.setRotationPoint(0.5F, 16.5F, -6F - f3);
        snout = new ModelRenderer(this, 0, 8);
        snout.addBox(-1.5F, -0.5F, -8F, 3, 2, 4, f2);
        snout.setRotationPoint(0.5F, 16.5F, -6F - f3);
        nose = new ModelRenderer(this, 0, 28);
        nose.addBox(-0.5F, -1F, -8.5F, 1, 1, 1, f2);
        nose.setRotationPoint(0.5F, 16.5F, -6F - f3);
        earL = new ModelRenderer(this, 16, 0);
        earL.addBox(0.0F, -1F, 1.0F, 1, 3, 2, f2);
        earL.setRotationPoint(0.5F, 16.5F, -6F - f3);
        earL.rotateAngleX = -2.66696F;
        earL.rotateAngleY = 1.44649F;
        earR = new ModelRenderer(this, 16, 0);
        earR.addBox(0.0F, -1F, -3.2F, 1, 3, 2, f2);
        earR.setRotationPoint(0.5F, 16.5F, -6F - f3);
        earR.rotateAngleX = 2.66696F;
        earR.rotateAngleY = 1.71771F;
        mouth = new ModelRenderer(this, 0, 16);
        mouth.addBox(-1F, -1.5F, -7F, 2, 1, 4, f2);
        mouth.setRotationPoint(0.5F, 16.5F, -6F - f3);
        mouth.rotateAngleX = 0.54243F;
        tail = new ModelRenderer(this, 30, 0);
        tail.addBox(-0.5F, 0.0F, 0.0F, 1, 1, 4, f2);
        tail.setRotationPoint(0.5F, 20F, 6F - f3);
        tail.rotateAngleX = -0.54243F;
        tail.rotateAngleY = 0.0F;
        tail.rotateAngleZ = 0.0F;
        saddle = new ModelRenderer(this, 40, 16);
        saddle.addBox(-2.5F, 0.0F, -3F, 6, 2, 6, f2);
        saddle.setRotationPoint(0.0F, 18.5F, 0.0F - f3);
        helmet = new ModelRenderer(this, 14, 8);
        helmet.addBox(-1.5F, -2F, -3.5F, 3, 1, 3, 0.3F);
        helmet.setRotationPoint(0.5F, 16F, -6F - f3);
        hoof2 = new ModelRenderer(this, 12, 16);
        hoof2.addBox(-1F, 2.0F, -1F, 2, 1, 2, f2);
        hoof2.setRotationPoint(2.0F, 21F, -3F - f3);
        hoof1 = new ModelRenderer(this, 12, 16);
        hoof1.addBox(-1F, 2.0F, -1F, 2, 1, 2, f2);
        hoof1.setRotationPoint(-1F, 21F, -3F - f3);
        hoof4 = new ModelRenderer(this, 12, 16);
        hoof4.addBox(-1F, 2.0F, -1F, 2, 1, 2, f2);
        hoof4.setRotationPoint(2.0F, 21F, 5F - f3);
        hoof3 = new ModelRenderer(this, 12, 16);
        hoof3.addBox(-1F, 2.0F, -1F, 2, 1, 2, f2);
        hoof3.setRotationPoint(-1F, 21F, 5F - f3);
        strapR = new ModelRenderer(this, 20, 16);
        strapR.addBox(1.5F, -2F, -3F, 1, 3, 1, -0.25F);
        strapR.setRotationPoint(0.5F, 16.5F, -6F - f3);
        strapR.rotateAngleX = -0.13561F;
        strapL = new ModelRenderer(this, 20, 16);
        strapL.addBox(-2.5F, -2F, -3F, 1, 3, 1, -0.25F);
        strapL.setRotationPoint(0.5F, 16.5F, -6F - f3);
        strapL.rotateAngleX = -0.13561F;
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
        neck.render(f5);
        headDog.render(f5);
        snout.render(f5);
        nose.render(f5);
        earL.render(f5);
        earR.render(f5);
        mouth.render(f5);
        tail.render(f5);
        saddle.render(f5);
        helmet.render(f5);
        hoof2.render(f5);
        hoof1.render(f5);
        hoof4.render(f5);
        hoof3.render(f5);
        strapR.render(f5);
        strapL.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        headDog.rotateAngleY = f3 / 97.29578F;
        headDog.rotateAngleX = f4 / 97.29578F;
        strapL.rotateAngleY = strapR.rotateAngleY = helmet.rotateAngleY = snout.rotateAngleY = nose.rotateAngleY = headDog.rotateAngleY;
        strapL.rotateAngleX = strapR.rotateAngleX = snout.rotateAngleX = nose.rotateAngleX = headDog.rotateAngleX;
        helmet.rotateAngleX = headDog.rotateAngleX - 0.13561F;
        earL.rotateAngleY = headDog.rotateAngleY + 1.44649F;
        earR.rotateAngleY = headDog.rotateAngleY + 1.71771F;
        earL.rotateAngleX = headDog.rotateAngleX - 2.66696F;
        earR.rotateAngleX = headDog.rotateAngleX + 2.66696F;

        if (taildirection > 0)
        {
            tailwag += 0.001F;

            if (tailwag > 0.077F)
            {
                taildirection = taildirection * -1;
            }
        }
        else
        {
            tailwag -= 0.001F;

            if ((double)tailwag < -0.076999999999999999D)
            {
                taildirection = taildirection * -1;
            }
        }

        tail.rotateAngleY = MathHelper.cos(f2 * 0.6662F + (float)Math.PI) * 1.4F;
        mouth.rotateAngleX = headDog.rotateAngleX + 0.54243F + tailwag;
        mouth.rotateAngleY = headDog.rotateAngleY;
        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        hoof1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        hoof2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        hoof3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        hoof4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}

package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class ModelEvilPig extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer headEvilpig;
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer headEvilpig1;
    public ModelRenderer headEvilpig2;
    public ModelRenderer headEvilpig3;
    public ModelRenderer headEvilpig4;
    public ModelRenderer headEvilpig5;

    public static Random rand = new Random();

    public int headEvilPigPop = 0;

    public ModelEvilPig()
    {
        byte byte0 = 6;
        headEvilpig = new ModelRenderer(this, 0, 0);
        headEvilpig.addBox(-16F, -4F, -10F, 8, 8, 8);
        headEvilpig.setRotationPoint(0.0F, 23 - byte0, -6F);
        headEvilpig1 = new ModelRenderer(this, 0, 0);
        headEvilpig1.addBox(-4F, -4F, -13F, 8, 8, 8);
        headEvilpig1.setRotationPoint(0.0F, 23 - byte0, -6F);
        headEvilpig2 = new ModelRenderer(this, 0, 0);
        headEvilpig2.addBox(8F, -4F, -10F, 8, 8, 8);
        headEvilpig2.setRotationPoint(0.0F, 23 - byte0, -6F);
        headEvilpig3 = new ModelRenderer(this, 0, 0);
        headEvilpig3.addBox(-16F, -4F, -6F, 8, 8, 8);
        headEvilpig3.setRotationPoint(0.0F, 12 - byte0, -6F);
        headEvilpig4 = new ModelRenderer(this, 0, 0);
        headEvilpig4.addBox(-4F, -4F, -9F, 8, 8, 8);
        headEvilpig4.setRotationPoint(0.0F, 12 - byte0, -6F);
        headEvilpig5 = new ModelRenderer(this, 0, 0);
        headEvilpig5.addBox(8F, -4F, -6F, 8, 8, 8);
        headEvilpig5.setRotationPoint(0.0F, 12 - byte0, -6F);
        body = new ModelRenderer(this, 20, 8);
        body.addBox(-10F, -10F, -7F, 20, 16, 12);
        body.setRotationPoint(0.0F, 17 - byte0, 2.0F);
        leg1 = new ModelRenderer(this, 0, 16);
        leg1.addBox(-2F, 0.0F, -2F, 4, byte0, 4);
        leg1.setRotationPoint(-8F, 24 - byte0, 7F);
        leg2 = new ModelRenderer(this, 0, 16);
        leg2.addBox(-2F, 0.0F, -2F, 4, byte0, 4);
        leg2.setRotationPoint(8F, 24 - byte0, 7F);
        leg3 = new ModelRenderer(this, 0, 16);
        leg3.addBox(-2F, 0.0F, -2F, 4, byte0, 4);
        leg3.setRotationPoint(-8F, 24 - byte0, -5F);
        leg4 = new ModelRenderer(this, 0, 16);
        leg4.addBox(-2F, 0.0F, -2F, 4, byte0, 4);
        leg4.setRotationPoint(8F, 24 - byte0, -5F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        headEvilpig.render(f5);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        headEvilpig1.render(f5);
        headEvilpig2.render(f5);
        headEvilpig3.render(f5);
        headEvilpig4.render(f5);
        headEvilpig5.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        body.rotateAngleX = ((float)Math.PI / 2F);
        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F + 0.0F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F + 0.0F) * 1.4F * f1;
        headEvilpig.rotateAngleX = -(f4 / 50.29578F);
        headEvilpig.rotateAngleY = f3 / 50.29578F;
        headEvilpig1.rotateAngleX = -(f4 / (180F / (float)Math.PI));
        headEvilpig1.rotateAngleY = f3 / (180F / (float)Math.PI);
        headEvilpig2.rotateAngleX = -(f4 / 52.29578F);
        headEvilpig2.rotateAngleY = f3 / 52.29578F;
        headEvilpig3.rotateAngleX = -(f4 / 52.29578F);
        headEvilpig3.rotateAngleY = f3 / 52.29578F;
        headEvilpig4.rotateAngleX = -(f4 / (180F / (float)Math.PI));
        headEvilpig4.rotateAngleY = f3 / (180F / (float)Math.PI);
        headEvilpig5.rotateAngleX = -(f4 / 50.29578F);
        headEvilpig5.rotateAngleY = f3 / 50.29578F;

        if (rand.nextInt(50) == 0)
        {
            headEvilPigPop++;

            if (headEvilPigPop > 5)
            {
                headEvilPigPop = 0;
            }

            headEvilpig.rotationPointZ = -10F;
            headEvilpig1.rotationPointZ = -13F;
            headEvilpig2.rotationPointZ = -10F;
            headEvilpig3.rotationPointZ = -6F;
            headEvilpig4.rotationPointZ = -9F;
            headEvilpig5.rotationPointZ = -6F;

            switch (headEvilPigPop)
            {
                case 0:
                case 2:
                    headEvilpig.rotationPointZ = -13F;

                    break;
                case 1:
                    headEvilpig.rotationPointZ = -16F;

                    break;
                case 3:
                case 5:
                    headEvilpig.rotationPointZ = -9F;

                    break;
                case 4:
                    headEvilpig.rotationPointZ = -12F;

                    break;
                default:
                    break;
            }
        }
    }
}

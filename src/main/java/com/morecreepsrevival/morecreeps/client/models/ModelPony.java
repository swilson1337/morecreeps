package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;

public class ModelPony extends ModelBase
{
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer body;
    public ModelRenderer mane2;
    public ModelRenderer neck;
    public ModelRenderer ponyHead;
    public ModelRenderer eyeL;
    public ModelRenderer snout;
    public ModelRenderer earL;
    public ModelRenderer mane1;
    public ModelRenderer tail;
    public ModelRenderer eyeR;
    public ModelRenderer earR;
    public float tailwag;
    public int taildirection;
    public ModelRenderer tail2;
    public ModelRenderer saddle;
    public ModelRenderer leg2hoof;
    public ModelRenderer leg1hoof;
    public ModelRenderer leg3hoof;
    public ModelRenderer leg4hoof;
    public float modelsize;
    public ModelRenderer saddle2;
    public ModelRenderer saddle3;
    public ModelRenderer saddle4;
    public ModelRenderer saddle5;
    public ModelRenderer wingL;
    public ModelRenderer wingR;
    public ModelRenderer horn;
    public boolean attackpose;
    public int sicky;
    public boolean adult;
    public int ponybreed;
    protected float moveForward;
    protected boolean notmoving;

    public ModelPony()
    {
        taildirection = 1;

        this.leg1 = new ModelRenderer(this, 18, 23);
        this.leg1.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.leg1.setRotationPoint(-1.5F, 17.0F, -2.0F);

        this.leg2 = new ModelRenderer(this, 18, 23);
        this.leg2.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.leg2.setRotationPoint(2.5F, 17.0F, -2.0F);


        this.leg3 = new ModelRenderer(this, 18, 23);
        this.leg3.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.leg3.setRotationPoint(2.5F, 17.0F, 7.0F);


        this.leg4 = new ModelRenderer(this, 18, 23);
        this.leg4.addBox(-1.0F, 0.0F, -1.0F, 2, 7, 2, 0.0F);
        this.leg4.setRotationPoint(-1.5F, 17.0F, 7.0F);

        this.body = new ModelRenderer(this, 26, 12);
        this.body.addBox(-3.5F, -4.0F, -6.0F, 7, 8, 12, 0.0F);
        this.body.setRotationPoint(0.5F, 13.0F, 2.0F);


        this.mane2 = new ModelRenderer(this, 54, 0);
        this.mane2.addBox(0.0F, 0.0F, 0.0F, 2, 9, 3, -0.1F);
        this.mane2.setRotationPoint(-0.5F, 4.5F, -7.666667F);

        this.mane2.rotateAngleX = 0.4461433F;

        this.neck = new ModelRenderer(this, 18, 0);
        this.neck.addBox(-1.5F, 0.0F, 0.0F, 3, 7, 3, 0.0F);
        this.neck.setRotationPoint(0.5F, 8.0F, -8.6F);

        this.neck.rotateAngleX = 0.7063936F;

        this.ponyHead = new ModelRenderer(this, 0, 0);
        this.ponyHead.addBox(-3.0F, -6.0F, -3.0F, 4, 4, 5, 0.0F);
        this.ponyHead.setRotationPoint(1.5F, 10.0F, -7.0F);



        this.eyeL = new ModelRenderer(this, 7, 18);
        this.eyeL.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.eyeL.setRotationPoint(2.0F, 5.0F, -9.0F);



        this.snout = new ModelRenderer(this, 0, 11);
        this.snout.addBox(0.0F, 0.0F, 0.0F, 3, 3, 4, 0.0F);
        this.snout.setRotationPoint(-1.0F, 5.0F, -14.0F);


        this.earL = new ModelRenderer(this, 7, 20);
        this.earL.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, -0.3F);
        this.earL.setRotationPoint(1.5F, 2.0F, -8.0F);

        this.mane1 = new ModelRenderer(this, 18, 12);
        this.mane1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 6, 0.0F);
        this.mane1.setRotationPoint(-0.5F, 3.933333F, -10.96667F);

        this.mane1.rotateAngleX = 0.3137737F;

        this.tail = new ModelRenderer(this, 0, 24);
        this.tail.addBox(-0.5F, 0.0F, 1.0F, 1, 6, 1, 0.25F);
        this.tail.setRotationPoint(0.5F, 12.0F, 5.966667F);

        this.tail.rotateAngleX = 0.5235988F;


        this.eyeR = new ModelRenderer(this, 0, 18);
        this.eyeR.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        this.eyeR.setRotationPoint(-2.0F, 5.0F, -9.0F);

        this.earR = new ModelRenderer(this, 0, 20);
        this.earR.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, -0.3F);
        this.earR.setRotationPoint(-1.5F, 2.0F, -8.0F);

        this.tail2 = new ModelRenderer(this, 14, 11);
        this.tail2.addBox(-0.5F, 5.5F, 0.0F, 1, 3, 1, 0.75F);
        this.tail2.setRotationPoint(0.5F, 12.0F, 7.0F);

        this.tail2.rotateAngleX = 0.5235988F;

        this.saddle = new ModelRenderer(this, 30, 0);
        this.saddle.addBox(-2.0F, 0.0F, -2.5F, 4, 1, 5, 1.95F);
        this.saddle.setRotationPoint(0.5F, 10.4F, 2.0F);

        this.leg2hoof = new ModelRenderer(this, 4, 24);
        this.leg2hoof.addBox(-1.0F, 5.0F, -1.0F, 2, 2, 2, 0.25F);
        this.leg2hoof.setRotationPoint(2.5F, 17.0F, -2.0F);

        this.leg1hoof = new ModelRenderer(this, 4, 24);
        this.leg1hoof.addBox(-1.0F, 5.0F, -1.0F, 2, 2, 2, 0.25F);
        this.leg1hoof.setRotationPoint(-1.5F, 17.0F, -2.0F);

        this.leg3hoof = new ModelRenderer(this, 4, 24);
        this.leg3hoof.addBox(-1.0F, 5.0F, -1.0F, 2, 2, 2, 0.25F);
        this.leg3hoof.setRotationPoint(2.5F, 17.0F, 7.0F);

        this.leg4hoof = new ModelRenderer(this, 4, 24);
        this.leg4hoof.addBox(-1.0F, 5.0F, -1.0F, 2, 2, 2, 0.25F);
        this.leg4hoof.setRotationPoint(-1.5F, 17.0F, 7.0F);

        this.saddle2 = new ModelRenderer(this, 30, 6);
        this.saddle2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.25F);
        this.saddle2.setRotationPoint(3.5F, 13.7F, 1.0F);

        this.saddle3 = new ModelRenderer(this, 30, 6);
        this.saddle3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 3, 0.25F);
        this.saddle3.setRotationPoint(-3.5F, 13.7F, 1.0F);

        this.saddle4 = new ModelRenderer(this, 38, 10);
        this.saddle4.addBox(-3.5F, -4.9F, -5.0F, 5, 1, 1, 0.15F);
        this.saddle4.setRotationPoint(1.5F, 13.0F, 2.0F);


        this.saddle5 = new ModelRenderer(this, 38, 10);
        this.saddle5.addBox(-3.5F, -4.9F, 4.0F, 5, 1, 1, 0.15F);
        this.saddle5.setRotationPoint(1.5F, 13.0F, 2.0F);

        this.wingL = new ModelRenderer(this, 16, 3);
        this.wingL.addBox(0.0F, 0.0F, -4.5F, 1, 13, 9);
        this.wingL.setRotationPoint(4.0F, 9.0F, 2.0F);

        this.wingR = new ModelRenderer(this, 16, 3);
        this.wingR.addBox(0.0F, 0.0F, -4.5F, 1, 13, 9);
        this.wingR.setRotationPoint(-4.0F, 9.0F, 2.0F);

        this.horn = new ModelRenderer(this, 0, 25);
        this.horn.addBox(0.0F, -5.0F, 0.0F, 1, 5, 1);
        this.horn.setRotationPoint(0.0F, 3.0F, -8.0F);
        this.horn.rotateAngleX = 0.2617994F;
    }

    @Override
    public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        if (!this.adult)
        {
            float var8 = 2.0F;
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 5.0F * f5, 2.0F * f5);
            this.ponyHead.render(f5);
            this.eyeL.render(f5);
            this.snout.render(f5);
            this.earL.render(f5);
            this.eyeR.render(f5);
            this.earR.render(f5);
            this.mane2.render(f5);
            this.mane1.render(f5);
            this.neck.render(f5);
            if (this.ponybreed == 8)
            {
                this.horn.render(f5);
            }
            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(1.0F / var8, 1.0F / var8, 1.0F / var8);
            GlStateManager.translate(0.0F, 24.0F * f5, 0.0F);
            this.leg1.render(f5);
            this.leg2.render(f5);
            this.leg3.render(f5);
            this.leg4.render(f5);
            this.body.render(f5);
            this.tail.render(f5);
            this.tail2.render(f5);
            this.saddle.render(f5);
            this.leg2hoof.render(f5);
            this.leg1hoof.render(f5);
            this.leg3hoof.render(f5);
            this.leg4hoof.render(f5);
            this.saddle2.render(f5);
            this.saddle3.render(f5);
            this.saddle4.render(f5);
            this.saddle5.render(f5);
            if (this.ponybreed == 8)
            {
                this.wingL.render(f5);
                this.wingR.render(f5);
            }
            GlStateManager.popMatrix();
        }
        else
        {
            this.leg1.render(f5);
            this.leg2.render(f5);
            this.leg3.render(f5);
            this.leg4.render(f5);
            this.body.render(f5);
            this.mane2.render(f5);
            this.neck.render(f5);
            this.ponyHead.render(f5);
            this.eyeL.render(f5);
            this.snout.render(f5);
            this.earL.render(f5);
            this.mane1.render(f5);
            this.tail.render(f5);
            this.eyeR.render(f5);
            this.earR.render(f5);
            this.tail2.render(f5);
            this.saddle.render(f5);
            this.leg2hoof.render(f5);
            this.leg1hoof.render(f5);
            this.leg3hoof.render(f5);
            this.leg4hoof.render(f5);
            this.saddle2.render(f5);
            this.saddle3.render(f5);
            this.saddle4.render(f5);
            this.saddle5.render(f5);
            if (this.ponybreed == 8)
            {
                this.wingL.render(f5);
                this.wingR.render(f5);
                this.horn.render(f5);
            }
        }
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, @Nonnull Entity entity)
    {
        if (this.taildirection > 0)
        {
            this.tailwag += 2.2E-5F;
            if (this.tailwag > 0.067F)
            {
                this.taildirection *= -1;
            }
        }
        else
        {
            this.tailwag -= 2.2E-5F;
            if (this.tailwag < -0.067D)
            {
                this.taildirection *= -1;
            }
        }

        if (this.attackpose)
        {
            if (this.ponybreed == 8)
            {
                this.wingL.rotateAngleZ = MathHelper.sin(f2 * 0.6662F + 3.141593F) * 0.24F - 1.33F;
                this.wingR.rotateAngleZ = -MathHelper.sin(f2 * 0.6662F + 3.141593F) * 0.24F + 1.33F;
            }

            this.body.rotateAngleX = -0.22307166F;

            this.leg1.setRotationPoint(-1.5F, 15.0F, -3.0F);
            this.leg2.setRotationPoint(2.5F, 15.0F, -3.0F);
            this.leg1hoof.setRotationPoint(-1.5F, 15.0F, -3.0F);
            this.leg2hoof.setRotationPoint(2.5F, 15.0F, -3.0F);
            this.leg1.rotateAngleX = MathHelper.sin(f * 0.6662F) * 1.4F * f1 - 1.375F;
            this.leg2.rotateAngleX = MathHelper.sin(f * 0.6662F + 3.141593F) * 1.4F * f1 - 1.375F;
            this.leg3.rotateAngleX = MathHelper.sin(f * 0.6662F + 3.141593F) * 1.4F * f1;
            this.leg4.rotateAngleX = MathHelper.sin(f * 0.6662F) * 1.4F * f1;

            this.mane2.setRotationPoint(-0.5F, 2.5F, -7.666667F);
            this.neck.setRotationPoint(0.5F, 6.0F, -8.6F);

            this.ponyHead.setRotationPoint(1.5F, 8.0F, -7.0F);
            this.eyeL.setRotationPoint(2.0F, 3.0F, -9.0F);
            this.snout.setRotationPoint(-1.0F, 3.0F, -14.0F);
            this.earL.setRotationPoint(1.5F, 0.0F, -8.0F);
            this.mane1.setRotationPoint(-0.5F, 1.933333F, -10.96667F);
            this.eyeR.setRotationPoint(-2.0F, 3.0F, -9.0F);
            this.earR.setRotationPoint(-1.5F, 0.0F, -8.0F);

            this.horn.setRotationPoint(0.0F, 1.0F, -8.0F);
        }
        else
        {
            this.leg1.setRotationPoint(-1.5F, 17.0F, -2.0F);
            this.leg2.setRotationPoint(2.5F, 17.0F, -2.0F);
            this.leg1hoof.setRotationPoint(-1.5F, 17.0F, -2.0F);
            this.leg2hoof.setRotationPoint(2.5F, 17.0F, -2.0F);
            this.leg1.rotateAngleX = MathHelper.sin(f * 0.6662F) * 1.4F * f1;
            this.leg2.rotateAngleX = MathHelper.sin(f * 0.6662F + 3.141593F) * 1.4F * f1;
            this.leg3.rotateAngleX = MathHelper.sin(f * 0.6662F + 3.141593F) * 1.4F * f1;
            this.leg4.rotateAngleX = MathHelper.sin(f * 0.6662F) * 1.4F * f1;

            this.mane2.setRotationPoint(-0.5F, 4.5F, -7.666667F);
            this.neck.setRotationPoint(0.5F, 8.0F, -8.6F);
            this.ponyHead.setRotationPoint(1.5F, 10.0F, -7.0F);
            this.eyeL.setRotationPoint(2.0F, 5.0F, -9.0F);
            this.snout.setRotationPoint(-1.0F, 5.0F, -14.0F);
            this.earL.setRotationPoint(1.5F, 2.3F, -8.0F);
            this.mane1.setRotationPoint(-0.5F, 3.933333F, -10.96667F);
            this.eyeR.setRotationPoint(-2.0F, 5.0F, -9.0F);
            this.earR.setRotationPoint(-1.5F, 2.3F, -8.0F);
            this.horn.setRotationPoint(0.0F, 3.0F, -8.0F);


            if (this.ponybreed == 8)
            {
                if (!this.notmoving);
                this.wingL.rotateAngleZ = MathHelper.sin(f2 * 0.6662F + 3.141593F) * 0.24F - 1.33F;
                this.wingR.rotateAngleZ = -MathHelper.sin(f2 * 0.6662F + 3.141593F) * 0.24F + 1.33F;
            }

            this.body.rotateAngleX = 0.0F;
        }

        if (this.sicky > 0)
        {

            this.leg4.rotateAngleZ = -0.7F + MathHelper.sin(f2 * 0.6662F + 3.141593F) * 0.0324F;
            this.leg3.rotateAngleZ = -2.0F - MathHelper.sin(f2 * 0.6662F + 3.141593F) * 0.0524F;
        }
        else
        {
            this.leg4.rotateAngleZ = 0.0F;
            this.leg3.rotateAngleZ = 0.0F;
            this.tail2.rotateAngleY = MathHelper.sin(f2 * 0.6662F + 3.141593F) * 0.24F;
        }
    }
}

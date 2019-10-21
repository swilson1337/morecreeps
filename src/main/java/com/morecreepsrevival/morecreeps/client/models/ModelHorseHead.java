package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHorseHead extends ModelBase
{
    public ModelRenderer horseHead;
    public ModelRenderer snout;
    public ModelRenderer earL;
    public ModelRenderer earR;
    public ModelRenderer mane1;
    public ModelRenderer pole;
    public ModelRenderer mane2;
    public ModelRenderer eyeL;
    public ModelRenderer eyeR;

    public ModelHorseHead()
    {
        this(0.0f);
    }

    public ModelHorseHead(float f)
    {
        this(f, 0.0f);
    }

    public ModelHorseHead(float f, float f1)
    {
        horseHead = new ModelRenderer(this, 0, 0);
        horseHead.addBox(-3F, -6F, -3F, 5, 5, 6, 0.0F);
        horseHead.setRotationPoint(1.0F, 11F, -7F);
        horseHead.rotateAngleX = 0.0F;
        horseHead.rotateAngleY = 0.0F;
        horseHead.rotateAngleZ = 0.0F;
        horseHead.mirror = false;
        snout = new ModelRenderer(this, 0, 11);
        snout.addBox(0.0F, 0.0F, 0.0F, 3, 3, 4, 0.0F);
        snout.setRotationPoint(-1F, 6.5F, -14F);
        snout.rotateAngleX = 0.0F;
        snout.rotateAngleY = 0.0F;
        snout.rotateAngleZ = 0.0F;
        snout.mirror = false;
        earL = new ModelRenderer(this, 7, 20);
        earL.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        earL.setRotationPoint(2.0F, 3F, -7F);
        earL.rotateAngleX = 0.0F;
        earL.rotateAngleY = 0.0F;
        earL.rotateAngleZ = 0.0F;
        earL.mirror = false;
        earR = new ModelRenderer(this, 0, 20);
        earR.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        earR.setRotationPoint(-2F, 3F, -7F);
        earR.rotateAngleX = 0.0F;
        earR.rotateAngleY = 0.0F;
        earR.rotateAngleZ = 0.0F;
        earR.mirror = false;
        mane1 = new ModelRenderer(this, 26, 0);
        mane1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 5, 0.0F);
        mane1.setRotationPoint(-0.5F, 3F, -7.5F);
        mane1.rotateAngleX = -0.1323696F;
        mane1.rotateAngleY = 0.0F;
        mane1.rotateAngleZ = 0.0F;
        mane1.mirror = false;
        pole = new ModelRenderer(this, 22, 0);
        pole.addBox(0.0F, 0.0F, 0.0F, 1, 16, 1, 0.0F);
        pole.setRotationPoint(0.0F, 9F, -6F);
        pole.rotateAngleX = 0.4584398F;
        pole.rotateAngleY = 0.0F;
        pole.rotateAngleZ = 0.0F;
        pole.mirror = false;
        mane2 = new ModelRenderer(this, 40, 0);
        mane2.addBox(0.0F, 0.0F, 0.0F, 2, 10, 3, 0.0F);
        mane2.setRotationPoint(-0.5F, 5F, -5.5F);
        mane2.rotateAngleX = 0.2602503F;
        mane2.rotateAngleY = 0.0F;
        mane2.rotateAngleZ = 0.0F;
        mane2.mirror = false;
        eyeL = new ModelRenderer(this, 7, 18);
        eyeL.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        eyeL.setRotationPoint(2.33F, 6F, -9F);
        eyeL.rotateAngleX = 0.0F;
        eyeL.rotateAngleY = 0.0F;
        eyeL.rotateAngleZ = 0.0F;
        eyeL.mirror = false;
        eyeR = new ModelRenderer(this, 0, 18);
        eyeR.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        eyeR.setRotationPoint(-2.3F, 6F, -9F);
        eyeR.rotateAngleX = 0.0F;
        eyeR.rotateAngleY = 0.0F;
        eyeR.rotateAngleZ = 0.0F;
        eyeR.mirror = false;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        horseHead.render(f5);
        snout.render(f5);
        earL.render(f5);
        earR.render(f5);
        mane1.render(f5);
        pole.render(f5);
        mane2.render(f5);
        eyeL.render(f5);
        eyeR.render(f5);
    }
}

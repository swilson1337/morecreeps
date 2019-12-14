package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDogHouse extends ModelBase
{
    public ModelRenderer body;
    public ModelRenderer roof2;
    public ModelRenderer roof1;
    public ModelRenderer house2;
    public ModelRenderer chim;
    public ModelRenderer chimcap;
    public ModelRenderer win1;
    public ModelRenderer win2;
    public ModelRenderer win3;
    public ModelRenderer win4;

    public ModelDogHouse()
    {
        body = new ModelRenderer(this, 0, 0);
        body.addBox(-5F, -10F, -5F, 10, 7, 10, 0.0F);
        body.setRotationPoint(0.0F, 27F, 0.0F);
        body.rotateAngleX = 0.0F;
        body.rotateAngleY = 0.0F;
        body.rotateAngleZ = 0.0F;
        body.mirror = false;
        roof2 = new ModelRenderer(this, 24, 20);
        roof2.addBox(0.0F, -0.4F, -6F, 9, 1, 11, 0.05F);
        roof2.setRotationPoint(-0.1F, 12F, 0.5F);
        roof2.rotateAngleX = 0.0F;
        roof2.rotateAngleY = 0.0F;
        roof2.rotateAngleZ = 0.8042327F;
        roof2.mirror = false;
        roof1 = new ModelRenderer(this, 24, 20);
        roof1.addBox(0.0F, 0.1F, -6F, 9, 1, 11, 0.0F);
        roof1.setRotationPoint(1.0F, 12.5F, 0.5F);
        roof1.rotateAngleX = 0.0F;
        roof1.rotateAngleY = 0.0F;
        roof1.rotateAngleZ = 2.431532F;
        roof1.mirror = false;
        house2 = new ModelRenderer(this, 30, 0);
        house2.addBox(0.0F, 0.0F, -5F, 7, 7, 10, 0.0F);
        house2.setRotationPoint(0.0F, 12F, 0.0F);
        house2.rotateAngleX = 0.0F;
        house2.rotateAngleY = 0.0F;
        house2.rotateAngleZ = 0.7684472F;
        house2.mirror = false;
        chim = new ModelRenderer(this, 6, 17);
        chim.addBox(0.0F, 0.0F, 0.0F, 2, 3, 2, -0.3F);
        chim.setRotationPoint(-4F, 12.5F, 1.533333F);
        chim.rotateAngleX = 0.0F;
        chim.rotateAngleY = 0.0F;
        chim.rotateAngleZ = 0.0F;
        chim.mirror = false;
        chimcap = new ModelRenderer(this, 14, 17);
        chimcap.addBox(0.0F, 0.0F, 0.0F, 2, 1, 2, 0.0F);
        chimcap.setRotationPoint(-4F, 12F, 1.533333F);
        chimcap.rotateAngleX = 0.0F;
        chimcap.rotateAngleY = 0.0F;
        chimcap.rotateAngleZ = 0.0F;
        chimcap.mirror = false;
        win1 = new ModelRenderer(this, 0, 17);
        win1.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        win1.setRotationPoint(-5.333333F, 19.6F, 1.0F);
        win1.rotateAngleX = 0.0F;
        win1.rotateAngleY = 0.0F;
        win1.rotateAngleZ = 0.0F;
        win1.mirror = false;
        win2 = new ModelRenderer(this, 0, 17);
        win2.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        win2.setRotationPoint(-5.333333F, 19.6F, -3.266667F);
        win2.rotateAngleX = 0.0F;
        win2.rotateAngleY = 0.0F;
        win2.rotateAngleZ = 0.0F;
        win2.mirror = false;
        win3 = new ModelRenderer(this, 0, 17);
        win3.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        win3.setRotationPoint(4.266667F, 19.6F, -3F);
        win3.rotateAngleX = 0.0F;
        win3.rotateAngleY = 0.0F;
        win3.rotateAngleZ = 0.0F;
        win3.mirror = false;
        win4 = new ModelRenderer(this, 0, 17);
        win4.addBox(0.0F, 0.0F, 0.0F, 1, 3, 2, 0.0F);
        win4.setRotationPoint(4.266667F, 19.6F, 1.266667F);
        win4.rotateAngleX = 0.0F;
        win4.rotateAngleY = 0.0F;
        win4.rotateAngleZ = 0.0F;
        win4.mirror = false;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        body.render(f5);
        roof2.render(f5);
        roof1.render(f5);
        house2.render(f5);
        chim.render(f5);
        chimcap.render(f5);
        win1.render(f5);
        win2.render(f5);
        win3.render(f5);
        win4.render(f5);
    }
}

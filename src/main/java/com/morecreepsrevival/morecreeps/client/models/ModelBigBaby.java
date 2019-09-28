package com.morecreepsrevival.morecreeps.client.models;

import com.morecreepsrevival.morecreeps.common.entity.EntityBigBaby;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBigBaby extends ModelBase
{
    public ModelRenderer babyHead;
    public ModelRenderer babyBody;
    public ModelRenderer armL;
    public ModelRenderer legL1;
    public ModelRenderer legL2;
    public ModelRenderer armR;
    public ModelRenderer legR1;
    public ModelRenderer legR2;
    public ModelRenderer earL;
    public ModelRenderer earR;

    public ModelBigBaby()
    {
        this(0.0f);
    }

    public ModelBigBaby(float f)
    {
        this(f, 0.0f);
    }

    public ModelBigBaby(float f, float f1)
    {
        babyHead = new ModelRenderer(this, 0, 0);
        babyHead.addBox(-3F, -6F, -6F, 6, 6, 6, 0.0F);
        babyHead.setRotationPoint(0.0F, 18F, -1.5F);
        babyHead.rotateAngleX = 0.0F;
        babyHead.rotateAngleY = 0.0F;
        babyHead.rotateAngleZ = 0.0F;
        babyHead.mirror = false;
        babyBody = new ModelRenderer(this, 24, 0);
        babyBody.addBox(-3F, -2F, -4F, 6, 4, 8, 0.0F);
        babyBody.setRotationPoint(0.0F, 19F, 2.0F);
        babyBody.rotateAngleX = -0.1858931F;
        babyBody.rotateAngleY = 0.0F;
        babyBody.rotateAngleZ = 0.0F;
        babyBody.mirror = false;
        armL = new ModelRenderer(this, 0, 13);
        armL.addBox(0.0F, 0.0F, -1F, 2, 7, 2, 0.0F);
        armL.setRotationPoint(3F, 17F, -1F);
        armL.rotateAngleX = 0.0F;
        armL.rotateAngleY = 0.0F;
        armL.rotateAngleZ = 0.0F;
        armL.mirror = false;
        legL1 = new ModelRenderer(this, 8, 25);
        legL1.addBox(0.0F, 0.0F, -1F, 2, 4, 2, 0.0F);
        legL1.setRotationPoint(3F, 19F, 5F);
        legL1.rotateAngleX = -0.5576792F;
        legL1.rotateAngleY = 0.0F;
        legL1.rotateAngleZ = 0.0F;
        legL1.mirror = false;
        legL2 = new ModelRenderer(this, 16, 25);
        legL2.addBox(0.0F, 3F, -3F, 2, 2, 5, 0.0F);
        legL2.setRotationPoint(3F, 19F, 5F);
        legL2.rotateAngleX = 0.0F;
        legL2.rotateAngleY = 0.0F;
        legL2.rotateAngleZ = 0.0F;
        legL2.mirror = false;
        armR = new ModelRenderer(this, 0, 22);
        armR.addBox(-2F, 0.0F, -1F, 2, 7, 2, 0.0F);
        armR.setRotationPoint(-3F, 17F, -1F);
        armR.rotateAngleX = 0.0F;
        armR.rotateAngleY = 0.0F;
        armR.rotateAngleZ = 0.0F;
        armR.mirror = false;
        legR1 = new ModelRenderer(this, 8, 25);
        legR1.addBox(-2F, 0.0F, -1F, 2, 4, 2, 0.0F);
        legR1.setRotationPoint(-3F, 19F, 5F);
        legR1.rotateAngleX = -0.5576851F;
        legR1.rotateAngleY = 0.0F;
        legR1.rotateAngleZ = 0.0F;
        legR1.mirror = false;
        legR2 = new ModelRenderer(this, 16, 25);
        legR2.addBox(-2F, 3F, -3F, 2, 2, 5, 0.0F);
        legR2.setRotationPoint(-3F, 19F, 5F);
        legR2.rotateAngleX = 0.0F;
        legR2.rotateAngleY = 0.0F;
        legR2.rotateAngleZ = 0.0F;
        legR2.mirror = false;
        earL = new ModelRenderer(this, 8, 16);
        earL.addBox(2.0F, -4F, -3F, 2, 2, 1, 0.0F);
        earL.setRotationPoint(0.0F, 18F, -1.5F);
        earL.rotateAngleX = 0.0F;
        earL.rotateAngleY = 0.0F;
        earL.rotateAngleZ = 0.0F;
        earL.mirror = false;
        earR = new ModelRenderer(this, 8, 13);
        earR.addBox(-4F, -4F, -3F, 2, 2, 1, 0.0F);
        earR.setRotationPoint(0.0F, 18F, -1.5F);
        earR.rotateAngleX = 0.0F;
        earR.rotateAngleY = 0.0F;
        earR.rotateAngleZ = 0.0F;
        earR.mirror = false;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        babyHead.render(f5);
        babyBody.render(f5);
        armL.render(f5);
        legL1.render(f5);
        legL2.render(f5);
        armR.render(f5);
        legR1.render(f5);
        legR2.render(f5);
        earL.render(f5);
        earR.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        earL.rotateAngleY = earR.rotateAngleY = babyHead.rotateAngleY = f3 / (180F / (float)Math.PI);
        earL.rotateAngleX = earR.rotateAngleX = babyHead.rotateAngleX = f4 / (180F / (float)Math.PI) + 0.25F;
        armL.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        legL1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1 - 0.5576792F;
        legL2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;

        float hammerSwing = 0.0f;

        if (entity instanceof EntityBigBaby)
        {
            hammerSwing = ((EntityBigBaby)entity).getHammerSwing();
        }

        if (hammerSwing != 0.0F)
        {
            armR.rotateAngleX = hammerSwing;
        }
        else
        {
            armR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        }

        legR1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1 - 0.5576792F;
        legR2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}

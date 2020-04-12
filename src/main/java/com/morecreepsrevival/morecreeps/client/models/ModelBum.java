package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelBum extends ModelBase
{
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;

    public ModelBum()
    {
        this(0.0f);
    }

    public ModelBum(float f)
    {
        this(f, 0.0f);
    }

    public ModelBum(float f, float f1)
    {
        bipedHead = new ModelRenderer(this, 0, 0);
        bipedHead.addBox(-4F, -8F, -4F, 8, 8, 8, f);
        bipedHead.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        bipedBody = new ModelRenderer(this, 16, 16);
        bipedBody.addBox(-4F, 0.0F, -2F, 8, 12, 4, f);
        bipedBody.setRotationPoint(0.0F, 0.0F + f1, 0.0F);
        bipedRightArm = new ModelRenderer(this, 40, 16);
        bipedRightArm.addBox(-3F, -2F, -2F, 4, 12, 4, f);
        bipedRightArm.setRotationPoint(-5F, 2.0F + f1, 0.0F);
        bipedLeftArm = new ModelRenderer(this, 40, 16);
        bipedLeftArm.mirror = true;
        bipedLeftArm.addBox(-1F, -2F, -2F, 4, 12, 4, f);
        bipedLeftArm.setRotationPoint(5F, 2.0F + f1, 0.0F);
        bipedRightLeg = new ModelRenderer(this, 0, 16);
        bipedRightLeg.addBox(-2F, 0.0F, -2F, 4, 12, 4, f);
        bipedRightLeg.setRotationPoint(-2F, 12F + f1, 0.0F);
        bipedLeftLeg = new ModelRenderer(this, 0, 16);
        bipedLeftLeg.mirror = true;
        bipedLeftLeg.addBox(-2F, 0.0F, -2F, 4, 12, 4, f);
        bipedLeftLeg.setRotationPoint(2.0F, 12F + f1, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        bipedHead.render(f5);
        bipedBody.render(f5);
        bipedRightArm.render(f5);
        bipedLeftArm.render(f5);
        bipedRightLeg.render(f5);
        bipedLeftLeg.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        bipedHead.rotateAngleY = f3 / (180F / (float)Math.PI);
        bipedHead.rotateAngleX = f4 / (180F / (float)Math.PI);
        bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        bipedRightLeg.rotateAngleY = 0.0F;
        bipedLeftLeg.rotateAngleY = 0.0F;
        bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        bipedRightArm.rotateAngleZ = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
    }
}

package com.morecreepsrevival.morecreeps.client.models;

import com.morecreepsrevival.morecreeps.common.entity.EntityLolliman;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLolliman extends ModelBase
{
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;

    public ModelLolliman()
    {
        this(0.0f);
    }

    public ModelLolliman(float f)
    {
        this(f, 0.0f);
    }

    public ModelLolliman(float f, float f1)
    {
        float f2 = 0.0F;
        bipedHead = new ModelRenderer(this, 28, 0);
        bipedHead.addBox(-8F, -16F, -1F, 16, 16, 2, 0.8F);
        bipedHead.setRotationPoint(0.0F, -1F, 0.0F);
        bipedBody = new ModelRenderer(this, 16, 17);
        bipedBody.addBox(-2F, 0.0F, -1.5F, 4, 12, 3, f2);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedRightArm = new ModelRenderer(this, 40, 20);
        bipedRightArm.addBox(-2F, 0.0F, -1F, 2, 10, 2, f2);
        bipedRightArm.setRotationPoint(-1F, 4F, 0.0F);
        bipedRightArm.rotateAngleZ = 0.669215F;
        bipedLeftArm = new ModelRenderer(this, 40, 20);
        bipedLeftArm.addBox(0.0F, 0.0F, -1F, 2, 10, 2, f2);
        bipedLeftArm.setRotationPoint(1.0F, 4F, 0.0F);
        bipedLeftArm.rotateAngleZ = -0.63284F;
        bipedRightLeg = new ModelRenderer(this, 0, 18);
        bipedRightLeg.addBox(-1F, 0.0F, -1F, 2, 12, 2, f2);
        bipedRightLeg.setRotationPoint(-1F, 12F, 0.0F);
        bipedRightLeg.rotateAngleZ = 0.13963F;
        bipedLeftLeg = new ModelRenderer(this, 0, 18);
        bipedLeftLeg.addBox(-1F, 0.0F, -1F, 2, 12, 2, f2);
        bipedLeftLeg.setRotationPoint(1.0F, 12F, 0.0F);
        bipedLeftLeg.rotateAngleZ = -0.13963F;
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
        bipedRightArm.rotateAngleZ = 0.66921F;
        bipedLeftArm.rotateAngleZ = -0.63284F;
        bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        bipedRightLeg.rotateAngleY = 0.0F;
        bipedLeftLeg.rotateAngleY = 0.0F;

        if (isRiding)
        {
            bipedRightArm.rotateAngleX += -((float)Math.PI / 5F);
            bipedLeftArm.rotateAngleX += -((float)Math.PI / 5F);
            bipedRightLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            bipedLeftLeg.rotateAngleX = -((float)Math.PI * 2F / 5F);
            bipedRightLeg.rotateAngleY = ((float)Math.PI / 10F);
            bipedLeftLeg.rotateAngleY = -((float)Math.PI / 10F);
        }

        bipedRightArm.rotateAngleY = 0.0F;
        bipedLeftArm.rotateAngleY = 0.0F;

        if (((EntityLolliman)entity).getKidMounted())
        {
            bipedHead.rotateAngleX = 0.0f;
        }
    }
}

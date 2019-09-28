package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCastleGuard extends ModelBase
{
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;

    public boolean heldItemLeft;
    public boolean heldItemRight;
    public boolean isSneak;

    public ModelRenderer hammerHandle;
    public ModelRenderer hammerHead;
    public ModelRenderer bootR;
    public ModelRenderer bootL;
    public ModelRenderer footL;
    public ModelRenderer footR;
    public ModelRenderer earL;
    public ModelRenderer earR;
    public ModelRenderer nose;

    public float hammerswing;

    public ModelCastleGuard()
    {
        this(0.0f);
    }

    public ModelCastleGuard(float f)
    {
        this(f, 0.0f);
    }

    public ModelCastleGuard(float f, float f1)
    {
        float f2 = 0.0F;
        bipedHead = new ModelRenderer(this, 0, 0);
        bipedHead.addBox(-2F, -9F, -4F, 4, 9, 7, 0.0F);
        bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedBody = new ModelRenderer(this, 24, 16);
        bipedBody.addBox(-4F, 0.0F, -2F, 8, 12, 4, 0.0F);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
        bipedRightArm = new ModelRenderer(this, 48, 16);
        bipedRightArm.addBox(-3F, -2F, -2F, 4, 12, 4, 0.0F);
        bipedRightArm.setRotationPoint(-5F, 2.0F, 0.0F);
        bipedRightArm.rotateAngleX = -0.5235988F;
        bipedLeftArm = new ModelRenderer(this, 48, 16);
        bipedLeftArm.addBox(-1F, -2F, -2F, 4, 12, 4, 0.0F);
        bipedLeftArm.setRotationPoint(5F, 2.0F, 0.0F);
        bipedRightLeg = new ModelRenderer(this, 0, 17);
        bipedRightLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 12, 3, 0.0F);
        bipedRightLeg.setRotationPoint(-2F, 12F, 0.0F);
        bipedLeftLeg = new ModelRenderer(this, 0, 17);
        bipedLeftLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 12, 3, 0.0F);
        bipedLeftLeg.setRotationPoint(2.5F, 12F, 0.0F);
        hammerHandle = new ModelRenderer(this, 24, 0);
        hammerHandle.addBox(-2F, 8F, -12F, 1, 1, 15, 0.25F);
        hammerHandle.setRotationPoint(-5F, 2.0F, 0.0F);
        hammerHandle.rotateAngleX = -0.5235988F;
        hammerHead = new ModelRenderer(this, 12, 22);
        hammerHead.addBox(-3F, 5F, -11F, 3, 7, 3, 0.25F);
        hammerHead.setRotationPoint(-5F, 2.0F, 0.0F);
        hammerHead.rotateAngleX = -0.5235988F;
        bootR = new ModelRenderer(this, 48, 0);
        bootR.addBox(-2F, 7F, -2F, 4, 5, 4, 0.0F);
        bootR.setRotationPoint(-2F, 12F, 0.0F);
        bootL = new ModelRenderer(this, 48, 0);
        bootL.addBox(-2F, 7F, -2F, 4, 5, 4, 0.0F);
        bootL.setRotationPoint(2.5F, 12F, 0.0F);
        footL = new ModelRenderer(this, 48, 9);
        footL.addBox(-2F, 10F, -4.5F, 4, 2, 3, 0.0F);
        footL.setRotationPoint(2.5F, 12F, 0.0F);
        footR = new ModelRenderer(this, 48, 9);
        footR.addBox(-2F, 10F, -4.5F, 4, 2, 3, 0.0F);
        footR.setRotationPoint(-2F, 12F, 0.0F);
        earL = new ModelRenderer(this, 12, 17);
        earL.addBox(2.0F, -7F, -1F, 1, 2, 1, 0.0F);
        earL.setRotationPoint(0.0F, 0.0F, 0.0F);
        earR = new ModelRenderer(this, 12, 17);
        earR.addBox(-3F, -7F, -1F, 1, 2, 1, 0.0F);
        earR.setRotationPoint(0.0F, 0.0F, 0.0F);
        nose = new ModelRenderer(this, 16, 17);
        nose.addBox(-0.5F, -6F, -5F, 1, 1, 3, 0.0F);
        nose.setRotationPoint(0.0F, 0.0F, 0.0F);
        nose.rotateAngleX = 0.2230717F;
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
        hammerHandle.render(f5);
        hammerHead.render(f5);
        bootR.render(f5);
        bootL.render(f5);
        footL.render(f5);
        footR.render(f5);
        earL.render(f5);
        earR.render(f5);
        nose.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        earL.rotateAngleY = earR.rotateAngleY = nose.rotateAngleY = bipedHead.rotateAngleY = f3 / (180F / (float)Math.PI);
        earL.rotateAngleX = earR.rotateAngleX = nose.rotateAngleX = bipedHead.rotateAngleX = f4 / (180F / (float)Math.PI);
        nose.rotateAngleX += 0.2230717F;

        if (hammerswing != 0.0F)
        {
            hammerHead.rotateAngleX = hammerHandle.rotateAngleX = bipedRightArm.rotateAngleX = hammerswing;
        }
        else
        {
            hammerHead.rotateAngleX = hammerHandle.rotateAngleX = bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        }

        bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        hammerHead.rotateAngleZ = hammerHandle.rotateAngleZ = bipedRightArm.rotateAngleZ = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
        bootR.rotateAngleX = footR.rotateAngleX = bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        bootL.rotateAngleX = footL.rotateAngleX = bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        bootR.rotateAngleY = footR.rotateAngleY = bipedRightLeg.rotateAngleY = 0.0F;
        bootL.rotateAngleY = footL.rotateAngleY = bipedLeftLeg.rotateAngleY = 0.0F;
        bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        hammerHead.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        hammerHandle.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        hammerHead.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        hammerHandle.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    }
}

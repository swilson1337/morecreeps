package com.morecreepsrevival.morecreeps.client.models;

import com.morecreepsrevival.morecreeps.common.entity.EntityCastleKing;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;

public class ModelPonyGirl extends ModelBiped
{
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer hair1;
    public ModelRenderer hair2;
    public ModelRenderer rightfoot;
    public ModelRenderer leftfoot;

    public boolean isSneak;
    public boolean heldItemLeft;
    public boolean heldItemRight;

    public ModelPonyGirl()
    {
        isSneak = false;
        heldItemLeft = false;
        heldItemRight = false;

        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-3.0F, -4.0F, -3.0F, 6, 6, 6, 0.0F);
        this.bipedHead.setRotationPoint(0.0F, 2.0F, 0.0F);

        this.bipedBody = new ModelRenderer(this, 16, 16);
        this.bipedBody.addBox(-3.5F, 0.0F, -2.0F, 7, 10, 4, 0.0F);
        this.bipedBody.setRotationPoint(0.0F, 2.0F, 0.0F);

        this.bipedRightArm = new ModelRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-2.0F, -2.0F, -1.5F, 3, 10, 3, 0.0F);
        this.bipedRightArm.setRotationPoint(-4.5F, 4.0F, 0.0F);

        this.bipedLeftArm = new ModelRenderer(this, 40, 16);
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -1.5F, 3, 10, 3, 0.0F);
        this.bipedLeftArm.setRotationPoint(4.5F, 4.0F, 0.0F);

        this.bipedRightLeg = new ModelRenderer(this, 0, 16);
        this.bipedRightLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 12, 3, 0.0F);
        this.bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);

        this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
        this.bipedLeftLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 12, 3, 0.0F);
        this.bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);

        this.hair1 = new ModelRenderer(this, 24, 0);
        this.hair1.addBox(-1.0F, -5.0F, 3.0F, 2, 2, 1, 0.0F);
        this.hair1.setRotationPoint(0.0F, 2.0F, 0.0F);

        this.hair2 = new ModelRenderer(this, 24, 3);
        this.hair2.addBox(-1.5F, -4.5F, 4.0F, 3, 6, 2, 0.0F);
        this.hair2.setRotationPoint(0.0F, 2.0F, 0.0F);

        this.rightfoot = new ModelRenderer(this, 34, 0);
        this.rightfoot.addBox(-1.5F, 11.0F, -3.5F, 3, 1, 2, 0.0F);
        this.rightfoot.setRotationPoint(-2.0F, 12.0F, 0.0F);

        this.leftfoot = new ModelRenderer(this, 34, 0);
        this.leftfoot.addBox(-1.5F, 11.0F, -3.5F, 3, 1, 2, 0.0F);
        this.leftfoot.setRotationPoint(2.0F, 12.0F, 0.0F);
    }

    @Override
    public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f, entity);
        this.bipedHead.render(f5);
        this.bipedBody.render(f5);
        this.bipedRightArm.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedLeftLeg.render(f5);
        this.hair1.render(f5);
        this.hair2.render(f5);
        this.rightfoot.render(f5);
        this.leftfoot.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, @Nonnull Entity entity)
    {
        bipedHead.rotateAngleY = f3 / 57.29578f;
        bipedHead.rotateAngleX = f4 / 57.29578f;

        bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662f + (float)Math.PI) * 2.0f * f1 * 0.5f;

        bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662f) * 2.0f * f1 * 0.5f;

        bipedLeftArm.rotateAngleZ = 0.0f;
        bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662f) * 1.4f * f1;
        bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662f + (float)Math.PI) * 1.4f * f1;
        bipedRightLeg.rotateAngleY = 0.0f;
        bipedLeftLeg.rotateAngleY = 0.0f;

        if (false)
        {
            bipedRightArm.rotateAngleX += -0.6283185f;
            bipedLeftArm.rotateAngleX += -0.6283185f;
            bipedRightLeg.rotateAngleX = -1.256637f;
            bipedLeftLeg.rotateAngleX = -1.256637f;
            bipedRightLeg.rotateAngleY = 0.3141593f;
            bipedLeftLeg.rotateAngleY = -0.3141593f;
        }

        if (heldItemLeft)
        {
            bipedLeftArm.rotateAngleX = bipedLeftArm.rotateAngleX * 0.5f - (float)Math.PI;
        }

        if (heldItemRight)
        {
            bipedRightArm.rotateAngleX = bipedRightArm.rotateAngleX * 0.5f - (float)Math.PI;
        }

        bipedRightArm.rotateAngleY = 0.0f;
        bipedLeftArm.rotateAngleY = 0.0f;

        if (swingProgress > -9990.0f)
        {
            float f6 = swingProgress;

            bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f6) * (float)Math.PI * 2.0f) * 0.2f;

            bipedRightArm.rotationPointZ = MathHelper.sin(bipedBody.rotateAngleY) * 5.0f;
            bipedRightArm.rotationPointX = -MathHelper.cos(bipedBody.rotateAngleY) * 5.0f;

            bipedLeftArm.rotationPointZ = -MathHelper.sin(bipedBody.rotateAngleY) * 5.0f;
            bipedLeftArm.rotationPointX = MathHelper.cos(bipedBody.rotateAngleY) * 5.0f;
            bipedRightArm.rotateAngleY += bipedBody.rotateAngleY;
            bipedLeftArm.rotateAngleY += bipedBody.rotateAngleY;
            bipedLeftArm.rotateAngleX += bipedBody.rotateAngleY;
            f6 = 1.0f - swingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0f - f6;
            float f7 = MathHelper.sin(f6 * (float)Math.PI);
            float f8 = MathHelper.sin(swingProgress * (float)Math.PI) * -(bipedHead.rotateAngleX - 0.7f) * 0.75f;

            bipedRightArm.rotateAngleX = (float)(bipedRightArm.rotateAngleX - f7 * 1.2d + f8);
            bipedRightArm.rotateAngleY += bipedBody.rotateAngleY * 2.0f;
            bipedRightArm.rotateAngleZ = MathHelper.sin(swingProgress * (float)Math.PI) * -0.4f;
        }

        if (isSneak)
        {
            bipedBody.rotateAngleX = 0.5f;
            rightfoot.rotateAngleX -= bipedRightLeg.rotateAngleX;
            leftfoot.rotateAngleX -= bipedLeftLeg.rotateAngleX;
            bipedRightArm.rotateAngleX += 0.4F;
            bipedLeftArm.rotateAngleX += 0.4F;
            bipedRightLeg.rotationPointZ = 4F;
            bipedLeftLeg.rotationPointZ = 4F;
            bipedRightLeg.rotationPointY = 9F;
            bipedLeftLeg.rotationPointY = 9F;
            bipedHead.rotationPointY = 1.0F;
        }
        else
        {
            bipedBody.rotateAngleX = 0.0F;
            bipedRightLeg.rotationPointZ = 0.0F;
            bipedLeftLeg.rotationPointZ = 0.0F;
            bipedRightLeg.rotationPointY = 12F;
            bipedLeftLeg.rotationPointY = 12F;
            bipedHead.rotationPointY = 0.0F;
        }

        bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
        bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09f) * 0.05f + 0.05f;
        bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067f) * 0.05f;
        bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067f) * 0.05f;
    }
}

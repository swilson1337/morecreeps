package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelGrowbotGregg extends ModelBiped
{
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedBody2;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer shoulderL;
    public ModelRenderer shoulderR;
    public ModelRenderer footR;
    public ModelRenderer footL;
    public ModelRenderer antR;
    public ModelRenderer antL;
    public ModelRenderer chest;
    public ModelRenderer eyeR;
    public ModelRenderer eyeL;
    public ModelRenderer mouth;
    public ModelRenderer capR;
    public ModelRenderer capL;
    public ModelRenderer lights;

    public boolean heldItemLeft;
    public boolean heldItemRight;
    public boolean isSneak;
    public float tailwag;
    public int taildirection;

    public ModelGrowbotGregg()
    {
        this(0.0F);
    }

    public ModelGrowbotGregg(float f)
    {
        this(f, 0.0F);
    }

    public ModelGrowbotGregg(float f, float f1)
    {
        heldItemLeft = false;
        heldItemRight = false;
        isSneak = false;
        tailwag = 0.0f;
        taildirection = 1;

        this.bipedBody = new ModelRenderer(this, 32, 0);
        this.bipedBody.addBox(-4.5F, -5.5F, -3.5F, 9, 6, 7, 0.25F);
        this.bipedBody.setRotationPoint(0.0F, 10.5F, 0.0F);

        this.shoulderL = new ModelRenderer(this, 0, 12);
        this.shoulderL.addBox(0.0F, 0.0F, 0.0F, 2, 2, 2);
        this.shoulderL.setRotationPoint(4.0F, 6.0F, -1.0F);

        this.shoulderR = new ModelRenderer(this, 0, 12);
        this.shoulderR.addBox(-2.0F, 0.0F, 0.0F, 2, 2, 2);
        this.shoulderR.setRotationPoint(-4.0F, 6.0F, -1.0F);

        this.bipedRightArm = new ModelRenderer(this, 0, 23);
        this.bipedRightArm.addBox(-2.0F, 0.0F, -1.0F, 2, 7, 2);
        this.bipedRightArm.setRotationPoint(-6.0F, 7.0F, 0.0F);

        this.bipedLeftArm = new ModelRenderer(this, 0, 23);
        this.bipedLeftArm.addBox(0.0F, 0.0F, -1.0F, 2, 7, 2);
        this.bipedLeftArm.setRotationPoint(6.0F, 7.0F, 0.0F);

        this.bipedRightLeg = new ModelRenderer(this, 40, 18);
        this.bipedRightLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3);
        this.bipedRightLeg.setRotationPoint(-3.0F, 16.0F, 0.0F);

        this.footR = new ModelRenderer(this, 0, 16);
        this.footR.addBox(-2.5F, 7.0F, -3.0F, 4, 1, 5);
        this.footR.setRotationPoint(-3.0F, 16.0F, 0.0F);

        this.bipedLeftLeg = new ModelRenderer(this, 40, 18);
        this.bipedLeftLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 7, 3);
        this.bipedLeftLeg.setRotationPoint(3.0F, 16.0F, 0.0F);

        this.footL = new ModelRenderer(this, 0, 16);
        this.footL.addBox(-1.5F, 7.0F, -3.0F, 4, 1, 5);
        this.footL.setRotationPoint(3.0F, 16.0F, 0.0F);

        this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-3.0F, -6.0F, -3.0F, 6, 6, 6);
        this.bipedHead.setRotationPoint(0.0F, 5.0F, 0.0F);

        this.antR = new ModelRenderer(this, 24, 0);
        this.antR.addBox(-3.5F, -9.5F, -0.6F, 1, 5, 1);
        this.antR.setRotationPoint(0.0F, 5.0F, 0.0F);

        this.antL = new ModelRenderer(this, 24, 0);
        this.antL.addBox(2.5F, -9.5F, -0.6F, 1, 5, 1);
        this.antL.setRotationPoint(0.0F, 5.0F, 0.0F);

        this.chest = new ModelRenderer(this, 52, 18);
        this.chest.addBox(0.0F, 0.0F, 0.0F, 5, 4, 1);
        this.chest.setRotationPoint(-2.533333F, 6.0F, -4.5F);

        this.eyeR = new ModelRenderer(this, 24, 6);
        this.eyeR.addBox(-3.0F, -5.0F, -4.0F, 2, 2, 1, 0.3F);
        this.eyeR.setRotationPoint(0.0F, 5.0F, 0.0F);

        this.eyeL = new ModelRenderer(this, 24, 6);
        this.eyeL.addBox(1.0F, -5.0F, -4.0F, 2, 2, 1, 0.3F);
        this.eyeL.setRotationPoint(0.0F, 5.0F, 0.0F);

        this.mouth = new ModelRenderer(this, 24, 9);
        this.mouth.addBox(-0.5F, -2.0F, -5.0F, 1, 1, 2);
        this.mouth.setRotationPoint(0.0F, 5.0F, 0.0F);

        this.bipedBody2 = new ModelRenderer(this, 32, 5);
        this.bipedBody2.addBox(-4.5F, -5.5F, -3.5F, 9, 6, 7, -0.65F);
        this.bipedBody2.setRotationPoint(0.0F, 16.0F, 0.0F);

        this.capR = new ModelRenderer(this, 0, 0);
        this.capR.addBox(-3.5F, -7.0F, -0.5F, 1, 1, 1, 0.5F);
        this.capR.setRotationPoint(0.0F, 5.0F, 0.0F);

        this.capL = new ModelRenderer(this, 0, 0);
        this.capL.addBox(2.5F, -7.0F, -0.5F, 1, 1, 1, 0.5F);
        this.capL.setRotationPoint(0.0F, 3.0F, 0.0F);

        this.lights = new ModelRenderer(this, 8, 27);
        this.lights.addBox(-3.0F, -10.0F, -0.5F, 6, 4, 1, -0.15F);
        this.lights.setRotationPoint(0.0F, 5.0F, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        this.bipedHead.render(f5);
        this.bipedBody.render(f5);
        this.bipedRightArm.render(f5);
        this.bipedLeftArm.render(f5);
        this.bipedRightLeg.render(f5);
        this.bipedLeftLeg.render(f5);
        this.shoulderL.render(f5);
        this.shoulderR.render(f5);
        this.footR.render(f5);
        this.footL.render(f5);
        this.antR.render(f5);
        this.antL.render(f5);
        this.chest.render(f5);
        this.eyeR.render(f5);
        this.eyeL.render(f5);
        this.mouth.render(f5);
        this.bipedBody2.render(f5);
        this.capR.render(f5);
        this.capL.render(f5);
        this.lights.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        if (this.taildirection > 0)
        {
            this.tailwag += 1.0E-4F;

            if (this.tailwag > 0.017F)
            {
                this.taildirection *= -1;
            }
        }
        else
        {
            this.tailwag -= 1.0E-4F;

            if (this.tailwag < -0.016999999999999998D)
            {
                this.taildirection *= -1;
            }
        }


        this.bipedHead.rotateAngleY = f3 / 57.29578F;
        this.bipedHead.rotateAngleX = f4 / 57.29578F;
        this.bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 2.0F * f1 * 0.5F;
        this.bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        this.bipedRightArm.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotateAngleZ = 0.0F;

        this.bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        this.bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + 3.141593F) * 1.4F * f1;

        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;

        // TODO: Figure out what this means!
        //this.capR.d -= this.tailwag;
        //this.capL.d += this.tailwag;

        if (this.isRiding)
        {
            this.bipedRightArm.rotateAngleX += -0.6283185F;
            this.bipedLeftArm.rotateAngleX += -0.6283185F;
            this.bipedRightLeg.rotateAngleX = -1.256637F;
            this.bipedLeftLeg.rotateAngleX = -1.256637F;
            this.bipedRightLeg.rotateAngleY = 0.3141593F;
            this.bipedLeftLeg.rotateAngleY = -0.3141593F;
        }

        if (this.heldItemLeft)
        {
            this.bipedLeftArm.rotateAngleX = this.bipedLeftArm.rotateAngleX * 0.5F - 0.1141593F;
        }

        if (this.heldItemRight)
        {
            this.bipedRightArm.rotateAngleX = this.bipedRightArm.rotateAngleX * 0.5F - 0.1141593F;
        }

        this.bipedRightArm.rotateAngleY = 0.0F;

        this.bipedLeftArm.rotateAngleY = 0.0F;

        if (this.swingProgress > -9990.0F)
        {
            float f6 = this.swingProgress;
            this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f6) * 3.141593F * 2.0F) * 0.2F;

            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
            this.bipedLeftArm.rotateAngleX += this.bipedBody.rotateAngleX;
            f6 = 1.0F - this.swingProgress;
            f6 *= f6;
            f6 *= f6;
            f6 = 1.0F - f6;
            float f7 = MathHelper.sin(f6 * 3.141593F);
            float f8 = MathHelper.sin(this.swingProgress * 3.141593F) * -(this.bipedHead.rotateAngleX - 0.7F) * 0.75F;
            this.bipedRightArm.rotateAngleX = (float)(this.bipedRightArm.rotateAngleX - f7 * 1.2D + f8);
            this.bipedRightArm.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
            this.bipedRightArm.rotateAngleZ = MathHelper.sin(this.swingProgress * 3.141593F) * -0.4F;
        }

        if (this.isSneak)
        {
            this.bipedBody.rotateAngleX = 0.5F;
            this.bipedRightLeg.rotateAngleX -= 0.0F;
            this.bipedLeftLeg.rotateAngleX -= 0.0F;
            this.bipedRightArm.rotateAngleX += 0.4F;
            this.bipedLeftArm.rotateAngleX += 0.4F;
        }
        else
        {
            this.bipedBody.rotateAngleX = 0.0F;
        }

        this.bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        this.bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        this.bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        this.bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;

        this.bipedRightArm.rotateAngleX = -1.1897501F;
    }
}

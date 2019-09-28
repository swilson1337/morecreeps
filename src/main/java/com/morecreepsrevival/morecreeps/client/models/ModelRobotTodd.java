package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelRobotTodd extends ModelBase
{
    public ModelRenderer robotHead;
    public ModelRenderer mouth;
    public ModelRenderer eyeL;
    public ModelRenderer eyeR;
    public ModelRenderer neck;
    public ModelRenderer body;
    public ModelRenderer legL;
    public ModelRenderer footL;
    public ModelRenderer legR;
    public ModelRenderer footR;
    public ModelRenderer armL;
    public ModelRenderer armR;
    public ModelRenderer chest;
    public ModelRenderer hand1L;
    public ModelRenderer hand2L;
    public ModelRenderer hand2R;
    public ModelRenderer hand1R;
    public ModelRenderer ears;
    public boolean heldItemLeft;
    public boolean heldItemRight;
    public boolean isSneak;
    public float tailwag;
    public int taildirection;

    public ModelRobotTodd()
    {
        this(0.0f);
    }

    public ModelRobotTodd(float f)
    {
        this(f, 0.0f);
    }

    public ModelRobotTodd(float f, float f1)
    {
        taildirection = 1;
        float f2 = 0.0F;
        robotHead = new ModelRenderer(this, 5, 0);
        robotHead.addBox(-2.5F, -7F, -1.5F, 5, 5, 3, f2);
        robotHead.setRotationPoint(1.5F, 5F, 0.0F);
        mouth = new ModelRenderer(this, 43, 27);
        mouth.addBox(-1.5F, -4F, -2.5F, 3, 1, 1, f2);
        mouth.setRotationPoint(1.5F, 5F, 0.0F);
        eyeL = new ModelRenderer(this, 0, 0);
        eyeL.addBox(-1.5F, -6F, -2.5F, 1, 1, 1, f2);
        eyeL.setRotationPoint(1.5F, 5F, 0.0F);
        eyeR = new ModelRenderer(this, 0, 0);
        eyeR.addBox(0.5F, -6F, -2.5F, 1, 1, 1, f2);
        eyeR.setRotationPoint(1.5F, 5F, 0.0F);
        neck = new ModelRenderer(this, 24, 12);
        neck.addBox(-1.5F, -2F, -0.5F, 3, 2, 1, f2);
        neck.setRotationPoint(1.5F, 5F, 0.0F);
        ears = new ModelRenderer(this, 44, 0);
        ears.addBox(-3.5F, -5.5F, 0.0F, 7, 1, 1, f2);
        ears.setRotationPoint(1.5F, 5F, 0.0F);
        body = new ModelRenderer(this, 0, 8);
        body.addBox(-4F, -9F, 0.0F, 9, 10, 3, f2);
        body.setRotationPoint(1.0F, 14F, -1F);
        legL = new ModelRenderer(this, 8, 21);
        legL.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3, f2);
        legL.setRotationPoint(-0.5F, 15F, 0.5F);
        footL = new ModelRenderer(this, 22, 21);
        footL.addBox(-1.5F, 8F, -2.5F, 3, 1, 6, f2);
        footL.setRotationPoint(-0.5F, 15F, 0.0F);
        legR = new ModelRenderer(this, 8, 21);
        legR.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3, f2);
        legR.setRotationPoint(3.5F, 15F, 0.5F);
        footR = new ModelRenderer(this, 22, 21);
        footR.addBox(-1.5F, 8F, -2.5F, 3, 1, 6, f2);
        footR.setRotationPoint(3.5F, 15F, 0.0F);
        armL = new ModelRenderer(this, 0, 21);
        armL.addBox(-1F, 0.0F, -1F, 2, 9, 2, f2);
        armL.setRotationPoint(-4F, 5F, 0.5F);
        armL.rotateAngleZ = 0.08727F;
        armR = new ModelRenderer(this, 0, 21);
        armR.addBox(-1F, 0.0F, -1F, 2, 9, 2, f2);
        armR.setRotationPoint(7F, 5F, 0.5F);
        armR.rotateAngleZ = -0.08727F;
        chest = new ModelRenderer(this, 22, 0);
        chest.addBox(-3.5F, -2F, 0.0F, 7, 4, 1, f2);
        chest.setRotationPoint(1.5F, 10F, -2F);
        hand1R = new ModelRenderer(this, 44, 10);
        hand1R.addBox(-1F, 9F, -1F, 1, 2, 2, f2);
        hand1R.setRotationPoint(-4F, 5F, 0.5F);
        hand1R.rotateAngleZ = 0.13247F;
        hand2R = new ModelRenderer(this, 44, 10);
        hand2R.addBox(0.0F, 9F, -1F, 1, 2, 2, f2);
        hand2R.setRotationPoint(-4F, 5F, 0.5F);
        hand2R.rotateAngleZ = 0.04206F;
        hand2L = new ModelRenderer(this, 44, 10);
        hand2L.addBox(0.0F, 9F, -1F, 1, 2, 2, f2);
        hand2L.setRotationPoint(7F, 5F, 0.5F);
        hand2L.rotateAngleZ = 0.03857F;
        hand1L = new ModelRenderer(this, 44, 10);
        hand1L.addBox(1.0F, 9F, -1F, 1, 2, 2, f2);
        hand1L.setRotationPoint(7F, 5F, 0.5F);
        hand1L.rotateAngleZ = -0.04834F;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        robotHead.render(f5);
        mouth.render(f5);
        eyeL.render(f5);
        eyeR.render(f5);
        neck.render(f5);
        body.render(f5);
        legL.render(f5);
        footL.render(f5);
        legR.render(f5);
        footR.render(f5);
        armL.render(f5);
        armR.render(f5);
        chest.render(f5);
        hand1L.render(f5);
        hand2L.render(f5);
        hand2R.render(f5);
        hand1R.render(f5);
        ears.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        robotHead.rotateAngleY = f3 / (180F / (float)Math.PI);
        robotHead.rotateAngleX = f4 / (180F / (float)Math.PI);
        ears.rotateAngleY = f3 / (180F / (float)Math.PI);
        ears.rotateAngleX = f4 / (180F / (float)Math.PI);
        mouth.rotateAngleY = f3 / (180F / (float)Math.PI);
        mouth.rotateAngleX = f4 / (180F / (float)Math.PI);
        eyeL.rotateAngleY = f3 / (180F / (float)Math.PI);
        eyeL.rotateAngleX = f4 / (180F / (float)Math.PI);
        eyeR.rotateAngleY = f3 / (180F / (float)Math.PI);
        eyeR.rotateAngleX = f4 / (180F / (float)Math.PI);
        neck.rotateAngleY = f3 / (180F / (float)Math.PI);
        neck.rotateAngleX = f4 / (180F / (float)Math.PI);
        armR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F - 0.5F;
        armL.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F - 0.5F;

        if (taildirection > 0)
        {
            tailwag += 0.0002F;

            if (tailwag > 0.037F)
            {
                taildirection = taildirection * -1;
            }
        }
        else
        {
            tailwag -= 0.0002F;

            if ((double)tailwag < -0.036999999999999998D)
            {
                taildirection = taildirection * -1;
            }
        }

        hand1L.rotateAngleZ = -0.04834F + tailwag;
        hand2L.rotateAngleZ = 0.03857F - tailwag;
        hand1R.rotateAngleZ = 0.13247F + tailwag;
        hand2R.rotateAngleZ = 0.04206F - tailwag;
        hand1L.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F - 0.5F;
        hand1R.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F - 0.5F;
        hand2L.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F - 0.5F;
        hand2R.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F - 0.5F;
        legR.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        legL.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        legR.rotateAngleY = 0.0F;
        legL.rotateAngleY = 0.0F;
        footR.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        footL.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        footR.rotateAngleY = 0.0F;
        footL.rotateAngleY = 0.0F;
    }
}

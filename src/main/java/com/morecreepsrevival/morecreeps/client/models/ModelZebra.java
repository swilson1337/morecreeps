package com.morecreepsrevival.morecreeps.client.models;

import com.morecreepsrevival.morecreeps.common.entity.EntityZebra;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelZebra extends ModelBase
{
    public ModelRenderer leg1;
    public ModelRenderer leg2;
    public ModelRenderer leg3;
    public ModelRenderer leg4;
    public ModelRenderer body;
    public ModelRenderer mane2;
    public ModelRenderer neck;
    public ModelRenderer zebraHead;
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
    public ModelRenderer sidesaddleR;
    public ModelRenderer sidesaddleL;

    public ModelZebra()
    {
        taildirection = 1;
        leg1 = new ModelRenderer(this, 16, 22);
        leg1.addBox(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        leg1.setRotationPoint(-1.5F, 16F, -4F);
        leg1.rotateAngleX = 0.0F;
        leg1.rotateAngleY = 0.0F;
        leg1.rotateAngleZ = 0.0F;
        leg1.mirror = false;
        leg2 = new ModelRenderer(this, 16, 22);
        leg2.addBox(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        leg2.setRotationPoint(2.5F, 16F, -4F);
        leg2.rotateAngleX = 0.0F;
        leg2.rotateAngleY = 0.0F;
        leg2.rotateAngleZ = 0.0F;
        leg2.mirror = false;
        leg3 = new ModelRenderer(this, 16, 22);
        leg3.addBox(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        leg3.setRotationPoint(2.5F, 16F, 7F);
        leg3.rotateAngleX = 0.0F;
        leg3.rotateAngleY = 0.0F;
        leg3.rotateAngleZ = 0.0F;
        leg3.mirror = false;
        leg4 = new ModelRenderer(this, 16, 22);
        leg4.addBox(-1F, 0.0F, -1F, 2, 8, 2, 0.0F);
        leg4.setRotationPoint(-1.5F, 16F, 7F);
        leg4.rotateAngleX = 0.0F;
        leg4.rotateAngleY = 0.0F;
        leg4.rotateAngleZ = 0.0F;
        leg4.mirror = false;
        body = new ModelRenderer(this, 24, 10);
        body.addBox(0.0F, 0.0F, 0.0F, 6, 8, 14, 0.0F);
        body.setRotationPoint(-2.5F, 8F, -6F);
        body.rotateAngleX = 0.0F;
        body.rotateAngleY = 0.0F;
        body.rotateAngleZ = 0.0F;
        body.mirror = false;
        mane2 = new ModelRenderer(this, 50, 0);
        mane2.addBox(0.0F, 0.0F, 0.0F, 2, 7, 3, 0.0F);
        mane2.setRotationPoint(-0.5F, 3F, -8.5F);
        mane2.rotateAngleX = 0.4089647F;
        mane2.rotateAngleY = 0.0F;
        mane2.rotateAngleZ = 0.0F;
        mane2.mirror = false;
        neck = new ModelRenderer(this, 24, 11);
        neck.addBox(-1.5F, 0.0F, 0.0F, 3, 6, 3, 0.0F);
        neck.setRotationPoint(0.5F, 8F, -9.6F);
        neck.rotateAngleX = 0.7063936F;
        neck.rotateAngleY = 0.0F;
        neck.rotateAngleZ = 0.0F;
        neck.mirror = false;
        zebraHead = new ModelRenderer(this, 0, 0);
        zebraHead.addBox(-3F, -6F, -3F, 5, 5, 6, 0.0F);
        zebraHead.setRotationPoint(1.0F, 9F, -10F);
        zebraHead.rotateAngleX = 0.0F;
        zebraHead.rotateAngleY = 0.0F;
        zebraHead.rotateAngleZ = 0.0F;
        zebraHead.mirror = false;
        eyeL = new ModelRenderer(this, 7, 18);
        eyeL.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        eyeL.setRotationPoint(2.33F, 4F, -12F);
        eyeL.rotateAngleX = 0.0F;
        eyeL.rotateAngleY = 0.0F;
        eyeL.rotateAngleZ = 0.0F;
        eyeL.mirror = false;
        snout = new ModelRenderer(this, 0, 11);
        snout.addBox(0.0F, 0.0F, 0.0F, 3, 3, 4, 0.0F);
        snout.setRotationPoint(-1F, 4.5F, -17F);
        snout.rotateAngleX = 0.0F;
        snout.rotateAngleY = 0.0F;
        snout.rotateAngleZ = 0.0F;
        snout.mirror = false;
        earL = new ModelRenderer(this, 7, 20);
        earL.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        earL.setRotationPoint(2.0F, 1.0F, -10F);
        earL.rotateAngleX = 0.0F;
        earL.rotateAngleY = 0.0F;
        earL.rotateAngleZ = 0.0F;
        earL.mirror = false;
        mane1 = new ModelRenderer(this, 26, 0);
        mane1.addBox(0.0F, 0.0F, 0.0F, 2, 2, 5, 0.0F);
        mane1.setRotationPoint(-0.5F, 1.0F, -10.5F);
        mane1.rotateAngleX = -0.1323696F;
        mane1.rotateAngleY = 0.0F;
        mane1.rotateAngleZ = 0.0F;
        mane1.mirror = false;
        tail = new ModelRenderer(this, 0, 25);
        tail.addBox(-0.5F, 0.0F, 0.0F, 1, 6, 1, 0.0F);
        tail.setRotationPoint(0.5F, 10F, 7F);
        tail.rotateAngleX = 0.5089647F;
        tail.rotateAngleY = 0.0F;
        tail.rotateAngleZ = 0.0F;
        tail.mirror = false;
        eyeR = new ModelRenderer(this, 0, 18);
        eyeR.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
        eyeR.setRotationPoint(-2.3F, 4F, -12F);
        eyeR.rotateAngleX = 0.0F;
        eyeR.rotateAngleY = 0.0F;
        eyeR.rotateAngleZ = 0.0F;
        eyeR.mirror = false;
        earR = new ModelRenderer(this, 0, 20);
        earR.addBox(0.0F, 0.0F, 0.0F, 1, 2, 2, 0.0F);
        earR.setRotationPoint(-2F, 1.0F, -10F);
        earR.rotateAngleX = 0.0F;
        earR.rotateAngleY = 0.0F;
        earR.rotateAngleZ = 0.0F;
        earR.mirror = false;
        tail2 = new ModelRenderer(this, 14, 11);
        tail2.addBox(-0.5F, 5.5F, 0.0F, 1, 3, 1, 0.35F);
        tail2.setRotationPoint(0.5F, 10F, 7F);
        tail2.rotateAngleX = 0.5235988F;
        tail2.rotateAngleY = 0.0F;
        tail2.rotateAngleZ = 0.0F;
        tail2.mirror = false;
        saddle = new ModelRenderer(this, 50, 18);
        saddle.addBox(-1F, 0.0F, -2.5F, 2, 1, 5, 2.25F);
        saddle.setRotationPoint(0.5F, 9.5F, 1.0F);
        saddle.rotateAngleX = 0.0F;
        saddle.rotateAngleY = 0.0F;
        saddle.rotateAngleZ = 0.0F;
        saddle.mirror = false;
        sidesaddleR = new ModelRenderer(this, 41, 0);
        sidesaddleR.addBox(0.0F, 0.0F, 0.0F, 1, 6, 2, 0.2F);
        sidesaddleR.setRotationPoint(-3F, 8.5F, 0.0F);
        sidesaddleR.rotateAngleX = 0.0F;
        sidesaddleR.rotateAngleY = 0.0F;
        sidesaddleR.rotateAngleZ = 0.0F;
        sidesaddleR.mirror = false;
        sidesaddleL = new ModelRenderer(this, 41, 0);
        sidesaddleL.addBox(0.0F, 0.0F, 0.0F, 1, 6, 2, 0.2F);
        sidesaddleL.setRotationPoint(3F, 8.5F, 0.0F);
        sidesaddleL.rotateAngleX = 0.0F;
        sidesaddleL.rotateAngleY = 0.0F;
        sidesaddleL.rotateAngleZ = 0.0F;
        sidesaddleL.mirror = false;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        leg1.render(f5);
        leg2.render(f5);
        leg3.render(f5);
        leg4.render(f5);
        body.render(f5);
        mane2.render(f5);
        neck.render(f5);
        zebraHead.render(f5);
        eyeL.render(f5);
        snout.render(f5);
        earL.render(f5);
        mane1.render(f5);
        tail.render(f5);
        eyeR.render(f5);
        earR.render(f5);
        tail2.render(f5);

        if (entity instanceof EntityZebra && ((EntityZebra)entity).isTamed())
        {
            saddle.render(f5);
            sidesaddleR.render(f5);
            sidesaddleL.render(f5);
        }
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        if (taildirection > 0)
        {
            tailwag += 0.0002F;

            if (tailwag > 0.067F)
            {
                taildirection = taildirection * -1;
            }
        }
        else
        {
            tailwag -= 0.0002F;

            if ((double)tailwag < -0.067000000000000004D)
            {
                taildirection = taildirection * -1;
            }
        }

        tail.rotateAngleY = tail2.rotateAngleY = MathHelper.cos(f2 * 0.6662F + (float)Math.PI) * 0.24F;
        leg1.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leg2.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg3.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        leg4.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
    }
}

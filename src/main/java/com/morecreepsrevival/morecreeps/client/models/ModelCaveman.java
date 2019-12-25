package com.morecreepsrevival.morecreeps.client.models;

import com.morecreepsrevival.morecreeps.common.entity.EntityCaveman;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCaveman extends ModelBase
{
    public boolean heldItemLeft;
    public boolean heldItemRight;
    public boolean isSneak;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedHead;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer beard;
    public ModelRenderer club1;
    public ModelRenderer club2;
    public ModelRenderer club3;
    public ModelRenderer ice1;
    public ModelRenderer ice2;
    public ModelRenderer ice3;
    public ModelRenderer ice4;
    public ModelRenderer ice5;
    public ModelRenderer bust;
    public ModelRenderer girlhair;
    public ModelRenderer flower;

    public ModelCaveman()
    {
        this(0.0f);
    }

    public ModelCaveman(float f)
    {
        this(f, 0.0f);
    }

    public ModelCaveman(float f, float f1)
    {
        bipedBody = new ModelRenderer(this, 17, 0);
        bipedBody.addBox(-3F, 0.0F, -2F, 6, 9, 4);
        bipedBody.setRotationPoint(0.0F, 7F, 0.0F);
        bipedBody.rotateAngleX = 0.122173F;
        bipedHead = new ModelRenderer(this, 0, 0);
        bipedHead.addBox(-2F, -4F, -2F, 4, 4, 4);
        bipedHead.setRotationPoint(0.0F, 7F, -1F);
        bipedRightArm = new ModelRenderer(this, 0, 8);
        bipedRightArm.addBox(-2F, 0.0F, -1F, 2, 9, 2);
        bipedRightArm.setRotationPoint(-3F, 8F, 0.0F);
        bipedLeftArm = new ModelRenderer(this, 0, 8);
        bipedLeftArm.addBox(0.0F, 0.0F, -1F, 2, 9, 2);
        bipedLeftArm.setRotationPoint(3F, 8F, 0.0F);
        bipedRightLeg = new ModelRenderer(this, 0, 20);
        bipedRightLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3);
        bipedRightLeg.setRotationPoint(-1.5F, 16F, 1.0F);
        bipedLeftLeg = new ModelRenderer(this, 0, 20);
        bipedLeftLeg.addBox(-1.5F, 0.0F, -1.5F, 3, 8, 3);
        bipedLeftLeg.setRotationPoint(1.5F, 16F, 1.0F);
        beard = new ModelRenderer(this, 9, 14);
        beard.addBox(-2F, -1F, -2.4F, 4, 3, 1, 0.15F);
        beard.setRotationPoint(0.0F, 7F, -1F);
        beard.rotateAngleX = 0.122173F;
        club1 = new ModelRenderer(this, 38, 0);
        club1.addBox(-1.5F, 7F, -2.5F, 1, 1, 5);
        club1.setRotationPoint(-3F, 8F, 0.0F);
        club1.rotateAngleX = -0.0523599F;
        club2 = new ModelRenderer(this, 38, 0);
        club2.addBox(-2F, 6.5F, -6.5F, 2, 2, 4);
        club2.setRotationPoint(-3F, 8F, 0.0F);
        club2.rotateAngleX = -0.0523599F;
        club3 = new ModelRenderer(this, 38, 0);
        club3.addBox(-2.5F, 6F, -10F, 3, 3, 4);
        club3.setRotationPoint(-3F, 8F, 0.0F);
        club3.rotateAngleX = -0.0523599F;
        bust = new ModelRenderer(this, 38, 7);
        bust.addBox(0.0F, 0.0F, 0.0F, 5, 2, 3);
        bust.setRotationPoint(-2.5F, 9.5F, -4F);
        bust.rotateAngleX = 0.122173F;
        bust.rotateAngleY = 0.0F;
        bust.rotateAngleZ = 0.0F;
        girlhair = new ModelRenderer(this, 53, 0);
        girlhair.addBox(-2.5F, -3.5F, 2.2F, 5, 6, 1);
        girlhair.setRotationPoint(0.0F, 7F, -1F);
        girlhair.rotateAngleX = 0.3490652F;
        girlhair.rotateAngleY = 0.0F;
        girlhair.rotateAngleZ = 0.0F;
        flower = new ModelRenderer(this, 8, 8);
        flower.addBox(0.0F, 0.0F, 0.0F, 3, 3, 1);
        flower.setRotationPoint(1.4F, 2.0F, -1F);
        flower.rotateAngleX = 0.0F;
        flower.rotateAngleY = 0.0F;
        flower.rotateAngleZ = 0.2617994F;
        ice1 = new ModelRenderer(this, 32, 19);
        ice1.addBox(0.0F, 0.0F, 0.0F, 5, 5, 6);
        ice1.setRotationPoint(0.0F, 12F, -2.666667F);
        ice1.rotateAngleX = 0.1858931F;
        ice1.rotateAngleY = 0.1858931F;
        ice1.rotateAngleZ = 0.0743572F;
        ice2 = new ModelRenderer(this, 27, 14);
        ice2.addBox(0.0F, 0.0F, 0.0F, 8, 12, 6, 0.45F);
        ice2.setRotationPoint(-4F, 8F, -3F);
        ice2.rotateAngleX = -0.0371786F;
        ice2.rotateAngleY = -0.0743572F;
        ice2.rotateAngleZ = 0.0743572F;
        ice3 = new ModelRenderer(this, 24, 15);
        ice3.addBox(0.0F, 0.0F, 0.0F, 5, 7, 7);
        ice3.setRotationPoint(-4F, 3F, -3F);
        ice3.rotateAngleX = -0.0369599F;
        ice3.rotateAngleY = 0.2217595F;
        ice3.rotateAngleZ = -0.1478397F;
        ice4 = new ModelRenderer(this, 23, 15);
        ice4.addBox(0.0F, 0.0F, 0.0F, 4, 4, 10);
        ice4.setRotationPoint(-5.733333F, 14F, -9F);
        ice4.rotateAngleX = 0.1478397F;
        ice4.rotateAngleY = -0.0739198F;
        ice4.rotateAngleZ = -0.3695991F;
        ice5 = new ModelRenderer(this, 39, 16);
        ice5.addBox(0.0F, 0.0F, 0.0F, 6, 8, 4);
        ice5.setRotationPoint(-2.066667F, 7.8F, -0.2F);
        ice5.rotateAngleX = 0.1108797F;
        ice5.rotateAngleY = 0.2956793F;
        ice5.rotateAngleZ = -0.1108797F;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        bipedBody.render(f5);
        bipedHead.render(f5);
        bipedRightArm.render(f5);
        bipedLeftArm.render(f5);
        bipedRightLeg.render(f5);
        bipedLeftLeg.render(f5);
        club1.render(f5);
        club2.render(f5);
        club3.render(f5);

        EntityCaveman caveman = (EntityCaveman)entity;

        int frozen = caveman.getFrozenLevel();

        if (frozen > 4)
        {
            ice1.render(f5);
        }

        if (frozen > 3)
        {
            ice5.render(f5);
        }

        if (frozen > 2)
        {
            ice3.render(f5);
        }

        if (frozen > 1)
        {
            ice4.render(f5);
        }

        if (frozen > 0)
        {
            ice2.render(f5);
        }

        if (caveman.getCaveGirl())
        {
            bust.render(f5);
            girlhair.render(f5);
            flower.render(f5);
        }
        else
        {
            beard.render(f5);
        }
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        girlhair.rotateAngleY = flower.rotateAngleY = beard.rotateAngleY = bipedHead.rotateAngleY = f3 / (180F / (float)Math.PI);
        girlhair.rotateAngleX = flower.rotateAngleX = beard.rotateAngleX = bipedHead.rotateAngleX = f4 / (180F / (float)Math.PI);
        bust.rotateAngleY = bipedBody.rotateAngleY;
        bust.rotateAngleX = bipedBody.rotateAngleX;

        EntityCaveman caveman = (EntityCaveman)entity;

        if (caveman.getFrozenLevel() < 1)
        {
            if (caveman.getHammerSwing() != 0.0F)
            {
                club1.rotateAngleX = club2.rotateAngleX = club3.rotateAngleX = bipedRightArm.rotateAngleX = caveman.getHammerSwing();
            }
            else
            {
                club1.rotateAngleX = club2.rotateAngleX = club3.rotateAngleX = bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
            }

            bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
            bipedLeftArm.rotateAngleZ = 0.0F;
            bipedLeftArm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
            bipedLeftArm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
            bipedRightArm.rotateAngleZ = 0.0F;
            bipedRightArm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
            bipedRightArm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
            club1.rotateAngleZ = club2.rotateAngleZ = club3.rotateAngleZ = bipedRightArm.rotateAngleZ;
            club1.rotateAngleX = club2.rotateAngleX = club3.rotateAngleX = bipedRightArm.rotateAngleX;
            bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
            bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        }
    }
}

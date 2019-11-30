package com.morecreepsrevival.morecreeps.client.models;

import com.morecreepsrevival.morecreeps.common.entity.EntityCastleKing;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;

public class ModelCastleKing extends ModelBiped
{
    public ModelRenderer bipedHead;
    public ModelRenderer bipedBody;
    public ModelRenderer bipedRightArm;
    public ModelRenderer bipedLeftArm;
    public ModelRenderer bipedRightLeg;
    public ModelRenderer bipedLeftLeg;
    public ModelRenderer crown;
    public ModelRenderer rightshoe;
    public ModelRenderer leftshoe;

    public ModelCastleKing()
    {
        this(0.0f);
    }

    public ModelCastleKing(float f)
    {
        this(f, 0.0f);
    }

    public ModelCastleKing(float f, float f1)
    {
        float f2 = 0.0F;
        crown = new ModelRenderer(this, 40, 2);
        crown.addBox(-3F, -12F, -3F, 6, 4, 6, 0.0F);
        crown.setRotationPoint(0.0F, 4F, 0.0F);
        bipedHead = new ModelRenderer(this, 0, 0);
        bipedHead.addBox(-4F, -7F, -4F, 8, 8, 8, 0.0F);
        bipedHead.setRotationPoint(0.0F, 3F, 0.0F);
        bipedBody = new ModelRenderer(this, 28, 22);
        bipedBody.addBox(-3F, 3F, -2F, 6, 6, 4, 3F);
        bipedBody.setRotationPoint(0.0F, 4F, 0.0F);
        bipedRightArm = new ModelRenderer(this, 48, 16);
        bipedRightArm.addBox(-3F, -2F, -2F, 4, 12, 4, 0.0F);
        bipedRightArm.setRotationPoint(-7F, 6F, 0.0F);
        bipedLeftArm = new ModelRenderer(this, 48, 16);
        bipedLeftArm.addBox(-1F, -2F, -2F, 4, 12, 4, 0.0F);
        bipedLeftArm.setRotationPoint(7F, 6F, 0.0F);
        bipedRightLeg = new ModelRenderer(this, 0, 16);
        bipedRightLeg.addBox(-2F, 0.0F, -2F, 4, 8, 4, 0.0F);
        bipedRightLeg.setRotationPoint(-4F, 16F, 0.0F);
        bipedLeftLeg = new ModelRenderer(this, 0, 16);
        bipedLeftLeg.addBox(-2F, 0.0F, -2F, 4, 8, 4, 0.0F);
        bipedLeftLeg.setRotationPoint(4F, 16F, 0.0F);
        rightshoe = new ModelRenderer(this, 28, 16);
        rightshoe.addBox(-2F, 6F, -6F, 4, 2, 4, 0.0F);
        rightshoe.setRotationPoint(-4F, 16F, 0.0F);
        leftshoe = new ModelRenderer(this, 28, 16);
        leftshoe.addBox(-2F, 6F, -6F, 4, 2, 4, 0.0F);
        leftshoe.setRotationPoint(4F, 16F, 0.0F);
    }

    @Override
    public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f, entity);
        bipedHead.render(f5);
        bipedBody.render(f5);
        bipedRightArm.render(f5);
        bipedLeftArm.render(f5);
        bipedRightLeg.render(f5);
        bipedLeftLeg.render(f5);
        crown.render(f5);
        rightshoe.render(f5);
        leftshoe.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, @Nonnull Entity entity)
    {
        crown.rotateAngleY = bipedHead.rotateAngleY = f3 / (180F / (float)Math.PI);
        crown.rotateAngleX = bipedHead.rotateAngleX = f4 / (180F / (float)Math.PI);
        rightshoe.rotateAngleX = bipedRightLeg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        leftshoe.rotateAngleX = bipedLeftLeg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        bipedRightLeg.rotateAngleY = 0.0F;
        bipedLeftLeg.rotateAngleY = 0.0F;

        float hammerSwing = 0.0f;

        if (entity instanceof EntityCastleKing)
        {
            hammerSwing = ((EntityCastleKing)entity).getHammerSwing();
        }

        if (hammerSwing != 0.0F)
        {
            bipedRightArm.rotateAngleX = hammerSwing;
        }
        else
        {
            bipedRightArm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        }

        bipedLeftArm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        bipedRightArm.rotateAngleZ = 0.0F;
        bipedLeftArm.rotateAngleZ = 0.0F;
    }
}

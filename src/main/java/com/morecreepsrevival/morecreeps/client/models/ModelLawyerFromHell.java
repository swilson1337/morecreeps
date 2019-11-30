package com.morecreepsrevival.morecreeps.client.models;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;

public class ModelLawyerFromHell extends ModelBiped
{
    public ModelRenderer headLawyer;
    public ModelRenderer body;
    public ModelRenderer rightarm;
    public ModelRenderer leftarm;
    public ModelRenderer rightleg;
    public ModelRenderer leftleg;
    public ModelRenderer casehandle;
    public ModelRenderer case2;
    public ModelRenderer glasses;
    public ModelRenderer watch1;
    public ModelRenderer watch2;
    public ModelRenderer shoeL;
    public ModelRenderer shoeR;

    public ModelLawyerFromHell()
    {
        this(0.0f);
    }

    public ModelLawyerFromHell(float f)
    {
        this(f, 0.0f);
    }

    public ModelLawyerFromHell(float f, float f1)
    {
        float f2 = 0.0F;
        headLawyer = new ModelRenderer(this, 0, 0);
        headLawyer.addBox(-4F, -10F, -4F, 8, 8, 8, f2);
        headLawyer.setRotationPoint(0.0F, 2.0F, 0.0F);
        body = new ModelRenderer(this, 16, 16);
        body.addBox(-3F, 0.0F, -2F, 6, 12, 4, f2);
        body.setRotationPoint(0.0F, 0.0F, 0.0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 3, 12, 4, f2);
        rightarm.setRotationPoint(-3F, 2.0F, 0.0F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.addBox(-1F, -1F, -2F, 3, 12, 4, f2);
        leftarm.setRotationPoint(4F, 1.0F, 0.0F);
        rightleg = new ModelRenderer(this, 0, 16);
        rightleg.addBox(-2F, 0.0F, -2F, 3, 12, 4, f2);
        rightleg.setRotationPoint(-1F, 12F, 0.0F);
        leftleg = new ModelRenderer(this, 0, 16);
        leftleg.addBox(-2F, 0.0F, -2F, 3, 12, 4, f2);
        leftleg.setRotationPoint(2.0F, 12F, 0.0F);
        casehandle = new ModelRenderer(this, 32, 2);
        casehandle.addBox(0.0F, 9.25F, -3F, 1, 3, 6, f2);
        casehandle.setRotationPoint(4F, 1.0F, 0.0F);
        case2 = new ModelRenderer(this, 40, 0);
        case2.addBox(0.0F, 12.5F, -6F, 1, 5, 11, 1.0F);
        case2.setRotationPoint(4F, 1.0F, 0.0F);
        glasses = new ModelRenderer(this, 26, 0);
        glasses.addBox(-4F, -7F, -5.5F, 8, 2, 1, f2);
        glasses.setRotationPoint(0.0F, 2.0F, 0.0F);
        watch1 = new ModelRenderer(this, 16, 2);
        watch1.addBox(-3.5F, 7F, -2.5F, 4, 1, 5, f2);
        watch1.setRotationPoint(-3F, 2.0F, 0.0F);
        watch2 = new ModelRenderer(this, 45, 0);
        watch2.addBox(-4F, 7F, -1F, 1, 1, 1, f2);
        watch2.setRotationPoint(-3F, 2.0F, 0.0F);
        shoeR = new ModelRenderer(this, 0, 0);
        shoeR.addBox(-2F, 11F, -5F, 3, 1, 3, f2);
        shoeR.setRotationPoint(-1F, 12F, 0.0F);
        shoeL = new ModelRenderer(this, 0, 0);
        shoeL.addBox(-2F, 11F, -5F, 3, 1, 3, f2);
        shoeL.setRotationPoint(2.0F, 12F, 0.0F);
    }

    @Override
    public void render(@Nonnull Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);
        headLawyer.render(f5);
        body.render(f5);
        rightarm.render(f5);
        leftarm.render(f5);
        rightleg.render(f5);
        leftleg.render(f5);
        casehandle.render(f5);
        case2.render(f5);
        glasses.render(f5);
        watch1.render(f5);
        watch2.render(f5);
        shoeL.render(f5);
        shoeR.render(f5);
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, @Nonnull Entity entity)
    {
        glasses.rotateAngleY = headLawyer.rotateAngleY = f3 / (180F / (float)Math.PI);
        glasses.rotateAngleX = headLawyer.rotateAngleX = f4 / (180F / (float)Math.PI);
        watch1.rotateAngleX = watch2.rotateAngleX = rightarm.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        case2.rotateAngleX = casehandle.rotateAngleX = leftarm.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        watch1.rotateAngleZ = watch2.rotateAngleZ = rightarm.rotateAngleZ = 0.0F;
        case2.rotateAngleZ = casehandle.rotateAngleZ = leftarm.rotateAngleZ = 0.0F;
        shoeR.rotateAngleX = rightleg.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        shoeL.rotateAngleX = leftleg.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        rightleg.rotateAngleY = 0.0F;
        leftleg.rotateAngleY = 0.0F;
        rightarm.rotateAngleY = 0.0F;
        leftarm.rotateAngleY = 0.0F;
        watch1.rotateAngleZ = watch2.rotateAngleZ = rightarm.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        case2.rotateAngleZ = casehandle.rotateAngleZ = leftarm.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        watch1.rotateAngleX = watch2.rotateAngleX = rightarm.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        case2.rotateAngleX = casehandle.rotateAngleX = leftarm.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    }
}

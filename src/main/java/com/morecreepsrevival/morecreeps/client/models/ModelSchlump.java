package com.morecreepsrevival.morecreeps.client.models;

import com.morecreepsrevival.morecreeps.common.entity.EntitySchlump;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelSchlump extends ModelBase
{
    public ModelRenderer head;
    public ModelRenderer body;
    public ModelRenderer armL;
    public ModelRenderer butt;
    public ModelRenderer armR;
    public ModelRenderer legL;
    public ModelRenderer legR;
    public ModelRenderer neck;
    public ModelRenderer b1;
    public ModelRenderer b2;
    public ModelRenderer b3;
    public ModelRenderer b4;
    public ModelRenderer b5;
    public ModelRenderer b6;
    public ModelRenderer b7;
    public ModelRenderer b8;
    public ModelRenderer b9;
    public ModelRenderer b10;
    public ModelRenderer earR;
    public ModelRenderer earL;
    public ModelRenderer b11;
    public ModelRenderer b12;
    public ModelRenderer b13;
    public ModelRenderer b14;
    public ModelRenderer b15;
    public ModelRenderer b16;
    public ModelRenderer b17;
    public ModelRenderer b18;
    public ModelRenderer b19;
    public ModelRenderer b20;
    public ModelRenderer b21;
    public ModelRenderer b22;
    public ModelRenderer stash;
    public ModelRenderer b23;
    public ModelRenderer b24;
    public ModelRenderer b25;
    public ModelRenderer nose;
    public ModelRenderer head2;
    public ModelRenderer neck2;
    public ModelRenderer neck3;
    public ModelRenderer head3;
    public ModelRenderer b33;
    public ModelRenderer b26;
    public ModelRenderer b27;
    public ModelRenderer b28;
    public ModelRenderer b29;
    public ModelRenderer b30;
    public ModelRenderer b31;
    public ModelRenderer b32;
    public ModelRenderer b34;
    public ModelRenderer b36;
    public ModelRenderer b35;
    public ModelRenderer b38;
    public ModelRenderer b37;
    public ModelRenderer b39;
    public ModelRenderer b40;

    public ModelSchlump()
    {
        this(0.0f);
    }

    public ModelSchlump(float f)
    {
        this(f, 0.0f);
    }

    public ModelSchlump(float f, float f1)
    {
        head = new ModelRenderer(this, 0, 0);
        head.addBox(-3F, -6F, -3F, 6, 6, 6, 0.0F);
        head.setRotationPoint(0.0F, 14.93333F, 4F);
        head.rotateAngleX = 0.0F;
        head.rotateAngleY = 0.0F;
        head.rotateAngleZ = 0.0F;
        head.mirror = false;
        body = new ModelRenderer(this, 0, 12);
        body.addBox(-3F, 0.0F, -2.966667F, 6, 7, 5, -0.2F);
        body.setRotationPoint(0.0F, 16F, 5F);
        body.rotateAngleX = -0.3346075F;
        body.rotateAngleY = 0.0F;
        body.rotateAngleZ = 0.0F;
        body.mirror = false;
        armL = new ModelRenderer(this, 22, 23);
        armL.addBox(0.0F, 0.0F, 0.0F, 2, 7, 2, 0.0F);
        armL.setRotationPoint(3F, 17F, 4F);
        armL.rotateAngleX = -0.483322F;
        armL.rotateAngleY = -0.7063936F;
        armL.rotateAngleZ = 0.0F;
        armL.mirror = false;
        butt = new ModelRenderer(this, 24, 0);
        butt.addBox(0.0F, 0.0F, 0.8F, 6, 4, 4, 0.0F);
        butt.setRotationPoint(-3F, 21F, -4F);
        butt.rotateAngleX = 0.2974289F;
        butt.rotateAngleY = 0.0F;
        butt.rotateAngleZ = 0.0F;
        butt.mirror = false;
        armR = new ModelRenderer(this, 22, 23);
        armR.addBox(-2F, 0.0F, -1F, 2, 7, 2, 0.0F);
        armR.setRotationPoint(-3F, 17F, 3F);
        armR.rotateAngleX = 0.0F;
        armR.rotateAngleY = 0.2230717F;
        armR.rotateAngleZ = 0.1858931F;
        armR.mirror = false;
        legL = new ModelRenderer(this, 0, 24);
        legL.addBox(-2F, -1F, -4F, 3, 3, 4, 0.0F);
        legL.setRotationPoint(2.0F, 22F, -3F);
        legL.rotateAngleX = 0.0F;
        legL.rotateAngleY = -0.3717861F;
        legL.rotateAngleZ = 0.0F;
        legL.mirror = false;
        legR = new ModelRenderer(this, 0, 24);
        legR.addBox(-2F, -1F, -4F, 3, 3, 4, 0.0F);
        legR.setRotationPoint(-1F, 22F, -3F);
        legR.rotateAngleX = 0.0F;
        legR.rotateAngleY = 0.1487144F;
        legR.rotateAngleZ = 0.0F;
        legR.mirror = false;
        neck = new ModelRenderer(this, 30, 26);
        neck.addBox(0.0F, 0.0F, 0.0F, 3, 3, 3, 0.0F);
        neck.setRotationPoint(-1.5F, 14F, 3.266667F);
        neck.rotateAngleX = -0.1115358F;
        neck.rotateAngleY = 0.0F;
        neck.rotateAngleZ = 0.0F;
        neck.mirror = false;
        b1 = new ModelRenderer(this, 24, 8);
        b1.addBox(-1.466667F, 0.0F, -0.3333333F, 4, 2, 1, 0.0F);
        b1.setRotationPoint(-0.4666667F, 14.8F, 0.9333333F);
        b1.rotateAngleX = -0.2602503F;
        b1.rotateAngleY = 0.0F;
        b1.rotateAngleZ = 0.0F;
        b1.mirror = false;
        b2 = new ModelRenderer(this, 24, 11);
        b2.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b2.setRotationPoint(-1.533333F, 16.33333F, 0.0F);
        b2.rotateAngleX = -0.7807508F;
        b2.rotateAngleY = 0.0F;
        b2.rotateAngleZ = 0.1115358F;
        b2.mirror = false;
        b3 = new ModelRenderer(this, 24, 14);
        b3.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b3.setRotationPoint(-1.533333F, 18.06667F, -1F);
        b3.rotateAngleX = -0.9666439F;
        b3.rotateAngleY = 0.1115358F;
        b3.rotateAngleZ = 0.0F;
        b3.mirror = false;
        b4 = new ModelRenderer(this, 24, 17);
        b4.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b4.setRotationPoint(-1.533333F, 19.6F, -1.533333F);
        b4.rotateAngleX = -1.561502F;
        b4.rotateAngleY = 0.2974289F;
        b4.rotateAngleZ = 5.270557E-016F;
        b4.mirror = false;
        b5 = new ModelRenderer(this, 24, 20);
        b5.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b5.setRotationPoint(-2.333333F, 19.8F, -3.066667F);
        b5.rotateAngleX = -1.524323F;
        b5.rotateAngleY = 0.483322F;
        b5.rotateAngleZ = 4.650491E-016F;
        b5.mirror = false;
        b6 = new ModelRenderer(this, 34, 8);
        b6.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b6.setRotationPoint(-2.8F, 19.93333F, -4.866667F);
        b6.rotateAngleX = -1.07818F;
        b6.rotateAngleY = 0.8922867F;
        b6.rotateAngleZ = 0.0F;
        b6.mirror = false;
        b7 = new ModelRenderer(this, 34, 11);
        b7.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b7.setRotationPoint(-3.666667F, 20.93333F, -5.4F);
        b7.rotateAngleX = -0.9491906F;
        b7.rotateAngleY = 1.226894F;
        b7.rotateAngleZ = 5.270557E-016F;
        b7.mirror = false;
        b8 = new ModelRenderer(this, 34, 14);
        b8.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b8.setRotationPoint(-4.466667F, 22F, -6.066667F);
        b8.rotateAngleX = -0.9666439F;
        b8.rotateAngleY = 1.412787F;
        b8.rotateAngleZ = 0.0F;
        b8.mirror = false;
        b9 = new ModelRenderer(this, 34, 17);
        b9.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b9.setRotationPoint(-6.466667F, 23F, -6.466667F);
        b9.rotateAngleX = -1.581227F;
        b9.rotateAngleY = 0.8551081F;
        b9.rotateAngleZ = 0.0F;
        b9.mirror = false;
        b10 = new ModelRenderer(this, 34, 20);
        b10.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b10.setRotationPoint(-8.333333F, 23.2F, -8.2F);
        b10.rotateAngleX = -1.561502F;
        b10.rotateAngleY = 0.3346075F;
        b10.rotateAngleZ = 0.0F;
        b10.mirror = false;
        earR = new ModelRenderer(this, 0, 0);
        earR.addBox(-4F, -4F, 0.0F, 1, 2, 1, 0.0F);
        earR.setRotationPoint(0.0F, 14.9F, 4F);
        earR.rotateAngleX = 0.0F;
        earR.rotateAngleY = 0.0F;
        earR.rotateAngleZ = 0.0F;
        earR.mirror = false;
        earL = new ModelRenderer(this, 0, 0);
        earL.addBox(3F, -4.066667F, 0.0F, 1, 2, 1, 0.0F);
        earL.setRotationPoint(0.0F, 14.9F, 4F);
        earL.rotateAngleX = 0.0F;
        earL.rotateAngleY = 0.0F;
        earL.rotateAngleZ = 0.0F;
        earL.mirror = false;
        b11 = new ModelRenderer(this, 34, 20);
        b11.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b11.setRotationPoint(-9.266666F, 23F, -10.4F);
        b11.rotateAngleX = -1.561502F;
        b11.rotateAngleY = 0.07435722F;
        b11.rotateAngleZ = 0.0F;
        b11.mirror = false;
        b12 = new ModelRenderer(this, 34, 20);
        b12.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b12.setRotationPoint(-9.333333F, 23.2F, -13F);
        b12.rotateAngleX = -1.561502F;
        b12.rotateAngleY = -0.4089647F;
        b12.rotateAngleZ = 0.0F;
        b12.mirror = false;
        b13 = new ModelRenderer(this, 34, 20);
        b13.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b13.setRotationPoint(-8.333333F, 23.26667F, -15.2F);
        b13.rotateAngleX = -1.561502F;
        b13.rotateAngleY = -0.9294652F;
        b13.rotateAngleZ = 0.0F;
        b13.mirror = false;
        b14 = new ModelRenderer(this, 34, 20);
        b14.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b14.setRotationPoint(-6.533333F, 23.1F, -16.6F);
        b14.rotateAngleX = -1.553343F;
        b14.rotateAngleY = -1.264073F;
        b14.rotateAngleZ = 0.0F;
        b14.mirror = false;
        b15 = new ModelRenderer(this, 34, 20);
        b15.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b15.setRotationPoint(-2.6F, 23.3F, -13.86667F);
        b15.rotateAngleX = -1.561502F;
        b15.rotateAngleY = 1.561502F;
        b15.rotateAngleZ = 0.0F;
        b15.mirror = false;
        b16 = new ModelRenderer(this, 34, 20);
        b16.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b16.setRotationPoint(-1.666667F, 23.1F, -13.33333F);
        b16.rotateAngleX = -1.561502F;
        b16.rotateAngleY = 1.264073F;
        b16.rotateAngleZ = 0.0F;
        b16.mirror = false;
        b17 = new ModelRenderer(this, 34, 20);
        b17.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b17.setRotationPoint(-1.133333F, 23.3F, -12.66667F);
        b17.rotateAngleX = -1.561502F;
        b17.rotateAngleY = 0.8179294F;
        b17.rotateAngleZ = 0.0F;
        b17.mirror = false;
        b18 = new ModelRenderer(this, 34, 20);
        b18.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b18.setRotationPoint(-0.8666667F, 23.2F, -11.46667F);
        b18.rotateAngleX = -1.561502F;
        b18.rotateAngleY = 0.4461433F;
        b18.rotateAngleZ = 0.0F;
        b18.mirror = false;
        b19 = new ModelRenderer(this, 34, 20);
        b19.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b19.setRotationPoint(0.6F, 23.1F, -9.7F);
        b19.rotateAngleX = -1.561502F;
        b19.rotateAngleY = 0.6320364F;
        b19.rotateAngleZ = 0.0F;
        b19.mirror = false;
        b20 = new ModelRenderer(this, 34, 20);
        b20.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b20.setRotationPoint(2.6F, 23.2F, -8.4F);
        b20.rotateAngleX = -1.561502F;
        b20.rotateAngleY = 0.9666439F;
        b20.rotateAngleZ = 0.0F;
        b20.mirror = false;
        b21 = new ModelRenderer(this, 34, 20);
        b21.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b21.setRotationPoint(4.533333F, 23.1F, -8.066667F);
        b21.rotateAngleX = -1.561502F;
        b21.rotateAngleY = 1.412787F;
        b21.rotateAngleZ = 0.0F;
        b21.mirror = false;
        b22 = new ModelRenderer(this, 34, 20);
        b22.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b22.setRotationPoint(6.666667F, 23.2F, -8.733334F);
        b22.rotateAngleX = -1.561502F;
        b22.rotateAngleY = 1.85893F;
        b22.rotateAngleZ = 0.0F;
        b22.mirror = false;
        stash = new ModelRenderer(this, 32, 24);
        stash.addBox(-2F, -2F, -3.533333F, 4, 1, 1, 0.0F);
        stash.setRotationPoint(0.0F, 14.9F, 3.933333F);
        stash.rotateAngleX = 0.0F;
        stash.rotateAngleY = 0.0F;
        stash.rotateAngleZ = 0.0F;
        stash.mirror = false;
        b23 = new ModelRenderer(this, 34, 20);
        b23.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b23.setRotationPoint(8.466666F, 23.3F, -10F);
        b23.rotateAngleX = -1.561502F;
        b23.rotateAngleY = 2.193538F;
        b23.rotateAngleZ = 0.0F;
        b23.mirror = false;
        b24 = new ModelRenderer(this, 34, 20);
        b24.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b24.setRotationPoint(9.666667F, 23.1F, -11.93333F);
        b24.rotateAngleX = -1.561502F;
        b24.rotateAngleY = 2.602503F;
        b24.rotateAngleZ = 0.0F;
        b24.mirror = false;
        b25 = new ModelRenderer(this, 34, 20);
        b25.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b25.setRotationPoint(7.133333F, 23.2F, -13.73333F);
        b25.rotateAngleX = -1.561502F;
        b25.rotateAngleY = -0.8551081F;
        b25.rotateAngleZ = 0.0F;
        b25.mirror = false;
        nose = new ModelRenderer(this, 14, 24);
        nose.addBox(-0.8666667F, -3F, -3.933333F, 2, 1, 1, 0.0F);
        nose.setRotationPoint(0.0F, 14.9F, 4F);
        nose.rotateAngleX = 0.0F;
        nose.rotateAngleY = 0.0F;
        nose.rotateAngleZ = 0.0F;
        nose.mirror = false;
        head2 = new ModelRenderer(this, 44, 6);
        head2.addBox(-1.5F, -5.7F, -1.5F, 3, 3, 3, 0.0F);
        head2.setRotationPoint(2.2F, 17.8F, 3F);
        head2.rotateAngleX = 0.0F;
        head2.rotateAngleY = 0.0F;
        head2.rotateAngleZ = 0.8377581F;
        head2.mirror = false;
        neck2 = new ModelRenderer(this, 56, 6);
        neck2.addBox(-0.5F, -3.5F, -0.5F, 1, 3, 1, 0.0F);
        neck2.setRotationPoint(2.2F, 17.8F, 3F);
        neck2.rotateAngleX = 0.0F;
        neck2.rotateAngleY = 0.0F;
        neck2.rotateAngleZ = 0.8377581F;
        neck2.mirror = false;
        neck3 = new ModelRenderer(this, 56, 0);
        neck3.addBox(-0.5F, -3.6F, -0.5F, 1, 3, 1, 0.0F);
        neck3.setRotationPoint(-2.5F, 17.4F, 3F);
        neck3.rotateAngleX = 0.0F;
        neck3.rotateAngleY = 0.0F;
        neck3.rotateAngleZ = -0.7330383F;
        neck3.mirror = false;
        head3 = new ModelRenderer(this, 44, 0);
        head3.addBox(-1.5F, -5.733333F, -1.5F, 3, 3, 3, 0.0F);
        head3.setRotationPoint(-2.5F, 17.4F, 3F);
        head3.rotateAngleX = 0.0F;
        head3.rotateAngleY = 0.0F;
        head3.rotateAngleZ = -0.7330383F;
        head3.mirror = false;
        b33 = new ModelRenderer(this, 34, 20);
        b33.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b33.setRotationPoint(17.13333F, 23.1F, -9.133333F);
        b33.rotateAngleX = -1.553343F;
        b33.rotateAngleY = 2.732628F;
        b33.rotateAngleZ = 0.0F;
        b33.mirror = false;
        b26 = new ModelRenderer(this, 34, 20);
        b26.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b26.setRotationPoint(8.666667F, 23F, -15.2F);
        b26.rotateAngleX = -1.375609F;
        b26.rotateAngleY = -1.189716F;
        b26.rotateAngleZ = 0.0F;
        b26.mirror = false;
        b27 = new ModelRenderer(this, 34, 20);
        b27.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b27.setRotationPoint(11.06667F, 23.26667F, -16.26667F);
        b27.rotateAngleX = -1.553343F;
        b27.rotateAngleY = -1.673038F;
        b27.rotateAngleZ = 0.0F;
        b27.mirror = false;
        b28 = new ModelRenderer(this, 34, 20);
        b28.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b28.setRotationPoint(13.4F, 23.26667F, -16.33333F);
        b28.rotateAngleX = -1.553343F;
        b28.rotateAngleY = -2.230717F;
        b28.rotateAngleZ = 0.0F;
        b28.mirror = false;
        b29 = new ModelRenderer(this, 34, 20);
        b29.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b29.setRotationPoint(13.4F, 23.26667F, -16.33333F);
        b29.rotateAngleX = -1.553343F;
        b29.rotateAngleY = -2.230717F;
        b29.rotateAngleZ = 0.0F;
        b29.mirror = false;
        b30 = new ModelRenderer(this, 34, 20);
        b30.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b30.setRotationPoint(15.06667F, 23.26667F, -15.13333F);
        b30.rotateAngleX = -1.553343F;
        b30.rotateAngleY = -2.565324F;
        b30.rotateAngleZ = 0.0F;
        b30.mirror = false;
        b31 = new ModelRenderer(this, 34, 20);
        b31.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b31.setRotationPoint(16.73333F, 23.1F, -13.46667F);
        b31.rotateAngleX = -1.553343F;
        b31.rotateAngleY = -2.974289F;
        b31.rotateAngleZ = 0.0F;
        b31.mirror = false;
        b32 = new ModelRenderer(this, 34, 20);
        b32.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b32.setRotationPoint(16.86667F, 23.3F, -11.4F);
        b32.rotateAngleX = -1.553343F;
        b32.rotateAngleY = (float)Math.PI;
        b32.rotateAngleZ = 0.0F;
        b32.mirror = false;
        b34 = new ModelRenderer(this, 34, 20);
        b34.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b34.setRotationPoint(16.06667F, 23.3F, -7.2F);
        b34.rotateAngleX = -1.553343F;
        b34.rotateAngleY = 2.398021F;
        b34.rotateAngleZ = 0.0F;
        b34.mirror = false;
        b36 = new ModelRenderer(this, 34, 20);
        b36.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b36.setRotationPoint(13.06667F, 23.3F, -5.266667F);
        b36.rotateAngleX = -1.553343F;
        b36.rotateAngleY = 2.249306F;
        b36.rotateAngleZ = 0.0F;
        b36.mirror = false;
        b35 = new ModelRenderer(this, 34, 20);
        b35.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b35.setRotationPoint(14.2F, 23.1F, -5.6F);
        b35.rotateAngleX = -1.553343F;
        b35.rotateAngleY = 1.87752F;
        b35.rotateAngleZ = 0.0F;
        b35.mirror = false;
        b38 = new ModelRenderer(this, 34, 20);
        b38.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b38.setRotationPoint(11.26667F, 23.2F, -3.4F);
        b38.rotateAngleX = -1.553343F;
        b38.rotateAngleY = 2.992878F;
        b38.rotateAngleZ = 0.0F;
        b38.mirror = false;
        b37 = new ModelRenderer(this, 34, 20);
        b37.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b37.setRotationPoint(12.13333F, 23.1F, -4.6F);
        b37.rotateAngleX = -1.553343F;
        b37.rotateAngleY = 2.658271F;
        b37.rotateAngleZ = 0.0F;
        b37.mirror = false;
        b39 = new ModelRenderer(this, 34, 20);
        b39.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b39.setRotationPoint(10.73333F, 23F, -2.333333F);
        b39.rotateAngleX = -1.553343F;
        b39.rotateAngleY = -2.881342F;
        b39.rotateAngleZ = 0.0F;
        b39.mirror = false;
        b40 = new ModelRenderer(this, 34, 20);
        b40.addBox(0.0F, 0.0F, 0.0F, 3, 2, 1, 0.0F);
        b40.setRotationPoint(11.06667F, 23.3F, -1.6F);
        b40.rotateAngleX = -1.553343F;
        b40.rotateAngleY = -2.472378F;
        b40.rotateAngleZ = 0.0F;
        b40.mirror = false;
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
    {
        setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        head.render(f5);
        body.render(f5);
        armL.render(f5);
        butt.render(f5);
        armR.render(f5);
        legL.render(f5);
        legR.render(f5);
        neck.render(f5);
        earR.render(f5);
        earL.render(f5);
        nose.render(f5);

        int age = ((EntitySchlump)entity).getAge();

        if (age > 100)
        {
            stash.render(f5);
        }

        if (age > 200)
        {
            b1.render(f5);
        }

        if (age > 300)
        {
            b2.render(f5);
        }

        if (age > 400)
        {
            b3.render(f5);
        }

        if (age > 500)
        {
            b4.render(f5);
        }

        if (age > 600)
        {
            b5.render(f5);
        }

        if (age > 700)
        {
            b6.render(f5);
        }

        if (age > 800)
        {
            b7.render(f5);
        }

        if (age > 900)
        {
            b8.render(f5);
        }

        if (age > 1000)
        {
            b9.render(f5);
        }

        if (age > 1100)
        {
            b10.render(f5);
        }

        if (age > 1200)
        {
            b11.render(f5);
        }

        if (age > 1300)
        {
            b12.render(f5);
        }

        if (age > 1400)
        {
            b13.render(f5);
        }

        if (age > 1500)
        {
            b14.render(f5);
        }

        if (age > 1600)
        {
            b15.render(f5);
        }

        if (age > 1700)
        {
            b16.render(f5);
        }

        if (age > 1800)
        {
            b17.render(f5);
        }

        if (age > 1900)
        {
            b18.render(f5);
        }

        if (age > 2000)
        {
            b19.render(f5);
        }

        if (age > 2100)
        {
            b20.render(f5);
        }

        if (age > 2200)
        {
            b21.render(f5);
        }

        if (age > 2300)
        {
            b22.render(f5);
        }

        if (age > 2400)
        {
            b23.render(f5);
        }

        if (age > 2500)
        {
            b24.render(f5);
        }

        if (age > 2600)
        {
            b25.render(f5);
        }

        if (age > 2700)
        {
            b26.render(f5);
        }

        if (age > 2800)
        {
            b27.render(f5);
        }

        if (age > 2900)
        {
            b28.render(f5);
        }

        if (age > 3000)
        {
            b29.render(f5);
        }

        if (age > 3100)
        {
            b30.render(f5);
        }

        if (age > 3200)
        {
            b31.render(f5);
        }

        if (age > 3300)
        {
            b32.render(f5);
        }

        if (age > 3400)
        {
            b33.render(f5);
        }

        if (age > 3500)
        {
            b34.render(f5);
        }

        if (age > 3600)
        {
            b35.render(f5);
        }

        if (age > 3700)
        {
            b36.render(f5);
        }

        if (age > 3800)
        {
            b37.render(f5);
        }

        if (age > 3900)
        {
            b38.render(f5);
        }

        if (age > 4000)
        {
            b39.render(f5);
        }

        if (age > 4100)
        {
            b40.render(f5);
        }

        if (age > 5000)
        {
            head2.render(f5);
        }

        if (age > 5000)
        {
            neck2.render(f5);
        }

        if (age > 6000)
        {
            neck3.render(f5);
        }

        if (age > 6000)
        {
            head3.render(f5);
        }
    }

    @Override
    public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
    {
        armR.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 2.0F * f1 * 0.5F;
        armL.rotateAngleX = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
        armR.rotateAngleZ = 0.0F;
        armL.rotateAngleZ = 0.0F;
        legR.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1;
        legL.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1;
        nose.rotateAngleX = earL.rotateAngleX = earR.rotateAngleX = stash.rotateAngleX = head.rotateAngleX = 0.0F;
        nose.rotateAngleZ = earL.rotateAngleZ = earR.rotateAngleZ = stash.rotateAngleZ = head.rotateAngleZ = 0.0F;
        nose.rotateAngleX = earL.rotateAngleX = earR.rotateAngleX = stash.rotateAngleX = head.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        nose.rotateAngleZ = earL.rotateAngleZ = earR.rotateAngleZ = stash.rotateAngleZ = head.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        neck2.rotateAngleX = 0.0F;
        head2.rotateAngleX = 0.0F;
        neck2.rotateAngleZ = 0.669215F;
        head2.rotateAngleZ = 0.5948578F;
        neck2.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        head2.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        neck2.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        head2.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        neck3.rotateAngleX = 0.0F;
        head3.rotateAngleX = 0.0F;
        neck3.rotateAngleZ = -0.7330383F;
        head3.rotateAngleZ = -0.7330383F;
        neck3.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
        head3.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
        neck3.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        head3.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        b1.rotateAngleX = -0.2602503F;
        b1.rotateAngleZ = 0.0F;
        b1.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        b1.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        b2.rotateAngleX = -0.7807508F;
        b2.rotateAngleZ = 0.1115358F;
        b2.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        b2.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        b3.rotateAngleX = -0.9666439F;
        b3.rotateAngleZ = 0.0F;
        b3.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        b3.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        b4.rotateAngleX = -1.561502F;
        b4.rotateAngleZ = 5.270557E-016F;
        b4.rotateAngleY = 0.2974289F;
        b4.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        b4.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        b5.rotateAngleX = -1.524323F;
        b5.rotateAngleZ = 4.650491E-016F;
        b5.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.03F;
        b5.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.03F + 0.05F;
        b6.rotateAngleX = -1.07818F;
        b6.rotateAngleY = 0.8922867F;
        b6.rotateAngleZ = 0.0F;
        b6.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.02F;
        b6.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.02F + 0.05F;
        b7.rotateAngleX = -0.9491906F;
        b7.rotateAngleY = 1.226894F;
        b7.rotateAngleZ = 5.270557E-016F;
        b7.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.015F;
        b7.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.015F + 0.05F;
        armR.rotateAngleY = 0.0F;
        armL.rotateAngleY = 0.0F;

        if (swingProgress > -9990F)
        {
            armR.rotateAngleZ = MathHelper.sin(swingProgress * (float)Math.PI) * -0.4F;
        }

        armR.rotateAngleZ += MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        armL.rotateAngleZ -= MathHelper.cos(f2 * 0.09F) * 0.05F + 0.05F;
        armR.rotateAngleX += MathHelper.sin(f2 * 0.067F) * 0.05F;
        armL.rotateAngleX -= MathHelper.sin(f2 * 0.067F) * 0.05F;
    }
}

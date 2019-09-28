package com.morecreepsrevival.morecreeps.client.render.layer;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import com.morecreepsrevival.morecreeps.common.entity.EntityBlackSoul;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;

public class LayerBlackSoulEyes implements LayerRenderer<EntityBlackSoul>
{
    private static final ResourceLocation layerTexture = new ResourceLocation(MoreCreepsAndWeirdos.modid, "textures/entity/blacksoulglow2.png");

    private final RenderLivingBase<?> renderer;

    public LayerBlackSoulEyes(RenderLivingBase<?> rendererIn)
    {
        renderer = rendererIn;
    }

    @Override
    public void doRenderLayer(EntityBlackSoul entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        GlStateManager.depthMask(!entity.isInvisible());

        renderer.bindTexture(layerTexture);

        GlStateManager.enableBlend();

        GlStateManager.blendFunc(770, 771);

        GlStateManager.color(1.0f, 1.0f, 1.0f, (1.0f - entity.getBrightness()) * 0.5f);

        renderer.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        GlStateManager.disableBlend();
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}

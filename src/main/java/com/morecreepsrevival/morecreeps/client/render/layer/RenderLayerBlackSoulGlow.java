package com.morecreepsrevival.morecreeps.client.render.layer;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderLayerBlackSoulGlow implements LayerRenderer<EntityLivingBase>
{
    private static final ResourceLocation layerTexture = new ResourceLocation(MoreCreepsAndWeirdos.modid, "textures/entity/blacksoulglow.png");

    private final RenderLivingBase<?> renderer;

    public RenderLayerBlackSoulGlow(RenderLivingBase<?> rendererIn)
    {
        renderer = rendererIn;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
    {
        GlStateManager.depthMask(!entity.isInvisible());

        renderer.bindTexture(layerTexture);

        GlStateManager.enableBlend();

        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        renderer.getMainModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

        GlStateManager.disableBlend();
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return true;
    }
}

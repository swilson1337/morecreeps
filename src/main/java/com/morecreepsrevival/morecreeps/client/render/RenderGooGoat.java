package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelGooGoat;
import com.morecreepsrevival.morecreeps.client.render.layer.LayerGooGoatSlime;
import com.morecreepsrevival.morecreeps.common.entity.EntityGooGoat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;

import javax.annotation.Nullable;

public class RenderGooGoat<T extends EntityGooGoat> extends RenderCreep<T>
{
    public RenderGooGoat(RenderManager renderManager)
    {
        super(renderManager, new ModelGooGoat(), 0.5f);

        addLayer(new LayerGooGoatSlime(this));
    }

    @Override
    protected void doScaling(T entity)
    {
        float modelSize = entity.getModelSize();

        GlStateManager.scale(modelSize, modelSize, modelSize + 0.5f);
    }

    @Override
    public void doRender(@Nullable T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.enableNormalize();

        GlStateManager.enableBlend();

        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE);

        //GlStateManager.color(1.0f, 1.0f, 1.0f, 0.9f);

        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        //GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        GlStateManager.disableBlend();

        GlStateManager.disableNormalize();
    }
}

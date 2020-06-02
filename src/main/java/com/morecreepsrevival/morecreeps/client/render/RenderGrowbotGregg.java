package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelGrowbotGregg;
import com.morecreepsrevival.morecreeps.common.entity.EntityGrowbotGregg;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;

public class RenderGrowbotGregg<T extends EntityGrowbotGregg> extends RenderCreep<T>
{
    public RenderGrowbotGregg(RenderManager renderManager)
    {
        super(renderManager, new ModelGrowbotGregg(), 0.5f);

        addLayer(new LayerHeldItem(this));
    }

    @Override
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
    }
}

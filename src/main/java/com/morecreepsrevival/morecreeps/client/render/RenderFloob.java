package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelFloob;
import com.morecreepsrevival.morecreeps.common.entity.EntityFloob;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;

public class RenderFloob<T extends EntityFloob> extends RenderCreep<T>
{
    public RenderFloob(RenderManager renderManager)
    {
        super(renderManager, new ModelFloob(), 0.5f);

        addLayer(new LayerHeldItem(this));
    }

    @Override
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
    }
}

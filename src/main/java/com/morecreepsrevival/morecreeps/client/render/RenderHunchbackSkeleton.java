package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelHunchbackSkeleton;
import com.morecreepsrevival.morecreeps.common.entity.EntityHunchbackSkeleton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;

public class RenderHunchbackSkeleton<T extends EntityHunchbackSkeleton> extends RenderCreep<T>
{
    public RenderHunchbackSkeleton(RenderManager renderManager)
    {
        super(renderManager, new ModelHunchbackSkeleton(1.0f, false), 0.5f);

        addLayer(new LayerHeldItem(this));
    }

    @Override
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
    }
}

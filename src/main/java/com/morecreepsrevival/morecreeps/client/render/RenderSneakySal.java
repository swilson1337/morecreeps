package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelSneakySal;
import com.morecreepsrevival.morecreeps.common.entity.EntitySneakySal;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;

public class RenderSneakySal<T extends EntitySneakySal> extends RenderCreep<T>
{
    public RenderSneakySal(RenderManager renderManager)
    {
        super(renderManager, new ModelSneakySal(), 0.5f);

        addLayer(new LayerHeldItem(this));
    }

    @Override
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
    }

    @Override
    protected void doScaling(T entity)
    {
        float modelSize = entity.getModelSize();

        GlStateManager.scale(modelSize, modelSize + 0.1f, modelSize);
    }
}

package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelPonyGirl;
import com.morecreepsrevival.morecreeps.common.entity.EntityPonyGirl;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;

public class RenderPonyGirl<T extends EntityPonyGirl> extends RenderCreep<T>
{
    public RenderPonyGirl(RenderManager renderManager)
    {
        super(renderManager, new ModelPonyGirl(), 0.5f);

        addLayer(new LayerHeldItem(this));
    }

    @Override
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
    }
}

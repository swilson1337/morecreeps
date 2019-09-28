package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelCastleKing;
import com.morecreepsrevival.morecreeps.common.entity.EntityCastleKing;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;

public class RenderCastleKing<T extends EntityCastleKing> extends RenderCreep<T>
{
    public RenderCastleKing(RenderManager renderManager)
    {
        super(renderManager, new ModelCastleKing(), 0.5f);

        addLayer(new LayerHeldItem(this));
    }

    @Override
    protected void doScaling(T entity)
    {
        GlStateManager.scale(2.0f, 1.5f, 2.0f);
    }

    @Override
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
    }
}

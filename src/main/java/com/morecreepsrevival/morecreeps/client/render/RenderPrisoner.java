package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityPrisoner;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderPrisoner<T extends EntityPrisoner> extends RenderCreep<T>
{
    public RenderPrisoner(RenderManager renderManager)
    {
        super(renderManager, new ModelBiped(), 0.5f);
    }

    @Override
    protected void doScaling(T entity)
    {
        GlStateManager.scale(0.75f, 1.0f, 0.9f);
    }
}
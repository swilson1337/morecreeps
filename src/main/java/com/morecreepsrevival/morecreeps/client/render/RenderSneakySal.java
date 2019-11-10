package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelSneakySal;
import com.morecreepsrevival.morecreeps.common.entity.EntitySneakySal;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderSneakySal<T extends EntitySneakySal> extends RenderCreep<T>
{
    public RenderSneakySal(RenderManager renderManager)
    {
        super(renderManager, new ModelSneakySal(), 0.5f);
    }

    @Override
    protected void doScaling(T entity)
    {
        float modelSize = entity.getModelSize();

        GlStateManager.scale(modelSize, modelSize + 0.1f, modelSize);
    }
}

package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelSnowDevil;
import com.morecreepsrevival.morecreeps.common.entity.EntitySnowDevil;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderSnowDevil<T extends EntitySnowDevil> extends RenderCreep<T>
{
    public RenderSnowDevil(RenderManager renderManager)
    {
        super(renderManager, new ModelSnowDevil(), 0.5f);
    }

    @Override
    protected int getTamedNameOffset(T entity)
    {
        return (int)((1.0f - entity.getModelSize()) * 55.0f);
    }

    @Override
    protected boolean shouldDrawHealthBar()
    {
        return false;
    }
}

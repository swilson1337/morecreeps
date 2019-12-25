package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelCamel;
import com.morecreepsrevival.morecreeps.common.entity.EntityCamel;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderCamel<T extends EntityCamel> extends RenderCreep<T>
{
    public RenderCamel(RenderManager renderManager)
    {
        super(renderManager, new ModelCamel(), 0.5f);
    }

    @Override
    protected int getTamedNameOffset(T entity)
    {
        return -140 + (int)((2.0f - entity.getModelSize()) * 80.0f);
    }

    @Override
    protected boolean shouldDrawHealthBar()
    {
        return false;
    }
}

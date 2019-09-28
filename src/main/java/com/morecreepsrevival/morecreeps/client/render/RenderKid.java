package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelKid;
import com.morecreepsrevival.morecreeps.common.entity.EntityKid;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderKid<T extends EntityKid> extends RenderCreep<T>
{
    public RenderKid(RenderManager renderManager)
    {
        super(renderManager, new ModelKid(), 0.5f);
    }

    @Override
    protected float getShadowSize(T entity)
    {
        return (0.6f - entity.getModelSize());
    }
}

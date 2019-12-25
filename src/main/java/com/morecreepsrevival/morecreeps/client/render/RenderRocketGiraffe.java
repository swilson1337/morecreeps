package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelRocketGiraffe;
import com.morecreepsrevival.morecreeps.common.entity.EntityRocketGiraffe;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderRocketGiraffe<T extends EntityRocketGiraffe> extends RenderCreep<T>
{
    public RenderRocketGiraffe(RenderManager renderManager)
    {
        super(renderManager, new ModelRocketGiraffe(), 0.5f);
    }

    @Override
    protected int getTamedNameOffset(T entity)
    {
        return -150 + (int)((1.0f - entity.getModelSize()) * 160.0f);
    }

    @Override
    protected boolean shouldDrawHealthBar()
    {
        return false;
    }
}

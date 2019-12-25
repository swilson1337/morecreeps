package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelManDog;
import com.morecreepsrevival.morecreeps.common.entity.EntityManDog;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderManDog<T extends EntityManDog> extends RenderCreep<T>
{
    public RenderManDog(RenderManager renderManager)
    {
        super(renderManager, new ModelManDog(), 0.5f);
    }

    @Override
    protected boolean shouldDrawTamedStatus(T entity)
    {
        return false;
    }
}

package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelHunchback;
import com.morecreepsrevival.morecreeps.common.entity.EntityHunchback;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderHunchback<T extends EntityHunchback> extends RenderCreep<T>
{
    public RenderHunchback(RenderManager renderManager)
    {
        super(renderManager, new ModelHunchback(), 0.5f);
    }
}

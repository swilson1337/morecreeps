package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityGuineaPig;
import net.minecraft.client.renderer.entity.RenderManager;
import com.morecreepsrevival.morecreeps.client.models.ModelGuineaPig;

public class RenderGuineaPig<T extends EntityGuineaPig> extends RenderCreep<T>
{
    public RenderGuineaPig(RenderManager renderManager)
    {
        super(renderManager, new ModelGuineaPig(0.5f), 0.5f);
    }
}
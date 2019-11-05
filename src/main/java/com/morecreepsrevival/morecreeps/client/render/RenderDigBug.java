package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelDigBug;
import com.morecreepsrevival.morecreeps.common.entity.EntityDigBug;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderDigBug<T extends EntityDigBug> extends RenderCreep<T>
{
    public RenderDigBug(RenderManager renderManager)
    {
        super(renderManager, new ModelDigBug(), 0.5f);
    }
}
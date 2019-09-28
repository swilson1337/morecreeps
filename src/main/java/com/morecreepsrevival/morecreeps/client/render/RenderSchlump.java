package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelSchlump;
import com.morecreepsrevival.morecreeps.common.entity.EntitySchlump;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderSchlump<T extends EntitySchlump> extends RenderCreep<T>
{
    public RenderSchlump(RenderManager renderManager)
    {
        super(renderManager, new ModelSchlump(), 0.5f);
    }
}

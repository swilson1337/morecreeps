package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelBlorp;
import com.morecreepsrevival.morecreeps.common.entity.EntityBlorp;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderBlorp<T extends EntityBlorp> extends RenderCreep<T>
{
    public RenderBlorp(RenderManager renderManager)
    {
        super(renderManager, new ModelBlorp(), 0.5f);
    }
}

package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelCamelJockey;
import com.morecreepsrevival.morecreeps.common.entity.EntityCamelJockey;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderCamelJockey<T extends EntityCamelJockey> extends RenderCreep<T>
{
    public RenderCamelJockey(RenderManager renderManager)
    {
        super(renderManager, new ModelCamelJockey(), 0.5f);
    }
}

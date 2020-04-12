package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelGrowbotGregg;
import com.morecreepsrevival.morecreeps.common.entity.EntityGrowbotGregg;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderGrowbotGregg<T extends EntityGrowbotGregg> extends RenderCreep<T>
{
    public RenderGrowbotGregg(RenderManager renderManager)
    {
        super(renderManager, new ModelGrowbotGregg(), 0.5f);
    }
}

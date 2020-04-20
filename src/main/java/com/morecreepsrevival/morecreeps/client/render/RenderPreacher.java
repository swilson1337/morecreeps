package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelPreacher;
import com.morecreepsrevival.morecreeps.common.entity.EntityPreacher;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderPreacher<T extends EntityPreacher> extends RenderCreep<T>
{
    public RenderPreacher(RenderManager renderManager)
    {
        super(renderManager, new ModelPreacher(), 0.5f);
    }
}

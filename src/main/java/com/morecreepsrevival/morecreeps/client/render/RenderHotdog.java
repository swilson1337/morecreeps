package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelHotdog;
import com.morecreepsrevival.morecreeps.common.entity.EntityHotdog;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderHotdog<T extends EntityHotdog> extends RenderCreep<T>
{
    public RenderHotdog(RenderManager renderManager)
    {
        super(renderManager, new ModelHotdog(), 0.5f);
    }
}
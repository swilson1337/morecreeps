package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelRatMan;
import com.morecreepsrevival.morecreeps.common.entity.EntityRatMan;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderRatMan<T extends EntityRatMan> extends RenderCreep<T>
{
    public RenderRatMan(RenderManager renderManager)
    {
        super(renderManager, new ModelRatMan(), 0.5f);
    }
}

package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelPonyCloud;
import com.morecreepsrevival.morecreeps.common.entity.EntityPonyCloud;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderPonyCloud<T extends EntityPonyCloud> extends RenderCreep<T>
{
    public RenderPonyCloud(RenderManager renderManager)
    {
        super(renderManager, new ModelPonyCloud(), 0.5f);
    }
}

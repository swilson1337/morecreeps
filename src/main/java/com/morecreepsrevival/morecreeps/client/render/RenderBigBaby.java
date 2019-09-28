package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelBigBaby;
import com.morecreepsrevival.morecreeps.common.entity.EntityBigBaby;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderBigBaby<T extends EntityBigBaby> extends RenderCreep<T>
{
    public RenderBigBaby(RenderManager renderManager)
    {
        super(renderManager, new ModelBigBaby(), 0.5f);
    }
}

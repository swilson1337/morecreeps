package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelCastleCritter;
import com.morecreepsrevival.morecreeps.common.entity.EntityCastleCritter;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderCastleCritter<T extends EntityCastleCritter> extends RenderCreep<T>
{
    public RenderCastleCritter(RenderManager renderManager)
    {
        super(renderManager, new ModelCastleCritter(), 0.5f);
    }
}

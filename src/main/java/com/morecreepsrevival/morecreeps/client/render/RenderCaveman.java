package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelCaveman;
import com.morecreepsrevival.morecreeps.common.entity.EntityCaveman;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderCaveman<T extends EntityCaveman> extends RenderCreep<T>
{
    public RenderCaveman(RenderManager renderManager)
    {
        super(renderManager, new ModelCaveman(), 0.5f);
    }
}

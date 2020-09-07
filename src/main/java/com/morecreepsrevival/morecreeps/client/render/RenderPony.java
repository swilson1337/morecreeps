package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelPony;
import com.morecreepsrevival.morecreeps.common.entity.EntityPony;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderPony<T extends EntityPony> extends RenderCreep<T>
{
    public RenderPony(RenderManager renderManager)
    {
        super(renderManager, new ModelPony(), 0.5f);
    }
}

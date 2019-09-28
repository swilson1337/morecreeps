package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelG;
import com.morecreepsrevival.morecreeps.common.entity.EntityG;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderG<T extends EntityG> extends RenderCreep<T>
{
    public RenderG(RenderManager renderManager)
    {
        super(renderManager, new ModelG(), 0.5f);
    }
}
package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelDogHouse;
import com.morecreepsrevival.morecreeps.common.entity.EntityDogHouse;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderDogHouse<T extends EntityDogHouse> extends RenderCreep<T>
{
    public RenderDogHouse(RenderManager renderManager)
    {
        super(renderManager, new ModelDogHouse(), 0.0f);
    }
}

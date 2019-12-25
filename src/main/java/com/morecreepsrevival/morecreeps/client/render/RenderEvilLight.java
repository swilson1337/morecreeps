package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelEvilLight;
import com.morecreepsrevival.morecreeps.common.entity.EntityEvilLight;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderEvilLight<T extends EntityEvilLight> extends RenderCreep<T>
{
    public RenderEvilLight(RenderManager renderManager)
    {
        super(renderManager, new ModelEvilLight(), 0.0f);
    }
}

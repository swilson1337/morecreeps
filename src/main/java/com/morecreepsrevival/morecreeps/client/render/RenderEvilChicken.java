package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelEvilChicken;
import com.morecreepsrevival.morecreeps.common.entity.EntityEvilChicken;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderEvilChicken<T extends EntityEvilChicken> extends RenderCreep<T>
{
    public RenderEvilChicken(RenderManager renderManager)
    {
        super(renderManager, new ModelEvilChicken(), 0.5f);
    }
}

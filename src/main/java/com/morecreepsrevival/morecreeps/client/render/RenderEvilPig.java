package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelEvilPig;
import com.morecreepsrevival.morecreeps.common.entity.EntityEvilPig;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderEvilPig<T extends EntityEvilPig> extends RenderCreep<T>
{
    public RenderEvilPig(RenderManager renderManager)
    {
        super(renderManager, new ModelEvilPig(), 0.5f);
    }
}

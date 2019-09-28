package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityBabyMummy;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderBabyMummy<T extends EntityBabyMummy> extends RenderCreep<T>
{
    public RenderBabyMummy(RenderManager renderManager)
    {
        super(renderManager, new ModelBiped(), 0.5f);
    }
}

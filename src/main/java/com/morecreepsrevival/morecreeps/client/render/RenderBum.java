package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelBum;
import com.morecreepsrevival.morecreeps.common.entity.EntityBum;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderBum<T extends EntityBum> extends RenderCreep<T>
{
    public RenderBum(RenderManager renderManager)
    {
        super(renderManager, new ModelBum(), 0.5f);
    }
}

package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelZebra;
import com.morecreepsrevival.morecreeps.common.entity.EntityZebra;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderZebra<T extends EntityZebra> extends RenderCreep<T>
{
    public RenderZebra(RenderManager renderManager)
    {
        super(renderManager, new ModelZebra(), 0.5f);
    }
}

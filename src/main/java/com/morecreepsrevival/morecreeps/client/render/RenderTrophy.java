package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelTrophy;
import com.morecreepsrevival.morecreeps.common.entity.EntityTrophy;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderTrophy<T extends EntityTrophy> extends RenderCreep<T>
{
    public RenderTrophy(RenderManager renderManager)
    {
        super(renderManager, new ModelTrophy());
    }
}

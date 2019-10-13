package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelHorseHead;
import com.morecreepsrevival.morecreeps.common.entity.EntityHorseHead;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderHorseHead<T extends EntityHorseHead> extends RenderCreep<T>
{
    public RenderHorseHead(RenderManager renderManager)
    {
        super(renderManager, new ModelHorseHead(), 0.5f);
    }
}

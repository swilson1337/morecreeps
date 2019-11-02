package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelBubbleScum;
import com.morecreepsrevival.morecreeps.common.entity.EntityBubbleScum;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderBubbleScum<T extends EntityBubbleScum> extends RenderCreep<T>
{
    public RenderBubbleScum(RenderManager renderManager)
    {
        super(renderManager, new ModelBubbleScum(), 0.5f);
    }
}

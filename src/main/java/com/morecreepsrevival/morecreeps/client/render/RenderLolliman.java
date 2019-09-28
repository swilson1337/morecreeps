package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelLolliman;
import com.morecreepsrevival.morecreeps.common.entity.EntityLolliman;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderLolliman<T extends EntityLolliman> extends RenderCreep<T>
{
    public RenderLolliman(RenderManager renderManager)
    {
        super(renderManager, new ModelLolliman(), 0.5f);
    }
}

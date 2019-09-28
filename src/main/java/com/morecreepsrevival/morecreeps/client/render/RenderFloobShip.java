package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelFloobShip;
import com.morecreepsrevival.morecreeps.common.entity.EntityFloobShip;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderFloobShip<T extends EntityFloobShip> extends RenderCreep<T>
{
    public RenderFloobShip(RenderManager renderManager)
    {
        super(renderManager, new ModelFloobShip(), 0.5f);

        shadowSize = 2.8f;
    }

    @Override
    protected void doScaling(T entity)
    {
        GlStateManager.scale(4.0f, 3.0f, 4.0f);
    }
}

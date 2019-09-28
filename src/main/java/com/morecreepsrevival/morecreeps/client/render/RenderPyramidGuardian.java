package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelPyramidGuardian;
import com.morecreepsrevival.morecreeps.common.entity.EntityPyramidGuardian;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderPyramidGuardian<T extends EntityPyramidGuardian> extends RenderCreep<T>
{
    public RenderPyramidGuardian(RenderManager renderManager)
    {
        super(renderManager, new ModelPyramidGuardian(), 0.5f);
    }

    @Override
    protected void doScaling(T entity)
    {
        GlStateManager.scale(0.55f, 0.55f, 0.75f);
    }
}

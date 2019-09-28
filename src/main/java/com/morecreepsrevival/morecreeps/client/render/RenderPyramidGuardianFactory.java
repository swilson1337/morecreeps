package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityPyramidGuardian;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderPyramidGuardianFactory implements IRenderFactory<EntityPyramidGuardian>
{
    @Override
    public Render<? super EntityPyramidGuardian> createRenderFor(RenderManager renderManager)
    {
        return new RenderPyramidGuardian<>(renderManager);
    }
}
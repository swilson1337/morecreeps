package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityFloob;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFloobFactory implements IRenderFactory<EntityFloob>
{
    @Override
    public Render<? super EntityFloob> createRenderFor(RenderManager renderManager)
    {
        return new RenderFloob<>(renderManager);
    }
}

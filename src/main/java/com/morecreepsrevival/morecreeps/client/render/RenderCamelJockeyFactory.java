package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityCamelJockey;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderCamelJockeyFactory implements IRenderFactory<EntityCamelJockey>
{
    @Override
    public Render<? super EntityCamelJockey> createRenderFor(RenderManager renderManager)
    {
        return new RenderCamelJockey<>(renderManager);
    }
}

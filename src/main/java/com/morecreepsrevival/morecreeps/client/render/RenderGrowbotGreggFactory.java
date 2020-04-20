package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityGrowbotGregg;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderGrowbotGreggFactory implements IRenderFactory<EntityGrowbotGregg>
{
    @Override
    public Render<? super EntityGrowbotGregg> createRenderFor(RenderManager renderManager)
    {
        return new RenderGrowbotGregg<>(renderManager);
    }
}

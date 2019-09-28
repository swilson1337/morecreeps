package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityThief;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderThiefFactory implements IRenderFactory<EntityThief>
{
    @Override
    public Render<? super EntityThief> createRenderFor(RenderManager renderManager)
    {
        return new RenderThief<>(renderManager);
    }
}

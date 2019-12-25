package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityCamel;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderCamelFactory implements IRenderFactory<EntityCamel>
{
    @Override
    public Render<? super EntityCamel> createRenderFor(RenderManager renderManager)
    {
        return new RenderCamel<>(renderManager);
    }
}

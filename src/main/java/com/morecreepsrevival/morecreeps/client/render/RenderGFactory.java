package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityG;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderGFactory implements IRenderFactory<EntityG>
{
    @Override
    public Render<? super EntityG> createRenderFor(RenderManager renderManager)
    {
        return new RenderG<>(renderManager);
    }
}

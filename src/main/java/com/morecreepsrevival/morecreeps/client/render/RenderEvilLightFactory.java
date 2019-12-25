package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityEvilLight;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderEvilLightFactory implements IRenderFactory<EntityEvilLight>
{
    @Override
    public Render<? super EntityEvilLight> createRenderFor(RenderManager renderManager)
    {
        return new RenderEvilLight<>(renderManager);
    }
}

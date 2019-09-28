package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntitySchlump;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderSchlumpFactory implements IRenderFactory<EntitySchlump>
{
    @Override
    public Render<? super EntitySchlump> createRenderFor(RenderManager renderManager)
    {
        return new RenderSchlump<>(renderManager);
    }
}

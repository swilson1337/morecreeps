package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityGuineaPig;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderGuineaPigFactory implements IRenderFactory<EntityGuineaPig>
{
    @Override
    public Render<? super EntityGuineaPig> createRenderFor(RenderManager renderManager)
    {
        return new RenderGuineaPig<>(renderManager);
    }
}

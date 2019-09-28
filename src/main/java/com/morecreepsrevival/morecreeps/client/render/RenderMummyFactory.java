package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityMummy;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderMummyFactory implements IRenderFactory<EntityMummy>
{
    @Override
    public Render<? super EntityMummy> createRenderFor(RenderManager renderManager)
    {
        return new RenderMummy<>(renderManager);
    }
}

package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityBabyMummy;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBabyMummyFactory implements IRenderFactory<EntityBabyMummy>
{
    @Override
    public Render<? super EntityBabyMummy> createRenderFor(RenderManager renderManager)
    {
        return new RenderBabyMummy<>(renderManager);
    }
}

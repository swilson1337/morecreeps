package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityBlorp;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBlorpFactory implements IRenderFactory<EntityBlorp>
{
    @Override
    public Render<? super EntityBlorp> createRenderFor(RenderManager renderManager)
    {
        return new RenderBlorp<>(renderManager);
    }
}

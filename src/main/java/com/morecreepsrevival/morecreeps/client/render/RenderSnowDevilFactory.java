package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntitySnowDevil;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderSnowDevilFactory implements IRenderFactory<EntitySnowDevil>
{
    @Override
    public Render<? super EntitySnowDevil> createRenderFor(RenderManager renderManager)
    {
        return new RenderSnowDevil<>(renderManager);
    }
}

package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityPonyCloud;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderPonyCloudFactory implements IRenderFactory<EntityPonyCloud>
{
    @Override
    public Render<? super EntityPonyCloud> createRenderFor(RenderManager renderManager)
    {
        return new RenderPonyCloud<>(renderManager);
    }
}

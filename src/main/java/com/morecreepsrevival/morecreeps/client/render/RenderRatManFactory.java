package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityRatMan;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderRatManFactory implements IRenderFactory<EntityRatMan>
{
    @Override
    public Render<? super EntityRatMan> createRenderFor(RenderManager renderManager)
    {
        return new RenderRatMan<>(renderManager);
    }
}

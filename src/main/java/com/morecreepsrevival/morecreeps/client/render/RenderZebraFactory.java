package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityZebra;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderZebraFactory implements IRenderFactory<EntityZebra>
{
    @Override
    public Render<? super EntityZebra> createRenderFor(RenderManager renderManager)
    {
        return new RenderZebra<>(renderManager);
    }
}

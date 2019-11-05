package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityDigBug;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderDigBugFactory implements IRenderFactory<EntityDigBug>
{
    @Override
    public Render<? super EntityDigBug> createRenderFor(RenderManager renderManager)
    {
        return new RenderDigBug<>(renderManager);
    }
}

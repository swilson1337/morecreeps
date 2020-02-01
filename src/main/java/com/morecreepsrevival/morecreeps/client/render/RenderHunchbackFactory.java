package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityHunchback;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderHunchbackFactory implements IRenderFactory<EntityHunchback>
{
    @Override
    public Render<? super EntityHunchback> createRenderFor(RenderManager renderManager)
    {
        return new RenderHunchback<>(renderManager);
    }
}

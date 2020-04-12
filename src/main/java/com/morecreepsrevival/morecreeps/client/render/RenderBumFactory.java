package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityBum;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBumFactory implements IRenderFactory<EntityBum>
{
    @Override
    public Render<? super EntityBum> createRenderFor(RenderManager renderManager)
    {
        return new RenderBum<>(renderManager);
    }
}

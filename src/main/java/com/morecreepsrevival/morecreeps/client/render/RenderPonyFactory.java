package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityPony;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderPonyFactory implements IRenderFactory<EntityPony>
{
    @Override
    public Render<? super EntityPony> createRenderFor(RenderManager renderManager)
    {
        return new RenderPony<>(renderManager);
    }
}

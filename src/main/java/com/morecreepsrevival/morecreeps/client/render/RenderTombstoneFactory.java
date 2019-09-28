package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityTombstone;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraft.client.renderer.entity.Render;

public class RenderTombstoneFactory implements IRenderFactory<EntityTombstone>
{
    @Override
    public Render<? super EntityTombstone> createRenderFor(RenderManager renderManager)
    {
        return new RenderTombstone<>(renderManager);
    }
}

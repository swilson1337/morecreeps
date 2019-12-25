package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityCaveman;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderCavemanFactory implements IRenderFactory<EntityCaveman>
{
    @Override
    public Render<? super EntityCaveman> createRenderFor(RenderManager renderManager)
    {
        return new RenderCaveman<>(renderManager);
    }
}

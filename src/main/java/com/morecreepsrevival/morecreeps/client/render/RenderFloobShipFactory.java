package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityFloobShip;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderFloobShipFactory implements IRenderFactory<EntityFloobShip>
{
    @Override
    public Render<? super EntityFloobShip> createRenderFor(RenderManager renderManager)
    {
        return new RenderFloobShip<>(renderManager);
    }
}

package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityDogHouse;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderDogHouseFactory implements IRenderFactory<EntityDogHouse>
{
    @Override
    public Render<? super EntityDogHouse> createRenderFor(RenderManager renderManager)
    {
        return new RenderDogHouse<>(renderManager);
    }
}

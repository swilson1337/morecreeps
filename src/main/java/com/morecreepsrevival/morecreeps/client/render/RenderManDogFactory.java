package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityManDog;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderManDogFactory implements IRenderFactory<EntityManDog>
{
    @Override
    public Render<? super EntityManDog> createRenderFor(RenderManager renderManager)
    {
        return new RenderManDog<>(renderManager);
    }
}

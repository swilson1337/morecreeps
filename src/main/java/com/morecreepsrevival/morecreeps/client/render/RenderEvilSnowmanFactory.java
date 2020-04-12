package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityEvilSnowman;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderEvilSnowmanFactory implements IRenderFactory<EntityEvilSnowman>
{
    @Override
    public Render<? super EntityEvilSnowman> createRenderFor(RenderManager renderManager)
    {
        return new RenderEvilSnowman<>(renderManager);
    }
}

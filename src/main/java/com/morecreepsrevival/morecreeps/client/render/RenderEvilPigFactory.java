package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityEvilPig;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderEvilPigFactory implements IRenderFactory<EntityEvilPig>
{
    @Override
    public Render<? super EntityEvilPig> createRenderFor(RenderManager renderManager)
    {
        return new RenderEvilPig<>(renderManager);
    }
}

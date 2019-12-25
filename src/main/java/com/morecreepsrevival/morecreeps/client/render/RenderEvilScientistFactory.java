package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityEvilScientist;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderEvilScientistFactory implements IRenderFactory<EntityEvilScientist>
{
    @Override
    public Render<? super EntityEvilScientist> createRenderFor(RenderManager renderManager)
    {
        return new RenderEvilScientist<>(renderManager);
    }
}

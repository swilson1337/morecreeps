package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityGooGoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderGooGoatFactory implements IRenderFactory<EntityGooGoat>
{
    @Override
    public Render<? super EntityGooGoat> createRenderFor(RenderManager renderManager)
    {
        return new RenderGooGoat<>(renderManager);
    }
}

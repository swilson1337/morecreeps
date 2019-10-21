package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityHorseHead;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderHorseHeadFactory implements IRenderFactory<EntityHorseHead>
{
    @Override
    public Render<? super EntityHorseHead> createRenderFor(RenderManager renderManager)
    {
        return new RenderHorseHead<>(renderManager);
    }
}

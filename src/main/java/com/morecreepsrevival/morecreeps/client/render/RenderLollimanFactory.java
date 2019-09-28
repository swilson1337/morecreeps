package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityLolliman;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderLollimanFactory implements IRenderFactory<EntityLolliman>
{
    @Override
    public Render<? super EntityLolliman> createRenderFor(RenderManager renderManager)
    {
        return new RenderLolliman<>(renderManager);
    }
}

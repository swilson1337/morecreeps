package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityPreacher;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderPreacherFactory implements IRenderFactory<EntityPreacher>
{
    @Override
    public Render<? super EntityPreacher> createRenderFor(RenderManager renderManager)
    {
        return new RenderPreacher<>(renderManager);
    }
}

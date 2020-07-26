package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityPonyGirl;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderPonyGirlFactory implements IRenderFactory<EntityPonyGirl>
{
    @Override
    public Render<? super EntityPonyGirl> createRenderFor(RenderManager renderManager)
    {
        return new RenderPonyGirl<>(renderManager);
    }
}

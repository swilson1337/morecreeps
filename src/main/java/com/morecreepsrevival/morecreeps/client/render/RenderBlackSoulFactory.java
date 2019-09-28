package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityBlackSoul;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBlackSoulFactory implements IRenderFactory<EntityBlackSoul>
{
    @Override
    public Render<? super EntityBlackSoul> createRenderFor(RenderManager renderManager)
    {
        return new RenderBlackSoul<>(renderManager);
    }
}

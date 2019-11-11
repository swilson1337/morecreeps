package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityPrisoner;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderPrisonerFactory implements IRenderFactory<EntityPrisoner>
{
    @Override
    public Render<? super EntityPrisoner> createRenderFor(RenderManager renderManager)
    {
        return new RenderPrisoner<>(renderManager);
    }
}

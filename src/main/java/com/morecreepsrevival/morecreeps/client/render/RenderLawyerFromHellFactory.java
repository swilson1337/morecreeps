package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityLawyerFromHell;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderLawyerFromHellFactory implements IRenderFactory<EntityLawyerFromHell>
{
    @Override
    public Render<? super EntityLawyerFromHell> createRenderFor(RenderManager renderManager)
    {
        return new RenderLawyerFromHell<>(renderManager);
    }
}

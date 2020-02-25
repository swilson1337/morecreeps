package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityHunchbackSkeleton;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderHunchbackSkeletonFactory implements IRenderFactory<EntityHunchbackSkeleton>
{
    @Override
    public Render<? super EntityHunchbackSkeleton> createRenderFor(RenderManager renderManager)
    {
        return new RenderHunchbackSkeleton<>(renderManager);
    }
}

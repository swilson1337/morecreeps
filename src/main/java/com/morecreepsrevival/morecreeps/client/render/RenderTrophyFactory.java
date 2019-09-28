package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityTrophy;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderTrophyFactory implements IRenderFactory<EntityTrophy>
{
    public Render<? super EntityTrophy> createRenderFor(RenderManager renderManager)
    {
        return new RenderTrophy<>(renderManager);
    }
}

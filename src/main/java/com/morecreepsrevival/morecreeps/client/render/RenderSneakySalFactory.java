package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntitySneakySal;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderSneakySalFactory implements IRenderFactory<EntitySneakySal>
{
    @Override
    public Render<? super EntitySneakySal> createRenderFor(RenderManager renderManager)
    {
        return new RenderSneakySal<>(renderManager);
    }
}

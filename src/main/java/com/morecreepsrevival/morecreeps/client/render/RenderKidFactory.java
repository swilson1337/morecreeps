package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityKid;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderKidFactory implements IRenderFactory<EntityKid>
{
    @Override
    public Render<? super EntityKid> createRenderFor(RenderManager renderManager)
    {
        return new RenderKid<>(renderManager);
    }
}

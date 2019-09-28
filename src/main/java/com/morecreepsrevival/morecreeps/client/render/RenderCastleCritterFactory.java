package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityCastleCritter;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderCastleCritterFactory implements IRenderFactory<EntityCastleCritter>
{
    @Override
    public Render<? super EntityCastleCritter> createRenderFor(RenderManager renderManager)
    {
        return new RenderCastleCritter<>(renderManager);
    }
}

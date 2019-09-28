package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityCastleGuard;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderCastleGuardFactory implements IRenderFactory<EntityCastleGuard>
{
    @Override
    public Render<? super EntityCastleGuard> createRenderFor(RenderManager renderManager)
    {
        return new RenderCastleGuard<>(renderManager);
    }
}

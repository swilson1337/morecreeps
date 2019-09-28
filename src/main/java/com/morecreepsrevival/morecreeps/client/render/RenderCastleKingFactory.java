package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityCastleKing;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderCastleKingFactory implements IRenderFactory<EntityCastleKing>
{
    @Override
    public Render<? super EntityCastleKing> createRenderFor(RenderManager renderManager)
    {
        return new RenderCastleKing<>(renderManager);
    }
}

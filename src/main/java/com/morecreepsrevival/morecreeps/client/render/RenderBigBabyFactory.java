package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityBigBaby;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBigBabyFactory implements IRenderFactory<EntityBigBaby>
{
    @Override
    public Render<? super EntityBigBaby> createRenderFor(RenderManager renderManager)
    {
        return new RenderBigBaby<>(renderManager);
    }
}

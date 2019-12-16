package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityRocketGiraffe;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderRocketGiraffeFactory implements IRenderFactory<EntityRocketGiraffe>
{
    @Override
    public Render<? super EntityRocketGiraffe> createRenderFor(RenderManager renderManager)
    {
        return new RenderRocketGiraffe<>(renderManager);
    }
}

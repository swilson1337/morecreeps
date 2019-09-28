package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityRobotTed;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderRobotTedFactory implements IRenderFactory<EntityRobotTed>
{
    @Override
    public Render<? super EntityRobotTed> createRenderFor(RenderManager renderManager)
    {
        return new RenderRobotTed<>(renderManager);
    }
}

package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityRobotTodd;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderRobotToddFactory implements IRenderFactory<EntityRobotTodd>
{
    @Override
    public Render<? super EntityRobotTodd> createRenderFor(RenderManager renderManager)
    {
        return new RenderRobotTodd<>(renderManager);
    }
}

package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelRobotTodd;
import com.morecreepsrevival.morecreeps.common.entity.EntityRobotTodd;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderRobotTodd<T extends EntityRobotTodd> extends RenderCreep<T>
{
    public RenderRobotTodd(RenderManager renderManager)
    {
        super(renderManager, new ModelRobotTodd(), 0.5f);
    }
}
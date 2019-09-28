package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelRobotTed;
import com.morecreepsrevival.morecreeps.common.entity.EntityRobotTed;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderRobotTed<T extends EntityRobotTed> extends RenderCreep<T>
{
    public RenderRobotTed(RenderManager renderManager)
    {
        super(renderManager, new ModelRobotTed(), 0.5f);
    }
}
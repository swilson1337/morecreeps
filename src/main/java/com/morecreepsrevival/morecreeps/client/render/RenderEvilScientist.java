package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelEvilScientist;
import com.morecreepsrevival.morecreeps.common.entity.EntityEvilScientist;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderEvilScientist<T extends EntityEvilScientist> extends RenderCreep<T>
{
    public RenderEvilScientist(RenderManager renderManager)
    {
        super(renderManager, new ModelEvilScientist(), 0.5f);
    }
}

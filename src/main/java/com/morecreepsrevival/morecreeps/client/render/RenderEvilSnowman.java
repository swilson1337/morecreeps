package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelEvilSnowman;
import com.morecreepsrevival.morecreeps.common.entity.EntityEvilSnowman;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderEvilSnowman<T extends EntityEvilSnowman> extends RenderCreep<T>
{
    public RenderEvilSnowman(RenderManager renderManager)
    {
        super(renderManager, new ModelEvilSnowman(), 0.5f);
    }
}

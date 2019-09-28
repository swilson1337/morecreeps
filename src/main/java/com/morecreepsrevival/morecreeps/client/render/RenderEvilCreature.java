package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityEvilCreature;
import com.morecreepsrevival.morecreeps.client.models.ModelEvilCreature;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderEvilCreature<T extends EntityEvilCreature> extends RenderCreep<T>
{
    public RenderEvilCreature(RenderManager renderManager)
    {
        super(renderManager, new ModelEvilCreature(), 2.9f);
    }
}

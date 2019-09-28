package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityBlackSoul;
import com.morecreepsrevival.morecreeps.client.render.layer.LayerBlackSoulEyes;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderManager;

public class RenderBlackSoul<T extends EntityBlackSoul> extends RenderCreep<T>
{
    public RenderBlackSoul(RenderManager renderManager)
    {
        super(renderManager, new ModelBiped(), 1.0f);

        addLayer(new LayerBlackSoulEyes(this));
    }
}

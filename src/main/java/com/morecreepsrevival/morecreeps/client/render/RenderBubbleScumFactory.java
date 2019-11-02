package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityBubbleScum;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderBubbleScumFactory implements IRenderFactory<EntityBubbleScum>
{
    @Override
    public Render<? super EntityBubbleScum> createRenderFor(RenderManager renderManager)
    {
        return new RenderBubbleScum<>(renderManager);
    }
}

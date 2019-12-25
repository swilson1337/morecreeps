package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityEvilChicken;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderEvilChickenFactory implements IRenderFactory<EntityEvilChicken>
{
    @Override
    public Render<? super EntityEvilChicken> createRenderFor(RenderManager renderManager)
    {
        return new RenderEvilChicken<>(renderManager);
    }
}

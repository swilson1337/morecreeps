package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.entity.EntityEvilCreature;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderEvilCreatureFactory implements IRenderFactory<EntityEvilCreature>
{
    @Override
    public Render<? super EntityEvilCreature> createRenderFor(RenderManager renderManager)
    {
        return new RenderEvilCreature<>(renderManager);
    }
}

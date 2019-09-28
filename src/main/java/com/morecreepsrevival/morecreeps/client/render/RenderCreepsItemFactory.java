package com.morecreepsrevival.morecreeps.client.render;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderCreepsItemFactory implements IRenderFactory<Entity>
{
    private final Item item;

    private final float shadowSize;

    public RenderCreepsItemFactory(Item itemIn, float shadowSizeIn)
    {
        super();

        item = itemIn;

        shadowSize = shadowSizeIn;
    }

    public RenderCreepsItemFactory(Item itemIn)
    {
        this(itemIn, 0.0f);
    }

    @Override
    public Render<? super Entity> createRenderFor(RenderManager renderManager)
    {
        return new RenderCreepsItem<>(renderManager, item, shadowSize);
    }
}

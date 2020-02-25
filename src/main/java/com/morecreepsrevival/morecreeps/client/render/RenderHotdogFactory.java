package com.morecreepsrevival.morecreeps.client.render;

        import com.morecreepsrevival.morecreeps.common.entity.EntityHotdog;
        import net.minecraft.client.renderer.entity.Render;
        import net.minecraft.client.renderer.entity.RenderManager;
        import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderHotdogFactory implements IRenderFactory<EntityHotdog>
{
    @Override
    public Render<? super EntityHotdog> createRenderFor(RenderManager renderManager)
    {
        return new RenderHotdog<>(renderManager);
    }
}

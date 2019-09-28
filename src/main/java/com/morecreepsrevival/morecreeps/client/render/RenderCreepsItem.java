package com.morecreepsrevival.morecreeps.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class RenderCreepsItem<T extends Entity> extends Render<T>
{
    protected final Item item;

    public RenderCreepsItem(RenderManager renderManagerIn, Item itemIn, float shadowSizeIn)
    {
        super(renderManagerIn);

        item = itemIn;

        shadowSize = shadowSizeIn;
    }

    @Override
    protected ResourceLocation getEntityTexture(@Nullable T entity)
    {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }

    public ItemStack getStackToRender(@Nullable T entity)
    {
        return new ItemStack(item);
    }

    @Override
    public void doRender(@Nullable T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();

        GlStateManager.translate((float)x, (float)y, (float)z);

        GlStateManager.enableRescaleNormal();

        GlStateManager.rotate(-renderManager.playerViewY, 0.0f, 1.0f, 0.0f);

        GlStateManager.rotate((float)((renderManager.options.thirdPersonView == 2) ? -1 : 1) * renderManager.playerViewX, 1.0f, 0.0f, 0.0f);

        GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);

        bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        if (renderOutlines)
        {
            GlStateManager.enableColorMaterial();

            GlStateManager.enableOutlineMode(getTeamColor(entity));
        }

        Minecraft.getMinecraft().getRenderItem().renderItem(getStackToRender(entity), ItemCameraTransforms.TransformType.GROUND);

        if (renderOutlines)
        {
            GlStateManager.disableOutlineMode();

            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();

        GlStateManager.popMatrix();

        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }
}

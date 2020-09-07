package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelLawyerFromHell;
import com.morecreepsrevival.morecreeps.common.capabilities.ILawyerFine;
import com.morecreepsrevival.morecreeps.common.capabilities.LawyerFineProvider;
import com.morecreepsrevival.morecreeps.common.entity.EntityLawyerFromHell;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class RenderLawyerFromHell<T extends EntityLawyerFromHell> extends RenderCreep<T>
{
    public RenderLawyerFromHell(RenderManager renderManager)
    {
        super(renderManager, new ModelLawyerFromHell(), 0.5f);

        addLayer(new LayerHeldItem(this));
    }

    @Override
    public void transformHeldFull3DItemLayer()
    {
        GlStateManager.translate(0.0F, 0.1875F, 0.0F);
    }

    @Override
    public void doRender(@Nullable T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        if (entity == null || entity.getUndead() || entity.getDistanceSq(renderManager.renderViewEntity) > 400)
        {
            return;
        }

        EntityPlayerSP player = Minecraft.getMinecraft().player;

        ILawyerFine capability = player.getCapability(LawyerFineProvider.capability, null);

        if (capability == null)
        {
            return;
        }

        int fine = capability.getFine();

        if (fine < 1)
        {
            return;
        }

        String str = "\247cFINE: \2472$\247f" + fine;

        if (fine >= 2500)
        {
            str += " \247cJAIL TIME";
        }

        float f2 = 1.6f;

        float f3 = 0.01666667f * f2;

        FontRenderer fontRenderer = getFontRendererFromRenderManager();

        GlStateManager.pushMatrix();

        GlStateManager.translate((float)x, (float)y + 1.1f, (float)z);

        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);

        GlStateManager.rotate(-renderManager.playerViewY, 0.0f, 1.0f, 0.0f);

        GlStateManager.rotate(-renderManager.playerViewX, 1.0f, 0.0f, 0.0f);

        GlStateManager.scale(-f3,-f3, f3);

        GlStateManager.disableLighting();

        GlStateManager.depthMask(false);

        GlStateManager.disableDepth();

        GlStateManager.enableBlend();

        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        int j = -60 + (int)((1.0f - entity.getModelSize()) * 9.0f);

        GlStateManager.disableTexture2D();

        Tessellator tessellator = Tessellator.getInstance();

        BufferBuilder bufferBuilder = tessellator.getBuffer();

        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);

        int k = fontRenderer.getStringWidth(str) / 2;

        bufferBuilder.pos(-k - 1, -1 + j, 0.0d).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();

        bufferBuilder.pos(-k - 1, 8 + j, 0.0d).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();

        bufferBuilder.pos(k + 1, 8 + j, 0.0d).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();

        bufferBuilder.pos(k + 1, -1 + j, 0.0d).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();

        tessellator.draw();

        GlStateManager.enableTexture2D();

        fontRenderer.drawString(str, -fontRenderer.getStringWidth(str) / 2, j, 0x20ffffff);

        GlStateManager.enableDepth();

        GlStateManager.depthMask(true);

        fontRenderer.drawString(str, -fontRenderer.getStringWidth(str) / 2, j, -1);

        GlStateManager.enableLighting();

        GlStateManager.disableBlend();

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        GlStateManager.popMatrix();
    }
}

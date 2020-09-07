package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.client.models.ModelTombstone;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderManager;
import com.morecreepsrevival.morecreeps.common.entity.EntityTombstone;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;

public class RenderTombstone<T extends EntityTombstone> extends RenderCreep<T>
{
    public RenderTombstone(RenderManager renderManager)
    {
        super(renderManager, new ModelTombstone(), 0.5f);
    }

    @Override
    protected boolean shouldDrawTamedStatus(T entity)
    {
        return false;
    }

    public void doRender(@Nullable T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        if (entity == null || entity.getDistanceSq(renderManager.renderViewEntity) > 16)
        {
            return;
        }

        float f2 = 1.6f;

        float f3 = 0.01666667f * f2;

        String s = "\247fHere lies \2476" + entity.getCreepName();

        String s1 = "\247f a level \2476" + entity.getLevel() + " \247f" + entity.getCreepTypeName();

        FontRenderer fontRenderer = getFontRendererFromRenderManager();

        GlStateManager.pushMatrix();

        GlStateManager.translate((float)x, (float)y + 1.1f, (float)z);

        GlStateManager.glNormal3f(0.0f, 1.0f, 0.0f);

        GlStateManager.rotate(-renderManager.playerViewY, 0.0f, 1.0f, 0.0f);

        GlStateManager.rotate(renderManager.playerViewX, 1.0f, 0.0f, 0.0f);

        GlStateManager.scale(-f3, -f3, f3);

        GlStateManager.disableLighting();

        GlStateManager.depthMask(false);

        GlStateManager.disableDepth();

        GlStateManager.enableBlend();

        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

        Tessellator tessellator = Tessellator.getInstance();

        BufferBuilder bufferBuilder = tessellator.getBuffer();

        GlStateManager.disableTexture2D();

        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);

        int i = fontRenderer.getStringWidth(s);

        if (fontRenderer.getStringWidth(s1) > i)
        {
            i = fontRenderer.getStringWidth(s1);
        }

        int j = i / 2;

        byte byte0 = -25;

        bufferBuilder.pos(-j -1, -1 + i, 0.0d).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();

        bufferBuilder.pos(-j - 1, 8 + i, 0.0d).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();

        bufferBuilder.pos(j + 1, 8 + i, 0.0d).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();

        bufferBuilder.pos(j + 1, -1 + i, 0.0d).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();

        tessellator.draw();

        GlStateManager.enableTexture2D();

        fontRenderer.drawString(s, -fontRenderer.getStringWidth(s) / 2, byte0 + 2, 0x20ffffff);

        fontRenderer.drawString(s1, -fontRenderer.getStringWidth(s1) / 2, byte0 + 12, 0x20ffffff);

        GlStateManager.enableDepth();

        GlStateManager.depthMask(true);

        fontRenderer.drawString(s, -fontRenderer.getStringWidth(s) / 2, byte0 + 2, -1);

        fontRenderer.drawString(s1, -fontRenderer.getStringWidth(s1) / 2, byte0 + 12, -1);

        GlStateManager.enableLighting();

        GlStateManager.disableBlend();

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        GlStateManager.popMatrix();
    }
}

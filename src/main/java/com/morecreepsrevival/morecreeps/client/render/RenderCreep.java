package com.morecreepsrevival.morecreeps.client.render;

import com.morecreepsrevival.morecreeps.common.MoreCreepsAndWeirdos;
import com.morecreepsrevival.morecreeps.common.entity.EntityCreepBase;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.model.ModelBase;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

public class RenderCreep<T extends EntityCreepBase> extends RenderLiving<T>
{
    public RenderCreep(RenderManager renderManager, ModelBase modelBase, float shadowSize)
    {
        super(renderManager, modelBase, shadowSize);
    }

    public RenderCreep(RenderManager renderManager, ModelBase modelBase)
    {
        super(renderManager, modelBase, 1.0f);
    }

    @Override
    protected ResourceLocation getEntityTexture(T entity)
    {
        return new ResourceLocation(MoreCreepsAndWeirdos.modid, entity.getTexture());
    }

    protected void drawTamedStatus(T entity, double x, double y, double z)
    {
        if (entity.getDistanceSq(renderManager.renderViewEntity) > 400)
        {
            return;
        }

        float f2 = 1.6F;

        float f3 = 0.01666667F * f2;

        String s = "";

        if (entity.getSpeedBoost() > 0)
        {
            s += "\2473* \247f";
        }

        s += entity.getCreepName();

        if (entity.getHealth() < (entity.getMaxHealth() / 2))
        {
            s += " \247c * WOUNDED *";
        }

        if (entity.canLevelUp())
        {
            s += " \2475<\2476" + entity.getLevel() + "\2475>";
        }

        if (s.isEmpty())
        {
            return;
        }

        FontRenderer fontrenderer = getFontRendererFromRenderManager();

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

        int i = getTamedNameOffset(entity);

        GlStateManager.disableTexture2D();

        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);

        int j = fontrenderer.getStringWidth(s) / 2;

        bufferBuilder.pos(-j - 1, -1 + i, 0.0d).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();

        bufferBuilder.pos(-j - 1, 8 + i, 0.0d).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();

        bufferBuilder.pos(j + 1, 8 + i, 0.0d).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();

        bufferBuilder.pos(j + 1, -1 + i, 0.0d).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex();

        if (shouldDrawHealthBar())
        {
            float f6 = entity.getHealth();

            float f7 = entity.getMaxHealth();

            float f8 = f6 / f7;

            float f9 = 50F * f8;

            bufferBuilder.pos(-25.0f + f9, -10 + i, 0.0d).color(1.0f, 0.0f, 0.0f, 1.0f).endVertex();

            bufferBuilder.pos(-25.0f + f9, -6 + i, 0.0d).color(1.0f, 0.0f, 0.0f, 1.0f).endVertex();

            bufferBuilder.pos(25.0d, -6 + i, 0.0d).color(1.0f, 0.0f, 0.0f, 1.0f).endVertex();

            bufferBuilder.pos(25.0d, -10 + i, 0.0d).color(1.0f, 0.0f, 0.0f, 1.0f).endVertex();

            bufferBuilder.pos(-25.0d, -10 + i, 0.0d).color(0.0f, 1.0f, 0.0f, 1.0f).endVertex();

            bufferBuilder.pos(-25.0d, -6 + i, 0.0d).color(0.0f, 1.0f, 0.0f, 1.0f).endVertex();

            bufferBuilder.pos(f9 - 25.0f, -6 + i, 0.0d).color(0.0f, 1.0f, 0.0f, 1.0f).endVertex();

            bufferBuilder.pos(f9 - 25.0f, -10 + i, 0.0d).color(0.0f, 1.0f, 0.0f, 1.0f).endVertex();
        }

        tessellator.draw();

        GlStateManager.enableTexture2D();

        fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, i, 0x20ffffff);

        GlStateManager.enableDepth();

        GlStateManager.depthMask(true);

        fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, i, -1);

        GlStateManager.enableLighting();

        GlStateManager.disableBlend();

        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);

        GlStateManager.popMatrix();
    }

    @Override
    public void doRender(@Nonnull T entity, double x, double y, double z, float entityYaw, float partialTicks)
    {
        shadowSize = getShadowSize(entity);

        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        if (shouldDrawTamedStatus(entity))
        {
            drawTamedStatus(entity, x, y, z);
        }
    }

    protected void doScaling(T entity)
    {
        float modelSize = entity.getModelSize();

        GlStateManager.scale(modelSize, modelSize, modelSize);
    }

    @Override
    protected void preRenderCallback(T entity, float f)
    {
        super.preRenderCallback(entity, f);

        if (shouldDoScaling(entity))
        {
            doScaling(entity);
        }
    }

    protected boolean shouldDrawTamedStatus(T entity)
    {
        return (entity.isTamable() && entity.isTamed() && entity.isEntityAlive() && !entity.isRiding());
    }

    protected boolean shouldDoScaling(T entity)
    {
        return true;
    }

    protected float getShadowSize(T entity)
    {
        return shadowSize;
    }

    protected int getTamedNameOffset(T entity)
    {
        return 10 + (int)((1.0F - entity.getModelSize()) * 20F);
    }

    protected boolean shouldDrawHealthBar()
    {
        return true;
    }
}
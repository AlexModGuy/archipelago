package com.github.alexthe666.archipelago.client.render.world;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.lwjgl.opengl.GL11;

public class ArchipelagoCloudRenderer extends IRenderHandler {

    private static final ResourceLocation CLOUDS_TEXTURES = new ResourceLocation("archipelago:textures/clouds.png");

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        GlStateManager.pushMatrix();
        this.renderCloudSide(partialTicks, world, mc);
        GlStateManager.popMatrix();
    }

    public void renderCloudSide(float partialTicks, WorldClient world, Minecraft mc) {
        Entity entity = mc.getRenderViewEntity();

        double d2 = (this.getCloudTickCounter() + partialTicks) * 4.0F;

        double d0 = entity.prevPosX + (entity.posX - entity.prevPosX) * partialTicks + d2 * 0.029999999329447746D;
        double d1 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * partialTicks;
        float f = (float) (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * partialTicks);

        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer buffer = tessellator.getBuffer();
        mc.renderEngine.bindTexture(CLOUDS_TEXTURES);

        Vec3d cloudColour = mc.theWorld.getCloudColour(partialTicks);
        float red = (float) cloudColour.xCoord;
        float green = (float) cloudColour.yCoord;
        float blue = (float) cloudColour.zCoord;

        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.alphaFunc(516, 0.1F);

        if (mc.gameSettings.anaglyph) {
            float anaglyphRed = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
            float anaglyphGreen = (red * 30.0F + green * 70.0F) / 100.0F;
            float anaglyphBlue = (red * 30.0F + blue * 70.0F) / 100.0F;
            red = anaglyphRed;
            green = anaglyphGreen;
            blue = anaglyphBlue;
        }

        float textureScale = 4.8828125E-4F * 4.0F;

        int k = MathHelper.floor_double(d0 / 2048.0D);
        int l = MathHelper.floor_double(d1 / 2048.0D);
        d0 = d0 - (k * 2048);
        d1 = d1 - (l * 2048);
        float renderY = f - (mc.theWorld.provider.getCloudHeight() + 0.33F);
        float f8 = (float) (d0 * textureScale);
        float f9 = (float) (d1 * textureScale);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);

        float alpha = 0.7F;

        for (int i1 = -256; i1 < 256; i1 += 32) {
            for (int j1 = -256; j1 < 256; j1 += 32) {
                buffer.pos(i1, renderY, j1 + 32).tex((i1 * textureScale + f8), (j1 + 32) * textureScale + f9).color(red, green, blue, alpha).endVertex();
                buffer.pos(i1 + 32, renderY, j1 + 32).tex((i1 + 32) * textureScale + f8, (j1 + 32) * textureScale + f9).color(red, green, blue, alpha).endVertex();
                buffer.pos(i1 + 32, renderY, j1).tex(((i1 + 32) * textureScale + f8), (j1 * textureScale + f9)).color(red, green, blue, alpha).endVertex();
                buffer.pos(i1, renderY, j1).tex(((i1) * textureScale + f8), j1 * textureScale + f9).color(red, green, blue, alpha).endVertex();
            }
        }

        GL11.glScalef(1, -1, 1);
        tessellator.draw();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        GlStateManager.alphaFunc(516, 0.5F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.enableCull();
    }

    public float getHeightFromCoords(int x, int z, float f) {
        float heightpoint = Minecraft.getMinecraft().theWorld.provider.getCloudHeight();
        int actualX = x - 128;
        int actualZ = z - 128;
        return (Math.abs(actualX * actualZ) / 2) - f + 0.33F;
    }

    public int getCloudTickCounter() {
        try {
            return (Integer) ReflectionHelper.findField(RenderGlobal.class, new String[] { "cloudTickCounter", "field_72773_u" }).get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

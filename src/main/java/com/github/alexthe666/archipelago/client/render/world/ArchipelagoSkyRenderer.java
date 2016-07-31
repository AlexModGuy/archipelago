package com.github.alexthe666.archipelago.client.render.world;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.lwjgl.opengl.GL11;

public class ArchipelagoSkyRenderer extends IRenderHandler {
    private static final ResourceLocation MOON_PHASES_TEXTURES = new ResourceLocation("textures/environment/moon_phases.png");
    private static final ResourceLocation SUN_TEXTURES = new ResourceLocation("textures/environment/sun.png");
    private static final ResourceLocation CLOUDS_TEXTURES = new ResourceLocation("archipelago:textures/clouds.png");

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        GlStateManager.disableTexture2D();
        Vec3d skyColour = mc.theWorld.getSkyColor(mc.getRenderViewEntity(), partialTicks);
        float red = (float) skyColour.xCoord;
        float green = (float) skyColour.yCoord;
        float blue = (float) skyColour.zCoord;
        if (mc.gameSettings.anaglyph) {
            float anaglyphRed = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
            float anaglyphGreen = (red * 30.0F + green * 70.0F) / 100.0F;
            float anaglypBlue = (red * 30.0F + blue * 70.0F) / 100.0F;
            red = anaglyphRed;
            green = anaglyphGreen;
            blue = anaglypBlue;
        }
        GlStateManager.color(red, green, blue);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer buffer = tessellator.getBuffer();
        GlStateManager.depthMask(false);
        GlStateManager.enableFog();
        GlStateManager.color(red, green, blue);
        if (mc.gameSettings.useVbo && this.getSkyVBO() != null) {
            this.getSkyVBO().bindBuffer();
            GlStateManager.glEnableClientState(GL11.GL_VERTEX_ARRAY);
            GlStateManager.glVertexPointer(3, GL11.GL_FLOAT, 12, 0);
            this.getSkyVBO().drawArrays(GL11.GL_QUADS);
            this.getSkyVBO().unbindBuffer();
            GlStateManager.glDisableClientState(GL11.GL_VERTEX_ARRAY);
        } else {
            GlStateManager.callList(this.getSkyCallist());
        }
        GlStateManager.disableFog();
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderHelper.disableStandardItemLighting();
        float[] sunriseColor = mc.theWorld.provider.calcSunriseSunsetColors(mc.theWorld.getCelestialAngle(partialTicks), partialTicks);
        if (sunriseColor != null) {
            GlStateManager.disableTexture2D();
            GlStateManager.shadeModel(GL11.GL_SMOOTH);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(MathHelper.sin(mc.theWorld.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            float sunriseRed = sunriseColor[0];
            float sunriseGreen = sunriseColor[1];
            float sunriseBlue = sunriseColor[2];
            if (mc.gameSettings.anaglyph) {
                float anaglyphRed = (sunriseRed * 30.0F + sunriseGreen * 59.0F + sunriseBlue * 11.0F) / 100.0F;
                float anaglyphGreen = (sunriseRed * 30.0F + sunriseGreen * 70.0F) / 100.0F;
                float anaglyphBlue = (sunriseRed * 30.0F + sunriseBlue * 70.0F) / 100.0F;
                sunriseRed = anaglyphRed;
                sunriseGreen = anaglyphGreen;
                sunriseBlue = anaglyphBlue;
            }
            buffer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);
            buffer.pos(0.0D, 100.0D, 0.0D).color(sunriseRed, sunriseGreen, sunriseBlue, sunriseColor[3]).endVertex();
            for (int section = 0; section <= 16; ++section) {
                float angle = (float) (section * (Math.PI * 6.0F) / 16.0F);
                float sin = MathHelper.sin(angle);
                float cos = MathHelper.cos(angle);
                buffer.pos(sin * 120.0F, cos * 120.0F, -cos * 100.0F * sunriseColor[3]).color(sunriseColor[0], sunriseColor[1], sunriseColor[2], 0.0F).endVertex();
            }
            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.shadeModel(GL11.GL_FLAT);
        }
        GlStateManager.enableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.pushMatrix();
        float alpha = 3.0F - mc.theWorld.getRainStrength(partialTicks);
        GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(mc.theWorld.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        float size = 35.0F;
        mc.renderEngine.bindTexture(SUN_TEXTURES);
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(-size, 100.0D, -size).tex(0.0D, 0.0D).endVertex();
        buffer.pos(size, 100.0D, -size).tex(1.0D, 0.0D).endVertex();
        buffer.pos(size, 100.0D, size).tex(1.0D, 1.0D).endVertex();
        buffer.pos(-size, 100.0D, size).tex(0.0D, 1.0D).endVertex();
        tessellator.draw();
        size = 35.0F;
        mc.renderEngine.bindTexture(MOON_PHASES_TEXTURES);
        int moonPhase = mc.theWorld.getMoonPhase();
        int phaseX = moonPhase % 4;
        int phaseY = moonPhase / 4 % 2;
        float moonTextureRight = phaseX / 4.0F;
        float moonTextureDown = phaseY / 2.0F;
        float moonTextureLeft = (phaseX + 1) / 4.0F;
        float moonTextureUp = (phaseY + 1) / 2.0F;
        buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        buffer.pos(-size, -100.0D, size).tex(moonTextureLeft, moonTextureUp).endVertex();
        buffer.pos(size, -100.0D, size).tex(moonTextureRight, moonTextureUp).endVertex();
        buffer.pos(size, -100.0D, -size).tex(moonTextureRight, moonTextureDown).endVertex();
        buffer.pos(-size, -100.0D, -size).tex(moonTextureLeft, moonTextureDown).endVertex();
        tessellator.draw();
        GlStateManager.disableTexture2D();
        float starBrightness = mc.theWorld.getStarBrightness(partialTicks) * alpha;
        if (starBrightness > 0.0F) {
            GlStateManager.color(starBrightness, starBrightness, starBrightness, starBrightness);
            if (mc.gameSettings.useVbo && this.getStarVBO() != null) {
                this.getStarVBO().bindBuffer();
                GlStateManager.glEnableClientState(GL11.GL_VERTEX_ARRAY);
                GlStateManager.glVertexPointer(3, GL11.GL_FLOAT, 12, 0);
                this.getStarVBO().drawArrays(GL11.GL_QUADS);
                this.getStarVBO().unbindBuffer();
                GlStateManager.glDisableClientState(GL11.GL_VERTEX_ARRAY);
            } else {
                GlStateManager.callList(this.getStarCallist());
            }
        }
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableFog();
        GlStateManager.popMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.color(0.0F, 0.0F, 0.0F);
        double horizonDelta = mc.thePlayer.getPositionEyes(partialTicks).yCoord - mc.theWorld.getHorizon();
        if (horizonDelta < 0.0D) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 12.0F, 0.0F);
            if (mc.gameSettings.useVbo && this.getSky2VBO() != null) {
                this.getSky2VBO().bindBuffer();
                GlStateManager.glEnableClientState(GL11.GL_VERTEX_ARRAY);
                GlStateManager.glVertexPointer(3, GL11.GL_FLOAT, 12, 0);
                this.getSky2VBO().drawArrays(GL11.GL_QUADS);
                this.getSky2VBO().unbindBuffer();
                GlStateManager.glDisableClientState(GL11.GL_VERTEX_ARRAY);
            } else {
                GlStateManager.callList(this.getSky2Callist());
            }
            GlStateManager.popMatrix();
            float horizonRenderY = (float) -(horizonDelta + 65.0F);
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
            buffer.pos(-1.0D, horizonRenderY, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, horizonRenderY, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, horizonRenderY, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, horizonRenderY, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, horizonRenderY, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, horizonRenderY, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, horizonRenderY, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, horizonRenderY, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            buffer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            tessellator.draw();
        }
        if (mc.theWorld.provider.isSkyColored()) {
            GlStateManager.color(red * 0.2F + 0.04F, green * 0.2F + 0.04F, blue * 0.6F + 0.1F);
        } else {
            GlStateManager.color(red, green, blue);
        }
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, -(horizonDelta - 16.0D), 0.0F);
        GlStateManager.callList(this.getSky2Callist());
        GlStateManager.popMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);
    }

    public net.minecraft.client.renderer.vertex.VertexBuffer getSkyVBO() {
        try {
            return (net.minecraft.client.renderer.vertex.VertexBuffer) ReflectionHelper.findField(RenderGlobal.class, new String[] { "skyVBO", "field_175012_t" }).get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public net.minecraft.client.renderer.vertex.VertexBuffer getSky2VBO() {
        try {
            return (net.minecraft.client.renderer.vertex.VertexBuffer) ReflectionHelper.findField(RenderGlobal.class, new String[] { "sky2VBO", "field_175011_u" }).get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public net.minecraft.client.renderer.vertex.VertexBuffer getStarVBO() {
        try {
            return (net.minecraft.client.renderer.vertex.VertexBuffer) ReflectionHelper.findField(RenderGlobal.class, new String[] { "starVBO", "field_175013_s" }).get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getSkyCallist() {
        try {
            return (Integer) ReflectionHelper.findField(RenderGlobal.class, new String[] { "glSkyList", "field_72771_w" }).get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSky2Callist() {
        try {
            return (Integer) ReflectionHelper.findField(RenderGlobal.class, new String[] { "glSkyList2", "field_72781_x" }).get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getStarCallist() {
        try {
            return (Integer) ReflectionHelper.findField(RenderGlobal.class, new String[] { "starGLCallList", "field_72772_v" }).get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

package com.github.alexthe666.archipelago.client.render.world;

import net.ilexiconn.llibrary.LLibrary;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
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
import org.lwjgl.util.glu.Sphere;

import java.lang.reflect.Field;

public class ArchipelagoSkyRenderer extends IRenderHandler {
    private static final ResourceLocation MOON_PHASES_TEXTURES = new ResourceLocation("textures/environment/moon_phases.png");
    private static final ResourceLocation SUN_TEXTURES = new ResourceLocation("textures/environment/sun.png");
    private static final ResourceLocation CLOUDS_TEXTURES = new ResourceLocation("archipelago:textures/clouds.png");

    private static Field CLOUD_TICK_COUNTER;
    private static Field SKY_VBO;
    private static Field SKY_2_VBO;
    private static Field STAR_VBO;
    private static Field SKY_LIST;
    private static Field SKY_2_LIST;
    private static Field STAR_LIST;

    private int skyboxList = -1;

    static {
        CLOUD_TICK_COUNTER = ReflectionHelper.findField(RenderGlobal.class, "cloudTickCounter", "field_72773_u");
        SKY_VBO = ReflectionHelper.findField(RenderGlobal.class, "skyVBO", "field_175012_t");
        SKY_2_VBO = ReflectionHelper.findField(RenderGlobal.class, "sky2VBO", "field_175011_u");
        STAR_VBO = ReflectionHelper.findField(RenderGlobal.class, "starVBO", "field_175013_s");
        SKY_LIST = ReflectionHelper.findField(RenderGlobal.class, "glSkyList", "field_72771_w");
        SKY_2_LIST = ReflectionHelper.findField(RenderGlobal.class, "glSkyList2", "field_72781_x");
        STAR_LIST = ReflectionHelper.findField(RenderGlobal.class, "starGLCallList", "field_72772_v");
    }

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
                GlStateManager.callList(this.getStarCallList());
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
                GlStateManager.callList(this.getSky2CallList());
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
        GlStateManager.callList(this.getSky2CallList());
        GlStateManager.popMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.depthMask(true);

        this.renderClouds(mc);
    }

    public void renderClouds(Minecraft mc) {
        if (this.skyboxList == -1) {
            GlStateManager.pushMatrix();
            this.skyboxList = GLAllocation.generateDisplayLists(1);
            GlStateManager.glNewList(this.skyboxList, GL11.GL_COMPILE);
            Tessellator tessellator = Tessellator.getInstance();
            VertexBuffer buffer = tessellator.getBuffer();

            double scale = 1.0 / 3.0;

            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
            buffer.pos(-30.0, 30.0, -30.0).tex(0.0, 0.5).endVertex();
            buffer.pos(30.0, 30.0, -30.0).tex(scale, 0.5).endVertex();
            buffer.pos(30.0, -30.0, -30.0).tex(scale, 1.0).endVertex();
            buffer.pos(-30.0, -30.0, -30.0).tex(0.0, 1.0).endVertex();
            tessellator.draw();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
            buffer.pos(-30.0, 30.0, 30.0).tex(scale * 3.0F, 0.5).endVertex();
            buffer.pos(30.0, 30.0, 30.0).tex(scale * 2.0F, 0.5).endVertex();
            buffer.pos(30.0, -30.0, 30.0).tex(scale * 2.0F, 1.0).endVertex();
            buffer.pos(-30.0, -30.0, 30.0).tex(scale * 3.0F, 1.0).endVertex();
            tessellator.draw();

            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
            buffer.pos(-30.0, 30.0, -30.0).tex(1.0, 0.0).endVertex();
            buffer.pos(-30.0, 30.0, 30.0).tex(1.0 - scale, 0.0).endVertex();
            buffer.pos(-30.0, -30.0, 30.0).tex(1.0 - scale, 0.5).endVertex();
            buffer.pos(-30.0, -30.0, -30.0).tex(1.0, 0.5).endVertex();
            tessellator.draw();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
            buffer.pos(30.0, 30.0, -30.0).tex(scale, 0.5).endVertex();
            buffer.pos(30.0, 30.0, 30.0).tex(scale * 2.0F, 0.5).endVertex();
            buffer.pos(30.0, -30.0, 30.0).tex(scale * 2.0F, 1.0).endVertex();
            buffer.pos(30.0, -30.0, -30.0).tex(scale, 1.0).endVertex();
            tessellator.draw();

            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
            buffer.pos(-30.0, 30.0, 30.0).tex(scale * 2.0F, 0.0).endVertex();
            buffer.pos(30.0, 30.0, 30.0).tex(scale * 2.0F, 0.5).endVertex();
            buffer.pos(30.0, 30.0, -30.0).tex(scale, 0.5).endVertex();
            buffer.pos(-30.0, 30.0, -30.0).tex(scale, 0.0).endVertex();
            tessellator.draw();
            buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
            buffer.pos(-30.0, -30.0, 30.0).tex(scale, 0.5).endVertex();
            buffer.pos(30.0, -30.0, 30.0).tex(scale, 0.0).endVertex();
            buffer.pos(30.0, -30.0, -30.0).tex(0.0, 0.0).endVertex();
            buffer.pos(-30.0, -30.0, -30.0).tex(0.0, 0.5).endVertex();
            tessellator.draw();

            GlStateManager.glEndList();
            GlStateManager.popMatrix();
        }

        GlStateManager.pushMatrix();

        mc.renderEngine.bindTexture(CLOUDS_TEXTURES);

        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.01F);
        GlStateManager.enableTexture2D();
        GlStateManager.disableFog();

        float partialTicks = LLibrary.PROXY.getPartialTicks();
        GlStateManager.scale(9.0F, 9.0F, 9.0F);

        float color = 1.0F - mc.theWorld.getStarBrightness(partialTicks) * 1.6F;

        GlStateManager.rotate((this.getCloudTickCounter() + partialTicks) * 0.005F, 0.0F, 1.0F, 0.0F);
        GlStateManager.color(color, color, color, color * 1.0F);

        GlStateManager.callList(this.skyboxList);

        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.5F);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }

    private net.minecraft.client.renderer.vertex.VertexBuffer getSkyVBO() {
        try {
            return (net.minecraft.client.renderer.vertex.VertexBuffer) SKY_VBO.get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private net.minecraft.client.renderer.vertex.VertexBuffer getSky2VBO() {
        try {
            return (net.minecraft.client.renderer.vertex.VertexBuffer) SKY_2_VBO.get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private net.minecraft.client.renderer.vertex.VertexBuffer getStarVBO() {
        try {
            return (net.minecraft.client.renderer.vertex.VertexBuffer) STAR_VBO.get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private int getSkyCallist() {
        try {
            return (Integer) SKY_LIST.get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getSky2CallList() {
        try {
            return (Integer) SKY_2_LIST.get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getStarCallList() {
        try {
            return (Integer) STAR_LIST.get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private int getCloudTickCounter() {
        try {
            return (Integer) CLOUD_TICK_COUNTER.get(Minecraft.getMinecraft().renderGlobal);
        } catch (IllegalArgumentException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return 0;
    }
}

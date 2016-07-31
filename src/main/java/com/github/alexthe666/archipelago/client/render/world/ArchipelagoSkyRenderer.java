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

public class ArchipelagoSkyRenderer extends IRenderHandler {
    private static final ResourceLocation MOON_PHASES_TEXTURES = new ResourceLocation("textures/environment/moon_phases.png");
    private static final ResourceLocation SUN_TEXTURES = new ResourceLocation("textures/environment/sun.png");
    private static final ResourceLocation CLOUDS_TEXTURES = new ResourceLocation("archipelago:textures/clouds.png");

    @Override
    public void render(float partialTicks, WorldClient world, Minecraft mc) {
        GlStateManager.disableTexture2D();
        Vec3d vec3d = mc.theWorld.getSkyColor(mc.getRenderViewEntity(), partialTicks);
        float f = (float) vec3d.xCoord;
        float f1 = (float) vec3d.yCoord;
        float f2 = (float) vec3d.zCoord;
        if (mc.gameSettings.anaglyph) {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }
        GlStateManager.color(f, f1, f2);
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        GlStateManager.depthMask(false);
        GlStateManager.enableFog();
        GlStateManager.color(f, f1, f2);

        if (mc.gameSettings.useVbo && this.getSkyVBO() != null) {
            this.getSkyVBO().bindBuffer();
            GlStateManager.glEnableClientState(32884);
            GlStateManager.glVertexPointer(3, 5126, 12, 0);
            this.getSkyVBO().drawArrays(7);
            this.getSkyVBO().unbindBuffer();
            GlStateManager.glDisableClientState(32884);
        } else {
            GlStateManager.callList(this.getSkyCallist());
        }

        GlStateManager.disableFog();
        GlStateManager.disableAlpha();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderHelper.disableStandardItemLighting();
        float[] afloat = mc.theWorld.provider.calcSunriseSunsetColors(mc.theWorld.getCelestialAngle(partialTicks), partialTicks);

        if (afloat != null) {
            GlStateManager.disableTexture2D();
            GlStateManager.shadeModel(7425);
            GlStateManager.pushMatrix();
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(MathHelper.sin(mc.theWorld.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            float f6 = afloat[0];
            float f7 = afloat[1];
            float f8 = afloat[2];
            if (mc.gameSettings.anaglyph) {
                float f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
                float f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
                float f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
                f6 = f9;
                f7 = f10;
                f8 = f11;
            }
            vertexbuffer.begin(6, DefaultVertexFormats.POSITION_COLOR);
            vertexbuffer.pos(0.0D, 100.0D, 0.0D).color(f6, f7, f8, afloat[3]).endVertex();
            int j = 16;

            for (int l = 0; l <= 16; ++l) {
                float f21 = (float) l * ((float) Math.PI * 2F) / 16.0F;
                float f12 = MathHelper.sin(f21);
                float f13 = MathHelper.cos(f21);
                vertexbuffer.pos((double) (f12 * 120.0F), (double) (f13 * 120.0F), (double) (-f13 * 40.0F * afloat[3])).color(afloat[0], afloat[1], afloat[2], 0.0F).endVertex();
            }
            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.shadeModel(7424);
        }

        GlStateManager.enableTexture2D();
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.pushMatrix();
        float f16 = 3.0F - mc.theWorld.getRainStrength(partialTicks);
        GlStateManager.color(1.0F, 1.0F, 1.0F, f16);
        GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(mc.theWorld.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
        float f17 = 30.0F;
        mc.renderEngine.bindTexture(SUN_TEXTURES);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos((double) (-f17), 100.0D, (double) (-f17)).tex(0.0D, 0.0D).endVertex();
        vertexbuffer.pos((double) f17, 100.0D, (double) (-f17)).tex(1.0D, 0.0D).endVertex();
        vertexbuffer.pos((double) f17, 100.0D, (double) f17).tex(1.0D, 1.0D).endVertex();
        vertexbuffer.pos((double) (-f17), 100.0D, (double) f17).tex(0.0D, 1.0D).endVertex();
        tessellator.draw();
        f17 = 30.0F;
        mc.renderEngine.bindTexture(MOON_PHASES_TEXTURES);
        int i = mc.theWorld.getMoonPhase();
        int k = i % 4;
        int i1 = i / 4 % 2;
        float f22 = (float) (k) / 4.0F;
        float f23 = (float) (i1) / 2.0F;
        float f24 = (float) (k + 1) / 4.0F;
        float f14 = (float) (i1 + 1) / 2.0F;
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos((double) (-f17), -100.0D, (double) f17).tex((double) f24, (double) f14).endVertex();
        vertexbuffer.pos((double) f17, -100.0D, (double) f17).tex((double) f22, (double) f14).endVertex();
        vertexbuffer.pos((double) f17, -100.0D, (double) (-f17)).tex((double) f22, (double) f23).endVertex();
        vertexbuffer.pos((double) (-f17), -100.0D, (double) (-f17)).tex((double) f24, (double) f23).endVertex();
        tessellator.draw();
        GlStateManager.disableTexture2D();
        float f15 = mc.theWorld.getStarBrightness(partialTicks) * f16;

        if (f15 > 0.0F) {
            GlStateManager.color(f15, f15, f15, f15);

            if (mc.gameSettings.useVbo && this.getStarVBO() != null) {
                this.getStarVBO().bindBuffer();
                GlStateManager.glEnableClientState(32884);
                GlStateManager.glVertexPointer(3, 5126, 12, 0);
                this.getStarVBO().drawArrays(7);
                this.getStarVBO().unbindBuffer();
                GlStateManager.glDisableClientState(32884);
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
        double d0 = mc.thePlayer.getPositionEyes(partialTicks).yCoord - mc.theWorld.getHorizon();
        if (d0 < 0.0D) {
            GlStateManager.pushMatrix();
            GlStateManager.translate(0.0F, 12.0F, 0.0F);

            if (mc.gameSettings.useVbo && this.getSky2VBO() != null) {
                this.getSky2VBO().bindBuffer();
                GlStateManager.glEnableClientState(32884);
                GlStateManager.glVertexPointer(3, 5126, 12, 0);
                this.getSky2VBO().drawArrays(7);
                this.getSky2VBO().unbindBuffer();
                GlStateManager.glDisableClientState(32884);
            } else {
                GlStateManager.callList(this.getSky2Callist());
            }

            GlStateManager.popMatrix();
            float f18 = 1.0F;
            float f19 = -((float) (d0 + 65.0D));
            float f20 = -1.0F;
            vertexbuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
            vertexbuffer.pos(-1.0D, (double) f19, 1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(1.0D, (double) f19, 1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(1.0D, (double) f19, -1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(-1.0D, (double) f19, -1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(1.0D, (double) f19, 1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(1.0D, (double) f19, -1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(-1.0D, (double) f19, -1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(-1.0D, (double) f19, 1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(-1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(-1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(1.0D, -1.0D, 1.0D).color(0, 0, 0, 255).endVertex();
            vertexbuffer.pos(1.0D, -1.0D, -1.0D).color(0, 0, 0, 255).endVertex();
            tessellator.draw();
        }

        if (mc.theWorld.provider.isSkyColored()) {
            GlStateManager.color(f * 0.2F + 0.04F, f1 * 0.2F + 0.04F, f2 * 0.6F + 0.1F);
        } else {
            GlStateManager.color(f, f1, f2);
        }

        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0F, -((float) (d0 - 16.0D)), 0.0F);
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

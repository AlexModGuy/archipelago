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
        GL11.glPushMatrix();
        renderCloudSide(partialTicks, world, mc);
        GL11.glPopMatrix();

    }

    public void renderCloudSide(float partialTicks, WorldClient world, Minecraft mc) {
        GL11.glDisable(GL11.GL_CULL_FACE);
        Entity entity = mc.getRenderViewEntity();
        float f = (float) (entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double) partialTicks);
        int i = 32;
        int j = 8;
        Tessellator tessellator = Tessellator.getInstance();
        VertexBuffer vertexbuffer = tessellator.getBuffer();
        mc.renderEngine.bindTexture(CLOUDS_TEXTURES);
        GL11.glEnable(GL11.GL_BLEND);
        GlStateManager.depthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Vec3d vec3d = mc.theWorld.getCloudColour(partialTicks);
        float f1 = (float) vec3d.xCoord;
        float f2 = (float) vec3d.yCoord;
        float f3 = (float) vec3d.zCoord;

        if (mc.gameSettings.anaglyph) {
            float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
            float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
            float f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
            f1 = f4;
            f2 = f5;
            f3 = f6;
        }

        float f10 = 64.8828125E-4F;
        double d2 = (double) ((float) getCloudTickCounter() + partialTicks) * 12;
        double d0 = entity.prevPosX + (entity.posX - entity.prevPosX) * (double) partialTicks + d2 * 0.029999999329447746D;
        double d1 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double) partialTicks;
        int k = MathHelper.floor_double(d0 / 2048.0D);
        int l = MathHelper.floor_double(d1 / 2048.0D);
        d0 = d0 - (double) (k * 2048);
        d1 = d1 - (double) (l * 2048);
        float f7 = mc.theWorld.provider.getCloudHeight() - f + 0.33F;
        float f8 = (float) (d0 * 64.8828125E-4D);
        float f9 = (float) (d1 * 64.8828125E-4D);
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);

        for (int i1 = -256; i1 < 256; i1 += 32) {
            for (int j1 = -256; j1 < 256; j1 += 32) {
                vertexbuffer.pos((double) (i1), (double) -f7, (double) (j1 + 32)).tex((double) ((float) (i1) * 64.8828125E-4F + f8), (double) ((float) (j1 + 32) * 64.8828125E-4F + f9)).color(f1, f2, f3, 0.8F).endVertex();
                vertexbuffer.pos((double) (i1 + 32), (double) -f7, (double) (j1 + 32)).tex((double) ((float) (i1 + 32) * 64.8828125E-4F + f8), (double) ((float) (j1 + 32) * 64.8828125E-4F + f9)).color(f1, f2, f3, 0.8F).endVertex();
                vertexbuffer.pos((double) (i1 + 32), (double) -f7, (double) (j1)).tex((double) ((float) (i1 + 32) * 64.8828125E-4F + f8), (double) ((float) (j1) * 64.8828125E-4F + f9)).color(f1, f2, f3, 0.8F).endVertex();
                vertexbuffer.pos((double) (i1), (double) -f7, (double) (j1)).tex((double) ((float) (i1) * 64.8828125E-4F + f8), (double) ((float) (j1) * 64.8828125E-4F + f9)).color(f1, f2, f3, 0.8F).endVertex();
            }
        }
        GL11.glScalef(1, -1, 1);
        tessellator.draw();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.depthMask(true);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_CULL_FACE);
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

package com.github.alexthe666.archipelago.event.client;

import com.github.alexthe666.archipelago.block.BlockGrowingSeaweed;
import com.github.alexthe666.archipelago.block.BlockShortCoral;
import com.github.alexthe666.archipelago.core.ModFluids;
import com.github.alexthe666.archipelago.properties.ArchipelagoEntityProperties;
import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.lwjgl.opengl.GL11;

public class ClientEvents {

    @SubscribeEvent
    public void onEntityRenderPre(RenderLivingEvent.Pre<EntityLivingBase> e) {
        if (e.getEntity() instanceof EntityPlayer) {
            int teleportTime = EntityPropertiesHandler.INSTANCE.getProperties((EntityPlayer) e.getEntity(), ArchipelagoEntityProperties.class).teleportTime;
            float scaledTeleportTime = (float) (teleportTime / 3 * 0.01);
            GL11.glPushMatrix();
            GL11.glColor4f(1, 1, 1, 1 - scaledTeleportTime);
        }
    }

    @SubscribeEvent
    public void onEntityRenderPost(RenderLivingEvent.Post<EntityLivingBase> e) {
        if (e.getEntity() instanceof EntityPlayer) {
            GL11.glPopMatrix();
        }
    }

    @SubscribeEvent
    public void onBlockOverlay(RenderBlockOverlayEvent e) {
        Block block = e.getPlayer().worldObj.getBlockState(e.getBlockPos()).getBlock();
        if (block instanceof BlockShortCoral || block instanceof BlockGrowingSeaweed || block == ModFluids.tropical_water) {
            e.setCanceled(true);
            Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation("archipelago:textures/underwater.png"));
            Tessellator tessellator = Tessellator.getInstance();
            VertexBuffer vertexbuffer = tessellator.getBuffer();
            float f = Minecraft.getMinecraft().thePlayer.getBrightness(e.getRenderPartialTicks());
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            GlStateManager.pushMatrix();
            float f1 = 4.0F;
            float f2 = -1.0F;
            float f3 = 1.0F;
            float f4 = -1.0F;
            float f5 = 1.0F;
            float f6 = -0.5F;
            float f7 = -Minecraft.getMinecraft().thePlayer.rotationYaw / 64.0F;
            float f8 = Minecraft.getMinecraft().thePlayer.rotationPitch / 64.0F;
            vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
            vertexbuffer.pos(-1.0D, -1.0D, -0.5D).tex((double) (4.0F + f7), (double) (4.0F + f8)).endVertex();
            vertexbuffer.pos(1.0D, -1.0D, -0.5D).tex((double) (0.0F + f7), (double) (4.0F + f8)).endVertex();
            vertexbuffer.pos(1.0D, 1.0D, -0.5D).tex((double) (0.0F + f7), (double) (0.0F + f8)).endVertex();
            vertexbuffer.pos(-1.0D, 1.0D, -0.5D).tex((double) (4.0F + f7), (double) (0.0F + f8)).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.disableBlend();
        } else {
            e.setCanceled(false);
        }
    }

    @SubscribeEvent
    public void onFogColor(EntityViewRenderEvent.FogColors e) {
        if (e.getState().getBlock() == ModFluids.tropical_water || e.getState().getBlock() instanceof BlockShortCoral || e.getState().getBlock() instanceof BlockGrowingSeaweed) {
            e.setRed(0F);
            e.setBlue(0.7F);
            e.setGreen(0.8F);
        }
        GlStateManager.setFogDensity(0.01F);
    }
}

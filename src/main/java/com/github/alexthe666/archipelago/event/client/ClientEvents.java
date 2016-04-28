package com.github.alexthe666.archipelago.event.client;

import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import org.lwjgl.opengl.GL11;

import com.github.alexthe666.archipelago.properties.ArchipelagoEntityProperties;

public class ClientEvents {

	@SubscribeEvent
	public void onEntityRenderPre(RenderLivingEvent.Pre<EntityLivingBase> e)
	{
		if(e.getEntity() instanceof EntityPlayer){
			int teleportTime = EntityPropertiesHandler.INSTANCE.getProperties((EntityPlayer)e.getEntity(), ArchipelagoEntityProperties.class).teleportTime;
			float scaledTeleportTime = (float)(teleportTime / 3 * 0.01);
			GL11.glPushMatrix();
			GL11.glColor4f(1, 1, 1, 1 - scaledTeleportTime);
		}
	}

	@SubscribeEvent
	public void onEntityRenderPost(RenderLivingEvent.Post<EntityLivingBase> e)
	{
		if(e.getEntity() instanceof EntityPlayer){
			GL11.glPopMatrix();
		}
	}
}

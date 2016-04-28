package com.github.alexthe666.archipelago.event.server;

import java.util.Random;

import net.ilexiconn.llibrary.server.entity.EntityPropertiesHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.enums.EnumParticle;
import com.github.alexthe666.archipelago.properties.ArchipelagoEntityProperties;

public class ServerEvents {

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if(event.getEntity() instanceof EntityPlayer){
			ArchipelagoEntityProperties properties = EntityPropertiesHandler.INSTANCE.getProperties(((EntityPlayer)event.getEntity()), ArchipelagoEntityProperties.class);
			if(properties.teleportTime > 0 && properties.teleportTime <= 300){
				properties.teleportTime++;
				Random rand = new Random();
				Archipelago.proxy.spawnParticle(EnumParticle.TELEPORT, event.getEntity().worldObj, (float)(event.getEntity().posX + (rand.nextDouble() - 0.5D) * (double)event.getEntity().width), (float)(event.getEntity().posY + rand.nextDouble() * (double)event.getEntity().height), (float)(event.getEntity().posZ + (rand.nextDouble() - 0.5D) * (double)event.getEntity().width), 0, 0, 0);
				event.getEntity().worldObj.spawnParticle(EnumParticleTypes.END_ROD, (float)(event.getEntity().posX + (rand.nextDouble() - 0.5D) * (double)event.getEntity().width), (float)(event.getEntity().posY + rand.nextDouble() * (double)event.getEntity().height), (float)(event.getEntity().posZ + (rand.nextDouble() - 0.5D) * (double)event.getEntity().width), 0, 0, 0, new int[0]);
			}
			if(properties.teleportTime == 300){
				properties.teleportTime = 0;
			}
		}
	}
}

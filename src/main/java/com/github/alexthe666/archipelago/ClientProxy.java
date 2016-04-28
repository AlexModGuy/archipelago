package com.github.alexthe666.archipelago;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

import com.github.alexthe666.archipelago.client.particle.TeleportFX;
import com.github.alexthe666.archipelago.enums.EnumParticle;
import com.github.alexthe666.archipelago.event.client.ClientEvents;

public class ClientProxy extends CommonProxy {

	public void render(){
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}

	public void addItemRender(Item item, String name){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation("archipelago:" + name, "inventory"));
	}

	public void addItemRenderWithMeta(Item item, String name, int meta){
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, meta, new ModelResourceLocation("archipelago:" + name, "inventory"));
	}

	public void spawnParticle(EnumParticle particle, World world, float x, float y, float z, double motionX, double motionY, double motionZ){
		switch(particle){
		case TELEPORT:
			Minecraft.getMinecraft().effectRenderer.addEffect(new TeleportFX(world, x, y, z, motionX, motionY, motionZ));
		default:
			break;
		}
	}

}

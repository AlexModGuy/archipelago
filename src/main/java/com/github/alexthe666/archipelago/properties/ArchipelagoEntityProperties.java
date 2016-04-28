package com.github.alexthe666.archipelago.properties;

import net.ilexiconn.llibrary.server.entity.EntityProperties;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class ArchipelagoEntityProperties extends EntityProperties{

	public int teleportTime;

	@Override
	public void saveNBTData(NBTTagCompound compound) {
		compound.setInteger("TeleportTime", teleportTime);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		this.teleportTime = compound.getInteger("TeleportTime");
	}

	@Override
	public void init() {
		teleportTime = 300;
	}

	@Override
	public String getID() {
		return "Archipelago";
	}

	@Override
	public Class getEntityClass() {
		return EntityPlayer.class;
	}

}

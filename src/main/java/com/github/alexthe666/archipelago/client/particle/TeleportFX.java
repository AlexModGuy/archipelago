package com.github.alexthe666.archipelago.client.particle;

import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.world.World;

public class TeleportFX extends EntitySmokeFX{

	public TeleportFX(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, 2);
		float sub = (float)(Math.random() * 0.30000001192092896D);
		this.particleRed = 0.95F - sub;
		this.particleGreen = 0.8439F - sub;
		this.particleBlue = 0.3135F - sub;
	}

}

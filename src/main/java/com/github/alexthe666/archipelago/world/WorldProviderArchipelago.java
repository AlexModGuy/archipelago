package com.github.alexthe666.archipelago.world;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.core.ModConfig;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;

public class WorldProviderArchipelago extends WorldProvider {

	@Override
	public void createBiomeProvider() {
		this.biomeProvider = new WorldChunkManagerArchipelago(worldObj.getSeed(), worldObj.getWorldType());
	}

	@Override
	public String getWelcomeMessage() {
		return "Entering the Archipelago";
	}

	@Override
	public IChunkGenerator createChunkGenerator() {
		return new ChunkGeneratorArchipelago(worldObj, worldObj.getSeed());
	}

	@Override
	public boolean canRespawnHere() {
		return true;
	}

	@Override
	public int getRespawnDimension(EntityPlayerMP player) {
		return ModConfig.ARCHIPELAGO_DIMENSION_ID;
	}

	@Override
	public double getMovementFactor() {
		return 1.0;
	}

	@Override
	public DimensionType getDimensionType() {
		return Archipelago.dimType;
	}

}

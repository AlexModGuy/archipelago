package com.github.alexthe666.archipelago.world;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.IChunkGenerator;

import com.github.alexthe666.archipelago.Archipelago;
import com.github.alexthe666.archipelago.core.ModConfig;

public class WorldProviderArchipelago extends WorldProvider{

	public void registerWorldChunkManager()
	{
		this.worldChunkMgr = new WorldChunkManagerArchipelago(worldObj.getSeed(), worldObj.getWorldType());
	}
	
	public String getWelcomeMessage()
	{
		return "Entering the Archipelago";
	}
	
	public IChunkGenerator createChunkGenerator()
	{
		return new ChunkGeneratorArchipelago(worldObj, worldObj.getSeed());
	}
	
	public boolean canRespawnHere()
	{
		return true;
	}

	public int getRespawnDimension(EntityPlayerMP player)
	{
		return ModConfig.ARCHIPELAGO_DIMENSION_ID;
	}
	
	public double getMovementFactor()
	{
		return 1.0;
	}

	@Override
	public DimensionType getDimensionType() {
		return Archipelago.dimType;
	}

}

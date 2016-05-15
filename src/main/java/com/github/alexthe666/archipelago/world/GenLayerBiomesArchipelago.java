package com.github.alexthe666.archipelago.world;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import com.github.alexthe666.archipelago.core.ModWorld;

public class GenLayerBiomesArchipelago extends GenLayer {

	public static BiomeGenBase[] islandBiomes = {ModWorld.tropicGrasslands, ModWorld.tropicShrublands, ModWorld.tropicJungle, ModWorld.dryPeaks, ModWorld.dryScrubland};
	public static BiomeGenBase[] oceanBiomes = {ModWorld.tropicOcean, ModWorld.tropicShallows, ModWorld.tropicReef};
	private boolean isIsland;
	public GenLayerBiomesArchipelago(long seed) {
		super(seed);
	}


	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width*depth);
		for (int dz = 0; dz < depth; dz++){
			for (int dx = 0; dx < width; dx++){
				this.initChunkSeed(dx + x, dz + z);
				dest[(dx + dz * width)] = BiomeGenBase.getIdForBiome(getBiome());
			}
		}

		return dest;
	}

	private BiomeGenBase getBiome(){
		int chanceOfIsland = nextInt(15);
		if(chanceOfIsland == 0){
			return islandBiomes[nextInt(islandBiomes.length)];
		}else{
			return oceanBiomes[nextInt(oceanBiomes.length)];

		}
	}
}


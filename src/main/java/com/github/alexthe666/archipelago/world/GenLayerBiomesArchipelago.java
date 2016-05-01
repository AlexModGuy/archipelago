package com.github.alexthe666.archipelago.world;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import com.github.alexthe666.archipelago.core.ModWorld;

public class GenLayerBiomesArchipelago extends GenLayer {

	protected BiomeGenBase[] allowedBiomes = {ModWorld.tropicOcean, ModWorld.tropicShallows, ModWorld.tropicReef};

	public GenLayerBiomesArchipelago(long seed, GenLayer genlayer) {
		super(seed);
		this.parent = genlayer;
	}

	public GenLayerBiomesArchipelago(long seed) {
		super(seed);
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width * depth);

		for (int dz=0; dz < depth; dz++)
		{
			for (int dx=0; dx < width; dx++)
			{
				if(this.nextInt(5) == 0){
					this.initChunkSeed(dx+x, dz+z);
					dest[(dx + dz * width)] = BiomeGenBase.getIdForBiome(this.allowedBiomes[nextInt(this.allowedBiomes.length)]);
				}
			}
		}
		return dest;
	}
}



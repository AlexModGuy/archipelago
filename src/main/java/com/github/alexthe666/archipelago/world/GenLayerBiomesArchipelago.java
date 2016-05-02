package com.github.alexthe666.archipelago.world;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import com.github.alexthe666.archipelago.core.ModWorld;

public class GenLayerBiomesArchipelago extends GenLayer {

	protected BiomeGenBase[] allowedBiomes = {ModWorld.tropicOcean, ModWorld.tropicShallows, ModWorld.tropicReef};
	protected BiomeGenBase[] allowedLandBiomes = {ModWorld.tropicGrasslands, ModWorld.tropicShrublands, ModWorld.tropicReef};
	private boolean isIsland;
	public GenLayerBiomesArchipelago(long seed, GenLayer genlayer, boolean isIsland) {
		super(seed);
		this.parent = genlayer;
		this.isIsland = isIsland;
	}

	public GenLayerBiomesArchipelago(long seed, boolean isIsland) {
		super(seed);
		this.isIsland = isIsland;
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width * depth);
		for (int dz=0; dz < depth; dz++)
		{
			for (int dx=0; dx < width; dx++)
			{
				this.initChunkSeed(dx+x, dz+z);
				dest[(dx + dz * width)] = BiomeGenBase.getIdForBiome(isIsland ? this.allowedLandBiomes[nextInt(this.allowedLandBiomes.length)] : this.allowedBiomes[nextInt(this.allowedBiomes.length)]);
			}

		}
		return dest;
	}
}

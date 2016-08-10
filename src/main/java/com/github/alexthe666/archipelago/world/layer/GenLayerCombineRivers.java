package com.github.alexthe666.archipelago.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerCombineRivers extends GenLayer {
    private final GenLayer biomes;
    private final GenLayer rivers;

    public GenLayerCombineRivers(long seed, GenLayer biomes, GenLayer rivers) {
        super(seed);
        this.biomes = biomes;
        this.rivers = rivers;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] biomes = this.biomes.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] rivers = this.rivers.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] combined = IntCache.getIntCache(areaWidth * areaHeight);
        for (int index = 0; index < areaWidth * areaHeight; ++index) {
            int biome = biomes[index];
            if (!isBiomeOceanic(biome)) {
                int river = rivers[index];
                if (river != -1) {
                    combined[index] = river & 0xFF;
                } else {
                    combined[index] = biome;
                }
            } else {
                combined[index] = biome;
            }
        }
        return combined;
    }
}
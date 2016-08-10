package com.github.alexthe666.archipelago.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerSeedLand extends GenLayer {
    public GenLayerSeedLand(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaZ, int areaWidth, int areaHeight) {
        int[] biomes = IntCache.getIntCache(areaWidth * areaHeight);
        for (int deltaZ = 0; deltaZ < areaHeight; deltaZ++) {
            for (int deltaX = 0; deltaX < areaWidth; deltaX++) {
                this.initChunkSeed(deltaX + areaX, deltaZ + areaZ);
                biomes[(deltaX + deltaZ * areaWidth)] = this.nextInt(4) == 0 ? 1 : 0;
            }
        }
        return biomes;
    }
}

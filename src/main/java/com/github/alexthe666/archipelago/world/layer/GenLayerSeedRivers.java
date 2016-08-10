package com.github.alexthe666.archipelago.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerSeedRivers extends GenLayer {
    public GenLayerSeedRivers(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] riverSeed = IntCache.getIntCache(areaWidth * areaHeight);
        for (int deltaX = 0; deltaX < areaWidth; deltaX++) {
            for (int deltaZ = 0; deltaZ < areaHeight; deltaZ++) {
                this.initChunkSeed((long) deltaX + areaX, (long) deltaZ + areaY);
                riverSeed[deltaX + deltaZ * areaWidth] = this.nextInt(5);
            }
        }
        return riverSeed;
    }
}

package com.github.alexthe666.archipelago.world.layer;

import com.github.alexthe666.archipelago.core.ModWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerLakes extends GenLayer {
    private static final int LAKES = Biome.getIdForBiome(ModWorld.tropicLakes);

    public GenLayerLakes(long seed, GenLayer parent) {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] parentBiomes = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] newBiomes = IntCache.getIntCache(areaWidth * areaHeight);
        for (int deltaZ = 0; deltaZ < areaHeight; ++deltaZ) {
            for (int deltaX = 0; deltaX < areaWidth; ++deltaX) {
                this.initChunkSeed((long) (deltaX + areaX), (long) (deltaZ + areaY));
                int index = deltaX + deltaZ * areaWidth;
                int parentBiome = parentBiomes[index];
                if (!isBiomeOceanic(parentBiome) && this.nextInt(5) == 0) {
                    newBiomes[index] = LAKES;
                } else {
                    newBiomes[index] = parentBiome;
                }
            }
        }
        return newBiomes;
    }
}
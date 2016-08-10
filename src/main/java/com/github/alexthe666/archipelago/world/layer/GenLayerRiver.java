package com.github.alexthe666.archipelago.world.layer;

import com.github.alexthe666.archipelago.core.ModWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerRiver extends GenLayer {
    private static final int RIVER = Biome.getIdForBiome(ModWorld.tropicRiver);

    public GenLayerRiver(long seed, GenLayer parent) {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int x, int y, int width, int length) {
        int newWidth = width + 2;
        int newHeight = length + 2;
        int[] parentSeed = this.parent.getInts(x - 1, y - 1, newWidth, newHeight);
        int[] biomes = IntCache.getIntCache(width * length);
        for (int deltaX = 0; deltaX < width; deltaX++) {
            for (int deltaZ = 0; deltaZ < length; deltaZ++) {
                int parent = parentSeed[deltaX + 1 + (deltaZ + 1) * newWidth];
                if (parent != parentSeed[deltaX + (deltaZ + 1) * newWidth] || parent != parentSeed[deltaX + 2 + (deltaZ + 1) * newWidth] || parent != parentSeed[deltaX + 1 + deltaZ * newWidth] || parent != parentSeed[deltaX + 1 + (deltaZ + 2) * newWidth]) {
                    biomes[deltaX + deltaZ * width] = RIVER;
                } else {
                    biomes[deltaX + deltaZ * width] = -1;
                }
            }
        }
        return biomes;
    }
}
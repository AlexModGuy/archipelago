package com.github.alexthe666.archipelago.world;

import com.github.alexthe666.archipelago.core.ModWorld;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerShoreArchipelago extends GenLayer {
    public GenLayerShoreArchipelago(long seed, GenLayer parent) {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] parentBiomes = this.parent.getInts(areaX - 1, areaY - 1, areaWidth + 2, areaHeight + 2);
        int[] newBiomes = IntCache.getIntCache(areaWidth * areaHeight);
        for (int deltaZ = 0; deltaZ < areaHeight; ++deltaZ) {
            for (int deltaX = 0; deltaX < areaWidth; ++deltaX) {
                this.initChunkSeed((long) (deltaX + areaX), (long) (deltaZ + areaY));
                int biomeIndex = parentBiomes[deltaX + 1 + (deltaZ + 1) * (areaWidth + 2)];
                this.replaceIfNeighbourOcean(parentBiomes, newBiomes, deltaX, deltaZ, areaWidth, biomeIndex, Biome.getIdForBiome(biomeIndex != Biome.getIdForBiome(ModWorld.volcano) && biomeIndex != Biome.getIdForBiome(ModWorld.ashField) ? ModWorld.tropicBeach : ModWorld.blackSandBeach));
            }
        }
        return newBiomes;
    }

    private void replaceIfNeighbourOcean(int[] parentBiomes, int[] newBiomes, int deltaX, int deltaZ, int areaWidth, int parentBiome, int newBiome) {
        if (isBiomeOceanic(parentBiome)) {
            newBiomes[deltaX + deltaZ * areaWidth] = parentBiome;
        } else {
            int neighbour1 = parentBiomes[deltaX + 1 + (deltaZ + 1 - 1) * (areaWidth + 2)];
            int neighbour2 = parentBiomes[deltaX + 1 + 1 + (deltaZ + 1) * (areaWidth + 2)];
            int neighbour3 = parentBiomes[deltaX + 1 - 1 + (deltaZ + 1) * (areaWidth + 2)];
            int neighbour4 = parentBiomes[deltaX + 1 + (deltaZ + 1 + 1) * (areaWidth + 2)];
            if (!isBiomeOceanic(neighbour1) && !isBiomeOceanic(neighbour2) && !isBiomeOceanic(neighbour3) && !isBiomeOceanic(neighbour4)) {
                newBiomes[deltaX + deltaZ * areaWidth] = parentBiome;
            } else {
                newBiomes[deltaX + deltaZ * areaWidth] = newBiome;
            }
        }
    }
}
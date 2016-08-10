package com.github.alexthe666.archipelago.world.layer;

import com.github.alexthe666.archipelago.core.ModWorld;
import com.github.alexthe666.archipelago.world.BiomeGenTropical;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomes extends GenLayer {
    public static BiomeGenTropical[] islandBiomes = { ModWorld.tropicGrassland, ModWorld.tropicLakes, ModWorld.tropicShrubland, ModWorld.tropicJungle, ModWorld.dryPeaks, ModWorld.dryScrubland, ModWorld.mangroveSwamp };
    public static BiomeGenTropical[] oceanBiomes = { ModWorld.tropicOcean, ModWorld.tropicSeaGrassBed, ModWorld.tropicBlueHoles, ModWorld.tropicTrench, ModWorld.tropicKelpForest };

    public GenLayerBiomes(long seed, GenLayer parent) {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int[] biomes = IntCache.getIntCache(areaWidth * areaHeight);
        int[] parent = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
        for (int deltaX = 0; deltaX < areaWidth; deltaX++) {
            for (int deltaZ = 0; deltaZ < areaHeight; deltaZ++) {
                this.initChunkSeed(deltaX + areaX, deltaZ + areaY);
                int index = deltaX + deltaZ * areaWidth;
                biomes[index] = Biome.getIdForBiome(parent[index] == 0 ? this.getRandomBiomeWeighted(oceanBiomes) : this.getRandomBiomeWeighted(islandBiomes));
            }
        }
        return biomes;
    }

    private BiomeGenTropical getRandomBiomeWeighted(BiomeGenTropical[] biomes) {
        int chance = 0;
        for (BiomeGenTropical biome : biomes) {
            chance += biome.getGenerationChance();
        }
        int chosen = this.nextInt(chance);
        chance = 0;
        for (BiomeGenTropical biome : biomes) {
            chance += biome.getGenerationChance();
            if (chance >= chosen) {
                return biome;
            }
        }
        return biomes[0];
    }
}

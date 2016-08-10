package com.github.alexthe666.archipelago.world.layer;

import com.github.alexthe666.archipelago.core.ModWorld;
import com.github.alexthe666.archipelago.world.BiomeGenTropical;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerSmallIslands extends GenLayer {
    public static BiomeGenTropical[] smallIslandBiomes = { ModWorld.tropicGrassland, ModWorld.tropicShrubland, ModWorld.dryScrubland, ModWorld.volcano, ModWorld.ashField };

    public GenLayerSmallIslands(long seed, GenLayer parent) {
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
                int parentBiome = parentBiomes[deltaX + 1 + (deltaZ + 1) * (areaWidth + 2)];
                if (isBiomeOceanic(parentBiome) && this.nextInt(8) == 0) {
                    newBiomes[deltaX + deltaZ * areaWidth] = Biome.getIdForBiome(this.getRandomBiomeWeighted(smallIslandBiomes));
                } else {
                    newBiomes[deltaX + deltaZ * areaWidth] = parentBiome;
                }
            }
        }
        return newBiomes;
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

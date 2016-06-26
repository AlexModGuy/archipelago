package com.github.alexthe666.archipelago.world;

import com.github.alexthe666.archipelago.core.ModWorld;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomesArchipelago extends GenLayer {

    public static Biome[] islandBiomes = { ModWorld.tropicGrassland, ModWorld.tropicShrubland, ModWorld.tropicJungle, ModWorld.dryPeaks, ModWorld.dryScrubland };
    public static Biome[] oceanBiomes = { ModWorld.tropicOcean, ModWorld.tropicShallows, ModWorld.tropicReef, ModWorld.tropicSeaGrassBed, ModWorld.tropicBlueHoles, ModWorld.tropicTrench, ModWorld.tropicKelpForest };
    private boolean isIsland;

    public GenLayerBiomesArchipelago(long seed) {
        super(seed);
    }

    @Override
    public int[] getInts(int x, int z, int width, int depth) {
        int[] dest = IntCache.getIntCache(width * depth);
        for (int dz = 0; dz < depth; dz++) {
            for (int dx = 0; dx < width; dx++) {
                this.initChunkSeed(dx + x, dz + z);
                dest[(dx + dz * width)] = Biome.getIdForBiome(getBiome());
            }
        }

        return dest;
    }

    private Biome getBiome() {
        int chanceOfIsland = nextInt(15);
        if (chanceOfIsland == 0) {
            return islandBiomes[nextInt(islandBiomes.length)];
        } else {
            return oceanBiomes[nextInt(oceanBiomes.length)];

        }
    }
}

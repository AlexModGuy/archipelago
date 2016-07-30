package com.github.alexthe666.archipelago.world;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerArchipelago extends GenLayer {

    public GenLayerArchipelago(long seed) {
        super(seed);
    }

    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldType, String settings) {
        GenLayer biomes = new GenLayerBiomesArchipelago(1L);
        biomes = new GenLayerZoom(1000L, biomes);
        biomes = new GenLayerZoom(1001L, biomes);
        biomes = new GenLayerZoom(1002L, biomes);
        biomes = new GenLayerZoom(1003L, biomes);
//        biomes = new GenLayerZoom(1004L, biomes);
//        biomes = new GenLayerZoom(1005L, biomes);
        GenLayer voronoiZoom = new GenLayerVoronoiZoom(25L, biomes);
        biomes.initWorldGenSeed(seed);
        voronoiZoom.initWorldGenSeed(seed);
        return new GenLayer[] { biomes, voronoiZoom };
    }
}

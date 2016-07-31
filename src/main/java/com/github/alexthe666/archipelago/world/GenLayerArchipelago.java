package com.github.alexthe666.archipelago.world;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerArchipelago extends GenLayer {
    public GenLayerArchipelago(long seed) {
        super(seed);
    }

    public static GenLayer[] initializeAllBiomeGenerators(long seed) {
        GenLayer biomesIndex = new GenLayerBiomesArchipelago(1L);
        biomesIndex = new GenLayerFuzzyZoom(1000L, biomesIndex);
        biomesIndex = new GenLayerFuzzyZoom(1001L, biomesIndex);
        biomesIndex = new GenLayerFuzzyZoom(1002L, biomesIndex);
        biomesIndex = new GenLayerShoreArchipelago(50L, biomesIndex);
        biomesIndex = new GenLayerZoom(1003L, biomesIndex);
        biomesIndex = new GenLayerZoom(1004L, biomesIndex);
        GenLayer zoom = new GenLayerVoronoiZoom(45L, biomesIndex);
        biomesIndex.initWorldGenSeed(seed);
        zoom.initWorldGenSeed(seed);
        return new GenLayer[] { biomesIndex, zoom };
    }
}

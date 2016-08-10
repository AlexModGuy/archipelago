package com.github.alexthe666.archipelago.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerFuzzyZoom;
import net.minecraft.world.gen.layer.GenLayerSmooth;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerArchipelago extends GenLayer {
    public GenLayerArchipelago(long seed) {
        super(seed);
    }

    public static GenLayer[] initializeAllBiomeGenerators(long seed) {
        GenLayer biomesIndex = new GenLayerSeedLand(1L);
        biomesIndex = new GenLayerFuzzyZoom(1000L, biomesIndex);
        biomesIndex = new GenLayerFuzzyZoom(1001L, biomesIndex);
        biomesIndex = new GenLayerBiomes(10L, biomesIndex);
        biomesIndex = new GenLayerFuzzyZoom(2000L, biomesIndex);
        biomesIndex = new GenLayerSmallIslands(50L, biomesIndex);
        biomesIndex = new GenLayerFuzzyZoom(1002L, biomesIndex);
        biomesIndex = new GenLayerShallows(40L, biomesIndex);
        biomesIndex = new GenLayerZoom(1003L, biomesIndex);
        biomesIndex = new GenLayerReef(20L, biomesIndex);
        biomesIndex = new GenLayerZoom(1004L, biomesIndex);
        biomesIndex = new GenLayerShore(50L, biomesIndex);
        biomesIndex = new GenLayerZoom(1005L, biomesIndex);
        GenLayer rivers = new GenLayerSeedRivers(200L);
        rivers = GenLayerZoom.magnify(1006L, rivers, 4);
        rivers = new GenLayerRiver(50L, rivers);
        rivers = new GenLayerSmooth(2000L, rivers);
        rivers = GenLayerZoom.magnify(1000L, rivers, 1);
        biomesIndex = new GenLayerCombineRivers(10L, biomesIndex, rivers);
        GenLayer zoom = new GenLayerVoronoiZoom(45L, biomesIndex);
        biomesIndex.initWorldGenSeed(seed);
        zoom.initWorldGenSeed(seed);
        return new GenLayer[] { biomesIndex, zoom };
    }
}

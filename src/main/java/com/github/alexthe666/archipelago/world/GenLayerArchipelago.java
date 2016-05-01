package com.github.alexthe666.archipelago.world;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddIsland;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.GenLayerRemoveTooMuchOcean;
import net.minecraft.world.gen.layer.GenLayerShore;
import net.minecraft.world.gen.layer.GenLayerVoronoiZoom;
import net.minecraft.world.gen.layer.GenLayerZoom;

public abstract class GenLayerArchipelago extends GenLayer{

	public GenLayerArchipelago(long seed) {
		super(seed);
	}

    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType world, String s){
		GenLayer biomes = new GenLayerBiomesArchipelago(1L);
		biomes = new GenLayerZoom(1000L, biomes);
		biomes = new GenLayerZoom(1001L, biomes);
		biomes = new GenLayerZoom(1002L, biomes);
		biomes = new GenLayerZoom(1003L, biomes);
		biomes = new GenLayerZoom(1004L, biomes);
		biomes = new GenLayerZoom(1005L, biomes);
		GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);
		biomes.initWorldGenSeed(seed);
		GenLayerAddIsland genlayeraddisland1 = new GenLayerAddIsland(3L, genlayervoronoizoom);
		genlayeraddisland1 = new GenLayerAddIsland(50L, genlayeraddisland1);
		genlayeraddisland1 = new GenLayerAddIsland(70L, genlayeraddisland1);
		genlayeraddisland1 = new GenLayerAddIsland(100L, genlayeraddisland1);
		genlayeraddisland1 = new GenLayerAddIsland(120L, genlayeraddisland1);
		genlayeraddisland1 = new GenLayerAddIsland(140L, genlayeraddisland1);
		genlayeraddisland1 = new GenLayerAddIsland(160L, genlayeraddisland1);
		genlayeraddisland1 = new GenLayerAddIsland(180L, genlayeraddisland1);
		genlayervoronoizoom.initWorldGenSeed(seed);
		return new GenLayer[] {biomes, genlayervoronoizoom};
	}
}




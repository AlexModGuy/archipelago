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
		GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);
		biomes.initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);
		return new GenLayer[] {biomes, genlayervoronoizoom};
	}
}




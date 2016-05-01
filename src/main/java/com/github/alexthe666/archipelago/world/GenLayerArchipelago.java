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

//    public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType world, String s){
//		GenLayer biomes = new GenLayerBiomesArchipelago(1L);
//		biomes = new GenLayerZoom(1000L, biomes);
//		biomes = new GenLayerZoom(1001L, biomes);
//		biomes = new GenLayerZoom(1002L, biomes);
//		biomes = new GenLayerZoom(1003L, biomes);
//		biomes = new GenLayerZoom(1004L, biomes);
//		biomes = new GenLayerZoom(1005L, biomes);
//		GenLayer genlayervoronoizoom = new GenLayerVoronoiZoom(10L, biomes);
//		GenLayer biomes1 = new GenLayerBiomesArchipelagoIslands(1L);
//		biomes1 = new GenLayerZoom(250L, biomes1);
//		biomes1 = new GenLayerZoom(251L, biomes1);
//		biomes1 = new GenLayerZoom(252L, biomes1);
//		biomes1 = new GenLayerZoom(253L, biomes1);
//		biomes1 = new GenLayerZoom(254L, biomes1);
//		biomes1 = new GenLayerZoom(255L, biomes1);
//		GenLayer genlayervoronoizoom1 = new GenLayerVoronoiZoom(10L, biomes1);
//		return new GenLayer[] {genlayervoronoizoom, genlayervoronoizoom1};
//	}
}




package com.github.alexthe666.archipelago.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class PlantEntry {
	
	private List<BiomeGenBase> biomesToSpawn = new ArrayList<BiomeGenBase>();
	public Block block;
	public int chancePerChunk;
	public boolean doublePlant;
	
	public PlantEntry(Block block, int chancePerChunk, boolean doublePlant){
		this.block = block;
		this.chancePerChunk = chancePerChunk;
		this.doublePlant = doublePlant;
	}
	
	public PlantEntry addBiome(BiomeGenBase biome){
		this.biomesToSpawn.add(biome);
		return this;
	}
	
	public boolean canSpawnIn(BiomeGenBase biome){
		return this.biomesToSpawn.contains(biome);
	}
}

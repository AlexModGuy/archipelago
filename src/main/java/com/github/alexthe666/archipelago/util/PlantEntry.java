package com.github.alexthe666.archipelago.util;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;

public class PlantEntry {

    private List<Integer> biomesToSpawn = new ArrayList<Integer>();
    public Block block;
    public int chancePerChunk;
    public boolean doublePlant;

    public PlantEntry(Block block, int chancePerChunk, boolean doublePlant) {
        this.block = block;
        this.chancePerChunk = chancePerChunk;
        this.doublePlant = doublePlant;
    }

    public void addBiome(int biome) {
        this.biomesToSpawn.add(biome);
    }

    public boolean canSpawnIn(Biome biome) {
        return biomesToSpawn.contains(Biome.getIdForBiome(biome));
    }
}

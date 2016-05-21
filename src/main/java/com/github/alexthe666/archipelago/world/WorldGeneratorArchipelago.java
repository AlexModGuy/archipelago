package com.github.alexthe666.archipelago.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.alexthe666.archipelago.core.ModBlocks;
import com.github.alexthe666.archipelago.core.ModConfig;
import com.github.alexthe666.archipelago.util.PlantEntry;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase.FlowerEntry;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.fml.common.IWorldGenerator;

public class WorldGeneratorArchipelago implements IWorldGenerator{

	public static List<PlantEntry> flowersEntries = new ArrayList<PlantEntry>();

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {

		if(world.provider.getDimension() == ModConfig.ARCHIPELAGO_DIMENSION_ID){
			for(int k = 0; k < 4; k++){
				int x = (chunkX * 16) + random.nextInt(16);
				int z = (chunkZ * 16) + random.nextInt(16);
				new WorldGenArchipelagoPlant(flowersEntries.get(random.nextInt(flowersEntries.size()))).generate(world, random, world.getHeight(new BlockPos(x, 0, z)));
			}
		}
	}

}

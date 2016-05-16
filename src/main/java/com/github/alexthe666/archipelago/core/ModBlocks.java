package com.github.alexthe666.archipelago.core;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import com.github.alexthe666.archipelago.block.BlockShortCoral;
import com.github.alexthe666.archipelago.block.BlockShortPlant;
import com.github.alexthe666.archipelago.block.BlockTallPlant;

public class ModBlocks {

	public static Block anthurium_andraeanum;
	public static Block bottle_palm;
	public static Block ceratozamia_mexicana;
	public static Block jambu;
	public static Block canary_island_foxglove;
	public static Block la_palma_sow_thistle;
	public static Block parrot_lily;
	public static Block dendrophyllia_cribosa;

	public static void init(){
		anthurium_andraeanum = new BlockShortPlant("anthurium_andraeanum", 5, new BiomeGenBase[]{ModWorld.tropicJungle, ModWorld.tropicShrublands});
		bottle_palm = new BlockTallPlant("bottle_palm", 5, new BiomeGenBase[]{ModWorld.tropicJungle});
		ceratozamia_mexicana = new BlockTallPlant("ceratozamia_mexicana", 5, new BiomeGenBase[]{ModWorld.tropicJungle, ModWorld.tropicShrublands});
		jambu = new BlockShortPlant("jambu", 5, new BiomeGenBase[]{ModWorld.tropicJungle});
		canary_island_foxglove = new BlockTallPlant("canary_island_foxglove", 5, new BiomeGenBase[]{ModWorld.tropicShrublands});
		la_palma_sow_thistle =new BlockTallPlant("la_palma_sow_thistle", 5, new BiomeGenBase[]{ModWorld.dryScrubland, ModWorld.tropicShrublands});
		parrot_lily = new BlockShortPlant("parrot_lily", 5, new BiomeGenBase[]{ModWorld.tropicGrasslands, ModWorld.tropicJungle, ModWorld.tropicShrublands});
		dendrophyllia_cribosa = new BlockShortCoral("dendrophyllia_cribosa", 5, new BiomeGenBase[]{ModWorld.tropicOcean, ModWorld.tropicBlueHoles, ModWorld.tropicKelpForest, ModWorld.tropicTrench});
	}
}

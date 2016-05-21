package com.github.alexthe666.archipelago.core;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

import com.github.alexthe666.archipelago.block.BlockGlowingCoral;
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
	public static Block ivory_bush_coral;
	public static Block maasella_edwardsii;
	public static Block yellow_gorgonian;
	public static Block yellow_tube_sponge;
	public static Block isidella;
	public static Block bipinnate_sea_plume;
	public static Block asian_green_mussels;
	public static Block blue_mussels;
	public static Block nori;
	public static Block cerianthus_lloydi;
	public static Block common_piddock_colony;
	public static Block bladderwrack;
	public static Block caulerpa_prolifera;
	public static Block cuvie;
	public static Block green_fleece;
	public static Block spiral_wrack;
	public static Block tooth_wrack;
	public static Block caulerpa_taxifolia;

	public static void init(){
		anthurium_andraeanum = new BlockShortPlant("anthurium_andraeanum", 5, new BiomeGenBase[]{ModWorld.tropicJungle, ModWorld.tropicShrublands});
		bottle_palm = new BlockTallPlant("bottle_palm", 5, new BiomeGenBase[]{ModWorld.tropicJungle});
		ceratozamia_mexicana = new BlockTallPlant("ceratozamia_mexicana", 5, new BiomeGenBase[]{ModWorld.tropicJungle, ModWorld.tropicShrublands});
		jambu = new BlockShortPlant("jambu", 5, new BiomeGenBase[]{ModWorld.tropicJungle});
		canary_island_foxglove = new BlockTallPlant("canary_island_foxglove", 5, new BiomeGenBase[]{ModWorld.tropicShrublands});
		la_palma_sow_thistle =new BlockTallPlant("la_palma_sow_thistle", 5, new BiomeGenBase[]{ModWorld.dryScrubland, ModWorld.tropicShrublands});
		parrot_lily = new BlockShortPlant("parrot_lily", 5, new BiomeGenBase[]{ModWorld.tropicGrasslands, ModWorld.tropicJungle, ModWorld.tropicShrublands});
		
		dendrophyllia_cribosa = new BlockShortCoral("dendrophyllia_cribosa", 5, new BiomeGenBase[]{ModWorld.tropicOcean, ModWorld.tropicBlueHoles, ModWorld.tropicKelpForest, ModWorld.tropicTrench});
		ivory_bush_coral = new BlockShortCoral("ivory_bush_coral", 5, new BiomeGenBase[]{ModWorld.tropicOcean});
		maasella_edwardsii = new BlockShortCoral("maasella_edwardsii", 5, new BiomeGenBase[]{ModWorld.tropicOcean, ModWorld.tropicBlueHoles, ModWorld.tropicKelpForest});
		yellow_gorgonian = new BlockShortCoral("yellow_gorgonian", 5, new BiomeGenBase[]{ModWorld.tropicOcean, ModWorld.tropicKelpForest});
		yellow_tube_sponge = new BlockShortCoral("yellow_tube_sponge", 5, new BiomeGenBase[]{ModWorld.tropicReef});
		isidella = new BlockGlowingCoral("isidella", 5, new BiomeGenBase[]{ModWorld.tropicTrench});
		bipinnate_sea_plume = new BlockShortCoral("bipinnate_sea_plume", 5, new BiomeGenBase[]{ModWorld.tropicBlueHoles});
		asian_green_mussels = new BlockShortCoral("asian_green_mussels", 5, new BiomeGenBase[]{ModWorld.tropicOcean});
		blue_mussels = new BlockShortCoral("blue_mussels", 5, new BiomeGenBase[]{ModWorld.tropicOcean});
		nori = new BlockShortCoral("nori", 5, new BiomeGenBase[]{ModWorld.tropicOcean});
		cerianthus_lloydi = new BlockShortCoral("cerianthus_lloydi", 5, new BiomeGenBase[]{ModWorld.tropicOcean});
		common_piddock_colony = new BlockGlowingCoral("common_piddock_colony", 5, new BiomeGenBase[]{ModWorld.tropicOcean});
		bladderwrack = new BlockShortCoral("bladderwrack", 5, new BiomeGenBase[]{ModWorld.tropicOcean});
		caulerpa_prolifera = new BlockShortCoral("caulerpa_prolifera", 5, new BiomeGenBase[]{ModWorld.tropicOcean});
		cuvie = new BlockShortCoral("cuvie", 5, new BiomeGenBase[]{ModWorld.tropicOcean});
		green_fleece = new BlockShortCoral("green_fleece", 5, new BiomeGenBase[]{ModWorld.tropicOcean});
		spiral_wrack = new BlockShortCoral("spiral_wrack", 5, new BiomeGenBase[]{ModWorld.tropicOcean});
		tooth_wrack = new BlockShortCoral("tooth_wrack", 5, new BiomeGenBase[]{ModWorld.tropicOcean});
		caulerpa_taxifolia = new BlockShortCoral("caulerpa_taxifolia", 5, new BiomeGenBase[]{ModWorld.tropicOcean});

	}
}

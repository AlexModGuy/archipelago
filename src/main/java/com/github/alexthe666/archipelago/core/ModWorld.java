package com.github.alexthe666.archipelago.core;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;

import com.github.alexthe666.archipelago.enums.EnumBiomeSediment;
import com.github.alexthe666.archipelago.enums.EnumGrassColor;
import com.github.alexthe666.archipelago.world.BiomeGenTropical;

public class ModWorld {
	
	public static BiomeGenBase tropicOcean;
	public static BiomeGenBase tropicShallows;
	public static BiomeGenBase tropicReef;
	public static BiomeGenBase tropicSeaGrassBed;
	public static BiomeGenBase tropicBlueHoles;
	public static BiomeGenBase tropicTrench;
	public static BiomeGenBase tropicKelpForest;
	public static BiomeGenBase tropicGrasslands;
	public static BiomeGenBase tropicShrublands;
	public static BiomeGenBase tropicJungle;
	public static BiomeGenBase dryPeaks;
	public static BiomeGenBase dryScrubland;
	public static BiomeGenBase ashField;
	public static BiomeGenBase volcano;
	
	public static void init(){
		tropicOcean = new BiomeGenTropical("Tropic Ocean", ModConfig.tropicOceanId, -1.3F, 0.1F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
		tropicShallows = new BiomeGenTropical("Tropic Shallows", ModConfig.tropicShallowsId, -0.5F, -0.015F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
		tropicReef = new BiomeGenTropical("Tropic Reef", ModConfig.tropicReefId, -0.5F, -0.1F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
		tropicSeaGrassBed = new BiomeGenTropical("Tropic Sea Grass Bed", ModConfig.tropicSeaGrassBedId, -0.5F, -0.025F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
		tropicBlueHoles = new BiomeGenTropical("Tropic Bule Holes", ModConfig.tropicBlueHolesId, -0.5F, -0.015F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
		tropicTrench = new BiomeGenTropical("Tropic Trench", ModConfig.tropicTrenchId, -1.9F, -0.05F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
		tropicKelpForest = new BiomeGenTropical("Tropic Kelp Forest", ModConfig.tropicKelpForestId, -0.5F, -0.025F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
		tropicGrasslands = new BiomeGenTropical("Tropic Grasslands", ModConfig.tropicGrasslandsId, 0.15F, 0.025F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.GRASSY);
		tropicShrublands = new BiomeGenTropical("Tropic Shrublands", ModConfig.tropicShrublandsId, 0.175F, 0.05F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.GRASSY);
		tropicJungle = new BiomeGenTropical("Tropic Jungle", ModConfig.tropicJungleId, 0.175F, 0.05F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.GRASSY);
		dryPeaks = new BiomeGenTropical("Dry Peaks", ModConfig.dryPeaksId, 0.2F, 0.2F, EnumGrassColor.DRY, EnumBiomeSediment.GRASSY);
		dryScrubland = new BiomeGenTropical("Dry Scrublands", ModConfig.dryScrublandId, 0.175F, 0.05F, EnumGrassColor.DRY, EnumBiomeSediment.GRASSY);
		ashField = new BiomeGenTropical("Ash Field", ModConfig.ashFieldId, 0.175F, 0.05F, EnumGrassColor.BURNT, EnumBiomeSediment.GRASSY);
		volcano = new BiomeGenTropical("Volcano", ModConfig.volcanoId, 0.1F, 0.05F, EnumGrassColor.BURNT, EnumBiomeSediment.GRASSY);
		BiomeManager.oceanBiomes.add(tropicOcean);
		BiomeManager.oceanBiomes.add(tropicShallows);
		BiomeManager.oceanBiomes.add(tropicReef);
		BiomeManager.oceanBiomes.add(tropicSeaGrassBed);
		BiomeManager.oceanBiomes.add(tropicBlueHoles);
		BiomeManager.oceanBiomes.add(tropicTrench);
		BiomeManager.oceanBiomes.add(tropicKelpForest);

	}
}

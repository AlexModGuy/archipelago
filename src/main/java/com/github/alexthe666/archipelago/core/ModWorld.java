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
	public static BiomeGenBase tropicBeach;
	public static BiomeGenBase tropicGrasslands;
	public static BiomeGenBase tropicShrublands;
	public static BiomeGenBase tropicJungle;
	public static BiomeGenBase dryPeaks;
	public static BiomeGenBase dryScrubland;
	public static BiomeGenBase ashField;
	public static BiomeGenBase volcano;

	public static void init(){
		tropicOcean = new BiomeGenTropical("Tropic Ocean", ModConfig.tropicOceanId, -1F, 0.1F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
		tropicShallows = new BiomeGenTropical("Tropic Shallows", ModConfig.tropicShallowsId, -0.4F, 0.025F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
		tropicReef = new BiomeGenTropical("Tropic Reef", ModConfig.tropicReefId, -0.4F, 0.05F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
		tropicBeach = new BiomeGenTropical("Tropic Beach", ModConfig.tropicBeachId, 0.1F, 0.025F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
		tropicGrasslands = new BiomeGenTropical("Tropic Grasslands", ModConfig.tropicGrasslandsId, 0.1F, 0.025F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.GRASSY);
		tropicShrublands = new BiomeGenTropical("Tropic Shrublands", ModConfig.tropicShrublandsId, 0.125F, 0.05F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.GRASSY);
		tropicJungle = new BiomeGenTropical("Tropic Jungle", ModConfig.tropicJungleId, 0.125F, 0.05F, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.GRASSY);
		dryPeaks = new BiomeGenTropical("Dry Peaks", ModConfig.dryPeaksId, 0.2F, 0.2F, EnumGrassColor.DRY, EnumBiomeSediment.GRASSY);
		dryScrubland = new BiomeGenTropical("Dry Scrublands", ModConfig.dryScrublandId, 0.125F, 0.05F, EnumGrassColor.DRY, EnumBiomeSediment.GRASSY);
		ashField = new BiomeGenTropical("Ash Field", ModConfig.ashFieldId, 0.125F, 0.05F, EnumGrassColor.BURNT, EnumBiomeSediment.GRASSY);
		volcano = new BiomeGenTropical("Volcano", ModConfig.volcanoId, 0.3F, 0.05F, EnumGrassColor.BURNT, EnumBiomeSediment.GRASSY);
		BiomeManager.oceanBiomes.add(tropicOcean);
		BiomeManager.oceanBiomes.add(tropicShallows);
		BiomeManager.oceanBiomes.add(tropicReef);
	}
}

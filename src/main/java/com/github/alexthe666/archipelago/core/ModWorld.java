package com.github.alexthe666.archipelago.core;

import com.github.alexthe666.archipelago.enums.EnumBiomeSediment;
import com.github.alexthe666.archipelago.enums.EnumGrassColor;
import com.github.alexthe666.archipelago.world.BiomeGenTropical;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager;

public class ModWorld {

    public static Biome tropicOcean;
    public static Biome tropicShallows;
    public static Biome tropicReef;
    public static Biome tropicSeaGrassBed;
    public static Biome tropicBlueHoles;
    public static Biome tropicTrench;
    public static Biome tropicKelpForest;
    public static Biome tropicGrasslands;
    public static Biome tropicShrublands;
    public static Biome tropicJungle;
    public static Biome dryPeaks;
    public static Biome dryScrubland;
    public static Biome ashField;
    public static Biome volcano;

    public static void init() {
        tropicOcean = new BiomeGenTropical("Tropic Ocean", ModConfig.tropicOceanId, -1.3F, 0.1F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicShallows = new BiomeGenTropical("Tropic Shallows", ModConfig.tropicShallowsId, -0.5F, -0.015F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicReef = new BiomeGenTropical("Tropic Reef", ModConfig.tropicReefId, -0.5F, -0.6F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicSeaGrassBed = new BiomeGenTropical("Tropic Sea Grass Bed", ModConfig.tropicSeaGrassBedId, -0.5F, -0.025F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicBlueHoles = new BiomeGenTropical("Tropic Bule Holes", ModConfig.tropicBlueHolesId, -0.5F, -0.015F, 0x479AFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicTrench = new BiomeGenTropical("Tropic Trench", ModConfig.tropicTrenchId, -1.9F, -0.05F, 0x479AFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicKelpForest = new BiomeGenTropical("Tropic Kelp Forest", ModConfig.tropicKelpForestId, -0.5F, -0.025F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicGrasslands = new BiomeGenTropical("Tropic Grasslands", ModConfig.tropicGrasslandsId, 0.15F, 0.025F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.GRASSY);
        tropicShrublands = new BiomeGenTropical("Tropic Shrublands", ModConfig.tropicShrublandsId, 0.175F, 0.05F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.GRASSY);
        tropicJungle = new BiomeGenTropical("Tropic Jungle", ModConfig.tropicJungleId, 0.175F, 0.05F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.GRASSY);
        dryPeaks = new BiomeGenTropical("Dry Peaks", ModConfig.dryPeaksId, 0.2F, 0.2F, 0x46FFFF, EnumGrassColor.DRY, EnumBiomeSediment.GRASSY);
        dryScrubland = new BiomeGenTropical("Dry Scrublands", ModConfig.dryScrublandId, 0.175F, 0.05F, 0x46FFFF, EnumGrassColor.DRY, EnumBiomeSediment.GRASSY);
        ashField = new BiomeGenTropical("Ash Field", ModConfig.ashFieldId, 0.175F, 0.05F, 0x46FFFF, EnumGrassColor.BURNT, EnumBiomeSediment.GRASSY);
        volcano = new BiomeGenTropical("Volcano", ModConfig.volcanoId, 0.1F, 0.05F, 0x46FFFF, EnumGrassColor.BURNT, EnumBiomeSediment.GRASSY);

        BiomeManager.oceanBiomes.add(tropicOcean);
        BiomeManager.oceanBiomes.add(tropicShallows);
        BiomeManager.oceanBiomes.add(tropicReef);
        BiomeManager.oceanBiomes.add(tropicSeaGrassBed);
        BiomeManager.oceanBiomes.add(tropicBlueHoles);
        BiomeManager.oceanBiomes.add(tropicTrench);
        BiomeManager.oceanBiomes.add(tropicKelpForest);
    }
}

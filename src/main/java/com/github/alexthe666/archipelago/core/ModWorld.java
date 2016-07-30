package com.github.alexthe666.archipelago.core;

import com.github.alexthe666.archipelago.enums.EnumBiomeSediment;
import com.github.alexthe666.archipelago.enums.EnumGrassColor;
import com.github.alexthe666.archipelago.enums.EnumTrees;
import com.github.alexthe666.archipelago.world.BiomeGenTropical;
import com.github.alexthe666.archipelago.world.tree.WorldGenCalophyllum;
import com.github.alexthe666.archipelago.world.tree.WorldGenCanaryIslandDatePalm;
import com.github.alexthe666.archipelago.world.tree.WorldGenCoconutPalm;
import com.github.alexthe666.archipelago.world.tree.WorldGenHispaniolan;
import com.github.alexthe666.archipelago.world.tree.WorldGenTambalocoque;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.BiomeManager;

public class ModWorld {
    private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);

    public static Biome tropicOcean;
    public static Biome tropicShallows;
    public static Biome tropicReef;
    public static Biome tropicSeaGrassBed;
    public static Biome tropicBlueHoles;
    public static Biome tropicTrench;
    public static Biome tropicKelpForest;
    public static Biome tropicGrassland;
    public static Biome tropicShrubland;
    public static Biome tropicJungle;
    public static Biome dryPeaks;
    public static Biome dryScrubland;
    public static Biome ashField;
    public static Biome volcano;

    public static void init() {
        tropicOcean = new BiomeGenTropical("Tropic Ocean", ModConfig.tropicOceanId, -1.9F, 0.1F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicShallows = new BiomeGenTropical("Tropic Shallows", ModConfig.tropicShallowsId, -0.5F, -0.015F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicReef = new BiomeGenTropical("Tropic Reef", ModConfig.tropicReefId, -1.0F, 0.4F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicSeaGrassBed = new BiomeGenTropical("Tropic Sea Grass Bed", ModConfig.tropicSeaGrassBedId, -0.5F, -0.025F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicBlueHoles = new BiomeGenTropical("Tropic Bule Holes", ModConfig.tropicBlueHolesId, -0.5F, -0.015F, 0x479AFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicTrench = new BiomeGenTropical("Tropic Trench", ModConfig.tropicTrenchId, -1.9F, -0.05F, 0x479AFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicKelpForest = new BiomeGenTropical("Tropic Kelp Forest", ModConfig.tropicKelpForestId, -0.5F, -0.025F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.SANDY);
        tropicGrassland = new BiomeGenTropical("Tropic Grasslands", ModConfig.tropicGrasslandsId, 0.15F, 0.025F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.GRASSY, new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenCoconutPalm(), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenTrees(false, rand.nextInt(7) + 4, JUNGLE_LOG, JUNGLE_LEAF, true), 5)).setTreesPerChunk(1);
        tropicShrubland = new BiomeGenTropical("Tropic Shrublands", ModConfig.tropicShrublandsId, 0.175F, 0.05F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.GRASSY, new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenTrees(false, rand.nextInt(7) + 4, JUNGLE_LOG, JUNGLE_LEAF, true), 5), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenShrub(JUNGLE_LOG, EnumTrees.CORRIOSA.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false)), 40), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenShrub(JUNGLE_LOG, EnumTrees.GALAPAGOS_MICONIA.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false)), 40)).setTreesPerChunk(20);
        tropicJungle = new BiomeGenTropical("Tropic Jungle", ModConfig.tropicJungleId, 0.175F, 0.05F, 0x46FFFF, EnumGrassColor.CLASSIC_TROPICAL, EnumBiomeSediment.GRASSY, new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenCoconutPalm(), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenTrees(false, rand.nextInt(7) + 4, JUNGLE_LOG, JUNGLE_LEAF, true), 5), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenMegaJungle(false, 20, 7, JUNGLE_LOG, JUNGLE_LEAF), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenTambalocoque(), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenCalophyllum(), 5), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenShrub(JUNGLE_LOG, EnumTrees.CORRIOSA.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false)), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenShrub(JUNGLE_LOG, EnumTrees.GALAPAGOS_MICONIA.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false)), 10)).setTreesPerChunk(20);
        dryPeaks = new BiomeGenTropical("Dry Peaks", ModConfig.dryPeaksId, 3.0F, 0.8F, 0x46FFFF, EnumGrassColor.DRY, EnumBiomeSediment.GRASSY, new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenHispaniolan(), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenSavannaTree(false), 10)).setTreesPerChunk(1);
        dryScrubland = new BiomeGenTropical("Dry Scrublands", ModConfig.dryScrublandId, 0.175F, 0.05F, 0x46FFFF, EnumGrassColor.DRY, EnumBiomeSediment.GRASSY, new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenSavannaTree(false), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenCanaryIslandDatePalm(), 5)).setTreesPerChunk(1);
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

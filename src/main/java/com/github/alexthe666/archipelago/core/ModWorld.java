package com.github.alexthe666.archipelago.core;

import com.github.alexthe666.archipelago.enums.TropicBiomeSediment;
import com.github.alexthe666.archipelago.enums.TropicGrassColor;
import com.github.alexthe666.archipelago.enums.TropicTreeType;
import com.github.alexthe666.archipelago.world.BiomeGenTropical;
import com.github.alexthe666.archipelago.world.tree.WorldGenCalophyllum;
import com.github.alexthe666.archipelago.world.tree.WorldGenCanaryIslandDatePalm;
import com.github.alexthe666.archipelago.world.tree.WorldGenCoconutPalm;
import com.github.alexthe666.archipelago.world.tree.WorldGenHispaniolan;
import com.github.alexthe666.archipelago.world.tree.WorldGenMangrove;
import com.github.alexthe666.archipelago.world.tree.WorldGenTambalocoque;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraftforge.common.BiomeManager;

public class ModWorld {
    private static final IBlockState JUNGLE_LOG = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.JUNGLE);
    private static final IBlockState JUNGLE_LEAF = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.JUNGLE).withProperty(BlockLeaves.CHECK_DECAY, Boolean.FALSE);

    public static BiomeGenTropical tropicOcean;
    public static BiomeGenTropical tropicShallows;
    public static BiomeGenTropical tropicReef;
    public static BiomeGenTropical tropicSeaGrassBed;
    public static BiomeGenTropical tropicBlueHoles;
    public static BiomeGenTropical tropicTrench;
    public static BiomeGenTropical tropicKelpForest;
    public static BiomeGenTropical tropicGrassland;
    public static BiomeGenTropical tropicShrubland;
    public static BiomeGenTropical tropicJungle;
    public static BiomeGenTropical dryPeaks;
    public static BiomeGenTropical dryScrubland;
    public static BiomeGenTropical ashField;
    public static BiomeGenTropical volcano;
    public static BiomeGenTropical tropicRiver;
    public static BiomeGenTropical tropicBeach;
    public static BiomeGenTropical blackSandBeach;
    public static BiomeGenTropical mangroveSwamp;
    public static BiomeGenTropical tropicLakes;

    public static void init() {
        tropicOcean = new BiomeGenTropical("Tropic Ocean", ModConfig.tropicOceanId, -1.4F, 0.1F, 0x46FFFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.SANDY).setGenerationChance(5);
        tropicShallows = new BiomeGenTropical("Tropic Shallows", ModConfig.tropicShallowsId, -0.5F, -0.05F, 0x46FFFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.SANDY);
        tropicRiver = new BiomeGenTropical("Tropic River", ModConfig.tropicRiverId, -0.5F, -0.005F, 0x46FFFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.SANDY);
        tropicReef = new BiomeGenTropical("Tropic Reef", ModConfig.tropicReefId, -0.8F, 0.4F, 0x46FFFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.SANDY);
        tropicSeaGrassBed = new BiomeGenTropical("Tropic Sea Grass Bed", ModConfig.tropicSeaGrassBedId, -0.8F, -0.025F, 0x46FFFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.SANDY);
        tropicBlueHoles = new BiomeGenTropical("Tropic Blue Holes", ModConfig.tropicBlueHolesId, -0.5F, -0.015F, 0x479AFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.SANDY);
        tropicTrench = new BiomeGenTropical("Tropic Trench", ModConfig.tropicTrenchId, -1.9F, -0.05F, 0x479AFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.SANDY).setGenerationChance(3);
        tropicKelpForest = new BiomeGenTropical("Tropic Kelp Forest", ModConfig.tropicKelpForestId, -0.5F, -0.025F, 0x46FFFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.SANDY).setGenerationChance(5);
        tropicGrassland = new BiomeGenTropical("Tropic Grasslands", ModConfig.tropicGrasslandsId, 0.15F, 0.025F, 0x46FFFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.GRASSY, new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenCoconutPalm(), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenTrees(false, rand.nextInt(7) + 4, JUNGLE_LOG, JUNGLE_LEAF, true), 5)).setTreesPerChunk(1).setGenerationChance(15);
        tropicShrubland = new BiomeGenTropical("Tropic Shrublands", ModConfig.tropicShrublandsId, 0.175F, 0.05F, 0x46FFFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.GRASSY, new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenTrees(false, rand.nextInt(7) + 4, JUNGLE_LOG, JUNGLE_LEAF, true), 5), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenShrub(JUNGLE_LOG, TropicTreeType.CORRIOSA.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false)), 40), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenShrub(JUNGLE_LOG, TropicTreeType.GALAPAGOS_MICONIA.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false)), 40)).setTreesPerChunk(20);
        tropicJungle = new BiomeGenTropical("Tropic Jungle", ModConfig.tropicJungleId, 0.175F, 0.05F, 0x46FFFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.GRASSY, new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenCoconutPalm(), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenTrees(false, rand.nextInt(7) + 4, JUNGLE_LOG, JUNGLE_LEAF, true), 5), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenMegaJungle(false, 20, 7, JUNGLE_LOG, JUNGLE_LEAF), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenTambalocoque(), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenCalophyllum(), 5), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenShrub(JUNGLE_LOG, TropicTreeType.CORRIOSA.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false)), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenShrub(JUNGLE_LOG, TropicTreeType.GALAPAGOS_MICONIA.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false)), 10)).setTreesPerChunk(20).setGenerationChance(6);
        dryPeaks = new BiomeGenTropical("Dry Peaks", ModConfig.dryPeaksId, 3.0F, 0.8F, 0x46FFFF, TropicGrassColor.DRY, TropicBiomeSediment.GRASSY, new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenHispaniolan(), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenSavannaTree(false), 10)).setTreesPerChunk(1).setGenerationChance(2);
        dryScrubland = new BiomeGenTropical("Dry Scrublands", ModConfig.dryScrublandId, 0.175F, 0.05F, 0x46FFFF, TropicGrassColor.DRY, TropicBiomeSediment.GRASSY, new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenSavannaTree(false), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenCanaryIslandDatePalm(), 5)).setTreesPerChunk(1).setGenerationChance(5);
        ashField = new BiomeGenTropical("Ash Field", ModConfig.ashFieldId, 0.175F, 0.05F, 0x46FFFF, TropicGrassColor.BURNT, TropicBiomeSediment.VOLCANIC).setGenerationChance(5);
        volcano = new BiomeGenTropical("Volcano", ModConfig.volcanoId, 0.1F, 0.05F, 0x46FFFF, TropicGrassColor.BURNT, TropicBiomeSediment.VOLCANIC).setGenerationChance(4);
        tropicBeach = new BiomeGenTropical("Tropic Beach", ModConfig.tropicBeachId, 0.1F, 0.05F, 0x46FFFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.SANDY);
        blackSandBeach = new BiomeGenTropical("Black Sand Beach", ModConfig.blackSandBeachId, 0.1F, 0.05F, 0x46FFFF, TropicGrassColor.BURNT, TropicBiomeSediment.SANDY);
        mangroveSwamp = new BiomeGenTropical("Mangrove Swamp", ModConfig.mangroveSwampId, -0.2F, 0.1F, 0x46FFFF, TropicGrassColor.SWAMP, TropicBiomeSediment.GRASSY, new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenCoconutPalm(), 10), new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenMangrove(), 10)).setTreesPerChunk(1).setGenerationChance(7);
        tropicLakes = new BiomeGenTropical("Tropic Lakes", ModConfig.tropicLakes, -0.35F, 0.2F, 0x46FFFF, TropicGrassColor.CLASSIC_TROPICAL, TropicBiomeSediment.GRASSY, new BiomeGenTropical.TreeGenerator((rand) -> new WorldGenCoconutPalm(), 10)).setTreesPerChunk(1);

        BiomeManager.oceanBiomes.add(tropicOcean);
        BiomeManager.oceanBiomes.add(tropicShallows);
        BiomeManager.oceanBiomes.add(tropicReef);
        BiomeManager.oceanBiomes.add(tropicSeaGrassBed);
        BiomeManager.oceanBiomes.add(tropicBlueHoles);
        BiomeManager.oceanBiomes.add(tropicTrench);
        BiomeManager.oceanBiomes.add(tropicKelpForest);
    }
}

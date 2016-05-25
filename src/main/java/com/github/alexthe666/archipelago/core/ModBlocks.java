package com.github.alexthe666.archipelago.core;

import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;

import com.github.alexthe666.archipelago.block.BlockCoralRock;
import com.github.alexthe666.archipelago.block.BlockGlowingCoral;
import com.github.alexthe666.archipelago.block.BlockGrowingSeaweed;
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
    public static Block socotra_bottle_tree;
    public static Block socotra_bottle_tree_blooming;
    public static Block socotrine_aloe;
    public static Block abal;
    public static Block australian_desert_raisin;
    public static Block casper_bush;
    public static Block common_yucca;
    public static Block cycas_nayagarhenis;
    public static Block mexican_clover;
    public static Block western_sand_cherry;
    public static Block allamanda_angustifolia;
    public static Block aztec_marigold_red;
    public static Block aztec_marigold_orange;
    public static Block aztec_marigold_yellow;
    public static Block fever_root;
    public static Block hibiscus;
    public static Block kakhiweed;
    public static Block lafoensia_pacari;
    public static Block lily_of_the_valley;
    public static Block monstera_deliciosa;
    public static Block pau_santo;
    public static Block west_indian_lilac;

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
    public static Block venus_fan;
    public static Block cauliflower_soft;
    public static Block gracilaria;
    public static Block pachycerianthus_fimbriatus;
    public static Block stove_pipe_sponge;
    public static Block heteractis_magnifica_blue;
    public static Block heteractis_magnifica_pink;
    public static Block heteractis_magnifica_red;
    public static Block heteractis_magnifica_yellow;
    public static Block bubble_tip;
    public static Block acropora_nasuta;
    public static Block alveopora_spongiosa;
    public static Block acropora_samoensis;
    public static Block dendronephthya_hemprichi;
    public static Block dragon_eye_zoanthid_green;
    public static Block dragon_eye_zoanthid_red;
    public static Block favites_pentagona;
    public static Block galaxy_coral;
    public static Block leptopsammia_pruvoti;
    public static Block lobophyllia_hemprichii;
    public static Block nephthyigorgia;
    public static Block smooth_flower_coral_green;
    public static Block smooth_flower_coral_orange;
    public static Block spiral_wire_coral;
    public static Block whip_fan_orange;
    public static Block whip_fan_red;
    public static Block bennetts_feather_star;
    public static Block passion_flower_feather_star;
    public static Block xestospongia_testudinaria;
    public static Block colonial_tube_sponge;
    public static Block wakame;
    public static Block snakelocks;
    public static Block bull_kelp;
    public static Block giant_kelp;
    public static Block sea_belt_kelp;
    public static Block venus_flower;
    public static Block carribean_seagrass;
    public static Block dwarf_eelgrass;
    public static Block enhalus_acoroides;
    public static Block japanese_ealgrass;
    public static Block sea_nymph;
    public static Block turtlegrass;
    public static Block zostera_tasmanica;
    public static Block coral_rock;

    public static void init() {
        anthurium_andraeanum = new BlockShortPlant("anthurium_andraeanum", 5, new Biome[] { ModWorld.tropicJungle, ModWorld.tropicShrublands });
        bottle_palm = new BlockTallPlant("bottle_palm", 5, new Biome[] { ModWorld.tropicJungle });
        ceratozamia_mexicana = new BlockTallPlant("ceratozamia_mexicana", 5, new Biome[] { ModWorld.tropicJungle, ModWorld.tropicShrublands });
        jambu = new BlockShortPlant("jambu", 5, new Biome[] { ModWorld.tropicJungle });
        canary_island_foxglove = new BlockTallPlant("canary_island_foxglove", 5, new Biome[] { ModWorld.tropicShrublands });
        la_palma_sow_thistle = new BlockTallPlant("la_palma_sow_thistle", 5, new Biome[] { ModWorld.dryScrubland, ModWorld.tropicShrublands });
        parrot_lily = new BlockShortPlant("parrot_lily", 5, new Biome[] { ModWorld.tropicGrasslands, ModWorld.tropicJungle, ModWorld.tropicShrublands });
        socotra_bottle_tree = new BlockTallPlant("socotra_bottle_tree", 5, new Biome[] { ModWorld.dryPeaks });
        socotra_bottle_tree_blooming = new BlockTallPlant("socotra_bottle_tree_blooming", 5, new Biome[] { ModWorld.dryPeaks });
        socotrine_aloe = new BlockShortPlant("socotrine_aloe", 5, new Biome[] { ModWorld.dryPeaks });
        abal = new BlockShortPlant("abal", 5, new Biome[] { ModWorld.dryScrubland });
        australian_desert_raisin = new BlockShortPlant("australian_desert_raisin", 5, new Biome[] { ModWorld.dryScrubland });
        casper_bush = new BlockShortPlant("casper_bush", 5, new Biome[] { ModWorld.dryScrubland });
        common_yucca = new BlockTallPlant("common_yucca", 5, new Biome[] { ModWorld.dryScrubland });
        cycas_nayagarhenis = new BlockTallPlant("cycas_nayagarhenis", 5, new Biome[] { ModWorld.dryScrubland });
        mexican_clover = new BlockShortPlant("mexican_clover", 5, new Biome[] { ModWorld.dryScrubland });
        western_sand_cherry = new BlockShortPlant("western_sand_cherry", 5, new Biome[] { ModWorld.dryScrubland });
        allamanda_angustifolia = new BlockShortPlant("allamanda_angustifolia", 5, new Biome[] { ModWorld.tropicGrasslands });
        aztec_marigold_red = new BlockShortPlant("aztec_marigold_red", 5, new Biome[] { ModWorld.tropicGrasslands });
        aztec_marigold_orange = new BlockShortPlant("aztec_marigold_orange", 5, new Biome[] { ModWorld.tropicGrasslands });
        aztec_marigold_yellow = new BlockShortPlant("aztec_marigold_yellow", 5, new Biome[] { ModWorld.tropicGrasslands });
        fever_root = new BlockShortPlant("fever_root", 5, new Biome[] { ModWorld.tropicGrasslands });
        hibiscus = new BlockTallPlant("hibiscus", 5, new Biome[] { ModWorld.tropicGrasslands });
        kakhiweed = new BlockShortPlant("kakhiweed", 5, new Biome[] { ModWorld.tropicGrasslands });
        lafoensia_pacari = new BlockTallPlant("lafoensia_pacari", 5, new Biome[] { ModWorld.tropicGrasslands });
        lily_of_the_valley = new BlockTallPlant("lily_of_the_valley", 5, new Biome[] { ModWorld.tropicGrasslands });
        monstera_deliciosa = new BlockTallPlant("monstera_deliciosa", 5, new Biome[] { ModWorld.tropicGrasslands });
        pau_santo = new BlockTallPlant("pau_santo", 5, new Biome[] { ModWorld.tropicGrasslands });
        west_indian_lilac = new BlockTallPlant("west_indian_lilac", 5, new Biome[] { ModWorld.tropicGrasslands });


        coral_rock = new BlockCoralRock();
        dendrophyllia_cribosa = new BlockShortCoral("dendrophyllia_cribosa", 5, new Biome[] { ModWorld.tropicOcean, ModWorld.tropicBlueHoles, ModWorld.tropicKelpForest, ModWorld.tropicTrench });
        ivory_bush_coral = new BlockShortCoral("ivory_bush_coral", 5, new Biome[] { ModWorld.tropicOcean });
        maasella_edwardsii = new BlockShortCoral("maasella_edwardsii", 5, new Biome[] { ModWorld.tropicOcean, ModWorld.tropicBlueHoles, ModWorld.tropicKelpForest });
        yellow_gorgonian = new BlockShortCoral("yellow_gorgonian", 5, new Biome[] { ModWorld.tropicOcean, ModWorld.tropicKelpForest });
        yellow_tube_sponge = new BlockShortCoral("yellow_tube_sponge", 5, new Biome[] { ModWorld.tropicReef });
        isidella = new BlockGlowingCoral("isidella", 5, new Biome[] { ModWorld.tropicTrench });
        bipinnate_sea_plume = new BlockShortCoral("bipinnate_sea_plume", 5, new Biome[] { ModWorld.tropicReef, ModWorld.tropicBlueHoles });
        asian_green_mussels = new BlockShortCoral("asian_green_mussels", 5, new Biome[] { ModWorld.tropicOcean, ModWorld.tropicKelpForest });
        blue_mussels = new BlockShortCoral("blue_mussels", 5, new Biome[] { ModWorld.tropicOcean, ModWorld.tropicKelpForest });
        nori = new BlockShortCoral("nori", 5, new Biome[] { ModWorld.tropicOcean });
        cerianthus_lloydi = new BlockShortCoral("cerianthus_lloydi", 5, new Biome[] { ModWorld.tropicOcean });
        common_piddock_colony = new BlockGlowingCoral("common_piddock_colony", 5, new Biome[] { ModWorld.tropicOcean });
        bladderwrack = new BlockShortCoral("bladderwrack", 5, new Biome[] { ModWorld.tropicOcean, ModWorld.tropicKelpForest });
        caulerpa_prolifera = new BlockShortCoral("caulerpa_prolifera", 5, new Biome[] { ModWorld.tropicOcean, ModWorld.tropicKelpForest, ModWorld.tropicSeaGrassBed });
        cuvie = new BlockShortCoral("cuvie", 5, new Biome[] { ModWorld.tropicOcean, ModWorld.tropicKelpForest });
        green_fleece = new BlockShortCoral("green_fleece", 5, new Biome[] { ModWorld.tropicOcean, ModWorld.tropicKelpForest, ModWorld.tropicSeaGrassBed });
        spiral_wrack = new BlockShortCoral("spiral_wrack", 5, new Biome[] { ModWorld.tropicOcean, ModWorld.tropicKelpForest });
        tooth_wrack = new BlockShortCoral("tooth_wrack", 5, new Biome[] { ModWorld.tropicOcean, ModWorld.tropicKelpForest });
        caulerpa_taxifolia = new BlockShortCoral("caulerpa_taxifolia", 5, new Biome[] { ModWorld.tropicOcean, ModWorld.tropicKelpForest });
        venus_fan = new BlockShortCoral("venus_fan", 5, new Biome[] { ModWorld.tropicBlueHoles });
        cauliflower_soft = new BlockShortCoral("cauliflower_soft", 5, new Biome[] { ModWorld.tropicReef, ModWorld.tropicBlueHoles });
        gracilaria = new BlockShortCoral("gracilaria", 5, new Biome[] { ModWorld.tropicBlueHoles });
        pachycerianthus_fimbriatus = new BlockShortCoral("pachycerianthus_fimbriatus", 5, new Biome[] { ModWorld.tropicBlueHoles });
        stove_pipe_sponge = new BlockShortCoral("stove_pipe_sponge", 5, new Biome[] { ModWorld.tropicReef, ModWorld.tropicBlueHoles });
        heteractis_magnifica_blue = new BlockShortCoral("heteractis_magnifica_blue", 5, new Biome[] { ModWorld.tropicReef });
        heteractis_magnifica_pink = new BlockShortCoral("heteractis_magnifica_pink", 5, new Biome[] { ModWorld.tropicReef });
        heteractis_magnifica_red = new BlockShortCoral("heteractis_magnifica_red", 5, new Biome[] { ModWorld.tropicReef });
        heteractis_magnifica_yellow = new BlockShortCoral("heteractis_magnifica_yellow", 5, new Biome[] { ModWorld.tropicReef });
        bubble_tip = new BlockShortCoral("bubble_tip", 5, new Biome[] { ModWorld.tropicReef });
        acropora_nasuta = new BlockShortCoral("acropora_nasuta", 5, new Biome[] { ModWorld.tropicReef });
        alveopora_spongiosa = new BlockShortCoral("alveopora_spongiosa", 5, new Biome[] { ModWorld.tropicReef });
        acropora_samoensis = new BlockShortCoral("acropora_samoensis", 5, new Biome[] { ModWorld.tropicReef });
        dendronephthya_hemprichi = new BlockShortCoral("dendronephthya_hemprichi", 5, new Biome[] { ModWorld.tropicReef });
        dragon_eye_zoanthid_green = new BlockShortCoral("dragon_eye_zoanthid_green", 5, new Biome[] { ModWorld.tropicReef });
        dragon_eye_zoanthid_red = new BlockShortCoral("dragon_eye_zoanthid_red", 5, new Biome[] { ModWorld.tropicReef });
        favites_pentagona = new BlockShortCoral("favites_pentagona", 5, new Biome[] { ModWorld.tropicReef });
        galaxy_coral = new BlockShortCoral("galaxy_coral", 5, new Biome[] { ModWorld.tropicReef });
        leptopsammia_pruvoti = new BlockShortCoral("leptopsammia_pruvoti", 5, new Biome[] { ModWorld.tropicReef });
        lobophyllia_hemprichii = new BlockShortCoral("lobophyllia_hemprichii", 5, new Biome[] { ModWorld.tropicReef });
        nephthyigorgia = new BlockShortCoral("nephthyigorgia", 5, new Biome[] { ModWorld.tropicReef });
        smooth_flower_coral_green = new BlockShortCoral("smooth_flower_coral_green", 5, new Biome[] { ModWorld.tropicReef });
        smooth_flower_coral_orange = new BlockShortCoral("smooth_flower_coral_orange", 5, new Biome[] { ModWorld.tropicReef });
        spiral_wire_coral = new BlockShortCoral("spiral_wire_coral", 5, new Biome[] { ModWorld.tropicReef });
        whip_fan_orange = new BlockShortCoral("whip_fan_orange", 5, new Biome[] { ModWorld.tropicReef });
        whip_fan_red = new BlockShortCoral("whip_fan_red", 5, new Biome[] { ModWorld.tropicReef });
        bennetts_feather_star = new BlockShortCoral("bennetts_feather_star", 5, new Biome[] { ModWorld.tropicReef });
        passion_flower_feather_star = new BlockShortCoral("passion_flower_feather_star", 5, new Biome[] { ModWorld.tropicReef });
        xestospongia_testudinaria = new BlockShortCoral("xestospongia_testudinaria", 5, new Biome[] { ModWorld.tropicReef });
        colonial_tube_sponge = new BlockShortCoral("colonial_tube_sponge", 5, new Biome[] { ModWorld.tropicReef });
        wakame = new BlockShortCoral("wakame", 5, new Biome[] { ModWorld.tropicKelpForest });
        snakelocks = new BlockShortCoral("snakelocks", 5, new Biome[] { ModWorld.tropicKelpForest, ModWorld.tropicSeaGrassBed });
        bull_kelp = new BlockGrowingSeaweed("bull_kelp", 5, 3, new Biome[] { ModWorld.tropicKelpForest });
        giant_kelp = new BlockGrowingSeaweed("giant_kelp", 5, 3, new Biome[] { ModWorld.tropicKelpForest });
        sea_belt_kelp = new BlockGrowingSeaweed("sea_belt_kelp", 5, 3, new Biome[] { ModWorld.tropicKelpForest });
        venus_flower = new BlockShortCoral("venus_flower", 5, new Biome[] { ModWorld.tropicTrench });
        carribean_seagrass = new BlockShortCoral("carribean_seagrass", 5, new Biome[] { ModWorld.tropicSeaGrassBed });
        dwarf_eelgrass = new BlockShortCoral("dwarf_eelgrass", 5, new Biome[] { ModWorld.tropicSeaGrassBed });
        enhalus_acoroides = new BlockShortCoral("enhalus_acoroides", 5, new Biome[] { ModWorld.tropicSeaGrassBed });
        japanese_ealgrass = new BlockShortCoral("japanese_ealgrass", 5, new Biome[] { ModWorld.tropicSeaGrassBed });
        sea_nymph = new BlockShortCoral("sea_nymph", 5, new Biome[] { ModWorld.tropicSeaGrassBed });
        turtlegrass = new BlockShortCoral("turtlegrass", 5, new Biome[] { ModWorld.tropicSeaGrassBed });
        zostera_tasmanica = new BlockShortCoral("zostera_tasmanica", 5, new Biome[] { ModWorld.tropicSeaGrassBed });
    }
}

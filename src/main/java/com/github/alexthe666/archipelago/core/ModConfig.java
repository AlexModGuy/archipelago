package com.github.alexthe666.archipelago.core;

import net.minecraftforge.common.config.Configuration;

public class ModConfig {

    public static int ARCHIPELAGO_DIMENSION_ID;
    public static int tropicOceanId;
    public static int tropicShallowsId;
    public static int tropicReefId;
    public static int tropicBeachId;
    public static int tropicGrasslandsId;
    public static int tropicShrublandsId;
    public static int tropicJungleId;
    public static int dryPeaksId;
    public static int dryScrublandId;
    public static int ashFieldId;
    public static int volcanoId;
    public static int tropicSeaGrassBedId;
    public static int tropicBlueHolesId;
    public static int tropicTrenchId;
    public static int tropicKelpForestId;

    public static void load(Configuration config) {
        ARCHIPELAGO_DIMENSION_ID = config.get("ID's", "Archipelago Dimension ID", 137).getInt();
        tropicOceanId = config.get("ID's", "Tropic Ocean Biome ID", 168).getInt();
        tropicShallowsId = config.get("ID's", "Tropic Shallows Biome ID", 169).getInt();
        tropicReefId = config.get("ID's", "Tropic Reef Biome ID", 170).getInt();
        tropicBeachId = config.get("ID's", "Tropic Beach Biome ID", 171).getInt();
        tropicGrasslandsId = config.get("ID's", "Tropic Grasslands Biome ID", 172).getInt();
        tropicShrublandsId = config.get("ID's", "Tropic Shrublands Biome ID", 173).getInt();
        tropicJungleId = config.get("ID's", "Tropic Jungle Biome ID", 174).getInt();
        dryPeaksId = config.get("ID's", "Dry Peaks Biome ID", 175).getInt();
        dryScrublandId = config.get("ID's", "Dry Scrubland Biome ID", 176).getInt();
        ashFieldId = config.get("ID's", "Ash Field Biome ID", 177).getInt();
        volcanoId = config.get("ID's", "Volcano Biome ID", 178).getInt();
        tropicSeaGrassBedId = config.get("ID's", "Tropic Sea Grass Bed Biome ID", 179).getInt();
        tropicBlueHolesId = config.get("ID's", "Tropic Blue Holes Biome ID", 180).getInt();
        tropicTrenchId = config.get("ID's", "Tropic Trench Biome ID", 181).getInt();
        tropicKelpForestId = config.get("ID's", "Tropic Kelp Forest Biome ID", 182).getInt();
    }
}

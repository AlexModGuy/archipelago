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
    public static int tropicRiverId;
    public static int blackSandBeachId;
    public static int mangroveSwampId;
    public static int tropicLakes;

    public static void load(Configuration config) {
        ARCHIPELAGO_DIMENSION_ID = config.get("IDs", "Archipelago Dimension ID", 137).getInt();
        tropicOceanId = config.get("IDs", "Tropic Ocean Biome ID", 168).getInt();
        tropicShallowsId = config.get("IDs", "Tropic Shallows Biome ID", 169).getInt();
        tropicReefId = config.get("IDs", "Tropic Reef Biome ID", 170).getInt();
        tropicBeachId = config.get("IDs", "Tropic Beach Biome ID", 171).getInt();
        tropicGrasslandsId = config.get("IDs", "Tropic Grasslands Biome ID", 172).getInt();
        tropicShrublandsId = config.get("IDs", "Tropic Shrublands Biome ID", 173).getInt();
        tropicJungleId = config.get("IDs", "Tropic Jungle Biome ID", 174).getInt();
        dryPeaksId = config.get("IDs", "Dry Peaks Biome ID", 175).getInt();
        dryScrublandId = config.get("IDs", "Dry Scrubland Biome ID", 176).getInt();
        ashFieldId = config.get("IDs", "Ash Field Biome ID", 177).getInt();
        volcanoId = config.get("IDs", "Volcano Biome ID", 178).getInt();
        tropicSeaGrassBedId = config.get("IDs", "Tropic Sea Grass Bed Biome ID", 179).getInt();
        tropicBlueHolesId = config.get("IDs", "Tropic Blue Holes Biome ID", 180).getInt();
        tropicTrenchId = config.get("IDs", "Tropic Trench Biome ID", 181).getInt();
        tropicKelpForestId = config.get("IDs", "Tropic Kelp Forest Biome ID", 182).getInt();
        tropicRiverId = config.get("IDs", "Tropic River Biome ID", 183).getInt();
        blackSandBeachId = config.get("IDs", "Black Sand Beach Biome ID", 184).getInt();
        mangroveSwampId = config.get("IDs", "Mangrove Swamp Biome ID", 184).getInt();
        tropicLakes = config.get("IDs", "Tropic Lakes Biome ID", 185).getInt();
    }
}

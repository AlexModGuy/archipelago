package com.github.alexthe666.archipelago.world;

import com.github.alexthe666.archipelago.core.ModWorld;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorldChunkManagerArchipelago extends BiomeProvider {

    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private BiomeCache biomeCache;
    @SuppressWarnings("rawtypes")
    private List biomesToSpawnIn;

    @SuppressWarnings("unchecked")
    public WorldChunkManagerArchipelago() {
        this.biomeCache = new BiomeCache(this);
        this.biomesToSpawnIn = new ArrayList<BiomeGenBase>();
        this.biomesToSpawnIn.add(ModWorld.tropicOcean);
        this.biomesToSpawnIn.add(ModWorld.tropicShallows);
        this.biomesToSpawnIn.add(ModWorld.tropicReef);
        this.biomesToSpawnIn.add(ModWorld.tropicGrasslands);
        this.biomesToSpawnIn.add(ModWorld.tropicShrublands);
        this.biomesToSpawnIn.add(ModWorld.tropicJungle);
        this.biomesToSpawnIn.add(ModWorld.dryPeaks);
        this.biomesToSpawnIn.add(ModWorld.dryScrubland);
        this.biomesToSpawnIn.add(ModWorld.ashField);
        this.biomesToSpawnIn.add(ModWorld.volcano);
        this.biomesToSpawnIn.add(ModWorld.tropicSeaGrassBed);
        this.biomesToSpawnIn.add(ModWorld.tropicBlueHoles);
        this.biomesToSpawnIn.add(ModWorld.tropicTrench);
        this.biomesToSpawnIn.add(ModWorld.tropicKelpForest);


    }

    public WorldChunkManagerArchipelago(long seed, WorldType worldtype) {
        this();
        GenLayer[] agenlayer = GenLayerArchipelago.initializeAllBiomeGenerators(seed, worldtype, null);
        this.genBiomes = agenlayer[0];
        this.biomeIndexLayer = agenlayer[1];
    }

    @SuppressWarnings("rawtypes")
    public List getBiomesToSpawnIn() {
        return this.biomesToSpawnIn;
    }

    @SideOnly(Side.CLIENT)
    public float getTemperatureAtHeight(float x, int z) {
        return x;
    }

    public BiomeGenBase[] getBiomesForGeneration(BiomeGenBase[] par1ArrayOfBiomeGenBase, int par2, int par3, int par4, int par5) {
        IntCache.resetIntCache();
        if (par1ArrayOfBiomeGenBase == null || par1ArrayOfBiomeGenBase.length < par4 * par5) {
            par1ArrayOfBiomeGenBase = new BiomeGenBase[par4 * par5];
        }
        int[] aint = this.genBiomes.getInts(par2, par3, par4, par5);
        try {
            for (int i1 = 0; i1 < par4 * par5; ++i1) {
                par1ArrayOfBiomeGenBase[i1] = BiomeGenBase.getBiomeForId(aint[i1]);
            }
            return par1ArrayOfBiomeGenBase;
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("RawBiomeBlock");
            crashreportcategory.addCrashSection("biomes[] size", Integer.valueOf(par1ArrayOfBiomeGenBase.length));
            crashreportcategory.addCrashSection("x", Integer.valueOf(par2));
            crashreportcategory.addCrashSection("z", Integer.valueOf(par3));
            crashreportcategory.addCrashSection("w", Integer.valueOf(par4));
            crashreportcategory.addCrashSection("h", Integer.valueOf(par5));
            throw new ReportedException(crashreport);
        }
    }

    public BiomeGenBase[] loadBlockGeneratorData(BiomeGenBase[] listToReuse, int x, int z, int width, int height) {
        return this.getBiomeGenAt(listToReuse, x, z, width, height, true);
    }

    public BiomeGenBase[] getBiomeGenAt(BiomeGenBase[] listToReuse, int x, int z, int width, int height, boolean flag) {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * height) {
            listToReuse = new BiomeGenBase[width * height];
        }

        if (flag && width == 16 && height == 16 && (x & 15) == 0 && (z & 15) == 0) {
            BiomeGenBase[] abiomegenbase1 = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(abiomegenbase1, 0, listToReuse, 0, width * height);
            return listToReuse;
        } else {
            int[] aint = this.biomeIndexLayer.getInts(x, z, width, height);

            for (int i1 = 0; i1 < width * height; ++i1) {
                listToReuse[i1] = BiomeGenBase.getBiome(aint[i1]);
            }

            return listToReuse;
        }
    }

    public boolean areBiomesViable(int x, int y, int list, List allowed) {
        IntCache.resetIntCache();
        int l = x - list >> 2;
        int i1 = y - list >> 2;
        int j1 = x + list >> 2;
        int k1 = y + list >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genBiomes.getInts(l, i1, l1, i2);

        try {
            for (int j2 = 0; j2 < l1 * i2; ++j2) {
                BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[j2]);

                if (!allowed.contains(biomegenbase)) {
                    return false;
                }
            }

            return true;
        } catch (Throwable throwable) {
            CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Invalid Biome id");
            CrashReportCategory crashreportcategory = crashreport.makeCategory("Layer");
            crashreportcategory.addCrashSection("Layer", this.genBiomes.toString());
            crashreportcategory.addCrashSection("x", Integer.valueOf(x));
            crashreportcategory.addCrashSection("z", Integer.valueOf(y));
            crashreportcategory.addCrashSection("radius", Integer.valueOf(list));
            crashreportcategory.addCrashSection("allowed", allowed);
            throw new ReportedException(crashreport);
        }
    }

    public BlockPos findBiomePosition(int x, int y, int z, List allowed, Random rand) {
        IntCache.resetIntCache();
        int l = x - z >> 2;
        int i1 = y - z >> 2;
        int j1 = x + z >> 2;
        int k1 = y + z >> 2;
        int l1 = j1 - l + 1;
        int i2 = k1 - i1 + 1;
        int[] aint = this.genBiomes.getInts(l, i1, l1, i2);
        BlockPos chunkposition = null;
        int j2 = 0;

        for (int k2 = 0; k2 < l1 * i2; ++k2) {
            int l2 = l + k2 % l1 << 2;
            int i3 = i1 + k2 / l1 << 2;
            BiomeGenBase biomegenbase = BiomeGenBase.getBiome(aint[k2]);

            if (allowed.contains(biomegenbase) && (chunkposition == null || rand.nextInt(j2 + 1) == 0)) {
                chunkposition = new BlockPos(l2, 0, i3);
                ++j2;
            }
        }

        return chunkposition;
    }

    public void cleanupCache() {
        this.biomeCache.cleanupCache();
    }

    public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original) {
        WorldTypeEvent.InitBiomeGens event = new WorldTypeEvent.InitBiomeGens(worldType, seed, original);
        MinecraftForge.TERRAIN_GEN_BUS.post(event);
        return event.getNewBiomeGens();
    }

}

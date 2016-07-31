package com.github.alexthe666.archipelago.world;

import com.github.alexthe666.archipelago.core.ModFluids;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.NoiseGeneratorOctaves;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraftforge.common.BiomeManager;

import java.util.List;
import java.util.Random;

public class ChunkGeneratorArchipelago implements IChunkGenerator {
    protected static final IBlockState stone = Blocks.STONE.getDefaultState();
    private final Random rand;
    private final World worldObj;
    private final WorldType terrainType;
    private final double[] heightMap;
    private final float[] field_185999_r;
    public NoiseGeneratorOctaves field_185983_b;
    public NoiseGeneratorOctaves field_185984_c;
    public NoiseGeneratorOctaves field_185985_d;
    double[] field_185986_e;
    double[] field_185987_f;
    double[] field_185988_g;
    double[] field_185989_h;
    private NoiseGeneratorOctaves field_185991_j;
    private NoiseGeneratorOctaves field_185992_k;
    private NoiseGeneratorOctaves field_185993_l;
    private NoiseGeneratorPerlin field_185994_m;
    private ChunkProviderSettings settings;
    private IBlockState oceanBlock = ModFluids.tropical_water.getDefaultState();
    private double[] noise = new double[256];
    private Biome[] biomesForGeneration;
    private MapGenBase caveGenerator = new MapGenBlueHoles();

    public ChunkGeneratorArchipelago(World worldIn, long seed) {
        this.worldObj = worldIn;
        this.terrainType = worldIn.getWorldInfo().getTerrainType();
        this.rand = new Random(seed);
        this.field_185991_j = new NoiseGeneratorOctaves(this.rand, 16);
        this.field_185992_k = new NoiseGeneratorOctaves(this.rand, 16);
        this.field_185993_l = new NoiseGeneratorOctaves(this.rand, 8);
        this.field_185994_m = new NoiseGeneratorPerlin(this.rand, 4);
        this.field_185983_b = new NoiseGeneratorOctaves(this.rand, 10);
        this.field_185984_c = new NoiseGeneratorOctaves(this.rand, 16);
        this.field_185985_d = new NoiseGeneratorOctaves(this.rand, 8);
        this.heightMap = new double[825];
        this.field_185999_r = new float[25];
        for (int i = -2; i <= 2; ++i) {
            for (int j = -2; j <= 2; ++j) {
                float f = 10.0F / MathHelper.sqrt_float(i * i + j * j + 0.2F);
                this.field_185999_r[i + 2 + (j + 2) * 5] = f;
            }
        }
        net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld ctx = new net.minecraftforge.event.terraingen.InitNoiseGensEvent.ContextOverworld(this.field_185991_j, this.field_185992_k, this.field_185993_l, this.field_185994_m, this.field_185983_b, this.field_185984_c, this.field_185985_d);
        ctx = net.minecraftforge.event.terraingen.TerrainGen.getModdedNoiseGenerators(worldIn, this.rand, ctx);
        this.field_185991_j = ctx.getLPerlin1();
        this.field_185992_k = ctx.getLPerlin2();
        this.field_185993_l = ctx.getPerlin();
        this.field_185994_m = ctx.getHeight();
        this.field_185983_b = ctx.getScale();
        this.field_185984_c = ctx.getDepth();
        this.field_185985_d = ctx.getForest();
        ChunkProviderSettings.Factory fact = new ChunkProviderSettings.Factory();
        fact.coordinateScale = 450;
        fact.heightScale = 1;
        fact.upperLimitScale = 1600;
        fact.lowerLimitScale = 1600;
        fact.depthNoiseScaleX = 150;
        fact.depthNoiseScaleZ = 150;
        fact.depthNoiseScaleExponent = 0.4F;
        fact.mainNoiseScaleX = 1400;
        fact.mainNoiseScaleY = 4000;
        fact.mainNoiseScaleZ = 1400;
        fact.biomeSize = 6;
        fact.stretchY = 6;
        fact.seaLevel = 63;
        fact.biomeSize = 6;
        this.settings = fact.build();
        this.caveGenerator = net.minecraftforge.event.terraingen.TerrainGen.getModdedMapGen(this.caveGenerator, net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE);
    }

    public void setBlocksInChunk(int x, int z, ChunkPrimer primer) {
        this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomesForGeneration(this.biomesForGeneration, x * 4 - 2, z * 4 - 2, 10, 10);
        this.generateHeightmap(x * 4, 0, z * 4);
        for (int i = 0; i < 4; ++i) {
            int j = i * 5;
            int k = (i + 1) * 5;
            for (int l = 0; l < 4; ++l) {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;
                for (int i2 = 0; i2 < 32; ++i2) {
                    double d0 = 0.125D;
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * d0;
                    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * d0;
                    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * d0;
                    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * d0;
                    for (int j2 = 0; j2 < 8; ++j2) {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;
                        for (int k2 = 0; k2 < 4; ++k2) {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double lvt_45_1_ = d10 - d16;
                            for (int l2 = 0; l2 < 4; ++l2) {
                                if ((lvt_45_1_ += d16) > 0.0D) {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, stone);
                                } else if (i2 * 8 + j2 < this.settings.seaLevel) {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.oceanBlock);
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomes) {
        if (!net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, primer, this.worldObj))
            return;
        double d0 = 0.03125D;
        this.noise = this.field_185994_m.getRegion(this.noise, x * 16, z * 16, 16, 16, d0 * 2.0D, d0 * 2.0D, 1.0D);

        for (int blockX = 0; blockX < 16; ++blockX) {
            for (int blockZ = 0; blockZ < 16; ++blockZ) {
                Biome biome = biomes[blockZ + blockX * 16];
                biome.genTerrainBlocks(this.worldObj, this.rand, primer, x * 16 + blockX, z * 16 + blockZ, this.noise[blockZ + blockX * 16]);
            }
        }
    }

    @Override
    public Chunk provideChunk(int x, int z) {
        this.rand.setSeed(x * 341873128712L + z * 132897987541L);
        ChunkPrimer chunkprimer = new ChunkPrimer();
        this.setBlocksInChunk(x, z, chunkprimer);
        this.biomesForGeneration = this.worldObj.getBiomeProvider().loadBlockGeneratorData(this.biomesForGeneration, x * 16, z * 16, 16, 16);
        this.replaceBiomeBlocks(x, z, chunkprimer, this.biomesForGeneration);
        this.caveGenerator.generate(this.worldObj, x, z, chunkprimer);
        Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
        byte[] biomes = chunk.getBiomeArray();

        for (int i = 0; i < biomes.length; ++i) {
            biomes[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    private void generateHeightmap(int x, int y, int z) {
        this.field_185989_h = this.field_185984_c.generateNoiseOctaves(this.field_185989_h, x, z, 5, 5, this.settings.depthNoiseScaleX, this.settings.depthNoiseScaleZ, this.settings.depthNoiseScaleExponent);
        float f = this.settings.coordinateScale;
        float f1 = this.settings.heightScale;
        this.field_185986_e = this.field_185993_l.generateNoiseOctaves(this.field_185986_e, x, y, z, 5, 33, 5, f / this.settings.mainNoiseScaleX, f1 / this.settings.mainNoiseScaleY, f / this.settings.mainNoiseScaleZ);
        this.field_185987_f = this.field_185991_j.generateNoiseOctaves(this.field_185987_f, x, y, z, 5, 33, 5, f, f1, f);
        this.field_185988_g = this.field_185992_k.generateNoiseOctaves(this.field_185988_g, x, y, z, 5, 33, 5, f, f1, f);
        z = 0;
        x = 0;
        int i = 0;
        int j = 0;
        for (int k = 0; k < 5; ++k) {
            for (int l = 0; l < 5; ++l) {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                int i1 = 2;
                Biome biomegenbase = this.biomesForGeneration[k + 2 + (l + 2) * 10];
                for (int j1 = -i1; j1 <= i1; ++j1) {
                    for (int k1 = -i1; k1 <= i1; ++k1) {
                        Biome biomegenbase1 = this.biomesForGeneration[k + j1 + 2 + (l + k1 + 2) * 10];
                        float f5 = this.settings.biomeDepthOffSet + biomegenbase1.getBaseHeight() * this.settings.biomeDepthWeight;
                        float f6 = this.settings.biomeScaleOffset + biomegenbase1.getHeightVariation() * this.settings.biomeScaleWeight;

                        if (this.terrainType == WorldType.AMPLIFIED && f5 > 0.0F) {
                            f5 = 1.0F + f5 * 2.0F;
                            f6 = 1.0F + f6 * 4.0F;
                        }

                        float f7 = this.field_185999_r[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

                        if (biomegenbase1.getBaseHeight() > biomegenbase.getBaseHeight()) {
                            f7 /= 2.0F;
                        }

                        f2 += f6 * f7;
                        f3 += f5 * f7;
                        f4 += f7;
                    }
                }
                f2 = f2 / f4;
                f3 = f3 / f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = this.field_185989_h[j] / 8000.0D;
                if (d7 < 0.0D) {
                    d7 = -d7 * 0.3D;
                }
                d7 = d7 * 3.0D - 2.0D;
                if (d7 < 0.0D) {
                    d7 = d7 / 2.0D;
                    if (d7 < -1.0D) {
                        d7 = -1.0D;
                    }
                    d7 = d7 / 1.4D;
                    d7 = d7 / 2.0D;
                } else {
                    if (d7 > 1.0D) {
                        d7 = 1.0D;
                    }
                    d7 = d7 / 8.0D;
                }
                ++j;
                double d8 = f3;
                double d9 = f2;
                d8 = d8 + d7 * 0.2D;
                d8 = d8 * this.settings.baseSize / 8.0D;
                double d0 = this.settings.baseSize + d8 * 4.0D;
                for (int l1 = 0; l1 < 33; ++l1) {
                    double d1 = (l1 - d0) * this.settings.stretchY * 128.0D / 256.0D / d9;
                    if (d1 < 0.0D) {
                        d1 *= 4.0D;
                    }
                    double d2 = this.field_185987_f[i] / this.settings.lowerLimitScale;
                    double d3 = this.field_185988_g[i] / this.settings.upperLimitScale;
                    double d4 = (this.field_185986_e[i] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.denormalizeClamp(d2, d3, d4) - d1;
                    if (l1 > 29) {
                        double d6 = (l1 - 29) / 3.0F;
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    this.heightMap[i] = d5;
                    ++i;
                }
            }
        }
    }

    @Override
    public void populate(int x, int z) {
        BlockFalling.fallInstantly = true;
        int i = x * 16;
        int j = z * 16;
        BlockPos blockpos = new BlockPos(i, 0, j);
        Biome Biome = this.worldObj.getBiomeGenForCoords(blockpos.add(16, 0, 16));
        this.rand.setSeed(this.worldObj.getSeed());
        long k = this.rand.nextLong() / 2L * 2L + 1L;
        long l = this.rand.nextLong() / 2L * 2L + 1L;
        this.rand.setSeed(x * k + z * l ^ this.worldObj.getSeed());
        boolean flag = false;
        ChunkPos chunkcoordintpair = new ChunkPos(x, z);
        if (this.settings.useWaterLakes && !flag && this.rand.nextInt(this.settings.waterLakeChance) == 0 && !BiomeManager.oceanBiomes.contains(Biome)) {
            if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.worldObj, this.rand, x, z, flag, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.LAKE)) {
                int i1 = this.rand.nextInt(16) + 8;
                int j1 = this.rand.nextInt(256);
                int k1 = this.rand.nextInt(16) + 8;
                (new WorldGenArchipelagoLakes(ModFluids.tropical_water)).generate(this.worldObj, this.rand, blockpos.add(i1, j1, k1));
            }
        }
        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(true, this, this.worldObj, x, z, flag);
        Biome.decorate(this.worldObj, this.rand, new BlockPos(i, 0, j));
        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.worldObj, this.rand, x, z, flag, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ANIMALS))
            WorldEntitySpawner.performWorldGenSpawning(this.worldObj, Biome, i + 8, j + 8, 16, 16, this.rand);
        blockpos = blockpos.add(8, 0, 8);

        if (net.minecraftforge.event.terraingen.TerrainGen.populate(this, this.worldObj, this.rand, x, z, flag, net.minecraftforge.event.terraingen.PopulateChunkEvent.Populate.EventType.ICE)) {
            for (int k2 = 0; k2 < 16; ++k2) {
                for (int j3 = 0; j3 < 16; ++j3) {
                    BlockPos blockpos1 = this.worldObj.getPrecipitationHeight(blockpos.add(k2, 0, j3));
                    BlockPos blockpos2 = blockpos1.down();
                    if (this.worldObj.canBlockFreezeWater(blockpos2)) {
                        this.worldObj.setBlockState(blockpos2, Blocks.ICE.getDefaultState(), 2);
                    }
                    if (this.worldObj.canSnowAt(blockpos1, true)) {
                        this.worldObj.setBlockState(blockpos1, Blocks.SNOW_LAYER.getDefaultState(), 2);
                    }
                }
            }
        }
        net.minecraftforge.event.ForgeEventFactory.onChunkPopulate(false, this, this.worldObj, x, z, flag);
        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        Biome Biome = this.worldObj.getBiomeGenForCoords(pos);
        return Biome.getSpawnableList(creatureType);
    }

    @Override
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {
    }
}
package com.github.alexthe666.archipelago.world;

import com.github.alexthe666.archipelago.core.ModFluids;
import com.github.alexthe666.archipelago.core.ModWorld;
import com.google.common.base.Objects;
import net.minecraft.block.BlockSand;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;

import java.util.Random;

public class MapGenBlueHoles extends MapGenBase {
    protected static final IBlockState BLK_LAVA = Blocks.LAVA.getDefaultState();
    protected static final IBlockState BLK_AIR = Blocks.AIR.getDefaultState();
    protected static final IBlockState BLK_WATER = ModFluids.tropical_water.getDefaultState();
    protected static final IBlockState BLK_SANDSTONE = Blocks.SANDSTONE.getDefaultState();
    protected static final IBlockState BLK_RED_SANDSTONE = Blocks.RED_SANDSTONE.getDefaultState();

    protected void addRoom(long seed, int width, int height, ChunkPrimer primer, double d0, double d1, double d2) {
        this.addTunnel(seed, width, height, primer, d0, d1, d2, 3.0F + this.rand.nextFloat() * 6.0F, 0.0F, 0.0F, -1, -1, 3D);
    }

    protected void addTunnel(long seed, int width, int height, ChunkPrimer chunkPrimer, double double0, double double1, double double2, float float1, float float2, float float3, int int1, int int2, double double3) {
        double d0 = (double) (width * 16 + 8);
        double d1 = (double) (height * 16 + 8);
        float f = 0.0F;
        float f1 = 0.0F;
        Random random = new Random(seed);

        if (int2 <= 0) {
            int i = this.range * 16 - 16;
            int2 = i - random.nextInt(i / 4);
        }

        boolean flag2 = false;

        if (int1 == -1) {
            int1 = int2 / 2;
            flag2 = true;
        }

        int j = random.nextInt(int2 / 2) + int2 / 4;

        for (boolean flag = random.nextInt(6) == 0; int1 < int2; ++int1) {
            double d2 = 1.5D + (double) (MathHelper.sin((float) int1 * (float) Math.PI / (float) int2) * float1);
            double d3 = d2 * double3;
            float f2 = MathHelper.cos(float3);
            float f3 = MathHelper.sin(float3);
            double0 += (double) (MathHelper.cos(float2) * f2);
            double1 += (double) f3;
            double2 += (double) (MathHelper.sin(float2) * f2);

            if (flag) {
                float3 = float3 * 0.92F;
            } else {
                float3 = float3 * 0.7F;
            }

            float3 = float3 + f1 * 0.1F;
            float2 += f * 0.1F;
            f1 = f1 * 0.9F;
            f = f * 0.75F;
            f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f = f + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;

            if (!flag2 && int1 == j && float1 > 1.0F && int2 > 0) {
                this.addTunnel(random.nextLong(), width, height, chunkPrimer, double0, double1, double2, random.nextFloat() * 0.5F + 0.5F, float2 - ((float) Math.PI / 2F), float3 / 3.0F, int1, int2, 1.0D);
                this.addTunnel(random.nextLong(), width, height, chunkPrimer, double0, double1, double2, random.nextFloat() * 0.5F + 0.5F, float2 + ((float) Math.PI / 2F), float3 / 3.0F, int1, int2, 1.0D);
                return;
            }

            if (flag2 || random.nextInt(4) != 0) {
                double d4 = double0 - d0;
                double d5 = double2 - d1;
                double d6 = (double) (int2 - int1);
                double d7 = (double) (float1 + 2.0F + 16.0F);

                if (d4 * d4 + d5 * d5 - d6 * d6 > d7 * d7) {
                    return;
                }

                if (double0 >= d0 - 16.0D - d2 * 2.0D && double2 >= d1 - 16.0D - d2 * 2.0D && double0 <= d0 + 16.0D + d2 * 2.0D && double2 <= d1 + 16.0D + d2 * 2.0D) {
                    int k2 = MathHelper.floor_double(double0 - d2) - width * 16 - 1;
                    int k = MathHelper.floor_double(double0 + d2) - width * 16 + 1;
                    int l2 = MathHelper.floor_double(double1 - d3) - 1;
                    int l = MathHelper.floor_double(double1 + d3) + 1;
                    int i3 = MathHelper.floor_double(double2 - d2) - height * 16 - 1;
                    int i1 = MathHelper.floor_double(double2 + d2) - height * 16 + 1;

                    if (k2 < 0) {
                        k2 = 0;
                    }

                    if (k > 16) {
                        k = 16;
                    }

                    if (l2 < 1) {
                        l2 = 1;
                    }

                    if (l > 248) {
                        l = 248;
                    }

                    if (i3 < 0) {
                        i3 = 0;
                    }

                    if (i1 > 16) {
                        i1 = 16;
                    }

                    boolean flag3 = false;

                    for (int j1 = k2; !flag3 && j1 < k; ++j1) {
                        for (int k1 = i3; !flag3 && k1 < i1; ++k1) {
                            for (int l1 = l + 1; !flag3 && l1 >= l2 - 1; --l1) {
                                if (l1 >= 0 && l1 < 256) {
                                    if (isOceanBlock(chunkPrimer, j1, l1, k1, width, height)) {
                                        flag3 = true;
                                    }

                                    if (l1 != l2 - 1 && j1 != k2 && j1 != k - 1 && k1 != i3 && k1 != i1 - 1) {
                                        l1 = l2;
                                    }
                                }
                            }
                        }
                    }

                    if (!flag3) {
                        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

                        for (int j3 = k2; j3 < k; ++j3) {
                            double d10 = ((double) (j3 + width * 16) + 0.5D - double0) / d2;

                            for (int i2 = i3; i2 < i1; ++i2) {
                                double d8 = ((double) (i2 + height * 16) + 0.5D - double2) / d2;
                                boolean flag1 = false;

                                if (d10 * d10 + d8 * d8 < 1.0D) {
                                    for (int j2 = l; j2 > l2; --j2) {
                                        double d9 = ((double) (j2 - 1) + 0.5D - double1) / d3;

                                        if (d9 > -0.7D && d10 * d10 + d9 * d9 + d8 * d8 < 1.0D) {
                                            IBlockState iblockstate1 = chunkPrimer.getBlockState(j3, j2, i2);
                                            IBlockState iblockstate2 = Objects.firstNonNull(chunkPrimer.getBlockState(j3, j2 + 1, i2), BLK_AIR);

                                            if (isTopBlock(chunkPrimer, j3, j2, i2, width, height)) {
                                                flag1 = true;
                                            }

                                            digBlock(chunkPrimer, j3, j2, i2, width, height, flag1, iblockstate1, iblockstate2);
                                        }
                                    }
                                }
                            }
                        }

                        if (flag2) {
                            break;
                        }
                    }
                }
            }
        }
    }

    protected boolean canReplaceBlock(IBlockState blockstate1, IBlockState blockstate2) {
        return blockstate1.getBlock() == Blocks.STONE || (blockstate1.getBlock() == Blocks.DIRT || (blockstate1.getBlock() == Blocks.GRASS || (blockstate1.getBlock() == Blocks.HARDENED_CLAY || (blockstate1.getBlock() == Blocks.STAINED_HARDENED_CLAY || (blockstate1.getBlock() == Blocks.SANDSTONE || (blockstate1.getBlock() == Blocks.RED_SANDSTONE || (blockstate1.getBlock() == Blocks.MYCELIUM || (blockstate1.getBlock() == Blocks.SNOW_LAYER || (blockstate1.getBlock() == Blocks.SAND || blockstate1.getBlock() == Blocks.GRAVEL) && blockstate2.getMaterial() != Material.WATER))))))));
    }

    @Override
    protected void recursiveGenerate(World world, int chunkX, int chunkZ, int width, int height, ChunkPrimer chunkPrimerIn) {
        double d0 = (double) (chunkX * 16 + this.rand.nextInt(16));
        double d1 = (double) this.rand.nextInt(55 - this.rand.nextInt(50));
        double d2 = (double) (chunkZ * 16 + this.rand.nextInt(16));
        if (world.getBiomeGenForCoords(new BlockPos(d0, d1, d2)) == ModWorld.tropicBlueHoles) {
            if (this.rand.nextInt(1) == 0) {
                this.addRoom(this.rand.nextLong(), width, height, chunkPrimerIn, d0, d1, d2);
            }
        }
    }

    protected boolean isOceanBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
        net.minecraft.block.Block block = data.getBlockState(x, y, z).getBlock();
        return block == Blocks.FLOWING_WATER || block == Blocks.WATER;
    }

    private boolean isExceptionBiome(net.minecraft.world.biome.Biome biome) {
        if (biome == net.minecraft.init.Biomes.BEACH)
            return true;
        return biome == net.minecraft.init.Biomes.DESERT;
    }

    private boolean isTopBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ) {
        net.minecraft.world.biome.Biome biome = worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState state = data.getBlockState(x, y, z);
        return (isExceptionBiome(biome) ? state.getBlock() == Blocks.GRASS : state.getBlock() == biome.topBlock);
    }

    protected void digBlock(ChunkPrimer data, int x, int y, int z, int chunkX, int chunkZ, boolean foundTop, IBlockState state, IBlockState up) {
        net.minecraft.world.biome.Biome biome = worldObj.getBiomeGenForCoords(new BlockPos(x + chunkX * 16, 0, z + chunkZ * 16));
        IBlockState top = biome.topBlock;
        IBlockState filler = biome.fillerBlock;
        if (y > 61) {
            return;
        }
        if (this.canReplaceBlock(state, up) || state.getBlock() == top.getBlock() || state.getBlock() == filler.getBlock()) {
            data.setBlockState(x, y, z, BLK_WATER);

            if (up.getBlock() == Blocks.SAND) {
                data.setBlockState(x, y + 1, z, up.getValue(BlockSand.VARIANT) == BlockSand.EnumType.RED_SAND ? BLK_RED_SANDSTONE : BLK_SANDSTONE);
            }
            if (foundTop && data.getBlockState(x, y - 1, z).getBlock() == filler.getBlock()) {
                data.setBlockState(x, y - 1, z, top.getBlock().getDefaultState());
            }
        }
    }
}
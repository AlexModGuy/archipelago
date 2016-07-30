package com.github.alexthe666.archipelago.world;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenArchipelagoLakes extends WorldGenerator {
    private Block block;

    public WorldGenArchipelagoLakes(Block blockIn) {
        this.block = blockIn;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position) {
        for (position = position.add(-8, 0, -8); position.getY() > 5 && world.isAirBlock(position); position = position.down()) {
        }

        if (position.getY() <= 4) {
            return false;
        } else {
            position = position.down(4);
            boolean[] blocks = new boolean[8192];
            int runs = rand.nextInt(4) + 8;
            for (int run = 0; run < runs; ++run) {
                double sizeX = rand.nextDouble() * 6.0D + 12.0D;
                double sizeY = rand.nextDouble() * 4.0D + 2.0D;
                double sizeZ = rand.nextDouble() * 6.0D + 12.0D;
                double centerX = rand.nextDouble() * (16.0D - sizeX - 2.0D) + 1.0D + sizeX / 2.0D;
                double centerY = rand.nextDouble() * (8.0D - sizeY - 4.0D) + 2.0D + sizeY / 2.0D;
                double centerZ = rand.nextDouble() * (16.0D - sizeZ - 2.0D) + 1.0D + sizeZ / 2.0D;
                for (int offsetX = 1; offsetX < 31; ++offsetX) {
                    for (int offsetZ = 1; offsetZ < 31; ++offsetZ) {
                        for (int offsetY = 1; offsetY < 7; ++offsetY) {
                            double deltaX = (offsetX - centerX) / (sizeX / 2.0D);
                            double deltaY = (offsetY - centerY) / (sizeY / 2.0D);
                            double deltaZ = (offsetZ - centerZ) / (sizeZ / 2.0D);
                            double delta = deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
                            if (delta < 1.0D) {
                                blocks[(offsetX * 16 + offsetZ) * 8 + offsetY] = true;
                            }
                        }
                    }
                }
            }

            for (int offsetX = 0; offsetX < 32; ++offsetX) {
                for (int offsetZ = 0; offsetZ < 32; ++offsetZ) {
                    for (int offsetY = 0; offsetY < 8; ++offsetY) {
                        boolean flag = !blocks[(offsetX * 16 + offsetZ) * 8 + offsetY] && (offsetX < 31 && blocks[((offsetX + 1) * 16 + offsetZ) * 8 + offsetY] || offsetX > 0 && blocks[((offsetX - 1) * 16 + offsetZ) * 8 + offsetY] || offsetZ < 31 && blocks[(offsetX * 16 + offsetZ + 1) * 8 + offsetY] || offsetZ > 0 && blocks[(offsetX * 16 + (offsetZ - 1)) * 8 + offsetY] || offsetY < 7 && blocks[(offsetX * 16 + offsetZ) * 8 + offsetY + 1] || offsetY > 0 && blocks[(offsetX * 16 + offsetZ) * 8 + (offsetY - 1)]);
                        if (flag) {
                            Material material = world.getBlockState(position.add(offsetX, offsetY, offsetZ)).getMaterial();
                            if (offsetY >= 4 && material.isLiquid()) {
                                return false;
                            }
                            if (offsetY < 4 && !material.isSolid() && world.getBlockState(position.add(offsetX, offsetY, offsetZ)).getBlock() != this.block) {
                                return false;
                            }
                        }
                    }
                }
            }

            for (int offsetX = 0; offsetX < 32; ++offsetX) {
                for (int offsetZ = 0; offsetZ < 32; ++offsetZ) {
                    for (int offsetY = 0; offsetY < 8; ++offsetY) {
                        if (blocks[(offsetX * 16 + offsetZ) * 8 + offsetY]) {
                            world.setBlockState(position.add(offsetX, offsetY, offsetZ), offsetY >= 4 ? Blocks.AIR.getDefaultState() : this.block.getDefaultState(), 2);
                        }
                    }
                }
            }

            for (int offsetX = 0; offsetX < 32; ++offsetX) {
                for (int offsetZ = 0; offsetZ < 32; ++offsetZ) {
                    for (int offsetY = 4; offsetY < 8; ++offsetY) {
                        if (blocks[(offsetX * 16 + offsetZ) * 8 + offsetY]) {
                            BlockPos surface = position.add(offsetX, offsetY - 1, offsetZ);
                            if (world.getBlockState(surface).getBlock() == Blocks.DIRT && world.getLightFor(EnumSkyBlock.SKY, position.add(offsetX, offsetY, offsetZ)) > 0) {
                                world.setBlockState(surface, Blocks.GRASS.getDefaultState(), 2);
                            }
                        }
                    }
                }
            }

            if (this.block.getDefaultState().getMaterial() == Material.WATER) {
                for (int offsetX = 0; offsetX < 32; ++offsetX) {
                    for (int offsetZ = 0; offsetZ < 32; ++offsetZ) {
                        for (int offsetY = 0; offsetY < 8; ++offsetY) {
                            boolean flag1 = !blocks[(offsetX * 16 + offsetZ) * 8 + offsetY] && (offsetX < 31 && blocks[((offsetX + 1) * 16 + offsetZ) * 8 + offsetY] || offsetX > 0 && blocks[((offsetX - 1) * 16 + offsetZ) * 8 + offsetY] || offsetZ < 31 && blocks[(offsetX * 16 + offsetZ + 1) * 8 + offsetY] || offsetZ > 0 && blocks[(offsetX * 16 + (offsetZ - 1)) * 8 + offsetY] || offsetY < 7 && blocks[(offsetX * 16 + offsetZ) * 8 + offsetY + 1] || offsetY > 0 && blocks[(offsetX * 16 + offsetZ) * 8 + (offsetY - 1)]);
                            if (flag1 && (offsetY < 4 || rand.nextInt(2) != 0) && world.getBlockState(position.add(offsetX, offsetY, offsetZ)).getMaterial().isSolid()) {
                                world.setBlockState(position.add(offsetX, offsetY, offsetZ), Blocks.SAND.getDefaultState(), 2);
                            }
                        }
                    }
                }
                for (int offsetX = 0; offsetX < 32; ++offsetX) {
                    for (int offsetZ = 0; offsetZ < 32; ++offsetZ) {
                        int offsetY = 4;
                        if (world.canBlockFreezeWater(position.add(offsetX, offsetY, offsetZ))) {
                            world.setBlockState(position.add(offsetX, offsetY, offsetZ), Blocks.ICE.getDefaultState(), 2);
                        }
                    }
                }
            }

            return true;
        }
    }
}
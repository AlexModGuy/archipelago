package com.github.alexthe666.archipelago.world;

import com.github.alexthe666.archipelago.block.BlockGrowingSeaweed;
import com.github.alexthe666.archipelago.util.PlantEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenArchipelagoKelp extends WorldGenerator {
    public PlantEntry plantType;

    public WorldGenArchipelagoKelp(PlantEntry plantEntry) {
        this.plantType = plantEntry;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position) {
        boolean flag = false;
        if (world != null && this.plantType != null && this.plantType.block != null) {
            for (int i = 0; i < 64; ++i) {
                BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
                if (world.isBlockLoaded(blockpos) && this.plantType.canSpawnIn(world.getBiomeGenForCoords(blockpos)) && this.plantType.block.canPlaceBlockAt(world, blockpos) && blockpos.getY() < 57) {
                    if (world.getBlockState(blockpos.up()).getBlock().equals(this.plantType.block)) {
                        return false;
                    }
                    int length = Math.abs(61 - blockpos.getY() - rand.nextInt(3));
                    if (length > 6) {
                        return false;
                    }
                    world.setBlockState(blockpos, this.plantType.block.getDefaultState().withProperty(BlockGrowingSeaweed.PART, BlockGrowingSeaweed.Part.LOWER), 2);
                    for (int y = 1; y < length; y++) {
                        world.setBlockState(blockpos.up(y), this.plantType.block.getDefaultState(), 2);
                    }
                    world.setBlockState(blockpos.up(length), this.plantType.block.getDefaultState().withProperty(BlockGrowingSeaweed.PART, BlockGrowingSeaweed.Part.UPPER), 2);
                    flag = true;
                }
            }
        }
        return flag;
    }
}
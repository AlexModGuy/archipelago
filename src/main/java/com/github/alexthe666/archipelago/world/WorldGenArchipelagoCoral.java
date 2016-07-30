package com.github.alexthe666.archipelago.world;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.github.alexthe666.archipelago.util.PlantEntry;

public class WorldGenArchipelagoCoral extends WorldGenerator {
    public PlantEntry plantType;

    public WorldGenArchipelagoCoral(PlantEntry plantEntry) {
        this.plantType = plantEntry;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position) {
        boolean flag = false;
        if (world != null && plantType != null && plantType.block != null) {
            for (int i = 0; i < 64; ++i) {
                BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
                if (world.isBlockLoaded(blockpos) && plantType.canSpawnIn(world.getBiomeGenForCoords(blockpos)) && plantType.block.canPlaceBlockAt(world, blockpos)) {
                    world.setBlockState(blockpos, plantType.block.getDefaultState(), 2);
                    flag = true;
                }
            }
        }
        return flag;
    }
}
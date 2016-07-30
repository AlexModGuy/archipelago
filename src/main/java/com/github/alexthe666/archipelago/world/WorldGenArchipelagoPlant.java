package com.github.alexthe666.archipelago.world;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.github.alexthe666.archipelago.block.BlockTallPlant;
import com.github.alexthe666.archipelago.util.PlantEntry;

public class WorldGenArchipelagoPlant extends WorldGenerator {
    public PlantEntry plantType;

    public WorldGenArchipelagoPlant(PlantEntry plantEntry) {
        this.plantType = plantEntry;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos position) {
        boolean generated = false;
        if (this.plantType != null && this.plantType.block != null) {
            for (int i = 0; i < 64; ++i) {
                BlockPos pos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
                if (world.isBlockLoaded(pos) && this.plantType.canSpawnIn(world.getBiomeGenForCoords(pos)) && world.isAirBlock(pos) && (!world.provider.getHasNoSky() || pos.getY() < 254) && plantType.block.canPlaceBlockAt(world, pos)) {
                    if (this.plantType.block instanceof BlockTallPlant) {
                        world.setBlockState(pos, this.plantType.block.getDefaultState().withProperty(BlockTallPlant.HALF, BlockTallPlant.EnumBlockHalf.LOWER), 2);
                        world.setBlockState(pos.up(), this.plantType.block.getDefaultState().withProperty(BlockTallPlant.HALF, BlockTallPlant.EnumBlockHalf.UPPER), 2);
                    } else {
                        world.setBlockState(pos, this.plantType.block.getDefaultState(), 2);
                    }
                    generated = true;
                }
            }
        }
        return generated;
    }
}
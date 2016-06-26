package com.github.alexthe666.archipelago.world;

import com.github.alexthe666.archipelago.block.BlockTallPlant;
import com.github.alexthe666.archipelago.util.PlantEntry;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public class WorldGenArchipelagoPlant extends WorldGenerator {
    public PlantEntry plantType;

    public WorldGenArchipelagoPlant(PlantEntry plantEntry) {
        this.plantType = plantEntry;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        boolean flag = false;
        if (worldIn != null && plantType != null && plantType.block != null) {
            for (int i = 0; i < 64; ++i) {
                BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
                if (worldIn.isBlockLoaded(blockpos) && plantType.canSpawnIn(worldIn.getBiomeGenForCoords(blockpos)) && worldIn.isAirBlock(blockpos) && (!worldIn.provider.getHasNoSky() || blockpos.getY() < 254) && plantType.block.canPlaceBlockAt(worldIn, blockpos)) {
                    if (plantType.block instanceof BlockTallPlant) {
                        worldIn.setBlockState(blockpos, plantType.block.getDefaultState().withProperty(BlockTallPlant.HALF, BlockTallPlant.EnumBlockHalf.LOWER), 2);
                        worldIn.setBlockState(blockpos.up(), plantType.block.getDefaultState().withProperty(BlockTallPlant.HALF, BlockTallPlant.EnumBlockHalf.UPPER), 2);
                    } else {
                        worldIn.setBlockState(blockpos, plantType.block.getDefaultState(), 2);
                    }
                    flag = true;
                }
            }
        }
        return flag;
    }
}
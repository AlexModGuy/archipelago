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
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		boolean flag = false;
		if (worldIn != null && plantType != null && plantType.block != null) {
			for (int i = 0; i < 64; ++i) {
				BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
				if (worldIn.isBlockLoaded(blockpos) && plantType.canSpawnIn(worldIn.getBiomeGenForCoords(blockpos)) && ((BlockGrowingSeaweed) plantType.block).canPlaceBlockAt(worldIn, blockpos)) {
					if (worldIn.getBlockState(blockpos.up()).getBlock().equals(plantType.block))
						return false;
					int length = Math.abs(61 - blockpos.getY() - rand.nextInt(3));
					if (length > 6)
						return false;
					worldIn.setBlockState(blockpos, plantType.block.getDefaultState().withProperty(BlockGrowingSeaweed.PART, BlockGrowingSeaweed.Part.LOWER), 2);
					for (int y = 1; y < length; y++) {
						worldIn.setBlockState(blockpos.up(y), plantType.block.getDefaultState(), 2);
					}
					worldIn.setBlockState(blockpos.up(length), plantType.block.getDefaultState().withProperty(BlockGrowingSeaweed.PART, BlockGrowingSeaweed.Part.UPPER), 2);
					flag = true;
				}
			}
		}
		return flag;
	}
}
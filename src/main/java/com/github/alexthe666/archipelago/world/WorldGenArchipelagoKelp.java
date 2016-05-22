package com.github.alexthe666.archipelago.world;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import com.github.alexthe666.archipelago.block.BlockGrowingSeaweed;
import com.github.alexthe666.archipelago.block.BlockShortCoral;
import com.github.alexthe666.archipelago.util.PlantEntry;

public class WorldGenArchipelagoKelp extends WorldGenerator
{
	public PlantEntry plantType;

	public WorldGenArchipelagoKelp(PlantEntry plantEntry){
		this.plantType = plantEntry;
	}

	public boolean generate(World worldIn, Random rand, BlockPos position){
		boolean flag = false;
		if(position.getY() < 45) return false;
		if(worldIn != null && plantType != null && plantType.block != null){
			for (int i = 0; i < 64; ++i){
				BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
				if (worldIn.isBlockLoaded(blockpos) && plantType.canSpawnIn(worldIn.getBiomeGenForCoords(blockpos)) && ((BlockGrowingSeaweed)plantType.block).canPlaceBlockAt(worldIn, blockpos)){
					int length = Math.abs(54 - position.getY() - rand.nextInt(3));
					if(worldIn.getBlockState(position.down(1)) != this){
						worldIn.setBlockState(blockpos, plantType.block.getStateFromMeta(2), 2);
						for(int y = 1; y < length - 1; y++){
							worldIn.setBlockState(blockpos.up(y), plantType.block.getStateFromMeta(1), 2);
						}
						worldIn.setBlockState(blockpos.up(length - 1), plantType.block.getStateFromMeta(0), 2);
						flag = true;
					}else{
						flag = false;
					}
				}
			}
		}
		return flag;
	}
}
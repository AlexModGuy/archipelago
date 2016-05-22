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
		if(worldIn != null && plantType != null && plantType.block != null){
			for (int i = 0; i < 64; ++i){
				BlockPos blockpos = position.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
				if (worldIn.isBlockLoaded(blockpos) && plantType.canSpawnIn(worldIn.getBiomeGenForCoords(blockpos)) && ((BlockShortCoral)plantType.block).canPlaceBlockAt(worldIn, blockpos)){
					int length = Math.abs(62 - position.getY() - rand.nextInt(3));
					for(int y = blockpos.getY(); y < blockpos.getY() + length; y++){
						if(y == blockpos.getY()){
							worldIn.setBlockState(blockpos, plantType.block.getDefaultState().withProperty(BlockGrowingSeaweed.PART, BlockGrowingSeaweed.EnumBlockPart.LOWER), 2);
						}
						else if(y == blockpos.getY() + length){
							worldIn.setBlockState(blockpos, plantType.block.getDefaultState().withProperty(BlockGrowingSeaweed.PART, BlockGrowingSeaweed.EnumBlockPart.UPPER), 2);
						}
						else{
							worldIn.setBlockState(blockpos, plantType.block.getDefaultState().withProperty(BlockGrowingSeaweed.PART, BlockGrowingSeaweed.EnumBlockPart.MIDDLE), 2);

						}
					}
					
					flag = true;
				}
			}
		}
		return flag;
	}
}
package com.github.alexthe666.archipelago.world.tree;

import java.util.Random;

import com.github.alexthe666.archipelago.enums.EnumTrees;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCanaryIslandDatePalm extends BasicTreeGen {

	public WorldGenCanaryIslandDatePalm() {
		this.leavesBlock = EnumTrees.CANARY_ISLAND_DATE_PALM.leaves.getDefaultState();
		this.logBlock = EnumTrees.CANARY_ISLAND_DATE_PALM.log.getDefaultState();
	}

	@Override
	public boolean generateTree(World world, Random rand, BlockPos position) {
		int maxHeight = 7 + rand.nextInt(6);
		for (int height = 0; height < maxHeight; height++) {
			setBlockState(world, position.up(height), logBlock);
		}
		for (int palmX = -1; palmX < 2; palmX++) {
			for (int palmY = -1; palmY < 1; palmY++) {
				for (int palmZ = -1; palmZ < 2; palmZ++) {
					setBlockState(world, position.add(palmX, maxHeight + 1 + palmY, palmZ), leavesBlock);
				}
			}
		}
		setBlockState(world, position.up(maxHeight + 2), leavesBlock);
		setBlockState(world, position.up(maxHeight + 3), leavesBlock);
		setBlockState(world, position.up(maxHeight + 4), leavesBlock);
		
		
		setBlockState(world, position.add(1, maxHeight + 2, 0), leavesBlock);
		setBlockState(world, position.add(-1, maxHeight + 2, 0), leavesBlock);
		setBlockState(world, position.add(0, maxHeight + 2, 1), leavesBlock);
		setBlockState(world, position.add(0, maxHeight + 2, -1), leavesBlock);

		setBlockState(world, position.add(2, maxHeight + 2, 0), leavesBlock);
		setBlockState(world, position.add(-2, maxHeight + 2, 0), leavesBlock);
		setBlockState(world, position.add(0, maxHeight + 2, 2), leavesBlock);
		setBlockState(world, position.add(0, maxHeight + 2, -2), leavesBlock);
		
		setBlockState(world, position.add(-2, maxHeight + 3, 0), leavesBlock);
		setBlockState(world, position.add(-3, maxHeight + 3, 0), leavesBlock);
		setBlockState(world, position.add(-4, maxHeight + 2, 0), leavesBlock);

		setBlockState(world, position.add(2, maxHeight + 3, 0), leavesBlock);
		setBlockState(world, position.add(3, maxHeight + 3, 0), leavesBlock);
		setBlockState(world, position.add(4, maxHeight + 2, 0), leavesBlock);
		
		setBlockState(world, position.add(0, maxHeight + 3, 2), leavesBlock);
		setBlockState(world, position.add(0, maxHeight + 3, 3), leavesBlock);
		setBlockState(world, position.add(0, maxHeight + 2, 4), leavesBlock);
		
		setBlockState(world, position.add(0, maxHeight + 3, -2), leavesBlock);
		setBlockState(world, position.add(0, maxHeight + 3, -3), leavesBlock);
		setBlockState(world, position.add(0, maxHeight + 2, -4), leavesBlock);
		
		setBlockState(world, position.add(2, maxHeight, 0), leavesBlock);
		setBlockState(world, position.add(-2, maxHeight, 0), leavesBlock);
		setBlockState(world, position.add(0, maxHeight, 2), leavesBlock);
		setBlockState(world, position.add(0, maxHeight, -2), leavesBlock);
		
		setBlockState(world, position.add(3, maxHeight - 1, 0), leavesBlock);
		setBlockState(world, position.add(-3, maxHeight - 1, 0), leavesBlock);
		setBlockState(world, position.add(0, maxHeight - 1, 3), leavesBlock);
		setBlockState(world, position.add(0, maxHeight - 1, -3), leavesBlock);
		setBlockState(world, position.add(3, maxHeight - 2, 0), leavesBlock);
		setBlockState(world, position.add(-3, maxHeight - 2, 0), leavesBlock);
		setBlockState(world, position.add(0, maxHeight - 2, 3), leavesBlock);
		setBlockState(world, position.add(0, maxHeight - 2, -3), leavesBlock);
		if(rand.nextBoolean()){
			setBlockState(world, position.add(3, maxHeight - 3, 0), leavesBlock);
			setBlockState(world, position.add(-3, maxHeight - 3, 0), leavesBlock);
			setBlockState(world, position.add(0, maxHeight - 3, 3), leavesBlock);
			setBlockState(world, position.add(0, maxHeight - 3, -3), leavesBlock);
		}
		setBlockState(world, position.add(2, maxHeight + 1, 2), leavesBlock);
		setBlockState(world, position.add(2, maxHeight + 2, 2), leavesBlock);
		setBlockState(world, position.add(-2, maxHeight + 1, 2), leavesBlock);
		setBlockState(world, position.add(-2, maxHeight + 2, 2), leavesBlock);
		setBlockState(world, position.add(2, maxHeight + 1, -2), leavesBlock);
		setBlockState(world, position.add(2, maxHeight + 2, -2), leavesBlock);
		setBlockState(world, position.add(-2, maxHeight + 1, -2), leavesBlock);
		setBlockState(world, position.add(-2, maxHeight + 2, -2), leavesBlock);
		
		setBlockState(world, position.add(3, maxHeight - 1, 3), leavesBlock);
		setBlockState(world, position.add(3, maxHeight, 3), leavesBlock);
		setBlockState(world, position.add(3, maxHeight + 1, 3), leavesBlock);

		setBlockState(world, position.add(-3, maxHeight - 1, 3), leavesBlock);
		setBlockState(world, position.add(-3, maxHeight, 3), leavesBlock);
		setBlockState(world, position.add(-3, maxHeight + 1, 3), leavesBlock);
		
		setBlockState(world, position.add(3, maxHeight - 1, -3), leavesBlock);
		setBlockState(world, position.add(3, maxHeight, -3), leavesBlock);
		setBlockState(world, position.add(3, maxHeight + 1, -3), leavesBlock);
		
		setBlockState(world, position.add(-3, maxHeight - 1, -3), leavesBlock);
		setBlockState(world, position.add(-3, maxHeight, -3), leavesBlock);
		setBlockState(world, position.add(-3, maxHeight + 1, -3), leavesBlock);
		return true;
	}

}

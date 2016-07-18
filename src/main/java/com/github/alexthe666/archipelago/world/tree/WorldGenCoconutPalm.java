package com.github.alexthe666.archipelago.world.tree;

import java.util.Random;

import com.github.alexthe666.archipelago.enums.EnumTrees;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCoconutPalm extends BasicTreeGen {

	public WorldGenCoconutPalm() {
		this.leavesBlock = EnumTrees.COCONUT_PALM.leaves.getDefaultState();
		this.logBlock = EnumTrees.COCONUT_PALM.log.getDefaultState();
	}

	@Override
	public boolean generateTree(World world, Random rand, BlockPos position) {
		int maxHeight = 7 + rand.nextInt(2);
		rotation = rand.nextInt(3);
		System.out.println(rotation);
		int additiveheight = 0;
		for (int height = 0; height < maxHeight; height++) {
			if (additiveheight < 3) {
				if (height == 4 && additiveheight == 0) {
					additiveheight++;
				} else if (height == 6 && additiveheight == 1) {
					additiveheight++;
				} else if (height == 7 && additiveheight == 2) {
					additiveheight++;
				}
			}
			setBlockState(world, position.up(height + 1).north(additiveheight), logBlock);
		}
		BlockPos top = position.up(maxHeight + 1).north(additiveheight);
		setBlockState(world, top, logBlock);
		setBlockState(world, top.up(), leavesBlock);
		setBlockState(world, top.add(1, 0, 0), leavesBlock);
		setBlockState(world, top.add(-1, 0, 0), leavesBlock);
		setBlockState(world, top.add(2, 0, 0), leavesBlock);
		setBlockState(world, top.add(-2, 0, 0), leavesBlock);
		setBlockState(world, top.add(0, 0, 1), leavesBlock);
		setBlockState(world, top.add(0, 0, -1), leavesBlock);
		setBlockState(world, top.add(0, 0, 2), leavesBlock);
		setBlockState(world, top.add(0, 0, -2), leavesBlock);
		setBlockState(world, top.add(0, -1, 3), leavesBlock);
		setBlockState(world, top.add(0, -1, -3), leavesBlock);
		setBlockState(world, top.add(3, -1, 0), leavesBlock);
		setBlockState(world, top.add(-3, -1, 0), leavesBlock);
		if (rand.nextBoolean()) {
			setBlockState(world, top.add(0, -2, 3), leavesBlock);
			setBlockState(world, top.add(0, -2, -3), leavesBlock);
			setBlockState(world, top.add(3, -2, 0), leavesBlock);
			setBlockState(world, top.add(-3, -2, 0), leavesBlock);
		}
		setBlockState(world, top.add(-1, 1, -1), leavesBlock);
		setBlockState(world, top.add(1, 1, 1), leavesBlock);
		setBlockState(world, top.add(-1, 1, 1), leavesBlock);
		setBlockState(world, top.add(1, 1, -1), leavesBlock);
		setBlockState(world, top.add(-2, 1, -2), leavesBlock);
		setBlockState(world, top.add(2, 1, 2), leavesBlock);
		setBlockState(world, top.add(-2, 1, 2), leavesBlock);
		setBlockState(world, top.add(2, 1, -2), leavesBlock);
		setBlockState(world, top.add(-3, 0, -3), leavesBlock);
		setBlockState(world, top.add(3, 0, 3), leavesBlock);
		setBlockState(world, top.add(-3, 0, 3), leavesBlock);
		setBlockState(world, top.add(3, 0, -3), leavesBlock);
		
		setBlockState(world, top.add(1, 2, 0), leavesBlock);
		setBlockState(world, top.add(2, 2, 0), leavesBlock);
		setBlockState(world, top.add(3, 3, 0), leavesBlock);
		setBlockState(world, top.add(4, 3, 0), leavesBlock);
		setBlockState(world, top.add(-1, 2, 0), leavesBlock);
		setBlockState(world, top.add(-2, 2, 0), leavesBlock);
		setBlockState(world, top.add(-3, 3, 0), leavesBlock);
		setBlockState(world, top.add(-4, 3, 0), leavesBlock);
		setBlockState(world, top.add(0, 2, 1), leavesBlock);
		setBlockState(world, top.add(0, 2, 2), leavesBlock);
		setBlockState(world, top.add(0, 3, 3), leavesBlock);
		setBlockState(world, top.add(0, 3, 4), leavesBlock);
		setBlockState(world, top.add(0, 2, -1), leavesBlock);
		setBlockState(world, top.add(0, 2, -2), leavesBlock);
		setBlockState(world, top.add(0, 3, -3), leavesBlock);
		setBlockState(world, top.add(0, 3, -4), leavesBlock);
		return true;
	}

}

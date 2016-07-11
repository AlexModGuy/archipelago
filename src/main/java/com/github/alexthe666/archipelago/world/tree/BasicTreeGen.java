package com.github.alexthe666.archipelago.world.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class BasicTreeGen extends WorldGenerator {

	public IBlockState logBlock;
	public IBlockState leavesBlock;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		int degreerotation_axis = rand.nextInt(3);
		switch(degreerotation_axis){
		case 0:
			return generateTree(worldIn, rand, position);
		case 1:
			return generateTree(worldIn, rand, new BlockPos(position.getZ(), position.getY(), -position.getX()));
		case 2:
			return generateTree(worldIn, rand, new BlockPos(-position.getX(), position.getY(), position.getZ()));
		case 3:
			return generateTree(worldIn, rand, new BlockPos(-position.getZ(), position.getY(), -position.getX()));
 		}
		return true;
	}

	public abstract boolean generateTree(World worldIn, Random rand, BlockPos position);
}
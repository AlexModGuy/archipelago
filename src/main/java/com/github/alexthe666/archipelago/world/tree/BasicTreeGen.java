package com.github.alexthe666.archipelago.world.tree;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class BasicTreeGen extends WorldGenerator {

	public IBlockState logBlock;
	public IBlockState leavesBlock;
	public int rotation;

	@Override
	public boolean generate(World worldIn, Random rand, BlockPos position) {
		rotation = rand.nextInt(3);
		return generateTree(worldIn, rand, position);
	}

	public abstract boolean generateTree(World worldIn, Random rand, BlockPos position);

	public void setBlockState(World world, BlockPos position, IBlockState blockstate) {
		BlockPos addition = new BlockPos(0, 0, 0);
		switch (rotation) {
		case 0:
			break;
		case 1:
			addition = new BlockPos(position.getZ(), position.getY(), -position.getX());
			break;
		case 2:
			addition = new BlockPos(-position.getX(), position.getY(), position.getZ());
			break;
		case 3:
			addition = new BlockPos(-position.getZ(), position.getY(), -position.getX());
			break;
		}
		world.setBlockState(position, blockstate);
	}
}
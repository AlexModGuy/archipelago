package com.github.alexthe666.archipelago.world.tree;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import java.util.Random;

public abstract class BasicTreeGen extends WorldGenerator {

    public IBlockState logBlock;
    public IBlockState leavesBlock;
    public int rotation;
    private BlockPos center;

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        center = position;
        rotation = rand.nextInt(3);
        return generateTree(worldIn, rand, position);
    }

    public abstract boolean generateTree(World worldIn, Random rand, BlockPos position);

    public void setBlockState(World world, BlockPos position, IBlockState blockstate) {
        BlockPos offset = position.subtract(center);
        switch (rotation) {
            case 0:
                break;
            case 1:
                offset = new BlockPos(offset.getZ(), offset.getY(), -offset.getX());
                break;
            case 2:
                offset = new BlockPos(-offset.getX(), offset.getY(), offset.getZ());
                break;
            case 3:
                offset = new BlockPos(-offset.getZ(), offset.getY(), -offset.getX());
                break;
        }
        world.setBlockState(center.add(offset), blockstate);
    }
}
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
    public boolean generate(World world, Random rand, BlockPos position) {
        center = position;
        rotation = rand.nextInt(3);
        return generateTree(world, rand, position);
    }

    public abstract boolean generateTree(World world, Random rand, BlockPos position);

    public void setBlockState(World world, BlockPos position, IBlockState state) {
        world.setBlockState(this.getRotatedPosition(position), state);
    }

    protected BlockPos getRotatedPosition(BlockPos position) {
        BlockPos offset = position.subtract(center);
        if (offset.getX() == 0 && offset.getZ() == 0) {
            return position;
        }
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
        return center.add(offset);
    }

    protected void generateLeafClump(World world, BlockPos pos, double size) {
        int blockRadius = (int) Math.ceil(size);
        for (int x = -blockRadius; x < blockRadius; x++) {
            for (int y = -blockRadius; y < blockRadius; y++) {
                for (int z = -blockRadius; z < blockRadius; z++) {
                    if (Math.abs(x * x + y * y + z * z) <= size) {
                        BlockPos leafPos = pos.add(x, y, z);
                        if (world.isAirBlock(this.getRotatedPosition(leafPos))) {
                            this.setBlockState(world, leafPos, this.leavesBlock);
                        }
                    }
                }
            }
        }
    }
}